package com.umeng.commonsdk.statistics.internal;

/* compiled from: IRequestStat */
public interface b {
    void onRequestEnd();

    void onRequestFailed();

    void onRequestStart();

    void onRequestSucceed(boolean z);
}
