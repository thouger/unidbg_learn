package cn.missfresh.flutter.feature;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import cn.missfresh.flutter.feature.base.a;
import cn.missfresh.module.base.bean.ProductDetail;
import cn.missfresh.module.base.bean.ProductInfoBean;
import cn.missfresh.module.base.common.event.h;
import cn.missfresh.module.base.common.event.i;
import cn.missfresh.module.base.common.interfaces.k;
import cn.missfresh.module.base.common.providers.IProductDetailService;
import cn.missfresh.module.base.common.providers.IShoppingCartService2;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.support.dialog.SubscriptionDialog;
import cn.missfresh.module.base.utils.ar;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSON;
import com.umeng.message.common.inter.ITagManager;
import de.greenrobot.event.EventBus;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/* compiled from: CommentFlutterFeature */
public class c extends a {
    private final IShoppingCartService2 a = ((IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation());
    private final IProductDetailService b = ((IProductDetailService) com.alibaba.android.arouter.b.a.a().a("/product/product_detail_service").navigation());

    static /* synthetic */ FragmentActivity a(c cVar) {
        AppMethodBeat.i(20906, false);
        FragmentActivity b = cVar.b();
        AppMethodBeat.o(20906);
        return b;
    }

    static /* synthetic */ FragmentActivity b(c cVar) {
        AppMethodBeat.i(20908, false);
        FragmentActivity b = cVar.b();
        AppMethodBeat.o(20908);
        return b;
    }

    static /* synthetic */ FragmentActivity c(c cVar) {
        AppMethodBeat.i(20910, false);
        FragmentActivity b = cVar.b();
        AppMethodBeat.o(20910);
        return b;
    }

    public c(FragmentActivity fragmentActivity, MethodChannel methodChannel) {
        super(fragmentActivity, methodChannel);
        AppMethodBeat.i(20901, false);
        AppMethodBeat.o(20901);
    }

    public void a(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(20902, false);
        if (methodCall.method.equals("commentSCcount")) {
            result.success(String.valueOf(this.a.a()));
        } else if (methodCall.method.equals("commentAddShoppingCart")) {
            a();
        } else if (methodCall.method.equals("commentSubscribe")) {
            b(methodCall, result);
        }
        AppMethodBeat.o(20902);
    }

    /* compiled from: CommentFlutterFeature */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.c$1  reason: invalid class name */
    public class AnonymousClass1 implements cn.missfresh.module.base.common.providers.c {
        final /* synthetic */ String a;
        final /* synthetic */ MethodChannel.Result b;

        AnonymousClass1(String str, MethodChannel.Result result) {
            this.a = str;
            this.b = result;
        }

        @Override // cn.missfresh.module.base.common.providers.c
        public void a() {
            AppMethodBeat.i(20890, false);
            if (b.a(c.a(c.this))) {
                new SubscriptionDialog(c.b(c.this)).show();
                i iVar = new i();
                iVar.a = this.a;
                EventBus.getDefault().post(iVar);
                this.b.success(ITagManager.STATUS_TRUE);
            }
            AppMethodBeat.o(20890);
        }
    }

    private void b(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(20904, false);
        String str = (String) methodCall.argument("sku");
        this.b.commentSubscribe(str, new AnonymousClass1(str, result));
        AppMethodBeat.o(20904);
    }

    private void a() {
        AppMethodBeat.i(20905, false);
        try {
            ProductInfoBean productInfoBean = (ProductInfoBean) JSON.parseObject(e.q(), ProductInfoBean.class);
            this.b.commentAddShoppingCart(productInfoBean, new AnonymousClass2(productInfoBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(20905);
    }

    /* compiled from: CommentFlutterFeature */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.c$2  reason: invalid class name */
    public class AnonymousClass2 implements cn.missfresh.module.base.common.providers.b {
        final /* synthetic */ ProductInfoBean a;

        @Override // cn.missfresh.module.base.common.providers.b
        public void a(boolean z) {
        }

        AnonymousClass2(ProductInfoBean productInfoBean) {
            this.a = productInfoBean;
        }

        /* compiled from: CommentFlutterFeature */
        /* renamed from: cn.missfresh.flutter.feature.c$2$1  reason: invalid class name */
        class AnonymousClass1 implements k {
            AnonymousClass1() {
            }

            @Override // cn.missfresh.module.base.common.interfaces.k
            public void a(String str, boolean z, String str2) {
                AppMethodBeat.i(20894, false);
                h hVar = new h();
                hVar.a = AnonymousClass2.this.a.getSku();
                hVar.b = str;
                hVar.c = str2;
                EventBus.getDefault().post(hVar);
                AppMethodBeat.o(20894);
            }
        }

        @Override // cn.missfresh.module.base.common.providers.b
        public void a(ProductDetail productDetail) {
            AppMethodBeat.i(20897, false);
            ar.a(c.c(c.this), (FragmentManager) null, productDetail, new AnonymousClass1());
            AppMethodBeat.o(20897);
        }
    }
}
