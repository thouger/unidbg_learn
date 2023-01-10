package cn.missfresh.utils;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.hardware.camera2.legacy.LegacyCameraDevice;
import android.util.DisplayMetrics;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: MFScreenUtils */
public class d {
    public static int a(Context context) {
        AppMethodBeat.i(13840, false);
        if (context != null) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics.heightPixels > 0) {
                int i = displayMetrics.heightPixels;
                AppMethodBeat.o(13840);
                return i;
            }
            int a = f.a(context, LegacyCameraDevice.MAX_DIMEN_FOR_ROUNDING);
            AppMethodBeat.o(13840);
            return a;
        }
        NullPointerException nullPointerException = new NullPointerException("context is null");
        AppMethodBeat.o(13840);
        throw nullPointerException;
    }

    public static int b(Context context) {
        AppMethodBeat.i(13848, false);
        if (context != null) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics.widthPixels > 0) {
                int i = displayMetrics.widthPixels;
                AppMethodBeat.o(13848);
                return i;
            }
            int a = f.a(context, BluetoothClass.Device.AUDIO_VIDEO_VIDEO_MONITOR);
            AppMethodBeat.o(13848);
            return a;
        }
        NullPointerException nullPointerException = new NullPointerException("context is null");
        AppMethodBeat.o(13848);
        throw nullPointerException;
    }

    public static int c(Context context) {
        int i = 0;
        AppMethodBeat.i(13856, false);
        if (context != null) {
            try {
                int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (identifier > 0) {
                    i = context.getResources().getDimensionPixelSize(identifier);
                }
            } catch (Exception e) {
                cn.missfresh.utils.a.d.a("MFScreenUtils", e);
            }
            AppMethodBeat.o(13856);
            return i;
        }
        NullPointerException nullPointerException = new NullPointerException("context is null");
        AppMethodBeat.o(13856);
        throw nullPointerException;
    }
}
