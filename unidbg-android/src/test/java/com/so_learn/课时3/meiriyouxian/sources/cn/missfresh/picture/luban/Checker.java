package cn.missfresh.picture.luban;

import android.graphics.BitmapFactory;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* access modifiers changed from: package-private */
public enum Checker {
    SINGLE;
    
    private static final String JPG = ".jpg";
    private static final String TAG = "Luban";
    private final byte[] JPEG_SIGNATURE = {-1, -40, -1};

    private Checker() {
        AppMethodBeat.i(18243, false);
        AppMethodBeat.o(18243);
    }

    public static Checker valueOf(String str) {
        AppMethodBeat.i(18240, false);
        Checker checker = (Checker) Enum.valueOf(Checker.class, str);
        AppMethodBeat.o(18240);
        return checker;
    }

    static {
        AppMethodBeat.i(18260, false);
        AppMethodBeat.o(18260);
    }

    /* access modifiers changed from: package-private */
    public boolean isJPG(InputStream inputStream) {
        AppMethodBeat.i(18245, false);
        boolean isJPG = isJPG(toByteArray(inputStream));
        AppMethodBeat.o(18245);
        return isJPG;
    }

    /* access modifiers changed from: package-private */
    public int getOrientation(InputStream inputStream) {
        AppMethodBeat.i(18247, false);
        int orientation = getOrientation(toByteArray(inputStream));
        AppMethodBeat.o(18247);
        return orientation;
    }

    private boolean isJPG(byte[] bArr) {
        AppMethodBeat.i(18248, false);
        if (bArr == null || bArr.length < 3) {
            AppMethodBeat.o(18248);
            return false;
        }
        boolean equals = Arrays.equals(this.JPEG_SIGNATURE, new byte[]{bArr[0], bArr[1], bArr[2]});
        AppMethodBeat.o(18248);
        return equals;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0073, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0074, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0075, code lost:
        if (r3 <= 8) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0077, code lost:
        r4 = pack(r13, r2, 4, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x007e, code lost:
        if (r4 == 1229531648) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0083, code lost:
        if (r4 == 1296891946) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0085, code lost:
        android.util.Log.e(cn.missfresh.picture.luban.Checker.TAG, "Invalid byte order");
        cn.missfresh.sherlock.trace.core.AppMethodBeat.o(18250);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008e, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x008f, code lost:
        if (r4 != 1229531648) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0091, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0093, code lost:
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0094, code lost:
        r5 = pack(r13, r2 + 4, 4, r4) + 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x009d, code lost:
        if (r5 < 10) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x009f, code lost:
        if (r5 <= r3) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a2, code lost:
        r2 = r2 + r5;
        r3 = r3 - r5;
        r5 = pack(r13, r2 - 2, 2, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00aa, code lost:
        r10 = r5 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ac, code lost:
        if (r5 <= 0) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b0, code lost:
        if (r3 < 12) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b8, code lost:
        if (pack(r13, r2, 2, r4) != 274) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ba, code lost:
        r13 = pack(r13, r2 + 8, 2, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00bf, code lost:
        if (r13 == 1) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00c2, code lost:
        if (r13 == 3) goto L_0x00df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00c5, code lost:
        if (r13 == 6) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00c7, code lost:
        if (r13 == 8) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00c9, code lost:
        android.util.Log.e(cn.missfresh.picture.luban.Checker.TAG, "Unsupported orientation");
        cn.missfresh.sherlock.trace.core.AppMethodBeat.o(18250);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00d2, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00d3, code lost:
        cn.missfresh.sherlock.trace.core.AppMethodBeat.o(18250);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00d8, code lost:
        return 270;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00d9, code lost:
        cn.missfresh.sherlock.trace.core.AppMethodBeat.o(18250);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00de, code lost:
        return 90;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00df, code lost:
        cn.missfresh.sherlock.trace.core.AppMethodBeat.o(18250);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00e4, code lost:
        return 180;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00e5, code lost:
        cn.missfresh.sherlock.trace.core.AppMethodBeat.o(18250);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00e8, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00e9, code lost:
        r2 = r2 + 12;
        r3 = r3 - 12;
        r5 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00ef, code lost:
        android.util.Log.e(cn.missfresh.picture.luban.Checker.TAG, "Invalid offset");
        cn.missfresh.sherlock.trace.core.AppMethodBeat.o(18250);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00f8, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00f9, code lost:
        android.util.Log.e(cn.missfresh.picture.luban.Checker.TAG, "Orientation not found");
        cn.missfresh.sherlock.trace.core.AppMethodBeat.o(18250);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0102, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getOrientation(byte[] r13) {
        /*
        // Method dump skipped, instructions count: 259
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.picture.luban.Checker.getOrientation(byte[]):int");
    }

    /* access modifiers changed from: package-private */
    public String extSuffix(e eVar) {
        AppMethodBeat.i(18251, false);
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(eVar.a(), null, options);
            String replace = options.outMimeType.replace("image/", ".");
            AppMethodBeat.o(18251);
            return replace;
        } catch (Exception unused) {
            AppMethodBeat.o(18251);
            return JPG;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean needCompress(int i, String str) {
        boolean z = false;
        AppMethodBeat.i(18255, false);
        if (i > 0) {
            File file = new File(str);
            if (file.exists() && file.length() > ((long) (i << 10))) {
                z = true;
            }
            AppMethodBeat.o(18255);
            return z;
        }
        AppMethodBeat.o(18255);
        return true;
    }

    private int pack(byte[] bArr, int i, int i2, boolean z) {
        int i3;
        if (z) {
            i += i2 - 1;
            i3 = -1;
        } else {
            i3 = 1;
        }
        int i4 = 0;
        while (true) {
            int i5 = i2 - 1;
            if (i2 <= 0) {
                return i4;
            }
            i4 = (bArr[i] & 255) | (i4 << 8);
            i += i3;
            i2 = i5;
        }
    }

    private byte[] toByteArray(InputStream inputStream) {
        AppMethodBeat.i(18259, false);
        if (inputStream == null) {
            byte[] bArr = new byte[0];
            AppMethodBeat.o(18259);
            return bArr;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr2 = new byte[4096];
        while (true) {
            try {
                int read = inputStream.read(bArr2, 0, bArr2.length);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    try {
                        break;
                    } catch (IOException unused) {
                    }
                }
            } catch (Exception unused2) {
                byte[] bArr3 = new byte[0];
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused3) {
                }
                AppMethodBeat.o(18259);
                return bArr3;
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused4) {
                }
                AppMethodBeat.o(18259);
                throw th;
            }
        }
        byteArrayOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        AppMethodBeat.o(18259);
        return byteArray;
    }
}
