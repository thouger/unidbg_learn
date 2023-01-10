package io.flutter.embedding.engine.systemchannels;

import io.flutter.Log;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.StandardMethodCodec;
import java.util.HashMap;
import java.util.Map;

public class RestorationChannel {
    private static final String TAG = "RestorationChannel";
    private MethodChannel channel;
    private boolean engineHasProvidedData;
    private boolean frameworkHasRequestedData;
    private final MethodChannel.MethodCallHandler handler;
    private MethodChannel.Result pendingFrameworkRestorationChannelRequest;
    private byte[] restorationData;
    public final boolean waitForRestorationData;

    public RestorationChannel(DartExecutor dartExecutor, boolean z) {
        this(new MethodChannel(dartExecutor, "flutter/restoration", StandardMethodCodec.INSTANCE), z);
    }

    RestorationChannel(MethodChannel methodChannel, boolean z) {
        this.engineHasProvidedData = false;
        this.frameworkHasRequestedData = false;
        this.handler = new AnonymousClass2();
        this.channel = methodChannel;
        this.waitForRestorationData = z;
        methodChannel.setMethodCallHandler(this.handler);
    }

    public byte[] getRestorationData() {
        return this.restorationData;
    }

    public void setRestorationData(byte[] bArr) {
        this.engineHasProvidedData = true;
        MethodChannel.Result result = this.pendingFrameworkRestorationChannelRequest;
        if (result != null) {
            result.success(packageData(bArr));
            this.pendingFrameworkRestorationChannelRequest = null;
            this.restorationData = bArr;
        } else if (this.frameworkHasRequestedData) {
            this.channel.invokeMethod("push", packageData(bArr), new AnonymousClass1(bArr));
        } else {
            this.restorationData = bArr;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.flutter.embedding.engine.systemchannels.RestorationChannel$1  reason: invalid class name */
    public class AnonymousClass1 implements MethodChannel.Result {
        final /* synthetic */ byte[] val$data;

        @Override // io.flutter.plugin.common.MethodChannel.Result
        public void notImplemented() {
        }

        AnonymousClass1(byte[] bArr) {
            this.val$data = bArr;
        }

        @Override // io.flutter.plugin.common.MethodChannel.Result
        public void success(Object obj) {
            RestorationChannel.this.restorationData = this.val$data;
        }

        @Override // io.flutter.plugin.common.MethodChannel.Result
        public void error(String str, String str2, Object obj) {
            Log.e(RestorationChannel.TAG, "Error " + str + " while sending restoration data to framework: " + str2);
        }
    }

    public void clearData() {
        this.restorationData = null;
    }

    /* renamed from: io.flutter.embedding.engine.systemchannels.RestorationChannel$2  reason: invalid class name */
    class AnonymousClass2 implements MethodChannel.MethodCallHandler {
        AnonymousClass2() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x002d  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x005b  */
        @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onMethodCall(io.flutter.plugin.common.MethodCall r5, io.flutter.plugin.common.MethodChannel.Result r6) {
            /*
                r4 = this;
                java.lang.String r0 = r5.method
                java.lang.Object r5 = r5.arguments
                int r1 = r0.hashCode()
                r2 = 102230(0x18f56, float:1.43255E-40)
                r3 = 1
                if (r1 == r2) goto L_0x001f
                r2 = 111375(0x1b30f, float:1.5607E-40)
                if (r1 == r2) goto L_0x0014
                goto L_0x002a
            L_0x0014:
                java.lang.String r1 = "put"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x002a
                r0 = 0
                goto L_0x002b
            L_0x001f:
                java.lang.String r1 = "get"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x002a
                r0 = r3
                goto L_0x002b
            L_0x002a:
                r0 = -1
            L_0x002b:
                if (r0 == 0) goto L_0x005b
                if (r0 == r3) goto L_0x0033
                r6.notImplemented()
                goto L_0x0068
            L_0x0033:
                io.flutter.embedding.engine.systemchannels.RestorationChannel r5 = io.flutter.embedding.engine.systemchannels.RestorationChannel.this
                io.flutter.embedding.engine.systemchannels.RestorationChannel.access$102(r5, r3)
                io.flutter.embedding.engine.systemchannels.RestorationChannel r5 = io.flutter.embedding.engine.systemchannels.RestorationChannel.this
                boolean r5 = io.flutter.embedding.engine.systemchannels.RestorationChannel.access$200(r5)
                if (r5 != 0) goto L_0x004d
                io.flutter.embedding.engine.systemchannels.RestorationChannel r5 = io.flutter.embedding.engine.systemchannels.RestorationChannel.this
                boolean r5 = r5.waitForRestorationData
                if (r5 != 0) goto L_0x0047
                goto L_0x004d
            L_0x0047:
                io.flutter.embedding.engine.systemchannels.RestorationChannel r5 = io.flutter.embedding.engine.systemchannels.RestorationChannel.this
                io.flutter.embedding.engine.systemchannels.RestorationChannel.access$402(r5, r6)
                goto L_0x0068
            L_0x004d:
                io.flutter.embedding.engine.systemchannels.RestorationChannel r5 = io.flutter.embedding.engine.systemchannels.RestorationChannel.this
                byte[] r0 = io.flutter.embedding.engine.systemchannels.RestorationChannel.access$000(r5)
                java.util.Map r5 = io.flutter.embedding.engine.systemchannels.RestorationChannel.access$300(r5, r0)
                r6.success(r5)
                goto L_0x0068
            L_0x005b:
                io.flutter.embedding.engine.systemchannels.RestorationChannel r0 = io.flutter.embedding.engine.systemchannels.RestorationChannel.this
                byte[] r5 = (byte[]) r5
                byte[] r5 = (byte[]) r5
                io.flutter.embedding.engine.systemchannels.RestorationChannel.access$002(r0, r5)
                r5 = 0
                r6.success(r5)
            L_0x0068:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.engine.systemchannels.RestorationChannel.AnonymousClass2.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Map<String, Object> packageData(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put("enabled", true);
        hashMap.put("data", bArr);
        return hashMap;
    }
}
