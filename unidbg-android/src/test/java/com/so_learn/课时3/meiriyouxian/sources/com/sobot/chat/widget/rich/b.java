package com.sobot.chat.widget.rich;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.sobot.chat.listener.a;
import com.sobot.chat.listener.c;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.y;

/* compiled from: PhoneSpan */
public class b extends ClickableSpan {
    private String a;
    private int b;
    private Context c;

    public b(Context context, String str, int i) {
        this.a = str;
        this.b = context.getResources().getColor(i);
        this.c = context;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        if (y.a != null) {
            a aVar = y.a;
            aVar.c("tel:" + this.a);
            return;
        }
        if (y.b != null) {
            c cVar = y.b;
            if (cVar.c("tel:" + this.a)) {
                return;
            }
        }
        d.a(this.a, this.c);
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(this.b);
        textPaint.setUnderlineText(false);
    }
}
