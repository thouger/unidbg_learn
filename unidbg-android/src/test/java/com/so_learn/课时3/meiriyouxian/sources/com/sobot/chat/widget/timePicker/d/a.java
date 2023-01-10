package com.sobot.chat.widget.timePicker.d;

import android.content.Context;
import com.sobot.chat.utils.q;

/* compiled from: SobotPickerViewAnimateUtil */
public class a {
    public static int a(Context context, int i, boolean z) {
        if (i != 80) {
            return -1;
        }
        return q.a(context, "anim", z ? "sobot_pickerview_slide_in_bottom" : "sobot_pickerview_slide_out_bottom");
    }
}
