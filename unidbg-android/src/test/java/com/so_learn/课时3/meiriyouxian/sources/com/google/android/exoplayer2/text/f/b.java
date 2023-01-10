package com.google.android.exoplayer2.text.f;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.TtmlUtils;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.util.Base64;
import android.util.Pair;
import com.google.android.exoplayer2.util.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/* compiled from: TtmlNode */
/* access modifiers changed from: package-private */
public final class b {
    public final String a;
    public final String b;
    public final boolean c;
    public final long d;
    public final long e;
    public final e f;
    public final String g;
    public final String h;
    private final String[] i;
    private final HashMap<String, Integer> j;
    private final HashMap<String, Integer> k;
    private List<b> l;

    public static b a(String str) {
        return new b(null, d.a(str), -9223372036854775807L, -9223372036854775807L, null, null, "", null);
    }

    public static b a(String str, long j, long j2, e eVar, String[] strArr, String str2, String str3) {
        return new b(str, null, j, j2, eVar, strArr, str2, str3);
    }

    private b(String str, String str2, long j, long j2, e eVar, String[] strArr, String str3, String str4) {
        this.a = str;
        this.b = str2;
        this.h = str4;
        this.f = eVar;
        this.i = strArr;
        this.c = str2 != null;
        this.d = j;
        this.e = j2;
        this.g = (String) a.a(str3);
        this.j = new HashMap<>();
        this.k = new HashMap<>();
    }

    public boolean a(long j) {
        return (this.d == -9223372036854775807L && this.e == -9223372036854775807L) || (this.d <= j && this.e == -9223372036854775807L) || ((this.d == -9223372036854775807L && j < this.e) || (this.d <= j && j < this.e));
    }

    public void a(b bVar) {
        if (this.l == null) {
            this.l = new ArrayList();
        }
        this.l.add(bVar);
    }

    public b a(int i) {
        List<b> list = this.l;
        if (list != null) {
            return list.get(i);
        }
        throw new IndexOutOfBoundsException();
    }

