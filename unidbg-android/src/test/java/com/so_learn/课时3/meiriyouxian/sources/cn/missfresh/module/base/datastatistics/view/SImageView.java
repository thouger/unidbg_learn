package cn.missfresh.module.base.datastatistics.view;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.appcompat.widget.AppCompatImageView;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SImageView extends AppCompatImageView {
    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        AppMethodBeat.i(12870, false);
        super.setImageDrawable(drawable);
        StatisticsManager.onEventToMRYX(this);
        AppMethodBeat.o(12870);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        AppMethodBeat.i(12872, false);
        super.setImageResource(i);
        StatisticsManager.onEventToMRYX(this);
        AppMethodBeat.o(12872);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        AppMethodBeat.i(12874, false);
        super.setImageURI(uri);
        StatisticsManager.onEventToMRYX(this);
        AppMethodBeat.o(12874);
    }
}
