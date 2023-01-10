package cn.missfresh.picture.internal.loader;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.loader.content.CursorLoader;
import cn.missfresh.picture.MimeType;
import cn.missfresh.picture.internal.entity.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class AlbumLoader extends CursorLoader {
    private static final Uri a = MediaStore.Files.getContentUri("external");
    private static final String[] b = {"_id", "bucket_id", "bucket_display_name", "mime_type", "uri", "count"};
    private static final String[] c = {"_id", "bucket_id", "bucket_display_name", "mime_type", "COUNT(*) AS count"};
    private static final String[] d = {"_id", "bucket_id", "bucket_display_name", "mime_type"};
    private static final String[] e = {String.valueOf(1), String.valueOf(3)};

    @Override // androidx.loader.content.Loader
    public void onContentChanged() {
    }

    static {
        AppMethodBeat.i(19355, false);
        AppMethodBeat.o(19355);
    }

    private static String[] a(int i) {
        AppMethodBeat.i(19336, false);
        String[] strArr = {String.valueOf(i)};
        AppMethodBeat.o(19336);
        return strArr;
    }

    private static String[] b(int i) {
        AppMethodBeat.i(19338, false);
        String[] strArr = {String.valueOf(i), "image/gif"};
        AppMethodBeat.o(19338);
        return strArr;
    }

    private static String[] c(int i) {
        AppMethodBeat.i(19339, false);
        String[] strArr = {String.valueOf(i), "image/jpeg", "image/png", "image/x-ms-bmp", "image/webp"};
        AppMethodBeat.o(19339);
        return strArr;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AlbumLoader(Context context, String str, String[] strArr) {
        super(context, a, a() ? c : d, str, strArr, "datetaken DESC");
        AppMethodBeat.i(19341, false);
        AppMethodBeat.o(19341);
    }

    public static CursorLoader a(Context context) {
        String[] strArr;
        String str;
        AppMethodBeat.i(19343, false);
        if (c.a().g()) {
            str = a() ? "media_type=? AND _size>0 AND mime_type=?) GROUP BY (bucket_id" : "media_type=? AND _size>0 AND mime_type=?";
            strArr = b(1);
        } else if (c.a().e()) {
            str = a() ? "media_type=? AND _size>0 AND (mime_type=? OR mime_type=? OR mime_type=? OR mime_type=?)) GROUP BY (bucket_id" : "media_type=? AND _size>0 AND (mime_type=? OR mime_type=? OR mime_type=? OR mime_type=?)";
            strArr = c(1);
        } else if (c.a().f()) {
            str = a() ? "media_type=? AND _size>0) GROUP BY (bucket_id" : "media_type=? AND _size>0";
            strArr = a(3);
        } else {
            str = a() ? "(media_type=? OR media_type=?) AND _size>0) GROUP BY (bucket_id" : "(media_type=? OR media_type=?) AND _size>0";
            strArr = e;
        }
        AlbumLoader albumLoader = new AlbumLoader(context, str, strArr);
        AppMethodBeat.o(19343);
        return albumLoader;
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x00b6: APUT  (r5v2 java.lang.String[]), (4 ??[int, float, short, byte, char]), (r16v3 java.lang.String) */
    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x01c0: APUT  (r0v2 java.lang.String[]), (4 ??[int, float, short, byte, char]), (r16v1 java.lang.String) */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x00b1  */
    @Override // androidx.loader.content.CursorLoader, androidx.loader.content.AsyncTaskLoader
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.database.Cursor loadInBackground() {
        /*
        // Method dump skipped, instructions count: 479
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.picture.internal.loader.AlbumLoader.loadInBackground():android.database.Cursor");
    }

    private static Uri a(Cursor cursor) {
        Uri uri;
        AppMethodBeat.i(19349, false);
        long j = cursor.getLong(cursor.getColumnIndex("_id"));
        String string = cursor.getString(cursor.getColumnIndex("mime_type"));
        if (MimeType.isImage(string)) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if (MimeType.isVideo(string)) {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else {
            uri = MediaStore.Files.getContentUri("external");
        }
        Uri withAppendedId = ContentUris.withAppendedId(uri, j);
        AppMethodBeat.o(19349);
        return withAppendedId;
    }

    private static boolean a() {
        return Build.VERSION.SDK_INT < 29;
    }
}
