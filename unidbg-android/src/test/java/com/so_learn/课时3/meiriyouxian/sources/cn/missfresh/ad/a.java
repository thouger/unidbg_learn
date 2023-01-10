package cn.missfresh.ad;

import android.content.Context;
import android.text.TextUtils;
import cn.missfresh.ad.a.b;
import cn.missfresh.ad.a.d;
import cn.missfresh.ad.api.IMFADClient;
import cn.missfresh.ad.api.IMFADDownloadListener;
import cn.missfresh.ad.api.IMFADLoader;
import cn.missfresh.ad.api.IMFReqADInfoListener;
import cn.missfresh.ad.b.c;
import cn.missfresh.ad.data.MFADBean;
import cn.missfresh.ad.data.MFADPathHelper;
import cn.missfresh.ad.data.db.MFADDbManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import java.io.File;

/* compiled from: MFADClientImpl */
public class a implements IMFADClient {
    private volatile boolean a;
    private MFADPathHelper b;
    private MFADDbManager c;
    private cn.missfresh.ad.a.a d;

    @Override // cn.missfresh.ad.api.IMFADClient
    public void requestPermissionIfNecessary(Context context) {
    }

    /* synthetic */ a(AnonymousClass1 r1) {
        this();
    }

    static /* synthetic */ void a(a aVar, d dVar, String str, IMFReqADInfoListener iMFReqADInfoListener, IMFADDownloadListener iMFADDownloadListener) {
        AppMethodBeat.i(5789, false);
        aVar.a(dVar, str, iMFReqADInfoListener, iMFADDownloadListener);
        AppMethodBeat.o(5789);
    }

    static /* synthetic */ void a(a aVar, String str, IMFReqADInfoListener iMFReqADInfoListener, d dVar, IMFADDownloadListener iMFADDownloadListener) {
        AppMethodBeat.i(5792, false);
        aVar.a(str, iMFReqADInfoListener, dVar, iMFADDownloadListener);
        AppMethodBeat.o(5792);
    }

    private a() {
    }

    public static a a() {
        AppMethodBeat.i(5741, false);
        a aVar = C0013a.a;
        AppMethodBeat.o(5741);
        return aVar;
    }

    /* compiled from: MFADClientImpl */
    /* renamed from: cn.missfresh.ad.a$a  reason: collision with other inner class name */
    private static class C0013a {
        private static a a = new a(null);

        static {
            AppMethodBeat.i(5733, false);
            AppMethodBeat.o(5733);
        }
    }

    public synchronized void a(cn.missfresh.ad.data.a aVar) {
        AppMethodBeat.i(5745, false);
        if (this.a) {
            cn.missfresh.ad.b.a.a("MFADSDK", "MFADClientImpl has been initialized");
            AppMethodBeat.o(5745);
            return;
        }
        c.a(aVar, "MFADConfig \u4e0d\u80fd\u4e3a\u7a7a");
        b bVar = new b();
        bVar.a(aVar);
        cn.missfresh.ad.b.a.a(aVar);
        this.b = new MFADPathHelper(bVar.a());
        this.c = new MFADDbManager(bVar.a());
        this.d = new cn.missfresh.ad.a.a(this.c);
        this.c.a((long) aVar.f());
        this.a = true;
        cn.missfresh.ad.b.a.a("MFADSDK", "MFADInit Success: \n version : 0.0.2 is initialized " + this.a);
        AppMethodBeat.o(5745);
    }

    @Override // cn.missfresh.ad.api.IMFADClient
    public void loadSplashAD(d dVar, IMFReqADInfoListener iMFReqADInfoListener, IMFADDownloadListener iMFADDownloadListener) {
        AppMethodBeat.i(5748, false);
        cn.missfresh.ad.b.a.a("MFADSDK", "loadSplashAD before");
        if (b()) {
            cn.missfresh.ad.b.a.a("MFADSDK", "loadSplashAD start");
            b.a().a(dVar, new AnonymousClass1(iMFReqADInfoListener, iMFADDownloadListener, dVar));
        }
        AppMethodBeat.o(5748);
    }

    /* compiled from: MFADClientImpl */
    /* renamed from: cn.missfresh.ad.a$1  reason: invalid class name */
    class AnonymousClass1 implements b.a {
        final /* synthetic */ IMFReqADInfoListener a;
        final /* synthetic */ IMFADDownloadListener b;
        final /* synthetic */ d c;

