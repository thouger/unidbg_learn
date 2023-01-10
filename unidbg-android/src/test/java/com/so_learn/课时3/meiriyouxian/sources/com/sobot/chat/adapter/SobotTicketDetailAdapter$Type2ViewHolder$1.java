package com.sobot.chat.adapter;

import android.content.Intent;
import android.view.View;
import com.sobot.chat.activity.WebViewActivity;
import com.sobot.chat.adapter.m;
import com.sobot.chat.api.model.StUserDealTicketReply;

class SobotTicketDetailAdapter$Type2ViewHolder$1 implements View.OnClickListener {
    final /* synthetic */ StUserDealTicketReply a;
    final /* synthetic */ m.d b;

    SobotTicketDetailAdapter$Type2ViewHolder$1(m.d dVar, StUserDealTicketReply stUserDealTicketReply) {
        this.b = dVar;
        this.a = stUserDealTicketReply;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Intent intent = new Intent(m.this.c, WebViewActivity.class);
        intent.putExtra("url", this.a.getReplyContent());
        m.this.c.startActivity(intent);
    }
}
