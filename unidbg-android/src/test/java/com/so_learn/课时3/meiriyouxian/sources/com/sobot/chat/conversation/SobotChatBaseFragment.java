package com.sobot.chat.conversation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.android.internal.logging.nano.MetricsProto;
import com.sobot.chat.activity.SobotQueryFromActivity;
import com.sobot.chat.activity.WebViewActivity;
import com.sobot.chat.adapter.e;
import com.sobot.chat.api.ResultCallBack;
import com.sobot.chat.api.enumtype.CustomerState;
import com.sobot.chat.api.enumtype.SobotAutoSendMsgMode;
import com.sobot.chat.api.model.CommonModel;
import com.sobot.chat.api.model.CommonModelBase;
import com.sobot.chat.api.model.ConsultingContent;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.OrderCardContentModel;
import com.sobot.chat.api.model.SobotLocationModel;
import com.sobot.chat.api.model.SobotQueryFormModel;
import com.sobot.chat.api.model.SobotQuestionRecommend;
import com.sobot.chat.api.model.SobotUserTicketInfoFlag;
import com.sobot.chat.api.model.ZhiChiInitModeBase;
import com.sobot.chat.api.model.ZhiChiMessage;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.api.model.ZhiChiReplyAnswer;
import com.sobot.chat.b;
import com.sobot.chat.b.a;
import com.sobot.chat.camera.c.f;
import com.sobot.chat.d;
import com.sobot.chat.fragment.SobotBaseFragment;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.h;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.n;
import com.sobot.chat.utils.p;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.s;
import com.sobot.chat.utils.y;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public abstract class SobotChatBaseFragment extends SobotBaseFragment implements SensorEventListener {
    private TimerTask A;
    private Timer B = null;
    private boolean C = false;
    private String D = "";
    private TimerTask E = null;
    private AudioManager F = null;
    private SensorManager G = null;
    private Sensor H = null;
    protected Context a;
    protected e b;
    protected int c = 301;
    protected CustomerState d = CustomerState.Offline;
    protected ZhiChiInitModeBase e;
    protected Information f;
    protected String g;
    protected boolean h = false;
    protected int i = 0;
    protected boolean j;
    protected boolean k = false;
    protected boolean l = false;
    protected boolean m = false;
    protected boolean n = false;
    protected int o = 0;
    public int p = 0;
    protected int q = 0;
    protected Timer r;
    protected TimerTask s;
    protected int t = 0;
    public int u = 0;
    protected int v = 0;
    protected View.OnClickListener w = new AnonymousClass16();
    private String x = "";
    private boolean y = false;
    private Timer z;

    /* access modifiers changed from: protected */
    public void a(ZhiChiInitModeBase zhiChiInitModeBase, int i) {
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, String str3, String str4, boolean z, int i) {
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
    }

    /* access modifiers changed from: protected */
    public abstract String n();

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.a = getContext().getApplicationContext();
        a();
        if (b.a(1) && b.a(4)) {
            com.sobot.chat.b.b.a().a(getActivity());
            getActivity().getWindow().setFlags(1024, 1024);
        }
    }

    @Override // com.sobot.chat.fragment.SobotBaseFragment
    public void a(View view) {
        if (b.a(1) && b.a(4) && view != null) {
            com.sobot.chat.b.b.a().a(getActivity(), new AnonymousClass1(view));
        }
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$1  reason: invalid class name */
    class AnonymousClass1 implements a.AbstractC0139a {
        final /* synthetic */ View a;

        AnonymousClass1(View view) {
            this.a = view;
        }

        @Override // com.sobot.chat.b.a.AbstractC0139a
        public void a(a.b bVar) {
            if (bVar.a) {
                for (Rect rect : bVar.b) {
                    View view = this.a;
                    if (!(view instanceof WebView) || !(view.getParent() instanceof LinearLayout)) {
                        View view2 = this.a;
                        if (!(view2 instanceof WebView) || !(view2.getParent() instanceof RelativeLayout)) {
                            this.a.setPadding(rect.right + this.a.getPaddingLeft(), this.a.getPaddingTop(), this.a.getPaddingRight(), this.a.getPaddingBottom());
                        } else {
                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.a.getLayoutParams();
                            layoutParams.leftMargin = rect.right + 14;
                            this.a.setLayoutParams(layoutParams);
                        }
                    } else {
                        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.a.getLayoutParams();
                        layoutParams2.leftMargin = rect.right + 14;
                        this.a.setLayoutParams(layoutParams2);
                    }
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.e != null && this.d == CustomerState.Online && this.c == 302) {
            i();
        }
        p.a(this.a);
        if (this.d == CustomerState.Online || this.d == CustomerState.Queuing) {
            this.U.reconnectChannel();
        }
        SensorManager sensorManager = this.G;
        if (sensorManager != null) {
            sensorManager.registerListener(this, this.H, 3);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        k();
        this.G.unregisterListener(this);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void d() {
        if (e() && Z() != null) {
            Z().finish();
        }
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return isAdded();
    }

    public void a(Handler handler) {
        m.d("--->  startUserInfoTimeTask=====" + this.q);
        if (this.q != 1 && this.c == 302 && this.e.isCustomOutTimeFlag()) {
            f();
            this.m = true;
            this.z = new Timer();
            this.A = new AnonymousClass8(handler);
            this.z.schedule(this.A, 1000, 1000);
        }
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$8  reason: invalid class name */
    class AnonymousClass8 extends TimerTask {
        final /* synthetic */ Handler a;

        AnonymousClass8(Handler handler) {
            this.a = handler;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            SobotChatBaseFragment.this.g(this.a);
        }
    }

    public void f() {
        this.m = false;
        Timer timer = this.z;
        if (timer != null) {
            timer.cancel();
            this.z = null;
        }
        TimerTask timerTask = this.A;
        if (timerTask != null) {
            timerTask.cancel();
            this.A = null;
        }
        this.o = 0;
    }

    public void b(Handler handler) {
        if (this.d != CustomerState.Online) {
            g();
            f();
        } else if (this.c == 302 && !this.n) {
            f();
            d(handler);
        }
    }

    public void c(Handler handler) {
        if (this.d == CustomerState.Online && this.c == 302 && !this.n) {
            f();
            d(handler);
        }
    }

    public void d(Handler handler) {
        if (this.q == 1 || this.c != 302 || !this.e.isServiceOutTimeFlag()) {
            return;
        }
        if (this.e.isServiceOutCountRule() && this.v >= 1) {
            g();
        } else if (!this.n) {
            g();
            this.l = true;
            this.n = true;
            this.r = new Timer();
            this.s = new AnonymousClass9(handler);
            this.r.schedule(this.s, 1000, 1000);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$9  reason: invalid class name */
    public class AnonymousClass9 extends TimerTask {
        final /* synthetic */ Handler a;

        AnonymousClass9(Handler handler) {
            this.a = handler;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            SobotChatBaseFragment.this.e(this.a);
        }
    }

    public void g() {
        this.l = false;
        this.n = false;
        Timer timer = this.r;
        if (timer != null) {
            timer.cancel();
            this.r = null;
        }
        TimerTask timerTask = this.s;
        if (timerTask != null) {
            timerTask.cancel();
            this.s = null;
        }
        this.t = 0;
    }

    public void e(Handler handler) {
        this.t++;
        ZhiChiInitModeBase zhiChiInitModeBase = this.e;
        if (zhiChiInitModeBase != null && this.t == Integer.parseInt(zhiChiInitModeBase.getAdminTipTime()) * 60) {
            this.v++;
            ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
            zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
            ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
            this.l = false;
            zhiChiMessageBase.setSenderName(this.g);
            zhiChiMessageBase.setSenderType("2");
            String b = s.b(this.a, "sobot_admin_tip_word", "");
            if (!TextUtils.isEmpty(b)) {
                zhiChiReplyAnswer.setMsg(b);
            } else if (!TextUtils.isEmpty(this.e.getAdminTipWord())) {
                String replace = this.e.getAdminTipWord().replace("\n", "<br/>");
                if (replace.startsWith("<br/>")) {
                    replace = replace.substring(5, replace.length());
                }
                if (replace.endsWith("<br/>")) {
                    replace = replace.substring(0, replace.length() - 5);
                }
                zhiChiReplyAnswer.setMsg(replace);
            } else {
                return;
            }
            zhiChiMessageBase.setSenderFace(this.x);
            zhiChiReplyAnswer.setMsgType("0");
            zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 802;
            obtainMessage.obj = zhiChiMessageBase;
            handler.sendMessage(obtainMessage);
            m.d("sobot---sendHandlerCustomTimeTaskMessage" + this.t);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void g(Handler handler) {
        ZhiChiInitModeBase zhiChiInitModeBase;
        this.o++;
        if (this.c == 302 && (zhiChiInitModeBase = this.e) != null && this.o == Integer.parseInt(zhiChiInitModeBase.getUserOutTime()) * 60) {
            this.m = false;
            ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
            zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
            zhiChiMessageBase.setSenderType("2");
            ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
            zhiChiReplyAnswer.setMsgType("0");
            zhiChiMessageBase.setSenderName(this.g);
            String b = s.b(this.a, "sobot_user_tip_word", "");
            if (!TextUtils.isEmpty(b)) {
                zhiChiReplyAnswer.setMsg(b);
            } else if (!TextUtils.isEmpty(this.e.getUserTipWord())) {
                String replace = this.e.getUserTipWord().replace("\n", "<br/>");
                if (replace.startsWith("<br/>")) {
                    replace = replace.substring(5, replace.length());
                }
                if (replace.endsWith("<br/>")) {
                    replace = replace.substring(0, replace.length() - 5);
                }
                zhiChiReplyAnswer.setMsg(replace);
            } else {
                return;
            }
            zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
            zhiChiMessageBase.setSenderFace(this.x);
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 800;
            obtainMessage.obj = zhiChiMessageBase;
            handler.sendMessage(obtainMessage);
        }
    }

    /* access modifiers changed from: protected */
    public void a(e eVar, Message message) {
        a(eVar, (ZhiChiMessageBase) message.obj);
    }

    /* access modifiers changed from: protected */
    public void b(e eVar, Message message) {
        ZhiChiMessageBase zhiChiMessageBase = (ZhiChiMessageBase) message.obj;
        eVar.a(zhiChiMessageBase.getId(), zhiChiMessageBase);
        eVar.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void c(e eVar, Message message) {
        ZhiChiMessageBase zhiChiMessageBase = (ZhiChiMessageBase) message.obj;
        eVar.a(zhiChiMessageBase.getId(), zhiChiMessageBase.getSendSuccessState(), zhiChiMessageBase.getAnswer().getDuration());
        eVar.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void d(e eVar, Message message) {
        eVar.a(((ZhiChiMessageBase) message.obj).getId());
        eVar.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void a(e eVar, ZhiChiMessageBase zhiChiMessageBase) {
        eVar.a(zhiChiMessageBase);
        eVar.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void b(e eVar, ZhiChiMessageBase zhiChiMessageBase) {
        eVar.c(zhiChiMessageBase);
        eVar.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void a(e eVar, String str, int i, int i2) {
        eVar.a(str, i, i2);
        eVar.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public String h() {
        return this.x;
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        m.d("\u5934\u50cf\u5730\u5740\u662f" + str);
        this.x = str;
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, ZhiChiInitModeBase zhiChiInitModeBase, Handler handler, int i, int i2, String str3) {
        if (301 == i) {
            a(str, str2, zhiChiInitModeBase.getPartnerid(), zhiChiInitModeBase.getCid(), handler, i2, str3, this.f.getLocale());
            m.d("\u673a\u5668\u4eba\u6a21\u5f0f");
        } else if (302 == i) {
            a(str2, zhiChiInitModeBase.getPartnerid(), zhiChiInitModeBase.getCid(), handler, str);
            m.d("\u5ba2\u670d\u6a21\u5f0f");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$10  reason: invalid class name */
    public class AnonymousClass10 implements com.sobot.chat.core.http.c.a<ZhiChiMessageBase> {
        final /* synthetic */ String a;
        final /* synthetic */ Handler b;

        AnonymousClass10(String str, Handler handler) {
            this.a = str;
            this.b = handler;
        }

        public void a(ZhiChiMessageBase zhiChiMessageBase) {
            if (SobotChatBaseFragment.this.e()) {
                SobotChatBaseFragment.this.a(this.a, (String) null, this.b, 1, 1);
                String str = System.currentTimeMillis() + "";
                if (zhiChiMessageBase.getUstatus() == 0) {
                    zhiChiMessageBase.setId(str);
                    zhiChiMessageBase.setSenderName(SobotChatBaseFragment.this.e.getRobotName());
                    zhiChiMessageBase.setSender(SobotChatBaseFragment.this.e.getRobotName());
                    zhiChiMessageBase.setSenderFace(SobotChatBaseFragment.this.e.getRobotLogo());
                    zhiChiMessageBase.setSenderType("1");
                    if (SobotChatBaseFragment.this.b != null) {
                        SobotChatBaseFragment.this.b.b(zhiChiMessageBase);
                        SobotChatBaseFragment.this.b.notifyDataSetChanged();
                    }
                    SobotChatBaseFragment sobotChatBaseFragment = SobotChatBaseFragment.this;
                    sobotChatBaseFragment.a(sobotChatBaseFragment.e, 4);
                } else if (zhiChiMessageBase.getUstatus() == 1) {
                    SobotChatBaseFragment.this.a(this.a, (String) null, this.b, 0, 1);
                    m.d("\u5e94\u8be5\u662f\u4eba\u5de5\u72b6\u6001\u7ed9\u673a\u5668\u4eba\u53d1\u6d88\u606f\u62e6\u622a,\u8fde\u63a5\u901a\u9053\uff0c\u4fee\u6539\u5f53\u524d\u6a21\u5f0f\u4e3a\u4eba\u5de5\u6a21\u5f0f");
                    d.a(SobotChatBaseFragment.this.Z(), SobotChatBaseFragment.this.f.getPartnerid());
                    SobotChatBaseFragment.this.c = 302;
                } else {
                    SobotChatBaseFragment.this.h = true;
                    zhiChiMessageBase.setId(str);
                    zhiChiMessageBase.setSenderName(SobotChatBaseFragment.this.e.getRobotName());
                    zhiChiMessageBase.setSender(SobotChatBaseFragment.this.e.getRobotName());
                    zhiChiMessageBase.setSenderFace(SobotChatBaseFragment.this.e.getRobotLogo());
                    zhiChiMessageBase.setSenderType("1");
                    Message obtainMessage = this.b.obtainMessage();
                    obtainMessage.what = MetricsProto.MetricsEvent.DIALOG_WIFI_SAVED_AP_EDIT;
                    obtainMessage.obj = zhiChiMessageBase;
                    this.b.sendMessage(obtainMessage);
                }
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            if (SobotChatBaseFragment.this.e()) {
                SobotChatBaseFragment.this.a(this.a, (String) null, this.b, 0, 1);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, String str3, String str4, Handler handler, int i, String str5, String str6) {
        this.U.chatSendMsgToRoot(this.e.getRobotid(), str2, i, str5, str3, str4, new AnonymousClass10(str, handler));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$11  reason: invalid class name */
    public class AnonymousClass11 implements com.sobot.chat.core.http.c.a<CommonModelBase> {
        final /* synthetic */ String a;
        final /* synthetic */ Handler b;

        AnonymousClass11(String str, Handler handler) {
            this.a = str;
            this.b = handler;
        }

        public void a(CommonModelBase commonModelBase) {
            if (SobotChatBaseFragment.this.e()) {
                if (Boolean.valueOf(Boolean.valueOf(commonModelBase.getSwitchFlag()).booleanValue()).booleanValue()) {
                    com.sobot.chat.utils.d.a(SobotChatBaseFragment.this.a, new Intent("sobot_chat_check_switchflag"));
                }
                if ("2".equals(commonModelBase.getStatus())) {
                    SobotChatBaseFragment.this.a(this.a, (String) null, this.b, 0, 1);
                    SobotChatBaseFragment sobotChatBaseFragment = SobotChatBaseFragment.this;
                    sobotChatBaseFragment.a(sobotChatBaseFragment.e, 1);
                } else if ("1".equals(commonModelBase.getStatus()) && !TextUtils.isEmpty(this.a)) {
                    com.sobot.chat.utils.d.a(SobotChatBaseFragment.this.a, new Intent("sobot_chat_check_connchannel"));
                    SobotChatBaseFragment sobotChatBaseFragment2 = SobotChatBaseFragment.this;
                    sobotChatBaseFragment2.h = true;
                    sobotChatBaseFragment2.a(this.a, (String) null, this.b, 1, 1);
                }
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            if (SobotChatBaseFragment.this.e()) {
                SobotChatBaseFragment.this.a(this.a, (String) null, this.b, 0, 1);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, String str3, Handler handler, String str4) {
        this.U.sendMsgToCoutom(str, str2, str3, new AnonymousClass11(str4, handler));
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$12  reason: invalid class name */
    class AnonymousClass12 implements com.sobot.chat.core.http.c.a<CommonModelBase> {
        final /* synthetic */ String a;
        final /* synthetic */ ConsultingContent b;
        final /* synthetic */ Handler c;

        AnonymousClass12(String str, ConsultingContent consultingContent, Handler handler) {
            this.a = str;
            this.b = consultingContent;
            this.c = handler;
        }

        public void a(CommonModelBase commonModelBase) {
            if (SobotChatBaseFragment.this.e()) {
                if ("2".equals(commonModelBase.getStatus())) {
                    SobotChatBaseFragment sobotChatBaseFragment = SobotChatBaseFragment.this;
                    sobotChatBaseFragment.a(sobotChatBaseFragment.e, 1);
                } else if ("1".equals(commonModelBase.getStatus()) && !TextUtils.isEmpty(this.a)) {
                    SobotChatBaseFragment.this.h = true;
                    ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
                    zhiChiMessageBase.setId(this.a);
                    zhiChiMessageBase.setConsultingContent(this.b);
                    zhiChiMessageBase.setSenderType("0");
                    zhiChiMessageBase.setSendSuccessState(1);
                    ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
                    zhiChiReplyAnswer.setMsgType(Constants.VIA_REPORT_TYPE_CHAT_AIO);
                    zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
                    zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
                    Message obtainMessage = this.c.obtainMessage();
                    obtainMessage.what = MetricsProto.MetricsEvent.DIALOG_USER_EDIT_PROFILE;
                    obtainMessage.obj = zhiChiMessageBase;
                    this.c.sendMessage(obtainMessage);
                }
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            if (SobotChatBaseFragment.this.e()) {
                HashMap hashMap = new HashMap();
                hashMap.put("sendHttpCardMsg", exc.toString() + str);
                m.a(hashMap, "1");
                m.d("sendHttpCardMsg error:" + exc.toString());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, Handler handler, String str3, ConsultingContent consultingContent) {
        this.U.sendCardMsg(consultingContent, str, str2, new AnonymousClass12(str3, consultingContent, handler));
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$13  reason: invalid class name */
    class AnonymousClass13 implements com.sobot.chat.core.http.c.a<CommonModelBase> {
        final /* synthetic */ String a;
        final /* synthetic */ OrderCardContentModel b;
        final /* synthetic */ Handler c;

        AnonymousClass13(String str, OrderCardContentModel orderCardContentModel, Handler handler) {
            this.a = str;
            this.b = orderCardContentModel;
            this.c = handler;
        }

        public void a(CommonModelBase commonModelBase) {
            if (SobotChatBaseFragment.this.e()) {
                if ("2".equals(commonModelBase.getStatus())) {
                    SobotChatBaseFragment sobotChatBaseFragment = SobotChatBaseFragment.this;
                    sobotChatBaseFragment.a(sobotChatBaseFragment.e, 1);
                } else if ("1".equals(commonModelBase.getStatus()) && !TextUtils.isEmpty(this.a)) {
                    SobotChatBaseFragment.this.h = true;
                    ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
                    zhiChiMessageBase.setId(this.a);
                    zhiChiMessageBase.setOrderCardContent(this.b);
                    zhiChiMessageBase.setSenderType("0");
                    zhiChiMessageBase.setSendSuccessState(1);
                    ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
                    zhiChiReplyAnswer.setMsgType(Constants.VIA_REPORT_TYPE_CHAT_AUDIO);
                    zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
                    zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
                    Message obtainMessage = this.c.obtainMessage();
                    obtainMessage.what = MetricsProto.MetricsEvent.DIALOG_USER_EDIT_PROFILE;
                    obtainMessage.obj = zhiChiMessageBase;
                    this.c.sendMessage(obtainMessage);
                }
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            if (SobotChatBaseFragment.this.e()) {
                HashMap hashMap = new HashMap();
                hashMap.put("sendHttpOrderCardMsg", exc.toString() + str);
                m.a(hashMap, "1");
                m.d("sendHttpOrderCardMsg error:" + exc.toString());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, Handler handler, String str3, OrderCardContentModel orderCardContentModel) {
        this.U.sendOrderCardMsg(orderCardContentModel, str, str2, new AnonymousClass13(str3, orderCardContentModel, handler));
    }

    /* access modifiers changed from: protected */
    public void a(File file, Handler handler, ListView listView, e eVar, boolean z) {
        if (file != null && file.exists()) {
            m.d(file.toString());
            String lowerCase = file.getName().toLowerCase();
            if (lowerCase.endsWith(".gif") || lowerCase.endsWith(".jpg") || lowerCase.endsWith(".png")) {
                c.a(file.getAbsolutePath(), this.e.getCid(), this.e.getPartnerid(), handler, this.a, listView, eVar, z);
            } else if (file.length() > 52428800) {
                ae.a(getContext(), i("sobot_file_upload_failed"));
            } else if (!h.a(lowerCase, getContext(), "sobot_fileEndingAll")) {
                String valueOf = String.valueOf(System.currentTimeMillis());
                m.d("tmpMsgId:" + valueOf);
                this.U.addUploadFileTask(false, valueOf, this.e.getPartnerid(), this.e.getCid(), file.getAbsolutePath(), null);
                a(eVar, c.a(getContext(), valueOf, file));
                this.h = true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, SobotLocationModel sobotLocationModel, Handler handler, boolean z) {
        if (!e()) {
            return;
        }
        if (this.e == null || this.c == 302) {
            if (z) {
                str = System.currentTimeMillis() + "";
                a(c.a(str, sobotLocationModel), handler, 2);
            } else if (!TextUtils.isEmpty(str)) {
                a(str, handler, 2);
            } else {
                return;
            }
            this.U.sendLocation(this, sobotLocationModel, this.e.getPartnerid(), this.e.getCid(), new AnonymousClass14(str, handler));
        }
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$14  reason: invalid class name */
    class AnonymousClass14 implements com.sobot.chat.core.http.c.a<CommonModelBase> {
        final /* synthetic */ String a;
        final /* synthetic */ Handler b;

        AnonymousClass14(String str, Handler handler) {
            this.a = str;
            this.b = handler;
        }

        public void a(CommonModelBase commonModelBase) {
            if (SobotChatBaseFragment.this.e()) {
                if ("2".equals(commonModelBase.getStatus())) {
                    SobotChatBaseFragment.this.a(this.a, this.b, 0);
                    SobotChatBaseFragment sobotChatBaseFragment = SobotChatBaseFragment.this;
                    sobotChatBaseFragment.a(sobotChatBaseFragment.e, 1);
                } else if ("1".equals(commonModelBase.getStatus()) && !TextUtils.isEmpty(this.a)) {
                    SobotChatBaseFragment sobotChatBaseFragment2 = SobotChatBaseFragment.this;
                    sobotChatBaseFragment2.h = true;
                    sobotChatBaseFragment2.a(this.a, this.b, 1);
                }
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            if (SobotChatBaseFragment.this.e()) {
                SobotChatBaseFragment.this.a(this.a, this.b, 0);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(File file, Uri uri, e eVar) {
        String valueOf = String.valueOf(System.currentTimeMillis());
        m.d("tmpMsgId:" + valueOf);
        String a = n.a(file.getAbsolutePath());
        try {
            Activity Z = Z();
            String a2 = f.a(Z, uri, a + f.b(file.getAbsolutePath()), file.getAbsolutePath());
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(a2);
            Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(1, 2);
            String a3 = frameAtTime != null ? f.a(100, frameAtTime) : "";
            this.U.addUploadFileTask(true, valueOf, this.e.getPartnerid(), this.e.getCid(), a2, a3);
            a(eVar, c.a(getContext(), valueOf, new File(a2), a3));
            this.h = true;
        } catch (Exception e) {
            e.printStackTrace();
            ae.a(Z(), q.f(Z(), "sobot_pic_type_error"));
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, Handler handler, int i, int i2) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        zhiChiMessageBase.setId(str);
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        if (!TextUtils.isEmpty(str2)) {
            zhiChiReplyAnswer.setMsg(str2.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\n", "<br/>").replace("&lt;br/&gt;", "<br/>"));
        } else {
            zhiChiReplyAnswer.setMsg(str2);
        }
        zhiChiReplyAnswer.setMsgType("0");
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        zhiChiMessageBase.setSenderName(this.f.getUser_nick());
        zhiChiMessageBase.setSenderFace(this.f.getFace());
        zhiChiMessageBase.setSenderType("0");
        zhiChiMessageBase.setSendSuccessState(i);
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        Message obtainMessage = handler.obtainMessage();
        if (i2 == 0) {
            obtainMessage.what = MetricsProto.MetricsEvent.DIALOG_USER_EDIT_PROFILE;
        } else if (i2 == 1) {
            obtainMessage.what = 1602;
        } else if (i2 == 2) {
            obtainMessage.what = MetricsProto.MetricsEvent.BLUETOOTH_DIALOG_FRAGMENT;
        }
        obtainMessage.obj = zhiChiMessageBase;
        handler.sendMessage(obtainMessage);
    }

    /* access modifiers changed from: protected */
    public void a(ZhiChiMessageBase zhiChiMessageBase, Handler handler, int i) {
        if (zhiChiMessageBase != null) {
            Message obtainMessage = handler.obtainMessage();
            zhiChiMessageBase.setSendSuccessState(i);
            obtainMessage.what = MetricsProto.MetricsEvent.DIALOG_USER_EDIT_PROFILE;
            obtainMessage.obj = zhiChiMessageBase;
            handler.sendMessage(obtainMessage);
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, Handler handler, int i) {
        if (!TextUtils.isEmpty(str)) {
            ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
            zhiChiMessageBase.setId(str);
            zhiChiMessageBase.setSendSuccessState(i);
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 1602;
            obtainMessage.obj = zhiChiMessageBase;
            handler.sendMessage(obtainMessage);
        }
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$15  reason: invalid class name */
    class AnonymousClass15 implements ResultCallBack<ZhiChiMessage> {
        final /* synthetic */ Handler a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;

        @Override // com.sobot.chat.api.ResultCallBack
        public void onLoading(long j, long j2, boolean z) {
        }

        AnonymousClass15(Handler handler, String str, String str2, String str3) {
            this.a = handler;
            this.b = str;
            this.c = str2;
            this.d = str3;
        }

        /* renamed from: a */
        public void onSuccess(ZhiChiMessage zhiChiMessage) {
            if (SobotChatBaseFragment.this.e()) {
                m.d("\u53d1\u9001\u7ed9\u673a\u5668\u4eba\u8bed\u97f3---sobot---" + zhiChiMessage.getMsg());
                String str = System.currentTimeMillis() + "";
                SobotChatBaseFragment sobotChatBaseFragment = SobotChatBaseFragment.this;
                sobotChatBaseFragment.h = true;
                sobotChatBaseFragment.c(this.a);
                if (!TextUtils.isEmpty(zhiChiMessage.getMsg())) {
                    SobotChatBaseFragment.this.a(this.b, zhiChiMessage.getMsg(), this.a, 1, 2);
                } else {
                    SobotChatBaseFragment.this.a(this.b, this.c, this.d, 1, 1, this.a);
                }
                ZhiChiMessageBase data = zhiChiMessage.getData();
                if (data.getUstatus() == 0) {
                    SobotChatBaseFragment sobotChatBaseFragment2 = SobotChatBaseFragment.this;
                    sobotChatBaseFragment2.a(sobotChatBaseFragment2.e, 4);
                    return;
                }
                SobotChatBaseFragment.this.h = true;
                data.setId(str);
                data.setSenderName(SobotChatBaseFragment.this.e.getRobotName());
                data.setSender(SobotChatBaseFragment.this.e.getRobotName());
                data.setSenderFace(SobotChatBaseFragment.this.e.getRobotLogo());
                data.setSenderType("1");
                Message obtainMessage = this.a.obtainMessage();
                obtainMessage.what = MetricsProto.MetricsEvent.DIALOG_WIFI_SAVED_AP_EDIT;
                obtainMessage.obj = data;
                this.a.sendMessage(obtainMessage);
            }
        }

        @Override // com.sobot.chat.api.ResultCallBack
        public void onFailure(Exception exc, String str) {
            if (SobotChatBaseFragment.this.e()) {
                m.d("\u53d1\u9001\u8bed\u97f3error:" + str + "exception:" + exc.toString());
                SobotChatBaseFragment.this.a(this.b, this.c, this.d, 0, 1, this.a);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, String str3, String str4, String str5, Handler handler) {
        int i = this.c;
        if (i == 301) {
            this.U.sendVoiceToRobot(str5, str4, str3, this.e.getRobotid(), str2, new AnonymousClass15(handler, str, str5, str2));
        } else if (i == 302) {
            m.d("\u53d1\u9001\u7ed9\u4eba\u5de5\u8bed\u97f3---sobot---" + str5);
            this.U.sendFile(str3, str4, str5, str2, new AnonymousClass2(handler, str, str5, str2));
        }
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$2  reason: invalid class name */
    class AnonymousClass2 implements ResultCallBack<ZhiChiMessage> {
        final /* synthetic */ Handler a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;

        @Override // com.sobot.chat.api.ResultCallBack
        public void onLoading(long j, long j2, boolean z) {
        }

        AnonymousClass2(Handler handler, String str, String str2, String str3) {
            this.a = handler;
            this.b = str;
            this.c = str2;
            this.d = str3;
        }

        /* renamed from: a */
        public void onSuccess(ZhiChiMessage zhiChiMessage) {
            if (SobotChatBaseFragment.this.e()) {
                SobotChatBaseFragment sobotChatBaseFragment = SobotChatBaseFragment.this;
                sobotChatBaseFragment.h = true;
                sobotChatBaseFragment.c(this.a);
                SobotChatBaseFragment.this.a(this.b, this.c, this.d, 1, 1, this.a);
            }
        }

        @Override // com.sobot.chat.api.ResultCallBack
        public void onFailure(Exception exc, String str) {
            if (SobotChatBaseFragment.this.e()) {
                HashMap hashMap = new HashMap();
                hashMap.put("sendHttpCustomServiceMessage", exc.toString() + str);
                m.a(hashMap, "1");
                m.d("\u53d1\u9001\u8bed\u97f3error:" + str + "exception:" + exc.toString());
                SobotChatBaseFragment.this.a(this.b, this.c, this.d, 0, 1, this.a);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, String str3, int i, int i2, Handler handler) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiReplyAnswer.setMsg(str2);
        zhiChiReplyAnswer.setDuration(str3);
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        zhiChiMessageBase.setSenderType(Constants.VIA_REPORT_TYPE_CHAT_AUDIO);
        zhiChiMessageBase.setId(str);
        zhiChiMessageBase.setSendSuccessState(i);
        Message obtainMessage = handler.obtainMessage();
        if (i2 == 1) {
            obtainMessage.what = 2000;
        } else if (i2 == 2) {
            obtainMessage.what = 2001;
        } else if (i2 == 0) {
            obtainMessage.what = MetricsProto.MetricsEvent.DIALOG_USER_EDIT_PROFILE;
        }
        obtainMessage.obj = zhiChiMessageBase;
        handler.sendMessage(obtainMessage);
    }

    /* access modifiers changed from: protected */
    public void i() {
        k();
        j();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$3  reason: invalid class name */
    public class AnonymousClass3 extends TimerTask {
        AnonymousClass3() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (SobotChatBaseFragment.this.d == CustomerState.Online && SobotChatBaseFragment.this.c == 302 && !SobotChatBaseFragment.this.C) {
                try {
                    String n = SobotChatBaseFragment.this.n();
                    if (!TextUtils.isEmpty(n) && !n.equals(SobotChatBaseFragment.this.D)) {
                        SobotChatBaseFragment.this.D = n;
                        SobotChatBaseFragment.this.C = true;
                        SobotChatBaseFragment.this.U.input(SobotChatBaseFragment.this.e.getPartnerid(), n, new AnonymousClass1());
                    }
                } catch (Exception unused) {
                }
            }
        }

        /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$3$1  reason: invalid class name */
        class AnonymousClass1 implements com.sobot.chat.core.http.c.a<CommonModel> {
            AnonymousClass1() {
            }

            public void a(CommonModel commonModel) {
                SobotChatBaseFragment.this.C = false;
            }

            @Override // com.sobot.chat.core.http.c.a
            public void a(Exception exc, String str) {
                SobotChatBaseFragment.this.C = false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void j() {
        this.B = new Timer();
        this.E = new AnonymousClass3();
        this.B.schedule(this.E, 0, (long) (this.e.getInputTime() * 1000));
    }

    /* access modifiers changed from: protected */
    public void k() {
        Timer timer = this.B;
        if (timer != null) {
            timer.cancel();
            this.B = null;
        }
    }

    private void a() {
        this.F = (AudioManager) getContext().getSystemService("audio");
        this.G = (SensorManager) getContext().getSystemService("sensor");
        SensorManager sensorManager = this.G;
        if (sensorManager != null) {
            this.H = sensorManager.getDefaultSensor(8);
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            String lowerCase = Build.MODEL.toLowerCase();
            float f = sensorEvent.values[0];
            if (lowerCase.contains("mi")) {
                return;
            }
            if (((double) f) != 0.0d) {
                this.F.setSpeakerphoneOn(true);
                this.F.setMode(0);
                return;
            }
            this.F.setSpeakerphoneOn(false);
            if (Z() != null) {
                Z().setVolumeControlStream(0);
            }
            this.F.setMode(2);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    public boolean l() {
        ZhiChiInitModeBase zhiChiInitModeBase = this.e;
        return zhiChiInitModeBase != null && "1".equals(zhiChiInitModeBase.getIsblack());
    }

    /* access modifiers changed from: protected */
    public void m() {
        com.sobot.chat.core.channel.a.a(this.a).b();
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, int i, boolean z) {
        if (this.d == CustomerState.Queuing || this.k) {
            a(str, str2);
        } else if (!this.y) {
            this.k = true;
            this.y = true;
            this.U.queryFormConfig(this, this.e.getPartnerid(), new AnonymousClass4(z, str, str2, i));
        }
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$4  reason: invalid class name */
    class AnonymousClass4 implements com.sobot.chat.core.http.c.a<SobotQueryFormModel> {
        final /* synthetic */ boolean a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ int d;

        AnonymousClass4(boolean z, String str, String str2, int i) {
            this.a = z;
            this.b = str;
            this.c = str2;
            this.d = i;
        }

        public void a(SobotQueryFormModel sobotQueryFormModel) {
            SobotChatBaseFragment.this.y = false;
            if (SobotChatBaseFragment.this.e()) {
                if (!sobotQueryFormModel.isOpenFlag() || this.a || sobotQueryFormModel.getField() == null || sobotQueryFormModel.getField().size() <= 0) {
                    SobotChatBaseFragment.this.a(this.b, this.c, this.d);
                    return;
                }
                Intent intent = new Intent(SobotChatBaseFragment.this.a, SobotQueryFromActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sobot_intent_bundle_data_groupid", this.b);
                bundle.putString("sobot_intent_bundle_data_groupname", this.c);
                bundle.putSerializable("sobot_intent_bundle_data_field", sobotQueryFormModel);
                bundle.putSerializable("sobot_intent_bundle_data_uid", SobotChatBaseFragment.this.e.getPartnerid());
                bundle.putInt("sobot_intent_bundle_data_transfer_type", this.d);
                intent.putExtra("sobot_intent_bundle_data", bundle);
                SobotChatBaseFragment.this.startActivityForResult(intent, 104);
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            SobotChatBaseFragment.this.y = false;
            if (SobotChatBaseFragment.this.e()) {
                ae.a(SobotChatBaseFragment.this.a, str);
            }
        }
    }

    public void a(Handler handler, ZhiChiInitModeBase zhiChiInitModeBase, Information information) {
        boolean b = s.b(this.a, "sobot_is_exit", false);
        if (zhiChiInitModeBase != null) {
            this.i++;
            if (this.i != 1) {
                return;
            }
            if (zhiChiInitModeBase.getUstatus() != -1 || b) {
                ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
                ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
                if (zhiChiInitModeBase.isRobotHelloWordFlag()) {
                    String b2 = s.b(this.a, "sobot_robot_hello_word", "");
                    if (!TextUtils.isEmpty(b2) || !TextUtils.isEmpty(zhiChiInitModeBase.getRobotHelloWord())) {
                        if (!TextUtils.isEmpty(b2)) {
                            zhiChiReplyAnswer.setMsg(b2);
                        } else if (!TextUtils.isEmpty(zhiChiInitModeBase.getRobotHelloWord())) {
                            String replace = zhiChiInitModeBase.getRobotHelloWord().replace("\n", "<br/>");
                            if (replace.startsWith("<br/>")) {
                                replace = replace.substring(5, replace.length());
                            }
                            if (replace.endsWith("<br/>")) {
                                replace = replace.substring(0, replace.length() - 5);
                            }
                            zhiChiReplyAnswer.setMsg(replace);
                        } else {
                            return;
                        }
                        zhiChiReplyAnswer.setMsgType("0");
                        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
                        zhiChiMessageBase.setSenderFace(zhiChiInitModeBase.getRobotLogo());
                        zhiChiMessageBase.setSender(zhiChiInitModeBase.getRobotName());
                        zhiChiMessageBase.setSenderType("30");
                        zhiChiMessageBase.setSenderName(zhiChiInitModeBase.getRobotName());
                        Message obtainMessage = handler.obtainMessage();
                        obtainMessage.what = MetricsProto.MetricsEvent.DIALOG_WIFI_SAVED_AP_EDIT;
                        obtainMessage.obj = zhiChiMessageBase;
                        handler.sendMessage(obtainMessage);
                    }
                }
                if (1 == zhiChiInitModeBase.getGuideFlag()) {
                    this.U.robotGuide(this, zhiChiInitModeBase.getPartnerid(), zhiChiInitModeBase.getRobotid(), information.getFaqId(), new AnonymousClass5(zhiChiInitModeBase, handler, information));
                    return;
                }
                b(handler, zhiChiInitModeBase, information);
                a(information);
                f(handler);
                return;
            }
            f(handler);
        }
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$5  reason: invalid class name */
    class AnonymousClass5 implements com.sobot.chat.core.http.c.a<ZhiChiMessageBase> {
        final /* synthetic */ ZhiChiInitModeBase a;
        final /* synthetic */ Handler b;
        final /* synthetic */ Information c;

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
        }

        AnonymousClass5(ZhiChiInitModeBase zhiChiInitModeBase, Handler handler, Information information) {
            this.a = zhiChiInitModeBase;
            this.b = handler;
            this.c = information;
        }

        public void a(ZhiChiMessageBase zhiChiMessageBase) {
            if (SobotChatBaseFragment.this.e() && SobotChatBaseFragment.this.c == 301) {
                zhiChiMessageBase.setSenderFace(this.a.getRobotLogo());
                zhiChiMessageBase.setSenderType("27");
                Message obtainMessage = this.b.obtainMessage();
                obtainMessage.what = MetricsProto.MetricsEvent.DIALOG_WIFI_SAVED_AP_EDIT;
                obtainMessage.obj = zhiChiMessageBase;
                this.b.sendMessage(obtainMessage);
                SobotChatBaseFragment.this.b(this.b, this.a, this.c);
                SobotChatBaseFragment.this.a(this.c);
                SobotChatBaseFragment.this.f(this.b);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void f(Handler handler) {
        if (this.e.getMsgFlag() == 0 && !TextUtils.isEmpty(this.e.getCustomerId())) {
            this.j = true;
            this.U.checkUserTicketInfo(this, this.e.getPartnerid(), this.e.getCompanyId(), this.e.getCustomerId(), new AnonymousClass6(handler));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$6  reason: invalid class name */
    public class AnonymousClass6 implements com.sobot.chat.core.http.c.a<SobotUserTicketInfoFlag> {
        final /* synthetic */ Handler a;

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
        }

        AnonymousClass6(Handler handler) {
            this.a = handler;
        }

        public void a(SobotUserTicketInfoFlag sobotUserTicketInfoFlag) {
            if (sobotUserTicketInfoFlag.isExistFlag()) {
                ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
                zhiChiMessageBase.setSenderType(Constants.VIA_REPORT_TYPE_CHAT_AIO);
                ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
                zhiChiReplyAnswer.setRemindType(9);
                zhiChiReplyAnswer.setMsg("<font color='#ffacb5c4'>" + SobotChatBaseFragment.this.i("sobot_new_ticket_info") + " </font> <a href='sobot:SobotTicketInfo'  target='_blank' >" + SobotChatBaseFragment.this.i("sobot_new_ticket_info_update") + "</a> ");
                zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
                Message obtainMessage = this.a.obtainMessage();
                obtainMessage.what = MetricsProto.MetricsEvent.DIALOG_USER_EDIT_PROFILE;
                obtainMessage.obj = zhiChiMessageBase;
                this.a.sendMessage(obtainMessage);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(Information information) {
        SobotAutoSendMsgMode autoSendMsgMode = information.getAutoSendMsgMode();
        if (!TextUtils.isEmpty(autoSendMsgMode.getContent())) {
            int i = this.c;
            if (i == 301) {
                if (autoSendMsgMode == SobotAutoSendMsgMode.SendToRobot || autoSendMsgMode == SobotAutoSendMsgMode.SendToAll) {
                    b(autoSendMsgMode.getContent());
                }
            } else if (i != 302) {
            } else {
                if ((autoSendMsgMode == SobotAutoSendMsgMode.SendToOperator || autoSendMsgMode == SobotAutoSendMsgMode.SendToAll) && this.d == CustomerState.Online) {
                    b(autoSendMsgMode.getContent());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(Handler handler, ZhiChiInitModeBase zhiChiInitModeBase, Information information) {
        if (information.getMargs() != null && information.getMargs().size() != 0) {
            this.U.questionRecommend(this, zhiChiInitModeBase.getPartnerid(), information.getMargs(), new AnonymousClass7(zhiChiInitModeBase, handler));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$7  reason: invalid class name */
    public class AnonymousClass7 implements com.sobot.chat.core.http.c.a<SobotQuestionRecommend> {
        final /* synthetic */ ZhiChiInitModeBase a;
        final /* synthetic */ Handler b;

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
        }

        AnonymousClass7(ZhiChiInitModeBase zhiChiInitModeBase, Handler handler) {
            this.a = zhiChiInitModeBase;
            this.b = handler;
        }

        public void a(SobotQuestionRecommend sobotQuestionRecommend) {
            if (SobotChatBaseFragment.this.e() && sobotQuestionRecommend != null && SobotChatBaseFragment.this.c == 301) {
                ZhiChiMessageBase a = c.a(this.a, sobotQuestionRecommend);
                Message obtainMessage = this.b.obtainMessage();
                obtainMessage.what = MetricsProto.MetricsEvent.DIALOG_WIFI_SAVED_AP_EDIT;
                obtainMessage.obj = a;
                this.b.sendMessage(obtainMessage);
            }
        }
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatBaseFragment$16  reason: invalid class name */
    class AnonymousClass16 implements View.OnClickListener {
        AnonymousClass16() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (y.a != null) {
                com.sobot.chat.listener.a aVar = y.a;
                aVar.a(view.getTag() + "");
                return;
            }
            if (y.b != null) {
                com.sobot.chat.listener.c cVar = y.b;
                if (cVar.a(view.getTag() + "")) {
                    return;
                }
            }
            Intent intent = new Intent(SobotChatBaseFragment.this.getContext(), WebViewActivity.class);
            intent.putExtra("url", view.getTag() + "");
            SobotChatBaseFragment.this.Z().startActivity(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2) {
        a(str, str2, 0);
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, boolean z) {
        a(str, str2, (String) null, (String) null, z, 0);
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, String str3, String str4, boolean z) {
        a(str, str2, str3, str4, z, 0);
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, int i) {
        a(str, str2, (String) null, (String) null, true, i);
    }
}
