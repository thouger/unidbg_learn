package com.vivo.push.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.model.InsideNotificationItem;

/* compiled from: DefaultNotifyDataAdapter */
public final class g implements BaseNotifyDataAdapter {
    private static int e;
    private static int f;
    private Resources a;
    private String b;
    private String c;
    private String d;

    private static boolean a(int i) {
        return (i == -1 || i == 0) ? false : true;
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final void init(Context context) {
        AppMethodBeat.i(1388, false);
        this.b = context.getPackageName();
        this.a = context.getResources();
        this.c = i.a();
        this.d = Build.VERSION.RELEASE;
        AppMethodBeat.o(1388);
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getDefaultNotifyIcon() {
        int i;
        AppMethodBeat.i(1391, false);
        if (a(e)) {
            int i2 = e;
            AppMethodBeat.o(1391);
            return i2;
        }
        String str = this.d;
        if (!a(str)) {
            i = -1;
        } else {
            i = a(str, "_notifyicon");
        }
        e = i;
        if (a(i)) {
            int i3 = e;
            AppMethodBeat.o(1391);
            return i3;
        }
        for (String str2 = this.c; !TextUtils.isEmpty(str2); str2 = str2.substring(0, str2.length() - 1)) {
            Resources resources = this.a;
            int identifier = resources.getIdentifier("vivo_push_rom" + str2 + "_notifyicon", "drawable", this.b);
            if (identifier > 0) {
                AppMethodBeat.o(1391);
                return identifier;
            }
        }
        int identifier2 = this.a.getIdentifier("vivo_push_notifyicon", "drawable", this.b);
        AppMethodBeat.o(1391);
        return identifier2;
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getDefaultSmallIconId() {
        int i;
        AppMethodBeat.i(1393, false);
        if (a(f)) {
            int i2 = f;
            AppMethodBeat.o(1393);
            return i2;
        }
        String str = this.d;
        if (!a(str)) {
            i = -1;
        } else {
            i = a(str, "_icon");
        }
        f = i;
        if (a(i)) {
            int i3 = f;
            AppMethodBeat.o(1393);
            return i3;
        }
        for (String str2 = this.c; !TextUtils.isEmpty(str2); str2 = str2.substring(0, str2.length() - 1)) {
            Resources resources = this.a;
            int identifier = resources.getIdentifier("vivo_push_rom" + str2 + "_icon", "drawable", this.b);
            if (identifier > 0) {
                AppMethodBeat.o(1393);
                return identifier;
            }
        }
        int identifier2 = this.a.getIdentifier("vivo_push_icon", "drawable", this.b);
        AppMethodBeat.o(1393);
        return identifier2;
    }

    private static boolean a(String str) {
        AppMethodBeat.i(1399, false);
        if (Build.VERSION.SDK_INT < 26) {
            AppMethodBeat.o(1399);
            return false;
        } else if (TextUtils.isEmpty(str)) {
            n.d("DefaultNotifyDataAdapter", "systemVersion is not suit ");
            AppMethodBeat.o(1399);
            return false;
        } else {
            AppMethodBeat.o(1399);
            return true;
        }
    }

    private int a(String str, String str2) {
        AppMethodBeat.i(1403, false);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            AppMethodBeat.o(1403);
            return -1;
        }
        String[] split = str.split("\\.");
        if (split != null && split.length > 0) {
            str = split[0];
        }
        try {
            for (int parseInt = Integer.parseInt(str); parseInt > 0; parseInt--) {
                String str3 = "vivo_push_ard" + parseInt + str2;
                n.c("DefaultNotifyDataAdapter", "get notify icon : " + str3);
                int identifier = this.a.getIdentifier(str3, "drawable", this.b);
                if (identifier > 0) {
                    n.c("DefaultNotifyDataAdapter", "find notify icon : " + str3);
                    AppMethodBeat.o(1403);
                    return identifier;
                }
            }
        } catch (Exception e2) {
            n.a("DefaultNotifyDataAdapter", e2);
        }
        AppMethodBeat.o(1403);
        return -1;
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getNotifyMode(InsideNotificationItem insideNotificationItem) {
        return Build.VERSION.SDK_INT >= 21 ? 2 : 1;
    }
}
