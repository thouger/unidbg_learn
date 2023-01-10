package cn.missfresh.module.base.support.sticky;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.util.ArrayList;
import java.util.List;

public class StickyItemViewDecoration extends RecyclerView.ItemDecoration {
    private int a;
    private b b;
    private a c;
    private boolean d;
    private List<Integer> e = new ArrayList();
    private LinearLayoutManager f;
    private int g = -1;
    private Paint h;
    private int i;
    private int j;

    public StickyItemViewDecoration() {
        AppMethodBeat.i(22553, false);
        a();
        this.i = aw.b(12);
        AppMethodBeat.o(22553);
    }

    private void a() {
        AppMethodBeat.i(22555, false);
        this.h = new Paint();
        this.h.setAntiAlias(true);
        AppMethodBeat.o(22555);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(22556, false);
        super.onDrawOver(canvas, recyclerView, state);
        if (this.b == null) {
            AppMethodBeat.o(22556);
        } else if (recyclerView.getAdapter().getItemCount() <= 0 || b.a(this.e)) {
            this.b.a(8);
            AppMethodBeat.o(22556);
        } else {
            if (this.f == null) {
                this.f = (LinearLayoutManager) recyclerView.getLayoutManager();
            }
            int findFirstVisibleItemPosition = this.f.findFirstVisibleItemPosition();
            int i = this.j;
            if (findFirstVisibleItemPosition < i || i == -1) {
                this.b.a(0);
                this.d = false;
                int childCount = recyclerView.getChildCount();
                int i2 = 0;
                while (true) {
                    if (i2 >= childCount) {
                        break;
                    }
                    View childAt = recyclerView.getChildAt(i2);
                    if (this.b.a(childAt)) {
                        this.b.a(0);
                        this.d = true;
                        c(i2);
                        if (childAt.getTop() <= 0) {
                            b(this.f.findFirstVisibleItemPosition());
                            this.b.a(0);
                        } else if (this.e.size() <= 0) {
                            this.b.a(8);
                        } else if (this.e.size() == 1) {
                            b(this.e.get(0).intValue());
                            this.b.a(8);
                        } else {
                            int lastIndexOf = this.e.lastIndexOf(Integer.valueOf(d(i2)));
                            if (lastIndexOf >= 1) {
                                b(this.e.get(lastIndexOf - 1).intValue());
                                this.b.a(0);
                            } else {
                                this.b.a(8);
                            }
                        }
                    } else {
                        i2++;
                    }
                }
                if (!this.d) {
                    this.a = 0;
                    int a = a(this.f.findFirstVisibleItemPosition());
                    if (a != -1) {
                        if (this.b.a() != 0) {
                            this.b.a(0);
                        }
                        b(a);
                    } else if (this.b.a() != 8) {
                        this.b.a(8);
                    }
                }
                AppMethodBeat.o(22556);
                return;
            }
            this.b.a(8);
            AppMethodBeat.o(22556);
        }
    }

    private int a(int i) {
        AppMethodBeat.i(22557, false);
        int i2 = -1;
        if (b.a(this.e)) {
            AppMethodBeat.o(22557);
            return -1;
        }
        int size = this.e.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            } else if (this.e.get(size).intValue() <= i) {
                i2 = this.e.get(size).intValue();
                break;
            } else {
                size--;
            }
        }
        AppMethodBeat.o(22557);
        return i2;
    }

    private void b(int i) {
        a aVar;
        AppMethodBeat.i(22559, false);
        if (this.g == i || (aVar = this.c) == null) {
            AppMethodBeat.o(22559);
            return;
        }
        this.g = i;
        this.b.a(aVar.a(i));
        AppMethodBeat.o(22559);
    }

    private void c(int i) {
        AppMethodBeat.i(22560, false);
        int d = d(i);
        if (!this.e.contains(Integer.valueOf(d))) {
            this.e.add(Integer.valueOf(d));
        }
        AppMethodBeat.o(22560);
    }

    private int d(int i) {
        AppMethodBeat.i(22561, false);
        int findFirstVisibleItemPosition = this.f.findFirstVisibleItemPosition() + i;
        AppMethodBeat.o(22561);
        return findFirstVisibleItemPosition;
    }
}
