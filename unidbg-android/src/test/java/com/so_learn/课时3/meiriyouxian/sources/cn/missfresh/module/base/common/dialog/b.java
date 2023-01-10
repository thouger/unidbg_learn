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
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.BaseSkuBean;
import cn.missfresh.module.base.bean.PricePro;
import cn.missfresh.module.base.bean.SelectSkuProductBean;
import cn.missfresh.module.base.bean.SelectSpuUiData;
import cn.missfresh.module.base.bean.SpuParams;
import cn.missfresh.module.base.bean.VipTag;
import cn.missfresh.module.base.common.interfaces.k;
import cn.missfresh.module.base.common.interfaces.l;
import cn.missfresh.module.base.common.interfaces.n;
import cn.missfresh.module.base.common.providers.IShoppingCartService2;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.widget.ProductPriceView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import cn.missfresh.utils.e;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: SelectSpuDialog */
public class b extends DialogFromBottom implements l {
    private SpuParams a;
    private SelectSpuUiData b;
    private SelectSkuProductBean c;
    private String[] d;
    private LinearLayout e;
    private ImageView f;
    private TextView g;
    private TextView h;
    private ProductPriceView i;
    private Button j;
    private ImageView k;
    private ImageView l;
    private TextView m;
    private ImageView n;
    private k o;
    private HashMap<String, String> p = new HashMap<>();
    private LayoutInflater q;
    private SpuParams.SpuInfoBean.SkuInfoBean r;
    private n s;
    private String t;
    private int u = 0;

    private int a(int i, int i2) {
        return (i2 != 0 && i > i2) ? i2 : i;
    }

    static /* synthetic */ int a(b bVar, int i, int i2) {
        AppMethodBeat.i(11491, false);
        int a = bVar.a(i, i2);
        AppMethodBeat.o(11491);
        return a;
    }

    static /* synthetic */ int a(b bVar, String str) {
        AppMethodBeat.i(11482, false);
        int e = bVar.e(str);
        AppMethodBeat.o(11482);
        return e;
    }

    static /* synthetic */ boolean a(b bVar) {
        AppMethodBeat.i(11478, false);
        boolean f = bVar.f();
        AppMethodBeat.o(11478);
        return f;
    }

    static /* synthetic */ void b(b bVar, String str) {
        AppMethodBeat.i(11494, false);
        bVar.f(str);
        AppMethodBeat.o(11494);
    }

    static /* synthetic */ boolean b(b bVar) {
        AppMethodBeat.i(11479, false);
        boolean g = bVar.g();
        AppMethodBeat.o(11479);
        return g;
    }

    static /* synthetic */ String[] k(b bVar) {
        AppMethodBeat.i(11489, false);
        String[] i = bVar.i();
        AppMethodBeat.o(11489);
        return i;
    }

    public b(Context context, SpuParams spuParams) {
        super(context);
        AppMethodBeat.i(11442, false);
        this.a = spuParams;
        setContentView(R.layout.dialog_select_sku);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        getWindow().setGravity(80);
        StatisticsManager.m("page_view", "sku", spuParams.getSku());
        setOnDismissListener(new AnonymousClass1(spuParams));
        c();
        a();
        AppMethodBeat.o(11442);
    }

    /* compiled from: SelectSpuDialog */
    /* renamed from: cn.missfresh.module.base.common.dialog.b$1  reason: invalid class name */
    class AnonymousClass1 implements DialogInterface.OnDismissListener {
        final /* synthetic */ SpuParams a;

        AnonymousClass1(SpuParams spuParams) {
            this.a = spuParams;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            AppMethodBeat.i(11395, false);
            StatisticsManager.m("click_close", "sku", this.a.getSku());
            AppMethodBeat.o(11395);
        }
    }

