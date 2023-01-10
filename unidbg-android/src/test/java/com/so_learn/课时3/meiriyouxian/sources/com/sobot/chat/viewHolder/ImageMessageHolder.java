package com.sobot.chat.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sobot.chat.adapter.e;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.t;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import com.sobot.chat.widget.RoundProgressBar;
import com.sobot.chat.widget.image.SobotRCImageView;

public class ImageMessageHolder extends MessageHolderBase {
    SobotRCImageView a;
    ImageView b;
    public ProgressBar c;
    public RoundProgressBar d;
    TextView e;
    RelativeLayout f;

    public ImageMessageHolder(Context context, View view) {
        super(context, view);
        this.e = (TextView) view.findViewById(q.a(context, "id", "sobot_pic_isgif"));
        this.a = (SobotRCImageView) view.findViewById(q.a(context, "id", "sobot_iv_picture"));
        this.b = (ImageView) view.findViewById(q.a(context, "id", "sobot_pic_send_status"));
        this.c = (ProgressBar) view.findViewById(q.a(context, "id", "sobot_pic_progress"));
        this.d = (RoundProgressBar) view.findViewById(q.a(context, "id", "sobot_pic_progress_round"));
        this.f = (RelativeLayout) view.findViewById(q.a(context, "id", "sobot_pic_progress_rl"));
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        this.e.setVisibility(8);
        this.a.setVisibility(0);
        if (this.o) {
            this.d.setVisibility(8);
            this.f.setVisibility(0);
            if (zhiChiMessageBase.getSendSuccessState() == 0) {
                this.b.setVisibility(0);
                this.c.setVisibility(8);
                this.d.setVisibility(8);
                this.f.setVisibility(8);
                this.b.setOnClickListener(new RetrySendImageLisenter(context, zhiChiMessageBase.getId(), zhiChiMessageBase.getAnswer().getMsg(), this.b, this.p));
            } else if (1 == zhiChiMessageBase.getSendSuccessState()) {
                this.b.setVisibility(8);
                this.c.setVisibility(8);
                this.d.setVisibility(8);
                this.f.setVisibility(8);
            } else if (2 == zhiChiMessageBase.getSendSuccessState()) {
                this.c.setVisibility(0);
                this.b.setVisibility(8);
            } else {
                this.b.setVisibility(8);
                this.c.setVisibility(8);
                this.d.setVisibility(8);
                this.f.setVisibility(8);
            }
        }
        t.a(context, zhiChiMessageBase.getAnswer().getMsg(), this.a);
        this.a.setOnClickListener(new MessageHolderBase.ImageClickLisenter(context, zhiChiMessageBase.getAnswer().getMsg(), this.o));
    }

    public static class RetrySendImageLisenter implements View.OnClickListener {
        e.a a;
        private String b;
        private String c;
        private ImageView d;
        private Context e;

        public RetrySendImageLisenter(Context context, String str, String str2, ImageView imageView, e.a aVar) {
            this.b = str;
            this.c = str2;
            this.d = imageView;
            this.e = context;
            this.a = aVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImageView imageView = this.d;
            if (imageView != null) {
                imageView.setClickable(false);
            }
            a(this.e, this.c, this.b, this.d);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.viewHolder.ImageMessageHolder$RetrySendImageLisenter$1  reason: invalid class name */
        public class AnonymousClass1 implements MessageHolderBase.a {
            final /* synthetic */ Context a;
            final /* synthetic */ String b;
            final /* synthetic */ String c;

            AnonymousClass1(Context context, String str, String str2) {
                this.a = context;
                this.b = str;
                this.c = str2;
            }

            @Override // com.sobot.chat.viewHolder.base.MessageHolderBase.a
            public void a() {
                if (this.a != null) {
                    ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
                    zhiChiMessageBase.setContent(this.b);
                    zhiChiMessageBase.setId(this.c);
                    zhiChiMessageBase.setSendSuccessState(2);
                    if (RetrySendImageLisenter.this.a != null) {
                        RetrySendImageLisenter.this.a.a(zhiChiMessageBase, 3, 3, "");
                    }
                }
            }
        }

        private void a(Context context, String str, String str2, ImageView imageView) {
            MessageHolderBase.a(context, imageView, new AnonymousClass1(context, str, str2));
        }
    }
}
