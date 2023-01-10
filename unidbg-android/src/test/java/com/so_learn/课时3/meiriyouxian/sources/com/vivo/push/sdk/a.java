package com.vivo.push.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.taobao.accs.common.Constants;
import com.vivo.push.i;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.n;
import com.vivo.push.util.r;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/* compiled from: CommandWorker */
public final class a extends i {
    private static a d;
    private static final List<Integer> f = Arrays.asList(3);
    public String c;
    private Handler e = new Handler(Looper.getMainLooper());

    static {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_AUTOFILL_NUM_DATASETS, false);
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_AUTOFILL_NUM_DATASETS);
    }

    private a() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_APPOP_DENIED_REQUEST_INSTALL_PACKAGES, false);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_APPOP_DENIED_REQUEST_INSTALL_PACKAGES);
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_INSTANT_APP_RESOLUTION_PHASE_ONE, false);
            if (d == null) {
                d = new a();
            }
            aVar = d;
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_INSTANT_APP_RESOLUTION_PHASE_ONE);
        }
        return aVar;
    }

    public final void a(Intent intent) {
        AppMethodBeat.i(901, false);
        if (intent == null || this.a == null) {
            n.d("CommandWorker", " sendMessage error: intent : " + intent + ", mContext: " + this.a);
            AppMethodBeat.o(901);
            return;
        }
        Message obtain = Message.obtain();
        obtain.obj = intent;
        a(obtain);
        AppMethodBeat.o(901);
    }

    @Override // com.vivo.push.i
    public final void b(Message message) {
        boolean z;
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_INSTANT_APP_LAUNCH_TOKEN, false);
        Intent intent = (Intent) message.obj;
        if (intent == null || this.a == null) {
            n.d("CommandWorker", " handleMessage error: intent : " + intent + ", mContext: " + this.a);
            AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_INSTANT_APP_LAUNCH_TOKEN);
            return;
        }
        String packageName = this.a.getPackageName();
        try {
            String stringExtra = intent.getStringExtra("command_type");
            if (!TextUtils.isEmpty(stringExtra)) {
                if (stringExtra.equals("reflect_receiver")) {
                    int intExtra = intent.getIntExtra(Constants.KEY_COMMAND, -1);
                    if (intExtra < 0) {
                        intExtra = intent.getIntExtra("method", -1);
                    }
                    if (f.contains(Integer.valueOf(intExtra)) && r.a(this.a, packageName, "com.vivo.pushclient.action.RECEIVE")) {
                        Context context = this.a;
                        if (r.a != null) {
                            z = r.a.booleanValue();
                        } else {
                            String str = null;
                            if (context != null) {
                                if (!TextUtils.isEmpty("com.vivo.push.sdk.service.SystemPushConfig")) {
                                    ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.vivo.push.sdk.service.SystemPushConfig", 128);
                                    if (resolveContentProvider != null) {
                                        str = resolveContentProvider.packageName;
                                    }
                                }
                            }
                            Boolean valueOf = Boolean.valueOf("BCC35D4D3606F154F0402AB7634E8490C0B244C2675C3C6238986987024F0C02".equals(r.b(context, str)));
                            r.a = valueOf;
                            z = valueOf.booleanValue();
                        }
                        if (!z) {
                            AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_INSTANT_APP_LAUNCH_TOKEN);
                            return;
                        }
                    }
                    String action = intent.getAction();
                    if (TextUtils.isEmpty(this.c)) {
                        this.c = a(this.a, packageName, action);
                        if (TextUtils.isEmpty(this.c)) {
                            n.d("CommandWorker", " reflectReceiver error: receiver for: " + action + " not found, package: " + packageName);
                            intent.setPackage(packageName);
                            this.a.sendBroadcast(intent);
                            AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_INSTANT_APP_LAUNCH_TOKEN);
                            return;
                        }
                    }
                    try {
                        Class<?> cls = Class.forName(this.c);
                        Object newInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
                        Method method = cls.getMethod("onReceive", Context.class, Intent.class);
                        intent.setClassName(packageName, this.c);
                        this.e.post(new AnonymousClass1(method, newInstance, new Object[]{ContextDelegate.getContext(this.a).getApplicationContext(), intent}));
                        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_INSTANT_APP_LAUNCH_TOKEN);
                        return;
                    } catch (Exception e) {
                        n.b("CommandWorker", "reflect e: ", e);
                        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_INSTANT_APP_LAUNCH_TOKEN);
                        return;
                    }
                }
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_INSTANT_APP_LAUNCH_TOKEN);
        } catch (Exception e2) {
            n.a("CommandWorker", e2);
        }
    }

    /* compiled from: CommandWorker */
    /* renamed from: com.vivo.push.sdk.a$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ Method a;
        final /* synthetic */ Object b;
        final /* synthetic */ Object[] c;

        AnonymousClass1(Method method, Object obj, Object[] objArr) {
            this.a = method;
            this.b = obj;
            this.c = objArr;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_APPOP_GRANT_WRITE_SETTINGS, false);
            try {
                this.a.invoke(this.b, this.c);
                AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_APPOP_GRANT_WRITE_SETTINGS);
            } catch (Exception e) {
                n.b("CommandWorker", "reflect e: ", e);
                AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_APPOP_GRANT_WRITE_SETTINGS);
            }
        }
    }

    private static String a(Context context, String str, String str2) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.AUTOFILL_SESSION_STARTED, false);
        String str3 = null;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.AUTOFILL_SESSION_STARTED);
            return null;
        }
        Intent intent = new Intent(str2);
        intent.setPackage(str);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                AppMethodBeat.o(MetricsProto.MetricsEvent.AUTOFILL_SESSION_STARTED);
                return null;
            }
            List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 64);
            if (queryBroadcastReceivers != null && queryBroadcastReceivers.size() > 0) {
                str3 = queryBroadcastReceivers.get(0).activityInfo.name;
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.AUTOFILL_SESSION_STARTED);
            return str3;
        } catch (Exception e) {
            n.a("CommandWorker", "error  " + e.getMessage());
        }
    }
}
