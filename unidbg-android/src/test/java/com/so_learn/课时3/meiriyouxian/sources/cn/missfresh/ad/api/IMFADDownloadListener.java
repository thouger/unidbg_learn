package cn.missfresh.ad.api;

import cn.missfresh.ad.data.MFADBean;
import java.io.File;

public interface IMFADDownloadListener {
    void onDownLoadError(String str);

    void onDownloadSucceed(MFADBean mFADBean, File file);
}
