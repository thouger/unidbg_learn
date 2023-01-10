package com.sobot.chat.widget.zxing.multi.qrcode.detector;

import com.android.internal.logging.nano.MetricsProto;
import com.sobot.chat.widget.zxing.DecodeHintType;
import com.sobot.chat.widget.zxing.NotFoundException;
import com.sobot.chat.widget.zxing.common.a;
import com.sobot.chat.widget.zxing.f;
import com.sobot.chat.widget.zxing.g;
import com.sobot.chat.widget.zxing.qrcode.detector.FinderPatternFinder;
import com.sobot.chat.widget.zxing.qrcode.detector.d;
import com.sobot.chat.widget.zxing.qrcode.detector.e;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class MultiFinderPatternFinder extends FinderPatternFinder {
    private static final e[] a = new e[0];
    private static final d[] b = new d[0];
    private static final d[][] c = new d[0][];

    /* access modifiers changed from: private */
    public static final class ModuleSizeComparator implements Serializable, Comparator<d> {
        private ModuleSizeComparator() {
        }

        public int compare(d dVar, d dVar2) {
            double c = (double) (dVar2.c() - dVar.c());
            if (c < 0.0d) {
                return -1;
            }
            return c > 0.0d ? 1 : 0;
        }
    }

    MultiFinderPatternFinder(a aVar, g gVar) {
        super(aVar, gVar);
    }

    private d[][] c() throws NotFoundException {
        List<d> b2 = b();
        int size = b2.size();
        int i = 3;
        if (size >= 3) {
            char c2 = 0;
            if (size == 3) {
                return new d[][]{(d[]) b2.toArray(b)};
            }
            Collections.sort(b2, new ModuleSizeComparator());
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            while (i2 < size - 2) {
                d dVar = b2.get(i2);
                if (dVar != null) {
                    int i3 = i2 + 1;
                    while (i3 < size - 1) {
                        d dVar2 = b2.get(i3);
                        if (dVar2 != null) {
                            float c3 = (dVar.c() - dVar2.c()) / Math.min(dVar.c(), dVar2.c());
                            float f = 0.5f;
                            float f2 = 0.05f;
                            if (Math.abs(dVar.c() - dVar2.c()) > 0.5f && c3 >= 0.05f) {
                                break;
                            }
                            int i4 = i3 + 1;
                            while (i4 < size) {
                                d dVar3 = b2.get(i4);
                                if (dVar3 != null) {
                                    float c4 = (dVar2.c() - dVar3.c()) / Math.min(dVar2.c(), dVar3.c());
                                    if (Math.abs(dVar2.c() - dVar3.c()) > f && c4 >= f2) {
                                        break;
                                    }
                                    d[] dVarArr = new d[i];
                                    dVarArr[c2] = dVar;
                                    dVarArr[1] = dVar2;
                                    dVarArr[2] = dVar3;
                                    f.a(dVarArr);
                                    e eVar = new e(dVarArr);
                                    float a2 = f.a(eVar.b(), eVar.a());
                                    float a3 = f.a(eVar.c(), eVar.a());
                                    float a4 = f.a(eVar.b(), eVar.c());
                                    float c5 = (a2 + a4) / (dVar.c() * 2.0f);
                                    if (c5 <= 180.0f && c5 >= 9.0f && Math.abs((a2 - a4) / Math.min(a2, a4)) < 0.1f) {
                                        double d = (double) a2;
                                        double d2 = (double) a4;
                                        float sqrt = (float) Math.sqrt((d * d) + (d2 * d2));
                                        if (Math.abs((a3 - sqrt) / Math.min(a3, sqrt)) < 0.1f) {
                                            arrayList.add(dVarArr);
                                        }
                                    }
                                }
                                i4++;
                                i = 3;
                                c2 = 0;
                                f = 0.5f;
                                f2 = 0.05f;
                            }
                        }
                        i3++;
                        i = 3;
                        c2 = 0;
                    }
                }
                i2++;
                i = 3;
                c2 = 0;
            }
            if (!arrayList.isEmpty()) {
                return (d[][]) arrayList.toArray(c);
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public e[] a(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        a a2 = a();
        int b2 = a2.b();
        int a3 = a2.a();
        int i = (b2 * 3) / MetricsProto.MetricsEvent.PREMIUM_SMS_ACCESS;
        if (i < 3 || z) {
            i = 3;
        }
        int[] iArr = new int[5];
        for (int i2 = i - 1; i2 < b2; i2 += i) {
            c(iArr);
            int i3 = 0;
            for (int i4 = 0; i4 < a3; i4++) {
                if (a2.a(i4, i2)) {
                    if ((i3 & 1) == 1) {
                        i3++;
                    }
                    iArr[i3] = iArr[i3] + 1;
                } else if ((i3 & 1) != 0) {
                    iArr[i3] = iArr[i3] + 1;
                } else if (i3 != 4) {
                    i3++;
                    iArr[i3] = iArr[i3] + 1;
                } else if (!a(iArr) || !a(iArr, i2, i4)) {
                    d(iArr);
                    i3 = 3;
                } else {
                    c(iArr);
                    i3 = 0;
                }
            }
            if (a(iArr)) {
                a(iArr, i2, a3);
            }
        }
        d[][] c2 = c();
        ArrayList arrayList = new ArrayList();
        for (d[] dVarArr : c2) {
            f.a(dVarArr);
            arrayList.add(new e(dVarArr));
        }
        if (arrayList.isEmpty()) {
            return a;
        }
        return (e[]) arrayList.toArray(a);
    }
}
