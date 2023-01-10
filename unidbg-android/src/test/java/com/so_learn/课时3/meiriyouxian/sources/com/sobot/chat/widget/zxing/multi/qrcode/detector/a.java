package com.sobot.chat.widget.zxing.multi.qrcode.detector;

import com.sobot.chat.widget.zxing.DecodeHintType;
import com.sobot.chat.widget.zxing.NotFoundException;
import com.sobot.chat.widget.zxing.ReaderException;
import com.sobot.chat.widget.zxing.common.e;
import com.sobot.chat.widget.zxing.g;
import com.sobot.chat.widget.zxing.qrcode.detector.c;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: MultiDetector */
public final class a extends c {
    private static final e[] a = new e[0];

    public a(com.sobot.chat.widget.zxing.common.a aVar) {
        super(aVar);
    }

    public e[] a(Map<DecodeHintType, ?> map) throws NotFoundException {
        g gVar;
        com.sobot.chat.widget.zxing.common.a a2 = a();
        if (map == null) {
            gVar = null;
        } else {
            gVar = (g) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        com.sobot.chat.widget.zxing.qrcode.detector.e[] a3 = new MultiFinderPatternFinder(a2, gVar).a(map);
        if (a3.length != 0) {
            ArrayList arrayList = new ArrayList();
            for (com.sobot.chat.widget.zxing.qrcode.detector.e eVar : a3) {
                try {
                    arrayList.add(a(eVar));
                } catch (ReaderException unused) {
                }
            }
            if (arrayList.isEmpty()) {
                return a;
            }
            return (e[]) arrayList.toArray(a);
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
