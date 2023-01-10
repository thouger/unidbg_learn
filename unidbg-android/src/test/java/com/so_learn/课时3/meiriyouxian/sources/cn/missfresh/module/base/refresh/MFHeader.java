package cn.missfresh.module.base.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.refresh.a;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.b;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;

public class MFHeader extends InternalAbstract implements RefreshHeader {
    private ImageView a;
    private int b;
    private a.b c;

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
        AppMethodBeat.i(18951, false);
        setGravity(1);
        this.a = new ImageView(context);
        this.b = aw.b(80);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(aw.b(124), this.b);
        this.a.setScaleType(ImageView.ScaleType.FIT_XY);
        this.a.setLayoutParams(layoutParams);
        addView(this.a);
        if (this.c == null) {
            this.c = a.a(getContext(), R.array.loading_head, 10).a(this.a);
        }
        AppMethodBeat.o(18951);
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    public b getSpinnerStyle() {
        return b.a;
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onStartAnimator(RefreshLayout refreshLayout, int i, int i2) {
        AppMethodBeat.i(18961, false);
        this.c.a();
        AppMethodBeat.o(18961);
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    public int onFinish(RefreshLayout refreshLayout, boolean z) {
        AppMethodBeat.i(18965, false);
        clearAnimation();
        this.c.b();
        clearAnimation();
        AppMethodBeat.o(18965);
        return 100;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public void detachViewFromParent(View view) {
        AppMethodBeat.i(18966, false);
        super.detachViewFromParent(view);
        a.b bVar = this.c;
        if (bVar != null) {
            bVar.b();
            this.c = null;
        }
        AppMethodBeat.o(18966);
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onInitialized(RefreshKernel refreshKernel, int i, int i2) {
        AppMethodBeat.i(18968, false);
        super.onInitialized(refreshKernel, i, i2);
        refreshKernel.requestDrawBackgroundFor(this, getResources().getColor(R.color.white));
        if (Math.abs(i - this.b) > 10) {
            refreshKernel.requestRemeasureHeightFor(this);
        }
        AppMethodBeat.o(18968);
    }
}
