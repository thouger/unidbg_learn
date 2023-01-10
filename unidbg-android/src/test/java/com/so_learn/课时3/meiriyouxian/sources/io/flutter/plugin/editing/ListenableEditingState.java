package io.flutter.plugin.editing;

import android.text.Editable;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import io.flutter.Log;
import io.flutter.embedding.engine.systemchannels.TextInputChannel;
import java.util.ArrayList;
import java.util.Iterator;

/* access modifiers changed from: package-private */
public class ListenableEditingState extends SpannableStringBuilder {
    private static final String TAG = "ListenableEditingState";
    private int mBatchEditNestDepth = 0;
    private int mChangeNotificationDepth = 0;
    private int mComposingEndWhenBeginBatchEdit;
    private int mComposingStartWhenBeginBatchEdit;
    private BaseInputConnection mDummyConnection;
    private ArrayList<EditingStateWatcher> mListeners = new ArrayList<>();
    private ArrayList<EditingStateWatcher> mPendingListeners = new ArrayList<>();
    private int mSelectionEndWhenBeginBatchEdit;
    private int mSelectionStartWhenBeginBatchEdit;
    private String mTextWhenBeginBatchEdit;
    private String mToStringCache;

    /* access modifiers changed from: package-private */
    public interface EditingStateWatcher {
        void didChangeEditingState(boolean z, boolean z2, boolean z3);
    }

    public ListenableEditingState(TextInputChannel.TextEditState textEditState, View view) {
        if (textEditState != null) {
            setEditingState(textEditState);
        }
        this.mDummyConnection = new AnonymousClass1(view, true, this);
    }

    /* renamed from: io.flutter.plugin.editing.ListenableEditingState$1  reason: invalid class name */
    class AnonymousClass1 extends BaseInputConnection {
        final /* synthetic */ Editable val$self;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass1(View view, boolean z, Editable editable) {
            super(view, z);
            this.val$self = editable;
        }

        @Override // android.view.inputmethod.BaseInputConnection
        public Editable getEditable() {
            return this.val$self;
        }
    }

    public void beginBatchEdit() {
        this.mBatchEditNestDepth++;
        if (this.mChangeNotificationDepth > 0) {
            Log.e(TAG, "editing state should not be changed in a listener callback");
        }
        if (this.mBatchEditNestDepth == 1 && !this.mListeners.isEmpty()) {
            this.mTextWhenBeginBatchEdit = toString();
            this.mSelectionStartWhenBeginBatchEdit = getSelectionStart();
            this.mSelectionEndWhenBeginBatchEdit = getSelectionEnd();
            this.mComposingStartWhenBeginBatchEdit = getComposingStart();
            this.mComposingEndWhenBeginBatchEdit = getComposingEnd();
        }
    }

    public void endBatchEdit() {
        int i = this.mBatchEditNestDepth;
        if (i == 0) {
            Log.e(TAG, "endBatchEdit called without a matching beginBatchEdit");
            return;
        }
        if (i == 1) {
            Iterator<EditingStateWatcher> it2 = this.mPendingListeners.iterator();
            while (it2.hasNext()) {
                notifyListener(it2.next(), true, true, true);
            }
            if (!this.mListeners.isEmpty()) {
                Log.v(TAG, "didFinishBatchEdit with " + String.valueOf(this.mListeners.size()) + " listener(s)");
                boolean equals = toString().equals(this.mTextWhenBeginBatchEdit) ^ true;
                boolean z = false;
                boolean z2 = (this.mSelectionStartWhenBeginBatchEdit == getSelectionStart() && this.mSelectionEndWhenBeginBatchEdit == getSelectionEnd()) ? false : true;
                if (!(this.mComposingStartWhenBeginBatchEdit == getComposingStart() && this.mComposingEndWhenBeginBatchEdit == getComposingEnd())) {
                    z = true;
                }
                notifyListenersIfNeeded(equals, z2, z);
            }
        }
        this.mListeners.addAll(this.mPendingListeners);
        this.mPendingListeners.clear();
        this.mBatchEditNestDepth--;
    }