    private void c() {
        AppMethodBeat.i(11444, false);
        this.q = LayoutInflater.from(getContext());
        this.e = (LinearLayout) findViewById(R.id.ll_list);
        this.f = (ImageView) findViewById(R.id.product_img);
        this.g = (TextView) findViewById(R.id.product_name);
        this.i = (ProductPriceView) findViewById(R.id.product_price);
        this.h = (TextView) findViewById(R.id.product_desc);
        this.j = (Button) findViewById(R.id.add_cart_btn);
        this.n = (ImageView) findViewById(R.id.close_btn);
        this.n.setOnClickListener(new SelectSpuDialog$2(this));
        this.j.setOnClickListener(new SelectSpuDialog$3(this));
        AppMethodBeat.o(11444);
    }

    public void a() {
        AppMethodBeat.i(11446, false);
        SpuParams spuParams = this.a;
        if (!(spuParams == null || spuParams.getSpuInfo() == null || this.a.getSpuInfo().getSkuInfo() == null)) {
            this.b = new SelectSpuUiData();
            this.c = new SelectSkuProductBean();
            for (SpuParams.SpuInfoBean.SkuInfoBean skuInfoBean : this.a.getSpuInfo().getSkuInfo()) {
                this.c.getProductStocks().put(skuInfoBean.getSkuProp(), new BaseSkuBean(skuInfoBean.getPricePro(), (long) skuInfoBean.getStock()));
            }
            this.c.defaultSelectedSku = this.a.getSkuProp();
            this.r = d(this.a.getSkuProp());
            int i = 1;
            for (SpuParams.SpuInfoBean.PropListBean propListBean : this.a.getSpuInfo().getPropList()) {
                SelectSkuProductBean.AttributesEntity attributesEntity = new SelectSkuProductBean.AttributesEntity();
                attributesEntity.setName(propListBean.getPropName());
                int i2 = 0;
                for (SpuParams.SpuInfoBean.PropListBean.SubListBean subListBean : propListBean.getSubList()) {
                    attributesEntity.getAttributeMembers().add(i2, new SelectSkuProductBean.AttributesEntity.AttributeMembersEntity(i, subListBean.getKey(), subListBean.getValue()));
                    this.p.put(subListBean.getKey() + "", subListBean.getValue());
                    i2++;
                }
                this.c.getAttributes().add(i - 1, attributesEntity);
                i++;
            }
            d();
            h();
        }
        AppMethodBeat.o(11446);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x02c0, code lost:
        if (r15.b.getResult().get(r5.getAttributeMemberId() + "").getStock() <= 0) goto L_0x02c2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d() {
        /*
        // Method dump skipped, instructions count: 784
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.common.dialog.b.d():void");
    }

    /* compiled from: SelectSpuDialog */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.dialog.b$2  reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.ItemDecoration {
        AnonymousClass2() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            AppMethodBeat.i(11410, false);
            rect.set(0, 0, aw.b(10), aw.b(10));
            AppMethodBeat.o(11410);
        }
    }

    /* compiled from: SelectSpuDialog */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.dialog.b$3  reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.ItemDecoration {
        AnonymousClass3() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            AppMethodBeat.i(11417, false);
            rect.set(0, 0, aw.b(10), aw.b(10));
            AppMethodBeat.o(11417);
        }
    }

    /* compiled from: SelectSpuDialog */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.dialog.b$4  reason: invalid class name */
    public class AnonymousClass4 implements ViewTreeObserver.OnGlobalLayoutListener {
        AnonymousClass4() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            AppMethodBeat.i(11423, false);
            int i = 306;
            if (aw.a(b.this.e.getHeight()) <= 306) {
                i = aw.a(b.this.e.getHeight());
            }
            b.this.getWindow().setLayout(-1, aw.b(i + 211));
            if (Build.VERSION.SDK_INT >= 16) {
                b.this.e.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else {
                b.this.e.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
            AppMethodBeat.o(11423);
        }
    }

    private void a(String str, String str2, PricePro pricePro, VipTag vipTag, String str3) {
        AppMethodBeat.i(11451, false);
        d.d(getContext(), str, this.f);
        this.g.setText(str2);
        this.i.a(pricePro, vipTag, false);
        this.h.setText(str3);
        AppMethodBeat.o(11451);
    }

