package cn.missfresh.module.base.helper;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: RecyclerViewClickHelper */
public class j {
    public static void a(RecyclerView recyclerView) {
        AppMethodBeat.i(13161, false);
        if (recyclerView == null) {
            AppMethodBeat.o(13161);
            return;
        }
        recyclerView.setOnTouchListener(new AnonymousClass1(new GestureDetector(recyclerView.getContext(), new a(recyclerView))));
        AppMethodBeat.o(13161);
    }

    /* compiled from: RecyclerViewClickHelper */
    /* renamed from: cn.missfresh.module.base.helper.j$1  reason: invalid class name */
    static class AnonymousClass1 implements View.OnTouchListener {
        final /* synthetic */ GestureDetector a;

        AnonymousClass1(GestureDetector gestureDetector) {
            this.a = gestureDetector;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            AppMethodBeat.i(13144, false);
            boolean onTouchEvent = this.a.onTouchEvent(motionEvent);
            AppMethodBeat.o(13144);
            return onTouchEvent;
        }
    }

    /* compiled from: RecyclerViewClickHelper */
    private static class a extends GestureDetector.SimpleOnGestureListener {
        private View a;

        a(View view) {
            AppMethodBeat.i(13156, false);
            if (view != null) {
                ViewParent parent = view.getParent();
                if (parent instanceof View) {
                    this.a = (View) parent;
                }
            }
            AppMethodBeat.o(13156);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            AppMethodBeat.i(13157, false);
            View view = this.a;
            if (view != null) {
                view.performClick();
            }
            boolean onSingleTapUp = super.onSingleTapUp(motionEvent);
            AppMethodBeat.o(13157);
            return onSingleTapUp;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            AppMethodBeat.i(13158, false);
            View view = this.a;
            if (view != null) {
                view.performLongClick();
            }
            super.onLongPress(motionEvent);
            AppMethodBeat.o(13158);
        }
    }

    /* compiled from: RecyclerViewClickHelper */
    /* renamed from: cn.missfresh.module.base.helper.j$2  reason: invalid class name */
    static class AnonymousClass2 extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ RecyclerView a;
        final /* synthetic */ g b;

        AnonymousClass2(RecyclerView recyclerView, g gVar) {
            this.a = recyclerView;
            this.b = gVar;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            AppMethodBeat.i(13146, false);
            View findChildViewUnder = this.a.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (findChildViewUnder != null) {
                this.b.a(findChildViewUnder, this.a.getChildAdapterPosition(findChildViewUnder));
                AppMethodBeat.o(13146);
                return true;
            }
            boolean onSingleTapUp = super.onSingleTapUp(motionEvent);
            AppMethodBeat.o(13146);
            return onSingleTapUp;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            AppMethodBeat.i(13148, false);
            View findChildViewUnder = this.a.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (findChildViewUnder != null) {
                this.b.b(findChildViewUnder, this.a.getChildAdapterPosition(findChildViewUnder));
            }
            AppMethodBeat.o(13148);
        }
    }

    public static void a(RecyclerView recyclerView, g gVar) {
        AppMethodBeat.i(13163, false);
        if (recyclerView == null || gVar == null) {
            AppMethodBeat.o(13163);
            return;
        }
        recyclerView.addOnItemTouchListener(new RecyclerViewClickHelper$3(new GestureDetector(recyclerView.getContext(), new AnonymousClass2(recyclerView, gVar))));
        AppMethodBeat.o(13163);
    }
}
