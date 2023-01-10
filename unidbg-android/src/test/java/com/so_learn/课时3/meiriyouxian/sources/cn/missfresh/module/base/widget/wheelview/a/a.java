package cn.missfresh.module.base.widget.wheelview.a;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;
import java.util.List;

/* compiled from: AbstractWheelAdapter */
public abstract class a implements c {
    private List<DataSetObserver> a;

    @Override // cn.missfresh.module.base.widget.wheelview.a.c
    public View a(View view, ViewGroup viewGroup) {
        return null;
    }

    @Override // cn.missfresh.module.base.widget.wheelview.a.c
    public void a(DataSetObserver dataSetObserver) {
        if (this.a == null) {
            this.a = new LinkedList();
        }
        this.a.add(dataSetObserver);
    }

    @Override // cn.missfresh.module.base.widget.wheelview.a.c
    public void b(DataSetObserver dataSetObserver) {
        List<DataSetObserver> list = this.a;
        if (list != null) {
            list.remove(dataSetObserver);
        }
    }
}
