package cn.missfresh.module.base.support.view.snackbar;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private boolean a;
    ViewDragHelper b;
    a c;
    int d = 2;
    float e = 0.5f;
    float f = 0.0f;
    float g = 0.5f;
    private float h = 0.0f;
    private boolean i;
    private final ViewDragHelper.Callback j = new AnonymousClass1();

    public interface a {
        void a(int i);

        void a(View view);
    }

    static float b(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }

    public boolean a(View view) {
        return true;
    }

    public SwipeDismissBehavior() {
        AppMethodBeat.i(22921, false);
        AppMethodBeat.o(22921);
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(float f) {
        AppMethodBeat.i(22923, false);
        this.f = a(0.0f, f, 1.0f);
        AppMethodBeat.o(22923);
    }

    public void b(float f) {
        AppMethodBeat.i(22924, false);
        this.g = a(0.0f, f, 1.0f);
        AppMethodBeat.o(22924);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        AppMethodBeat.i(22925, false);
        boolean z = this.a;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.a = coordinatorLayout.isPointInChildBounds(v, (int) motionEvent.getX(), (int) motionEvent.getY());
            z = this.a;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.a = false;
        }
        if (z) {
            a((ViewGroup) coordinatorLayout);
            boolean shouldInterceptTouchEvent = this.b.shouldInterceptTouchEvent(motionEvent);
            AppMethodBeat.o(22925);
            return shouldInterceptTouchEvent;
        }
        AppMethodBeat.o(22925);
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        AppMethodBeat.i(22926, false);
        ViewDragHelper viewDragHelper = this.b;
        if (viewDragHelper != null) {
            viewDragHelper.processTouchEvent(motionEvent);
            AppMethodBeat.o(22926);
            return true;
        }
        AppMethodBeat.o(22926);
        return false;
    }

    /* renamed from: cn.missfresh.module.base.support.view.snackbar.SwipeDismissBehavior$1  reason: invalid class name */
    class AnonymousClass1 extends ViewDragHelper.Callback {
        private int b;
        private int c = -1;

        private boolean a(View view, float f) {
            return false;
        }

        AnonymousClass1() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i) {
            boolean z = false;
            AppMethodBeat.i(22912, false);
            if (this.c == -1 && SwipeDismissBehavior.this.a(view)) {
                z = true;
            }
            AppMethodBeat.o(22912);
            return z;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewCaptured(View view, int i) {
            AppMethodBeat.i(22913, false);
            this.c = i;
            this.b = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            AppMethodBeat.o(22913);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i) {
            AppMethodBeat.i(22914, false);
            if (SwipeDismissBehavior.this.c != null) {
                SwipeDismissBehavior.this.c.a(i);
            }
            AppMethodBeat.o(22914);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f, float f2) {
            int i;
            boolean z = false;
            AppMethodBeat.i(22915, false);
            this.c = -1;
            int width = view.getWidth();
            if (a(view, f)) {
                int left = view.getLeft();
                int i2 = this.b;
                int i3 = left < i2 ? i2 - width : i2 + width;
                z = true;
                i = i3;
            } else {
                i = this.b;
            }
            if (SwipeDismissBehavior.this.b.settleCapturedViewAt(i, view.getTop())) {
                ViewCompat.postOnAnimation(view, new b(view, z));
            } else if (z && SwipeDismissBehavior.this.c != null) {
                SwipeDismissBehavior.this.c.a(view);
            }
            AppMethodBeat.o(22915);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            AppMethodBeat.i(22916, false);
            int width = view.getWidth();
            AppMethodBeat.o(22916);
            return width;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i, int i2) {
            int i3;
            int i4;
            int width;
            boolean z = false;
            AppMethodBeat.i(22917, false);
            if (ViewCompat.getLayoutDirection(view) == 1) {
                z = true;
            }
            if (SwipeDismissBehavior.this.d != 0) {
                if (SwipeDismissBehavior.this.d != 1) {
                    i3 = this.b - view.getWidth();
                    i4 = view.getWidth() + this.b;
                } else if (z) {
                    i3 = this.b;
                    width = view.getWidth();
                } else {
                    i3 = this.b - view.getWidth();
                    i4 = this.b;
                }
                int a = SwipeDismissBehavior.a(i3, i, i4);
                AppMethodBeat.o(22917);
                return a;
            } else if (z) {
                i3 = this.b - view.getWidth();
                i4 = this.b;
                int a = SwipeDismissBehavior.a(i3, i, i4);
                AppMethodBeat.o(22917);
                return a;
            } else {
                i3 = this.b;
                width = view.getWidth();
            }
            i4 = width + i3;
            int a = SwipeDismissBehavior.a(i3, i, i4);
            AppMethodBeat.o(22917);
            return a;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i, int i2) {
            AppMethodBeat.i(22918, false);
            int top = view.getTop();
            AppMethodBeat.o(22918);
            return top;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            AppMethodBeat.i(22919, false);
            float width = ((float) this.b) + (((float) view.getWidth()) * SwipeDismissBehavior.this.f);
            float width2 = ((float) this.b) + (((float) view.getWidth()) * SwipeDismissBehavior.this.g);
            float f = (float) i;
            if (f <= width) {
                view.setAlpha(1.0f);
            } else if (f >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.a(0.0f, 1.0f - SwipeDismissBehavior.b(width, width2, f), 1.0f));
            }
            AppMethodBeat.o(22919);
        }
    }

    private void a(ViewGroup viewGroup) {
        ViewDragHelper viewDragHelper;
        AppMethodBeat.i(22927, false);
        if (this.b == null) {
            if (this.i) {
                viewDragHelper = ViewDragHelper.create(viewGroup, this.h, this.j);
            } else {
                viewDragHelper = ViewDragHelper.create(viewGroup, this.j);
            }
            this.b = viewDragHelper;
        }
        AppMethodBeat.o(22927);
    }

    private class b implements Runnable {
        private final View b;
        private final boolean c;

        b(View view, boolean z) {
            this.b = view;
            this.c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(22920, false);
            if (SwipeDismissBehavior.this.b != null && SwipeDismissBehavior.this.b.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.b, this);
            } else if (this.c && SwipeDismissBehavior.this.c != null) {
                SwipeDismissBehavior.this.c.a(this.b);
            }
            AppMethodBeat.o(22920);
        }
    }

    static float a(float f, float f2, float f3) {
        AppMethodBeat.i(22928, false);
        float min = Math.min(Math.max(f, f2), f3);
        AppMethodBeat.o(22928);
        return min;
    }

    static int a(int i, int i2, int i3) {
        AppMethodBeat.i(22929, false);
        int min = Math.min(Math.max(i, i2), i3);
        AppMethodBeat.o(22929);
        return min;
    }
}
