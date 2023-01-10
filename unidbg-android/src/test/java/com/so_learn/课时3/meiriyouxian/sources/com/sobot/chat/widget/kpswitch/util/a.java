package com.sobot.chat.widget.kpswitch.util;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.sobot.chat.b;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.r;

/* compiled from: KPSwitchConflictUtil */
public class a {
    private static boolean a;

    /* compiled from: KPSwitchConflictUtil */
    /* renamed from: com.sobot.chat.widget.kpswitch.util.a$a  reason: collision with other inner class name */
    public interface AbstractC0146a {
        void a(View view, boolean z);
    }

    public static boolean a(boolean z, boolean z2, boolean z3) {
        return z || (z2 && !z3);
    }

    public static void a(View view, View view2, View view3) {
        a(view, view2, view3, null);
    }

    public static void a(View view, View view2, View view3, AbstractC0146a aVar) {
        Activity activity = (Activity) view.getContext();
        if (view2 != null) {
            view2.setOnClickListener(new KPSwitchConflictUtil$1(view, view3, aVar));
        }
        if (a(activity)) {
            view3.setOnTouchListener(new AnonymousClass1(view));
        }
    }

    /* compiled from: KPSwitchConflictUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.kpswitch.util.a$1  reason: invalid class name */
    public static class AnonymousClass1 implements View.OnTouchListener {
        final /* synthetic */ View a;

        AnonymousClass1(View view) {
            this.a = view;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 1) {
                return false;
            }
            if (!b.a(1)) {
                this.a.setVisibility(4);
                return false;
            }
            this.a.setVisibility(8);
            return false;
        }
    }

    public static void a(View view) {
        Activity activity = (Activity) view.getContext();
        view.setVisibility(0);
        if (b.a(1)) {
            m.c(view.getMeasuredHeight() + "");
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.height = (int) (((double) r.b(activity)) * 0.37d);
            view.setLayoutParams(layoutParams);
            if (activity.getCurrentFocus() != null) {
                c.b(activity.getCurrentFocus());
            }
        } else if (activity.getCurrentFocus() != null) {
            c.b(activity.getCurrentFocus());
        }
    }

    public static void a(View view, View view2) {
        c.a(view2);
        if (a((Activity) view.getContext())) {
            if (!b.a(1)) {
                view.setVisibility(4);
            } else {
                view.setVisibility(8);
            }
        } else if (a) {
            view.setVisibility(8);
        }
    }

    public static boolean b(View view, View view2) {
        boolean z = view.getVisibility() != 0;
        if (!z) {
            a(view, view2);
        } else {
            a(view);
        }
        return z;
    }

    public static void b(View view) {
        Activity activity = (Activity) view.getContext();
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            c.b(activity.getCurrentFocus());
            currentFocus.clearFocus();
        }
        view.setVisibility(8);
    }

    static boolean a(Activity activity) {
        return a(e.a(activity), e.b(activity), e.c(activity));
    }
}
