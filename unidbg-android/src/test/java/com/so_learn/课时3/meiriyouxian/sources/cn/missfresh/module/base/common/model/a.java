package cn.missfresh.module.base.common.model;

import cn.missfresh.module.base.bean.CategoryArea;
import cn.missfresh.module.base.bean.MissFreshProduct;
import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.network.c;
import cn.missfresh.module.base.network.m;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.accs.common.Constants;
import de.greenrobot.event.EventBus;
import java.util.List;
import java.util.Map;
import okhttp3.Request;

/* compiled from: CategoryAreaInteractor */
public class a {

    /* compiled from: CategoryAreaInteractor */
    /* renamed from: cn.missfresh.module.base.common.model.a$1  reason: invalid class name */
    class AnonymousClass1 extends m {
        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a() {
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void b() {
        }

        AnonymousClass1() {
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(12227, false);
            super.a(i);
            AppMethodBeat.o(12227);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(12229, false);
            super.a(request, exc);
            AppMethodBeat.o(12229);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(12231, false);
            super.a(str);
            try {
                JSONObject parseObject = JSONObject.parseObject(str);
                int intValue = parseObject.getIntValue(Constants.KEY_HTTP_CODE);
                String string = parseObject.getString("category_areas");
                String string2 = parseObject.containsKey("categoryAreaV2") ? parseObject.getString("categoryAreaV2") : "";
                if (intValue == 0) {
                    List parseArray = JSON.parseArray(string, CategoryArea.class);
                    MissFreshProduct.CategoryAreaV2 categoryAreaV2 = null;
                    if (!b.a(string2)) {
                        categoryAreaV2 = (MissFreshProduct.CategoryAreaV2) JSON.parseObject(string2, MissFreshProduct.CategoryAreaV2.class);
                    }
                    EventBus.getDefault().post(new cn.missfresh.module.base.common.event.b(parseArray, categoryAreaV2));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            AppMethodBeat.o(12231);
        }
    }

    public void a() {
        AppMethodBeat.i(12236, false);
        c.a(this, i.h, (Map<String, String>) null, new AnonymousClass1());
        AppMethodBeat.o(12236);
    }
}
