package com.google.android.exoplayer2.text.b;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Region;
import android.net.wifi.WifiNetworkScoreCache;
import android.util.SparseArray;
import com.android.internal.logging.nano.MetricsProto;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: DvbParser */
final class b {
    private static final byte[] a = {0, 7, 8, 15};
    private static final byte[] b = {0, 119, -120, -1};
    private static final byte[] c = {0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1};
    private final Paint d = new Paint();
    private final Paint e;
    private final Canvas f;
    private final C0097b g;
    private final a h;
    private final h i;
    private Bitmap j;

    private static int a(int i, int i2, int i3, int i4) {
        return (i << 24) | (i2 << 16) | (i3 << 8) | i4;
    }

    public b(int i, int i2) {
        this.d.setStyle(Paint.Style.FILL_AND_STROKE);
        this.d.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        this.d.setPathEffect(null);
        this.e = new Paint();
        this.e.setStyle(Paint.Style.FILL);
        this.e.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        this.e.setPathEffect(null);
        this.f = new Canvas();
        this.g = new C0097b(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_RECEIVE_WAP_PUSH, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_DISCONNECT, 0, MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_RECEIVE_WAP_PUSH, 0, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_DISCONNECT);
        this.h = new a(0, b(), c(), d());
        this.i = new h(i, i2);
    }

    public void a() {
        this.i.a();
    }

    public List<com.google.android.exoplayer2.text.a> a(byte[] bArr, int i) {
        int i2;
        int i3;
        SparseArray<g> sparseArray;
        n nVar = new n(bArr, i);
        while (nVar.a() >= 48 && nVar.c(8) == 15) {
            a(nVar, this.i);
        }
        if (this.i.i == null) {
            return Collections.emptyList();
        }
        C0097b bVar = this.i.h != null ? this.i.h : this.g;
        if (!(this.j != null && bVar.a + 1 == this.j.getWidth() && bVar.b + 1 == this.j.getHeight())) {
            this.j = Bitmap.createBitmap(bVar.a + 1, bVar.b + 1, Bitmap.Config.ARGB_8888);
            this.f.setBitmap(this.j);
        }
        ArrayList arrayList = new ArrayList();
        SparseArray<e> sparseArray2 = this.i.i.d;
        for (int i4 = 0; i4 < sparseArray2.size(); i4++) {
            e eVar = (e) sparseArray2.valueAt(i4);
            f fVar = (f) this.i.c.get(sparseArray2.keyAt(i4));
            int i5 = eVar.a + bVar.c;
            int i6 = eVar.b + bVar.e;
            float f2 = (float) i5;
            float f3 = (float) i6;
            this.f.clipRect(f2, f3, (float) Math.min(fVar.c + i5, bVar.d), (float) Math.min(fVar.d + i6, bVar.f), Region.Op.REPLACE);
            a aVar = (a) this.i.d.get(fVar.g);
            if (aVar == null && (aVar = (a) this.i.f.get(fVar.g)) == null) {
                aVar = this.h;
            }
            SparseArray<g> sparseArray3 = fVar.k;
            int i7 = 0;
            while (i7 < sparseArray3.size()) {
                int keyAt = sparseArray3.keyAt(i7);
                g gVar = (g) sparseArray3.valueAt(i7);
                c cVar = (c) this.i.e.get(keyAt);
                c cVar2 = cVar == null ? (c) this.i.g.get(keyAt) : cVar;
                if (cVar2 != null) {
                    i3 = i7;
                    sparseArray = sparseArray3;
                    a(cVar2, aVar, fVar.f, gVar.c + i5, i6 + gVar.d, cVar2.b ? null : this.d, this.f);
                } else {
                    i3 = i7;
                    sparseArray = sparseArray3;
                }
                i7 = i3 + 1;
                sparseArray3 = sparseArray;
            }
            if (fVar.b) {
                if (fVar.f == 3) {
                    i2 = aVar.d[fVar.h];
                } else if (fVar.f == 2) {
                    i2 = aVar.c[fVar.i];
                } else {
                    i2 = aVar.b[fVar.j];
                }
                this.e.setColor(i2);
                this.f.drawRect(f2, f3, (float) (fVar.c + i5), (float) (fVar.d + i6), this.e);
            }
            arrayList.add(new com.google.android.exoplayer2.text.a(Bitmap.createBitmap(this.j, i5, i6, fVar.c, fVar.d), f2 / ((float) bVar.a), 0, f3 / ((float) bVar.b), 0, ((float) fVar.c) / ((float) bVar.a), ((float) fVar.d) / ((float) bVar.b)));
            this.f.drawColor(0, PorterDuff.Mode.CLEAR);
        }
        return arrayList;
    }

