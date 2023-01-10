package cn.missfresh.module.base.common.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.BaseSkuBean;
import cn.missfresh.module.base.bean.PricePro;
import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.module.base.bean.SelectSkuProductBean;
import cn.missfresh.module.base.bean.SelectSkuUiData;
import cn.missfresh.module.base.bean.VipTag;
import cn.missfresh.module.base.common.interfaces.c;
import cn.missfresh.module.base.common.interfaces.k;
import cn.missfresh.module.base.common.interfaces.l;
import cn.missfresh.module.base.common.interfaces.n;
import cn.missfresh.module.base.common.providers.IShoppingCartService2;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.widget.ProductPriceView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: SelectSkuDialog */
public class a extends DialogFromBottom implements l {
    private ProductsEntity a;
    private SelectSkuUiData b;
    private SelectSkuProductBean c;
    private String[] d;
    private NestedScrollView e;
    private LinearLayout f;
    private ImageView g;
    private TextView h;
    private TextView i;
    private ProductPriceView j;
    private Button k;
    private RelativeLayout l;
    private ImageView m;
    private ImageView n;
    private TextView o;
    private ImageView p;
    private k q;
    private int r = 0;
    private HashMap<String, String> s = new HashMap<>();
    private LayoutInflater t;
    private ProductsEntity.SpuInfoBean.SkuInfoBean u;
    private c v;
    private n w;
    private String x;
    private int y = 0;

    private int a(int i, int i2) {
        return (i2 != 0 && i > i2) ? i2 : i;
    }

    static /* synthetic */ int a(a aVar, int i, int i2) {
        AppMethodBeat.i(11391, false);
        int a = aVar.a(i, i2);
        AppMethodBeat.o(11391);
        return a;
    }

    static /* synthetic */ int a(a aVar, String str) {
        AppMethodBeat.i(11384, false);
        int e = aVar.e(str);
        AppMethodBeat.o(11384);
        return e;
    }

    static /* synthetic */ boolean a(a aVar) {
        AppMethodBeat.i(11377, false);
        boolean f = aVar.f();
        AppMethodBeat.o(11377);
        return f;
    }

    static /* synthetic */ void b(a aVar, String str) {
        AppMethodBeat.i(11393, false);
        aVar.f(str);
        AppMethodBeat.o(11393);
    }

    static /* synthetic */ boolean b(a aVar) {
        AppMethodBeat.i(11378, false);
        boolean g = aVar.g();
        AppMethodBeat.o(11378);
        return g;
    }

    static /* synthetic */ String[] l(a aVar) {
        AppMethodBeat.i(11389, false);
        String[] i = aVar.i();
        AppMethodBeat.o(11389);
        return i;
    }

    public a(Context context, ProductsEntity productsEntity) {
        super(context);
        AppMethodBeat.i(11315, false);
        this.a = productsEntity;
        setContentView(R.layout.dialog_select_sku);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        getWindow().setGravity(80);
        StatisticsManager.m("page_view", "sku", productsEntity.getSku());
        setOnDismissListener(new AnonymousClass1(productsEntity));
        c();
        a();
        AppMethodBeat.o(11315);
    }

    /* compiled from: SelectSkuDialog */
    /* renamed from: cn.missfresh.module.base.common.dialog.a$1  reason: invalid class name */
    class AnonymousClass1 implements DialogInterface.OnDismissListener {
        final /* synthetic */ ProductsEntity a;

        AnonymousClass1(ProductsEntity productsEntity) {
            this.a = productsEntity;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            AppMethodBeat.i(11306, false);
            StatisticsManager.m("click_close", "sku", this.a.getSku());
            AppMethodBeat.o(11306);
        }
    }

    private void c() {
        AppMethodBeat.i(11338, false);
        this.t = LayoutInflater.from(getContext());
        this.e = (NestedScrollView) findViewById(R.id.scrollView);
        this.f = (LinearLayout) findViewById(R.id.ll_list);
        this.g = (ImageView) findViewById(R.id.product_img);
        this.h = (TextView) findViewById(R.id.product_name);
        this.j = (ProductPriceView) findViewById(R.id.product_price);
        this.i = (TextView) findViewById(R.id.product_desc);
        this.k = (Button) findViewById(R.id.add_cart_btn);
        this.p = (ImageView) findViewById(R.id.close_btn);
        this.l = (RelativeLayout) findViewById(R.id.rl_bottom);
        this.p.setOnClickListener(new SelectSkuDialog$2(this));
        this.k.setOnClickListener(new SelectSkuDialog$3(this));
        AppMethodBeat.o(11338);
    }

