package cn.missfresh.module.base.network.upload;

import android.content.ContentResolver;
import cn.missfresh.module.base.bean.ImageToken;
import cn.missfresh.module.base.network.a.g;
import cn.missfresh.module.base.network.a.i;
import cn.missfresh.module.base.network.a.j;
import cn.missfresh.module.base.network.bean.TokenBean;
import cn.missfresh.module.base.network.bean.TokenResult;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.fastjson.JSONObject;
import com.bangcle.andjni.JniLib;
import io.reactivex.c.h;
import io.reactivex.c.k;
import io.reactivex.t;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* compiled from: UploadHttpManager */
public class b {
    private static OkHttpClient a;
    private AbstractC0025b b;
    private List<TokenBean> c;

    /* compiled from: UploadHttpManager */
    /* renamed from: cn.missfresh.module.base.network.upload.b$1  reason: invalid class name */
    class AnonymousClass1 implements Callback {
        final /* synthetic */ UIProgressRequestListener a;
        final /* synthetic */ b b;

        AnonymousClass1(b bVar, UIProgressRequestListener uIProgressRequestListener) {
            JniLib.cV(this, bVar, uIProgressRequestListener, 203);
        }

        public void onFailure(Call call, IOException iOException) {
            JniLib.cV(this, call, iOException, 201);
        }

        public void onResponse(Call call, Response response) throws IOException {
            JniLib.cV(this, call, response, 202);
        }
    }

    /* compiled from: UploadHttpManager */
    /* renamed from: cn.missfresh.module.base.network.upload.b$3  reason: invalid class name */
    class AnonymousClass3 implements AbstractC0025b {
        final /* synthetic */ b a;

        AnonymousClass3(b bVar) {
            JniLib.cV(this, bVar, 208);
        }

        @Override // cn.missfresh.module.base.network.upload.b.AbstractC0025b
        public String a(String str) {
            return (String) JniLib.cL(this, str, 207);
        }
    }

    /* compiled from: UploadHttpManager */
    /* renamed from: cn.missfresh.module.base.network.upload.b$b  reason: collision with other inner class name */
    public interface AbstractC0025b {
        String a(String str);
    }

    /* compiled from: UploadHttpManager */
    public interface c {
        void a();

        void a(int i);

        void a(String str);

        void a(List<ImageToken> list);
    }

    public static b a() {
        return (b) JniLib.cL(224);
    }

    private String a(String str) {
        return (String) JniLib.cL(this, str, 225);
    }

    private void a(int i, TokenResult tokenResult, c cVar) {
        JniLib.cV(this, Integer.valueOf(i), tokenResult, cVar, 226);
    }

    public void a(String str, c cVar) {
        JniLib.cV(this, str, cVar, 221);
    }

    public void a(List<String> list, AbstractC0025b bVar, c cVar) {
        JniLib.cV(this, list, bVar, cVar, 222);
    }

    public void a(List<String> list, c cVar) {
        JniLib.cV(this, list, cVar, 223);
    }

    /* synthetic */ b(AnonymousClass1 r1) {
        this();
    }

    static /* synthetic */ String a(b bVar, String str) {
        AppMethodBeat.i(16201, false);
        String a2 = bVar.a(str);
        AppMethodBeat.o(16201);
        return a2;
    }

    static /* synthetic */ void a(b bVar, int i, TokenResult tokenResult, c cVar) {
        AppMethodBeat.i(16203, false);
        bVar.a(i, tokenResult, cVar);
        AppMethodBeat.o(16203);
    }

    /* compiled from: UploadHttpManager */
    private static final class a {
        private static final b a = new b(null);

        static {
            AppMethodBeat.i(16162, false);
            AppMethodBeat.o(16162);
        }
    }

