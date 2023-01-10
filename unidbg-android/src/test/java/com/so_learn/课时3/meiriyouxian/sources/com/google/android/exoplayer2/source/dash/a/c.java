package com.google.android.exoplayer2.source.dash.a;

import android.app.backup.FullBackup;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Pair;
import android.util.TimeUtils;
import android.util.Xml;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.android.exoplayer2.source.dash.a.j;
import com.google.android.exoplayer2.upstream.q;
import com.google.android.exoplayer2.util.aa;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.l;
import com.google.android.exoplayer2.util.y;
import com.google.android.exoplayer2.util.z;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

/* compiled from: DashManifestParser */
public class c extends DefaultHandler implements q.a<b> {
    private static final Pattern a = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final Pattern b = Pattern.compile("CC([1-4])=.*");
    private static final Pattern c = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    private final String d;
    private final XmlPullParserFactory e;

    public c() {
        this(null);
    }

    public c(String str) {
        this.d = str;
        try {
            this.e = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* renamed from: a */
    public b b(Uri uri, InputStream inputStream) throws IOException {
        try {
            XmlPullParser newPullParser = this.e.newPullParser();
            newPullParser.setInput(inputStream, null);
            if (newPullParser.next() == 2 && "MPD".equals(newPullParser.getName())) {
                return a(newPullParser, uri.toString());
            }
            throw new ParserException("inputStream does not contain a valid media presentation description");
        } catch (XmlPullParserException e) {
            throw new ParserException(e);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0182  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x018b A[LOOP:0: B:20:0x0075->B:67:0x018b, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x014d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.a.b a(org.xmlpull.v1.XmlPullParser r33, java.lang.String r34) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        // Method dump skipped, instructions count: 399
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.a.c.a(org.xmlpull.v1.XmlPullParser, java.lang.String):com.google.android.exoplayer2.source.dash.a.b");
    }

    /* access modifiers changed from: protected */
    public b a(long j, long j2, long j3, boolean z, long j4, long j5, long j6, long j7, g gVar, m mVar, Uri uri, List<f> list) {
        return new b(j, j2, j3, z, j4, j5, j6, j7, gVar, mVar, uri, list);
    }

    /* access modifiers changed from: protected */
    public m a(XmlPullParser xmlPullParser) {
        return a(xmlPullParser.getAttributeValue(null, "schemeIdUri"), xmlPullParser.getAttributeValue(null, "value"));
    }

    /* access modifiers changed from: protected */
    public m a(String str, String str2) {
        return new m(str, str2);
    }

    /* access modifiers changed from: protected */
    public Pair<f, Long> a(XmlPullParser xmlPullParser, String str, long j) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "id");
        long b2 = b(xmlPullParser, Telephony.BaseMmsColumns.START, j);
        long b3 = b(xmlPullParser, "duration", -9223372036854775807L);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        boolean z = false;
        j jVar = null;
        do {
            xmlPullParser.next();
            if (aa.b(xmlPullParser, "BaseURL")) {
                if (!z) {
                    str = c(xmlPullParser, str);
                    z = true;
                }
            } else if (aa.b(xmlPullParser, "AdaptationSet")) {
                arrayList.add(a(xmlPullParser, str, jVar));
            } else if (aa.b(xmlPullParser, "EventStream")) {
                arrayList2.add(f(xmlPullParser));
            } else if (aa.b(xmlPullParser, "SegmentBase")) {
                jVar = a(xmlPullParser, (j.e) null);
            } else if (aa.b(xmlPullParser, "SegmentList")) {
                jVar = a(xmlPullParser, (j.b) null);
            } else if (aa.b(xmlPullParser, "SegmentTemplate")) {
                jVar = a(xmlPullParser, (j.c) null);
            } else {
                l(xmlPullParser);
            }
        } while (!aa.a(xmlPullParser, "Period"));
        return Pair.create(a(attributeValue, b2, arrayList, arrayList2), Long.valueOf(b3));
    }

    /* access modifiers changed from: protected */
    public f a(String str, long j, List<a> list, List<e> list2) {
        return new f(str, j, list, list2);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x024a A[LOOP:0: B:1:0x0071->B:59:0x024a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0210 A[EDGE_INSN: B:60:0x0210->B:53:0x0210 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.a.a a(org.xmlpull.v1.XmlPullParser r41, java.lang.String r42, com.google.android.exoplayer2.source.dash.a.j r43) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        // Method dump skipped, instructions count: 604
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.a.c.a(org.xmlpull.v1.XmlPullParser, java.lang.String, com.google.android.exoplayer2.source.dash.a.j):com.google.android.exoplayer2.source.dash.a.a");
    }

    /* access modifiers changed from: protected */
    public a a(int i, int i2, List<i> list, List<d> list2, List<d> list3) {
        return new a(i, i2, list, list2, list3);
    }

    /* access modifiers changed from: protected */
    public int b(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "contentType");
        if (TextUtils.isEmpty(attributeValue)) {
            return -1;
        }
        if ("audio".equals(attributeValue)) {
            return 1;
        }
        if (MediaCodec.MetricsConstants.MODE_VIDEO.equals(attributeValue)) {
            return 2;
        }
        if ("text".equals(attributeValue)) {
            return 3;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public int a(Format format) {
        String str = format.g;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (l.b(str)) {
            return 2;
        }
        if (l.a(str)) {
            return 1;
        }
        if (a(str)) {
            return 3;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0152 A[LOOP:1: B:35:0x00a5->B:75:0x0152, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0140 A[EDGE_INSN: B:77:0x0140->B:70:0x0140 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.util.Pair<java.lang.String, com.google.android.exoplayer2.drm.DrmInitData.SchemeData> c(org.xmlpull.v1.XmlPullParser r17) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        // Method dump skipped, instructions count: 344
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.a.c.c(org.xmlpull.v1.XmlPullParser):android.util.Pair");
    }

    /* access modifiers changed from: protected */
    public int d(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String b2 = b(xmlPullParser, "schemeIdUri", (String) null);
        String b3 = b(xmlPullParser, "value", (String) null);
        do {
            xmlPullParser.next();
        } while (!aa.a(xmlPullParser, "Role"));
        return (!"urn:mpeg:dash:role:2011".equals(b2) || !"main".equals(b3)) ? 0 : 1;
    }

    /* access modifiers changed from: protected */
    public void e(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        l(xmlPullParser);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x015a A[LOOP:0: B:1:0x005f->B:43:0x015a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x011e A[EDGE_INSN: B:44:0x011e->B:37:0x011e ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.a.c.a a(org.xmlpull.v1.XmlPullParser r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, int r28, int r29, float r30, int r31, int r32, java.lang.String r33, int r34, java.util.List<com.google.android.exoplayer2.source.dash.a.d> r35, com.google.android.exoplayer2.source.dash.a.j r36) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        // Method dump skipped, instructions count: 358
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.a.c.a(org.xmlpull.v1.XmlPullParser, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, float, int, int, java.lang.String, int, java.util.List, com.google.android.exoplayer2.source.dash.a.j):com.google.android.exoplayer2.source.dash.a.c$a");
    }

    /* access modifiers changed from: protected */
    public Format a(String str, String str2, String str3, int i, int i2, float f, int i3, int i4, int i5, String str4, int i6, List<d> list, String str5, List<d> list2) {
        String str6;
        int b2;
        String b3 = b(str3, str5);
        if (b3 != null) {
            if (MediaFormat.MIMETYPE_AUDIO_EAC3.equals(b3)) {
                b3 = c(list2);
            }
            str6 = b3;
            if (l.b(str6)) {
                return Format.a(str, str2, str3, str6, str5, i5, i, i2, f, (List<byte[]>) null, i6);
            }
            if (l.a(str6)) {
                return Format.a(str, str2, str3, str6, str5, i5, i3, i4, (List<byte[]>) null, i6, str4);
            }
            if (a(str6)) {
                if ("application/cea-608".equals(str6)) {
                    b2 = a(list);
                } else {
                    b2 = "application/cea-708".equals(str6) ? b(list) : -1;
                }
                return Format.a(str, str2, str3, str6, str5, i5, i6, str4, b2);
            }
        } else {
            str6 = b3;
        }
        return Format.b(str, str2, str3, str6, str5, i5, i6, str4);
    }

    /* access modifiers changed from: protected */
    public i a(a aVar, String str, String str2, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<d> arrayList2) {
        Format format = aVar.a;
        if (aVar.d != null) {
            str2 = aVar.d;
        }
        ArrayList<DrmInitData.SchemeData> arrayList3 = aVar.e;
        arrayList3.addAll(arrayList);
        if (!arrayList3.isEmpty()) {
            a(arrayList3);
            format = format.a(new DrmInitData(str2, arrayList3));
        }
        ArrayList<d> arrayList4 = aVar.f;
        arrayList4.addAll(arrayList2);
        return i.a(str, aVar.g, format, aVar.b, aVar.c, arrayList4);
    }

    /* access modifiers changed from: protected */
    public j.e a(XmlPullParser xmlPullParser, j.e eVar) throws XmlPullParserException, IOException {
        long j;
        long j2;
        long d = d(xmlPullParser, "timescale", eVar != null ? eVar.b : 1);
        long j3 = 0;
        long d2 = d(xmlPullParser, "presentationTimeOffset", eVar != null ? eVar.c : 0);
        long j4 = eVar != null ? eVar.d : 0;
        if (eVar != null) {
            j3 = eVar.e;
        }
        h hVar = null;
        String attributeValue = xmlPullParser.getAttributeValue(null, "indexRange");
        if (attributeValue != null) {
            String[] split = attributeValue.split("-");
            long parseLong = Long.parseLong(split[0]);
            j = (Long.parseLong(split[1]) - parseLong) + 1;
            j2 = parseLong;
        } else {
            j = j3;
            j2 = j4;
        }
        if (eVar != null) {
            hVar = eVar.a;
        }
        do {
            xmlPullParser.next();
            if (aa.b(xmlPullParser, "Initialization")) {
                hVar = h(xmlPullParser);
            } else {
                l(xmlPullParser);
            }
        } while (!aa.a(xmlPullParser, "SegmentBase"));
        return a(hVar, d, d2, j2, j);
    }

    /* access modifiers changed from: protected */
    public j.e a(h hVar, long j, long j2, long j3, long j4) {
        return new j.e(hVar, j, j2, j3, j4);
    }

    /* access modifiers changed from: protected */
    public j.b a(XmlPullParser xmlPullParser, j.b bVar) throws XmlPullParserException, IOException {
        long j = 1;
        long d = d(xmlPullParser, "timescale", bVar != null ? bVar.b : 1);
        long d2 = d(xmlPullParser, "presentationTimeOffset", bVar != null ? bVar.c : 0);
        long d3 = d(xmlPullParser, "duration", bVar != null ? bVar.e : -9223372036854775807L);
        if (bVar != null) {
            j = bVar.d;
        }
        long d4 = d(xmlPullParser, "startNumber", j);
        List<h> list = null;
        h hVar = null;
        List<j.d> list2 = null;
        do {
            xmlPullParser.next();
            if (aa.b(xmlPullParser, "Initialization")) {
                hVar = h(xmlPullParser);
            } else if (aa.b(xmlPullParser, "SegmentTimeline")) {
                list2 = g(xmlPullParser);
            } else if (aa.b(xmlPullParser, "SegmentURL")) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(i(xmlPullParser));
            } else {
                l(xmlPullParser);
            }
        } while (!aa.a(xmlPullParser, "SegmentList"));
        if (bVar != null) {
            if (hVar == null) {
                hVar = bVar.a;
            }
            if (list2 == null) {
                list2 = bVar.f;
            }
            if (list == null) {
                list = bVar.g;
            }
        }
        return a(hVar, d, d2, d4, d3, list2, list);
    }

    /* access modifiers changed from: protected */
    public j.b a(h hVar, long j, long j2, long j3, long j4, List<j.d> list, List<h> list2) {
        return new j.b(hVar, j, j2, j3, j4, list, list2);
    }

    /* access modifiers changed from: protected */
    public j.c a(XmlPullParser xmlPullParser, j.c cVar) throws XmlPullParserException, IOException {
        long j = 1;
        long d = d(xmlPullParser, "timescale", cVar != null ? cVar.b : 1);
        long d2 = d(xmlPullParser, "presentationTimeOffset", cVar != null ? cVar.c : 0);
        long d3 = d(xmlPullParser, "duration", cVar != null ? cVar.e : -9223372036854775807L);
        if (cVar != null) {
            j = cVar.d;
        }
        long d4 = d(xmlPullParser, "startNumber", j);
        h hVar = null;
        l a2 = a(xmlPullParser, MediaStore.AUTHORITY, cVar != null ? cVar.h : null);
        l a3 = a(xmlPullParser, "initialization", cVar != null ? cVar.g : null);
        List<j.d> list = null;
        do {
            xmlPullParser.next();
            if (aa.b(xmlPullParser, "Initialization")) {
                hVar = h(xmlPullParser);
            } else if (aa.b(xmlPullParser, "SegmentTimeline")) {
                list = g(xmlPullParser);
            } else {
                l(xmlPullParser);
            }
        } while (!aa.a(xmlPullParser, "SegmentTemplate"));
        if (cVar != null) {
            if (hVar == null) {
                hVar = cVar.a;
            }
            if (list == null) {
                list = cVar.f;
            }
        }
        return a(hVar, d, d2, d4, d3, list, a3, a2);
    }

    /* access modifiers changed from: protected */
    public j.c a(h hVar, long j, long j2, long j3, long j4, List<j.d> list, l lVar, l lVar2) {
        return new j.c(hVar, j, j2, j3, j4, list, lVar, lVar2);
    }

    /* access modifiers changed from: protected */
    public e f(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String b2 = b(xmlPullParser, "schemeIdUri", "");
        String b3 = b(xmlPullParser, "value", "");
        long d = d(xmlPullParser, "timescale", 1);
        ArrayList arrayList = new ArrayList();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        do {
            xmlPullParser.next();
            if (aa.b(xmlPullParser, "Event")) {
                arrayList.add(a(xmlPullParser, b2, b3, d, byteArrayOutputStream));
            } else {
                l(xmlPullParser);
            }
        } while (!aa.a(xmlPullParser, "EventStream"));
        long[] jArr = new long[arrayList.size()];
        EventMessage[] eventMessageArr = new EventMessage[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            Pair pair = (Pair) arrayList.get(i);
            jArr[i] = pair.first.longValue();
            eventMessageArr[i] = pair.second;
        }
        return a(b2, b3, d, jArr, eventMessageArr);
    }

    /* access modifiers changed from: protected */
    public e a(String str, String str2, long j, long[] jArr, EventMessage[] eventMessageArr) {
        return new e(str, str2, j, jArr, eventMessageArr);
    }

    /* access modifiers changed from: protected */
    public Pair<Long, EventMessage> a(XmlPullParser xmlPullParser, String str, String str2, long j, ByteArrayOutputStream byteArrayOutputStream) throws IOException, XmlPullParserException {
        long d = d(xmlPullParser, "id", 0);
        long d2 = d(xmlPullParser, "duration", -9223372036854775807L);
        long d3 = d(xmlPullParser, "presentationTime", 0);
        long d4 = z.d(d2, 1000, j);
        long d5 = z.d(d3, TimeUtils.NANOS_PER_MS, j);
        String b2 = b(xmlPullParser, "messageData", (String) null);
        byte[] a2 = a(xmlPullParser, byteArrayOutputStream);
        Long valueOf = Long.valueOf(d5);
        if (b2 != null) {
            a2 = z.c(b2);
        }
        return Pair.create(valueOf, a(str, str2, d, d4, a2));
    }

    /* access modifiers changed from: protected */
    public byte[] a(XmlPullParser xmlPullParser, ByteArrayOutputStream byteArrayOutputStream) throws XmlPullParserException, IOException {
        byteArrayOutputStream.reset();
        XmlSerializer newSerializer = Xml.newSerializer();
        newSerializer.setOutput(byteArrayOutputStream, "UTF-8");
        xmlPullParser.nextToken();
        while (!aa.a(xmlPullParser, "Event")) {
            switch (xmlPullParser.getEventType()) {
                case 0:
                    newSerializer.startDocument(null, false);
                    break;
                case 1:
                    newSerializer.endDocument();
                    break;
                case 2:
                    newSerializer.startTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                    for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                        newSerializer.attribute(xmlPullParser.getAttributeNamespace(i), xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i));
                    }
                    break;
                case 3:
                    newSerializer.endTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                    break;
                case 4:
                    newSerializer.text(xmlPullParser.getText());
                    break;
                case 5:
                    newSerializer.cdsect(xmlPullParser.getText());
                    break;
                case 6:
                    newSerializer.entityRef(xmlPullParser.getText());
                    break;
                case 7:
                    newSerializer.ignorableWhitespace(xmlPullParser.getText());
                    break;
                case 8:
                    newSerializer.processingInstruction(xmlPullParser.getText());
                    break;
                case 9:
                    newSerializer.comment(xmlPullParser.getText());
                    break;
                case 10:
                    newSerializer.docdecl(xmlPullParser.getText());
                    break;
            }
            xmlPullParser.nextToken();
        }
        newSerializer.flush();
        return byteArrayOutputStream.toByteArray();
    }

    /* access modifiers changed from: protected */
    public EventMessage a(String str, String str2, long j, long j2, byte[] bArr) {
        return new EventMessage(str, str2, j2, j, bArr);
    }

    /* access modifiers changed from: protected */
    public List<j.d> g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        long j = 0;
        do {
            xmlPullParser.next();
            if (aa.b(xmlPullParser, ExifInterface.LATITUDE_SOUTH)) {
                j = d(xmlPullParser, "t", j);
                long d = d(xmlPullParser, "d", -9223372036854775807L);
                int a2 = a(xmlPullParser, FullBackup.ROOT_TREE_TOKEN, 0) + 1;
                for (int i = 0; i < a2; i++) {
                    arrayList.add(a(j, d));
                    j += d;
                }
            } else {
                l(xmlPullParser);
            }
        } while (!aa.a(xmlPullParser, "SegmentTimeline"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public j.d a(long j, long j2) {
        return new j.d(j, j2);
    }

    /* access modifiers changed from: protected */
    public l a(XmlPullParser xmlPullParser, String str, l lVar) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue != null ? l.a(attributeValue) : lVar;
    }

    /* access modifiers changed from: protected */
    public h h(XmlPullParser xmlPullParser) {
        return a(xmlPullParser, "sourceURL", "range");
    }

    /* access modifiers changed from: protected */
    public h i(XmlPullParser xmlPullParser) {
        return a(xmlPullParser, MediaStore.AUTHORITY, "mediaRange");
    }

    /* access modifiers changed from: protected */
    public h a(XmlPullParser xmlPullParser, String str, String str2) {
        long j;
        long j2;
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        String attributeValue2 = xmlPullParser.getAttributeValue(null, str2);
        if (attributeValue2 != null) {
            String[] split = attributeValue2.split("-");
            j2 = Long.parseLong(split[0]);
            if (split.length == 2) {
                j = (Long.parseLong(split[1]) - j2) + 1;
                return a(attributeValue, j2, j);
            }
        } else {
            j2 = 0;
        }
        j = -1;
        return a(attributeValue, j2, j);
    }

    /* access modifiers changed from: protected */
    public h a(String str, long j, long j2) {
        return new h(str, j, j2);
    }

    /* access modifiers changed from: protected */
    public g j(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        String str = null;
        String b2 = b(xmlPullParser, "moreInformationURL", (String) null);
        String b3 = b(xmlPullParser, "lang", (String) null);
        String str2 = null;
        String str3 = null;
        while (true) {
            xmlPullParser.next();
            if (aa.b(xmlPullParser, "Title")) {
                str = xmlPullParser.nextText();
            } else if (aa.b(xmlPullParser, "Source")) {
                str2 = xmlPullParser.nextText();
            } else if (aa.b(xmlPullParser, "Copyright")) {
                str3 = xmlPullParser.nextText();
            } else {
                l(xmlPullParser);
            }
            if (aa.a(xmlPullParser, "ProgramInformation")) {
                return new g(str, str2, str3, b2, b3);
            }
            str3 = str3;
        }
    }

    /* access modifiers changed from: protected */
    public int k(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String b2 = b(xmlPullParser, "schemeIdUri", (String) null);
        int i = -1;
        if ("urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(b2)) {
            i = a(xmlPullParser, "value", -1);
        } else if ("tag:dolby.com,2014:dash:audio_channel_configuration:2011".equals(b2)) {
            i = m(xmlPullParser);
        }
        do {
            xmlPullParser.next();
        } while (!aa.a(xmlPullParser, "AudioChannelConfiguration"));
        return i;
    }

    public static void l(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        if (aa.b(xmlPullParser)) {
            int i = 1;
            while (i != 0) {
                xmlPullParser.next();
                if (aa.b(xmlPullParser)) {
                    i++;
                } else if (aa.a(xmlPullParser)) {
                    i--;
                }
            }
        }
    }

    private static void a(ArrayList<DrmInitData.SchemeData> arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            DrmInitData.SchemeData schemeData = arrayList.get(size);
            if (!schemeData.a()) {
                int i = 0;
                while (true) {
                    if (i >= arrayList.size()) {
                        break;
                    } else if (arrayList.get(i).a(schemeData)) {
                        arrayList.remove(size);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    private static String b(String str, String str2) {
        if (l.a(str)) {
            return l.e(str2);
        }
        if (l.b(str)) {
            return l.d(str2);
        }
        if (a(str)) {
            return str;
        }
        if ("application/mp4".equals(str)) {
            if (str2 != null) {
                if (str2.startsWith("stpp")) {
                    return "application/ttml+xml";
                }
                if (str2.startsWith("wvtt")) {
                    return "application/x-mp4-vtt";
                }
            }
        } else if ("application/x-rawcc".equals(str) && str2 != null) {
            if (str2.contains("cea708")) {
                return "application/cea-708";
            }
            if (str2.contains("eia608") || str2.contains("cea608")) {
                return "application/cea-608";
            }
        }
        return null;
    }

    private static boolean a(String str) {
        return l.c(str) || "application/ttml+xml".equals(str) || "application/x-mp4-vtt".equals(str) || "application/cea-708".equals(str) || "application/cea-608".equals(str);
    }

    private static String c(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        com.google.android.exoplayer2.util.a.b(str.equals(str2));
        return str;
    }

    private static int a(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        com.google.android.exoplayer2.util.a.b(i == i2);
        return i;
    }

    protected static d b(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String b2 = b(xmlPullParser, "schemeIdUri", "");
        String b3 = b(xmlPullParser, "value", (String) null);
        String b4 = b(xmlPullParser, "id", (String) null);
        do {
            xmlPullParser.next();
        } while (!aa.a(xmlPullParser, str));
        return new d(b2, b3, b4);
    }

    protected static int a(List<d> list) {
        for (int i = 0; i < list.size(); i++) {
            d dVar = list.get(i);
            if ("urn:scte:dash:cc:cea-608:2015".equals(dVar.a) && dVar.b != null) {
                Matcher matcher = b.matcher(dVar.b);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                i.c("MpdParser", "Unable to parse CEA-608 channel number from: " + dVar.b);
            }
        }
        return -1;
    }

    protected static int b(List<d> list) {
        for (int i = 0; i < list.size(); i++) {
            d dVar = list.get(i);
            if ("urn:scte:dash:cc:cea-708:2015".equals(dVar.a) && dVar.b != null) {
                Matcher matcher = c.matcher(dVar.b);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                i.c("MpdParser", "Unable to parse CEA-708 service block number from: " + dVar.b);
            }
        }
        return -1;
    }

    protected static String c(List<d> list) {
        for (int i = 0; i < list.size(); i++) {
            d dVar = list.get(i);
            if ("tag:dolby.com,2014:dash:DolbyDigitalPlusExtensionType:2014".equals(dVar.a) && "ec+3".equals(dVar.b)) {
                return "audio/eac3-joc";
            }
        }
        return MediaFormat.MIMETYPE_AUDIO_EAC3;
    }

    protected static float a(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "frameRate");
        if (attributeValue == null) {
            return f;
        }
        Matcher matcher = a.matcher(attributeValue);
        if (!matcher.matches()) {
            return f;
        }
        int parseInt = Integer.parseInt(matcher.group(1));
        String group = matcher.group(2);
        return !TextUtils.isEmpty(group) ? ((float) parseInt) / ((float) Integer.parseInt(group)) : (float) parseInt;
    }

    protected static long b(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return j;
        }
        return z.f(attributeValue);
    }

    protected static long c(XmlPullParser xmlPullParser, String str, long j) throws ParserException {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return j;
        }
        return z.g(attributeValue);
    }

    protected static String c(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        xmlPullParser.next();
        return y.b(str, xmlPullParser.getText());
    }

    protected static int a(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? i : Integer.parseInt(attributeValue);
    }

    protected static long d(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Long.parseLong(attributeValue);
    }

    protected static String b(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? str2 : attributeValue;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    protected static int m(XmlPullParser xmlPullParser) {
        boolean z;
        String d = z.d(xmlPullParser.getAttributeValue(null, "value"));
        if (d == null) {
            return -1;
        }
        switch (d.hashCode()) {
            case 1596796:
                if (d.equals("4000")) {
                    z = false;
                    break;
                }
                z = true;
                break;
            case 2937391:
                if (d.equals("a000")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 3094035:
                if (d.equals("f801")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 3133436:
                if (d.equals("fa01")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            default:
                z = true;
                break;
        }
        if (!z) {
            return 1;
        }
        if (z) {
            return 2;
        }
        if (z) {
            return 6;
        }
        if (!z) {
            return -1;
        }
        return 8;
    }

    /* compiled from: DashManifestParser */
    /* access modifiers changed from: protected */
    public static final class a {
        public final Format a;
        public final String b;
        public final j c;
        public final String d;
        public final ArrayList<DrmInitData.SchemeData> e;
        public final ArrayList<d> f;
        public final long g;

        public a(Format format, String str, j jVar, String str2, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<d> arrayList2, long j) {
            this.a = format;
            this.b = str;
            this.c = jVar;
            this.d = str2;
            this.e = arrayList;
            this.f = arrayList2;
            this.g = j;
        }
    }
}
