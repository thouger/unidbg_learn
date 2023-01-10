package cn.missfresh.picture.internal.ui.adapter;

import android.view.View;
import cn.missfresh.picture.internal.entity.LocalMedia;
import cn.missfresh.picture.internal.ui.adapter.SmallPictureAdapter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class SmallPictureAdapter$SmallPictureHolder$1 implements View.OnClickListener {
    final /* synthetic */ SmallPictureAdapter a;
    final /* synthetic */ SmallPictureAdapter.b b;

    SmallPictureAdapter$SmallPictureHolder$1(SmallPictureAdapter.b bVar, SmallPictureAdapter smallPictureAdapter) {
        this.b = bVar;
        this.a = smallPictureAdapter;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(17913, false);
        if (SmallPictureAdapter.this.a != null) {
            SmallPictureAdapter.this.a.b((LocalMedia) SmallPictureAdapter.this.b.get(this.b.getAdapterPosition()));
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(17913);
    }
}
