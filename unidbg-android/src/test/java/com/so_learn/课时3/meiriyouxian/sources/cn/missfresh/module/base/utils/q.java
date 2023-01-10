package cn.missfresh.module.base.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.module.base.support.dialog.f;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: CommonTools */
public class q {
    public static int a(int i) {
        return i | -16777216;
    }

    public static int b(int i) {
        return i | -1291845632;
    }

    public static void a(View view) {
        InputMethodManager inputMethodManager;
        AppMethodBeat.i(23154, false);
        if (!(view == null || (inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)) == null || !inputMethodManager.isActive())) {
            inputMethodManager.showSoftInput(view, 1);
        }
        AppMethodBeat.o(23154);
    }

    public static void a(FragmentActivity fragmentActivity) {
        AppMethodBeat.i(23157, false);
        ((InputMethodManager) fragmentActivity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(fragmentActivity.getWindow().peekDecorView().getWindowToken(), 0);
        AppMethodBeat.o(23157);
    }

    public static void a(FragmentActivity fragmentActivity, MotionEvent motionEvent, List<View> list) {
        boolean z;
        AppMethodBeat.i(23159, false);
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        Rect rect = new Rect();
        Iterator<View> it2 = list.iterator();
        loop0:
        while (true) {
            z = true;
            while (true) {
                if (!it2.hasNext()) {
                    break loop0;
                }
                View next = it2.next();
                if (next != null && next.isShown()) {
                    next.getGlobalVisibleRect(rect);
                    if (rect.contains(x, y) || !z) {
                        z = false;
                    }
                }
            }
        }
        if (z) {
            for (View view : list) {
                if (view != null) {
                    view.clearFocus();
                }
            }
            a(fragmentActivity);
        }
        AppMethodBeat.o(23159);
    }

    public static void a(FragmentActivity fragmentActivity, String str, String str2, String str3) {
        AppMethodBeat.i(23161, false);
        if (b.a(str3)) {
            a.a("\u7535\u8bdd\u53f7\u7801\u683c\u5f0f\u4e0d\u6b63\u786e");
            AppMethodBeat.o(23161);
            return;
        }
        cn.missfresh.module.base.permission.a.a(fragmentActivity, Manifest.permission.CALL_PHONE, new AnonymousClass1(fragmentActivity, str3, str, str2), new c());
        AppMethodBeat.o(23161);
    }

    /* compiled from: CommonTools */
    /* renamed from: cn.missfresh.module.base.utils.q$1  reason: invalid class name */
    static class AnonymousClass1 implements cn.missfresh.basiclib.ui.permission.b {
        final /* synthetic */ FragmentActivity a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;

        AnonymousClass1(FragmentActivity fragmentActivity, String str, String str2, String str3) {
            this.a = fragmentActivity;
            this.b = str;
            this.c = str2;
            this.d = str3;
        }

        @Override // cn.missfresh.basiclib.ui.permission.b
        public void l_(String str) {
            AppMethodBeat.i(23150, false);
            q.a(this.a, this.b);
            AppMethodBeat.o(23150);
        }

        @Override // cn.missfresh.basiclib.ui.permission.b
        public void t_() {
            AppMethodBeat.i(23151, false);
            f fVar = new f(this.a, this.c, this.d, "\u786e\u8ba4", "\u53d6\u6d88", new CommonTools$1$1(this), null);
            fVar.a(true);
            fVar.show();
            AppMethodBeat.o(23151);
        }
    }

    public static void a(Context context, String str) {
        AppMethodBeat.i(23162, false);
        if (b.a(str)) {
            a.a("\u7535\u8bdd\u53f7\u7801\u683c\u5f0f\u4e0d\u6b63\u786e");
            AppMethodBeat.o(23162);
            return;
        }
        context.startActivity(ad.a(context, new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + str))));
        AppMethodBeat.o(23162);
    }

    public static void a(Context context) {
        boolean z = false;
        AppMethodBeat.i(23169, false);
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName()));
            intent.addFlags(268435456);
            if (context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0) {
                z = true;
            }
            if (z) {
                context.startActivity(ad.a(context, intent));
            } else {
                a.a("\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u53bb\u76f8\u5e94\u7684\u5e94\u7528\u5e02\u573a\u8bc4\u5206");
            }
        } catch (Exception unused) {
        }
        AppMethodBeat.o(23169);
    }

    public static String a() {
        return Build.MODEL;
    }

    public static boolean a(Activity activity) {
        boolean z = false;
        AppMethodBeat.i(23175, false);
        if (activity != null && !activity.isFinishing()) {
            z = true;
        }
        AppMethodBeat.o(23175);
        return z;
    }

    public static ArrayList<String> a(Map<String, String> map) {
        ArrayList<String> arrayList;
        Exception e;
        AppMethodBeat.i(23176, false);
        if (map == null || map.size() == 0) {
            AppMethodBeat.o(23176);
            return null;
        }
        try {
            arrayList = new ArrayList<>();
            try {
                for (String str : map.keySet()) {
                    arrayList.add(str);
                    arrayList.add(map.get(str));
                }
            } catch (Exception e2) {
                e = e2;
                d.a("converMap2List", e);
                AppMethodBeat.o(23176);
                return arrayList;
            }
        } catch (Exception e3) {
            e = e3;
            arrayList = null;
            d.a("converMap2List", e);
            AppMethodBeat.o(23176);
            return arrayList;
        }
        AppMethodBeat.o(23176);
        return arrayList;
    }

    public static HashMap<String, Object> a(List<String> list) {
        int i = 0;
        AppMethodBeat.i(23177, false);
        if (b.a(list)) {
            AppMethodBeat.o(23177);
            return null;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        while (i < list.size()) {
            try {
                String str = list.get(i);
                String str2 = "";
                int i2 = i + 1;
                if (i2 < list.size()) {
                    str2 = list.get(i2);
                }
                hashMap.put(str, str2);
                i = i2 + 1;
            } catch (Exception e) {
                d.a("converList2Map", e);
            }
        }
        AppMethodBeat.o(23177);
        return hashMap;
    }

    public static Bundle a(String str) {
        AppMethodBeat.i(23178, false);
        Bundle bundle = new Bundle();
        try {
            JSONObject parseObject = JSON.parseObject(str);
            if (parseObject == null) {
                AppMethodBeat.o(23178);
                return bundle;
            }
            for (String str2 : parseObject.keySet()) {
                bundle.putString(str2, parseObject.getString(str2));
            }
            AppMethodBeat.o(23178);
            return bundle;
        } catch (Exception unused) {
            AppMethodBeat.o(23178);
            return bundle;
        }
    }

    public static HashMap<String, String> a(HashMap<String, Object> hashMap) {
        AppMethodBeat.i(23181, false);
        if (hashMap == null || hashMap.size() <= 0) {
            AppMethodBeat.o(23181);
            return null;
        }
        HashMap<String, String> hashMap2 = new HashMap<>();
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            hashMap2.put(entry.getKey(), String.valueOf(entry.getValue()));
        }
        AppMethodBeat.o(23181);
        return hashMap2;
    }

    public static String a(ProductsEntity productsEntity) {
        String str;
        AppMethodBeat.i(23183, false);
        if (productsEntity != null) {
            str = b.a(productsEntity.getThemeTag()) ? productsEntity.getProductTag() : productsEntity.getThemeTag();
        } else {
            str = "";
        }
        AppMethodBeat.o(23183);
        return str;
    }

    public static String a(String str, String str2) {
        AppMethodBeat.i(23184, false);
        if (b.a(str)) {
            str = str2;
        }
        AppMethodBeat.o(23184);
        return str;
    }

    public static int a(float f, int i) {
        AppMethodBeat.i(23185, false);
        int min = (Math.min(255, Math.max(0, (int) (f * 255.0f))) << 24) + (i & 16777215);
        AppMethodBeat.o(23185);
        return min;
    }

    public static int b(String str) {
        AppMethodBeat.i(23187, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(23187);
            return 0;
        }
        try {
            int intValue = Integer.valueOf(Uri.parse(str).getQueryParameter("mryxw")).intValue();
            AppMethodBeat.o(23187);
            return intValue;
        } catch (Exception unused) {
            AppMethodBeat.o(23187);
            return 0;
        }
    }

    public static int c(String str) {
        AppMethodBeat.i(23188, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(23188);
            return 0;
        }
        try {
            int intValue = Integer.valueOf(Uri.parse(str).getQueryParameter("mryxh")).intValue();
            AppMethodBeat.o(23188);
            return intValue;
        } catch (Exception unused) {
            AppMethodBeat.o(23188);
            return 0;
        }
    }

    public static float d(String str) {
        AppMethodBeat.i(23189, false);
        if (b.a(str)) {
            AppMethodBeat.o(23189);
            return 0.0f;
        }
        Uri parse = Uri.parse(str);
        String queryParameter = parse.getQueryParameter("mryxw");
        String queryParameter2 = parse.getQueryParameter("mryxh");
        try {
            int parseInt = Integer.parseInt(queryParameter);
            int parseInt2 = Integer.parseInt(queryParameter2);
            if (parseInt2 != 0) {
                float f = (((float) parseInt) * 1.0f) / ((float) parseInt2);
                AppMethodBeat.o(23189);
                return f;
            }
            AppMethodBeat.o(23189);
            return 0.0f;
        } catch (Exception unused) {
            AppMethodBeat.o(23189);
            return 0.0f;
        }
    }

    public static String e(String str) {
        AppMethodBeat.i(23190, false);
        if (!b.a(str)) {
            Uri parse = Uri.parse(str);
            String str2 = parse.getQueryParameter("mryxw") + ":" + parse.getQueryParameter("mryxh");
            AppMethodBeat.o(23190);
            return str2;
        }
        AppMethodBeat.o(23190);
        return null;
    }

    public static int a(TextView textView) {
        AppMethodBeat.i(23191, false);
        Paint paint = new Paint();
        paint.setTextSize(textView.getTextSize());
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int ceil = (int) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent));
        AppMethodBeat.o(23191);
        return ceil;
    }

    public static void a(TextView textView, String str, String str2) {
        AppMethodBeat.i(23192, false);
        if (b.a(str)) {
            textView.setText(str2);
            AppMethodBeat.o(23192);
            return;
        }
        int a = a(textView);
        cn.missfresh.lib.image.c.a(textView).a(str).a((int) (((float) a) * d(str)), a).a(new AnonymousClass2(String.format(textView.getResources().getString(R.string.format_title_image), str2), textView));
        AppMethodBeat.o(23192);
    }

    /* compiled from: CommonTools */
    /* renamed from: cn.missfresh.module.base.utils.q$2  reason: invalid class name */
    static class AnonymousClass2 extends cn.missfresh.lib.image.c.b<BitmapDrawable> {
        final /* synthetic */ String a;
        final /* synthetic */ TextView b;

        AnonymousClass2(String str, TextView textView) {
            this.a = str;
            this.b = textView;
        }

        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(23153, false);
            a((BitmapDrawable) obj);
            AppMethodBeat.o(23153);
        }

        public void a(BitmapDrawable bitmapDrawable) {
            AppMethodBeat.i(23152, false);
            SpannableString spannableString = new SpannableString(this.a);
            spannableString.setSpan(new cn.missfresh.module.base.span.a(this.b.getContext(), bitmapDrawable.getBitmap()), 0, 2, 18);
            this.b.setText(spannableString);
            AppMethodBeat.o(23152);
        }
    }
}
