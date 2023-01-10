package cn.missfresh.basiclib.ui.permission;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.basiclib.R;
import cn.missfresh.basiclib.base.BasePermissionDialogFragment;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class InformDialog extends BasePermissionDialogFragment {
    private InformAdapter a;
    private a b;

    public interface a {
        void a();
    }

    @Override // cn.missfresh.basiclib.base.BasePermissionDialogFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    @Override // cn.missfresh.basiclib.base.BasePermissionDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AppMethodBeat.onResume(this);
    }

    @Override // cn.missfresh.basiclib.base.BasePermissionDialogFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    /* renamed from: cn.missfresh.basiclib.ui.permission.InformDialog$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(4594, false);
            if (InformDialog.this.b != null) {
                InformDialog.this.b.a();
            }
            InformDialog.this.dismissAllowingStateLoss();
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(4594);
        }
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.basiclib.base.BasePermissionDialogFragment
    public void a(View view) {
        AppMethodBeat.i(4600, false);
        view.findViewById(R.id.btn_next).setOnClickListener(new AnonymousClass1());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_permission);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.a = new InformAdapter();
        recyclerView.setAdapter(this.a);
        AppMethodBeat.o(4600);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.basiclib.base.BasePermissionDialogFragment
    public void a(Bundle bundle) {
        AppMethodBeat.i(4601, false);
        this.a.a(getArguments().getStringArrayList("permissions"));
        AppMethodBeat.o(4601);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.basiclib.base.BasePermissionDialogFragment
    public int b() {
        return R.layout.dialog_fragment_explain;
    }
}
