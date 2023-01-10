package cn.missfresh.module.base.support.a;

import android.app.job.JobInfo;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Point;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ScreenShotMonitorManager */
public class e {
    private static Point d;
    private static e i;
    private final List<String> a = new ArrayList();
    private List<c> b = new ArrayList();
    private Context c;
    private long e;
    private a f;
    private a g;
    private final Handler h = new Handler(Looper.getMainLooper());

    static /* synthetic */ void a(e eVar, Uri uri) {
        AppMethodBeat.i(22422, false);
        eVar.a(uri);
        AppMethodBeat.o(22422);
    }

    private e(Context context) {
        AppMethodBeat.i(22409, false);
        if (context != null) {
            this.c = context.getApplicationContext();
            if (d == null) {
                d = new Point(aw.a(), aw.b());
            }
            AppMethodBeat.o(22409);
            return;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("The context must not be null.");
        AppMethodBeat.o(22409);
        throw illegalArgumentException;
    }

    public static synchronized e a(Context context) {
        e eVar;
        synchronized (e.class) {
            AppMethodBeat.i(22410, false);
            c();
            if (i == null) {
                i = new e(context.getApplicationContext());
            }
            eVar = i;
            AppMethodBeat.o(22410);
        }
        return eVar;
    }

    public void a() {
        AppMethodBeat.i(22411, false);
        c();
        this.a.clear();
        this.e = System.currentTimeMillis();
        if (this.f == null) {
            this.f = new a(MediaStore.Images.Media.INTERNAL_CONTENT_URI, this.h);
        }
        if (this.g == null) {
            this.g = new a(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.h);
        }
        this.c.getContentResolver().registerContentObserver(MediaStore.Images.Media.INTERNAL_CONTENT_URI, false, this.f);
        this.c.getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, false, this.g);
        AppMethodBeat.o(22411);
    }

