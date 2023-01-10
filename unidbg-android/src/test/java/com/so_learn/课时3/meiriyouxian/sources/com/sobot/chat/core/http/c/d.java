package com.sobot.chat.core.http.c;

import java.io.IOException;
import okhttp3.Response;

/* compiled from: StringCallback */
public abstract class d extends b<String> {
    /* renamed from: b */
    public String a(Response response) throws IOException {
        return response.body().string();
    }
}
