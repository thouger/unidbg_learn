package cn.missfresh.sherlock;

import android.text.format.DateUtils;

/* compiled from: Constants */
public interface b {
    public static final Long a = Long.valueOf((long) DateUtils.WEEK_IN_MILLIS);

    /* compiled from: Constants */
    public interface a {
        public static final String[] a = {"_data", "datetaken"};
        public static final String[] b = {"_data", "datetaken", "width", "height"};
        public static final String[] c = {"screenshot", "screen_shot", "screen-shot", "screen shot", "screencapture", "screen_capture", "screen-capture", "screen capture", "screencap", "screen_cap", "screen-cap", "screen cap"};
    }

    static {
        Long.valueOf(86400000);
    }
}
