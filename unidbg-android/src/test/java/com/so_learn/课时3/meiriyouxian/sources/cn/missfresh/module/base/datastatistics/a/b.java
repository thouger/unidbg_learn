package cn.missfresh.module.base.datastatistics.a;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;

/* compiled from: Scm */
public class b {
    h a;
    String b;

    public h a() {
        return this.a;
    }

    public void a(h hVar) {
        this.a = hVar;
    }

    public void a(String str) {
        AppMethodBeat.i(12748, false);
        if ("null".equals(str)) {
            str = null;
        }
        this.b = str;
        AppMethodBeat.o(12748);
    }

    public int hashCode() {
        int i = 0;
        AppMethodBeat.i(12749, false);
        String str = this.b;
        if (str != null) {
            i = str.hashCode();
        }
        int i2 = MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE + i;
        AppMethodBeat.o(12749);
        return i2;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        AppMethodBeat.i(12751, false);
        if (obj instanceof b) {
            b bVar = (b) obj;
            if (a(bVar) && TextUtils.equals(this.b, bVar.b)) {
                z = true;
            }
        }
        AppMethodBeat.o(12751);
        return z;
    }

    private boolean a(b bVar) {
        boolean z = false;
        AppMethodBeat.i(12753, false);
        h hVar = this.a;
        if (hVar == null) {
            if (bVar.a() == null) {
                z = true;
            }
            AppMethodBeat.o(12753);
            return z;
        }
        boolean equals = hVar.equals(bVar.a());
        AppMethodBeat.o(12753);
        return equals;
    }

    public String toString() {
        AppMethodBeat.i(12756, false);
        StringBuilder sb = new StringBuilder(this.a.toString());
        if (this.b != null) {
            sb.append('@');
            sb.append(this.b);
        }
        String sb2 = sb.toString();
        AppMethodBeat.o(12756);
        return sb2;
    }
}
