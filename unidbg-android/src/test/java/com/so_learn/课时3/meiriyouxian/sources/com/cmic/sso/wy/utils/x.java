package com.cmic.sso.wy.utils;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cmic.sso.wy.widget.a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: UmcActivityUtil */
public class x {
    private static ArrayList<WeakReference<Activity>> a;

    public static SpannableString a(Context context, String str, String str2, a aVar, a aVar2, a aVar3) {
        SpannableString spannableString = new SpannableString(str);
        try {
            AnonymousClass1 r1 = new AnonymousClass1(context, aVar);
            AnonymousClass3 r8 = null;
            AnonymousClass2 r2 = aVar2 != null ? new AnonymousClass2(context, aVar2) : null;
            if (aVar3 != null) {
                r8 = new AnonymousClass3(context, aVar3);
            }
            com.cmic.sso.wy.a a2 = com.cmic.sso.wy.b.a.a(context).a();
            int indexOf = str.indexOf(str2);
            spannableString.setSpan(r1, indexOf, str2.length() + indexOf, 34);
            if (aVar2 != null && aVar3 != null) {
                String F = a2.F();
                int indexOf2 = str.indexOf(F);
                spannableString.setSpan(r2, indexOf2, F.length() + indexOf2, 34);
                String H = a2.H();
                int indexOf3 = str.indexOf(H, indexOf2 + F.length());
                spannableString.setSpan(r8, indexOf3, H.length() + indexOf3, 34);
            } else if (aVar2 != null) {
                String F2 = a2.F();
                int indexOf4 = str.indexOf(F2);
                spannableString.setSpan(r2, indexOf4, F2.length() + indexOf4, 34);
            } else if (aVar3 != null) {
                String H2 = a2.H();
                int indexOf5 = str.indexOf(H2);
                spannableString.setSpan(r8, indexOf5, H2.length() + indexOf5, 34);
            }
            return spannableString;
        } catch (Exception e) {
            e.printStackTrace();
            return spannableString;
        }
    }

    /* compiled from: UmcActivityUtil */
    /* renamed from: com.cmic.sso.wy.utils.x$1  reason: invalid class name */
    static class AnonymousClass1 extends ClickableSpan {
        final /* synthetic */ Context a;
        final /* synthetic */ a b;

        AnonymousClass1(Context context, a aVar) {
            this.a = context;
            this.b = aVar;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
            try {
                textPaint.setColor(com.cmic.sso.wy.b.a.a(this.a).a().L());
            } catch (Exception unused) {
                textPaint.setColor(-16007674);
            }
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            a aVar = this.b;
            if (aVar != null && !aVar.isShowing()) {
                this.b.show();
            }
        }
    }

    /* compiled from: UmcActivityUtil */
    /* renamed from: com.cmic.sso.wy.utils.x$2  reason: invalid class name */
    static class AnonymousClass2 extends ClickableSpan {
        final /* synthetic */ Context a;
        final /* synthetic */ a b;

        AnonymousClass2(Context context, a aVar) {
            this.a = context;
            this.b = aVar;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
            try {
                textPaint.setColor(com.cmic.sso.wy.b.a.a(this.a).a().L());
            } catch (Exception unused) {
                textPaint.setColor(-16007674);
            }
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            a aVar = this.b;
            if (aVar != null && !aVar.isShowing()) {
                this.b.show();
            }
        }
    }

    /* compiled from: UmcActivityUtil */
    /* renamed from: com.cmic.sso.wy.utils.x$3  reason: invalid class name */
    static class AnonymousClass3 extends ClickableSpan {
        final /* synthetic */ Context a;
        final /* synthetic */ a b;

        AnonymousClass3(Context context, a aVar) {
            this.a = context;
            this.b = aVar;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
            try {
                textPaint.setColor(com.cmic.sso.wy.b.a.a(this.a).a().L());
            } catch (Exception unused) {
                textPaint.setColor(-16007674);
            }
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            a aVar = this.b;
            if (aVar != null && !aVar.isShowing()) {
                this.b.show();
            }
        }
    }

    public static RelativeLayout a(Context context, View view, int i, int i2, String str, View.OnClickListener onClickListener) {
        int i3;
        com.cmic.sso.wy.a a2 = com.cmic.sso.wy.b.a.a(context).a();
        RelativeLayout relativeLayout = new RelativeLayout(context);
        if (view != null) {
            i3 = -2;
        } else {
            i3 = a(context, 49.0f);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, i3);
        layoutParams.addRule(10, -1);
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setId(i);
        TextView textView = new TextView(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13, -1);
        textView.setLayoutParams(layoutParams2);
        textView.setTextColor(a2.g());
        textView.setTextSize(2, (float) a2.f());
        textView.setText(str);
        if (view != null) {
            relativeLayout.addView(view);
            relativeLayout.addView(textView);
            return relativeLayout;
        }
        ImageButton imageButton = new ImageButton(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(a(context, (float) a2.h()), a(context, (float) a2.i()));
        layoutParams3.addRule(9, -1);
        layoutParams3.addRule(15, -1);
        layoutParams3.setMargins(a(context, 12.0f), 0, 0, 0);
        imageButton.setLayoutParams(layoutParams3);
        imageButton.setId(i2);
        imageButton.setOnClickListener(onClickListener);
        imageButton.setBackgroundColor(0);
        relativeLayout.addView(imageButton);
        relativeLayout.setBackgroundColor(-16742704);
        imageButton.setImageResource(o.a(context, "umcsdk_return_bg"));
        return relativeLayout;
    }

    public static int a(Context context, float f) {
        if (f < 0.0f) {
            return (int) f;
        }
        try {
            return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
        } catch (Exception unused) {
            return (int) f;
        }
    }

    public static int a(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.widthPixels;
    }

    public static int b(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.heightPixels;
    }
}
