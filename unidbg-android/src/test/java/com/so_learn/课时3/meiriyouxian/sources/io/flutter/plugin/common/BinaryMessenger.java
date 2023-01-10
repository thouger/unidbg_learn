package io.flutter.plugin.common;

import java.nio.ByteBuffer;

public interface BinaryMessenger {

    public interface BinaryMessageHandler {
        void onMessage(ByteBuffer byteBuffer, BinaryReply binaryReply);
    }

    public interface BinaryReply {
        void reply(ByteBuffer byteBuffer);
    }

    void send(String str, ByteBuffer byteBuffer);

    void send(String str, ByteBuffer byteBuffer, BinaryReply binaryReply);

    void setMessageHandler(String str, BinaryMessageHandler binaryMessageHandler);
}
