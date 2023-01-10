package cn.missfresh.buttomline.logtrace.a;

import cn.missfresh.buttomline.logtrace.a;
import cn.missfresh.buttomline.logtrace.b.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.File;

/* compiled from: LogWriterImpl */
public class b implements c {
    public static final String a = (cn.missfresh.utils.c.a(a.b()) + "census");
    public static final String b = (cn.missfresh.utils.c.a(a.b()) + "report");
    public static final String c = (cn.missfresh.utils.c.a(a.b()) + "log_file");

    static {
        AppMethodBeat.i(16919, false);
        AppMethodBeat.o(16919);
    }

    @Override // cn.missfresh.buttomline.logtrace.b.c
    public void a(String str, String str2) {
        AppMethodBeat.i(16912, false);
        if (str2 == null || str2.length() == 0 || str == null || str.length() == 0) {
            AppMethodBeat.o(16912);
            return;
        }
        File file = new File(a, str);
        try {
            cn.missfresh.utils.c.a(str2 + "\n", file.getAbsolutePath(), true);
        } catch (Exception unused) {
        }
        AppMethodBeat.o(16912);
    }

    @Override // cn.missfresh.buttomline.logtrace.b.c
    public void b(String str, String str2) {
        AppMethodBeat.i(16915, false);
        if (str2 == null || str2.length() == 0 || str == null || str.length() == 0) {
            AppMethodBeat.o(16915);
            return;
        }
        File file = new File(c, str);
        try {
            cn.missfresh.utils.c.a(str2 + "\n", file.getAbsolutePath(), true);
        } catch (Exception unused) {
        }
        AppMethodBeat.o(16915);
    }

    @Override // cn.missfresh.buttomline.logtrace.b.c
    public void a(String str) {
        AppMethodBeat.i(16917, false);
        cn.missfresh.utils.c.a(a + File.separator + str, b + File.separator + str);
        AppMethodBeat.o(16917);
    }
}
