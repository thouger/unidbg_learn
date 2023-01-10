package cn.com.chinatelecom.account.api.c;

import android.content.Context;
import cn.com.chinatelecom.account.api.Helper;
import cn.com.chinatelecom.account.api.a.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class k implements i {
    @Override // cn.com.chinatelecom.account.api.c.i
    public String a() {
        AppMethodBeat.i(8360, false);
        String cepahsul = Helper.cepahsul();
        AppMethodBeat.o(8360);
        return cepahsul;
    }

    @Override // cn.com.chinatelecom.account.api.c.i
    public String a(Context context, String str, String str2, String str3, long j, String str4) {
        AppMethodBeat.i(8365, false);
        String dnepah = Helper.dnepah(context, str, str2, str3, j, str4);
        AppMethodBeat.o(8365);
        return dnepah;
    }

    @Override // cn.com.chinatelecom.account.api.c.i
    public String a(String str, String str2) {
        String str3;
        AppMethodBeat.i(8369, false);
        try {
            str3 = new String(Helper.dnepmret(c.a(str), str2));
        } catch (Throwable th) {
            th.printStackTrace();
            str3 = "";
        }
        AppMethodBeat.o(8369);
        return str3;
    }

    @Override // cn.com.chinatelecom.account.api.c.i
    public String b() {
        AppMethodBeat.i(8363, false);
        String cemppmul = Helper.cemppmul();
        AppMethodBeat.o(8363);
        return cemppmul;
    }

    @Override // cn.com.chinatelecom.account.api.c.i
    public String b(Context context, String str, String str2, String str3, long j, String str4) {
        AppMethodBeat.i(8367, false);
        String dnepmo = Helper.dnepmo(context, str, str2, str3, j, str4);
        AppMethodBeat.o(8367);
        return dnepmo;
    }
}
