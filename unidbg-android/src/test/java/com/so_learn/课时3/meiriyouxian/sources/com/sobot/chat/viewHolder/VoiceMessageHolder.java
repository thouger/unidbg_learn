package com.sobot.chat.viewHolder;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sobot.chat.adapter.e;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.api.model.ZhiChiReplyAnswer;
import com.sobot.chat.c;
import com.sobot.chat.utils.f;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.viewHolder.base.MessageHolderBase;

public class VoiceMessageHolder extends MessageHolderBase {
    TextView a;
    ImageView b;
    LinearLayout c;
    public ZhiChiMessageBase d;

    public VoiceMessageHolder(Context context, View view) {
        super(context, view);
        this.b = (ImageView) view.findViewById(q.a(context, "id", "sobot_iv_voice"));
        this.a = (TextView) view.findViewById(q.a(context, "id", "sobot_voiceTimeLong"));
        this.c = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_voice_layout"));
        if (!(this.c == null || -1 == c.u)) {
            r.a(this.n, this.c, c.u);
        }
        this.u = (ProgressBar) view.findViewById(q.a(context, "id", "sobot_msgProgressBar"));
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        String str;
        this.d = zhiChiMessageBase;
        TextView textView = this.a;
        if (zhiChiMessageBase.getAnswer().getDuration() == null) {
            str = "";
        } else {
            str = f.a(zhiChiMessageBase.getAnswer().getDuration()) + "\u2033";
        }
        textView.setText(str);
        a(this.a);
        c();
        this.c.setOnClickListener(new AnonymousClass1(zhiChiMessageBase));
        if (this.o) {
            if (zhiChiMessageBase.getSendSuccessState() == 1) {
                this.t.setVisibility(8);
                this.u.setVisibility(8);
                this.a.setVisibility(0);
                this.b.setVisibility(0);
            } else if (zhiChiMessageBase.getSendSuccessState() == 0) {
                this.t.setVisibility(0);
                this.u.setVisibility(8);
                this.b.setVisibility(0);
                this.a.setVisibility(0);
                b();
                this.t.setOnClickListener(new RetrySendVoiceLisenter(context, zhiChiMessageBase.getId(), zhiChiMessageBase.getAnswer().getMsg(), zhiChiMessageBase.getAnswer().getDuration(), this.t, this.p));
            } else if (zhiChiMessageBase.getSendSuccessState() == 2) {
                this.u.setVisibility(0);
                this.t.setVisibility(8);
                this.a.setVisibility(8);
                this.b.setVisibility(8);
            } else if (zhiChiMessageBase.getSendSuccessState() == 4) {
                this.u.setVisibility(8);
                this.t.setVisibility(8);
                this.a.setVisibility(8);
                this.b.setVisibility(8);
            }
            long a = f.a(zhiChiMessageBase.getAnswer().getDuration());
            if (a == 0) {
                a = 1;
            }
            Activity activity = (Activity) context;
            int a2 = r.a(activity) / 5;
            int a3 = (r.a(activity) * 3) / 5;
            if (a >= 10) {
                a = (a / 10) + 9;
            }
            int i = (int) a;
            ViewGroup.LayoutParams layoutParams = this.c.getLayoutParams();
            if (i != 0) {
                a2 += ((a3 - a2) / 15) * i;
            }
            layoutParams.width = a2;
        }
    }

    /* renamed from: com.sobot.chat.viewHolder.VoiceMessageHolder$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ ZhiChiMessageBase a;

        AnonymousClass1(ZhiChiMessageBase zhiChiMessageBase) {
            this.a = zhiChiMessageBase;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (VoiceMessageHolder.this.p != null) {
                VoiceMessageHolder.this.p.a(this.a);
            }
        }
    }

    private void c() {
        int i;
        if (this.d.isVoideIsPlaying()) {
            d();
            return;
        }
        ImageView imageView = this.b;
        if (this.o) {
            i = q.a(this.n, "drawable", "sobot_pop_voice_send_anime_3");
        } else {
            i = q.a(this.n, "drawable", "sobot_pop_voice_receive_anime_3");
        }
        imageView.setImageResource(i);
    }

    private void d() {
        int i;
        ImageView imageView = this.b;
        if (this.o) {
            i = q.a(this.n, "drawable", "sobot_voice_to_icon");
        } else {
            i = q.a(this.n, "drawable", "sobot_voice_from_icon");
        }
        imageView.setImageResource(i);
        Drawable drawable = this.b.getDrawable();
        if (drawable != null && (drawable instanceof AnimationDrawable)) {
            ((AnimationDrawable) drawable).start();
        }
    }

    public void a() {
        this.d.setVoideIsPlaying(true);
        Drawable drawable = this.b.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        } else {
            d();
        }
    }

    public void b() {
        this.d.setVoideIsPlaying(false);
        Drawable drawable = this.b.getDrawable();
        if (drawable != null && (drawable instanceof AnimationDrawable)) {
            AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
            animationDrawable.stop();
            animationDrawable.selectDrawable(2);
        }
    }

    public static class RetrySendVoiceLisenter implements View.OnClickListener {
        private String a;
        private String b;
        private String c;
        private ImageView d;
        private Context e;
        private e.a f;

        public RetrySendVoiceLisenter(Context context, String str, String str2, String str3, ImageView imageView, e.a aVar) {
            this.e = context;
            this.f = aVar;
            this.a = str2;
            this.b = str;
            this.c = str3;
            this.d = imageView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImageView imageView = this.d;
            if (imageView != null) {
                imageView.setClickable(false);
            }
            a(this.e, this.a, this.b, this.c, this.d);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.viewHolder.VoiceMessageHolder$RetrySendVoiceLisenter$1  reason: invalid class name */
        public class AnonymousClass1 implements MessageHolderBase.a {
            final /* synthetic */ Context a;
            final /* synthetic */ String b;
            final /* synthetic */ String c;
            final /* synthetic */ String d;

            AnonymousClass1(Context context, String str, String str2, String str3) {
                this.a = context;
                this.b = str;
                this.c = str2;
                this.d = str3;
            }

            @Override // com.sobot.chat.viewHolder.base.MessageHolderBase.a
            public void a() {
                if (this.a != null) {
                    ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
                    ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
                    zhiChiReplyAnswer.setDuration(this.b);
                    zhiChiMessageBase.setContent(this.c);
                    zhiChiMessageBase.setId(this.d);
                    zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
                    if (RetrySendVoiceLisenter.this.f != null) {
                        RetrySendVoiceLisenter.this.f.a(zhiChiMessageBase, 2, 3, "");
                    }
                }
            }
        }

        private void a(Context context, String str, String str2, String str3, ImageView imageView) {
            MessageHolderBase.a(context, imageView, new AnonymousClass1(context, str3, str, str2));
        }
    }
}
