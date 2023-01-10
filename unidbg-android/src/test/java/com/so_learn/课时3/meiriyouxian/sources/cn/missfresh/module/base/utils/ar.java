package cn.missfresh.module.base.utils;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import cn.missfresh.module.base.bean.ProductDetail;
import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.module.base.bean.SpuParams;
import cn.missfresh.module.base.common.dialog.a;
import cn.missfresh.module.base.common.dialog.b;
import cn.missfresh.module.base.common.interfaces.c;
import cn.missfresh.module.base.common.interfaces.k;
import cn.missfresh.module.base.common.interfaces.n;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: SelectSkuDialogUtil */
public class ar {
    public static void a(Context context, FragmentManager fragmentManager, ProductsEntity productsEntity) {
        AppMethodBeat.i(23418, false);
        a(context, fragmentManager, productsEntity, (c) null);
        AppMethodBeat.o(23418);
    }

    public static void a(Context context, FragmentManager fragmentManager, ProductsEntity productsEntity, c cVar) {
        AppMethodBeat.i(23419, false);
        a aVar = new a(context, productsEntity);
        aVar.a(productsEntity);
        aVar.a(cVar);
        aVar.show();
        AppMethodBeat.o(23419);
    }

    public static void a(Context context, ProductsEntity productsEntity, String str, c cVar) {
        AppMethodBeat.i(23420, false);
        a aVar = new a(context, productsEntity);
        aVar.a(productsEntity);
        aVar.a(cVar);
        aVar.b(str);
        aVar.show();
        AppMethodBeat.o(23420);
    }

    public static void a(Context context, SpuParams spuParams, String str, n nVar) {
        AppMethodBeat.i(23421, false);
        b bVar = new b(context, spuParams);
        bVar.a(spuParams);
        bVar.a(nVar);
        bVar.b(str);
        bVar.show();
        AppMethodBeat.o(23421);
    }

    public static void a(Context context, FragmentManager fragmentManager, ProductDetail productDetail, k kVar) {
        AppMethodBeat.i(23422, false);
        ProductsEntity productsEntity = new ProductsEntity();
        productsEntity.setSpuInfo(productDetail.getSpuInfo());
        productsEntity.setSkuProp(productDetail.getSkuProp());
        a aVar = new a(context, productsEntity);
        aVar.a(productsEntity);
        aVar.a(kVar);
        aVar.show();
        AppMethodBeat.o(23422);
    }
}
