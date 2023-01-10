package cn.missfresh.buttomline.abtest.uitl;

import android.text.TextUtils;
import cn.missfresh.buttomline.abtest.log.Logger;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.imsdk.BaseConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class FileOpt {
    public static final String ENCODE_STR = "utf-8";

    public static String readFileContent(String str) {
        AppMethodBeat.i(7490, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(7490);
            return null;
        }
        File file = new File(str);
        String str2 = "";
        try {
            if (file.exists() && file.isFile()) {
                str2 = readFileContent(new FileInputStream(file));
            }
        } catch (Exception e) {
            Logger.e(e);
        }
        AppMethodBeat.o(7490);
        return str2;
    }

    /* JADX INFO: finally extract failed */
    private static String readFileContent(InputStream inputStream) {
        AppMethodBeat.i(7492, false);
        BufferedSource bufferedSource = null;
        try {
            bufferedSource = Okio.buffer(Okio.source(inputStream));
            String readString = bufferedSource.readString(Charset.forName(ENCODE_STR));
            Util.closeQuietly(bufferedSource);
            AppMethodBeat.o(7492);
            return readString;
        } catch (IOException e) {
            Logger.e(e);
            Util.closeQuietly(bufferedSource);
            AppMethodBeat.o(7492);
            return "";
        } catch (Throwable th) {
            Util.closeQuietly(bufferedSource);
            AppMethodBeat.o(7492);
            throw th;
        }
    }

    public static void writeStringToFile(String str, String str2) {
        AppMethodBeat.i(7495, false);
        if (!TextUtils.isEmpty(str2)) {
            writeStringToFile(str, new File(str2));
        }
        AppMethodBeat.o(7495);
    }

    private static void writeStringToFile(String str, File file) {
        AppMethodBeat.i(7498, false);
        if (file != null) {
            if (!file.exists()) {
                try {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                } catch (Exception e) {
                    Logger.e(e);
                }
            }
            if (!TextUtils.isEmpty(str)) {
                BufferedSink bufferedSink = null;
                try {
                    bufferedSink = Okio.buffer(Okio.sink(file));
                    bufferedSink.writeString(str, Charset.forName(ENCODE_STR));
                    bufferedSink.close();
                } catch (Exception e2) {
                    Logger.e(e2);
                } catch (Throwable th) {
                    Util.closeQuietly(bufferedSink);
                    AppMethodBeat.o(7498);
                    throw th;
                }
                Util.closeQuietly(bufferedSink);
            }
        }
        AppMethodBeat.o(7498);
    }

    public static void clearFileList(String str) {
        AppMethodBeat.i(BaseConstants.ERR_SDK_ACCOUNT_LOGIN_IN_PROCESS, false);
        File file = new File(str);
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    clearFileList(file2.getAbsolutePath());
                }
            } else {
                file.delete();
            }
        }
        AppMethodBeat.o(BaseConstants.ERR_SDK_ACCOUNT_LOGIN_IN_PROCESS);
    }
}
