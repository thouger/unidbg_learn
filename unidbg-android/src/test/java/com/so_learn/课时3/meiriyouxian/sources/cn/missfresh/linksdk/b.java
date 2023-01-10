package cn.missfresh.linksdk;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;
import cn.missfresh.linksdk.api.IMFLink;
import cn.missfresh.linksdk.api.MFLinkCallBack;
import cn.missfresh.linksdk.bean.IntentData;
import cn.missfresh.linksdk.bean.MFLinkConfig;
import cn.missfresh.linksdk.c.a;
import cn.missfresh.linksdk.c.c;
import cn.missfresh.linksdk.c.d;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.HashMap;

/* compiled from: MFLinkImpl */
public class b implements IMFLink {
    MFLinkConfig a;
    a b;
    HashMap<String, MFLinkCallBack> c = new HashMap<>();
    IntentData d;

    public b() {
        AppMethodBeat.i(13602, false);
        AppMethodBeat.o(13602);
    }

    public void init(MFLinkConfig mFLinkConfig) {
        AppMethodBeat.i(13607, false);
        this.a = mFLinkConfig;
        if (this.a.getContext() instanceof Application) {
            this.b = new a((Application) this.a.getContext());
        } else {
            d.a("Context format error, context is not instance of Application");
        }
        AppMethodBeat.o(13607);
    }

    public void register(String str, MFLinkCallBack mFLinkCallBack) {
        IntentData intentData;
        AppMethodBeat.i(13611, false);
        if (TextUtils.isEmpty(str) || mFLinkCallBack == null) {
            c.a("MFLinkAgent", "register callback or key is null\uff01");
            cn.missfresh.linksdk.c.b.a("register callback or key is null\uff01", mFLinkCallBack);
            AppMethodBeat.o(13611);
        } else if (a()) {
            c.a("MFLinkAgent", "MFLinkSDK is not init\uff01");
            cn.missfresh.linksdk.c.b.a("MFLinkSDK is not init\uff01", mFLinkCallBack);
            AppMethodBeat.o(13611);
        } else {
            this.c.put(str, mFLinkCallBack);
            Activity a = this.b.a();
            if (a == null || a.isFinishing()) {
                c.a("MFLinkAgent", "Current activity is invalid!");
                cn.missfresh.linksdk.c.b.a("Current activity is invalid!", mFLinkCallBack);
                AppMethodBeat.o(13611);
            } else if (!this.b.a(a) && (intentData = this.d) != null && intentData.isValid()) {
                a(this.d, mFLinkCallBack);
                AppMethodBeat.o(13611);
            } else if (this.b.a(a) || this.d != null) {
                Intent intent = a.getIntent();
                if (d.a(intent)) {
                    c.a("MFLinkAgent", "intent is null");
                    AppMethodBeat.o(13611);
                    return;
                }
                this.d = IntentData.create(intent);
                if (d.a(this.d.getUri())) {
                    c.a("MFLinkAgent", "data is null");
                    AppMethodBeat.o(13611);
                } else if (TextUtils.isEmpty(this.d.getScheme())) {
                    c.a("MFLinkAgent", "scheme is null");
                    cn.missfresh.linksdk.c.b.a("scheme is null!", mFLinkCallBack);
                    AppMethodBeat.o(13611);
                } else {
                    a(this.d, mFLinkCallBack);
                    AppMethodBeat.o(13611);
                }
            } else {
                c.a("MFLinkAgent", "Not launch activity intent is null");
                cn.missfresh.linksdk.c.b.a("Current activity is Not launch activity!", mFLinkCallBack);
                AppMethodBeat.o(13611);
            }
        }
    }

    private boolean a() {
        return this.b == null || this.a == null;
    }

    public void unRegister(String str) {
        AppMethodBeat.i(13615, false);
        if (a()) {
            c.a("MFLinkAgent", "MFLinkSDK is not init\uff01");
            cn.missfresh.linksdk.c.b.a("MFLinkSDK is not init\uff01", this.c.get(str));
            AppMethodBeat.o(13615);
            return;
        }
        this.c.remove(str);
        AppMethodBeat.o(13615);
    }

    private void a(IntentData intentData, MFLinkCallBack mFLinkCallBack) {
        AppMethodBeat.i(13618, false);
        if (a(intentData.getScheme(), intentData.getHost())) {
            c.a("MFLinkAgent", "params " + intentData.getMapParams().size());
            if (a(intentData.getScheme())) {
                new cn.missfresh.linksdk.a.b(this.a).b(intentData, mFLinkCallBack);
            } else {
                new cn.missfresh.linksdk.a.a(this.a).b(intentData, mFLinkCallBack);
            }
        } else {
            c.a("MFLinkAgent", "scheme is not registered ! " + intentData.toString());
            cn.missfresh.linksdk.c.b.a("scheme is not registered ! ", mFLinkCallBack);
        }
        AppMethodBeat.o(13618);
    }

    private boolean a(String str) {
        AppMethodBeat.i(13621, false);
        if (TextUtils.isEmpty(str) || (!"http".equalsIgnoreCase(str) && !"https".equalsIgnoreCase(str))) {
            c.a("MFLinkAgent", "scheme is not deeplink !" + str);
            AppMethodBeat.o(13621);
            return true;
        }
        AppMethodBeat.o(13621);
        return false;
    }

    public boolean a(String str, String str2) {
        AppMethodBeat.i(13624, false);
        if (this.a.getSchemeList().contains(str) || this.a.getHostList().contains(str2)) {
            AppMethodBeat.o(13624);
            return true;
        }
        AppMethodBeat.o(13624);
        return false;
    }
}
