package com.google.android.exoplayer2.text.f;

import android.media.TtmlUtils;
import android.net.wifi.WifiEnterpriseConfig;
import android.provider.Telephony;
import android.text.Layout;
import android.text.style.SuggestionSpan;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.aa;
import com.google.android.exoplayer2.util.d;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.z;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: TtmlDecoder */
public final class a extends com.google.android.exoplayer2.text.b {
    private static final Pattern a = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final Pattern b = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern c = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern d = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final Pattern e = Pattern.compile("^(\\d+\\.?\\d*?)px (\\d+\\.?\\d*?)px$");
    private static final Pattern f = Pattern.compile("^(\\d+) (\\d+)$");
    private static final b g = new b(30.0f, 1, 1);
    private static final C0099a h = new C0099a(32, 15);
    private final XmlPullParserFactory i;

    public a() {
        super("TtmlDecoder");
        try {
            this.i = XmlPullParserFactory.newInstance();
            this.i.setNamespaceAware(true);
        } catch (XmlPullParserException e2) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e2);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public f a(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        c cVar;
        C0099a aVar;
        b bVar;
        f fVar;
        b bVar2;
        try {
            XmlPullParser newPullParser = this.i.newPullParser();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            c cVar2 = null;
            hashMap2.put("", new c(null));
            newPullParser.setInput(new ByteArrayInputStream(bArr, 0, i), null);
            ArrayDeque arrayDeque = new ArrayDeque();
            b bVar3 = g;
            C0099a aVar2 = h;
            int i2 = 0;
            f fVar2 = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.getEventType()) {
                b bVar4 = (b) arrayDeque.peek();
                if (i2 == 0) {
                    String name = newPullParser.getName();
                    if (eventType == 2) {
                        if (TtmlUtils.TAG_TT.equals(name)) {
                            b a2 = a(newPullParser);
                            aVar = a(newPullParser, h);
                            cVar = b(newPullParser);
                            bVar = a2;
                        } else {
                            cVar = cVar2;
                            bVar = bVar3;
                            aVar = aVar2;
                        }
                        if (!b(name)) {
                            i.b("TtmlDecoder", "Ignoring unsupported tag: " + newPullParser.getName());
                            i2++;
                            bVar3 = bVar;
                        } else {
                            if (TtmlUtils.TAG_HEAD.equals(name)) {
                                fVar = fVar2;
                                bVar2 = bVar;
                                a(newPullParser, hashMap, aVar, cVar, hashMap2, hashMap3);
                            } else {
                                fVar = fVar2;
                                bVar2 = bVar;
                                try {
                                    b a3 = a(newPullParser, bVar4, hashMap2, bVar2);
                                    arrayDeque.push(a3);
                                    if (bVar4 != null) {
                                        bVar4.a(a3);
                                    }
                                } catch (SubtitleDecoderException e2) {
                                    i.a("TtmlDecoder", "Suppressing parser error", e2);
                                    i2++;
                                }
                            }
                            bVar3 = bVar2;
                            fVar2 = fVar;
                        }
                    } else {
                        if (eventType == 4) {
                            bVar4.a(b.a(newPullParser.getText()));
                        } else if (eventType == 3) {
                            fVar2 = newPullParser.getName().equals(TtmlUtils.TAG_TT) ? new f((b) arrayDeque.peek(), hashMap, hashMap2, hashMap3) : fVar2;
                            arrayDeque.pop();
                            cVar = cVar2;
                            aVar = aVar2;
                        }
                        fVar2 = fVar2;
                        cVar = cVar2;
                        aVar = aVar2;
                    }
                    aVar2 = aVar;
                    cVar2 = cVar;
                } else {
                    if (eventType == 2) {
                        i2++;
                    } else if (eventType == 3) {
                        i2--;
                    }
                    fVar2 = fVar2;
                }
                newPullParser.next();
            }
            return fVar2;
        } catch (XmlPullParserException e3) {
            throw new SubtitleDecoderException("Unable to decode source", e3);
        } catch (IOException e4) {
            throw new IllegalStateException("Unexpected error when reading input.", e4);
        }
    }

    private b a(XmlPullParser xmlPullParser) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRate");
        int parseInt = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        float f2 = 1.0f;
        String attributeValue2 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRateMultiplier");
        if (attributeValue2 != null) {
            String[] a2 = z.a(attributeValue2, WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
            if (a2.length == 2) {
                f2 = ((float) Integer.parseInt(a2[0])) / ((float) Integer.parseInt(a2[1]));
            } else {
                throw new SubtitleDecoderException("frameRateMultiplier doesn't have 2 parts");
            }
        }
        int i = g.b;
        String attributeValue3 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "subFrameRate");
        if (attributeValue3 != null) {
            i = Integer.parseInt(attributeValue3);
        }
        int i2 = g.c;
        String attributeValue4 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "tickRate");
        if (attributeValue4 != null) {
            i2 = Integer.parseInt(attributeValue4);
        }
        return new b(((float) parseInt) * f2, i, i2);
    }

    private C0099a a(XmlPullParser xmlPullParser, C0099a aVar) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "cellResolution");
        if (attributeValue == null) {
            return aVar;
        }
        Matcher matcher = f.matcher(attributeValue);
        if (!matcher.matches()) {
            i.c("TtmlDecoder", "Ignoring malformed cell resolution: " + attributeValue);
            return aVar;
        }
        try {
            int parseInt = Integer.parseInt(matcher.group(1));
            int parseInt2 = Integer.parseInt(matcher.group(2));
            if (parseInt != 0 && parseInt2 != 0) {
                return new C0099a(parseInt, parseInt2);
            }
            throw new SubtitleDecoderException("Invalid cell resolution " + parseInt + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + parseInt2);
        } catch (NumberFormatException unused) {
            i.c("TtmlDecoder", "Ignoring malformed cell resolution: " + attributeValue);
            return aVar;
        }
    }

    private c b(XmlPullParser xmlPullParser) {
        String d2 = aa.d(xmlPullParser, "extent");
        if (d2 == null) {
            return null;
        }
        Matcher matcher = e.matcher(d2);
        if (!matcher.matches()) {
            i.c("TtmlDecoder", "Ignoring non-pixel tts extent: " + d2);
            return null;
        }
        try {
            return new c(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        } catch (NumberFormatException unused) {
            i.c("TtmlDecoder", "Ignoring malformed tts extent: " + d2);
            return null;
        }
    }

    private Map<String, e> a(XmlPullParser xmlPullParser, Map<String, e> map, C0099a aVar, c cVar, Map<String, c> map2, Map<String, String> map3) throws IOException, XmlPullParserException {
        do {
            xmlPullParser.next();
            if (aa.b(xmlPullParser, "style")) {
                String d2 = aa.d(xmlPullParser, "style");
                e a2 = a(xmlPullParser, new e());
                if (d2 != null) {
                    for (String str : a(d2)) {
                        a2.a(map.get(str));
                    }
                }
                if (a2.i() != null) {
                    map.put(a2.i(), a2);
                }
            } else if (aa.b(xmlPullParser, TtmlUtils.TAG_REGION)) {
                c a3 = a(xmlPullParser, aVar, cVar);
                if (a3 != null) {
                    map2.put(a3.a, a3);
                }
            } else if (aa.b(xmlPullParser, TtmlUtils.TAG_METADATA)) {
                a(xmlPullParser, map3);
            }
        } while (!aa.a(xmlPullParser, TtmlUtils.TAG_HEAD));
        return map;
    }

    private void a(XmlPullParser xmlPullParser, Map<String, String> map) throws IOException, XmlPullParserException {
        String d2;
        do {
            xmlPullParser.next();
            if (aa.b(xmlPullParser, "image") && (d2 = aa.d(xmlPullParser, "id")) != null) {
                map.put(d2, xmlPullParser.nextText());
            }
        } while (!aa.a(xmlPullParser, TtmlUtils.TAG_METADATA));
    }

    private c a(XmlPullParser xmlPullParser, C0099a aVar, c cVar) {
        float f2;
        float f3;
        float f4;
        float f5;
        int i;
        String d2 = aa.d(xmlPullParser, "id");
        if (d2 == null) {
            return null;
        }
        String d3 = aa.d(xmlPullParser, "origin");
        if (d3 != null) {
            Matcher matcher = d.matcher(d3);
            Matcher matcher2 = e.matcher(d3);
            if (matcher.matches()) {
                try {
                    float parseFloat = Float.parseFloat(matcher.group(1)) / 100.0f;
                    f2 = Float.parseFloat(matcher.group(2)) / 100.0f;
                    f3 = parseFloat;
                } catch (NumberFormatException unused) {
                    i.c("TtmlDecoder", "Ignoring region with malformed origin: " + d3);
                    return null;
                }
            } else if (!matcher2.matches()) {
                i.c("TtmlDecoder", "Ignoring region with unsupported origin: " + d3);
                return null;
            } else if (cVar == null) {
                i.c("TtmlDecoder", "Ignoring region with missing tts:extent: " + d3);
                return null;
            } else {
                try {
                    int parseInt = Integer.parseInt(matcher2.group(1));
                    int parseInt2 = Integer.parseInt(matcher2.group(2));
                    f3 = ((float) parseInt) / ((float) cVar.a);
                    f2 = ((float) parseInt2) / ((float) cVar.b);
                } catch (NumberFormatException unused2) {
                    i.c("TtmlDecoder", "Ignoring region with malformed origin: " + d3);
                    return null;
                }
            }
            String d4 = aa.d(xmlPullParser, "extent");
            if (d4 != null) {
                Matcher matcher3 = d.matcher(d4);
                Matcher matcher4 = e.matcher(d4);
                if (matcher3.matches()) {
                    try {
                        float parseFloat2 = Float.parseFloat(matcher3.group(1)) / 100.0f;
                        f5 = Float.parseFloat(matcher3.group(2)) / 100.0f;
                        f4 = parseFloat2;
                    } catch (NumberFormatException unused3) {
                        i.c("TtmlDecoder", "Ignoring region with malformed extent: " + d3);
                        return null;
                    }
                } else if (!matcher4.matches()) {
                    i.c("TtmlDecoder", "Ignoring region with unsupported extent: " + d3);
                    return null;
                } else if (cVar == null) {
                    i.c("TtmlDecoder", "Ignoring region with missing tts:extent: " + d3);
                    return null;
                } else {
                    try {
                        int parseInt3 = Integer.parseInt(matcher4.group(1));
                        int parseInt4 = Integer.parseInt(matcher4.group(2));
                        f4 = ((float) parseInt3) / ((float) cVar.a);
                        f5 = ((float) parseInt4) / ((float) cVar.b);
                    } catch (NumberFormatException unused4) {
                        i.c("TtmlDecoder", "Ignoring region with malformed extent: " + d3);
                        return null;
                    }
                }
                String d5 = aa.d(xmlPullParser, "displayAlign");
                if (d5 != null) {
                    String d6 = z.d(d5);
                    char c2 = '\uffff';
                    int hashCode = d6.hashCode();
                    if (hashCode != -1364013995) {
                        if (hashCode == 92734940 && d6.equals(SuggestionSpan.SUGGESTION_SPAN_PICKED_AFTER)) {
                            c2 = 1;
                        }
                    } else if (d6.equals("center")) {
                        c2 = 0;
                    }
                    if (c2 == 0) {
                        f2 += f5 / 2.0f;
                        i = 1;
                    } else if (c2 == 1) {
                        f2 += f5;
                        i = 2;
                    }
                    return new c(d2, f3, f2, 0, i, f4, 1, 1.0f / ((float) aVar.b));
                }
                i = 0;
                return new c(d2, f3, f2, 0, i, f4, 1, 1.0f / ((float) aVar.b));
            }
            i.c("TtmlDecoder", "Ignoring region without an extent");
            return null;
        }
        i.c("TtmlDecoder", "Ignoring region without an origin");
        return null;
    }

    private String[] a(String str) {
        String trim = str.trim();
        return trim.isEmpty() ? new String[0] : z.a(trim, "\\s+");
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private e a(XmlPullParser xmlPullParser, e eVar) {
        char c2;
        int attributeCount = xmlPullParser.getAttributeCount();
        e eVar2 = eVar;
        for (int i = 0; i < attributeCount; i++) {
            String attributeValue = xmlPullParser.getAttributeValue(i);
            String attributeName = xmlPullParser.getAttributeName(i);
            char c3 = '\uffff';
            switch (attributeName.hashCode()) {
                case -1550943582:
                    if (attributeName.equals("fontStyle")) {
                        c2 = 6;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -1224696685:
                    if (attributeName.equals("fontFamily")) {
                        c2 = 3;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -1065511464:
                    if (attributeName.equals("textAlign")) {
                        c2 = 7;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -879295043:
                    if (attributeName.equals("textDecoration")) {
                        c2 = '\b';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -734428249:
                    if (attributeName.equals("fontWeight")) {
                        c2 = 5;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 3355:
                    if (attributeName.equals("id")) {
                        c2 = 0;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 94842723:
                    if (attributeName.equals("color")) {
                        c2 = 2;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 365601008:
                    if (attributeName.equals("fontSize")) {
                        c2 = 4;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1287124693:
                    if (attributeName.equals("backgroundColor")) {
                        c2 = 1;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                default:
                    c2 = '\uffff';
                    break;
            }
            switch (c2) {
                case 0:
                    if ("style".equals(xmlPullParser.getName())) {
                        eVar2 = a(eVar2).b(attributeValue);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    eVar2 = a(eVar2);
                    try {
                        eVar2.b(d.a(attributeValue));
                        break;
                    } catch (IllegalArgumentException unused) {
                        i.c("TtmlDecoder", "Failed parsing background value: " + attributeValue);
                        break;
                    }
                case 2:
                    eVar2 = a(eVar2);
                    try {
                        eVar2.a(d.a(attributeValue));
                        break;
                    } catch (IllegalArgumentException unused2) {
                        i.c("TtmlDecoder", "Failed parsing color value: " + attributeValue);
                        break;
                    }
                case 3:
                    eVar2 = a(eVar2).a(attributeValue);
                    break;
                case 4:
                    try {
                        eVar2 = a(eVar2);
                        a(attributeValue, eVar2);
                        break;
                    } catch (SubtitleDecoderException unused3) {
                        i.c("TtmlDecoder", "Failed parsing fontSize value: " + attributeValue);
                        break;
                    }
                case 5:
                    eVar2 = a(eVar2).c("bold".equalsIgnoreCase(attributeValue));
                    break;
                case 6:
                    eVar2 = a(eVar2).d("italic".equalsIgnoreCase(attributeValue));
                    break;
                case 7:
                    String d2 = z.d(attributeValue);
                    switch (d2.hashCode()) {
                        case -1364013995:
                            if (d2.equals("center")) {
                                c3 = 4;
                                break;
                            }
                            break;
                        case 100571:
                            if (d2.equals("end")) {
                                c3 = 3;
                                break;
                            }
                            break;
                        case 3317767:
                            if (d2.equals("left")) {
                                c3 = 0;
                                break;
                            }
                            break;
                        case 108511772:
                            if (d2.equals("right")) {
                                c3 = 2;
                                break;
                            }
                            break;
                        case 109757538:
                            if (d2.equals(Telephony.BaseMmsColumns.START)) {
                                c3 = 1;
                                break;
                            }
                            break;
                    }
                    if (c3 != 0) {
                        if (c3 != 1) {
                            if (c3 != 2) {
                                if (c3 != 3) {
                                    if (c3 != 4) {
                                        break;
                                    } else {
                                        eVar2 = a(eVar2).a(Layout.Alignment.ALIGN_CENTER);
                                        break;
                                    }
                                } else {
                                    eVar2 = a(eVar2).a(Layout.Alignment.ALIGN_OPPOSITE);
                                    break;
                                }
                            } else {
                                eVar2 = a(eVar2).a(Layout.Alignment.ALIGN_OPPOSITE);
                                break;
                            }
                        } else {
                            eVar2 = a(eVar2).a(Layout.Alignment.ALIGN_NORMAL);
                            break;
                        }
                    } else {
                        eVar2 = a(eVar2).a(Layout.Alignment.ALIGN_NORMAL);
                        break;
                    }
                case '\b':
                    String d3 = z.d(attributeValue);
                    switch (d3.hashCode()) {
                        case -1461280213:
                            if (d3.equals("nounderline")) {
                                c3 = 3;
                                break;
                            }
                            break;
                        case -1026963764:
                            if (d3.equals("underline")) {
                                c3 = 2;
                                break;
                            }
                            break;
                        case 913457136:
                            if (d3.equals("nolinethrough")) {
                                c3 = 1;
                                break;
                            }
                            break;
                        case 1679736913:
                            if (d3.equals("linethrough")) {
                                c3 = 0;
                                break;
                            }
                            break;
                    }
                    if (c3 != 0) {
                        if (c3 != 1) {
                            if (c3 != 2) {
                                if (c3 != 3) {
                                    break;
                                } else {
                                    eVar2 = a(eVar2).b(false);
                                    break;
                                }
                            } else {
                                eVar2 = a(eVar2).b(true);
                                break;
                            }
                        } else {
                            eVar2 = a(eVar2).a(false);
                            break;
                        }
                    } else {
                        eVar2 = a(eVar2).a(true);
                        break;
                    }
            }
        }
        return eVar2;
    }

    private e a(e eVar) {
        return eVar == null ? new e() : eVar;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private b a(XmlPullParser xmlPullParser, b bVar, Map<String, c> map, b bVar2) throws SubtitleDecoderException {
        long j;
        long j2;
        char c2;
        int attributeCount = xmlPullParser.getAttributeCount();
        e a2 = a(xmlPullParser, (e) null);
        String str = null;
        String[] strArr = null;
        String str2 = "";
        long j3 = -9223372036854775807L;
        long j4 = -9223372036854775807L;
        long j5 = -9223372036854775807L;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            switch (attributeName.hashCode()) {
                case -934795532:
                    if (attributeName.equals(TtmlUtils.TAG_REGION)) {
                        c2 = 4;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 99841:
                    if (attributeName.equals(TtmlUtils.ATTR_DURATION)) {
                        c2 = 2;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 100571:
                    if (attributeName.equals("end")) {
                        c2 = 1;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 93616297:
                    if (attributeName.equals("begin")) {
                        c2 = 0;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 109780401:
                    if (attributeName.equals("style")) {
                        c2 = 3;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1292595405:
                    if (attributeName.equals("backgroundImage")) {
                        c2 = 5;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                default:
                    c2 = '\uffff';
                    break;
            }
            if (c2 == 0) {
                j3 = a(attributeValue, bVar2);
            } else if (c2 == 1) {
                j4 = a(attributeValue, bVar2);
            } else if (c2 == 2) {
                j5 = a(attributeValue, bVar2);
            } else if (c2 == 3) {
                String[] a3 = a(attributeValue);
                if (a3.length > 0) {
                    strArr = a3;
                }
            } else if (c2 != 4) {
                if (c2 == 5 && attributeValue.startsWith("#")) {
                    str = attributeValue.substring(1);
                }
            } else if (map.containsKey(attributeValue)) {
                str2 = attributeValue;
            }
        }
        if (bVar != null) {
            j = -9223372036854775807L;
            if (bVar.d != -9223372036854775807L) {
                if (j3 != -9223372036854775807L) {
                    j3 += bVar.d;
                }
                if (j4 != -9223372036854775807L) {
                    j4 += bVar.d;
                }
            }
        } else {
            j = -9223372036854775807L;
        }
        if (j4 == j) {
            if (j5 != j) {
                j2 = j5 + j3;
            } else if (!(bVar == null || bVar.e == j)) {
                j2 = bVar.e;
            }
            return b.a(xmlPullParser.getName(), j3, j2, a2, strArr, str2, str);
        }
        j2 = j4;
        return b.a(xmlPullParser.getName(), j3, j2, a2, strArr, str2, str);
    }

    private static boolean b(String str) {
        return str.equals(TtmlUtils.TAG_TT) || str.equals(TtmlUtils.TAG_HEAD) || str.equals("body") || str.equals(TtmlUtils.TAG_DIV) || str.equals("p") || str.equals(TtmlUtils.TAG_SPAN) || str.equals(TtmlUtils.TAG_BR) || str.equals("style") || str.equals(TtmlUtils.TAG_STYLING) || str.equals(TtmlUtils.TAG_LAYOUT) || str.equals(TtmlUtils.TAG_REGION) || str.equals(TtmlUtils.TAG_METADATA) || str.equals("image") || str.equals("data") || str.equals("information");
    }

    private static void a(String str, e eVar) throws SubtitleDecoderException {
        Matcher matcher;
        String[] a2 = z.a(str, "\\s+");
        if (a2.length == 1) {
            matcher = c.matcher(str);
        } else if (a2.length == 2) {
            matcher = c.matcher(a2[1]);
            i.c("TtmlDecoder", "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            throw new SubtitleDecoderException("Invalid number of entries for fontSize: " + a2.length + ".");
        }
        if (matcher.matches()) {
            String group = matcher.group(3);
            char c2 = '\uffff';
            int hashCode = group.hashCode();
            if (hashCode != 37) {
                if (hashCode != 3240) {
                    if (hashCode == 3592 && group.equals("px")) {
                        c2 = 0;
                    }
                } else if (group.equals("em")) {
                    c2 = 1;
                }
            } else if (group.equals("%")) {
                c2 = 2;
            }
            if (c2 == 0) {
                eVar.c(1);
            } else if (c2 == 1) {
                eVar.c(2);
            } else if (c2 == 2) {
                eVar.c(3);
            } else {
                throw new SubtitleDecoderException("Invalid unit for fontSize: '" + group + "'.");
            }
            eVar.a(Float.valueOf(matcher.group(1)).floatValue());
            return;
        }
        throw new SubtitleDecoderException("Invalid expression for fontSize: '" + str + "'.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x010b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long a(java.lang.String r14, com.google.android.exoplayer2.text.f.a.b r15) throws com.google.android.exoplayer2.text.SubtitleDecoderException {
        /*
        // Method dump skipped, instructions count: 300
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.f.a.a(java.lang.String, com.google.android.exoplayer2.text.f.a$b):long");
    }

    /* compiled from: TtmlDecoder */
    /* access modifiers changed from: private */
    public static final class b {
        final float a;
        final int b;
        final int c;

        b(float f, int i, int i2) {
            this.a = f;
            this.b = i;
            this.c = i2;
        }
    }

    /* compiled from: TtmlDecoder */
    /* access modifiers changed from: private */
    /* renamed from: com.google.android.exoplayer2.text.f.a$a  reason: collision with other inner class name */
    public static final class C0099a {
        final int a;
        final int b;

        C0099a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    /* compiled from: TtmlDecoder */
    /* access modifiers changed from: private */
    public static final class c {
        final int a;
        final int b;

        c(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }
}
