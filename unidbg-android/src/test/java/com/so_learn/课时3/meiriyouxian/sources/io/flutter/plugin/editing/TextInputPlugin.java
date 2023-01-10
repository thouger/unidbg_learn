package io.flutter.plugin.editing;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewStructure;
import android.view.WindowInsets;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import io.flutter.Log;
import io.flutter.embedding.android.AndroidKeyProcessor;
import io.flutter.embedding.engine.systemchannels.TextInputChannel;
import io.flutter.plugin.editing.ListenableEditingState;
import io.flutter.plugin.platform.PlatformViewsController;
import java.util.HashMap;

public class TextInputPlugin implements ListenableEditingState.EditingStateWatcher {
    private static final String TAG = "TextInputPlugin";
    private final AutofillManager afm;
    private TextInputChannel.Configuration configuration;
    private ImeSyncDeferringInsetsCallback imeSyncCallback;
    private InputTarget inputTarget = new InputTarget(InputTarget.Type.NO_TARGET, 0);
    private boolean isInputConnectionLocked;
    private AndroidKeyProcessor keyProcessor;
    private Rect lastClientRect;
    private InputConnection lastInputConnection;
    private SparseArray<TextInputChannel.Configuration> mAutofillConfigurations;
    private ListenableEditingState mEditable;
    private final InputMethodManager mImm;
    private TextInputChannel.TextEditState mLastKnownFrameworkTextEditingState;
    private boolean mRestartInputPending;
    private final View mView;
    private PlatformViewsController platformViewsController;
    private final TextInputChannel textInputChannel;

    /* access modifiers changed from: private */
    public interface MinMax {
        void inspect(double d, double d2);
    }

