package com.bun.miitmdid.supplier;

public interface IdSupplier {
    String getAAID();

    String getOAID();

    String getVAID();

    boolean isSupported();

    void shutDown();
}
