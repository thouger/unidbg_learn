package cn.missfresh.module.base.widget.wheelview.widget;

import android.view.View;
import android.widget.LinearLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.LinkedList;
import java.util.List;

/* compiled from: WheelRecycle */
public class e {
    private List<View> a;
    private List<View> b;
    private WheelView c;

    public e(WheelView wheelView) {
        this.c = wheelView;
    }

    public int a(LinearLayout linearLayout, int i, a aVar) {
        int i2 = 0;
        AppMethodBeat.i(24390, false);
        int i3 = i;
        while (i2 < linearLayout.getChildCount()) {
            if (!aVar.a(i3)) {
                a(linearLayout.getChildAt(i2), i3);
                linearLayout.removeViewAt(i2);
                if (i2 == 0) {
                    i++;
                }
            } else {
                i2++;
            }
            i3++;
        }
        AppMethodBeat.o(24390);
        return i;
    }

    public View a() {
        AppMethodBeat.i(24391, false);
        View a = a(this.a);
        AppMethodBeat.o(24391);
        return a;
    }

    public View b() {
        AppMethodBeat.i(24392, false);
        View a = a(this.b);
        AppMethodBeat.o(24392);
        return a;
    }

    public void c() {
        AppMethodBeat.i(24393, false);
        List<View> list = this.a;
        if (list != null) {
            list.clear();
        }
        List<View> list2 = this.b;
        if (list2 != null) {
            list2.clear();
        }
        AppMethodBeat.o(24393);
    }

    private List<View> a(View view, List<View> list) {
        AppMethodBeat.i(24394, false);
        if (list == null) {
            list = new LinkedList<>();
        }
        list.add(view);
        AppMethodBeat.o(24394);
        return list;
    }

    private void a(View view, int i) {
        AppMethodBeat.i(24395, false);
        int b = this.c.getViewAdapter().b();
        if ((i < 0 || i >= b) && !this.c.c()) {
            this.b = a(view, this.b);
        } else {
            while (i < 0) {
                i += b;
            }
            int i2 = i % b;
            this.a = a(view, this.a);
        }
        AppMethodBeat.o(24395);
    }

    private View a(List<View> list) {
        AppMethodBeat.i(24396, false);
        if (list == null || list.size() <= 0) {
            AppMethodBeat.o(24396);
            return null;
        }
        View view = list.get(0);
        list.remove(0);
        AppMethodBeat.o(24396);
        return view;
    }
}
