package io.flutter.embedding.engine.systemchannels;

import io.flutter.Log;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.view.AccessibilityBridge;
import java.util.HashMap;

public class AccessibilityChannel {
    private static final String TAG = "AccessibilityChannel";
    public final BasicMessageChannel<Object> channel;
    public final FlutterJNI flutterJNI;
    private AccessibilityMessageHandler handler;
    private final BasicMessageChannel.MessageHandler<Object> parsingMessageHandler = new AnonymousClass1();

    public interface AccessibilityMessageHandler extends FlutterJNI.AccessibilityDelegate {
        void announce(String str);

        void onLongPress(int i);

        void onTap(int i);

        void onTooltip(String str);
    }

    /* renamed from: io.flutter.embedding.engine.systemchannels.AccessibilityChannel$1  reason: invalid class name */
    class AnonymousClass1 implements BasicMessageChannel.MessageHandler<Object> {
        AnonymousClass1() {
        }

        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
        public void onMessage(Object obj, BasicMessageChannel.Reply<Object> reply) {
            String str;
            if (AccessibilityChannel.this.handler != null) {
                HashMap hashMap = (HashMap) obj;
                String str2 = (String) hashMap.get("type");
                HashMap hashMap2 = (HashMap) hashMap.get("data");
                Log.v(AccessibilityChannel.TAG, "Received " + str2 + " message.");
                char c = '\uffff';
                switch (str2.hashCode()) {
                    case -1140076541:
                        if (str2.equals("tooltip")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -649620375:
                        if (str2.equals("announce")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 114595:
                        if (str2.equals("tap")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 114203431:
                        if (str2.equals("longPress")) {
                            c = 2;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    String str3 = (String) hashMap2.get("message");
                    if (str3 != null) {
                        AccessibilityChannel.this.handler.announce(str3);
                    }
                } else if (c == 1) {
                    Integer num = (Integer) hashMap.get("nodeId");
                    if (num != null) {
                        AccessibilityChannel.this.handler.onTap(num.intValue());
                    }
                } else if (c == 2) {
                    Integer num2 = (Integer) hashMap.get("nodeId");
                    if (num2 != null) {
                        AccessibilityChannel.this.handler.onLongPress(num2.intValue());
                    }
                } else if (c == 3 && (str = (String) hashMap2.get("message")) != null) {
                    AccessibilityChannel.this.handler.onTooltip(str);
                }
                reply.reply(null);
            }
        }
    }

    public AccessibilityChannel(DartExecutor dartExecutor, FlutterJNI flutterJNI) {
        this.channel = new BasicMessageChannel<>(dartExecutor, "flutter/accessibility", StandardMessageCodec.INSTANCE);
        this.channel.setMessageHandler(this.parsingMessageHandler);
        this.flutterJNI = flutterJNI;
    }

    public void onAndroidAccessibilityEnabled() {
        this.flutterJNI.setSemanticsEnabled(true);
    }

    public void onAndroidAccessibilityDisabled() {
        this.flutterJNI.setSemanticsEnabled(false);
    }

    public void setAccessibilityFeatures(int i) {
        this.flutterJNI.setAccessibilityFeatures(i);
    }

    public void dispatchSemanticsAction(int i, AccessibilityBridge.Action action) {
        this.flutterJNI.dispatchSemanticsAction(i, action);
    }

    public void dispatchSemanticsAction(int i, AccessibilityBridge.Action action, Object obj) {
        this.flutterJNI.dispatchSemanticsAction(i, action, obj);
    }

    public void setAccessibilityMessageHandler(AccessibilityMessageHandler accessibilityMessageHandler) {
        this.handler = accessibilityMessageHandler;
        this.flutterJNI.setAccessibilityDelegate(accessibilityMessageHandler);
    }
}
