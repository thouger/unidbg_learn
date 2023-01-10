package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.net.wifi.WifiEnterpriseConfig;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import android.util.TimeUtils;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.c;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.mp4.h;
import com.google.android.exoplayer2.source.UnrecognizedInputFormatException;
import com.google.android.exoplayer2.source.hls.playlist.c;
import com.google.android.exoplayer2.source.hls.playlist.d;
import com.google.android.exoplayer2.upstream.q;
import com.google.android.exoplayer2.util.z;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.umeng.message.proguard.l;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: HlsPlaylistParser */
public final class f implements q.a<e> {
    private static final Pattern A = b("AUTOSELECT");
    private static final Pattern B = b("DEFAULT");
    private static final Pattern C = b("FORCED");
    private static final Pattern D = Pattern.compile("VALUE=\"(.+?)\"");
    private static final Pattern E = Pattern.compile("IMPORT=\"(.+?)\"");
    private static final Pattern F = Pattern.compile("\\{\\$([a-zA-Z0-9\\-_]+)\\}");
    private static final Pattern a = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
    private static final Pattern b = Pattern.compile("AUDIO=\"(.+?)\"");
    private static final Pattern c = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
    private static final Pattern d = Pattern.compile("CHANNELS=\"(.+?)\"");
    private static final Pattern e = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern f = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern g = Pattern.compile("FRAME-RATE=([\\d\\.]+)\\b");
    private static final Pattern h = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern i = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final Pattern j = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
    private static final Pattern k = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern l = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    private static final Pattern m = Pattern.compile("#EXTINF:[\\d\\.]+\\b,(.+)");
    private static final Pattern n = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
    private static final Pattern o = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern p = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
    private static final Pattern q = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(?:,|$)");
    private static final Pattern r = Pattern.compile("KEYFORMAT=\"(.+?)\"");
    private static final Pattern s = Pattern.compile("KEYFORMATVERSIONS=\"(.+?)\"");
    private static final Pattern t = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern u = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern v = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern w = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern x = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern y = Pattern.compile("GROUP-ID=\"(.+?)\"");
    private static final Pattern z = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
    private final c G;

    public f() {
        this(c.a);
    }

    public f(c cVar) {
        this.G = cVar;
    }

