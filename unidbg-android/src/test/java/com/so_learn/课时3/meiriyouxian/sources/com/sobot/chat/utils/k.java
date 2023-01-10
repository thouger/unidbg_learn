package com.sobot.chat.utils;

import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: IOUtils */
public class k {
    public static boolean a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return a(new File(str));
        }
        return false;
    }

    public static boolean a(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                return true;
            }
            file.delete();
        }
        return file.mkdirs();
    }

    public static boolean a(OutputStream outputStream, InputStream inputStream) {
        if (outputStream == null || inputStream == null) {
            return false;
        }
        try {
            byte[] bArr = new byte[1444];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
                outputStream.flush();
            }
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return false;
        } catch (Throwable th) {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }
}
