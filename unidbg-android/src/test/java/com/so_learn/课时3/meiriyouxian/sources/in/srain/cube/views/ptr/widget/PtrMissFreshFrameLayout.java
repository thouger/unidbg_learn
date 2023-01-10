package in.srain.cube.views.ptr.widget;

import android.content.Context;
import android.util.AttributeSet;

public class PtrMissFreshFrameLayout extends PtrFrameLayout {
    private PtrMissFreshHeader header;

    public PtrMissFreshFrameLayout(Context context) {
        super(context);
        initViews();
    }

    public PtrMissFreshFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews();
    }

    public PtrMissFreshFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initViews();
    }

    private void initViews() {
        setBackgroundColor(getResources().getColor(17170443));
        this.header = new PtrMissFreshHeader(getContext());
        setHeaderView(this.header);
        addPtrUIHandler(this.header);
    }

    public PtrMissFreshHeader getHeader() {
        return this.header;
    }
}