    public void setComposingRange(int i, int i2) {
        if (i < 0 || i >= i2) {
            BaseInputConnection.removeComposingSpans(this);
        } else {
            this.mDummyConnection.setComposingRegion(i, i2);
        }
    }

    public void setEditingState(TextInputChannel.TextEditState textEditState) {
        beginBatchEdit();
        replace(0, length(), (CharSequence) textEditState.text);
        if (textEditState.hasSelection()) {
            Selection.setSelection(this, textEditState.selectionStart, textEditState.selectionEnd);
        } else {
            Selection.removeSelection(this);
        }
        setComposingRange(textEditState.composingStart, textEditState.composingEnd);
        endBatchEdit();
    }

    public void addEditingStateListener(EditingStateWatcher editingStateWatcher) {
        if (this.mChangeNotificationDepth > 0) {
            Log.e(TAG, "adding a listener " + editingStateWatcher.toString() + " in a listener callback");
        }
        if (this.mBatchEditNestDepth > 0) {
            Log.w(TAG, "a listener was added to EditingState while a batch edit was in progress");
            this.mPendingListeners.add(editingStateWatcher);
            return;
        }
        this.mListeners.add(editingStateWatcher);
    }

    public void removeEditingStateListener(EditingStateWatcher editingStateWatcher) {
        if (this.mChangeNotificationDepth > 0) {
            Log.e(TAG, "removing a listener " + editingStateWatcher.toString() + " in a listener callback");
        }
        this.mListeners.remove(editingStateWatcher);
        if (this.mBatchEditNestDepth > 0) {
            this.mPendingListeners.remove(editingStateWatcher);
        }
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public SpannableStringBuilder replace(int i, int i2, CharSequence charSequence, int i3, int i4) {
        if (this.mChangeNotificationDepth > 0) {
            Log.e(TAG, "editing state should not be changed in a listener callback");
        }
        int i5 = i2 - i;
        boolean z = true;
        boolean z2 = i5 != i4 - i3;
        for (int i6 = 0; i6 < i5 && !z2; i6++) {
            z2 |= charAt(i + i6) != charSequence.charAt(i3 + i6);
        }
        if (z2) {
            this.mToStringCache = null;
        }
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        int composingStart = getComposingStart();
        int composingEnd = getComposingEnd();
        SpannableStringBuilder replace = super.replace(i, i2, charSequence, i3, i4);
        if (this.mBatchEditNestDepth > 0) {
            return replace;
        }
        boolean z3 = (getSelectionStart() == selectionStart && getSelectionEnd() == selectionEnd) ? false : true;
        if (getComposingStart() == composingStart && getComposingEnd() == composingEnd) {
            z = false;
        }
        notifyListenersIfNeeded(z2, z3, z);
        return replace;
    }

    private void notifyListener(EditingStateWatcher editingStateWatcher, boolean z, boolean z2, boolean z3) {
        this.mChangeNotificationDepth++;
        editingStateWatcher.didChangeEditingState(z, z2, z3);
        this.mChangeNotificationDepth--;
    }

    private void notifyListenersIfNeeded(boolean z, boolean z2, boolean z3) {
        if (z || z2 || z3) {
            Iterator<EditingStateWatcher> it2 = this.mListeners.iterator();
            while (it2.hasNext()) {
                notifyListener(it2.next(), z, z2, z3);
            }
        }
    }

    public final int getSelectionStart() {
        return Selection.getSelectionStart(this);
    }

    public final int getSelectionEnd() {
        return Selection.getSelectionEnd(this);
    }

    public final int getComposingStart() {
        return BaseInputConnection.getComposingSpanStart(this);
    }

    public final int getComposingEnd() {
        return BaseInputConnection.getComposingSpanEnd(this);
    }

    @Override // android.text.SpannableStringBuilder, java.lang.CharSequence, java.lang.Object
    public String toString() {
        String str = this.mToStringCache;
        if (str != null) {
            return str;
        }
        String spannableStringBuilder = super.toString();
        this.mToStringCache = spannableStringBuilder;
        return spannableStringBuilder;
    }
}
