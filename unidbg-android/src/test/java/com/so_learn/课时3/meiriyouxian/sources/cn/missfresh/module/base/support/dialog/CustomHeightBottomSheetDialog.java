package cn.missfresh.module.base.support.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.core.content.ContextCompat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.google.android.material.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CustomHeightBottomSheetDialog extends BottomSheetDialogFragment {
    private int a;
    private int b;
    private boolean c;
    private Window d;
    private BottomSheetBehavior e;
    private final BottomSheetBehavior.BottomSheetCallback f = new AnonymousClass2();

    public int d() {
        return 0;
    }

    public int e() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean f() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AppMethodBeat.onResume(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public CustomHeightBottomSheetDialog() {
        AppMethodBeat.i(21045, false);
        AppMethodBeat.o(21045);
    }

    static /* synthetic */ void a(CustomHeightBottomSheetDialog customHeightBottomSheetDialog) {
        AppMethodBeat.i(21077, false);
        customHeightBottomSheetDialog.b();
        AppMethodBeat.o(21077);
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AppMethodBeat.i(21047, false);
        this.c = true;
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(bundle);
        bottomSheetDialog.setOnShowListener(new AnonymousClass1());
        this.d = bottomSheetDialog.getWindow();
        a();
        AppMethodBeat.o(21047);
        return bottomSheetDialog;
    }

    /* renamed from: cn.missfresh.module.base.support.dialog.CustomHeightBottomSheetDialog$1  reason: invalid class name */
    class AnonymousClass1 implements DialogInterface.OnShowListener {
        AnonymousClass1() {
        }

        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(DialogInterface dialogInterface) {
            AppMethodBeat.i(21030, false);
            BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
            CustomHeightBottomSheetDialog.a(CustomHeightBottomSheetDialog.this);
            AppMethodBeat.o(21030);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        AppMethodBeat.i(21050, false);
        super.onStart();
        if (f()) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        AppMethodBeat.o(21050);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        AppMethodBeat.i(21052, false);
        super.onActivityCreated(bundle);
        b();
        h();
        AppMethodBeat.o(21052);
    }

    private void a() {
        AppMethodBeat.i(21054, false);
        this.a = d();
        this.b = e();
        AppMethodBeat.o(21054);
    }

    private void b() {
        AppMethodBeat.i(21065, false);
        int i = this.a;
        if (i <= 0) {
            AppMethodBeat.o(21065);
            return;
        }
        this.d.setLayout(-1, i);
        this.d.setGravity(80);
        AppMethodBeat.o(21065);
    }

    private BottomSheetBehavior c() {
        AppMethodBeat.i(21067, false);
        if (this.e != null) {
            g();
            BottomSheetBehavior bottomSheetBehavior = this.e;
            AppMethodBeat.o(21067);
            return bottomSheetBehavior;
        }
        View findViewById = this.d.findViewById(R.id.design_bottom_sheet);
        if (findViewById == null) {
            AppMethodBeat.o(21067);
            return null;
        }
        if (f()) {
            findViewById.setBackgroundColor(Color.parseColor("#00000000"));
        }
        this.e = BottomSheetBehavior.from(findViewById);
        int i = this.b;
        if (i > 0) {
            this.e.setPeekHeight(i);
        }
        g();
        BottomSheetBehavior bottomSheetBehavior2 = this.e;
        AppMethodBeat.o(21067);
        return bottomSheetBehavior2;
    }

    public void a(int i) {
        AppMethodBeat.i(21069, false);
        View findViewById = this.d.findViewById(R.id.design_bottom_sheet);
        if (findViewById != null) {
            findViewById.setBackgroundColor(ContextCompat.getColor(getContext(), i));
        }
        AppMethodBeat.o(21069);
    }

    private void g() {
        int i;
        AppMethodBeat.i(21070, false);
        BottomSheetBehavior bottomSheetBehavior = this.e;
        if (bottomSheetBehavior != null && (i = this.b) > 0) {
            bottomSheetBehavior.setPeekHeight(i);
        }
        AppMethodBeat.o(21070);
    }

    private void h() {
        AppMethodBeat.i(21073, false);
        if (c() != null) {
            this.e.setBottomSheetCallback(this.f);
        }
        AppMethodBeat.o(21073);
    }

    /* renamed from: cn.missfresh.module.base.support.dialog.CustomHeightBottomSheetDialog$2  reason: invalid class name */
    class AnonymousClass2 extends BottomSheetBehavior.BottomSheetCallback {
        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onSlide(View view, float f) {
        }

        AnonymousClass2() {
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onStateChanged(View view, int i) {
            AppMethodBeat.i(21038, false);
            if (i == 5) {
                CustomHeightBottomSheetDialog.this.dismiss();
                BottomSheetBehavior.from(view).setState(4);
            }
            AppMethodBeat.o(21038);
        }
    }
}
