package io.flutter.plugin.platform;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewTreeObserver;
import io.flutter.plugin.platform.SingleViewPresentation;
import io.flutter.view.TextureRegistry;

/* access modifiers changed from: package-private */
public class VirtualDisplayController {
    private final AccessibilityEventsDelegate accessibilityEventsDelegate;
    private final Context context;
    private final int densityDpi;
    private final View.OnFocusChangeListener focusChangeListener;
    SingleViewPresentation presentation;
    private Surface surface;
    private final TextureRegistry.SurfaceTextureEntry textureEntry;
    private VirtualDisplay virtualDisplay;

    public static VirtualDisplayController create(Context context, AccessibilityEventsDelegate accessibilityEventsDelegate, PlatformViewFactory platformViewFactory, TextureRegistry.SurfaceTextureEntry surfaceTextureEntry, int i, int i2, int i3, Object obj, View.OnFocusChangeListener onFocusChangeListener) {
        surfaceTextureEntry.surfaceTexture().setDefaultBufferSize(i, i2);
        Surface surface = new Surface(surfaceTextureEntry.surfaceTexture());
        VirtualDisplay createVirtualDisplay = ((DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE)).createVirtualDisplay("flutter-vd", i, i2, context.getResources().getDisplayMetrics().densityDpi, surface, 0);
        if (createVirtualDisplay == null) {
            return null;
        }
        return new VirtualDisplayController(context, accessibilityEventsDelegate, createVirtualDisplay, platformViewFactory, surface, surfaceTextureEntry, onFocusChangeListener, i3, obj);
    }

    private VirtualDisplayController(Context context, AccessibilityEventsDelegate accessibilityEventsDelegate, VirtualDisplay virtualDisplay, PlatformViewFactory platformViewFactory, Surface surface, TextureRegistry.SurfaceTextureEntry surfaceTextureEntry, View.OnFocusChangeListener onFocusChangeListener, int i, Object obj) {
        this.context = context;
        this.accessibilityEventsDelegate = accessibilityEventsDelegate;
        this.textureEntry = surfaceTextureEntry;
        this.focusChangeListener = onFocusChangeListener;
        this.surface = surface;
        this.virtualDisplay = virtualDisplay;
        this.densityDpi = context.getResources().getDisplayMetrics().densityDpi;
        this.presentation = new SingleViewPresentation(context, this.virtualDisplay.getDisplay(), platformViewFactory, accessibilityEventsDelegate, i, obj, onFocusChangeListener);
        this.presentation.show();
    }

    public void resize(int i, int i2, Runnable runnable) {
        boolean isFocused = getView().isFocused();
        SingleViewPresentation.PresentationState detachState = this.presentation.detachState();
        this.virtualDisplay.setSurface(null);
        this.virtualDisplay.release();
        this.textureEntry.surfaceTexture().setDefaultBufferSize(i, i2);
        this.virtualDisplay = ((DisplayManager) this.context.getSystemService(Context.DISPLAY_SERVICE)).createVirtualDisplay("flutter-vd", i, i2, this.densityDpi, this.surface, 0);
        View view = getView();
        view.addOnAttachStateChangeListener(new AnonymousClass1(view, runnable));
        SingleViewPresentation singleViewPresentation = new SingleViewPresentation(this.context, this.virtualDisplay.getDisplay(), this.accessibilityEventsDelegate, detachState, this.focusChangeListener, isFocused);
        singleViewPresentation.show();
        this.presentation.cancel();
        this.presentation = singleViewPresentation;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugin.platform.VirtualDisplayController$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ View val$embeddedView;
        final /* synthetic */ Runnable val$onNewSizeFrameAvailable;

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }

        AnonymousClass1(View view, Runnable runnable) {
            this.val$embeddedView = view;
            this.val$onNewSizeFrameAvailable = runnable;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            OneTimeOnDrawListener.schedule(this.val$embeddedView, new AnonymousClass1());
            this.val$embeddedView.removeOnAttachStateChangeListener(this);
        }

        /* renamed from: io.flutter.plugin.platform.VirtualDisplayController$1$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                AnonymousClass1.this.val$embeddedView.postDelayed(AnonymousClass1.this.val$onNewSizeFrameAvailable, 128);
            }
        }
    }

    public void dispose() {
        PlatformView view = this.presentation.getView();
        this.presentation.cancel();
        this.presentation.detachState();
        view.dispose();
        this.virtualDisplay.release();
        this.textureEntry.release();
    }

    /* access modifiers changed from: package-private */
    public void onFlutterViewAttached(View view) {
        SingleViewPresentation singleViewPresentation = this.presentation;
        if (singleViewPresentation != null && singleViewPresentation.getView() != null) {
            this.presentation.getView().onFlutterViewAttached(view);
        }
    }

    /* access modifiers changed from: package-private */
    public void onFlutterViewDetached() {
        SingleViewPresentation singleViewPresentation = this.presentation;
        if (singleViewPresentation != null && singleViewPresentation.getView() != null) {
            this.presentation.getView().onFlutterViewDetached();
        }
    }

    /* access modifiers changed from: package-private */
    public void onInputConnectionLocked() {
        SingleViewPresentation singleViewPresentation = this.presentation;
        if (singleViewPresentation != null && singleViewPresentation.getView() != null) {
            this.presentation.getView().onInputConnectionLocked();
        }
    }

    /* access modifiers changed from: package-private */
    public void onInputConnectionUnlocked() {
        SingleViewPresentation singleViewPresentation = this.presentation;
        if (singleViewPresentation != null && singleViewPresentation.getView() != null) {
            this.presentation.getView().onInputConnectionUnlocked();
        }
    }

    public View getView() {
        SingleViewPresentation singleViewPresentation = this.presentation;
        if (singleViewPresentation == null) {
            return null;
        }
        return singleViewPresentation.getView().getView();
    }

    public void dispatchTouchEvent(MotionEvent motionEvent) {
        SingleViewPresentation singleViewPresentation = this.presentation;
        if (singleViewPresentation != null) {
            singleViewPresentation.dispatchTouchEvent(motionEvent);
        }
    }

    static class OneTimeOnDrawListener implements ViewTreeObserver.OnDrawListener {
        Runnable mOnDrawRunnable;
        final View mView;

        static void schedule(View view, Runnable runnable) {
            view.getViewTreeObserver().addOnDrawListener(new OneTimeOnDrawListener(view, runnable));
        }

        OneTimeOnDrawListener(View view, Runnable runnable) {
            this.mView = view;
            this.mOnDrawRunnable = runnable;
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public void onDraw() {
            Runnable runnable = this.mOnDrawRunnable;
            if (runnable != null) {
                runnable.run();
                this.mOnDrawRunnable = null;
                this.mView.post(new AnonymousClass1());
            }
        }

        /* renamed from: io.flutter.plugin.platform.VirtualDisplayController$OneTimeOnDrawListener$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                OneTimeOnDrawListener.this.mView.getViewTreeObserver().removeOnDrawListener(OneTimeOnDrawListener.this);
            }
        }
    }
}
