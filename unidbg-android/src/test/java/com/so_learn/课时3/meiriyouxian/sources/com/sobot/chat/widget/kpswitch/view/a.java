package com.sobot.chat.widget.kpswitch.view;

import android.content.Context;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.q;

/* compiled from: CustomeViewFactory */
public class a {
    public static BaseChattingPanelView a(Context context, int i) {
        m.d("BaseChattingPanelView");
        if (i != 0) {
            if (i == q.a(context, "id", "sobot_btn_upload_view")) {
                return new ChattingPanelUploadView(context);
            }
            if (i == q.a(context, "id", "sobot_btn_emoticon_view")) {
                return new ChattingPanelEmoticonView(context);
            }
        }
        return null;
    }

    public static String b(Context context, int i) {
        if (i != 0) {
            if (i == q.a(context, "id", "sobot_btn_upload_view")) {
                return "ChattingPanelUploadView";
            }
            if (i == q.a(context, "id", "sobot_btn_emoticon_view")) {
                return "ChattingPanelEmoticonView";
            }
        }
        return null;
    }
}
