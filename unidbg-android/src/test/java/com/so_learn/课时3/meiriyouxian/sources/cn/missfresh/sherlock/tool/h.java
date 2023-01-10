package cn.missfresh.sherlock.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Build;
import android.util.Base64;
import android.view.Display;
import android.view.WindowManager;
import java.io.ByteArrayOutputStream;

/* compiled from: ImageUtils */
public class h {
    public static String a(String str, int i) {
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decodeFile.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        int i2 = 50;
        while (byteArrayOutputStream.toByteArray().length / 1024 > i) {
            byteArrayOutputStream.reset();
            decodeFile.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
            if (i2 < 10) {
                i2--;
                continue;
            } else {
                i2 -= 5;
                continue;
            }
            if (i2 < 0) {
                break;
            }
        }
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0).trim().replaceAll("\n", "").replaceAll(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER, "").replaceAll("\t", "").replaceAll("\r", "");
    }

    public static Point a(Context context) {
        Point point;
        Exception e;
        try {
            point = new Point();
            try {
                Display defaultDisplay = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                if (Build.VERSION.SDK_INT >= 17) {
                    defaultDisplay.getRealSize(point);
                } else {
                    point.set(((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue(), ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue());
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                j.a("ImageUtils", "getRealScreenSize fail!", e);
                return point;
            }
        } catch (Exception e3) {
            e = e3;
            point = null;
            e.printStackTrace();
            j.a("ImageUtils", "getRealScreenSize fail!", e);
            return point;
        }
        return point;
    }

    public static Point a(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return new Point(options.outWidth, options.outHeight);
    }
}
