package cn.missfresh.module.base.utils;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: PayResult */
public class ai {
    private String a;
    private String b;
    private String c;

    public ai(String str) {
        AppMethodBeat.i(23379, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(23379);
            return;
        }
        String[] split = str.split(";");
        for (String str2 : split) {
            if (str2.startsWith("resultStatus")) {
                this.a = a(str2, "resultStatus");
            }
            if (str2.startsWith("result")) {
                this.b = a(str2, "result");
            }
            if (str2.startsWith("memo")) {
                this.c = a(str2, "memo");
            }
        }
        AppMethodBeat.o(23379);
    }

    public String toString() {
        AppMethodBeat.i(23380, false);
        String str = "resultStatus={" + this.a + "};memo={" + this.c + "};result={" + this.b + "}";
        AppMethodBeat.o(23380);
        return str;
    }

    private String a(String str, String str2) {
        AppMethodBeat.i(23381, false);
        String str3 = str2 + "={";
        String substring = str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf("}"));
        AppMethodBeat.o(23381);
        return substring;
    }

    public String a() {
        return this.a;
    }
}
