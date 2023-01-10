package cn.missfresh.module.base.support.adapter;

import android.content.Context;
import android.media.midi.MidiDeviceInfo;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.HomeAssetBean;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

public class HomeDialogAssetAdapter extends RecyclerView.Adapter<ViewHolder> {
    private LayoutInflater a;
    private List<HomeAssetBean.CouponList> b;
    private View.OnClickListener c;
    private String d = "";
    private a e;

    public interface a {
        void a(HomeAssetBean.CouponList couponList);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(20469, false);
        a((ViewHolder) viewHolder, i);
        AppMethodBeat.o(20469);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(20473, false);
        ViewHolder a2 = a(viewGroup, i);
        AppMethodBeat.o(20473);
        return a2;
    }

    public HomeDialogAssetAdapter(Context context, List<HomeAssetBean.CouponList> list) {
        AppMethodBeat.i(20447, false);
        this.a = LayoutInflater.from(context);
        this.d = context.getResources().getString(R.string.RMB);
        if (list != null) {
            this.b = list;
        } else {
            this.b = new ArrayList();
        }
        AppMethodBeat.o(20447);
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public ViewHolder a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(20453, false);
        View inflate = this.a.inflate(R.layout.item_home_assests, viewGroup, false);
        inflate.setOnClickListener(this.c);
        ViewHolder viewHolder = new ViewHolder(inflate);
        AppMethodBeat.o(20453);
        return viewHolder;
    }

    public void a(ViewHolder viewHolder, int i) {
        AppMethodBeat.i(20458, false);
        HomeAssetBean.CouponList couponList = this.b.get(i);
        if (MidiDeviceInfo.PROPERTY_PRODUCT.equals(couponList.c_type)) {
            viewHolder.a.setVisibility(8);
            viewHolder.d.setVisibility(0);
        } else {
            viewHolder.a.setVisibility(0);
            viewHolder.d.setVisibility(8);
        }
        viewHolder.a.setTextSize(2, 30.0f);
        viewHolder.b.setText(couponList.c_name);
        viewHolder.c.setText(couponList.c_des);
        if (MidiDeviceInfo.PROPERTY_PRODUCT.equals(couponList.c_type)) {
            d.a(viewHolder.itemView.getContext(), couponList.c_img, -1, -1, -1, -1, viewHolder.d);
        } else if ("discount".equals(couponList.c_type)) {
            viewHolder.a.setText(couponList.c_money);
            viewHolder.a.setTextSize(2, (float) aw.a(viewHolder.a, couponList.c_money, 30, 8));
        } else {
            TextView textView = viewHolder.a;
            int a2 = aw.a(textView, this.d + couponList.c_money, 30, 8);
            a(this.d + couponList.c_money, this.d, viewHolder.a, (a2 * 2) / 3, a2, true);
        }
        viewHolder.e.setOnClickListener(new AnonymousClass1(couponList));
        AppMethodBeat.o(20458);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.adapter.HomeDialogAssetAdapter$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ HomeAssetBean.CouponList a;

        AnonymousClass1(HomeAssetBean.CouponList couponList) {
            this.a = couponList;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(20416, false);
            if (HomeDialogAssetAdapter.this.e != null) {
                HomeDialogAssetAdapter.this.e.a(this.a);
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(20416);
        }
    }

    private void a(String str, String str2, TextView textView, int i, int i2, boolean z) {
        AppMethodBeat.i(20461, false);
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(i, z), 0, str2.length(), 34);
        spannableString.setSpan(new StyleSpan(1), 0, str2.length(), 34);
        spannableString.setSpan(new AbsoluteSizeSpan(i2, z), str2.length(), str.length(), 18);
        textView.setText(spannableString);
        AppMethodBeat.o(20461);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 0;
        AppMethodBeat.i(20465, false);
        List<HomeAssetBean.CouponList> list = this.b;
        if (list != null) {
            i = list.size();
        }
        AppMethodBeat.o(20465);
        return i;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView a;
        private TextView b;
        private TextView c;
        private ImageView d;
        private TextView e;

        public ViewHolder(View view) {
            super(view);
            AppMethodBeat.i(20423, false);
            this.a = (TextView) view.findViewById(R.id.tv_home_assets_price);
            this.b = (TextView) view.findViewById(R.id.tv_home_assets_title);
            this.c = (TextView) view.findViewById(R.id.tv_home_assets_desc);
            this.d = (ImageView) view.findViewById(R.id.iv_home_assets_product);
            this.e = (TextView) view.findViewById(R.id.tv_use);
            AppMethodBeat.o(20423);
        }
    }
}
