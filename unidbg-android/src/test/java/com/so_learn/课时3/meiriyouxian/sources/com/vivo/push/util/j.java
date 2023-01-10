package com.vivo.push.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.c.l;
import com.vivo.push.model.InsideNotificationItem;
import java.util.List;

/* compiled from: ImageDownTask */
public final class j extends AsyncTask<String, Void, List<Bitmap>> {
    private Context a;
    private InsideNotificationItem b;
    private long c;
    private boolean d;
    private int e = 0;
    private l.a f;

    /* access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.APP_TRANSITION_REPORTED_DRAWN_MS, false);
        List<Bitmap> a = a((String[]) objArr);
        AppMethodBeat.o(MetricsProto.MetricsEvent.APP_TRANSITION_REPORTED_DRAWN_MS);
        return a;
    }

    /* access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public final /* synthetic */ void onPostExecute(Object obj) {
        AppMethodBeat.i(1088, false);
        List list = (List) obj;
        super.onPostExecute(list);
        n.c("ImageDownTask", "onPostExecute");
        if (this.b != null) {
            v.b().a("com.vivo.push.notify_key", this.c);
            NotifyAdapterUtil.pushNotification(this.a, list, this.b, this.c, this.e, this.f);
        }
        AppMethodBeat.o(1088);
    }

    public j(Context context, InsideNotificationItem insideNotificationItem, long j, boolean z, l.a aVar) {
        this.a = context;
        this.b = insideNotificationItem;
        this.c = j;
        this.d = z;
        this.f = aVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009e, code lost:
        if (r6 != null) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ab, code lost:
        if (r6 == null) goto L_0x00ae;
     */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b7 A[SYNTHETIC, Splitter:B:41:0x00b7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<android.graphics.Bitmap> a(java.lang.String... r11) {
        /*
            r10 = this;
            r0 = 0
            r1 = 1087(0x43f, float:1.523E-42)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            android.content.Context r2 = r10.a
            com.vivo.push.cache.ClientConfigManagerImpl r2 = com.vivo.push.cache.ClientConfigManagerImpl.getInstance(r2)
            int r2 = r2.getNotifyStyle()
            r10.e = r2
            boolean r2 = r10.d
            r3 = 0
            java.lang.String r4 = "ImageDownTask"
            if (r2 != 0) goto L_0x0024
            java.lang.String r11 = "bitmap is not display by forbid net"
            com.vivo.push.util.n.d(r4, r11)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r3
        L_0x0024:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r5 = r0
        L_0x002a:
            r6 = 2
            if (r5 >= r6) goto L_0x00c7
            r6 = r11[r5]
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "imgUrl="
            r7.<init>(r8)
            r7.append(r6)
            java.lang.String r8 = " i="
            r7.append(r8)
            r7.append(r5)
            java.lang.String r7 = r7.toString()
            com.vivo.push.util.n.d(r4, r7)
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 != 0) goto L_0x00be
            java.net.URL r7 = new java.net.URL     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            r7.<init>(r6)     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            java.net.URLConnection r6 = r7.openConnection()     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            r7 = 30000(0x7530, float:4.2039E-41)
            r6.setConnectTimeout(r7)     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            r7 = 1
            r6.setDoInput(r7)     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            r6.setUseCaches(r0)     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            r6.connect()     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            int r7 = r6.getResponseCode()     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            java.lang.String r9 = "code="
            r8.<init>(r9)     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            r8.append(r7)     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            java.lang.String r8 = r8.toString()     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            com.vivo.push.util.n.c(r4, r8)     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            r8 = 200(0xc8, float:2.8E-43)
            if (r7 != r8) goto L_0x008d
            java.io.InputStream r6 = r6.getInputStream()     // Catch:{ MalformedURLException -> 0x00a4, IOException -> 0x0097, all -> 0x0095 }
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeStream(r6)     // Catch:{ MalformedURLException -> 0x00a5, IOException -> 0x0098 }
            goto L_0x008f
        L_0x008d:
            r6 = r3
            r7 = r6
        L_0x008f:
            if (r6 == 0) goto L_0x00af
            r6.close()     // Catch:{ Exception -> 0x00af }
            goto L_0x00af
        L_0x0095:
            r11 = move-exception
            goto L_0x00b5
        L_0x0097:
            r6 = r3
        L_0x0098:
            java.lang.String r7 = "IOException"
            com.vivo.push.util.n.a(r4, r7)     // Catch:{ all -> 0x00b3 }
            if (r6 == 0) goto L_0x00ae
        L_0x00a0:
            r6.close()     // Catch:{ Exception -> 0x00ae }
            goto L_0x00ae
        L_0x00a4:
            r6 = r3
        L_0x00a5:
            java.lang.String r7 = "MalformedURLException"
            com.vivo.push.util.n.a(r4, r7)
            if (r6 == 0) goto L_0x00ae
            goto L_0x00a0
        L_0x00ae:
            r7 = r3
        L_0x00af:
            r2.add(r7)
            goto L_0x00c3
        L_0x00b3:
            r11 = move-exception
            r3 = r6
        L_0x00b5:
            if (r3 == 0) goto L_0x00ba
            r3.close()     // Catch:{ Exception -> 0x00ba }
        L_0x00ba:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r11
        L_0x00be:
            if (r5 != 0) goto L_0x00c3
            r2.add(r3)
        L_0x00c3:
            int r5 = r5 + 1
            goto L_0x002a
        L_0x00c7:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.j.a(java.lang.String[]):java.util.List");
    }
}
