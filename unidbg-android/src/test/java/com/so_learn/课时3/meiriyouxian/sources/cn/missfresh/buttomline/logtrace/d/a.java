package cn.missfresh.buttomline.logtrace.d;

import cn.missfresh.basiclib.tool.SystemUtils;
import cn.missfresh.basiclib.tool.c;
import cn.missfresh.basiclib.tool.d;
import cn.missfresh.buttomline.logtrace.b;
import cn.missfresh.buttomline.logtrace.bean.RequestBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;
import retrofit2.adapter.rxjava2.g;
import retrofit2.r;

/* compiled from: ApiManager */
public class a {
    private static c a;
    private static String b = b.a();

    static {
        AppMethodBeat.i(16955, false);
        AppMethodBeat.o(16955);
    }

    public static c a() {
        AppMethodBeat.i(16947, false);
        if (a == null) {
            synchronized (c.class) {
                try {
                    if (a == null) {
                        r.a a2 = cn.missfresh.basiclib.net.a.a().c().a(b);
                        a2.a(g.a());
                        a2.a(cn.missfresh.lib.b.a.a());
                        a = (c) a2.a().a(c.class);
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(16947);
                    throw th;
                }
            }
        }
        c cVar = a;
        AppMethodBeat.o(16947);
        return cVar;
    }

    public static void a(RequestBean requestBean, b bVar) {
        String str;
        String str2;
        AppMethodBeat.i(16951, false);
        String str3 = "";
        String replace = SystemUtils.d().replace("-", str3);
        if (cn.missfresh.buttomline.logtrace.a.c()) {
            str = str3;
            str2 = "0";
        } else {
            try {
                String c = SystemUtils.c();
                str3 = cn.missfresh.basiclib.tool.b.a(d.a(c.getBytes(), d.a("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxoRz14rbDZdTan6IrLE2xgNr+XOLVXFiBbh/HFZzOIMIZNp/hN0Ld78GlBt+yGJpNNXojmGpH7yM3WJxM09sBvEBb+WLK6O0T3GnClbFPmNEXIB1eR2W1pmVI9vw0E1VCQ8u/9vuH91OnPSXfevQGmGD2zNduTXDTGYdUA8VoXbqdu0BpDYx0y10IZgGAkQjGA6nqY06WJmRNflaxfx7uwdElbESo2VnkQKdIwYIkbkn7exlf1O8/aQgwMzTZwQzVQR+gvqYZ62tkM82Dkm6Px0BtPv0jYwaW0ATYjYUQEuGYUkYqpz0oNduNBwKKMFsBOfpCjTm4y0J9AUnMzNpiwIDAQAB")));
                requestBean.setObj(cn.missfresh.basiclib.tool.b.a(cn.missfresh.basiclib.tool.a.a(a(c.a.toJson(requestBean).getBytes()), c.getBytes())));
            } catch (Exception e) {
                cn.missfresh.utils.a.d.a("CensusConfig", e);
            }
            str = str3;
            str2 = "1";
        }
        a().a(replace, str2, str, str2, requestBean).a(cn.missfresh.basiclib.net.e.a.a).subscribe(new cn.missfresh.basiclib.net.c(bVar));
        AppMethodBeat.o(16951);
    }

    private static byte[] a(byte[] bArr) {
        AppMethodBeat.i(16953, false);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.flush();
            gZIPOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            AppMethodBeat.o(16953);
            return byteArray;
        } catch (Exception e) {
            cn.missfresh.utils.a.d.a("CensusConfig", e);
            AppMethodBeat.o(16953);
            return bArr;
        }
    }
}
