package cn.missfresh.ui.tablayout;

import android.bluetooth.BluetoothClass;
import android.graphics.Point;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: BadgeDelegate */
/* access modifiers changed from: package-private */
public class a {
    private SparseArray<View> a = new SparseArray<>();
    private SparseArray<Point> b = new SparseArray<>();
    private SparseArray<Point> c = new SparseArray<>();

    a() {
        AppMethodBeat.i(2304, false);
        AppMethodBeat.o(2304);
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerView recyclerView, int i, View view, int i2, int i3) {
        AppMethodBeat.i(BluetoothClass.Device.HEALTH_BLOOD_PRESSURE, false);
        View view2 = (View) this.a.get(i);
        this.a.remove(i);
        if (!(view2 == null || recyclerView == null || !(recyclerView.getParent() instanceof ViewGroup))) {
            ((ViewGroup) recyclerView.getParent()).removeView(view2);
        }
        this.a.put(i, view);
        this.b.put(i, new Point(i2, i3));
        b(recyclerView);
        AppMethodBeat.o(BluetoothClass.Device.HEALTH_BLOOD_PRESSURE);
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerView recyclerView, int i, int i2) {
        AppMethodBeat.i(BluetoothClass.Device.HEALTH_THERMOMETER, false);
        if (recyclerView == null || this.a.size() == 0) {
            AppMethodBeat.o(BluetoothClass.Device.HEALTH_THERMOMETER);
            return;
        }
        for (int i3 = 0; i3 < this.c.size(); i3++) {
            Point point = (Point) this.c.valueAt(i3);
            if (point != null) {
                point.x -= i;
                point.y -= i2;
            }
        }
        b(recyclerView);
        AppMethodBeat.o(BluetoothClass.Device.HEALTH_THERMOMETER);
    }

    private void b(RecyclerView recyclerView) {
        AppMethodBeat.i(2317, false);
        if (recyclerView == null || !(recyclerView.getParent() instanceof ViewGroup)) {
            AppMethodBeat.o(2317);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) recyclerView.getParent();
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View childAt = recyclerView.getChildAt(i);
            int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
            if (((View) this.a.get(childAdapterPosition)) != null) {
                Point point = (Point) this.c.get(childAdapterPosition);
                if (point == null) {
                    SparseArray<Point> sparseArray = this.c;
                    Point point2 = new Point();
                    sparseArray.put(childAdapterPosition, point2);
                    point = point2;
                }
                point.x = (int) (((childAt.getX() + 0.5f) + ((float) childAt.getWidth())) - ((float) childAt.getPaddingRight()));
                point.y = (int) (childAt.getY() + 0.5f + ((float) childAt.getPaddingTop()));
            }
        }
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            int keyAt = this.a.keyAt(i2);
            Point point3 = (Point) this.c.get(keyAt);
            if (point3 != null) {
                View view = (View) this.a.get(keyAt);
                Point point4 = (Point) this.b.get(keyAt);
                float f = (float) (point3.x + (point4 != null ? point4.x : 0));
                int i3 = point3.y;
                int i4 = point4 != null ? point4.y : 0;
                view.setX(f);
                view.setY((float) (i3 + i4));
                if (viewGroup.indexOfChild(view) < 0) {
                    if (view.getLayoutParams() != null) {
                        viewGroup.addView(view);
                    } else {
                        viewGroup.addView(view, new FrameLayout.LayoutParams(-2, -2));
                    }
                    view.requestLayout();
                }
            }
        }
        AppMethodBeat.o(2317);
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerView recyclerView) {
        AppMethodBeat.i(2327, false);
        if (recyclerView != null && (recyclerView.getParent() instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) recyclerView.getParent();
            for (int i = 0; i < this.a.size(); i++) {
                viewGroup.removeView((View) this.a.valueAt(i));
            }
        }
        this.b.clear();
        this.c.clear();
        this.a.clear();
        AppMethodBeat.o(2327);
    }
}
