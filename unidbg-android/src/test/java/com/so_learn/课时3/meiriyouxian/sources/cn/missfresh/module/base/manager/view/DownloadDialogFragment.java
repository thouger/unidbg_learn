package cn.missfresh.module.base.manager.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.utils.ah;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;
import java.io.File;

public class DownloadDialogFragment extends DialogFragment implements View.OnClickListener {
    private static String a = "upgrade_type";
    private int b = 0;
    private ProgressBar c;
    private TextView d;
    private TextView e;
    private TextView f;
    private Button g;
    private Button h;
    private Button i;
    private String j;
    private String k;
    private int l = 0;

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public static DownloadDialogFragment a(int i) {
        AppMethodBeat.i(15122, false);
        Bundle bundle = new Bundle();
        bundle.putInt(a, i);
        DownloadDialogFragment downloadDialogFragment = new DownloadDialogFragment();
        downloadDialogFragment.setArguments(bundle);
        AppMethodBeat.o(15122);
        return downloadDialogFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AppMethodBeat.i(15124, false);
        View inflate = layoutInflater.inflate(R.layout.dialog_fragment_download, viewGroup, false);
        a(inflate);
        AppMethodBeat.o(15124);
        return inflate;
    }

    private void a(View view) {
        AppMethodBeat.i(15126, false);
        this.c = (ProgressBar) view.findViewById(R.id.pb_progress);
        this.d = (TextView) view.findViewById(R.id.tv_subTitle);
        this.e = (TextView) view.findViewById(R.id.tv_progress);
        this.f = (TextView) view.findViewById(R.id.tv_count);
        this.g = (Button) view.findViewById(R.id.btn_cancel);
        this.h = (Button) view.findViewById(R.id.btn_setting);
        this.i = (Button) view.findViewById(R.id.btn_install);
        this.c.setProgress(0);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        AppMethodBeat.o(15126);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        AppMethodBeat.i(15127, false);
        super.onViewCreated(view, bundle);
        a(getDialog());
        AppMethodBeat.o(15127);
    }

    /* access modifiers changed from: protected */
    public void a(Dialog dialog) {
        AppMethodBeat.i(15128, false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(1);
        dialog.setOnKeyListener(new AnonymousClass1());
        AppMethodBeat.o(15128);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.manager.view.DownloadDialogFragment$1  reason: invalid class name */
    public class AnonymousClass1 implements DialogInterface.OnKeyListener {
        AnonymousClass1() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            AppMethodBeat.i(15118, false);
            if (keyEvent.getKeyCode() == 4) {
                AppMethodBeat.o(15118);
                return true;
            }
            AppMethodBeat.o(15118);
            return false;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        AppMethodBeat.i(15129, false);
        super.onActivityCreated(bundle);
        this.j = getString(R.string.download_progress);
        this.k = getString(R.string.download_size);
        this.b = getArguments().getInt(a, 0);
        AppMethodBeat.o(15129);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        AppMethodBeat.i(15131, false);
        super.onResume();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.5f;
        attributes.width = aw.b(300);
        attributes.height = -2;
        window.setAttributes(attributes);
        AppMethodBeat.onResume(this);
        AppMethodBeat.o(15131);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(15133, true);
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            dismissAllowingStateLoss();
            if (this.b == 2) {
                getActivity().finish();
            }
        } else if (id == R.id.btn_setting) {
            c();
        } else if (id == R.id.btn_install) {
            e();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(15133);
    }

    public void b(int i) {
        AppMethodBeat.i(15135, false);
        if (this.h != null) {
            this.b = i;
        } else {
            getArguments().putInt(a, i);
        }
        AppMethodBeat.o(15135);
    }

    public void c(int i) {
        AppMethodBeat.i(15138, false);
        this.l = i;
        TextView textView = this.e;
        if (textView != null) {
            textView.setText(String.format(this.j, Integer.valueOf(this.l)));
        }
        ProgressBar progressBar = this.c;
        if (progressBar != null) {
            progressBar.setProgress(i);
        }
        AppMethodBeat.o(15138);
    }

    public int a() {
        return this.l;
    }

    public void a(float f, float f2) {
        AppMethodBeat.i(15141, false);
        if (f < 0.0f || f2 < 0.0f) {
            AppMethodBeat.o(15141);
            return;
        }
        TextView textView = this.f;
        if (textView != null) {
            textView.setText(String.format(this.k, ah.a(f), ah.a(f2)));
        }
        AppMethodBeat.o(15141);
    }

    public void b() {
        AppMethodBeat.i(15142, false);
        this.d.setText(R.string.download_install);
        d();
        if (Build.VERSION.SDK_INT < 26) {
            e();
        } else if (getActivity().getPackageManager().canRequestPackageInstalls()) {
            e();
        } else {
            c();
        }
        AppMethodBeat.o(15142);
    }

    private void c() {
        AppMethodBeat.i(15144, false);
        startActivityForResult(new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + getContext().getPackageName())), 10010);
        AppMethodBeat.o(15144);
    }

    private void d() {
        AppMethodBeat.i(15146, false);
        boolean canRequestPackageInstalls = Build.VERSION.SDK_INT >= 26 ? getActivity().getPackageManager().canRequestPackageInstalls() : true;
        this.g.setVisibility(0);
        if (canRequestPackageInstalls) {
            this.i.setVisibility(0);
            this.h.setVisibility(8);
        } else {
            this.i.setVisibility(8);
            this.h.setVisibility(0);
        }
        AppMethodBeat.o(15146);
    }

    private void e() {
        Uri uri;
        AppMethodBeat.i(15148, false);
        if (getActivity() == null) {
            AppMethodBeat.o(15148);
            return;
        }
        File file = new File(r.a((Context) getActivity()) + File.separator + "MissFresh.apk");
        if (!file.exists()) {
            AppMethodBeat.o(15148);
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(1);
            Context context = getContext();
            uri = FileProvider.getUriForFile(context, ((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplicationID() + ".cameraprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);
        AppMethodBeat.o(15148);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        AppMethodBeat.i(15149, false);
        if (i != 10010) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1) {
            b();
        } else {
            d();
        }
        AppMethodBeat.o(15149);
    }
}
