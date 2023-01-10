package com.sobot.chat.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.widget.TextView;
import com.sobot.chat.core.a;
import com.sobot.chat.widget.emoji.c;
import com.sobot.chat.widget.rich.MyURLSpan;
import com.sobot.chat.widget.rich.b;
import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: HtmlTools */
public class j {
    public static Pattern a = Pattern.compile("(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
    public static final Pattern b = Pattern.compile("(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?");
    public static final Pattern c = Pattern.compile("((?:(http|https|Http|Https|rtsp|Rtsp):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?((?:(?:[a-zA-Z0-9\u00a0-\ud7ff\uf900-\ufdcf\ufdf0-\uffef][a-zA-Z0-9\u00a0-\ud7ff\uf900-\ufdcf\ufdf0-\uffef\\-]{0,64}\\.)+(?:(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])|(?:biz|b[abdefghijmnorstvwyz])|(?:cat|com|coop|c[acdfghiklmnoruvxyz])|d[ejkmoz]|(?:edu|e[cegrstu])|f[ijkmor]|(?:gov|g[abdefghilmnpqrstuwy])|h[kmnrtu]|(?:info|int|i[delmnoqrst])|(?:jobs|j[emop])|k[eghimnprwyz]|l[abcikrstuvy]|(?:mil|mobi|museum|m[acdeghklmnopqrstuvwxyz])|(?:name|net|n[acefgilopruz])|(?:org|om)|(?:pro|p[aefghklmnrstwy])|qa|r[eosuw]|s[abcdeghijklmnortuvyz]|(?:tel|travel|t[cdfghjklmnoprtvwz])|u[agksyz]|v[aceginu]|w[fs]|(?:\u03b4\u03bf\u03ba\u03b9\u03bc\u03ae|\u0438\u0441\u043f\u044b\u0442\u0430\u043d\u0438\u0435|\u0440\u0444|\u0441\u0440\u0431|\u05d8\u05e2\u05e1\u05d8|\u0622\u0632\u0645\u0627\u06cc\u0634\u06cc|\u0625\u062e\u062a\u0628\u0627\u0631|\u0627\u0644\u0627\u0631\u062f\u0646|\u0627\u0644\u062c\u0632\u0627\u0626\u0631|\u0627\u0644\u0633\u0639\u0648\u062f\u064a\u0629|\u0627\u0644\u0645\u063a\u0631\u0628|\u0627\u0645\u0627\u0631\u0627\u062a|\u0628\u06be\u0627\u0631\u062a|\u062a\u0648\u0646\u0633|\u0633\u0648\u0631\u064a\u0629|\u0641\u0644\u0633\u0637\u064a\u0646|\u0642\u0637\u0631|\u0645\u0635\u0631|\u092a\u0930\u0940\u0915\u094d\u0937\u093e|\u092d\u093e\u0930\u0924|\u09ad\u09be\u09b0\u09a4|\u0a2d\u0a3e\u0a30\u0a24|\u0aad\u0abe\u0ab0\u0aa4|\u0b87\u0ba8\u0bcd\u0ba4\u0bbf\u0baf\u0bbe|\u0b87\u0bb2\u0b99\u0bcd\u0b95\u0bc8|\u0b9a\u0bbf\u0b99\u0bcd\u0b95\u0baa\u0bcd\u0baa\u0bc2\u0bb0\u0bcd|\u0baa\u0bb0\u0bbf\u0b9f\u0bcd\u0b9a\u0bc8|\u0c2d\u0c3e\u0c30\u0c24\u0c4d|\u0dbd\u0d82\u0d9a\u0dcf|\u0e44\u0e17\u0e22|\u30c6\u30b9\u30c8|\u4e2d\u56fd|\u4e2d\u570b|\u53f0\u6e7e|\u53f0\u7063|\u65b0\u52a0\u5761|\u6d4b\u8bd5|\u6e2c\u8a66|\u9999\u6e2f|\ud14c\uc2a4\ud2b8|\ud55c\uad6d|xn\\-\\-0zwm56d|xn\\-\\-11b5bs3a9aj6g|xn\\-\\-3e0b707e|xn\\-\\-45brj9c|xn\\-\\-80akhbyknj4f|xn\\-\\-90a3ac|xn\\-\\-9t4b11yi5a|xn\\-\\-clchc0ea0b2g2a9gcd|xn\\-\\-deba0ad|xn\\-\\-fiqs8s|xn\\-\\-fiqz9s|xn\\-\\-fpcrj9c3d|xn\\-\\-fzc2c9e2c|xn\\-\\-g6w251d|xn\\-\\-gecrj9c|xn\\-\\-h2brj9c|xn\\-\\-hgbk6aj7f53bba|xn\\-\\-hlcj6aya9esc7a|xn\\-\\-j6w193g|xn\\-\\-jxalpdlp|xn\\-\\-kgbechtv|xn\\-\\-kprw13d|xn\\-\\-kpry57d|xn\\-\\-lgbbat1ad8j|xn\\-\\-mgbaam7a8h|xn\\-\\-mgbayh7gpa|xn\\-\\-mgbbh1a71e|xn\\-\\-mgbc0a9azcg|xn\\-\\-mgberp4a5d4ar|xn\\-\\-o3cw4h|xn\\-\\-ogbpf8fl|xn\\-\\-p1ai|xn\\-\\-pgbs0dh|xn\\-\\-s9brj9c|xn\\-\\-wgbh1c|xn\\-\\-wgbl6a|xn\\-\\-xkc2al3hye2a|xn\\-\\-xkc2dl3a5ee0h|xn\\-\\-yfro4i67o|xn\\-\\-ygbi2ammx|xn\\-\\-zckzah|xxx)|y[et]|z[amw]))|(?:(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])))(?:\\:\\d{1,5})?)(\\/(?:(?:[a-zA-Z0-9\u00a0-\ud7ff\uf900-\ufdcf\ufdf0-\uffef\\;\\/\\?\\:\\@\\&\\=\\#\\~\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?(?:\\b|$)");
    public static final Pattern d = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+");
    public static Pattern e = Pattern.compile("\\d{3}-\\d{8}|\\d{3}-\\d{7}|\\d{4}-\\d{8}|\\d{4}-\\d{7}|1+[34578]+\\d{9}|\\+\\d{2}1+[34578]+\\d{9}|400\\d{7}|400-\\d{3}-\\d{4}|\\d{12}|\\d{11}|\\d{10}|\\d{8}|\\d{7}");
    public static final Pattern f = Pattern.compile("\\[(([\u4e00-\u9fa5]+)|([a-zA-z]+))\\]");
    public static final Pattern g = Pattern.compile("\\[[(0-9)]+\\]");
    private static j h;
    private String i = "/sdcard/Record/";
    private Context j;

    public static j a(Context context) {
        if (h == null) {
            h = new j(context.getApplicationContext());
        }
        return h;
    }

    public static Pattern a() {
        return e;
    }

    public static Pattern b() {
        return a;
    }

    private j(Context context) {
        this.j = context.getApplicationContext();
    }

    public void a(TextView textView, String str, String str2, String str3, int i) {
        a.a().a(str, new File(str3), (Map<String, String>) null, new AnonymousClass1(textView, str2, i));
    }

    /* compiled from: HtmlTools */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.utils.j$1  reason: invalid class name */
    public class AnonymousClass1 implements a.AbstractC0142a {
        final /* synthetic */ TextView a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;

        AnonymousClass1(TextView textView, String str, int i) {
            this.a = textView;
            this.b = str;
            this.c = i;
        }

        @Override // com.sobot.chat.core.a.AbstractC0142a
        public void a(File file) {
            j.this.a(this.a, this.b, this.c);
        }

        @Override // com.sobot.chat.core.a.AbstractC0142a
        public void a(Exception exc, String str, int i) {
            m.a(" \u6587\u672c\u56fe\u7247\u7684\u4e0b\u8f7d\u5931\u8d25", exc);
        }

        @Override // com.sobot.chat.core.a.AbstractC0142a
        public void a(int i) {
            m.d(" \u6587\u672c\u56fe\u7247\u7684\u4e0b\u8f7d\u8fdb\u5ea6" + i);
        }
    }

    public void a(TextView textView, String str, int i, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            while (!TextUtils.isEmpty(str) && str.length() > 5 && "<br/>".equals(str.substring(0, 5))) {
                str = str.substring(5, str.length());
            }
            if (!TextUtils.isEmpty(str) && str.length() > 5 && "<br/>".equals(str.substring(str.length() - 5, str.length()))) {
                str = str.substring(0, str.length() - 5);
            }
            textView.setMovementMethod(com.sobot.chat.widget.a.a());
            textView.setFocusable(false);
            a(this.j, textView, c.a(this.j.getApplicationContext(), b(textView, str.replace("\n", "<br />"), i)), i, z);
        }
    }

