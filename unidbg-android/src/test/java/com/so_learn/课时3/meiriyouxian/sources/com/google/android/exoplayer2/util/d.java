package com.google.android.exoplayer2.util;

import android.graphics.Color;
import android.hardware.Camera;
import android.net.wifi.WifiEnterpriseConfig;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: ColorParser */
public final class d {
    private static final Pattern a = Pattern.compile("^rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
    private static final Pattern b = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
    private static final Pattern c = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d*\\.?\\d*?)\\)$");
    private static final Map<String, Integer> d = new HashMap();

    private static int a(int i, int i2, int i3, int i4) {
        return (i << 24) | (i2 << 16) | (i3 << 8) | i4;
    }

    static {
        d.put("aliceblue", -984833);
        d.put("antiquewhite", -332841);
        Map<String, Integer> map = d;
        Integer valueOf = Integer.valueOf((int) Color.CYAN);
        map.put(Camera.Parameters.EFFECT_AQUA, valueOf);
        d.put("aquamarine", -8388652);
        d.put("azure", -983041);
        d.put("beige", -657956);
        d.put("bisque", -6972);
        d.put("black", -16777216);
        d.put("blanchedalmond", -5171);
        d.put("blue", Integer.valueOf((int) Color.BLUE));
        d.put("blueviolet", -7722014);
        d.put("brown", -5952982);
        d.put("burlywood", -2180985);
        d.put("cadetblue", -10510688);
        d.put("chartreuse", -8388864);
        d.put("chocolate", -2987746);
        d.put("coral", -32944);
        d.put("cornflowerblue", -10185235);
        d.put("cornsilk", -1828);
        d.put("crimson", -2354116);
        d.put("cyan", valueOf);
        d.put("darkblue", -16777077);
        d.put("darkcyan", -16741493);
        d.put("darkgoldenrod", -4684277);
        d.put("darkgray", -5658199);
        d.put("darkgreen", -16751616);
        d.put("darkgrey", -5658199);
        d.put("darkkhaki", -4343957);
        d.put("darkmagenta", -7667573);
        d.put("darkolivegreen", -11179217);
        d.put("darkorange", -29696);
        d.put("darkorchid", -6737204);
        d.put("darkred", -7667712);
        d.put("darksalmon", -1468806);
        d.put("darkseagreen", -7357297);
        d.put("darkslateblue", -12042869);
        d.put("darkslategray", -13676721);
        d.put("darkslategrey", -13676721);
        d.put("darkturquoise", -16724271);
        d.put("darkviolet", -7077677);
        d.put("deeppink", -60269);
        d.put("deepskyblue", -16728065);
        d.put("dimgray", -9868951);
        d.put("dimgrey", -9868951);
        d.put("dodgerblue", -14774017);
        d.put("firebrick", -5103070);
        d.put("floralwhite", -1296);
        d.put("forestgreen", -14513374);
        Map<String, Integer> map2 = d;
        Integer valueOf2 = Integer.valueOf((int) Color.MAGENTA);
        map2.put("fuchsia", valueOf2);
        d.put("gainsboro", -2302756);
        d.put("ghostwhite", -460545);
        d.put("gold", -10496);
        d.put("goldenrod", -2448096);
        d.put("gray", -8355712);
        d.put("green", -16744448);
        d.put("greenyellow", -5374161);
        d.put("grey", -8355712);
        d.put("honeydew", -983056);
        d.put("hotpink", -38476);
        d.put("indianred", -3318692);
        d.put("indigo", -11861886);
        d.put("ivory", -16);
        d.put("khaki", -989556);
        d.put("lavender", -1644806);
        d.put("lavenderblush", -3851);
        d.put("lawngreen", -8586240);
        d.put("lemonchiffon", -1331);
        d.put("lightblue", -5383962);
        d.put("lightcoral", -1015680);
        d.put("lightcyan", -2031617);
        d.put("lightgoldenrodyellow", -329006);
        d.put("lightgray", -2894893);
        d.put("lightgreen", -7278960);
        d.put("lightgrey", -2894893);
        d.put("lightpink", -18751);
        d.put("lightsalmon", -24454);
        d.put("lightseagreen", -14634326);
        d.put("lightskyblue", -7876870);
        d.put("lightslategray", -8943463);
        d.put("lightslategrey", -8943463);
        d.put("lightsteelblue", -5192482);
        d.put("lightyellow", -32);
        d.put("lime", Integer.valueOf((int) Color.GREEN));
        d.put("limegreen", -13447886);
        d.put("linen", -331546);
        d.put("magenta", valueOf2);
        d.put("maroon", -8388608);
        d.put("mediumaquamarine", -10039894);
        d.put("mediumblue", -16777011);
        d.put("mediumorchid", -4565549);
        d.put("mediumpurple", -7114533);
        d.put("mediumseagreen", -12799119);
        d.put("mediumslateblue", -8689426);
        d.put("mediumspringgreen", -16713062);
        d.put("mediumturquoise", -12004916);
        d.put("mediumvioletred", -3730043);
        d.put("midnightblue", -15132304);
        d.put("mintcream", -655366);
        d.put("mistyrose", -6943);
        d.put("moccasin", -6987);
        d.put("navajowhite", -8531);
        d.put("navy", -16777088);
        d.put("oldlace", -133658);
        d.put("olive", -8355840);
        d.put("olivedrab", -9728477);
        d.put("orange", -23296);
        d.put("orangered", -47872);
        d.put("orchid", -2461482);
        d.put("palegoldenrod", -1120086);
        d.put("palegreen", -6751336);
        d.put("paleturquoise", -5247250);
        d.put("palevioletred", -2396013);
        d.put("papayawhip", -4139);
        d.put("peachpuff", -9543);
        d.put("peru", -3308225);
        d.put("pink", -16181);
        d.put("plum", -2252579);
        d.put("powderblue", -5185306);
        d.put("purple", -8388480);
        d.put("rebeccapurple", -10079335);
        d.put("red", -65536);
        d.put("rosybrown", -4419697);
        d.put("royalblue", -12490271);
        d.put("saddlebrown", -7650029);
        d.put("salmon", -360334);
        d.put("sandybrown", -744352);
        d.put("seagreen", -13726889);
        d.put("seashell", -2578);
        d.put("sienna", -6270419);
        d.put("silver", -4144960);
        d.put("skyblue", -7876885);
        d.put("slateblue", -9807155);
        d.put("slategray", -9404272);
        d.put("slategrey", -9404272);
        d.put(Camera.Parameters.SCENE_MODE_SNOW, -1286);
        d.put("springgreen", -16711809);
        d.put("steelblue", -12156236);
        d.put("tan", -2968436);
        d.put("teal", -16744320);
        d.put("thistle", -2572328);
        d.put("tomato", -40121);
        d.put("transparent", 0);
        d.put("turquoise", -12525360);
        d.put("violet", -1146130);
        d.put("wheat", -663885);
        d.put("white", -1);
        d.put("whitesmoke", -657931);
        d.put("yellow", -256);
        d.put("yellowgreen", -6632142);
    }

    public static int a(String str) {
        return a(str, false);
    }

    public static int b(String str) {
        return a(str, true);
    }

    private static int a(String str, boolean z) {
        int i;
        a.a(!TextUtils.isEmpty(str));
        String replace = str.replace(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER, "");
        if (replace.charAt(0) == '#') {
            int parseLong = (int) Long.parseLong(replace.substring(1), 16);
            if (replace.length() == 7) {
                return -16777216 | parseLong;
            }
            if (replace.length() == 9) {
                return ((parseLong & 255) << 24) | (parseLong >>> 8);
            }
            throw new IllegalArgumentException();
        }
        if (replace.startsWith("rgba")) {
            Matcher matcher = (z ? c : b).matcher(replace);
            if (matcher.matches()) {
                if (z) {
                    i = (int) (Float.parseFloat(matcher.group(4)) * 255.0f);
                } else {
                    i = Integer.parseInt(matcher.group(4), 10);
                }
                return a(i, Integer.parseInt(matcher.group(1), 10), Integer.parseInt(matcher.group(2), 10), Integer.parseInt(matcher.group(3), 10));
            }
        } else if (replace.startsWith("rgb")) {
            Matcher matcher2 = a.matcher(replace);
            if (matcher2.matches()) {
                return a(Integer.parseInt(matcher2.group(1), 10), Integer.parseInt(matcher2.group(2), 10), Integer.parseInt(matcher2.group(3), 10));
            }
        } else {
            Integer num = d.get(z.d(replace));
            if (num != null) {
                return num.intValue();
            }
        }
        throw new IllegalArgumentException();
    }

    private static int a(int i, int i2, int i3) {
        return a(255, i, i2, i3);
    }
}
