package com.sina.weibo.sdk.cmd;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.sina.weibo.sdk.a.d;
import com.sina.weibo.sdk.a.f;
import com.sina.weibo.sdk.a.g;
import com.sina.weibo.sdk.a.h;
import com.sina.weibo.sdk.b;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.b;
import com.sina.weibo.sdk.net.e;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* access modifiers changed from: package-private */
public class AppInstallCmdExecutor {
    private static final String a = (Environment.getExternalStorageDirectory() + "/Android/org_share_data/");
    private static final String b = AppInstallCmdExecutor.class.getName();
    private Context c;
    private HandlerThread d;
    private Looper e;
    private a f;
    private boolean g = false;

    public AppInstallCmdExecutor(Context context) {
        this.c = context.getApplicationContext();
    }

    /* access modifiers changed from: private */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                AppInstallCmdExecutor.this.b((a) message.obj);
            } else if (i == 2) {
                AppInstallCmdExecutor.this.e.quit();
                AppInstallCmdExecutor.this.g = false;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(a aVar) {
        String str = "";
        if (a(this.c, aVar)) {
            String str2 = a;
            String d = aVar.d();
            long a2 = aVar.a();
            Pair<Integer, File> a3 = a(this.c, str2, aVar);
            if (a3 != null && a3.second != 0 && ((long) a3.first.intValue()) >= a2) {
                a(this.c, aVar, a3.second.getAbsolutePath());
            } else if (f.c(this.c) && !TextUtils.isEmpty(d)) {
                try {
                    String a4 = b.a(this.c, d, "GET", new e(str));
                    String a5 = a(a4);
                    if (!TextUtils.isEmpty(a5)) {
                        if (a5.endsWith(".apk")) {
                            str = b.a(this.c, a4, str2, a5);
                            if (TextUtils.isEmpty(str)) {
                                return;
                            }
                            a(this.c, aVar, str);
                            return;
                        }
                    }
                    d.c(b, "redirectDownloadUrl is illeagle");
                    if (!TextUtils.isEmpty(str)) {
                        a(this.c, aVar, str);
                    }
                } catch (WeiboException e) {
                    e.printStackTrace();
                    if (TextUtils.isEmpty(str)) {
                    }
                } catch (Throwable th) {
                    if (!TextUtils.isEmpty(str)) {
                        a(this.c, aVar, str);
                    }
                    throw th;
                }
            }
        }
    }

    private static boolean a(Context context, a aVar) {
        List<String> b2 = aVar.b();
        if (b2 == null || b2.size() == 0 || TextUtils.isEmpty(aVar.c()) || TextUtils.isEmpty(aVar.d()) || TextUtils.isEmpty(aVar.e())) {
            return false;
        }
        if (b2.contains("com.sina.weibo")) {
            b.a a2 = com.sina.weibo.sdk.b.a(context).a();
            if (a2 == null || !a2.c()) {
                return true;
            }
            return false;
        }
        for (String str : b2) {
            if (a(context, str)) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (context.getPackageManager().getPackageInfo(str, 1) == null) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public void a() {
        if (!this.g) {
            this.g = true;
            this.d = new HandlerThread("");
            this.d.start();
            this.e = this.d.getLooper();
            this.f = new a(this.e);
        }
    }

    public void b() {
        a aVar;
        if (this.d == null || (aVar = this.f) == null) {
            d.d(b, "no thread running. please call start method first!");
            return;
        }
        Message obtainMessage = aVar.obtainMessage();
        obtainMessage.what = 2;
        this.f.sendMessage(obtainMessage);
    }

    public boolean a(a aVar) {
        a aVar2;
        if (this.d == null || (aVar2 = this.f) == null) {
            throw new RuntimeException("no thread running. please call start method first!");
        } else if (aVar == null) {
            return false;
        } else {
            Message obtainMessage = aVar2.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = aVar;
            this.f.sendMessage(obtainMessage);
            return false;
        }
    }

    private static Pair<Integer, File> a(Context context, String str, a aVar) {
        File[] listFiles;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory() || (listFiles = file.listFiles()) == null) {
            return null;
        }
        File file2 = null;
        int i = 0;
        for (File file3 : listFiles) {
            String name = file3.getName();
            if (file3.isFile() && name.endsWith(".apk")) {
                PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file3.getAbsolutePath(), 64);
                if (a(packageArchiveInfo, aVar.b(), aVar.c()) && packageArchiveInfo.versionCode > i) {
                    i = packageArchiveInfo.versionCode;
                    file2 = file3;
                }
            }
        }
        return new Pair<>(Integer.valueOf(i), file2);
    }

    private static boolean a(PackageInfo packageInfo, List<String> list, String str) {
        boolean z;
        Iterator<String> it2 = list.iterator();
        while (true) {
            if (it2.hasNext()) {
                if (a(packageInfo, it2.next())) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        return z && b(packageInfo, str);
    }

    private static boolean a(PackageInfo packageInfo, String str) {
        if (packageInfo == null) {
            return false;
        }
        return str.equals(packageInfo.packageName);
    }

    private static boolean b(PackageInfo packageInfo, String str) {
        if (packageInfo == null) {
            return false;
        }
        if (packageInfo.signatures != null) {
            String str2 = "";
            for (int i = 0; i < packageInfo.signatures.length; i++) {
                byte[] byteArray = packageInfo.signatures[i].toByteArray();
                if (byteArray != null) {
                    str2 = com.sina.weibo.sdk.a.e.a(byteArray);
                }
            }
            if (str2 == null) {
                return false;
            }
            return str2.equals(str);
        } else if (Build.VERSION.SDK_INT < 11) {
            return true;
        } else {
            return false;
        }
    }

    private static String a(String str) {
        int lastIndexOf = str.lastIndexOf(NotificationIconUtil.SPLIT_CHAR);
        return lastIndexOf != -1 ? str.substring(lastIndexOf + 1, str.length()) : "";
    }

    private static void a(Context context, a aVar, String str) {
        h.a.a().c(aVar.e()).a(b(context, str)).b(c(context, aVar.f())).a(aVar.e()).a(context).a(1);
    }

    private static PendingIntent b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return PendingIntent.getActivity(context, 0, new Intent(), 16);
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
        return PendingIntent.getActivity(context, 0, intent, 16);
    }

    private static String c(Context context, String str) {
        return TextUtils.isEmpty(str) ? g.a(context, "Weibo", "\u5fae\u535a", "\u5fae\u535a") : str;
    }
}