    public TextInputPlugin(View view, TextInputChannel textInputChannel, PlatformViewsController platformViewsController) {
        int i = 0;
        this.mView = view;
        this.mImm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            this.afm = (AutofillManager) view.getContext().getSystemService(AutofillManager.class);
        } else {
            this.afm = null;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            i = (this.mView.getWindowSystemUiVisibility() & 2) == 0 ? 0 | WindowInsets.Type.navigationBars() : i;
            this.imeSyncCallback = new ImeSyncDeferringInsetsCallback(view, (this.mView.getWindowSystemUiVisibility() & 4) == 0 ? i | WindowInsets.Type.statusBars() : i, WindowInsets.Type.ime());
            this.imeSyncCallback.install();
        }
        this.textInputChannel = textInputChannel;
        textInputChannel.setTextInputMethodHandler(new AnonymousClass1());
        textInputChannel.requestExistingInputState();
        this.platformViewsController = platformViewsController;
        this.platformViewsController.attachTextInputPlugin(this);
    }

    /* renamed from: io.flutter.plugin.editing.TextInputPlugin$1  reason: invalid class name */
    class AnonymousClass1 implements TextInputChannel.TextInputMethodHandler {
        AnonymousClass1() {
        }

        @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
        public void show() {
            TextInputPlugin textInputPlugin = TextInputPlugin.this;
            textInputPlugin.showTextInput(textInputPlugin.mView);
        }

        @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
        public void hide() {
            TextInputPlugin textInputPlugin = TextInputPlugin.this;
            textInputPlugin.hideTextInput(textInputPlugin.mView);
        }

        @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
        public void requestAutofill() {
            TextInputPlugin.this.notifyViewEntered();
        }

        @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
        public void finishAutofillContext(boolean z) {
            if (Build.VERSION.SDK_INT >= 26 && TextInputPlugin.this.afm != null) {
                if (z) {
                    TextInputPlugin.this.afm.commit();
                } else {
                    TextInputPlugin.this.afm.cancel();
                }
            }
        }

        @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
        public void setClient(int i, TextInputChannel.Configuration configuration) {
            TextInputPlugin.this.setTextInputClient(i, configuration);
        }

        @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
        public void setPlatformViewClient(int i) {
            TextInputPlugin.this.setPlatformViewTextInputClient(i);
        }

        @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
        public void setEditingState(TextInputChannel.TextEditState textEditState) {
            TextInputPlugin textInputPlugin = TextInputPlugin.this;
            textInputPlugin.setTextInputEditingState(textInputPlugin.mView, textEditState);
        }

        @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
        public void setEditableSizeAndTransform(double d, double d2, double[] dArr) {
            TextInputPlugin.this.saveEditableSizeAndTransform(d, d2, dArr);
        }

        @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
        public void clearClient() {
            TextInputPlugin.this.clearTextInputClient();
        }

        @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
        public void sendAppPrivateCommand(String str, Bundle bundle) {
            TextInputPlugin.this.sendTextInputAppPrivateCommand(str, bundle);
        }
    }

    public InputMethodManager getInputMethodManager() {
        return this.mImm;
    }

    /* access modifiers changed from: package-private */
    public Editable getEditable() {
        return this.mEditable;
    }

    /* access modifiers changed from: package-private */
    public ImeSyncDeferringInsetsCallback getImeSyncCallback() {
        return this.imeSyncCallback;
    }

    public AndroidKeyProcessor getKeyEventProcessor() {
        return this.keyProcessor;
    }

    public void setKeyEventProcessor(AndroidKeyProcessor androidKeyProcessor) {
        this.keyProcessor = androidKeyProcessor;
    }

    public void lockPlatformViewInputConnection() {
        if (this.inputTarget.type == InputTarget.Type.PLATFORM_VIEW) {
            this.isInputConnectionLocked = true;
        }
    }

    public void unlockPlatformViewInputConnection() {
        this.isInputConnectionLocked = false;
    }

    public void destroy() {
        this.platformViewsController.detachTextInputPlugin();
        this.textInputChannel.setTextInputMethodHandler(null);
        notifyViewExited();
        ListenableEditingState listenableEditingState = this.mEditable;
        if (listenableEditingState != null) {
            listenableEditingState.removeEditingStateListener(this);
        }
        ImeSyncDeferringInsetsCallback imeSyncDeferringInsetsCallback = this.imeSyncCallback;
        if (imeSyncDeferringInsetsCallback != null) {
            imeSyncDeferringInsetsCallback.remove();
        }
    }

    private static int inputTypeFromTextInputType(TextInputChannel.InputType inputType, boolean z, boolean z2, boolean z3, TextInputChannel.TextCapitalization textCapitalization) {
        int i;
        if (inputType.type == TextInputChannel.TextInputType.DATETIME) {
            return 4;
        }
        if (inputType.type == TextInputChannel.TextInputType.NUMBER) {
            int i2 = 2;
            if (inputType.isSigned) {
                i2 = 4098;
            }
            return inputType.isDecimal ? i2 | 8192 : i2;
        } else if (inputType.type == TextInputChannel.TextInputType.PHONE) {
            return 3;
        } else {
            int i3 = 1;
            if (inputType.type == TextInputChannel.TextInputType.MULTILINE) {
                i3 = 131073;
            } else if (inputType.type == TextInputChannel.TextInputType.EMAIL_ADDRESS) {
                i3 = 33;
            } else if (inputType.type == TextInputChannel.TextInputType.URL) {
                i3 = 17;
            } else if (inputType.type == TextInputChannel.TextInputType.VISIBLE_PASSWORD) {
                i3 = 145;
            } else if (inputType.type == TextInputChannel.TextInputType.NAME) {
                i3 = 97;
            } else if (inputType.type == TextInputChannel.TextInputType.POSTAL_ADDRESS) {
                i3 = 113;
            }
            if (z) {
                i = 524288 | i3 | 128;
            } else {
                if (z2) {
                    i3 |= 32768;
                }
                i = !z3 ? 524288 | i3 : i3;
            }
            if (textCapitalization == TextInputChannel.TextCapitalization.CHARACTERS) {
                return i | 4096;
            }
            if (textCapitalization == TextInputChannel.TextCapitalization.WORDS) {
                return i | 8192;
            }
            return textCapitalization == TextInputChannel.TextCapitalization.SENTENCES ? i | 16384 : i;
        }
    }

    public InputConnection createInputConnection(View view, EditorInfo editorInfo) {
        int i;
        if (this.inputTarget.type == InputTarget.Type.NO_TARGET) {
            this.lastInputConnection = null;
            return null;
        } else if (this.inputTarget.type != InputTarget.Type.PLATFORM_VIEW) {
            editorInfo.inputType = inputTypeFromTextInputType(this.configuration.inputType, this.configuration.obscureText, this.configuration.autocorrect, this.configuration.enableSuggestions, this.configuration.textCapitalization);
            editorInfo.imeOptions = 33554432;
            if (this.configuration.inputAction == null) {
                i = (131072 & editorInfo.inputType) != 0 ? 1 : 6;
            } else {
                i = this.configuration.inputAction.intValue();
            }
            if (this.configuration.actionLabel != null) {
                editorInfo.actionLabel = this.configuration.actionLabel;
                editorInfo.actionId = i;
            }
            editorInfo.imeOptions = i | editorInfo.imeOptions;
            InputConnectionAdaptor inputConnectionAdaptor = new InputConnectionAdaptor(view, this.inputTarget.id, this.textInputChannel, this.keyProcessor, this.mEditable, editorInfo);
            editorInfo.initialSelStart = this.mEditable.getSelectionStart();
            editorInfo.initialSelEnd = this.mEditable.getSelectionEnd();
            this.lastInputConnection = inputConnectionAdaptor;
            return this.lastInputConnection;
        } else if (this.isInputConnectionLocked) {
            return this.lastInputConnection;
        } else {
            this.lastInputConnection = this.platformViewsController.getPlatformViewById(Integer.valueOf(this.inputTarget.id)).onCreateInputConnection(editorInfo);
            return this.lastInputConnection;
        }
    }

    public InputConnection getLastInputConnection() {
        return this.lastInputConnection;
    }

    public void clearPlatformViewClient(int i) {
        if (this.inputTarget.type == InputTarget.Type.PLATFORM_VIEW && this.inputTarget.id == i) {
            this.inputTarget = new InputTarget(InputTarget.Type.NO_TARGET, 0);
            hideTextInput(this.mView);
            this.mImm.restartInput(this.mView);
            this.mRestartInputPending = false;
        }
    }

    public void sendTextInputAppPrivateCommand(String str, Bundle bundle) {
        this.mImm.sendAppPrivateCommand(this.mView, str, bundle);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showTextInput(View view) {
        view.requestFocus();
        this.mImm.showSoftInput(view, 0);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void hideTextInput(View view) {
        notifyViewExited();
        this.mImm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    /* access modifiers changed from: package-private */
    public void setTextInputClient(int i, TextInputChannel.Configuration configuration) {
        notifyViewExited();
        this.inputTarget = new InputTarget(InputTarget.Type.FRAMEWORK_CLIENT, i);
        ListenableEditingState listenableEditingState = this.mEditable;
        if (listenableEditingState != null) {
            listenableEditingState.removeEditingStateListener(this);
        }
        this.mEditable = new ListenableEditingState(configuration.autofill != null ? configuration.autofill.editState : null, this.mView);
        this.configuration = configuration;
        updateAutofillConfigurationIfNeeded(configuration);
        this.mRestartInputPending = true;
        unlockPlatformViewInputConnection();
        this.lastClientRect = null;
        this.mEditable.addEditingStateListener(this);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPlatformViewTextInputClient(int i) {
        this.mView.requestFocus();
        this.inputTarget = new InputTarget(InputTarget.Type.PLATFORM_VIEW, i);
        this.mImm.restartInput(this.mView);
        this.mRestartInputPending = false;
    }

    private static boolean composingChanged(TextInputChannel.TextEditState textEditState, TextInputChannel.TextEditState textEditState2) {
        int i = textEditState.composingEnd - textEditState.composingStart;
        if (i != textEditState2.composingEnd - textEditState2.composingStart) {
            return true;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (textEditState.text.charAt(textEditState.composingStart + i2) != textEditState2.text.charAt(textEditState2.composingStart + i2)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void setTextInputEditingState(View view, TextInputChannel.TextEditState textEditState) {
        TextInputChannel.TextEditState textEditState2;
        if (!this.mRestartInputPending && (textEditState2 = this.mLastKnownFrameworkTextEditingState) != null && textEditState2.hasComposing()) {
            this.mRestartInputPending = composingChanged(this.mLastKnownFrameworkTextEditingState, textEditState);
            if (this.mRestartInputPending) {
                Log.w(TAG, "Changing the content within the the composing region may cause the input method to behave strangely, and is therefore discouraged. See https://github.com/flutter/flutter/issues/78827 for more details");
            }
        }
        this.mLastKnownFrameworkTextEditingState = textEditState;
        this.mEditable.setEditingState(textEditState);
        if (this.mRestartInputPending) {
            this.mImm.restartInput(view);
            this.mRestartInputPending = false;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void saveEditableSizeAndTransform(double d, double d2, double[] dArr) {
        double[] dArr2 = new double[4];
        boolean z = dArr[3] == 0.0d && dArr[7] == 0.0d && dArr[15] == 1.0d;
        double d3 = dArr[12] / dArr[15];
        dArr2[1] = d3;
        dArr2[0] = d3;
        double d4 = dArr[13] / dArr[15];
        dArr2[3] = d4;
        dArr2[2] = d4;
        AnonymousClass2 r14 = new AnonymousClass2(z, dArr, dArr2);
        r14.inspect(d, 0.0d);
        r14.inspect(d, d2);
        r14.inspect(0.0d, d2);
        Float valueOf = Float.valueOf(this.mView.getContext().getResources().getDisplayMetrics().density);
        this.lastClientRect = new Rect((int) (dArr2[0] * ((double) valueOf.floatValue())), (int) (dArr2[2] * ((double) valueOf.floatValue())), (int) Math.ceil(dArr2[1] * ((double) valueOf.floatValue())), (int) Math.ceil(dArr2[3] * ((double) valueOf.floatValue())));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugin.editing.TextInputPlugin$2  reason: invalid class name */
    public class AnonymousClass2 implements MinMax {
        final /* synthetic */ boolean val$isAffine;
        final /* synthetic */ double[] val$matrix;
        final /* synthetic */ double[] val$minMax;

        AnonymousClass2(boolean z, double[] dArr, double[] dArr2) {
            this.val$isAffine = z;
            this.val$matrix = dArr;
            this.val$minMax = dArr2;
        }

        @Override // io.flutter.plugin.editing.TextInputPlugin.MinMax
        public void inspect(double d, double d2) {
            double d3 = 1.0d;
            if (!this.val$isAffine) {
                double[] dArr = this.val$matrix;
                d3 = 1.0d / (((dArr[3] * d) + (dArr[7] * d2)) + dArr[15]);
            }
            double[] dArr2 = this.val$matrix;
            double d4 = ((dArr2[0] * d) + (dArr2[4] * d2) + dArr2[12]) * d3;
            double d5 = ((dArr2[1] * d) + (dArr2[5] * d2) + dArr2[13]) * d3;
            double[] dArr3 = this.val$minMax;
            if (d4 < dArr3[0]) {
                dArr3[0] = d4;
            } else if (d4 > dArr3[1]) {
                dArr3[1] = d4;
            }
            double[] dArr4 = this.val$minMax;
            if (d5 < dArr4[2]) {
                dArr4[2] = d5;
            } else if (d5 > dArr4[3]) {
                dArr4[3] = d5;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void clearTextInputClient() {
        if (this.inputTarget.type != InputTarget.Type.PLATFORM_VIEW) {
            this.mEditable.removeEditingStateListener(this);
            notifyViewExited();
            updateAutofillConfigurationIfNeeded(null);
            this.inputTarget = new InputTarget(InputTarget.Type.NO_TARGET, 0);
            unlockPlatformViewInputConnection();
            this.lastClientRect = null;
        }
    }

    /* access modifiers changed from: private */
    public static class InputTarget {
        int id;
        Type type;

        /* access modifiers changed from: package-private */
        public enum Type {
            NO_TARGET,
            FRAMEWORK_CLIENT,
            PLATFORM_VIEW
        }

        public InputTarget(Type type, int i) {
            this.type = type;
            this.id = i;
        }
    }

    @Override // io.flutter.plugin.editing.ListenableEditingState.EditingStateWatcher
    public void didChangeEditingState(boolean z, boolean z2, boolean z3) {
        if (z) {
            notifyValueChanged(this.mEditable.toString());
        }
        int selectionStart = this.mEditable.getSelectionStart();
        int selectionEnd = this.mEditable.getSelectionEnd();
        int composingStart = this.mEditable.getComposingStart();
        int composingEnd = this.mEditable.getComposingEnd();
        if (!(this.mLastKnownFrameworkTextEditingState == null || (this.mEditable.toString().equals(this.mLastKnownFrameworkTextEditingState.text) && selectionStart == this.mLastKnownFrameworkTextEditingState.selectionStart && selectionEnd == this.mLastKnownFrameworkTextEditingState.selectionEnd && composingStart == this.mLastKnownFrameworkTextEditingState.composingStart && composingEnd == this.mLastKnownFrameworkTextEditingState.composingEnd))) {
            Log.v(TAG, "send EditingState to flutter: " + this.mEditable.toString());
            this.textInputChannel.updateEditingState(this.inputTarget.id, this.mEditable.toString(), selectionStart, selectionEnd, composingStart, composingEnd);
            this.mLastKnownFrameworkTextEditingState = new TextInputChannel.TextEditState(this.mEditable.toString(), selectionStart, selectionEnd, composingStart, composingEnd);
        }
    }

    private boolean needsAutofill() {
        return this.mAutofillConfigurations != null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyViewEntered() {
        if (Build.VERSION.SDK_INT >= 26 && this.afm != null && needsAutofill()) {
            String str = this.configuration.autofill.uniqueIdentifier;
            int[] iArr = new int[2];
            this.mView.getLocationOnScreen(iArr);
            Rect rect = new Rect(this.lastClientRect);
            rect.offset(iArr[0], iArr[1]);
            this.afm.notifyViewEntered(this.mView, str.hashCode(), rect);
        }
    }

    private void notifyViewExited() {
        TextInputChannel.Configuration configuration;
        if (Build.VERSION.SDK_INT >= 26 && this.afm != null && (configuration = this.configuration) != null && configuration.autofill != null && needsAutofill()) {
            this.afm.notifyViewExited(this.mView, this.configuration.autofill.uniqueIdentifier.hashCode());
        }
    }

    private void notifyValueChanged(String str) {
        if (Build.VERSION.SDK_INT >= 26 && this.afm != null && needsAutofill()) {
            this.afm.notifyValueChanged(this.mView, this.configuration.autofill.uniqueIdentifier.hashCode(), AutofillValue.forText(str));
        }
    }

    private void updateAutofillConfigurationIfNeeded(TextInputChannel.Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 26) {
            if (configuration == null || configuration.autofill == null) {
                this.mAutofillConfigurations = null;
                return;
            }
            TextInputChannel.Configuration[] configurationArr = configuration.fields;
            this.mAutofillConfigurations = new SparseArray<>();
            if (configurationArr == null) {
                this.mAutofillConfigurations.put(configuration.autofill.uniqueIdentifier.hashCode(), configuration);
                return;
            }
            for (TextInputChannel.Configuration configuration2 : configurationArr) {
                TextInputChannel.Configuration.Autofill autofill = configuration2.autofill;
                if (autofill != null) {
                    this.mAutofillConfigurations.put(autofill.uniqueIdentifier.hashCode(), configuration2);
                    this.afm.notifyValueChanged(this.mView, autofill.uniqueIdentifier.hashCode(), AutofillValue.forText(autofill.editState.text));
                }
            }
        }
    }

    public void onProvideAutofillVirtualStructure(ViewStructure viewStructure, int i) {
        Rect rect;
        if (Build.VERSION.SDK_INT >= 26 && needsAutofill()) {
            String str = this.configuration.autofill.uniqueIdentifier;
            AutofillId autofillId = viewStructure.getAutofillId();
            for (int i2 = 0; i2 < this.mAutofillConfigurations.size(); i2++) {
                int keyAt = this.mAutofillConfigurations.keyAt(i2);
                TextInputChannel.Configuration.Autofill autofill = ((TextInputChannel.Configuration) this.mAutofillConfigurations.valueAt(i2)).autofill;
                if (autofill != null) {
                    viewStructure.addChildCount(1);
                    ViewStructure newChild = viewStructure.newChild(i2);
                    newChild.setAutofillId(autofillId, keyAt);
                    newChild.setAutofillHints(autofill.hints);
                    newChild.setAutofillType(1);
                    newChild.setVisibility(0);
                    if (str.hashCode() != keyAt || (rect = this.lastClientRect) == null) {
                        newChild.setDimens(0, 0, 0, 0, 1, 1);
                        newChild.setAutofillValue(AutofillValue.forText(autofill.editState.text));
                    } else {
                        newChild.setDimens(rect.left, this.lastClientRect.top, 0, 0, this.lastClientRect.width(), this.lastClientRect.height());
                        newChild.setAutofillValue(AutofillValue.forText(this.mEditable));
                    }
                }
            }
        }
    }

    public void autofill(SparseArray<AutofillValue> sparseArray) {
        TextInputChannel.Configuration.Autofill autofill;
        if (Build.VERSION.SDK_INT >= 26 && (autofill = this.configuration.autofill) != null) {
            HashMap<String, TextInputChannel.TextEditState> hashMap = new HashMap<>();
            for (int i = 0; i < sparseArray.size(); i++) {
                TextInputChannel.Configuration configuration = (TextInputChannel.Configuration) this.mAutofillConfigurations.get(sparseArray.keyAt(i));
                if (!(configuration == null || configuration.autofill == null)) {
                    TextInputChannel.Configuration.Autofill autofill2 = configuration.autofill;
                    String charSequence = ((AutofillValue) sparseArray.valueAt(i)).getTextValue().toString();
                    TextInputChannel.TextEditState textEditState = new TextInputChannel.TextEditState(charSequence, charSequence.length(), charSequence.length(), -1, -1);
                    if (autofill2.uniqueIdentifier.equals(autofill.uniqueIdentifier)) {
                        this.mEditable.setEditingState(textEditState);
                    } else {
                        hashMap.put(autofill2.uniqueIdentifier, textEditState);
                    }
                }
            }
            this.textInputChannel.updateEditingStateWithTag(this.inputTarget.id, hashMap);
        }
    }
}
