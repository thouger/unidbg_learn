package com.umeng.message;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import anet.channel.AwcnConfig;
import anet.channel.strategy.dispatch.DispatchConstants;
import anet.channel.util.ALog;
import com.taobao.accs.ACCSClient;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.AccsException;
import com.taobao.accs.client.GlobalConfig;
import com.taobao.agoo.ICallback;
import com.taobao.agoo.IRegister;
import com.taobao.agoo.TaobaoRegister;
import com.umeng.analytics.pro.ai;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.UTrack;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.common.a;
import com.umeng.message.common.e;
import com.umeng.message.proguard.h;
import com.umeng.message.proguard.k;
import com.umeng.message.service.UMJobIntentService;
import com.umeng.message.tag.TagManager;
import com.umeng.message.util.b;
import com.umeng.message.util.d;
import java.lang.reflect.Method;
import java.util.Random;
import org.android.spdy.SpdyAgent;
import org.json.JSONObject;

public class PushAgent {
    public static boolean DEBUG;
    private static final String TAG = PushAgent.class.getName();
    private static volatile PushAgent instance;
    private static boolean sAppLaunchBy;
    private Handler handler;
    public boolean isZyb = true;
    private boolean mAccsHeartbeatEnable = false;
    private UHandler mAd;
    private Context mContext;
    private UHandler mMessageHandler;
    private UHandler mNotificationClickHandler;
    private boolean mPullUpEnable = false;
    private boolean mPushCheck = false;
    private IUmengRegisterCallback mRegisterCallback;
    private TagManager mTagMgr;
    private IUmengCallback mUnregisterCallback;
    private boolean powerMode = true;
    private UpushLogDataProtocol upushLogDataProtocol;

    static {
        ALog.setPrintLog(false);
    }

    public UpushLogDataProtocol getUpushLogDataProtocol() {
        return this.upushLogDataProtocol;
    }

    private PushAgent(Context context) {
        try {
            a.a(context);
            this.mContext = context.getApplicationContext();
            this.mTagMgr = TagManager.getInstance(this.mContext);
            this.mMessageHandler = new UmengMessageHandler();
            this.mAd = new UmengAdHandler();
            this.mNotificationClickHandler = new UmengNotificationClickHandler();
            this.upushLogDataProtocol = new UpushLogDataProtocol(context);
            b.a(context);
            checkAvailable();
        } catch (Exception e) {
            UMLog.mutlInfo(TAG, 0, "PushAgent\u521d\u59cb\u5316\u5931\u8d25", e.getMessage());
        }
        this.handler = new AnonymousClass1(context.getMainLooper());
    }

    /* renamed from: com.umeng.message.PushAgent$1  reason: invalid class name */
    class AnonymousClass1 extends Handler {
        AnonymousClass1(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }

    private PushAgent() {
    }

