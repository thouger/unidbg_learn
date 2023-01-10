package cn.missfresh.module.base.common.dialog.dclog;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.d;
import com.alibaba.android.arouter.b.a;

public class LogWindowView implements View.OnClickListener, View.OnTouchListener {
    private static LogWindowView a = null;
    private static Context h = ((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication();
    private WindowManager b;
    private Button c;
    private WindowManager.LayoutParams d = new WindowManager.LayoutParams(-2, -2);
    private int e = 44;
    private int f = 44;
    private int g = (aw.a() / 2);

    static {
        AppMethodBeat.i(11627, false);
        AppMethodBeat.o(11627);
    }

    private LogWindowView() {
        AppMethodBeat.i(11617, false);
        if (Build.VERSION.SDK_INT >= 23) {
            this.d.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            this.d.type = 2002;
        }
        WindowManager.LayoutParams layoutParams = this.d;
        layoutParams.format = -2;
        layoutParams.flags = 40;
        layoutParams.gravity = 51;
        layoutParams.width = aw.b(this.e);
        this.d.height = aw.b(this.f);
        WindowManager.LayoutParams layoutParams2 = this.d;
        layoutParams2.x = 0;
        layoutParams2.y = 0;
        this.c = new Button(h);
        this.c.setBackgroundResource(R.mipmap.ic_launcher);
        this.b = (WindowManager) h.getSystemService(Context.WINDOW_SERVICE);
        this.b.addView(this.c, this.d);
        this.c.setOnClickListener(this);
        this.c.setOnTouchListener(this);
        AppMethodBeat.o(11617);
    }

    public static LogWindowView a() {
        AppMethodBeat.i(11618, false);
        if (a == null) {
            a = new LogWindowView();
        }
        LogWindowView logWindowView = a;
        AppMethodBeat.o(11618);
        return logWindowView;
    }

    private void c() {
        Button button;
        AppMethodBeat.i(11621, false);
        WindowManager windowManager = this.b;
        if (!(windowManager == null || (button = this.c) == null)) {
            windowManager.removeView(button);
            this.b = null;
            this.c = null;
        }
        AppMethodBeat.o(11621);
    }

    public void b() {
        AppMethodBeat.i(11622, false);
        c();
        a = null;
        AppMethodBeat.o(11622);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(11624, false);
        if (((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getTopActivity() instanceof BaseFragmentActivity) {
            ((BaseFragmentActivity) ((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getTopActivity()).L_();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(11624);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        AppMethodBeat.i(11626, false);
        int rawX = ((int) motionEvent.getRawX()) - aw.b(this.e / 2);
        int rawY = (((int) motionEvent.getRawY()) - d.c(r.r())) - aw.b(this.f / 2);
        int action = motionEvent.getAction();
        if (action == 1) {
            if (rawX > this.g) {
                this.d.x = aw.a();
            } else {
                this.d.x = 0;
            }
            WindowManager.LayoutParams layoutParams = this.d;
            layoutParams.y = rawY;
            this.b.updateViewLayout(this.c, layoutParams);
        } else if (action == 2) {
            WindowManager.LayoutParams layoutParams2 = this.d;
            layoutParams2.x = rawX;
            layoutParams2.y = rawY;
            this.b.updateViewLayout(this.c, layoutParams2);
        }
        AppMethodBeat.o(11626);
        return false;
    }
}
