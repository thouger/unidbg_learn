package cn.missfresh.sherlock.e;

import android.app.job.JobInfo;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import cn.missfresh.sherlock.b;
import cn.missfresh.sherlock.config.Type;
import cn.missfresh.sherlock.e;
import cn.missfresh.sherlock.to.CommonTO;
import cn.missfresh.sherlock.to.ScreenShotTO;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.h;
import cn.missfresh.sherlock.tool.j;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ScreenShotManager */
public class c {
    private boolean a;
    private Point b;
    private final List<String> c;
    private b d;
    private long e;
    private b f;
    private b g;

    /* compiled from: ScreenShotManager */
    /* access modifiers changed from: package-private */
    public class a implements b {
        a(c cVar) {
        }

        @Override // cn.missfresh.sherlock.e.b
        public void a(String str) {
            j.a("ScreenShotManager", "onShot image path: " + str);
            String a = cn.missfresh.sherlock.d.a.a(str);
            if (TextUtils.isEmpty(a)) {
                j.a("ScreenShotManager", "gen image null");
                return;
            }
            String b = cn.missfresh.sherlock.d.a.b();
            if (TextUtils.isEmpty(b)) {
                j.a("ScreenShotManager", "vc null");
                return;
            }
            ScreenShotTO screenShotTO = new ScreenShotTO();
            screenShotTO.setEventType(Type.SCREENSHOT.getValue());
            screenShotTO.setTimestamp(Long.valueOf(System.currentTimeMillis()));
            screenShotTO.setNetwork(cn.missfresh.sherlock.d.a.a(e.e()));
            screenShotTO.setNetworkOperator(cn.missfresh.sherlock.d.a.b(e.e()));
            if (TextUtils.isEmpty(e.g())) {
                screenShotTO.setUserId(Utils.e(e.e()));
            } else {
                screenShotTO.setUserId(e.g());
            }
            screenShotTO.setPhoneNumber(e.h());
            screenShotTO.setVc(b);
            screenShotTO.setRequest(cn.missfresh.sherlock.d.a.b(screenShotTO.getVc()));
            screenShotTO.setFile(a);
            screenShotTO.setRegion(cn.missfresh.sherlock.tool.c.a(e.e()));
            cn.missfresh.sherlock.c.a().a((CommonTO) screenShotTO);
        }
    }

    /* compiled from: ScreenShotManager */
    /* access modifiers changed from: private */
    public class b extends ContentObserver {
        private Uri b;

        public b(Uri uri, Handler handler) {
            super(handler);
            this.b = uri;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            if (c.this.a) {
                c.this.a(this.b);
            }
        }
    }

    /* compiled from: ScreenShotManager */
    /* renamed from: cn.missfresh.sherlock.e.c$c  reason: collision with other inner class name */
    public static class C0042c {
        private static final c a = new c(null);
    }

    /* synthetic */ c(a aVar) {
        this();
    }

    public static c a() {
        return C0042c.a;
    }

    private void e() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String str = null;
            if (stackTrace != null && stackTrace.length >= 4) {
                str = stackTrace[3].toString();
            }
            throw new IllegalStateException("Call the method must be in main thread: " + str);
        }
    }

    public void c() {
        if (!this.a) {
            try {
                e();
                j.b("ScreenShotManager", "startListen");
                this.a = true;
                e.e().getContentResolver().registerContentObserver(MediaStore.Images.Media.INTERNAL_CONTENT_URI, false, this.f);
                e.e().getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, false, this.g);
                this.e = System.currentTimeMillis();
                this.c.clear();
            } catch (Exception unused) {
                j.b("ScreenShotManager", "Call the method must be in main thread");
            }
        }
    }

    public void d() {
        try {
            e();
            j.b("ScreenShotManager", "stopListen");
            this.a = false;
            if (this.f != null) {
                try {
                    e.e().getContentResolver().unregisterContentObserver(this.f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.g != null) {
                try {
                    e.e().getContentResolver().unregisterContentObserver(this.g);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.e = 0;
            this.c.clear();
        } catch (Exception unused) {
            j.b("ScreenShotManager", "Call the method must be in main thread");
        }
    }

    private c() {
        this.a = false;
        this.c = new ArrayList();
        if (this.b == null) {
            this.b = h.a(e.e());
        }
        this.f = new b(MediaStore.Images.Media.INTERNAL_CONTENT_URI, e.i());
        this.g = new b(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, e.i());
    }

    public void b() {
        j.b("ScreenShotManager", "initScreenShotManager");
        a(new a(this));
        c();
    }

    private void a(String str, long j, int i, int i2) {
        if (!b(str, j, i, i2)) {
            j.a("ScreenShotManager", "Not ScreenShot");
        } else if (this.d != null && !a(str)) {
            this.d.a(str);
        }
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Uri uri) {
        int i;
        int i2;
        int i3;
        Cursor cursor = null;
        try {
            cursor = e.e().getContentResolver().query(uri, Build.VERSION.SDK_INT < 16 ? b.a.a : b.a.b, null, null, "date_added desc limit 1");
            if (cursor == null) {
                j.b("ScreenShotManager", "ScreenSize cursor null");
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            } else if (!cursor.moveToFirst()) {
                j.b("ScreenShotManager", "ScreenSize cursor empty");
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            } else {
                int columnIndex = cursor.getColumnIndex("_data");
                int columnIndex2 = cursor.getColumnIndex("datetaken");
                int i4 = -1;
                if (Build.VERSION.SDK_INT >= 16) {
                    i4 = cursor.getColumnIndex("width");
                    i = cursor.getColumnIndex("height");
                } else {
                    i = -1;
                }
                String string = cursor.getString(columnIndex);
                long j = cursor.getLong(columnIndex2);
                if (i4 < 0 || i < 0) {
                    Point a2 = h.a(string);
                    int i5 = a2.x;
                    i2 = a2.y;
                    i3 = i5;
                } else {
                    i3 = cursor.getInt(i4);
                    i2 = cursor.getInt(i);
                }
                a(string, j, i3, i2);
                if (cursor == null || cursor.isClosed()) {
                    return;
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (0 == 0 || cursor.isClosed()) {
            }
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    private boolean b(String str, long j, int i, int i2) {
        if (j >= this.e && System.currentTimeMillis() - j <= JobInfo.MIN_BACKOFF_MILLIS) {
            Point point = this.b;
            if (point != null && (i > point.x || i2 > point.y)) {
                Point point2 = this.b;
                if (i2 > point2.x || i > point2.y) {
                    return false;
                }
            }
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            String lowerCase = str.toLowerCase();
            for (String str2 : b.a.c) {
                if (lowerCase.contains(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean a(String str) {
        if (this.c.contains(str)) {
            return true;
        }
        if (this.c.size() >= 20) {
            for (int i = 0; i < 5; i++) {
                this.c.remove(0);
            }
        }
        this.c.add(str);
        return false;
    }
}
