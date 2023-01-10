package com.sobot.chat.utils;

import android.text.format.DateFormat;
import com.sobot.chat.api.enumtype.CustomerState;
import com.sobot.chat.api.model.ZhiChiInitModeBase;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ZhiChiConfig implements Serializable {
    public String activityTitle = null;
    public String adminFace = "";
    public int bottomViewtype = 0;
    public List<String> cids = new ArrayList();
    public int currentCidPosition = 0;
    public String currentUserName;
    public int current_client_model = 301;
    public boolean customTimeTask = false;
    public CustomerState customerState = CustomerState.Offline;
    private ZhiChiInitModeBase initModel = null;
    public boolean isAboveZero = false;
    public int isChatLock = 0;
    public boolean isComment = false;
    public boolean isNoMoreHistoryMsg = false;
    public boolean isProcessAutoSendMsg = false;
    public boolean isShowQueueTip = true;
    public boolean isShowUnreadUi = true;
    private List<ZhiChiMessageBase> messageList = new ArrayList();
    public int paseReplyTimeCustoms = 0;
    public int paseReplyTimeUserInfo = 0;
    public int queryCidsStatus = 0;
    public int queueNum = 0;
    public int remindRobotMessageTimes = 0;
    public int showTimeVisiableCustomBtn = 0;
    public String tempMsgContent;
    public boolean userInfoTimeTask = false;

    public ZhiChiInitModeBase getInitModel() {
        return this.initModel;
    }

    public void setInitModel(ZhiChiInitModeBase zhiChiInitModeBase) {
        this.initModel = zhiChiInitModeBase;
    }

    public void setMessageList(List<ZhiChiMessageBase> list) {
        List<ZhiChiMessageBase> list2;
        if (!(list == null || (list2 = this.messageList) == null)) {
            list2.clear();
            this.messageList.addAll(list);
            for (int size = this.messageList.size() - 1; size >= 0; size--) {
                if (this.messageList.get(size).getSendSuccessState() == 4) {
                    this.messageList.remove(size);
                } else if (this.messageList.get(size).getSendSuccessState() == 2) {
                    this.messageList.get(size).setSendSuccessState(0);
                } else if (this.messageList.get(size).getAnswer() != null && 7 == this.messageList.get(size).getAnswer().getRemindType()) {
                    this.messageList.remove(size);
                }
            }
        }
    }

    public void addMessage(ZhiChiMessageBase zhiChiMessageBase) {
        List<ZhiChiMessageBase> list;
        if (zhiChiMessageBase != null && (list = this.messageList) != null) {
            removeByAction(list, zhiChiMessageBase, "action_remind_info_paidui", "action_remind_info_paidui");
            removeByAction(this.messageList, zhiChiMessageBase, "action_remind_connt_success", "action_remind_info_paidui");
            removeByAction(this.messageList, zhiChiMessageBase, "action_custom_evaluate", "action_custom_evaluate");
            removeByAction(this.messageList, zhiChiMessageBase, "action_remind_connt_success", "action_remind_info_post_msg");
            this.messageList.add(zhiChiMessageBase);
        }
    }

    private void removeByAction(List<ZhiChiMessageBase> list, ZhiChiMessageBase zhiChiMessageBase, String str, String str2) {
        if (zhiChiMessageBase.getAction() != null && zhiChiMessageBase.getAction().equals(str)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getAction() != null && list.get(i).getAction().equals(str2)) {
                    list.remove(i);
                }
            }
        }
    }

    public void hideItemTransferBtn() {
        if (this.messageList != null) {
            for (int i = 0; i < this.messageList.size(); i++) {
                this.messageList.get(i).setShowTransferBtn(false);
            }
        }
    }

    public List<ZhiChiMessageBase> getMessageList() {
        return this.messageList;
    }

    public void clearInitModel() {
        if (this.initModel != null) {
            this.initModel = null;
        }
    }

    public void clearMessageList() {
        List<ZhiChiMessageBase> list = this.messageList;
        if (list != null) {
            list.clear();
        }
    }

    public void clearCache() {
        clearMessageList();
        clearInitModel();
        this.current_client_model = 301;
        this.cids = null;
        this.currentCidPosition = 0;
        this.queryCidsStatus = 0;
        this.activityTitle = null;
        this.customerState = CustomerState.Offline;
        this.remindRobotMessageTimes = 0;
        this.bottomViewtype = -1;
        this.isAboveZero = false;
        this.isComment = false;
        this.adminFace = "";
        this.paseReplyTimeCustoms = 0;
        this.customTimeTask = false;
        this.paseReplyTimeUserInfo = 0;
        this.isChatLock = 0;
        this.userInfoTimeTask = false;
        this.currentUserName = null;
        this.isNoMoreHistoryMsg = false;
        this.showTimeVisiableCustomBtn = 0;
        this.queueNum = 0;
        this.isShowUnreadUi = true;
        this.isShowQueueTip = true;
        this.isProcessAutoSendMsg = false;
        this.tempMsgContent = "";
    }

    @Override // java.lang.Object
    public String toString() {
        return "ZhiChiConfig{cids=" + this.cids + ", currentCidPosition=" + this.currentCidPosition + ", queryCidsStatus=" + this.queryCidsStatus + ", isShowUnreadUi=" + this.isShowUnreadUi + ", initModel=" + this.initModel + ", messageList=" + this.messageList + ", current_client_model=" + this.current_client_model + ", activityTitle='" + this.activityTitle + DateFormat.QUOTE + ", customerState=" + this.customerState + ", isAboveZero=" + this.isAboveZero + ", isComment=" + this.isComment + ", remindRobotMessageTimes=" + this.remindRobotMessageTimes + ", adminFace='" + this.adminFace + DateFormat.QUOTE + ", paseReplyTimeCustoms=" + this.paseReplyTimeCustoms + ", customTimeTask=" + this.customTimeTask + ", paseReplyTimeUserInfo=" + this.paseReplyTimeUserInfo + ", userInfoTimeTask=" + this.userInfoTimeTask + ", isChatLock=" + this.isChatLock + ", isNoMoreHistoryMsg=" + this.isNoMoreHistoryMsg + ", showTimeVisiableCustomBtn=" + this.showTimeVisiableCustomBtn + ", currentUserName='" + this.currentUserName + DateFormat.QUOTE + ", queueNum=" + this.queueNum + ", isShowQueueTip=" + this.isShowQueueTip + ", isProcessAutoSendMsg=" + this.isProcessAutoSendMsg + ", bottomViewtype=" + this.bottomViewtype + '}';
    }
}