    private b() {
        AppMethodBeat.i(16176, false);
        this.b = new AnonymousClass3(this);
        a = new OkHttpClient();
        a.newBuilder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).retryOnConnectionFailure(true).addNetworkInterceptor(new cn.missfresh.module.base.network.a.c()).addNetworkInterceptor(new i()).addNetworkInterceptor(new j()).addNetworkInterceptor(new g()).build();
        AppMethodBeat.o(16176);
    }

    @Deprecated
    public void a(String str, String str2, String str3, String str4, UIProgressRequestListener uIProgressRequestListener) {
        AppMethodBeat.i(16183, false);
        if (cn.missfresh.utils.b.a(str) || cn.missfresh.utils.b.a(str2) || cn.missfresh.utils.b.a(str3) || uIProgressRequestListener == null) {
            d.b("UploadHttpManager", String.format("upload params has null fromFilePath=%s&toFileName=%s&authorization=%s&uiProgressRequestListener=%s", str, str2, str3, uIProgressRequestListener));
            RuntimeException runtimeException = new RuntimeException("upload params has null, please check it");
            AppMethodBeat.o(16183);
            throw runtimeException;
        }
        File file = new File(str);
        if (file.exists()) {
            a.newCall(new Request.Builder().url(String.format("http://%s.ufile.ucloud.cn/", str4)).post(new a(new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("Authorization", str3).addFormDataPart("FileName", str2).addFormDataPart(ContentResolver.SCHEME_FILE, file.getName(), RequestBody.create(MediaType.parse(cn.missfresh.utils.c.b(str, false)), file)).build(), uIProgressRequestListener)).addHeader("Authorization", str3).build()).enqueue(new AnonymousClass1(this, uIProgressRequestListener));
            AppMethodBeat.o(16183);
            return;
        }
        d.b("UploadHttpManager", "upload fromFilePath is not exists, fromFilePath is " + str);
        RuntimeException runtimeException2 = new RuntimeException("upload fromFilePath is not exists, please check it");
        AppMethodBeat.o(16183);
        throw runtimeException2;
    }

    /* compiled from: UploadHttpManager */
    /* renamed from: cn.missfresh.module.base.network.upload.b$9  reason: invalid class name */
    class AnonymousClass9 implements k<String> {
        final /* synthetic */ b a;

        AnonymousClass9(b bVar) {
            JniLib.cV(this, bVar, 220);
        }

        public boolean a(String str) throws Exception {
            return JniLib.cZ(this, str, 219);
        }

        @Override // io.reactivex.c.k
        public /* synthetic */ boolean test(Object obj) throws Exception {
            AppMethodBeat.i(16144, false);
            boolean a = a((String) obj);
            AppMethodBeat.o(16144);
            return a;
        }
    }

    /* compiled from: UploadHttpManager */
    /* renamed from: cn.missfresh.module.base.network.upload.b$8  reason: invalid class name */
    class AnonymousClass8 implements h<String, TokenBean> {
        final /* synthetic */ AbstractC0025b a;
        final /* synthetic */ b b;

        AnonymousClass8(b bVar, AbstractC0025b bVar2) {
            JniLib.cV(this, bVar, bVar2, 218);
        }

        public TokenBean a(String str) throws Exception {
            return (TokenBean) JniLib.cL(this, str, 217);
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(16137, false);
            TokenBean a = a((String) obj);
            AppMethodBeat.o(16137);
            return a;
        }
    }

    /* compiled from: UploadHttpManager */
    /* renamed from: cn.missfresh.module.base.network.upload.b$7  reason: invalid class name */
    class AnonymousClass7 implements k<TokenBean> {
        final /* synthetic */ b a;

        AnonymousClass7(b bVar) {
            JniLib.cV(this, bVar, 216);
        }

        public boolean a(TokenBean tokenBean) throws Exception {
            return JniLib.cZ(this, tokenBean, 215);
        }

        @Override // io.reactivex.c.k
        public /* synthetic */ boolean test(Object obj) throws Exception {
            AppMethodBeat.i(16128, false);
            boolean a = a((TokenBean) obj);
            AppMethodBeat.o(16128);
            return a;
        }
    }

    /* compiled from: UploadHttpManager */
    /* renamed from: cn.missfresh.module.base.network.upload.b$6  reason: invalid class name */
    class AnonymousClass6 implements h<TokenBean, JSONObject> {
        final /* synthetic */ b a;

        AnonymousClass6(b bVar) {
            JniLib.cV(this, bVar, 214);
        }

        public JSONObject a(TokenBean tokenBean) throws Exception {
            return (JSONObject) JniLib.cL(this, tokenBean, 213);
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(16119, false);
            JSONObject a = a((TokenBean) obj);
            AppMethodBeat.o(16119);
            return a;
        }
    }

    /* compiled from: UploadHttpManager */
    /* renamed from: cn.missfresh.module.base.network.upload.b$5  reason: invalid class name */
    class AnonymousClass5 implements k<List<JSONObject>> {
        final /* synthetic */ b a;

        AnonymousClass5(b bVar) {
            JniLib.cV(this, bVar, 212);
        }

        public boolean a(List<JSONObject> list) throws Exception {
            return JniLib.cZ(this, list, 211);
        }

        @Override // io.reactivex.c.k
        public /* synthetic */ boolean test(Object obj) throws Exception {
            AppMethodBeat.i(16112, false);
            boolean a = a((List) obj);
            AppMethodBeat.o(16112);
            return a;
        }
    }

    /* compiled from: UploadHttpManager */
    /* renamed from: cn.missfresh.module.base.network.upload.b$4  reason: invalid class name */
    class AnonymousClass4 implements h<List<JSONObject>, t<TokenResult>> {
        final /* synthetic */ b a;

        AnonymousClass4(b bVar) {
            JniLib.cV(this, bVar, 210);
        }

        public t<TokenResult> a(List<JSONObject> list) throws Exception {
            return (t) JniLib.cL(this, list, 209);
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(16102, false);
            t<TokenResult> a = a((List) obj);
            AppMethodBeat.o(16102);
            return a;
        }
    }

    /* compiled from: UploadHttpManager */
    /* renamed from: cn.missfresh.module.base.network.upload.b$10  reason: invalid class name */
    class AnonymousClass10 extends cn.missfresh.module.base.network.j<TokenResult> {
        final /* synthetic */ c a;

        @Override // cn.missfresh.module.base.network.j
        public void a(int i, String str) {
            JniLib.cV(this, Integer.valueOf(i), str, 199);
        }

        public void a(TokenResult tokenResult) {
            JniLib.cV(this, tokenResult, 200);
        }

        AnonymousClass10(c cVar) {
            this.a = cVar;
        }

        @Override // cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(16156, false);
            a((TokenResult) obj);
            AppMethodBeat.o(16156);
        }
    }

    /* compiled from: UploadHttpManager */
    /* renamed from: cn.missfresh.module.base.network.upload.b$2  reason: invalid class name */
    class AnonymousClass2 extends UIProgressRequestListener {
        final /* synthetic */ int a;
        final /* synthetic */ int b;
        final /* synthetic */ c c;
        final /* synthetic */ TokenResult d;

        @Override // cn.missfresh.module.base.network.upload.UIProgressRequestListener
        public void b(long j, long j2, boolean z) {
            JniLib.cV(this, Long.valueOf(j), Long.valueOf(j2), Boolean.valueOf(z), 204);
        }

        @Override // cn.missfresh.module.base.network.upload.UIProgressRequestListener
        public void c() {
            JniLib.cV(this, 205);
        }

        @Override // cn.missfresh.module.base.network.upload.UIProgressRequestListener
        public void d() {
            JniLib.cV(this, 206);
        }

        AnonymousClass2(int i, int i2, c cVar, TokenResult tokenResult) {
            this.a = i;
            this.b = i2;
            this.c = cVar;
            this.d = tokenResult;
        }
    }
}
