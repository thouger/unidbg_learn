package com.sobot.chat.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.media.TtmlUtils;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sobot.chat.api.model.SobotUserTicketInfo;
import com.sobot.chat.b.a;
import com.sobot.chat.d;
import com.sobot.chat.utils.f;
import com.sobot.chat.utils.q;
import java.util.List;

/* compiled from: SobotTicketInfoAdapter */
public class n extends com.sobot.chat.adapter.base.a<SobotUserTicketInfo> {
    private static final String[] e = {"sobot_ticket_info_item"};
    private Context a;
    private Activity d;

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return 0;
    }

    public n(Activity activity, Context context, List list) {
        super(context, list);
        this.a = context;
        this.d = activity;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        SobotUserTicketInfo sobotUserTicketInfo = (SobotUserTicketInfo) this.b.get(i);
        if (sobotUserTicketInfo == null) {
            return view;
        }
        View a2 = a(view, getItemViewType(i), i, sobotUserTicketInfo);
        ((a) a2.getTag()).a(sobotUserTicketInfo);
        return a2;
    }

    private View a(View view, int i, int i2, SobotUserTicketInfo sobotUserTicketInfo) {
        b bVar;
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(q.a(this.c, TtmlUtils.TAG_LAYOUT, e[i]), (ViewGroup) null);
            if (i != 0) {
                bVar = new b(this.d, this.c, view);
            } else {
                bVar = new b(this.d, this.c, view);
            }
            view.setTag(bVar);
        }
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        String[] strArr = e;
        if (strArr.length > 0) {
            return strArr.length;
        }
        return super.getViewTypeCount();
    }

    /* compiled from: SobotTicketInfoAdapter */
    static abstract class a {
        Context a;

        /* access modifiers changed from: package-private */
        public abstract void a(SobotUserTicketInfo sobotUserTicketInfo);

        a(Context context, View view) {
            this.a = context;
        }
    }

    /* compiled from: SobotTicketInfoAdapter */
    /* access modifiers changed from: package-private */
    public class b extends a {
        private TextView c;
        private TextView d;
        private TextView e;
        private TextView f;
        private TextView g;
        private ImageView h;
        private int i;
        private int j;
        private int k;
        private String l;
        private String m;
        private String n;
        private Context o;
        private Activity p;

        b(Activity activity, Context context, View view) {
            super(context, view);
            this.o = context;
            this.p = activity;
            this.c = (TextView) view.findViewById(q.g(context, "sobot_tv_title"));
            this.d = (TextView) view.findViewById(q.g(context, "sobot_tv_ticket_status"));
            this.e = (TextView) view.findViewById(q.g(context, "sobot_tv_content"));
            this.f = (TextView) view.findViewById(q.g(context, "sobot_tv_code"));
            this.g = (TextView) view.findViewById(q.g(context, "sobot_tv_time"));
            this.h = (ImageView) view.findViewById(q.g(context, "sobot_tv_new"));
            this.i = q.e(context, "sobot_ticket_status_bg3");
            this.j = q.e(context, "sobot_ticket_status_bg2");
            this.k = q.e(context, "sobot_ticket_status_bg1");
            this.l = q.f(context, "sobot_created_1");
            this.m = q.f(context, "sobot_processing");
            this.n = q.f(context, "sobot_completed");
        }

        /* access modifiers changed from: package-private */
        @Override // com.sobot.chat.adapter.n.a
        public void a(SobotUserTicketInfo sobotUserTicketInfo) {
            this.e.setText(TextUtils.isEmpty(sobotUserTicketInfo.getContent()) ? "" : Html.fromHtml(sobotUserTicketInfo.getContent()));
            if (2 == sobotUserTicketInfo.getFlag()) {
                this.d.setText(this.m);
                this.d.setBackgroundResource(this.j);
            } else if (3 == sobotUserTicketInfo.getFlag()) {
                this.d.setText(this.n);
                this.d.setBackgroundResource(this.k);
            } else {
                this.d.setText(this.l);
                this.d.setBackgroundResource(this.i);
            }
            this.h.setVisibility(sobotUserTicketInfo.isNewFlag() ? 0 : 8);
            this.g.setText(f.a(sobotUserTicketInfo.getTimeStr(), f.i, Boolean.valueOf(d.a(8))));
            a(this.g);
            a(this.e);
        }

        public void a(View view) {
            if (com.sobot.chat.b.a(1) && com.sobot.chat.b.a(4) && view != null) {
                com.sobot.chat.b.b.a().a(this.p);
                this.p.getWindow().setFlags(1024, 1024);
                com.sobot.chat.b.b.a().a(this.p, new AnonymousClass1(view));
            }
        }

        /* compiled from: SobotTicketInfoAdapter */
        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.adapter.n$b$1  reason: invalid class name */
        public class AnonymousClass1 implements a.AbstractC0139a {
            final /* synthetic */ View a;

            AnonymousClass1(View view) {
                this.a = view;
            }

            @Override // com.sobot.chat.b.a.AbstractC0139a
            public void a(a.b bVar) {
                if (bVar.a) {
                    for (Rect rect : bVar.b) {
                        this.a.setPadding(rect.right, this.a.getPaddingTop(), this.a.getPaddingRight(), this.a.getPaddingBottom());
                    }
                }
            }
        }
    }
}
