package in.srain.cube.views.ptr.interfaces;

import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.widget.PtrFrameLayout;

public interface PtrUIHandler {
    void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator);

    void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout);

    void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout);

    void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout);

    void onUIReset(PtrFrameLayout ptrFrameLayout);
}
