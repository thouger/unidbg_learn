package cn.missfresh.module.base.datastatistics.a;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* compiled from: ScmCache */
/* access modifiers changed from: package-private */
public class c {
    private Map<String, String> a;
    private boolean b;
    private boolean c;
    private boolean d;
    private h e;
    private h f;
    private List<b> g;

    c() {
    }

    /* access modifiers changed from: package-private */
    public h a() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public List<b> b() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public void a(h hVar) {
        this.e = hVar;
        this.b = true;
    }

    /* access modifiers changed from: package-private */
    public void b(h hVar) {
        this.f = hVar;
        this.c = true;
    }

    /* access modifiers changed from: package-private */
    public void a(b bVar) {
        AppMethodBeat.i(12759, false);
        if (bVar == null) {
            AppMethodBeat.o(12759);
            return;
        }
        if (this.g == null) {
            this.g = new LinkedList();
        }
        if (this.g.size() >= 100) {
            this.g.remove(1);
        }
        this.g.add(bVar);
        this.d = true;
        AppMethodBeat.o(12759);
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> c() {
        String str;
        String str2;
        AppMethodBeat.i(12762, false);
        if (this.a == null) {
            this.a = new HashMap();
            this.d = true;
            this.c = true;
            this.b = true;
        }
        if (this.b) {
            Map<String, String> map = this.a;
            h hVar = this.e;
            if (hVar != null) {
                str2 = hVar.a();
            } else {
                str2 = "";
            }
            map.put("spm", str2);
            this.b = false;
        }
        if (this.c) {
            Map<String, String> map2 = this.a;
            h hVar2 = this.f;
            if (hVar2 != null) {
                str = hVar2.a();
            } else {
                str = "";
            }
            map2.put("pre_spm", str);
            this.c = false;
        }
        if (this.d) {
            List<b> list = this.g;
            if (list == null || list.isEmpty()) {
                this.a.put("scm", "");
            } else {
                StringBuilder sb = new StringBuilder(String.valueOf(this.g.get(0)));
                for (int i = 1; i < this.g.size(); i++) {
                    sb.append(',');
                    sb.append(this.g.get(i));
                }
                this.a.put("scm", sb.toString());
            }
            this.d = false;
        }
        HashMap hashMap = new HashMap(this.a);
        AppMethodBeat.o(12762);
        return hashMap;
    }

    public String toString() {
        AppMethodBeat.i(12763, false);
        StringBuilder sb = new StringBuilder("{");
        Map<String, String> map = this.a;
        if (!(map == null || map.keySet() == null)) {
            sb.append("\"spm\":\"");
            sb.append(this.a.get("spm"));
            sb.append('\"');
            sb.append(",\"pre_spm\":\"");
            sb.append(this.a.get("pre_spm"));
            sb.append('\"');
            sb.append(",\"scm\":\"");
            sb.append(this.a.get("scm"));
            sb.append('\"');
        }
        sb.append('}');
        String sb2 = sb.toString();
        AppMethodBeat.o(12763);
        return sb2;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        this.g = null;
        this.f = null;
        this.e = null;
        this.a = null;
        this.d = false;
        this.c = false;
        this.b = false;
    }
}
