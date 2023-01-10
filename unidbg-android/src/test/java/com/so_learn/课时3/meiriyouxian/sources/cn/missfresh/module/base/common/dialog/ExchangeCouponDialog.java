package cn.missfresh.module.base.common.dialog;

import android.app.Dialog;
import android.media.midi.MidiDeviceInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.basiclib.net.c;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.common.dialog.adapter.CouponExchangeAdapter;
import cn.missfresh.module.base.common.dialog.bean.CouponExchangeBean;
import cn.missfresh.module.base.common.dialog.bean.CouponProduct;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.network.api.MFApiManager;
import cn.missfresh.module.base.network.j;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.umeng.analytics.pro.ai;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

public class ExchangeCouponDialog extends DialogFragment implements View.OnClickListener {
    private RecyclerView a;
    private TextView b;
    private ProgressBar c;
    private List<CouponProduct> d;
    private CouponExchangeAdapter e;
    private a f;
    private String g = "";
    private String h = "";

    public interface a {
        void a(String str);

        void a(String str, String str2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AppMethodBeat.onResume(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    static /* synthetic */ a c(ExchangeCouponDialog exchangeCouponDialog) {
        AppMethodBeat.i(11288, false);
        a c = exchangeCouponDialog.c();
        AppMethodBeat.o(11288);
        return c;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AppMethodBeat.i(11277, false);
        Dialog dialog = new Dialog(getActivity(), R.style.my_dialog);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_coupon_exchange);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 17;
            attributes.width = -2;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
        a();
        a(dialog);
        AppMethodBeat.o(11277);
        return dialog;
    }

    private void a() {
        AppMethodBeat.i(11279, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.d = new ArrayList();
            this.d.addAll((Collection) arguments.getSerializable(MidiDeviceInfo.PROPERTY_PRODUCT));
            this.g = arguments.getString("couponId");
            this.h = arguments.getString("sku");
        }
        AppMethodBeat.o(11279);
    }

    private void a(Dialog dialog) {
        AppMethodBeat.i(11280, false);
        this.e = new CouponExchangeAdapter();
        this.a = (RecyclerView) dialog.findViewById(R.id.rv_coupon_list);
        this.a.setLayoutManager(new LinearLayoutManager(getContext()));
        this.b = (TextView) dialog.findViewById(R.id.btn_exchange);
        this.c = (ProgressBar) dialog.findViewById(R.id.pb_loading);
        this.a.setAdapter(this.e);
        this.b.setOnClickListener(this);
        dialog.findViewById(R.id.btn_give_up).setOnClickListener(this);
        this.e.a(this.d);
        this.f = c();
        AppMethodBeat.o(11280);
    }

    private void b() {
        AppMethodBeat.i(11281, false);
        if (this.e == null) {
            AppMethodBeat.o(11281);
            return;
        }
        CouponExchangeBean couponExchangeBean = new CouponExchangeBean();
        couponExchangeBean.setId(this.g);
        couponExchangeBean.setProductSku(this.e.a());
        RequestParam<CouponExchangeBean> requestParam = new RequestParam<>();
        requestParam.setParam(couponExchangeBean);
        MFApiManager.getMFApi().exchangeCoupon(requestParam).a(cn.missfresh.basiclib.net.e.a.a).subscribe(new c(new AnonymousClass1()));
        AppMethodBeat.o(11281);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.dialog.ExchangeCouponDialog$1  reason: invalid class name */
    public class AnonymousClass1 extends j<String> {
        AnonymousClass1() {
        }

        @Override // cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(11270, false);
            a((String) obj);
            AppMethodBeat.o(11270);
        }

        @Override // cn.missfresh.module.base.network.j
        public void a(int i, String str) {
            AppMethodBeat.i(11267, false);
            ExchangeCouponDialog.this.c.setVisibility(8);
            if (ExchangeCouponDialog.this.f == null) {
                ExchangeCouponDialog exchangeCouponDialog = ExchangeCouponDialog.this;
                exchangeCouponDialog.f = ExchangeCouponDialog.c(exchangeCouponDialog);
            }
            a aVar = ExchangeCouponDialog.this.f;
            if (TextUtils.isEmpty(str)) {
                str = "\u8bf7\u6c42\u8d85\u65f6";
            }
            aVar.a(str);
            AppMethodBeat.o(11267);
        }

        public void a(String str) {
            AppMethodBeat.i(11268, false);
            ExchangeCouponDialog.this.c.setVisibility(8);
            if (ExchangeCouponDialog.this.f == null) {
                ExchangeCouponDialog exchangeCouponDialog = ExchangeCouponDialog.this;
                exchangeCouponDialog.f = ExchangeCouponDialog.c(exchangeCouponDialog);
            }
            a aVar = ExchangeCouponDialog.this.f;
            if (TextUtils.isEmpty(str)) {
                str = "\u5151\u6362\u6210\u529f";
            }
            aVar.a(str, ExchangeCouponDialog.this.g);
            ExchangeCouponDialog.this.dismiss();
            AppMethodBeat.o(11268);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(11284, false);
        if (R.id.btn_exchange == view.getId()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(ai.e, "exchangePop");
            linkedHashMap.put("sku", this.h);
            linkedHashMap.put("ableToExchangeSku", this.e.a());
            if (cn.missfresh.module.base.common.config.a.c == 0) {
                linkedHashMap.put("ableToExchangeSkuType", "algorithm");
            } else {
                linkedHashMap.put("ableToExchangeSkuType", "operation");
            }
            StatisticsManager.b(getContext(), "click_exchange", linkedHashMap);
            this.c.setVisibility(0);
            b();
        } else {
            dismiss();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(11284);
    }

    private a c() {
        AppMethodBeat.i(11285, false);
        a aVar = (a) aw.b(this, a.class);
        AppMethodBeat.o(11285);
        return aVar;
    }
}
