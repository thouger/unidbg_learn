package cn.missfresh.player;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.util.TimedRemoteCaller;
import android.view.Window;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.Formatter;
import java.util.Locale;

public class MediaUtils {
    public static int SYSTEM_UI = 0;
    public static final String TAG = "MediaUtils";

    public static String stringForTime(long j) {
        AppMethodBeat.i(7619, false);
        if (j <= 0 || j >= 86400000) {
            AppMethodBeat.o(7619);
            return "00:00";
        }
        long j2 = j / 1000;
        int i = (int) (j2 % 60);
        int i2 = (int) ((j2 / 60) % 60);
        int i3 = (int) (j2 / 3600);
        Formatter formatter = new Formatter(new StringBuilder(), Locale.getDefault());
        if (i3 > 0) {
            String formatter2 = formatter.format("%d:%02d:%02d", Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(i)).toString();
            AppMethodBeat.o(7619);
            return formatter2;
        }
        String formatter3 = formatter.format("%02d:%02d", Integer.valueOf(i2), Integer.valueOf(i)).toString();
        AppMethodBeat.o(7619);
        return formatter3;
    }

    public static boolean isWifiConnected(Context context) {
        boolean z = false;
        AppMethodBeat.i(7621, false);
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
            z = true;
        }
        AppMethodBeat.o(7621);
        return z;
    }

    public static Activity scanForActivity(Context context) {
        AppMethodBeat.i(7623, false);
        if (context == null) {
            AppMethodBeat.o(7623);
            return null;
        } else if (context instanceof Activity) {
            Activity activity = (Activity) context;
            AppMethodBeat.o(7623);
            return activity;
        } else if (context instanceof ContextWrapper) {
            Activity scanForActivity = scanForActivity(((ContextWrapper) context).getBaseContext());
            AppMethodBeat.o(7623);
            return scanForActivity;
        } else {
            AppMethodBeat.o(7623);
            return null;
        }
    }

    public static void setRequestedOrientation(Context context, int i) {
        AppMethodBeat.i(7626, false);
        if (scanForActivity(context) != null) {
            scanForActivity(context).setRequestedOrientation(i);
        } else {
            scanForActivity(context).setRequestedOrientation(i);
        }
        AppMethodBeat.o(7626);
    }

    public static Window getWindow(Context context) {
        AppMethodBeat.i(7627, false);
        if (scanForActivity(context) != null) {
            Window window = scanForActivity(context).getWindow();
            AppMethodBeat.o(7627);
            return window;
        }
        Window window2 = scanForActivity(context).getWindow();
        AppMethodBeat.o(7627);
        return window2;
    }

    public static int dip2px(Context context, float f) {
        AppMethodBeat.i(7628, false);
        int i = (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
        AppMethodBeat.o(7628);
        return i;
    }

    public static void saveProgress(Context context, Object obj, long j) {
        AppMethodBeat.i(7631, false);
        if (!VideoLayout.SAVE_PROGRESS) {
            AppMethodBeat.o(7631);
            return;
        }
        Log.i(TAG, "saveProgress: " + j);
        if (j < TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS) {
            j = 0;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("JZVD_PROGRESS", 0).edit();
        edit.putLong("newVersion:" + obj.toString(), j).apply();
        AppMethodBeat.o(7631);
    }

    public static long getSavedProgress(Context context, Object obj) {
        AppMethodBeat.i(7634, false);
        if (!VideoLayout.SAVE_PROGRESS) {
            AppMethodBeat.o(7634);
            return 0;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("JZVD_PROGRESS", 0);
        long j = sharedPreferences.getLong("newVersion:" + obj.toString(), 0);
        AppMethodBeat.o(7634);
        return j;
    }

    public static void clearSavedProgress(Context context, Object obj) {
        AppMethodBeat.i(7636, false);
        if (obj == null) {
            context.getSharedPreferences("JZVD_PROGRESS", 0).edit().clear().apply();
        } else {
            SharedPreferences.Editor edit = context.getSharedPreferences("JZVD_PROGRESS", 0).edit();
            edit.putLong("newVersion:" + obj.toString(), 0).apply();
        }
        AppMethodBeat.o(7636);
    }

    public static void showStatusBar(Context context) {
        AppMethodBeat.i(7638, false);
        if (VideoLayout.TOOL_BAR_EXIST) {
            getWindow(context).clearFlags(1024);
        }
        AppMethodBeat.o(7638);
    }

    public static void hideStatusBar(Context context) {
        AppMethodBeat.i(7639, false);
        if (VideoLayout.TOOL_BAR_EXIST) {
            getWindow(context).setFlags(1024, 1024);
        }
        AppMethodBeat.o(7639);
    }

    public static void hideSystemUI(Context context) {
        AppMethodBeat.i(7642, false);
        int i = Build.VERSION.SDK_INT >= 19 ? 5638 : 1542;
        SYSTEM_UI = getWindow(context).getDecorView().getSystemUiVisibility();
        getWindow(context).getDecorView().setSystemUiVisibility(i);
        AppMethodBeat.o(7642);
    }

    public static void showSystemUI(Context context) {
        AppMethodBeat.i(7643, false);
        getWindow(context).getDecorView().setSystemUiVisibility(SYSTEM_UI);
        AppMethodBeat.o(7643);
    }
}
