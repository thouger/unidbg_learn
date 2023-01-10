package cn.missfresh.module.base.widget.tagflow;

import android.view.View;
import cn.missfresh.module.base.widget.FlowLayout;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: TagAdapter */
public abstract class a<T> {
    private List<T> a;
    private AbstractC0036a b;
    private HashSet<Integer> c = new HashSet<>();

    /* compiled from: TagAdapter */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.tagflow.a$a  reason: collision with other inner class name */
    public interface AbstractC0036a {
        void a();
    }

    public abstract View a(FlowLayout flowLayout, int i, T t);

    public boolean a(int i, T t) {
        return false;
    }

    public a(List<T> list) {
        this.a = list;
    }

    /* access modifiers changed from: package-private */
    public void a(AbstractC0036a aVar) {
        this.b = aVar;
    }

    public void a(Set<Integer> set) {
        this.c.clear();
        this.c.addAll(set);
        c();
    }

    /* access modifiers changed from: protected */
    public HashSet<Integer> a() {
        return this.c;
    }

    public int b() {
        List<T> list = this.a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void c() {
        this.b.a();
    }

    public T a(int i) {
        return this.a.get(i);
    }
}
