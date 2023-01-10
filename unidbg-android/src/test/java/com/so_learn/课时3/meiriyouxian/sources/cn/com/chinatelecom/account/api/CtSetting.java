package cn.com.chinatelecom.account.api;

import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;

public class CtSetting {
    private static final int DEFAULT_CONN_TIMEOUT = 3000;
    private static final int DEFAULT_READ_TIMEOUT = 3000;
    private static final int DEFAULT_TOTAL_TIMEOUT = 10000;
    private int connTimeout = 0;
    private int readTimeout = 0;
    private int totalTimeout = 0;

    public CtSetting() {
    }

    public CtSetting(int i, int i2, int i3) {
        this.connTimeout = i;
        this.readTimeout = i2;
        this.totalTimeout = i3;
    }

    public static int getConnTimeout(CtSetting ctSetting) {
        int i;
        return (ctSetting == null || (i = ctSetting.connTimeout) <= 0) ? PathInterpolatorCompat.MAX_NUM_POINTS : i;
    }

    public static int getReadTimeout(CtSetting ctSetting) {
        int i;
        return (ctSetting == null || (i = ctSetting.readTimeout) <= 0) ? PathInterpolatorCompat.MAX_NUM_POINTS : i;
    }

    public static int getTotalTimeout(CtSetting ctSetting) {
        int i;
        if (ctSetting == null || (i = ctSetting.totalTimeout) <= 0) {
            return 10000;
        }
        return i;
    }

    public void setConnTimeout(int i) {
        this.connTimeout = i;
    }

    public void setReadTimeout(int i) {
        this.readTimeout = i;
    }

    public void setTotalTimeout(int i) {
        this.totalTimeout = i;
    }
}
