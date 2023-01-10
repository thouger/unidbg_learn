package cn.missfresh.basiclib.ui.permission;

import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import cn.missfresh.basiclib.R;
import cn.missfresh.basiclib.base.BasePermissionDialogFragment;
import cn.missfresh.basiclib.utils.permission.a;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;

public class PermissionExplainDialog extends BasePermissionDialogFragment implements View.OnClickListener {
    private TextView a;
    private Button b;
    private Button c;
    private String d;
    private String e;
    private boolean f = false;
    private boolean g = false;
    private c h;

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.basiclib.base.BasePermissionDialogFragment
    public int a() {
        return 80;
    }

    @Override // cn.missfresh.basiclib.base.BasePermissionDialogFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    @Override // cn.missfresh.basiclib.base.BasePermissionDialogFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public static PermissionExplainDialog a(String str) {
        AppMethodBeat.i(4615, false);
        Bundle bundle = new Bundle();
        bundle.putString(UsbManager.EXTRA_PERMISSION_GRANTED, str);
        PermissionExplainDialog permissionExplainDialog = new PermissionExplainDialog();
        permissionExplainDialog.setArguments(bundle);
        AppMethodBeat.o(4615);
        return permissionExplainDialog;
    }

    @Override // cn.missfresh.basiclib.base.BasePermissionDialogFragment
    public void a(View view) {
        AppMethodBeat.i(4619, false);
        this.a = (TextView) view.findViewById(R.id.tv_message);
        this.b = (Button) view.findViewById(R.id.btn_ensure);
        this.b.setTag(0);
        this.c = (Button) view.findViewById(R.id.btn_cancel);
        this.c.setTag(1);
        this.c.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.d = getString(R.string.permission_open);
        AppMethodBeat.o(4619);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.basiclib.base.BasePermissionDialogFragment
    public void a(Bundle bundle) {
        AppMethodBeat.i(4621, false);
        c(getArguments().getString(UsbManager.EXTRA_PERMISSION_GRANTED, ""));
        AppMethodBeat.o(4621);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.basiclib.base.BasePermissionDialogFragment
    public int b() {
        return R.layout.dialog_fragment_permission_hint;
    }

    private void c(String str) {
        AppMethodBeat.i(4629, false);
        this.e = str;
        TextView textView = this.a;
        if (textView != null) {
            textView.setText(String.format(this.d, c.a(getContext(), str)));
        }
        AppMethodBeat.o(4629);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(4631, false);
        int intValue = ((Integer) view.getTag()).intValue();
        if (intValue != 0) {
            if (intValue == 1) {
                c cVar = this.h;
                if (cVar != null) {
                    cVar.a(this.e);
                }
                dismissAllowingStateLoss();
            }
        } else if (this.g) {
            c cVar2 = this.h;
            if (cVar2 != null) {
                cVar2.a();
            }
        } else {
            c();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(4631);
    }

    private void c() {
        AppMethodBeat.i(4635, false);
        if (getContext() == null) {
            dismissAllowingStateLoss();
        } else if (ActivityCompat.checkSelfPermission(getContext(), this.e) != 0 || !a.a(this.e)) {
            try {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), this.e)) {
                    e();
                    AppMethodBeat.o(4635);
                    return;
                }
            } catch (Exception e) {
                d.d("tag", "e=" + e);
            }
            f();
        } else {
            d();
        }
        AppMethodBeat.o(4635);
    }

    private void d() {
        AppMethodBeat.i(4638, false);
        c cVar = this.h;
        if (cVar != null) {
            cVar.b(this.e);
        }
        dismissAllowingStateLoss();
        AppMethodBeat.o(4638);
    }

    private void e() {
        AppMethodBeat.i(4639, false);
        c cVar = this.h;
        if (cVar != null) {
            cVar.c(this.e);
        }
        dismissAllowingStateLoss();
        AppMethodBeat.o(4639);
    }

    private void f() {
        AppMethodBeat.i(4641, false);
        this.f = true;
        a.a(getContext());
        AppMethodBeat.o(4641);
    }

    public void b(String str) {
        AppMethodBeat.i(4644, false);
        if (this.a != null) {
            c(str);
        } else {
            Bundle arguments = getArguments();
            arguments.putString(UsbManager.EXTRA_PERMISSION_GRANTED, str);
            setArguments(arguments);
        }
        AppMethodBeat.o(4644);
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void a(c cVar) {
        this.h = cVar;
    }

    @Override // cn.missfresh.basiclib.base.BasePermissionDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        AppMethodBeat.i(4648, false);
        super.onResume();
        if (this.f) {
            this.f = false;
            dismissAllowingStateLoss();
            c cVar = this.h;
            if (cVar != null) {
                cVar.c(this.e);
            }
        }
        AppMethodBeat.onResume(this);
        AppMethodBeat.o(4648);
    }
}
