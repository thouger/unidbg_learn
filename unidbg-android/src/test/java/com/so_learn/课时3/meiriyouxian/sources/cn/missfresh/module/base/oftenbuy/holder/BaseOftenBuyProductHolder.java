package cn.missfresh.module.base.oftenbuy.holder;

import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.lib.image.c;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.module.base.common.listener.a;
import cn.missfresh.module.base.common.providers.IProductDetailService;
import cn.missfresh.module.base.helper.i;
import cn.missfresh.module.base.oftenbuy.bean.OftenBuyStatictisBean;
import cn.missfresh.module.base.utils.ar;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import cn.missfresh.utils.f;

public class BaseOftenBuyProductHolder extends RecyclerView.ViewHolder {
    private ImageView a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private i h = new i();
    private ProductsEntity i;
    private a j;
    private int k;
    private String l;
    private String m;
    private int n;
    private cn.missfresh.module.base.oftenbuy.b.a o;
    private View.OnClickListener p = new AnonymousClass1();
    private i.b q = new AnonymousClass2(this.h);

    public BaseOftenBuyProductHolder(View view, a aVar, int i, cn.missfresh.module.base.oftenbuy.b.a aVar2) {
        super(view);
        AppMethodBeat.i(16266, false);
        this.n = i;
        this.o = aVar2;
        this.a = (ImageView) view.findViewById(R.id.image_view);
        this.b = (ImageView) view.findViewById(R.id.top_image_view);
        this.d = (TextView) view.findViewById(R.id.price_tv);
        this.e = (TextView) view.findViewById(R.id.title_tv);
        this.f = (TextView) view.findViewById(R.id.tv_count);
        this.g = (TextView) view.findViewById(R.id.tv_buy_time);
        this.c = (TextView) view.findViewById(R.id.tv_markdown_tag);
        this.j = aVar;
        view.findViewById(R.id.add_cart_btn).setOnClickListener(this.p);
        view.setOnClickListener(this.p);
        AppMethodBeat.o(16266);
    }

    public void a(ProductsEntity productsEntity, int i, String str, String str2) {
        AppMethodBeat.i(16268, false);
        if (productsEntity == null) {
            this.itemView.setVisibility(8);
            AppMethodBeat.o(16268);
            return;
        }
        this.itemView.setVisibility(0);
        this.k = i;
        this.i = productsEntity;
        this.m = str2;
        this.l = str;
        this.k = i;
        this.q.setProduct(productsEntity);
        AppMethodBeat.o(16268);
    }

    /* renamed from: cn.missfresh.module.base.oftenbuy.holder.BaseOftenBuyProductHolder$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(16255, false);
            if (view.getId() == R.id.add_cart_btn) {
                String str = "";
                if (BaseOftenBuyProductHolder.this.i != null) {
                    if (BaseOftenBuyProductHolder.this.i.isSpu()) {
                        ar.a(BaseOftenBuyProductHolder.this.itemView.getContext(), BaseOftenBuyProductHolder.this.i, 1 == BaseOftenBuyProductHolder.this.n ? "index" : str, BaseOftenBuyProductHolder.this.q);
                    } else {
                        BaseOftenBuyProductHolder.this.q.a(BaseOftenBuyProductHolder.this.i);
                    }
                }
                int i = -1;
                if (1 == BaseOftenBuyProductHolder.this.n) {
                    String str2 = BaseOftenBuyProductHolder.this.l;
                    String str3 = BaseOftenBuyProductHolder.this.m;
                    String requestId = BaseOftenBuyProductHolder.this.i != null ? BaseOftenBuyProductHolder.this.i.getRequestId() : str;
                    if (BaseOftenBuyProductHolder.this.i != null) {
                        str = BaseOftenBuyProductHolder.this.i.getSku();
                    }
                    if (BaseOftenBuyProductHolder.this.i != null) {
                        i = BaseOftenBuyProductHolder.this.i.getSkuTypeBuyTag();
                    }
                    cn.missfresh.module.base.oftenbuy.c.a.b(str2, str3, requestId, str, i, BaseOftenBuyProductHolder.this.getAdapterPosition(), BaseOftenBuyProductHolder.this.k);
                } else if (2 == BaseOftenBuyProductHolder.this.n) {
                    String str4 = BaseOftenBuyProductHolder.this.l;
                    String str5 = BaseOftenBuyProductHolder.this.m;
                    String requestId2 = BaseOftenBuyProductHolder.this.i != null ? BaseOftenBuyProductHolder.this.i.getRequestId() : str;
                    if (BaseOftenBuyProductHolder.this.i != null) {
                        str = BaseOftenBuyProductHolder.this.i.getSku();
                    }
                    if (BaseOftenBuyProductHolder.this.i != null) {
                        i = BaseOftenBuyProductHolder.this.i.getSkuTypeBuyTag();
                    }
                    cn.missfresh.module.base.oftenbuy.c.a.a(str4, str5, requestId2, str, i, BaseOftenBuyProductHolder.this.k, BaseOftenBuyProductHolder.this.getAdapterPosition());
                }
            } else {
                ((IProductDetailService) com.alibaba.android.arouter.b.a.a().a("/product/product_detail_service").navigation()).routerProductDetail(BaseOftenBuyProductHolder.this.i.getSku(), BaseOftenBuyProductHolder.this.i.getImage(), BaseOftenBuyProductHolder.this.i.getRequestId(), 0, "");
                if (1 == BaseOftenBuyProductHolder.this.n) {
                    cn.missfresh.module.base.oftenbuy.c.a.b(BaseOftenBuyProductHolder.this.l, BaseOftenBuyProductHolder.this.m, BaseOftenBuyProductHolder.this.i.getRequestId(), 2, BaseOftenBuyProductHolder.this.i.getSkuTypeBuyTag(), BaseOftenBuyProductHolder.this.k, BaseOftenBuyProductHolder.this.getAdapterPosition());
                } else if (2 == BaseOftenBuyProductHolder.this.n) {
                    cn.missfresh.module.base.oftenbuy.c.a.a(BaseOftenBuyProductHolder.this.l, BaseOftenBuyProductHolder.this.m, BaseOftenBuyProductHolder.this.i.getRequestId(), 2, BaseOftenBuyProductHolder.this.i.getSkuTypeBuyTag(), BaseOftenBuyProductHolder.this.k, BaseOftenBuyProductHolder.this.getAdapterPosition());
                }
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(16255);
        }
    }

    /* renamed from: cn.missfresh.module.base.oftenbuy.holder.BaseOftenBuyProductHolder$2  reason: invalid class name */
    class AnonymousClass2 extends i.b {
        AnonymousClass2(i iVar) {
            super(iVar);
        }

