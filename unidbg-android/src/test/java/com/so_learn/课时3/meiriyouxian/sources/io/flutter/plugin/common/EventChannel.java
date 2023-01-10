package io.flutter.plugin.common;

import com.unionpay.tsmservice.data.Constant;
import io.flutter.Log;
import io.flutter.plugin.common.BinaryMessenger;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class EventChannel {
    private static final String TAG = "EventChannel#";
    private final MethodCodec codec;
    private final BinaryMessenger messenger;
    private final String name;

    public interface EventSink {
        void endOfStream();

        void error(String str, String str2, Object obj);

        void success(Object obj);
    }

    public interface StreamHandler {
        void onCancel(Object obj);

        void onListen(Object obj, EventSink eventSink);
    }

    public EventChannel(BinaryMessenger binaryMessenger, String str) {
        this(binaryMessenger, str, StandardMethodCodec.INSTANCE);
    }

    public EventChannel(BinaryMessenger binaryMessenger, String str, MethodCodec methodCodec) {
        this.messenger = binaryMessenger;
        this.name = str;
        this.codec = methodCodec;
    }

    public void setStreamHandler(StreamHandler streamHandler) {
        this.messenger.setMessageHandler(this.name, streamHandler == null ? null : new IncomingStreamRequestHandler(streamHandler));
    }

    private final class IncomingStreamRequestHandler implements BinaryMessenger.BinaryMessageHandler {
        private final AtomicReference<EventSink> activeSink = new AtomicReference<>(null);
        private final StreamHandler handler;

        IncomingStreamRequestHandler(StreamHandler streamHandler) {
            this.handler = streamHandler;
        }

        @Override // io.flutter.plugin.common.BinaryMessenger.BinaryMessageHandler
        public void onMessage(ByteBuffer byteBuffer, BinaryMessenger.BinaryReply binaryReply) {
            MethodCall decodeMethodCall = EventChannel.this.codec.decodeMethodCall(byteBuffer);
            if (decodeMethodCall.method.equals("listen")) {
                onListen(decodeMethodCall.arguments, binaryReply);
            } else if (decodeMethodCall.method.equals(Constant.CASH_LOAD_CANCEL)) {
                onCancel(decodeMethodCall.arguments, binaryReply);
            } else {
                binaryReply.reply(null);
            }
        }

        private void onListen(Object obj, BinaryMessenger.BinaryReply binaryReply) {
            EventSinkImplementation eventSinkImplementation = new EventSinkImplementation();
            if (this.activeSink.getAndSet(eventSinkImplementation) != null) {
                try {
                    this.handler.onCancel(null);
                } catch (RuntimeException e) {
                    Log.e(EventChannel.TAG + EventChannel.this.name, "Failed to close existing event stream", e);
                }
            }
            try {
                this.handler.onListen(obj, eventSinkImplementation);
                binaryReply.reply(EventChannel.this.codec.encodeSuccessEnvelope(null));
            } catch (RuntimeException e2) {
                this.activeSink.set(null);
                Log.e(EventChannel.TAG + EventChannel.this.name, "Failed to open event stream", e2);
                binaryReply.reply(EventChannel.this.codec.encodeErrorEnvelope("error", e2.getMessage(), null));
            }
        }

        private void onCancel(Object obj, BinaryMessenger.BinaryReply binaryReply) {
            if (this.activeSink.getAndSet(null) != null) {
                try {
                    this.handler.onCancel(obj);
                    binaryReply.reply(EventChannel.this.codec.encodeSuccessEnvelope(null));
                } catch (RuntimeException e) {
                    Log.e(EventChannel.TAG + EventChannel.this.name, "Failed to close event stream", e);
                    binaryReply.reply(EventChannel.this.codec.encodeErrorEnvelope("error", e.getMessage(), null));
                }
            } else {
                binaryReply.reply(EventChannel.this.codec.encodeErrorEnvelope("error", "No active stream to cancel", null));
            }
        }

        /* access modifiers changed from: private */
        public final class EventSinkImplementation implements EventSink {
            final AtomicBoolean hasEnded;

            private EventSinkImplementation() {
                this.hasEnded = new AtomicBoolean(false);
            }

            @Override // io.flutter.plugin.common.EventChannel.EventSink
            public void success(Object obj) {
                if (!this.hasEnded.get() && IncomingStreamRequestHandler.this.activeSink.get() == this) {
                    EventChannel.this.messenger.send(EventChannel.this.name, EventChannel.this.codec.encodeSuccessEnvelope(obj));
                }
            }

            @Override // io.flutter.plugin.common.EventChannel.EventSink
            public void error(String str, String str2, Object obj) {
                if (!this.hasEnded.get() && IncomingStreamRequestHandler.this.activeSink.get() == this) {
                    EventChannel.this.messenger.send(EventChannel.this.name, EventChannel.this.codec.encodeErrorEnvelope(str, str2, obj));
                }
            }

            @Override // io.flutter.plugin.common.EventChannel.EventSink
            public void endOfStream() {
                if (!this.hasEnded.getAndSet(true) && IncomingStreamRequestHandler.this.activeSink.get() == this) {
                    EventChannel.this.messenger.send(EventChannel.this.name, null);
                }
            }
        }
    }
}
