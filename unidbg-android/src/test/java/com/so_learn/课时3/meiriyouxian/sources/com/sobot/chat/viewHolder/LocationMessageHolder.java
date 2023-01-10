package com.sobot.chat.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sobot.chat.api.model.SobotLocationModel;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.aa;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.t;
import com.sobot.chat.utils.y;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import com.sobot.chat.widget.image.SobotRCImageView;

public class LocationMessageHolder extends MessageHolderBase implements View.OnClickListener {
    private TextView a;
    private TextView b;
    private SobotRCImageView c;
    private ImageView d;
    private ProgressBar e;
    private LinearLayout f;
    private ZhiChiMessageBase g;
    private SobotLocationModel h;
    private int i;

    public LocationMessageHolder(Context context, View view) {
        super(context, view);
        this.a = (TextView) view.findViewById(q.g(context, "st_localName"));
        this.b = (TextView) view.findViewById(q.g(context, "st_localLabel"));
        this.d = (ImageView) view.findViewById(q.g(context, "sobot_msgStatus"));
        this.c = (SobotRCImageView) view.findViewById(q.g(context, "st_snapshot"));
        this.f = (LinearLayout) view.findViewById(q.g(context, "sobot_ll_hollow_container"));
        this.e = (ProgressBar) view.findViewById(q.g(context, "sobot_msgProgressBar"));
        this.f.setOnClickListener(this);
        this.i = q.e(context, "sobot_bg_default_map");
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        this.g = zhiChiMessageBase;
        if (zhiChiMessageBase.getAnswer() != null && zhiChiMessageBase.getAnswer().getLocationData() != null) {
            this.h = zhiChiMessageBase.getAnswer().getLocationData();
            this.a.setText(this.h.getLocalName());
            this.b.setText(this.h.getLocalLabel());
            String snapshot = this.h.getSnapshot();
            SobotRCImageView sobotRCImageView = this.c;
            int i = this.i;
            t.a(context, snapshot, sobotRCImageView, i, i);
            if (this.o) {
                a();
            }
        }
    }

    private void a() {
        try {
            if (this.g != null) {
                if (this.g.getSendSuccessState() == 1) {
                    this.d.setVisibility(8);
                    this.e.setVisibility(8);
                } else if (this.g.getSendSuccessState() == 0) {
                    this.d.setVisibility(0);
                    this.e.setVisibility(8);
                    this.e.setClickable(true);
                    this.d.setOnClickListener(this);
                } else if (this.g.getSendSuccessState() == 2) {
                    this.d.setVisibility(8);
                    this.e.setVisibility(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.sobot.chat.viewHolder.LocationMessageHolder$1  reason: invalid class name */
    class AnonymousClass1 implements MessageHolderBase.a {
        AnonymousClass1() {
        }

        @Override // com.sobot.chat.viewHolder.base.MessageHolderBase.a
        public void a() {
            if (LocationMessageHolder.this.p != null && LocationMessageHolder.this.g != null && LocationMessageHolder.this.g.getAnswer() != null) {
                LocationMessageHolder.this.p.a(LocationMessageHolder.this.g, 5, 0, null);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.d) {
            a(this.n, this.t, new AnonymousClass1());
        }
        if (view == this.f && this.h != null) {
            if (y.i == null || !y.i.a(this.n, this.h)) {
                aa.a(this.n, this.h);
            }
        }
    }
}