        @Override // cn.missfresh.module.base.helper.i.b
        public boolean a(ProductsEntity productsEntity) {
            AppMethodBeat.i(16259, false);
            if (productsEntity == null) {
                AppMethodBeat.o(16259);
                return false;
            } else if (!j.a(productsEntity, productsEntity.getPermission_msg())) {
                AppMethodBeat.o(16259);
                return false;
            } else {
                int a = BaseOftenBuyProductHolder.this.h.a(productsEntity);
                if (a < 0) {
                    AppMethodBeat.o(16259);
                    return false;
                }
                b(a + 1);
                if (BaseOftenBuyProductHolder.this.o != null) {
                    BaseOftenBuyProductHolder.this.o.a(1, BaseOftenBuyProductHolder.this.getAdapterPosition(), new OftenBuyStatictisBean(productsEntity.getSku(), productsEntity.getQuantity(), productsEntity.getStock(), productsEntity.getProductLimit()));
                } else if (1 == BaseOftenBuyProductHolder.this.n) {
                    BaseOftenBuyProductHolder.this.h.a(BaseOftenBuyProductHolder.this.i, BaseOftenBuyProductHolder.this.i.getRequestId(), "index");
                } else {
                    BaseOftenBuyProductHolder.this.h.a(BaseOftenBuyProductHolder.this.i, BaseOftenBuyProductHolder.this.i.getRequestId());
                }
                if (!(BaseOftenBuyProductHolder.this.j == null || BaseOftenBuyProductHolder.this.a == null)) {
                    BaseOftenBuyProductHolder.this.j.a(BaseOftenBuyProductHolder.this.a);
                }
                AppMethodBeat.o(16259);
                return true;
            }
        }

        @Override // cn.missfresh.module.base.helper.i.b
        public void b(int i) {
            AppMethodBeat.i(16260, false);
            if (i > 0) {
                BaseOftenBuyProductHolder.this.f.setVisibility(0);
                if (i > 99) {
                    BaseOftenBuyProductHolder.this.f.setText("99+");
                } else {
                    BaseOftenBuyProductHolder.this.f.setText(String.valueOf(i));
                }
            } else {
                BaseOftenBuyProductHolder.this.f.setVisibility(8);
            }
            BaseOftenBuyProductHolder.this.i.setCount(i);
            AppMethodBeat.o(16260);
        }

        @Override // cn.missfresh.module.base.common.interfaces.c
        public void setProduct(ProductsEntity productsEntity) {
            AppMethodBeat.i(16262, false);
            if (productsEntity == null) {
                BaseOftenBuyProductHolder.this.itemView.setVisibility(8);
                AppMethodBeat.o(16262);
                return;
            }
            BaseOftenBuyProductHolder.this.itemView.setVisibility(0);
            BaseOftenBuyProductHolder.this.e.setText(productsEntity.getName());
            BaseOftenBuyProductHolder.this.g.setText(productsEntity.getBuyTimes());
            c.a(BaseOftenBuyProductHolder.this.a).a(new cn.missfresh.lib.image.d.a(BaseOftenBuyProductHolder.this.a.getContext(), BaseOftenBuyProductHolder.this.a.getResources().getDimension(R.dimen.margin_4)).a(false, false, false, false)).a(productsEntity.getImage()).a(R.drawable.ic_default_100).a(BaseOftenBuyProductHolder.this.a);
            StringBuilder sb = new StringBuilder(i.a);
            sb.append(at.b(productsEntity.getNowPrice()));
            SpannableString spannableString = new SpannableString(sb);
            spannableString.setSpan(new RelativeSizeSpan(0.6f), 0, 1, 33);
            BaseOftenBuyProductHolder.this.d.setText(spannableString);
            BaseOftenBuyProductHolder.this.q.b(BaseOftenBuyProductHolder.this.h.a(productsEntity));
            if (productsEntity.getMarkdownTag() == null || b.a(productsEntity.getMarkdownTag().getName())) {
                BaseOftenBuyProductHolder.this.c.setVisibility(8);
            } else {
                BaseOftenBuyProductHolder.this.c.setVisibility(0);
                BaseOftenBuyProductHolder.this.c.setText(productsEntity.getMarkdownTag().getName());
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius((float) f.c(BaseOftenBuyProductHolder.this.itemView.getContext(), 4));
                gradientDrawable.setColor(q.a(productsEntity.getMarkdownTag().getBgColor()));
                BaseOftenBuyProductHolder.this.c.setTextColor(q.a(productsEntity.getMarkdownTag().getNameColor()));
                BaseOftenBuyProductHolder.this.c.setBackground(gradientDrawable);
            }
            AppMethodBeat.o(16262);
        }
    }
}
