package cn.missfresh.module.base.helper;

import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class RecyclerViewClickHelper$3 implements RecyclerView.OnItemTouchListener {
    final /* synthetic */ GestureDetector a;

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    }

    RecyclerViewClickHelper$3(GestureDetector gestureDetector) {
        this.a = gestureDetector;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        AppMethodBeat.i(13152, false);
        boolean onTouchEvent = this.a.onTouchEvent(motionEvent);
        AppMethodBeat.o(13152);
        return onTouchEvent;
    }
}
