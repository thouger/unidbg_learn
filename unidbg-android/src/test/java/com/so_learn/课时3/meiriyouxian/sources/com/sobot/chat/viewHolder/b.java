package com.sobot.chat.viewHolder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.q;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import com.sobot.chat.widget.StExpandableTextView;

/* compiled from: NoticeMessageHolder */
public class b extends MessageHolderBase {
    private StExpandableTextView a;
    private ZhiChiMessageBase b;

    public b(Context context, View view) {
        super(context, view);
        this.a = (StExpandableTextView) view.findViewById(q.g(context, "expand_text_view"));
        this.a.setLinkBottomLine(true);
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        this.b = zhiChiMessageBase;
        if (zhiChiMessageBase.getAnswer() != null && !TextUtils.isEmpty(zhiChiMessageBase.getAnswer().getMsg())) {
            this.a.setText(zhiChiMessageBase.getAnswer().getMsg());
        }
    }
}