    private static void a(n nVar, h hVar) {
        int c2 = nVar.c(8);
        int c3 = nVar.c(16);
        int c4 = nVar.c(16);
        int c5 = nVar.c() + c4;
        if (c4 * 8 > nVar.a()) {
            i.c("DvbParser", "Data field length exceeds limit");
            nVar.b(nVar.a());
            return;
        }
        switch (c2) {
            case 16:
                if (c3 == hVar.a) {
                    d dVar = hVar.i;
                    d a2 = a(nVar, c4);
                    if (a2.c == 0) {
                        if (!(dVar == null || dVar.b == a2.b)) {
                            hVar.i = a2;
                            break;
                        }
                    } else {
                        hVar.i = a2;
                        hVar.c.clear();
                        hVar.d.clear();
                        hVar.e.clear();
                        break;
                    }
                }
                break;
            case 17:
                d dVar2 = hVar.i;
                if (c3 == hVar.a && dVar2 != null) {
                    f b2 = b(nVar, c4);
                    if (dVar2.c == 0) {
                        b2.a((f) hVar.c.get(b2.a));
                    }
                    hVar.c.put(b2.a, b2);
                    break;
                }
            case 18:
                if (c3 != hVar.a) {
                    if (c3 == hVar.b) {
                        a c6 = c(nVar, c4);
                        hVar.f.put(c6.a, c6);
                        break;
                    }
                } else {
                    a c7 = c(nVar, c4);
                    hVar.d.put(c7.a, c7);
                    break;
                }
                break;
            case 19:
                if (c3 != hVar.a) {
                    if (c3 == hVar.b) {
                        c b3 = b(nVar);
                        hVar.g.put(b3.a, b3);
                        break;
                    }
                } else {
                    c b4 = b(nVar);
                    hVar.e.put(b4.a, b4);
                    break;
                }
                break;
            case 20:
                if (c3 == hVar.a) {
                    hVar.h = a(nVar);
                    break;
                }
                break;
        }
        nVar.d(c5 - nVar.c());
    }

    private static C0097b a(n nVar) {
        int i;
        int i2;
        int i3;
        int i4;
        nVar.b(4);
        boolean e2 = nVar.e();
        nVar.b(3);
        int c2 = nVar.c(16);
        int c3 = nVar.c(16);
        if (e2) {
            int c4 = nVar.c(16);
            int c5 = nVar.c(16);
            int c6 = nVar.c(16);
            i = nVar.c(16);
            i3 = c5;
            i2 = c6;
            i4 = c4;
        } else {
            i4 = 0;
            i2 = 0;
            i3 = c2;
            i = c3;
        }
        return new C0097b(c2, c3, i4, i3, i2, i);
    }

    private static d a(n nVar, int i) {
        int c2 = nVar.c(8);
        int c3 = nVar.c(4);
        int c4 = nVar.c(2);
        nVar.b(2);
        int i2 = i - 2;
        SparseArray sparseArray = new SparseArray();
        while (i2 > 0) {
            int c5 = nVar.c(8);
            nVar.b(8);
            i2 -= 6;
            sparseArray.put(c5, new e(nVar.c(16), nVar.c(16)));
        }
        return new d(c2, c3, c4, sparseArray);
    }

    private static f b(n nVar, int i) {
        int i2;
        int i3;
        int c2 = nVar.c(8);
        nVar.b(4);
        boolean e2 = nVar.e();
        nVar.b(3);
        int i4 = 16;
        int c3 = nVar.c(16);
        int c4 = nVar.c(16);
        int c5 = nVar.c(3);
        int c6 = nVar.c(3);
        int i5 = 2;
        nVar.b(2);
        int c7 = nVar.c(8);
        int c8 = nVar.c(8);
        int c9 = nVar.c(4);
        int c10 = nVar.c(2);
        nVar.b(2);
        int i6 = i - 10;
        SparseArray sparseArray = new SparseArray();
        while (i6 > 0) {
            int c11 = nVar.c(i4);
            int c12 = nVar.c(i5);
            int c13 = nVar.c(i5);
            int c14 = nVar.c(12);
            nVar.b(4);
            int c15 = nVar.c(12);
            i6 -= 6;
            if (c12 == 1 || c12 == 2) {
                i6 -= 2;
                i3 = nVar.c(8);
                i2 = nVar.c(8);
            } else {
                i3 = 0;
                i2 = 0;
            }
            sparseArray.put(c11, new g(c12, c13, c14, c15, i3, i2));
            c10 = c10;
            i5 = 2;
            i4 = 16;
        }
        return new f(c2, e2, c3, c4, c5, c6, c7, c8, c9, c10, sparseArray);
    }

