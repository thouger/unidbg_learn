package com.sobot.chat.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.api.model.SobotMsgCenterModel;
import com.sobot.chat.api.model.ZhiChiPushMessage;
import java.util.Calendar;
import java.util.List;

public abstract class SobotMsgCenterReceiver extends BroadcastReceiver {
    public abstract List<SobotMsgCenterModel> a();

    public abstract void a(SobotMsgCenterModel sobotMsgCenterModel);

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        SobotMsgCenterModel sobotMsgCenterModel;
        ZhiChiPushMessage zhiChiPushMessage;
        List<SobotMsgCenterModel> a;
        if (ZhiChiConstants.receiveMessageBrocast.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            if (!(extras == null || (zhiChiPushMessage = (ZhiChiPushMessage) extras.getSerializable(ZhiChiConstants.ZHICHI_PUSH_MESSAGE)) == null || TextUtils.isEmpty(zhiChiPushMessage.getAppId()) || 202 != zhiChiPushMessage.getType() || (a = a()) == null)) {
                for (int i = 0; i < a.size(); i++) {
                    SobotMsgCenterModel sobotMsgCenterModel2 = a.get(i);
                    if (sobotMsgCenterModel2.getInfo() != null && zhiChiPushMessage.getAppId().equals(sobotMsgCenterModel2.getInfo().getApp_key())) {
                        sobotMsgCenterModel2.setLastDateTime(Calendar.getInstance().getTime().getTime() + "");
                        if (zhiChiPushMessage.getAnswer() != null) {
                            sobotMsgCenterModel2.setLastMsg(zhiChiPushMessage.getAnswer().getMsg());
                        }
                        sobotMsgCenterModel2.setUnreadCount(sobotMsgCenterModel2.getUnreadCount() + 1);
                        a(sobotMsgCenterModel2);
                        return;
                    }
                }
            }
        } else if ("SOBOT_ACTION_UPDATE_LAST_MSG".equals(intent.getAction()) && (sobotMsgCenterModel = (SobotMsgCenterModel) intent.getSerializableExtra("lastMsg")) != null && sobotMsgCenterModel.getInfo() != null && !TextUtils.isEmpty(sobotMsgCenterModel.getInfo().getApp_key())) {
            a(sobotMsgCenterModel);
        }
    }
}
