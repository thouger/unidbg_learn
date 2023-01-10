package io.flutter.plugins.webviewflutter;

import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/* access modifiers changed from: package-private */
public final class ThreadedInputConnectionProxyAdapterView extends View {
    private InputConnection cachedConnection;
    final View containerView;
    final Handler imeHandler;
    private boolean isLocked = false;
    final View rootView;
    final View targetView;
    private boolean triggerDelayed = true;
    final IBinder windowToken;

    @Override // android.view.View
    public boolean checkInputConnectionProxy(View view) {
        return true;
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return true;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    @Override // android.view.View
    public boolean onCheckIsTextEditor() {
        return true;
    }

    ThreadedInputConnectionProxyAdapterView(View view, View view2, Handler handler) {
        super(view.getContext());
        this.imeHandler = handler;
        this.containerView = view;
        this.targetView = view2;
        this.windowToken = view.getWindowToken();
        this.rootView = view.getRootView();
        setFocusable(true);
        setFocusableInTouchMode(true);
        setVisibility(0);
    }

    /* access modifiers changed from: package-private */
    public boolean isTriggerDelayed() {
        return this.triggerDelayed;
    }

    /* access modifiers changed from: package-private */
    public void setLocked(boolean z) {
        this.isLocked = z;
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnection;
        this.triggerDelayed = false;
        if (this.isLocked) {
            inputConnection = this.cachedConnection;
        } else {
            inputConnection = this.targetView.onCreateInputConnection(editorInfo);
        }
        this.triggerDelayed = true;
        this.cachedConnection = inputConnection;
        return inputConnection;
    }

    @Override // android.view.View
    public View getRootView() {
        return this.rootView;
    }

    @Override // android.view.View
    public IBinder getWindowToken() {
        return this.windowToken;
    }

    @Override // android.view.View
    public Handler getHandler() {
        return this.imeHandler;
    }
}
