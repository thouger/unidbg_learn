package com.google.android.exoplayer2.audio;

import android.hardware.camera2.legacy.LegacyCameraDevice;
import android.opengl.GLES30;
import android.text.format.DateUtils;
import com.android.internal.logging.nano.MetricsProto;
import com.android.internal.midi.MidiConstants;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.util.n;
import com.huawei.hms.support.api.entity.core.JosStatusCodes;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

/* compiled from: DtsUtil */
public final class k {
    private static final int[] a = {1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    private static final int[] b = {-1, JosStatusCodes.RTN_CODE_COMMON_ERROR, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
    private static final int[] c = {64, 112, 128, 192, 224, 256, MetricsProto.MetricsEvent.ACTION_SHOW_SETTINGS_SUGGESTION, 448, 512, 640, 768, 896, 1024, 1152, 1280, 1536, LegacyCameraDevice.MAX_DIMEN_FOR_ROUNDING, 2048, 2304, DateUtils.FORMAT_NO_NOON_MIDNIGHT, 2688, 2816, 2823, 2944, 3072, 3840, 4096, GLES30.GL_COLOR, 7680};

    public static boolean a(int i) {
        return i == 2147385345 || i == -25230976 || i == 536864768 || i == -14745368;
    }

    public static Format a(byte[] bArr, String str, String str2, DrmInitData drmInitData) {
        n c2 = c(bArr);
        c2.b(60);
        int i = a[c2.c(6)];
        int i2 = b[c2.c(4)];
        int c3 = c2.c(5);
        int[] iArr = c;
        int i3 = c3 >= iArr.length ? -1 : (iArr[c3] * 1000) / 2;
        c2.b(10);
        return Format.a(str, "audio/vnd.dts", (String) null, i3, -1, i + (c2.c(2) > 0 ? 1 : 0), i2, (List<byte[]>) null, drmInitData, 0, str2);
    }

    public static int a(byte[] bArr) {
        int i;
        byte b2;
        int i2;
        byte b3;
        byte b4 = bArr[0];
        if (b4 != -2) {
            if (b4 == -1) {
                i = (bArr[4] & 7) << 4;
                b3 = bArr[7];
            } else if (b4 != 31) {
                i = (bArr[4] & 1) << 6;
                b2 = bArr[5];
            } else {
                i = (bArr[5] & 7) << 4;
                b3 = bArr[6];
            }
            i2 = b3 & 60;
            return (((i2 >> 2) | i) + 1) * 32;
        }
        i = (bArr[5] & 1) << 6;
        b2 = bArr[4];
        i2 = b2 & MidiConstants.STATUS_STOP;
        return (((i2 >> 2) | i) + 1) * 32;
    }

    public static int a(ByteBuffer byteBuffer) {
        int i;
        byte b2;
        int i2;
        byte b3;
        int position = byteBuffer.position();
        byte b4 = byteBuffer.get(position);
        if (b4 != -2) {
            if (b4 == -1) {
                i = (byteBuffer.get(position + 4) & 7) << 4;
                b3 = byteBuffer.get(position + 7);
            } else if (b4 != 31) {
                i = (byteBuffer.get(position + 4) & 1) << 6;
                b2 = byteBuffer.get(position + 5);
            } else {
                i = (byteBuffer.get(position + 5) & 7) << 4;
                b3 = byteBuffer.get(position + 6);
            }
            i2 = b3 & 60;
            return (((i2 >> 2) | i) + 1) * 32;
        }
        i = (byteBuffer.get(position + 5) & 1) << 6;
        b2 = byteBuffer.get(position + 4);
        i2 = b2 & MidiConstants.STATUS_STOP;
        return (((i2 >> 2) | i) + 1) * 32;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int b(byte[] r7) {
        /*
            r0 = 0
            byte r1 = r7[r0]
            r2 = -2
            r3 = 7
            r4 = 6
            r5 = 1
            r6 = 4
            if (r1 == r2) goto L_0x004f
            r2 = -1
            if (r1 == r2) goto L_0x0037
            r2 = 31
            if (r1 == r2) goto L_0x0026
            r1 = 5
            byte r1 = r7[r1]
            r1 = r1 & 3
            int r1 = r1 << 12
            byte r2 = r7[r4]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r6
            r1 = r1 | r2
            byte r7 = r7[r3]
        L_0x0020:
            r7 = r7 & 240(0xf0, float:3.36E-43)
            int r7 = r7 >> r6
            r7 = r7 | r1
            int r7 = r7 + r5
            goto L_0x005e
        L_0x0026:
            byte r0 = r7[r4]
            r0 = r0 & 3
            int r0 = r0 << 12
            byte r1 = r7[r3]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r6
            r0 = r0 | r1
            r1 = 8
            byte r7 = r7[r1]
            goto L_0x0047
        L_0x0037:
            byte r0 = r7[r3]
            r0 = r0 & 3
            int r0 = r0 << 12
            byte r1 = r7[r4]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r6
            r0 = r0 | r1
            r1 = 9
            byte r7 = r7[r1]
        L_0x0047:
            r7 = r7 & 60
            int r7 = r7 >> 2
            r7 = r7 | r0
            int r7 = r7 + r5
            r0 = r5
            goto L_0x005e
        L_0x004f:
            byte r1 = r7[r6]
            r1 = r1 & 3
            int r1 = r1 << 12
            byte r2 = r7[r3]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r6
            r1 = r1 | r2
            byte r7 = r7[r4]
            goto L_0x0020
        L_0x005e:
            if (r0 == 0) goto L_0x0064
            int r7 = r7 * 16
            int r7 = r7 / 14
        L_0x0064:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.k.b(byte[]):int");
    }

    private static n c(byte[] bArr) {
        if (bArr[0] == Byte.MAX_VALUE) {
            return new n(bArr);
        }
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        if (d(copyOf)) {
            for (int i = 0; i < copyOf.length - 1; i += 2) {
                byte b2 = copyOf[i];
                int i2 = i + 1;
                copyOf[i] = copyOf[i2];
                copyOf[i2] = b2;
            }
        }
        n nVar = new n(copyOf);
        if (copyOf[0] == 31) {
            n nVar2 = new n(copyOf);
            while (nVar2.a() >= 16) {
                nVar2.b(2);
                nVar.a(nVar2.c(14), 14);
            }
        }
        nVar.a(copyOf);
        return nVar;
    }

    private static boolean d(byte[] bArr) {
        return bArr[0] == -2 || bArr[0] == -1;
    }
}
