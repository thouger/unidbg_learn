package in.srain.cube.views.ptr.interfaces;

import android.view.View;
import in.srain.cube.views.ptr.widget.PtrFrameLayout;

public interface PtrHandler {
    boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2);

    void onRefreshBegin(PtrFrameLayout ptrFrameLayout);
}
