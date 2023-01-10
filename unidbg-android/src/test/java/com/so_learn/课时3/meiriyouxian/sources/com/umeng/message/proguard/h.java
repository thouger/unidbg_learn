package com.umeng.message.proguard;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Toast;
import com.hjq.toast.IToastStrategy;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.j;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.entity.Ucode;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: Helper */
public class h {
    public static final String a = System.getProperty("line.separator");
    private static final String b = h.class.getName();
    private static final AtomicInteger c = new AtomicInteger(1);

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            instance.reset();
            instance.update(bytes);
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02X", Byte.valueOf(digest[i])));
            }
            return sb.toString();
        } catch (Exception unused) {
            return str.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
        }
    }

    public static String b(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b2 : digest) {
                sb.append(Integer.toHexString(b2 & 255));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            UMLog.mutlInfo(b, 2, "getMD5 error");
            return "";
        }
    }

    public static String a(File file) {
        byte[] bArr = new byte[1024];
        try {
            if (!file.isFile()) {
                return "";
            }
            MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    instance.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return String.format("%1$032x", new BigInteger(1, instance.digest()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(Context context, long j) {
        if (j < 1000) {
            return ((int) j) + "B";
        } else if (j < TimeUtils.NANOS_PER_MS) {
            return Math.round(((double) ((float) j)) / 1000.0d) + "K";
        } else if (j < 1000000000) {
            DecimalFormat decimalFormat = new DecimalFormat("#0.0");
            return decimalFormat.format(((double) ((float) j)) / 1000000.0d) + "M";
        } else {
            DecimalFormat decimalFormat2 = new DecimalFormat("#0.00");
            return decimalFormat2.format(((double) ((float) j)) / 1.0E9d) + "G";
        }
    }

    public static String c(String str) {
        try {
            long longValue = Long.valueOf(str).longValue();
            if (longValue < 1024) {
                return ((int) longValue) + "B";
            } else if (longValue < 1048576) {
                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                return decimalFormat.format(((double) ((float) longValue)) / 1024.0d) + "K";
            } else if (longValue < TrafficStats.GB_IN_BYTES) {
                DecimalFormat decimalFormat2 = new DecimalFormat("#0.00");
                return decimalFormat2.format(((double) ((float) longValue)) / 1048576.0d) + "M";
            } else {
                DecimalFormat decimalFormat3 = new DecimalFormat("#0.00");
                return decimalFormat3.format(((double) ((float) longValue)) / 1.073741824E9d) + "G";
            }
        } catch (NumberFormatException unused) {
            return str;
        }
    }

    public static void a(Context context, String str) {
        try {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str));
        } catch (Exception unused) {
        }
    }

    public static boolean b(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean d(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean e(String str) {
        if (d(str)) {
            return false;
        }
        String lowerCase = str.trim().toLowerCase(Locale.US);
        if (lowerCase.startsWith("http://") || lowerCase.startsWith("https://")) {
            return true;
        }
        return false;
    }

    public static String a() {
        return a(new Date());
    }

    public static String a(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static boolean a(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            List<ActivityManager.RunningAppProcessInfo> list = null;
            if (activityManager != null) {
                list = activityManager.getRunningAppProcesses();
            }
            if (list == null) {
                return false;
            }
            String packageName = context.getPackageName();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : list) {
                if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static boolean b(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            List<ActivityManager.RunningTaskInfo> list = null;
            if (activityManager != null) {
                list = activityManager.getRunningTasks(1);
            }
            return list != null && !list.isEmpty() && list.get(0).topActivity.getPackageName().equals(context.getPackageName());
        } catch (Exception unused) {
        }
    }

    public static boolean c(Context context) {
        return context.getPackageManager().checkPermission("android.permission.GET_TASKS", context.getPackageName()) == 0;
    }

    public static boolean c(Context context, String str) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningServiceInfo> runningServices = activityManager != null ? activityManager.getRunningServices(Integer.MAX_VALUE) : null;
        if (runningServices == null || runningServices.isEmpty()) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
            if (runningServiceInfo.service.getClassName().equals(str) && TextUtils.equals(runningServiceInfo.service.getPackageName(), context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static String a(Context context, int i) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager != null ? activityManager.getRunningAppProcesses() : null;
        if (runningAppProcesses == null) {
            return "";
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == i) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }

    public static void a(Context context, Class<?> cls) {
        ComponentName componentName;
        int componentEnabledSetting;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && packageManager.getApplicationEnabledSetting(context.getPackageName()) > -1 && (componentEnabledSetting = packageManager.getComponentEnabledSetting((componentName = new ComponentName(context, cls)))) != 1) {
                if (componentEnabledSetting != 0) {
                    packageManager.setComponentEnabledSetting(componentName, 1, 1);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static Object f(String str) {
        try {
            try {
                return new ObjectInputStream(new ByteArrayInputStream(d.h(str.getBytes()))).readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } catch (StreamCorruptedException e2) {
            e2.printStackTrace();
            return null;
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static String a(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(obj);
            return new String(d.c(byteArrayOutputStream.toByteArray()));
        } catch (IOException unused) {
            return null;
        }
    }

    public static String b() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static boolean a(Context context, Handler handler) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14;
        boolean z15;
        boolean z16;
        boolean z17;
        boolean z18;
        boolean z19;
        boolean z20;
        boolean z21;
        boolean z22;
        boolean z23;
        boolean z24;
        boolean z25;
        boolean z26;
        boolean z27;
        boolean z28;
        boolean z29;
        boolean z30;
        if (!PushAgent.getInstance(context).isPushCheck()) {
            return true;
        }
        try {
            if (TextUtils.isEmpty(PushAgent.getInstance(context).getMessageAppkey())) {
                handler.post(new AnonymousClass1(context));
                return false;
            } else if (TextUtils.isEmpty(PushAgent.getInstance(context).getMessageSecret())) {
                handler.post(new AnonymousClass12(context));
                return false;
            } else {
                Intent intent = new Intent();
                intent.setPackage(context.getPackageName());
                intent.setAction("com.taobao.accs.intent.action.SERVICE");
                Iterator<ResolveInfo> it2 = context.getPackageManager().queryIntentServices(intent, 64).iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z = false;
                        break;
                    }
                    ResolveInfo next = it2.next();
                    if (next.serviceInfo.name.equals(j.channelService)) {
                        String str = next.serviceInfo.processName;
                        if (!TextUtils.isEmpty(str) && !TextUtils.equals(str, context.getPackageName()) && next.serviceInfo.exported) {
                            z = true;
                            break;
                        }
                    }
                }
                if (!z) {
                    handler.post(new AnonymousClass18(context));
                    return false;
                }
                Intent intent2 = new Intent();
                intent2.setPackage(context.getPackageName());
                intent2.setAction("com.taobao.accs.intent.action.ELECTION");
                Iterator<ResolveInfo> it3 = context.getPackageManager().queryIntentServices(intent2, 64).iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z2 = false;
                        break;
                    }
                    ResolveInfo next2 = it3.next();
                    if (next2.serviceInfo.name.equals(j.channelService)) {
                        String str2 = next2.serviceInfo.processName;
                        if (!TextUtils.isEmpty(str2) && !TextUtils.equals(str2, context.getPackageName()) && next2.serviceInfo.exported) {
                            z2 = true;
                            break;
                        }
                    }
                }
                if (!z2) {
                    handler.post(new AnonymousClass19(context));
                    return false;
                }
                Intent intent3 = new Intent();
                intent3.setPackage(context.getPackageName());
                intent3.setAction(Constants.ACTION_RECEIVE);
                Iterator<ResolveInfo> it4 = context.getPackageManager().queryIntentServices(intent3, 64).iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        z3 = false;
                        break;
                    }
                    ResolveInfo next3 = it4.next();
                    if (next3.serviceInfo.name.equals(j.msgService) && next3.serviceInfo.exported) {
                        z3 = true;
                        break;
                    }
                }
                if (!z3) {
                    handler.post(new AnonymousClass20(context));
                    return false;
                }
                Intent intent4 = new Intent();
                intent4.setPackage(context.getPackageName());
                intent4.setAction(Intent.ACTION_BOOT_COMPLETED);
                Iterator<ResolveInfo> it5 = context.getPackageManager().queryBroadcastReceivers(intent4, 64).iterator();
                while (true) {
                    if (!it5.hasNext()) {
                        z4 = false;
                        break;
                    }
                    ResolveInfo next4 = it5.next();
                    if (next4.activityInfo.name.equals("com.taobao.accs.EventReceiver")) {
                        String str3 = next4.activityInfo.processName;
                        if (!TextUtils.isEmpty(str3) && !TextUtils.equals(str3, context.getPackageName())) {
                            z4 = true;
                            break;
                        }
                    }
                }
                if (!z4) {
                    handler.post(new AnonymousClass21(context));
                    return false;
                }
                Intent intent5 = new Intent();
                intent5.setPackage(context.getPackageName());
                intent5.setAction(ConnectivityManager.CONNECTIVITY_ACTION);
                Iterator<ResolveInfo> it6 = context.getPackageManager().queryBroadcastReceivers(intent5, 64).iterator();
                while (true) {
                    if (!it6.hasNext()) {
                        z5 = false;
                        break;
                    }
                    ResolveInfo next5 = it6.next();
                    if (next5.activityInfo.name.equals("com.taobao.accs.EventReceiver")) {
                        String str4 = next5.activityInfo.processName;
                        if (!TextUtils.isEmpty(str4) && !TextUtils.equals(str4, context.getPackageName())) {
                            z5 = true;
                            break;
                        }
                    }
                }
                if (!z5) {
                    handler.post(new AnonymousClass22(context));
                    return false;
                }
                Intent intent6 = new Intent();
                intent6.setPackage(context.getPackageName());
                intent6.setAction(Intent.ACTION_PACKAGE_REMOVED);
                intent6.setData(Uri.parse("package:"));
                Iterator<ResolveInfo> it7 = context.getPackageManager().queryBroadcastReceivers(intent6, 64).iterator();
                while (true) {
                    if (!it7.hasNext()) {
                        z6 = false;
                        break;
                    }
                    ResolveInfo next6 = it7.next();
                    if (next6.activityInfo.name.equals("com.taobao.accs.EventReceiver")) {
                        String str5 = next6.activityInfo.processName;
                        if (!TextUtils.isEmpty(str5) && !TextUtils.equals(str5, context.getPackageName())) {
                            z6 = true;
                            break;
                        }
                    }
                }
                if (!z6) {
                    handler.post(new AnonymousClass23(context));
                    return false;
                }
                Intent intent7 = new Intent();
                intent7.setPackage(context.getPackageName());
                intent7.setAction(Intent.ACTION_USER_PRESENT);
                Iterator<ResolveInfo> it8 = context.getPackageManager().queryBroadcastReceivers(intent7, 64).iterator();
                while (true) {
                    if (!it8.hasNext()) {
                        z7 = false;
                        break;
                    }
                    ResolveInfo next7 = it8.next();
                    if (next7.activityInfo.name.equals("com.taobao.accs.EventReceiver")) {
                        String str6 = next7.activityInfo.processName;
                        if (!TextUtils.isEmpty(str6) && !TextUtils.equals(str6, context.getPackageName())) {
                            z7 = true;
                            break;
                        }
                    }
                }
                if (!z7) {
                    handler.post(new AnonymousClass24(context));
                    return false;
                }
                Intent intent8 = new Intent();
                intent8.setPackage(context.getPackageName());
                intent8.setAction(Constants.ACTION_COMMAND);
                Iterator<ResolveInfo> it9 = context.getPackageManager().queryBroadcastReceivers(intent8, 64).iterator();
                while (true) {
                    if (!it9.hasNext()) {
                        z8 = false;
                        break;
                    }
                    ResolveInfo next8 = it9.next();
                    if (next8.activityInfo.name.equals("com.taobao.accs.ServiceReceiver")) {
                        String str7 = next8.activityInfo.processName;
                        if (!TextUtils.isEmpty(str7) && !TextUtils.equals(str7, context.getPackageName())) {
                            z8 = true;
                            break;
                        }
                    }
                }
                if (!z8) {
                    handler.post(new AnonymousClass2(context));
                    return false;
                }
                Intent intent9 = new Intent();
                intent9.setPackage(context.getPackageName());
                intent9.setAction(Constants.ACTION_START_FROM_AGOO);
                Iterator<ResolveInfo> it10 = context.getPackageManager().queryBroadcastReceivers(intent9, 64).iterator();
                while (true) {
                    if (!it10.hasNext()) {
                        z9 = false;
                        break;
                    }
                    ResolveInfo next9 = it10.next();
                    if (next9.activityInfo.name.equals("com.taobao.accs.ServiceReceiver")) {
                        String str8 = next9.activityInfo.processName;
                        if (!TextUtils.isEmpty(str8) && !TextUtils.equals(str8, context.getPackageName())) {
                            z9 = true;
                            break;
                        }
                    }
                }
                if (!z9) {
                    handler.post(new AnonymousClass3(context));
                    return false;
                }
                Intent intent10 = new Intent();
                intent10.setPackage(context.getPackageName());
                intent10.setAction(Constants.ACTION_RECEIVE);
                Iterator<ResolveInfo> it11 = context.getPackageManager().queryIntentServices(intent10, 64).iterator();
                while (true) {
                    if (!it11.hasNext()) {
                        z10 = false;
                        break;
                    }
                    ResolveInfo next10 = it11.next();
                    if (next10.serviceInfo.name.equals("org.android.agoo.accs.AgooService") && next10.serviceInfo.exported) {
                        z10 = true;
                        break;
                    }
                }
                if (!z10) {
                    handler.post(new AnonymousClass4(context));
                    return false;
                }
                Intent intent11 = new Intent();
                intent11.setPackage(context.getPackageName());
                intent11.setAction("org.agoo.android.intent.action.RECEIVE");
                Iterator<ResolveInfo> it12 = context.getPackageManager().queryIntentServices(intent11, 64).iterator();
                while (true) {
                    if (!it12.hasNext()) {
                        z11 = false;
                        break;
                    }
                    ResolveInfo next11 = it12.next();
                    if (next11.serviceInfo.name.equals("com.umeng.message.UmengIntentService") && next11.serviceInfo.exported) {
                        z11 = true;
                        break;
                    }
                }
                if (!z11) {
                    handler.post(new AnonymousClass5(context));
                    return false;
                }
                Intent intent12 = new Intent();
                intent12.setPackage(context.getPackageName());
                intent12.setAction(context.getPackageName() + ".intent.action.COMMAND");
                Iterator<ResolveInfo> it13 = context.getPackageManager().queryBroadcastReceivers(intent12, 64).iterator();
                while (true) {
                    if (!it13.hasNext()) {
                        z12 = false;
                        break;
                    }
                    ResolveInfo next12 = it13.next();
                    if (next12.activityInfo.name.equals("com.taobao.agoo.AgooCommondReceiver") && next12.activityInfo.exported) {
                        z12 = true;
                        break;
                    }
                }
                if (!z12) {
                    handler.post(new AnonymousClass6(context));
                    return false;
                }
                Intent intent13 = new Intent();
                intent13.setPackage(context.getPackageName());
                intent13.setAction(Intent.ACTION_PACKAGE_REMOVED);
                intent13.setData(Uri.parse("package:"));
                Iterator<ResolveInfo> it14 = context.getPackageManager().queryBroadcastReceivers(intent13, 64).iterator();
                while (true) {
                    if (!it14.hasNext()) {
                        z13 = false;
                        break;
                    }
                    ResolveInfo next13 = it14.next();
                    if (next13.activityInfo.name.equals("com.taobao.agoo.AgooCommondReceiver") && next13.activityInfo.exported) {
                        z13 = true;
                        break;
                    }
                }
                if (!z13) {
                    handler.post(new AnonymousClass7(context));
                    return false;
                }
                Intent intent14 = new Intent();
                intent14.setClassName(context.getPackageName(), "com.umeng.message.NotificationProxyBroadcastReceiver");
                Iterator<ResolveInfo> it15 = context.getPackageManager().queryBroadcastReceivers(intent14, 65536).iterator();
                while (true) {
                    if (!it15.hasNext()) {
                        z14 = false;
                        break;
                    }
                    ResolveInfo next14 = it15.next();
                    if (next14.activityInfo.name.equals("com.umeng.message.NotificationProxyBroadcastReceiver") && TextUtils.equals(next14.activityInfo.processName, context.getPackageName()) && !next14.activityInfo.exported) {
                        z14 = true;
                        break;
                    }
                }
                if (!z14) {
                    handler.post(new AnonymousClass8(context));
                    return false;
                }
                Intent intent15 = new Intent();
                intent15.setPackage(context.getPackageName());
                intent15.setAction(MsgConstant.MESSAGE_REGISTER_CALLBACK_ACTION);
                Iterator<ResolveInfo> it16 = context.getPackageManager().queryIntentServices(intent15, 64).iterator();
                while (true) {
                    if (!it16.hasNext()) {
                        z15 = false;
                        break;
                    }
                    ResolveInfo next15 = it16.next();
                    if (next15.serviceInfo.name.equals("com.umeng.message.UmengMessageCallbackHandlerService") && TextUtils.equals(context.getPackageName(), next15.serviceInfo.processName) && !next15.serviceInfo.exported) {
                        z15 = true;
                        break;
                    }
                }
                if (!z15) {
                    handler.post(new AnonymousClass9(context));
                    return false;
                }
                Intent intent16 = new Intent();
                intent16.setPackage(context.getPackageName());
                intent16.setAction(MsgConstant.MESSAGE_ENABLE_CALLBACK_ACTION);
                Iterator<ResolveInfo> it17 = context.getPackageManager().queryIntentServices(intent16, 64).iterator();
                while (true) {
                    if (!it17.hasNext()) {
                        z16 = false;
                        break;
                    }
                    ResolveInfo next16 = it17.next();
                    if (next16.serviceInfo.name.equals("com.umeng.message.UmengMessageCallbackHandlerService") && !next16.serviceInfo.exported) {
                        z16 = true;
                        break;
                    }
                }
                if (!z16) {
                    handler.post(new AnonymousClass10(context));
                    return false;
                }
                Intent intent17 = new Intent();
                intent17.setPackage(context.getPackageName());
                intent17.setAction(MsgConstant.MESSAGE_DISABLE_CALLBACK_ACTION);
                Iterator<ResolveInfo> it18 = context.getPackageManager().queryIntentServices(intent17, 64).iterator();
                while (true) {
                    if (!it18.hasNext()) {
                        z17 = false;
                        break;
                    }
                    ResolveInfo next17 = it18.next();
                    if (next17.serviceInfo.name.equals("com.umeng.message.UmengMessageCallbackHandlerService") && !next17.serviceInfo.exported) {
                        z17 = true;
                        break;
                    }
                }
                if (!z17) {
                    handler.post(new AnonymousClass11(context));
                    return false;
                }
                Intent intent18 = new Intent();
                intent18.setPackage(context.getPackageName());
                intent18.setAction(MsgConstant.MESSAGE_MESSAGE_HANDLER_ACTION);
                Iterator<ResolveInfo> it19 = context.getPackageManager().queryIntentServices(intent18, 64).iterator();
                while (true) {
                    if (!it19.hasNext()) {
                        z18 = false;
                        break;
                    }
                    ResolveInfo next18 = it19.next();
                    if (next18.serviceInfo.name.equals("com.umeng.message.UmengMessageCallbackHandlerService") && !next18.serviceInfo.exported) {
                        z18 = true;
                        break;
                    }
                }
                if (!z18) {
                    handler.post(new AnonymousClass13(context));
                    return false;
                }
                Intent intent19 = new Intent();
                intent19.setClassName(context.getPackageName(), "com.umeng.message.UmengDownloadResourceService");
                Iterator<ResolveInfo> it20 = context.getPackageManager().queryIntentServices(intent19, 65536).iterator();
                while (true) {
                    if (it20.hasNext()) {
                        if (it20.next().serviceInfo.name.equals("com.umeng.message.UmengDownloadResourceService")) {
                            z19 = true;
                            break;
                        }
                    } else {
                        z19 = false;
                        break;
                    }
                }
                if (!z19) {
                    handler.post(new AnonymousClass14(context));
                    return false;
                }
                Intent intent20 = new Intent();
                intent20.setPackage(context.getPackageName());
                intent20.setAction("org.android.agoo.client.MessageReceiverService");
                Iterator<ResolveInfo> it21 = context.getPackageManager().queryIntentServices(intent20, 64).iterator();
                while (true) {
                    if (!it21.hasNext()) {
                        z20 = false;
                        break;
                    }
                    ResolveInfo next19 = it21.next();
                    if (next19.serviceInfo.name.equals("com.umeng.message.UmengMessageIntentReceiverService") && next19.serviceInfo.exported) {
                        String str9 = next19.serviceInfo.processName;
                        if (!TextUtils.isEmpty(str9) && !TextUtils.equals(str9, context.getPackageName())) {
                            z20 = true;
                            break;
                        }
                    }
                }
                if (!z20) {
                    handler.post(new AnonymousClass15(context));
                    return false;
                }
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
                if (packageInfo.requestedPermissions != null) {
                    z30 = false;
                    z29 = false;
                    z28 = false;
                    z27 = false;
                    z26 = false;
                    z25 = false;
                    z24 = false;
                    z23 = false;
                    z22 = false;
                    z21 = false;
                    for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                        if ("android.permission.INTERNET".equals(packageInfo.requestedPermissions[i])) {
                            z30 = true;
                        } else if ("android.permission.ACCESS_WIFI_STATE".equals(packageInfo.requestedPermissions[i])) {
                            z28 = true;
                        } else if ("android.permission.ACCESS_NETWORK_STATE".equals(packageInfo.requestedPermissions[i])) {
                            z29 = true;
                        } else if ("android.permission.WAKE_LOCK".equals(packageInfo.requestedPermissions[i])) {
                            z27 = true;
                        } else if (MsgConstant.PERMISSION_BROADCAST_PACKAGE_ADDED.equals(packageInfo.requestedPermissions[i])) {
                            z26 = true;
                        } else if (MsgConstant.PERMISSION_BROADCAST_PACKAGE_CHANGED.equals(packageInfo.requestedPermissions[i])) {
                            z25 = true;
                        } else if (MsgConstant.PERMISSION_BROADCAST_PACKAGE_INSTALL.equals(packageInfo.requestedPermissions[i])) {
                            z24 = true;
                        } else if (MsgConstant.PERMISSION_BROADCAST_PACKAGE_REPLACED.equals(packageInfo.requestedPermissions[i])) {
                            z23 = true;
                        } else if ("android.permission.GET_TASKS".equals(packageInfo.requestedPermissions[i])) {
                            z22 = true;
                        } else if ("android.permission.RECEIVE_BOOT_COMPLETED".equals(packageInfo.requestedPermissions[i])) {
                            z21 = true;
                        }
                    }
                } else {
                    z30 = false;
                    z29 = false;
                    z28 = false;
                    z27 = false;
                    z26 = false;
                    z25 = false;
                    z24 = false;
                    z23 = false;
                    z22 = false;
                    z21 = false;
                }
                if (z30 && z28 && z29 && z27 && z26 && z25 && z24 && z23 && z22 && z21) {
                    return true;
                }
                handler.post(new AnonymousClass16(context));
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$1  reason: invalid class name */
    public static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass1(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please set umeng appkey!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$12  reason: invalid class name */
    public static class AnonymousClass12 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass12(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please set umeng appsecret!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$18  reason: invalid class name */
    public static class AnonymousClass18 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass18(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct ChannelService in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$19  reason: invalid class name */
    public static class AnonymousClass19 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass19(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct ChannelService in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$20  reason: invalid class name */
    public static class AnonymousClass20 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass20(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct MsgDistributeService in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$21  reason: invalid class name */
    public static class AnonymousClass21 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass21(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct EventReceiver in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$22  reason: invalid class name */
    public static class AnonymousClass22 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass22(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct EventReceiver in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$23  reason: invalid class name */
    public static class AnonymousClass23 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass23(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct EventReceiver in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$24  reason: invalid class name */
    public static class AnonymousClass24 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass24(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct EventReceiver in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$2  reason: invalid class name */
    public static class AnonymousClass2 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass2(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct ServiceReceiver in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$3  reason: invalid class name */
    public static class AnonymousClass3 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass3(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct ServiceReceiver in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$4  reason: invalid class name */
    public static class AnonymousClass4 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass4(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct AgooService in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$5  reason: invalid class name */
    public static class AnonymousClass5 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass5(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct UmengIntentService in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$6  reason: invalid class name */
    public static class AnonymousClass6 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass6(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please replace '${applicationId}.intent.action.COMMAND' with application's applicationId for AgooCommondReceiver in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$7  reason: invalid class name */
    public static class AnonymousClass7 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass7(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct AgooCommondReceiver in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$8  reason: invalid class name */
    public static class AnonymousClass8 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass8(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct NotificationProxyBroadcastReceiver in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$9  reason: invalid class name */
    public static class AnonymousClass9 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass9(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct UmengMessageCallbackHandlerService in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$10  reason: invalid class name */
    public static class AnonymousClass10 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass10(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct UmengMessageCallbackHandlerService in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$11  reason: invalid class name */
    public static class AnonymousClass11 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass11(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct UmengMessageCallbackHandlerService in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$13  reason: invalid class name */
    public static class AnonymousClass13 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass13(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct UmengMessageCallbackHandlerService in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$14  reason: invalid class name */
    public static class AnonymousClass14 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass14(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct UmengDownloadResourceService in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$15  reason: invalid class name */
    public static class AnonymousClass15 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass15(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add or correct UmengMessageIntentReceiverService in AndroidManifest!", 1).show();
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$16  reason: invalid class name */
    public static class AnonymousClass16 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass16(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.a, "Please add required permission in AndroidManifest!", 1).show();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x002d A[SYNTHETIC, Splitter:B:18:0x002d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(java.io.File r3) throws java.io.IOException {
        /*
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x0029 }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ all -> 0x0029 }
            r2.<init>(r3)     // Catch:{ all -> 0x0029 }
            r1.<init>(r2)     // Catch:{ all -> 0x0029 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0027 }
            r3.<init>()     // Catch:{ all -> 0x0027 }
        L_0x0010:
            java.lang.String r0 = r1.readLine()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x001a
            r3.append(r0)     // Catch:{ all -> 0x0027 }
            goto L_0x0010
        L_0x001a:
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0027 }
            r1.close()     // Catch:{ IOException -> 0x0022 }
            goto L_0x0026
        L_0x0022:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0026:
            return r3
        L_0x0027:
            r3 = move-exception
            goto L_0x002b
        L_0x0029:
            r3 = move-exception
            r1 = r0
        L_0x002b:
            if (r1 == 0) goto L_0x0035
            r1.close()     // Catch:{ IOException -> 0x0031 }
            goto L_0x0035
        L_0x0031:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0035:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.proguard.h.b(java.io.File):java.lang.String");
    }

    public static boolean d(Context context) {
        return UMUtils.isMainProgress(context);
    }

    public static void b(Context context, Handler handler) {
        MessageSharedPrefs.getInstance(context).setRegisterTimes(MessageSharedPrefs.getInstance(context).getRegisterTimes() + 1);
        if (d(context)) {
            handler.postDelayed(new AnonymousClass17(context, handler), 20000);
        }
    }

    /* compiled from: Helper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.h$17  reason: invalid class name */
    public static class AnonymousClass17 implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ Handler b;

        AnonymousClass17(Context context, Handler handler) {
            this.a = context;
            this.b = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MessageSharedPrefs.getInstance(this.a).getRegisterTimes() <= 1) {
                UMLog.mutlInfo(h.b, 0, "pushAgent.register\u65b9\u6cd5\u5e94\u8be5\u5728\u4e3b\u8fdb\u7a0b\u548cchannel\u8fdb\u7a0b\u4e2d\u90fd\u88ab\u8c03\u7528!");
                UMLog.aq(k.b, 0, "\\|");
                for (int i = 0; i < 3; i++) {
                    this.b.postDelayed(new AnonymousClass1(), (long) (i * IToastStrategy.LONG_DURATION_TIMEOUT));
                }
            }
        }

        /* compiled from: Helper */
        /* renamed from: com.umeng.message.proguard.h$17$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(AnonymousClass17.this.a, "pushAgent.register\u65b9\u6cd5\u5e94\u8be5\u5728\u4e3b\u8fdb\u7a0b\u548cchannel\u8fdb\u7a0b\u4e2d\u90fd\u88ab\u8c03\u7528!", 1).show();
            }
        }
    }

    public static String a(List<Ucode> list) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(list);
        String encode = URLEncoder.encode(byteArrayOutputStream.toString("ISO-8859-1"), "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return encode;
    }

    public static List<Ucode> g(String str) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(URLDecoder.decode(str, "UTF-8").getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        List<Ucode> list = (List) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return list;
    }

    public static String d(Context context, String str) {
        String str2 = context.getCacheDir() + "/umeng_push_inapp/";
        if (str == null) {
            return str2;
        }
        return str2 + str + NotificationIconUtil.SPLIT_CHAR;
    }

    public static int c() {
        int i;
        int i2;
        if (Build.VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        }
        do {
            i = c.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!c.compareAndSet(i, i2));
        return i;
    }
}
