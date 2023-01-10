package com.google.android.exoplayer2.audio;

import android.media.MediaFormat;
import com.android.internal.logging.nano.MetricsProto;
import com.android.internal.midi.MidiConstants;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.o;
import com.tencent.smtt.sdk.TbsListener;
import java.nio.ByteBuffer;
import java.util.List;

/* compiled from: Ac3Util */
public final class a {
    private static final int[] a = {1, 2, 3, 6};
    private static final int[] b = {48000, 44100, 32000};
    private static final int[] c = {24000, 22050, 16000};
    private static final int[] d = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] e = {32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320, MetricsProto.MetricsEvent.ACTION_SHOW_SETTINGS_SUGGESTION, 448, 512, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT, 640};
    private static final int[] f = {69, 87, 104, 121, 139, 174, 208, 243, 278, MetricsProto.MetricsEvent.DATA_SAVER_SUMMARY, TbsListener.ErrorCode.INFO_TEMP_CORE_EXIST_CONF_ERROR, MetricsProto.MetricsEvent.ACTION_SUPPORT_DIAL_TOLLED, MetricsProto.MetricsEvent.DIALOG_SERVICE_ACCESS_WARNING, MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_PROCESS_OUTGOING_CALLS, MetricsProto.MetricsEvent.WIFI_NETWORK_RECOMMENDATION_SAVED_NETWORK_EVALUATOR, 975, MetricsProto.MetricsEvent.ACTION_TEXT_SELECTION_DRAG, 1253, 1393};

    public static int a() {
        return 1536;
    }

    /* compiled from: Ac3Util */
    /* renamed from: com.google.android.exoplayer2.audio.a$a  reason: collision with other inner class name */
    public static final class C0079a {
        public final String a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;

        private C0079a(String str, int i, int i2, int i3, int i4, int i5) {
            this.a = str;
            this.b = i;
            this.d = i2;
            this.c = i3;
            this.e = i4;
            this.f = i5;
        }
    }

    public static Format a(o oVar, String str, String str2, DrmInitData drmInitData) {
        int i = b[(oVar.h() & 192) >> 6];
        int h = oVar.h();
        int i2 = d[(h & 56) >> 3];
        if ((h & 4) != 0) {
            i2++;
        }
        return Format.a(str, MediaFormat.MIMETYPE_AUDIO_AC3, (String) null, -1, -1, i2, i, (List<byte[]>) null, drmInitData, 0, str2);
    }

    public static Format b(o oVar, String str, String str2, DrmInitData drmInitData) {
        oVar.d(2);
        int i = b[(oVar.h() & 192) >> 6];
        int h = oVar.h();
        int i2 = d[(h & 14) >> 1];
        if ((h & 1) != 0) {
            i2++;
        }
        if (((oVar.h() & 30) >> 1) > 0 && (2 & oVar.h()) != 0) {
            i2 += 2;
        }
        return Format.a(str, (oVar.b() <= 0 || (oVar.h() & 1) == 0) ? MediaFormat.MIMETYPE_AUDIO_EAC3 : "audio/eac3-joc", (String) null, -1, -1, i2, i, (List<byte[]>) null, drmInitData, 0, str2);
    }

    public static C0079a a(n nVar) {
        int i;
        int i2;
        int i3;
        int i4;
        String str;
        int i5;
        int i6;
        int i7;
        int b2 = nVar.b();
        nVar.b(40);
        boolean z = nVar.c(5) == 16;
        nVar.a(b2);
        int i8 = -1;
        if (z) {
            nVar.b(16);
            int c2 = nVar.c(2);
            if (c2 == 0) {
                i8 = 0;
            } else if (c2 == 1) {
                i8 = 1;
            } else if (c2 == 2) {
                i8 = 2;
            }
            nVar.b(3);
            i4 = (nVar.c(11) + 1) * 2;
            int c3 = nVar.c(2);
            if (c3 == 3) {
                i5 = 6;
                i3 = c[nVar.c(2)];
                i6 = 3;
            } else {
                i6 = nVar.c(2);
                i5 = a[i6];
                i3 = b[c3];
            }
            i2 = i5 * 256;
            int c4 = nVar.c(3);
            boolean e2 = nVar.e();
            i = d[c4] + (e2 ? 1 : 0);
            nVar.b(10);
            if (nVar.e()) {
                nVar.b(8);
            }
            if (c4 == 0) {
                nVar.b(5);
                if (nVar.e()) {
                    nVar.b(8);
                }
            }
            if (i8 == 1 && nVar.e()) {
                nVar.b(16);
            }
            if (nVar.e()) {
                if (c4 > 2) {
                    nVar.b(2);
                }
                if ((c4 & 1) != 0 && c4 > 2) {
                    nVar.b(6);
                }
                if ((c4 & 4) != 0) {
                    nVar.b(6);
                }
                if (e2 && nVar.e()) {
                    nVar.b(5);
                }
                if (i8 == 0) {
                    if (nVar.e()) {
                        nVar.b(6);
                    }
                    if (c4 == 0 && nVar.e()) {
                        nVar.b(6);
                    }
                    if (nVar.e()) {
                        nVar.b(6);
                    }
                    int c5 = nVar.c(2);
                    if (c5 == 1) {
                        nVar.b(5);
                    } else if (c5 == 2) {
                        nVar.b(12);
                    } else if (c5 == 3) {
                        int c6 = nVar.c(5);
                        if (nVar.e()) {
                            nVar.b(5);
                            if (nVar.e()) {
                                nVar.b(4);
                            }
                            if (nVar.e()) {
                                nVar.b(4);
                            }
                            if (nVar.e()) {
                                nVar.b(4);
                            }
                            if (nVar.e()) {
                                nVar.b(4);
                            }
                            if (nVar.e()) {
                                nVar.b(4);
                            }
                            if (nVar.e()) {
                                nVar.b(4);
                            }
                            if (nVar.e()) {
                                nVar.b(4);
                            }
                            if (nVar.e()) {
                                if (nVar.e()) {
                                    nVar.b(4);
                                }
                                if (nVar.e()) {
                                    nVar.b(4);
                                }
                            }
                        }
                        if (nVar.e()) {
                            nVar.b(5);
                            if (nVar.e()) {
                                nVar.b(7);
                                if (nVar.e()) {
                                    nVar.b(8);
                                }
                            }
                        }
                        nVar.b((c6 + 2) * 8);
                        nVar.f();
                    }
                    if (c4 < 2) {
                        if (nVar.e()) {
                            nVar.b(14);
                        }
                        if (c4 == 0 && nVar.e()) {
                            nVar.b(14);
                        }
                    }
                    if (nVar.e()) {
                        if (i6 == 0) {
                            nVar.b(5);
                        } else {
                            for (int i9 = 0; i9 < i5; i9++) {
                                if (nVar.e()) {
                                    nVar.b(5);
                                }
                            }
                        }
                    }
                }
            }
            if (nVar.e()) {
                nVar.b(5);
                if (c4 == 2) {
                    nVar.b(4);
                }
                if (c4 >= 6) {
                    nVar.b(2);
                }
                if (nVar.e()) {
                    nVar.b(8);
                }
                if (c4 == 0 && nVar.e()) {
                    nVar.b(8);
                }
                i7 = 3;
                if (c3 < 3) {
                    nVar.d();
                }
            } else {
                i7 = 3;
            }
            if (i8 == 0 && i6 != i7) {
                nVar.d();
            }
            if (i8 == 2 && (i6 == i7 || nVar.e())) {
                nVar.b(6);
            }
            str = (nVar.e() && nVar.c(6) == 1 && nVar.c(8) == 1) ? "audio/eac3-joc" : MediaFormat.MIMETYPE_AUDIO_EAC3;
        } else {
            nVar.b(32);
            int c7 = nVar.c(2);
            i4 = a(c7, nVar.c(6));
            nVar.b(8);
            int c8 = nVar.c(3);
            if (!((c8 & 1) == 0 || c8 == 1)) {
                nVar.b(2);
            }
            if ((c8 & 4) != 0) {
                nVar.b(2);
            }
            if (c8 == 2) {
                nVar.b(2);
            }
            i3 = b[c7];
            i2 = 1536;
            i = d[c8] + (nVar.e() ? 1 : 0);
            str = MediaFormat.MIMETYPE_AUDIO_AC3;
        }
        return new C0079a(str, i8, i, i3, i4, i2);
    }

    public static int a(byte[] bArr) {
        if (bArr.length < 6) {
            return -1;
        }
        if (!(((bArr[5] & 255) >> 3) == 16)) {
            return a((bArr[4] & 192) >> 6, bArr[4] & 63);
        }
        return (((bArr[3] & 255) | ((bArr[2] & 7) << 8)) + 1) * 2;
    }

    public static int a(ByteBuffer byteBuffer) {
        int i = 6;
        if (((byteBuffer.get(byteBuffer.position() + 4) & 192) >> 6) != 3) {
            i = a[(byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4];
        }
        return i * 256;
    }

    public static int b(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit() - 10;
        for (int i = position; i <= limit; i++) {
            if ((byteBuffer.getInt(i + 4) & -16777217) == -1167101192) {
                return i - position;
            }
        }
        return -1;
    }

    public static int b(byte[] bArr) {
        boolean z = false;
        if (bArr[4] != -8 || bArr[5] != 114 || bArr[6] != 111 || (bArr[7] & MidiConstants.STATUS_ACTIVE_SENSING) != 186) {
            return 0;
        }
        if ((bArr[7] & 255) == 187) {
            z = true;
        }
        return 40 << ((bArr[z ? (char) '\t' : '\b'] >> 4) & 7);
    }

    public static int a(ByteBuffer byteBuffer, int i) {
        return 40 << ((byteBuffer.get((byteBuffer.position() + i) + ((byteBuffer.get((byteBuffer.position() + i) + 7) & 255) == 187 ? 9 : 8)) >> 4) & 7);
    }

    private static int a(int i, int i2) {
        int i3 = i2 / 2;
        if (i < 0) {
            return -1;
        }
        int[] iArr = b;
        if (i >= iArr.length || i2 < 0) {
            return -1;
        }
        int[] iArr2 = f;
        if (i3 >= iArr2.length) {
            return -1;
        }
        int i4 = iArr[i];
        if (i4 == 44100) {
            return (iArr2[i3] + (i2 % 2)) * 2;
        }
        int i5 = e[i3];
        return i4 == 32000 ? i5 * 6 : i5 * 4;
    }
}
