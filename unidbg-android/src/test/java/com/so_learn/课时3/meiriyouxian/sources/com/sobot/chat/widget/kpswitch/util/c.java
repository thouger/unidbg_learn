package com.sobot.chat.widget.kpswitch.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import com.sobot.chat.utils.q;

/* compiled from: KeyboardUtil */
public class c {
    private static int a;
    private static int b;
    private static int c;
    private static int d;

    /* compiled from: KeyboardUtil */
    public interface b {
        void a(boolean z);
    }

    public static void a(View view) {
        view.requestFocus();
        ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
    }

    public static void b(View view) {
        InputMethodManager inputMethodManager;
        if (view != null && (inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)) != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /* access modifiers changed from: private */
    public static boolean b(Context context, int i) {
        if (a == i || i < 0) {
            return false;
        }
        a = i;
        Log.d("KeyBordUtil", String.format("save keyboard: %d", Integer.valueOf(i)));
        return b.a(context, i);
    }

    public static int a(Context context) {
        if (a == 0) {
            a = b.b(context, d(context));
        }
        return a;
    }

    public static int b(Context context) {
        return Math.min(c(context), Math.max(d(context), a(context)));
    }

    public static int c(Context context) {
        Resources resources = context.getResources();
        if (b == 0) {
            b = resources.getDimensionPixelSize(q.a(context, "dimen", "sobot_max_panel_height"));
        }
        return b;
    }

    public static int d(Context context) {
        Resources resources = context.getResources();
        if (c == 0) {
            c = resources.getDimensionPixelSize(q.a(context, "dimen", "sobot_min_panel_height"));
        }
        return c;
    }

    public static int e(Context context) {
        if (d == 0) {
            d = context.getResources().getDimensionPixelSize(q.a(context, "dimen", "sobot_min_keyboard_height"));
        }
        return d;
    }

    public static ViewTreeObserver.OnGlobalLayoutListener a(Activity activity, com.sobot.chat.widget.kpswitch.b bVar, b bVar2) {
        int i;
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        boolean a2 = e.a(activity);
        boolean b2 = e.b(activity);
        boolean c2 = e.c(activity);
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 13) {
            Point point = new Point();
            defaultDisplay.getSize(point);
            i = point.y;
        } else {
            i = defaultDisplay.getHeight();
        }
        a aVar = new a(a2, b2, c2, viewGroup, bVar, bVar2, i);
        viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(aVar);
        return aVar;
    }

    public static void a(Activity activity, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        if (Build.VERSION.SDK_INT >= 16) {
            viewGroup.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        } else {
            viewGroup.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        }
    }

    /* compiled from: KeyboardUtil */
    private static class a implements ViewTreeObserver.OnGlobalLayoutListener {
        private int a = 0;
        private final ViewGroup b;
        private final com.sobot.chat.widget.kpswitch.b c;
        private final boolean d;
        private final boolean e;
        private final boolean f;
        private final int g;
        private boolean h;
        private final b i;
        private final int j;
        private boolean k = false;
        private int l;

        a(boolean z, boolean z2, boolean z3, ViewGroup viewGroup, com.sobot.chat.widget.kpswitch.b bVar, b bVar2, int i) {
            this.b = viewGroup;
            this.c = bVar;
            this.d = z;
            this.e = z2;
            this.f = z3;
            this.g = d.a(viewGroup.getContext());
            this.i = bVar2;
            this.j = i;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            int i;
            boolean z = false;
            View childAt = this.b.getChildAt(0);
            View view = (View) this.b.getParent();
            Rect rect = new Rect();
            if (this.e) {
                view.getWindowVisibleDisplayFrame(rect);
                i = rect.bottom - rect.top;
                if (!this.k) {
                    if (i == this.j) {
                        z = true;
                    }
                    this.k = z;
                }
                if (!this.k) {
                    i += this.g;
                }
            } else if (childAt != null) {
                childAt.getWindowVisibleDisplayFrame(rect);
                i = rect.bottom - rect.top;
            } else {
                i = -1;
            }
            if (i != -1) {
                a(i);
                b(i);
                this.a = i;
            }
        }

        private void a(int i) {
            int i2;
            int b;
            if (this.a == 0) {
                this.a = i;
                this.c.a(c.b(a()));
                return;
            }
            if (a.a(this.d, this.e, this.f)) {
                i2 = ((View) this.b.getParent()).getHeight() - i;
                Log.d("KeyboardStatusListener", String.format("action bar over layout %d display height: %d", Integer.valueOf(((View) this.b.getParent()).getHeight()), Integer.valueOf(i)));
            } else {
                i2 = Math.abs(i - this.a);
            }
            if (i2 > c.e(a())) {
                Log.d("KeyboardStatusListener", String.format("pre display height: %d display height: %d keyboard: %d ", Integer.valueOf(this.a), Integer.valueOf(i), Integer.valueOf(i2)));
                if (i2 == this.g) {
                    Log.w("KeyboardStatusListener", String.format("On global layout change get keyboard height just equal statusBar height %d", Integer.valueOf(i2)));
                } else if (c.b(a(), i2) && this.c.getHeight() != (b = c.b(a()))) {
                    this.c.a(b);
                }
            }
        }

        private void b(int i) {
            boolean z;
            View view = (View) this.b.getParent();
            int height = view.getHeight() - view.getPaddingTop();
            if (!a.a(this.d, this.e, this.f)) {
                if (this.l == 0 || com.sobot.chat.b.a(1)) {
                    z = this.h;
                } else {
                    z = i < this.l - c.e(a());
                }
                this.l = Math.max(this.l, height);
            } else if (this.e || height - i != this.g) {
                z = height > i;
            } else {
                z = this.h;
            }
            if (this.h != z) {
                Log.d("KeyboardStatusListener", String.format("displayHeight %d actionBarOverlayLayoutHeight %d keyboard status change: %B", Integer.valueOf(i), Integer.valueOf(height), Boolean.valueOf(z)));
                this.c.a(z);
                b bVar = this.i;
                if (bVar != null) {
                    bVar.a(z);
                }
            }
            this.h = z;
        }

        private Context a() {
            return this.b.getContext();
        }
    }
}
