package io.flutter.plugin.common;

import io.flutter.Log;
import io.flutter.plugin.common.BinaryMessenger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Locale;

public final class BasicMessageChannel<T> {
    public static final String CHANNEL_BUFFERS_CHANNEL = "dev.flutter/channel-buffers";
    private static final String TAG = "BasicMessageChannel#";
    private final MessageCodec<T> codec;
    private final BinaryMessenger messenger;
    private final String name;

    public interface MessageHandler<T> {
        void onMessage(T t, Reply<T> reply);
    }

    public interface Reply<T> {
        void reply(T t);
    }

    public BasicMessageChannel(BinaryMessenger binaryMessenger, String str, MessageCodec<T> messageCodec) {
        this.messenger = binaryMessenger;
        this.name = str;
        this.codec = messageCodec;
    }

    public void send(T t) {
        send(t, null);
    }

    public void send(T t, Reply<T> reply) {
        BinaryMessenger binaryMessenger = this.messenger;
        String str = this.name;
        ByteBuffer encodeMessage = this.codec.encodeMessage(t);
        IncomingReplyHandler incomingReplyHandler = null;
        if (reply != null) {
            incomingReplyHandler = new IncomingReplyHandler(reply);
        }
        binaryMessenger.send(str, encodeMessage, incomingReplyHandler);
    }

    public void setMessageHandler(MessageHandler<T> messageHandler) {
        BinaryMessenger binaryMessenger = this.messenger;
        String str = this.name;
        IncomingMessageHandler incomingMessageHandler = null;
        if (messageHandler != null) {
            incomingMessageHandler = new IncomingMessageHandler(messageHandler);
        }
        binaryMessenger.setMessageHandler(str, incomingMessageHandler);
    }

    public void resizeChannelBuffer(int i) {
        resizeChannelBuffer(this.messenger, this.name, i);
    }

    static void resizeChannelBuffer(BinaryMessenger binaryMessenger, String str, int i) {
        binaryMessenger.send(CHANNEL_BUFFERS_CHANNEL, ByteBuffer.wrap(String.format(Locale.US, "resize\r%s\r%d", str, Integer.valueOf(i)).getBytes(Charset.forName("UTF-8"))));
    }

    /* access modifiers changed from: private */
    public final class IncomingReplyHandler implements BinaryMessenger.BinaryReply {
        private final Reply<T> callback;

        private IncomingReplyHandler(Reply<T> reply) {
            this.callback = reply;
        }

        @Override // io.flutter.plugin.common.BinaryMessenger.BinaryReply
        public void reply(ByteBuffer byteBuffer) {
            try {
                this.callback.reply(BasicMessageChannel.this.codec.decodeMessage(byteBuffer));
            } catch (RuntimeException e) {
                Log.e(BasicMessageChannel.TAG + BasicMessageChannel.this.name, "Failed to handle message reply", e);
            }
        }
    }

    private final class IncomingMessageHandler implements BinaryMessenger.BinaryMessageHandler {
        private final MessageHandler<T> handler;

        private IncomingMessageHandler(MessageHandler<T> messageHandler) {
            this.handler = messageHandler;
        }

        @Override // io.flutter.plugin.common.BinaryMessenger.BinaryMessageHandler
        public void onMessage(ByteBuffer byteBuffer, BinaryMessenger.BinaryReply binaryReply) {
            try {
                this.handler.onMessage(BasicMessageChannel.this.codec.decodeMessage(byteBuffer), new AnonymousClass1(binaryReply));
            } catch (RuntimeException e) {
                Log.e(BasicMessageChannel.TAG + BasicMessageChannel.this.name, "Failed to handle message", e);
                binaryReply.reply(null);
            }
        }

        /* renamed from: io.flutter.plugin.common.BasicMessageChannel$IncomingMessageHandler$1  reason: invalid class name */
        class AnonymousClass1 implements Reply<T> {
            final /* synthetic */ BinaryMessenger.BinaryReply val$callback;

            AnonymousClass1(BinaryMessenger.BinaryReply binaryReply) {
                this.val$callback = binaryReply;
            }

            @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
            public void reply(T t) {
                this.val$callback.reply(BasicMessageChannel.this.codec.encodeMessage(t));
            }
        }
    }
}
