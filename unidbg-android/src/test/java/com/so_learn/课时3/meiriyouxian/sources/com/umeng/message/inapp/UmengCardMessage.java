package com.umeng.message.inapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.tencent.connect.common.Constants;
import com.umeng.message.common.d;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.proguard.h;
import com.umeng.message.proguard.j;
import com.umeng.message.view.UCloseView;
import org.json.JSONObject;

public final class UmengCardMessage extends DialogFragment {
    private static final String a = UmengCardMessage.class.getName();
    private static final int r = 12;
    private Activity b;
    private UInAppMessage c;
    private String d;
    private Bitmap e;
    private ViewGroup f;
    private int g;
    private int h;
    private int i;
    private int j;
    private UInAppHandler k;
    private IUmengInAppMsgCloseCallback l;
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private String[] q = {"18", Constants.VIA_REPORT_TYPE_START_WAP, Constants.VIA_REPORT_TYPE_START_WAP};

    private int a(boolean z) {
        return z ? 1 : 0;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public final void onCreate(Bundle bundle) {
        String[] b;
        super.onCreate(bundle);
        setStyle(2, 16973830);
        setRetainInstance(true);
        try {
            this.b = getActivity();
            Bundle arguments = getArguments();
            this.c = new UInAppMessage(new JSONObject(arguments.getString("msg")));
            this.d = arguments.getString("label");
            byte[] byteArray = arguments.getByteArray("bitmapByte");
            if (byteArray != null) {
                this.e = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            }
            this.k = InAppMessageManager.getInstance(this.b).getInAppHandler();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ((this.c.msg_type == 5 || this.c.msg_type == 6) && (b = InAppMessageManager.getInstance(this.b).b()) != null) {
            this.q = b;
        }
    }

    @Override // android.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Rect rect;
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
            rect = new Rect();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            this.h = rect.height() - j.a(this.b, 65.0f);
            this.g = (int) (((double) this.h) * 1.2d);
            this.i = rect.width() - j.a(this.b, 70.0f);
            this.j = (this.i / 2) * 3;
        } else {
            rect = null;
        }
        if (this.c.msg_type == 2 || this.c.msg_type == 3) {
            return a();
        }
        if (this.c.msg_type == 4) {
            return a(layoutInflater, viewGroup, bundle);
        }
        if ((this.c.msg_type == 5 || this.c.msg_type == 6) && rect != null) {
            return a(rect);
        }
        return null;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        if (!this.m) {
            d.a(this.b).a(this.c.msg_id, this.c.msg_type, 1, 0, 0, 0, 0, 0, 0);
        }
        this.m = true;
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.requestWindowFeature(1);
        return onCreateDialog;
    }

    private View a() {
        RelativeLayout.LayoutParams layoutParams;
        RelativeLayout relativeLayout = new RelativeLayout(this.b);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(Color.parseColor("#33000000"));
        if (getResources().getConfiguration().orientation == 1) {
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        } else if (this.c.msg_type == 2) {
            layoutParams = new RelativeLayout.LayoutParams(this.g, this.h);
        } else {
            layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        }
        int a2 = j.a(this.b, 30.0f);
        int a3 = j.a(this.b, 15.0f);
        layoutParams.setMargins(a2, a3, a2, a3);
        layoutParams.addRule(13);
        this.f = new FrameLayout(this.b);
        this.f.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        int a4 = j.a(this.b, 12.0f);
        layoutParams2.setMargins(a4, a4, a4, a4);
        ImageView imageView = new ImageView(this.b);
        imageView.setLayoutParams(layoutParams2);
        imageView.setAdjustViewBounds(true);
        imageView.setId(h.c());
        imageView.setImageBitmap(this.e);
        this.f.addView(imageView);
        int a5 = j.a(this.b, 24.0f);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(a5, a5, 5);
        UCloseView uCloseView = new UCloseView(this.b);
        uCloseView.setLayoutParams(layoutParams3);
        this.f.addView(uCloseView);
        relativeLayout.addView(this.f);
        imageView.setOnClickListener(new AnonymousClass1());
        uCloseView.setOnClickListener(new AnonymousClass2());
        return relativeLayout;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.inapp.UmengCardMessage$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UmengCardMessage.this.n = true;
            if (!TextUtils.equals("none", UmengCardMessage.this.c.action_type)) {
                UmengCardMessage.this.k.handleInAppMessage(UmengCardMessage.this.b, UmengCardMessage.this.c, 16);
                UmengCardMessage.this.dismiss();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.inapp.UmengCardMessage$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UmengCardMessage.this.p = true;
            UmengCardMessage.this.dismiss();
        }
    }

