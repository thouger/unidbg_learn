package com.sobot.chat.core.http.d;

import com.lidroid.xutils.http.client.multipart.MIME;
import com.sobot.chat.core.http.a.c;
import com.sobot.chat.core.http.c.b;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/* compiled from: PostFormRequest */
public class c extends b {
    private List<c.a> g;

    public c(String str, Object obj, Map<String, String> map, Map<String, String> map2, List<c.a> list) {
        super(str, obj, map, map2);
        this.g = list;
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.core.http.d.b
    public RequestBody a() {
        List<c.a> list = this.g;
        if (list == null || list.isEmpty()) {
            FormBody.Builder builder = new FormBody.Builder();
            a(builder);
            return builder.build();
        }
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        a(type);
        for (int i = 0; i < this.g.size(); i++) {
            c.a aVar = this.g.get(i);
            try {
                type.addFormDataPart(aVar.a, URLEncoder.encode(aVar.b, "UTF-8"), RequestBody.create(MediaType.parse(a(aVar.b)), aVar.c));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return type.build();
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.core.http.d.b
    public RequestBody a(RequestBody requestBody, b bVar) {
        d dVar = new d(requestBody, bVar);
        dVar.a(this.f);
        return dVar;
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.core.http.d.b
    public Request a(RequestBody requestBody) {
        return this.e.post(requestBody).build();
    }

    private String a(String str) {
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str.replace("#", ""));
        return contentTypeFor == null ? "application/octet-stream" : contentTypeFor;
    }

    private void a(MultipartBody.Builder builder) {
        if (!(this.c == null || this.c.isEmpty())) {
            for (String str : this.c.keySet()) {
                builder.addPart(Headers.of(new String[]{MIME.CONTENT_DISPOSITION, "form-data; name=\"" + str + "\""}), RequestBody.create((MediaType) null, (String) this.c.get(str)));
            }
        }
    }

    private void a(FormBody.Builder builder) {
        if (this.c != null) {
            for (String str : this.c.keySet()) {
                if (this.c.get(str) != null) {
                    builder.add(str, (String) this.c.get(str));
                }
            }
        }
    }
}
