package com.google.android.exoplayer2.source.dash.a;

import com.android.internal.logging.nano.MetricsProto;
import com.google.android.exoplayer2.util.z;

/* compiled from: ProgramInformation */
public class g {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;

    public g(String str, String str2, String str3, String str4, String str5) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        g gVar = (g) obj;
        return z.a(this.a, gVar.a) && z.a(this.b, gVar.b) && z.a(this.c, gVar.c) && z.a(this.d, gVar.d) && z.a(this.e, gVar.e);
    }

    public int hashCode() {
        String str = this.a;
        int i = 0;
        int hashCode = (MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.c;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.d;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.e;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return hashCode4 + i;
    }
}
