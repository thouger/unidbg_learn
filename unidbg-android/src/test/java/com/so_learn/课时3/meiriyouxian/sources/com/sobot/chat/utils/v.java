package com.sobot.chat.utils;

import com.sobot.chat.api.model.SobotMsgCenterModel;
import java.util.Comparator;

/* compiled from: SobotCompareNewMsgTime */
public class v implements Comparator<SobotMsgCenterModel> {
    /* renamed from: a */
    public int compare(SobotMsgCenterModel sobotMsgCenterModel, SobotMsgCenterModel sobotMsgCenterModel2) {
        return b(sobotMsgCenterModel, sobotMsgCenterModel2);
    }

    private int b(SobotMsgCenterModel sobotMsgCenterModel, SobotMsgCenterModel sobotMsgCenterModel2) {
        int i = ((a(sobotMsgCenterModel2) - a(sobotMsgCenterModel)) > 0 ? 1 : ((a(sobotMsgCenterModel2) - a(sobotMsgCenterModel)) == 0 ? 0 : -1));
        if (i > 0) {
            return 1;
        }
        return i == 0 ? 0 : -1;
    }

    private long a(SobotMsgCenterModel sobotMsgCenterModel) {
        if (sobotMsgCenterModel == null || sobotMsgCenterModel.getLastDateTime() == null) {
            return 0;
        }
        try {
            return Long.parseLong(sobotMsgCenterModel.getLastDateTime());
        } catch (Exception unused) {
            return 0;
        }
    }
}
