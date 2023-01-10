package cn.missfresh.module.base.widget.wheelview.a;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

/* compiled from: AbstractWheelTextAdapter */
public abstract class b extends a {
    private static int f = 24;
    private static int g = 14;
    protected Context a;
    protected LayoutInflater b;
    protected int c;
    protected int d;
    protected int e;
    private boolean h = false;
    private int i = -15724528;
    private int j = 24;
    private int k = 0;
    private ArrayList<View> l = new ArrayList<>();

    /* access modifiers changed from: protected */
    public abstract CharSequence b(int i);

    protected b(Context context, int i, int i2, int i3, int i4, int i5) {
        this.a = context;
        this.c = i;
        this.d = i2;
        this.k = i3;
        f = i4;
        g = i5;
        this.b = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<View> a() {
        return this.l;
    }

    public void a(int i) {
        this.d = i;
    }

    @Override // cn.missfresh.module.base.widget.wheelview.a.c
    public View a(int i, View view, ViewGroup viewGroup) {
        if (i < 0 || i >= b()) {
            return null;
        }
        if (view == null) {
            view = a(this.c, viewGroup);
        }
        TextView a = a(view, this.d);
        if (!this.l.contains(a)) {
            this.l.add(a);
        }
        if (a != null) {
            CharSequence b = b(i);
            if (b == null) {
                b = "";
            }
            a.setText(b);
            if (i == this.k) {
                a.setTextSize((float) f);
                if (this.h) {
                    a.setTypeface(Typeface.defaultFromStyle(1));
                }
            } else {
                a.setTextSize((float) g);
                if (this.h) {
                    a.setTypeface(Typeface.defaultFromStyle(0));
                }
            }
            if (this.c == -1) {
                a(a);
            }
        }
        return view;
    }

    @Override // cn.missfresh.module.base.widget.wheelview.a.a, cn.missfresh.module.base.widget.wheelview.a.c
    public View a(View view, ViewGroup viewGroup) {
        if (view == null) {
            view = a(this.e, viewGroup);
        }
        if (this.e == -1 && (view instanceof TextView)) {
            a((TextView) view);
        }
        return view;
    }

    /* access modifiers changed from: protected */
    public void a(TextView textView) {
        textView.setTextColor(this.i);
        textView.setGravity(17);
        textView.setTextSize((float) this.j);
        textView.setLines(1);
        textView.setTypeface(Typeface.SANS_SERIF, 1);
    }

    private TextView a(View view, int i) {
        if (i == 0) {
            try {
                if (view instanceof TextView) {
                    return (TextView) view;
                }
            } catch (ClassCastException e) {
                throw new IllegalStateException("AbstractWheelAdapter requires the resource ID to be a TextView", e);
            }
        }
        if (i != 0) {
            return (TextView) view.findViewById(i);
        }
        return null;
    }

    private View a(int i, ViewGroup viewGroup) {
        if (i == -1) {
            return new TextView(this.a);
        }
        if (i != 0) {
            return this.b.inflate(i, viewGroup, false);
        }
        return null;
    }
}
