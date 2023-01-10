package com.vivo.push.model;

import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ConfigItem */
public final class a {
    public String a;
    public String b;

    public a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final int hashCode() {
        int i = 0;
        AppMethodBeat.i(1951, false);
        String str = this.a;
        if (str != null) {
            i = str.hashCode();
        }
        int i2 = i + 31;
        AppMethodBeat.o(1951);
        return i2;
    }

    public final boolean equals(Object obj) {
        AppMethodBeat.i(1953, false);
        if (this == obj) {
            AppMethodBeat.o(1953);
            return true;
        } else if (obj == null) {
            AppMethodBeat.o(1953);
            return false;
        } else if (getClass() != obj.getClass()) {
            AppMethodBeat.o(1953);
            return false;
        } else {
            a aVar = (a) obj;
            String str = this.a;
            if (str == null) {
                if (aVar.a != null) {
                    AppMethodBeat.o(1953);
                    return false;
                }
            } else if (!str.equals(aVar.a)) {
                AppMethodBeat.o(1953);
                return false;
            }
            AppMethodBeat.o(1953);
            return true;
        }
    }

    public final String toString() {
        AppMethodBeat.i(1956, false);
        String str = "ConfigItem{mKey='" + this.a + DateFormat.QUOTE + ", mValue='" + this.b + DateFormat.QUOTE + '}';
        AppMethodBeat.o(1956);
        return str;
    }
}
