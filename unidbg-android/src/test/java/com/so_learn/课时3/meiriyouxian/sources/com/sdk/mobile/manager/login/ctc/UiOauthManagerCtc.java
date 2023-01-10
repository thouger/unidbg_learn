package com.sdk.mobile.manager.login.ctc;

import android.content.Context;
import android.content.Intent;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.api.CallBack;
import com.sdk.base.api.OnCustomViewListener;
import com.sdk.base.api.UiOauthListener;
import com.sdk.base.framework.bean.OauthResultMode;
import com.sdk.base.framework.c.f;
import com.sdk.base.module.manager.SDKManager;
import com.sdk.mobile.handler.UiHandler;
import com.sdk.mobile.handler.a.a;
import com.sdk.mobile.handler.a.e;
import com.sdk.mobile.manager.login.manager.UiConfig;
import java.io.Serializable;

public class UiOauthManagerCtc extends SDKManager {
    private static final String TAG = UiOauthManagerCtc.class.getSimpleName();
    private static Boolean isDebug = Boolean.valueOf(f.a);
    private static volatile UiOauthManagerCtc manager;
    private boolean cancel;
    private Context mContext;
    private UiOauthListener oauthListener;
    private OnCustomViewListener otherLoginListener;
    private OauthResultMode resultMode;

    /* renamed from: com.sdk.mobile.manager.login.ctc.UiOauthManagerCtc$1  reason: invalid class name */
    class AnonymousClass1 implements CallBack {
        final /* synthetic */ UiOauthListener val$listener;
        final /* synthetic */ UiConfig val$uiConfig;

        AnonymousClass1(UiConfig uiConfig, UiOauthListener uiOauthListener) {
            this.val$uiConfig = uiConfig;
            this.val$listener = uiOauthListener;
        }

        public void onFailed(int i, int i2, String str, String str2) {
            AppMethodBeat.i(18859, false);
            this.val$listener.onFailed(new OauthResultMode(i, str, i2), (UiHandler) null);
            AppMethodBeat.o(18859);
        }

        public void onSuccess(int i, String str, int i2, Object obj, String str2) {
            AppMethodBeat.i(18858, false);
            if (i == 0) {
                UiOauthManagerCtc.access$000(UiOauthManagerCtc.this, this.val$uiConfig);
            } else {
                this.val$listener.onSuccess(new OauthResultMode(i, str, i2, obj, str2), (UiHandler) null);
            }
            AppMethodBeat.o(18858);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sdk.mobile.manager.login.ctc.UiOauthManagerCtc$2  reason: invalid class name */
    public class AnonymousClass2 implements CallBack<T> {
        final /* synthetic */ CallBack val$callBack;

        AnonymousClass2(CallBack callBack) {
            this.val$callBack = callBack;
        }

        public void onFailed(int i, int i2, String str, String str2) {
            AppMethodBeat.i(18839, false);
            this.val$callBack.onFailed(i, i2, str, str2);
            AppMethodBeat.o(18839);
        }

        public void onSuccess(int i, String str, int i2, T t, String str2) {
            AppMethodBeat.i(18835, false);
            if (i == 0) {
                UiOauthManagerCtc.this.resultMode = new OauthResultMode(i, str, i2, t, str2);
            }
            this.val$callBack.onSuccess(i, str, i2, (Object) null, str2);
            AppMethodBeat.o(18835);
        }
    }

    static {
        AppMethodBeat.i(18895, false);
        AppMethodBeat.o(18895);
    }

    private UiOauthManagerCtc(Context context) {
        this.mContext = context;
    }

    static /* synthetic */ void access$000(UiOauthManagerCtc uiOauthManagerCtc, UiConfig uiConfig) {
        AppMethodBeat.i(18892, false);
        uiOauthManagerCtc.open(uiConfig);
        AppMethodBeat.o(18892);
    }

    private <T> void dispatchHandler(int i, CallBack<T> callBack) {
        AppMethodBeat.i(18882, false);
        new a(this.mContext, i, new AnonymousClass2(callBack)).a(0);
        AppMethodBeat.o(18882);
    }

    public static UiOauthManagerCtc getInstance(Context context) {
        AppMethodBeat.i(18864, false);
        if (manager == null) {
            synchronized (UiOauthManagerCtc.class) {
                try {
                    if (manager == null) {
                        manager = new UiOauthManagerCtc(context);
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(18864);
                    throw th;
                }
            }
        }
        UiOauthManagerCtc uiOauthManagerCtc = manager;
        AppMethodBeat.o(18864);
        return uiOauthManagerCtc;
    }

    private void open(UiConfig uiConfig) {
        AppMethodBeat.i(18875, false);
        if (this.cancel) {
            this.oauthListener.onSuccess(new OauthResultMode(1, "\u7528\u6237\u53d6\u6d88\u767b\u5f55", 101007), (UiHandler) null);
            this.cancel = false;
        } else {
            Intent intent = new Intent(this.mContext, OauthActivityCtc.class);
            intent.putExtra("uiConfig", (Serializable) uiConfig);
            intent.putExtra("resultMode", (Serializable) this.resultMode);
            intent.addFlags(268435456);
            this.mContext.startActivity(intent);
        }
        AppMethodBeat.o(18875);
    }

    public void cancel() {
        this.cancel = true;
    }

    /* access modifiers changed from: package-private */
    public OnCustomViewListener getOtherLoginListener() {
        return this.otherLoginListener;
    }

    public <T> void login(int i, CallBack<T> callBack) {
        AppMethodBeat.i(18868, false);
        dispatchHandler(i, callBack);
        AppMethodBeat.o(18868);
    }

    public void openActivity(UiConfig uiConfig, int i, UiOauthListener uiOauthListener) {
        AppMethodBeat.i(18871, false);
        this.oauthListener = uiOauthListener;
        if (uiConfig == null) {
            uiOauthListener.onFailed(new OauthResultMode(1, "UiConfig \u4e0d\u80fd\u4e3a\u7a7a", 101003), (UiHandler) null);
        } else if (this.resultMode == null || !SDKManager.useCache() || com.sdk.base.framework.f.l.a.a((String) this.resultMode.getObject())) {
            dispatchHandler(i, new AnonymousClass1(uiConfig, uiOauthListener));
        } else {
            open(uiConfig);
        }
        AppMethodBeat.o(18871);
    }

    /* access modifiers changed from: package-private */
    public void setOauthResult(OauthResultMode oauthResultMode, OauthActivityCtc oauthActivityCtc) {
        AppMethodBeat.i(18876, false);
        if (this.oauthListener != null) {
            e eVar = new e(oauthActivityCtc);
            if (oauthResultMode.getSeq() != null) {
                this.oauthListener.onSuccess(oauthResultMode, eVar);
            } else {
                this.oauthListener.onFailed(oauthResultMode, eVar);
            }
            this.oauthListener = null;
        }
        AppMethodBeat.o(18876);
    }

    /* access modifiers changed from: package-private */
    public void setOauthResult(OauthResultMode oauthResultMode, OauthActivityCtc oauthActivityCtc, boolean z) {
        AppMethodBeat.i(18878, false);
        if (z) {
            this.resultMode = null;
            com.sdk.base.framework.a.a.a.a(this.mContext);
        }
        setOauthResult(oauthResultMode, oauthActivityCtc);
        AppMethodBeat.o(18878);
    }

    public void setOtherLoginListener(OnCustomViewListener onCustomViewListener) {
        this.otherLoginListener = onCustomViewListener;
    }
}
