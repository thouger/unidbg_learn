package com.sobot.chat.widget.rich;

import android.app.Activity;
import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.core.app.ShareCompat;
import com.sobot.chat.utils.y;

/* compiled from: EmailSpan */
public class a extends ClickableSpan {
    private String a;
    private int b;

    public a(Context context, String str, int i) {
        this.a = str;
        this.b = context.getResources().getColor(i);
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        if (y.a != null) {
            y.a.b(this.a);
        } else if (y.b == null || !y.b.b(this.a)) {
            try {
                ShareCompat.IntentBuilder from = ShareCompat.IntentBuilder.from((Activity) view.getContext());
                from.setType("message/rfc822");
                from.addEmailTo(this.a);
                from.setSubject("");
                from.setChooserTitle("");
                from.startChooser();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(this.b);
        textPaint.setUnderlineText(false);
    }
}
