package com.sobot.chat.camera.c;

import android.app.backup.FullBackup;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.k;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.z;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* compiled from: FileUtil */
public class f {
    public static String a(int i, Bitmap bitmap) {
        String e = z.a().e();
        k.a(e);
        String str = e + "pic_" + System.currentTimeMillis() + ".jpg";
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            return str;
        } catch (Exception e2) {
            e2.printStackTrace();
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            return "";
        } catch (Throwable th) {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            throw th;
        }
    }

    public static String a(Context context, Uri uri, String str, String str2) throws Exception {
        if (uri == null) {
            return str2;
        }
        FileInputStream fileInputStream = new FileInputStream(context.getContentResolver().openFileDescriptor(uri, FullBackup.ROOT_TREE_TOKEN).getFileDescriptor());
        String e = z.a().e();
        k.a(e);
        String str3 = e + str;
        if (k.a(new FileOutputStream(str3), fileInputStream)) {
            return str3;
        }
        ae.a(context, q.f(context, "sobot_pic_type_error"));
        return str2;
    }

    public static boolean a(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static String b(String str) {
        return str.indexOf(".") != -1 ? str.substring(str.lastIndexOf(".") - 1) : "";
    }
}
