package cn.missfresh.ad.api;

import android.content.Context;
import cn.missfresh.ad.a.d;
import cn.missfresh.ad.data.MFADBean;

public interface IMFADClient {

    public interface ISplashADListener {
        void onReqADInfoFailed(d dVar, String str);

        void onReqADInfoSucceed(MFADBean mFADBean);
    }

    MFADBean getCachedMFADBean();

    String getCachedSplashADPath();

    void loadSplashAD(d dVar, IMFReqADInfoListener iMFReqADInfoListener, IMFADDownloadListener iMFADDownloadListener);

    void loadSplashADByCustom(IMFADLoader iMFADLoader, IMFReqADInfoListener iMFReqADInfoListener, IMFADDownloadListener iMFADDownloadListener);

    void requestPermissionIfNecessary(Context context);
}
