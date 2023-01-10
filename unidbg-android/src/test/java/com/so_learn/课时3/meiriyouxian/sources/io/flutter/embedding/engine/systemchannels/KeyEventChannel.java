package io.flutter.embedding.engine.systemchannels;

import android.os.Build;
import android.view.InputDevice;
import android.view.KeyEvent;
import io.flutter.Log;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.JSONMessageCodec;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class KeyEventChannel {
    private static final String TAG = "KeyEventChannel";
    public final BasicMessageChannel<Object> channel;
    private EventResponseHandler eventResponseHandler;

    public interface EventResponseHandler {
        void onKeyEventHandled(KeyEvent keyEvent);

        void onKeyEventNotHandled(KeyEvent keyEvent);
    }

    public void setEventResponseHandler(EventResponseHandler eventResponseHandler) {
        this.eventResponseHandler = eventResponseHandler;
    }

    public KeyEventChannel(BinaryMessenger binaryMessenger) {
        this.channel = new BasicMessageChannel<>(binaryMessenger, "flutter/keyevent", JSONMessageCodec.INSTANCE);
    }

    /* access modifiers changed from: package-private */
    public BasicMessageChannel.Reply<Object> createReplyHandler(KeyEvent keyEvent) {
        return new $$Lambda$KeyEventChannel$ks_N3mtKlEyd7XxJih2MvfzlnY(this, keyEvent);
    }

    public /* synthetic */ void lambda$createReplyHandler$0$KeyEventChannel(KeyEvent keyEvent, Object obj) {
        EventResponseHandler eventResponseHandler = this.eventResponseHandler;
        if (eventResponseHandler != null) {
            if (obj == null) {
                try {
                    eventResponseHandler.onKeyEventNotHandled(keyEvent);
                } catch (JSONException e) {
                    Log.e(TAG, "Unable to unpack JSON message: " + e);
                    this.eventResponseHandler.onKeyEventNotHandled(keyEvent);
                }
            } else if (((JSONObject) obj).getBoolean("handled")) {
                this.eventResponseHandler.onKeyEventHandled(keyEvent);
            } else {
                this.eventResponseHandler.onKeyEventNotHandled(keyEvent);
            }
        }
    }

    public void keyUp(FlutterKeyEvent flutterKeyEvent) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", "keyup");
        hashMap.put("keymap", "android");
        encodeKeyEvent(flutterKeyEvent, hashMap);
        this.channel.send(hashMap, createReplyHandler(flutterKeyEvent.event));
    }

    public void keyDown(FlutterKeyEvent flutterKeyEvent) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", "keydown");
        hashMap.put("keymap", "android");
        encodeKeyEvent(flutterKeyEvent, hashMap);
        this.channel.send(hashMap, createReplyHandler(flutterKeyEvent.event));
    }

    private void encodeKeyEvent(FlutterKeyEvent flutterKeyEvent, Map<String, Object> map) {
        int i;
        map.put("flags", Integer.valueOf(flutterKeyEvent.event.getFlags()));
        int i2 = 0;
        map.put("plainCodePoint", Integer.valueOf(flutterKeyEvent.event.getUnicodeChar(0)));
        map.put("codePoint", Integer.valueOf(flutterKeyEvent.event.getUnicodeChar()));
        map.put("keyCode", Integer.valueOf(flutterKeyEvent.event.getKeyCode()));
        map.put("scanCode", Integer.valueOf(flutterKeyEvent.event.getScanCode()));
        map.put("metaState", Integer.valueOf(flutterKeyEvent.event.getMetaState()));
        if (flutterKeyEvent.complexCharacter != null) {
            map.put("character", flutterKeyEvent.complexCharacter.toString());
        }
        map.put("source", Integer.valueOf(flutterKeyEvent.event.getSource()));
        InputDevice device = InputDevice.getDevice(flutterKeyEvent.event.getDeviceId());
        if (device == null || Build.VERSION.SDK_INT < 19) {
            i = 0;
        } else {
            i2 = device.getVendorId();
            i = device.getProductId();
        }
        map.put("vendorId", Integer.valueOf(i2));
        map.put("productId", Integer.valueOf(i));
        map.put("deviceId", Integer.valueOf(flutterKeyEvent.event.getDeviceId()));
        map.put("repeatCount", Integer.valueOf(flutterKeyEvent.event.getRepeatCount()));
    }

    public static class FlutterKeyEvent {
        public final Character complexCharacter;
        public final KeyEvent event;

        public FlutterKeyEvent(KeyEvent keyEvent) {
            this(keyEvent, null);
        }

        public FlutterKeyEvent(KeyEvent keyEvent, Character ch) {
            this.event = keyEvent;
            this.complexCharacter = ch;
        }
    }
}
