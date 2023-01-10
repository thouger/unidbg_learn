package com.sijla.lj;

public class LException extends Exception {
    private static final long serialVersionUID = 1;

    public LException(String str) {
        super(str);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LException(Exception exc) {
        super(exc.getCause() != null ? exc.getCause() : exc);
    }
}
