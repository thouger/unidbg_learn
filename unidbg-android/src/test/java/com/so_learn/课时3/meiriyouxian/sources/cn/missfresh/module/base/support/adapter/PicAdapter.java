package cn.missfresh.module.base.support.adapter;

import android.app.Activity;
import android.content.Context;
import android.mtp.MtpConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PicAdapter extends PagerAdapter {
    private Context a;
    private float b = -1.0f;
    private List<BannerEntity> c;
    private a d;
    private int e;

    public interface a {
        void a();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public PicAdapter(Context context, int i) {
        AppMethodBeat.i(MtpConstants.DEVICE_PROPERTY_CONTRAST, false);
        this.a = context;
        this.e = i;
        this.c = new ArrayList();
        AppMethodBeat.o(MtpConstants.DEVICE_PROPERTY_CONTRAST);
    }

    public void a(List<BannerEntity> list) {
        AppMethodBeat.i(MtpConstants.DEVICE_PROPERTY_BURST_NUMBER, false);
        if (list == null) {
            this.c = new ArrayList();
        } else {
            this.c = list;
        }
        AppMethodBeat.o(MtpConstants.DEVICE_PROPERTY_BURST_NUMBER);
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        AppMethodBeat.i(MtpConstants.DEVICE_PROPERTY_COPYRIGHT_INFO, false);
        int size = this.c.size();
        AppMethodBeat.o(MtpConstants.DEVICE_PROPERTY_COPYRIGHT_INFO);
        return size;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        AppMethodBeat.i(20515, false);
        viewGroup.removeView((View) obj);
        AppMethodBeat.o(20515);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(20518, false);
        View inflate = LayoutInflater.from(this.a.getApplicationContext()).inflate(R.layout.recommend_page_item, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.image);
        TextView textView = (TextView) inflate.findViewById(R.id.image_desc);
        if (i < this.c.size()) {
            BannerEntity bannerEntity = this.c.get(i);
            d.b(this.a, bannerEntity.getPath(), R.drawable.ic_default_200, R.drawable.ic_default_200, imageView);
            imageView.setOnClickListener(new AnonymousClass1(bannerEntity));
        }
        viewGroup.addView(inflate);
        AppMethodBeat.o(20518);
        return inflate;
    }

    /* renamed from: cn.missfresh.module.base.support.adapter.PicAdapter$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ BannerEntity a;

        AnonymousClass1(BannerEntity bannerEntity) {
            this.a = bannerEntity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(MtpConstants.DEVICE_PROPERTY_F_NUMBER, false);
            String str = "";
            j.a(this.a, (Activity) PicAdapter.this.a, str, str, "pop_task");
            HashMap hashMap = new HashMap();
            if (!b.a(this.a.getName())) {
                str = "pop_task_" + this.a.getName();
            }
            hashMap.put("last_page", str);
            if (PicAdapter.this.d != null) {
                PicAdapter.this.d.a();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(MtpConstants.DEVICE_PROPERTY_F_NUMBER);
        }
    }
}
