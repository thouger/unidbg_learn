package com.umeng.message;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.tencent.open.SocialConstants;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.message.entity.UMessage;
import com.umeng.message.service.UMJobIntentService;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.json.JSONObject;

public class UmengDownloadResourceService extends UMJobIntentService {
    public static final String TAG = UmengDownloadResourceService.class.getName();
    private static final String o = ".tmp";
    private static final String p = "RETRY_TIME";
    private static final String q = "OPERATIOIN";
    private static final int r = 1;
    private static final int s = 2;
    private static final long t = 1048576;
    private static final long u = 86400000;
    private static final int v = 300000;
    private static final int w = 3;
    private static Thread x;
    ScheduledThreadPoolExecutor a = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 4);
    Context b = this;
    ArrayList<String> c = new ArrayList<>();

    /* access modifiers changed from: protected */
    @Override // com.umeng.message.service.UMJobIntentService, com.umeng.message.service.JobIntentService
    public void a(Intent intent) {
        MLog.i("wuchi", "--->>> UmengDownloadResourceService onHandleWork");
        if (intent != null) {
            int intExtra = intent.getIntExtra(q, 2);
            int intExtra2 = intent.getIntExtra(p, 3);
            try {
                UMessage uMessage = new UMessage(new JSONObject(intent.getStringExtra("body")));
                uMessage.message_id = intent.getStringExtra("id");
                uMessage.task_id = intent.getStringExtra("task_id");
                if (!this.c.contains(uMessage.msg_id)) {
                    this.c.add(uMessage.msg_id);
                    if (intExtra == 1) {
                        deleteAlarm(uMessage, intExtra2);
                        UMLog.mutlInfo(TAG, 2, "\u4e0b\u8f7d\u8d44\u6e90\u540e\u663e\u793a\u901a\u77e5");
                        notification(uMessage);
                        this.c.remove(uMessage.msg_id);
                        if (this.c.size() == 0) {
                            stopSelf();
                        }
                    } else if (intExtra == 2) {
                        UMLog.mutlInfo(TAG, 2, "\u5f00\u59cb\u4e0b\u8f7d\u8d44\u6e90");
                        int i = intExtra2 - 1;
                        setAlarm(uMessage, i);
                        checkCache();
                        downloadResource(uMessage, i);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void notification(UMessage uMessage) {
        UHandler uHandler;
        if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
            uHandler = PushAgent.getInstance(this).getAdHandler();
        } else {
            uHandler = PushAgent.getInstance(this).getMessageHandler();
        }
        if (uHandler == null) {
            return;
        }
        if (TextUtils.equals(UMessage.DISPLAY_TYPE_AUTOUPDATE, uMessage.display_type)) {
            UmengMessageHandler umengMessageHandler = (UmengMessageHandler) PushAgent.getInstance(this.b).getMessageHandler();
            if (umengMessageHandler != null) {
                umengMessageHandler.dealWithNotificationMessage(this.b, uMessage);
                return;
            }
            return;
        }
        uHandler.handleMessage(this, uMessage);
    }

    public void downloadResource(UMessage uMessage, int i) {
        DownloadResourceTask downloadResourceTask = new DownloadResourceTask(uMessage, i);
        if (Build.VERSION.SDK_INT >= 11) {
            downloadResourceTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            downloadResourceTask.execute(new Void[0]);
        }
    }

    public void setAlarm(UMessage uMessage, int i) {
        UMLog.mutlInfo(TAG, 2, "setAlarm");
        ((AlarmManager) getSystemService("alarm")).set(1, System.currentTimeMillis() + 300000, a(uMessage, i));
    }

    public void deleteAlarm(UMessage uMessage, int i) {
        UMLog.mutlInfo(TAG, 2, "deleteAlarm");
        ((AlarmManager) getSystemService("alarm")).cancel(a(uMessage, i));
    }

    private PendingIntent a(UMessage uMessage, int i) {
        String jSONObject = uMessage.getRaw().toString();
        int hashCode = uMessage.msg_id.hashCode();
        Intent intent = new Intent(this.b, UmengDownloadResourceService.class);
        intent.putExtra("body", jSONObject);
        intent.putExtra("id", uMessage.message_id);
        intent.putExtra("task_id", uMessage.task_id);
        intent.putExtra(q, 2);
        intent.putExtra(p, i);
        PendingIntent service = PendingIntent.getService(this.b, hashCode, intent, 134217728);
        String str = TAG;
        UMLog.mutlInfo(str, 2, "PendingIntent: msgId:" + uMessage.msg_id + ",requestCode:" + hashCode + ",retryTime:" + i);
        return service;
    }

    public class DownloadResourceTask extends AsyncTask<Void, Void, Boolean> {
        UMessage a;
        ArrayList<String> b = new ArrayList<>();
        int c;

        public DownloadResourceTask(UMessage uMessage, int i) {
            this.a = uMessage;
            if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                try {
                    this.b.add(new JSONObject(uMessage.custom).optString(SocialConstants.PARAM_IMG_URL));
                } catch (Exception unused) {
                }
            }
            if (uMessage.isLargeIconFromInternet()) {
                this.b.add(uMessage.img);
            }
            if (uMessage.isSoundFromInternet()) {
                this.b.add(uMessage.sound);
            }
            if (!TextUtils.isEmpty(uMessage.bar_image)) {
                this.b.add(uMessage.bar_image);
            }
            if (!TextUtils.isEmpty(uMessage.expand_image)) {
                this.b.add(uMessage.expand_image);
            }
            this.c = i;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Boolean doInBackground(Void... voidArr) {
            Iterator<String> it2 = this.b.iterator();
            boolean z = true;
            while (it2.hasNext()) {
                z &= download(it2.next());
            }
            return Boolean.valueOf(z);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            UmengDownloadResourceService.this.c.remove(this.a.msg_id);
            if (bool.booleanValue() || this.c <= 0) {
                MessageSharedPrefs.getInstance(UmengDownloadResourceService.this.b).setMessageResourceDownloaded(this.a.msg_id);
                String jSONObject = this.a.getRaw().toString();
                Intent intent = new Intent(UmengDownloadResourceService.this.b, UmengDownloadResourceService.class);
                intent.putExtra("body", jSONObject);
                intent.putExtra("id", this.a.message_id);
                intent.putExtra("task_id", this.a.task_id);
                intent.putExtra(UmengDownloadResourceService.q, 1);
                intent.putExtra(UmengDownloadResourceService.p, this.c);
                UMJobIntentService.enqueueWork(UmengDownloadResourceService.this.b, UmengDownloadResourceService.class, intent);
            } else if (UmengDownloadResourceService.this.c.size() == 0) {
                UmengDownloadResourceService.this.stopSelf();
            }
        }

        public boolean download(String str) {
            Throwable th;
            FileOutputStream fileOutputStream;
            Exception e;
            Exception e2;
            Throwable th2;
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            InputStream inputStream = null;
            try {
                String str2 = str.hashCode() + "";
                String messageResourceFolder = UmengDownloadResourceService.getMessageResourceFolder(UmengDownloadResourceService.this.b, this.a);
                File file = new File(messageResourceFolder, str2 + UmengDownloadResourceService.o);
                File file2 = new File(messageResourceFolder, str2);
                if (file2.exists()) {
                    UmengDownloadResourceService.this.close(null);
                    UmengDownloadResourceService.this.close(null);
                    return true;
                }
                File file3 = new File(messageResourceFolder);
                if (!file3.exists()) {
                    file3.mkdirs();
                }
                if (file.exists()) {
                    file.delete();
                }
                InputStream openStream = new URL(new URI(str).toASCIIString()).openStream();
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        byte[] bArr = new byte[10240];
                        while (true) {
                            int read = openStream.read(bArr);
                            if (read > 0) {
                                fileOutputStream.write(bArr, 0, read);
                            } else {
                                file.renameTo(file2);
                                UmengDownloadResourceService.this.close(openStream);
                                UmengDownloadResourceService.this.close(fileOutputStream);
                                return true;
                            }
                        }
                    } catch (Exception e3) {
                        e2 = e3;
                        inputStream = openStream;
                        e = e2;
                        try {
                            e.printStackTrace();
                            UmengDownloadResourceService.this.close(inputStream);
                            UmengDownloadResourceService.this.close(fileOutputStream);
                            return false;
                        } catch (Throwable th3) {
                            th = th3;
                            UmengDownloadResourceService.this.close(inputStream);
                            UmengDownloadResourceService.this.close(fileOutputStream);
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th2 = th4;
                        inputStream = openStream;
                        th = th2;
                        UmengDownloadResourceService.this.close(inputStream);
                        UmengDownloadResourceService.this.close(fileOutputStream);
                        throw th;
                    }
                } catch (Exception e4) {
                    e2 = e4;
                    fileOutputStream = null;
                    inputStream = openStream;
                    e = e2;
                    e.printStackTrace();
                    UmengDownloadResourceService.this.close(inputStream);
                    UmengDownloadResourceService.this.close(fileOutputStream);
                    return false;
                } catch (Throwable th5) {
                    th2 = th5;
                    fileOutputStream = null;
                    inputStream = openStream;
                    th = th2;
                    UmengDownloadResourceService.this.close(inputStream);
                    UmengDownloadResourceService.this.close(fileOutputStream);
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                fileOutputStream = null;
                e.printStackTrace();
                UmengDownloadResourceService.this.close(inputStream);
                UmengDownloadResourceService.this.close(fileOutputStream);
                return false;
            } catch (Throwable th6) {
                th = th6;
                fileOutputStream = null;
                UmengDownloadResourceService.this.close(inputStream);
                UmengDownloadResourceService.this.close(fileOutputStream);
                throw th;
            }
        }
    }

    public void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void checkCache() {
        try {
            checkDir(new File(getMessageResourceFolder(this.b, null)), 1048576, 86400000);
        } catch (Throwable unused) {
        }
    }

    public static void checkDir(File file, long j, long j2) throws IOException {
        if (file.exists() && a(file.getCanonicalFile()) > j) {
            if (x == null) {
                x = new Thread(new AnonymousClass1(file, j2));
            }
            synchronized (x) {
                x.start();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.UmengDownloadResourceService$1  reason: invalid class name */
    public static class AnonymousClass1 implements Runnable {
        final /* synthetic */ File a;
        final /* synthetic */ long b;

        AnonymousClass1(File file, long j) {
            this.a = file;
            this.b = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            UmengDownloadResourceService.b(this.a, this.b);
            Thread unused = UmengDownloadResourceService.x = null;
        }
    }

    private static long a(File file) {
        long j = 0;
        if (file != null && file.exists() && file.isDirectory()) {
            Stack stack = new Stack();
            stack.clear();
            stack.push(file);
            while (!stack.isEmpty()) {
                File[] listFiles = ((File) stack.pop()).listFiles();
                for (File file2 : listFiles) {
                    if (!file2.isDirectory()) {
                        j += file2.length();
                    }
                }
            }
        }
        return j;
    }

    /* access modifiers changed from: private */
    public static void b(File file, long j) {
        if (file != null && file.exists() && file.canWrite() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                if (!file2.isDirectory() && System.currentTimeMillis() - file2.lastModified() > j) {
                    file2.delete();
                }
            }
        }
    }

    public static String getMessageResourceFolder(Context context, UMessage uMessage) {
        String str = context.getCacheDir() + "/umeng_push/";
        if (uMessage == null || uMessage.msg_id == null) {
            return str;
        }
        return str + uMessage.msg_id + NotificationIconUtil.SPLIT_CHAR;
    }
}
