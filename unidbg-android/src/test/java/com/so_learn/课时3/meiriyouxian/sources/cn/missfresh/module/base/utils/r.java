package cn.missfresh.module.base.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.security.keystore.KeyProperties;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.android.arouter.b.a;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.meituan.android.walle.f;
import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: ContextUtils */
public class r {
    public static String a = "";
    public static String b = "";
    public static String c = "";
    private static String d = null;
    private static String e = "";
    private static String f = "";
    private static String g = "";
    private static String h = null;
    private static String i = "";
    private static String j = "";
    private static String k = "";
    private static String l = "";
    private static IApplicationDelegateService m;

    public static String f(Context context) {
        return "";
    }

    public static boolean a() {
        AppMethodBeat.i(23219, false);
        boolean equalsIgnoreCase = Environment.MEDIA_MOUNTED.equalsIgnoreCase(Environment.getExternalStorageState());
        AppMethodBeat.o(23219);
        return equalsIgnoreCase;
    }

    public static String a(Context context) {
        AppMethodBeat.i(23220, false);
        boolean a2 = new c().a("android.permission.WRITE_EXTERNAL_STORAGE");
        if (!a() || context.getExternalCacheDir() == null || !a2) {
            String absolutePath = context.getCacheDir().getAbsolutePath();
            AppMethodBeat.o(23220);
            return absolutePath;
        }
        String absolutePath2 = context.getExternalCacheDir().getAbsolutePath();
        AppMethodBeat.o(23220);
        return absolutePath2;
    }

