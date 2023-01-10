package com.sobot.chat.viewHolder;

import android.content.Context;
import android.media.TtmlUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sobot.chat.adapter.e;
import com.sobot.chat.api.model.SobotQuestionRecommend;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.j;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.t;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import java.util.List;

public class RobotQRMessageHolder extends MessageHolderBase {
    private TextView a;
    private LinearLayout b;
    private HorizontalScrollView c;

    public RobotQRMessageHolder(Context context, View view) {
        super(context, view);
        this.a = (TextView) view.findViewById(q.a(context, "id", "sobot_msg"));
        this.b = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_template1_horizontal_scrollview_layout"));
        this.c = (HorizontalScrollView) view.findViewById(q.a(context, "id", "sobot_template1_horizontal_scrollview"));
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        QuestionRecommendViewHolder questionRecommendViewHolder;
        if (!(zhiChiMessageBase.getAnswer() == null || zhiChiMessageBase.getAnswer().getQuestionRecommend() == null)) {
            SobotQuestionRecommend questionRecommend = zhiChiMessageBase.getAnswer().getQuestionRecommend();
            if (TextUtils.isEmpty(questionRecommend.getGuide())) {
                this.a.setVisibility(8);
            } else {
                j.a(context).a(this.a, questionRecommend.getGuide(), i());
                a(this.a);
                this.a.setVisibility(0);
            }
            List<SobotQuestionRecommend.SobotQRMsgBean> msg = questionRecommend.getMsg();
            if (msg == null || msg.size() <= 0) {
                this.c.setVisibility(8);
                return;
            }
            this.c.setVisibility(0);
            int childCount = this.b.getChildCount();
            for (int size = msg.size(); size < childCount; size++) {
                this.b.getChildAt(size).setVisibility(8);
            }
            for (int i = 0; i < msg.size(); i++) {
                SobotQuestionRecommend.SobotQRMsgBean sobotQRMsgBean = msg.get(i);
                if (i < childCount) {
                    View childAt = this.b.getChildAt(i);
                    childAt.setVisibility(0);
                    questionRecommendViewHolder = (QuestionRecommendViewHolder) childAt.getTag();
                } else {
                    View inflate = View.inflate(context, q.a(context, TtmlUtils.TAG_LAYOUT, "sobot_chat_msg_item_qr_item"), null);
                    QuestionRecommendViewHolder questionRecommendViewHolder2 = new QuestionRecommendViewHolder(context, inflate, this.p);
                    inflate.setTag(questionRecommendViewHolder2);
                    this.b.addView(inflate);
                    questionRecommendViewHolder = questionRecommendViewHolder2;
                }
                boolean z = true;
                if (i != msg.size() - 1) {
                    z = false;
                }
                questionRecommendViewHolder.a(context, sobotQRMsgBean, z);
            }
        }
    }

    public static class QuestionRecommendViewHolder implements View.OnClickListener {
        LinearLayout a;
        ImageView b;
        TextView c;
        Context d;
        SobotQuestionRecommend.SobotQRMsgBean e;
        e.a f;

        private QuestionRecommendViewHolder(Context context, View view, e.a aVar) {
            this.f = aVar;
            this.a = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_template1_item"));
            this.b = (ImageView) view.findViewById(q.a(context, "id", "sobot_item_thumbnail"));
            this.c = (TextView) view.findViewById(q.a(context, "id", "sobot_item_title"));
        }

        public void a(Context context, SobotQuestionRecommend.SobotQRMsgBean sobotQRMsgBean, boolean z) {
            this.d = context;
            this.e = sobotQRMsgBean;
            if (sobotQRMsgBean != null) {
                int i = 0;
                t.a(context, sobotQRMsgBean.getIcon(), this.b, 0, 0);
                this.c.setText(TextUtils.isEmpty(sobotQRMsgBean.getTitle()) ? sobotQRMsgBean.getQuestion() : sobotQRMsgBean.getTitle());
                this.a.setOnClickListener(this);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.a.getLayoutParams();
                int i2 = layoutParams.leftMargin;
                int i3 = layoutParams.topMargin;
                if (z) {
                    i = (int) d.e(context, "sobot_item_qr_divider");
                }
                layoutParams.setMargins(i2, i3, i, layoutParams.bottomMargin);
                this.a.setLayoutParams(layoutParams);
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f != null && this.e != null) {
                ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
                zhiChiMessageBase.setContent(this.e.getQuestion());
                this.f.a(zhiChiMessageBase, 0, 0, null);
            }
        }
    }
}
