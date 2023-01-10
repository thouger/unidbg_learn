package com.sobot.chat.widget.rich;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;
import com.sobot.chat.activity.WebViewActivity;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.y;

public class MyURLSpan extends URLSpan {
    private Context a;
    private int b;
    private boolean c;

    public MyURLSpan(Context context, String str, int i, boolean z) {
        super(str);
        this.a = context;
        this.b = context.getResources().getColor(i);
        this.c = z;
    }

    @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
    public void onClick(View view) {
        String url = getURL();
        if (url.startsWith("sobot:")) {
            if ("sobot:SobotPostMsgActivity".equals(url)) {
                Intent intent = new Intent();
                intent.setAction(ZhiChiConstants.chat_remind_post_msg);
                d.a(this.a, intent);
            } else if ("sobot:SobotTicketInfo".equals(url)) {
                Intent intent2 = new Intent();
                intent2.putExtra("isShowTicket", true);
                intent2.setAction(ZhiChiConstants.chat_remind_post_msg);
                d.a(this.a, intent2);
            }
        } else if (url.endsWith(".doc") || url.endsWith(".docx") || url.endsWith(".xls") || url.endsWith(".txt") || url.endsWith(".ppt") || url.endsWith(".pptx") || url.endsWith(".xlsx") || url.endsWith(".pdf") || url.endsWith(".rar") || url.endsWith(".zip")) {
            if (y.a != null) {
                y.a.a(url);
            } else if (y.b == null || !y.b.a(url)) {
                String a = a(url);
                Intent intent3 = new Intent();
                intent3.setAction("android.intent.action.VIEW");
                intent3.addFlags(268435456);
                intent3.setData(Uri.parse(a));
                this.a.startActivity(intent3);
            }
        } else if (url.startsWith("tel:")) {
            if (y.a != null) {
                y.a.c(url);
            } else if (y.b == null || !y.b.c(url)) {
                Intent intent4 = new Intent();
                intent4.setAction("android.intent.action.VIEW");
                intent4.addFlags(268435456);
                intent4.setData(Uri.parse(url));
                this.a.startActivity(intent4);
            }
        } else if (y.a != null) {
            y.a.a(url);
        } else if (y.b == null || !y.b.a(url)) {
            String a2 = a(url);
            Intent intent5 = new Intent(this.a, WebViewActivity.class);
            intent5.putExtra("url", a2);
            intent5.addFlags(268435456);
            this.a.startActivity(intent5);
        }
    }

    private String a(String str) {
        if (str.startsWith("http://") || str.startsWith("https://")) {
            return str;
        }
        String str2 = "https://" + str;
        m.d("url:" + str2);
        return str2;
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(this.b);
        textPaint.setUnderlineText(this.c);
    }
}
