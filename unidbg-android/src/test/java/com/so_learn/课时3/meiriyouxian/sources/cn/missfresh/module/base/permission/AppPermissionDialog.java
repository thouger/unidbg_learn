package cn.missfresh.module.base.permission;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.basiclib.ui.permission.InformDialog;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.permission.adapter.PermissionAdapter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;

public class AppPermissionDialog extends AppCompatDialogFragment {
    private TextView a;
    private TextView b;
    private RecyclerView c;
    private PermissionAdapter d;
    private TextView e;
    private TextView f;
    private InformDialog.a g;

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

    public static AppPermissionDialog a(ArrayList<String> arrayList) {
        AppMethodBeat.i(18616, false);
        Bundle bundle = new Bundle();
        AppPermissionDialog appPermissionDialog = new AppPermissionDialog();
        bundle.putStringArrayList("permissions", arrayList);
        appPermissionDialog.setArguments(bundle);
        AppMethodBeat.o(18616);
        return appPermissionDialog;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(18617, false);
        super.onCreate(bundle);
        setStyle(1, R.style.DialogConner6);
        AppMethodBeat.o(18617);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AppMethodBeat.i(18619, false);
        View inflate = View.inflate(getContext(), a(), null);
        a(inflate);
        AppMethodBeat.o(18619);
        return inflate;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        AppMethodBeat.i(18622, false);
        super.onActivityCreated(bundle);
        a(bundle);
        AppMethodBeat.o(18622);
    }

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
        AppMethodBeat.i(18624, false);
        this.d = new PermissionAdapter(getContext(), getArguments().getStringArrayList("permissions"));
        this.c.setAdapter(this.d);
        AppMethodBeat.o(18624);
    }

    /* access modifiers changed from: protected */
    public int a() {
        return R.layout.layout_app_permission;
    }

    /* access modifiers changed from: protected */
    public void a(View view) {
        AppMethodBeat.i(18627, false);
        this.a = (TextView) view.findViewById(R.id.tv_title);
        this.b = (TextView) view.findViewById(R.id.tv_subtitle);
        this.c = (RecyclerView) view.findViewById(R.id.rcv_list);
        this.c.setLayoutManager(new LinearLayoutManager(getContext()));
        this.e = (TextView) view.findViewById(R.id.tv_next);
        this.f = (TextView) view.findViewById(R.id.tv_denny);
        this.e.setOnClickListener(new AnonymousClass1());
        this.f.setOnClickListener(new AnonymousClass2());
        AppMethodBeat.o(18627);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.permission.AppPermissionDialog$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(18598, false);
            if (AppPermissionDialog.this.g != null) {
                AppPermissionDialog.this.g.a();
            }
            AppPermissionDialog.this.dismissAllowingStateLoss();
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(18598);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.permission.AppPermissionDialog$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(18608, false);
            e.y(true);
            AppPermissionDialog.this.dismissAllowingStateLoss();
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(18608);
        }
    }

    public void a(InformDialog.a aVar) {
        this.g = aVar;
    }

    public void b(ArrayList<String> arrayList) {
        AppMethodBeat.i(18632, false);
        PermissionAdapter permissionAdapter = this.d;
        if (permissionAdapter != null) {
            permissionAdapter.a(arrayList);
        } else {
            getArguments().putStringArrayList("permissions", arrayList);
        }
        AppMethodBeat.o(18632);
    }
}
