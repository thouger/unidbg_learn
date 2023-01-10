package cn.missfresh.risk.f;

import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/* compiled from: CompressUtil */
public class c {
    public static String a(String str) throws IOException {
        AppMethodBeat.i(2811, false);
        if (str == null || str.length() <= 0) {
            AppMethodBeat.o(2811);
            return str;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(str.getBytes(FileOpt.ENCODE_STR));
        gZIPOutputStream.close();
        String a = a.a(byteArrayOutputStream.toByteArray());
        AppMethodBeat.o(2811);
        return a;
    }
}
