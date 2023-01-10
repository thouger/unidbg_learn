package com.sobot.chat.utils;

import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;
import java.io.File;

/* compiled from: FileOpenHelper */
public class h {
    public static Intent a(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(268435456);
        intent.setDataAndType(a(context, file, intent), "image/*");
        return intent;
    }

    public static Intent b(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(268435456);
        intent.setDataAndType(a(context, file, intent), "application/pdf");
        return intent;
    }

    public static Intent c(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(268435456);
        intent.setDataAndType(a(context, file, intent), ClipDescription.MIMETYPE_TEXT_PLAIN);
        return intent;
    }

    public static Intent d(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        intent.setDataAndType(a(context, file, intent), "audio/*");
        return intent;
    }

    public static Intent e(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        intent.setDataAndType(a(context, file, intent), "video/*");
        return intent;
    }

    public static Intent f(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(268435456);
        intent.setDataAndType(a(context, file, intent), "application/msword");
        return intent;
    }

    public static Intent g(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(268435456);
        intent.setDataAndType(a(context, file, intent), "application/vnd.ms-excel");
        return intent;
    }

    public static Intent h(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(268435456);
        intent.setDataAndType(a(context, file, intent), "application/vnd.ms-powerpoint");
        return intent;
    }

    public static Intent i(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(a(context, file, intent), "application");
        return intent;
    }

    private static Uri a(Context context, File file, Intent intent) {
        if (Build.VERSION.SDK_INT < 24) {
            return Uri.fromFile(file);
        }
        intent.addFlags(1);
        return FileProvider.getUriForFile(context, context.getPackageName() + ".sobot_fileprovider", file);
    }

    public static Uri j(Context context, File file) {
        if (Build.VERSION.SDK_INT < 24) {
            return Uri.fromFile(file);
        }
        return FileProvider.getUriForFile(context, context.getPackageName() + ".sobot_fileprovider", file);
    }

    public static boolean a(String str, Context context, String str2) {
        try {
            for (String str3 : context.getResources().getStringArray(q.a(context, "array", str2))) {
                if (str.endsWith(str3)) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static void k(Context context, File file) {
        Intent intent;
        if (context != null && file != null && file.exists() && file.isFile()) {
            String lowerCase = file.getName().toLowerCase();
            if (a(lowerCase, context, "sobot_fileEndingPackage")) {
                intent = i(context, file);
            } else if (a(lowerCase, context, "sobot_fileEndingVideo")) {
                intent = e(context, file);
            } else if (a(lowerCase, context, "sobot_fileEndingAudio")) {
                intent = d(context, file);
            } else if (a(lowerCase, context, "sobot_fileEndingWord")) {
                intent = f(context, file);
            } else if (a(lowerCase, context, "sobot_fileEndingExcel")) {
                intent = g(context, file);
            } else if (a(lowerCase, context, "sobot_fileEndingPPT")) {
                intent = h(context, file);
            } else if (a(lowerCase, context, "sobot_fileEndingPdf")) {
                intent = b(context, file);
            } else if (a(lowerCase, context, "sobot_fileEndingText")) {
                intent = c(context, file);
            } else if (a(lowerCase, context, "sobot_fileEndingImage")) {
                intent = a(context, file);
            } else {
                intent = i(context, file);
            }
            try {
                context.startActivity(intent);
            } catch (Exception unused) {
                ae.a(context, q.f(context, "sobot_cannot_open_file"));
            }
        }
    }
}