    public void b() {
        AppMethodBeat.i(22412, false);
        c();
        if (this.f != null) {
            this.c.getContentResolver().unregisterContentObserver(this.f);
            this.f = null;
        }
        if (this.g != null) {
            this.c.getContentResolver().unregisterContentObserver(this.g);
            this.g = null;
        }
        this.e = 0;
        this.a.clear();
        AppMethodBeat.o(22412);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0081, code lost:
        if (r1.isClosed() == false) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0083, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a5, code lost:
        if (r1.isClosed() == false) goto L_0x0083;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.net.Uri r12) {
        /*
            r11 = this;
            r0 = 22413(0x578d, float:3.1407E-41)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            r1 = 0
            android.content.Context r2 = r11.c     // Catch:{ Exception -> 0x0098 }
            android.content.ContentResolver r3 = r2.getContentResolver()     // Catch:{ Exception -> 0x0098 }
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0098 }
            r9 = 16
            if (r2 >= r9) goto L_0x0016
            java.lang.String[] r2 = cn.missfresh.module.base.support.a.d.a     // Catch:{ Exception -> 0x0098 }
            goto L_0x0018
        L_0x0016:
            java.lang.String[] r2 = cn.missfresh.module.base.support.a.d.b     // Catch:{ Exception -> 0x0098 }
        L_0x0018:
            r5 = r2
            r6 = 0
            r7 = 0
            java.lang.String r8 = "date_added desc limit 1"
            r4 = r12
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0098 }
            if (r1 == 0) goto L_0x0087
            boolean r12 = r1.moveToFirst()     // Catch:{ Exception -> 0x0098 }
            if (r12 != 0) goto L_0x002c
            goto L_0x0087
        L_0x002c:
            java.lang.String r12 = "_data"
            int r12 = r1.getColumnIndex(r12)     // Catch:{ Exception -> 0x0098 }
            java.lang.String r2 = "datetaken"
            int r2 = r1.getColumnIndex(r2)     // Catch:{ Exception -> 0x0098 }
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0098 }
            r4 = -1
            if (r3 < r9) goto L_0x004e
            java.lang.String r3 = "width"
            int r4 = r1.getColumnIndex(r3)     // Catch:{ Exception -> 0x0098 }
            java.lang.String r3 = "height"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ Exception -> 0x0098 }
            goto L_0x004f
        L_0x004e:
            r3 = r4
        L_0x004f:
            java.lang.String r6 = r1.getString(r12)     // Catch:{ Exception -> 0x0098 }
            long r7 = r1.getLong(r2)     // Catch:{ Exception -> 0x0098 }
            if (r4 < 0) goto L_0x0066
            if (r3 < 0) goto L_0x0066
            int r12 = r1.getInt(r4)     // Catch:{ Exception -> 0x0098 }
            int r2 = r1.getInt(r3)     // Catch:{ Exception -> 0x0098 }
            r9 = r12
            r10 = r2
            goto L_0x0077
        L_0x0066:
            android.graphics.BitmapFactory$Options r12 = new android.graphics.BitmapFactory$Options     // Catch:{ Exception -> 0x0098 }
            r12.<init>()     // Catch:{ Exception -> 0x0098 }
            r2 = 1
            r12.inJustDecodeBounds = r2     // Catch:{ Exception -> 0x0098 }
            android.graphics.BitmapFactory.decodeFile(r6, r12)     // Catch:{ Exception -> 0x0098 }
            int r2 = r12.outWidth     // Catch:{ Exception -> 0x0098 }
            int r12 = r12.outHeight     // Catch:{ Exception -> 0x0098 }
            r10 = r12
            r9 = r2
        L_0x0077:
            r5 = r11
            r5.a(r6, r7, r9, r10)     // Catch:{ Exception -> 0x0098 }
            if (r1 == 0) goto L_0x00a8
            boolean r12 = r1.isClosed()
            if (r12 != 0) goto L_0x00a8
        L_0x0083:
            r1.close()
            goto L_0x00a8
        L_0x0087:
            if (r1 == 0) goto L_0x0092
            boolean r12 = r1.isClosed()
            if (r12 != 0) goto L_0x0092
            r1.close()
        L_0x0092:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x0096:
            r12 = move-exception
            goto L_0x00ac
        L_0x0098:
            r12 = move-exception
            java.lang.String r2 = "ScreenShotMonitorManager"
            cn.missfresh.utils.a.d.a(r2, r12)     // Catch:{ all -> 0x0096 }
            if (r1 == 0) goto L_0x00a8
            boolean r12 = r1.isClosed()
            if (r12 != 0) goto L_0x00a8
            goto L_0x0083
        L_0x00a8:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x00ac:
            if (r1 == 0) goto L_0x00b7
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L_0x00b7
            r1.close()
        L_0x00b7:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.support.a.e.a(android.net.Uri):void");
    }

    private void a(String str, long j, int i2, int i3) {
        AppMethodBeat.i(22414, false);
        if (b(str, j, i2, i3)) {
            List<c> list = this.b;
            if (list != null && list.size() > 0) {
                for (c cVar : this.b) {
                    if (cVar != null) {
                        cVar.j_(str);
                    }
                }
            }
        } else {
            d.c("ScreenShotMonitorManager", "The data is not screen shot " + str + " dateTaken " + j);
        }
        AppMethodBeat.o(22414);
    }

    private boolean b(String str, long j, int i2, int i3) {
        AppMethodBeat.i(22415, false);
        boolean z = j < this.e;
        boolean z2 = System.currentTimeMillis() - j > JobInfo.MIN_BACKOFF_MILLIS;
        if (z || z2) {
            AppMethodBeat.o(22415);
            return false;
        } else if (d != null && !a(i2, i3)) {
            AppMethodBeat.o(22415);
            return false;
        } else if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(22415);
            return false;
        } else {
            String lowerCase = str.toLowerCase();
            for (String str2 : d.c) {
                if (lowerCase.contains(str2)) {
                    AppMethodBeat.o(22415);
                    return true;
                }
            }
            AppMethodBeat.o(22415);
            return false;
        }
    }

    private boolean a(int i2, int i3) {
        if (i2 <= d.x && i3 <= d.y) {
            return true;
        }
        if (i2 > d.y || i3 > d.x) {
            return false;
        }
        return true;
    }

    public void a(c cVar) {
        AppMethodBeat.i(22418, false);
        this.b.add(cVar);
        AppMethodBeat.o(22418);
    }

    public void b(c cVar) {
        AppMethodBeat.i(22419, false);
        this.b.remove(cVar);
        AppMethodBeat.o(22419);
    }

    private static void c() {
        AppMethodBeat.i(22420, false);
        if (Looper.myLooper() != Looper.getMainLooper()) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String str = null;
            if (stackTrace != null && stackTrace.length >= 4) {
                str = stackTrace[3].toString();
            }
            d.b("ScreenShotMonitorManager", str);
            IllegalStateException illegalStateException = new IllegalStateException("Call the method must be in main thread: " + str);
            AppMethodBeat.o(22420);
            throw illegalStateException;
        }
        AppMethodBeat.o(22420);
    }

    /* compiled from: ScreenShotMonitorManager */
    private class a extends ContentObserver {
        private Uri b;

        private a(Uri uri, Handler handler) {
            super(handler);
            this.b = uri;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            AppMethodBeat.i(22408, false);
            super.onChange(z);
            e.a(e.this, this.b);
            AppMethodBeat.o(22408);
        }
    }
}
