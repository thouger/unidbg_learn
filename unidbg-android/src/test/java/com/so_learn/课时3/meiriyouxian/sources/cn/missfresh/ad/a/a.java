package cn.missfresh.ad.a;

import cn.missfresh.ad.api.IMFADDownloadListener;
import cn.missfresh.ad.data.MFADBean;
import cn.missfresh.ad.data.MFADPathHelper;
import cn.missfresh.ad.data.db.MFADDbManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.imsdk.BaseConstants;
import java.io.File;

/* compiled from: MFADDownLoadManager */
public class a {
    private IMFADDownloadListener a;
    private MFADDbManager b;

    static /* synthetic */ void a(a aVar, String str, File file) {
        AppMethodBeat.i(6037, false);
        aVar.a(str, file);
        AppMethodBeat.o(6037);
    }

    public a(MFADDbManager mFADDbManager) {
        this.b = mFADDbManager;
    }

    /* compiled from: MFADDownLoadManager */
    /* renamed from: cn.missfresh.ad.a.a$1  reason: invalid class name */
    class AnonymousClass1 implements g {
        final /* synthetic */ String a;

        AnonymousClass1(String str) {
            this.a = str;
        }

        @Override // cn.missfresh.ad.a.g
        public void a() {
            AppMethodBeat.i(BaseConstants.ERR_INIT_CORE_FAIL, false);
            cn.missfresh.ad.b.a.a("MFADSDK", "onRequestStart");
            AppMethodBeat.o(BaseConstants.ERR_INIT_CORE_FAIL);
        }

        @Override // cn.missfresh.ad.a.g
        public void a(long j, long j2, boolean z) {
            AppMethodBeat.i(BaseConstants.ERR_DATABASE_OPERATE_FAILED, false);
            cn.missfresh.ad.b.a.a("MFADSDK", "onResponseProgress totalBytesRead : " + j + " contentLength :  " + j2);
            AppMethodBeat.o(BaseConstants.ERR_DATABASE_OPERATE_FAILED);
        }

        @Override // cn.missfresh.ad.a.g
        public void b() {
            AppMethodBeat.i(BaseConstants.ERR_INVALID_SDK_OBJECT, false);
            cn.missfresh.ad.b.a.a("MFADSDK", "onRequestPause");
            AppMethodBeat.o(BaseConstants.ERR_INVALID_SDK_OBJECT);
        }

        @Override // cn.missfresh.ad.a.g
        public void a(String str, File file) {
            AppMethodBeat.i(BaseConstants.ERR_LOGGED_OUT_BEFORE_LOGIN_FINISHED, false);
            cn.missfresh.ad.b.a.a("MFADSDK", "onRequestSucceed");
            File file2 = new File(file.getParentFile(), this.a);
            if (file.renameTo(file2)) {
                a.a(a.this, str, file2);
            } else {
                a("\u6587\u4ef6\u91cd\u547d\u540d\u5931\u8d25\uff01");
            }
            AppMethodBeat.o(BaseConstants.ERR_LOGGED_OUT_BEFORE_LOGIN_FINISHED);
        }

        @Override // cn.missfresh.ad.a.g
        public void a(String str) {
            AppMethodBeat.i(BaseConstants.ERR_TLSSDK_NOT_INITIALIZED, false);
            cn.missfresh.ad.b.a.a("MFADSDK", "onRequestFailed " + str);
            AppMethodBeat.o(BaseConstants.ERR_TLSSDK_NOT_INITIALIZED);
        }
    }

    public void a(String str, String str2, String str3) {
        AppMethodBeat.i(6030, false);
        b.a().a(str, str2, new AnonymousClass1(str3));
        AppMethodBeat.o(6030);
    }

    private void a(String str, File file) {
        AppMethodBeat.i(6033, false);
        if (MFADPathHelper.d(file.getAbsolutePath())) {
            cn.missfresh.ad.b.a.a("MFADSDK", "\u547d\u540d\u6210\u529f\uff0c\u4e0b\u8f7d\u597d\u7684\u6587\u4ef6\u540d\u4e3a: " + file.getAbsolutePath() + " url : " + str);
            this.b.b();
            this.b.a(file.getAbsolutePath());
            MFADBean b = this.b.b(str);
            this.b.a(b);
            IMFADDownloadListener iMFADDownloadListener = this.a;
            if (iMFADDownloadListener != null) {
                iMFADDownloadListener.onDownloadSucceed(b, file);
            }
        } else {
            cn.missfresh.ad.b.a.a("MFADSDK", "\u4e0b\u8f7d\u6210\u529f\uff0c\u91cd\u547d\u540d\u5931\u8d25");
            this.b.a("");
            this.b.c(str);
            this.b.b();
            IMFADDownloadListener iMFADDownloadListener2 = this.a;
            if (iMFADDownloadListener2 != null) {
                iMFADDownloadListener2.onDownLoadError("\u4e0b\u8f7d\u6210\u529f\uff0c\u91cd\u547d\u540d\u5931\u8d25");
            }
        }
        AppMethodBeat.o(6033);
    }

    public void a(IMFADDownloadListener iMFADDownloadListener) {
        this.a = iMFADDownloadListener;
    }
}
