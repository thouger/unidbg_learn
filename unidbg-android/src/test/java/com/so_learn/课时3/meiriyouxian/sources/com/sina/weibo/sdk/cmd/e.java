package com.sina.weibo.sdk.cmd;

import android.content.Context;
import android.content.SharedPreferences;
import com.sina.weibo.sdk.a.d;
import com.sina.weibo.sdk.a.k;
import com.sina.weibo.sdk.net.b;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: WbAppActivator */
public class e {
    private static final String a = e.class.getName();
    private static e c;
    private Context b;
    private String d;
    private volatile ReentrantLock e = new ReentrantLock(true);
    private AppInvokeCmdExecutor f;
    private AppInstallCmdExecutor g;

    private e(Context context, String str) {
        this.b = context.getApplicationContext();
        this.f = new AppInvokeCmdExecutor(this.b);
        this.g = new AppInstallCmdExecutor(this.b);
        this.d = str;
    }

    public static synchronized e a(Context context, String str) {
        e eVar;
        synchronized (e.class) {
            if (c == null) {
                c = new e(context, str);
            }
            eVar = c;
        }
        return eVar;
    }

    public void a() {
        SharedPreferences a2 = a.a(this.b);
        long a3 = a.a(this.b, a2);
        long currentTimeMillis = System.currentTimeMillis() - a.b(this.b, a2);
        if (currentTimeMillis < a3) {
            d.e(a, String.format("it's only %d ms from last time get cmd", Long.valueOf(currentTimeMillis)));
        } else {
            new Thread(new AnonymousClass1(a2)).start();
        }
    }

    /* compiled from: WbAppActivator */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sina.weibo.sdk.cmd.e$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        private final /* synthetic */ SharedPreferences b;

        AnonymousClass1(SharedPreferences sharedPreferences) {
            this.b = sharedPreferences;
        }

        /* JADX WARNING: Removed duplicated region for block: B:25:0x00b6  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x00fe  */
        @Override // java.lang.Runnable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            // Method dump skipped, instructions count: 315
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.cmd.e.AnonymousClass1.run():void");
        }
    }

    /* access modifiers changed from: private */
    public static String c(Context context, String str) {
        String packageName = context.getPackageName();
        String a2 = k.a(context, packageName);
        com.sina.weibo.sdk.net.e eVar = new com.sina.weibo.sdk.net.e(str);
        eVar.a("appkey", str);
        eVar.a("packagename", packageName);
        eVar.a("key_hash", a2);
        eVar.a("version", "0031405000");
        return b.b(context, "http://api.weibo.cn/2/client/common_config", "GET", eVar);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(List<a> list) {
        if (list != null) {
            this.g.a();
            for (a aVar : list) {
                this.g.a(aVar);
            }
            this.g.b();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(List<b> list) {
        if (list != null) {
            for (b bVar : list) {
                this.f.a(bVar);
            }
        }
    }

    /* compiled from: WbAppActivator */
    /* access modifiers changed from: private */
    public static class a {
        public static SharedPreferences a(Context context) {
            return context.getSharedPreferences("com_sina_weibo_sdk", 0);
        }

        public static long a(Context context, SharedPreferences sharedPreferences) {
            if (sharedPreferences != null) {
                return sharedPreferences.getLong("frequency_get_cmd", 3600000);
            }
            return 3600000;
        }

        public static void a(Context context, SharedPreferences sharedPreferences, long j) {
            if (sharedPreferences != null && j > 0) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putLong("frequency_get_cmd", j);
                edit.commit();
            }
        }

        public static long b(Context context, SharedPreferences sharedPreferences) {
            if (sharedPreferences != null) {
                return sharedPreferences.getLong("last_time_get_cmd", 0);
            }
            return 0;
        }

        public static void b(Context context, SharedPreferences sharedPreferences, long j) {
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putLong("last_time_get_cmd", j);
                edit.commit();
            }
        }
    }
}