        AnonymousClass1(IMFReqADInfoListener iMFReqADInfoListener, IMFADDownloadListener iMFADDownloadListener, d dVar) {
            this.a = iMFReqADInfoListener;
            this.b = iMFADDownloadListener;
            this.c = dVar;
        }

        @Override // cn.missfresh.ad.a.b.a
        public void a(d dVar, String str) {
            AppMethodBeat.i(5715, false);
            a.a(a.this, dVar, str, this.a, this.b);
            AppMethodBeat.o(5715);
        }

        @Override // cn.missfresh.ad.a.b.a
        public void a(String str) {
            AppMethodBeat.i(5717, false);
            a.a(a.this, str, this.a, this.c, this.b);
            AppMethodBeat.o(5717);
        }
    }

    private void a(d dVar, String str, IMFReqADInfoListener iMFReqADInfoListener, IMFADDownloadListener iMFADDownloadListener) {
        AppMethodBeat.i(5752, false);
        if (iMFReqADInfoListener != null) {
            iMFReqADInfoListener.onReqADInfoFailed(dVar, str);
        }
        cn.missfresh.ad.b.a.a("MFADSDK", "loadSplashAD errorMsg: " + str);
        a("", iMFReqADInfoListener, dVar, iMFADDownloadListener);
        AppMethodBeat.o(5752);
    }

    private void a(String str, IMFReqADInfoListener iMFReqADInfoListener, d dVar, IMFADDownloadListener iMFADDownloadListener) {
        AppMethodBeat.i(5756, false);
        MFADBean mFADBean = null;
        try {
            cn.missfresh.ad.data.c cVar = (cn.missfresh.ad.data.c) JSON.parseObject(str, cn.missfresh.ad.data.c.class);
            if (!(cVar == null || cVar.a == null || cVar.a.isEmpty())) {
                if (cVar.a.get(0) != null) {
                    mFADBean = cVar.a.get(0).a();
                    cn.missfresh.ad.b.a.a("MFADSDK", "loadSplashAD onADReqSucceed: " + str);
                    if (mFADBean == null || !mFADBean.isSuccess()) {
                        if (iMFReqADInfoListener != null) {
                            iMFReqADInfoListener.onReqADInfoFailed(dVar, "\u6570\u636e\u5904\u7406\u5931\u8d25");
                        }
                        cn.missfresh.ad.b.a.a("MFADSDK", "loadSplashAD errorMsg: data is null");
                    } else {
                        if (iMFReqADInfoListener != null) {
                            iMFReqADInfoListener.onReqADInfoSucceed(mFADBean);
                        }
                        if (TextUtils.isEmpty(mFADBean.getImage())) {
                            this.c.a("");
                            cn.missfresh.ad.b.a.a("MFADSDK", "loadSplashAD onADReqSucceed: no AD\uff0c clear cache");
                            AppMethodBeat.o(5756);
                            return;
                        }
                        this.c.a(mFADBean.getImage(), mFADBean);
                        a(mFADBean, iMFADDownloadListener);
                    }
                    AppMethodBeat.o(5756);
                    return;
                }
            }
            cn.missfresh.ad.b.a.a("MFADSDK", "loadSplashAD data is null");
            if (iMFReqADInfoListener != null) {
                iMFReqADInfoListener.onReqADInfoFailed(dVar, "\u65e0\u5e7f\u544a\u6570\u636e");
            }
            AppMethodBeat.o(5756);
        } catch (Exception e) {
            iMFReqADInfoListener.onReqADInfoFailed(dVar, "\u6570\u636e\u89e3\u6790\u5931\u8d25\uff1a " + e.getMessage());
            cn.missfresh.ad.b.a.a("MFADSDK", "loadSplashAD errorMsg: parse exception");
        }
    }

    /* compiled from: MFADClientImpl */
    /* renamed from: cn.missfresh.ad.a$2  reason: invalid class name */
    class AnonymousClass2 implements b.a {
        final /* synthetic */ IMFReqADInfoListener a;
        final /* synthetic */ IMFADDownloadListener b;

        AnonymousClass2(IMFReqADInfoListener iMFReqADInfoListener, IMFADDownloadListener iMFADDownloadListener) {
            this.a = iMFReqADInfoListener;
            this.b = iMFADDownloadListener;
        }