    private static a c(n nVar, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = 8;
        int c2 = nVar.c(8);
        nVar.b(8);
        int i8 = 2;
        int i9 = i - 2;
        int[] b2 = b();
        int[] c3 = c();
        int[] d2 = d();
        while (i9 > 0) {
            int c4 = nVar.c(i7);
            int c5 = nVar.c(i7);
            int i10 = i9 - 2;
            int[] iArr = (c5 & 128) != 0 ? b2 : (c5 & 64) != 0 ? c3 : d2;
            if ((c5 & 1) != 0) {
                i5 = nVar.c(i7);
                i4 = nVar.c(i7);
                i3 = nVar.c(i7);
                i2 = nVar.c(i7);
                i6 = i10 - 4;
            } else {
                int c6 = nVar.c(4) << 4;
                i3 = nVar.c(4) << 4;
                i6 = i10 - 2;
                i2 = nVar.c(i8) << 6;
                i5 = nVar.c(6) << i8;
                i4 = c6;
            }
            if (i5 == 0) {
                i2 = 255;
                i4 = 0;
                i3 = 0;
            }
            double d3 = (double) i5;
            double d4 = (double) (i4 + WifiNetworkScoreCache.INVALID_NETWORK_SCORE);
            double d5 = (double) (i3 + WifiNetworkScoreCache.INVALID_NETWORK_SCORE);
            iArr[c4] = a((byte) (255 - (i2 & 255)), z.a((int) (d3 + (1.402d * d4)), 0, 255), z.a((int) ((d3 - (0.34414d * d5)) - (d4 * 0.71414d)), 0, 255), z.a((int) (d3 + (d5 * 1.772d)), 0, 255));
            i9 = i6;
            c2 = c2;
            i7 = 8;
            i8 = 2;
        }
        return new a(c2, b2, c3, d2);
    }

    private static c b(n nVar) {
        byte[] bArr;
        int c2 = nVar.c(16);
        nVar.b(4);
        int c3 = nVar.c(2);
        boolean e2 = nVar.e();
        nVar.b(1);
        byte[] bArr2 = null;
        if (c3 == 1) {
            nVar.b(nVar.c(8) * 16);
        } else if (c3 == 0) {
            int c4 = nVar.c(16);
            int c5 = nVar.c(16);
            if (c4 > 0) {
                bArr2 = new byte[c4];
                nVar.b(bArr2, 0, c4);
            }
            if (c5 > 0) {
                bArr = new byte[c5];
                nVar.b(bArr, 0, c5);
                return new c(c2, e2, bArr2, bArr);
            }
        }
        bArr = bArr2;
        return new c(c2, e2, bArr2, bArr);
    }

    private static int[] b() {
        return new int[]{0, -1, -16777216, -8421505};
    }

    private static int[] c() {
        int[] iArr = new int[16];
        iArr[0] = 0;
        for (int i = 1; i < iArr.length; i++) {
            if (i < 8) {
                iArr[i] = a(255, (i & 1) != 0 ? 255 : 0, (i & 2) != 0 ? 255 : 0, (i & 4) != 0 ? 255 : 0);
            } else {
                int i2 = 127;
                int i3 = (i & 1) != 0 ? 127 : 0;
                int i4 = (i & 2) != 0 ? 127 : 0;
                if ((i & 4) == 0) {
                    i2 = 0;
                }
                iArr[i] = a(255, i3, i4, i2);
            }
        }
        return iArr;
    }

