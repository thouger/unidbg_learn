package cn.missfresh.module.base.widget.tagflow;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class TagView extends FrameLayout implements Checkable {
    private static final int[] a = {16842912};
    private boolean b;

    public TagView(Context context) {
        super(context);
    }

    public View getTagView() {
        AppMethodBeat.i(24384, false);
        View childAt = getChildAt(0);
        AppMethodBeat.o(24384);
        return childAt;
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i) {
        AppMethodBeat.i(24385, false);
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, a);
        }
        AppMethodBeat.o(24385);
        return onCreateDrawableState;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.b;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        AppMethodBeat.i(24386, false);
        if (this.b != z) {
            this.b = z;
            refreshDrawableState();
        }
        AppMethodBeat.o(24386);
    }

    @Override // android.widget.Checkable
    public void toggle() {
        AppMethodBeat.i(24387, false);
        setChecked(!this.b);
        AppMethodBeat.o(24387);
    }
}
