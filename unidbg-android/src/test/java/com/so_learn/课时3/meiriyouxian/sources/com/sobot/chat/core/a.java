package com.sobot.chat.core;

import android.content.ContentResolver;
import android.text.TextUtils;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.sobot.chat.api.apiUtils.ZhiChiUrlApi;
import com.sobot.chat.core.http.c.c;
import com.sobot.chat.core.http.c.d;
import com.sobot.chat.core.http.d.e;
import com.sobot.chat.utils.m;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import okhttp3.Call;
import okhttp3.Response;

/* compiled from: HttpUtils */
public class a {
    private static a a;

    /* compiled from: HttpUtils */
    /* renamed from: com.sobot.chat.core.a$a  reason: collision with other inner class name */
    public interface AbstractC0142a {
        void a(int i);

        void a(File file);

        void a(Exception exc, String str, int i);
    }

    /* compiled from: HttpUtils */
    public interface b {
        void a(int i);

        void a(Exception exc, String str, int i);

        void a(String str);
    }

    private a() {
    }

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public void a(Object obj, String str, Map<String, String> map, b bVar) {
        String substring = str.substring(str.lastIndexOf(NotificationIconUtil.SPLIT_CHAR) + 1);
        m.d("\u8bf7\u6c42URL: --> " + str);
        m.d("\u8bf7\u6c42\u53c2\u6570: --> " + map);
        com.sobot.chat.core.http.a.e().a(obj).a(str).a(map).a("from", "2").a("version", ZhiChiUrlApi.VERSION).a().a(8000).b(8000).c(8000).b(new AnonymousClass1(substring, bVar));
    }

    /* compiled from: HttpUtils */
    /* renamed from: com.sobot.chat.core.a$1  reason: invalid class name */
    class AnonymousClass1 extends d {
        final /* synthetic */ String a;
        final /* synthetic */ b b;

        AnonymousClass1(String str, b bVar) {
            this.a = str;
            this.b = bVar;
        }

        public void a(String str) {
            m.d(this.a + "----\u8bf7\u6c42\u8fd4\u56de\u7ed3\u679c: --> " + str);
            this.b.a(str);
        }

        @Override // com.sobot.chat.core.http.c.b
        public void a(Call call, Exception exc) {
            m.d(call.toString());
            exc.printStackTrace();
            this.b.a(exc, call.toString(), -1);
        }
    }

    public void a(String str, File file, Map<String, String> map, AbstractC0142a aVar) {
        m.d("\u4e0b\u8f7d\u5730\u5740\uff1a" + str);
        com.sobot.chat.core.http.a.d().a(str).a("from", "2").a("version", ZhiChiUrlApi.VERSION).a().c(30000).a(30000).b(30000).b(new AnonymousClass2(file.getAbsolutePath(), aVar));
    }

    /* compiled from: HttpUtils */
    /* renamed from: com.sobot.chat.core.a$2  reason: invalid class name */
    class AnonymousClass2 extends c {
        final /* synthetic */ AbstractC0142a a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, AbstractC0142a aVar) {
            super(str);
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.http.c.b
        public void a(Call call, Exception exc) {
            this.a.a(exc, call.toString(), -1);
        }

        public void a(File file) {
            this.a.a(file);
        }

        @Override // com.sobot.chat.core.http.c.c
        public void a(float f, long j) {
            this.a.a((int) (f * 100.0f));
        }
    }

    public void a(Object obj, String str, Map<String, String> map, String str2, b bVar) {
        com.sobot.chat.core.http.a.c e = com.sobot.chat.core.http.a.e();
        if (!TextUtils.isEmpty(str2)) {
            File file = new File(str2);
            if (file.exists() && file.isFile()) {
                e.a(ContentResolver.SCHEME_FILE, file.getName(), file);
            }
        }
        e.a(str).a(map).a(obj).a("from", "2").a("version", ZhiChiUrlApi.VERSION).a().c(30000).a(30000).b(30000).b(new AnonymousClass3(bVar));
    }

    /* compiled from: HttpUtils */
    /* renamed from: com.sobot.chat.core.a$3  reason: invalid class name */
    class AnonymousClass3 extends d {
        final /* synthetic */ b a;

        AnonymousClass3(b bVar) {
            this.a = bVar;
        }

        public void a(String str) {
            this.a.a(str);
        }

        @Override // com.sobot.chat.core.http.c.b
        public void a(Call call, Exception exc) {
            this.a.a(exc, call.toString(), -1);
        }

        @Override // com.sobot.chat.core.http.c.b
        public void a(float f) {
            super.a(f);
            this.a.a((int) (f * 100.0f));
        }
    }

    public com.sobot.chat.core.http.i.d a(String str, String str2, Map<String, String> map, String str3, String str4) {
        m.d("\u4e0a\u4f20\u6587\u4ef6 \u8bf7\u6c42URL: --> " + str2);
        m.d("\u4e0a\u4f20\u6587\u4ef6 \u8bf7\u6c42\u53c2\u6570: --> " + map);
        m.d("\u4e0a\u4f20\u6587\u4ef6 \u6587\u4ef6\u5730\u5740: --> " + str3);
        m.d("\u4e0a\u4f20\u6587\u4ef6 \u56fe\u7247\u5feb\u7167: --> " + str4);
        com.sobot.chat.core.http.a.c e = com.sobot.chat.core.http.a.e();
        if (!TextUtils.isEmpty(str3)) {
            File file = new File(str3);
            e.a(ContentResolver.SCHEME_FILE, file.getName(), file);
        }
        if (!TextUtils.isEmpty(str4)) {
            File file2 = new File(str4);
            e.a("imageFile", file2.getName(), file2);
        }
        return com.sobot.chat.core.http.i.b.a(str, e.a(str2).a(map).a("from", "2").a("version", ZhiChiUrlApi.VERSION).a().c(30000).a(30000).b(30000)).a(new Random().nextInt(100)).b(str).a(str3).a();
    }

    public com.sobot.chat.core.http.g.c a(String str, String str2, String str3, Map<String, String> map) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return com.sobot.chat.core.http.g.a.a(str, a(str2, map)).a(new Random().nextInt(100)).a(str3).a();
    }

    public e a(String str, Map<String, String> map) {
        return com.sobot.chat.core.http.a.d().a(str).a(map).a("from", "2").a("version", ZhiChiUrlApi.VERSION).a().c(30000).a(30000).b(30000);
    }

    public Response a(Object obj, String str, Map<String, String> map) throws IOException {
        return com.sobot.chat.core.http.a.e().a(obj).a(str).a(map).a("from", "2").a("version", ZhiChiUrlApi.VERSION).a().a(8000).b(8000).c(8000).c();
    }
}
