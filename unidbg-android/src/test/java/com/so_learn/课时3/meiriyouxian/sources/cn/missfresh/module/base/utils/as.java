package cn.missfresh.module.base.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: StatusBarUtil */
public class as {
    private static final int a = R.id.statusbarutil_fake_status_bar_view;
    private static final int b = R.id.statusbarutil_translucent_view;

    private static int a(int i, int i2) {
        if (i2 == 0) {
            return i;
        }
        float f = 1.0f - (((float) i2) / 255.0f);
        return ((int) (((double) (((float) (i & 255)) * f)) + 0.5d)) | (((int) (((double) (((float) ((i >> 16) & 255)) * f)) + 0.5d)) << 16) | -16777216 | (((int) (((double) (((float) ((i >> 8) & 255)) * f)) + 0.5d)) << 8);
    }

    public static void a(Activity activity, int i) {
        AppMethodBeat.i(23446, false);
        a(activity, i, 40);
        AppMethodBeat.o(23446);
    }

    public static void a(Activity activity, int i, int i2) {
        AppMethodBeat.i(23447, false);
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().addFlags(Integer.MIN_VALUE);
            activity.getWindow().clearFlags(67108864);
            activity.getWindow().setStatusBarColor(a(i, i2));
        } else if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().addFlags(67108864);
            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
            View findViewById = viewGroup.findViewById(a);
            if (findViewById != null) {
                if (findViewById.getVisibility() == 8) {
                    findViewById.setVisibility(0);
                }
                findViewById.setBackgroundColor(a(i, i2));
            } else {
                viewGroup.addView(b(activity, i, i2));
            }
            d(activity);
        }
        AppMethodBeat.o(23447);
    }

    public static void a(Activity activity) {
        AppMethodBeat.i(23452, false);
        b(activity, 40);
        AppMethodBeat.o(23452);
    }

    public static void b(Activity activity, int i) {
        AppMethodBeat.i(23453, false);
        if (Build.VERSION.SDK_INT < 19) {
            AppMethodBeat.o(23453);
            return;
        }
        b(activity);
        c(activity, i);
        AppMethodBeat.o(23453);
    }

    public static void b(Activity activity) {
        AppMethodBeat.i(23455, false);
        if (Build.VERSION.SDK_INT < 19) {
            AppMethodBeat.o(23455);
            return;
        }
        f(activity);
        d(activity);
        AppMethodBeat.o(23455);
    }

    public static void a(Activity activity, int i, View view) {
        AppMethodBeat.i(23468, false);
        if (Build.VERSION.SDK_INT < 19) {
            AppMethodBeat.o(23468);
            return;
        }
        e(activity);
        c(activity, i);
        if (view != null) {
            Object tag = view.getTag(-123);
            if (tag == null || !((Boolean) tag).booleanValue()) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin + a((Context) activity), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                view.setTag(-123, true);
            } else {
                AppMethodBeat.o(23468);
                return;
            }
        }
        AppMethodBeat.o(23468);
    }

    public static void a(Activity activity, View view) {
        AppMethodBeat.i(23469, false);
        b(activity, 40, view);
        AppMethodBeat.o(23469);
    }

    public static void b(Activity activity, int i, View view) {
        AppMethodBeat.i(23471, false);
        a(activity, i, view);
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            c(activity);
        }
        AppMethodBeat.o(23471);
    }

    private static void c(Activity activity) {
        AppMethodBeat.i(23477, false);
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        View findViewById = viewGroup.findViewById(a);
        if (findViewById != null) {
            viewGroup.removeView(findViewById);
            ((ViewGroup) ((ViewGroup) activity.findViewById(16908290)).getChildAt(0)).setPadding(0, 0, 0, 0);
        }
        AppMethodBeat.o(23477);
    }

    private static void c(Activity activity, int i) {
        AppMethodBeat.i(23478, false);
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        View findViewById = viewGroup.findViewById(b);
        if (findViewById != null) {
            if (findViewById.getVisibility() == 8) {
                findViewById.setVisibility(0);
            }
            findViewById.setBackgroundColor(Color.argb(i, 0, 0, 0));
        } else {
            viewGroup.addView(d(activity, i));
        }
        AppMethodBeat.o(23478);
    }

    private static View b(Activity activity, int i, int i2) {
        AppMethodBeat.i(23480, false);
        View view = new View(activity);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, a((Context) activity)));
        view.setBackgroundColor(a(i, i2));
        view.setId(a);
        AppMethodBeat.o(23480);
        return view;
    }

    private static void d(Activity activity) {
        AppMethodBeat.i(23481, false);
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                childAt.setFitsSystemWindows(true);
                ((ViewGroup) childAt).setClipToPadding(true);
            }
        }
        AppMethodBeat.o(23481);
    }

    private static void e(Activity activity) {
        AppMethodBeat.i(23482, false);
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().setStatusBarColor(0);
            activity.getWindow().getDecorView().setSystemUiVisibility(1280);
        } else if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().setFlags(67108864, 67108864);
        }
        AppMethodBeat.o(23482);
    }

    private static void f(Activity activity) {
        AppMethodBeat.i(23483, false);
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().addFlags(Integer.MIN_VALUE);
            activity.getWindow().clearFlags(67108864);
            activity.getWindow().addFlags(134217728);
            activity.getWindow().setStatusBarColor(0);
        } else {
            activity.getWindow().addFlags(67108864);
        }
        AppMethodBeat.o(23483);
    }

    private static View d(Activity activity, int i) {
        AppMethodBeat.i(23484, false);
        View view = new View(activity);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, a((Context) activity)));
        view.setBackgroundColor(Color.argb(i, 0, 0, 0));
        view.setId(b);
        AppMethodBeat.o(23484);
        return view;
    }

    public static int a(Context context) {
        AppMethodBeat.i(23485, false);
        if (Build.VERSION.SDK_INT < 19) {
            AppMethodBeat.o(23485);
            return 0;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("status_bar_height", "dimen", "android"));
        AppMethodBeat.o(23485);
        return dimensionPixelSize;
    }
}
