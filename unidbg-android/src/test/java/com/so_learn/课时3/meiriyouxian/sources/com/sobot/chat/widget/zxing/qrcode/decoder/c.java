package com.sobot.chat.widget.zxing.qrcode.decoder;

import com.sobot.chat.widget.zxing.DecodeHintType;
import com.sobot.chat.widget.zxing.FormatException;
import com.sobot.chat.widget.zxing.common.CharacterSetECI;
import com.sobot.chat.widget.zxing.common.b;
import com.sobot.chat.widget.zxing.common.j;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* compiled from: DecodedBitStreamParser */
/* access modifiers changed from: package-private */
public final class c {
    private static final char[] a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    static com.sobot.chat.widget.zxing.common.c a(byte[] bArr, g gVar, ErrorCorrectionLevel errorCorrectionLevel, Map<DecodeHintType, ?> map) throws FormatException {
        Mode forBits;
        Mode mode;
        String str;
        b bVar = new b(bArr);
        StringBuilder sb = new StringBuilder(50);
        boolean z = true;
        ArrayList arrayList = new ArrayList(1);
        boolean z2 = false;
        int i = -1;
        int i2 = -1;
        CharacterSetECI characterSetECI = null;
        while (true) {
            try {
                if (bVar.a() < 4) {
                    forBits = Mode.TERMINATOR;
                } else {
                    forBits = Mode.forBits(bVar.a(4));
                }
                switch (forBits) {
                    case TERMINATOR:
                        mode = forBits;
                        break;
                    case FNC1_FIRST_POSITION:
                    case FNC1_SECOND_POSITION:
                        z2 = z;
                        mode = forBits;
                        break;
                    case STRUCTURED_APPEND:
                        if (bVar.a() >= 16) {
                            int a2 = bVar.a(8);
                            i2 = bVar.a(8);
                            i = a2;
                            mode = forBits;
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    case ECI:
                        characterSetECI = CharacterSetECI.getCharacterSetECIByValue(a(bVar));
                        if (characterSetECI != null) {
                            mode = forBits;
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    case HANZI:
                        int a3 = bVar.a(4);
                        int a4 = bVar.a(forBits.getCharacterCountBits(gVar));
                        if (a3 == z) {
                            a(bVar, sb, a4);
                        }
                        mode = forBits;
                        break;
                    default:
                        int a5 = bVar.a(forBits.getCharacterCountBits(gVar));
                        int i3 = AnonymousClass1.a[forBits.ordinal()];
                        if (i3 != z) {
                            if (i3 != 2) {
                                if (i3 == 3) {
                                    mode = forBits;
                                    a(bVar, sb, a5, characterSetECI, arrayList, map);
                                    break;
                                } else if (i3 == 4) {
                                    b(bVar, sb, a5);
                                    mode = forBits;
                                    break;
                                } else {
                                    throw FormatException.getFormatInstance();
                                }
                            } else {
                                mode = forBits;
                                a(bVar, sb, a5, z2);
                                break;
                            }
                        } else {
                            mode = forBits;
                            c(bVar, sb, a5);
                            break;
                        }
                }
                if (mode == Mode.TERMINATOR) {
                    String sb2 = sb.toString();
                    ArrayList arrayList2 = arrayList.isEmpty() ? null : arrayList;
                    if (errorCorrectionLevel == null) {
                        str = null;
                    } else {
                        str = errorCorrectionLevel.toString();
                    }
                    return new com.sobot.chat.widget.zxing.common.c(bArr, sb2, arrayList2, str, i, i2);
                }
                z = true;
            } catch (IllegalArgumentException unused) {
                throw FormatException.getFormatInstance();
            }
        }
    }

    private static void a(b bVar, StringBuilder sb, int i) throws FormatException {
        if (i * 13 <= bVar.a()) {
            byte[] bArr = new byte[(i * 2)];
            int i2 = 0;
            while (i > 0) {
                int a2 = bVar.a(13);
                int i3 = (a2 % 96) | ((a2 / 96) << 8);
                int i4 = i3 + (i3 < 2560 ? 41377 : 42657);
                bArr[i2] = (byte) ((i4 >> 8) & 255);
                bArr[i2 + 1] = (byte) (i4 & 255);
                i2 += 2;
                i--;
            }
            try {
                sb.append(new String(bArr, "GB2312"));
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static void b(b bVar, StringBuilder sb, int i) throws FormatException {
        if (i * 13 <= bVar.a()) {
            byte[] bArr = new byte[(i * 2)];
            int i2 = 0;
            while (i > 0) {
                int a2 = bVar.a(13);
                int i3 = (a2 % 192) | ((a2 / 192) << 8);
                int i4 = i3 + (i3 < 7936 ? 33088 : 49472);
                bArr[i2] = (byte) (i4 >> 8);
                bArr[i2 + 1] = (byte) i4;
                i2 += 2;
                i--;
            }
            try {
                sb.append(new String(bArr, "SJIS"));
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static void a(b bVar, StringBuilder sb, int i, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        String str;
        if (i * 8 <= bVar.a()) {
            byte[] bArr = new byte[i];
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = (byte) bVar.a(8);
            }
            if (characterSetECI == null) {
                str = j.a(bArr, map);
            } else {
                str = characterSetECI.name();
            }
            try {
                sb.append(new String(bArr, str));
                collection.add(bArr);
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static char a(int i) throws FormatException {
        char[] cArr = a;
        if (i < cArr.length) {
            return cArr[i];
        }
        throw FormatException.getFormatInstance();
    }

    private static void a(b bVar, StringBuilder sb, int i, boolean z) throws FormatException {
        while (i > 1) {
            if (bVar.a() >= 11) {
                int a2 = bVar.a(11);
                sb.append(a(a2 / 45));
                sb.append(a(a2 % 45));
                i -= 2;
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (i == 1) {
            if (bVar.a() >= 6) {
                sb.append(a(bVar.a(6)));
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (z) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i2 = length + 1;
                        if (sb.charAt(i2) == '%') {
                            sb.deleteCharAt(i2);
                        }
                    }
                    sb.setCharAt(length, 29);
                }
            }
        }
    }

    private static void c(b bVar, StringBuilder sb, int i) throws FormatException {
        while (i >= 3) {
            if (bVar.a() >= 10) {
                int a2 = bVar.a(10);
                if (a2 < 1000) {
                    sb.append(a(a2 / 100));
                    sb.append(a((a2 / 10) % 10));
                    sb.append(a(a2 % 10));
                    i -= 3;
                } else {
                    throw FormatException.getFormatInstance();
                }
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (i == 2) {
            if (bVar.a() >= 7) {
                int a3 = bVar.a(7);
                if (a3 < 100) {
                    sb.append(a(a3 / 10));
                    sb.append(a(a3 % 10));
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            throw FormatException.getFormatInstance();
        } else if (i != 1) {
        } else {
            if (bVar.a() >= 4) {
                int a4 = bVar.a(4);
                if (a4 < 10) {
                    sb.append(a(a4));
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            throw FormatException.getFormatInstance();
        }
    }

    private static int a(b bVar) throws FormatException {
        int a2 = bVar.a(8);
        if ((a2 & 128) == 0) {
            return a2 & 127;
        }
        if ((a2 & 192) == 128) {
            return bVar.a(8) | ((a2 & 63) << 8);
        }
        if ((a2 & 224) == 192) {
            return bVar.a(16) | ((a2 & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }
}
