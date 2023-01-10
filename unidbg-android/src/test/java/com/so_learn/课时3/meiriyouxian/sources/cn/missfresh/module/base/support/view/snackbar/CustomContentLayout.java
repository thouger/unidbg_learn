package cn.missfresh.module.base.support.view.snackbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import cn.missfresh.module.base.support.view.snackbar.BaseTransientBar;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class CustomContentLayout extends LinearLayout implements BaseTransientBar.c {
    private GestureDetector a;
    private a b;
    private GestureDetector.OnGestureListener c = new AnonymousClass1();

    public interface a {
        void a();

        void b();
    }

    @Override // cn.missfresh.module.base.support.view.snackbar.BaseTransientBar.c
    public void a(int i, int i2) {
    }

    @Override // cn.missfresh.module.base.support.view.snackbar.BaseTransientBar.c
    public void b(int i, int i2) {
    }

    public CustomContentLayout(Context context) {
        super(context);
        AppMethodBeat.i(22865, false);
        a(context);
        AppMethodBeat.o(22865);
    }

    public CustomContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(22866, false);
        a(context);
        AppMethodBeat.o(22866);
    }

    public CustomContentLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(22867, false);
        a(context);
        AppMethodBeat.o(22867);
    }

    public void setFlingCallback(a aVar) {
        this.b = aVar;
    }

    private void a(Context context) {
        AppMethodBeat.i(22868, false);
        this.a = new GestureDetector(context, this.c);
        AppMethodBeat.o(22868);
    }

    public CustomContentLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        AppMethodBeat.i(22869, false);
        AppMethodBeat.o(22869);
    }

    /* renamed from: cn.missfresh.module.base.support.view.snackbar.CustomContentLayout$1  reason: invalid class name */
    class AnonymousClass1 implements GestureDetector.OnGestureListener {
        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent motionEvent) {
        }

        AnonymousClass1() {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            AppMethodBeat.i(22862, false);
            if (CustomContentLayout.this.b != null) {
                CustomContentLayout.this.b.b();
                AppMethodBeat.o(22862);
                return true;
            }
            AppMethodBeat.o(22862);
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            AppMethodBeat.i(22863, false);
            if (motionEvent.getY() - motionEvent2.getY() > 10.0f) {
                if (CustomContentLayout.this.b != null) {
                    CustomContentLayout.this.b.a();
                }
                AppMethodBeat.o(22863);
                return true;
            }
            AppMethodBeat.o(22863);
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            AppMethodBeat.i(22864, false);
            if (motionEvent.getY() - motionEvent2.getY() > 10.0f) {
                if (CustomContentLayout.this.b != null) {
                    CustomContentLayout.this.b.a();
                }
                AppMethodBeat.o(22864);
                return true;
            }
            AppMethodBeat.o(22864);
            return false;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(22870, false);
        this.a.onTouchEvent(motionEvent);
        AppMethodBeat.o(22870);
        return true;
    }
}
