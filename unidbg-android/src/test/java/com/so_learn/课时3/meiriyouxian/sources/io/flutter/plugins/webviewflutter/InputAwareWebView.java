package io.flutter.plugins.webviewflutter;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.ThreadedRenderer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ListPopupWindow;

/* access modifiers changed from: package-private */
public final class InputAwareWebView extends WebView {
    private static final String TAG = "InputAwareWebView";
    private View containerView;
    private ThreadedInputConnectionProxyAdapterView proxyAdapterView;
    private View threadedInputConnectionProxyView;

    InputAwareWebView(Context context, View view) {
        super(context);
        this.containerView = view;
    }

    /* access modifiers changed from: package-private */
    public void setContainerView(View view) {
        this.containerView = view;
        if (this.proxyAdapterView != null) {
            Log.w(TAG, "The containerView has changed while the proxyAdapterView exists.");
            if (view != null) {
                setInputConnectionTarget(this.proxyAdapterView);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void lockInputConnection() {
        ThreadedInputConnectionProxyAdapterView threadedInputConnectionProxyAdapterView = this.proxyAdapterView;
        if (threadedInputConnectionProxyAdapterView != null) {
            threadedInputConnectionProxyAdapterView.setLocked(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void unlockInputConnection() {
        ThreadedInputConnectionProxyAdapterView threadedInputConnectionProxyAdapterView = this.proxyAdapterView;
        if (threadedInputConnectionProxyAdapterView != null) {
            threadedInputConnectionProxyAdapterView.setLocked(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void dispose() {
        resetInputConnection();
    }

    @Override // android.view.View
    public boolean checkInputConnectionProxy(View view) {
        View view2 = this.threadedInputConnectionProxyView;
        this.threadedInputConnectionProxyView = view;
        if (view2 == view) {
            return super.checkInputConnectionProxy(view);
        }
        View view3 = this.containerView;
        if (view3 == null) {
            Log.e(TAG, "Can't create a proxy view because there's no container view. Text input may not work.");
            return super.checkInputConnectionProxy(view);
        }
        this.proxyAdapterView = new ThreadedInputConnectionProxyAdapterView(view3, view, view.getHandler());
        setInputConnectionTarget(this.proxyAdapterView);
        return super.checkInputConnectionProxy(view);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        super.clearFocus();
        resetInputConnection();
    }

    private void resetInputConnection() {
        if (this.proxyAdapterView != null) {
            View view = this.containerView;
            if (view == null) {
                Log.e(TAG, "Can't reset the input connection to the container view because there is none.");
            } else {
                setInputConnectionTarget(view);
            }
        }
    }

    private void setInputConnectionTarget(View view) {
        if (this.containerView == null) {
            Log.e(TAG, "Can't set the input connection target because there is no containerView to use as a handler.");
            return;
        }
        view.requestFocus();
        this.containerView.post(new AnonymousClass1(view));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugins.webviewflutter.InputAwareWebView$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ View val$targetView;

        AnonymousClass1(View view) {
            this.val$targetView = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$targetView.onWindowFocusChanged(true);
            ((InputMethodManager) InputAwareWebView.this.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).isActive(InputAwareWebView.this.containerView);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.webkit.WebView, android.view.View
    public void onFocusChanged(boolean z, int i, Rect rect) {
        if (Build.VERSION.SDK_INT >= 28 || !isCalledFromListPopupWindowShow() || z) {
            super.onFocusChanged(z, i, rect);
        }
    }

    private boolean isCalledFromListPopupWindowShow() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTrace.length; i++) {
            if (stackTrace[i].getClassName().equals(ListPopupWindow.class.getCanonicalName()) && stackTrace[i].getMethodName().equals(ThreadedRenderer.OVERDRAW_PROPERTY_SHOW)) {
                return true;
            }
        }
        return false;
    }
}