    public void a(TextView textView, String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            while (!TextUtils.isEmpty(str) && str.length() > 5 && "<br/>".equals(str.substring(0, 5))) {
                str = str.substring(5, str.length());
            }
            if (!TextUtils.isEmpty(str) && str.length() > 5 && "<br/>".equals(str.substring(str.length() - 5, str.length()))) {
                str = str.substring(0, str.length() - 5);
            }
            if (!TextUtils.isEmpty(str) && str.length() > 0 && "\n".equals(str.substring(str.length() - 1, str.length()))) {
                String str2 = str;
                for (int i2 = 0; i2 < str2.length() && str2.lastIndexOf("\n") == str2.length() - 1; i2++) {
                    str2 = str2.substring(0, str2.length() - 1);
                }
                str = str2;
            }
            textView.setMovementMethod(com.sobot.chat.widget.a.a());
            a(this.j, textView, (Spanned) c.a(this.j.getApplicationContext(), b(textView, str.replace("\n", "<br />"), i)), i, false);
        }
    }

    /* compiled from: HtmlTools */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.utils.j$2  reason: invalid class name */
    public class AnonymousClass2 implements Html.ImageGetter {
        final /* synthetic */ TextView a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;

        AnonymousClass2(TextView textView, String str, int i) {
            this.a = textView;
            this.b = str;
            this.c = i;
        }

        @Override // android.text.Html.ImageGetter
        public Drawable getDrawable(String str) {
            if (!TextUtils.isEmpty(str)) {
                String str2 = j.this.i + String.valueOf(str.hashCode());
                if (new File(str2).exists()) {
                    m.d(" \u7f51\u7edc\u4e0b\u8f7d \u6587\u672c\u4e2d\u7684\u56fe\u7247\u4fe1\u606f  " + str2 + "  eixts");
                    Drawable createFromPath = Drawable.createFromPath(str2);
                    if (createFromPath != null) {
                        m.d(" \u56fe\u6587\u5e76\u8302\u4e2d \u56fe\u7247\u7684 \u5927\u5c0f width\uff1a " + createFromPath.getIntrinsicWidth() + "--height:" + createFromPath.getIntrinsicWidth());
                        createFromPath.setBounds(0, 0, createFromPath.getIntrinsicWidth() * 4, createFromPath.getIntrinsicHeight() * 4);
                    }
                    return createFromPath;
                }
                m.d(str2 + " Do not eixts");
                if (!str.startsWith("https://") && !str.startsWith("http://")) {
                    return null;
                }
                j.this.a(this.a, str, this.b, str2, this.c);
            }
            return null;
        }
    }

    public Spanned b(TextView textView, String str, int i) {
        return Html.fromHtml(str, new AnonymousClass2(textView, str, i), null);
    }

    public static void a(Context context, TextView textView, Spanned spanned, int i, boolean z) {
        if (spanned instanceof Spannable) {
            Spannable spannable = (Spannable) spanned;
            Matcher matcher = d.matcher(spannable);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                if (((URLSpan[]) spannable.getSpans(start, end, URLSpan.class)).length == 0) {
                    spannable.setSpan(new com.sobot.chat.widget.rich.a(context.getApplicationContext(), matcher.group(), i), start, end, 33);
                }
            }
            Matcher matcher2 = b().matcher(spannable);
            while (matcher2.find()) {
                int start2 = matcher2.start();
                int end2 = matcher2.end();
                if (((URLSpan[]) spannable.getSpans(start2, end2, URLSpan.class)).length == 0) {
                    spannable.setSpan(new MyURLSpan(context.getApplicationContext(), matcher2.group(), i, true), start2, end2, 33);
                }
            }
            Matcher matcher3 = a().matcher(spannable);
            while (matcher3.find()) {
                int start3 = matcher3.start();
                int end3 = matcher3.end();
                if (((URLSpan[]) spannable.getSpans(start3, end3, URLSpan.class)).length == 0) {
                    spannable.setSpan(new b(context.getApplicationContext(), matcher3.group(), i), start3, end3, 33);
                }
            }
            int length = spanned.length();
            URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, length, URLSpan.class);
            URLSpan[] uRLSpanArr2 = spanned != null ? (URLSpan[]) spanned.getSpans(0, length, URLSpan.class) : new URLSpan[0];
            if (uRLSpanArr.length == 0 && uRLSpanArr2.length == 0) {
                textView.setText(spannable);
                return;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spanned);
            for (URLSpan uRLSpan : uRLSpanArr2) {
                spannableStringBuilder.removeSpan(uRLSpan);
                spannableStringBuilder.setSpan(new MyURLSpan(context.getApplicationContext(), uRLSpan.getURL(), i, z), spanned.getSpanStart(uRLSpan), spanned.getSpanEnd(uRLSpan), 33);
            }
            textView.setText(spannableStringBuilder);
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (b().matcher(str.toString()).matches()) {
            return true;
        }
        m.d("URL \u975e\u6cd5\uff0c\u8bf7\u8f93\u5165\u6709\u6548\u7684URL\u94fe\u63a5:" + str);
        return false;
    }
}
