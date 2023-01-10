package io.flutter.plugins.imagepicker;

import android.media.ExifInterface;
import android.util.Log;
import java.util.Arrays;

/* access modifiers changed from: package-private */
public class ExifDataCopier {
    ExifDataCopier() {
    }

    /* access modifiers changed from: package-private */
    public void copyExif(String str, String str2) {
        try {
            ExifInterface exifInterface = new ExifInterface(str);
            ExifInterface exifInterface2 = new ExifInterface(str2);
            for (String str3 : Arrays.asList("FNumber", "ExposureTime", "ISOSpeedRatings", "GPSAltitude", "GPSAltitudeRef", "FocalLength", "GPSDateStamp", "WhiteBalance", "GPSProcessingMethod", "GPSTimeStamp", "DateTime", "Flash", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "Make", "Model", "Orientation")) {
                setIfNotNull(exifInterface, exifInterface2, str3);
            }
            exifInterface2.saveAttributes();
        } catch (Exception e) {
            Log.e("ExifDataCopier", "Error preserving Exif data on selected image: " + e);
        }
    }

    private static void setIfNotNull(ExifInterface exifInterface, ExifInterface exifInterface2, String str) {
        if (exifInterface.getAttribute(str) != null) {
            exifInterface2.setAttribute(str, exifInterface.getAttribute(str));
        }
    }
}
