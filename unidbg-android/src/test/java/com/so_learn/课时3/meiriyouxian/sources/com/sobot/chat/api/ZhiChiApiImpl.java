package com.sobot.chat.api;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.AsyncTask;
import android.os.Build;
import android.os.storage.StorageManager;
import android.provider.Telephony;
import android.provider.UserDictionary;
import android.telecom.PhoneAccount;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.support.api.push.PushReceiver;
import com.sobot.chat.api.apiUtils.GsonUtil;
import com.sobot.chat.api.apiUtils.HttpUtilsTools;
import com.sobot.chat.api.apiUtils.ZhiChiUrlApi;
import com.sobot.chat.api.model.BaseCode;
import com.sobot.chat.api.model.CommonModel;
import com.sobot.chat.api.model.CommonModelBase;
import com.sobot.chat.api.model.ConsultingContent;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.OrderCardContentModel;
import com.sobot.chat.api.model.PostParamModel;
import com.sobot.chat.api.model.SatisfactionSet;
import com.sobot.chat.api.model.SobotCityResult;
import com.sobot.chat.api.model.SobotCommentParam;
import com.sobot.chat.api.model.SobotConfigModel;
import com.sobot.chat.api.model.SobotConfigResult;
import com.sobot.chat.api.model.SobotConnCusParam;
import com.sobot.chat.api.model.SobotLableInfoList;
import com.sobot.chat.api.model.SobotLeaveMsgConfig;
import com.sobot.chat.api.model.SobotLeaveMsgConfigResult;
import com.sobot.chat.api.model.SobotLeaveMsgParamBaseModel;
import com.sobot.chat.api.model.SobotLeaveMsgParamModel;
import com.sobot.chat.api.model.SobotLeaveReplyModel;
import com.sobot.chat.api.model.SobotLocationModel;
import com.sobot.chat.api.model.SobotMsgCenterModel;
import com.sobot.chat.api.model.SobotPostMsgTemplate;
import com.sobot.chat.api.model.SobotPostMsgTemplateResult;
import com.sobot.chat.api.model.SobotQueryFormModel;
import com.sobot.chat.api.model.SobotQueryFormModelResult;
import com.sobot.chat.api.model.SobotQuestionRecommend;
import com.sobot.chat.api.model.SobotQuestionRecommendResult;
import com.sobot.chat.api.model.SobotRobot;
import com.sobot.chat.api.model.SobotRobotGuess;
import com.sobot.chat.api.model.SobotRobotGuessResult;
import com.sobot.chat.api.model.SobotUserTicketInfo;
import com.sobot.chat.api.model.SobotUserTicketInfoFlag;
import com.sobot.chat.api.model.SobotUserTicketInfoResult;
import com.sobot.chat.api.model.StCategoryModel;
import com.sobot.chat.api.model.StDocModel;
import com.sobot.chat.api.model.StHelpDocModel;
import com.sobot.chat.api.model.StUserDealTicketInfo;
import com.sobot.chat.api.model.StUserDealTicketInfoResult;
import com.sobot.chat.api.model.ZhiChiCidsModel;
import com.sobot.chat.api.model.ZhiChiCidsModelResult;
import com.sobot.chat.api.model.ZhiChiGroup;
import com.sobot.chat.api.model.ZhiChiHistoryMessage;
import com.sobot.chat.api.model.ZhiChiInitModeBase;
import com.sobot.chat.api.model.ZhiChiInitModel;
import com.sobot.chat.api.model.ZhiChiMessage;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.api.model.ZhiChiWorkModel;
import com.sobot.chat.api.model.ZhiChiWorkResult;
import com.sobot.chat.core.a;
import com.sobot.chat.core.channel.SobotTCPServer;
import com.sobot.chat.core.http.c.a;
import com.sobot.chat.utils.ab;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.s;
import com.sobot.chat.utils.w;
import com.sobot.chat.utils.x;
import com.taobao.accs.utl.BaseMonitor;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.unionpay.tsmservice.data.Constant;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Response;
import org.json.JSONObject;

public class ZhiChiApiImpl implements ZhiChiApi {
    private static final String a = (ZhiChiApiImpl.class.getSimpleName() + "");
    private Context b;
    private String c = "2";
    private String d = ZhiChiUrlApi.VERSION;

    private ZhiChiApiImpl() {
    }

