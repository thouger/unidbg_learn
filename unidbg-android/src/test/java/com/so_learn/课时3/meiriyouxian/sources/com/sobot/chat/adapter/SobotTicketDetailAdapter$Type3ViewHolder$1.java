package com.sobot.chat.adapter;

import android.content.Intent;
import android.view.View;
import com.sobot.chat.activity.WebViewActivity;
import com.sobot.chat.adapter.m;
import com.sobot.chat.api.model.StUserDealTicketInfo;

class SobotTicketDetailAdapter$Type3ViewHolder$1 implements View.OnClickListener {
    final /* synthetic */ StUserDealTicketInfo a;
    final /* synthetic */ m.e b;

    SobotTicketDetailAdapter$Type3ViewHolder$1(m.e eVar, StUserDealTicketInfo stUserDealTicketInfo) {
        this.b = eVar;
        this.a = stUserDealTicketInfo;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Intent intent = new Intent(m.this.c, WebViewActivity.class);
        intent.putExtra("url", this.a.getContent());
        m.this.c.startActivity(intent);
    }
}
