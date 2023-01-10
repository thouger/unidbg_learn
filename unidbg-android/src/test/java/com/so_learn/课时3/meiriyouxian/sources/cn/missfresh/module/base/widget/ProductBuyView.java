package cn.missfresh.module.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.module.base.bean.UpdataPromotionBean;
import cn.missfresh.module.base.common.interfaces.c;
import cn.missfresh.module.base.common.providers.IShoppingCartService2;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.support.dialog.e;
import cn.missfresh.module.base.utils.ar;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.base.utils.m;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.android.arouter.b.a;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;

public class ProductBuyView extends RelativeLayout implements View.OnClickListener, c, m.a {
    protected ImageView a;
    protected TextView b;
    private View c;
    private View d;
    private ImageView e;
    private TextView f;
    private ImageView g;
    private View h;
    private Drawable i;
    private Drawable j;
    private c.a k;
    private ProductsEntity l;
    private boolean m;
    private boolean n;
    private boolean o;

    @Override // cn.missfresh.module.base.common.interfaces.c
    public void a(int i) {
    }

    public ProductBuyView(Context context) {
        this(context, null);
    }

    public ProductBuyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(23807, false);
        this.m = false;
        this.n = true;
        this.o = true;
        LayoutInflater.from(context).inflate(R.layout.homepage_product_buy_contail, (ViewGroup) this, true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ProductBuyView);
        this.m = obtainStyledAttributes.getBoolean(R.styleable.ProductBuyView_enableReduce, false);
        obtainStyledAttributes.recycle();
        c();
        b();
        AppMethodBeat.o(23807);
    }

    private void b() {
        AppMethodBeat.i(23808, false);
        this.i = getResources().getDrawable(R.drawable.shape_corners_2_ff4891_stork);
        this.j = getResources().getDrawable(R.drawable.shape_round_pink_corner4);
        AppMethodBeat.o(23808);
    }

    private void c() {
        AppMethodBeat.i(23809, false);
        this.c = findViewById(R.id.homepage_product_add_or_sub);
        this.e = (ImageView) findViewById(R.id.tv_main_item_add);
        this.f = (TextView) findViewById(R.id.tv_main_item_product_count);
        this.g = (ImageView) findViewById(R.id.tv_main_item_sub);
        this.d = findViewById(R.id.homepage_product_buy_text_or_ico);
        this.a = (ImageView) findViewById(R.id.btn_main_item_buy_now);
        this.b = (TextView) findViewById(R.id.tv_count);
        this.h = findViewById(R.id.btn_main_item_find_like);
        this.a.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        AppMethodBeat.o(23809);
    }

    @Override // cn.missfresh.module.base.common.interfaces.c
    public void setProduct(ProductsEntity productsEntity) {
        AppMethodBeat.i(23810, false);
        this.l = productsEntity;
        if (this.l == null || !this.o) {
            AppMethodBeat.o(23810);
            return;
        }
        int e = e(productsEntity);
        if (e()) {
            this.c.setVisibility(8);
            this.d.setVisibility(0);
            this.a.setVisibility(8);
            this.b.setVisibility(8);
            this.h.setVisibility(0);
            AppMethodBeat.o(23810);
            return;
        }
        if (e <= 0 || productsEntity.isSpu() || !this.m) {
            this.c.setVisibility(8);
            this.d.setVisibility(0);
            this.a.setVisibility(0);
            this.h.setVisibility(8);
            if (a()) {
                setCount(e);
                if (!productsEntity.isSpu()) {
                    productsEntity.setCount(e);
                }
            } else {
                this.b.setVisibility(8);
            }
        } else {
            this.c.setVisibility(0);
            this.d.setVisibility(8);
            this.a.setVisibility(8);
            this.h.setVisibility(8);
            this.f.setText(String.valueOf(e));
            productsEntity.setCount(e);
        }
        AppMethodBeat.o(23810);
    }

    /* access modifiers changed from: protected */
    public void setCount(int i) {
        AppMethodBeat.i(23811, false);
        if (i > 0) {
            this.b.setVisibility(0);
            if (i > 99) {
                this.b.setText("99+");
            } else {
                TextView textView = this.b;
                textView.setText(i + "");
            }
        } else {
            this.b.setVisibility(8);
        }
        AppMethodBeat.o(23811);
    }

    @Override // cn.missfresh.module.base.common.interfaces.c
    public void setBuyViewClickedListener(c.a aVar) {
        this.k = aVar;
    }

    private void d(ProductsEntity productsEntity) {
        AppMethodBeat.i(23812, false);
        if (productsEntity != null) {
            a.a().a("/order/shoppingcart_like").withString("sku", productsEntity.getSku()).navigation();
        }
        AppMethodBeat.o(23812);
    }

    private void d() {
        AppMethodBeat.i(23813, false);
        if (!a()) {
            this.c.setVisibility(8);
            this.d.setVisibility(0);
            this.a.setVisibility(0);
            this.h.setVisibility(8);
            this.b.setVisibility(8);
        } else if (this.m) {
            this.c.setVisibility(0);
            this.d.setVisibility(8);
            this.a.setVisibility(8);
            this.h.setVisibility(8);
            this.b.setVisibility(8);
        } else {
            this.c.setVisibility(8);
            this.d.setVisibility(0);
            this.a.setVisibility(0);
            this.h.setVisibility(8);
            this.b.setVisibility(0);
            int count = this.l.getCount() + 1;
            ProductsEntity productsEntity = this.l;
            if (productsEntity != null) {
                productsEntity.setCount(count);
            }
            setCount(count);
        }
        AppMethodBeat.o(23813);
    }

    private int e(ProductsEntity productsEntity) {
        AppMethodBeat.i(23814, false);
        IShoppingCartService2 iShoppingCartService2 = (IShoppingCartService2) a.a().a("/order/new_shoppingcart_service").navigation();
        if (iShoppingCartService2 == null || productsEntity == null) {
            AppMethodBeat.o(23814);
            return -1;
        } else if (!productsEntity.isSpu()) {
            int b = iShoppingCartService2.b(productsEntity.getSku());
            AppMethodBeat.o(23814);
            return b;
        } else if (productsEntity.getSpuInfo() == null || b.a(productsEntity.getSpuInfo().getSkuInfo())) {
            AppMethodBeat.o(23814);
            return -1;
        } else {
            int size = productsEntity.getSpuInfo().getSkuInfo().size();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < size; i++) {
                arrayList.add(productsEntity.getSpuInfo().getSkuInfo().get(i).getSku());
            }
            int a = iShoppingCartService2.a(arrayList);
            AppMethodBeat.o(23814);
            return a;
        }
    }

    @Override // cn.missfresh.module.base.utils.m.a
    public void a(String str, String str2, String str3, int i) {
        AppMethodBeat.i(23815, false);
        if (i == 0) {
            o.a(1);
        } else {
            e.a(getContext(), str, str2, str3, "channel");
        }
        AppMethodBeat.o(23815);
    }

    public boolean a() {
        return this.n;
    }

    @Override // cn.missfresh.module.base.common.interfaces.c
    public void setShowCount(boolean z) {
        this.n = z;
    }

    public void a(ProductsEntity productsEntity) {
        AppMethodBeat.i(23816, false);
        if (b(this.l)) {
            d();
            c.a aVar = this.k;
            if (aVar != null) {
                aVar.a();
            }
        }
        AppMethodBeat.o(23816);
    }

    public boolean b(ProductsEntity productsEntity) {
        AppMethodBeat.i(23817, false);
        boolean f = f(productsEntity);
        AppMethodBeat.o(23817);
        return f;
    }

    private boolean f(ProductsEntity productsEntity) {
        AppMethodBeat.i(23818, false);
        if (productsEntity == null) {
            AppMethodBeat.o(23818);
            return false;
        } else if (!j.a(productsEntity, productsEntity.getPermission_msg())) {
            AppMethodBeat.o(23818);
            return false;
        } else {
            int e = e(productsEntity);
            if (e < 0) {
                AppMethodBeat.o(23818);
                return false;
            }
            TextView textView = this.f;
            if (textView != null) {
                textView.setText(String.valueOf(e + 1));
            }
            c.a aVar = this.k;
            if (aVar != null) {
                aVar.c(e + 1);
            }
            AppMethodBeat.o(23818);
            return true;
        }
    }

    public void c(ProductsEntity productsEntity) {
        AppMethodBeat.i(23819, false);
        g(productsEntity);
        AppMethodBeat.o(23819);
    }

    private void g(ProductsEntity productsEntity) {
        AppMethodBeat.i(23820, false);
        if (productsEntity == null) {
            AppMethodBeat.o(23820);
            return;
        }
        int e = e(productsEntity) - 1;
        if (e <= 0) {
            this.c.setVisibility(8);
            this.d.setVisibility(0);
            this.a.setVisibility(0);
            this.h.setVisibility(8);
            this.b.setVisibility(8);
        } else {
            this.f.setText(String.valueOf(e));
        }
        c.a aVar = this.k;
        if (aVar != null) {
            aVar.e(e);
        }
        AppMethodBeat.o(23820);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(23821, false);
        if (view.getId() == R.id.btn_main_item_buy_now) {
            if (this.l.isSpu()) {
                ar.a(getContext(), ((AppCompatActivity) getContext()).getSupportFragmentManager(), this.l, this);
            } else {
                a(this.l);
                EventBus.getDefault().post(new UpdataPromotionBean());
            }
        } else if (view.getId() == R.id.tv_main_item_add) {
            b(this.l);
            EventBus.getDefault().post(new UpdataPromotionBean());
        } else if (view.getId() == R.id.tv_main_item_sub) {
            c(this.l);
            EventBus.getDefault().post(new UpdataPromotionBean());
        } else if (view.getId() == R.id.btn_main_item_find_like) {
            d(this.l);
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(23821);
    }

    private boolean e() {
        AppMethodBeat.i(23822, false);
        ProductsEntity productsEntity = this.l;
        if (productsEntity == null) {
            AppMethodBeat.o(23822);
            return false;
        }
        boolean sell_out = productsEntity.getSell_out();
        AppMethodBeat.o(23822);
        return sell_out;
    }

    @Override // cn.missfresh.module.base.common.interfaces.m
    public void setShow(boolean z) {
        AppMethodBeat.i(23823, false);
        if (this.o == z) {
            AppMethodBeat.o(23823);
            return;
        }
        this.o = z;
        if (this.o) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        AppMethodBeat.o(23823);
    }
}