    /* renamed from: a */
    public e b(Uri uri, InputStream inputStream) throws IOException {
        String trim;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayDeque arrayDeque = new ArrayDeque();
        try {
        } finally {
            z.a((Closeable) bufferedReader);
        }
        if (a(bufferedReader)) {
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    trim = readLine.trim();
                    if (!trim.isEmpty()) {
                        if (trim.startsWith("#EXT-X-STREAM-INF")) {
                            arrayDeque.add(trim);
                            c a2 = a(new a(arrayDeque, bufferedReader), uri.toString());
                            z.a((Closeable) bufferedReader);
                            return a2;
                        } else if (trim.startsWith("#EXT-X-TARGETDURATION") || trim.startsWith("#EXT-X-MEDIA-SEQUENCE") || trim.startsWith("#EXTINF") || trim.startsWith("#EXT-X-KEY") || trim.startsWith("#EXT-X-BYTERANGE") || trim.equals("#EXT-X-DISCONTINUITY") || trim.equals("#EXT-X-DISCONTINUITY-SEQUENCE") || trim.equals("#EXT-X-ENDLIST")) {
                            break;
                        } else {
                            arrayDeque.add(trim);
                        }
                    }
                } else {
                    z.a((Closeable) bufferedReader);
                    throw new ParserException("Failed to parse the playlist, could not identify any tags.");
                }
            }
            arrayDeque.add(trim);
            return a(this.G, new a(arrayDeque, bufferedReader), uri.toString());
        }
        throw new UnrecognizedInputFormatException("Input does not start with the #EXTM3U header.", uri);
    }

    private static boolean a(BufferedReader bufferedReader) throws IOException {
        int read = bufferedReader.read();
        if (read == 239) {
            if (!(bufferedReader.read() == 187 && bufferedReader.read() == 191)) {
                return false;
            }
            read = bufferedReader.read();
        }
        int a2 = a(bufferedReader, true, read);
        for (int i2 = 0; i2 < 7; i2++) {
            if (a2 != "#EXTM3U".charAt(i2)) {
                return false;
            }
            a2 = bufferedReader.read();
        }
        return z.a(a(bufferedReader, false, a2));
    }

    private static int a(BufferedReader bufferedReader, boolean z2, int i2) throws IOException {
        while (i2 != -1 && Character.isWhitespace(i2) && (z2 || !z.a(i2))) {
            i2 = bufferedReader.read();
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01a7  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0211  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.source.hls.playlist.c a(com.google.android.exoplayer2.source.hls.playlist.f.a r32, java.lang.String r33) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 627
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.playlist.f.a(com.google.android.exoplayer2.source.hls.playlist.f$a, java.lang.String):com.google.android.exoplayer2.source.hls.playlist.c");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:173:0x004f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:174:0x004f */
    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r27v0, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r27v1, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r27v2, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r1v4, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r1v8, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r47v4, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r26v3, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r27v3, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    private static d a(c cVar, a aVar, String str) throws IOException {
        TreeMap treeMap;
        DrmInitData.SchemeData schemeData;
        DrmInitData drmInitData;
        c cVar2 = cVar;
        boolean z2 = cVar2.p;
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        TreeMap treeMap2 = new TreeMap();
        int i2 = 0;
        int i3 = 1;
        boolean z3 = z2;
        long j2 = -9223372036854775807L;
        long j3 = -9223372036854775807L;
        String str2 = "";
        boolean z4 = 0;
        int i4 = 0;
        int i5 = 0;
        boolean z5 = 0;
        boolean z6 = false;
        int i6 = 0;
        int i7 = 1;
        String str3 = null;
        long j4 = 0;
        long j5 = 0;
        DrmInitData drmInitData2 = null;
        long j6 = 0;
        long j7 = 0;
        DrmInitData drmInitData3 = null;
        long j8 = -1;
        long j9 = 0;
        String str4 = null;
        String str5 = null;
        d.a aVar2 = null;
        long j10 = 0;
        while (aVar.a()) {
            String b2 = aVar.b();
            if (b2.startsWith("#EXT")) {
                arrayList2.add(b2);
            }
            if (b2.startsWith("#EXT-X-PLAYLIST-TYPE")) {
                String a2 = a(b2, j, hashMap);
                if ("VOD".equals(a2)) {
                    i4 = i3;
                } else if ("EVENT".equals(a2)) {
                    i4 = 2;
                }
            } else if (b2.startsWith("#EXT-X-START")) {
                j2 = (long) (c(b2, n) * 1000000.0d);
            } else if (b2.startsWith("#EXT-X-MAP")) {
                String a3 = a(b2, t, hashMap);
                String b3 = b(b2, p, hashMap);
                if (b3 != null) {
                    String[] split = b3.split("@");
                    j8 = Long.parseLong(split[i2]);
                    if (split.length > i3) {
                        j6 = Long.parseLong(split[i3]);
                    }
                }
                aVar2 = new d.a(a3, j6, j8);
                j6 = 0;
                j8 = -1;
            } else if (b2.startsWith("#EXT-X-TARGETDURATION")) {
                j3 = TimeUtils.NANOS_PER_MS * ((long) a(b2, h));
            } else if (b2.startsWith("#EXT-X-MEDIA-SEQUENCE")) {
                j7 = b(b2, k);
                j5 = j7;
            } else if (b2.startsWith("#EXT-X-VERSION")) {
                i7 = a(b2, i);
            } else {
                if (b2.startsWith("#EXT-X-DEFINE")) {
                    String b4 = b(b2, E, hashMap);
                    if (b4 != null) {
                        String str6 = cVar2.g.get(b4);
                        if (str6 != null) {
                            hashMap.put(b4, str6);
                        }
                    } else {
                        hashMap.put(a(b2, x, hashMap), a(b2, D, hashMap));
                    }
                } else if (b2.startsWith("#EXTINF")) {
                    str2 = a(b2, m, "", hashMap);
                    j10 = (long) (c(b2, l) * 1000000.0d);
                } else if (b2.startsWith("#EXT-X-KEY")) {
                    String a4 = a(b2, q, hashMap);
                    String a5 = a(b2, r, WifiEnterpriseConfig.IDENTITY_KEY, hashMap);
                    if (KeyProperties.DIGEST_NONE.equals(a4)) {
                        treeMap2.clear();
                        drmInitData3 = null;
                        str4 = null;
                        str5 = null;
                    } else {
                        String b5 = b(b2, u, hashMap);
                        if (!WifiEnterpriseConfig.IDENTITY_KEY.equals(a5)) {
                            if (str3 == null) {
                                str3 = ("SAMPLE-AES-CENC".equals(a4) || "SAMPLE-AES-CTR".equals(a4)) ? "cenc" : "cbcs";
                            }
                            if ("com.microsoft.playready".equals(a5)) {
                                schemeData = b(b2, hashMap);
                            } else {
                                schemeData = a(b2, a5, hashMap);
                            }
                            if (schemeData != null) {
                                treeMap2.put(a5, schemeData);
                                str5 = b5;
                                drmInitData3 = null;
                                str4 = null;
                            }
                        } else if ("AES-128".equals(a4)) {
                            str4 = a(b2, t, hashMap);
                            str5 = b5;
                        }
                        str5 = b5;
                        str4 = null;
                    }
                } else if (b2.startsWith("#EXT-X-BYTERANGE")) {
                    String[] split2 = a(b2, o, hashMap).split("@");
                    j8 = Long.parseLong(split2[i2]);
                    if (split2.length > i3) {
                        j6 = Long.parseLong(split2[i3]);
                    }
                } else if (b2.startsWith("#EXT-X-DISCONTINUITY-SEQUENCE")) {
                    i5 = Integer.parseInt(b2.substring(b2.indexOf(58) + i3));
                    z4 = i3;
                } else if (b2.equals("#EXT-X-DISCONTINUITY")) {
                    i6++;
                } else if (b2.startsWith("#EXT-X-PROGRAM-DATE-TIME")) {
                    if (j4 == 0) {
                        j4 = c.b(z.g(b2.substring(b2.indexOf(58) + i3))) - j9;
                    }
                } else if (b2.equals("#EXT-X-GAP")) {
                    z6 = i3;
                } else if (b2.equals("#EXT-X-INDEPENDENT-SEGMENTS")) {
                    z3 = i3;
                } else if (b2.equals("#EXT-X-ENDLIST")) {
                    z5 = i3;
                } else if (!b2.startsWith("#")) {
                    String hexString = str4 == null ? null : str5 != null ? str5 : Long.toHexString(j7);
                    long j11 = j7 + 1;
                    int i8 = (j8 > -1 ? 1 : (j8 == -1 ? 0 : -1));
                    if (i8 == 0) {
                        j6 = 0;
                    }
                    if (drmInitData3 != null || treeMap2.isEmpty()) {
                        treeMap = treeMap2;
                        drmInitData = drmInitData3;
                    } else {
                        DrmInitData.SchemeData[] schemeDataArr = (DrmInitData.SchemeData[]) treeMap2.values().toArray(new DrmInitData.SchemeData[i2]);
                        drmInitData = new DrmInitData(str3, schemeDataArr);
                        if (drmInitData2 == null) {
                            DrmInitData.SchemeData[] schemeDataArr2 = new DrmInitData.SchemeData[schemeDataArr.length];
                            int i9 = 0;
                            while (i9 < schemeDataArr.length) {
                                schemeDataArr2[i9] = schemeDataArr[i9].a((byte[]) null);
                                i9++;
                                treeMap2 = treeMap2;
                            }
                            treeMap = treeMap2;
                            drmInitData2 = new DrmInitData(str3, schemeDataArr2);
                        } else {
                            treeMap = treeMap2;
                        }
                    }
                    arrayList.add(new d.a(c(b2, hashMap), aVar2, str2, j10, i6, j9, drmInitData, str4, hexString, j6, j8, z6));
                    j9 += j10;
                    if (i8 != 0) {
                        j6 += j8;
                    }
                    i2 = 0;
                    i3 = 1;
                    z6 = false;
                    j8 = -1;
                    j10 = 0;
                    cVar2 = cVar;
                    str2 = "";
                    j7 = j11;
                    drmInitData3 = drmInitData;
                    treeMap2 = treeMap;
                }
                treeMap = treeMap2;
                i2 = 0;
                i3 = 1;
                cVar2 = cVar;
                z6 = z6;
                treeMap2 = treeMap;
            }
        }
        return new d(i4, str, arrayList2, j2, j4, z4, i5, j5, i7, j3, z3, z5, j4 != 0, drmInitData2, arrayList);
    }

    private static int a(String str) {
        int i2 = a(str, B, false) ? 1 : 0;
        if (a(str, C, false)) {
            i2 |= 2;
        }
        return a(str, A, false) ? i2 | 4 : i2;
    }

    private static int a(String str, Map<String, String> map) {
        String b2 = b(str, d, map);
        if (b2 != null) {
            return Integer.parseInt(z.b(b2, NotificationIconUtil.SPLIT_CHAR)[0]);
        }
        return -1;
    }

    private static DrmInitData.SchemeData b(String str, Map<String, String> map) throws ParserException {
        if (!"1".equals(a(str, s, "1", map))) {
            return null;
        }
        String a2 = a(str, t, map);
        return new DrmInitData.SchemeData(c.e, "video/mp4", h.a(c.e, Base64.decode(a2.substring(a2.indexOf(44)), 0)));
    }

    private static DrmInitData.SchemeData a(String str, String str2, Map<String, String> map) throws ParserException {
        if ("urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed".equals(str2)) {
            String a2 = a(str, t, map);
            return new DrmInitData.SchemeData(c.d, "video/mp4", Base64.decode(a2.substring(a2.indexOf(44)), 0));
        } else if (!"com.widevine".equals(str2)) {
            return null;
        } else {
            try {
                return new DrmInitData.SchemeData(c.d, "hls", str.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e2) {
                throw new ParserException(e2);
            }
        }
    }

    private static int a(String str, Pattern pattern) throws ParserException {
        return Integer.parseInt(a(str, pattern, Collections.emptyMap()));
    }

    private static long b(String str, Pattern pattern) throws ParserException {
        return Long.parseLong(a(str, pattern, Collections.emptyMap()));
    }

    private static double c(String str, Pattern pattern) throws ParserException {
        return Double.parseDouble(a(str, pattern, Collections.emptyMap()));
    }

    private static String a(String str, Pattern pattern, Map<String, String> map) throws ParserException {
        String b2 = b(str, pattern, map);
        if (b2 != null) {
            return b2;
        }
        throw new ParserException("Couldn't match " + pattern.pattern() + " in " + str);
    }

    private static String b(String str, Pattern pattern, Map<String, String> map) {
        return a(str, pattern, null, map);
    }

    private static String a(String str, Pattern pattern, String str2, Map<String, String> map) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            str2 = matcher.group(1);
        }
        return (map.isEmpty() || str2 == null) ? str2 : c(str2, map);
    }

    private static String c(String str, Map<String, String> map) {
        Matcher matcher = F.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group(1);
            if (map.containsKey(group)) {
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(map.get(group)));
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    private static boolean a(String str, Pattern pattern, boolean z2) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.group(1).equals("YES") : z2;
    }

    private static Pattern b(String str) {
        return Pattern.compile(str + "=(NO" + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + "YES" + l.t);
    }

    private static boolean a(List<c.a> list, String str) {
        if (str == null) {
            return true;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (str.equals(list.get(i2).a)) {
                return true;
            }
        }
        return false;
    }

    /* compiled from: HlsPlaylistParser */
    /* access modifiers changed from: private */
    public static class a {
        private final BufferedReader a;
        private final Queue<String> b;
        private String c;

        public a(Queue<String> queue, BufferedReader bufferedReader) {
            this.b = queue;
            this.a = bufferedReader;
        }

        public boolean a() throws IOException {
            if (this.c != null) {
                return true;
            }
            if (!this.b.isEmpty()) {
                this.c = this.b.poll();
                return true;
            }
            do {
                String readLine = this.a.readLine();
                this.c = readLine;
                if (readLine == null) {
                    return false;
                }
                this.c = this.c.trim();
            } while (this.c.isEmpty());
            return true;
        }

        public String b() throws IOException {
            if (!a()) {
                return null;
            }
            String str = this.c;
            this.c = null;
            return str;
        }
    }
}
