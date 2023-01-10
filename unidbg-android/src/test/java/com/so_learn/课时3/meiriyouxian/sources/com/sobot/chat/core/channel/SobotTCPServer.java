package com.sobot.chat.core.channel;

import android.app.Service;
import android.app.job.JobInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.TimedRemoteCaller;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.sobot.chat.api.apiUtils.SobotBaseUrl;
import com.sobot.chat.core.a.a.b;
import com.sobot.chat.core.a.a.i;
import com.sobot.chat.core.a.a.j;
import com.sobot.chat.core.http.c.d;
import com.sobot.chat.utils.af;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.s;
import com.umeng.analytics.pro.ai;
import com.umeng.analytics.pro.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SobotTCPServer extends Service {
    public static NetworkInfo b = null;
    public static WifiInfo c = null;
    public static boolean d = true;
    private List<String> A = new ArrayList();
    private Timer B = null;
    private TimerTask C = null;
    private final int D = 0;
    private final int E = 1;
    private int F = 0;
    private boolean G = false;
    private Map<String, String> H = new HashMap();
    private Map<String, String> I = new HashMap();
    private a J;
    private Runnable K = new AnonymousClass5();
    private boolean L = true;
    private PowerManager.WakeLock M = null;
    int a = 0;
    Context e = this;
    String f;
    String g;
    String h;
    String i;
    String j;
    String k;
    String l;
    boolean m = true;
    int n = 0;
    final int o = 0;
    final int p = 1;
    int q = 0;
    SparseArray<String> r = new SparseArray<>();
    private MyMessageReceiver s;
    private SystemMessageReceiver t;
    private LocalBroadcastManager u;
    private final int v = Process.myPid();
    private boolean w = false;
    private b<String> x = new b<>(50);
    private com.sobot.chat.core.a.a y;
    private int z = 0;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.u = LocalBroadcastManager.getInstance(this);
        i();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            return 3;
        }
        String stringExtra = intent.getStringExtra("sobot_uid_chat");
        String stringExtra2 = intent.getStringExtra("sobot_puid_chat");
        String stringExtra3 = intent.getStringExtra("sobot_wslinkbak_chat");
        String stringExtra4 = intent.getStringExtra("sobot_wslinkdefault_chat");
        String stringExtra5 = intent.getStringExtra("sobot_appkey_chat");
        String stringExtra6 = intent.getStringExtra("sobot_wayhttp_chat");
        a(true);
        if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2) || TextUtils.isEmpty(stringExtra5)) {
            return 3;
        }
        a(stringExtra, stringExtra2, stringExtra5, stringExtra3, stringExtra4, stringExtra6);
        return 3;
    }

    @Override // android.app.Service
    public void onDestroy() {
        MyMessageReceiver myMessageReceiver;
        LocalBroadcastManager localBroadcastManager = this.u;
        if (!(localBroadcastManager == null || (myMessageReceiver = this.s) == null)) {
            localBroadcastManager.unregisterReceiver(myMessageReceiver);
        }
        unregisterReceiver(this.t);
        b();
        this.y = null;
        m.d("\u63a8\u9001\u670d\u52a1\u88ab\u9500\u6bc1");
        super.onDestroy();
    }

    public class MyMessageReceiver extends BroadcastReceiver {
        public MyMessageReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context != null && intent != null) {
                if ("sobot_chat_disconnchannel".equals(intent.getAction())) {
                    SobotTCPServer.this.b();
                } else if ("sobot_chat_check_connchannel".equals(intent.getAction())) {
                    SobotTCPServer.this.a(true);
                    SobotTCPServer.this.h();
                } else if ("sobot_chat_user_outline".equals(intent.getAction())) {
                    SobotTCPServer.this.a(false);
                    SobotTCPServer.this.b();
                } else if ("sobot_chat_check_switchflag".equals(intent.getAction())) {
                    SobotTCPServer.this.b();
                    SobotTCPServer.this.f();
                }
            }
        }
    }

    public class SystemMessageReceiver extends BroadcastReceiver {
        public SystemMessageReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context != null && intent != null && ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                m.d("SobotTCPServer \u63a5\u6536\u5230\u7cfb\u7edf \u7f51\u7edc\u72b6\u6001\u53d8\u5316 \u5e7f\u64ad");
                NetworkInfo networkInfo = null;
                try {
                    networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
                } catch (Exception unused) {
                    m.d("getActiveNetworkInfo failed.");
                }
                SobotTCPServer.this.a(context, networkInfo);
                if (SobotTCPServer.this.a == 0) {
                    SobotTCPServer.this.a++;
                }
            }
        }
    }

    public void a(Context context, NetworkInfo networkInfo) {
        if (networkInfo == null) {
            b = null;
            c = null;
            g();
        } else if (networkInfo.getDetailedState() != NetworkInfo.DetailedState.CONNECTED) {
            if (d) {
                b = null;
                c = null;
                g();
            }
            d = false;
        } else {
            if (b(context, networkInfo)) {
                g();
            }
            d = true;
        }
    }

    public boolean b(Context context, NetworkInfo networkInfo) {
        WifiInfo wifiInfo;
        if (networkInfo.getType() == 1) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            WifiInfo wifiInfo2 = null;
            if (wifiManager != null) {
                wifiInfo2 = wifiManager.getConnectionInfo();
            }
            if (wifiInfo2 == null || (wifiInfo = c) == null || wifiInfo.getBSSID() == null || !c.getBSSID().equals(wifiInfo2.getBSSID()) || !c.getSSID().equals(wifiInfo2.getSSID()) || c.getNetworkId() != wifiInfo2.getNetworkId()) {
                c = wifiInfo2;
            } else {
                m.d("Same Wifi, do not NetworkChanged");
                return false;
            }
        } else {
            NetworkInfo networkInfo2 = b;
            if (networkInfo2 != null && networkInfo2.getExtraInfo() != null && networkInfo.getExtraInfo() != null && b.getExtraInfo().equals(networkInfo.getExtraInfo()) && b.getSubtype() == networkInfo.getSubtype() && b.getType() == networkInfo.getType()) {
                return false;
            }
            NetworkInfo networkInfo3 = b;
            if (networkInfo3 != null && networkInfo3.getExtraInfo() == null && networkInfo.getExtraInfo() == null && b.getSubtype() == networkInfo.getSubtype() && b.getType() == networkInfo.getType()) {
                m.d("Same Network, do not NetworkChanged");
                return false;
            }
        }
        b = networkInfo;
        return true;
    }

    private void g() {
        if (this.a != 0) {
            if (a()) {
                n();
            }
            if (!af.a(getApplicationContext())) {
                m.d("\u6ca1\u6709\u7f51\u7edc");
                d();
                af.a(getApplicationContext(), 0);
                return;
            }
            m.d("\u6709\u7f51\u7edc");
            j();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void h() {
        if (!a() && !e()) {
            j();
        }
    }

    private void i() {
        if (this.s == null) {
            this.s = new MyMessageReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("sobot_chat_disconnchannel");
        intentFilter.addAction("sobot_chat_check_connchannel");
        intentFilter.addAction("sobot_chat_check_switchflag");
        intentFilter.addAction("sobot_chat_user_outline");
        LocalBroadcastManager localBroadcastManager = this.u;
        if (localBroadcastManager != null) {
            localBroadcastManager.registerReceiver(this.s, intentFilter);
        }
        if (this.t == null) {
            this.t = new SystemMessageReceiver();
        }
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(this.t, intentFilter2);
    }

    public static class AssistService extends Service {

        public class a extends Binder {
            public a() {
            }
        }

        @Override // android.app.Service
        public IBinder onBind(Intent intent) {
            m.b("AssistService: onBind()");
            return new a();
        }

        @Override // android.app.Service
        public void onDestroy() {
            super.onDestroy();
            m.b("AssistService: onDestroy()");
        }
    }

    public synchronized void a(String str, String str2, String str3, String str4, String str5, String str6) {
        this.f = str;
        this.g = str2;
        this.h = str3;
        if (!TextUtils.isEmpty(str4)) {
            this.j = str4;
        }
        if (!TextUtils.isEmpty(str5)) {
            this.k = str5;
        }
        this.l = str6;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ai.aE, str);
            jSONObject.put(c.N, str2);
            jSONObject.put("msgId", af.c(str));
            jSONObject.put("t", 0);
            jSONObject.put("appkey", str3);
            this.i = jSONObject.toString();
            j();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void j() {
        if (!(TextUtils.isEmpty(this.f) || TextUtils.isEmpty(this.g) || TextUtils.isEmpty(this.h))) {
            int i = 1;
            this.m = true;
            if (this.q == 1 || "1".equals(this.l) || TextUtils.isEmpty(this.k)) {
                f();
                return;
            }
            try {
                if (!TextUtils.isEmpty(this.j)) {
                    JSONArray jSONArray = new JSONArray(this.j);
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        this.A.add(jSONArray.getString(i2));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Context context = this.e;
            if (a()) {
                i = 2;
            }
            af.a(context, i);
            c().b();
        }
    }

    public boolean a() {
        com.sobot.chat.core.a.a aVar = this.y;
        if (aVar != null) {
            return aVar.d();
        }
        return false;
    }

    public synchronized void b() {
        this.n = 0;
        this.m = false;
        n();
        d();
        o();
    }

    public com.sobot.chat.core.a.a c() {
        if (this.y == null) {
            this.y = new com.sobot.chat.core.a.a();
            a(this.y);
            b(this.y);
            c(this.y);
            d(this.y);
            e(this.y);
            this.y.a(new AnonymousClass1());
        }
        return this.y;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.channel.SobotTCPServer$1  reason: invalid class name */
    public class AnonymousClass1 implements b {
        AnonymousClass1() {
        }

        @Override // com.sobot.chat.core.a.a.b
        public void a(com.sobot.chat.core.a.a aVar) {
            com.sobot.chat.core.a.a.a g = aVar.g();
            if (g != null) {
                af.a("onConnected", "SocketClient: onConnected   " + g.e() + ":" + g.f());
                SobotTCPServer.this.r.clear();
                SparseArray<String> sparseArray = SobotTCPServer.this.r;
                sparseArray.put(0, g.e() + ":" + g.f());
            }
            if (!TextUtils.isEmpty(SobotTCPServer.this.i)) {
                SobotTCPServer sobotTCPServer = SobotTCPServer.this;
                sobotTCPServer.n = 0;
                sobotTCPServer.o();
                SobotTCPServer.this.d();
                SobotTCPServer sobotTCPServer2 = SobotTCPServer.this;
                sobotTCPServer2.q = 0;
                if (sobotTCPServer2.y != null) {
                    SobotTCPServer.this.y.a(com.sobot.chat.core.a.b.a.c(SobotTCPServer.this.i, "UTF-8"));
                    af.a(SobotTCPServer.this.e, 2);
                }
            }
        }

        @Override // com.sobot.chat.core.a.a.b
        public void b(com.sobot.chat.core.a.a aVar) {
            af.a("onDisconnected", "SocketClient: onDisconnected");
            SobotTCPServer.this.l();
        }

        @Override // com.sobot.chat.core.a.a.b
        public void a(com.sobot.chat.core.a.a aVar, j jVar) {
            if (!jVar.c()) {
                if (jVar.d() == 3) {
                    String b = af.b(jVar.b());
                    if (!TextUtils.isEmpty(b)) {
                        if (SobotTCPServer.this.x.a(b) == -1) {
                            SobotTCPServer.this.x.offer(b);
                            af.a(SobotTCPServer.this.e, jVar);
                        }
                        String a = af.a(b);
                        if (!TextUtils.isEmpty(a) && SobotTCPServer.this.y != null) {
                            SobotTCPServer.this.y.a(com.sobot.chat.core.a.b.a.d(a, "UTF-8"));
                            return;
                        }
                        return;
                    }
                    af.a(SobotTCPServer.this.e, jVar);
                    return;
                }
                jVar.d();
            }
        }
    }

    private void a(com.sobot.chat.core.a.a aVar) {
        if (!TextUtils.isEmpty(this.k)) {
            String[] split = this.k.split(":");
            if (split.length == 2) {
                aVar.h().a(split[0]);
                aVar.h().b(split[1]);
                aVar.h().a(10000);
            }
        }
    }

    private void b(com.sobot.chat.core.a.a aVar) {
        aVar.a("UTF-8");
    }

    private void c(com.sobot.chat.core.a.a aVar) {
        aVar.k().a(com.sobot.chat.core.a.b.a.b("ping", "UTF-8"));
        aVar.k().b(com.sobot.chat.core.a.b.a.b("pong", "UTF-8"));
        aVar.k().a(JobInfo.MIN_BACKOFF_MILLIS);
        aVar.k().a(true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.channel.SobotTCPServer$2  reason: invalid class name */
    public class AnonymousClass2 implements i.c {
        @Override // com.sobot.chat.core.a.a.i.c
        public byte[] a(i iVar, int i) {
            byte[] bArr = new byte[4];
            bArr[3] = (byte) (i & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
            bArr[1] = (byte) ((i >> 16) & 255);
            bArr[0] = (byte) ((i >> 24) & 255);
            return bArr;
        }

        AnonymousClass2() {
        }
    }

    private void d(com.sobot.chat.core.a.a aVar) {
        aVar.j().a(new AnonymousClass2());
        aVar.j().b(10240);
        aVar.j().a(true);
    }

    private void e(com.sobot.chat.core.a.a aVar) {
        aVar.j().a(i.a.AutoReadByLength);
        aVar.j().c(4);
        aVar.j().a(new AnonymousClass3());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.channel.SobotTCPServer$3  reason: invalid class name */
    public class AnonymousClass3 implements i.b {
        AnonymousClass3() {
        }

        @Override // com.sobot.chat.core.a.a.i.b
        public int a(i iVar, byte[] bArr) {
            return (bArr[3] & 255) + ((bArr[2] & 255) << 8) + ((bArr[1] & 255) << 16) + ((bArr[0] & 255) << 24);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void f(com.sobot.chat.core.a.a aVar) {
        String k = k();
        if (!TextUtils.isEmpty(k)) {
            String[] split = k.split(":");
            if (split.length == 2) {
                aVar.h().a(split[0]);
                aVar.h().b(split[1]);
            }
        }
    }

    private String k() {
        try {
            String str = this.A.get(this.z);
            this.z++;
            return str;
        } catch (Exception unused) {
            this.z = 0;
            return this.k;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void l() {
        if (!this.m || !af.a(getApplicationContext())) {
            af.a(this.e, 0);
        } else if (this.q != 1 && !this.w && !TextUtils.isEmpty(this.i) && !a()) {
            m.d("\u5f00\u542f\u91cd\u8fde");
            af.a(this.e, 1);
            this.w = true;
            Timer timer = this.B;
            if (timer != null) {
                timer.cancel();
                this.B = null;
            }
            TimerTask timerTask = this.C;
            if (timerTask != null) {
                timerTask.cancel();
                this.C = null;
            }
            this.B = new Timer();
            this.C = new AnonymousClass4();
            try {
                this.B.schedule(this.C, 300, TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS);
            } catch (Exception unused) {
                d();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.channel.SobotTCPServer$4  reason: invalid class name */
    public class AnonymousClass4 extends TimerTask {
        AnonymousClass4() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (af.a(SobotTCPServer.this.e) && SobotTCPServer.this.q == 0 && SobotTCPServer.this.m) {
                SobotTCPServer.this.n++;
                if (SobotTCPServer.this.r == null || SobotTCPServer.this.r.size() <= 0) {
                    if (SobotTCPServer.this.n > 3) {
                        SobotTCPServer.this.f();
                        return;
                    } else {
                        SobotTCPServer sobotTCPServer = SobotTCPServer.this;
                        sobotTCPServer.f(sobotTCPServer.c());
                    }
                } else if (SobotTCPServer.this.n > 3) {
                    SobotTCPServer.this.f();
                    return;
                }
                if (SobotTCPServer.this.q == 0) {
                    SobotTCPServer.this.c().b();
                }
            }
        }
    }

    public void d() {
        try {
            if (this.B != null) {
                this.B.cancel();
                this.B = null;
            }
            if (this.C != null) {
                this.C.cancel();
                this.C = null;
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            this.B = null;
            this.C = null;
            throw th;
        }
        this.B = null;
        this.C = null;
        this.w = false;
        this.n = 0;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private a m() {
        if (this.J == null) {
            this.J = new a();
        }
        return this.J;
    }

    /* access modifiers changed from: private */
    public static class a extends Handler {
        public a() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }

    public boolean e() {
        return this.F == 1;
    }

    public void f() {
        this.q = 1;
        n();
        d();
        af.a(this.e, 2);
        StringBuilder sb = new StringBuilder();
        sb.append("inPolling:");
        sb.append(this.G);
        sb.append("    isRunning:");
        sb.append(this.m);
        sb.append("   isPollingStart:");
        sb.append(e());
        sb.append("   !isOnline");
        sb.append(!this.L);
        m.d(sb.toString());
        if (!this.G && this.m && !e() && this.L) {
            this.F = 1;
            m().removeCallbacks(this.K);
            m().post(this.K);
            HashMap hashMap = new HashMap();
            hashMap.put("content", "isOnline:" + this.L + "  pollingSt:" + this.F + "  inPolling:" + this.G + "  isRunning:" + this.m);
            hashMap.put("title", "startPolling");
            m.a(hashMap, "\u5f00\u542f\u8f6e\u8be2");
        }
    }

    private void n() {
        com.sobot.chat.core.a.a aVar = this.y;
        if (aVar != null) {
            aVar.c();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.channel.SobotTCPServer$5  reason: invalid class name */
    public class AnonymousClass5 implements Runnable {
        AnonymousClass5() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!SobotTCPServer.this.G && SobotTCPServer.this.m && SobotTCPServer.this.F != 0) {
                if (!af.a(SobotTCPServer.this.e)) {
                    SobotTCPServer.this.m().removeCallbacks(SobotTCPServer.this.K);
                    SobotTCPServer.this.m().postDelayed(SobotTCPServer.this.K, JobInfo.MIN_BACKOFF_MILLIS);
                    return;
                }
                SobotTCPServer.this.G = true;
                SobotTCPServer.this.H.put("uid", SobotTCPServer.this.f);
                SobotTCPServer.this.H.put(c.N, SobotTCPServer.this.g);
                Map map = SobotTCPServer.this.H;
                map.put("tnk", System.currentTimeMillis() + "");
                s.b(SobotTCPServer.this.e, "sobot_platform_unioncode", "");
                com.sobot.chat.core.http.a.c e = com.sobot.chat.core.http.a.e();
                e.a(SobotBaseUrl.getBaseIp() + "msg.action").a(SobotTCPServer.this.H).a().a(8000).b(8000).c(8000).b(new AnonymousClass1());
            }
        }

        /* renamed from: com.sobot.chat.core.channel.SobotTCPServer$5$1  reason: invalid class name */
        class AnonymousClass1 extends d {
            AnonymousClass1() {
            }

            public void a(String str) {
                SobotTCPServer.this.n = 0;
                SobotTCPServer.this.m().removeCallbacks(SobotTCPServer.this.K);
                SobotTCPServer.this.m().postDelayed(SobotTCPServer.this.K, TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS);
                SobotTCPServer.this.a(str);
                SobotTCPServer.this.G = false;
            }

            @Override // com.sobot.chat.core.http.c.b
            public void a(Call call, Exception exc) {
                SobotTCPServer.this.m().removeCallbacks(SobotTCPServer.this.K);
                SobotTCPServer.this.m().postDelayed(SobotTCPServer.this.K, JobInfo.MIN_BACKOFF_MILLIS);
                SobotTCPServer.this.G = false;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "content"
            boolean r1 = android.text.TextUtils.isEmpty(r9)
            if (r1 != 0) goto L_0x0103
            r1 = 0
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0067 }
            r2.<init>(r9)     // Catch:{ JSONException -> 0x0067 }
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0067 }
            r3.<init>()     // Catch:{ JSONException -> 0x0067 }
            r1 = 0
        L_0x0015:
            int r4 = r2.length()     // Catch:{ JSONException -> 0x0065 }
            if (r1 >= r4) goto L_0x0096
            java.lang.String r4 = r2.getString(r1)     // Catch:{ JSONException -> 0x0065 }
            java.lang.String r5 = com.sobot.chat.utils.af.b(r4)     // Catch:{ JSONException -> 0x0065 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ JSONException -> 0x0065 }
            if (r6 != 0) goto L_0x005d
            com.sobot.chat.core.channel.b<java.lang.String> r6 = r8.x     // Catch:{ JSONException -> 0x0065 }
            int r6 = r6.a(r5)     // Catch:{ JSONException -> 0x0065 }
            r7 = -1
            if (r6 != r7) goto L_0x003c
            com.sobot.chat.core.channel.b<java.lang.String> r6 = r8.x     // Catch:{ JSONException -> 0x0065 }
            r6.offer(r5)     // Catch:{ JSONException -> 0x0065 }
            android.content.Context r6 = r8.e     // Catch:{ JSONException -> 0x0065 }
            com.sobot.chat.utils.af.a(r6, r4)     // Catch:{ JSONException -> 0x0065 }
        L_0x003c:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0065 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0065 }
            r6.<init>()     // Catch:{ JSONException -> 0x0065 }
            java.lang.String r7 = "{msgId:"
            r6.append(r7)     // Catch:{ JSONException -> 0x0065 }
            r6.append(r5)     // Catch:{ JSONException -> 0x0065 }
            java.lang.String r5 = "}"
            r6.append(r5)     // Catch:{ JSONException -> 0x0065 }
            java.lang.String r5 = r6.toString()     // Catch:{ JSONException -> 0x0065 }
            r4.<init>(r5)     // Catch:{ JSONException -> 0x0065 }
            r3.put(r4)     // Catch:{ JSONException -> 0x0065 }
            goto L_0x0062
        L_0x005d:
            android.content.Context r5 = r8.e     // Catch:{ JSONException -> 0x0065 }
            com.sobot.chat.utils.af.a(r5, r4)     // Catch:{ JSONException -> 0x0065 }
        L_0x0062:
            int r1 = r1 + 1
            goto L_0x0015
        L_0x0065:
            r1 = move-exception
            goto L_0x006a
        L_0x0067:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L_0x006a:
            r1.printStackTrace()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "rl:"
            r2.append(r4)
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            r1.put(r0, r9)
            java.lang.String r9 = "title"
            java.lang.String r2 = "polling jsonException"
            r1.put(r9, r2)
            java.lang.String r9 = "\u8bf7\u6c42\u5931\u8d25"
            com.sobot.chat.utils.m.a(r1, r9)
        L_0x0096:
            if (r3 == 0) goto L_0x0103
            int r9 = r3.length()
            if (r9 <= 0) goto L_0x0103
            java.util.Map<java.lang.String, java.lang.String> r9 = r8.I
            java.lang.String r1 = r3.toString()
            r9.put(r0, r1)
            java.util.Map<java.lang.String, java.lang.String> r9 = r8.I
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            long r1 = java.lang.System.currentTimeMillis()
            r0.append(r1)
            java.lang.String r1 = ""
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "tnk"
            r9.put(r1, r0)
            com.sobot.chat.core.http.a.c r9 = com.sobot.chat.core.http.a.e()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = com.sobot.chat.api.apiUtils.SobotBaseUrl.getBaseIp()
            r0.append(r1)
            java.lang.String r1 = "msg/ack.action"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.sobot.chat.core.http.a.c r9 = r9.a(r0)
            java.util.Map<java.lang.String, java.lang.String> r0 = r8.I
            com.sobot.chat.core.http.a.c r9 = r9.a(r0)
            com.sobot.chat.core.http.d.e r9 = r9.a()
            r0 = 8000(0x1f40, double:3.9525E-320)
            com.sobot.chat.core.http.d.e r9 = r9.a(r0)
            com.sobot.chat.core.http.d.e r9 = r9.b(r0)
            com.sobot.chat.core.http.d.e r9 = r9.c(r0)
            com.sobot.chat.core.channel.SobotTCPServer$6 r0 = new com.sobot.chat.core.channel.SobotTCPServer$6
            r0.<init>()
            r9.b(r0)
        L_0x0103:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.core.channel.SobotTCPServer.a(java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.channel.SobotTCPServer$6  reason: invalid class name */
    public class AnonymousClass6 extends d {
        public void a(String str) {
        }

        @Override // com.sobot.chat.core.http.c.b
        public void a(Call call, Exception exc) {
        }

        AnonymousClass6() {
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void o() {
        this.F = 0;
        m().removeCallbacks(this.K);
        HashMap hashMap = new HashMap();
        hashMap.put("content", "isOnline:" + this.L + "  pollingSt:" + this.F + "  inPolling:" + this.G + "  isRunning:" + this.m);
        hashMap.put("title", "stopPolling");
        m.a(hashMap, "\u5173\u95ed\u8f6e\u8be2");
    }

    public void a(boolean z) {
        this.L = z;
        if (!z) {
            o();
        }
    }
}
