package com.sobot.chat.widget.zxing.multi.qrcode;

import com.sobot.chat.widget.zxing.BarcodeFormat;
import com.sobot.chat.widget.zxing.DecodeHintType;
import com.sobot.chat.widget.zxing.NotFoundException;
import com.sobot.chat.widget.zxing.ReaderException;
import com.sobot.chat.widget.zxing.ResultMetadataType;
import com.sobot.chat.widget.zxing.b;
import com.sobot.chat.widget.zxing.common.c;
import com.sobot.chat.widget.zxing.e;
import com.sobot.chat.widget.zxing.f;
import com.sobot.chat.widget.zxing.qrcode.a;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public final class QRCodeMultiReader extends a {
    private static final e[] a = new e[0];
    private static final f[] b = new f[0];

    public e[] a(b bVar) throws NotFoundException {
        return a(bVar, null);
    }

    public e[] a(b bVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        ArrayList arrayList = new ArrayList();
        com.sobot.chat.widget.zxing.common.e[] a2 = new com.sobot.chat.widget.zxing.multi.qrcode.detector.a(bVar.a()).a(map);
        for (com.sobot.chat.widget.zxing.common.e eVar : a2) {
            try {
                c a3 = a().a(eVar.a(), map);
                f[] b2 = eVar.b();
                if (a3.e() instanceof com.sobot.chat.widget.zxing.qrcode.decoder.f) {
                    ((com.sobot.chat.widget.zxing.qrcode.decoder.f) a3.e()).a(b2);
                }
                e eVar2 = new e(a3.b(), a3.a(), b2, BarcodeFormat.QR_CODE);
                List<byte[]> c = a3.c();
                if (c != null) {
                    eVar2.a(ResultMetadataType.BYTE_SEGMENTS, c);
                }
                String d = a3.d();
                if (d != null) {
                    eVar2.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, d);
                }
                if (a3.f()) {
                    eVar2.a(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(a3.h()));
                    eVar2.a(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(a3.g()));
                }
                arrayList.add(eVar2);
            } catch (ReaderException unused) {
            }
        }
        if (arrayList.isEmpty()) {
            return a;
        }
        return (e[]) a(arrayList).toArray(a);
    }

    static List<e> a(List<e> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList<e> arrayList2 = new ArrayList();
        for (e eVar : list) {
            if (eVar.c().containsKey(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)) {
                arrayList2.add(eVar);
            } else {
                arrayList.add(eVar);
            }
        }
        if (arrayList2.isEmpty()) {
            return list;
        }
        Collections.sort(arrayList2, new SAComparator());
        StringBuilder sb = new StringBuilder();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        for (e eVar2 : arrayList2) {
            sb.append(eVar2.a());
            byte[] b2 = eVar2.b();
            byteArrayOutputStream.write(b2, 0, b2.length);
            Iterable<byte[]> iterable = (Iterable) eVar2.c().get(ResultMetadataType.BYTE_SEGMENTS);
            if (iterable != null) {
                for (byte[] bArr : iterable) {
                    byteArrayOutputStream2.write(bArr, 0, bArr.length);
                }
            }
        }
        e eVar3 = new e(sb.toString(), byteArrayOutputStream.toByteArray(), b, BarcodeFormat.QR_CODE);
        if (byteArrayOutputStream2.size() > 0) {
            eVar3.a(ResultMetadataType.BYTE_SEGMENTS, Collections.singletonList(byteArrayOutputStream2.toByteArray()));
        }
        arrayList.add(eVar3);
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static final class SAComparator implements Serializable, Comparator<e> {
        private SAComparator() {
        }

        public int compare(e eVar, e eVar2) {
            return Integer.compare(((Integer) eVar.c().get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue(), ((Integer) eVar2.c().get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue());
        }
    }
}