    private void e() {
        AppMethodBeat.i(11453, false);
        LinearLayout linearLayout = (LinearLayout) this.q.inflate(R.layout.dialog_select_sku_count, (ViewGroup) null);
        this.m = (TextView) linearLayout.findViewById(R.id.tv_main_item_product_count);
        this.k = (ImageView) linearLayout.findViewById(R.id.tv_main_item_sub);
        this.l = (ImageView) linearLayout.findViewById(R.id.tv_main_item_add);
        this.k.setOnClickListener(new SelectSpuDialog$7(this));
        this.l.setOnClickListener(new SelectSpuDialog$8(this));
        this.e.addView(linearLayout);
        AppMethodBeat.o(11453);
    }

    private String c(String str) {
        String[] split;
        AppMethodBeat.i(11456, false);
        String str2 = "";
        if (!e.a(str) && (split = str.split(";")) != null && split.length > 0) {
            int length = split.length;
            for (int i = 0; i < length; i++) {
                if (!e.a(this.p.get(split[i]))) {
                    StringBuilder sb = new StringBuilder();
                    if (!e.a(str2)) {
                        str2 = str2 + NotificationIconUtil.SPLIT_CHAR;
                    }
                    sb.append(str2);
                    sb.append(this.p.get(split[i]));
                    str2 = sb.toString();
                }
            }
        }
        String str3 = "\u5df2\u9009\uff1a";
        if (!cn.missfresh.utils.b.a(this.a.getSpuInfo().getServiceList())) {
            String str4 = e.a(str2) ? "\u5df2\u9009\uff1a\u8bf7\u9009\u62e9\u670d\u52a1" : str3 + str2 + "/\u8bf7\u9009\u62e9\u670d\u52a1";
            AppMethodBeat.o(11456);
            return str4;
        }
        if (!e.a(str2)) {
            str3 = str3 + str2;
        }
        AppMethodBeat.o(11456);
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
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.common.dialog.b.b():void");
    }

    public void a(SpuParams spuParams) {
        this.a = spuParams;
    }

    public void a(String str) {
        AppMethodBeat.i(11462, false);
        a.a(str);
        AppMethodBeat.o(11462);
    }

    private boolean f() {
        SelectSpuUiData selectSpuUiData;
        boolean z = false;
        AppMethodBeat.i(11464, false);
        SpuParams spuParams = this.a;
        if (spuParams == null || spuParams.getSpuInfo() == null || this.a.getSpuInfo().getPropList() == null || (selectSpuUiData = this.b) == null || selectSpuUiData.getSelectedEntities() == null) {
            AppMethodBeat.o(11464);
            return false;
        }
        if (this.a.getSpuInfo().getPropList().size() == this.b.getSelectedEntities().size()) {
            z = true;
        }
        AppMethodBeat.o(11464);
        return z;
    }

    private boolean g() {
        SelectSpuUiData selectSpuUiData;
        boolean z = false;
        AppMethodBeat.i(11466, false);
        SpuParams spuParams = this.a;
        if (spuParams == null || spuParams.getSpuInfo() == null || this.a.getSpuInfo().getServiceList() == null || (selectSpuUiData = this.b) == null || selectSpuUiData.getSelectedEntities() == null) {
            AppMethodBeat.o(11466);
            return false;
        }
        if (this.a.getSpuInfo().getServiceList().size() == this.b.getServiceResult().size()) {
            z = true;
        }
        AppMethodBeat.o(11466);
        return z;
    }

    private SpuParams.SpuInfoBean.SkuInfoBean d(String str) {
        AppMethodBeat.i(11467, false);
        SpuParams spuParams = this.a;
        if (!(spuParams == null || spuParams.getSpuInfo() == null || this.a.getSpuInfo().getSkuInfo() == null)) {
            for (SpuParams.SpuInfoBean.SkuInfoBean skuInfoBean : this.a.getSpuInfo().getSkuInfo()) {
                if (skuInfoBean.getSkuProp().equalsIgnoreCase(str)) {
                    AppMethodBeat.o(11467);
                    return skuInfoBean;
                }
            }
        }
        AppMethodBeat.o(11467);
        return null;
    }

