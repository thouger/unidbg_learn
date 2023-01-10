package cn.missfresh.buttomline.abtest.uitl;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataUtil {
    static final String ENCODE_STR = "UTF-8";
    private static Gson gson;

    public static Gson getGsonInstance() {
        AppMethodBeat.i(7480, false);
        if (gson == null) {
            gson = new GsonBuilder().serializeNulls().create();
        }
        Gson gson2 = gson;
        AppMethodBeat.o(7480);
        return gson2;
    }

    public static <T> T jsonToObject(String str, TypeToken<T> typeToken) {
        AppMethodBeat.i(7483, false);
        T t = (T) getGsonInstance().fromJson(str, typeToken.getType());
        AppMethodBeat.o(7483);
        return t;
    }

    public static String inputStream2String(InputStream inputStream) throws IOException {
        AppMethodBeat.i(7485, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read();
            if (read != -1) {
                byteArrayOutputStream.write(read);
            } else {
                String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                AppMethodBeat.o(7485);
                return byteArrayOutputStream2;
            }
        }
    }
}
