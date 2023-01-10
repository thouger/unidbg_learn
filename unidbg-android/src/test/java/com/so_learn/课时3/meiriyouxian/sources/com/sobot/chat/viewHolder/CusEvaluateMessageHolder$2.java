package com.sobot.chat.viewHolder;

import android.text.TextUtils;
import android.view.View;
import com.sobot.chat.api.model.SatisfactionSetBase;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.q;

class CusEvaluateMessageHolder$2 implements View.OnClickListener {
    final /* synthetic */ a a;

    CusEvaluateMessageHolder$2(a aVar) {
        this.a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.a.Q != null && this.a.Q.size() == 5 && ((SatisfactionSetBase) this.a.Q.get(4)).getIsInputMust()) {
            this.a.a(false, 5);
        } else if (!TextUtils.isEmpty(this.a.e()) || this.a.Q == null || this.a.Q.size() != 5 || !((SatisfactionSetBase) this.a.Q.get(4)).getIsTagMust() || TextUtils.isEmpty(((SatisfactionSetBase) this.a.Q.get(4)).getLabelName()) || this.a.j.isHideManualEvaluationLabels()) {
            this.a.a(true, 5);
        } else {
            ae.a(this.a.n, q.f(this.a.n, "sobot_the_label_is_required"));
        }
    }
}
