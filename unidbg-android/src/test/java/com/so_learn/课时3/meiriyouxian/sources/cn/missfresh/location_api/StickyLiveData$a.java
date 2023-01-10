package cn.missfresh.location_api;

import androidx.lifecycle.Observer;

class StickyLiveData$a extends StickyLiveData<T>.b {
    final /* synthetic */ StickyLiveData a;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StickyLiveData$a(StickyLiveData stickyLiveData, Observer<T> observer, boolean z) {
        super(stickyLiveData, observer, z);
        this.a = stickyLiveData;
    }
}
