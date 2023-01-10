package cn.missfresh.module.base.im;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.ICustomMessageViewGroup;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.IOnCustomMessageDrawListener;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;

public class CustomMessageDraw implements IOnCustomMessageDrawListener {
    private Context a;

    public CustomMessageDraw(Context context) {
        this.a = context;
    }

    @Override // com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.IOnCustomMessageDrawListener
    public void onDraw(ICustomMessageViewGroup iCustomMessageViewGroup, MessageInfo messageInfo) {
        AppMethodBeat.i(13298, false);
        if (!(messageInfo.getElement() instanceof TIMCustomElem)) {
            AppMethodBeat.o(13298);
            return;
        }
        TIMCustomElem tIMCustomElem = (TIMCustomElem) messageInfo.getElement();
        if (tIMCustomElem == null) {
            AppMethodBeat.o(13298);
            return;
        }
        if ("9".equalsIgnoreCase(new String(tIMCustomElem.getData()))) {
            View inflate = LayoutInflater.from(this.a).inflate(R.layout.order_chat_custom_message, (ViewGroup) null, false);
            iCustomMessageViewGroup.addMessageContentView(inflate);
            ((TextView) inflate.findViewById(R.id.customer_service_title)).setText(tIMCustomElem.getDesc());
            ((TextView) inflate.findViewById(R.id.customer_service_chat)).setOnClickListener(new AnonymousClass1());
        } else {
            View inflate2 = LayoutInflater.from(this.a).inflate(R.layout.order_chat_custom_message_text, (ViewGroup) null, false);
            iCustomMessageViewGroup.addMessageContentView(inflate2);
            ((TextView) inflate2.findViewById(R.id.customer_service_title)).setText(tIMCustomElem.getDesc());
        }
        AppMethodBeat.o(13298);
    }

    /* renamed from: cn.missfresh.module.base.im.CustomMessageDraw$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(13292, false);
            a.a().a("/order/custom_service").navigation();
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(13292);
        }
    }
}
