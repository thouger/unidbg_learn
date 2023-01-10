package com.google.android.exoplayer2.text.f;

import android.net.wifi.WifiEnterpriseConfig;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import java.util.Map;

/* compiled from: TtmlRenderUtil */
/* access modifiers changed from: package-private */
public final class d {
    public static e a(e eVar, String[] strArr, Map<String, e> map) {
        if (eVar == null && strArr == null) {
            return null;
        }
        int i = 0;
        if (eVar == null && strArr.length == 1) {
            return map.get(strArr[0]);
        }
        if (eVar == null && strArr.length > 1) {
            e eVar2 = new e();
            int length = strArr.length;
            while (i < length) {
                eVar2.a(map.get(strArr[i]));
                i++;
            }
            return eVar2;
        } else if (eVar != null && strArr != null && strArr.length == 1) {
            return eVar.a(map.get(strArr[0]));
        } else {
            if (!(eVar == null || strArr == null || strArr.length <= 1)) {
                int length2 = strArr.length;
                while (i < length2) {
                    eVar.a(map.get(strArr[i]));
                    i++;
                }
            }
            return eVar;
        }
    }

    public static void a(SpannableStringBuilder spannableStringBuilder, int i, int i2, e eVar) {
        if (eVar.a() != -1) {
            spannableStringBuilder.setSpan(new StyleSpan(eVar.a()), i, i2, 33);
        }
        if (eVar.b()) {
            spannableStringBuilder.setSpan(new StrikethroughSpan(), i, i2, 33);
        }
        if (eVar.c()) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
        }
        if (eVar.f()) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(eVar.e()), i, i2, 33);
        }
        if (eVar.h()) {
            spannableStringBuilder.setSpan(new BackgroundColorSpan(eVar.g()), i, i2, 33);
        }
        if (eVar.d() != null) {
            spannableStringBuilder.setSpan(new TypefaceSpan(eVar.d()), i, i2, 33);
        }
        if (eVar.j() != null) {
            spannableStringBuilder.setSpan(new AlignmentSpan.Standard(eVar.j()), i, i2, 33);
        }
        int k = eVar.k();
        if (k == 1) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) eVar.l(), true), i, i2, 33);
        } else if (k == 2) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(eVar.l()), i, i2, 33);
        } else if (k == 3) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(eVar.l() / 100.0f), i, i2, 33);
        }
    }

    static void a(SpannableStringBuilder spannableStringBuilder) {
        int length = spannableStringBuilder.length() - 1;
        while (length >= 0 && spannableStringBuilder.charAt(length) == ' ') {
            length--;
        }
        if (length >= 0 && spannableStringBuilder.charAt(length) != '\n') {
            spannableStringBuilder.append('\n');
        }
    }

    static String a(String str) {
        return str.replaceAll("\r\n", "\n").replaceAll(" *\n *", "\n").replaceAll("\n", WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER).replaceAll("[ \t\\x0B\f\r]+", WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
    }
}