    public ZhiChiApiImpl(Context context) {
        this.b = context;
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void sobotInit(Object obj, Information information, a<ZhiChiInitModeBase> aVar) {
        s.a(this.b, "sobot_platform_uid", information.getPartnerid());
        HashMap hashMap = new HashMap();
        String stackTraceString = Log.getStackTraceString(new Throwable());
        hashMap.put("partnerId", information.getPartnerid());
        hashMap.put("way", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        hashMap.put("from", this.c);
        hashMap.put("version", this.d);
        hashMap.put(BaseMonitor.COUNT_ACK, "1");
        hashMap.put("appId", information.getApp_key());
        hashMap.put(StorageManager.UUID_SYSTEM, "android" + Build.VERSION.RELEASE);
        hashMap.put("appVersion", d.h(this.b) + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + d.g(this.b));
        hashMap.put("phoneModel", Build.MANUFACTURER + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + Build.MODEL);
        hashMap.put(UserDictionary.Words.LOCALE, information.getLocale());
        if (!TextUtils.isEmpty(information.getCustomer_fields())) {
            hashMap.put("customerFields", information.getCustomer_fields());
        }
        if (information.getService_mode() >= 1 && information.getService_mode() <= 4) {
            hashMap.put("joinType", information.getService_mode() + "");
        }
        if (!TextUtils.isEmpty(information.getParams())) {
            hashMap.put(Constant.KEY_PARAMS, information.getParams());
        }
        if (!TextUtils.isEmpty(information.getSummary_params())) {
            hashMap.put("summaryParams", information.getSummary_params());
        }
        if (!TextUtils.isEmpty(information.getRobotCode())) {
            hashMap.put("robotFlag", information.getRobotCode());
        }
        if (!TextUtils.isEmpty(information.getGroupid())) {
            hashMap.put("groupId", information.getGroupid());
        }
        if (!TextUtils.isEmpty(information.getUser_nick())) {
            hashMap.put("uname", information.getUser_nick());
        }
        if (!TextUtils.isEmpty(information.getUser_tels())) {
            hashMap.put(PhoneAccount.SCHEME_TEL, information.getUser_tels());
        }
        if (!TextUtils.isEmpty(information.getUser_emails())) {
            hashMap.put("email", information.getUser_emails());
        }
        if (!TextUtils.isEmpty(information.getQq())) {
            hashMap.put("qq", information.getQq());
        }
        if (!TextUtils.isEmpty(information.getRemark())) {
            hashMap.put("remark", information.getRemark());
        }
        if (!TextUtils.isEmpty(information.getFace())) {
            hashMap.put("face", information.getFace());
        }
        if (!TextUtils.isEmpty(information.getUser_name())) {
            hashMap.put("realname", information.getUser_name());
        }
        if (!TextUtils.isEmpty(information.getVisit_title())) {
            hashMap.put("visitTitle", information.getVisit_title());
        }
        if (!TextUtils.isEmpty(information.getVisit_url())) {
            hashMap.put("visitUrl", information.getVisit_url());
        }
        if (!TextUtils.isEmpty(information.getEquipmentId())) {
            hashMap.put("equipmentId", information.getEquipmentId());
        }
        if (!TextUtils.isEmpty(information.getChoose_adminid())) {
            hashMap.put("chooseAdminId", information.getChoose_adminid());
        }
        if (!TextUtils.isEmpty(information.getMulti_params())) {
            hashMap.put("multiParams", information.getMulti_params());
        }
        if (!TextUtils.isEmpty(information.getIsVip())) {
            hashMap.put("isVip", information.getIsVip());
        }
        if (!TextUtils.isEmpty(information.getVip_level())) {
            hashMap.put("vipLevel", information.getVip_level());
        }
        if (!TextUtils.isEmpty(information.getUser_label())) {
            hashMap.put("userLabel", information.getUser_label());
        }
        if (!TextUtils.isEmpty(information.getRobot_alias())) {
            hashMap.put("robotAlias", information.getRobot_alias());
        }
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.api_robot_chat_init, hashMap, new AnonymousClass1(hashMap, stackTraceString, aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$1  reason: invalid class name */
    class AnonymousClass1 implements a.b {
        final /* synthetic */ Map a;
        final /* synthetic */ String b;
        final /* synthetic */ com.sobot.chat.core.http.c.a c;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass1(Map map, String str, com.sobot.chat.core.http.c.a aVar) {
            this.a = map;
            this.b = str;
            this.c = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            HashMap hashMap = new HashMap();
            hashMap.put("\u63a5\u53e3\u5f02\u5e38", "\u8bf7\u6c42url-->" + ZhiChiUrlApi.api_robot_chat_init + "  \u8bf7\u6c42\u53c2\u6570-->" + this.a + "  \u8bf7\u6c42\u5f02\u5e38\u4fe1\u606f: --> " + str + "------" + exc.getMessage() + "\u8c03\u7528\u8fc7\u7a0b -->" + this.b);
            m.a(ZhiChiApiImpl.this.b, hashMap, "\u8bf7\u6c42\u5f02\u5e38");
            com.sobot.chat.core.channel.a.a(ZhiChiApiImpl.this.b).a().logCollect(ZhiChiApiImpl.this.b, s.b(ZhiChiApiImpl.this.b, ""));
            this.c.a(exc, "\u7f51\u7edc\u9519\u8bef");
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            ZhiChiInitModel jsonToZhiChiInitModel = GsonUtil.jsonToZhiChiInitModel(str);
            if (jsonToZhiChiInitModel == null || TextUtils.isEmpty(jsonToZhiChiInitModel.getCode()) || 1 != Integer.parseInt(jsonToZhiChiInitModel.getCode())) {
                HashMap hashMap = new HashMap();
                hashMap.put("\u63a5\u53e3\u5931\u8d25", "  \u8bf7\u6c42url-->" + ZhiChiUrlApi.api_robot_chat_init + "  \u8bf7\u6c42\u53c2\u6570-->" + this.a + "  \u8bf7\u6c42\u7ed3\u679c: --> " + str + "\u8c03\u7528\u8fc7\u7a0b -->" + this.b);
                m.a(ZhiChiApiImpl.this.b, hashMap, "\u8bf7\u6c42\u5931\u8d25");
                com.sobot.chat.core.channel.a.a(ZhiChiApiImpl.this.b).a().logCollect(ZhiChiApiImpl.this.b, s.b(ZhiChiApiImpl.this.b, ""));
                this.c.a(new IllegalArgumentException(), "appkey\u9519\u8bef\uff01");
            } else if (jsonToZhiChiInitModel.getData() != null) {
                this.c.a(jsonToZhiChiInitModel.getData());
            }
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void connnect(Object obj, SobotConnCusParam sobotConnCusParam, com.sobot.chat.core.http.c.a<ZhiChiMessageBase> aVar) {
        HashMap hashMap = new HashMap();
        String stackTraceString = Log.getStackTraceString(new Throwable());
        hashMap.put("uid", sobotConnCusParam.getPartnerid());
        hashMap.put("cid", sobotConnCusParam.getCid());
        hashMap.put("from", this.c);
        hashMap.put("version", this.d);
        hashMap.put("groupId", sobotConnCusParam.getGroupId());
        hashMap.put("groupName", sobotConnCusParam.getGroupName());
        hashMap.put("chooseAdminId", sobotConnCusParam.getChooseAdminId());
        hashMap.put("tranFlag", sobotConnCusParam.getTran_flag() + "");
        hashMap.put(Telephony.Carriers.CURRENT, sobotConnCusParam.isCurrentFlag() + "");
        hashMap.put("keyword", sobotConnCusParam.getKeyword());
        hashMap.put("keywordId", sobotConnCusParam.getKeywordId());
        hashMap.put("summaryParams", sobotConnCusParam.getSummary_params());
        hashMap.put("offlineMsgAdminId", sobotConnCusParam.getOfflineMsgAdminId());
        hashMap.put("isOfflineMsgConnect", sobotConnCusParam.getOfflineMsgConnectFlag() + "");
        if (sobotConnCusParam.getTransferType() == 1 || sobotConnCusParam.getTransferType() == 2) {
            hashMap.put("transferType", sobotConnCusParam.getTransferType() + "");
        }
        if (!TextUtils.isEmpty(sobotConnCusParam.getTransferAction())) {
            hashMap.put("transferAction", sobotConnCusParam.getTransferAction());
        }
        if (sobotConnCusParam.is_queue_first()) {
            hashMap.put("queueFirst", "1");
        }
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.api_transfer_people, hashMap, new AnonymousClass12(aVar, hashMap, stackTraceString));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$12  reason: invalid class name */
    class AnonymousClass12 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;
        final /* synthetic */ Map b;
        final /* synthetic */ String c;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass12(com.sobot.chat.core.http.c.a aVar, Map map, String str) {
            this.a = aVar;
            this.b = map;
            this.c = str;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("\u8f6c\u4eba\u5de5\u8fd4\u56de\u503c---\uff1a" + str);
            if (!TextUtils.isEmpty(str)) {
                ZhiChiMessage jsonToZhiChiMessage = GsonUtil.jsonToZhiChiMessage(str);
                if (jsonToZhiChiMessage == null || jsonToZhiChiMessage.getData() == null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("\u63a5\u53e3\u5931\u8d25", "  \u8bf7\u6c42url-->" + ZhiChiUrlApi.api_transfer_people + "  \u8bf7\u6c42\u53c2\u6570-->" + this.b + "  \u8bf7\u6c42\u7ed3\u679c: --> " + str + "\u8c03\u7528\u8fc7\u7a0b -->" + this.c);
                    m.a(ZhiChiApiImpl.this.b, hashMap, "\u8bf7\u6c42\u5931\u8d25");
                    this.a.a(new IllegalStateException(), "");
                    return;
                }
                if (!TextUtils.isEmpty(jsonToZhiChiMessage.getMsg())) {
                    jsonToZhiChiMessage.getData().setMsg(jsonToZhiChiMessage.getMsg());
                }
                this.a.a(jsonToZhiChiMessage.getData());
                return;
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("\u63a5\u53e3\u5931\u8d25", "  \u8bf7\u6c42url-->" + ZhiChiUrlApi.api_transfer_people + "  \u8bf7\u6c42\u53c2\u6570-->" + this.b + "  \u8bf7\u6c42\u7ed3\u679c: --> " + str + "\u8c03\u7528\u8fc7\u7a0b -->" + this.c);
            m.a(ZhiChiApiImpl.this.b, hashMap2, "\u8bf7\u6c42\u5931\u8d25");
            this.a.a(new IllegalStateException(), "");
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            HashMap hashMap = new HashMap();
            hashMap.put("\u63a5\u53e3\u5f02\u5e38", "\u8bf7\u6c42url-->" + ZhiChiUrlApi.api_transfer_people + "  \u8bf7\u6c42\u53c2\u6570-->" + this.b + "  \u8bf7\u6c42\u5f02\u5e38\u4fe1\u606f: --> " + str + "------" + exc.getMessage() + "\u8c03\u7528\u8fc7\u7a0b -->" + this.c);
            m.a(ZhiChiApiImpl.this.b, hashMap, "\u8bf7\u6c42\u5f02\u5e38");
            StringBuilder sb = new StringBuilder();
            sb.append(ZhiChiApiImpl.a);
            sb.append(str);
            m.a(sb.toString(), exc);
            this.a.a(exc, "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e");
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void disconnChannel() {
        d.a(this.b, new Intent("sobot_chat_disconnchannel"));
        Context context = this.b;
        context.stopService(new Intent(context, SobotTCPServer.class));
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void connChannel(String str, String str2, String str3, String str4, String str5, String str6) {
        m.d("connChannel wslinkBak= " + str + "wslinkDefault=" + str2 + "uid=" + str3 + "puid=" + str4 + "appkey=" + str5 + "wayHttp=" + str6);
        if (this.b != null && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str5)) {
            s.b(this.b, "sobot_platform_unioncode", "");
            Intent intent = new Intent(this.b, SobotTCPServer.class);
            intent.putExtra("sobot_wslinkbak_chat", str);
            intent.putExtra("sobot_wslinkdefault_chat", str2);
            intent.putExtra("sobot_uid_chat", str3);
            intent.putExtra("sobot_puid_chat", str4);
            intent.putExtra("sobot_appkey_chat", str5);
            intent.putExtra("sobot_wayhttp_chat", str6);
            ab.a(this.b, intent);
            s.a(this.b, "sobot_wslinkbak_chat", str);
            s.a(this.b, "sobot_wslinkdefault_chat", str2);
            s.a(this.b, "sobot_uid_chat", str3);
            s.a(this.b, "sobot_puid_chat", str4);
            s.a(this.b, "sobot_appkey_chat", str5);
            s.a(this.b, "sobot_wayhttp_chat", str6);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void reconnectChannel() {
        connChannel(s.b(this.b, "sobot_wslinkbak_chat", ""), s.b(this.b, "sobot_wslinkdefault_chat", ""), s.b(this.b, "sobot_uid_chat", ""), s.b(this.b, "sobot_puid_chat", ""), s.b(this.b, "sobot_appkey_chat", ""), s.b(this.b, "sobot_wayhttp_chat", ""));
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void chatSendMsgToRoot(String str, String str2, int i, String str3, String str4, String str5, com.sobot.chat.core.http.c.a<ZhiChiMessageBase> aVar) {
        String stackTraceString = Log.getStackTraceString(new Throwable());
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str3)) {
            str3 = str2;
        }
        if (i == 1) {
            hashMap.put("requestText", str2);
            hashMap.put("question", str3);
        } else {
            hashMap.put("requestText", str3);
            hashMap.put("question", str2);
        }
        hashMap.put("questionFlag", i + "");
        hashMap.put("uid", str4);
        hashMap.put("cid", str5);
        hashMap.put("from", this.c);
        hashMap.put("version", this.d);
        hashMap.put("robotFlag", str);
        HttpUtilsTools.doPost("sobot_global_request_cancel_tag", ZhiChiUrlApi.api_robot_chat_sendMessage, hashMap, new AnonymousClass23(aVar, hashMap, stackTraceString));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$23  reason: invalid class name */
    class AnonymousClass23 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;
        final /* synthetic */ Map b;
        final /* synthetic */ String c;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass23(com.sobot.chat.core.http.c.a aVar, Map map, String str) {
            this.a = aVar;
            this.b = map;
            this.c = str;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            ZhiChiMessage jsonToZhiChiMessage = GsonUtil.jsonToZhiChiMessage(str);
            if (jsonToZhiChiMessage == null || TextUtils.isEmpty(jsonToZhiChiMessage.getCode()) || 1 != Integer.parseInt(jsonToZhiChiMessage.getCode()) || jsonToZhiChiMessage.getData() == null) {
                HashMap hashMap = new HashMap();
                hashMap.put("\u63a5\u53e3\u5931\u8d25", "  \u8bf7\u6c42url-->" + ZhiChiUrlApi.api_robot_chat_sendMessage + "  \u8bf7\u6c42\u53c2\u6570-->" + this.b + "  \u8bf7\u6c42\u7ed3\u679c: --> " + str + "\u8c03\u7528\u8fc7\u7a0b -->" + this.c);
                m.a(ZhiChiApiImpl.this.b, hashMap, "\u8bf7\u6c42\u5931\u8d25");
                this.a.a(new Exception(), "\u670d\u52a1\u5668\u9519\u8bef");
                return;
            }
            this.a.a(jsonToZhiChiMessage.getData());
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            HashMap hashMap = new HashMap();
            hashMap.put("\u63a5\u53e3\u5f02\u5e38", "\u8bf7\u6c42url-->" + ZhiChiUrlApi.api_robot_chat_sendMessage + "  \u8bf7\u6c42\u53c2\u6570-->" + this.b + "  \u8bf7\u6c42\u5f02\u5e38\u4fe1\u606f: --> " + str + "------" + exc.getMessage() + "\u8c03\u7528\u8fc7\u7a0b -->" + this.c);
            m.a(ZhiChiApiImpl.this.b, hashMap, "\u8bf7\u6c42\u5f02\u5e38");
            this.a.a(exc, str);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void sendMsgToCoutom(String str, String str2, String str3, com.sobot.chat.core.http.c.a<CommonModelBase> aVar) {
        HashMap hashMap = new HashMap();
        String stackTraceString = Log.getStackTraceString(new Throwable());
        hashMap.put("content", str);
        hashMap.put("uid", str2);
        hashMap.put("cid", str3);
        hashMap.put("from", this.c);
        hashMap.put("version", this.d);
        HttpUtilsTools.doPost("sobot_global_request_cancel_tag", ZhiChiUrlApi.api_sendmessage_to_customService, hashMap, new AnonymousClass34(aVar, hashMap, stackTraceString));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$34  reason: invalid class name */
    class AnonymousClass34 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;
        final /* synthetic */ Map b;
        final /* synthetic */ String c;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass34(com.sobot.chat.core.http.c.a aVar, Map map, String str) {
            this.a = aVar;
            this.b = map;
            this.c = str;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("\u8fd4\u56de\u503c--\uff1a" + str);
            CommonModel jsonToCommonModel = GsonUtil.jsonToCommonModel(str);
            if (jsonToCommonModel == null || 1 != Integer.parseInt(jsonToCommonModel.getCode()) || jsonToCommonModel.getData() == null) {
                HashMap hashMap = new HashMap();
                hashMap.put("\u63a5\u53e3\u5931\u8d25", "  \u8bf7\u6c42url-->" + ZhiChiUrlApi.api_sendmessage_to_customService + "  \u8bf7\u6c42\u53c2\u6570-->" + this.b + "  \u8bf7\u6c42\u7ed3\u679c: --> " + str + "\u8c03\u7528\u8fc7\u7a0b -->" + this.c);
                m.a(ZhiChiApiImpl.this.b, hashMap, "\u8bf7\u6c42\u5931\u8d25");
                this.a.a(new Exception(), "\u670d\u52a1\u5668\u9519\u8bef");
                return;
            }
            this.a.a(jsonToCommonModel.getData());
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            HashMap hashMap = new HashMap();
            hashMap.put("\u63a5\u53e3\u5f02\u5e38", "\u8bf7\u6c42url-->" + ZhiChiUrlApi.api_sendmessage_to_customService + "  \u8bf7\u6c42\u53c2\u6570-->" + this.b + "  \u8bf7\u6c42\u5f02\u5e38\u4fe1\u606f: --> " + str + "------" + exc.getMessage() + "\u8c03\u7528\u8fc7\u7a0b -->" + this.c);
            m.a(ZhiChiApiImpl.this.b, hashMap, "\u8bf7\u6c42\u5f02\u5e38");
            StringBuilder sb = new StringBuilder();
            sb.append(ZhiChiApiImpl.a);
            sb.append(str);
            m.a(sb.toString(), exc);
            this.a.a(exc, str);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void sendCardMsg(ConsultingContent consultingContent, String str, String str2, com.sobot.chat.core.http.c.a<CommonModelBase> aVar) {
        if (consultingContent != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("content", consultingContent.toString());
            hashMap.put("uid", str);
            hashMap.put("cid", str2);
            hashMap.put(PushReceiver.PushMessageThread.MSGTYPE, Constants.VIA_REPORT_TYPE_CHAT_AIO);
            hashMap.put("from", this.c);
            hashMap.put("version", this.d);
            HttpUtilsTools.doPost("sobot_global_request_cancel_tag", ZhiChiUrlApi.api_sendmessage_to_customService, hashMap, new AnonymousClass44(aVar));
        }
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$44  reason: invalid class name */
    class AnonymousClass44 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass44(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("\u8fd4\u56de\u503c--\uff1a" + str);
            CommonModel jsonToCommonModel = GsonUtil.jsonToCommonModel(str);
            if (jsonToCommonModel == null || 1 != Integer.parseInt(jsonToCommonModel.getCode()) || jsonToCommonModel.getData() == null) {
                this.a.a(new Exception(), "\u670d\u52a1\u5668\u9519\u8bef");
            } else {
                this.a.a(jsonToCommonModel.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, str);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void sendOrderCardMsg(OrderCardContentModel orderCardContentModel, String str, String str2, com.sobot.chat.core.http.c.a<CommonModelBase> aVar) {
        if (orderCardContentModel != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("content", x.b(orderCardContentModel));
            hashMap.put("uid", str);
            hashMap.put("cid", str2);
            hashMap.put(PushReceiver.PushMessageThread.MSGTYPE, Constants.VIA_REPORT_TYPE_CHAT_AUDIO);
            hashMap.put("from", this.c);
            hashMap.put("version", this.d);
            HttpUtilsTools.doPost("sobot_global_request_cancel_tag", ZhiChiUrlApi.api_sendmessage_to_customService, hashMap, new AnonymousClass45(aVar));
        }
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$45  reason: invalid class name */
    class AnonymousClass45 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass45(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("\u8fd4\u56de\u503c--\uff1a" + str);
            CommonModel jsonToCommonModel = GsonUtil.jsonToCommonModel(str);
            if (jsonToCommonModel == null || 1 != Integer.parseInt(jsonToCommonModel.getCode()) || jsonToCommonModel.getData() == null) {
                this.a.a(new Exception(), "\u670d\u52a1\u5668\u9519\u8bef");
            } else {
                this.a.a(jsonToCommonModel.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, str);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void sendFile(String str, String str2, String str3, String str4, ResultCallBack<ZhiChiMessage> resultCallBack) {
        HashMap hashMap = new HashMap();
        hashMap.put("uid", str2);
        hashMap.put("cid", str);
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put("duration", str4);
        }
        hashMap.put("from", this.c);
        hashMap.put("version", this.d);
        HttpUtilsTools.uploadFile("sobot_global_request_cancel_tag", ZhiChiUrlApi.api_sendFile_to_customeService, hashMap, str3, new AnonymousClass46(resultCallBack, new File(str3).getTotalSpace()));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$46  reason: invalid class name */
    class AnonymousClass46 implements a.b {
        final /* synthetic */ ResultCallBack a;
        final /* synthetic */ long b;

        AnonymousClass46(ResultCallBack resultCallBack, long j) {
            this.a = resultCallBack;
            this.b = j;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("sendFile---" + str);
            ZhiChiMessage jsonToZhiChiMessage = GsonUtil.jsonToZhiChiMessage(str);
            if (jsonToZhiChiMessage == null || 1 != Integer.parseInt(jsonToZhiChiMessage.getCode())) {
                this.a.onFailure(new Exception(), "\u670d\u52a1\u5668\u9519\u8bef");
            } else {
                this.a.onSuccess(jsonToZhiChiMessage);
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.onFailure(exc, str);
        }

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
            this.a.onLoading(this.b, (long) i, true);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void comment(Object obj, String str, String str2, SobotCommentParam sobotCommentParam, com.sobot.chat.core.http.c.a<CommonModel> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("cid", str);
        hashMap.put("userId", str2);
        hashMap.put("type", sobotCommentParam.getType());
        hashMap.put("problem", sobotCommentParam.getProblem());
        hashMap.put("suggest", sobotCommentParam.getSuggest());
        hashMap.put("isresolve", sobotCommentParam.getIsresolve() + "");
        hashMap.put("commentType", sobotCommentParam.getCommentType() + "");
        if (!TextUtils.isEmpty(sobotCommentParam.getRobotFlag())) {
            hashMap.put("robotFlag", sobotCommentParam.getRobotFlag());
        }
        if (!TextUtils.isEmpty(sobotCommentParam.getScore())) {
            hashMap.put("source", sobotCommentParam.getScore());
        }
        hashMap.put("from", this.c);
        hashMap.put("version", this.d);
        HttpUtilsTools.doPost(ZhiChiUrlApi.api_chat_comment, hashMap, new AnonymousClass47(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$47  reason: invalid class name */
    class AnonymousClass47 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass47(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("comment----" + str);
            CommonModel jsonToCommonModel = GsonUtil.jsonToCommonModel(str);
            if (jsonToCommonModel != null && jsonToCommonModel.getData() != null && "1".equals(jsonToCommonModel.getCode())) {
                if ("1".equals(jsonToCommonModel.getData().getStatus()) || "2".equals(jsonToCommonModel.getData().getStatus())) {
                    this.a.a(jsonToCommonModel);
                }
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void out(String str, String str2, com.sobot.chat.core.http.c.a<CommonModel> aVar) {
        HashMap hashMap = new HashMap();
        String stackTraceString = Log.getStackTraceString(new Throwable());
        hashMap.put("uid", str2);
        hashMap.put("cid", str);
        hashMap.put("from", this.c);
        hashMap.put("version", this.d);
        HttpUtilsTools.doPost(ZhiChiUrlApi.api_login_out, hashMap, new AnonymousClass48(aVar, hashMap, stackTraceString));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$48  reason: invalid class name */
    class AnonymousClass48 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;
        final /* synthetic */ Map b;
        final /* synthetic */ String c;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass48(com.sobot.chat.core.http.c.a aVar, Map map, String str) {
            this.a = aVar;
            this.b = map;
            this.c = str;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            CommonModel jsonToCommonModel = GsonUtil.jsonToCommonModel(str);
            if (jsonToCommonModel != null && jsonToCommonModel.getData() != null) {
                this.a.a(jsonToCommonModel);
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            HashMap hashMap = new HashMap();
            hashMap.put("\u63a5\u53e3\u5f02\u5e38", "\u8bf7\u6c42url-->" + ZhiChiUrlApi.api_login_out + "  \u8bf7\u6c42\u53c2\u6570-->" + this.b + "  \u8bf7\u6c42\u5f02\u5e38\u4fe1\u606f: --> " + str + "------" + exc.getMessage() + "\u8c03\u7528\u8fc7\u7a0b -->" + this.c);
            m.a(ZhiChiApiImpl.this.b, hashMap, "\u8bf7\u6c42\u5f02\u5e38");
            StringBuilder sb = new StringBuilder();
            sb.append(ZhiChiApiImpl.a);
            sb.append(str);
            m.a(sb.toString(), exc);
            this.a.a(exc, str);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void getGroupList(Object obj, String str, String str2, com.sobot.chat.core.http.c.a<ZhiChiGroup> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("appId", str);
        hashMap.put("userId", str2);
        hashMap.put("source", "2");
        hashMap.put("from", this.c);
        hashMap.put("version", this.d);
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.api_group_list, hashMap, new AnonymousClass2(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$2  reason: invalid class name */
    class AnonymousClass2 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass2(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("getGroupList" + str);
            ZhiChiGroup jsonToZhiChiGroup = GsonUtil.jsonToZhiChiGroup(str);
            if (jsonToZhiChiGroup != null) {
                this.a.a(jsonToZhiChiGroup);
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e");
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void postMsg(Object obj, PostParamModel postParamModel, com.sobot.chat.core.http.c.a<CommonModelBase> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("templateId", postParamModel.getTemplateId());
        hashMap.put("uid", postParamModel.getUid());
        hashMap.put("partnerId", postParamModel.getPartnerId());
        hashMap.put("ticketContent", postParamModel.getTicketContent());
        hashMap.put("customerEmail", postParamModel.getCustomerEmail());
        hashMap.put("customerPhone", postParamModel.getCustomerPhone());
        hashMap.put("ticketTitle", postParamModel.getTicketTitle());
        hashMap.put("companyId", postParamModel.getCompanyId());
        hashMap.put("fileStr", postParamModel.getFileStr());
        hashMap.put("ticketTypeId", postParamModel.getTicketTypeId());
        hashMap.put("groupId", postParamModel.getGroupId());
        hashMap.put("extendFields", postParamModel.getExtendFields());
        hashMap.put("paramsExtends", postParamModel.getParamsExtends());
        hashMap.put("ticketFrom", "4");
        hashMap.put("customerSource", "4");
        hashMap.put("from", this.c);
        hashMap.put("version", this.d);
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.api_post_msg, hashMap, new AnonymousClass3(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$3  reason: invalid class name */
    class AnonymousClass3 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass3(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("postMsg-----" + str);
            CommonModel jsonToCommonModel = GsonUtil.jsonToCommonModel(str);
            if (jsonToCommonModel != null && jsonToCommonModel.getData() != null && "1".equals(jsonToCommonModel.getCode())) {
                this.a.a(jsonToCommonModel.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, str);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void input(String str, String str2, com.sobot.chat.core.http.c.a<CommonModel> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("uid", str);
        hashMap.put("content", str2);
        HttpUtilsTools.doPost("sobot_global_request_cancel_tag", ZhiChiUrlApi.api_input, hashMap, new AnonymousClass4(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$4  reason: invalid class name */
    class AnonymousClass4 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass4(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("input---" + str);
            CommonModel jsonToCommonModel = GsonUtil.jsonToCommonModel(str);
            if (!(jsonToCommonModel == null || jsonToCommonModel.getData() == null)) {
                m.d(ZhiChiApiImpl.a + "input" + jsonToCommonModel.toString());
            }
            this.a.a(jsonToCommonModel);
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, str);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void deleteHisMsg(Object obj, String str, com.sobot.chat.core.http.c.a<CommonModelBase> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("uid", str);
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.api_delete_history_msg, hashMap, new AnonymousClass5(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$5  reason: invalid class name */
    class AnonymousClass5 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass5(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("deleteHisMsg---" + str);
            CommonModel jsonToCommonModel = GsonUtil.jsonToCommonModel(str);
            if (jsonToCommonModel != null && jsonToCommonModel.getData() != null) {
                this.a.a(jsonToCommonModel.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            this.a.a(exc, str);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void robotGuide(Object obj, String str, String str2, int i, com.sobot.chat.core.http.c.a<ZhiChiMessageBase> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("uid", str);
        hashMap.put("robotFlag", str2);
        hashMap.put("faqId", i + "");
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.api_robot_guide, hashMap, new AnonymousClass6(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$6  reason: invalid class name */
    class AnonymousClass6 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
        }

        AnonymousClass6(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("robotGuide-----------:" + str);
            ZhiChiMessage jsonToZhiChiMessage = GsonUtil.jsonToZhiChiMessage(str);
            if (jsonToZhiChiMessage != null && jsonToZhiChiMessage.getData() != null) {
                this.a.a(jsonToZhiChiMessage.getData());
            }
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void queryCids(Object obj, String str, long j, com.sobot.chat.core.http.c.a<ZhiChiCidsModel> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("uid", str);
        hashMap.put("time", j + "");
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.api_queryUserCids, hashMap, new AnonymousClass7(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$7  reason: invalid class name */
    class AnonymousClass7 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass7(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("queryCids---" + str);
            ZhiChiCidsModelResult jsonToZhiChiCidsModel = GsonUtil.jsonToZhiChiCidsModel(str);
            if (jsonToZhiChiCidsModel == null || !"1".equals(jsonToZhiChiCidsModel.getCode()) || jsonToZhiChiCidsModel.getData() == null) {
                this.a.a(new Exception(), "\u670d\u52a1\u5668\u9519\u8bef");
            } else {
                this.a.a(jsonToZhiChiCidsModel.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, str);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void getChatDetailByCid(Object obj, String str, String str2, com.sobot.chat.core.http.c.a<ZhiChiHistoryMessage> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("uid", str);
        hashMap.put("cid", str2);
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.api_robot_chat_historyMessage_cid, hashMap, new AnonymousClass8(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$8  reason: invalid class name */
    class AnonymousClass8 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass8(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            ZhiChiHistoryMessage jsonToZhiChiHistoryMessage = GsonUtil.jsonToZhiChiHistoryMessage(str);
            if (jsonToZhiChiHistoryMessage == null || !"1".equals(jsonToZhiChiHistoryMessage.getCode())) {
                this.a.a(new Exception(), "\u670d\u52a1\u5668\u9519\u8bef");
            } else {
                this.a.a(jsonToZhiChiHistoryMessage);
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, str);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public synchronized void logCollect(Context context, String str) {
        if (s.b(context, "sobot_config_req_collectFlag", false) && !TextUtils.isEmpty(str)) {
            try {
                new AnonymousClass9(str, context).execute(new Void[0]);
            } catch (Exception unused) {
                m.a();
            }
        }
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$9  reason: invalid class name */
    class AnonymousClass9 extends AsyncTask<Void, Void, String> {
        final /* synthetic */ String a;
        final /* synthetic */ Context b;

        AnonymousClass9(String str, Context context) {
            this.a = str;
            this.b = context;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public String doInBackground(Void... voidArr) {
            try {
                return m.b();
            } catch (Exception unused) {
                m.a();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            try {
                if (!TextUtils.isEmpty(str)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(com.taobao.accs.common.Constants.KEY_APP_KEY, this.a);
                    hashMap.put("appVersion", d.g(this.b));
                    hashMap.put("items", str);
                    hashMap.put(com.taobao.accs.common.Constants.KEY_SDK_VERSION, "sobot_sdk_v2.9.5");
                    hashMap.put("mobilemodels", Build.MODEL);
                    hashMap.put("systemVersion", Build.VERSION.SDK_INT + "");
                    hashMap.put("from", "2");
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("data", GsonUtil.map2Str(hashMap));
                    HttpUtilsTools.doPost(ZhiChiUrlApi.api_collect, hashMap2, new AnonymousClass1());
                }
            } catch (Exception unused) {
                m.a();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$9$1  reason: invalid class name */
        public class AnonymousClass1 implements a.b {
            @Override // com.sobot.chat.core.a.b
            public void a(int i) {
            }

            @Override // com.sobot.chat.core.a.b
            public void a(Exception exc, String str, int i) {
            }

            AnonymousClass1() {
            }

            @Override // com.sobot.chat.core.a.b
            public void a(String str) {
                m.a();
            }
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void rbAnswerComment(Object obj, String str, String str2, String str3, String str4, String str5, String str6, boolean z, com.sobot.chat.core.http.c.a<CommonModelBase> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("msgId", str);
        hashMap.put("uid", str2);
        hashMap.put("cid", str3);
        hashMap.put("robotFlag", str4);
        hashMap.put("docId", str5);
        hashMap.put("docName", str6);
        hashMap.put("status", z ? "1" : "-1");
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.api_rbAnswerComment, hashMap, new AnonymousClass10(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$10  reason: invalid class name */
    class AnonymousClass10 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass10(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("rbAnswerComment-----" + str);
            CommonModel jsonToCommonModel = GsonUtil.jsonToCommonModel(str);
            if (jsonToCommonModel == null || !"1".equals(jsonToCommonModel.getCode()) || jsonToCommonModel.getData() == null) {
                this.a.a(new Exception(), "\u670d\u52a1\u5668\u9519\u8bef");
            } else {
                this.a.a(jsonToCommonModel.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, str);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void fileUploadForPostMsg(Object obj, String str, String str2, String str3, ResultCallBack<ZhiChiMessage> resultCallBack) {
        HashMap hashMap = new HashMap();
        hashMap.put("companyId", str);
        hashMap.put("uid", str2);
        HttpUtilsTools.uploadFile(obj, ZhiChiUrlApi.api_fileUploadForPostMsg, hashMap, str3, new AnonymousClass11(resultCallBack, new File(str3).getTotalSpace()));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$11  reason: invalid class name */
    class AnonymousClass11 implements a.b {
        final /* synthetic */ ResultCallBack a;
        final /* synthetic */ long b;

        AnonymousClass11(ResultCallBack resultCallBack, long j) {
            this.a = resultCallBack;
            this.b = j;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("sendFile---" + str);
            ZhiChiMessage jsonToZhiChiMessage = GsonUtil.jsonToZhiChiMessage(str);
            if (jsonToZhiChiMessage == null) {
                this.a.onFailure(new Exception(), "\u670d\u52a1\u5668\u9519\u8bef");
            } else if (1 == Integer.parseInt(jsonToZhiChiMessage.getCode())) {
                this.a.onSuccess(jsonToZhiChiMessage);
            } else {
                this.a.onFailure(new Exception(), "\u6587\u4ef6\u4e0d\u80fd\u5927\u4e8e20M");
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.onFailure(exc, "\u7f51\u7edc\u9519\u8bef");
        }

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
            this.a.onLoading(this.b, (long) i, true);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void satisfactionMessage(Object obj, String str, ResultCallBack<SatisfactionSet> resultCallBack) {
        HashMap hashMap = new HashMap();
        hashMap.put("uid", str);
        HttpUtilsTools.doPost(ZhiChiUrlApi.api_satisfactionMessage, hashMap, new AnonymousClass13(resultCallBack));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$13  reason: invalid class name */
    class AnonymousClass13 implements a.b {
        final /* synthetic */ ResultCallBack a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass13(ResultCallBack resultCallBack) {
            this.a = resultCallBack;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("\u8bf7\u6c42\u6210\u529f---" + str);
            SatisfactionSet jsonToSatisfactionSet = GsonUtil.jsonToSatisfactionSet(str);
            if (jsonToSatisfactionSet != null && !TextUtils.isEmpty(jsonToSatisfactionSet.getCode()) && "1".equals(jsonToSatisfactionSet.getCode()) && jsonToSatisfactionSet.getData() != null) {
                this.a.onSuccess(jsonToSatisfactionSet);
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(str, exc);
            this.a.onFailure(exc, "\u7f51\u7edc\u9519\u8bef");
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void isWork(String str, String str2, com.sobot.chat.core.http.c.a<ZhiChiWorkModel> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("appId", str);
        hashMap.put("groupId", str2);
        HttpUtilsTools.doPost(ZhiChiUrlApi.api_is_work, hashMap, new AnonymousClass14(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$14  reason: invalid class name */
    class AnonymousClass14 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass14(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("isWork---" + str);
            ZhiChiWorkResult jsonToZhiChiWorkResult = GsonUtil.jsonToZhiChiWorkResult(str);
            if (jsonToZhiChiWorkResult == null || !"1".equals(jsonToZhiChiWorkResult.getCode()) || jsonToZhiChiWorkResult.getData() == null) {
                this.a.a(null);
            } else {
                this.a.a(jsonToZhiChiWorkResult.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, str);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void sendVoiceToRobot(String str, String str2, String str3, String str4, String str5, ResultCallBack<ZhiChiMessage> resultCallBack) {
        HashMap hashMap = new HashMap();
        hashMap.put("uid", str2);
        hashMap.put("cid", str3);
        hashMap.put("robotFlag", str4);
        hashMap.put("duration", str5);
        m.d("map" + hashMap.toString());
        HttpUtilsTools.uploadFile("sobot_global_request_cancel_tag", ZhiChiUrlApi.api_sendVoiceToRobot, hashMap, str, new AnonymousClass15(resultCallBack, new File(str).getTotalSpace()));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$15  reason: invalid class name */
    class AnonymousClass15 implements a.b {
        final /* synthetic */ ResultCallBack a;
        final /* synthetic */ long b;

        AnonymousClass15(ResultCallBack resultCallBack, long j) {
            this.a = resultCallBack;
            this.b = j;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("sendVoiceToRobot---" + str);
            ZhiChiMessage jsonToZhiChiMessage = GsonUtil.jsonToZhiChiMessage(str);
            if (jsonToZhiChiMessage == null || 1 != Integer.parseInt(jsonToZhiChiMessage.getCode()) || jsonToZhiChiMessage.getData() == null) {
                this.a.onFailure(new Exception(), (jsonToZhiChiMessage == null || TextUtils.isEmpty(jsonToZhiChiMessage.getMsg())) ? "\u670d\u52a1\u5668\u9519\u8bef" : jsonToZhiChiMessage.getMsg());
            } else {
                this.a.onSuccess(jsonToZhiChiMessage);
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.onFailure(exc, str);
        }

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
            this.a.onLoading(this.b, (long) i, true);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void queryFormConfig(Object obj, String str, com.sobot.chat.core.http.c.a<SobotQueryFormModel> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("uid", str);
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.queryFormConfig, hashMap, new AnonymousClass16(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$16  reason: invalid class name */
    class AnonymousClass16 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass16(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("queryFormConfig---" + str);
            SobotQueryFormModelResult jsonToSobotQueryFormModelResult = GsonUtil.jsonToSobotQueryFormModelResult(str);
            if (jsonToSobotQueryFormModelResult != null && "1".equals(jsonToSobotQueryFormModelResult.getCode()) && jsonToSobotQueryFormModelResult.getData() != null) {
                this.a.a(jsonToSobotQueryFormModelResult.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e");
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void submitForm(Object obj, String str, String str2, com.sobot.chat.core.http.c.a<CommonModel> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("uid", str);
        hashMap.put("customerFields", str2);
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.submitForm, hashMap, new AnonymousClass17(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$17  reason: invalid class name */
    class AnonymousClass17 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass17(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("submitForm---" + str);
            this.a.a(GsonUtil.jsonToCommonModel(str));
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e");
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void queryCity(Object obj, String str, String str2, com.sobot.chat.core.http.c.a<SobotCityResult> aVar) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("provinceId", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("cityId", str2);
        }
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.queryCity, hashMap, new AnonymousClass18(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$18  reason: invalid class name */
    class AnonymousClass18 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass18(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("queryCity---" + str);
            SobotCityResult jsonToSobotCityResult = GsonUtil.jsonToSobotCityResult(str);
            if (jsonToSobotCityResult == null || !"1".equals(jsonToSobotCityResult.getCode())) {
                this.a.a(new IllegalStateException(), "\u670d\u52a1\u5668\u51fa\u9519\u4e86\uff01");
            } else {
                this.a.a(jsonToSobotCityResult);
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e");
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void questionRecommend(Object obj, String str, Map<String, String> map, com.sobot.chat.core.http.c.a<SobotQuestionRecommend> aVar) {
        if (map != null && map.size() != 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("uid", str);
            hashMap.put("margs", GsonUtil.map2Json(map));
            HttpUtilsTools.doPost(obj, ZhiChiUrlApi.questionRecommend, hashMap, new AnonymousClass19(aVar));
        }
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$19  reason: invalid class name */
    class AnonymousClass19 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass19(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("questionRecommend---" + str);
            SobotQuestionRecommendResult jsonToSobotQRResult = GsonUtil.jsonToSobotQRResult(str);
            if (jsonToSobotQRResult == null || !"1".equals(jsonToSobotQRResult.getCode()) || jsonToSobotQRResult.getData() == null) {
                this.a.a(new IllegalStateException(), "");
            } else {
                this.a.a(jsonToSobotQRResult.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e");
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void robotGuess(Object obj, String str, String str2, String str3, com.sobot.chat.core.http.c.a<SobotRobotGuess> aVar) {
        if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str)) {
            HashMap hashMap = new HashMap();
            hashMap.put("uid", str);
            hashMap.put("question", str3);
            if (!TextUtils.isEmpty(str2)) {
                hashMap.put("robotFlag", str2);
            }
            HttpUtilsTools.doPost(obj, ZhiChiUrlApi.robotGuess, hashMap, new AnonymousClass20(aVar));
        }
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$20  reason: invalid class name */
    class AnonymousClass20 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass20(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("robotGuess---" + str);
            SobotRobotGuessResult jsonToRobotGuessResult = GsonUtil.jsonToRobotGuessResult(str);
            if (jsonToRobotGuessResult == null || !"1".equals(jsonToRobotGuessResult.getCode()) || jsonToRobotGuessResult.getData() == null) {
                this.a.a(new IllegalStateException(), "");
            } else {
                this.a.a(jsonToRobotGuessResult.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e");
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void config(Object obj, String str) {
        if (!TextUtils.isEmpty(str)) {
            Context context = this.b;
            if (context != null) {
                m.a(context);
            }
            long b = s.b(this.b, "sobot_config_last_update_time", -1L);
            long b2 = (long) (s.b(this.b, "sobot_config_req_frequency", 2) * 86400000);
            if (-1 == b || System.currentTimeMillis() > b + b2) {
                HashMap hashMap = new HashMap();
                hashMap.put("appId", str);
                HttpUtilsTools.doPost(obj, ZhiChiUrlApi.sobotConfig, hashMap, new AnonymousClass21());
            }
        }
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$21  reason: invalid class name */
    class AnonymousClass21 implements a.b {
        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass21() {
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("sobotConfig---" + str);
            SobotConfigResult jsonToSobotConfigResult = GsonUtil.jsonToSobotConfigResult(str);
            if (jsonToSobotConfigResult != null && "1".equals(jsonToSobotConfigResult.getCode()) && jsonToSobotConfigResult.getData() != null) {
                SobotConfigModel data = jsonToSobotConfigResult.getData();
                s.a(ZhiChiApiImpl.this.b, "sobot_config_last_update_time", System.currentTimeMillis());
                s.a(ZhiChiApiImpl.this.b, "sobot_config_req_frequency", data.reqFrequency);
                s.a(ZhiChiApiImpl.this.b, "sobot_config_companyid", data.companyId);
                s.a(ZhiChiApiImpl.this.b, "sobot_config_req_collectFlag", data.collectFlag);
                s.a(ZhiChiApiImpl.this.b, "sobot_config_support", data.support);
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void getRobotSwitchList(Object obj, String str, com.sobot.chat.core.http.c.a<List<SobotRobot>> aVar) {
        if (!TextUtils.isEmpty(str)) {
            HashMap hashMap = new HashMap();
            hashMap.put("uid", str);
            HttpUtilsTools.doPost(obj, ZhiChiUrlApi.getRobotSwitchList, hashMap, new AnonymousClass22(aVar));
        }
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$22  reason: invalid class name */
    class AnonymousClass22 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass22(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("getRobotSwitchList---" + str);
            List<SobotRobot> jsonToRobotListResult = GsonUtil.jsonToRobotListResult(str);
            if (jsonToRobotListResult == null || jsonToRobotListResult.size() <= 0) {
                this.a.a(new IllegalStateException(), "");
            } else {
                this.a.a(jsonToRobotListResult);
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e");
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void getLableInfoList(Object obj, String str, com.sobot.chat.core.http.c.a<List<SobotLableInfoList>> aVar) {
        if (!TextUtils.isEmpty(str)) {
            HashMap hashMap = new HashMap();
            hashMap.put("uid", str);
            HttpUtilsTools.doPost(obj, ZhiChiUrlApi.getLableInfoList, hashMap, new AnonymousClass24(aVar));
        }
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$24  reason: invalid class name */
    class AnonymousClass24 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass24(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("getLableInfoList---" + str);
            List<SobotLableInfoList> jsonToLableInfoList = GsonUtil.jsonToLableInfoList(str);
            if (jsonToLableInfoList == null || jsonToLableInfoList.size() <= 0) {
                this.a.a(new IllegalStateException(), "");
            } else {
                this.a.a(jsonToLableInfoList);
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e");
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public List<SobotMsgCenterModel> getPlatformList(Object obj, String str, String str2) throws IOException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        HashMap hashMap = new HashMap();
        String b = s.b(this.b, "sobot_platform_key", "");
        hashMap.put(ConstantsAPI.Token.WX_TOKEN_PLATFORMID_KEY, str);
        hashMap.put("partnerId", str2);
        hashMap.put("platformKey", b);
        Response doPostSync = HttpUtilsTools.doPostSync(obj, ZhiChiUrlApi.getPlatformList, hashMap);
        if (!doPostSync.isSuccessful()) {
            return null;
        }
        String string = doPostSync.body().string();
        m.d("getPlatformList---" + string);
        return GsonUtil.jsonToMsgCenterModel(string);
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void removeMerchant(Object obj, String str, String str2, SobotMsgCenterModel sobotMsgCenterModel, com.sobot.chat.core.http.c.a<SobotMsgCenterModel> aVar) {
        if (!TextUtils.isEmpty(str) && sobotMsgCenterModel != null) {
            HashMap hashMap = new HashMap();
            String b = s.b(this.b, "sobot_platform_key", "");
            hashMap.put(ConstantsAPI.Token.WX_TOKEN_PLATFORMID_KEY, str);
            hashMap.put("partnerId", str2);
            hashMap.put("platformKey", b);
            hashMap.put("id", sobotMsgCenterModel.getId());
            w.a().execute(new AnonymousClass25(sobotMsgCenterModel, str2, aVar, obj, hashMap));
        }
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$25  reason: invalid class name */
    class AnonymousClass25 implements Runnable {
        final /* synthetic */ SobotMsgCenterModel a;
        final /* synthetic */ String b;
        final /* synthetic */ com.sobot.chat.core.http.c.a c;
        final /* synthetic */ Object d;
        final /* synthetic */ Map e;

        AnonymousClass25(SobotMsgCenterModel sobotMsgCenterModel, String str, com.sobot.chat.core.http.c.a aVar, Object obj, Map map) {
            this.a = sobotMsgCenterModel;
            this.b = str;
            this.c = aVar;
            this.d = obj;
            this.e = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                com.sobot.chat.core.channel.a.a(ZhiChiApiImpl.this.b).b(ZhiChiApiImpl.this.b, this.a.getApp_key(), this.b);
                if (TextUtils.isEmpty(this.a.getId())) {
                    ZhiChiApiImpl.this.a(this.a, this.c);
                    return;
                }
                Response doPostSync = HttpUtilsTools.doPostSync(this.d, ZhiChiUrlApi.removeMerchant, this.e);
                if (doPostSync.isSuccessful()) {
                    String string = doPostSync.body().string();
                    m.d("removeMerchant---" + string);
                    if (new JSONObject(string).optInt(com.taobao.accs.common.Constants.KEY_HTTP_CODE, 0) == 1) {
                        ZhiChiApiImpl.this.a(this.a, this.c);
                    } else {
                        ZhiChiApiImpl.this.a(new IllegalStateException(), "", this.c);
                    }
                } else {
                    ZhiChiApiImpl.this.a(new IllegalStateException(), "", this.c);
                }
            } catch (Exception e) {
                ZhiChiApiImpl.this.a(e, "", this.c);
            }
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public com.sobot.chat.core.http.i.d addUploadFileTask(boolean z, String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap();
        hashMap.put("uid", str2);
        hashMap.put("cid", str3);
        return com.sobot.chat.core.a.a().a(str, z ? ZhiChiUrlApi.sendVideo : ZhiChiUrlApi.uploadFile, hashMap, str4, str5);
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void sendLocation(Object obj, SobotLocationModel sobotLocationModel, String str, String str2, com.sobot.chat.core.http.c.a<CommonModelBase> aVar) {
        if (!TextUtils.isEmpty(str)) {
            HashMap hashMap = new HashMap();
            hashMap.put("uid", str);
            hashMap.put("cid", str2);
            hashMap.put("lng", sobotLocationModel.getLng());
            hashMap.put("lat", sobotLocationModel.getLat());
            hashMap.put("localLabel", sobotLocationModel.getLocalLabel());
            hashMap.put("localName", sobotLocationModel.getLocalName());
            HttpUtilsTools.uploadFile(obj, ZhiChiUrlApi.sendLocation, hashMap, sobotLocationModel.getSnapshot(), new AnonymousClass26(aVar));
        }
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$26  reason: invalid class name */
    class AnonymousClass26 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass26(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("sendLocation---" + str);
            CommonModel jsonToCommonModel = GsonUtil.jsonToCommonModel(str);
            if (jsonToCommonModel == null || 1 != Integer.parseInt(jsonToCommonModel.getCode()) || jsonToCommonModel.getData() == null) {
                this.a.a(new Exception(), "\u670d\u52a1\u5668\u9519\u8bef");
            } else {
                this.a.a(jsonToCommonModel.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e");
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void getWsTemplate(Object obj, String str, com.sobot.chat.core.http.c.a<ArrayList<SobotPostMsgTemplate>> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("uid", str);
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.getWsTemplate, hashMap, new AnonymousClass27(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$27  reason: invalid class name */
    class AnonymousClass27 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass27(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("getWsTemplate---" + str);
            SobotPostMsgTemplateResult jsonToSobotPostMsgTemplate = GsonUtil.jsonToSobotPostMsgTemplate(str);
            if (jsonToSobotPostMsgTemplate == null || 1 != Integer.parseInt(jsonToSobotPostMsgTemplate.getCode()) || jsonToSobotPostMsgTemplate.getData() == null) {
                this.a.a(new Exception(), q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
            } else {
                this.a.a(jsonToSobotPostMsgTemplate.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void getMsgTemplateConfig(Object obj, String str, String str2, com.sobot.chat.core.http.c.a<SobotLeaveMsgConfig> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", "basic-set/getMsgTemplateConfig/4");
        hashMap.put("method", "get");
        hashMap.put("uid", str);
        hashMap.put("param", "{\"templateId\":\"" + str2 + "\"}");
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.invokeOtherByUser, hashMap, new AnonymousClass28(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$28  reason: invalid class name */
    class AnonymousClass28 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass28(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("getCusMsgTemplateConfig---" + str);
            SobotLeaveMsgConfigResult jsonToLeaveMsgConfigResult = GsonUtil.jsonToLeaveMsgConfigResult(str);
            if (jsonToLeaveMsgConfigResult == null || !"1".equals(jsonToLeaveMsgConfigResult.getCode()) || jsonToLeaveMsgConfigResult.getData() == null) {
                this.a.a(new Exception(), q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
            } else {
                this.a.a(jsonToLeaveMsgConfigResult.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void getTemplateFieldsInfo(Object obj, String str, String str2, com.sobot.chat.core.http.c.a<SobotLeaveMsgParamModel> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", "basic-set/getTemplateFieldsInfo/4");
        hashMap.put("method", "get");
        hashMap.put("uid", str);
        hashMap.put("param", "{\"templateId\":\"" + str2 + "\"}");
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.invokeOtherByUser, hashMap, new AnonymousClass29(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$29  reason: invalid class name */
    class AnonymousClass29 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass29(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("getTemplateFieldsInfo---" + str);
            SobotLeaveMsgParamBaseModel jsonToLeaveMsgParamBaseModel = GsonUtil.jsonToLeaveMsgParamBaseModel(str);
            if (jsonToLeaveMsgParamBaseModel == null || !"1".equals(jsonToLeaveMsgParamBaseModel.getCode()) || jsonToLeaveMsgParamBaseModel.getData() == null) {
                this.a.a(new Exception(), q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
            } else {
                this.a.a(jsonToLeaveMsgParamBaseModel.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void getUserTicketInfoList(Object obj, String str, String str2, String str3, com.sobot.chat.core.http.c.a<List<SobotUserTicketInfo>> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", "ws/getUserTicketInfoList/4");
        hashMap.put("method", "get");
        hashMap.put("uid", str);
        hashMap.put("param", "{\"companyId\":\"" + str2 + "\",\"customerId\":\"" + str3 + "\",\"pageSize\":\"60\"}");
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.invokeOtherByUser, hashMap, new AnonymousClass30(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$30  reason: invalid class name */
    class AnonymousClass30 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass30(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("getUserTicketInfoList---" + str);
            SobotUserTicketInfoResult jsonToSobotUserTicketInfoResult = GsonUtil.jsonToSobotUserTicketInfoResult(str);
            if (jsonToSobotUserTicketInfoResult == null || !"1".equals(jsonToSobotUserTicketInfoResult.getCode()) || jsonToSobotUserTicketInfoResult.getData() == null) {
                this.a.a(new Exception(), q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
            } else {
                this.a.a(jsonToSobotUserTicketInfoResult.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void getUserDealTicketInfoList(Object obj, String str, String str2, String str3, com.sobot.chat.core.http.c.a<List<StUserDealTicketInfo>> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", "ws/getUserDealTicketInfoList/4");
        hashMap.put("method", "get");
        hashMap.put("uid", str);
        hashMap.put("param", "{\"companyId\":\"" + str2 + "\",\"ticketId\":\"" + str3 + "\"}");
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.invokeOtherByUser, hashMap, new AnonymousClass31(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$31  reason: invalid class name */
    class AnonymousClass31 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass31(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("getUserDealTicketInfoList---" + str);
            StUserDealTicketInfoResult jsonToStUserDealTicketInfoResult = GsonUtil.jsonToStUserDealTicketInfoResult(str);
            if (jsonToStUserDealTicketInfoResult == null || !"1".equals(jsonToStUserDealTicketInfoResult.getCode()) || jsonToStUserDealTicketInfoResult.getData() == null) {
                this.a.a(new Exception(), q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
            } else {
                this.a.a(jsonToStUserDealTicketInfoResult.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void checkUserTicketInfo(Object obj, String str, String str2, String str3, com.sobot.chat.core.http.c.a<SobotUserTicketInfoFlag> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", "ws/checkUserTicketInfo/4");
        hashMap.put("method", "get");
        hashMap.put("uid", str);
        hashMap.put("param", "{\"companyId\":\"" + str2 + "\",\"customerId\":\"" + str3 + "\"}");
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.invokeOtherByUser, hashMap, new AnonymousClass32(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$32  reason: invalid class name */
    class AnonymousClass32 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass32(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("checkUserTicketInfo---" + str);
            SobotUserTicketInfoFlag jsonToSobotUserTicketInfoFlag = GsonUtil.jsonToSobotUserTicketInfoFlag(str);
            if (jsonToSobotUserTicketInfoFlag == null || !"1".equals(jsonToSobotUserTicketInfoFlag.getCode())) {
                this.a.a(new Exception(), q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
            } else {
                this.a.a(jsonToSobotUserTicketInfoFlag);
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void getCategoryList(Object obj, String str, com.sobot.chat.core.http.c.a<List<StCategoryModel>> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("appId", str);
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.getCategoryList, hashMap, new AnonymousClass33(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$33  reason: invalid class name */
    class AnonymousClass33 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass33(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("getCategoryList---" + str);
            BaseCode<List<StCategoryModel>> jsonToStCategoryModelResult = GsonUtil.jsonToStCategoryModelResult(str);
            if (jsonToStCategoryModelResult == null || !"1".equals(jsonToStCategoryModelResult.getCode()) || jsonToStCategoryModelResult.getData() == null) {
                this.a.a(new Exception(), q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
            } else {
                this.a.a(jsonToStCategoryModelResult.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void getHelpDocByCategoryId(Object obj, String str, String str2, com.sobot.chat.core.http.c.a<List<StDocModel>> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("appId", str);
        hashMap.put("categoryId", str2);
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.getHelpDocByCategoryId, hashMap, new AnonymousClass35(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$35  reason: invalid class name */
    class AnonymousClass35 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass35(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("getHelpDocByCategoryId---" + str);
            BaseCode<List<StDocModel>> jsonToStDocModelResult = GsonUtil.jsonToStDocModelResult(str);
            if (jsonToStDocModelResult == null || !"1".equals(jsonToStDocModelResult.getCode()) || jsonToStDocModelResult.getData() == null) {
                this.a.a(new Exception(), q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
            } else {
                this.a.a(jsonToStDocModelResult.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void getHelpDocByDocId(Object obj, String str, String str2, com.sobot.chat.core.http.c.a<StHelpDocModel> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("appId", str);
        hashMap.put("docId", str2);
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.getHelpDocByDocId, hashMap, new AnonymousClass36(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$36  reason: invalid class name */
    class AnonymousClass36 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass36(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("getHelpDocByDocId---" + str);
            BaseCode<StHelpDocModel> jsonToStHelpDocModelResult = GsonUtil.jsonToStHelpDocModelResult(str);
            if (jsonToStHelpDocModelResult == null || !"1".equals(jsonToStHelpDocModelResult.getCode()) || jsonToStHelpDocModelResult.getData() == null) {
                this.a.a(new Exception(), q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
            } else {
                this.a.a(jsonToStHelpDocModelResult.getData());
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void leaveMsg(Object obj, String str, String str2, com.sobot.chat.core.http.c.a<BaseCode> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("uid", str);
        hashMap.put("content", str2);
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.leaveMsg, hashMap, new AnonymousClass37(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$37  reason: invalid class name */
    class AnonymousClass37 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass37(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("leaveMsg---" + str);
            BaseCode jsonToBaseCode = GsonUtil.jsonToBaseCode(str);
            if (jsonToBaseCode == null || !"1".equals(jsonToBaseCode.getCode())) {
                this.a.a(new Exception(), q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
            } else {
                this.a.a(jsonToBaseCode);
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void addTicketSatisfactionScoreInfo(Object obj, String str, String str2, String str3, int i, String str4, com.sobot.chat.core.http.c.a<String> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", "ws/addTicketSatisfactionScoreInfo/4 ");
        hashMap.put("method", "post");
        hashMap.put("uid", str);
        hashMap.put("param", "{\"ticketId\":\"" + str3 + "\",\"score\":\"" + i + "\",\"remark\":\"" + str4 + "\",\"companyId\":\"" + str2 + "\"}");
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.invokeOtherByUser, hashMap, new AnonymousClass38(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$38  reason: invalid class name */
    class AnonymousClass38 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass38(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("addTicketSatisfactionScoreInfo---" + str);
            this.a.a(str);
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
            this.a.a(exc, q.f(ZhiChiApiImpl.this.b, "sobot_try_again"));
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void replyTicketContent(Object obj, String str, String str2, String str3, String str4, String str5, com.sobot.chat.core.http.c.a<String> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", "ws/saveUserReplyInfo/4");
        hashMap.put("method", "post");
        hashMap.put("uid", str);
        hashMap.put("param", "{\"replyContent\":\"" + str3 + "\",\"companyId\":\"" + str5 + "\",\"fileStr\":\"" + str4 + "\",\"ticketId\":\"" + str2 + "\"}");
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.invokeOtherByUser, hashMap, new AnonymousClass39(aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$39  reason: invalid class name */
    class AnonymousClass39 implements a.b {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass39(com.sobot.chat.core.http.c.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            this.a.a(str);
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            this.a.a(exc, str);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void getUserTicketReplyInfo(Object obj, String str, String str2, com.sobot.chat.core.http.c.a<List<SobotLeaveReplyModel>> aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("companyId", str);
        hashMap.put("partnerId", str2);
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.getUserTicketReplyInfo, hashMap, new AnonymousClass40(new ArrayList(), aVar));
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$40  reason: invalid class name */
    class AnonymousClass40 implements a.b {
        final /* synthetic */ List a;
        final /* synthetic */ com.sobot.chat.core.http.c.a b;

        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass40(List list, com.sobot.chat.core.http.c.a aVar) {
            this.a = list;
            this.b = aVar;
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("getUserTicketReplyInfo---" + str);
            this.a.clear();
            List<SobotLeaveReplyModel> jsonToLeaveReplyModelListResult = GsonUtil.jsonToLeaveReplyModelListResult(str);
            if (jsonToLeaveReplyModelListResult != null && jsonToLeaveReplyModelListResult.size() > 0) {
                this.a.addAll(jsonToLeaveReplyModelListResult);
            }
            this.b.a(this.a);
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            this.b.a(exc, str);
        }
    }

    @Override // com.sobot.chat.api.ZhiChiApi
    public void updateUserTicketReplyInfo(Object obj, String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("companyId", str);
        hashMap.put("partnerId", str2);
        hashMap.put("ticketId", str3);
        HttpUtilsTools.doPost(obj, ZhiChiUrlApi.updateUserTicketReplyInfo, hashMap, new AnonymousClass41());
    }

    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$41  reason: invalid class name */
    class AnonymousClass41 implements a.b {
        @Override // com.sobot.chat.core.a.b
        public void a(int i) {
        }

        AnonymousClass41() {
        }

        @Override // com.sobot.chat.core.a.b
        public void a(String str) {
            m.d("\u8fd4\u56de\u503c--\uff1a" + str);
            CommonModel jsonToCommonModel = GsonUtil.jsonToCommonModel(str);
            if (jsonToCommonModel != null && 1 == Integer.parseInt(jsonToCommonModel.getCode()) && jsonToCommonModel.getData() != null) {
                m.d("\u8fd4\u56de\u503c--\uff1a" + str);
            }
        }

        @Override // com.sobot.chat.core.a.b
        public void a(Exception exc, String str, int i) {
            m.a(ZhiChiApiImpl.a + str, exc);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$42  reason: invalid class name */
    public class AnonymousClass42 implements Runnable {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;
        final /* synthetic */ Exception b;
        final /* synthetic */ String c;

        AnonymousClass42(com.sobot.chat.core.http.c.a aVar, Exception exc, String str) {
            this.a = aVar;
            this.b = exc;
            this.c = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(this.b, this.c);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Exception exc, String str, com.sobot.chat.core.http.c.a aVar) {
        com.sobot.chat.core.http.a.a().b().post(new AnonymousClass42(aVar, exc, str));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.api.ZhiChiApiImpl$43  reason: invalid class name */
    public class AnonymousClass43 implements Runnable {
        final /* synthetic */ com.sobot.chat.core.http.c.a a;
        final /* synthetic */ Object b;

        AnonymousClass43(com.sobot.chat.core.http.c.a aVar, Object obj) {
            this.a = aVar;
            this.b = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(this.b);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Object obj, com.sobot.chat.core.http.c.a aVar) {
        com.sobot.chat.core.http.a.a().b().post(new AnonymousClass43(aVar, obj));
    }
}
