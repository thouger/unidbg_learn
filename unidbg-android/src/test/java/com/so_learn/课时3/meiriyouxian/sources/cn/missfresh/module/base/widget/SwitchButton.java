package cn.missfresh.module.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SwitchButton extends RelativeLayout {
    private View a;
    private int b;
    private a c;

    public interface a {
        void a(int i);
    }

    public SwitchButton(Context context) {
        super(context);
        AppMethodBeat.i(23865, false);
        a(context, null);
        AppMethodBeat.o(23865);
    }

    public SwitchButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(23866, false);
        a(context, attributeSet);
        AppMethodBeat.o(23866);
    }

    public SwitchButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(23867, false);
        a(context, attributeSet);
        AppMethodBeat.o(23867);
    }

    private void a(Context context, AttributeSet attributeSet) {
        AppMethodBeat.i(23868, false);
        LayoutInflater.from(context).inflate(R.layout.layout_switch_btn, this);
        this.a = findViewById(R.id.v_switch_btn);
        this.b = 1;
        AppMethodBeat.o(23868);
    }

    public void setOnSwitchListener(a aVar) {
        this.c = aVar;
    }

    public void setCurrentStatus(int i) {
        AppMethodBeat.i(23869, false);
        if (i == 1) {
            a();
        } else if (i == 0) {
            d();
        }
        AppMethodBeat.o(23869);
    }

    public void setCurrent(int i) {
        AppMethodBeat.i(23870, false);
        if (i == 1) {
            c();
        } else if (i == 0) {
            b();
        }
        AppMethodBeat.o(23870);
    }

    private void a() {
        AppMethodBeat.i(23871, false);
        c();
        this.c.a(0);
        AppMethodBeat.o(23871);
    }

    private void b() {
        AppMethodBeat.i(23872, false);
        this.a.setBackgroundResource(R.drawable.switch_btn_close);
        this.b = 0;
        AppMethodBeat.o(23872);
    }

    private void c() {
        AppMethodBeat.i(23873, false);
        this.a.setBackgroundResource(R.drawable.switch_btn_open);
        this.b = 1;
        AppMethodBeat.o(23873);
    }

    private void d() {
        AppMethodBeat.i(23874, false);
        b();
        a aVar = this.c;
        if (aVar != null) {
            aVar.a(1);
        }
        AppMethodBeat.o(23874);
    }

    private void e() {
        AppMethodBeat.i(23875, false);
        int i = this.b;
        if (i == 1) {
            d();
        } else if (i == 0) {
            a();
        }
        AppMethodBeat.o(23875);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(23876, false);
        if (motionEvent.getAction() == 0) {
            e();
        }
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        AppMethodBeat.o(23876);
        return onTouchEvent;
    }
}
