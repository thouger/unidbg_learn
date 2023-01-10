package com.sobot.chat.conversation;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.telecom.Logging.Session;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.internal.logging.nano.MetricsProto;
import com.sobot.chat.activity.SobotCameraActivity;
import com.sobot.chat.activity.SobotChooseFileActivity;
import com.sobot.chat.activity.SobotPostLeaveMsgActivity;
import com.sobot.chat.activity.SobotSkillGroupActivity;
import com.sobot.chat.activity.WebViewActivity;
import com.sobot.chat.adapter.e;
import com.sobot.chat.api.ZhiChiApi;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.api.enumtype.CustomerState;
import com.sobot.chat.api.enumtype.SobotAutoSendMsgMode;
import com.sobot.chat.api.enumtype.SobotChatStatusMode;
import com.sobot.chat.api.model.CommonModel;
import com.sobot.chat.api.model.CommonModelBase;
import com.sobot.chat.api.model.ConsultingContent;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.OrderCardContentModel;
import com.sobot.chat.api.model.SobotCommentParam;
import com.sobot.chat.api.model.SobotConnCusParam;
import com.sobot.chat.api.model.SobotEvaluateModel;
import com.sobot.chat.api.model.SobotKeyWordTransfer;
import com.sobot.chat.api.model.SobotLableInfoList;
import com.sobot.chat.api.model.SobotLeaveMsgConfig;
import com.sobot.chat.api.model.SobotLocationModel;
import com.sobot.chat.api.model.SobotRobot;
import com.sobot.chat.api.model.SobotTransferOperatorParam;
import com.sobot.chat.api.model.ZhiChiCidsModel;
import com.sobot.chat.api.model.ZhiChiGroup;
import com.sobot.chat.api.model.ZhiChiGroupBase;
import com.sobot.chat.api.model.ZhiChiHistoryMessage;
import com.sobot.chat.api.model.ZhiChiHistoryMessageBase;
import com.sobot.chat.api.model.ZhiChiInitModeBase;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.api.model.ZhiChiPushMessage;
import com.sobot.chat.api.model.ZhiChiReplyAnswer;
import com.sobot.chat.listener.NoDoubleClickListener;
import com.sobot.chat.presenter.b;
import com.sobot.chat.server.SobotSessionServer;
import com.sobot.chat.utils.ExtAudioRecorder;
import com.sobot.chat.utils.ZhiChiConfig;
import com.sobot.chat.utils.ab;
import com.sobot.chat.utils.ac;
import com.sobot.chat.utils.ad;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.l;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.n;
import com.sobot.chat.utils.o;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.utils.s;
import com.sobot.chat.utils.t;
import com.sobot.chat.utils.y;
import com.sobot.chat.utils.z;
import com.sobot.chat.viewHolder.RichTextMessageHolder;
import com.sobot.chat.viewHolder.VoiceMessageHolder;
import com.sobot.chat.viewHolder.f;
import com.sobot.chat.viewHolder.g;
import com.sobot.chat.viewHolder.h;
import com.sobot.chat.viewHolder.i;
import com.sobot.chat.viewHolder.j;
import com.sobot.chat.viewHolder.k;
import com.sobot.chat.voice.AudioPlayPresenter;
import com.sobot.chat.widget.ClearHistoryDialog;
import com.sobot.chat.widget.ContainsEmojiEditText;
import com.sobot.chat.widget.DropdownListView;
import com.sobot.chat.widget.dialog.SobotRobotListDialog;
import com.sobot.chat.widget.emoji.DisplayEmojiRules;
import com.sobot.chat.widget.image.SobotRCImageView;
import com.sobot.chat.widget.kpswitch.CustomeChattingPanel;
import com.sobot.chat.widget.kpswitch.util.c;
import com.sobot.chat.widget.kpswitch.view.ChattingPanelEmoticonView;
import com.sobot.chat.widget.kpswitch.view.ChattingPanelUploadView;
import com.sobot.chat.widget.kpswitch.widget.KPSwitchPanelLinearLayout;
import com.tencent.connect.common.Constants;
import com.umeng.message.common.inter.ITagManager;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class SobotChatFragment extends SobotChatBaseFragment implements View.OnClickListener, e.a, ContainsEmojiEditText.d, DropdownListView.b, SobotRobotListDialog.a, ChattingPanelEmoticonView.a, ChattingPanelUploadView.a {
    private static String aV;
    private static int aW;
    public TextView A;
    public LinearLayout B;
    public TextView C;
    public TextView D;
    public ProgressBar E;
    public RelativeLayout F;
    public TextView G;
    public RelativeLayout H;
    protected int I = -1;
    protected Timer J;
    protected TimerTask K;
    protected int L = 0;
    protected String M = "00";
    boolean N;
    AudioPlayPresenter O = null;
    com.sobot.chat.voice.a P = null;
    public int Q = 0;
    ZhiChiMessageBase R;
    String S;
    public Handler T = new AnonymousClass17();
    private TextView X;
    private TextView Y;
    private TextView Z;
    private KPSwitchPanelLinearLayout aA;
    private LinearLayout aB;
    private LinearLayout aC;
    private LinearLayout aD;
    private TextView aE;
    private RelativeLayout aF;
    private ImageView aG;
    private LinearLayout aH;
    private RelativeLayout aI;
    private TextView aJ;
    private TextView aK;
    private LinearLayout aL;
    private TextView aM;
    private com.sobot.chat.widget.dialog.e aN;
    private SobotRobotListDialog aO;
    private HorizontalScrollView aP;
    private LinearLayout aQ;
    private TextView aR;
    private List<ZhiChiMessageBase> aS = new ArrayList();
    private int aT = 0;
    private List<ZhiChiGroupBase> aU;
    private boolean aX = true;
    private boolean aY = false;
    private boolean aZ = true;
    private TextView aa;
    private TextView ab;
    private ProgressBar ac;
    private TextView ad;
    private ImageView ae;
    private Button af;
    private RelativeLayout ag;
    private FrameLayout ah;
    private DropdownListView ai;
    private ContainsEmojiEditText aj;
    private Button ak;
    private ImageButton al;
    private View am;
    private TextView an;
    private Button ao;
    private ImageButton ap;
    private TextView aq;
    private LinearLayout ar;
    private ImageView as;
    private ImageView at;
    private ImageView au;
    private ImageView av;
    private ImageButton aw;
    private ImageButton ax;
    private TextView ay;
    private AnimationDrawable az;
    private int ba = 0;
    private int bb = 0;
    private int bc = 0;
    private int bd = 60;
    private int be = (this.bd - 10);
    private String bf = "";
    private int bg = 0;
    private String bh = null;
    private ExtAudioRecorder bi;
    private List<String> bj = new ArrayList();
    private int bk = 0;
    private int bl = 0;
    private boolean bm = false;
    private boolean bn = false;
    private boolean bo = false;
    private int bp = 0;
    private ViewTreeObserver.OnGlobalLayoutListener bq;
    private MyMessageReceiver br;
    private LocalBroadcastManager bs;
    private a bt;
    private com.sobot.chat.presenter.b bu;
    private int bv;
    private String bw;
    private com.sobot.chat.widget.dialog.a bx;
    private com.sobot.chat.widget.dialog.b by;
    public LinearLayout x;
    public TextView y;
    public SobotRCImageView z;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void ak() {
    }

    static /* synthetic */ int W(SobotChatFragment sobotChatFragment) {
        int i = sobotChatFragment.bk;
        sobotChatFragment.bk = i + 1;
        return i;
    }

    public static SobotChatFragment a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putBundle("sobot_bundle_information", bundle);
        SobotChatFragment sobotChatFragment = new SobotChatFragment();
        sobotChatFragment.setArguments(bundle2);
        return sobotChatFragment;
    }

    @Override // com.sobot.chat.fragment.SobotBaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        Bundle bundle2;
        Serializable serializable;
        super.onCreate(bundle);
        m.d("onCreate");
        if (getArguments() != null && (bundle2 = getArguments().getBundle("sobot_bundle_information")) != null && (serializable = bundle2.getSerializable("sobot_bundle_info")) != null && (serializable instanceof Information)) {
            this.f = (Information) serializable;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(g("sobot_chat_fragment"), viewGroup, false);
        d(inflate);
        return inflate;
    }

    @Override // com.sobot.chat.conversation.SobotChatBaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.f == null) {
            m.c("\u521d\u59cb\u5316\u53c2\u6570\u4e0d\u80fd\u4e3a\u7a7a");
            d();
        } else if (TextUtils.isEmpty(this.f.getApp_key())) {
            m.c("\u60a8\u7684AppKey\u4e3a\u7a7a");
            d();
        } else {
            s.a(this.a, "sobot_current_im_appid", this.f.getApp_key());
            c.a(this.a, this.f);
            o();
        }
    }

    @Override // com.sobot.chat.conversation.SobotChatBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.aR != null) {
            if (!this.f.isShowCloseBtn() || this.c != 302) {
                this.aR.setVisibility(8);
            } else {
                this.aR.setVisibility(0);
            }
        }
        s.a(this.a, "sobot_current_im_appid", this.f.getApp_key());
        Intent intent = new Intent(this.a, SobotSessionServer.class);
        intent.putExtra("sobot_current_im_partnerid", this.f.getPartnerid());
        ab.a(this.a, intent);
        com.sobot.chat.core.channel.a.a(this.a).a(this.f.getApp_key()).clearCache();
    }

    @Override // com.sobot.chat.conversation.SobotChatBaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        if (this.e != null) {
            if (!this.aX) {
                aD();
            } else {
                m();
            }
            c.a(this.a, this.f, this.f.getApp_key(), this.e, this.aS);
        }
        super.onPause();
    }

    @Override // com.sobot.chat.fragment.SobotBaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (!this.h) {
            s.a(Z(), "sobot_finish_curtime", System.currentTimeMillis());
        }
        w();
        try {
            if (Z() != null) {
                Z().unregisterReceiver(this.br);
                com.sobot.chat.widget.kpswitch.util.c.a(Z(), this.bq);
            }
            if (this.bs != null) {
                this.bs.unregisterReceiver(this.bt);
            }
        } catch (Exception unused) {
        }
        f();
        g();
        ag();
        com.sobot.chat.utils.b.c();
        com.sobot.chat.core.http.i.b.a().d();
        this.bu.a();
        com.sobot.chat.widget.dialog.e eVar = this.aN;
        if (eVar != null && eVar.isShowing()) {
            this.aN.dismiss();
        }
        SobotRobotListDialog sobotRobotListDialog = this.aO;
        if (sobotRobotListDialog != null && sobotRobotListDialog.isShowing()) {
            this.aO.dismiss();
        }
        if (y.c != null) {
            y.c.a(this.d);
        }
        super.onDestroyView();
    }

    private void d(View view) {
        if (view != null) {
            this.H = (RelativeLayout) view.findViewById(e("sobot_layout_titlebar"));
            this.x = (LinearLayout) view.findViewById(e("sobot_header_center_ll"));
            this.y = (TextView) view.findViewById(e("sobot_text_title"));
            this.z = (SobotRCImageView) view.findViewById(e("sobot_avatar_iv"));
            this.A = (TextView) view.findViewById(e("sobot_title_conn_status"));
            this.B = (LinearLayout) view.findViewById(e("sobot_container_conn_status"));
            this.C = (TextView) view.findViewById(e("sobot_tv_right_second"));
            this.D = (TextView) view.findViewById(e("sobot_tv_right_third"));
            this.E = (ProgressBar) view.findViewById(e("sobot_conn_loading"));
            this.F = (RelativeLayout) view.findViewById(e("sobot_net_status_remide"));
            this.G = (TextView) view.findViewById(e("sobot_net_not_connect"));
            this.G.setText(q.f(Z(), "sobot_network_unavailable"));
            this.H.setVisibility(8);
            this.Y = (TextView) view.findViewById(e("notReadInfo"));
            this.ag = (RelativeLayout) view.findViewById(e("sobot_chat_main"));
            this.ah = (FrameLayout) view.findViewById(e("sobot_welcome"));
            this.ad = (TextView) view.findViewById(e("sobot_txt_loading"));
            this.ab = (TextView) view.findViewById(e("sobot_textReConnect"));
            this.ab.setText(q.f(Z(), "sobot_network_unavailable"));
            this.ac = (ProgressBar) view.findViewById(e("sobot_image_view"));
            this.aG = (ImageView) view.findViewById(e("sobot_image_reloading"));
            this.ae = (ImageView) view.findViewById(e("sobot_icon_nonet"));
            this.af = (Button) view.findViewById(e("sobot_btn_reconnect"));
            this.af.setText(q.f(Z(), "sobot_reunicon"));
            this.af.setOnClickListener(new AnonymousClass1());
            this.ai = (DropdownListView) view.findViewById(e("sobot_lv_message"));
            if (Build.VERSION.SDK_INT >= 8) {
                this.ai.setOverScrollMode(2);
            }
            this.aj = (ContainsEmojiEditText) view.findViewById(e("sobot_et_sendmessage"));
            this.aj.setVisibility(0);
            this.ak = (Button) view.findViewById(e("sobot_btn_send"));
            this.ak.setText(q.f(Z(), "sobot_button_send"));
            this.al = (ImageButton) view.findViewById(e("sobot_btn_set_mode_rengong"));
            this.am = view.findViewById(e("sobot_view_model_split"));
            this.an = (TextView) view.findViewById(e("send_voice_robot_hint"));
            this.an.setHint(q.f(Z(), "sobot_robot_voice_hint"));
            this.an.setVisibility(8);
            this.ao = (Button) view.findViewById(e("sobot_btn_upload_view"));
            this.ap = (ImageButton) view.findViewById(e("sobot_btn_emoticon_view"));
            this.aw = (ImageButton) view.findViewById(e("sobot_btn_model_edit"));
            this.ax = (ImageButton) view.findViewById(e("sobot_btn_model_voice"));
            this.aA = (KPSwitchPanelLinearLayout) view.findViewById(e("sobot_panel_root"));
            this.aB = (LinearLayout) view.findViewById(e("sobot_btn_press_to_speak"));
            this.aC = (LinearLayout) view.findViewById(e("sobot_edittext_layout"));
            this.aE = (TextView) view.findViewById(e("sobot_recording_hint"));
            this.aD = (LinearLayout) view.findViewById(e("sobot_recording_container"));
            this.ar = (LinearLayout) view.findViewById(e("sobot_voice_top_image"));
            this.as = (ImageView) view.findViewById(e("sobot_image_endVoice"));
            this.au = (ImageView) view.findViewById(e("sobot_mic_image_animate"));
            this.aq = (TextView) view.findViewById(e("sobot_voiceTimeLong"));
            this.ay = (TextView) view.findViewById(e("sobot_txt_speak_content"));
            this.ay.setText(i("sobot_press_say"));
            this.av = (ImageView) view.findViewById(e("sobot_recording_timeshort"));
            this.at = (ImageView) view.findViewById(e("sobot_mic_image"));
            this.aF = (RelativeLayout) view.findViewById(e("sobot_ll_restart_talk"));
            this.aa = (TextView) view.findViewById(e("sobot_txt_restart_talk"));
            this.aa.setText(q.f(Z(), "sobot_restart_talk"));
            this.Z = (TextView) view.findViewById(e("sobot_tv_message"));
            this.Z.setText(q.f(Z(), "sobot_str_bottom_message"));
            this.X = (TextView) view.findViewById(e("sobot_tv_satisfaction"));
            this.X.setText(q.f(Z(), "sobot_str_bottom_satisfaction"));
            this.aH = (LinearLayout) view.findViewById(e("sobot_ll_bottom"));
            this.aL = (LinearLayout) view.findViewById(e("sobot_ll_switch_robot"));
            this.aM = (TextView) view.findViewById(e("sobot_tv_switch_robot"));
            this.aM.setText(q.f(Z(), "sobot_switch_business"));
            this.aI = (RelativeLayout) view.findViewById(e("sobot_announcement"));
            this.aJ = (TextView) view.findViewById(e("sobot_announcement_right_icon"));
            this.aK = (TextView) view.findViewById(e("sobot_announcement_title"));
            this.aK.setSelected(true);
            this.aP = (HorizontalScrollView) view.findViewById(e("sobot_custom_menu"));
            this.aP.setVisibility(8);
            this.aQ = (LinearLayout) view.findViewById(e("sobot_custom_menu_linearlayout"));
            aK();
            this.bu = com.sobot.chat.presenter.b.a(this, getContext());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotChatFragment.this.ab.setVisibility(8);
            SobotChatFragment.this.ae.setVisibility(8);
            SobotChatFragment.this.af.setVisibility(8);
            SobotChatFragment.this.ac.setVisibility(0);
            SobotChatFragment.this.ad.setVisibility(0);
            SobotChatFragment.this.aj();
        }
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$17  reason: invalid class name */
    class AnonymousClass17 extends Handler {
        AnonymousClass17() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (SobotChatFragment.this.e()) {
                int i = message.what;
                if (i == 613) {
                    ZhiChiMessageBase zhiChiMessageBase = (ZhiChiMessageBase) message.obj;
                    SobotChatFragment.this.b.b(zhiChiMessageBase.getId(), zhiChiMessageBase);
                    SobotChatFragment.this.b.notifyDataSetChanged();
                    SobotChatFragment.this.ai.setSelection(SobotChatFragment.this.b.getCount());
                } else if (i == 800) {
                    SobotChatFragment sobotChatFragment = SobotChatFragment.this;
                    sobotChatFragment.a(sobotChatFragment.b, message);
                    SobotChatFragment.this.f();
                    m.d("\u5ba2\u6237\u7684\u5b9a\u65f6\u4efb\u52a1\u7684\u65f6\u95f4  \u505c\u6b62\u5b9a\u65f6\u4efb\u52a1\uff1a" + SobotChatFragment.this.o);
                } else if (i == 802) {
                    SobotChatFragment sobotChatFragment2 = SobotChatFragment.this;
                    sobotChatFragment2.a(sobotChatFragment2.b, message);
                    m.d("\u5ba2\u670d\u7684\u5b9a\u65f6\u4efb\u52a1:" + SobotChatFragment.this.t);
                    SobotChatFragment.this.g();
                } else if (i == 1602) {
                    SobotChatFragment sobotChatFragment3 = SobotChatFragment.this;
                    sobotChatFragment3.b(sobotChatFragment3.b, message);
                } else if (i != 1000) {
                    if (i != 1001) {
                        if (i == 2000) {
                            SobotChatFragment sobotChatFragment4 = SobotChatFragment.this;
                            sobotChatFragment4.c(sobotChatFragment4.b, message);
                        } else if (i != 2001) {
                            switch (i) {
                                case 401:
                                    SobotChatFragment sobotChatFragment5 = SobotChatFragment.this;
                                    sobotChatFragment5.a(sobotChatFragment5.b, (String) message.obj, 0, 0);
                                    return;
                                case 402:
                                    SobotChatFragment sobotChatFragment6 = SobotChatFragment.this;
                                    sobotChatFragment6.b(sobotChatFragment6.T);
                                    SobotChatFragment sobotChatFragment7 = SobotChatFragment.this;
                                    sobotChatFragment7.a(sobotChatFragment7.b, (String) message.obj, 1, 0);
                                    return;
                                case 403:
                                    int i2 = message.arg1;
                                    SobotChatFragment sobotChatFragment8 = SobotChatFragment.this;
                                    sobotChatFragment8.a(sobotChatFragment8.b, (String) message.obj, 2, i2);
                                    return;
                                default:
                                    switch (i) {
                                        case MetricsProto.MetricsEvent.DIALOG_USER_EDIT_PROFILE /* 601 */:
                                            SobotChatFragment sobotChatFragment9 = SobotChatFragment.this;
                                            sobotChatFragment9.a(sobotChatFragment9.b, message);
                                            SobotChatFragment.this.ai.setSelection(SobotChatFragment.this.b.getCount());
                                            return;
                                        case MetricsProto.MetricsEvent.DIALOG_WIFI_SAVED_AP_EDIT /* 602 */:
                                            ZhiChiMessageBase zhiChiMessageBase2 = (ZhiChiMessageBase) message.obj;
                                            zhiChiMessageBase2.setT(Calendar.getInstance().getTime().getTime() + "");
                                            if ((SobotChatFragment.this.I == 3 || SobotChatFragment.this.I == 4) && SobotChatFragment.this.e != null && c.a(SobotChatFragment.this.e.getManualType(), zhiChiMessageBase2.getAnswerType())) {
                                                zhiChiMessageBase2.setShowTransferBtn(true);
                                            }
                                            if ("1".equals(zhiChiMessageBase2.getAnswerType()) || "9".equals(zhiChiMessageBase2.getAnswerType()) || Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE.equals(zhiChiMessageBase2.getAnswerType()) || Constants.VIA_REPORT_TYPE_SET_AVATAR.equals(zhiChiMessageBase2.getAnswerType()) || Constants.VIA_REPORT_TYPE_MAKE_FRIEND.equals(zhiChiMessageBase2.getAnswerType()) || (!TextUtils.isEmpty(zhiChiMessageBase2.getAnswerType()) && zhiChiMessageBase2.getAnswerType().startsWith("152"))) {
                                                if (SobotChatFragment.this.e == null || !SobotChatFragment.this.e.isRealuateFlag()) {
                                                    zhiChiMessageBase2.setRevaluateState(0);
                                                } else {
                                                    zhiChiMessageBase2.setRevaluateState(1);
                                                }
                                            }
                                            if (!(zhiChiMessageBase2.getAnswer() == null || zhiChiMessageBase2.getAnswer().getMultiDiaRespInfo() == null || !zhiChiMessageBase2.getAnswer().getMultiDiaRespInfo().getEndFlag())) {
                                                SobotChatFragment.this.aH();
                                            }
                                            SobotChatFragment.this.b.b(zhiChiMessageBase2);
                                            SobotKeyWordTransfer sobotKeyWordTransfer = zhiChiMessageBase2.getSobotKeyWordTransfer();
                                            if (sobotKeyWordTransfer != null) {
                                                if (SobotChatFragment.this.I != 1) {
                                                    if (1 == sobotKeyWordTransfer.getTransferFlag()) {
                                                        SobotChatFragment.this.a(sobotKeyWordTransfer.getGroupId(), sobotKeyWordTransfer.getKeyword(), sobotKeyWordTransfer.getKeywordId(), sobotKeyWordTransfer.isQueueFlag());
                                                    } else if (2 == sobotKeyWordTransfer.getTransferFlag()) {
                                                        ZhiChiMessageBase zhiChiMessageBase3 = new ZhiChiMessageBase();
                                                        zhiChiMessageBase3.setSenderFace(zhiChiMessageBase2.getSenderFace());
                                                        zhiChiMessageBase3.setSenderType("31");
                                                        zhiChiMessageBase3.setSenderName(zhiChiMessageBase2.getSenderName());
                                                        zhiChiMessageBase3.setSobotKeyWordTransfer(sobotKeyWordTransfer);
                                                        SobotChatFragment.this.b.b(zhiChiMessageBase3);
                                                    } else if (3 == sobotKeyWordTransfer.getTransferFlag()) {
                                                        SobotChatFragment.this.a("", "", "", sobotKeyWordTransfer.isQueueFlag());
                                                    }
                                                }
                                            } else if (zhiChiMessageBase2.getTransferType() == 1 || zhiChiMessageBase2.getTransferType() == 2 || zhiChiMessageBase2.getTransferType() == 5) {
                                                SobotChatFragment.this.b.b(c.a(SobotChatFragment.this.getContext(), SobotChatFragment.this.e));
                                                SobotChatFragment.this.a((String) null, (String) null, (String) null, true, zhiChiMessageBase2.getTransferType());
                                            }
                                            SobotChatFragment.this.b.notifyDataSetChanged();
                                            if (com.sobot.chat.core.channel.a.a(SobotChatFragment.this.a).a(SobotChatFragment.this.f.getApp_key()).getInitModel() != null) {
                                                com.sobot.chat.core.channel.a.a(SobotChatFragment.this.a).a(SobotChatFragment.this.f.getApp_key()).addMessage(zhiChiMessageBase2);
                                            }
                                            if (SobotChatFragment.this.I == 3 && ("3".equals(zhiChiMessageBase2.getAnswerType()) || "4".equals(zhiChiMessageBase2.getAnswerType()))) {
                                                SobotChatFragment.this.am();
                                            }
                                            SobotChatFragment.this.ap();
                                            return;
                                        case MetricsProto.MetricsEvent.DIALOG_WIFI_AP_EDIT /* 603 */:
                                            int i3 = message.arg1;
                                            SobotChatFragment.this.ay.setText(SobotChatFragment.this.i("sobot_press_say"));
                                            SobotChatFragment.this.bg = 0;
                                            SobotChatFragment.this.aD.setVisibility(8);
                                            if (i3 == 0) {
                                                for (int size = SobotChatFragment.this.aS.size() - 1; size > 0; size--) {
                                                    if (!TextUtils.isEmpty(((ZhiChiMessageBase) SobotChatFragment.this.aS.get(size)).getSenderType()) && Integer.parseInt(((ZhiChiMessageBase) SobotChatFragment.this.aS.get(size)).getSenderType()) == 8) {
                                                        SobotChatFragment.this.aS.remove(size);
                                                        return;
                                                    }
                                                }
                                                return;
                                            }
                                            return;
                                        default:
                                            return;
                                    }
                            }
                        } else {
                            SobotChatFragment sobotChatFragment10 = SobotChatFragment.this;
                            sobotChatFragment10.d(sobotChatFragment10.b, message);
                        }
                    } else if (SobotChatFragment.this.aR != null && SobotChatFragment.this.f.isShowCloseBtn() && SobotChatFragment.this.c == 302) {
                        SobotChatFragment.this.aR.setVisibility(0);
                    }
                } else if (SobotChatFragment.this.L >= SobotChatFragment.this.bd * 1000) {
                    SobotChatFragment sobotChatFragment11 = SobotChatFragment.this;
                    sobotChatFragment11.N = true;
                    sobotChatFragment11.p();
                    SobotChatFragment sobotChatFragment12 = SobotChatFragment.this;
                    sobotChatFragment12.L = 0;
                    sobotChatFragment12.aE.setText(SobotChatFragment.this.i("sobot_voiceTooLong"));
                    SobotChatFragment.this.aE.setBackgroundResource(SobotChatFragment.this.f("sobot_recording_text_hint_bg"));
                    SobotChatFragment.this.av.setVisibility(0);
                    SobotChatFragment.this.at.setVisibility(8);
                    SobotChatFragment.this.au.setVisibility(8);
                    SobotChatFragment.this.a(2);
                    SobotChatFragment.this.aB.setPressed(false);
                    SobotChatFragment.this.bg = 0;
                } else {
                    int parseInt = Integer.parseInt(message.obj.toString());
                    SobotChatFragment.this.bg = parseInt;
                    if (parseInt < SobotChatFragment.this.be * 1000) {
                        if (parseInt % 1000 == 0) {
                            SobotChatFragment.this.M = ad.a.a(parseInt);
                            SobotChatFragment.this.aq.setText(SobotChatFragment.this.M.substring(3) + "''");
                        }
                    } else if (parseInt >= SobotChatFragment.this.bd * 1000) {
                        SobotChatFragment.this.aq.setText(SobotChatFragment.this.i("sobot_voiceTooLong"));
                    } else if (parseInt % 1000 == 0) {
                        SobotChatFragment.this.M = ad.a.a(parseInt);
                        SobotChatFragment.this.aq.setText(SobotChatFragment.this.i("sobot_count_down") + (((SobotChatFragment.this.bd * 1000) - parseInt) / 1000));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void o() {
        boolean z;
        aa();
        ab();
        ac();
        ad();
        ah();
        ZhiChiConfig a2 = com.sobot.chat.core.channel.a.a(this.a).a(this.f.getApp_key());
        if (!(a2 == null || a2.getInitModel() == null || a2.isAboveZero)) {
            long b2 = s.b(Z(), "sobot_finish_curtime", System.currentTimeMillis());
            long currentTimeMillis = System.currentTimeMillis() - b2;
            if (!TextUtils.isEmpty(a2.getInitModel().getUserOutTime()) && b2 > 0) {
                long parseLong = Long.parseLong(a2.getInitModel().getUserOutTime()) * 60 * 1000;
                z = currentTimeMillis - parseLong > 0;
                m.d("\u8fdb\u5165\u5f53\u524d\u754c\u9762\u51cf\u53bb\u4e0a\u6b21\u754c\u9762\u5173\u95ed\u7684\u65f6\u95f4\u5dee\uff1a" + currentTimeMillis + " ms");
                m.d("\u7528\u6237\u8d85\u65f6\u65f6\u95f4\uff1a" + parseLong + " ms");
                StringBuilder sb = new StringBuilder();
                sb.append("\u662f\u5426\u9700\u8981\u91cd\u65b0\u521d\u59cb\u5316\uff1a");
                sb.append(z);
                m.d(sb.toString());
                d(z);
                Intent intent = new Intent();
                intent.setAction(ZhiChiConstants.SOBOT_TIMER_BROCAST);
                intent.putExtra("isStartTimer", false);
                this.bs.sendBroadcast(intent);
            }
        }
        z = false;
        d(z);
        Intent intent = new Intent();
        intent.setAction(ZhiChiConstants.SOBOT_TIMER_BROCAST);
        intent.putExtra("isStartTimer", false);
        this.bs.sendBroadcast(intent);
    }

    private void aa() {
        if (getView() != null) {
            if (!(this.f == null || this.f.getTitleImgId() == 0)) {
                this.H.setBackgroundResource(this.f.getTitleImgId());
            }
            View view = getView();
            View findViewById = view.findViewById(e("sobot_layout_titlebar"));
            TextView textView = (TextView) view.findViewById(e("sobot_tv_left"));
            TextView textView2 = (TextView) view.findViewById(e("sobot_tv_right"));
            this.aR = (TextView) view.findViewById(e("sobot_tv_close"));
            this.aR.setText(q.f(Z(), "sobot_colse"));
            if (findViewById != null) {
                if (textView != null) {
                    b(textView, f("sobot_icon_back_grey"), "");
                    a((View) textView);
                    textView.setOnClickListener(new AnonymousClass3());
                }
                if (textView2 != null) {
                    if (-1 != com.sobot.chat.c.j) {
                        a(textView2, com.sobot.chat.c.j, "");
                    } else {
                        a(textView2, f("sobot_delete_hismsg_selector"), "");
                    }
                    textView2.setOnClickListener(new AnonymousClass4());
                    if (com.sobot.chat.c.c) {
                        textView2.setVisibility(0);
                    } else {
                        textView2.setVisibility(8);
                    }
                }
                if (this.aR != null && this.f.isShowCloseBtn() && this.c == 302) {
                    this.aR.setVisibility(0);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$3  reason: invalid class name */
    public class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotChatFragment.this.u();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$4  reason: invalid class name */
    public class AnonymousClass4 implements View.OnClickListener {
        AnonymousClass4() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotChatFragment.this.b(view);
        }
    }

    private void ab() {
        if (this.br == null) {
            this.br = new MyMessageReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        Z().registerReceiver(this.br, intentFilter);
        if (this.bt == null) {
            this.bt = new a();
        }
        this.bs = LocalBroadcastManager.getInstance(this.a);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(ZhiChiConstants.receiveMessageBrocast);
        intentFilter2.addAction("SOBOT_BROCAST_ACTION_SEND_LOCATION");
        intentFilter2.addAction("SOBOT_BROCAST_ACTION_SEND_TEXT");
        intentFilter2.addAction("SOBOT_BROCAST_ACTION_SEND_OBJECT");
        intentFilter2.addAction("SOBOT_BROCAST_ACTION_SEND_CARD");
        intentFilter2.addAction("SOBOT_BROCAST_ACTION_SEND_ORDERCARD");
        intentFilter2.addAction("SOBOT_BROCAST_ACTION_TRASNFER_TO_OPERATOR");
        intentFilter2.addAction(ZhiChiConstants.chat_remind_post_msg);
        intentFilter2.addAction(ZhiChiConstants.sobot_click_cancle);
        intentFilter2.addAction(ZhiChiConstants.dcrc_comment_state);
        intentFilter2.addAction(ZhiChiConstants.sobot_close_now);
        intentFilter2.addAction(ZhiChiConstants.sobot_close_now_clear_cache);
        intentFilter2.addAction(ZhiChiConstants.SOBOT_CHANNEL_STATUS_CHANGE);
        intentFilter2.addAction(ZhiChiConstants.SOBOT_BROCAST_KEYWORD_CLICK);
        intentFilter2.addAction(ZhiChiConstants.SOBOT_BROCAST_REMOVE_FILE_TASK);
        this.bs.registerReceiver(this.bt, intentFilter2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$39  reason: invalid class name */
    public class AnonymousClass39 implements c.b {
        AnonymousClass39() {
        }

        @Override // com.sobot.chat.widget.kpswitch.util.c.b
        public void a(boolean z) {
            SobotChatFragment.this.x();
            if (z) {
                SobotChatFragment.this.ai.setSelection(SobotChatFragment.this.b.getCount());
            }
        }
    }

    private void ac() {
        this.bq = com.sobot.chat.widget.kpswitch.util.c.a(Z(), this.aA, new AnonymousClass39());
        com.sobot.chat.widget.kpswitch.util.a.a(this.aA, this.ao, this.aj);
        this.Y.setOnClickListener(this);
        this.ak.setOnClickListener(this);
        this.ao.setOnClickListener(this);
        this.ap.setOnClickListener(this);
        this.aw.setOnClickListener(this);
        this.ax.setOnClickListener(this);
        this.aL.setOnClickListener(this);
        this.C.setOnClickListener(this);
        this.D.setOnClickListener(this);
        if (s.b((Context) Z(), "sobot_use_language", false) || !ac.a((Context) Z())) {
            this.al.setBackgroundResource(q.e(Z(), "sobot_icon_common_manualwork"));
        }
        this.al.setOnClickListener(new AnonymousClass6());
        this.ai.setDropdownListScrollListener(new AnonymousClass40());
        TextView textView = this.aR;
        if (textView != null) {
            textView.setOnClickListener(new AnonymousClass8());
        }
        this.aj.setOnClickListener(new AnonymousClass9());
        this.aj.setSobotAutoCompleteListener(this);
        this.aj.setOnClickListener(new AnonymousClass10());
        this.aj.setOnFocusChangeListener(new AnonymousClass2());
        this.aj.addTextChangedListener(new AnonymousClass5());
        this.aB.setOnTouchListener(new b());
        this.ai.setOnTouchListener(new AnonymousClass7());
        this.aa.setOnClickListener(new AnonymousClass14());
        this.Z.setOnClickListener(new AnonymousClass15());
        this.X.setOnClickListener(new AnonymousClass16());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$6  reason: invalid class name */
    public class AnonymousClass6 extends NoDoubleClickListener {
        AnonymousClass6() {
        }

        @Override // com.sobot.chat.listener.NoDoubleClickListener
        public void a(View view) {
            SobotChatFragment.this.b();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$40  reason: invalid class name */
    public class AnonymousClass40 implements DropdownListView.a {
        AnonymousClass40() {
        }

        @Override // com.sobot.chat.widget.DropdownListView.a
        public void a(AbsListView absListView, int i, int i2, int i3) {
            if (SobotChatFragment.this.Y.getVisibility() == 0 && SobotChatFragment.this.aS.size() > 0 && SobotChatFragment.this.aS.size() > i && SobotChatFragment.this.aS.get(i) != null && ((ZhiChiMessageBase) SobotChatFragment.this.aS.get(i)).getAnswer() != null && 7 == ((ZhiChiMessageBase) SobotChatFragment.this.aS.get(i)).getAnswer().getRemindType()) {
                SobotChatFragment.this.Y.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$8  reason: invalid class name */
    public class AnonymousClass8 implements View.OnClickListener {
        AnonymousClass8() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotChatFragment.this.s();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$9  reason: invalid class name */
    public class AnonymousClass9 implements View.OnClickListener {
        AnonymousClass9() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotChatFragment.this.az();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$10  reason: invalid class name */
    public class AnonymousClass10 implements View.OnClickListener {
        AnonymousClass10() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotChatFragment.this.F();
            SobotChatFragment.this.ap.setBackgroundResource(q.e(SobotChatFragment.this.getContext(), "sobot_emoticon_button_selector"));
            if (com.sobot.chat.b.a(1)) {
                SobotChatFragment.this.aj.a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnFocusChangeListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z && SobotChatFragment.this.aj.getText().toString().trim().length() != 0) {
                SobotChatFragment.this.ak.setVisibility(0);
                SobotChatFragment.this.ao.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$5  reason: invalid class name */
    public class AnonymousClass5 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        AnonymousClass5() {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            SobotChatFragment.this.az();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$7  reason: invalid class name */
    public class AnonymousClass7 implements View.OnTouchListener {
        AnonymousClass7() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 1) {
                return false;
            }
            SobotChatFragment sobotChatFragment = SobotChatFragment.this;
            sobotChatFragment.a(sobotChatFragment.aA);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$14  reason: invalid class name */
    public class AnonymousClass14 implements View.OnClickListener {
        AnonymousClass14() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotChatFragment.this.d(true);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$15  reason: invalid class name */
    public class AnonymousClass15 implements View.OnClickListener {
        AnonymousClass15() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotChatFragment.this.a(false);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$16  reason: invalid class name */
    public class AnonymousClass16 implements View.OnClickListener {
        AnonymousClass16() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotChatFragment.this.a(true, 5, 0, "");
        }
    }

    private void ad() {
        this.b = new e(getContext(), this.aS, this);
        this.ai.setAdapter((BaseAdapter) this.b);
        this.ai.setPullRefreshEnable(true);
        this.ai.setOnRefreshListenerHead(this);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void ae() {
        this.au.setBackgroundResource(f("sobot_voice_animation"));
        this.az = (AnimationDrawable) this.au.getBackground();
        this.au.post(new AnonymousClass11());
        this.aE.setText(i("sobot_move_up_to_cancel"));
        this.aE.setBackgroundResource(f("sobot_recording_text_hint_bg1"));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$11  reason: invalid class name */
    public class AnonymousClass11 implements Runnable {
        AnonymousClass11() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SobotChatFragment.this.az.start();
        }
    }

    public void a(int i) {
        Message obtainMessage = this.T.obtainMessage();
        obtainMessage.what = MetricsProto.MetricsEvent.DIALOG_WIFI_AP_EDIT;
        obtainMessage.arg1 = i;
        this.T.sendMessageDelayed(obtainMessage, 500);
    }

    public void p() {
        ag();
        a(1, this.bf);
        this.aq.setText("59''");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void af() {
        try {
            ag();
            this.bh = z.a().d() + UUID.randomUUID().toString() + ".wav";
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                m.d("sd\u5361\u88ab\u5378\u8f7d\u4e86");
            }
            File parentFile = new File(this.bh).getParentFile();
            if (!parentFile.exists() && !parentFile.mkdirs()) {
                m.d("\u6587\u4ef6\u5939\u521b\u5efa\u5931\u8d25");
            }
            this.bi = ExtAudioRecorder.a((Boolean) false);
            this.bi.a(this.bh);
            this.bi.b();
            this.bi.a(new AnonymousClass12());
        } catch (Exception unused) {
            m.d("prepare() failed");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$12  reason: invalid class name */
    public class AnonymousClass12 implements ExtAudioRecorder.a {
        AnonymousClass12() {
        }

        @Override // com.sobot.chat.utils.ExtAudioRecorder.a
        public void a() {
            SobotChatFragment.this.ae();
            SobotChatFragment sobotChatFragment = SobotChatFragment.this;
            sobotChatFragment.g(sobotChatFragment.T);
            SobotChatFragment sobotChatFragment2 = SobotChatFragment.this;
            sobotChatFragment2.a(0, sobotChatFragment2.bf);
        }

        @Override // com.sobot.chat.utils.ExtAudioRecorder.a
        public void b() {
            ae.a(SobotChatFragment.this.a, SobotChatFragment.this.i("sobot_no_record_audio_permission"));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void ag() {
        try {
            if (this.bi != null) {
                q();
                this.bi.d();
                this.bi.c();
            }
        } catch (Exception unused) {
        }
    }

    public void g(Handler handler) {
        this.L = 0;
        q();
        this.J = new Timer();
        this.K = new AnonymousClass13(handler);
        this.J.schedule(this.K, 0, 500);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$13  reason: invalid class name */
    public class AnonymousClass13 extends TimerTask {
        final /* synthetic */ Handler a;

        AnonymousClass13(Handler handler) {
            this.a = handler;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            SobotChatFragment.this.h(this.a);
        }
    }

    public void h(Handler handler) {
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.what = 1000;
        this.L += 500;
        obtainMessage.obj = Integer.valueOf(this.L);
        handler.sendMessage(obtainMessage);
    }

    public void q() {
        Timer timer = this.J;
        if (timer != null) {
            timer.cancel();
            this.J = null;
        }
        TimerTask timerTask = this.K;
        if (timerTask != null) {
            timerTask.cancel();
            this.K = null;
        }
        this.L = 0;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(int i, String str) {
        if (i == 0) {
            a(str, this.bh, this.M, 4, 0, this.T);
        } else if (i == 2) {
            a(str, this.bh, this.M, 0, 2, this.T);
        } else {
            a(str, this.bh, this.M, 2, 1, this.T);
            a(str, this.M, this.e.getCid(), this.e.getPartnerid(), this.bh, this.T);
            this.ai.setSelection(this.b.getCount());
        }
        ap();
    }

    private void ah() {
        this.bc = com.sobot.chat.core.channel.a.a(this.a).a(this.f.getApp_key(), true, this.f.getPartnerid());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d(boolean z) {
        if (z) {
            this.c = 301;
            this.aT = 0;
            this.aS.clear();
            this.b.notifyDataSetChanged();
            this.bj.clear();
            this.bk = 0;
            this.bl = 0;
            this.bo = false;
            this.h = false;
            this.aY = false;
            this.d = CustomerState.Offline;
            this.i = 0;
            this.bb = 0;
            this.aX = false;
            this.k = false;
            this.aa.setVisibility(8);
            this.Z.setVisibility(8);
            this.X.setVisibility(8);
            this.aG.setVisibility(0);
            com.sobot.chat.utils.a.a(this.aG);
            this.ai.setPullRefreshEnable(true);
            Context context = this.a;
            this.f.setChoose_adminid(s.b(context, this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_receptionistid", ""));
            ai();
        } else if (com.sobot.chat.utils.c.a(this.a, this.f.getApp_key(), this.f)) {
            ai();
        } else {
            al();
        }
        az();
    }

    private void ai() {
        this.U.disconnChannel();
        m();
        Context context = this.a;
        s.a(context, this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_last_login_group_id", TextUtils.isEmpty(this.f.getGroupid()) ? "" : this.f.getGroupid());
        aj();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void aj() {
        m.d("customerInit\u521d\u59cb\u5316\u63a5\u53e3");
        if (this.f.getService_mode() == 1) {
            com.sobot.chat.utils.c.b(this.a);
        }
        this.U.sobotInit(this, this.f, new AnonymousClass18());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$18  reason: invalid class name */
    public class AnonymousClass18 implements com.sobot.chat.core.http.c.a<ZhiChiInitModeBase> {
        AnonymousClass18() {
        }

        public void a(ZhiChiInitModeBase zhiChiInitModeBase) {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            String str9;
            if (SobotChatFragment.this.e()) {
                s.a((Context) SobotChatFragment.this.Z(), "sobot_finish_curtime", 0L);
                SobotChatFragment sobotChatFragment = SobotChatFragment.this;
                sobotChatFragment.e = zhiChiInitModeBase;
                sobotChatFragment.ak();
                SobotChatFragment.this.av();
                if (SobotChatFragment.this.f.getService_mode() > 0) {
                    SobotChatFragment.this.e.setType(SobotChatFragment.this.f.getService_mode() + "");
                }
                SobotChatFragment sobotChatFragment2 = SobotChatFragment.this;
                sobotChatFragment2.I = Integer.parseInt(sobotChatFragment2.e.getType());
                s.a(SobotChatFragment.this.a, SobotChatFragment.this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "initType", SobotChatFragment.this.I);
                SobotChatFragment.this.at();
                SobotChatFragment.this.aI();
                SobotChatFragment.this.ao();
                if (!TextUtils.isEmpty(SobotChatFragment.this.e.getPartnerid())) {
                    s.a(SobotChatFragment.this.a, "sobot_cid_chat", SobotChatFragment.this.e.getPartnerid());
                }
                s.a(SobotChatFragment.this.a, "sobot_msg_flag", SobotChatFragment.this.e.getMsgFlag());
                s.a(SobotChatFragment.this.a, "sobot_leave_msg_flag", SobotChatFragment.this.e.isMsgToTicketFlag());
                s.a(SobotChatFragment.this.a, "lastCid", SobotChatFragment.this.e.getCid());
                s.a(SobotChatFragment.this.a, SobotChatFragment.this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_last_current_partnerId", SobotChatFragment.this.f.getPartnerid());
                s.a(SobotChatFragment.this.a, "sobot_last_current_appkey", SobotChatFragment.this.f.getApp_key());
                s.a(SobotChatFragment.this.a, "sobot_last_current_info", SobotChatFragment.this.f);
                s.a(SobotChatFragment.this.a, "sobot_last_current_initModel", SobotChatFragment.this.e);
                Context context = SobotChatFragment.this.a;
                String str10 = SobotChatFragment.this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_receptionistid";
                if (TextUtils.isEmpty(SobotChatFragment.this.f.getChoose_adminid())) {
                    str = "";
                } else {
                    str = SobotChatFragment.this.f.getChoose_adminid();
                }
                s.a(context, str10, str);
                Context context2 = SobotChatFragment.this.a;
                String str11 = SobotChatFragment.this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_robot_code";
                if (TextUtils.isEmpty(SobotChatFragment.this.f.getRobotCode())) {
                    str2 = "";
                } else {
                    str2 = SobotChatFragment.this.f.getRobotCode();
                }
                s.a(context2, str11, str2);
                Context context3 = SobotChatFragment.this.a;
                String str12 = SobotChatFragment.this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_last_current_remark";
                if (TextUtils.isEmpty(SobotChatFragment.this.f.getRemark())) {
                    str3 = "";
                } else {
                    str3 = SobotChatFragment.this.f.getRemark();
                }
                s.a(context3, str12, str3);
                Context context4 = SobotChatFragment.this.a;
                String str13 = SobotChatFragment.this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_last_current_groupid";
                if (TextUtils.isEmpty(SobotChatFragment.this.f.getGroupid())) {
                    str4 = "";
                } else {
                    str4 = SobotChatFragment.this.f.getGroupid();
                }
                s.a(context4, str13, str4);
                s.a(SobotChatFragment.this.a, SobotChatFragment.this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_current_service_mode", SobotChatFragment.this.f.getService_mode());
                Context context5 = SobotChatFragment.this.a;
                String str14 = SobotChatFragment.this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_current_customer_fields";
                if (TextUtils.isEmpty(SobotChatFragment.this.f.getCustomer_fields())) {
                    str5 = "";
                } else {
                    str5 = SobotChatFragment.this.f.getCustomer_fields();
                }
                s.a(context5, str14, str5);
                Context context6 = SobotChatFragment.this.a;
                String str15 = SobotChatFragment.this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_current_isvip";
                if (TextUtils.isEmpty(SobotChatFragment.this.f.getIsVip())) {
                    str6 = "";
                } else {
                    str6 = SobotChatFragment.this.f.getIsVip();
                }
                s.a(context6, str15, str6);
                Context context7 = SobotChatFragment.this.a;
                String str16 = SobotChatFragment.this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_current_vip_level";
                if (TextUtils.isEmpty(SobotChatFragment.this.f.getVip_level())) {
                    str7 = "";
                } else {
                    str7 = SobotChatFragment.this.f.getVip_level();
                }
                s.a(context7, str16, str7);
                Context context8 = SobotChatFragment.this.a;
                String str17 = SobotChatFragment.this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_current_user_label";
                if (TextUtils.isEmpty(SobotChatFragment.this.f.getUser_label())) {
                    str8 = "";
                } else {
                    str8 = SobotChatFragment.this.f.getUser_label();
                }
                s.a(context8, str17, str8);
                Context context9 = SobotChatFragment.this.a;
                String str18 = SobotChatFragment.this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_current_robot_alias";
                if (TextUtils.isEmpty(SobotChatFragment.this.f.getRobot_alias())) {
                    str9 = "";
                } else {
                    str9 = SobotChatFragment.this.f.getRobot_alias();
                }
                s.a(context9, str18, str9);
                if (SobotChatFragment.this.e.getAnnounceMsgFlag() && !SobotChatFragment.this.e.isAnnounceTopFlag() && !TextUtils.isEmpty(SobotChatFragment.this.e.getAnnounceMsg())) {
                    SobotChatFragment.this.b.b(com.sobot.chat.utils.c.b(SobotChatFragment.this.getContext(), SobotChatFragment.this.e));
                    SobotChatFragment.this.b.notifyDataSetChanged();
                }
                if (SobotChatFragment.this.e.getOfflineMsgConnectFlag() != 1 || TextUtils.isEmpty(SobotChatFragment.this.e.getOfflineMsgAdminId()) || "null".equals(SobotChatFragment.this.e.getOfflineMsgAdminId())) {
                    if (SobotChatFragment.this.I == 1) {
                        SobotChatFragment sobotChatFragment3 = SobotChatFragment.this;
                        sobotChatFragment3.a(sobotChatFragment3.T, SobotChatFragment.this.e, SobotChatFragment.this.f);
                        SobotChatFragment.this.aJ();
                        if (y.h != null) {
                            y.h.a(SobotChatStatusMode.ZCServerConnectRobot);
                        }
                    } else if (SobotChatFragment.this.I == 3) {
                        if (SobotChatFragment.this.e.getUstatus() == 1 || SobotChatFragment.this.e.getUstatus() == -2) {
                            if (SobotChatFragment.this.e.getUstatus() == -2) {
                                SobotChatFragment sobotChatFragment4 = SobotChatFragment.this;
                                sobotChatFragment4.a(sobotChatFragment4.T, SobotChatFragment.this.e, SobotChatFragment.this.f);
                            }
                            SobotChatFragment.this.a("", "");
                        } else {
                            SobotChatFragment sobotChatFragment5 = SobotChatFragment.this;
                            sobotChatFragment5.a(sobotChatFragment5.T, SobotChatFragment.this.e, SobotChatFragment.this.f);
                            SobotChatFragment.this.aJ();
                            if (y.h != null) {
                                y.h.a(SobotChatStatusMode.ZCServerConnectRobot);
                            }
                        }
                    } else if (SobotChatFragment.this.I == 2) {
                        if (SobotChatFragment.this.l()) {
                            SobotChatFragment.this.aA();
                        } else if (SobotChatFragment.this.e.getInvalidSessionFlag() == 1) {
                            String b = s.b(SobotChatFragment.this.a, "sobot_admin_hello_word", "");
                            if (!TextUtils.isEmpty(b)) {
                                SobotChatFragment.this.b.a(com.sobot.chat.utils.c.a("", "", b));
                            } else {
                                SobotChatFragment.this.b.a(com.sobot.chat.utils.c.a("", "", SobotChatFragment.this.e.getAdminHelloWord()));
                            }
                            SobotChatFragment.this.b(0);
                            SobotChatFragment.this.al.setVisibility(8);
                            SobotChatFragment.this.aw.setVisibility(8);
                            SobotChatFragment.this.ax.setVisibility(8);
                            SobotChatFragment.this.ap.setVisibility(0);
                            SobotChatFragment sobotChatFragment6 = SobotChatFragment.this;
                            sobotChatFragment6.a(sobotChatFragment6.f("def_admin"), true);
                            SobotChatFragment.this.a((CharSequence) "", false);
                        } else {
                            SobotChatFragment.this.a((String) null, (String) null, (String) null, true);
                        }
                    } else if (SobotChatFragment.this.I == 4) {
                        if (SobotChatFragment.this.e.getInvalidSessionFlag() == 1) {
                            String b2 = s.b(SobotChatFragment.this.a, "sobot_robot_hello_word", "");
                            if (!TextUtils.isEmpty(b2)) {
                                SobotChatFragment.this.b.a(com.sobot.chat.utils.c.a("", "", b2));
                            } else {
                                SobotChatFragment.this.b.a(com.sobot.chat.utils.c.a("", "", SobotChatFragment.this.e.getRobotHelloWord()));
                            }
                        } else {
                            SobotChatFragment.this.aJ();
                            SobotChatFragment.this.a((String) null, (String) null, (String) null, true);
                        }
                    }
                    SobotChatFragment.this.aX = false;
                    if (SobotChatFragment.this.aR == null) {
                        return;
                    }
                    if (!SobotChatFragment.this.f.isShowCloseBtn() || SobotChatFragment.this.c != 302) {
                        SobotChatFragment.this.aR.setVisibility(8);
                    } else {
                        SobotChatFragment.this.aR.setVisibility(0);
                    }
                } else {
                    SobotChatFragment sobotChatFragment7 = SobotChatFragment.this;
                    sobotChatFragment7.bv = sobotChatFragment7.e.getOfflineMsgConnectFlag();
                    SobotChatFragment sobotChatFragment8 = SobotChatFragment.this;
                    sobotChatFragment8.bw = sobotChatFragment8.e.getOfflineMsgAdminId();
                    SobotChatFragment.this.a("", "", false);
                }
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            if (SobotChatFragment.this.e()) {
                if (exc instanceof IllegalArgumentException) {
                    if (m.a) {
                        ae.a(SobotChatFragment.this.a, q.f(SobotChatFragment.this.getContext(), "sobot_net_work_err"));
                    }
                    SobotChatFragment.this.d();
                } else {
                    SobotChatFragment.this.au();
                }
                SobotChatFragment.this.aX = true;
            }
        }
    }

    private void al() {
        List<ZhiChiMessageBase> messageList = com.sobot.chat.core.channel.a.a(this.a).a(this.f.getApp_key()).getMessageList();
        if (messageList == null || com.sobot.chat.core.channel.a.a(this.a).a(this.f.getApp_key()).getInitModel() == null) {
            ai();
            return;
        }
        Context context = this.a;
        int b2 = s.b(context, this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "initType", -1);
        if (this.f.getService_mode() >= 0 && b2 != this.f.getService_mode()) {
            ai();
        } else if (!TextUtils.isEmpty(this.f.getGroupid())) {
            Context context2 = this.a;
            if (s.b(context2, this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_last_login_group_id", "").equals(this.f.getGroupid())) {
                a(messageList);
            } else {
                ai();
            }
        } else {
            a(messageList);
        }
    }

    @Override // com.sobot.chat.conversation.SobotChatBaseFragment
    public void a(ZhiChiInitModeBase zhiChiInitModeBase, int i) {
        if (zhiChiInitModeBase != null) {
            this.ba = 0;
            k();
            f();
            g();
            this.d = CustomerState.Offline;
            b(zhiChiInitModeBase, i);
            b(4);
            this.aP.setVisibility(8);
            this.bp = 4;
            if (Integer.parseInt(zhiChiInitModeBase.getType()) == 2 && 1 == i) {
                b(i("sobot_no_access"), (String) null, false);
            }
            if (6 == i) {
                m.d("\u6253\u5f00\u65b0\u7a97\u53e3");
            }
            this.aX = true;
            d.a(this.a, new Intent("sobot_chat_user_outline"));
        }
    }

    private void b(ZhiChiInitModeBase zhiChiInitModeBase, int i) {
        if (y.h != null) {
            y.h.a(SobotChatStatusMode.ZCServerConnectOffline);
        }
        String a2 = com.sobot.chat.utils.c.a(this.a, zhiChiInitModeBase, i);
        if (!TextUtils.isEmpty(a2)) {
            ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
            zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
            ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
            zhiChiMessageBase.setSenderType(Constants.VIA_REPORT_TYPE_CHAT_AIO);
            zhiChiReplyAnswer.setRemindType(5);
            zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
            if (1 == i) {
                zhiChiMessageBase.setAction("sobot_outline_leverByManager");
            } else if (2 == i) {
                a2 = a2.replace("#" + q.f(getContext(), "sobot_cus_service") + "#", this.g);
                zhiChiMessageBase.setAction("sobot_outline_leverByManager");
            } else if (3 == i) {
                zhiChiMessageBase.setAction("sobot_outline_leverByManager");
                if (zhiChiInitModeBase != null) {
                    zhiChiInitModeBase.setIsblack("1");
                }
            } else if (5 == i) {
                zhiChiMessageBase.setAction("sobot_outline_leverByManager");
            } else if (4 == i) {
                zhiChiMessageBase.setAction("action_remind_past_time");
            } else if (6 == i) {
                zhiChiMessageBase.setAction("sobot_outline_leverByManager");
            } else if (99 == i) {
                zhiChiMessageBase.setAction("sobot_outline_leverByManager");
            }
            zhiChiReplyAnswer.setMsg(a2);
            a(this.b, zhiChiMessageBase);
        }
    }

    private void k(String str) {
        if (!TextUtils.isEmpty(str)) {
            a(this.b, com.sobot.chat.utils.c.b(str));
            ap();
        }
    }

    private void a(List<ZhiChiMessageBase> list) {
        ZhiChiConfig a2 = com.sobot.chat.core.channel.a.a(this.a).a(this.f.getApp_key());
        this.e = a2.getInitModel();
        aq();
        this.bc = 0;
        this.b.a(list);
        this.b.notifyDataSetChanged();
        this.c = a2.current_client_model;
        this.I = Integer.parseInt(this.e.getType());
        String cid = this.e.getCid();
        String str = aV;
        if (str == null) {
            aW = 0;
        } else if (!cid.equals(str)) {
            aW = 0;
        }
        s.a(this.a, this.f.getApp_key() + Session.SESSION_SEPARATION_CHAR_CHILD + "initType", this.I);
        StringBuilder sb = new StringBuilder();
        sb.append("sobot----type---->");
        sb.append(this.I);
        m.d(sb.toString());
        b(a2.activityTitle, a2.adminFace, false);
        aJ();
        this.d = a2.customerState;
        this.i = a2.remindRobotMessageTimes;
        this.aY = a2.isComment;
        this.h = a2.isAboveZero;
        this.g = a2.currentUserName;
        this.bo = a2.isNoMoreHistoryMsg;
        this.bk = a2.currentCidPosition;
        this.bl = a2.queryCidsStatus;
        this.aZ = a2.isShowQueueTip;
        if (a2.cids != null) {
            this.bj.addAll(a2.cids);
        }
        this.aT = a2.showTimeVisiableCustomBtn;
        this.ba = a2.queueNum;
        if (this.bo) {
            this.ai.setPullRefreshEnable(false);
        }
        a(a2.adminFace);
        this.bp = a2.bottomViewtype;
        b(a2.bottomViewtype);
        this.q = a2.isChatLock;
        if (this.I == 2 && aW == 0) {
            aV = cid;
            if (l()) {
                aA();
            } else if (this.e.getInvalidSessionFlag() == 1) {
                b(0);
                this.al.setVisibility(8);
                this.aw.setVisibility(8);
                this.ax.setVisibility(8);
                this.ap.setVisibility(0);
                this.S = a2.tempMsgContent;
                a(f("def_admin"), true);
                a("", false);
            } else {
                a((String) null, (String) null, (String) null, true);
            }
        }
        if (this.I == 4 && aW == 0) {
            this.S = a2.tempMsgContent;
        }
        m.d("sobot----isChatLock--->userInfoTimeTask " + a2.userInfoTimeTask + "=====customTimeTask====" + a2.customTimeTask + this.q);
        this.u = a2.paseReplyTimeCustoms;
        this.p = a2.paseReplyTimeUserInfo;
        if (a2.userInfoTimeTask && this.q != 1) {
            f();
            a(this.T);
            this.o = a2.paseReplyTimeUserInfo;
        }
        if (a2.customTimeTask && this.q != 1) {
            g();
            d(this.T);
            this.t = a2.paseReplyTimeCustoms;
        }
        if (this.f.getAutoSendMsgMode().geIsEveryTimeAutoSend()) {
            a2.isProcessAutoSendMsg = true;
        }
        if (a2.isProcessAutoSendMsg) {
            if (this.f.getAutoSendMsgMode().getAuto_send_msgtype() == SobotAutoSendMsgMode.ZCMessageTypeText) {
                a(this.f);
            } else if (this.c == 302 && !TextUtils.isEmpty(this.f.getAutoSendMsgMode().getContent()) && this.f.getAutoSendMsgMode() == SobotAutoSendMsgMode.SendToOperator && this.d == CustomerState.Online) {
                String content = this.f.getAutoSendMsgMode().getContent();
                if (this.f.getAutoSendMsgMode().getAuto_send_msgtype() == SobotAutoSendMsgMode.ZCMessageTypeFile) {
                    File file = new File(content);
                    if (file.exists()) {
                        a(file, this.T, (ListView) this.ai, this.b, false);
                    }
                } else if (this.f.getAutoSendMsgMode().getAuto_send_msgtype() == SobotAutoSendMsgMode.ZCMessageTypeVideo) {
                    File file2 = new File(content);
                    if (file2.exists()) {
                        a(file2, (Uri) null, this.b);
                    }
                } else if (this.f.getAutoSendMsgMode().getAuto_send_msgtype() == SobotAutoSendMsgMode.ZCMessageTypePhoto) {
                    File file3 = new File(content);
                    if (file3.exists()) {
                        a(file3, this.T, (ListView) this.ai, this.b, false);
                    }
                }
            }
            a2.isProcessAutoSendMsg = false;
        }
        this.aj.a(this.e.getPartnerid(), this.e.getRobotid());
        if (this.d == CustomerState.Online && this.c == 302) {
            ax();
            ay();
            this.aj.setAutoCompleteEnable(false);
            b((String) null, a2.adminFace, false);
        } else {
            this.aj.setAutoCompleteEnable(true);
            b((String) null, this.e.getRobotLogo(), false);
        }
        this.ai.setSelection(this.b.getCount());
        av();
        aI();
        a2.clearMessageList();
        a2.clearInitModel();
        this.aX = false;
        int size = this.aS.size() - 1;
        while (true) {
            if (size > 0) {
                if (!TextUtils.isEmpty(this.aS.get(size).getSenderType()) && Integer.parseInt(this.aS.get(size).getSenderType()) == 24 && this.aS.get(size).getAnswer() != null && 9 == this.aS.get(size).getAnswer().getRemindType()) {
                    this.aS.remove(size);
                    this.b.notifyDataSetChanged();
                    break;
                }
                size--;
            } else {
                break;
            }
        }
        f(this.T);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void am() {
        this.aT++;
        if (this.aT >= this.f.getArtificialIntelligenceNum()) {
            this.al.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void e(int i) {
        a(this.f.getGroupid(), this.f.getGroup_name(), i, this.f.isCloseInquiryForm());
    }

    private void an() {
        if (DisplayEmojiRules.getMapAll(this.a).size() > 0) {
            this.ap.setVisibility(0);
        } else {
            this.ap.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, String str2, String str3, boolean z) {
        a(str, str2, str3, z, 0);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, String str2, String str3, boolean z, int i) {
        if (l()) {
            a("", "", str2, str3, z);
        } else if (y.f != null) {
            SobotTransferOperatorParam sobotTransferOperatorParam = new SobotTransferOperatorParam();
            sobotTransferOperatorParam.setGroupId(str);
            sobotTransferOperatorParam.setKeyword(str2);
            sobotTransferOperatorParam.setKeywordId(str3);
            sobotTransferOperatorParam.setShowTips(z);
            sobotTransferOperatorParam.setTransferType(i);
            sobotTransferOperatorParam.setConsultingContent(this.f.getConsultingContent());
            y.f.a(getContext(), sobotTransferOperatorParam);
        } else if (!TextUtils.isEmpty(this.f.getGroupid())) {
            if (!TextUtils.isEmpty(str2)) {
                a(this.f.getGroupid(), "", str2, str3, z);
            } else {
                e(i);
            }
        } else if (!TextUtils.isEmpty(str2)) {
            a(str, "", str2, str3, z);
        } else if (!this.e.getGroupflag().equals("1") || !TextUtils.isEmpty(this.f.getChoose_adminid()) || this.e.isSmartRouteInfoFlag() || !TextUtils.isEmpty(this.f.getTransferAction())) {
            a("", "", i, this.f.isCloseInquiryForm());
        } else {
            f(i);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$19  reason: invalid class name */
    public class AnonymousClass19 implements com.sobot.chat.core.http.c.a<ZhiChiGroup> {
        final /* synthetic */ int a;

        AnonymousClass19(int i) {
            this.a = i;
        }

        public void a(ZhiChiGroup zhiChiGroup) {
            if (SobotChatFragment.this.e()) {
                if ("0".equals(zhiChiGroup.getUstatus())) {
                    SobotChatFragment sobotChatFragment = SobotChatFragment.this;
                    sobotChatFragment.a(sobotChatFragment.e, 4);
                    return;
                }
                SobotChatFragment.this.aU = zhiChiGroup.getData();
                if (SobotChatFragment.this.aU == null || SobotChatFragment.this.aU.size() <= 0) {
                    SobotChatFragment sobotChatFragment2 = SobotChatFragment.this;
                    sobotChatFragment2.a("", "", this.a, sobotChatFragment2.f.isCloseInquiryForm());
                    return;
                }
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= SobotChatFragment.this.aU.size()) {
                        break;
                    } else if (ITagManager.STATUS_TRUE.equals(((ZhiChiGroupBase) SobotChatFragment.this.aU.get(i)).isOnline())) {
                        z = true;
                        break;
                    } else {
                        i++;
                    }
                }
                if (z) {
                    if (SobotChatFragment.this.e.getUstatus() == 1 || SobotChatFragment.this.e.getUstatus() == -2) {
                        SobotChatFragment.this.a("", "");
                    } else if (!TextUtils.isEmpty(SobotChatFragment.this.f.getGroupid())) {
                        SobotChatFragment.this.e(this.a);
                    } else {
                        Intent intent = new Intent(SobotChatFragment.this.a, SobotSkillGroupActivity.class);
                        intent.putExtra("grouplist", (Serializable) SobotChatFragment.this.aU);
                        intent.putExtra("uid", SobotChatFragment.this.e.getPartnerid());
                        intent.putExtra("type", SobotChatFragment.this.I);
                        intent.putExtra("appkey", SobotChatFragment.this.f.getApp_key());
                        intent.putExtra("companyId", SobotChatFragment.this.e.getCompanyId());
                        intent.putExtra("msgTmp", SobotChatFragment.this.e.getMsgTmp());
                        intent.putExtra("msgTxt", SobotChatFragment.this.e.getMsgTxt());
                        intent.putExtra("msgFlag", SobotChatFragment.this.e.getMsgFlag());
                        intent.putExtra("transferType", this.a);
                        SobotChatFragment.this.startActivityForResult(intent, 100);
                    }
                } else if (SobotChatFragment.this.b == null || SobotChatFragment.this.R == null) {
                    SobotChatFragment.this.e(true);
                } else {
                    SobotChatFragment.this.b.b(SobotChatFragment.this.R);
                    SobotChatFragment.this.b.notifyDataSetChanged();
                    SobotChatFragment.this.R = null;
                }
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            if (SobotChatFragment.this.e()) {
                ae.a(SobotChatFragment.this.a, str);
            }
        }
    }

    private void f(int i) {
        this.U.getGroupList(this, this.f.getApp_key(), this.e.getPartnerid(), new AnonymousClass19(i));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void e(boolean z) {
        if (this.I == 2) {
            aA();
        } else {
            b(this.e.getRobotName(), this.e.getRobotLogo(), false);
            aJ();
            if (z) {
                ar();
            }
            if (this.I == 4 && this.c == 301) {
                a(this.T, this.e, this.f);
            }
        }
        ap();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void f(boolean z) {
        b(this.e.getRobotName(), this.e.getRobotLogo(), false);
        aJ();
        if (z) {
            as();
        }
        if (this.I == 4) {
            a(this.T, this.e, this.f);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void ao() {
        if (this.e != null) {
            int i = this.I;
            if (i == 1) {
                b(0);
                this.bp = 0;
                b(this.e.getRobotName(), this.e.getRobotLogo(), false);
            } else if (i == 3 || i == 4) {
                b(1);
                this.bp = 1;
                b(this.e.getRobotName(), this.e.getRobotLogo(), false);
            } else if (i == 2) {
                b(2);
                this.bp = 2;
                b(i("sobot_connecting_customer_service"), (String) null, false);
            }
            if (this.I != 2) {
                this.aj.a(this.e.getPartnerid(), this.e.getRobotid());
                this.aj.setAutoCompleteEnable(true);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.conversation.SobotChatBaseFragment
    public void a(String str, String str2, String str3, String str4, boolean z, int i) {
        if (!this.bn) {
            boolean z2 = true;
            this.bn = true;
            if (!(this.d == CustomerState.Queuing || this.d == CustomerState.Online)) {
                z2 = false;
            }
            SobotConnCusParam sobotConnCusParam = new SobotConnCusParam();
            sobotConnCusParam.setChooseAdminId(this.f.getChoose_adminid());
            sobotConnCusParam.setTran_flag(this.f.getTranReceptionistFlag());
            sobotConnCusParam.setPartnerid(this.e.getPartnerid());
            sobotConnCusParam.setCid(this.e.getCid());
            sobotConnCusParam.setGroupId(str);
            sobotConnCusParam.setGroupName(str2);
            sobotConnCusParam.setCurrentFlag(z2);
            sobotConnCusParam.setKeyword(str3);
            sobotConnCusParam.setKeywordId(str4);
            sobotConnCusParam.setTransferType(i);
            sobotConnCusParam.setTransferAction(this.f.getTransferAction());
            sobotConnCusParam.setIs_Queue_First(this.f.is_queue_first());
            sobotConnCusParam.setSummary_params(this.f.getSummary_params());
            sobotConnCusParam.setOfflineMsgAdminId(this.bw);
            sobotConnCusParam.setOfflineMsgConnectFlag(this.bv);
            this.U.connnect(this, sobotConnCusParam, new AnonymousClass20(str4, str3, z));
        }
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$20  reason: invalid class name */
    class AnonymousClass20 implements com.sobot.chat.core.http.c.a<ZhiChiMessageBase> {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ boolean c;

        AnonymousClass20(String str, String str2, boolean z) {
            this.a = str;
            this.b = str2;
            this.c = z;
        }

        public void a(ZhiChiMessageBase zhiChiMessageBase) {
            m.d("connectCustomerService:zhichiMessageBase= " + zhiChiMessageBase);
            SobotChatFragment.this.bn = false;
            SobotChatFragment.this.bw = "";
            SobotChatFragment.this.bv = 0;
            if (SobotChatFragment.this.e()) {
                if (!TextUtils.isEmpty(zhiChiMessageBase.getServiceEndPushMsg())) {
                    SobotChatFragment.this.e.setServiceEndPushMsg(zhiChiMessageBase.getServiceEndPushMsg());
                }
                int parseInt = Integer.parseInt(zhiChiMessageBase.getStatus());
                int unused = SobotChatFragment.aW = parseInt;
                String unused2 = SobotChatFragment.aV = SobotChatFragment.this.e.getCid();
                SobotChatFragment.this.a(zhiChiMessageBase.getAface());
                m.d("status---:" + parseInt);
                if (parseInt == 0) {
                    m.d("\u8f6c\u4eba\u5de5--\u6392\u961f");
                    SobotChatFragment.this.U.connChannel(zhiChiMessageBase.getWslinkBak(), zhiChiMessageBase.getWslinkDefault(), SobotChatFragment.this.e.getPartnerid(), zhiChiMessageBase.getPuid(), SobotChatFragment.this.f.getApp_key(), zhiChiMessageBase.getWayHttp());
                    SobotChatFragment.this.d = CustomerState.Queuing;
                    SobotChatFragment.this.aZ = this.c;
                    SobotChatFragment sobotChatFragment = SobotChatFragment.this;
                    sobotChatFragment.a(zhiChiMessageBase.getCount() + "", parseInt, zhiChiMessageBase.getQueueDoc(), this.c);
                } else if (parseInt == 5) {
                    SobotChatFragment sobotChatFragment2 = SobotChatFragment.this;
                    sobotChatFragment2.a(sobotChatFragment2.e, 4);
                } else if (parseInt == 6) {
                    if (TextUtils.isEmpty(this.a)) {
                        SobotChatFragment sobotChatFragment3 = SobotChatFragment.this;
                        sobotChatFragment3.b(sobotChatFragment3.e.getRobotName(), SobotChatFragment.this.e.getRobotLogo(), false);
                        SobotChatFragment.this.f.setChoose_adminid(null);
                        SobotChatFragment.this.e.setSmartRouteInfoFlag(false);
                        SobotChatFragment.this.a((String) null, this.b, this.a, this.c);
                    }
                } else if (1 == parseInt) {
                    SobotChatFragment.this.b(zhiChiMessageBase);
                } else if (2 == parseInt) {
                    if (SobotChatFragment.this.b == null || SobotChatFragment.this.R == null) {
                        SobotChatFragment.this.e(this.c);
                        return;
                    }
                    SobotChatFragment.this.b.b(SobotChatFragment.this.R);
                    SobotChatFragment.this.b.notifyDataSetChanged();
                    SobotChatFragment.this.R = null;
                } else if (3 == parseInt) {
                    if (SobotChatFragment.this.b == null || SobotChatFragment.this.R == null) {
                        SobotChatFragment.this.f(this.c);
                        return;
                    }
                    SobotChatFragment.this.b.b(SobotChatFragment.this.R);
                    SobotChatFragment.this.b.notifyDataSetChanged();
                    SobotChatFragment.this.R = null;
                } else if (4 == parseInt) {
                    SobotChatFragment.this.b(zhiChiMessageBase);
                } else if (7 == parseInt) {
                    if (SobotChatFragment.this.I == 2) {
                        SobotChatFragment sobotChatFragment4 = SobotChatFragment.this;
                        sobotChatFragment4.b(sobotChatFragment4.i("sobot_wait_full"), (String) null, true);
                        SobotChatFragment.this.b(6);
                        SobotChatFragment.this.bp = 6;
                    }
                    if (SobotChatFragment.this.e.getMsgFlag() == 0) {
                        if (!TextUtils.isEmpty(zhiChiMessageBase.getMsg())) {
                            ae.a(SobotChatFragment.this.a, zhiChiMessageBase.getMsg(), 3000, new AnonymousClass1());
                        } else {
                            ae.a(SobotChatFragment.this.a, q.f(SobotChatFragment.this.a, "sobot_line_transfinite_def_hint"), 3000, new AnonymousClass2());
                        }
                    }
                    SobotChatFragment.this.aJ();
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.conversation.SobotChatFragment$20$1  reason: invalid class name */
        public class AnonymousClass1 implements ae.a {
            AnonymousClass1() {
            }

            @Override // com.sobot.chat.utils.ae.a
            public void a() {
                SobotChatFragment.this.a(false);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.conversation.SobotChatFragment$20$2  reason: invalid class name */
        public class AnonymousClass2 implements ae.a {
            AnonymousClass2() {
            }

            @Override // com.sobot.chat.utils.ae.a
            public void a() {
                SobotChatFragment.this.a(false);
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            m.d("connectCustomerService:e= " + exc.toString() + str);
            SobotChatFragment.this.bn = false;
            if (!(SobotChatFragment.this.b == null || SobotChatFragment.this.R == null)) {
                SobotChatFragment.this.b.b(SobotChatFragment.this.R);
                SobotChatFragment.this.R = null;
            }
            if (SobotChatFragment.this.e()) {
                if (SobotChatFragment.this.I == 2) {
                    SobotChatFragment.this.b(6);
                    SobotChatFragment sobotChatFragment = SobotChatFragment.this;
                    sobotChatFragment.b(sobotChatFragment.i("sobot_no_access"), (String) null, false);
                    SobotChatFragment.this.aX = true;
                }
                ae.a(SobotChatFragment.this.a, str);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$21  reason: invalid class name */
    public class AnonymousClass21 implements Runnable {
        AnonymousClass21() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SobotChatFragment.this.ai.setSelection(SobotChatFragment.this.b.getCount());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void ap() {
        this.T.post(new AnonymousClass21());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void aq() {
        if (this.bc >= 10) {
            this.Y.setVisibility(0);
            TextView textView = this.Y;
            textView.setText(this.bc + i("sobot_new_msg"));
            return;
        }
        this.Y.setVisibility(8);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(ZhiChiMessageBase zhiChiMessageBase) {
        if (zhiChiMessageBase != null && this.e != null) {
            this.e.setAdminHelloWord(!TextUtils.isEmpty(zhiChiMessageBase.getAdminHelloWord()) ? zhiChiMessageBase.getAdminHelloWord() : this.e.getAdminHelloWord());
            this.e.setAdminTipTime(!TextUtils.isEmpty(zhiChiMessageBase.getServiceOutTime()) ? zhiChiMessageBase.getServiceOutTime() : this.e.getAdminTipTime());
            this.e.setAdminTipWord(!TextUtils.isEmpty(zhiChiMessageBase.getServiceOutDoc()) ? zhiChiMessageBase.getServiceOutDoc() : this.e.getAdminTipWord());
            this.U.connChannel(zhiChiMessageBase.getWslinkBak(), zhiChiMessageBase.getWslinkDefault(), this.e.getPartnerid(), zhiChiMessageBase.getPuid(), this.f.getApp_key(), zhiChiMessageBase.getWayHttp());
            b(zhiChiMessageBase.getAname(), zhiChiMessageBase.getAface());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(String str, String str2) {
        this.c = 302;
        if (y.h != null) {
            y.h.a(SobotChatStatusMode.ZCServerConnectArtificial);
        }
        this.d = CustomerState.Online;
        this.h = false;
        this.aY = false;
        this.ba = 0;
        this.g = TextUtils.isEmpty(str) ? "" : str;
        this.b.a(com.sobot.chat.utils.c.b(this.a, str));
        this.b.b();
        if (this.e.isAdminHelloWordFlag() && (!this.e.isAdminHelloWordCountRule() || this.e.getUstatus() != 1)) {
            String b2 = s.b(this.a, "sobot_admin_hello_word", "");
            if (!TextUtils.isEmpty(b2)) {
                this.b.a(com.sobot.chat.utils.c.a(str, str2, b2));
            } else {
                this.b.a(com.sobot.chat.utils.c.a(str, str2, this.e.getAdminHelloWord()));
            }
        }
        this.b.notifyDataSetChanged();
        b(str, str2, false);
        Message obtainMessage = this.T.obtainMessage();
        obtainMessage.what = 1001;
        this.T.sendMessage(obtainMessage);
        aJ();
        ax();
        ay();
        ap();
        b(2);
        this.bp = 2;
        i();
        f();
        this.n = false;
        a(this.T);
        r();
        this.aj.setAutoCompleteEnable(false);
        if (this.f.getAutoSendMsgMode().getAuto_send_msgtype() == SobotAutoSendMsgMode.ZCMessageTypeText) {
            a(this.f);
        } else if (this.c == 302 && !TextUtils.isEmpty(this.f.getAutoSendMsgMode().getContent()) && this.f.getAutoSendMsgMode() == SobotAutoSendMsgMode.SendToOperator && this.d == CustomerState.Online) {
            String content = this.f.getAutoSendMsgMode().getContent();
            if (this.f.getAutoSendMsgMode().getAuto_send_msgtype() == SobotAutoSendMsgMode.ZCMessageTypeFile) {
                File file = new File(content);
                if (file.exists()) {
                    a(file, this.T, (ListView) this.ai, this.b, false);
                }
            } else if (this.f.getAutoSendMsgMode().getAuto_send_msgtype() == SobotAutoSendMsgMode.ZCMessageTypeVideo) {
                File file2 = new File(content);
                if (file2.exists()) {
                    a(file2, (Uri) null, this.b);
                }
            } else if (this.f.getAutoSendMsgMode().getAuto_send_msgtype() == SobotAutoSendMsgMode.ZCMessageTypePhoto) {
                File file3 = new File(content);
                if (file3.exists()) {
                    a(file3, this.T, (ListView) this.ai, this.b, false);
                }
            }
        }
        if (!this.j) {
            f(this.T);
        }
        if (!TextUtils.isEmpty(this.S)) {
            b(this.S);
            this.S = "";
        }
    }

    public void r() {
        if (e()) {
            this.ai.post(new AnonymousClass22());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$22  reason: invalid class name */
    public class AnonymousClass22 implements Runnable {
        AnonymousClass22() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int childCount = SobotChatFragment.this.ai.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = SobotChatFragment.this.ai.getChildAt(i);
                if (!(childAt == null || childAt.getTag() == null || !(childAt.getTag() instanceof RichTextMessageHolder))) {
                    RichTextMessageHolder richTextMessageHolder = (RichTextMessageHolder) childAt.getTag();
                    if (richTextMessageHolder.m != null) {
                        richTextMessageHolder.m.setShowTransferBtn(false);
                    }
                    richTextMessageHolder.a();
                }
            }
        }
    }

    private void ar() {
        if (this.e.isAdminNoneLineFlag()) {
            ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
            zhiChiReplyAnswer.setMsgType(null);
            String b2 = s.b(this.a, "sobot_admin_offline_title", "");
            if (!TextUtils.isEmpty(b2)) {
                zhiChiReplyAnswer.setMsg(b2);
            } else if (!TextUtils.isEmpty(this.e.getAdminNonelineTitle())) {
                zhiChiReplyAnswer.setMsg(this.e.getAdminNonelineTitle());
            } else {
                return;
            }
            zhiChiReplyAnswer.setRemindType(1);
            ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
            zhiChiMessageBase.setSenderType(Constants.VIA_REPORT_TYPE_CHAT_AIO);
            zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
            zhiChiMessageBase.setAction("action_remind_info_post_msg");
            a(this.b, zhiChiMessageBase);
        }
    }

    private void as() {
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiReplyAnswer.setMsgType(null);
        zhiChiReplyAnswer.setMsg(i("sobot_unable_transfer_to_customer_service"));
        zhiChiReplyAnswer.setRemindType(2);
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        zhiChiMessageBase.setSenderType(Constants.VIA_REPORT_TYPE_CHAT_AIO);
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        zhiChiMessageBase.setAction("action_remind_info_post_msg");
        a(this.b, zhiChiMessageBase);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, int i, String str2, boolean z) {
        if (this.d == CustomerState.Queuing && !TextUtils.isEmpty(str) && Integer.parseInt(str) > 0) {
            f();
            g();
            k();
            this.ba = Integer.parseInt(str);
            if (i != 7 && z) {
                k(str2);
            }
            if (this.I == 2) {
                b(i("sobot_in_line"), (String) null, false);
                b(3);
                this.bp = 3;
            } else {
                b(this.e.getRobotName(), this.e.getRobotLogo(), false);
                b(5);
                this.bp = 5;
            }
            this.bb++;
            if (this.I == 4 && this.bb == 1) {
                a(this.T, this.e, this.f);
            }
            aJ();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void at() {
        int i;
        if (this.e != null && (i = this.bl) != 1 && i != 2) {
            long b2 = s.b(this.a, "sobot_scope_time", 0L);
            this.bl = 1;
            this.U.queryCids(this, this.e.getPartnerid(), b2, new AnonymousClass23());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$23  reason: invalid class name */
    public class AnonymousClass23 implements com.sobot.chat.core.http.c.a<ZhiChiCidsModel> {
        AnonymousClass23() {
        }

        public void a(ZhiChiCidsModel zhiChiCidsModel) {
            if (SobotChatFragment.this.e()) {
                SobotChatFragment.this.bl = 2;
                SobotChatFragment.this.bj = zhiChiCidsModel.getCids();
                if (SobotChatFragment.this.bj != null) {
                    boolean z = false;
                    int i = 0;
                    while (true) {
                        if (i >= SobotChatFragment.this.bj.size()) {
                            break;
                        } else if (((String) SobotChatFragment.this.bj.get(i)).equals(SobotChatFragment.this.e.getCid())) {
                            z = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (!z) {
                        SobotChatFragment.this.bj.add(SobotChatFragment.this.e.getCid());
                    }
                    Collections.reverse(SobotChatFragment.this.bj);
                }
                SobotChatFragment.this.b(true);
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            SobotChatFragment.this.bl = 3;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void au() {
        b("", (String) null, false);
        this.ac.setVisibility(8);
        this.ad.setVisibility(8);
        this.ab.setVisibility(0);
        this.ae.setVisibility(0);
        this.af.setVisibility(0);
        this.aj.setVisibility(8);
        this.H.setVisibility(8);
        this.ah.setVisibility(0);
    }

    @Override // com.sobot.chat.adapter.e.a
    public void a() {
        a(this.f.getConsultingContent());
    }

    @Override // com.sobot.chat.adapter.e.a
    public void a(ZhiChiMessageBase zhiChiMessageBase, int i, int i2, String str) {
        a(zhiChiMessageBase, i, i2, str, (String) null);
    }

    @Override // com.sobot.chat.adapter.e.a
    public void a(ZhiChiMessageBase zhiChiMessageBase, int i, int i2, String str, String str2) {
        if (i == 5) {
            a(zhiChiMessageBase.getId(), zhiChiMessageBase.getAnswer().getLocationData(), this.T, false);
        }
        if (i == 4) {
            b(zhiChiMessageBase, 0, i2, str, str2);
        } else if (i == 3) {
            this.b.a(zhiChiMessageBase.getId(), zhiChiMessageBase.getSendSuccessState());
            this.b.notifyDataSetChanged();
            com.sobot.chat.utils.c.a(this.a, this.e.getCid(), this.e.getPartnerid(), zhiChiMessageBase.getContent(), this.T, zhiChiMessageBase.getId(), this.ai, this.b);
        } else if (i == 2) {
            a(zhiChiMessageBase.getId(), zhiChiMessageBase.getContent(), zhiChiMessageBase.getAnswer().getDuration(), 2, 1, this.T);
            a(zhiChiMessageBase.getId(), zhiChiMessageBase.getAnswer().getDuration(), this.e.getCid(), this.e.getPartnerid(), zhiChiMessageBase.getContent(), this.T);
        } else if (i == 1) {
            b(zhiChiMessageBase, 1, i2, str);
        } else if (i == 0) {
            if (!this.aX) {
                ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
                zhiChiReplyAnswer.setMsgType("0");
                zhiChiReplyAnswer.setMsg(zhiChiMessageBase.getContent());
                zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
                zhiChiMessageBase.setSenderType("0");
                if (zhiChiMessageBase.getId() == null || TextUtils.isEmpty(zhiChiMessageBase.getId())) {
                    zhiChiMessageBase.setId(System.currentTimeMillis() + "");
                    a(this.b, zhiChiMessageBase);
                }
                a(zhiChiMessageBase.getId(), zhiChiMessageBase.getContent(), this.e, this.T, this.c, i2, str);
            } else {
                b(this.e, 1);
            }
        }
        ap();
    }

    @Override // com.sobot.chat.adapter.e.a
    public void b() {
        a(this.aA);
        F();
        a((String) null, (String) null, (String) null, true);
    }

    @Override // com.sobot.chat.adapter.e.a
    public void a(ZhiChiMessageBase zhiChiMessageBase) {
        if (this.O == null) {
            this.O = new AudioPlayPresenter(this.a);
        }
        if (this.P == null) {
            this.P = new AnonymousClass24();
        }
        this.O.a(zhiChiMessageBase, this.P);
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$24  reason: invalid class name */
    class AnonymousClass24 implements com.sobot.chat.voice.a {
        AnonymousClass24() {
        }

        @Override // com.sobot.chat.voice.a
        public void a(ZhiChiMessageBase zhiChiMessageBase) {
            SobotChatFragment.this.a(zhiChiMessageBase, true);
        }

        @Override // com.sobot.chat.voice.a
        public void b(ZhiChiMessageBase zhiChiMessageBase) {
            SobotChatFragment.this.a(zhiChiMessageBase, false);
        }
    }

    public void a(ZhiChiMessageBase zhiChiMessageBase, boolean z) {
        if (e()) {
            this.ai.post(new AnonymousClass25(zhiChiMessageBase, z));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$25  reason: invalid class name */
    public class AnonymousClass25 implements Runnable {
        final /* synthetic */ ZhiChiMessageBase a;
        final /* synthetic */ boolean b;

        AnonymousClass25(ZhiChiMessageBase zhiChiMessageBase, boolean z) {
            this.a = zhiChiMessageBase;
            this.b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a != null) {
                int childCount = SobotChatFragment.this.ai.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = SobotChatFragment.this.ai.getChildAt(i);
                    if (!(childAt == null || childAt.getTag() == null || !(childAt.getTag() instanceof VoiceMessageHolder))) {
                        VoiceMessageHolder voiceMessageHolder = (VoiceMessageHolder) childAt.getTag();
                        voiceMessageHolder.b();
                        if (voiceMessageHolder.d == this.a && this.b) {
                            voiceMessageHolder.a();
                        }
                    }
                }
            }
        }
    }

    @Override // com.sobot.chat.adapter.e.a
    public void c() {
        a(this.aA);
    }

    @Override // com.sobot.chat.adapter.e.a
    public void b(boolean z, ZhiChiMessageBase zhiChiMessageBase) {
        if (this.aX) {
            b(this.e, 1);
            com.sobot.chat.utils.e.a(this.a, i("sobot_ding_cai_sessionoff"), 1500).show();
            return;
        }
        com.sobot.chat.utils.e.a(this.a, i(z ? "sobot_ding_cai_like" : "sobot_ding_cai_dislike"), 1500).show();
        this.U.rbAnswerComment(this, zhiChiMessageBase.getMsgId(), this.e.getPartnerid(), this.e.getCid(), this.e.getRobotid(), zhiChiMessageBase.getDocId(), zhiChiMessageBase.getDocName(), z, new AnonymousClass26(zhiChiMessageBase, z));
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$26  reason: invalid class name */
    class AnonymousClass26 implements com.sobot.chat.core.http.c.a<CommonModelBase> {
        final /* synthetic */ ZhiChiMessageBase a;
        final /* synthetic */ boolean b;

        AnonymousClass26(ZhiChiMessageBase zhiChiMessageBase, boolean z) {
            this.a = zhiChiMessageBase;
            this.b = z;
        }

        public void a(CommonModelBase commonModelBase) {
            if (SobotChatFragment.this.e()) {
                this.a.setRevaluateState(this.b ? 2 : 3);
                SobotChatFragment.this.a(RichTextMessageHolder.class);
                if (!TextUtils.isEmpty(this.a.getAnswerType()) && this.a.getAnswerType().startsWith("152")) {
                    SobotChatFragment.this.a(f.class);
                    SobotChatFragment.this.a(g.class);
                    SobotChatFragment.this.a(h.class);
                    SobotChatFragment.this.a(i.class);
                    SobotChatFragment.this.a(j.class);
                    SobotChatFragment.this.a(k.class);
                }
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            ae.a(SobotChatFragment.this.a, q.f(SobotChatFragment.this.getContext(), "sobot_net_work_err"));
        }
    }

    @Override // com.sobot.chat.adapter.e.a
    public void a(boolean z, ZhiChiMessageBase zhiChiMessageBase) {
        SobotEvaluateModel sobotEvaluateModel;
        if (this.e != null && zhiChiMessageBase != null && (sobotEvaluateModel = zhiChiMessageBase.getSobotEvaluateModel()) != null) {
            if (z) {
                SobotCommentParam sobotCommentParam = new SobotCommentParam();
                sobotCommentParam.setType("1");
                sobotCommentParam.setScore("5");
                sobotCommentParam.setCommentType(0);
                sobotCommentParam.setProblem(sobotEvaluateModel.getProblem());
                sobotCommentParam.setIsresolve(sobotEvaluateModel.getIsResolved());
                this.U.comment(this, this.e.getCid(), this.e.getPartnerid(), sobotCommentParam, new AnonymousClass27(zhiChiMessageBase));
                return;
            }
            a(false, sobotEvaluateModel.getScore(), sobotEvaluateModel.getIsResolved(), sobotEvaluateModel.getProblem());
        }
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$27  reason: invalid class name */
    class AnonymousClass27 implements com.sobot.chat.core.http.c.a<CommonModel> {
        final /* synthetic */ ZhiChiMessageBase a;

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
        }

        AnonymousClass27(ZhiChiMessageBase zhiChiMessageBase) {
            this.a = zhiChiMessageBase;
        }

        public void a(CommonModel commonModel) {
            if (SobotChatFragment.this.e()) {
                Intent intent = new Intent();
                intent.setAction(ZhiChiConstants.dcrc_comment_state);
                intent.putExtra("commentState", true);
                intent.putExtra("commentType", 0);
                intent.putExtra("score", this.a.getSobotEvaluateModel().getScore());
                intent.putExtra("isResolved", this.a.getSobotEvaluateModel().getIsResolved());
                d.a(SobotChatFragment.this.a, intent);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private <T> void a(Class<T> cls) {
        if (e()) {
            this.ai.post(new AnonymousClass28(cls));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$28  reason: invalid class name */
    public class AnonymousClass28 implements Runnable {
        final /* synthetic */ Class a;

        AnonymousClass28(Class cls) {
            this.a = cls;
        }

        @Override // java.lang.Runnable
        public void run() {
            int childCount = SobotChatFragment.this.ai.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = SobotChatFragment.this.ai.getChildAt(i);
                if (!(childAt == null || childAt.getTag() == null)) {
                    if (this.a == RichTextMessageHolder.class && (childAt.getTag() instanceof RichTextMessageHolder)) {
                        ((RichTextMessageHolder) childAt.getTag()).c();
                    } else if (this.a == com.sobot.chat.viewHolder.a.class && (childAt.getTag() instanceof com.sobot.chat.viewHolder.a)) {
                        ((com.sobot.chat.viewHolder.a) childAt.getTag()).a();
                    } else if (this.a == f.class && (childAt.getTag() instanceof f)) {
                        ((f) childAt.getTag()).c();
                    } else if (this.a == g.class && (childAt.getTag() instanceof g)) {
                        ((g) childAt.getTag()).c();
                    } else if (this.a == h.class && (childAt.getTag() instanceof h)) {
                        ((h) childAt.getTag()).c();
                    } else if (this.a == i.class && (childAt.getTag() instanceof i)) {
                        ((i) childAt.getTag()).c();
                    } else if (this.a == j.class && (childAt.getTag() instanceof j)) {
                        ((j) childAt.getTag()).c();
                    } else if (this.a == k.class && (childAt.getTag() instanceof k)) {
                        ((k) childAt.getTag()).c();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void av() {
        if (TextUtils.isEmpty(this.e.getAnnounceClickUrl()) || !this.e.getAnnounceClickFlag()) {
            this.aK.setTextColor(ContextCompat.getColor(getContext(), q.c(getContext(), "sobot_announcement_title_color")));
            this.aJ.setVisibility(8);
        } else {
            this.aJ.setVisibility(8);
            this.aK.setTextColor(ContextCompat.getColor(getContext(), q.c(getContext(), "sobot_announcement_title_color_2")));
        }
        if (!this.e.getAnnounceMsgFlag() || !this.e.isAnnounceTopFlag() || TextUtils.isEmpty(this.e.getAnnounceMsg())) {
            this.aI.setVisibility(8);
            return;
        }
        this.aI.setVisibility(0);
        this.aK.setText(this.e.getAnnounceMsg());
        this.aI.setOnClickListener(new AnonymousClass31());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$31  reason: invalid class name */
    public class AnonymousClass31 implements View.OnClickListener {
        AnonymousClass31() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!TextUtils.isEmpty(SobotChatFragment.this.e.getAnnounceClickUrl()) && SobotChatFragment.this.e.getAnnounceClickFlag()) {
                if (y.a != null) {
                    y.a.a(SobotChatFragment.this.e.getAnnounceClickUrl());
                } else if (y.b == null || !y.b.a(SobotChatFragment.this.e.getAnnounceClickUrl())) {
                    Intent intent = new Intent(SobotChatFragment.this.a, WebViewActivity.class);
                    intent.putExtra("url", SobotChatFragment.this.e.getAnnounceClickUrl());
                    SobotChatFragment.this.startActivity(intent);
                }
            }
        }
    }

    public void b(int i) {
        this.ah.setVisibility(8);
        int i2 = 0;
        this.H.setVisibility(0);
        this.ag.setVisibility(0);
        this.aj.setVisibility(0);
        this.aF.setVisibility(8);
        this.aH.setVisibility(0);
        w();
        if (l()) {
            this.aF.setVisibility(8);
            this.aH.setVisibility(0);
            this.ax.setVisibility(8);
            this.ap.setVisibility(8);
        }
        if (this.f.isHideMenuSatisfaction()) {
            this.X.setVisibility(8);
        } else {
            this.X.setVisibility(0);
        }
        this.aa.setVisibility(0);
        this.Z.setVisibility(0);
        m.d("setBottomView:" + i);
        switch (i) {
            case 0:
                I();
                if (this.aG.getVisibility() == 0) {
                    this.aH.setVisibility(0);
                    this.aC.setVisibility(0);
                    this.aF.setVisibility(8);
                    if (this.aB.getVisibility() == 0) {
                        this.aB.setVisibility(8);
                    }
                    this.al.setClickable(false);
                    this.al.setVisibility(8);
                }
                this.ap.setVisibility(8);
                this.ao.setVisibility(0);
                this.ak.setVisibility(8);
                return;
            case 1:
                if (!this.f.isArtificialIntelligence() || this.I != 3) {
                    this.al.setVisibility(0);
                } else if (this.aT >= this.f.getArtificialIntelligenceNum()) {
                    this.al.setVisibility(0);
                } else {
                    this.al.setVisibility(8);
                }
                this.al.setClickable(true);
                I();
                if (Build.VERSION.SDK_INT >= 11) {
                    this.al.setAlpha(1.0f);
                }
                if (this.aG.getVisibility() == 0) {
                    this.aH.setVisibility(0);
                    this.aC.setVisibility(0);
                    this.aF.setVisibility(8);
                    if (this.aB.getVisibility() == 0) {
                        this.aB.setVisibility(8);
                    }
                    this.al.setClickable(true);
                    this.al.setEnabled(true);
                }
                this.ao.setVisibility(0);
                this.ap.setVisibility(8);
                this.ak.setVisibility(8);
                return;
            case 2:
                aG();
                this.aw.setVisibility(8);
                this.al.setVisibility(8);
                this.ao.setVisibility(0);
                this.ak.setVisibility(8);
                an();
                I();
                this.ax.setEnabled(true);
                this.ax.setClickable(true);
                this.ao.setEnabled(true);
                this.ao.setClickable(true);
                this.ap.setClickable(true);
                this.ap.setEnabled(true);
                if (Build.VERSION.SDK_INT >= 11) {
                    this.ax.setAlpha(1.0f);
                    this.ao.setAlpha(1.0f);
                }
                this.aC.setVisibility(0);
                this.aH.setVisibility(0);
                this.aB.setVisibility(8);
                this.aB.setClickable(true);
                this.aB.setEnabled(true);
                this.ay.setText(i("sobot_press_say"));
                return;
            case 3:
                aw();
                a(this.aA);
                if (this.ai.getLastVisiblePosition() != this.b.getCount()) {
                    this.ai.setSelection(this.b.getCount());
                    return;
                }
                return;
            case 4:
                w();
                a(this.aA);
                this.aH.setVisibility(8);
                this.aF.setVisibility(0);
                if (this.f.isHideMenuSatisfaction()) {
                    this.X.setVisibility(8);
                } else {
                    this.X.setVisibility(0);
                }
                this.aa.setVisibility(0);
                this.aw.setVisibility(8);
                TextView textView = this.Z;
                if (this.e.getMsgFlag() == 1) {
                    i2 = 8;
                }
                textView.setVisibility(i2);
                this.ax.setVisibility(8);
                this.ai.setSelection(this.b.getCount());
                return;
            case 5:
                if (this.aB.getVisibility() == 8) {
                    I();
                }
                this.al.setVisibility(0);
                this.ap.setVisibility(8);
                if (this.aG.getVisibility() == 0) {
                    this.aH.setVisibility(0);
                    this.aC.setVisibility(0);
                    this.ax.setVisibility(8);
                    this.aF.setVisibility(8);
                    if (this.aB.getVisibility() == 0) {
                        this.aB.setVisibility(8);
                        return;
                    }
                    return;
                }
                return;
            case 6:
                this.aF.setVisibility(0);
                this.aH.setVisibility(8);
                if (this.aG.getVisibility() == 0) {
                    this.aa.setVisibility(0);
                    this.aa.setClickable(true);
                    this.aa.setEnabled(true);
                }
                if (this.e.getMsgFlag() == 1) {
                    this.X.setVisibility(4);
                    this.Z.setVisibility(4);
                    return;
                }
                this.X.setVisibility(8);
                this.Z.setVisibility(0);
                return;
            default:
                return;
        }
    }

    private void aw() {
        if (y.h != null) {
            y.h.a(SobotChatStatusMode.ZCServerConnectWaiting);
        }
        this.aH.setVisibility(0);
        this.al.setVisibility(8);
        this.al.setClickable(false);
        this.ao.setVisibility(0);
        this.ak.setVisibility(8);
        this.ao.setClickable(false);
        this.ao.setEnabled(false);
        an();
        this.ap.setClickable(false);
        this.ap.setEnabled(false);
        I();
        this.ax.setClickable(false);
        this.ax.setEnabled(false);
        this.ax.setVisibility(8);
        this.aC.setVisibility(8);
        this.aB.setClickable(false);
        this.aB.setEnabled(false);
        this.aB.setVisibility(0);
        this.ay.setText(i("sobot_in_line"));
        b(i("sobot_in_line"), (String) null, false);
        if (this.aF.getVisibility() == 0) {
            this.aF.setVisibility(8);
        }
    }

    private void ax() {
        ConsultingContent consultingContent = this.f.getConsultingContent();
        if (consultingContent != null && !TextUtils.isEmpty(consultingContent.getSobotGoodsTitle()) && !TextUtils.isEmpty(consultingContent.getSobotGoodsFromUrl())) {
            ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
            zhiChiMessageBase.setSenderType(Constants.VIA_REPORT_TYPE_CHAT_VIDEO);
            if (!TextUtils.isEmpty(consultingContent.getSobotGoodsImgUrl())) {
                zhiChiMessageBase.setPicurl(consultingContent.getSobotGoodsImgUrl());
            }
            zhiChiMessageBase.setAnswer(new ZhiChiReplyAnswer());
            zhiChiMessageBase.setContent(consultingContent.getSobotGoodsTitle());
            zhiChiMessageBase.setUrl(consultingContent.getSobotGoodsFromUrl());
            zhiChiMessageBase.setCid(this.e == null ? "" : this.e.getCid());
            zhiChiMessageBase.setAname(consultingContent.getSobotGoodsLable());
            zhiChiMessageBase.setReceiverFace(consultingContent.getSobotGoodsDescribe());
            zhiChiMessageBase.setAction("action_consultingContent_info");
            a(this.b, zhiChiMessageBase);
            this.T.post(new AnonymousClass29());
            if (consultingContent.isAutoSend()) {
                a();
            }
        } else if (this.b != null) {
            this.b.a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$29  reason: invalid class name */
    public class AnonymousClass29 implements Runnable {
        AnonymousClass29() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SobotChatFragment.this.ai.setSelection(SobotChatFragment.this.b.getCount());
        }
    }

    private void ay() {
        OrderCardContentModel orderGoodsInfo = this.f.getOrderGoodsInfo();
        if (orderGoodsInfo != null && !TextUtils.isEmpty(orderGoodsInfo.getOrderCode()) && orderGoodsInfo.isAutoSend()) {
            a(orderGoodsInfo);
        }
    }

    /* access modifiers changed from: protected */
    public void s() {
        a(this.aA);
        if (e()) {
            if (!this.f.isShowCloseSatisfaction()) {
                a(this.e, 1);
                com.sobot.chat.utils.c.b(this.a);
            } else if (!this.h || this.aY) {
                a(this.e, 1);
                com.sobot.chat.utils.c.b(this.a);
            } else {
                this.aN = com.sobot.chat.utils.c.a(Z(), this.aX, true, true, this.e, this.c, 1, this.g, 5, 0, "", false, true);
                return;
            }
            d();
        }
    }

    /* access modifiers changed from: protected */
    public void t() {
        a(this.aA);
        if (e()) {
            if (!this.f.isShowSatisfaction()) {
                a(this.e, 1);
                com.sobot.chat.utils.c.b(this.a);
            } else if (!this.h || this.aY) {
                a(this.e, 1);
                com.sobot.chat.utils.c.b(this.a);
            } else {
                this.aN = com.sobot.chat.utils.c.a(Z(), this.aX, true, true, this.e, this.c, 1, this.g, 5, 0, "", false, true);
                return;
            }
            d();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void az() {
        if (this.aj.getText().toString().length() > 0) {
            this.ao.setVisibility(8);
            this.ak.setVisibility(0);
            return;
        }
        this.ak.setVisibility(8);
        this.ao.setVisibility(0);
        this.ao.setEnabled(true);
        this.ao.setClickable(true);
        if (Build.VERSION.SDK_INT >= 11) {
            this.ao.setAlpha(1.0f);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(String str, String str2, boolean z) {
        if (this.e != null) {
            String b2 = com.sobot.chat.utils.c.b(this.a, z, str2, this.e.getCompanyLogo());
            boolean b3 = s.b(getContext(), "sobot_chat_avatar_is_show", true);
            if (TextUtils.isEmpty(str2)) {
                b3 = false;
            }
            a(b2, b3);
            String a2 = com.sobot.chat.utils.c.a(this.a, z, str, this.e.getCompanyName());
            boolean b4 = s.b(getContext(), "sobot_chat_title_is_show", false);
            if (TextUtils.isEmpty(str2)) {
                b4 = true;
            }
            a((CharSequence) a2, b4);
        }
    }

    public void a(CharSequence charSequence, boolean z) {
        if (z) {
            this.y.setVisibility(0);
        } else {
            this.y.setVisibility(8);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            this.y.setText(charSequence);
        }
        a(this.y);
    }

    public void a(String str, boolean z) {
        if (z) {
            this.z.setVisibility(0);
            this.z.setRoundAsCircle(true);
            this.z.setStrokeWidth(r.a(getContext(), 0.4f));
            this.z.setStrokeColor(q.d(getContext(), "sobot_line_1dp"));
            if (!TextUtils.isEmpty(str)) {
                t.a(getContext(), str, this.z);
            } else {
                t.a(getContext(), f("sobot_robot"), this.z);
            }
        } else {
            this.z.setVisibility(8);
        }
    }

    public void a(int i, boolean z) {
        if (z) {
            this.z.setVisibility(0);
            this.z.setRoundAsCircle(true);
            this.z.setStrokeWidth(r.a(getContext(), 0.4f));
            this.z.setStrokeColor(q.d(getContext(), "sobot_line_1dp"));
            t.a(getContext(), i, this.z);
            return;
        }
        this.z.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void u() {
        this.aT = 0;
        a(this.aA);
        if (this.aX || !this.f.isShowLeftBackPop()) {
            K();
            return;
        }
        this.bx = new com.sobot.chat.widget.dialog.a(Z(), new AnonymousClass33());
        this.bx.show();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$33  reason: invalid class name */
    public class AnonymousClass33 implements View.OnClickListener {
        AnonymousClass33() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotChatFragment.this.bx.dismiss();
            if (view.getId() == q.g(SobotChatFragment.this.getContext(), "sobot_btn_cancle_conversation")) {
                SobotChatFragment.this.t();
            } else if (view.getId() == q.g(SobotChatFragment.this.getContext(), "sobot_btn_temporary_leave") && SobotChatFragment.this.e()) {
                if (SobotChatFragment.this.aA.getVisibility() == 0) {
                    SobotChatFragment sobotChatFragment = SobotChatFragment.this;
                    sobotChatFragment.a(sobotChatFragment.aA);
                    return;
                }
                SobotChatFragment.this.d();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(View view) {
        a(this.aA);
        ClearHistoryDialog clearHistoryDialog = new ClearHistoryDialog(Z());
        clearHistoryDialog.setCanceledOnTouchOutside(true);
        clearHistoryDialog.a(new AnonymousClass30());
        clearHistoryDialog.show();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$30  reason: invalid class name */
    public class AnonymousClass30 implements ClearHistoryDialog.a {
        AnonymousClass30() {
        }

        @Override // com.sobot.chat.widget.ClearHistoryDialog.a
        public void a() {
            SobotChatFragment.this.v();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$35  reason: invalid class name */
    public class AnonymousClass35 implements View.OnClickListener {
        AnonymousClass35() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotChatFragment.this.by.dismiss();
            if (view.getId() == q.g(SobotChatFragment.this.getContext(), "sobot_btn_cancle_conversation")) {
                ZhiChiApi zhiChiApi = SobotChatFragment.this.U;
                SobotChatFragment sobotChatFragment = SobotChatFragment.this;
                zhiChiApi.deleteHisMsg(sobotChatFragment, sobotChatFragment.e.getPartnerid(), new AnonymousClass1());
            } else if (view.getId() == q.g(SobotChatFragment.this.getContext(), "sobot_btn_temporary_leave")) {
                SobotChatFragment.this.by.dismiss();
            }
        }

        /* renamed from: com.sobot.chat.conversation.SobotChatFragment$35$1  reason: invalid class name */
        class AnonymousClass1 implements com.sobot.chat.core.http.c.a<CommonModelBase> {
            @Override // com.sobot.chat.core.http.c.a
            public void a(Exception exc, String str) {
            }

            AnonymousClass1() {
            }

            public void a(CommonModelBase commonModelBase) {
                if (SobotChatFragment.this.e()) {
                    SobotChatFragment.this.aS.clear();
                    SobotChatFragment.this.bj.clear();
                    SobotChatFragment.this.b.notifyDataSetChanged();
                    SobotChatFragment.this.ai.setPullRefreshEnable(true);
                }
            }
        }
    }

    public void v() {
        com.sobot.chat.widget.dialog.b bVar = this.by;
        if (bVar == null) {
            this.by = new com.sobot.chat.widget.dialog.b(Z(), new AnonymousClass35());
            this.by.show();
            return;
        }
        bVar.show();
    }

    public void w() {
        this.aG.clearAnimation();
        this.aG.setVisibility(8);
    }

    public void x() {
        String e = e(this.aA);
        String b2 = com.sobot.chat.widget.kpswitch.view.a.b(this.a, this.ap.getId());
        if (this.aA.getVisibility() != 0 || !b2.equals(e)) {
            F();
        } else {
            y();
        }
    }

    public void y() {
        this.ap.setSelected(true);
    }

    public void F() {
        this.ap.setSelected(false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void aA() {
        m.d("\u4ec5\u4eba\u5de5\uff0c\u65e0\u5ba2\u670d\u5728\u7ebf");
        b(i("sobot_no_access"), (String) null, false);
        b(6);
        this.bp = 6;
        if (l()) {
            as();
        } else {
            ar();
        }
        this.aX = true;
    }

    @Override // com.sobot.chat.widget.kpswitch.view.ChattingPanelEmoticonView.a
    public void a(com.sobot.chat.widget.emoji.b bVar) {
        com.sobot.chat.widget.emoji.c.a(this.aj, bVar);
    }

    @Override // com.sobot.chat.widget.kpswitch.view.ChattingPanelEmoticonView.a
    public void z() {
        com.sobot.chat.widget.emoji.c.a(this.aj);
    }

    @Override // com.sobot.chat.widget.kpswitch.view.ChattingPanelUploadView.a
    public void A() {
        a(this.aA);
        W();
        this.ai.setSelection(this.b.getCount());
    }

    @Override // com.sobot.chat.widget.kpswitch.view.ChattingPanelUploadView.a
    public void B() {
        a(this.aA);
        X();
        this.ai.setSelection(this.b.getCount());
    }

    @Override // com.sobot.chat.widget.kpswitch.view.ChattingPanelUploadView.a
    public void C() {
        a(this.aA);
        U();
        this.ai.setSelection(this.b.getCount());
    }

    @Override // com.sobot.chat.widget.kpswitch.view.ChattingPanelUploadView.a
    public void D() {
        this.ai.setSelection(this.b.getCount());
        a(true, 5, 0, "");
    }

    @Override // com.sobot.chat.widget.kpswitch.view.ChattingPanelUploadView.a
    public void E() {
        if (!b(i("sobot_memory_card"), i("sobot_memory_card_yongtu"), 1) && M()) {
            startActivityForResult(new Intent(Z(), SobotChooseFileActivity.class), 107);
        }
    }

    @Override // com.sobot.chat.widget.kpswitch.view.ChattingPanelUploadView.a
    public void a(boolean z) {
        a(z, false);
    }

    public void a(boolean z, boolean z2) {
        if (this.e != null) {
            if (y.d != null) {
                y.d.a();
                return;
            }
            c();
            if (this.e.isMsgToTicketFlag()) {
                startActivityForResult(SobotPostLeaveMsgActivity.a(getContext(), this.e.getMsgLeaveTxt(), this.e.getMsgLeaveContentTxt(), this.e.getPartnerid()), 109);
            } else {
                this.bu.a(this.e.getPartnerid(), z, z2, new AnonymousClass32(z, z2));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$32  reason: invalid class name */
    public class AnonymousClass32 implements b.a {
        final /* synthetic */ boolean a;
        final /* synthetic */ boolean b;

        AnonymousClass32(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }

        @Override // com.sobot.chat.presenter.b.a
        public void a(Intent intent) {
            intent.putExtra("intent_key_companyid", SobotChatFragment.this.e.getCompanyId());
            intent.putExtra("intent_key_customerid", SobotChatFragment.this.e.getCustomerId());
            intent.putExtra("FLAG_EXIT_SDK", this.a);
            intent.putExtra("intent_key_groupid", SobotChatFragment.this.f.getGroupid());
            intent.putExtra("intent_key_is_show_ticket", this.b);
            SobotChatFragment.this.startActivity(intent);
            if (SobotChatFragment.this.Z() != null) {
                SobotChatFragment.this.Z().overridePendingTransition(q.a(SobotChatFragment.this.a, "anim", "push_left_in"), q.a(SobotChatFragment.this.a, "anim", "push_left_out"));
            }
        }
    }

    public void H() {
        if (this.ap.isSelected()) {
            F();
        } else {
            y();
        }
        if (this.ap.isSelected()) {
            this.ap.setBackgroundResource(q.e(getContext(), "sobot_keyboard_normal"));
        } else {
            this.ap.setBackgroundResource(q.e(getContext(), "sobot_emoticon_button_selector"));
        }
    }

    public void a(View view, View view2, View view3) {
        int i = this.Q;
        if (i == 0 || i == view2.getId()) {
            if (!(view.getVisibility() != 0)) {
                com.sobot.chat.widget.kpswitch.util.a.a(view, view3);
            } else {
                com.sobot.chat.widget.kpswitch.util.a.a(view);
                a(view, view2.getId());
            }
        } else {
            com.sobot.chat.widget.kpswitch.util.a.a(view);
            a(view, view2.getId());
        }
        this.Q = view2.getId();
    }

    public void c(View view) {
        if (this.aB.isShown()) {
            this.aw.setVisibility(8);
            I();
            this.aB.setVisibility(8);
            this.aC.setVisibility(0);
            this.aj.requestFocus();
            com.sobot.chat.widget.kpswitch.util.a.a(this.aA);
            a(this.aA, view.getId());
            this.Q = view.getId();
            return;
        }
        a(this.aA, view, this.aj);
    }

    private void a(View view, int i) {
        View childAt;
        if ((view instanceof KPSwitchPanelLinearLayout) && (childAt = ((KPSwitchPanelLinearLayout) view).getChildAt(0)) != null && (childAt instanceof CustomeChattingPanel)) {
            Bundle bundle = new Bundle();
            bundle.putInt("current_client_model", this.c);
            ((CustomeChattingPanel) childAt).a(i, bundle, this);
        }
    }

    private String e(View view) {
        View childAt;
        return (!(view instanceof KPSwitchPanelLinearLayout) || (childAt = ((KPSwitchPanelLinearLayout) view).getChildAt(0)) == null || !(childAt instanceof CustomeChattingPanel)) ? "" : ((CustomeChattingPanel) childAt).getPanelViewTag();
    }

    public void a(KPSwitchPanelLinearLayout kPSwitchPanelLinearLayout) {
        this.aj.a();
        com.sobot.chat.widget.kpswitch.util.a.b(kPSwitchPanelLinearLayout);
        F();
        this.Q = 0;
    }

    private void l(String str) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiMessageBase.setSenderType(Constants.VIA_REPORT_TYPE_CHAT_AIO);
        zhiChiReplyAnswer.setMsg(str);
        zhiChiReplyAnswer.setRemindType(8);
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        zhiChiMessageBase.setAction("action_remind_no_service");
        a(this.b, zhiChiMessageBase);
    }

    @Override // com.sobot.chat.widget.ContainsEmojiEditText.d
    public void c(String str) {
        this.aj.setText("");
        b(str);
    }

    @Override // com.sobot.chat.widget.DropdownListView.b
    public void G() {
        b(false);
    }

    public void b(boolean z) {
        if (this.e != null) {
            int i = this.bl;
            if (i == 0 || i == 3) {
                aC();
                at();
            } else if ((i != 1 || z) && !this.bm) {
                String a2 = com.sobot.chat.utils.c.a(this.e, this.bj, this.bk);
                if ("-1".equals(a2)) {
                    aB();
                    aC();
                    return;
                }
                this.bm = true;
                this.U.getChatDetailByCid(this, this.e.getPartnerid(), a2, new AnonymousClass34());
            } else {
                aC();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$34  reason: invalid class name */
    public class AnonymousClass34 implements com.sobot.chat.core.http.c.a<ZhiChiHistoryMessage> {
        AnonymousClass34() {
        }

        public void a(ZhiChiHistoryMessage zhiChiHistoryMessage) {
            SobotChatFragment.this.bm = false;
            if (SobotChatFragment.this.e()) {
                SobotChatFragment.this.aC();
                SobotChatFragment.W(SobotChatFragment.this);
                List<ZhiChiHistoryMessageBase> data = zhiChiHistoryMessage.getData();
                if (data == null || data.size() <= 0) {
                    SobotChatFragment.this.b(false);
                } else {
                    SobotChatFragment.this.b(data);
                }
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            SobotChatFragment.this.bm = false;
            if (SobotChatFragment.this.e()) {
                SobotChatFragment.this.bc = 0;
                SobotChatFragment.this.aq();
                SobotChatFragment.this.aC();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(List<ZhiChiHistoryMessageBase> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            List<ZhiChiMessageBase> content = list.get(i).getContent();
            for (ZhiChiMessageBase zhiChiMessageBase : content) {
                zhiChiMessageBase.setSugguestionsFontColor(1);
                if (!"29".equals(zhiChiMessageBase.getAction()) && zhiChiMessageBase.getSdkMsg() != null) {
                    ZhiChiReplyAnswer answer = zhiChiMessageBase.getSdkMsg().getAnswer();
                    if (answer != null) {
                        if (answer.getMsgType() == null) {
                            answer.setMsgType("0");
                        }
                        if (!TextUtils.isEmpty(answer.getMsg()) && answer.getMsg().length() > 4) {
                            String replace = answer.getMsg().replace("&lt;/p&gt;", "<br>");
                            if (replace.endsWith("<br>")) {
                                replace = replace.substring(0, replace.length() - 4);
                            }
                            answer.setMsg(replace);
                        }
                    }
                    if (!TextUtils.isEmpty(zhiChiMessageBase.getSenderType())) {
                        if (1 == Integer.parseInt(zhiChiMessageBase.getSenderType())) {
                            zhiChiMessageBase.setSenderName(TextUtils.isEmpty(zhiChiMessageBase.getSenderName()) ? this.e.getRobotName() : zhiChiMessageBase.getSenderName());
                            zhiChiMessageBase.setSenderFace(TextUtils.isEmpty(zhiChiMessageBase.getSenderFace()) ? this.e.getRobotLogo() : zhiChiMessageBase.getSenderFace());
                        }
                        zhiChiMessageBase.setAnswer(answer);
                        zhiChiMessageBase.setAnswerType(zhiChiMessageBase.getSdkMsg().getAnswerType());
                    }
                }
            }
            arrayList.addAll(content);
        }
        if (arrayList.size() > 0) {
            if (this.bc > 0) {
                ZhiChiMessageBase a2 = com.sobot.chat.utils.c.a(this.a);
                a2.setCid(((ZhiChiMessageBase) arrayList.get(arrayList.size() - 1)).getCid());
                arrayList.add(arrayList.size() - this.bc < 0 ? 0 : arrayList.size() - this.bc, a2);
                aq();
                this.bc = 0;
            }
            this.b.a(arrayList);
            this.b.notifyDataSetChanged();
            this.ai.setSelection(arrayList.size());
        }
    }

    private void aB() {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        zhiChiMessageBase.setSenderType(Constants.VIA_REPORT_TYPE_CHAT_AIO);
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiReplyAnswer.setRemindType(6);
        zhiChiReplyAnswer.setMsg("");
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        b(this.b, zhiChiMessageBase);
        this.ai.setSelection(0);
        this.ai.setPullRefreshEnable(false);
        this.bo = true;
        this.bc = 0;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void aC() {
        this.ai.a();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(int i, String str) {
        this.aw.setVisibility(8 == i ? 8 : 0);
        this.ax.setVisibility(i != 0 ? 0 : 8);
        this.aB.setVisibility(8 != i ? 0 : 8);
        this.aC.setVisibility(i == 0 ? 8 : 0);
        if (TextUtils.isEmpty(this.aj.getText().toString()) || !str.equals("123")) {
            this.ak.setVisibility(8);
            this.ao.setVisibility(0);
            return;
        }
        this.ak.setVisibility(0);
        this.ao.setVisibility(8);
    }

    public void c(boolean z) {
        this.F.setVisibility(z ? 0 : 8);
    }

    public class MyMessageReceiver extends BroadcastReceiver {
        public MyMessageReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                return;
            }
            if (d.a(SobotChatFragment.this.a)) {
                SobotChatFragment.this.c(false);
            } else if (SobotChatFragment.this.ah.getVisibility() != 0) {
                SobotChatFragment.this.c(true);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ZhiChiPushMessage zhiChiPushMessage;
            try {
                m.d("\u5e7f\u64ad\u662f  :" + intent.getAction());
                if (ZhiChiConstants.receiveMessageBrocast.equals(intent.getAction())) {
                    try {
                        Bundle extras = intent.getExtras();
                        if (extras != null) {
                            zhiChiPushMessage = (ZhiChiPushMessage) extras.getSerializable(ZhiChiConstants.ZHICHI_PUSH_MESSAGE);
                            try {
                                m.d("\u5e7f\u64ad\u5bf9\u8c61\u662f  :" + zhiChiPushMessage.toString());
                            } catch (Exception unused) {
                            }
                            if (zhiChiPushMessage != null && SobotChatFragment.this.f.getApp_key().equals(zhiChiPushMessage.getAppId())) {
                                ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
                                if (215 == zhiChiPushMessage.getType() && SobotChatFragment.this.d == CustomerState.Online) {
                                    zhiChiMessageBase.setAction("29");
                                    zhiChiMessageBase.setMsgId(zhiChiPushMessage.getMsgId());
                                    zhiChiMessageBase.setMsg(zhiChiPushMessage.getContent());
                                    SobotChatFragment.this.g();
                                    SobotChatFragment.this.a(SobotChatFragment.this.T);
                                    SobotChatFragment.this.b.b(zhiChiMessageBase);
                                    SobotChatFragment.this.b.notifyDataSetChanged();
                                    com.sobot.chat.utils.c.a(SobotChatFragment.this.e, SobotChatFragment.this.b, zhiChiPushMessage);
                                    SobotChatFragment.this.b.notifyDataSetChanged();
                                    return;
                                }
                                zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
                                zhiChiMessageBase.setMsgId(zhiChiPushMessage.getMsgId());
                                zhiChiMessageBase.setSender(zhiChiPushMessage.getAname());
                                zhiChiMessageBase.setSenderName(zhiChiPushMessage.getAname());
                                zhiChiMessageBase.setSenderFace(zhiChiPushMessage.getAface());
                                zhiChiMessageBase.setOrderCardContent(zhiChiPushMessage.getOrderCardContent());
                                zhiChiMessageBase.setConsultingContent(zhiChiPushMessage.getConsultingContent());
                                zhiChiMessageBase.setSenderType("2");
                                zhiChiMessageBase.setAnswer(zhiChiPushMessage.getAnswer());
                                if (200 == zhiChiPushMessage.getType()) {
                                    SobotChatFragment.this.a(zhiChiPushMessage.getAface());
                                    if (SobotChatFragment.this.I == 2 || SobotChatFragment.this.I == 3 || SobotChatFragment.this.I == 4) {
                                        SobotChatFragment.this.b(zhiChiPushMessage.getAname(), zhiChiPushMessage.getAface());
                                    }
                                } else if (201 == zhiChiPushMessage.getType()) {
                                    SobotChatFragment.this.a(zhiChiPushMessage.getCount(), 0, zhiChiPushMessage.getQueueDoc(), SobotChatFragment.this.aZ);
                                } else if (202 == zhiChiPushMessage.getType()) {
                                    if (SobotChatFragment.this.d == CustomerState.Online) {
                                        zhiChiMessageBase.setMsgId(zhiChiPushMessage.getMsgId());
                                        zhiChiMessageBase.setSender(zhiChiPushMessage.getAname());
                                        zhiChiMessageBase.setSenderName(zhiChiPushMessage.getAname());
                                        zhiChiMessageBase.setSenderFace(zhiChiPushMessage.getAface());
                                        zhiChiMessageBase.setSenderType("2");
                                        zhiChiMessageBase.setAnswer(zhiChiPushMessage.getAnswer());
                                        SobotChatFragment.this.g();
                                        SobotChatFragment.this.a(SobotChatFragment.this.T);
                                        SobotChatFragment.this.b.b(zhiChiMessageBase);
                                        SobotChatFragment.this.b.notifyDataSetChanged();
                                        com.sobot.chat.utils.c.a(SobotChatFragment.this.e, SobotChatFragment.this.b, zhiChiPushMessage);
                                        SobotChatFragment.this.b.notifyDataSetChanged();
                                    }
                                } else if (204 == zhiChiPushMessage.getType()) {
                                    SobotChatFragment.this.a(SobotChatFragment.this.e, Integer.parseInt(zhiChiPushMessage.getStatus()));
                                } else if (210 == zhiChiPushMessage.getType()) {
                                    m.d("\u7528\u6237\u88ab\u8f6c\u63a5--->" + zhiChiPushMessage.getName());
                                    SobotChatFragment.this.b(zhiChiPushMessage.getName(), zhiChiPushMessage.getFace(), false);
                                    SobotChatFragment.this.a(zhiChiPushMessage.getFace());
                                    SobotChatFragment.this.g = zhiChiPushMessage.getName();
                                } else if (213 == zhiChiPushMessage.getType()) {
                                    if (SobotChatFragment.this.d == CustomerState.Online) {
                                        if (1 == zhiChiPushMessage.getLockType()) {
                                            SobotChatFragment.this.u = SobotChatFragment.this.t;
                                            SobotChatFragment.this.p = SobotChatFragment.this.o;
                                            SobotChatFragment.this.q = 1;
                                            if (SobotChatFragment.this.n) {
                                                m.d("\u5ba2\u670d\u5b9a\u65f6\u4efb\u52a1 \u9501\u5b9a--->" + SobotChatFragment.this.t);
                                                SobotChatFragment.this.g();
                                                SobotChatFragment.this.n = true;
                                                SobotChatFragment.this.t = SobotChatFragment.this.u;
                                                SobotChatFragment.this.l = true;
                                            } else {
                                                m.d("\u7528\u6237\u5b9a\u65f6\u4efb\u52a1 \u9501\u5b9a--->" + SobotChatFragment.this.o);
                                                SobotChatFragment.this.f();
                                                SobotChatFragment.this.o = SobotChatFragment.this.p;
                                                SobotChatFragment.this.m = true;
                                            }
                                        } else {
                                            SobotChatFragment.this.q = 2;
                                            if (SobotChatFragment.this.c == 302) {
                                                if (SobotChatFragment.this.n) {
                                                    SobotChatFragment.this.g();
                                                    SobotChatFragment.this.d(SobotChatFragment.this.T);
                                                    SobotChatFragment.this.t = SobotChatFragment.this.u;
                                                    SobotChatFragment.this.l = true;
                                                    m.d("\u5ba2\u670d\u5b9a\u65f6\u4efb\u52a1 \u89e3\u9501--->" + SobotChatFragment.this.t);
                                                } else {
                                                    SobotChatFragment.this.f();
                                                    SobotChatFragment.this.a(SobotChatFragment.this.T);
                                                    SobotChatFragment.this.m = true;
                                                    SobotChatFragment.this.o = SobotChatFragment.this.p;
                                                    m.d("\u7528\u6237\u5b9a\u65f6\u4efb\u52a1 \u89e3\u9501--->" + SobotChatFragment.this.o);
                                                }
                                            }
                                        }
                                    }
                                } else if (209 == zhiChiPushMessage.getType()) {
                                    m.d("\u5ba2\u670d\u63a8\u9001\u6ee1\u610f\u5ea6\u8bc4\u4ef7.................");
                                    if (SobotChatFragment.this.h && !SobotChatFragment.this.aY && SobotChatFragment.this.d == CustomerState.Online) {
                                        SobotChatFragment.this.a(SobotChatFragment.this.b, com.sobot.chat.utils.c.a(zhiChiPushMessage));
                                    }
                                } else if (211 == zhiChiPushMessage.getType() && !TextUtils.isEmpty(zhiChiPushMessage.getRevokeMsgId())) {
                                    List<ZhiChiMessageBase> d = SobotChatFragment.this.b.d();
                                    int size = d.size() - 1;
                                    while (true) {
                                        if (size < 0) {
                                            break;
                                        }
                                        ZhiChiMessageBase zhiChiMessageBase2 = d.get(size);
                                        if (!zhiChiPushMessage.getRevokeMsgId().equals(zhiChiMessageBase2.getMsgId())) {
                                            size--;
                                        } else if (!zhiChiMessageBase2.isRetractedMsg()) {
                                            zhiChiMessageBase2.setRetractedMsg(true);
                                            SobotChatFragment.this.b.notifyDataSetChanged();
                                        }
                                    }
                                }
                            } else {
                                return;
                            }
                        }
                    } catch (Exception unused2) {
                    }
                    zhiChiPushMessage = null;
                    if (zhiChiPushMessage != null) {
                        return;
                    }
                    return;
                } else if ("SOBOT_BROCAST_ACTION_SEND_LOCATION".equals(intent.getAction())) {
                    SobotLocationModel sobotLocationModel = (SobotLocationModel) intent.getSerializableExtra("SOBOT_LOCATION_DATA");
                    if (sobotLocationModel != null) {
                        SobotChatFragment.this.a((String) null, sobotLocationModel, SobotChatFragment.this.T, true);
                    }
                } else if ("SOBOT_BROCAST_ACTION_SEND_TEXT".equals(intent.getAction())) {
                    String stringExtra = intent.getStringExtra("SOBOT_SEND_DATA");
                    String stringExtra2 = intent.getStringExtra("sendTextTo");
                    if (301 != SobotChatFragment.this.c || !"robot".equals(stringExtra2)) {
                        if (302 == SobotChatFragment.this.c && "user".equals(stringExtra2) && !TextUtils.isEmpty(stringExtra)) {
                            SobotChatFragment.this.b(stringExtra);
                        }
                    } else if (!TextUtils.isEmpty(stringExtra)) {
                        SobotChatFragment.this.b(stringExtra);
                    }
                } else if ("SOBOT_BROCAST_ACTION_SEND_OBJECT".equals(intent.getAction())) {
                    String stringExtra3 = intent.getStringExtra("SOBOT_SEND_DATA");
                    String stringExtra4 = intent.getStringExtra("SOBOT_TYPE_DATA");
                    if (302 == SobotChatFragment.this.c) {
                        if (TextUtils.isEmpty(stringExtra3)) {
                            m.d("\u53d1\u9001\u5185\u5bb9\u4e0d\u80fd\u4e3a\u7a7a");
                            return;
                        } else if ("0".equals(stringExtra4)) {
                            SobotChatFragment.this.b(stringExtra3);
                        } else if ("1".equals(stringExtra4)) {
                            File file = new File(stringExtra3);
                            if (file.exists()) {
                                SobotChatFragment.this.a(file, SobotChatFragment.this.T, (ListView) SobotChatFragment.this.ai, SobotChatFragment.this.b, false);
                            }
                        } else if ("3".equals(stringExtra4)) {
                            File file2 = new File(stringExtra3);
                            if (file2.exists()) {
                                SobotChatFragment.this.a(file2, (Uri) null, SobotChatFragment.this.b);
                            }
                        } else if ("4".equals(stringExtra4)) {
                            File file3 = new File(stringExtra3);
                            if (file3.exists()) {
                                SobotChatFragment.this.a(file3, SobotChatFragment.this.T, (ListView) SobotChatFragment.this.ai, SobotChatFragment.this.b, false);
                            }
                        }
                    }
                } else if ("SOBOT_BROCAST_ACTION_TRASNFER_TO_OPERATOR".equals(intent.getAction())) {
                    SobotTransferOperatorParam sobotTransferOperatorParam = (SobotTransferOperatorParam) intent.getSerializableExtra("SOBOT_SEND_DATA");
                    if (sobotTransferOperatorParam != null) {
                        if (sobotTransferOperatorParam.getConsultingContent() != null) {
                            SobotChatFragment.this.f.setConsultingContent(sobotTransferOperatorParam.getConsultingContent());
                        }
                        if (sobotTransferOperatorParam.getSummary_params() != null) {
                            SobotChatFragment.this.f.setSummary_params(sobotTransferOperatorParam.getSummary_params());
                        }
                        SobotChatFragment.this.a(sobotTransferOperatorParam.getGroupId(), sobotTransferOperatorParam.getGroupName(), sobotTransferOperatorParam.getKeyword(), sobotTransferOperatorParam.getKeywordId(), sobotTransferOperatorParam.isShowTips());
                    }
                } else if ("SOBOT_BROCAST_ACTION_SEND_CARD".equals(intent.getAction())) {
                    SobotChatFragment.this.a((ConsultingContent) intent.getSerializableExtra("SOBOT_SEND_DATA"));
                } else if ("SOBOT_BROCAST_ACTION_SEND_ORDERCARD".equals(intent.getAction())) {
                    SobotChatFragment.this.a((OrderCardContentModel) intent.getSerializableExtra("SOBOT_SEND_DATA"));
                }
                if (ZhiChiConstants.chat_remind_post_msg.equals(intent.getAction())) {
                    if (intent.getBooleanExtra("isShowTicket", false)) {
                        int size2 = SobotChatFragment.this.aS.size() - 1;
                        while (true) {
                            if (size2 > 0) {
                                if (!TextUtils.isEmpty(((ZhiChiMessageBase) SobotChatFragment.this.aS.get(size2)).getSenderType()) && Integer.parseInt(((ZhiChiMessageBase) SobotChatFragment.this.aS.get(size2)).getSenderType()) == 24 && ((ZhiChiMessageBase) SobotChatFragment.this.aS.get(size2)).getAnswer() != null && 9 == ((ZhiChiMessageBase) SobotChatFragment.this.aS.get(size2)).getAnswer().getRemindType()) {
                                    SobotChatFragment.this.aS.remove(size2);
                                    SobotChatFragment.this.b.notifyDataSetChanged();
                                    break;
                                }
                                size2--;
                            } else {
                                break;
                            }
                        }
                        Intent a = SobotChatFragment.this.bu.a(SobotChatFragment.this.e.getUid(), (SobotLeaveMsgConfig) null);
                        a.putExtra("intent_key_companyid", SobotChatFragment.this.e.getCompanyId());
                        a.putExtra("intent_key_customerid", SobotChatFragment.this.e.getCustomerId());
                        a.putExtra("FLAG_EXIT_SDK", false);
                        a.putExtra("intent_key_groupid", SobotChatFragment.this.f.getSkillSetId());
                        a.putExtra("intent_key_is_show_ticket", true);
                        SobotChatFragment.this.startActivity(a);
                        if (SobotChatFragment.this.Z() != null) {
                            SobotChatFragment.this.Z().overridePendingTransition(q.a(SobotChatFragment.this.a, "anim", "push_left_in"), q.a(SobotChatFragment.this.a, "anim", "push_left_out"));
                            return;
                        }
                        return;
                    }
                    SobotChatFragment.this.a(false, false);
                } else if (ZhiChiConstants.sobot_click_cancle.equals(intent.getAction())) {
                    if (SobotChatFragment.this.I == 4 && SobotChatFragment.this.c == 301) {
                        SobotChatFragment.this.a(SobotChatFragment.this.T, SobotChatFragment.this.e, SobotChatFragment.this.f);
                    }
                } else if (ZhiChiConstants.dcrc_comment_state.equals(intent.getAction())) {
                    SobotChatFragment.this.aY = intent.getBooleanExtra("commentState", false);
                    boolean booleanExtra = intent.getBooleanExtra("isFinish", false);
                    boolean booleanExtra2 = intent.getBooleanExtra("isExitSession", false);
                    intent.getIntExtra("commentType", 1);
                    intent.getIntExtra("score", 5);
                    intent.getIntExtra("isResolved", 0);
                    SobotChatFragment.this.b.c();
                    SobotChatFragment.this.b.notifyDataSetChanged();
                    if (booleanExtra2 || com.sobot.chat.utils.c.a(SobotChatFragment.this.a, SobotChatFragment.this.aY, SobotChatFragment.this.c)) {
                        SobotChatFragment.this.aX = true;
                        SobotChatFragment.this.a(SobotChatFragment.this.e, 5);
                        com.sobot.chat.utils.c.b(SobotChatFragment.this.a);
                    }
                    if (SobotChatFragment.this.e()) {
                        com.sobot.chat.utils.c.a(SobotChatFragment.this.Z(), SobotChatFragment.this.T, booleanExtra);
                    }
                } else if (ZhiChiConstants.sobot_close_now.equals(intent.getAction())) {
                    if (intent.getBooleanExtra("isBackShowEvaluate", true)) {
                        SobotChatFragment.this.d();
                        return;
                    }
                    SobotChatFragment.this.a(SobotChatFragment.this.e, 1);
                    com.sobot.chat.utils.c.b(SobotChatFragment.this.a);
                    SobotChatFragment.this.d();
                } else if (ZhiChiConstants.sobot_close_now_clear_cache.equals(intent.getAction())) {
                    SobotChatFragment.this.aX = true;
                    SobotChatFragment.this.d();
                } else if (ZhiChiConstants.SOBOT_CHANNEL_STATUS_CHANGE.equals(intent.getAction())) {
                    if (SobotChatFragment.this.d == CustomerState.Online || SobotChatFragment.this.d == CustomerState.Queuing) {
                        int intExtra = intent.getIntExtra("connStatus", 1);
                        m.d("connStatus:" + intExtra);
                        if (intExtra == 0) {
                            SobotChatFragment.this.B.setVisibility(0);
                            SobotChatFragment.this.A.setText(SobotChatFragment.this.i("sobot_conntype_unconnected"));
                            if (SobotChatFragment.this.x != null) {
                                SobotChatFragment.this.x.setVisibility(8);
                            }
                            SobotChatFragment.this.E.setVisibility(8);
                            if (SobotChatFragment.this.ah.getVisibility() != 0) {
                                SobotChatFragment.this.c(true);
                            }
                        } else if (intExtra == 1) {
                            SobotChatFragment.this.B.setVisibility(0);
                            SobotChatFragment.this.A.setText(SobotChatFragment.this.i("sobot_conntype_in_connection"));
                            if (SobotChatFragment.this.x != null) {
                                SobotChatFragment.this.x.setVisibility(8);
                            }
                            SobotChatFragment.this.E.setVisibility(0);
                        } else if (intExtra == 2) {
                            SobotChatFragment.this.c(false);
                            SobotChatFragment.this.B.setVisibility(8);
                            SobotChatFragment.this.A.setText("");
                            if (SobotChatFragment.this.x != null) {
                                SobotChatFragment.this.x.setVisibility(0);
                            }
                            SobotChatFragment.this.E.setVisibility(8);
                        }
                    } else {
                        SobotChatFragment.this.y.setVisibility(8);
                        SobotChatFragment.this.z.setVisibility(0);
                        SobotChatFragment.this.B.setVisibility(8);
                    }
                } else if (ZhiChiConstants.SOBOT_BROCAST_KEYWORD_CLICK.equals(intent.getAction())) {
                    SobotChatFragment.this.a(intent.getStringExtra("tempGroupId"), intent.getStringExtra("keyword"), intent.getStringExtra("keywordId"), true);
                } else if (ZhiChiConstants.SOBOT_BROCAST_REMOVE_FILE_TASK.equals(intent.getAction())) {
                    String stringExtra5 = intent.getStringExtra("sobot_msgId");
                    if (!TextUtils.isEmpty(stringExtra5)) {
                        int size3 = SobotChatFragment.this.aS.size() - 1;
                        while (true) {
                            if (size3 < 0) {
                                break;
                            } else if (stringExtra5.equals(((ZhiChiMessageBase) SobotChatFragment.this.aS.get(size3)).getId())) {
                                SobotChatFragment.this.aS.remove(size3);
                                break;
                            } else {
                                size3--;
                            }
                        }
                        SobotChatFragment.this.b.notifyDataSetChanged();
                    }
                }
            } catch (Exception unused3) {
            }
        }
    }

    private void aD() {
        ZhiChiConfig a2 = com.sobot.chat.core.channel.a.a(this.a).a(this.f.getApp_key());
        a2.isShowUnreadUi = true;
        a2.setMessageList(this.aS);
        a2.setInitModel(this.e);
        a2.current_client_model = this.c;
        int i = this.bl;
        if (i == 2) {
            a2.cids = this.bj;
            a2.currentCidPosition = this.bk;
            a2.queryCidsStatus = i;
        }
        a2.activityTitle = J();
        a2.customerState = this.d;
        a2.remindRobotMessageTimes = this.i;
        a2.isAboveZero = this.h;
        a2.isComment = this.aY;
        a2.adminFace = h();
        a2.paseReplyTimeCustoms = this.t;
        a2.customTimeTask = this.l;
        a2.paseReplyTimeUserInfo = this.o;
        a2.userInfoTimeTask = this.m;
        a2.isChatLock = this.q;
        a2.currentUserName = this.g;
        a2.isNoMoreHistoryMsg = this.bo;
        a2.showTimeVisiableCustomBtn = this.aT;
        a2.bottomViewtype = this.bp;
        a2.queueNum = this.ba;
        a2.isShowQueueTip = this.aZ;
        a2.tempMsgContent = this.S;
        if (a2.isChatLock == 2 || a2.isChatLock == 0) {
            Intent intent = new Intent();
            intent.setAction(ZhiChiConstants.SOBOT_TIMER_BROCAST);
            intent.putExtra("info", this.f);
            intent.putExtra("isStartTimer", true);
            this.bs.sendBroadcast(intent);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        SobotRobotListDialog sobotRobotListDialog;
        if (view == this.Y) {
            int size = this.aS.size() - 1;
            while (true) {
                if (size >= 0) {
                    if (this.aS.get(size).getAnswer() != null && 7 == this.aS.get(size).getAnswer().getRemindType()) {
                        this.ai.setSelection(size);
                        break;
                    }
                    size--;
                } else {
                    break;
                }
            }
            this.Y.setVisibility(8);
        }
        if (view == this.ak) {
            String trim = this.aj.getText().toString().trim();
            if (!TextUtils.isEmpty(trim) && !this.bn) {
                x();
                try {
                    this.aj.setText("");
                    b(trim);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Button button = this.ao;
        if (view == button) {
            c(button);
            F();
            ap();
        }
        ImageButton imageButton = this.ap;
        if (view == imageButton) {
            c(imageButton);
            H();
            ap();
        }
        if (view == this.aw) {
            aG();
            F();
            com.sobot.chat.widget.kpswitch.util.a.a(this.aA, this.aj);
            b(8, "123");
        }
        if (view == this.ax) {
            aF();
            F();
            this.W = new AnonymousClass36();
            if (!b(i("sobot_microphone"), i("sobot_microphone_yongtu"), 2) && O()) {
                aE();
            } else {
                return;
            }
        }
        if (view == this.aL && !this.aX && ((sobotRobotListDialog = this.aO) == null || !sobotRobotListDialog.isShowing())) {
            this.aO = com.sobot.chat.utils.c.a(Z(), this.e, this);
        }
        if (view == this.C) {
            if (!TextUtils.isEmpty(com.sobot.chat.c.h)) {
                d.a(com.sobot.chat.c.h, getContext());
            } else {
                D();
            }
        }
        if (view != this.D) {
            return;
        }
        if (!TextUtils.isEmpty(com.sobot.chat.c.i)) {
            d.a(com.sobot.chat.c.i, getContext());
        } else {
            m.c("\u7535\u8bdd\u53f7\u7801\u4e0d\u80fd\u4e3a\u7a7a");
        }
    }

    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$36  reason: invalid class name */
    class AnonymousClass36 extends com.sobot.chat.listener.e {
        AnonymousClass36() {
        }

        @Override // com.sobot.chat.listener.e, com.sobot.chat.listener.d
        public void a() {
            SobotChatFragment.this.aE();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void aE() {
        try {
            this.bh = z.a().d() + "sobot_tmp.wav";
            String externalStorageState = Environment.getExternalStorageState();
            if (!externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
                m.d("SD Card is not mounted,It is  " + externalStorageState + ".");
            }
            File parentFile = new File(this.bh).getParentFile();
            if (!parentFile.exists() && !parentFile.mkdirs()) {
                m.d("Path to file could not be created");
            }
            this.bi = ExtAudioRecorder.a((Boolean) false);
            this.bi.a(this.bh);
            this.bi.b();
            this.bi.a(new AnonymousClass37());
            ag();
        } catch (Exception unused) {
            m.d("prepare() failed");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$37  reason: invalid class name */
    public class AnonymousClass37 implements ExtAudioRecorder.a {
        AnonymousClass37() {
        }

        @Override // com.sobot.chat.utils.ExtAudioRecorder.a
        public void a() {
            SobotChatFragment sobotChatFragment = SobotChatFragment.this;
            sobotChatFragment.a(sobotChatFragment.aA);
            SobotChatFragment.this.b(0, "");
            if (SobotChatFragment.this.aB.getVisibility() == 0) {
                SobotChatFragment.this.aB.setVisibility(0);
                SobotChatFragment.this.aB.setClickable(true);
                SobotChatFragment.this.aB.setOnTouchListener(new b());
                SobotChatFragment.this.aB.setEnabled(true);
                SobotChatFragment.this.ay.setText(SobotChatFragment.this.i("sobot_press_say"));
                SobotChatFragment.this.ay.setVisibility(0);
            }
        }

        @Override // com.sobot.chat.utils.ExtAudioRecorder.a
        public void b() {
            ae.a(SobotChatFragment.this.a, SobotChatFragment.this.i("sobot_no_record_audio_permission"));
        }
    }

    private void aF() {
        this.an.setVisibility(this.c == 301 ? 0 : 8);
    }

    private void aG() {
        this.an.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.conversation.SobotChatBaseFragment
    public void b(String str) {
        if (this.e != null) {
            String str2 = System.currentTimeMillis() + "";
            if (301 == this.c) {
                if (this.I != 4 || this.e.getInvalidSessionFlag() != 1 || this.d == CustomerState.Queuing || !TextUtils.isEmpty(this.S)) {
                    int i = this.I;
                    if (i == 2) {
                        if (this.e.getInvalidSessionFlag() == 1) {
                            this.S = str;
                        }
                        b();
                        return;
                    } else if ((i == 3 || i == 4) && this.f.getTransferKeyWord() != null) {
                        HashSet<String> transferKeyWord = this.f.getTransferKeyWord();
                        if (!TextUtils.isEmpty(str) && transferKeyWord.contains(str)) {
                            a(str2, str, this.T, 1, 0);
                            b();
                            return;
                        }
                    }
                } else {
                    this.S = str;
                    b();
                    return;
                }
            }
            a(str2, str, this.T, 2, 0);
            m.d("\u5f53\u524d\u53d1\u9001\u6d88\u606f\u6a21\u5f0f\uff1a" + this.c);
            b(this.T);
            a(str2, str, this.e, this.T, this.c, 0, "");
        }
    }

    /* access modifiers changed from: protected */
    public void a(ConsultingContent consultingContent) {
        if (this.e != null && consultingContent != null) {
            String sobotGoodsTitle = consultingContent.getSobotGoodsTitle();
            String sobotGoodsFromUrl = consultingContent.getSobotGoodsFromUrl();
            if (this.d == CustomerState.Online && this.c == 302 && !TextUtils.isEmpty(sobotGoodsFromUrl) && !TextUtils.isEmpty(sobotGoodsTitle)) {
                b(this.T);
                a(this.e.getPartnerid(), this.e.getCid(), this.T, System.currentTimeMillis() + "", consultingContent);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(OrderCardContentModel orderCardContentModel) {
        if (this.e != null && orderCardContentModel != null) {
            String orderCode = orderCardContentModel.getOrderCode();
            if (this.d == CustomerState.Online && this.c == 302 && !TextUtils.isEmpty(orderCode)) {
                b(this.T);
                a(this.e.getPartnerid(), this.e.getCid(), this.T, System.currentTimeMillis() + "", orderCardContentModel);
            }
        }
    }

    public void a(boolean z, int i, int i2, String str) {
        if (this.aY) {
            a(this.aA);
            l(i("sobot_completed_the_evaluation"));
        } else if (l()) {
            l(i("sobot_unable_to_evaluate"));
        } else if (!this.h) {
            l(i("sobot_after_consultation_to_evaluate_custome_service"));
        } else if (e()) {
            com.sobot.chat.widget.dialog.e eVar = this.aN;
            if (eVar == null || !eVar.isShowing()) {
                this.aN = com.sobot.chat.utils.c.a(Z(), this.aX, false, false, this.e, this.c, z ? 1 : 0, this.g, i, i2, str, false, false);
            }
        }
    }

    public void I() {
        int i = 0;
        if (this.c != 301 || this.I == 2) {
            this.am.setVisibility(8);
            ImageButton imageButton = this.ax;
            if (!this.f.isUseVoice()) {
                i = 8;
            }
            imageButton.setVisibility(i);
            return;
        }
        this.ax.setVisibility((!this.f.isUseVoice() || !this.f.isUseRobotVoice()) ? 8 : 0);
        View view = this.am;
        if (!this.f.isUseVoice() || !this.f.isUseRobotVoice()) {
            i = 8;
        }
        view.setVisibility(i);
    }

    private void b(ZhiChiMessageBase zhiChiMessageBase, int i, int i2, String str) {
        b(zhiChiMessageBase, i, i2, str, null);
    }

    private void b(ZhiChiMessageBase zhiChiMessageBase, int i, int i2, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            a(zhiChiMessageBase.getId(), str2, this.T, 2, i);
        } else {
            a(zhiChiMessageBase.getId(), zhiChiMessageBase.getContent(), this.T, 2, i);
        }
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiReplyAnswer.setMsgType("0");
        zhiChiReplyAnswer.setMsg(zhiChiMessageBase.getContent());
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        zhiChiMessageBase.setSenderType("0");
        a(zhiChiMessageBase.getId(), zhiChiMessageBase.getContent(), this.e, this.T, this.c, i2, str);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void aH() {
        for (int i = 0; i < this.aS.size(); i++) {
            ZhiChiMessageBase zhiChiMessageBase = this.aS.get(i);
            if (!(zhiChiMessageBase.getAnswer() == null || zhiChiMessageBase.getAnswer().getMultiDiaRespInfo() == null || zhiChiMessageBase.getAnswer().getMultiDiaRespInfo().getEndFlag())) {
                zhiChiMessageBase.setMultiDiaRespEnd(1);
            }
        }
        this.b.notifyDataSetChanged();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            super.onActivityResult(i, i2, intent);
            m.d("\u591a\u5a92\u4f53\u8fd4\u56de\u7684\u7ed3\u679c\uff1a" + i + "--" + i2 + "--" + intent);
            if (i2 == -1) {
                if (i == 701) {
                    if (intent == null || intent.getData() == null) {
                        ae.b(this.a, i("sobot_did_not_get_picture_path"));
                    } else {
                        Uri data = intent.getData();
                        if (data == null) {
                            data = l.a(intent, Z());
                        }
                        String a2 = l.a(Z(), data);
                        if (o.b(a2)) {
                            MediaPlayer mediaPlayer = new MediaPlayer();
                            try {
                                mediaPlayer.setDataSource(Z(), data);
                                mediaPlayer.prepare();
                                if (mediaPlayer.getDuration() / 1000 > 15) {
                                    ae.a(Z(), i("sobot_upload_vodie_length"));
                                    return;
                                }
                                File file = new File(a2);
                                if (file.exists()) {
                                    a(file, data, this.b);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            com.sobot.chat.utils.c.a(this.a, this.T, data, this.e, this.ai, this.b, false);
                        }
                    }
                }
                a(this.aA);
            }
            if (intent == null) {
                return;
            }
            if (i != 100) {
                if (i != 104) {
                    switch (i) {
                        case 107:
                            Uri data2 = intent.getData();
                            if (data2 == null) {
                                a((File) intent.getSerializableExtra("sobot_intent_data_selected_file"), this.T, (ListView) this.ai, this.b, false);
                                return;
                            }
                            String valueOf = String.valueOf(System.currentTimeMillis());
                            if (data2 == null) {
                                data2 = l.a(intent, Z());
                            }
                            String a3 = l.a(Z(), data2);
                            if (TextUtils.isEmpty(a3)) {
                                ae.a(Z(), q.f(Z(), "sobot_pic_type_error"));
                                return;
                            }
                            File file2 = new File(a3);
                            m.d("tmpMsgId:" + valueOf);
                            String a4 = n.a(file2.getAbsolutePath());
                            try {
                                Activity Z = Z();
                                String a5 = com.sobot.chat.camera.c.f.a(Z, data2, a4 + com.sobot.chat.camera.c.f.b(file2.getAbsolutePath()), file2.getAbsolutePath());
                                if (TextUtils.isEmpty(a5)) {
                                    ae.a(Z(), q.f(Z(), "sobot_pic_type_error"));
                                    return;
                                } else {
                                    a(new File(a5), this.T, (ListView) this.ai, this.b, true);
                                    return;
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                ae.a(Z(), q.f(Z(), "sobot_pic_type_error"));
                                return;
                            }
                        case 108:
                            if (SobotCameraActivity.c(intent) == 1) {
                                File file3 = new File(SobotCameraActivity.b(intent));
                                if (file3.exists()) {
                                    SobotCameraActivity.a(intent);
                                    a(file3, (Uri) null, this.b);
                                    return;
                                }
                                ae.b(this.a, i("sobot_pic_select_again"));
                                return;
                            }
                            File file4 = new File(SobotCameraActivity.a(intent));
                            if (file4.exists()) {
                                com.sobot.chat.utils.c.a(file4.getAbsolutePath(), this.e.getCid(), this.e.getPartnerid(), this.T, this.a, (ListView) this.ai, this.b, true);
                                return;
                            } else {
                                ae.b(this.a, i("sobot_pic_select_again"));
                                return;
                            }
                        case 109:
                            ZhiChiMessageBase a6 = com.sobot.chat.utils.c.a(SobotPostLeaveMsgActivity.a(intent));
                            ZhiChiMessageBase c = com.sobot.chat.utils.c.c(q.f(getContext(), "sobot_leavemsg_success_tip"));
                            this.b.b(a6);
                            this.b.b(c);
                            this.b.notifyDataSetChanged();
                            a(this.e, 99);
                            return;
                        default:
                            return;
                    }
                } else if (i2 == 104) {
                    a(intent.getStringExtra("sobot_intent_bundle_data_groupid"), intent.getStringExtra("sobot_intent_bundle_data_groupname"), intent.getIntExtra("sobot_intent_bundle_data_transfer_type", 0));
                } else {
                    this.k = false;
                    if (this.I == 2) {
                        this.aX = true;
                        m();
                        d();
                    }
                }
            } else if (intent.getBooleanExtra("toLeaveMsg", false)) {
                a(false);
            } else {
                int intExtra = intent.getIntExtra("groupIndex", -1);
                int intExtra2 = intent.getIntExtra("transferType", 0);
                m.d("groupIndex-->" + intExtra);
                if (intExtra >= 0) {
                    a(this.aU.get(intExtra).getGroupId(), this.aU.get(intExtra).getGroupName(), intExtra2, this.f.isCloseInquiryForm());
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public class b implements View.OnTouchListener {
        b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int i;
            SobotChatFragment.this.N = false;
            int action = motionEvent.getAction() & 255;
            if (action == 0) {
                SobotChatFragment.this.bf = System.currentTimeMillis() + "";
                SobotChatFragment.this.ao.setClickable(false);
                SobotChatFragment.this.aw.setClickable(false);
                SobotChatFragment.this.ao.setEnabled(false);
                SobotChatFragment.this.aw.setEnabled(false);
                if (Build.VERSION.SDK_INT >= 11) {
                    SobotChatFragment.this.ao.setAlpha(0.4f);
                    SobotChatFragment.this.aw.setAlpha(0.4f);
                }
                SobotChatFragment.this.q();
                view.setPressed(true);
                SobotChatFragment.this.aq.setText("00''");
                SobotChatFragment sobotChatFragment = SobotChatFragment.this;
                sobotChatFragment.M = "00:00";
                sobotChatFragment.L = 0;
                sobotChatFragment.bg = 0;
                SobotChatFragment.this.aD.setVisibility(0);
                SobotChatFragment.this.ar.setVisibility(0);
                SobotChatFragment.this.at.setVisibility(0);
                SobotChatFragment.this.au.setVisibility(0);
                SobotChatFragment.this.aq.setVisibility(0);
                SobotChatFragment.this.av.setVisibility(8);
                SobotChatFragment.this.as.setVisibility(8);
                SobotChatFragment.this.ay.setText(SobotChatFragment.this.i("sobot_up_send"));
                SobotChatFragment.this.af();
                return true;
            } else if (action == 1) {
                SobotChatFragment.this.ao.setClickable(true);
                SobotChatFragment.this.aw.setClickable(true);
                SobotChatFragment.this.ao.setEnabled(true);
                SobotChatFragment.this.aw.setEnabled(true);
                if (Build.VERSION.SDK_INT >= 11) {
                    SobotChatFragment.this.ao.setAlpha(1.0f);
                    SobotChatFragment.this.aw.setAlpha(1.0f);
                }
                view.setPressed(false);
                SobotChatFragment.this.ay.setText(SobotChatFragment.this.i("sobot_press_say"));
                SobotChatFragment.this.q();
                SobotChatFragment.this.ag();
                if (SobotChatFragment.this.aD.getVisibility() != 0 || SobotChatFragment.this.N) {
                    SobotChatFragment sobotChatFragment2 = SobotChatFragment.this;
                    sobotChatFragment2.a(2, sobotChatFragment2.bf);
                } else {
                    SobotChatFragment sobotChatFragment3 = SobotChatFragment.this;
                    sobotChatFragment3.a(sobotChatFragment3.aA);
                    if (SobotChatFragment.this.az != null) {
                        SobotChatFragment.this.az.stop();
                    }
                    SobotChatFragment.this.aq.setText("00''");
                    SobotChatFragment.this.aq.setVisibility(4);
                    if (motionEvent.getY() < 0.0f) {
                        SobotChatFragment.this.aD.setVisibility(8);
                        SobotChatFragment sobotChatFragment4 = SobotChatFragment.this;
                        sobotChatFragment4.a(2, sobotChatFragment4.bf);
                        return true;
                    }
                    if (SobotChatFragment.this.bg < 1000) {
                        SobotChatFragment.this.ar.setVisibility(0);
                        SobotChatFragment.this.aE.setText(SobotChatFragment.this.i("sobot_voice_time_short"));
                        SobotChatFragment.this.aE.setBackgroundResource(SobotChatFragment.this.f("sobot_recording_text_hint_bg"));
                        SobotChatFragment.this.av.setVisibility(0);
                        SobotChatFragment.this.aq.setVisibility(0);
                        SobotChatFragment.this.aq.setText("00:00");
                        SobotChatFragment.this.at.setVisibility(8);
                        SobotChatFragment.this.au.setVisibility(8);
                        SobotChatFragment sobotChatFragment5 = SobotChatFragment.this;
                        sobotChatFragment5.a(2, sobotChatFragment5.bf);
                    } else if (SobotChatFragment.this.bg < SobotChatFragment.this.bd * 1000) {
                        SobotChatFragment.this.aD.setVisibility(8);
                        SobotChatFragment sobotChatFragment6 = SobotChatFragment.this;
                        sobotChatFragment6.a(1, sobotChatFragment6.bf);
                        return true;
                    } else if (SobotChatFragment.this.bg > SobotChatFragment.this.bd * 1000) {
                        SobotChatFragment.this.ar.setVisibility(0);
                        SobotChatFragment.this.aE.setText(SobotChatFragment.this.i("sobot_voiceTooLong"));
                        SobotChatFragment.this.aE.setBackgroundResource(SobotChatFragment.this.f("sobot_recording_text_hint_bg"));
                        SobotChatFragment.this.av.setVisibility(0);
                        SobotChatFragment.this.at.setVisibility(8);
                        SobotChatFragment.this.au.setVisibility(8);
                        i = 1;
                        SobotChatFragment.this.bg = 0;
                        SobotChatFragment.this.a(i);
                    } else {
                        SobotChatFragment sobotChatFragment7 = SobotChatFragment.this;
                        sobotChatFragment7.a(2, sobotChatFragment7.bf);
                    }
                    i = 0;
                    SobotChatFragment.this.bg = 0;
                    SobotChatFragment.this.a(i);
                }
                SobotChatFragment sobotChatFragment8 = SobotChatFragment.this;
                sobotChatFragment8.L = 0;
                sobotChatFragment8.c(sobotChatFragment8.T);
                return true;
            } else if (action != 2) {
                if (!(action == 5 || action == 6)) {
                    SobotChatFragment sobotChatFragment9 = SobotChatFragment.this;
                    sobotChatFragment9.a(2, sobotChatFragment9.bf);
                    SobotChatFragment.this.a(2);
                }
                return true;
            } else {
                if (!SobotChatFragment.this.n) {
                    SobotChatFragment.this.o = 0;
                }
                if (motionEvent.getY() < 10.0f) {
                    SobotChatFragment.this.ar.setVisibility(8);
                    SobotChatFragment.this.as.setVisibility(0);
                    SobotChatFragment.this.at.setVisibility(8);
                    SobotChatFragment.this.au.setVisibility(8);
                    SobotChatFragment.this.av.setVisibility(8);
                    SobotChatFragment.this.ay.setText(SobotChatFragment.this.i("sobot_release_to_cancel"));
                    SobotChatFragment.this.aE.setText(SobotChatFragment.this.i("sobot_release_to_cancel"));
                    SobotChatFragment.this.aE.setBackgroundResource(SobotChatFragment.this.f("sobot_recording_text_hint_bg"));
                } else if (SobotChatFragment.this.L != 0) {
                    SobotChatFragment.this.ay.setText(SobotChatFragment.this.i("sobot_up_send"));
                    SobotChatFragment.this.ar.setVisibility(0);
                    SobotChatFragment.this.au.setVisibility(0);
                    SobotChatFragment.this.as.setVisibility(8);
                    SobotChatFragment.this.at.setVisibility(0);
                    SobotChatFragment.this.av.setVisibility(8);
                    SobotChatFragment.this.aE.setText(SobotChatFragment.this.i("sobot_move_up_to_cancel"));
                    SobotChatFragment.this.aE.setBackgroundResource(SobotChatFragment.this.f("sobot_recording_text_hint_bg1"));
                }
                return true;
            }
        }
    }

    public String J() {
        return this.y.getText().toString();
    }

    public void K() {
        if (!e()) {
            return;
        }
        if (this.aA.getVisibility() == 0) {
            a(this.aA);
        } else if (!this.f.isShowSatisfaction() || !this.h || this.aY) {
            d();
        } else {
            this.aN = com.sobot.chat.utils.c.a(Z(), this.aX, true, false, this.e, this.c, 1, this.g, 5, 0, "", true, true);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.conversation.SobotChatBaseFragment
    public String n() {
        return this.aj.getText().toString().trim();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void aI() {
        if (this.e.isLableLinkFlag()) {
            this.U.getLableInfoList(this, this.e.getPartnerid(), new AnonymousClass38((int) j("sobot_layout_lable_margin_right")));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.conversation.SobotChatFragment$38  reason: invalid class name */
    public class AnonymousClass38 implements com.sobot.chat.core.http.c.a<List<SobotLableInfoList>> {
        final /* synthetic */ int a;

        AnonymousClass38(int i) {
            this.a = i;
        }

        public void a(List<SobotLableInfoList> list) {
            if (SobotChatFragment.this.e()) {
                SobotChatFragment.this.aQ.removeAllViews();
                if (list == null || list.size() <= 0) {
                    SobotChatFragment.this.aP.setVisibility(8);
                    return;
                }
                for (int i = 0; i < list.size(); i++) {
                    TextView textView = (TextView) View.inflate(SobotChatFragment.this.getContext(), SobotChatFragment.this.g("sobot_layout_lable"), null);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.setMargins(0, 0, this.a, 0);
                    textView.setLayoutParams(layoutParams);
                    textView.setText(list.get(i).getLableName());
                    textView.setTag(list.get(i).getLableLink());
                    SobotChatFragment.this.aQ.addView(textView);
                    if (!TextUtils.isEmpty(textView.getTag() + "")) {
                        textView.setOnClickListener(SobotChatFragment.this.w);
                    }
                }
                SobotChatFragment.this.aP.setVisibility(0);
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            if (SobotChatFragment.this.e()) {
                SobotChatFragment.this.aP.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void aJ() {
        int i = 8;
        if (this.e == null || this.I == 2 || this.c != 301) {
            this.aL.setVisibility(8);
            return;
        }
        LinearLayout linearLayout = this.aL;
        if (this.e.isRobotSwitchFlag()) {
            i = 0;
        }
        linearLayout.setVisibility(i);
    }

    @Override // com.sobot.chat.widget.dialog.SobotRobotListDialog.a
    public void a(SobotRobot sobotRobot) {
        if (!(this.e == null || sobotRobot == null)) {
            this.e.setGuideFlag(sobotRobot.getGuideFlag());
            this.e.setRobotid(sobotRobot.getRobotFlag());
            this.e.setRobotLogo(sobotRobot.getRobotLogo());
            this.e.setRobotName(sobotRobot.getRobotName());
            this.e.setRobotHelloWord(sobotRobot.getRobotHelloWord());
            b(this.e.getRobotName(), this.e.getRobotLogo(), false);
            List<ZhiChiMessageBase> d = this.b.d();
            int i = 0;
            for (int size = d.size() - 1; size >= 0; size--) {
                if ("30".equals(d.get(size).getSenderType()) || "29".equals(d.get(size).getSenderType()) || "27".equals(d.get(size).getSenderType())) {
                    d.remove(size);
                    i++;
                    if (i >= 3) {
                        break;
                    }
                }
            }
            this.b.notifyDataSetChanged();
            this.i = 0;
            a(this.T, this.e, this.f);
        }
    }

    private void aK() {
        if (-1 != com.sobot.chat.c.a) {
            this.al.setBackgroundResource(com.sobot.chat.c.a);
        }
        if (-1 != com.sobot.chat.c.s) {
            this.aH.setBackgroundColor(getContext().getResources().getColor(com.sobot.chat.c.s));
        }
        if (-1 != com.sobot.chat.c.k) {
            this.H.setBackgroundColor(com.sobot.chat.c.k);
        }
        if (-1 != com.sobot.chat.c.l) {
            this.H.setBackgroundColor(getContext().getResources().getColor(com.sobot.chat.c.l));
        }
        if (com.sobot.chat.c.d) {
            this.C.setVisibility(0);
            if (-1 != com.sobot.chat.c.f) {
                Drawable drawable = getResources().getDrawable(com.sobot.chat.c.f);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                this.C.setCompoundDrawables(null, null, drawable, null);
            }
        }
        if (com.sobot.chat.c.e) {
            this.D.setVisibility(0);
            if (-1 != com.sobot.chat.c.g) {
                Drawable drawable2 = getResources().getDrawable(com.sobot.chat.c.g);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                this.D.setCompoundDrawables(null, null, drawable2, null);
            }
        }
    }
}