        @Override // cn.missfresh.ad.a.b.a
        public void a(d dVar, String str) {
            AppMethodBeat.i(5724, false);
            a.a(a.this, dVar, str, this.a, this.b);
            AppMethodBeat.o(5724);
        }

        @Override // cn.missfresh.ad.a.b.a
        public void a(String str) {
            AppMethodBeat.i(5726, false);
            a.a(a.this, str, this.a, (d) null, this.b);
            AppMethodBeat.o(5726);
        }
    }

    @Override // cn.missfresh.ad.api.IMFADClient
    public void loadSplashADByCustom(IMFADLoader iMFADLoader, IMFReqADInfoListener iMFReqADInfoListener, IMFADDownloadListener iMFADDownloadListener) {
        AppMethodBeat.i(5761, false);
        if (iMFADLoader != null) {
            iMFADLoader.requestAdData(new AnonymousClass2(iMFReqADInfoListener, iMFADDownloadListener));
        }
        AppMethodBeat.o(5761);
    }

    @Override // cn.missfresh.ad.api.IMFADClient
    public String getCachedSplashADPath() {
        AppMethodBeat.i(5764, false);
        cn.missfresh.ad.b.a.a("MFADSDK", "getCachedSplashADPath getCachedSplashADPath");
        if (b()) {
            MFADDbManager mFADDbManager = this.c;
            if (mFADDbManager == null) {
                cn.missfresh.ad.b.a.a("MFADSDK", "MFADDbManager is null ");
                AppMethodBeat.o(5764);
                return null;
            }
            String a = mFADDbManager.a();
            if (!TextUtils.isEmpty(a) && !this.c.c() && new File(a).exists()) {
                AppMethodBeat.o(5764);
                return a;
            }
        }
        AppMethodBeat.o(5764);
        return null;
    }

    @Override // cn.missfresh.ad.api.IMFADClient
    public MFADBean getCachedMFADBean() {
        AppMethodBeat.i(5767, false);
        cn.missfresh.ad.b.a.a("MFADSDK", "getCachedMFADBean getCachedMFADBean");
        if (b()) {
            MFADDbManager mFADDbManager = this.c;
            if (mFADDbManager == null) {
                AppMethodBeat.o(5767);
                return null;
            }
            MFADBean d = mFADDbManager.d();
            AppMethodBeat.o(5767);
            return d;
        }
        AppMethodBeat.o(5767);
        return null;
    }

    private void a(MFADBean mFADBean, IMFADDownloadListener iMFADDownloadListener) {
        AppMethodBeat.i(5773, false);
        String c = this.b.c(mFADBean.getImage());
        File a = a(c);
        if (a != null) {
            iMFADDownloadListener.onDownloadSucceed(mFADBean, a);
            cn.missfresh.ad.b.a.a("MFADSDK", "loadSplashAD has cache");
            AppMethodBeat.o(5773);
            return;
        }
        a(mFADBean.getImage(), c, iMFADDownloadListener);
        AppMethodBeat.o(5773);
    }

    private File a(String str) {
        AppMethodBeat.i(5777, false);
        String b = this.b.b(str);
        String a = this.c.a();
        cn.missfresh.ad.b.a.a("MFADSDK", "Cache file gen : " + b + " Cache File origin: " + a);
        if (b.equalsIgnoreCase(a)) {
            File file = new File(b);
            if (!this.c.c() && file.exists()) {
                AppMethodBeat.o(5777);
                return file;
            }
        }
        AppMethodBeat.o(5777);
        return null;
    }

    private void a(String str, String str2, IMFADDownloadListener iMFADDownloadListener) {
        AppMethodBeat.i(5781, false);
        cn.missfresh.ad.b.a.a("MFADSDK", "exeDownLoad before");
        if (b()) {
            cn.missfresh.ad.b.a.a("MFADSDK", "exeDownLoad start");
            String a = this.b.a(str2);
            this.d.a(iMFADDownloadListener);
            this.d.a(str, a, str2);
        }
        AppMethodBeat.o(5781);
    }

    public boolean b() {
        AppMethodBeat.i(5785, false);
        if (!this.a) {
            cn.missfresh.ad.b.a.b("MFADSDK", "MFADSDKis not initialized");
        }
        boolean z = this.a;
        AppMethodBeat.o(5785);
        return z;
    }
}
