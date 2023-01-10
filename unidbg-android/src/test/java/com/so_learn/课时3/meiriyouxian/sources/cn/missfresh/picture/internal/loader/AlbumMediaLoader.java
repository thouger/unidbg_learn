package cn.missfresh.picture.internal.loader;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.hardware.camera2.legacy.LegacyCameraDevice;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.loader.content.CursorLoader;
import cn.missfresh.picture.internal.a.b;
import cn.missfresh.picture.internal.entity.Album;
import cn.missfresh.picture.internal.entity.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class AlbumMediaLoader extends CursorLoader {
    private static final Uri a = MediaStore.Files.getContentUri("external");
    private static final String[] b = {"_id", "_display_name", "mime_type", "_size", "duration", "width", "height"};
    private static final String[] c = {String.valueOf(1), String.valueOf(3)};
    private final boolean d;

    @Override // androidx.loader.content.Loader
    public void onContentChanged() {
    }

    static {
        AppMethodBeat.i(18809, false);
        AppMethodBeat.o(18809);
    }

    private static String[] a(int i) {
        AppMethodBeat.i(18769, false);
        String[] strArr = {String.valueOf(i)};
        AppMethodBeat.o(18769);
        return strArr;
    }

    private static String[] a(String str) {
        AppMethodBeat.i(18772, false);
        String[] strArr = {String.valueOf(1), String.valueOf(3), str};
        AppMethodBeat.o(18772);
        return strArr;
    }

    private static String[] a(int i, String str) {
        AppMethodBeat.i(18777, false);
        String[] strArr = {String.valueOf(i), str};
        AppMethodBeat.o(18777);
        return strArr;
    }

    private static String[] b(int i) {
        AppMethodBeat.i(18782, false);
        String[] strArr = {String.valueOf(i), "image/gif"};
        AppMethodBeat.o(18782);
        return strArr;
    }

    private static String[] c(int i) {
        AppMethodBeat.i(18786, false);
        String[] strArr = {String.valueOf(i), "image/jpeg", "image/png", "image/x-ms-bmp", "image/webp"};
        AppMethodBeat.o(18786);
        return strArr;
    }

    private static String[] b(int i, String str) {
        AppMethodBeat.i(18790, false);
        String[] strArr = {String.valueOf(i), str, "image/jpeg", "image/png", "image/x-ms-bmp", "image/webp"};
        AppMethodBeat.o(18790);
        return strArr;
    }

    private static String[] c(int i, String str) {
        AppMethodBeat.i(18792, false);
        String[] strArr = {String.valueOf(i), str, "image/gif"};
        AppMethodBeat.o(18792);
        return strArr;
    }

    private AlbumMediaLoader(Context context, String str, String[] strArr, boolean z) {
        super(context, a, b, str, strArr, "datetaken DESC");
        this.d = z;
    }

    public static CursorLoader a(Context context, Album album, boolean z) {
        String str;
        String[] strArr;
        String str2;
        boolean z2 = false;
        AppMethodBeat.i(18795, false);
        if (album.e()) {
            if (c.a().g()) {
                strArr = b(1);
                str2 = "media_type=? AND mime_type=? AND _size>0";
            } else if (c.a().e()) {
                strArr = c(1);
                str2 = "media_type=? AND (mime_type=? OR mime_type=? OR mime_type=? OR mime_type=?) AND _size>0";
            } else if (c.a().f()) {
                strArr = a(3);
                str2 = "media_type=? AND _size>0";
            } else {
                strArr = c;
                str2 = "(media_type=? OR media_type=?) AND _size>0";
            }
            z2 = z;
            str = str2;
        } else if (c.a().g()) {
            strArr = c(1, album.a());
            str = "media_type=? AND  bucket_id=? AND mime_type=? AND _size>0";
        } else if (c.a().e()) {
            strArr = b(1, album.a());
            str = "media_type=? AND  bucket_id=? AND (mime_type=? OR mime_type=? OR mime_type=? OR mime_type=?) AND _size>0";
        } else if (c.a().f()) {
            strArr = a(3, album.a());
            str = "media_type=? AND  bucket_id=? AND _size>0";
        } else {
            strArr = a(album.a());
            str = "(media_type=? OR media_type=?) AND  bucket_id=? AND _size>0";
        }
        AlbumMediaLoader albumMediaLoader = new AlbumMediaLoader(context, str, strArr, z2);
        AppMethodBeat.o(18795);
        return albumMediaLoader;
    }

    @Override // androidx.loader.content.CursorLoader, androidx.loader.content.AsyncTaskLoader
    public Cursor loadInBackground() {
        AppMethodBeat.i(18800, false);
        Cursor loadInBackground = super.loadInBackground();
        if (!this.d || !b.a(getContext())) {
            AppMethodBeat.o(18800);
            return loadInBackground;
        }
        MatrixCursor matrixCursor = new MatrixCursor(b);
        matrixCursor.addRow(new Object[]{-1L, "Capture", "", 0, 0, Integer.valueOf((int) BluetoothClass.Device.AUDIO_VIDEO_VIDEO_MONITOR), Integer.valueOf((int) LegacyCameraDevice.MAX_DIMEN_FOR_ROUNDING)});
        MergeCursor mergeCursor = new MergeCursor(new Cursor[]{matrixCursor, loadInBackground});
        AppMethodBeat.o(18800);
        return mergeCursor;
    }
}
