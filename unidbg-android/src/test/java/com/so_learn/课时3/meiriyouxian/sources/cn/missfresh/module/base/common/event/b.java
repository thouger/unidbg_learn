package cn.missfresh.module.base.common.event;

import cn.missfresh.module.base.base.a;
import cn.missfresh.module.base.bean.CategoryArea;
import cn.missfresh.module.base.bean.MissFreshProduct;
import java.util.List;

/* compiled from: EventCategoryArea */
public class b extends a {
    public List<CategoryArea> a;
    public MissFreshProduct.CategoryAreaV2 b;

    public b(List<CategoryArea> list, MissFreshProduct.CategoryAreaV2 categoryAreaV2) {
        this.a = list;
        this.b = categoryAreaV2;
    }
}
