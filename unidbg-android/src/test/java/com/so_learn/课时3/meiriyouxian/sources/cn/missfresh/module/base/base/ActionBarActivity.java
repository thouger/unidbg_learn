package cn.missfresh.module.base.base;

import android.view.MotionEvent;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

public class ActionBarActivity extends BaseFragmentActivity {
    protected List<View> a = new ArrayList();

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.base.base.ActionBarActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        ActionBarActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    public ActionBarActivity() {
        AppMethodBeat.i(3027, false);
        AppMethodBeat.o(3027);
    }

    public void setContentView(View view) {
        AppMethodBeat.i(3034, false);
        super.setContentView(view);
        this.e.setLeftButtonVisibility(0);
        this.e.setCenterTxt(getTitle().toString());
        this.e.setCenterVisibility(0);
        AppMethodBeat.o(3034);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: cn.missfresh.module.base.base.ActionBarActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(3039, false);
        q.a((FragmentActivity) this, motionEvent, this.a);
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        AppMethodBeat.o(3039);
        return dispatchTouchEvent;
    }
}
