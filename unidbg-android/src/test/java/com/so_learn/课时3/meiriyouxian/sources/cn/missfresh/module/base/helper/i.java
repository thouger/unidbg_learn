package cn.missfresh.module.base.helper;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.AddResult;
import cn.missfresh.module.base.bean.PricePro;
import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.module.base.common.interfaces.c;
import cn.missfresh.module.base.common.providers.IShoppingCartService2;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.hjq.toast.ToastUtils;
import io.reactivex.v;
import java.util.ArrayList;

/* compiled from: ProductHelper */
public class i {
    public static final String a = aw.c().getString(R.string.RMB);
    private IShoppingCartService2 b = ((IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation());

    /* compiled from: ProductHelper */
    public interface a {
        void a(AddResult addResult);
    }

    static {
        AppMethodBeat.i(13141, false);
        AppMethodBeat.o(13141);
    }

    public i() {
        AppMethodBeat.i(13117, false);
        AppMethodBeat.o(13117);
    }

    public int a(ProductsEntity productsEntity) {
        AppMethodBeat.i(13118, false);
        if (this.b == null || productsEntity == null) {
            AppMethodBeat.o(13118);
            return -1;
        } else if (!productsEntity.isSpu()) {
            int b2 = this.b.b(productsEntity.getSku());
            AppMethodBeat.o(13118);
            return b2;
        } else if (productsEntity.getSpuInfo() == null || cn.missfresh.utils.b.a(productsEntity.getSpuInfo().getSkuInfo())) {
            AppMethodBeat.o(13118);
            return -1;
        } else {
            ArrayList arrayList = new ArrayList();
            int size = productsEntity.getSpuInfo().getSkuInfo().size();
            for (int i = 0; i < size; i++) {
                arrayList.add(productsEntity.getSpuInfo().getSkuInfo().get(i).getSku());
            }
            int a2 = this.b.a(arrayList);
            AppMethodBeat.o(13118);
            return a2;
        }
    }

    public void a(ProductsEntity productsEntity, String str, int i, String str2) {
        AppMethodBeat.i(13120, false);
        if (!(this.b == null || productsEntity == null)) {
            String algoId = productsEntity.getAlgoId();
            if (!cn.missfresh.utils.b.a(algoId)) {
                str = algoId;
            }
            this.b.a(str, productsEntity.getSku(), i, str2);
        }
        AppMethodBeat.o(13120);
    }

    public void a(ProductsEntity productsEntity, String str, int i, a aVar) {
        AppMethodBeat.i(13121, false);
        if (!(this.b == null || productsEntity == null)) {
            String algoId = productsEntity.getAlgoId();
            if (!cn.missfresh.utils.b.a(algoId)) {
                str = algoId;
            }
            this.b.a(str, productsEntity.getSku(), i).a(io.reactivex.a.b.a.a()).subscribe(new AnonymousClass1(aVar));
        }
        AppMethodBeat.o(13121);
    }

    /* compiled from: ProductHelper */
    /* renamed from: cn.missfresh.module.base.helper.i$1  reason: invalid class name */
    class AnonymousClass1 implements v<AddResult> {
        final /* synthetic */ a a;

        @Override // io.reactivex.v
        public void onComplete() {
        }

        @Override // io.reactivex.v
        public void onSubscribe(io.reactivex.disposables.b bVar) {
        }

        AnonymousClass1(a aVar) {
            this.a = aVar;
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(13116, false);
            a((AddResult) obj);
            AppMethodBeat.o(13116);
        }

        public void a(AddResult addResult) {
            AppMethodBeat.i(13114, false);
            if (addResult != null && !TextUtils.isEmpty(addResult.msg)) {
                ToastUtils.show((CharSequence) addResult.msg);
            }
            a aVar = this.a;
            if (aVar != null) {
                aVar.a(addResult);
            }
            AppMethodBeat.o(13114);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            AppMethodBeat.i(13115, false);
            a aVar = this.a;
            if (aVar != null) {
                aVar.a(null);
            }
            AppMethodBeat.o(13115);
        }
    }

    public void a(ProductsEntity productsEntity, String str) {
        AppMethodBeat.i(13122, false);
        if (this.b != null) {
            String algoId = productsEntity.getAlgoId();
            if (!cn.missfresh.utils.b.a(algoId)) {
                str = algoId;
            }
            this.b.b(str, productsEntity.getSku(), 1);
        }
        AppMethodBeat.o(13122);
    }

    public void a(ProductsEntity productsEntity, String str, String str2) {
        AppMethodBeat.i(13123, false);
        if (this.b != null) {
            String algoId = productsEntity.getAlgoId();
            if (!cn.missfresh.utils.b.a(algoId)) {
                str = algoId;
            }
            this.b.a(str, productsEntity.getSku(), 1, str2);
        }
        AppMethodBeat.o(13123);
    }

    public void a(PricePro.Price price, TextView textView, boolean z) {
        AppMethodBeat.i(13125, false);
        a(price, textView, (TextView) null, z);
        AppMethodBeat.o(13125);
    }

    public void a(PricePro.Price price, TextView textView, TextView textView2, boolean z) {
        int i;
        int i2 = 0;
        AppMethodBeat.i(13127, false);
        if (price != null) {
            if (price.getShowStyle() == 1) {
                i = textView.getPaintFlags() | 16;
            } else {
                i = textView.getPaintFlags() & -17;
            }
            if (textView.getPaint().getFlags() != i) {
                textView.getPaint().setFlags(i);
            }
            if (price.getShowStyle() == 2) {
                i2 = R.drawable.ic_product_vip_tag;
            }
            textView.setBackgroundResource(i2);
            int color = price.getColor();
            textView.setTextColor(color);
            if (z) {
                StringBuilder sb = new StringBuilder(a);
                sb.append(at.b(price.getPrice()));
                textView.setText(sb);
            } else {
                textView.setText(at.b(price.getPrice()));
                if (textView2 != null) {
                    textView2.setTextColor(color);
                }
            }
        } else {
            textView.setText("");
            textView.setBackgroundResource(0);
        }
        AppMethodBeat.o(13127);
    }

    public void a(TextView textView, ProductsEntity productsEntity) {
        AppMethodBeat.i(13131, false);
        a(textView, (ImageView) null, productsEntity);
        AppMethodBeat.o(13131);
    }

    public void a(TextView textView, ImageView imageView, ProductsEntity productsEntity) {
        AppMethodBeat.i(13133, false);
        a(textView, imageView, b(productsEntity));
        AppMethodBeat.o(13133);
    }

    private int b(ProductsEntity productsEntity) {
        AppMethodBeat.i(13134, false);
        if (this.b == null || productsEntity == null) {
            AppMethodBeat.o(13134);
            return -1;
        } else if (!productsEntity.isSpu()) {
            int b2 = this.b.b(productsEntity.getSku());
            AppMethodBeat.o(13134);
            return b2;
        } else if (productsEntity.getSpuInfo() == null || cn.missfresh.utils.b.a(productsEntity.getSpuInfo().getSkuInfo())) {
            AppMethodBeat.o(13134);
            return -1;
        } else {
            ArrayList arrayList = new ArrayList();
            int size = productsEntity.getSpuInfo().getSkuInfo().size();
            for (int i = 0; i < size; i++) {
                arrayList.add(productsEntity.getSpuInfo().getSkuInfo().get(i).getSku());
            }
            int a2 = this.b.a(arrayList);
            AppMethodBeat.o(13134);
            return a2;
        }
    }

    public boolean a(TextView textView, int i, ProductsEntity productsEntity) {
        AppMethodBeat.i(13135, false);
        boolean a2 = a(textView, (ImageView) null, i, productsEntity);
        AppMethodBeat.o(13135);
        return a2;
    }

    public boolean a(TextView textView, ImageView imageView, int i, ProductsEntity productsEntity) {
        AppMethodBeat.i(13137, false);
        if (productsEntity == null) {
            AppMethodBeat.o(13137);
            return false;
        } else if (!j.a(productsEntity, productsEntity.getPermission_msg())) {
            AppMethodBeat.o(13137);
            return false;
        } else {
            int a2 = a(productsEntity);
            if (a2 < 0) {
                AppMethodBeat.o(13137);
                return false;
            }
            int i2 = a2 + i;
            productsEntity.setCount(i2);
            a(textView, imageView, i2);
            AppMethodBeat.o(13137);
            return true;
        }
    }

    public void a(TextView textView, int i) {
        AppMethodBeat.i(13139, false);
        a(textView, (ImageView) null, i);
        AppMethodBeat.o(13139);
    }

    public void a(TextView textView, ImageView imageView, int i) {
        AppMethodBeat.i(13140, false);
        if (i > 0) {
            textView.setVisibility(0);
            if (i > 99) {
                textView.setText("99+");
            } else {
                textView.setText(String.valueOf(i));
            }
            if (imageView != null) {
                imageView.setVisibility(0);
            }
        } else {
            textView.setVisibility(8);
            if (imageView != null) {
                imageView.setVisibility(8);
            }
        }
        AppMethodBeat.o(13140);
    }

    /* compiled from: ProductHelper */
    public static abstract class b implements c {
        private boolean a;
        private boolean b = true;
        private c.a c;
        private i d;

        public abstract boolean a(ProductsEntity productsEntity);

        public abstract void b(int i);

        public b(i iVar) {
            this.d = iVar;
        }

        @Override // cn.missfresh.module.base.common.interfaces.c
        public void setShowCount(boolean z) {
            this.a = z;
        }

        @Override // cn.missfresh.module.base.common.interfaces.c
        public void setBuyViewClickedListener(c.a aVar) {
            this.c = aVar;
        }

        @Override // cn.missfresh.module.base.common.interfaces.c
        public void a(int i) {
            b(i);
        }

        @Override // cn.missfresh.module.base.common.interfaces.m
        public void setShow(boolean z) {
            this.b = z;
        }
    }
}