    private static int[] d() {
        int[] iArr = new int[256];
        iArr[0] = 0;
        for (int i = 0; i < iArr.length; i++) {
            int i2 = 255;
            if (i < 8) {
                int i3 = (i & 1) != 0 ? 255 : 0;
                int i4 = (i & 2) != 0 ? 255 : 0;
                if ((i & 4) == 0) {
                    i2 = 0;
                }
                iArr[i] = a(63, i3, i4, i2);
            } else {
                int i5 = i & 136;
                int i6 = 170;
                int i7 = 85;
                if (i5 == 0) {
                    int i8 = ((i & 1) != 0 ? 85 : 0) + ((i & 16) != 0 ? 170 : 0);
                    int i9 = ((i & 2) != 0 ? 85 : 0) + ((i & 32) != 0 ? 170 : 0);
                    if ((i & 4) == 0) {
                        i7 = 0;
                    }
                    if ((i & 64) == 0) {
                        i6 = 0;
                    }
                    iArr[i] = a(255, i8, i9, i7 + i6);
                } else if (i5 != 8) {
                    int i10 = 43;
                    if (i5 == 128) {
                        int i11 = ((i & 1) != 0 ? 43 : 0) + 127 + ((i & 16) != 0 ? 85 : 0);
                        int i12 = ((i & 2) != 0 ? 43 : 0) + 127 + ((i & 32) != 0 ? 85 : 0);
                        if ((i & 4) == 0) {
                            i10 = 0;
                        }
                        int i13 = i10 + 127;
                        if ((i & 64) == 0) {
                            i7 = 0;
                        }
                        iArr[i] = a(255, i11, i12, i13 + i7);
                    } else if (i5 == 136) {
                        int i14 = ((i & 1) != 0 ? 43 : 0) + ((i & 16) != 0 ? 85 : 0);
                        int i15 = ((i & 2) != 0 ? 43 : 0) + ((i & 32) != 0 ? 85 : 0);
                        if ((i & 4) == 0) {
                            i10 = 0;
                        }
                        if ((i & 64) == 0) {
                            i7 = 0;
                        }
                        iArr[i] = a(255, i14, i15, i10 + i7);
                    }
                } else {
                    int i16 = ((i & 1) != 0 ? 85 : 0) + ((i & 16) != 0 ? 170 : 0);
                    int i17 = ((i & 2) != 0 ? 85 : 0) + ((i & 32) != 0 ? 170 : 0);
                    if ((i & 4) == 0) {
                        i7 = 0;
                    }
                    if ((i & 64) == 0) {
                        i6 = 0;
                    }
                    iArr[i] = a(127, i16, i17, i7 + i6);
                }
            }
        }
        return iArr;
    }

    private static void a(c cVar, a aVar, int i, int i2, int i3, Paint paint, Canvas canvas) {
        int[] iArr;
        if (i == 3) {
            iArr = aVar.d;
        } else if (i == 2) {
            iArr = aVar.c;
        } else {
            iArr = aVar.b;
        }
        a(cVar.c, iArr, i, i2, i3, paint, canvas);
        a(cVar.d, iArr, i, i2, i3 + 1, paint, canvas);
    }

