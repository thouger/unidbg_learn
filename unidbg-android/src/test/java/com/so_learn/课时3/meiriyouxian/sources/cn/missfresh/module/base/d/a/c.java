package cn.missfresh.module.base.d.a;

import cn.missfresh.module.base.datastatistics.a.b.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.umeng.analytics.pro.ai;
import java.util.List;

/* compiled from: BaseScmStrategy */
public class c extends a {
    private boolean a = false;
    private boolean b = false;
    private boolean c = false;

    @Override // cn.missfresh.module.base.datastatistics.a.b.a
    public boolean a(cn.missfresh.module.base.datastatistics.a.a.a aVar) {
        return false;
    }

    public List<String> c() {
        return null;
    }

    public List<String> d() {
        return null;
    }

    @Override // cn.missfresh.module.base.datastatistics.a.b.a
    public void b(cn.missfresh.module.base.datastatistics.a.a.a aVar) {
        List<String> c;
        List<String> d;
        AppMethodBeat.i(19034, false);
        this.a = false;
        this.b = false;
        this.c = false;
        if (aVar.f != null) {
            Object obj = aVar.f.get("pos");
            if ((obj instanceof Number) || (obj instanceof String)) {
                aVar.d = obj.toString();
            }
        }
        if ("page_view".equals(aVar.a) || ((c = c()) != null && c.contains(aVar.a))) {
            this.b = true;
            this.c = true;
        } else if (aVar.f != null && "recommend".equals(aVar.f.get(ai.e)) && ("click_product".equals(aVar.a) || "add_cart".equals(aVar.a))) {
            this.b = true;
            this.a = true;
            if (aVar.c == null) {
                aVar.c = String.valueOf(aVar.f.get(ai.e));
            }
            if (aVar.e == null && aVar.f.containsKey("recommend_request_id")) {
                aVar.e = String.valueOf(aVar.f.get("recommend_request_id"));
            }
        } else if (aVar.a != null && (aVar.a.startsWith("click_ad_") || ((d = d()) != null && d.contains(aVar.a)))) {
            this.b = true;
            this.a = true;
            if (aVar.f != null) {
                if (aVar.c == null) {
                    if (aVar.f.containsKey(ai.e)) {
                        aVar.c = String.valueOf(aVar.f.get(ai.e));
                    } else {
                        aVar.c = aVar.a.replaceFirst("click_ad_", "");
                    }
                }
                if (aVar.e == null) {
                    if (aVar.f.containsKey("promotion_id")) {
                        aVar.e = String.valueOf(aVar.f.get("promotion_id"));
                    } else if (aVar.f.containsKey("sku")) {
                        aVar.e = String.valueOf(aVar.f.get("sku"));
                    } else if (aVar.f.containsKey("url")) {
                        aVar.e = String.valueOf(aVar.f.get("url"));
                    }
                }
            }
        } else if ((aVar.a != null && aVar.a.startsWith("click")) || (aVar.a != null && aVar.a.startsWith("exposure"))) {
            this.b = true;
            if (aVar.c == null && aVar.f != null && aVar.f.containsKey(ai.e)) {
                aVar.c = String.valueOf(aVar.f.get(ai.e));
            }
        }
        AppMethodBeat.o(19034);
    }

    @Override // cn.missfresh.module.base.datastatistics.a.b.a
    public final boolean a() {
        return this.a;
    }
}