    @Override // cn.missfresh.module.base.common.interfaces.l
    public void a(boolean z) {
        StringBuilder sb;
        StringBuilder sb2;
        AppMethodBeat.i(11468, false);
        String str = "";
        String str2 = str;
        for (SelectSkuProductBean.AttributesEntity.AttributeMembersEntity attributeMembersEntity : this.b.getSelectedEntities()) {
            str = e.a(str) ? str + attributeMembersEntity.getName() : str + NotificationIconUtil.SPLIT_CHAR + attributeMembersEntity.getName();
            if (e.a(str2)) {
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
        for (SpuParams.SpuInfoBean.ServiceListBean serviceListBean : this.a.getSpuInfo().getServiceList()) {
            if (this.b.getServiceResult().get(serviceListBean.getPropKey()) != null) {
                if (e.a(str)) {
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
        TextView textView = this.h;
        String str3 = "\u5df2\u9009\uff1a";
        if (!e.a(str)) {
            str3 = str3 + str;
        }
        textView.setText(str3);
        if (!z) {
            if (f()) {
                SpuParams.SpuInfoBean.SkuInfoBean d = d(str2);
                if (d != null) {
                    this.r = d;
                    d.d(getContext(), d.getImage(), this.f);
                    this.g.setText(d.getName());
                    this.i.a(d.getPricePro(), d.getVipTag(), false);
                    this.m.setText("1");
                }
            } else {
                this.r = null;
            }
        }
        h();
        AppMethodBeat.o(11468);
    }

    private int e(String str) {
        AppMethodBeat.i(11471, false);
        IShoppingCartService2 iShoppingCartService2 = (IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation();
        if (iShoppingCartService2 == null || e.a(str)) {
            AppMethodBeat.o(11471);
            return 0;
        }
        int b = iShoppingCartService2.b(str);
        AppMethodBeat.o(11471);
        return b;
    }

    private void h() {
        AppMethodBeat.i(11472, false);
        if (!f() || !g()) {
            this.l.setEnabled(false);
            this.k.setEnabled(false);
        } else if (this.r == null) {
            a(Integer.parseInt(this.m.getText().toString()), this.a.getStock(), this.a.getSeckill_limit());
        } else {
            a(Integer.parseInt(this.m.getText().toString()), this.r.getStock(), this.r.getSeckill_limit());
        }
        AppMethodBeat.o(11472);
    }

    private void a(int i, int i2, int i3) {
        AppMethodBeat.i(11473, false);
        if (i == a(i2, i3)) {
            this.l.setEnabled(false);
            this.k.setEnabled(false);
        } else {
            this.l.setEnabled(true);
            if (i == 1) {
                this.k.setEnabled(false);
            } else {
                this.k.setEnabled(true);
            }
        }
        AppMethodBeat.o(11473);
    }

    private void f(String str) {
        AppMethodBeat.i(11474, false);
        if ("1".equalsIgnoreCase(str)) {
            this.k.setEnabled(false);
        } else {
            this.k.setEnabled(true);
        }
        AppMethodBeat.o(11474);
    }

    private String[] i() {
        int i = 0;
        AppMethodBeat.i(11475, false);
        SelectSpuUiData selectSpuUiData = this.b;
        if (selectSpuUiData == null || selectSpuUiData.getServiceResult() == null || this.b.getServiceResult().size() <= 0) {
            AppMethodBeat.o(11475);
            return null;
        }
        String[] strArr = new String[this.b.getServiceResult().size()];
        Iterator<String> it2 = this.b.getServiceResult().keySet().iterator();
        while (it2.hasNext()) {
            strArr[i] = this.b.getServiceResult().get(it2.next()).getServiceId() + "";
            i++;
        }
        AppMethodBeat.o(11475);
        return strArr;
    }

    public void a(n nVar) {
        this.s = nVar;
    }

    public void b(String str) {
        this.t = str;
    }
}