    public int a() {
        List<b> list = this.l;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public long[] b() {
        TreeSet<Long> treeSet = new TreeSet<>();
        int i = 0;
        a(treeSet, false);
        long[] jArr = new long[treeSet.size()];
        Iterator<Long> it2 = treeSet.iterator();
        while (it2.hasNext()) {
            jArr[i] = it2.next().longValue();
            i++;
        }
        return jArr;
    }

    private void a(TreeSet<Long> treeSet, boolean z) {
        boolean equals = "p".equals(this.a);
        boolean equals2 = TtmlUtils.TAG_DIV.equals(this.a);
        if (z || equals || (equals2 && this.h != null)) {
            long j = this.d;
            if (j != -9223372036854775807L) {
                treeSet.add(Long.valueOf(j));
            }
            long j2 = this.e;
            if (j2 != -9223372036854775807L) {
                treeSet.add(Long.valueOf(j2));
            }
        }
        if (this.l != null) {
            for (int i = 0; i < this.l.size(); i++) {
                this.l.get(i).a(treeSet, z || equals);
            }
        }
    }

    public List<com.google.android.exoplayer2.text.a> a(long j, Map<String, e> map, Map<String, c> map2, Map<String, String> map3) {
        List<Pair<String, String>> arrayList = new ArrayList<>();
        a(j, this.g, arrayList);
        TreeMap treeMap = new TreeMap();
        a(j, false, this.g, (Map<String, SpannableStringBuilder>) treeMap);
        a(j, map, treeMap);
        ArrayList arrayList2 = new ArrayList();
        for (Pair<String, String> pair : arrayList) {
            String str = map3.get(pair.second);
            if (str != null) {
                byte[] decode = Base64.decode(str, 0);
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                c cVar = map2.get(pair.first);
                arrayList2.add(new com.google.android.exoplayer2.text.a(decodeByteArray, cVar.b, 1, cVar.c, cVar.e, cVar.f, Float.MIN_VALUE));
            }
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            c cVar2 = map2.get(entry.getKey());
            arrayList2.add(new com.google.android.exoplayer2.text.a(a((SpannableStringBuilder) entry.getValue()), (Layout.Alignment) null, cVar2.c, cVar2.d, cVar2.e, cVar2.b, Integer.MIN_VALUE, cVar2.f, cVar2.g, cVar2.h));
        }
        return arrayList2;
    }

    private void a(long j, String str, List<Pair<String, String>> list) {
        String str2;
        if (!"".equals(this.g)) {
            str = this.g;
        }
        if (!a(j) || !TtmlUtils.TAG_DIV.equals(this.a) || (str2 = this.h) == null) {
            for (int i = 0; i < a(); i++) {
                a(i).a(j, str, list);
            }
            return;
        }
        list.add(new Pair<>(str, str2));
    }

    private void a(long j, boolean z, String str, Map<String, SpannableStringBuilder> map) {
        this.j.clear();
        this.k.clear();
        if (!TtmlUtils.TAG_METADATA.equals(this.a)) {
            if (!"".equals(this.g)) {
                str = this.g;
            }
            if (this.c && z) {
                a(str, map).append((CharSequence) this.b);
            } else if (TtmlUtils.TAG_BR.equals(this.a) && z) {
                a(str, map).append('\n');
            } else if (a(j)) {
                for (Map.Entry<String, SpannableStringBuilder> entry : map.entrySet()) {
                    this.j.put(entry.getKey(), Integer.valueOf(entry.getValue().length()));
                }
                boolean equals = "p".equals(this.a);
                for (int i = 0; i < a(); i++) {
                    a(i).a(j, z || equals, str, map);
                }
                if (equals) {
                    d.a(a(str, map));
                }
                for (Map.Entry<String, SpannableStringBuilder> entry2 : map.entrySet()) {
                    this.k.put(entry2.getKey(), Integer.valueOf(entry2.getValue().length()));
                }
            }
        }
    }

    private static SpannableStringBuilder a(String str, Map<String, SpannableStringBuilder> map) {
        if (!map.containsKey(str)) {
            map.put(str, new SpannableStringBuilder());
        }
        return map.get(str);
    }

    private void a(long j, Map<String, e> map, Map<String, SpannableStringBuilder> map2) {
        int i;
        if (a(j)) {
            Iterator<Map.Entry<String, Integer>> it2 = this.k.entrySet().iterator();
            while (true) {
                i = 0;
                if (!it2.hasNext()) {
                    break;
                }
                Map.Entry<String, Integer> next = it2.next();
                String key = next.getKey();
                if (this.j.containsKey(key)) {
                    i = this.j.get(key).intValue();
                }
                int intValue = next.getValue().intValue();
                if (i != intValue) {
                    a(map, map2.get(key), i, intValue);
                }
            }
            while (i < a()) {
                a(i).a(j, map, map2);
                i++;
            }
        }
    }

    private void a(Map<String, e> map, SpannableStringBuilder spannableStringBuilder, int i, int i2) {
        e a = d.a(this.f, this.i, map);
        if (a != null) {
            d.a(spannableStringBuilder, i, i2, a);
        }
    }

    private SpannableStringBuilder a(SpannableStringBuilder spannableStringBuilder) {
        int i;
        int i2;
        int i3 = 0;
        int length = spannableStringBuilder.length();
        for (int i4 = 0; i4 < length; i4++) {
            if (spannableStringBuilder.charAt(i4) == ' ') {
                int i5 = i4 + 1;
                int i6 = i5;
                while (i6 < spannableStringBuilder.length() && spannableStringBuilder.charAt(i6) == ' ') {
                    i6++;
                }
                int i7 = i6 - i5;
                if (i7 > 0) {
                    spannableStringBuilder.delete(i4, i4 + i7);
                    length -= i7;
                }
            }
        }
        if (length > 0 && spannableStringBuilder.charAt(0) == ' ') {
            spannableStringBuilder.delete(0, 1);
            length--;
        }
        int i8 = 0;
        while (true) {
            i = length - 1;
            if (i8 >= i) {
                break;
            }
            if (spannableStringBuilder.charAt(i8) == '\n') {
                int i9 = i8 + 1;
                if (spannableStringBuilder.charAt(i9) == ' ') {
                    spannableStringBuilder.delete(i9, i8 + 2);
                    length--;
                }
            }
            i8++;
        }
        if (length > 0 && spannableStringBuilder.charAt(i) == ' ') {
            spannableStringBuilder.delete(i, length);
            length--;
        }
        while (true) {
            i2 = length - 1;
            if (i3 >= i2) {
                break;
            }
            if (spannableStringBuilder.charAt(i3) == ' ') {
                int i10 = i3 + 1;
                if (spannableStringBuilder.charAt(i10) == '\n') {
                    spannableStringBuilder.delete(i3, i10);
                    length--;
                }
            }
            i3++;
        }
        if (length > 0 && spannableStringBuilder.charAt(i2) == '\n') {
            spannableStringBuilder.delete(i2, length);
        }
        return spannableStringBuilder;
    }
}
