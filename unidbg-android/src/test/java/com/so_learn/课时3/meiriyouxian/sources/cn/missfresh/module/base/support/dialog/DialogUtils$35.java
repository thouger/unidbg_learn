package cn.missfresh.module.base.support.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.bean.HomeAssetBean;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class DialogUtils$35 implements View.OnClickListener {
    final /* synthetic */ Dialog a;
    final /* synthetic */ HomeAssetBean b;
    final /* synthetic */ Activity c;

    DialogUtils$35(Dialog dialog, HomeAssetBean homeAssetBean, Activity activity) {
        this.a = dialog;
        this.b = homeAssetBean;
        this.c = activity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21185, false);
        this.a.dismiss();
        BannerEntity bannerEntity = new BannerEntity();
        bannerEntity.setType(this.b.link_type);
        j.a(bannerEntity, this.c, "", "", "asset_dialog");
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21185);
    }
}