    public static String b() {
        AppMethodBeat.i(23221, false);
        String absolutePath = ((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication().getCacheDir().getAbsolutePath();
        AppMethodBeat.o(23221);
        return absolutePath;
    }

    public static String c() {
        AppMethodBeat.i(23222, false);
        File externalFilesDir = ((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null || !externalFilesDir.exists()) {
            String b2 = b();
            AppMethodBeat.o(23222);
            return b2;
        }
        String absolutePath = externalFilesDir.getAbsolutePath();
        AppMethodBeat.o(23222);
        return absolutePath;
    }

    public static String d() {
        IApplicationDelegateService iApplicationDelegateService;
        AppMethodBeat.i(23223, false);
        if (b.a(d) && (iApplicationDelegateService = (IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()) != null) {
            d = iApplicationDelegateService.getAppVersion();
        }
        String str = d;
        AppMethodBeat.o(23223);
        return str;
    }

    public static String e() {
        AppMethodBeat.i(23224, false);
        try {
            String str = Build.MODEL;
            AppMethodBeat.o(23224);
            return str;
        } catch (Exception e2) {
            e2.printStackTrace();
            AppMethodBeat.o(23224);
            return "";
        }
    }

    public static boolean b(Context context) {
        boolean z = false;
        AppMethodBeat.i(23225, false);
        if (context == null) {
            AppMethodBeat.o(23225);
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            AppMethodBeat.o(23225);
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            z = true;
        }
        AppMethodBeat.o(23225);
        return z;
    }

    public static String f() {
        IApplicationDelegateService iApplicationDelegateService;
        AppMethodBeat.i(23226, false);
        if (b.a(e)) {
            synchronized (r.class) {
                try {
                    if (b.a(e) && (iApplicationDelegateService = (IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()) != null) {
                        try {
                            e = f.a(iApplicationDelegateService.getApplication());
                            if (b.a(e)) {
                                e = "";
                            }
                        } catch (Exception e2) {
                            d.b("tag", "e=" + e2);
                        }
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(23226);
                    throw th;
                }
            }
        }
        String str = e;
        AppMethodBeat.o(23226);
        return str;
    }

    public static int g() {
        AppMethodBeat.i(23227, false);
        int nextInt = new Random().nextInt(Integer.MAX_VALUE);
        AppMethodBeat.o(23227);
        return nextInt;
    }

    public static String h() {
        AppMethodBeat.i(23228, false);
        if (Build.VERSION.SDK_INT >= 29) {
            String str = f;
            AppMethodBeat.o(23228);
            return str;
        }
        if (b.a(f)) {
            synchronized (r.class) {
                try {
                    if (b.a(f) && new c().a("android.permission.READ_PHONE_STATE")) {
                        Application application = ((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication();
                        TelephonyManager telephonyManager = (TelephonyManager) application.getSystemService("phone");
                        String e2 = e(application);
                        if (b.a(e2)) {
                            e2 = "";
                        }
                        String simSerialNumber = telephonyManager.getSimSerialNumber();
                        if (b.a(simSerialNumber)) {
                            simSerialNumber = "";
                        }
                        f = a(new UUID((long) i().hashCode(), ((long) simSerialNumber.hashCode()) | (((long) e2.hashCode()) << 32)).toString());
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(23228);
                    throw th;
                }
            }
        }
        String str2 = f;
        AppMethodBeat.o(23228);
        return str2;
    }

    public static String i() {
        AppMethodBeat.i(23229, false);
        if (b.a(g)) {
            synchronized (r.class) {
                try {
                    if (b.a(g)) {
                        g = Settings.Secure.getString(((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication().getContentResolver(), "android_id");
                        if (b.a(g)) {
                            try {
                                g = c(m.getApplication().getApplicationContext());
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(23229);
                    throw th;
                }
            }
        }
        String str = g;
        AppMethodBeat.o(23229);
        return str;
    }

    public static synchronized String c(Context context) {
        String str;
        synchronized (r.class) {
            AppMethodBeat.i(23230, false);
            if (b.a(h)) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_UNIQUE_ID", 0);
                h = sharedPreferences.getString("PREF_UNIQUE_ID", null);
                if (b.a(h)) {
                    h = UUID.randomUUID().toString();
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("PREF_UNIQUE_ID", h);
                    edit.commit();
                }
            }
            str = h;
            AppMethodBeat.o(23230);
        }
        return str;
    }

    public static String a(String str) {
        AppMethodBeat.i(23231, false);
        try {
            MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[charArray.length];
            for (int i2 = 0; i2 < charArray.length; i2++) {
                bArr[i2] = (byte) charArray[i2];
            }
            byte[] digest = instance.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                int i3 = b2 & 255;
                if (i3 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i3));
            }
            String stringBuffer2 = stringBuffer.toString();
            AppMethodBeat.o(23231);
            return stringBuffer2;
        } catch (Exception e2) {
            d.a("ContextUtils", e2);
            AppMethodBeat.o(23231);
            return "";
        }
    }

    public static String a(FragmentActivity fragmentActivity, int i2) {
        AppMethodBeat.i(23232, false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("_display_name", a(simpleDateFormat.format(new Date())) + ".jpg");
        contentValues.put("mime_type", "image/jpeg");
        Uri insert = fragmentActivity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        if (insert == null) {
            AppMethodBeat.o(23232);
            return null;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, insert);
        a(fragmentActivity, new AnonymousClass1(fragmentActivity, intent, i2));
        String uri = insert.toString();
        AppMethodBeat.o(23232);
        return uri;
    }

    /* compiled from: ContextUtils */
    /* renamed from: cn.missfresh.module.base.utils.r$1  reason: invalid class name */
    static class AnonymousClass1 implements cn.missfresh.basiclib.ui.permission.a {
        final /* synthetic */ FragmentActivity a;
        final /* synthetic */ Intent b;
        final /* synthetic */ int c;

        @Override // cn.missfresh.basiclib.ui.permission.a
        public void G_() {
        }

        @Override // cn.missfresh.basiclib.ui.permission.a
        public void a(String str, int i) {
        }

        AnonymousClass1(FragmentActivity fragmentActivity, Intent intent, int i) {
            this.a = fragmentActivity;
            this.b = intent;
            this.c = i;
        }

        @Override // cn.missfresh.basiclib.ui.permission.b
        public void l_(String str) {
            AppMethodBeat.i(23217, false);
            cn.missfresh.ui.a.a.a("\u6ca1\u6709\u76f8\u673a\u6743\u9650");
            AppMethodBeat.o(23217);
        }

        @Override // cn.missfresh.basiclib.ui.permission.b
        public void t_() {
            AppMethodBeat.i(23218, false);
            this.a.startActivityForResult(this.b, this.c);
            AppMethodBeat.o(23218);
        }
    }

    private static void a(FragmentActivity fragmentActivity, cn.missfresh.basiclib.ui.permission.a aVar) {
        AppMethodBeat.i(23233, false);
        new c().a(fragmentActivity, aVar, Manifest.permission.CAMERA, "android.permission.WRITE_EXTERNAL_STORAGE");
        AppMethodBeat.o(23233);
    }

    public static void a(Activity activity, int i2) {
        AppMethodBeat.i(23235, false);
        try {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            activity.startActivityForResult(intent, i2);
        } catch (ActivityNotFoundException e2) {
            e2.printStackTrace();
        }
        AppMethodBeat.o(23235);
    }

    public static int d(Context context) {
        int i2;
        AppMethodBeat.i(23236, false);
        int i3 = 1001;
        if (context == null) {
            AppMethodBeat.o(23236);
            return 1001;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            String typeName = activeNetworkInfo.getTypeName();
            if (typeName.equalsIgnoreCase("WIFI")) {
                i2 = 4001;
            } else if (typeName.equalsIgnoreCase("MOBILE")) {
                String defaultHost = Proxy.getDefaultHost();
                if (defaultHost == null || defaultHost.equals("") || !a(telephonyManager)) {
                    i3 = 2001;
                } else {
                    i2 = 3001;
                }
            } else {
                i3 = -1;
            }
            i3 = i2;
        }
        d.d("ContextUtils", "[getNetType]...end.rs:" + i3);
        AppMethodBeat.o(23236);
        return i3;
    }

    private static boolean a(TelephonyManager telephonyManager) {
        AppMethodBeat.i(23237, false);
        switch (telephonyManager.getNetworkType()) {
            case 0:
                AppMethodBeat.o(23237);
                return false;
            case 1:
                AppMethodBeat.o(23237);
                return false;
            case 2:
                AppMethodBeat.o(23237);
                return false;
            case 3:
                AppMethodBeat.o(23237);
                return true;
            case 4:
                AppMethodBeat.o(23237);
                return false;
            case 5:
                AppMethodBeat.o(23237);
                return true;
            case 6:
                AppMethodBeat.o(23237);
                return true;
            case 7:
                AppMethodBeat.o(23237);
                return false;
            case 8:
                AppMethodBeat.o(23237);
                return true;
            case 9:
                AppMethodBeat.o(23237);
                return true;
            case 10:
                AppMethodBeat.o(23237);
                return true;
            case 11:
                AppMethodBeat.o(23237);
                return false;
            case 12:
                AppMethodBeat.o(23237);
                return true;
            case 13:
                AppMethodBeat.o(23237);
                return true;
            case 14:
                AppMethodBeat.o(23237);
                return true;
            case 15:
                AppMethodBeat.o(23237);
                return true;
            default:
                AppMethodBeat.o(23237);
                return false;
        }
    }

    public static String j() {
        AppMethodBeat.i(23238, false);
        q();
        IApplicationDelegateService iApplicationDelegateService = m;
        if (iApplicationDelegateService != null) {
            String e2 = e(iApplicationDelegateService.getApplication());
            AppMethodBeat.o(23238);
            return e2;
        }
        String str = i;
        AppMethodBeat.o(23238);
        return str;
    }

    public static String e(Context context) {
        AppMethodBeat.i(23239, false);
        if (Build.VERSION.SDK_INT >= 29) {
            AppMethodBeat.o(23239);
            return "";
        }
        if (b.a(i)) {
            synchronized (r.class) {
                try {
                    if (b.a(i) && new c().a("android.permission.READ_PHONE_STATE")) {
                        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
                        if (telephonyManager != null) {
                            i = telephonyManager.getDeviceId();
                        }
                        if (b.a(i)) {
                            i = "";
                        }
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(23239);
                    throw th;
                }
            }
        }
        String str = i;
        AppMethodBeat.o(23239);
        return str;
    }

    public static String k() {
        AppMethodBeat.i(23240, false);
        String f2 = f(null);
        AppMethodBeat.o(23240);
        return f2;
    }

    public static String l() {
        AppMethodBeat.i(23241, false);
        q();
        IApplicationDelegateService iApplicationDelegateService = m;
        if (iApplicationDelegateService != null) {
            String g2 = g(iApplicationDelegateService.getApplication());
            AppMethodBeat.o(23241);
            return g2;
        }
        String str = j;
        AppMethodBeat.o(23241);
        return str;
    }

    public static String g(Context context) {
        AppMethodBeat.i(23242, false);
        if (b.a(j)) {
            synchronized (r.class) {
                try {
                    if (b.a(j)) {
                        j = e.O();
                        if (b.a(j)) {
                            String e2 = e(context);
                            if (b.a(e2)) {
                                e2 = h();
                            }
                            if (b.a(e2)) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(Math.random());
                                sb.append(System.currentTimeMillis());
                                if (sb.length() > 60) {
                                    e2 = sb.substring(0, 60);
                                } else {
                                    e2 = sb.toString();
                                }
                            }
                            j = "android" + e2;
                            e.A(j);
                        }
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(23242);
                    throw th;
                }
            }
        }
        String str = j;
        AppMethodBeat.o(23242);
        return str;
    }

    public static boolean a(Context context, String str) {
        AppMethodBeat.i(23243, false);
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            for (int i2 = 0; i2 < installedPackages.size(); i2++) {
                if (str.equals(installedPackages.get(i2).packageName)) {
                    AppMethodBeat.o(23243);
                    return true;
                }
            }
        }
        AppMethodBeat.o(23243);
        return false;
    }

    public static void a(TextView textView, boolean z) {
        AppMethodBeat.i(23244, false);
        if (z) {
            textView.getPaint().setFlags(17);
        } else {
            textView.getPaint().setFlags(0);
        }
        AppMethodBeat.o(23244);
    }

    public static void a(TextView textView, String str, int i2) {
        AppMethodBeat.i(23245, false);
        a(textView, str, "\\d+\\.\\d+[\u6298\u5143]|\\d+[\u6298\u5143]", i2, 0);
        AppMethodBeat.o(23245);
    }

    public static void a(TextView textView, String str, String str2, int i2, int i3) {
        AppMethodBeat.i(23247, false);
        if (textView == null || b.a(str)) {
            AppMethodBeat.o(23247);
            return;
        }
        Matcher matcher = Pattern.compile(str2).matcher(str);
        AbsoluteSizeSpan absoluteSizeSpan = null;
        if (i3 != 0) {
            int b2 = aw.b(textView.getTextSize());
            int i4 = i3 + b2;
            if (i4 <= 0) {
                i4 = b2;
            }
            absoluteSizeSpan = new AbsoluteSizeSpan(i4, true);
        }
        SpannableString spannableString = new SpannableString(str);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            spannableString.setSpan(new ForegroundColorSpan(i2), start, end, 33);
            if (absoluteSizeSpan != null) {
                spannableString.setSpan(absoluteSizeSpan, start, end, 33);
            }
        }
        textView.setText(spannableString);
        AppMethodBeat.o(23247);
    }

    public static String b(String str) {
        AppMethodBeat.i(23248, false);
        String a2 = a(str, 0);
        AppMethodBeat.o(23248);
        return a2;
    }

    public static String a(String str, int i2) {
        String str2;
        AppMethodBeat.i(23249, false);
        if (Build.VERSION.SDK_INT < 18) {
            AppMethodBeat.o(23249);
            return str;
        } else if (b.a(str) || (!str.startsWith("http:") && !str.startsWith("https"))) {
            AppMethodBeat.o(23249);
            return str;
        } else {
            String lowerCase = str.toLowerCase();
            if (lowerCase.contains("?")) {
                lowerCase = lowerCase.substring(0, lowerCase.indexOf("?"));
            }
            if (!lowerCase.endsWith(".jpg") && !lowerCase.endsWith(".jpeg") && !lowerCase.endsWith(".png")) {
                AppMethodBeat.o(23249);
                return str;
            } else if (Build.VERSION.SDK_INT > 18 || (!lowerCase.endsWith(".png") && !lowerCase.endsWith(".gif"))) {
                Matcher matcher = Pattern.compile("^(http://|https://)[^\\/]+\\/").matcher(str);
                if (!matcher.find()) {
                    AppMethodBeat.o(23249);
                    return str;
                }
                String lowerCase2 = matcher.group().toLowerCase();
                if (lowerCase2.contains("image.missfresh.cn") || lowerCase2.contains("ufile.ucloud.cn") || lowerCase2.contains("ufile.ucloud.com.cn") || lowerCase2.contains("images.missfresh.cn")) {
                    if (!str.contains("iopcmd=convert&dst=webp")) {
                        if (!str.contains("?")) {
                            str = str.concat("?").concat("iopcmd=convert&dst=webp");
                        } else {
                            if (str.contains("iopcmd")) {
                                str2 = str.concat(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
                            } else {
                                str2 = str.concat("&");
                            }
                            str = str2.concat("iopcmd=convert&dst=webp");
                        }
                    }
                    if (i2 > 0 && !str.contains("iopcmd=thumbnail")) {
                        str = str.concat("|iopcmd=thumbnail&type=4&width=" + i2);
                    }
                    if (!str.contains("iopcmd=thumbnail")) {
                        str = str.concat("&q=85");
                    }
                    AppMethodBeat.o(23249);
                    return str;
                }
                AppMethodBeat.o(23249);
                return str;
            } else {
                AppMethodBeat.o(23249);
                return str;
            }
        }
    }

    public static void a(Activity activity, String str) {
        AppMethodBeat.i(23250, false);
        if (!b.a(str)) {
            try {
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (Exception e2) {
                d.a("ContextUtils", e2);
            }
        }
        AppMethodBeat.o(23250);
    }

    public static String m() {
        return Build.VERSION.RELEASE;
    }

    public static void b(TextView textView, String str, int i2) {
        AppMethodBeat.i(23251, false);
        if (i2 < 1 || b.a(str)) {
            textView.setText(str);
        } else {
            textView.setText((String) TextUtils.ellipsize(str, textView.getPaint(), (float) i2, TextUtils.TruncateAt.END));
        }
        AppMethodBeat.o(23251);
    }

    public static void a(Activity activity) {
        AppMethodBeat.i(23252, false);
        if (activity == null) {
            AppMethodBeat.o(23252);
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View peekDecorView = activity.getWindow().peekDecorView();
        if (!(peekDecorView == null || inputMethodManager == null)) {
            inputMethodManager.hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
        AppMethodBeat.o(23252);
    }

    public static void a(View view) {
        AppMethodBeat.i(23253, false);
        if (view == null) {
            AppMethodBeat.o(23253);
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(view, 0);
        }
        AppMethodBeat.o(23253);
    }

    public static String n() {
        IApplicationDelegateService iApplicationDelegateService;
        AppMethodBeat.i(23254, false);
        if (b.a(k)) {
            synchronized (r.class) {
                try {
                    if (!(!b.a(k) || (iApplicationDelegateService = (IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()) == null || iApplicationDelegateService.getApplication() == null)) {
                        k = e(iApplicationDelegateService.getApplication()) + g.a().h();
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(23254);
                    throw th;
                }
            }
        }
        String str = k;
        AppMethodBeat.o(23254);
        return str;
    }

    public static String o() {
        AppMethodBeat.i(23255, false);
        if (b.a(l)) {
            l = "NewMissFresh/" + d() + "(Android; )";
        }
        String str = l;
        AppMethodBeat.o(23255);
        return str;
    }

    public static String p() {
        AppMethodBeat.i(23256, false);
        IApplicationDelegateService iApplicationDelegateService = (IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation();
        if (iApplicationDelegateService != null) {
            String h2 = h(iApplicationDelegateService.getApplication());
            AppMethodBeat.o(23256);
            return h2;
        }
        String d2 = d();
        AppMethodBeat.o(23256);
        return d2;
    }

    public static String h(Context context) {
        String str;
        AppMethodBeat.i(23257, false);
        try {
            int i2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            if (i2 > 0) {
                int i3 = i2 / 1000000;
                int i4 = (i2 % 1000000) / 10000;
                int i5 = (i2 % 10000) / 100;
                int i6 = i2 % 100;
                if (i3 > 0) {
                    str = i3 + "." + i4 + "." + i5 + "." + i6;
                } else {
                    str = null;
                }
                AppMethodBeat.o(23257);
                return str;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        String d2 = d();
        AppMethodBeat.o(23257);
        return d2;
    }

    public static synchronized IApplicationDelegateService q() {
        IApplicationDelegateService iApplicationDelegateService;
        synchronized (r.class) {
            AppMethodBeat.i(23258, false);
            if (m == null) {
                m = (IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation();
            }
            iApplicationDelegateService = m;
            AppMethodBeat.o(23258);
        }
        return iApplicationDelegateService;
    }

    public static Application r() {
        AppMethodBeat.i(23259, false);
        IApplicationDelegateService q = q();
        if (q != null) {
            Application application = q.getApplication();
            AppMethodBeat.o(23259);
            return application;
        }
        AppMethodBeat.o(23259);
        return null;
    }
}
