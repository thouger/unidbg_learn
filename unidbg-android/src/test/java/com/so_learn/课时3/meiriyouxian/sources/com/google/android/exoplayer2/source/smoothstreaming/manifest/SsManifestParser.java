package com.google.android.exoplayer2.source.smoothstreaming.manifest;

import android.app.backup.FullBackup;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.net.Uri;
import android.net.wifi.WifiScanner;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.mp4.h;
import com.google.android.exoplayer2.extractor.mp4.k;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.a;
import com.google.android.exoplayer2.upstream.q;
import com.google.android.exoplayer2.util.z;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class SsManifestParser implements q.a<a> {
    private final XmlPullParserFactory a;

    public SsManifestParser() {
        try {
            this.a = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e2) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e2);
        }
    }

    /* renamed from: a */
    public a b(Uri uri, InputStream inputStream) throws IOException {
        try {
            XmlPullParser newPullParser = this.a.newPullParser();
            newPullParser.setInput(inputStream, null);
            return (a) new d(null, uri.toString()).a(newPullParser);
        } catch (XmlPullParserException e2) {
            throw new ParserException(e2);
        }
    }

    public static class MissingFieldException extends ParserException {
        public MissingFieldException(String str) {
            super("Missing required field: " + str);
        }
    }

    /* access modifiers changed from: private */
    public static abstract class a {
        private final String a;
        private final String b;
        private final a c;
        private final List<Pair<String, Object>> d = new LinkedList();

        /* access modifiers changed from: protected */
        public abstract Object a();

        /* access modifiers changed from: protected */
        public void a(Object obj) {
        }

        /* access modifiers changed from: protected */
        public void b(XmlPullParser xmlPullParser) throws ParserException {
        }

        /* access modifiers changed from: protected */
        public boolean b(String str) {
            return false;
        }

        /* access modifiers changed from: protected */
        public void c(XmlPullParser xmlPullParser) {
        }

        /* access modifiers changed from: protected */
        public void d(XmlPullParser xmlPullParser) {
        }

        public a(a aVar, String str, String str2) {
            this.c = aVar;
            this.a = str;
            this.b = str2;
        }

        public final Object a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            boolean z = false;
            int i = 0;
            while (true) {
                int eventType = xmlPullParser.getEventType();
                if (eventType == 1) {
                    return null;
                }
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    if (this.b.equals(name)) {
                        b(xmlPullParser);
                        z = true;
                    } else if (z) {
                        if (i > 0) {
                            i++;
                        } else if (b(name)) {
                            b(xmlPullParser);
                        } else {
                            a a = a(this, name, this.a);
                            if (a == null) {
                                i = 1;
                            } else {
                                a(a.a(xmlPullParser));
                            }
                        }
                    }
                } else if (eventType != 3) {
                    if (eventType == 4 && z && i == 0) {
                        c(xmlPullParser);
                    }
                } else if (!z) {
                    continue;
                } else if (i > 0) {
                    i--;
                } else {
                    String name2 = xmlPullParser.getName();
                    d(xmlPullParser);
                    if (!b(name2)) {
                        return a();
                    }
                }
                xmlPullParser.next();
            }
        }

        private a a(a aVar, String str, String str2) {
            if ("QualityLevel".equals(str)) {
                return new c(aVar, str2);
            }
            if ("Protection".equals(str)) {
                return new b(aVar, str2);
            }
            if ("StreamIndex".equals(str)) {
                return new e(aVar, str2);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public final void a(String str, Object obj) {
            this.d.add(Pair.create(str, obj));
        }

        /* access modifiers changed from: protected */
        public final Object a(String str) {
            for (int i = 0; i < this.d.size(); i++) {
                Pair<String, Object> pair = this.d.get(i);
                if (pair.first.equals(str)) {
                    return pair.second;
                }
            }
            a aVar = this.c;
            if (aVar == null) {
                return null;
            }
            return aVar.a(str);
        }

        /* access modifiers changed from: protected */
        public final String a(XmlPullParser xmlPullParser, String str) throws MissingFieldException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                return attributeValue;
            }
            throw new MissingFieldException(str);
        }

        /* access modifiers changed from: protected */
        public final int a(XmlPullParser xmlPullParser, String str, int i) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue == null) {
                return i;
            }
            try {
                return Integer.parseInt(attributeValue);
            } catch (NumberFormatException e) {
                throw new ParserException(e);
            }
        }

        /* access modifiers changed from: protected */
        public final int b(XmlPullParser xmlPullParser, String str) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                try {
                    return Integer.parseInt(attributeValue);
                } catch (NumberFormatException e) {
                    throw new ParserException(e);
                }
            } else {
                throw new MissingFieldException(str);
            }
        }

        /* access modifiers changed from: protected */
        public final long a(XmlPullParser xmlPullParser, String str, long j) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue == null) {
                return j;
            }
            try {
                return Long.parseLong(attributeValue);
            } catch (NumberFormatException e) {
                throw new ParserException(e);
            }
        }

        /* access modifiers changed from: protected */
        public final long c(XmlPullParser xmlPullParser, String str) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                try {
                    return Long.parseLong(attributeValue);
                } catch (NumberFormatException e) {
                    throw new ParserException(e);
                }
            } else {
                throw new MissingFieldException(str);
            }
        }

        /* access modifiers changed from: protected */
        public final boolean a(XmlPullParser xmlPullParser, String str, boolean z) {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            return attributeValue != null ? Boolean.parseBoolean(attributeValue) : z;
        }
    }

    /* access modifiers changed from: private */
    public static class d extends a {
        private final List<a.b> a = new LinkedList();
        private int b;
        private int c;
        private long d;
        private long e;
        private long f;
        private int g = -1;
        private boolean h;
        private a.C0094a i = null;

        public d(a aVar, String str) {
            super(aVar, str, "SmoothStreamingMedia");
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.a
        public void b(XmlPullParser xmlPullParser) throws ParserException {
            this.b = b(xmlPullParser, "MajorVersion");
            this.c = b(xmlPullParser, "MinorVersion");
            this.d = a(xmlPullParser, "TimeScale", 10000000L);
            this.e = c(xmlPullParser, "Duration");
            this.f = a(xmlPullParser, "DVRWindowLength", 0L);
            this.g = a(xmlPullParser, "LookaheadCount", -1);
            this.h = a(xmlPullParser, "IsLive", false);
            a("TimeScale", Long.valueOf(this.d));
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.a
        public void a(Object obj) {
            if (obj instanceof a.b) {
                this.a.add((a.b) obj);
            } else if (obj instanceof a.C0094a) {
                com.google.android.exoplayer2.util.a.b(this.i == null);
                this.i = (a.C0094a) obj;
            }
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.a
        public Object a() {
            a.b[] bVarArr = new a.b[this.a.size()];
            this.a.toArray(bVarArr);
            a.C0094a aVar = this.i;
            if (aVar != null) {
                DrmInitData drmInitData = new DrmInitData(new DrmInitData.SchemeData(aVar.a, "video/mp4", this.i.b));
                for (a.b bVar : bVarArr) {
                    int i = bVar.a;
                    if (i == 2 || i == 1) {
                        Format[] formatArr = bVar.j;
                        for (int i2 = 0; i2 < formatArr.length; i2++) {
                            formatArr[i2] = formatArr[i2].a(drmInitData);
                        }
                    }
                }
            }
            return new a(this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, bVarArr);
        }
    }

    /* access modifiers changed from: private */
    public static class b extends a {
        private boolean a;
        private UUID b;
        private byte[] c;

        public b(a aVar, String str) {
            super(aVar, str, "Protection");
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.a
        public boolean b(String str) {
            return "ProtectionHeader".equals(str);
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.a
        public void b(XmlPullParser xmlPullParser) {
            if ("ProtectionHeader".equals(xmlPullParser.getName())) {
                this.a = true;
                this.b = UUID.fromString(c(xmlPullParser.getAttributeValue(null, "SystemID")));
            }
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.a
        public void c(XmlPullParser xmlPullParser) {
            if (this.a) {
                this.c = Base64.decode(xmlPullParser.getText(), 0);
            }
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.a
        public void d(XmlPullParser xmlPullParser) {
            if ("ProtectionHeader".equals(xmlPullParser.getName())) {
                this.a = false;
            }
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.a
        public Object a() {
            UUID uuid = this.b;
            return new a.C0094a(uuid, h.a(uuid, this.c), a(this.c));
        }

        private static k[] a(byte[] bArr) {
            return new k[]{new k(true, null, 8, b(bArr), 0, 0, null)};
        }

        private static byte[] b(byte[] bArr) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bArr.length; i += 2) {
                sb.append((char) bArr[i]);
            }
            String sb2 = sb.toString();
            byte[] decode = Base64.decode(sb2.substring(sb2.indexOf("<KID>") + 5, sb2.indexOf("</KID>")), 0);
            a(decode, 0, 3);
            a(decode, 1, 2);
            a(decode, 4, 5);
            a(decode, 6, 7);
            return decode;
        }

        private static void a(byte[] bArr, int i, int i2) {
            byte b = bArr[i];
            bArr[i] = bArr[i2];
            bArr[i2] = b;
        }

        private static String c(String str) {
            return (str.charAt(0) == '{' && str.charAt(str.length() - 1) == '}') ? str.substring(1, str.length() - 1) : str;
        }
    }

    /* access modifiers changed from: private */
    public static class e extends a {
        private final String a;
        private final List<Format> b = new LinkedList();
        private int c;
        private String d;
        private long e;
        private String f;
        private String g;
        private int h;
        private int i;
        private int j;
        private int k;
        private String l;
        private ArrayList<Long> m;
        private long n;

        public e(a aVar, String str) {
            super(aVar, str, "StreamIndex");
            this.a = str;
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.a
        public boolean b(String str) {
            return "c".equals(str);
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.a
        public void b(XmlPullParser xmlPullParser) throws ParserException {
            if ("c".equals(xmlPullParser.getName())) {
                e(xmlPullParser);
            } else {
                f(xmlPullParser);
            }
        }

        private void e(XmlPullParser xmlPullParser) throws ParserException {
            int size = this.m.size();
            long a = a(xmlPullParser, "t", -9223372036854775807L);
            int i = 1;
            if (a == -9223372036854775807L) {
                if (size == 0) {
                    a = 0;
                } else if (this.n != -1) {
                    a = this.m.get(size - 1).longValue() + this.n;
                } else {
                    throw new ParserException("Unable to infer start time");
                }
            }
            this.m.add(Long.valueOf(a));
            this.n = a(xmlPullParser, "d", -9223372036854775807L);
            long a2 = a(xmlPullParser, FullBackup.ROOT_TREE_TOKEN, 1L);
            if (a2 <= 1 || this.n != -9223372036854775807L) {
                while (true) {
                    long j = (long) i;
                    if (j < a2) {
                        this.m.add(Long.valueOf((this.n * j) + a));
                        i++;
                    } else {
                        return;
                    }
                }
            } else {
                throw new ParserException("Repeated chunk with unspecified duration");
            }
        }

        private void f(XmlPullParser xmlPullParser) throws ParserException {
            this.c = g(xmlPullParser);
            a("Type", Integer.valueOf(this.c));
            if (this.c == 3) {
                this.d = a(xmlPullParser, "Subtype");
            } else {
                this.d = xmlPullParser.getAttributeValue(null, "Subtype");
            }
            this.f = xmlPullParser.getAttributeValue(null, "Name");
            this.g = a(xmlPullParser, "Url");
            this.h = a(xmlPullParser, "MaxWidth", -1);
            this.i = a(xmlPullParser, "MaxHeight", -1);
            this.j = a(xmlPullParser, "DisplayWidth", -1);
            this.k = a(xmlPullParser, "DisplayHeight", -1);
            this.l = xmlPullParser.getAttributeValue(null, "Language");
            a("Language", this.l);
            this.e = (long) a(xmlPullParser, "TimeScale", -1);
            if (this.e == -1) {
                this.e = ((Long) a("TimeScale")).longValue();
            }
            this.m = new ArrayList<>();
        }

        private int g(XmlPullParser xmlPullParser) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, "Type");
            if (attributeValue == null) {
                throw new MissingFieldException("Type");
            } else if ("audio".equalsIgnoreCase(attributeValue)) {
                return 1;
            } else {
                if (MediaCodec.MetricsConstants.MODE_VIDEO.equalsIgnoreCase(attributeValue)) {
                    return 2;
                }
                if ("text".equalsIgnoreCase(attributeValue)) {
                    return 3;
                }
                throw new ParserException("Invalid key value[" + attributeValue + "]");
            }
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.a
        public void a(Object obj) {
            if (obj instanceof Format) {
                this.b.add((Format) obj);
            }
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.a
        public Object a() {
            Format[] formatArr = new Format[this.b.size()];
            this.b.toArray(formatArr);
            return new a.b(this.a, this.g, this.c, this.d, this.e, this.f, this.h, this.i, this.j, this.k, this.l, formatArr, this.m, this.n);
        }
    }

    /* access modifiers changed from: private */
    public static class c extends a {
        private Format a;

        public c(a aVar, String str) {
            super(aVar, str, "QualityLevel");
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.a
        public void b(XmlPullParser xmlPullParser) throws ParserException {
            int intValue = ((Integer) a("Type")).intValue();
            String attributeValue = xmlPullParser.getAttributeValue(null, "Index");
            String str = (String) a("Name");
            int b = b(xmlPullParser, "Bitrate");
            String d = d(a(xmlPullParser, "FourCC"));
            if (intValue == 2) {
                this.a = Format.a(attributeValue, str, "video/mp4", d, (String) null, b, b(xmlPullParser, "MaxWidth"), b(xmlPullParser, "MaxHeight"), -1.0f, c(xmlPullParser.getAttributeValue(null, "CodecPrivateData")), 0);
            } else if (intValue == 1) {
                if (d == null) {
                    d = MediaFormat.MIMETYPE_AUDIO_AAC;
                }
                int b2 = b(xmlPullParser, WifiScanner.GET_AVAILABLE_CHANNELS_EXTRA);
                int b3 = b(xmlPullParser, "SamplingRate");
                List<byte[]> c = c(xmlPullParser.getAttributeValue(null, "CodecPrivateData"));
                if (c.isEmpty() && MediaFormat.MIMETYPE_AUDIO_AAC.equals(d)) {
                    c = Collections.singletonList(com.google.android.exoplayer2.util.c.a(b3, b2));
                }
                this.a = Format.a(attributeValue, str, "audio/mp4", d, (String) null, b, b2, b3, c, 0, (String) a("Language"));
            } else if (intValue == 3) {
                this.a = Format.a(attributeValue, str, "application/mp4", d, (String) null, b, 0, (String) a("Language"));
            } else {
                this.a = Format.b(attributeValue, str, "application/mp4", d, null, b, 0, null);
            }
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.a
        public Object a() {
            return this.a;
        }

        private static List<byte[]> c(String str) {
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(str)) {
                byte[] i = z.i(str);
                byte[][] b = com.google.android.exoplayer2.util.c.b(i);
                if (b == null) {
                    arrayList.add(i);
                } else {
                    Collections.addAll(arrayList, b);
                }
            }
            return arrayList;
        }

        private static String d(String str) {
            if (str.equalsIgnoreCase("H264") || str.equalsIgnoreCase("X264") || str.equalsIgnoreCase("AVC1") || str.equalsIgnoreCase("DAVC")) {
                return MediaFormat.MIMETYPE_VIDEO_AVC;
            }
            if (str.equalsIgnoreCase("AAC") || str.equalsIgnoreCase("AACL") || str.equalsIgnoreCase("AACH") || str.equalsIgnoreCase("AACP")) {
                return MediaFormat.MIMETYPE_AUDIO_AAC;
            }
            if (str.equalsIgnoreCase("TTML") || str.equalsIgnoreCase("DFXP")) {
                return "application/ttml+xml";
            }
            if (str.equalsIgnoreCase("ac-3") || str.equalsIgnoreCase("dac3")) {
                return MediaFormat.MIMETYPE_AUDIO_AC3;
            }
            if (str.equalsIgnoreCase("ec-3") || str.equalsIgnoreCase("dec3")) {
                return MediaFormat.MIMETYPE_AUDIO_EAC3;
            }
            if (str.equalsIgnoreCase("dtsc")) {
                return "audio/vnd.dts";
            }
            if (str.equalsIgnoreCase("dtsh") || str.equalsIgnoreCase("dtsl")) {
                return "audio/vnd.dts.hd";
            }
            if (str.equalsIgnoreCase("dtse")) {
                return "audio/vnd.dts.hd;profile=lbr";
            }
            if (str.equalsIgnoreCase("opus")) {
                return MediaFormat.MIMETYPE_AUDIO_OPUS;
            }
            return null;
        }
    }
}
