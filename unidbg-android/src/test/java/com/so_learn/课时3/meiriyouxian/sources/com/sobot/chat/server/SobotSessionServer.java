package com.sobot.chat.server;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hms.support.api.push.PushReceiver;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.api.enumtype.CustomerState;
import com.sobot.chat.api.enumtype.SobotChatStatusMode;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.ZhiChiInitModeBase;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.api.model.ZhiChiPushMessage;
import com.sobot.chat.core.channel.a;
import com.sobot.chat.utils.ZhiChiConfig;
import com.sobot.chat.utils.af;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.p;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.s;
import com.sobot.chat.utils.y;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

public class SobotSessionServer extends Service {
    protected Timer a = null;
    protected TimerTask b = null;
    private LocalBroadcastManager c;
    private MyMessageReceiver d;
    private MyNetWorkChangeReceiver e;
    private int f = 0;
    private String g = "";
    private Information h = null;
    private ZhiChiConfig i = null;
    private boolean j = false;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            this.g = intent.getStringExtra("sobot_current_im_partnerid");
        }
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        m.d("SobotSessionServer  ---> onCreate");
        c();
    }

    private void c() {
        if (this.d == null) {
            this.d = new MyMessageReceiver();
        }
        if (this.e == null) {
            this.e = new MyNetWorkChangeReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ZhiChiConstants.receiveMessageBrocast);
        intentFilter.addAction(ZhiChiConstants.SOBOT_TIMER_BROCAST);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.c = LocalBroadcastManager.getInstance(this);
        this.c.registerReceiver(this.d, intentFilter);
        registerReceiver(this.e, intentFilter);
    }

    public class MyNetWorkChangeReceiver extends BroadcastReceiver {
        public MyNetWorkChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            m.d("MyNetWorkChangeReceiver action=" + intent.getAction());
            if (context != null && intent != null) {
                af.a(SobotSessionServer.this.getApplicationContext());
            }
        }
    }

    public class MyMessageReceiver extends BroadcastReceiver {
        public MyMessageReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Bundle extras;
            ZhiChiPushMessage zhiChiPushMessage;
            if (ZhiChiConstants.receiveMessageBrocast.equals(intent.getAction())) {
                try {
                    Bundle extras2 = intent.getExtras();
                    if (extras2 != null && (zhiChiPushMessage = (ZhiChiPushMessage) extras2.getSerializable(ZhiChiConstants.ZHICHI_PUSH_MESSAGE)) != null && SobotSessionServer.this.b(zhiChiPushMessage.getAppId())) {
                        SobotSessionServer.this.a(zhiChiPushMessage);
                    }
                } catch (Exception unused) {
                }
            } else if (ZhiChiConstants.SOBOT_TIMER_BROCAST.equals(intent.getAction()) && (extras = intent.getExtras()) != null) {
                SobotSessionServer.this.j = extras.getBoolean("isStartTimer");
                if (!SobotSessionServer.this.j) {
                    SobotSessionServer.this.b();
                    return;
                }
                SobotSessionServer.this.h = (Information) extras.getSerializable("info");
                SobotSessionServer sobotSessionServer = SobotSessionServer.this;
                sobotSessionServer.i = a.a(sobotSessionServer.getApplicationContext()).a(SobotSessionServer.this.h.getApp_key());
                if (SobotSessionServer.this.i.getInitModel() != null && SobotSessionServer.this.i.customerState == CustomerState.Online) {
                    SobotSessionServer.this.a();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(ZhiChiPushMessage zhiChiPushMessage) {
        List<ZhiChiMessageBase> messageList;
        String str;
        int i;
        String str2;
        if (zhiChiPushMessage != null) {
            ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
            zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
            zhiChiMessageBase.setSenderName(zhiChiPushMessage.getAname());
            this.i = a.a(getApplication()).a(zhiChiPushMessage.getAppId());
            if (200 == zhiChiPushMessage.getType()) {
                if (this.i.getInitModel() != null) {
                    this.i.adminFace = zhiChiPushMessage.getAface();
                    int parseInt = Integer.parseInt(this.i.getInitModel().getType());
                    if (parseInt == 2 || parseInt == 3 || parseInt == 4) {
                        ZhiChiInitModeBase initModel = this.i.getInitModel();
                        if (initModel != null) {
                            initModel.setAdminHelloWord(!TextUtils.isEmpty(zhiChiPushMessage.getAdminHelloWord()) ? zhiChiPushMessage.getAdminHelloWord() : initModel.getAdminHelloWord());
                            initModel.setAdminTipTime(!TextUtils.isEmpty(zhiChiPushMessage.getServiceOutTime()) ? zhiChiPushMessage.getServiceOutTime() : initModel.getAdminTipTime());
                            initModel.setAdminTipWord(!TextUtils.isEmpty(zhiChiPushMessage.getServiceOutDoc()) ? zhiChiPushMessage.getServiceOutDoc() : initModel.getAdminTipWord());
                        }
                        a(zhiChiPushMessage.getAppId(), zhiChiPushMessage.getAname(), zhiChiPushMessage.getAface(), zhiChiPushMessage);
                    }
                }
            } else if (202 == zhiChiPushMessage.getType()) {
                if (this.i.getInitModel() != null && this.i.customerState == CustomerState.Online) {
                    zhiChiMessageBase.setMsgId(zhiChiPushMessage.getMsgId());
                    zhiChiMessageBase.setSender(zhiChiPushMessage.getAname());
                    zhiChiMessageBase.setSenderName(zhiChiPushMessage.getAname());
                    zhiChiMessageBase.setSenderFace(zhiChiPushMessage.getAface());
                    zhiChiMessageBase.setOrderCardContent(zhiChiPushMessage.getOrderCardContent());
                    zhiChiMessageBase.setConsultingContent(zhiChiPushMessage.getConsultingContent());
                    zhiChiMessageBase.setSenderType("2");
                    zhiChiMessageBase.setAnswer(zhiChiPushMessage.getAnswer());
                    if (this.i.isShowUnreadUi) {
                        this.i.addMessage(c.a(getApplicationContext()));
                        this.i.isShowUnreadUi = false;
                    }
                    this.i.addMessage(zhiChiMessageBase);
                    if (this.i.customerState == CustomerState.Online) {
                        ZhiChiConfig zhiChiConfig = this.i;
                        zhiChiConfig.customTimeTask = false;
                        zhiChiConfig.userInfoTimeTask = true;
                    }
                }
                if (b(zhiChiPushMessage.getAppId())) {
                    try {
                        JSONObject jSONObject = new JSONObject(zhiChiPushMessage.getContent());
                        str = jSONObject.optString("msg");
                        i = jSONObject.optInt(PushReceiver.PushMessageThread.MSGTYPE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        i = -1;
                        str = "";
                    }
                    if (!(i == -1 || TextUtils.isEmpty(str))) {
                        if (i == 4 || i == 5) {
                            str = q.f(this, "sobot_chat_type_rich_text");
                            str2 = q.f(this, "sobot_receive_new_message");
                        } else if (i == 1) {
                            str = q.f(this, "sobot_upload");
                            str2 = q.f(this, "sobot_upload");
                        } else {
                            str2 = str;
                        }
                        int a = a.a(getApplicationContext()).a(zhiChiPushMessage, Calendar.getInstance().getTime().getTime() + "", this.g);
                        Intent intent = new Intent();
                        intent.setAction("sobot_unreadCountBrocast");
                        intent.putExtra("noReadCount", a);
                        intent.putExtra("content", str);
                        intent.putExtra("sobot_appId", zhiChiPushMessage.getAppId());
                        d.b(getApplicationContext(), intent);
                        a("[" + str2 + "]", zhiChiPushMessage);
                    }
                }
            } else if (215 == zhiChiPushMessage.getType()) {
                if (this.i.getInitModel() != null && this.i.customerState == CustomerState.Online) {
                    zhiChiMessageBase.setMsgId(zhiChiPushMessage.getMsgId());
                    zhiChiMessageBase.setAction("29");
                    zhiChiMessageBase.setMsg(zhiChiPushMessage.getContent());
                    this.i.addMessage(zhiChiMessageBase);
                    if (this.i.customerState == CustomerState.Online) {
                        ZhiChiConfig zhiChiConfig2 = this.i;
                        zhiChiConfig2.customTimeTask = false;
                        zhiChiConfig2.userInfoTimeTask = true;
                    }
                }
            } else if (201 == zhiChiPushMessage.getType()) {
                if (this.i.getInitModel() != null) {
                    a(zhiChiPushMessage.getAppId(), zhiChiPushMessage.getCount(), zhiChiPushMessage.getQueueDoc());
                }
            } else if (204 == zhiChiPushMessage.getType()) {
                if (y.h != null) {
                    y.h.a(SobotChatStatusMode.ZCServerConnectOffline);
                }
                a.a(getApplication()).b();
                d.a(getApplicationContext(), new Intent("sobot_chat_user_outline"));
                a(q.f(this, "sobot_dialogue_finish"), zhiChiPushMessage);
            } else if (210 == zhiChiPushMessage.getType()) {
                if (this.i.getInitModel() != null) {
                    m.d("\u7528\u6237\u88ab\u8f6c\u63a5--->" + zhiChiPushMessage.getName());
                    this.i.activityTitle = zhiChiPushMessage.getName();
                    this.i.adminFace = zhiChiPushMessage.getFace();
                    this.i.currentUserName = zhiChiPushMessage.getName();
                }
            } else if (211 == zhiChiPushMessage.getType()) {
                if (!(this.i.getInitModel() == null || TextUtils.isEmpty(zhiChiPushMessage.getRevokeMsgId()) || (messageList = this.i.getMessageList()) == null || messageList.size() <= 0)) {
                    for (int size = messageList.size() - 1; size >= 0; size--) {
                        ZhiChiMessageBase zhiChiMessageBase2 = messageList.get(size);
                        if (zhiChiPushMessage.getRevokeMsgId().equals(zhiChiMessageBase2.getMsgId())) {
                            zhiChiMessageBase2.setRetractedMsg(true);
                            return;
                        }
                    }
                }
            } else if (209 == zhiChiPushMessage.getType()) {
                if (this.i.getInitModel() != null && this.i.isAboveZero && !this.i.isComment && this.i.customerState == CustomerState.Online) {
                    this.i.addMessage(c.a(zhiChiPushMessage));
                }
            } else if (213 == zhiChiPushMessage.getType()) {
                m.d("SobotSessionServer  ---> push_message_user_get_session_lock_msg---------------" + zhiChiPushMessage.getLockType());
                if (this.i.getInitModel() != null && this.i.customerState == CustomerState.Online) {
                    if (1 == zhiChiPushMessage.getLockType()) {
                        this.i.isChatLock = 1;
                        b();
                        return;
                    }
                    this.i.isChatLock = 2;
                    a();
                }
            }
        }
    }

    private void a(String str, String str2, String str3) {
        ZhiChiInitModeBase initModel;
        ZhiChiConfig a = a.a(getApplication()).a(str);
        if (a.customerState == CustomerState.Queuing && !TextUtils.isEmpty(str2) && Integer.parseInt(str2) > 0 && (initModel = a.getInitModel()) != null) {
            int parseInt = Integer.parseInt(initModel.getType());
            a.queueNum = Integer.parseInt(str2);
            if (a.isShowQueueTip && !TextUtils.isEmpty(str3)) {
                a.addMessage(c.b(str3));
            }
            if (parseInt == 2) {
                a.activityTitle = c.a(getApplicationContext(), false, a("sobot_in_line"), initModel.getCompanyName());
                a.bottomViewtype = 3;
                return;
            }
            a.activityTitle = c.a(getApplicationContext(), false, initModel.getRobotName(), initModel.getCompanyName());
            a.bottomViewtype = 5;
        }
    }

    private void a(String str, String str2, String str3, ZhiChiPushMessage zhiChiPushMessage) {
        ZhiChiConfig a = a.a(getApplication()).a(str);
        ZhiChiInitModeBase initModel = a.getInitModel();
        if (initModel != null) {
            a.current_client_model = 302;
            if (y.h != null) {
                y.h.a(SobotChatStatusMode.ZCServerConnectArtificial);
            }
            a.customerState = CustomerState.Online;
            a.isAboveZero = false;
            a.isComment = false;
            a.queueNum = 0;
            a.currentUserName = TextUtils.isEmpty(str2) ? "" : str2;
            a.addMessage(c.b(getApplicationContext(), str2));
            if (initModel.isAdminHelloWordFlag()) {
                String b = s.b(getApplicationContext(), "sobot_admin_hello_word", "");
                if (!TextUtils.isEmpty(b)) {
                    a.addMessage(c.a(str2, str3, b));
                } else {
                    a.addMessage(c.a(str2, str3, initModel.getAdminHelloWord()));
                }
            }
            a.activityTitle = c.a(getApplicationContext(), false, str2, initModel.getCompanyName());
            a.bottomViewtype = 2;
            a.userInfoTimeTask = true;
            a.customTimeTask = false;
            a.isProcessAutoSendMsg = true;
            a.hideItemTransferBtn();
            if (b(str)) {
                a(String.format(a("sobot_service_accept"), a.currentUserName), zhiChiPushMessage);
            }
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager localBroadcastManager = this.c;
        if (localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(this.d);
        }
        MyNetWorkChangeReceiver myNetWorkChangeReceiver = this.e;
        if (myNetWorkChangeReceiver != null) {
            unregisterReceiver(myNetWorkChangeReceiver);
        }
        b();
        m.d("SobotSessionServer  ---> onDestroy");
    }

    public String a(String str) {
        return q.f(this, str);
    }

    private void a(String str, ZhiChiPushMessage zhiChiPushMessage) {
        String str2;
        if (s.b(getApplicationContext(), "sobot_notification_flag_chat", false)) {
            String f = q.f(getApplicationContext(), "sobot_notification_tip_title");
            if (!TextUtils.isEmpty(zhiChiPushMessage.getAname())) {
                str2 = q.f(getApplicationContext(), "sobot_cus_service") + zhiChiPushMessage.getAname() + "\uff1a" + str;
            } else {
                str2 = str;
            }
            p.a(getApplicationContext(), f, str2, str, d(), zhiChiPushMessage);
        }
    }

    private int d() {
        if (this.f == 999) {
            this.f = 0;
        }
        this.f++;
        return this.f;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean b(String str) {
        return !s.b(getApplicationContext(), "sobot_current_im_appid", "").equals(str) || !d.j(getApplicationContext()).contains("SobotChatActivity") || !d.i(getApplicationContext()) || d.k(getApplicationContext());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.server.SobotSessionServer$1  reason: invalid class name */
    public class AnonymousClass1 extends TimerTask {
        AnonymousClass1() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (SobotSessionServer.this.i.userInfoTimeTask) {
                if (SobotSessionServer.this.i.paseReplyTimeCustoms > 1800) {
                    SobotSessionServer.this.b();
                    return;
                }
                SobotSessionServer.this.i.paseReplyTimeUserInfo++;
            } else if (SobotSessionServer.this.i.paseReplyTimeCustoms > 1800) {
                SobotSessionServer.this.b();
            } else {
                SobotSessionServer.this.i.paseReplyTimeCustoms++;
            }
        }
    }

    public void a() {
        this.a = new Timer();
        this.b = new AnonymousClass1();
        this.a.schedule(this.b, 1000, 1000);
    }

    public void b() {
        Timer timer = this.a;
        if (timer != null) {
            timer.cancel();
            this.a = null;
        }
        TimerTask timerTask = this.b;
        if (timerTask != null) {
            timerTask.cancel();
            this.b = null;
        }
    }
}
