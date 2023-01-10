package cn.missfresh.module.base.support.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class LoadingLikeIosDialog extends Dialog {
    private ImageView a;

    public LoadingLikeIosDialog(Context context) {
        super(context);
    }

    public LoadingLikeIosDialog(Context context, int i) {
        super(context, i);
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(21528, false);
        super.onCreate(bundle);
        setContentView(R.layout.dialog_loading_like_ios_view);
        a();
        AppMethodBeat.o(21528);
    }

    private void a() {
        AppMethodBeat.i(21529, false);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        this.a = (ImageView) findViewById(R.id.iv_loading);
        setOnDismissListener(new AnonymousClass1());
        AppMethodBeat.o(21529);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.dialog.LoadingLikeIosDialog$1  reason: invalid class name */
    public class AnonymousClass1 implements DialogInterface.OnDismissListener {
        AnonymousClass1() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            AppMethodBeat.i(21527, false);
            LoadingLikeIosDialog.this.a.clearAnimation();
            AppMethodBeat.o(21527);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        AppMethodBeat.i(21530, false);
        super.show();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -2;
        attributes.height = -2;
        attributes.gravity = 17;
        getWindow().setAttributes(attributes);
        getWindow().setDimAmount(0.0f);
        this.a.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_like_ios_rotate));
        AppMethodBeat.o(21530);
    }
}