    public static PushAgent getInstance(Context context) {
        if (instance == null) {
            synchronized (PushAgent.class) {
                if (instance == null) {
                    instance = new PushAgent(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private void register() {
        try {
            if (Build.VERSION.SDK_INT < 14) {
                UMLog.mutlInfo(TAG, 0, "U-Push\u6700\u4f4e\u652f\u6301\u7684\u7cfb\u7edf\u7248\u672c\u4e3aAndroid 4.0");
            } else if (!h.a(this.mContext, this.handler)) {
                UMLog.mutlInfo(TAG, 0, "AndroidManifest\u6743\u9650\u6216\u53c2\u6570\u9519\u8bef");
            } else {
                UMLog.mutlInfo(TAG, 2, "AndroidManifest\u914d\u7f6e\u6b63\u786e\u3001\u53c2\u6570\u6b63\u786e");
                if (!TextUtils.isEmpty(getMessageAppkey())) {
                    if (!TextUtils.isEmpty(getMessageSecret())) {
                        h.a(this.mContext, UmengMessageCallbackHandlerService.class);
                        if (DEBUG) {
                            h.b(this.mContext, this.handler);
                        }
                        com.taobao.accs.utl.ALog.setUseTlog(false);
                        ALog.setUseTlog(false);
                        AwcnConfig.setAccsSessionCreateForbiddenInBg(false);
                        ACCSClient.setEnvironment(this.mContext, 0);
                        AccsClientConfig.Builder builder = new AccsClientConfig.Builder();
                        ACCSClient.init(this.mContext, builder.setAppKey("umeng:" + getMessageAppkey()).setAppSecret(getMessageSecret()).setInappHost("umengacs.m.taobao.com").setInappPubKey(11).setChannelHost("umengjmacs.m.taobao.com").setChannelPubKey(11).setKeepAlive(getPowerMode()).setAutoUnit(false).setTag("default").setPullUpEnable(this.mPullUpEnable).setAccsHeartbeatEnable(this.mAccsHeartbeatEnable).build());
                        DispatchConstants.setAmdcServerDomain(new String[]{"amdcopen.m.taobao.com", "amdc.wapa.taobao.com", "amdc.taobao.net"});
                        DispatchConstants.setAmdcServerFixIp(new String[][]{new String[]{"106.11.61.135", "106.11.61.137"}, null, null});
                        if (UmengMessageDeviceConfig.isMi8()) {
                            TaobaoRegister.setAgooMsgReceiveService("com.umeng.message.XiaomiIntentService");
                        } else {
                            TaobaoRegister.setAgooMsgReceiveService("com.umeng.message.UmengIntentService");
                        }
                        e.a(new AnonymousClass2());
                        if (Build.VERSION.SDK_INT >= 26 && !UmengMessageHandler.isChannelSet) {
                            UmengMessageHandler.isChannelSet = true;
                            NotificationChannel notificationChannel = new NotificationChannel(UmengMessageHandler.PRIMARY_CHANNEL, getNotificationChannel(this.mContext), 3);
                            NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
                            if (notificationManager != null) {
                                notificationManager.createNotificationChannel(notificationChannel);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                }
                UMLog.mutlInfo(TAG, 0, "Appkey\u548cSecret key\u5747\u4e0d\u80fd\u4e3a\u7a7a");
            }
        } catch (Exception unused) {
            UMLog.mutlInfo(TAG, 0, "\u6ce8\u518c\u5931\u8d25");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.PushAgent$2  reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = "umeng:" + PushAgent.this.getMessageAppkey();
            String messageSecret = PushAgent.this.getMessageSecret();
            UMLog.mutlInfo(PushAgent.TAG, 2, "appkey:" + str + ",secret:" + messageSecret);
            try {
                TaobaoRegister.register(PushAgent.this.mContext, "default", str, messageSecret, "android@umeng", new AnonymousClass1());
            } catch (AccsException unused) {
                UMLog.mutlInfo(PushAgent.TAG, 0, "\u6ce8\u518c\u5931\u8d25");
            }
        }

        /* renamed from: com.umeng.message.PushAgent$2$1  reason: invalid class name */
        class AnonymousClass1 extends IRegister {
            AnonymousClass1() {
            }

            @Override // com.taobao.agoo.IRegister
            public void onSuccess(String str) {
                String str2 = PushAgent.TAG;
                UMLog.mutlInfo(str2, 2, "\u6ce8\u518c\u6210\u529f:" + str);
                PushAgent.this.registerSucceed(str);
            }

            @Override // com.taobao.agoo.IRegister, com.taobao.agoo.ICallback
            public void onFailure(String str, String str2) {
                String str3 = PushAgent.TAG;
                UMLog.mutlInfo(str3, 0, "\u6ce8\u518c\u5931\u8d25-->s:" + str + ",s1:" + str2);
                PushAgent.this.registerFailure(str, str2);
                UMLog.aq(k.a, 0, "\\|");
            }
        }
    }

    private String getNotificationChannel(Context context) {
        String notificationChannelName = getInstance(context).getNotificationChannelName();
        return TextUtils.isEmpty(notificationChannelName) ? UmengMessageHandler.DEFAULT_NOTIFICATION_CHANNEL_NAME : notificationChannelName;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void registerSucceed(String str) {
        Intent intent = new Intent();
        intent.setPackage(this.mContext.getPackageName());
        intent.setAction(MsgConstant.MESSAGE_REGISTER_CALLBACK_ACTION);
        intent.putExtra(MsgConstant.KEY_REGISTRATION_ID, str);
        intent.putExtra("status", true);
        UMJobIntentService.enqueueWork(this.mContext, UmengMessageCallbackHandlerService.class, intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void registerFailure(String str, String str2) {
        Intent intent = new Intent();
        intent.setPackage(this.mContext.getPackageName());
        intent.setAction(MsgConstant.MESSAGE_REGISTER_CALLBACK_ACTION);
        intent.putExtra("status", false);
        intent.putExtra(ai.az, str);
        intent.putExtra("s1", str2);
        UMJobIntentService.enqueueWork(this.mContext, UmengMessageCallbackHandlerService.class, intent);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.PushAgent$3  reason: invalid class name */
    public class AnonymousClass3 extends ICallback {
        AnonymousClass3() {
        }

        @Override // com.taobao.agoo.ICallback
        public void onSuccess() {
            UMLog.mutlInfo(PushAgent.TAG, 2, "\u5f00\u542f\u63a8\u9001\u6210\u529f");
            Intent intent = new Intent();
            intent.setPackage(PushAgent.this.mContext.getPackageName());
            intent.setAction(MsgConstant.MESSAGE_ENABLE_CALLBACK_ACTION);
            intent.putExtra("status", true);
            UMJobIntentService.enqueueWork(PushAgent.this.mContext, UmengMessageCallbackHandlerService.class, intent);
        }

        @Override // com.taobao.agoo.ICallback
        public void onFailure(String str, String str2) {
            String str3 = PushAgent.TAG;
            UMLog.mutlInfo(str3, 2, "\u5f00\u542f\u63a8\u9001\u5931\u8d25-->s:" + str + ",s1:" + str2);
            Intent intent = new Intent();
            intent.setPackage(PushAgent.this.mContext.getPackageName());
            intent.setAction(MsgConstant.MESSAGE_ENABLE_CALLBACK_ACTION);
            intent.putExtra("status", false);
            intent.putExtra(ai.az, str);
            intent.putExtra("s1", str2);
            UMJobIntentService.enqueueWork(PushAgent.this.mContext, UmengMessageCallbackHandlerService.class, intent);
        }
    }

    private void enable() {
        try {
            TaobaoRegister.bindAgoo(this.mContext, new AnonymousClass3());
        } catch (Exception unused) {
            UMLog.mutlInfo(TAG, 0, "\u5f00\u542f\u63a8\u9001\u5931\u8d25");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.PushAgent$4  reason: invalid class name */
    public class AnonymousClass4 extends ICallback {
        AnonymousClass4() {
        }

        @Override // com.taobao.agoo.ICallback
        public void onSuccess() {
            UMLog.mutlInfo(PushAgent.TAG, 2, "\u5173\u95ed\u63a8\u9001\u6210\u529f");
            Intent intent = new Intent();
            intent.setPackage(PushAgent.this.mContext.getPackageName());
            intent.setAction(MsgConstant.MESSAGE_DISABLE_CALLBACK_ACTION);
            intent.putExtra("status", true);
            UMJobIntentService.enqueueWork(PushAgent.this.mContext, UmengMessageCallbackHandlerService.class, intent);
        }

        @Override // com.taobao.agoo.ICallback
        public void onFailure(String str, String str2) {
            String str3 = PushAgent.TAG;
            UMLog.mutlInfo(str3, 0, "\u5173\u95ed\u63a8\u9001\u5931\u8d25-->s:" + str + ",s1:" + str2);
            Intent intent = new Intent();
            intent.setPackage(PushAgent.this.mContext.getPackageName());
            intent.setAction(MsgConstant.MESSAGE_DISABLE_CALLBACK_ACTION);
            intent.putExtra("status", false);
            intent.putExtra(ai.az, str);
            intent.putExtra("s1", str2);
            UMJobIntentService.enqueueWork(PushAgent.this.mContext, UmengMessageCallbackHandlerService.class, intent);
        }
    }

    private void disable() {
        try {
            TaobaoRegister.unbindAgoo(this.mContext, new AnonymousClass4());
        } catch (Exception unused) {
            UMLog.mutlInfo(TAG, 0, "\u5173\u95ed\u63a8\u9001\u5931\u8d25");
        }
    }

    private void checkAvailable() {
        Class<?> a = d.a("com.umeng.commonsdk.framework.UMEnvelopeBuild");
        if (a == null) {
            Log.e(TAG, "--->>> common sdk\u7248\u672c\u4e0d\u5339\u914d\uff0c\u8bf7\u786e\u8ba4\uff01<<<--- ");
            new AnonymousClass5().start();
            return;
        }
        Method method = null;
        try {
            method = a.getDeclaredMethod("buildEnvelopeWithExtHeader", Context.class, JSONObject.class, JSONObject.class, String.class, String.class, String.class);
        } catch (NoSuchMethodException unused) {
        }
        if (method == null) {
            Log.e(TAG, "--->>> common sdk\u7248\u672c\u4e0d\u5339\u914d\uff0c\u8bf7\u786e\u8ba4\uff01<<<--- ");
            new AnonymousClass6().start();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.PushAgent$5  reason: invalid class name */
    public class AnonymousClass5 extends Thread {
        AnonymousClass5() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Looper.prepare();
                Toast.makeText(PushAgent.this.mContext, "common sdk\u7248\u672c\u4e0d\u5339\u914d\uff0c\u8bf7\u786e\u8ba4\uff01", 1).show();
                Looper.loop();
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.PushAgent$6  reason: invalid class name */
    public class AnonymousClass6 extends Thread {
        AnonymousClass6() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Looper.prepare();
                Toast.makeText(PushAgent.this.mContext, "common sdk\u7248\u672c\u4e0d\u5339\u914d\uff0c\u8bf7\u786e\u8ba4\uff01", 1).show();
                Looper.loop();
            } catch (Throwable unused) {
            }
        }
    }

    public void register(IUmengRegisterCallback iUmengRegisterCallback) {
        setRegisterCallback(iUmengRegisterCallback);
        register();
    }

    public void enable(IUmengCallback iUmengCallback) {
        setCallback(iUmengCallback);
        enable();
    }

    public void disable(IUmengCallback iUmengCallback) {
        setCallback(iUmengCallback);
        disable();
    }

    public void setMessageHandler(UHandler uHandler) {
        this.mMessageHandler = uHandler;
    }

    public UHandler getMessageHandler() {
        return this.mMessageHandler;
    }

    public UHandler getAdHandler() {
        return this.mAd;
    }

    public void setNotificationClickHandler(UHandler uHandler) {
        this.mNotificationClickHandler = uHandler;
    }

    public UHandler getNotificationClickHandler() {
        return this.mNotificationClickHandler;
    }

    public TagManager getTagManager() {
        return this.mTagMgr;
    }

    public void addAlias(String str, String str2, UTrack.ICallBack iCallBack) {
        UTrack.getInstance(this.mContext).addAlias(str, str2, iCallBack);
    }

    public void setAlias(String str, String str2, UTrack.ICallBack iCallBack) {
        UTrack.getInstance(this.mContext).setAlias(str, str2, iCallBack);
    }

    public void deleteAlias(String str, String str2, UTrack.ICallBack iCallBack) {
        UTrack.getInstance(this.mContext).deleteAlias(str, str2, iCallBack);
    }

    public String getMessageSecret() {
        String messageAppSecret = MessageSharedPrefs.getInstance(this.mContext).getMessageAppSecret();
        return TextUtils.isEmpty(messageAppSecret) ? UmengMessageDeviceConfig.getMetaData(this.mContext, "UMENG_MESSAGE_SECRET") : messageAppSecret;
    }

    public String getMessageAppkey() {
        return MessageSharedPrefs.getInstance(this.mContext).getMessageAppKey();
    }

    public String getMessageChannel() {
        String messageChannel = MessageSharedPrefs.getInstance(this.mContext).getMessageChannel();
        return TextUtils.isEmpty(messageChannel) ? UmengMessageDeviceConfig.getChannel(this.mContext) : messageChannel;
    }

    public void onAppStart() {
        UTrack.getInstance(this.mContext).trackAppLaunch(JobInfo.MIN_BACKOFF_MILLIS);
        UTrack.getInstance(this.mContext).sendCachedMsgLog(isAppLaunchByMessage() ? Math.abs(new Random().nextLong() % MsgConstant.b) : 0);
    }

    public <U extends UmengMessageService> void setPushIntentServiceClass(Class<U> cls) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setPushIntentServiceClass(cls);
        }
    }

    public String getPushIntentServiceClass() {
        return MessageSharedPrefs.getInstance(this.mContext).getPushIntentServiceClass();
    }

    public void setPullUpEnable(boolean z) {
        this.mPullUpEnable = z;
    }

    public void setAccsHeartbeatEnable(boolean z) {
        this.mAccsHeartbeatEnable = z;
    }

    private void setDebugMode(boolean z) {
        DEBUG = z;
        com.taobao.accs.utl.ALog.setPrintLog(z);
        ALog.setPrintLog(z);
        SpdyAgent.enableDebug = z;
    }

    public void setNoDisturbMode(int i, int i2, int i3, int i4) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).a(i, i2, i3, i4);
        }
    }

    public int getNoDisturbStartHour() {
        return MessageSharedPrefs.getInstance(this.mContext).a();
    }

    public int getNoDisturbStartMinute() {
        return MessageSharedPrefs.getInstance(this.mContext).b();
    }

    public int getNoDisturbEndHour() {
        return MessageSharedPrefs.getInstance(this.mContext).c();
    }

    public int getNoDisturbEndMinute() {
        return MessageSharedPrefs.getInstance(this.mContext).d();
    }

    public static void setAppLaunchByMessage() {
        sAppLaunchBy = true;
    }

    public static boolean isAppLaunchByMessage() {
        return sAppLaunchBy;
    }

    public String getRegistrationId() {
        return MessageSharedPrefs.getInstance(this.mContext).getDeviceToken();
    }

    public void setDisplayNotificationNumber(int i) {
        if (h.d(this.mContext) && i >= 0 && i <= 10) {
            MessageSharedPrefs.getInstance(this.mContext).setDisplayNotificationNumber(i);
        }
    }

    public int getDisplayNotificationNumber() {
        return MessageSharedPrefs.getInstance(this.mContext).getDisplayNotificationNumber();
    }

    @Deprecated
    private void setAppkeyAndSecret(String str, String str2) {
        if (h.d(this.mContext)) {
            String messageAppKey = MessageSharedPrefs.getInstance(this.mContext).getMessageAppKey();
            String messageAppSecret = MessageSharedPrefs.getInstance(this.mContext).getMessageAppSecret();
            if (!messageAppKey.equals(str) && !messageAppSecret.equals(str2)) {
                MessageSharedPrefs.getInstance(this.mContext).removeMessageAppKey();
                MessageSharedPrefs.getInstance(this.mContext).removeMessageAppSecret();
            }
            MessageSharedPrefs.getInstance(this.mContext).setMessageAppKey(str);
            MessageSharedPrefs.getInstance(this.mContext).setMessageAppSecret(str2);
            UTrack.getInstance(this.mContext).updateHeader();
        }
    }

    @Deprecated
    private void setAppkey(String str) {
        if (!h.d(this.mContext)) {
            return;
        }
        if (str == null || str.equals("")) {
            UMLog.mutlInfo(TAG, 0, "appkey\u4e0d\u80fd\u4e3anull");
        } else {
            MessageSharedPrefs.getInstance(this.mContext).setMessageAppKey(str);
        }
    }

    @Deprecated
    private void setSecret(String str) {
        if (!h.d(this.mContext)) {
            return;
        }
        if (str == null || str.equals("")) {
            UMLog.mutlInfo(TAG, 0, "appSecret\u4e0d\u80fd\u4e3anull");
        } else {
            MessageSharedPrefs.getInstance(this.mContext).setMessageAppSecret(str);
        }
    }

    @Deprecated
    private void setMessageChannel(String str) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setMessageChannel(str);
            e.a(new AnonymousClass7());
        }
    }

    /* renamed from: com.umeng.message.PushAgent$7  reason: invalid class name */
    class AnonymousClass7 implements Runnable {
        AnonymousClass7() {
        }

        @Override // java.lang.Runnable
        public void run() {
            UTrack.getInstance(PushAgent.this.mContext).updateHeader();
        }
    }

    public void setRegisterCallback(IUmengRegisterCallback iUmengRegisterCallback) {
        this.mRegisterCallback = iUmengRegisterCallback;
    }

    public IUmengRegisterCallback getRegisterCallback() {
        return this.mRegisterCallback;
    }

    public void setCallback(IUmengCallback iUmengCallback) {
        this.mUnregisterCallback = iUmengCallback;
    }

    public IUmengCallback getCallback() {
        return this.mUnregisterCallback;
    }

    public void setMuteDurationSeconds(int i) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setMuteDuration(i);
        }
    }

    public int getMuteDurationSeconds() {
        return MessageSharedPrefs.getInstance(this.mContext).getMuteDuration();
    }

    public boolean isIncludesUmengUpdateSDK() {
        Class<?> cls;
        try {
            cls = Class.forName("com.umeng.update.UmengUpdateAgent");
        } catch (Exception unused) {
            cls = null;
        }
        return cls != null;
    }

    public boolean getNotificationOnForeground() {
        return MessageSharedPrefs.getInstance(this.mContext).getNotificaitonOnForeground();
    }

    public void setNotificationOnForeground(boolean z) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setNotificaitonOnForeground(z);
        }
    }

    public String getResourcePackageName() {
        return MessageSharedPrefs.getInstance(this.mContext).getResourcePackageName();
    }

    public void setResourcePackageName(String str) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setResourcePackageName(str);
        }
    }

    public boolean isPushCheck() {
        return this.mPushCheck;
    }

    public void setPushCheck(boolean z) {
        this.mPushCheck = z;
    }

    public int getNotificationPlayVibrate() {
        return MessageSharedPrefs.getInstance(this.mContext).getNotificationPlayVibrate();
    }

    public void setNotificationPlayVibrate(int i) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setNotificationPlayVibrate(i);
        }
    }

    public int getNotificationPlayLights() {
        return MessageSharedPrefs.getInstance(this.mContext).getNotificationPlayLights();
    }

    public void setNotificationPlayLights(int i) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setNotificationPlayLights(i);
        }
    }

    public int getNotificationPlaySound() {
        return MessageSharedPrefs.getInstance(this.mContext).getNotificationPlaySound();
    }

    public void setNotificationPlaySound(int i) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).setNotificationPlaySound(i);
        }
    }

    public void keepLowPowerMode(boolean z) {
        this.powerMode = !z;
    }

    private boolean getPowerMode() {
        return this.powerMode;
    }

    public void setEnableForeground(Context context, boolean z) {
        GlobalConfig.setEnableForground(context, z);
    }

    public void setNotificationChannelName(String str) {
        if (h.d(this.mContext)) {
            MessageSharedPrefs.getInstance(this.mContext).b(str);
        }
    }

    public String getNotificationChannelName() {
        return MessageSharedPrefs.getInstance(this.mContext).h();
    }
}
