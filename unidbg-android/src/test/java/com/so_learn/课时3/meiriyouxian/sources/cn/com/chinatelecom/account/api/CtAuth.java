package cn.com.chinatelecom.account.api;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.a.d;
import cn.com.chinatelecom.account.api.b.b;
import cn.com.chinatelecom.account.api.c.f;
import cn.com.chinatelecom.account.api.c.g;
import cn.com.chinatelecom.account.api.c.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import org.json.JSONObject;

public class CtAuth {
    private static final String TAG = CtAuth.class.getSimpleName();
    private static volatile CtAuth instance;
    public static boolean isInit = false;
    public static String mAppId = "";
    public static String mAppSecret = "";
    public static Context mContext;
    public static Handler mHandler = new Handler(Looper.getMainLooper());
    public static TraceLogger mTraceLogger;

    /* renamed from: cn.com.chinatelecom.account.api.CtAuth$1  reason: invalid class name */
    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ ResultListener a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;

        AnonymousClass1(ResultListener resultListener, String str, String str2) {
            this.a = resultListener;
            this.b = str;
            this.c = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(7765, false);
            if (this.a != null) {
                try {
                    JSONObject jSONObject = new JSONObject(this.b);
                    jSONObject.put("reqId", this.c);
                    this.a.onResult(jSONObject.toString());
                } catch (Throwable th) {
                    th.printStackTrace();
                    this.a.onResult(this.b);
                }
                f.c(this.c);
            }
            AppMethodBeat.o(7765);
        }
    }

    static {
        AppMethodBeat.i(7808, false);
        AppMethodBeat.o(7808);
    }

    public static CtAuth getInstance() {
        AppMethodBeat.i(7780, false);
        if (instance == null) {
            synchronized (CtAuth.class) {
                try {
                    if (instance == null) {
                        instance = new CtAuth();
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(7780);
                    throw th;
                }
            }
        }
        CtAuth ctAuth = instance;
        AppMethodBeat.o(7780);
        return ctAuth;
    }

    public static void info(String str, String str2) {
        AppMethodBeat.i(7802, false);
        if (mTraceLogger != null) {
            mTraceLogger.info("CT_" + str, str2);
        }
        AppMethodBeat.o(7802);
    }

    public static void postResultOnMainThread(Context context, String str, String str2, ResultListener resultListener) {
        AppMethodBeat.i(7798, false);
        mHandler.post(new AnonymousClass1(resultListener, str2, str));
        AppMethodBeat.o(7798);
    }

    public static void warn(String str, String str2, Throwable th) {
        AppMethodBeat.i(7805, false);
        if (mTraceLogger != null) {
            mTraceLogger.warn("CT_" + str, str2, th);
        }
        AppMethodBeat.o(7805);
    }

    public void init(Context context, String str, String str2, TraceLogger traceLogger) {
        AppMethodBeat.i(7784, false);
        if (context == null) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("context must not be null!");
            AppMethodBeat.o(7784);
            throw illegalArgumentException;
        } else if (str == null) {
            IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException("appId must not be null!");
            AppMethodBeat.o(7784);
            throw illegalArgumentException2;
        } else if (str2 != null) {
            if (!(context instanceof Application)) {
                context = context.getApplicationContext();
            }
            mContext = context;
            b.a(mContext);
            mAppId = str;
            mAppSecret = str2;
            mTraceLogger = traceLogger;
            AppMethodBeat.o(7784);
        } else {
            IllegalArgumentException illegalArgumentException3 = new IllegalArgumentException("appSecret must not be null!");
            AppMethodBeat.o(7784);
            throw illegalArgumentException3;
        }
    }

    @Deprecated
    public void requestPreCode(CtSetting ctSetting, ResultListener resultListener) {
        AppMethodBeat.i(7788, false);
        requestPreLogin(ctSetting, resultListener);
        AppMethodBeat.o(7788);
    }

    public void requestPreLogin(CtSetting ctSetting, ResultListener resultListener) {
        String e;
        AppMethodBeat.i(7793, false);
        info(TAG, "called requestPreLogin()");
        if (resultListener == null) {
            AppMethodBeat.o(7793);
            return;
        }
        if (mContext == null || TextUtils.isEmpty(mAppId) || TextUtils.isEmpty(mAppSecret)) {
            e = j.e();
        } else if (!g.b(mContext)) {
            e = j.a();
        } else {
            if (g.c(mContext)) {
                new a().a(mContext, mAppId, mAppSecret, d.a(cn.com.chinatelecom.account.api.c.b.d), ctSetting, resultListener);
            } else if (g.d(mContext)) {
                new a().b(mContext, mAppId, mAppSecret, d.a(cn.com.chinatelecom.account.api.c.b.d), ctSetting, resultListener);
            } else {
                resultListener.onResult(j.d());
            }
            AppMethodBeat.o(7793);
            return;
        }
        resultListener.onResult(e);
        AppMethodBeat.o(7793);
    }
}