    private static void a(byte[] bArr, int[] iArr, int i, int i2, int i3, Paint paint, Canvas canvas) {
        byte[] a2;
        byte[] bArr2;
        int a3;
        byte[] bArr3;
        n nVar = new n(bArr);
        int i4 = i2;
        int i5 = i3;
        byte[] bArr4 = null;
        byte[] bArr5 = null;
        while (nVar.a() != 0) {
            int c2 = nVar.c(8);
            if (c2 != 240) {
                switch (c2) {
                    case 16:
                        if (i != 3) {
                            if (i != 2) {
                                bArr2 = null;
                                a3 = a(nVar, iArr, bArr2, i4, i5, paint, canvas);
                                nVar.f();
                                i4 = a3;
                                break;
                            } else {
                                bArr3 = bArr5 == null ? a : bArr5;
                            }
                        } else {
                            bArr3 = bArr4 == null ? b : bArr4;
                        }
                        bArr2 = bArr3;
                        a3 = a(nVar, iArr, bArr2, i4, i5, paint, canvas);
                        nVar.f();
                        i4 = a3;
                    case 17:
                        a3 = b(nVar, iArr, i == 3 ? c : null, i4, i5, paint, canvas);
                        nVar.f();
                        i4 = a3;
                        break;
                    case 18:
                        a3 = c(nVar, iArr, null, i4, i5, paint, canvas);
                        i4 = a3;
                        break;
                    default:
                        switch (c2) {
                            case 32:
                                bArr5 = a(4, 4, nVar);
                                break;
                            case 33:
                                a2 = a(4, 8, nVar);
                                bArr4 = a2;
                                break;
                            case 34:
                                a2 = a(16, 8, nVar);
                                bArr4 = a2;
                                break;
                        }
                }
            } else {
                i5 += 2;
                i4 = i2;
            }
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:25:0x0064 */
    /* JADX DEBUG: Multi-variable search result rejected for r15v0, resolved type: byte[] */
    /* JADX DEBUG: Multi-variable search result rejected for r3v4, resolved type: byte */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2 */
    private static int a(n nVar, int[] iArr, byte[] bArr, int i, int i2, Paint paint, Canvas canvas) {
        boolean z;
        int i3;
        byte b2;
        boolean z2;
        boolean z3;
        int c2;
        int c3;
        int i4 = i;
        boolean z4 = false;
        while (true) {
            int c4 = nVar.c(2);
            if (c4 != 0) {
                z = z4;
                b2 = c4;
                i3 = 1;
            } else {
                if (nVar.e()) {
                    c2 = nVar.c(3) + 3;
                    c3 = nVar.c(2);
                } else {
                    if (nVar.e()) {
                        z3 = z4;
                        i3 = 1;
                    } else {
                        int c5 = nVar.c(2);
                        if (c5 == 0) {
                            z2 = true;
                        } else if (c5 == 1) {
                            z3 = z4;
                            i3 = 2;
                        } else if (c5 == 2) {
                            c2 = nVar.c(4) + 12;
                            c3 = nVar.c(2);
                        } else if (c5 != 3) {
                            z2 = z4;
                        } else {
                            c2 = nVar.c(8) + 29;
                            c3 = nVar.c(2);
                        }
                        b2 = 0;
                        i3 = 0;
                        z = z2;
                    }
                    b2 = 0;
                    z = z3;
                }
                z = z4;
                i3 = c2;
                b2 = c3;
            }
            if (!(i3 == 0 || paint == null)) {
                if (bArr != 0) {
                    b2 = bArr[b2];
                }
                paint.setColor(iArr[b2 == true ? 1 : 0]);
                canvas.drawRect((float) i4, (float) i2, (float) (i4 + i3), (float) (i2 + 1), paint);
            }
            i4 += i3;
            if (z) {
                return i4;
            }
            z4 = z;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:28:0x0070 */
    /* JADX DEBUG: Multi-variable search result rejected for r15v0, resolved type: byte[] */
    /* JADX DEBUG: Multi-variable search result rejected for r3v4, resolved type: byte */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2 */
    private static int b(n nVar, int[] iArr, byte[] bArr, int i, int i2, Paint paint, Canvas canvas) {
        boolean z;
        int i3;
        byte b2;
        boolean z2;
        boolean z3;
        int c2;
        int c3;
        int i4 = i;
        boolean z4 = false;
        while (true) {
            int c4 = nVar.c(4);
            if (c4 != 0) {
                z = z4;
                b2 = c4;
                i3 = 1;
            } else {
                if (!nVar.e()) {
                    int c5 = nVar.c(3);
                    if (c5 != 0) {
                        z3 = z4;
                        i3 = c5 + 2;
                    } else {
                        z2 = true;
                        b2 = 0;
                        i3 = 0;
                        z = z2;
                    }
                } else {
                    if (!nVar.e()) {
                        c2 = nVar.c(2) + 4;
                        c3 = nVar.c(4);
                    } else {
                        int c6 = nVar.c(2);
                        if (c6 == 0) {
                            z3 = z4;
                            i3 = 1;
                        } else if (c6 == 1) {
                            z3 = z4;
                            i3 = 2;
                        } else if (c6 == 2) {
                            c2 = nVar.c(4) + 9;
                            c3 = nVar.c(4);
                        } else if (c6 != 3) {
                            z2 = z4;
                            b2 = 0;
                            i3 = 0;
                            z = z2;
                        } else {
                            c2 = nVar.c(8) + 25;
                            c3 = nVar.c(4);
                        }
                    }
                    z = z4;
                    i3 = c2;
                    b2 = c3;
                }
                b2 = 0;
                z = z3;
            }
            if (!(i3 == 0 || paint == null)) {
                if (bArr != 0) {
                    b2 = bArr[b2];
                }
                paint.setColor(iArr[b2 == true ? 1 : 0]);
                canvas.drawRect((float) i4, (float) i2, (float) (i4 + i3), (float) (i2 + 1), paint);
            }
            i4 += i3;
            if (z) {
                return i4;
            }
            z4 = z;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:13:0x0039 */
    /* JADX DEBUG: Multi-variable search result rejected for r15v0, resolved type: byte[] */
    /* JADX DEBUG: Multi-variable search result rejected for r3v4, resolved type: byte */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2 */
    private static int c(n nVar, int[] iArr, byte[] bArr, int i, int i2, Paint paint, Canvas canvas) {
        boolean z;
        int i3;
        byte b2;
        int i4 = i;
        boolean z2 = false;
        while (true) {
            int c2 = nVar.c(8);
            if (c2 != 0) {
                z = z2;
                b2 = c2;
                i3 = 1;
            } else if (!nVar.e()) {
                int c3 = nVar.c(7);
                if (c3 != 0) {
                    z = z2;
                    i3 = c3;
                    b2 = 0;
                } else {
                    z = true;
                    b2 = 0;
                    i3 = 0;
                }
            } else {
                z = z2;
                i3 = nVar.c(7);
                b2 = nVar.c(8);
            }
            if (!(i3 == 0 || paint == null)) {
                if (bArr != 0) {
                    b2 = bArr[b2];
                }
                paint.setColor(iArr[b2 == true ? 1 : 0]);
                canvas.drawRect((float) i4, (float) i2, (float) (i4 + i3), (float) (i2 + 1), paint);
            }
            i4 += i3;
            if (z) {
                return i4;
            }
            z2 = z;
        }
    }

    private static byte[] a(int i, int i2, n nVar) {
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) nVar.c(i2);
        }
        return bArr;
    }

    /* compiled from: DvbParser */
    /* access modifiers changed from: private */
    public static final class h {
        public final int a;
        public final int b;
        public final SparseArray<f> c = new SparseArray<>();
        public final SparseArray<a> d = new SparseArray<>();
        public final SparseArray<c> e = new SparseArray<>();
        public final SparseArray<a> f = new SparseArray<>();
        public final SparseArray<c> g = new SparseArray<>();
        public C0097b h;
        public d i;

        public h(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public void a() {
            this.c.clear();
            this.d.clear();
            this.e.clear();
            this.f.clear();
            this.g.clear();
            this.h = null;
            this.i = null;
        }
    }

    /* compiled from: DvbParser */
    /* access modifiers changed from: private */
    /* renamed from: com.google.android.exoplayer2.text.b.b$b  reason: collision with other inner class name */
    public static final class C0097b {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;

        public C0097b(int i, int i2, int i3, int i4, int i5, int i6) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
        }
    }

    /* compiled from: DvbParser */
    /* access modifiers changed from: private */
    public static final class d {
        public final int a;
        public final int b;
        public final int c;
        public final SparseArray<e> d;

        public d(int i, int i2, int i3, SparseArray<e> sparseArray) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = sparseArray;
        }
    }

    /* compiled from: DvbParser */
    /* access modifiers changed from: private */
    public static final class e {
        public final int a;
        public final int b;

        public e(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    /* compiled from: DvbParser */
    /* access modifiers changed from: private */
    public static final class f {
        public final int a;
        public final boolean b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final int g;
        public final int h;
        public final int i;
        public final int j;
        public final SparseArray<g> k;

        public f(int i, boolean z, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, SparseArray<g> sparseArray) {
            this.a = i;
            this.b = z;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
            this.g = i6;
            this.h = i7;
            this.i = i8;
            this.j = i9;
            this.k = sparseArray;
        }

        public void a(f fVar) {
            if (fVar != null) {
                SparseArray<g> sparseArray = fVar.k;
                for (int i = 0; i < sparseArray.size(); i++) {
                    this.k.put(sparseArray.keyAt(i), sparseArray.valueAt(i));
                }
            }
        }
    }

    /* compiled from: DvbParser */
    /* access modifiers changed from: private */
    public static final class g {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;

        public g(int i, int i2, int i3, int i4, int i5, int i6) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
        }
    }

    /* compiled from: DvbParser */
    /* access modifiers changed from: private */
    public static final class a {
        public final int a;
        public final int[] b;
        public final int[] c;
        public final int[] d;

        public a(int i, int[] iArr, int[] iArr2, int[] iArr3) {
            this.a = i;
            this.b = iArr;
            this.c = iArr2;
            this.d = iArr3;
        }
    }

    /* compiled from: DvbParser */
    /* access modifiers changed from: private */
    public static final class c {
        public final int a;
        public final boolean b;
        public final byte[] c;
        public final byte[] d;

        public c(int i, boolean z, byte[] bArr, byte[] bArr2) {
            this.a = i;
            this.b = z;
            this.c = bArr;
            this.d = bArr2;
        }
    }
}
