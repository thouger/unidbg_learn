package com.sobot.chat.widget.attachment;

import android.content.Context;
import android.text.TextUtils;
import com.sobot.chat.utils.q;

/* compiled from: FileTypeConfig */
public class a {
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static int a(String str) {
        char c;
        if (TextUtils.isEmpty(str)) {
            return 10;
        }
        switch (str.hashCode()) {
            case 99640:
                if (str.equals("doc")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 102340:
                if (str.equals("gif")) {
                    c = 14;
                    break;
                }
                c = '\uffff';
                break;
            case 105441:
                if (str.equals("jpg")) {
                    c = '\f';
                    break;
                }
                c = '\uffff';
                break;
            case 108272:
                if (str.equals("mp3")) {
                    c = '\t';
                    break;
                }
                c = '\uffff';
                break;
            case 108273:
                if (str.equals("mp4")) {
                    c = '\n';
                    break;
                }
                c = '\uffff';
                break;
            case 110834:
                if (str.equals("pdf")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 111145:
                if (str.equals("png")) {
                    c = '\r';
                    break;
                }
                c = '\uffff';
                break;
            case 111220:
                if (str.equals("ppt")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case 112675:
                if (str.equals("rar")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case 115312:
                if (str.equals("txt")) {
                    c = 11;
                    break;
                }
                c = '\uffff';
                break;
            case 118783:
                if (str.equals("xls")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 120609:
                if (str.equals("zip")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 3088960:
                if (str.equals("docx")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 3447940:
                if (str.equals("pptx")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 3682393:
                if (str.equals("xlsx")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
            case 1:
                return 19;
            case 2:
            case 3:
                return 13;
            case 4:
            case 5:
                return 14;
            case 6:
            case 7:
                return 15;
            case '\b':
                return 16;
            case '\t':
                return 17;
            case '\n':
                return 18;
            case 11:
                return 20;
            case '\f':
            case '\r':
            case 14:
                return 1;
            default:
                return 10;
        }
    }

    public static int a(Context context, int i) {
        if (context == null) {
            return 0;
        }
        if (i == 10) {
            return q.a(context, "drawable", "sobot_icon_file_unknow");
        }
        switch (i) {
            case 13:
                return q.a(context, "drawable", "sobot_icon_file_doc");
            case 14:
                return q.a(context, "drawable", "sobot_icon_file_ppt");
            case 15:
                return q.a(context, "drawable", "sobot_icon_file_xls");
            case 16:
                return q.a(context, "drawable", "sobot_icon_file_pdf");
            case 17:
                return q.a(context, "drawable", "sobot_icon_file_mp3");
            case 18:
                return q.a(context, "drawable", "sobot_icon_file_mp4");
            case 19:
                return q.a(context, "drawable", "sobot_icon_file_rar");
            case 20:
                return q.a(context, "drawable", "sobot_icon_file_txt");
            default:
                return q.a(context, "drawable", "sobot_icon_file_unknow");
        }
    }
}
