package com.umeng.message.inapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.proguard.h;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/* access modifiers changed from: package-private */
public class UImageLoadTask extends AsyncTask<String, Void, Bitmap[]> {
    private static final String a = UImageLoadTask.class.getName();
    private ImageLoaderCallback b;
    private String c;
    private BitmapFactory.Options d;

    /* access modifiers changed from: package-private */
    public interface ImageLoaderCallback {
        void onLoadImage(Bitmap[] bitmapArr);
    }

    public UImageLoadTask(Context context, UInAppMessage uInAppMessage) {
        this.c = h.d(context, uInAppMessage.msg_id);
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            this.d = new BitmapFactory.Options();
            this.d.inSampleSize = a(uInAppMessage, i, i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Bitmap[] doInBackground(String... strArr) {
        Bitmap[] bitmapArr = new Bitmap[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            try {
                if (!a(strArr[i])) {
                    bitmapArr[i] = b(strArr[i]);
                    if (bitmapArr[i] == null) {
                        bitmapArr[i] = c(strArr[i]);
                        a(bitmapArr[i], strArr[i]);
                    }
                } else {
                    bitmapArr[i] = b(strArr[i]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmapArr;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Bitmap[] bitmapArr) {
        super.onPostExecute(bitmapArr);
        for (Bitmap bitmap : bitmapArr) {
            if (bitmap == null) {
                return;
            }
        }
        ImageLoaderCallback imageLoaderCallback = this.b;
        if (imageLoaderCallback != null) {
            imageLoaderCallback.onLoadImage(bitmapArr);
        }
    }

    private boolean a(String str) {
        return new File(this.c, str.hashCode() + "").exists();
    }

    private Bitmap b(String str) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(this.c + (str.hashCode() + ""));
            UMLog.mutlInfo(a, 2, "load from local");
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return bitmap;
        }
    }

    private Bitmap c(String str) throws IOException {
        Bitmap bitmap;
        UMLog.mutlInfo(a, 2, "Downloading image start");
        URLConnection openConnection = new URL(str).openConnection();
        openConnection.connect();
        InputStream inputStream = openConnection.getInputStream();
        if (this.d == null) {
            bitmap = BitmapFactory.decodeStream(inputStream);
        } else {
            UMLog.mutlInfo(a, 2, "decode options");
            bitmap = BitmapFactory.decodeStream(inputStream, null, this.d);
        }
        inputStream.close();
        UMLog.mutlInfo(a, 2, "Downloading image finish");
        return bitmap;
    }

    private void a(Bitmap bitmap, String str) {
        boolean z;
        boolean z2;
        Exception e;
        if (bitmap != null) {
            try {
                File file = new File(this.c);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(new File(this.c, str.hashCode() + ""));
                z = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                try {
                    fileOutputStream.close();
                } catch (Exception e2) {
                    z2 = z;
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
                z2 = false;
                e.printStackTrace();
                z = z2;
                UMLog.mutlInfo(a, 2, "store bitmap" + z);
            }
            UMLog.mutlInfo(a, 2, "store bitmap" + z);
        }
    }

    private static int a(UInAppMessage uInAppMessage, int i, int i2) {
        int i3 = uInAppMessage.height;
        int i4 = uInAppMessage.width;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public void a(ImageLoaderCallback imageLoaderCallback) {
        this.b = imageLoaderCallback;
    }
}
