package com.vivo.push.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.TtmlUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: DefaultNotifyLayoutAdapter */
public final class h implements BaseNotifyLayoutAdapter {
    private Resources a;
    private String b;

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final void init(Context context) {
        AppMethodBeat.i(1415, false);
        this.b = context.getPackageName();
        this.a = context.getResources();
        AppMethodBeat.o(1415);
    }

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final int getNotificationLayout() {
        AppMethodBeat.i(1419, false);
        int identifier = this.a.getIdentifier("push_notify", TtmlUtils.TAG_LAYOUT, this.b);
        AppMethodBeat.o(1419);
        return identifier;
    }

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final int getSuitIconId() {
        Resources resources;
        String str;
        String str2;
        AppMethodBeat.i(1420, false);
        if (i.g) {
            resources = this.a;
            str = this.b;
            str2 = "notify_icon_rom30";
        } else if (i.f) {
            resources = this.a;
            str = this.b;
            str2 = "notify_icon_rom20";
        } else {
            resources = this.a;
            str = this.b;
            str2 = "notify_icon";
        }
        int identifier = resources.getIdentifier(str2, "id", str);
        AppMethodBeat.o(1420);
        return identifier;
    }

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final int getTitleColor() {
        int i = 0;
        AppMethodBeat.i(1425, false);
        try {
            i = ((Integer) y.a("com.android.internal.R$color", "vivo_notification_title_text_color")).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i > 0) {
            int color = this.a.getColor(i);
            AppMethodBeat.o(1425);
            return color;
        } else if (i.g) {
            AppMethodBeat.o(1425);
            return -1;
        } else if (!i.f) {
            AppMethodBeat.o(1425);
            return -16777216;
        } else if (i.g) {
            int parseColor = Color.parseColor("#ff999999");
            AppMethodBeat.o(1425);
            return parseColor;
        } else {
            AppMethodBeat.o(1425);
            return -1;
        }
    }
}
