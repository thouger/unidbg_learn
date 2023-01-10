package cn.missfresh.module.base.manager;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.reflect.Method;

/* compiled from: SystemBarTintManager */
public class o {
    private static String a;
    private final a b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private View g;
    private View h;

    static {
        AppMethodBeat.i(14608, false);
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class);
                declaredMethod.setAccessible(true);
                a = (String) declaredMethod.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable unused) {
                a = null;
            }
        }
        AppMethodBeat.o(14608);
    }

    public o(Activity activity) {
        AppMethodBeat.i(14574, false);
        Window window = activity.getWindow();
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        if (Build.VERSION.SDK_INT >= 19) {
            TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(new int[]{16843759, 16843760});
            try {
                this.c = obtainStyledAttributes.getBoolean(0, false);
                this.d = obtainStyledAttributes.getBoolean(1, false);
                obtainStyledAttributes.recycle();
                WindowManager.LayoutParams attributes = window.getAttributes();
                if ((67108864 & attributes.flags) != 0) {
                    this.c = true;
                }
                if ((attributes.flags & 134217728) != 0) {
                    this.d = true;
                }
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                AppMethodBeat.o(14574);
                throw th;
            }
        }
        this.b = new a(activity, this.c, this.d);
        if (!this.b.c()) {
            this.d = false;
        }
        if (this.c) {
            a(activity, viewGroup);
        }
        if (this.d) {
            b(activity, viewGroup);
        }
        AppMethodBeat.o(14574);
    }

    public void a(boolean z) {
        int i = 0;
        AppMethodBeat.i(14576, false);
        this.e = z;
        if (this.c) {
            View view = this.g;
            if (!z) {
                i = 8;
            }
            view.setVisibility(i);
        }
        AppMethodBeat.o(14576);
    }

    public void b(boolean z) {
        int i = 0;
        AppMethodBeat.i(14577, false);
        this.f = z;
        if (this.d) {
            View view = this.h;
            if (!z) {
                i = 8;
            }
            view.setVisibility(i);
        }
        AppMethodBeat.o(14577);
    }

    public void a(int i) {
        AppMethodBeat.i(14588, false);
        if (this.c) {
            this.g.setBackgroundResource(i);
        }
        AppMethodBeat.o(14588);
    }

    private void a(Context context, ViewGroup viewGroup) {
        AppMethodBeat.i(14604, false);
        this.g = new View(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.b.b());
        layoutParams.gravity = 48;
        if (this.d && !this.b.a()) {
            layoutParams.rightMargin = this.b.e();
        }
        this.g.setLayoutParams(layoutParams);
        this.g.setBackgroundColor(-1728053248);
        this.g.setVisibility(8);
        viewGroup.addView(this.g);
        AppMethodBeat.o(14604);
    }

    private void b(Context context, ViewGroup viewGroup) {
        FrameLayout.LayoutParams layoutParams;
        AppMethodBeat.i(14606, false);
        this.h = new View(context);
        if (this.b.a()) {
            layoutParams = new FrameLayout.LayoutParams(-1, this.b.d());
            layoutParams.gravity = 80;
        } else {
            layoutParams = new FrameLayout.LayoutParams(this.b.e(), -1);
            layoutParams.gravity = 5;
        }
        this.h.setLayoutParams(layoutParams);
        this.h.setBackgroundColor(-1728053248);
        this.h.setVisibility(8);
        viewGroup.addView(this.h);
        AppMethodBeat.o(14606);
    }

    /* compiled from: SystemBarTintManager */
    public static class a {
        private final boolean a;
        private final boolean b;
        private final int c;
        private final int d;
        private final boolean e;
        private final int f;
        private final int g;
        private final boolean h;
        private final float i;

        private a(Activity activity, boolean z, boolean z2) {
            boolean z3 = false;
            AppMethodBeat.i(14559, false);
            Resources resources = activity.getResources();
            this.h = resources.getConfiguration().orientation == 1;
            this.i = a(activity);
            this.c = a(resources, "status_bar_height");
            this.d = a((Context) activity);
            this.f = b(activity);
            this.g = c(activity);
            this.e = this.f > 0 ? true : z3;
            this.a = z;
            this.b = z2;
            AppMethodBeat.o(14559);
        }

        private int a(Context context) {
            int i = 0;
            AppMethodBeat.i(14561, false);
            if (Build.VERSION.SDK_INT >= 14) {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(16843499, typedValue, true);
                i = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
            }
            AppMethodBeat.o(14561);
            return i;
        }

        private int b(Context context) {
            AppMethodBeat.i(14562, false);
            Resources resources = context.getResources();
            if (Build.VERSION.SDK_INT < 14 || !d(context)) {
                AppMethodBeat.o(14562);
                return 0;
            }
            int a = a(resources, this.h ? "navigation_bar_height" : "navigation_bar_height_landscape");
            AppMethodBeat.o(14562);
            return a;
        }

        private int c(Context context) {
            AppMethodBeat.i(14563, false);
            Resources resources = context.getResources();
            if (Build.VERSION.SDK_INT < 14 || !d(context)) {
                AppMethodBeat.o(14563);
                return 0;
            }
            int a = a(resources, "navigation_bar_width");
            AppMethodBeat.o(14563);
            return a;
        }

        private boolean d(Context context) {
            AppMethodBeat.i(14564, false);
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            if (identifier != 0) {
                boolean z = resources.getBoolean(identifier);
                if ("1".equals(o.a)) {
                    z = false;
                } else if ("0".equals(o.a)) {
                    z = true;
                }
                AppMethodBeat.o(14564);
                return z;
            }
            boolean z2 = !ViewConfiguration.get(context).hasPermanentMenuKey();
            AppMethodBeat.o(14564);
            return z2;
        }

        private int a(Resources resources, String str) {
            int i = 0;
            AppMethodBeat.i(14565, false);
            int identifier = resources.getIdentifier(str, "dimen", "android");
            if (identifier > 0) {
                i = resources.getDimensionPixelSize(identifier);
            }
            AppMethodBeat.o(14565);
            return i;
        }

        private float a(Activity activity) {
            AppMethodBeat.i(14567, false);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= 16) {
                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
            } else {
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            }
            float min = Math.min(((float) displayMetrics.widthPixels) / displayMetrics.density, ((float) displayMetrics.heightPixels) / displayMetrics.density);
            AppMethodBeat.o(14567);
            return min;
        }

        public boolean a() {
            return this.i >= 600.0f || this.h;
        }

        public int b() {
            return this.c;
        }

        public boolean c() {
            return this.e;
        }

        public int d() {
            return this.f;
        }

        public int e() {
            return this.g;
        }
    }
}
