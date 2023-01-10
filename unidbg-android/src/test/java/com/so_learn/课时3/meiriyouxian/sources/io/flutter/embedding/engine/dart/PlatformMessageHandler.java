package io.flutter.embedding.engine.dart;

public interface PlatformMessageHandler {
    void handleMessageFromDart(String str, byte[] bArr, int i);

    void handlePlatformMessageResponse(int i, byte[] bArr);
}
