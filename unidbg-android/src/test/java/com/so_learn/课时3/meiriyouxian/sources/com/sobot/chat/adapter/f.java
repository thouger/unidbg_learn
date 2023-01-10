package com.sobot.chat.adapter;

import android.content.Context;
import android.media.TtmlUtils;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sobot.chat.api.model.SobotMsgCenterModel;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.t;
import java.util.List;

/* compiled from: SobotMsgCenterAdapter */
public class f extends com.sobot.chat.adapter.base.a<SobotMsgCenterModel> {
    public f(Context context, List<SobotMsgCenterModel> list) {
        super(context, list);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        SobotMsgCenterModel sobotMsgCenterModel = (SobotMsgCenterModel) this.b.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(q.a(this.c, TtmlUtils.TAG_LAYOUT, "sobot_msg_center_item"), (ViewGroup) null);
            aVar = new a(this.c, view);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a(sobotMsgCenterModel);
        return view;
    }

    /* compiled from: SobotMsgCenterAdapter */
    public static class a {
        TextView a;
        TextView b;
        TextView c;
        TextView d;
        ImageView e;
        Context f;
        int g;
        private SobotMsgCenterModel h = null;

        public a(Context context, View view) {
            this.f = context;
            this.a = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_title"));
            this.c = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_unread_count"));
            this.b = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_content"));
            this.d = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_date"));
            this.e = (ImageView) view.findViewById(q.a(context, "id", "sobot_iv_face"));
            this.g = q.a(context, "drawable", "sobot_chatting_default_head");
        }

        public void a(SobotMsgCenterModel sobotMsgCenterModel) {
            if (sobotMsgCenterModel != null) {
                this.h = sobotMsgCenterModel;
                t.a(this.f, sobotMsgCenterModel.getFace(), this.e, this.g);
                this.a.setText(sobotMsgCenterModel.getName());
                this.b.setText(TextUtils.isEmpty(sobotMsgCenterModel.getLastMsg()) ? "" : Html.fromHtml(sobotMsgCenterModel.getLastMsg()).toString());
                if (!TextUtils.isEmpty(sobotMsgCenterModel.getLastDateTime())) {
                    try {
                        this.d.setText(com.sobot.chat.utils.f.b(sobotMsgCenterModel.getLastDateTime()));
                    } catch (Exception unused) {
                    }
                }
                a(this.c, sobotMsgCenterModel.getUnreadCount());
            }
        }

        private void a(TextView textView, int i) {
            if (i > 0) {
                if (i <= 9) {
                    textView.setBackgroundResource(q.a(this.f, "drawable", "sobot_message_bubble_1"));
                    textView.setText(i + "");
                } else if (i <= 9 || i > 99) {
                    textView.setBackgroundResource(q.a(this.f, "drawable", "sobot_message_bubble_3"));
                    textView.setText("99+");
                } else {
                    textView.setBackgroundResource(q.a(this.f, "drawable", "sobot_message_bubble_2"));
                    textView.setText(i + "");
                }
                textView.setVisibility(0);
                return;
            }
            textView.setVisibility(8);
        }
    }
}
