package com.sobot.chat.widget.zxing;

public final class FormatException extends ReaderException {
    private static final FormatException INSTANCE = new FormatException();

    static {
        INSTANCE.setStackTrace(NO_TRACE);
    }

    private FormatException() {
    }

    private FormatException(Throwable th) {
        super(th);
    }

    public static FormatException getFormatInstance() {
        return isStackTrace ? new FormatException() : INSTANCE;
    }

    public static FormatException getFormatInstance(Throwable th) {
        return isStackTrace ? new FormatException(th) : INSTANCE;
    }
}
