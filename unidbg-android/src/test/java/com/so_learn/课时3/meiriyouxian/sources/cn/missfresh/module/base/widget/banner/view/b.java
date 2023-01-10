package cn.missfresh.module.base.widget.banner.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.network.d;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: LocalImageHolderView */
public class b implements a<BannerEntity> {
    protected ImageView a;
    private int b = 0;

    @Override // cn.missfresh.module.base.widget.banner.view.a
    public /* synthetic */ void a(Context context, int i, Object obj) {
        AppMethodBeat.i(24005, false);
        a(context, i, (BannerEntity) obj);
        AppMethodBeat.o(24005);
    }

    public b(int i) {
        this.b = i;
    }

    public b() {
    }

    @Override // cn.missfresh.module.base.widget.banner.view.a
    public View a(Context context) {
        AppMethodBeat.i(24003, false);
        this.a = new ImageView(context);
        this.a.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView imageView = this.a;
        AppMethodBeat.o(24003);
        return imageView;
    }

    public void a(Context context, int i, BannerEntity bannerEntity) {
        AppMethodBeat.i(24004, false);
        if (!(bannerEntity == null || context.getApplicationContext() == null)) {
            d.b(context, bannerEntity.getPath(), this.b == 0 ? R.drawable.ic_375_140_f5 : R.drawable.ic_305_90_f5, R.drawable.ic_375_140_f5, this.a);
        }
        AppMethodBeat.o(24004);
    }
}
