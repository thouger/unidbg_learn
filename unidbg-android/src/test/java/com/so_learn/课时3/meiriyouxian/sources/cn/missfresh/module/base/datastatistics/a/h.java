package cn.missfresh.module.base.datastatistics.a;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;

/* compiled from: Spm */
public class h {
    String a;
    String b;
    String c;
    String d;

    public void a(String str) {
        this.c = str;
    }

    public void b(String str) {
        this.d = str;
    }

    public void c(String str) {
        AppMethodBeat.i(12843, false);
        if ("null".equals(str)) {
            str = null;
        }
        this.a = str;
        AppMethodBeat.o(12843);
    }

    public void d(String str) {
        AppMethodBeat.i(12845, false);
        if ("null".equals(str)) {
            str = null;
        }
        this.b = str;
        AppMethodBeat.o(12845);
    }

    public boolean equals(Object obj) {
        boolean z = false;
        AppMethodBeat.i(12848, false);
        if (obj instanceof h) {
            h hVar = (h) obj;
            if (TextUtils.equals(this.a, hVar.a) && TextUtils.equals(this.b, hVar.b)) {
                z = true;
            }
        }
        AppMethodBeat.o(12848);
        return z;
    }

    public int hashCode() {
        int i = 0;
        AppMethodBeat.i(12851, false);
        String str = this.a;
        int hashCode = (MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.b;
        if (str2 != null) {
            i = str2.hashCode();
        }
        int i2 = hashCode + i;
        AppMethodBeat.o(12851);
        return i2;
    }

    public String toString() {
        AppMethodBeat.i(12853, false);
        String c = e.a() != null ? e.a().c() : null;
        if (c == null) {
            c = "";
        }
        StringBuilder sb = new StringBuilder(c);
        sb.append('.');
        String str = this.a;
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append('.');
        String str2 = this.b;
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        String sb2 = sb.toString();
        AppMethodBeat.o(12853);
        return sb2;
    }

    public String a() {
        AppMethodBeat.i(12854, false);
        StringBuilder sb = new StringBuilder();
        sb.append("missfresh.");
        sb.append(this.a);
        sb.append(".");
        sb.append(this.c);
        sb.append(".");
        String str = "-";
        sb.append(TextUtils.isEmpty(this.b) ? str : this.b);
        sb.append(".");
        if (!TextUtils.isEmpty(this.d)) {
            str = this.d;
        }
        sb.append(str);
        String sb2 = sb.toString();
        AppMethodBeat.o(12854);
        return sb2;
    }
}
