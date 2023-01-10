package com.sobot.chat.utils;

import android.content.Context;
import android.graphics.Color;
import android.net.wifi.WifiEnterpriseConfig;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.sobot.chat.widget.timePicker.SobotTimePickerView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: DateUtil */
public class f {
    public static final SimpleDateFormat a = new SimpleDateFormat("HH:mm", Locale.getDefault());
    public static final SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    public static final SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static final SimpleDateFormat d = new SimpleDateFormat("HH:mm", Locale.getDefault());
    public static final SimpleDateFormat e = new SimpleDateFormat("mm:ss", Locale.getDefault());
    public static final SimpleDateFormat f = new SimpleDateFormat("MM\u6708dd\u65e5", Locale.getDefault());
    public static final SimpleDateFormat g = new SimpleDateFormat("MM-dd", Locale.getDefault());
    public static String h = "yyyy-MM-dd";
    public static String i = "yyyy-MM-dd HH:mm:ss";
    public static String j = "yyyy\u5e74M\u6708d\u65e5";

    public static String a(long j2, SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.format(new Date(j2));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static long a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            Calendar instance = Calendar.getInstance();
            instance.setTime(e.parse(str));
            return (long) instance.get(13);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static String a(Long l, String str) {
        return new SimpleDateFormat(str).format(new Date(l.longValue()));
    }

    public static String a(String str, boolean z, String str2, Boolean bool) {
        if (str == null || "".equals(str) || str.length() < 19) {
            return "";
        }
        String b2 = b(str, "yyyy-MM-dd HH:mm:ss", bool);
        Date date = null;
        try {
            date = b.parse(b2);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getDefault());
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeZone(TimeZone.getDefault());
        instance2.set(1, instance.get(1));
        instance2.set(2, instance.get(2));
        instance2.set(5, instance.get(5));
        instance2.set(11, 0);
        instance2.set(12, 0);
        instance2.set(13, 0);
        Calendar instance3 = Calendar.getInstance();
        instance3.setTimeZone(TimeZone.getDefault());
        instance3.set(1, instance.get(1));
        instance3.set(2, instance.get(2));
        instance3.set(5, instance.get(5) - 1);
        instance3.set(11, 0);
        instance3.set(12, 0);
        instance3.set(13, 0);
        if (date != null) {
            instance.setTime(date);
        }
        if (instance.after(instance2)) {
            return str2 + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + b2.split(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER)[1].substring(0, 5);
        }
        int indexOf = b2.indexOf("-") + 1;
        if (z) {
            return b2.substring(indexOf, b2.length()).substring(0, 11);
        }
        return b2.substring(indexOf, b2.length()).substring(0, 5);
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            Calendar instance = Calendar.getInstance();
            Calendar instance2 = Calendar.getInstance();
            instance2.set(11, 0);
            instance2.set(12, 0);
            instance2.set(13, 0);
            instance.setTime(new Date(Long.parseLong(str)));
            if (instance.before(instance2)) {
                return a(Long.parseLong(str), f);
            }
            return a(Long.parseLong(str), d);
        } catch (Exception unused) {
            return "";
        }
    }

    public static Date a(String str, SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e2) {
            System.out.println(e2.getMessage());
            return null;
        }
    }

    public static void a(Context context, View view, View view2, Date date, int i2) {
        Calendar instance = Calendar.getInstance();
        if (date != null) {
            instance.setTime(date);
        }
        boolean[] zArr = new boolean[6];
        if (i2 == 0) {
            // fill-array-data instruction
            zArr[0] = true;
            zArr[1] = true;
            zArr[2] = true;
            zArr[3] = false;
            zArr[4] = false;
            zArr[5] = false;
        } else {
            // fill-array-data instruction
            zArr[0] = false;
            zArr[1] = false;
            zArr[2] = false;
            zArr[3] = true;
            zArr[4] = true;
            zArr[5] = false;
        }
        new SobotTimePickerView.a(context, new AnonymousClass1(i2, view, context)).a(zArr).a("", "", "", "", "", "").a(false).h(q.d(context, "sobot_line_1dp")).g(17).f(17).d(q.d(context, "sobot_common_gray6")).e(q.d(context, "sobot_common_gray1")).a(q.f(context, i2 == 0 ? "sobot_title_date" : "sobot_title_time")).b(Color.parseColor("#0DAEAF")).a(Color.parseColor("#FFFFFFFF")).k(q.d(context, "sobot_common_gray2")).j(q.d(context, "sobot_common_wenzi_black")).a(instance).c(q.d(context, "sobot_common_gray6")).i(Integer.MIN_VALUE).a((ViewGroup) null).a(2.0f).a().a(view2);
    }

    /* compiled from: DateUtil */
    /* renamed from: com.sobot.chat.utils.f$1  reason: invalid class name */
    static class AnonymousClass1 implements SobotTimePickerView.b {
        final /* synthetic */ int a;
        final /* synthetic */ View b;
        final /* synthetic */ Context c;

        AnonymousClass1(int i, View view, Context context) {
            this.a = i;
            this.b = view;
            this.c = context;
        }

        @Override // com.sobot.chat.widget.timePicker.SobotTimePickerView.b
        public void a(Date date, View view) {
            if (view != null && (view instanceof TextView) && date != null) {
                ((TextView) view).setText((this.a == 0 ? f.c : f.a).format(date));
                TextView textView = (TextView) this.b.findViewById(q.a(this.c, "id", "work_order_customer_field_text_lable"));
                ((LinearLayout) this.b.findViewById(q.a(this.c, "id", "work_order_customer_field_ll"))).setVisibility(0);
                Context context = this.c;
                textView.setTextColor(ContextCompat.getColor(context, q.c(context, "sobot_common_gray2")));
                textView.setTextSize(12.0f);
            }
        }
    }

    public static String a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(h);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return simpleDateFormat.format(new Date());
    }

    public static String b() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(i);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return simpleDateFormat.format(new Date());
    }

    public static String a(String str, String str2, Boolean bool) {
        return b(str, str2, bool);
    }

    public static Date a(String str, String str2) {
        Date date = new Date();
        try {
            return new SimpleDateFormat(str2).parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return date;
        }
    }

    public static String a(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }

    public static String b(String str, String str2, Boolean bool) {
        Date date;
        if (!bool.booleanValue()) {
            new SimpleDateFormat(str2);
            try {
                return a(a(str, "yyyy-MM-dd HH:mm:ss"), str2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date2 = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e3) {
            e3.printStackTrace();
            date = null;
        }
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        try {
            date2 = simpleDateFormat.parse(simpleDateFormat.format(Long.valueOf(date.getTime())));
        } catch (ParseException e4) {
            e4.printStackTrace();
        }
        return a(date2, str2);
    }
}
