package cn.missfresh.module.base.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.PricePro;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.android.internal.logging.nano.MetricsProto;
import java.util.HashMap;
import java.util.Map;

/* compiled from: UIUtil */
public class aw {
    private static final Map<String, Long> a = new HashMap();
    private static int b = 0;
    private static int c = 0;

    /* compiled from: UIUtil */
    public interface a {
        void a(String str);
    }

    static {
        AppMethodBeat.i(23573, false);
        AppMethodBeat.o(23573);
    }

    private static void d() {
        int i = 23529;
        AppMethodBeat.i(23529, false);
        if (c == 0 || b == 0) {
            synchronized (aw.class) {
                try {
                    if (b == 0 || c == 0) {
                        WindowManager windowManager = (WindowManager) ((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getApplication().getSystemService(Context.WINDOW_SERVICE);
                        if (windowManager != null) {
                            DisplayMetrics displayMetrics = new DisplayMetrics();
                            if (Build.VERSION.SDK_INT >= 17) {
                                windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
                            } else {
                                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                            }
                            b = displayMetrics.widthPixels;
                            c = displayMetrics.heightPixels;
                        } else {
                            return;
                        }
                    }
                } finally {
                    AppMethodBeat.o(i);
                }
            }
        }
        AppMethodBeat.o(23529);
    }

    @Deprecated
    public static int a(Context context) {
        AppMethodBeat.i(23530, false);
        int a2 = a();
        AppMethodBeat.o(23530);
        return a2;
    }

    public static int a() {
        AppMethodBeat.i(23531, false);
        if (b == 0) {
            synchronized (aw.class) {
                try {
                    if (b == 0) {
                        d();
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(23531);
                    throw th;
                }
            }
        }
        int i = b;
        AppMethodBeat.o(23531);
        return i;
    }

    @Deprecated
    public static int b(Context context) {
        AppMethodBeat.i(23532, false);
        int b2 = b();
        AppMethodBeat.o(23532);
        return b2;
    }

    public static int b() {
        AppMethodBeat.i(23533, false);
        if (c == 0) {
            synchronized (aw.class) {
                try {
                    if (c == 0) {
                        d();
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(23533);
                    throw th;
                }
            }
        }
        int i = c;
        AppMethodBeat.o(23533);
        return i;
    }

    public static int a(int i) {
        AppMethodBeat.i(23535, false);
        int i2 = (int) ((((float) i) / c().getDisplayMetrics().density) + 0.5f);
        AppMethodBeat.o(23535);
        return i2;
    }

    public static int b(int i) {
        AppMethodBeat.i(23536, false);
        int i2 = (int) ((((float) i) * c().getDisplayMetrics().density) + 0.5f);
        AppMethodBeat.o(23536);
        return i2;
    }

    public static int a(float f) {
        AppMethodBeat.i(23537, false);
        int i = (int) ((f * c().getDisplayMetrics().density) + 0.5f);
        AppMethodBeat.o(23537);
        return i;
    }

    public static int a(Context context, int i) {
        AppMethodBeat.i(23538, false);
        int i2 = (int) ((((float) i) * context.getResources().getDisplayMetrics().density) + 0.5f);
        AppMethodBeat.o(23538);
        return i2;
    }

    public static int b(Context context, int i) {
        AppMethodBeat.i(23539, false);
        int a2 = (int) ((((float) a(context)) / 750.0f) * ((float) i));
        AppMethodBeat.o(23539);
        return a2;
    }

    public static int c(Context context, int i) {
        AppMethodBeat.i(23540, false);
        int b2 = (int) ((((float) b(context)) / 1334.0f) * ((float) i));
        AppMethodBeat.o(23540);
        return b2;
    }

    public static int b(float f) {
        AppMethodBeat.i(23541, false);
        int i = (int) ((f / c().getDisplayMetrics().scaledDensity) + 0.5f);
        AppMethodBeat.o(23541);
        return i;
    }

    public static int c(float f) {
        AppMethodBeat.i(23542, false);
        int i = (int) ((f * c().getDisplayMetrics().scaledDensity) + 0.5f);
        AppMethodBeat.o(23542);
        return i;
    }

    public static Resources c() {
        AppMethodBeat.i(23544, false);
        Resources resources = ((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getApplication().getResources();
        AppMethodBeat.o(23544);
        return resources;
    }

    public static boolean a(View view, int i, int i2) {
        AppMethodBeat.i(23546, false);
        if (view != null) {
            if (i2 <= 0 || i <= 0) {
                view.setVisibility(8);
            } else {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                int a2 = (a(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getApplication()) - view.getPaddingLeft()) - view.getPaddingRight();
                int i3 = (int) (((((float) a2) * 1.0f) * ((float) i2)) / ((float) i));
                if (layoutParams == null) {
                    layoutParams = new ViewGroup.LayoutParams(a2, i3);
                } else {
                    layoutParams.width = a2;
                    layoutParams.height = i3;
                }
                view.setLayoutParams(layoutParams);
                view.setVisibility(0);
                AppMethodBeat.o(23546);
                return true;
            }
        }
        AppMethodBeat.o(23546);
        return false;
    }

    public static <T> T a(DialogFragment dialogFragment, Class<T> cls) {
        AppMethodBeat.i(23550, false);
        if (dialogFragment == null || cls == null) {
            AppMethodBeat.o(23550);
            return null;
        }
        T t = (T) dialogFragment.getTargetFragment();
        if (t == null || !cls.isAssignableFrom(t.getClass())) {
            T t2 = (T) dialogFragment.getActivity();
            if (t2 == null || !cls.isAssignableFrom(t2.getClass())) {
                AppMethodBeat.o(23550);
                return null;
            }
            AppMethodBeat.o(23550);
            return t2;
        }
        AppMethodBeat.o(23550);
        return t;
    }

    public static float a(TextView textView, int i, String str, int i2, int i3) {
        AppMethodBeat.i(23551, false);
        Paint paint = new Paint();
        paint.set(textView.getPaint());
        float c2 = (float) c((float) i2);
        float c3 = (float) c((float) i3);
        if (i > 0) {
            int paddingLeft = (i - textView.getPaddingLeft()) - textView.getPaddingRight();
            paint.setTextSize(c2);
            while (true) {
                if (c2 <= c3 || paint.measureText(str) <= ((float) paddingLeft)) {
                    break;
                }
                c2 -= 1.0f;
                if (c2 <= c3) {
                    c2 = c3;
                    break;
                }
                paint.setTextSize(c2);
            }
        }
        float f = c2 - 1.0f;
        AppMethodBeat.o(23551);
        return f;
    }

    public static int a(TextView textView, String str, int i, int i2) {
        AppMethodBeat.i(23552, false);
        if (textView == null) {
            AppMethodBeat.o(23552);
            return 0;
        }
        int width = textView.getWidth();
        if (Build.VERSION.SDK_INT >= 16 && textView.getMaxWidth() != -1) {
            width = textView.getMaxWidth();
        }
        Paint paint = new Paint();
        paint.set(textView.getPaint());
        float c2 = (float) c((float) i);
        float c3 = (float) c((float) i2);
        if (width > 0) {
            int paddingLeft = (width - textView.getPaddingLeft()) - textView.getPaddingRight();
            paint.setTextSize(c2);
            while (true) {
                if (c2 <= c3 || paint.measureText(str) <= ((float) paddingLeft)) {
                    break;
                }
                c2 -= 1.0f;
                if (c2 <= c3) {
                    c2 = c3;
                    break;
                }
                paint.setTextSize(c2);
            }
        }
        int b2 = b(c2) - 2;
        AppMethodBeat.o(23552);
        return b2;
    }

    public static void b(TextView textView, String str, int i, int i2) {
        AppMethodBeat.i(23553, false);
        if (textView == null) {
            AppMethodBeat.o(23553);
            return;
        }
        textView.setTextSize(2, (float) a(textView, str, i, i2));
        AppMethodBeat.o(23553);
    }

    public static Bitmap a(Context context, Bitmap bitmap, Bitmap bitmap2) {
        AppMethodBeat.i(23554, false);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_warp_screen_shot, (ViewGroup) null);
        ((ImageView) inflate.findViewById(R.id.iv_screen_shot)).setImageBitmap(bitmap);
        ((ImageView) inflate.findViewById(R.id.iv_sub)).setImageBitmap(bitmap2);
        inflate.measure(View.MeasureSpec.makeMeasureSpec(b((int) MetricsProto.MetricsEvent.ACTION_SETTINGS_CONDITION_CLICK), 1073741824), View.MeasureSpec.makeMeasureSpec(b((int) MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_ACCESS_FINE_LOCATION), 1073741824));
        inflate.layout(0, 0, b((int) MetricsProto.MetricsEvent.ACTION_SETTINGS_CONDITION_CLICK), b((int) MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_ACCESS_FINE_LOCATION));
        Bitmap createBitmap = Bitmap.createBitmap(b((int) MetricsProto.MetricsEvent.ACTION_SETTINGS_CONDITION_CLICK), b((int) MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_ACCESS_FINE_LOCATION), Bitmap.Config.RGB_565);
        inflate.draw(new Canvas(createBitmap));
        AppMethodBeat.o(23554);
        return createBitmap;
    }

    public static void a(Context context, View view, int i, int i2) {
        AppMethodBeat.i(23555, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = b(context, i);
        layoutParams.height = c(context, i2);
        view.setLayoutParams(layoutParams);
        AppMethodBeat.o(23555);
    }

    public static void a(TextView textView, String str) {
        AppMethodBeat.i(23556, false);
        if (!b.a(str)) {
            textView.setText(str);
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        AppMethodBeat.o(23556);
    }

    public static void a(TextView textView, PricePro.Price price) {
        boolean z = false;
        AppMethodBeat.i(23561, false);
        if (price != null) {
            textView.setText(price.name);
            textView.setTextColor(q.a(price.getColor()));
            if (price.show_type == 2) {
                z = true;
            }
            r.a(textView, z);
        }
        AppMethodBeat.o(23561);
    }

    public static void a(TextView textView, String str, int i, String[] strArr, boolean z, a aVar, int i2) {
        String[] strArr2;
        CharSequence[] charSequenceArr = strArr;
        AppMethodBeat.i(23562, false);
        if (textView == null || TextUtils.isEmpty(str) || charSequenceArr.length != 2) {
            AppMethodBeat.o(23562);
            return;
        }
        try {
            textView.setText("");
            int i3 = 1;
            AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(i2, true);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            int i4 = 0;
            int i5 = 0;
            boolean z2 = true;
            CharSequence[] charSequenceArr2 = charSequenceArr;
            while (z2) {
                int indexOf = str.indexOf(charSequenceArr2[0], i4);
                int indexOf2 = str.indexOf(charSequenceArr2[i3], (charSequenceArr2[0].length() + indexOf) - i3);
                if (indexOf2 <= indexOf || indexOf <= -1) {
                    spannableStringBuilder.append((CharSequence) str.substring(i5));
                    strArr2 = strArr;
                    z2 = false;
                    i4 = indexOf2;
                } else {
                    int length = indexOf2 + charSequenceArr2[0].length();
                    spannableStringBuilder.append((CharSequence) str.substring(i5, indexOf));
                    String substring = str.substring(indexOf, length);
                    if (z) {
                        substring = substring.replace(charSequenceArr2[0], "").replace(charSequenceArr2[i3], "");
                    }
                    int length2 = substring.length();
                    SpannableString spannableString = new SpannableString(substring);
                    spannableString.setSpan(new ForegroundColorSpan(i), 0, length2, 33);
                    if (i2 != 0) {
                        spannableString.setSpan(absoluteSizeSpan, 0, length2, 33);
                    }
                    spannableStringBuilder.append((CharSequence) spannableString);
                    if (aVar != null) {
                        aVar.a(substring);
                    }
                    strArr2 = strArr;
                    i4 = length;
                    i5 = i4;
                }
                i3 = 1;
                charSequenceArr2 = strArr2;
            }
            textView.setText(spannableStringBuilder);
        } catch (Exception unused) {
            textView.setText(str);
        }
        AppMethodBeat.o(23562);
    }

    public static void a(TextView textView, String str, int i, String[] strArr, boolean z) {
        AppMethodBeat.i(23563, false);
        a(textView, str, i, strArr, z, (a) null, 0);
        AppMethodBeat.o(23563);
    }

    public static void a(SpannableStringBuilder spannableStringBuilder, String str, int i, String[] strArr, boolean z) {
        AppMethodBeat.i(23565, false);
        if (b.a(str) || strArr.length != 2 || spannableStringBuilder == null) {
            AppMethodBeat.o(23565);
            return;
        }
        int i2 = 0;
        boolean z2 = true;
        while (z2) {
            try {
                int indexOf = str.indexOf(strArr[0], i2);
                int indexOf2 = str.indexOf(strArr[1], indexOf);
                if (indexOf2 <= indexOf || indexOf <= -1) {
                    z2 = false;
                } else {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(i), indexOf, indexOf2 + 1, 0);
                }
                if (z) {
                    spannableStringBuilder.replace(indexOf2, indexOf2 + 1, "");
                    spannableStringBuilder.replace(indexOf, indexOf + 1, "");
                    i2 = indexOf2 - 2;
                } else {
                    i2 = indexOf2;
                }
            } catch (Exception unused) {
            }
        }
        AppMethodBeat.o(23565);
    }

    public static void a(Context context, SpannableStringBuilder spannableStringBuilder, String str, int i, String str2, String str3, boolean z) {
        AppMethodBeat.i(23566, false);
        if (b.a(str) || spannableStringBuilder == null) {
            AppMethodBeat.o(23566);
            return;
        }
        boolean z2 = true;
        int i2 = 0;
        while (true) {
            if (!z2) {
                break;
            }
            try {
                int indexOf = str.indexOf(str2, i2);
                int indexOf2 = str.indexOf(str3, indexOf);
                if (indexOf == -1) {
                    break;
                }
                if (indexOf2 <= indexOf || indexOf <= -1) {
                    z2 = false;
                } else {
                    int i3 = indexOf + 1;
                    int i4 = indexOf2 + 1;
                    spannableStringBuilder.setSpan(new AnonymousClass1(str.substring(i3, indexOf2), context, i), indexOf, i4, 18);
                    if (z) {
                        spannableStringBuilder.replace(indexOf2, i4, "");
                        spannableStringBuilder.replace(indexOf, i3, "");
                        indexOf2 -= 2;
                    }
                }
                i2 = indexOf2;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(23566);
    }

    /* compiled from: UIUtil */
    /* renamed from: cn.missfresh.module.base.utils.aw$1  reason: invalid class name */
    static class AnonymousClass1 extends cn.missfresh.module.base.support.a {
        final /* synthetic */ Context a;
        final /* synthetic */ int b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Context context, int i) {
            super(str);
            this.a = context;
            this.b = i;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            AppMethodBeat.i(23527, false);
            if (p.a(a())) {
                cn.missfresh.ui.a.a.a(this.a.getString(R.string.copy_succ));
            }
            AppMethodBeat.o(23527);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            AppMethodBeat.i(23528, false);
            super.updateDrawState(textPaint);
            textPaint.setColor(this.b);
            textPaint.setUnderlineText(false);
            AppMethodBeat.o(23528);
        }
    }

    public static int b(TextView textView, String str) {
        AppMethodBeat.i(23570, false);
        if (textView == null || str == null) {
            AppMethodBeat.o(23570);
            return 0;
        }
        Rect rect = new Rect();
        textView.getPaint().getTextBounds(str, 0, str.length(), rect);
        int width = rect.width();
        AppMethodBeat.o(23570);
        return width;
    }

    public static <T> T b(DialogFragment dialogFragment, Class<T> cls) {
        AppMethodBeat.i(23571, false);
        if (dialogFragment == null || cls == null) {
            AppMethodBeat.o(23571);
            return null;
        }
        T t = (T) dialogFragment.getTargetFragment();
        if (t == null || !cls.isAssignableFrom(t.getClass())) {
            T t2 = (T) dialogFragment.getActivity();
            if (t2 == null || !cls.isAssignableFrom(t2.getClass())) {
                AppMethodBeat.o(23571);
                return null;
            }
            AppMethodBeat.o(23571);
            return t2;
        }
        AppMethodBeat.o(23571);
        return t;
    }

    public static int a(float f, int i, int i2) {
        AppMethodBeat.i(23572, false);
        int red = Color.red(i);
        int blue = Color.blue(i);
        int green = Color.green(i);
        int alpha = Color.alpha(i);
        int red2 = Color.red(i2);
        int blue2 = Color.blue(i2);
        int green2 = (int) (((float) green) + (((float) (Color.green(i2) - green)) * f));
        int alpha2 = (int) (((float) alpha) + (f * ((float) (Color.alpha(i2) - alpha))));
        int argb = Color.argb(alpha2, (int) (((float) red) + (((float) (red2 - red)) * f)), green2, (int) (((float) blue) + (((float) (blue2 - blue)) * f)));
        AppMethodBeat.o(23572);
        return argb;
    }
}