    public void a() {
        AppMethodBeat.i(11340, false);
        ProductsEntity productsEntity = this.a;
        if (!(productsEntity == null || productsEntity.getSpuInfo() == null || this.a.getSpuInfo().getSkuInfo() == null)) {
            this.b = new SelectSkuUiData();
            this.c = new SelectSkuProductBean();
            for (ProductsEntity.SpuInfoBean.SkuInfoBean skuInfoBean : this.a.getSpuInfo().getSkuInfo()) {
                this.c.getProductStocks().put(skuInfoBean.getSkuProp(), new BaseSkuBean(skuInfoBean.getPricePro(), (long) skuInfoBean.getStock()));
            }
            this.c.defaultSelectedSku = this.a.getSkuProp();
            this.u = d(this.a.getSkuProp());
            int i = 1;
            for (ProductsEntity.SpuInfoBean.PropListBean propListBean : this.a.getSpuInfo().getPropList()) {
                SelectSkuProductBean.AttributesEntity attributesEntity = new SelectSkuProductBean.AttributesEntity();
                attributesEntity.setName(propListBean.getPropName());
                int i2 = 0;
                for (ProductsEntity.SpuInfoBean.PropListBean.SubListBean subListBean : propListBean.getSubList()) {
                    attributesEntity.getAttributeMembers().add(i2, new SelectSkuProductBean.AttributesEntity.AttributeMembersEntity(i, subListBean.getKey(), subListBean.getValue()));
                    this.s.put(subListBean.getKey() + "", subListBean.getValue());
                    i2++;
                }
                this.c.getAttributes().add(i - 1, attributesEntity);
                i++;
            }
            d();
            h();
        }
        AppMethodBeat.o(11340);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x02cc, code lost:
        if (r15.b.getResult().get(r6.getAttributeMemberId() + "").getStock() <= 0) goto L_0x02ce;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d() {
        /*
        // Method dump skipped, instructions count: 795
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.common.dialog.a.d():void");
    }

    /* compiled from: SelectSkuDialog */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.dialog.a$2  reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.ItemDecoration {
        AnonymousClass2() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            AppMethodBeat.i(11310, false);
            rect.set(0, 0, aw.b(10), aw.b(10));
            AppMethodBeat.o(11310);
        }
    }

    /* compiled from: SelectSkuDialog */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.dialog.a$3  reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.ItemDecoration {
        AnonymousClass3() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            AppMethodBeat.i(11311, false);
            rect.set(0, 0, aw.b(10), aw.b(10));
            AppMethodBeat.o(11311);
        }
    }

    /* compiled from: SelectSkuDialog */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.dialog.a$4  reason: invalid class name */
    public class AnonymousClass4 implements ViewTreeObserver.OnGlobalLayoutListener {
        AnonymousClass4() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            AppMethodBeat.i(11312, false);
            int i = 306;
            if (aw.a(a.this.f.getHeight()) <= 306) {
                i = aw.a(a.this.f.getHeight());
            }
            a.this.getWindow().setLayout(-1, aw.b(i + 211));
            if (Build.VERSION.SDK_INT >= 16) {
                a.this.f.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else {
                a.this.f.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
            AppMethodBeat.o(11312);
        }
    }

    private void a(String str, String str2, PricePro pricePro, VipTag vipTag, String str3) {
        AppMethodBeat.i(11343, false);
        d.d(getContext(), str, this.g);
        this.h.setText(str2);
        this.j.a(pricePro, vipTag, false);
        this.i.setText(str3);
        AppMethodBeat.o(11343);
    }

    private void e() {
        AppMethodBeat.i(11345, false);
        LinearLayout linearLayout = (LinearLayout) this.t.inflate(R.layout.dialog_select_sku_count, (ViewGroup) null);
        this.o = (TextView) linearLayout.findViewById(R.id.tv_main_item_product_count);
        this.m = (ImageView) linearLayout.findViewById(R.id.tv_main_item_sub);
        this.n = (ImageView) linearLayout.findViewById(R.id.tv_main_item_add);
        this.m.setOnClickListener(new SelectSkuDialog$7(this));
        this.n.setOnClickListener(new SelectSkuDialog$8(this));
        this.f.addView(linearLayout);
        AppMethodBeat.o(11345);
    }

