package io.flutter.embedding.engine.systemchannels;

import com.unionpay.tsmservice.data.Constant;
import io.flutter.Log;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.StandardMethodCodec;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public class PlatformViewsChannel {
    private static final String TAG = "PlatformViewsChannel";
    private final MethodChannel channel;
    private PlatformViewsHandler handler;
    private final MethodChannel.MethodCallHandler parsingHandler = new AnonymousClass1();

    public interface PlatformViewsHandler {
        void clearFocus(int i);

        void createAndroidViewForPlatformView(PlatformViewCreationRequest platformViewCreationRequest);

        long createVirtualDisplayForPlatformView(PlatformViewCreationRequest platformViewCreationRequest);

        void disposeAndroidViewForPlatformView(int i);

        void disposeVirtualDisplayForPlatformView(int i);

        void onTouch(PlatformViewTouch platformViewTouch);

        void resizePlatformView(PlatformViewResizeRequest platformViewResizeRequest, Runnable runnable);

        void setDirection(int i, int i2);
    }

    public void invokeViewFocused(int i) {
        MethodChannel methodChannel = this.channel;
        if (methodChannel != null) {
            methodChannel.invokeMethod("viewFocused", Integer.valueOf(i));
        }
    }

    /* access modifiers changed from: private */
    public static String detailedExceptionString(Exception exc) {
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /* renamed from: io.flutter.embedding.engine.systemchannels.PlatformViewsChannel$1  reason: invalid class name */
    class AnonymousClass1 implements MethodChannel.MethodCallHandler {
        AnonymousClass1() {
        }

        @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
        public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
            if (PlatformViewsChannel.this.handler != null) {
                Log.v(PlatformViewsChannel.TAG, "Received '" + methodCall.method + "' message.");
                String str = methodCall.method;
                char c = '\uffff';
                switch (str.hashCode()) {
                    case -1352294148:
                        if (str.equals("create")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -934437708:
                        if (str.equals("resize")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -756050293:
                        if (str.equals("clearFocus")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 110550847:
                        if (str.equals("touch")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 576796989:
                        if (str.equals("setDirection")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 1671767583:
                        if (str.equals("dispose")) {
                            c = 1;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    create(methodCall, result);
                } else if (c == 1) {
                    dispose(methodCall, result);
                } else if (c == 2) {
                    resize(methodCall, result);
                } else if (c == 3) {
                    touch(methodCall, result);
                } else if (c == 4) {
                    setDirection(methodCall, result);
                } else if (c != 5) {
                    result.notImplemented();
                } else {
                    clearFocus(methodCall, result);
                }
            }
        }

        private void create(MethodCall methodCall, MethodChannel.Result result) {
            double d;
            Map map = (Map) methodCall.arguments();
            boolean z = map.containsKey("hybrid") && ((Boolean) map.get("hybrid")).booleanValue();
            double d2 = 0.0d;
            if (z) {
                d = 0.0d;
            } else {
                d = ((Double) map.get("width")).doubleValue();
            }
            if (!z) {
                d2 = ((Double) map.get("height")).doubleValue();
            }
            PlatformViewCreationRequest platformViewCreationRequest = new PlatformViewCreationRequest(((Integer) map.get("id")).intValue(), (String) map.get("viewType"), d, d2, ((Integer) map.get("direction")).intValue(), map.containsKey(Constant.KEY_PARAMS) ? ByteBuffer.wrap((byte[]) map.get(Constant.KEY_PARAMS)) : null);
            if (z) {
                try {
                    PlatformViewsChannel.this.handler.createAndroidViewForPlatformView(platformViewCreationRequest);
                    result.success(null);
                } catch (IllegalStateException e) {
                    result.error("error", PlatformViewsChannel.detailedExceptionString(e), null);
                }
            } else {
                result.success(Long.valueOf(PlatformViewsChannel.this.handler.createVirtualDisplayForPlatformView(platformViewCreationRequest)));
            }
        }

        private void dispose(MethodCall methodCall, MethodChannel.Result result) {
            Map map = (Map) methodCall.arguments();
            int intValue = ((Integer) map.get("id")).intValue();
            if (map.containsKey("hybrid") && ((Boolean) map.get("hybrid")).booleanValue()) {
                try {
                    PlatformViewsChannel.this.handler.disposeAndroidViewForPlatformView(intValue);
                } catch (IllegalStateException e) {
                    result.error("error", PlatformViewsChannel.detailedExceptionString(e), null);
                    return;
                }
            } else {
                PlatformViewsChannel.this.handler.disposeVirtualDisplayForPlatformView(intValue);
            }
            result.success(null);
        }

        private void resize(MethodCall methodCall, MethodChannel.Result result) {
            Map map = (Map) methodCall.arguments();
            try {
                PlatformViewsChannel.this.handler.resizePlatformView(new PlatformViewResizeRequest(((Integer) map.get("id")).intValue(), ((Double) map.get("width")).doubleValue(), ((Double) map.get("height")).doubleValue()), new AnonymousClass1(result));
            } catch (IllegalStateException e) {
                result.error("error", PlatformViewsChannel.detailedExceptionString(e), null);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: io.flutter.embedding.engine.systemchannels.PlatformViewsChannel$1$1  reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ MethodChannel.Result val$result;

            AnonymousClass1(MethodChannel.Result result) {
                this.val$result = result;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.val$result.success(null);
            }
        }

        private void touch(MethodCall methodCall, MethodChannel.Result result) {
            MethodChannel.Result result2;
            IllegalStateException e;
            List list = (List) methodCall.arguments();
            try {
                PlatformViewsChannel.this.handler.onTouch(new PlatformViewTouch(((Integer) list.get(0)).intValue(), (Number) list.get(1), (Number) list.get(2), ((Integer) list.get(3)).intValue(), ((Integer) list.get(4)).intValue(), list.get(5), list.get(6), ((Integer) list.get(7)).intValue(), ((Integer) list.get(8)).intValue(), (float) ((Double) list.get(9)).doubleValue(), (float) ((Double) list.get(10)).doubleValue(), ((Integer) list.get(11)).intValue(), ((Integer) list.get(12)).intValue(), ((Integer) list.get(13)).intValue(), ((Integer) list.get(14)).intValue(), ((Number) list.get(15)).longValue()));
                result2 = result;
                try {
                    result2.success(null);
                } catch (IllegalStateException e2) {
                    e = e2;
                }
            } catch (IllegalStateException e3) {
                e = e3;
                result2 = result;
                result2.error("error", PlatformViewsChannel.detailedExceptionString(e), null);
            }
        }

        private void setDirection(MethodCall methodCall, MethodChannel.Result result) {
            Map map = (Map) methodCall.arguments();
            try {
                PlatformViewsChannel.this.handler.setDirection(((Integer) map.get("id")).intValue(), ((Integer) map.get("direction")).intValue());
                result.success(null);
            } catch (IllegalStateException e) {
                result.error("error", PlatformViewsChannel.detailedExceptionString(e), null);
            }
        }

        private void clearFocus(MethodCall methodCall, MethodChannel.Result result) {
            try {
                PlatformViewsChannel.this.handler.clearFocus(((Integer) methodCall.arguments()).intValue());
                result.success(null);
            } catch (IllegalStateException e) {
                result.error("error", PlatformViewsChannel.detailedExceptionString(e), null);
            }
        }
    }

    public PlatformViewsChannel(DartExecutor dartExecutor) {
        this.channel = new MethodChannel(dartExecutor, "flutter/platform_views", StandardMethodCodec.INSTANCE);
        this.channel.setMethodCallHandler(this.parsingHandler);
    }

    public void setPlatformViewsHandler(PlatformViewsHandler platformViewsHandler) {
        this.handler = platformViewsHandler;
    }

    public static class PlatformViewCreationRequest {
        public final int direction;
        public final double logicalHeight;
        public final double logicalWidth;
        public final ByteBuffer params;
        public final int viewId;
        public final String viewType;

        public PlatformViewCreationRequest(int i, String str, double d, double d2, int i2, ByteBuffer byteBuffer) {
            this.viewId = i;
            this.viewType = str;
            this.logicalWidth = d;
            this.logicalHeight = d2;
            this.direction = i2;
            this.params = byteBuffer;
        }
    }

    public static class PlatformViewResizeRequest {
        public final double newLogicalHeight;
        public final double newLogicalWidth;
        public final int viewId;

        public PlatformViewResizeRequest(int i, double d, double d2) {
            this.viewId = i;
            this.newLogicalWidth = d;
            this.newLogicalHeight = d2;
        }
    }

    public static class PlatformViewTouch {
        public final int action;
        public final int buttonState;
        public final int deviceId;
        public final Number downTime;
        public final int edgeFlags;
        public final Number eventTime;
        public final int flags;
        public final int metaState;
        public final long motionEventId;
        public final int pointerCount;
        public final Object rawPointerCoords;
        public final Object rawPointerPropertiesList;
        public final int source;
        public final int viewId;
        public final float xPrecision;
        public final float yPrecision;

        public PlatformViewTouch(int i, Number number, Number number2, int i2, int i3, Object obj, Object obj2, int i4, int i5, float f, float f2, int i6, int i7, int i8, int i9, long j) {
            this.viewId = i;
            this.downTime = number;
            this.eventTime = number2;
            this.action = i2;
            this.pointerCount = i3;
            this.rawPointerPropertiesList = obj;
            this.rawPointerCoords = obj2;
            this.metaState = i4;
            this.buttonState = i5;
            this.xPrecision = f;
            this.yPrecision = f2;
            this.deviceId = i6;
            this.edgeFlags = i7;
            this.source = i8;
            this.flags = i9;
            this.motionEventId = j;
        }
    }
}
