package com.sobot.chat.utils;

import android.content.Context;
import android.media.TtmlUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/* compiled from: CustomToast */
public class e {
    public static Toast a(Context context, CharSequence charSequence, int i) {
        Toast toast = new Toast(context.getApplicationContext());
        View inflate = View.inflate(context, q.a(context, TtmlUtils.TAG_LAYOUT, "sobot_custom_toast_layout_2"), null);
        ((TextView) inflate.findViewById(q.a(context, "id", "sobot_tv_content"))).setText(charSequence);
        toast.setView(inflate);
        toast.setGravity(17, 0, 0);
        toast.setDuration(i);
        return toast;
    }

    public static Toast a(Context context, CharSequence charSequence, int i, int i2) {
        Toast toast = new Toast(context.getApplicationContext());
        View inflate = View.inflate(context, q.a(context, TtmlUtils.TAG_LAYOUT, "sobot_custom_toast_layout"), null);
        ((TextView) inflate.findViewById(q.a(context, "id", "sobot_tv_content"))).setText(charSequence);
        ((ImageView) inflate.findViewById(q.a(context, "id", "sobot_iv_content"))).setImageResource(i2);
        toast.setView(inflate);
        toast.setGravity(17, 0, 0);
        toast.setDuration(i);
        return toast;
    }
}
