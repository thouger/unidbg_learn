package cn.missfresh.module.base.common.config;

import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;
import java.io.File;

/* compiled from: FileConfigs */
public class c {
    public static String a = r.c();
    public static String b = cn.missfresh.utils.c.a(((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication(), (String) null).getAbsolutePath();
    public static String c = "MissFresh";
    public static String d = (b + File.separator + c + File.separator + "logs" + File.separator + "log.txt");
    public static String e;
    @Deprecated
    public static String f = (a + File.separator + c + File.separator + "logs" + File.separator);
    public static String g;
    public static String h = (b + File.separator + c + File.separator + "cfg.json");

    static {
        AppMethodBeat.i(10330, false);
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        sb.append(File.separator);
        sb.append(c);
        sb.append(File.separator);
        sb.append("cache");
        e = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(b);
        sb2.append(File.separator);
        sb2.append(c);
        sb2.append(File.separator);
        sb2.append("images");
        g = sb2.toString();
        AppMethodBeat.o(10330);
    }
}
