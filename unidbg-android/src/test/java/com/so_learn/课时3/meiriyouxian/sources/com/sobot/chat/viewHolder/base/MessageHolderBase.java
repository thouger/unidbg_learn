package com.sobot.chat.viewHolder.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sobot.chat.activity.SobotPhotoActivity;
import com.sobot.chat.adapter.e;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.c;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.widget.ReSendDialog;
import com.sobot.chat.widget.SobotImageView;

public abstract class MessageHolderBase {
    protected LinearLayout A;
    protected LinearLayout B;
    protected TextView C;
    protected TextView D;
    protected View E;
    private SobotImageView a;
    private TextView b;
    public ZhiChiMessageBase m;
    protected Context n;
    protected boolean o = false;
    protected e.a p;
    public TextView q;
    public TextView r;
    protected FrameLayout s;
    protected ImageView t;
    protected ProgressBar u;
    protected View v;
    protected RelativeLayout w;
    protected LinearLayout x;
    protected int y;
    protected RelativeLayout z;

    public interface a {
        void a();
    }

    public abstract void a(Context context, ZhiChiMessageBase zhiChiMessageBase);

    public MessageHolderBase(Context context, View view) {
        this.E = view;
        this.n = context;
        this.r = (TextView) view.findViewById(q.a(context, "id", "sobot_reminde_time_Text"));
        this.a = (SobotImageView) view.findViewById(q.a(context, "id", "sobot_imgHead"));
        this.q = (TextView) view.findViewById(q.a(context, "id", "sobot_name"));
        this.s = (FrameLayout) view.findViewById(q.a(context, "id", "sobot_frame_layout"));
        this.u = (ProgressBar) view.findViewById(q.a(context, "id", "sobot_msgProgressBar"));
        this.t = (ImageView) view.findViewById(q.a(context, "id", "sobot_msgStatus"));
        this.v = view.findViewById(q.a(context, "id", "sobot_ll_content"));
        this.w = (RelativeLayout) view.findViewById(q.a(context, "id", "sobot_rl_hollow_container"));
        this.x = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_hollow_container"));
        this.z = (RelativeLayout) view.findViewById(q.a(context, "id", "sobot_right_empty_rl"));
        this.A = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_likeBtn"));
        this.B = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_dislikeBtn"));
        this.C = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_likeBtn"));
        this.D = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_dislikeBtn"));
        this.b = (TextView) view.findViewById(q.a(context, "id", "sobot_msg"));
        this.y = q.a(this.n, "color", "sobot_chat_file_bgColor");
        a();
    }

    public void a(int i, Context context, ZhiChiMessageBase zhiChiMessageBase, String str, String str2) {
        switch (i) {
            case 0:
            case 3:
            case 4:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 17:
            case 18:
            case 19:
            case 28:
                this.o = false;
                return;
            case 1:
            case 5:
            case 6:
            case 12:
            case 20:
            case 21:
            case 22:
            case 24:
            case 25:
                this.o = true;
                return;
            case 2:
            case 7:
            case 8:
            case 16:
            case 23:
            case 26:
            case 27:
            default:
                return;
        }
    }

    public void h() {
        View view;
        View view2;
        if (j()) {
            if (!(-1 == c.u || (view2 = this.v) == null)) {
                r.a(this.n, view2, c.u);
            }
        } else if (!(-1 == c.p || (view = this.v) == null)) {
            r.a(this.n, view, c.p);
        }
        RelativeLayout relativeLayout = this.w;
        GradientDrawable gradientDrawable = null;
        if (!(relativeLayout == null || relativeLayout.getBackground() == null)) {
            int i = -1 != c.q ? c.q : this.y;
            Drawable mutate = this.w.getBackground().mutate();
            GradientDrawable gradientDrawable2 = mutate instanceof GradientDrawable ? (GradientDrawable) mutate : null;
            if (gradientDrawable2 != null) {
                gradientDrawable2.setStroke(r.a(this.n, 1.0f), this.n.getResources().getColor(i));
            }
        }
        LinearLayout linearLayout = this.x;
        if (linearLayout != null && linearLayout.getBackground() != null) {
            int i2 = -1 != c.q ? c.q : this.y;
            Drawable mutate2 = this.x.getBackground().mutate();
            if (mutate2 instanceof GradientDrawable) {
                gradientDrawable = (GradientDrawable) mutate2;
            }
            if (gradientDrawable != null) {
                gradientDrawable.setStroke(r.a(this.n, 1.0f), this.n.getResources().getColor(i2));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(TextView textView) {
        if (textView == null) {
            return;
        }
        if (!j()) {
            if (-1 != c.n) {
                textView.setTextColor(this.n.getResources().getColor(c.n));
            }
        } else if (-1 != c.v) {
            textView.setTextColor(this.n.getResources().getColor(c.v));
        }
    }

    /* access modifiers changed from: protected */
    public int i() {
        if (j()) {
            if (-1 != c.r) {
                return c.r;
            }
            return q.a(this.n, "color", "sobot_color_rlink");
        } else if (-1 != c.o) {
            return c.o;
        } else {
            return q.a(this.n, "color", "sobot_color_link");
        }
    }

    public boolean j() {
        return this.o;
    }

    public void a(boolean z) {
        this.o = z;
    }

    public void a(e.a aVar) {
        this.p = aVar;
    }

    public static void a(Context context, ImageView imageView, a aVar) {
        int i = context.getResources().getDisplayMetrics().widthPixels == 480 ? 80 : 120;
        ReSendDialog reSendDialog = new ReSendDialog(context);
        reSendDialog.a(new AnonymousClass1(aVar, reSendDialog));
        reSendDialog.show();
        imageView.setClickable(true);
        Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        if (reSendDialog.getWindow() != null) {
            WindowManager.LayoutParams attributes = reSendDialog.getWindow().getAttributes();
            attributes.width = defaultDisplay.getWidth() - i;
            reSendDialog.getWindow().setAttributes(attributes);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.viewHolder.base.MessageHolderBase$1  reason: invalid class name */
    public static class AnonymousClass1 implements ReSendDialog.a {
        final /* synthetic */ a a;
        final /* synthetic */ ReSendDialog b;

        AnonymousClass1(a aVar, ReSendDialog reSendDialog) {
            this.a = aVar;
            this.b = reSendDialog;
        }

        @Override // com.sobot.chat.widget.ReSendDialog.a
        public void a(int i) {
            if (i == 0) {
                this.a.a();
            }
            this.b.dismiss();
        }
    }

    public static class ImageClickLisenter implements View.OnClickListener {
        private Context a;
        private String b;
        private boolean c;

        public ImageClickLisenter(Context context, String str) {
            this.b = str;
            this.a = context;
        }

        public ImageClickLisenter(Context context, String str, boolean z) {
            this(context, str);
            this.c = z;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TextUtils.isEmpty(this.b)) {
                Context context = this.a;
                ae.a(context, q.f(context, "sobot_pic_type_error"));
                return;
            }
            Intent intent = new Intent(this.a, SobotPhotoActivity.class);
            intent.putExtra("imageUrL", this.b);
            boolean z = this.c;
            if (z) {
                intent.putExtra("isRight", z);
            }
            this.a.startActivity(intent);
        }
    }

    public void a(ZhiChiMessageBase zhiChiMessageBase) {
        this.m = zhiChiMessageBase;
    }

    private void a() {
        SobotImageView sobotImageView = this.a;
        if (sobotImageView != null) {
            sobotImageView.setCornerRadius(4);
        }
    }
}
