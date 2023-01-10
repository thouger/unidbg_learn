package com.sobot.chat.widget;

import android.content.Context;
import android.media.TtmlUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.android.internal.app.DumpHeapActivity;
import com.sobot.chat.utils.q;

public class DropdownListView extends ListView implements AbsListView.OnScrollListener {
    private LayoutInflater a;
    private FrameLayout b;
    private LinearLayout c;
    private ProgressBar d;
    private boolean e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private boolean k;
    private b l;
    private boolean m;
    private boolean n;
    private a o;

    public interface a {
        void a(AbsListView absListView, int i, int i2, int i3);
    }

    public interface b {
        void G();
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public DropdownListView(Context context) {
        super(context);
        a(context);
    }

    public DropdownListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        setCacheColorHint(context.getResources().getColor(q.a(getContext(), "color", "sobot_transparent")));
        this.a = LayoutInflater.from(context);
        this.b = (FrameLayout) this.a.inflate(q.a(getContext(), TtmlUtils.TAG_LAYOUT, "sobot_dropdown_lv_head"), (ViewGroup) null);
        this.c = (LinearLayout) this.b.findViewById(q.a(getContext(), "id", "drop_down_head"));
        this.d = (ProgressBar) this.b.findViewById(q.a(getContext(), "id", "sobot_loading"));
        a(this.c);
        this.g = this.c.getMeasuredHeight();
        this.f = this.c.getMeasuredWidth();
        this.c.setPadding(0, this.g * -1, 0, 0);
        this.c.invalidate();
        Log.v(DumpHeapActivity.KEY_SIZE, "width:" + this.f + " height:" + this.g);
        addHeaderView(this.b, null, false);
        setOnScrollListener(this);
        this.j = 3;
        this.m = false;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.i = i;
        a aVar = this.o;
        if (aVar != null) {
            aVar.a(absListView, i, i2, i3);
        }
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.m) {
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action == 1) {
                    int i = this.j;
                    if (!(i == 2 || i == 4)) {
                        if (i == 1) {
                            this.j = 3;
                            b();
                            Log.v("listview", "\u7531\u4e0b\u62c9\u5237\u65b0\u72b6\u6001\uff0c\u5230done\u72b6\u6001");
                        }
                        if (this.j == 0) {
                            this.j = 2;
                            b();
                            c();
                            Log.v("listview", "\u7531\u677e\u5f00\u5237\u65b0\u72b6\u6001\uff0c\u5230done\u72b6\u6001");
                        }
                    }
                    this.e = false;
                    this.k = false;
                } else if (action == 2) {
                    int y = (int) motionEvent.getY();
                    if (!this.e && this.i == 0) {
                        Log.v("listview", "\u5728move\u65f6\u5019\u8bb0\u5f55\u4e0b\u4f4d\u7f6e");
                        this.e = true;
                        this.h = y;
                    }
                    int i2 = this.j;
                    if (!(i2 == 2 || !this.e || i2 == 4)) {
                        if (i2 == 0) {
                            setSelection(0);
                            int i3 = this.h;
                            if ((y - i3) / 3 < this.g && y - i3 > 0) {
                                this.j = 1;
                                b();
                                Log.v("listview", "\u7531\u677e\u5f00\u5237\u65b0\u72b6\u6001\u8f6c\u53d8\u5230\u4e0b\u62c9\u5237\u65b0\u72b6\u6001");
                            } else if (y - this.h <= 0) {
                                this.j = 3;
                                b();
                                Log.v("listview", "\u7531\u677e\u5f00\u5237\u65b0\u72b6\u6001\u8f6c\u53d8\u5230done\u72b6\u6001");
                            }
                        }
                        if (this.j == 1) {
                            setSelection(0);
                            int i4 = this.h;
                            if ((y - i4) / 3 >= this.g) {
                                this.j = 0;
                                this.k = true;
                                b();
                                Log.v("listview", "\u7531done\u6216\u8005\u4e0b\u62c9\u5237\u65b0\u72b6\u6001\u8f6c\u53d8\u5230\u677e\u5f00\u5237\u65b0");
                            } else if (y - i4 <= 0) {
                                this.j = 3;
                                b();
                                Log.v("listview", "\u7531DOne\u6216\u8005\u4e0b\u62c9\u5237\u65b0\u72b6\u6001\u8f6c\u53d8\u5230done\u72b6\u6001");
                            }
                        }
                        if (this.j == 3 && y - this.h > 0) {
                            this.j = 1;
                            b();
                        }
                        if (this.j == 1) {
                            this.c.setPadding(0, (this.g * -1) + ((y - this.h) / 3), 0, 0);
                        }
                        if (this.j == 0) {
                            this.c.setPadding(0, ((y - this.h) / 3) - this.g, 0, 0);
                        }
                    }
                }
            } else if (this.i == 0 && !this.e) {
                this.e = true;
                this.h = (int) motionEvent.getY();
                Log.v("listview", "\u5728down\u65f6\u5019\u8bb0\u5f55\u5f53\u524d\u4f4d\u7f6e\u2018");
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private void b() {
        int i = this.j;
        if (i == 0) {
            this.d.setVisibility(0);
            Log.v("listview", "\u5f53\u524d\u72b6\u6001\uff0c\u677e\u5f00\u5237\u65b0");
        } else if (i == 1) {
            this.d.setVisibility(0);
            if (this.k) {
                this.k = false;
            }
            Log.v("listview", "\u5f53\u524d\u72b6\u6001\uff0c\u4e0b\u62c9\u5237\u65b0");
        } else if (i == 2) {
            this.c.setPadding(0, 0, 0, 0);
            this.d.setVisibility(0);
            Log.v("listview", "\u5f53\u524d\u72b6\u6001,\u6b63\u5728\u5237\u65b0...");
        } else if (i == 3) {
            this.c.setPadding(0, this.g * -1, 0, 0);
            this.d.setVisibility(8);
            Log.v("listview", "\u5f53\u524d\u72b6\u6001\uff0cdone");
        }
    }

    public void setOnRefreshListenerHead(b bVar) {
        this.l = bVar;
        this.m = true;
    }

    public void a() {
        this.j = 3;
        b();
    }

    private void c() {
        b bVar = this.l;
        if (bVar == null) {
            return;
        }
        if (this.n) {
            bVar.G();
        } else {
            a();
        }
    }

    private void a(View view) {
        int i;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i2 = layoutParams.height;
        if (i2 > 0) {
            i = View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
        } else {
            i = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, i);
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        super.setAdapter((ListAdapter) baseAdapter);
    }

    public void setPullRefreshEnable(boolean z) {
        this.n = z;
    }

    public void setDropdownListScrollListener(a aVar) {
        this.o = aVar;
    }
}
