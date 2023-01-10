package com.sobot.chat.api;

public interface ResultCallBack<T> {
    void onFailure(Exception exc, String str);

    void onLoading(long j, long j2, boolean z);

    void onSuccess(T t);
}
