package cn.missfresh.ui.refreshlayout.header;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.b;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;

public class MFHeader extends InternalAbstract implements RefreshHeader {
    private final int a;
    private ImageView b;
    private AnimationDrawable c;
    private Resources d;

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    public View getView() {
        return this;
    }

    public MFHeader(Context context) {
        this(context, null);
    }

    public MFHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MFHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(1259, false);
        this.a = 50;
        setGravity(1);
        this.d = getResources();
        this.c = new AnimationDrawable();
        this.c.addFrame(this.d.getDrawable(R.drawable.mf_refresh_header_0), 50);
        this.c.addFrame(this.d.getDrawable(R.drawable.mf_refresh_header_1), 50);
        this.c.addFrame(this.d.getDrawable(R.drawable.mf_refresh_header_2), 50);
        this.c.addFrame(this.d.getDrawable(R.drawable.mf_refresh_header_3), 50);
        this.c.addFrame(this.d.getDrawable(R.drawable.mf_refresh_header_4), 50);
        this.c.addFrame(this.d.getDrawable(R.drawable.mf_refresh_header_5), 50);
        this.c.addFrame(this.d.getDrawable(R.drawable.mf_refresh_header_6), 50);
        this.c.addFrame(this.d.getDrawable(R.drawable.mf_refresh_header_7), 50);
        this.c.addFrame(this.d.getDrawable(R.drawable.mf_refresh_header_8), 50);
        this.c.addFrame(this.d.getDrawable(R.drawable.mf_refresh_header_9), 50);
        this.c.setOneShot(false);
        this.b = new ImageView(context);
        this.b.setImageDrawable(this.d.getDrawable(R.drawable.mf_refresh_header_0));
        this.b.setPadding(0, 80, 0, 50);
        addView(this.b);
        AppMethodBeat.o(1259);
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    public b getSpinnerStyle() {
        return b.a;
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onStartAnimator(RefreshLayout refreshLayout, int i, int i2) {
        AppMethodBeat.i(1267, false);
        this.b.setImageDrawable(this.c);
        this.c.start();
        AppMethodBeat.o(1267);
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    public int onFinish(RefreshLayout refreshLayout, boolean z) {
        AppMethodBeat.i(1272, false);
        clearAnimation();
        this.c.stop();
        clearAnimation();
        AppMethodBeat.o(1272);
        return 200;
    }
}
