package io.flutter.plugins.imagepicker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* access modifiers changed from: package-private */
public class ImageResizer {
    private final ExifDataCopier exifDataCopier;
    private final File externalFilesDirectory;

    ImageResizer(File file, ExifDataCopier exifDataCopier) {
        this.externalFilesDirectory = file;
        this.exifDataCopier = exifDataCopier;
    }

    /* access modifiers changed from: package-private */
    public String resizeImageIfNeeded(String str, Double d, Double d2, Integer num) {
        Bitmap decodeFile = decodeFile(str);
        if (decodeFile == null) {
            return null;
        }
        if (!((d == null && d2 == null && !isImageQualityValid(num)) ? false : true)) {
            return str;
        }
        try {
            String[] split = str.split(NotificationIconUtil.SPLIT_CHAR);
            File resizedImage = resizedImage(decodeFile, d, d2, num, split[split.length - 1]);
            copyExif(str, resizedImage.getPath());
            return resizedImage.getPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File resizedImage(Bitmap bitmap, Double d, Double d2, Integer num, String str) throws IOException {
        double width = ((double) bitmap.getWidth()) * 1.0d;
        double height = ((double) bitmap.getHeight()) * 1.0d;
        Integer num2 = num;
        if (!isImageQualityValid(num2)) {
            num2 = 100;
        }
        boolean z = true;
        boolean z2 = d != null;
        boolean z3 = d2 != null;
        Double valueOf = Double.valueOf(z2 ? Math.min(width, d.doubleValue()) : width);
        Double valueOf2 = Double.valueOf(z3 ? Math.min(height, d2.doubleValue()) : height);
        boolean z4 = z2 && d.doubleValue() < width;
        boolean z5 = z3 && d2.doubleValue() < height;
        if (!z4 && !z5) {
            z = false;
        }
        if (z) {
            double doubleValue = (valueOf2.doubleValue() / height) * width;
            double doubleValue2 = (valueOf.doubleValue() / width) * height;
            if (valueOf.doubleValue() < valueOf2.doubleValue()) {
                if (!z2) {
                    valueOf = Double.valueOf(doubleValue);
                } else {
                    valueOf2 = Double.valueOf(doubleValue2);
                }
            } else if (valueOf2.doubleValue() < valueOf.doubleValue()) {
                if (!z3) {
                    valueOf2 = Double.valueOf(doubleValue2);
                } else {
                    valueOf = Double.valueOf(doubleValue);
                }
            } else if (width < height) {
                valueOf = Double.valueOf(doubleValue);
            } else if (height < width) {
                valueOf2 = Double.valueOf(doubleValue2);
            }
        }
        return createImageOnExternalDirectory("/scaled_" + str, createScaledBitmap(bitmap, valueOf.intValue(), valueOf2.intValue(), false), num2.intValue());
    }

    private File createFile(File file, String str) {
        File file2 = new File(file, str);
        if (!file2.getParentFile().exists()) {
            file2.getParentFile().mkdirs();
        }
        return file2;
    }

    private FileOutputStream createOutputStream(File file) throws IOException {
        return new FileOutputStream(file);
    }

    private void copyExif(String str, String str2) {
        this.exifDataCopier.copyExif(str, str2);
    }

    private Bitmap decodeFile(String str) {
        return BitmapFactory.decodeFile(str);
    }

    private Bitmap createScaledBitmap(Bitmap bitmap, int i, int i2, boolean z) {
        return Bitmap.createScaledBitmap(bitmap, i, i2, z);
    }

    private boolean isImageQualityValid(Integer num) {
        return num != null && num.intValue() > 0 && num.intValue() < 100;
    }

    private File createImageOnExternalDirectory(String str, Bitmap bitmap, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean hasAlpha = bitmap.hasAlpha();
        if (hasAlpha) {
            Log.d("ImageResizer", "image_picker: compressing is not supported for type PNG. Returning the image with original quality");
        }
        bitmap.compress(hasAlpha ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        File createFile = createFile(this.externalFilesDirectory, str);
        FileOutputStream createOutputStream = createOutputStream(createFile);
        createOutputStream.write(byteArrayOutputStream.toByteArray());
        createOutputStream.close();
        return createFile;
    }
}
