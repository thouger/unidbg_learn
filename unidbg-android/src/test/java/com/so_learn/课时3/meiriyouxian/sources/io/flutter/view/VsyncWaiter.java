package io.flutter.view;

import android.view.Choreographer;
import android.view.WindowManager;
import io.flutter.embedding.engine.FlutterJNI;

public class VsyncWaiter {
    private static VsyncWaiter instance;
    private final FlutterJNI.AsyncWaitForVsyncDelegate asyncWaitForVsyncDelegate = new AnonymousClass1();
    private final WindowManager windowManager;

    public static VsyncWaiter getInstance(WindowManager windowManager) {
        if (instance == null) {
            instance = new VsyncWaiter(windowManager);
        }
        return instance;
    }

    /* renamed from: io.flutter.view.VsyncWaiter$1  reason: invalid class name */
    class AnonymousClass1 implements FlutterJNI.AsyncWaitForVsyncDelegate {
        AnonymousClass1() {
        }

        @Override // io.flutter.embedding.engine.FlutterJNI.AsyncWaitForVsyncDelegate
        public void asyncWaitForVsync(long j) {
            Choreographer.getInstance().postFrameCallback(new AnonymousClass1(j));
        }

        /* renamed from: io.flutter.view.VsyncWaiter$1$1  reason: invalid class name */
        class AnonymousClass1 implements Choreographer.FrameCallback {
            final /* synthetic */ long val$cookie;

            AnonymousClass1(long j) {
                this.val$cookie = j;
            }

            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                FlutterJNI.nativeOnVsync(j, j + ((long) (1.0E9d / ((double) VsyncWaiter.this.windowManager.getDefaultDisplay().getRefreshRate()))), this.val$cookie);
            }
        }
    }

    private VsyncWaiter(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public void init() {
        FlutterJNI.setAsyncWaitForVsyncDelegate(this.asyncWaitForVsyncDelegate);
        FlutterJNI.setRefreshRateFPS(this.windowManager.getDefaultDisplay().getRefreshRate());
    }
}