    private String c(String str) {
        String[] split;
        AppMethodBeat.i(11349, false);
        String str2 = "";
        if (!b.a(str) && (split = str.split(";")) != null && split.length > 0) {
            int length = split.length;
            for (int i = 0; i < length; i++) {
                if (!b.a(this.s.get(split[i]))) {
                    StringBuilder sb = new StringBuilder();
                    if (!b.a(str2)) {
                        str2 = str2 + NotificationIconUtil.SPLIT_CHAR;
                    }
                    sb.append(str2);
                    sb.append(this.s.get(split[i]));
                    str2 = sb.toString();
                }
            }
        }
        String str3 = "\u5df2\u9009\uff1a";
        if (!b.a(this.a.getSpuInfo().getServiceList())) {
            String str4 = b.a(str2) ? "\u5df2\u9009\uff1a\u8bf7\u9009\u62e9\u670d\u52a1" : str3 + str2 + "/\u8bf7\u9009\u62e9\u670d\u52a1";
            AppMethodBeat.o(11349);
            return str4;
        }
        if (!b.a(str2)) {
            str3 = str3 + str2;
        }
        AppMethodBeat.o(11349);
        return str3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0174 A[LOOP:7: B:44:0x016e->B:46:0x0174, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
        // Method dump skipped, instructions count: 495
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.common.dialog.a.b():void");
    }

    public void a(k kVar) {
        this.q = kVar;
    }

    public void a(ProductsEntity productsEntity) {
        this.a = productsEntity;
    }

    public void a(String str) {
        AppMethodBeat.i(11356, false);
        cn.missfresh.ui.a.a.a(str);
        AppMethodBeat.o(11356);
    }

    private boolean f() {
        SelectSkuUiData selectSkuUiData;
        boolean z = false;
        AppMethodBeat.i(11359, false);
        ProductsEntity productsEntity = this.a;
        if (productsEntity == null || productsEntity.getSpuInfo() == null || this.a.getSpuInfo().getPropList() == null || (selectSkuUiData = this.b) == null || selectSkuUiData.getSelectedEntities() == null) {
            AppMethodBeat.o(11359);
            return false;
        }
        if (this.a.getSpuInfo().getPropList().size() == this.b.getSelectedEntities().size()) {
            z = true;
        }
        AppMethodBeat.o(11359);
        return z;
    }

    private boolean g() {
        SelectSkuUiData selectSkuUiData;
        boolean z = false;
        AppMethodBeat.i(11362, false);
        ProductsEntity productsEntity = this.a;
        if (productsEntity == null || productsEntity.getSpuInfo() == null || this.a.getSpuInfo().getServiceList() == null || (selectSkuUiData = this.b) == null || selectSkuUiData.getSelectedEntities() == null) {
            AppMethodBeat.o(11362);
            return false;
        }
        if (this.a.getSpuInfo().getServiceList().size() == this.b.getServiceResult().size()) {
            z = true;
        }
        AppMethodBeat.o(11362);
        return z;
    }

    private ProductsEntity.SpuInfoBean.SkuInfoBean d(String str) {
        AppMethodBeat.i(11363, false);
        ProductsEntity productsEntity = this.a;
        if (!(productsEntity == null || productsEntity.getSpuInfo() == null || this.a.getSpuInfo().getSkuInfo() == null)) {
            for (ProductsEntity.SpuInfoBean.SkuInfoBean skuInfoBean : this.a.getSpuInfo().getSkuInfo()) {
                if (skuInfoBean != null && skuInfoBean.getSkuProp().equalsIgnoreCase(str)) {
                    AppMethodBeat.o(11363);
                    return skuInfoBean;
                }
            }
        }
        AppMethodBeat.o(11363);
        return null;
    }

    @Override // cn.missfresh.module.base.common.interfaces.l
    public void a(boolean z) {
        StringBuilder sb;
        StringBuilder sb2;
        AppMethodBeat.i(11365, false);
        String str = "";
        String str2 = str;
        for (SelectSkuProductBean.AttributesEntity.AttributeMembersEntity attributeMembersEntity : this.b.getSelectedEntities()) {
            str = b.a(str) ? str + attributeMembersEntity.getName() : str + NotificationIconUtil.SPLIT_CHAR + attributeMembersEntity.getName();
            if (b.a(str2)) {
                sb2 = new StringBuilder();
            } else {
                sb2 = new StringBuilder();
                sb2.append(str2);
                str2 = ";";
            }
            sb2.append(str2);
            sb2.append(attributeMembersEntity.getAttributeMemberId());
            str2 = sb2.toString();
        }
        for (ProductsEntity.SpuInfoBean.ServiceListBean serviceListBean : this.a.getSpuInfo().getServiceList()) {
            if (this.b.getServiceResult().get(serviceListBean.getPropKey()) != null) {
                if (b.a(str)) {
                    sb = new StringBuilder();
                    sb.append(str);
                } else {
                    sb = new StringBuilder();
                    sb.append(str);
                    sb.append(NotificationIconUtil.SPLIT_CHAR);
                }
                sb.append(this.b.getServiceResult().get(serviceListBean.getPropKey()).getServiceName());
                str = sb.toString();
            }
        }
        TextView textView = this.i;
        String str3 = "\u5df2\u9009\uff1a";
        if (!b.a(str)) {
            str3 = str3 + str;
        }
        textView.setText(str3);
        if (!z) {
            if (f()) {
                ProductsEntity.SpuInfoBean.SkuInfoBean d = d(str2);
                if (d != null) {
                    this.u = d;
                    d.d(getContext(), d.getImage(), this.g);
                    this.h.setText(d.getName());
                    this.j.a(d.getPricePro(), d.getVipTag(), false);
                    this.o.setText("1");
                }
            } else {
                this.u = null;
            }
        }
        h();
        AppMethodBeat.o(11365);
    }

    private int e(String str) {
        AppMethodBeat.i(11367, false);
        IShoppingCartService2 iShoppingCartService2 = (IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation();
        if (iShoppingCartService2 == null || b.a(str)) {
            AppMethodBeat.o(11367);
            return 0;
        }
        int b = iShoppingCartService2.b(str);
        AppMethodBeat.o(11367);
        return b;
    }

    private void h() {
        AppMethodBeat.i(11368, false);
        if (!f() || !g()) {
            this.n.setEnabled(false);
            this.m.setEnabled(false);
        } else if (this.u == null) {
            a(Integer.parseInt(this.o.getText().toString()), this.a.getStock(), this.a.getSeckill_limit());
        } else {
            a(Integer.parseInt(this.o.getText().toString()), this.u.getStock(), this.u.getSeckill_limit());
        }
        AppMethodBeat.o(11368);
    }

    private void a(int i, int i2, int i3) {
        AppMethodBeat.i(11369, false);
        if (i == a(i2, i3)) {
            this.n.setEnabled(false);
            this.m.setEnabled(false);
        } else {
            this.n.setEnabled(true);
            if (i == 1) {
                this.m.setEnabled(false);
            } else {
                this.m.setEnabled(true);
            }
        }
        AppMethodBeat.o(11369);
    }

    private void f(String str) {
        AppMethodBeat.i(11370, false);
        if ("1".equalsIgnoreCase(str)) {
            this.m.setEnabled(false);
        } else {
            this.m.setEnabled(true);
        }
        AppMethodBeat.o(11370);
    }

    private String[] i() {
        int i = 0;
        AppMethodBeat.i(11372, false);
        SelectSkuUiData selectSkuUiData = this.b;
        if (selectSkuUiData == null || selectSkuUiData.getServiceResult() == null || this.b.getServiceResult().size() <= 0) {
            AppMethodBeat.o(11372);
            return null;
        }
        String[] strArr = new String[this.b.getServiceResult().size()];
        Iterator<String> it2 = this.b.getServiceResult().keySet().iterator();
        while (it2.hasNext()) {
            strArr[i] = this.b.getServiceResult().get(it2.next()).getServiceId() + "";
            i++;
        }
        AppMethodBeat.o(11372);
        return strArr;
    }

    public void a(c cVar) {
        this.v = cVar;
    }

    public void b(String str) {
        this.x = str;
    }
}
