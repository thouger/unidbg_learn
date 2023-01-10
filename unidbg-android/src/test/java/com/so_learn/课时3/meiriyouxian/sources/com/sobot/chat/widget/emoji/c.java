package com.sobot.chat.widget.emoji;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.widget.EditText;
import com.sobot.chat.utils.q;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: InputHelper */
public class c {
    public static void a(EditText editText) {
        if (editText != null) {
            KeyEvent keyEvent = new KeyEvent(0, 67);
            KeyEvent keyEvent2 = new KeyEvent(1, 67);
            editText.onKeyDown(67, keyEvent);
            editText.onKeyUp(67, keyEvent2);
        }
    }

    public static int a(Context context, String str) {
        Integer num;
        Map<String, Integer> mapAll = DisplayRules.getMapAll(context);
        if (mapAll.size() <= 0 || (num = mapAll.get(str)) == null) {
            return -1;
        }
        return num.intValue();
    }

    public static Spannable a(Context context, CharSequence charSequence) {
        Spannable spannable;
        String charSequence2 = charSequence.toString();
        if (charSequence instanceof Spannable) {
            spannable = (Spannable) charSequence;
        } else {
            spannable = new SpannableString(charSequence2);
        }
        Resources resources = context.getResources();
        int dimension = (int) resources.getDimension(q.a(context, "dimen", "sobot_text_font_large"));
        Matcher matcher = Pattern.compile("\\[[^\\]^\\[]+\\]").matcher(spannable);
        while (matcher.find()) {
            matcher.group();
            try {
                int a = a(context, matcher.group());
                if (a > 0) {
                    Drawable drawable = resources.getDrawable(a);
                    drawable.setBounds(0, 0, dimension, dimension + 0);
                    spannable.setSpan(new ImageSpan(drawable, 0), matcher.start(), matcher.end(), 17);
                }
            } catch (Exception unused) {
            }
        }
        return spannable;
    }

    public static void a(EditText editText, b bVar) {
        if (editText != null && bVar != null) {
            int selectionStart = editText.getSelectionStart();
            int selectionEnd = editText.getSelectionEnd();
            if (selectionStart < 0) {
                editText.append(bVar.a());
                return;
            }
            String a = bVar.a();
            editText.getText().replace(Math.min(selectionStart, selectionEnd), Math.max(selectionStart, selectionEnd), a, 0, a.length());
        }
    }
}
