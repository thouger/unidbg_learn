package com.sobot.chat.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.sobot.chat.activity.SobotTicketDetailActivity;
import com.sobot.chat.api.model.SobotLeaveReplyModel;
import com.sobot.chat.api.model.SobotUserTicketInfo;
import com.sobot.chat.utils.m;

public class SobotLeaveMsgReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("sobot_leaverepley_notification_click".equals(intent.getAction())) {
            SobotLeaveReplyModel sobotLeaveReplyModel = (SobotLeaveReplyModel) intent.getSerializableExtra("sobot_leavereply_model");
            String stringExtra = intent.getStringExtra("sobot_leavereply_companyId");
            String stringExtra2 = intent.getStringExtra("sobot_leavereply_uid");
            m.d(" \u7559\u8a00\u56de\u590d\uff1a" + sobotLeaveReplyModel);
            SobotUserTicketInfo sobotUserTicketInfo = new SobotUserTicketInfo();
            sobotUserTicketInfo.setTicketId(sobotLeaveReplyModel.getTicketId());
            context.startActivity(SobotTicketDetailActivity.a(context, stringExtra, stringExtra2, sobotUserTicketInfo));
        }
    }
}