    private View a(Rect rect) {
        RelativeLayout relativeLayout = new RelativeLayout(this.b);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(Color.parseColor("#33000000"));
        if (getResources().getConfiguration().orientation == 1) {
            this.i = rect.width() - j.a(this.b, 70.0f);
            if (this.c.msg_type == 5) {
                this.j = (this.i / 6) * 5;
            } else {
                this.j = (this.i / 2) * 3;
            }
        } else {
            this.j = rect.height() - j.a(this.b, 65.0f);
            this.i = (this.j / 5) * 6;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.i, this.j);
        layoutParams.addRule(13);
        LinearLayout linearLayout = new LinearLayout(this.b);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(1);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        int a2 = j.a(this.b, 20.0f);
        layoutParams2.setMargins(a2, a2, a2, a2);
        TextView textView = new TextView(this.b);
        textView.setLayoutParams(layoutParams2);
        textView.setGravity(17);
        textView.setText(this.c.title);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
        textView.setTextSize((float) Integer.parseInt(this.q[0]));
        textView.setTextColor(Color.parseColor("#000000"));
        linearLayout.addView(textView);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, 0);
        layoutParams3.setMargins(a2, 0, a2, 0);
        layoutParams3.weight = 1.0f;
        ScrollView scrollView = new ScrollView(this.b);
        scrollView.setLayoutParams(layoutParams3);
        scrollView.setScrollBarStyle(16777216);
        scrollView.setVerticalScrollBarEnabled(false);
        TextView textView2 = new TextView(this.b);
        textView2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        textView2.setText(this.c.content);
        textView2.setTextSize((float) Integer.parseInt(this.q[1]));
        textView2.setTextColor(Color.parseColor("#000000"));
        scrollView.addView(textView2);
        linearLayout.addView(scrollView);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setStroke(j.a(this.b, 1.0f), Color.parseColor("#D8D8D8"));
        gradientDrawable.setCornerRadius(20.0f);
        gradientDrawable.setColor(-1);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, j.a(this.b, 35.0f));
        layoutParams4.setMargins(a2, j.a(this.b, 30.0f), a2, a2);
        TextView textView3 = new TextView(this.b);
        textView3.setLayoutParams(layoutParams4);
        textView3.setGravity(17);
        textView3.setBackgroundColor(Color.parseColor("#FFFFFF"));
        textView3.setText(this.c.button_text);
        textView3.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
        textView3.setTextSize((float) Integer.parseInt(this.q[2]));
        textView3.setTextColor(Color.parseColor("#000000"));
        textView3.setBackgroundDrawable(gradientDrawable);
        linearLayout.addView(textView3);
        relativeLayout.addView(linearLayout);
        textView3.setOnClickListener(new AnonymousClass3());
        return relativeLayout;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.inapp.UmengCardMessage$3  reason: invalid class name */
    public class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UmengCardMessage.this.n = true;
            UmengCardMessage.this.k.handleInAppMessage(UmengCardMessage.this.b, UmengCardMessage.this.c, 18);
            UmengCardMessage.this.dismiss();
        }
    }

    private View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(d.a(this.b).e("umeng_custom_card_message"), viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(d.a(this.b).b("umeng_card_message_image"));
        imageView.setImageBitmap(this.e);
        imageView.setOnClickListener(new AnonymousClass4());
        ((Button) inflate.findViewById(d.a(this.b).b("umeng_card_message_ok"))).setOnClickListener(new AnonymousClass5());
        ((Button) inflate.findViewById(d.a(this.b).b("umeng_card_message_close"))).setOnClickListener(new AnonymousClass6());
        return inflate;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.inapp.UmengCardMessage$4  reason: invalid class name */
    public class AnonymousClass4 implements View.OnClickListener {
        AnonymousClass4() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UmengCardMessage.this.n = true;
            if (!TextUtils.equals("none", UmengCardMessage.this.c.action_type)) {
                UmengCardMessage.this.k.handleInAppMessage(UmengCardMessage.this.b, UmengCardMessage.this.c, 16);
                UmengCardMessage.this.dismiss();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.inapp.UmengCardMessage$5  reason: invalid class name */
    public class AnonymousClass5 implements View.OnClickListener {
        AnonymousClass5() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UmengCardMessage.this.o = true;
            if (!TextUtils.equals("none", UmengCardMessage.this.c.action_type)) {
                UmengCardMessage.this.k.handleInAppMessage(UmengCardMessage.this.b, UmengCardMessage.this.c, 19);
                UmengCardMessage.this.dismiss();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.inapp.UmengCardMessage$6  reason: invalid class name */
    public class AnonymousClass6 implements View.OnClickListener {
        AnonymousClass6() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UmengCardMessage.this.p = true;
            UmengCardMessage.this.dismiss();
        }
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        RelativeLayout.LayoutParams layoutParams;
        super.onConfigurationChanged(configuration);
        if (this.f != null) {
            if (configuration.orientation == 1) {
                layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            } else {
                layoutParams = new RelativeLayout.LayoutParams(-2, -1);
            }
            int a2 = j.a(this.b, 30.0f);
            int a3 = j.a(this.b, 15.0f);
            layoutParams.setMargins(a2, a3, a2, a3);
            layoutParams.addRule(13);
            this.f.setLayoutParams(layoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(IUmengInAppMsgCloseCallback iUmengInAppMsgCloseCallback) {
        this.l = iUmengInAppMsgCloseCallback;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        d.a(this.b).a(this.c.msg_id, this.c.msg_type, 0, a(this.n), 0, 0, a(this.p), 0, a(this.o));
        this.p = false;
        this.n = false;
        this.o = false;
        this.m = false;
        IUmengInAppMsgCloseCallback iUmengInAppMsgCloseCallback = this.l;
        if (iUmengInAppMsgCloseCallback != null) {
            iUmengInAppMsgCloseCallback.onClose();
        }
    }
}
