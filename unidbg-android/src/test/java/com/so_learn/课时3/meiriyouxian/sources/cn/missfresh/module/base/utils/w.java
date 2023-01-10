package cn.missfresh.module.base.utils;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import cn.missfresh.module.base.api.DownloadApiManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.c;
import io.reactivex.f.a;
import java.io.File;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

/* compiled from: DownLoadUtils */
public class w {
    static /* synthetic */ String a(String str) {
        AppMethodBeat.i(23300, false);
        String b = b(str);
        AppMethodBeat.o(23300);
        return b;
    }

    private static String b(String str) {
        AppMethodBeat.i(23297, false);
        String str2 = ".jpg";
        if (!TextUtils.isEmpty(str)) {
            try {
                HttpUrl url = new OkHttpClient().newCall(new Request.Builder().url(str).build()).execute().request().url();
                if (url != null) {
                    String httpUrl = url.toString();
                    str2 = httpUrl.substring(httpUrl.lastIndexOf("."));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(23297);
        return str2;
    }

    public static void a(Context context, String str, Handler handler) {
        AppMethodBeat.i(23298, false);
        DownloadApiManager.getDownloadApi().downloadFile(str).b(a.b()).a(a.c()).subscribe(new AnonymousClass1(str, handler, context));
        AppMethodBeat.o(23298);
    }

    /* compiled from: DownLoadUtils */
    /* renamed from: cn.missfresh.module.base.utils.w$1  reason: invalid class name */
    static class AnonymousClass1 extends io.reactivex.observers.a<ResponseBody> {
        final /* synthetic */ String a;
        final /* synthetic */ Handler b;
        final /* synthetic */ Context c;

        @Override // io.reactivex.v
        public void onComplete() {
        }

        AnonymousClass1(String str, Handler handler, Context context) {
            this.a = str;
            this.b = handler;
            this.c = context;
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(23292, false);
            a((ResponseBody) obj);
            AppMethodBeat.o(23292);
        }

        /* JADX WARNING: Removed duplicated region for block: B:35:0x0099  */
        /* JADX WARNING: Removed duplicated region for block: B:41:0x00a7 A[SYNTHETIC, Splitter:B:41:0x00a7] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(okhttp3.ResponseBody r10) {
            /*
                r9 = this;
                r0 = 0
                r1 = 23290(0x5afa, float:3.2636E-41)
                cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
                java.io.InputStream r10 = r10.byteStream()
                java.lang.String r2 = r9.a
                java.lang.String r2 = cn.missfresh.module.base.utils.w.a(r2)
                java.lang.String r3 = ""
                java.io.File r2 = cn.missfresh.module.base.utils.w.a(r3, r2)
                r3 = 1
                if (r2 != 0) goto L_0x0027
                android.os.Handler r10 = r9.b
                android.os.Message r10 = r10.obtainMessage(r3)
                r10.sendToTarget()
                cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
                return
            L_0x0027:
                r4 = 0
                java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x008e }
                r5.<init>(r2)     // Catch:{ IOException -> 0x008e }
                r6 = 4096(0x1000, float:5.74E-42)
                byte[] r6 = new byte[r6]     // Catch:{ IOException -> 0x0089, all -> 0x0087 }
            L_0x0031:
                int r7 = r10.read(r6)     // Catch:{ IOException -> 0x0089, all -> 0x0087 }
                r8 = -1
                if (r7 == r8) goto L_0x003c
                r5.write(r6, r0, r7)     // Catch:{ IOException -> 0x0089, all -> 0x0087 }
                goto L_0x0031
            L_0x003c:
                android.content.Context r10 = r9.c     // Catch:{ FileNotFoundException -> 0x006e }
                android.content.ContentResolver r10 = r10.getContentResolver()     // Catch:{ FileNotFoundException -> 0x006e }
                java.lang.String r6 = r2.getAbsolutePath()     // Catch:{ FileNotFoundException -> 0x006e }
                java.lang.String r7 = r2.getName()     // Catch:{ FileNotFoundException -> 0x006e }
                android.provider.MediaStore.Images.Media.insertImage(r10, r6, r7, r4)     // Catch:{ FileNotFoundException -> 0x006e }
                android.content.Intent r10 = new android.content.Intent
                java.lang.String r4 = "android.intent.action.MEDIA_SCANNER_SCAN_FILE"
                r10.<init>(r4)
                android.net.Uri r2 = android.net.Uri.fromFile(r2)
                r10.setData(r2)
                android.content.Context r2 = r9.c
                r2.sendBroadcast(r10)
                android.os.Handler r10 = r9.b
                android.os.Message r10 = r10.obtainMessage(r0)
                r10.sendToTarget()
                r5.close()     // Catch:{ IOException -> 0x009d }
                goto L_0x00a1
            L_0x006e:
                r10 = move-exception
                r10.printStackTrace()
                android.os.Handler r10 = r9.b
                android.os.Message r10 = r10.obtainMessage(r3)
                r10.sendToTarget()
                r5.close()     // Catch:{ IOException -> 0x007f }
                goto L_0x0083
            L_0x007f:
                r10 = move-exception
                r10.printStackTrace()
            L_0x0083:
                cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
                return
            L_0x0087:
                r10 = move-exception
                goto L_0x00a5
            L_0x0089:
                r4 = r5
                goto L_0x008e
            L_0x008b:
                r10 = move-exception
                r5 = r4
                goto L_0x00a5
            L_0x008e:
                android.os.Handler r10 = r9.b     // Catch:{ all -> 0x008b }
                android.os.Message r10 = r10.obtainMessage(r3)     // Catch:{ all -> 0x008b }
                r10.sendToTarget()     // Catch:{ all -> 0x008b }
                if (r4 == 0) goto L_0x00a1
                r4.close()
                goto L_0x00a1
            L_0x009d:
                r10 = move-exception
                r10.printStackTrace()
            L_0x00a1:
                cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
                return
            L_0x00a5:
                if (r5 == 0) goto L_0x00af
                r5.close()     // Catch:{ IOException -> 0x00ab }
                goto L_0x00af
            L_0x00ab:
                r0 = move-exception
                r0.printStackTrace()
            L_0x00af:
                cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.utils.w.AnonymousClass1.a(okhttp3.ResponseBody):void");
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            AppMethodBeat.i(23291, false);
            this.b.obtainMessage(1).sendToTarget();
            AppMethodBeat.o(23291);
        }
    }

    public static File a(String str, String str2) {
        AppMethodBeat.i(23299, false);
        File file = new File(Environment.getExternalStorageDirectory(), "DCIM");
        if (TextUtils.isEmpty(str)) {
            str = String.valueOf(System.currentTimeMillis());
        }
        File file2 = new File(file, str + str2);
        if (!c.b(file2, true)) {
            AppMethodBeat.o(23299);
            return null;
        }
        AppMethodBeat.o(23299);
        return file2;
    }
}
