package cn.missfresh.module.base.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import cn.missfresh.basiclib.ui.permission.a;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.dialog.dclog.LogDialog;
import cn.missfresh.module.base.common.dialog.dclog.b;
import cn.missfresh.module.base.helper.m;
import cn.missfresh.module.base.manager.o;
import cn.missfresh.module.base.support.view.TitleBar;
import cn.missfresh.module.mvp.base.BaseActivity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import de.greenrobot.event.EventBus;

public class BaseFragmentActivity extends BaseActivity implements View.OnClickListener, a {
    private b a;
    protected String b = getClass().getSimpleName();
    protected o c;
    protected m d;
    protected TitleBar e;
    protected Context f;
    protected boolean g = false;
    protected ProgressDialog h;
    protected LogDialog i;

    public void E_() {
    }

    public boolean F_() {
        return false;
    }

    /* access modifiers changed from: protected */
    public int M_() {
        return 0;
    }

    public void N_() {
    }

    public boolean f() {
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.base.base.BaseFragmentActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void onWindowFocusChanged(boolean z) {
        BaseFragmentActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    public void z_() {
    }

    public BaseFragmentActivity() {
        AppMethodBeat.i(3087, false);
        AppMethodBeat.o(3087);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.base.base.BaseFragmentActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(3094, false);
        BaseFragmentActivity.super.onCreate(bundle);
        this.f = this;
        if (Build.VERSION.SDK_INT >= 19) {
            c_(true);
        }
        this.c = new o(this);
        this.c.b(true);
        if (f()) {
            this.c.a(true);
            this.c.a(R.color.status_bar);
        }
        AppMethodBeat.o(3094);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: cn.missfresh.module.base.base.BaseFragmentActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void setContentView(int i) {
        AppMethodBeat.i(3099, false);
        this.d = new m(this, i);
        this.e = this.d.a();
        this.e.setLeftButtonOnClickListener(this);
        setContentView(this.d.b());
        AppMethodBeat.o(3099);
    }

    public void setContentViewWithOutTitleBar(int i) {
        AppMethodBeat.i(3107, false);
        BaseFragmentActivity.super.setContentView(i);
        AppMethodBeat.o(3107);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        AppMethodBeat.i(3114, false);
        BaseFragmentActivity.super.onStart();
        AppMethodBeat.o(3114);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        AppMethodBeat.i(3120, false);
        BaseFragmentActivity.super.onNewIntent(intent);
        setIntent(intent);
        AppMethodBeat.o(3120);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        AppMethodBeat.i(3124, false);
        BaseFragmentActivity.super.onStop();
        AppMethodBeat.o(3124);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        AppMethodBeat.i(3134, false);
        BaseFragmentActivity.super.onPause();
        b bVar = this.a;
        if (bVar != null) {
            bVar.b();
        }
        AppMethodBeat.o(3134);
    }

    /* access modifiers changed from: protected */
    public void c_(boolean z) {
        AppMethodBeat.i(3139, false);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags |= 67108864;
        } else {
            attributes.flags &= -67108865;
        }
        window.setAttributes(attributes);
        AppMethodBeat.o(3139);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(3143, true);
        if (view.getId() == R.id.ll_title_bar_left_button) {
            J_();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(3143);
    }

    /* access modifiers changed from: protected */
    public void J_() {
        AppMethodBeat.i(3147, false);
        finish();
        AppMethodBeat.o(3147);
    }

    /* access modifiers changed from: protected */
    public void e_(String str) {
        AppMethodBeat.i(3151, false);
        this.e.setCenterTxt(str);
        AppMethodBeat.o(3151);
    }

    public void onBackPressed() {
        AppMethodBeat.i(3153, false);
        J_();
        AppMethodBeat.o(3153);
    }

    public void b(boolean z) {
        AppMethodBeat.i(3156, false);
        d(z);
        if (!isFinishing() && !this.h.isShowing()) {
            this.h.show();
        }
        AppMethodBeat.o(3156);
    }

    public void K_() {
        AppMethodBeat.i(3159, false);
        b(false);
        AppMethodBeat.o(3159);
    }

    public void c(boolean z) {
        AppMethodBeat.i(3162, false);
        d(z);
        if (this.h.isShowing()) {
            this.h.dismiss();
        }
        AppMethodBeat.o(3162);
    }

    public void l() {
        AppMethodBeat.i(3166, false);
        d(false);
        if (this.h.isShowing()) {
            this.h.dismiss();
        }
        AppMethodBeat.o(3166);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.base.base.BaseFragmentActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void d(boolean z) {
        AppMethodBeat.i(3170, false);
        if (this.h == null) {
            this.h = new ProgressDialog(this);
            this.h.setCanceledOnTouchOutside(z);
            this.h.setOnKeyListener(new 1(this));
            this.h.setMessage("\u8bf7\u7a0d\u5019...");
            this.h.setCancelable(z);
            this.h.dismiss();
        }
        AppMethodBeat.o(3170);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.base.base.BaseFragmentActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void L_() {
        AppMethodBeat.i(3174, false);
        if (this.a == null) {
            this.a = new b(this.i);
        }
        this.a.a((Context) this);
        AppMethodBeat.o(3174);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(3176, false);
        if (getCurrentFocus() != null) {
            boolean n = n();
            AppMethodBeat.o(3176);
            return n;
        }
        boolean onTouchEvent = BaseFragmentActivity.super.onTouchEvent(motionEvent);
        AppMethodBeat.o(3176);
        return onTouchEvent;
    }

    public boolean n() {
        AppMethodBeat.i(3181, false);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            boolean hideSoftInputFromWindow = inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            AppMethodBeat.o(3181);
            return hideSoftInputFromWindow;
        }
        AppMethodBeat.o(3181);
        return false;
    }

    public void startActivity(Intent intent) {
        AppMethodBeat.i(3185, false);
        BaseFragmentActivity.super.startActivity(intent);
        overridePendingTransition(R.anim.anim_base_activity_enter, R.anim.anim_base_activity_exit);
        AppMethodBeat.o(3185);
    }

    public void a(Intent intent) {
        AppMethodBeat.i(3191, false);
        BaseFragmentActivity.super.startActivity(intent);
        AppMethodBeat.o(3191);
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        AppMethodBeat.i(3193, false);
        BaseFragmentActivity.super.startActivityForResult(intent, i, bundle);
        overridePendingTransition(R.anim.anim_base_activity_enter, R.anim.anim_base_activity_exit);
        AppMethodBeat.o(3193);
    }

    public void startActivityForResult(Intent intent, int i) {
        AppMethodBeat.i(3197, false);
        BaseFragmentActivity.super.startActivityForResult(intent, i);
        overridePendingTransition(R.anim.anim_base_activity_enter, R.anim.anim_base_activity_exit);
        AppMethodBeat.o(3197);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        AppMethodBeat.i(3205, false);
        BaseFragmentActivity.super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        b bVar = this.a;
        if (bVar != null) {
            bVar.c();
        }
        AppMethodBeat.o(3205);
    }

    public void finish() {
        AppMethodBeat.i(3209, false);
        BaseFragmentActivity.super.finish();
        overridePendingTransition(R.anim.anim_base_activity_exit, R.anim.anim_base_activity_close);
        b bVar = this.a;
        if (bVar != null) {
            bVar.d();
        }
        AppMethodBeat.o(3209);
    }

    /* access modifiers changed from: protected */
    public void p() {
        AppMethodBeat.i(3215, false);
        BaseFragmentActivity.super.finish();
        AppMethodBeat.o(3215);
    }

    public void setTitleBarVisibility(boolean z) {
        AppMethodBeat.i(3220, false);
        TitleBar titleBar = this.e;
        if (titleBar != null) {
            if (z) {
                titleBar.setVisibility(0);
            } else {
                titleBar.setVisibility(8);
            }
        }
        AppMethodBeat.o(3220);
    }

    public void w_() {
        AppMethodBeat.i(3226, false);
        finish();
        AppMethodBeat.o(3226);
    }
}
