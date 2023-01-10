package cn.missfresh.module.base.common.holder;

import android.app.Activity;
import android.provider.Telephony;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.module.base.common.interfaces.IShelfFun;
import cn.missfresh.module.base.common.interfaces.c;
import cn.missfresh.module.base.common.interfaces.f;
import cn.missfresh.module.base.common.listener.a;
import cn.missfresh.module.base.common.providers.IShoppingCartService2;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.utils.am;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.module.base.widget.ProductTitleView;
import cn.missfresh.utils.b;
import com.umeng.analytics.pro.ai;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract class BaseProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, c.a, f {
    private int a = -1;
    protected ProductsEntity b;
    protected int c;
    protected String d;
    protected String e;
    protected String f;
    protected String g;
    protected int h;
    protected String i;
    protected String j;
    protected String k;
    private a l;
    private String m = "";
    private String n;
    private int o;
    private String p;
    private long q;

    @Override // cn.missfresh.module.base.common.interfaces.c.a
    public void a() {
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return true;
    }

    public BaseProductViewHolder(View view) {
        super(view);
        view.setOnClickListener(this);
    }

    public void a(a aVar) {
        this.l = aVar;
    }

    public void a(int i, String str) {
        this.c = i;
        this.d = str;
    }

    public void a(ProductsEntity productsEntity) {
        this.b = productsEntity;
        if (this.b == null) {
            this.itemView.setVisibility(8);
            return;
        }
        this.itemView.setVisibility(0);
        if (h() != null) {
            h().setPicture(this.b.getImage());
        }
        if (g() != null) {
            g().a(this.b.getName(), this.b.getProductTag());
            g().setSubTitle(this.b.getSubtitle());
        }
        if (f() != null) {
            f().a(this.b.getPriceInfo());
        }
        if (i() != null) {
            i().setProduct(this.b);
            i().setBuyViewClickedListener(this);
        }
        b(this.b);
        if (l() != null) {
            l().setPromotionTags(this.b.getPromotionDeliveryTag());
        }
    }

    /* access modifiers changed from: protected */
    public void b(ProductsEntity productsEntity) {
        if (j() != null) {
            j().b(productsEntity.getBrandTag());
        }
        if (k() != null) {
            k().c(productsEntity.getThemeTag());
        }
    }

    public void b(int i) {
        if (g() != null) {
            ((ProductTitleView) g()).setSubTitleLines(i);
        }
    }

    @Override // cn.missfresh.module.base.common.interfaces.c.a
    public void c(int i) {
        if (this.b != null && b()) {
            d(i);
            if (this.l != null && d()) {
                this.l.a(c());
            }
            m();
        }
    }

    /* access modifiers changed from: protected */
    public void d(int i) {
        String algoId = this.b.getAlgoId();
        if (b.a(algoId)) {
            algoId = this.n;
        }
        a(algoId, this.b.getSku(), 1);
    }

    /* access modifiers changed from: protected */
    public ImageView c() {
        if (h() != null) {
            return h().getImageView();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return (!((this.itemView.getContext() instanceof Activity) && "MainActivity".equals(((Activity) this.itemView.getContext()).getClass().getSimpleName())) || !e.W()) && !"vip_two_recommend".equals(this.b.getType());
    }

    @Override // cn.missfresh.module.base.common.interfaces.c.a
    public void e(int i) {
        if (this.b != null) {
            n();
            e();
        }
    }

    /* access modifiers changed from: protected */
    public void e() {
        a("", this.b.getSku(), -1);
    }

    private void a(String str, String str2, int i) {
        IShoppingCartService2 iShoppingCartService2 = (IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation();
        if (iShoppingCartService2 != null) {
            a(str, str2, i, iShoppingCartService2);
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, int i, IShoppingCartService2 iShoppingCartService2) {
        iShoppingCartService2.b(str, str2, i);
    }

    private void m() {
        if (this.c == IShelfFun.PageType.CLASSIFY_PRODUCT.getCode()) {
            StatisticsManager.e("add_cart", "action", "add", "sku", this.b.getSku(), "pos", Integer.valueOf(this.b.getPosInProductList()), "channel", this.d, "second_channel", this.e, "skuName", this.b.getName(), "channelName", this.i, "second_channelName", this.j, "recommend_request_id", this.n, "tag_id", q.a(this.b), "channel_type", this.m);
        } else if (this.c == IShelfFun.PageType.HOME_SHELF.getCode()) {
            String a = am.a(this.f);
            if ("recommend".equals(a)) {
                StatisticsManager.c("add_cart", "action", "add", "channel", this.d, "sku", this.b.getSku(), "sku_type", this.k, "pos", Integer.valueOf(this.b.getPosInProductList()), "recommend_request_id", this.g, "skuName", this.b.getName(), "skuCategory", this.b.getSkuCategory(), "channelName", this.i, "tag_id", q.a(this.b), ai.e, a);
            } else {
                StatisticsManager.c("add_cart", "action", "add", "channel", this.d, "sku", this.b.getSku(), "sku_type", this.k, "pos", Integer.valueOf(this.b.getPosInProductList()), "recommend_request_id", this.g, "skuName", this.b.getName(), "skuCategory", this.b.getSkuCategory(), "channelName", this.i, ai.e, a);
            }
        } else {
            int i = this.c;
            if (i == 7) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put(ai.e, "recommend");
                linkedHashMap.put("action", "add");
                linkedHashMap.put("pos", Integer.valueOf(this.b.getPosInProductList()));
                linkedHashMap.put("sku", this.b.getSku());
                linkedHashMap.put("recommend_request_id", cn.missfresh.module.base.common.c.b.a);
                linkedHashMap.put("tag_id", q.a(this.b));
                StatisticsManager.a(this.itemView.getContext(), "add_cart", linkedHashMap);
            } else if (i != 12) {
                if (i == 13) {
                    StatisticsManager.c("add_cart", ai.e, "big_pic", "channel", this.d, "second_channel", this.e, "action", "add", "sku", this.b.getSku(), "pos", Integer.valueOf(this.b.getPosInProductList()), "channel_type", Integer.valueOf(this.h), "skuName", this.b.getName(), "skuCategory", this.b.getSkuCategory(), "channelName", this.i, "second_channelName", this.j);
                } else if (i == IShelfFun.PageType.RECIPES_DETAIL.getCode()) {
                    a("add");
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        Integer[] numArr = {Integer.valueOf(this.o), Integer.valueOf(this.b.getPosInProductList())};
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.p);
        StatisticsManager.K("add_cart", "menu", Long.valueOf(this.q), "sku", this.b.getSku(), "pos", numArr, "foods", arrayList);
    }

    private void n() {
        if (this.c == IShelfFun.PageType.CLASSIFY_PRODUCT.getCode()) {
            String str = this.e;
            StatisticsManager.e("add_cart", "action", Telephony.BaseMmsColumns.SUBJECT, "sku", this.b.getSku(), "pos", Integer.valueOf(this.b.getPosInProductList()), "channel", this.d, "secondary_tag_name", str, "second_channel", str, "skuName", this.b.getName(), "sku_type", this.k, "skuCategory", this.b.getSkuCategory(), "channelName", this.i, "second_channelName", this.j, "recommend_request_id", this.n, "tag_id", q.a(this.b), "channel_type", this.m);
        } else if (this.c == IShelfFun.PageType.HOME_SHELF.getCode()) {
            String a = am.a(this.f);
            if ("recommend".equals(a)) {
                StatisticsManager.c("add_cart", "action", Telephony.BaseMmsColumns.SUBJECT, "channel", this.d, "sku", this.b.getSku(), "sku_type", this.k, "pos", Integer.valueOf(this.b.getPosInProductList()), "recommend_request_id", this.g, "skuName", this.b.getName(), "skuCategory", this.b.getSkuCategory(), "channelName", this.i, "tag_id", q.a(this.b), ai.e, a);
            } else {
                StatisticsManager.c("add_cart", "action", Telephony.BaseMmsColumns.SUBJECT, "channel", this.d, "sku", this.b.getSku(), "sku_type", this.k, "pos", Integer.valueOf(this.b.getPosInProductList()), "recommend_request_id", this.g, "skuName", this.b.getName(), "skuCategory", this.b.getSkuCategory(), "channelName", this.i, ai.e, a);
            }
        } else if (this.c == 13) {
            StatisticsManager.c("add_cart", ai.e, "big_pic", "channel", this.d, "second_channel", this.e, "action", Telephony.BaseMmsColumns.SUBJECT, "sku", this.b.getSku(), "pos", Integer.valueOf(this.b.getPosInProductList()), "channel_type", Integer.valueOf(this.h), "skuName", this.b.getName(), "skuCategory", this.b.getSkuCategory(), "channelName", this.i, "second_channelName", this.j);
        }
    }
}
