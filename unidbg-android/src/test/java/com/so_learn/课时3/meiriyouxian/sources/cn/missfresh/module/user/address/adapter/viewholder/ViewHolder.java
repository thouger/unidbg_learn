package cn.missfresh.module.user.address.adapter.viewholder;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> a = new SparseArray<>();
    private int b;
    private View c;
    private Context d;
    private int e;

    public ViewHolder(Context context, View view, ViewGroup viewGroup, int i) {
        super(view);
        AppMethodBeat.i(3541, false);
        this.d = context;
        this.c = view;
        this.b = i;
        this.c.setTag(this);
        AppMethodBeat.o(3541);
    }

    public ViewHolder(Context context, View view) {
        super(view);
        AppMethodBeat.i(3544, false);
        this.d = context;
        this.c = view;
        AppMethodBeat.o(3544);
    }

    public static ViewHolder a(Context context, View view, ViewGroup viewGroup, int i, int i2) {
        AppMethodBeat.i(3546, false);
        if (view == null) {
            ViewHolder viewHolder = new ViewHolder(context, LayoutInflater.from(context).inflate(i, viewGroup, false), viewGroup, i2);
            viewHolder.e = i;
            AppMethodBeat.o(3546);
            return viewHolder;
        }
        ViewHolder viewHolder2 = (ViewHolder) view.getTag();
        viewHolder2.b = i2;
        AppMethodBeat.o(3546);
        return viewHolder2;
    }

    public <T extends View> T a(int i) {
        AppMethodBeat.i(3548, false);
        T t = (T) ((View) this.a.get(i));
        if (t == null) {
            t = (T) this.c.findViewById(i);
            this.a.put(i, t);
        }
        AppMethodBeat.o(3548);
        return t;
    }

    public View a() {
        return this.c;
    }

    public ViewHolder a(int i, String str) {
        AppMethodBeat.i(3553, false);
        ((TextView) a(i)).setText(str);
        AppMethodBeat.o(3553);
        return this;
    }

    public void b(int i) {
        this.b = i;
    }
}
