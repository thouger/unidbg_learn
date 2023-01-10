package cn.missfresh.unitepoplib.view.dialog;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.unitepoplib.R;
import cn.missfresh.unitepoplib.UnitePopManager;
import cn.missfresh.unitepoplib.b.a;
import cn.missfresh.unitepoplib.bean.ConfirmDialogBean;
import cn.missfresh.unitepoplib.listener.b;

public class ConfirmAlertDialog extends DialogFragment implements View.OnClickListener {
    private String clickType;
    private ConfirmDialogBean mConfirmAlertbean;
    private a mDialogDissMissListener;
    private String mTag;
    private TextView mTvCancel;
    private TextView mTvContent;
    private TextView mTvOk;
    private TextView mTvTitle;

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

    public static ConfirmAlertDialog getInstance(ConfirmDialogBean confirmDialogBean, String str) {
        AppMethodBeat.i(15740, false);
        ConfirmAlertDialog confirmAlertDialog = new ConfirmAlertDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("extra_dialog_bean", confirmDialogBean);
        bundle.putString("queue_tag", str);
        confirmAlertDialog.setArguments(bundle);
        AppMethodBeat.o(15740);
        return confirmAlertDialog;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        AppMethodBeat.i(15742, false);
        super.onActivityCreated(bundle);
        a.a("dialogsdk", "ConfirmAlertDialog---onActivityCreated");
        AppMethodBeat.o(15742);
    }

    private void initData() {
        AppMethodBeat.i(15744, false);
        a.a("dialogsdk", "ConfirmAlertDialog---initData");
        if (getArguments() != null) {
            this.mConfirmAlertbean = (ConfirmDialogBean) getArguments().getSerializable("extra_dialog_bean");
            this.mTag = getArguments().getString("queue_tag");
        }
        AppMethodBeat.o(15744);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AppMethodBeat.i(15746, false);
        a.a("dialogsdk", "ConfirmAlertDialog---onCreateView");
        initData();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        int i = R.layout.confirm_dialog_layout;
        ConfirmDialogBean confirmDialogBean = this.mConfirmAlertbean;
        if (!(confirmDialogBean == null || confirmDialogBean.getLayoutId() == -1)) {
            i = this.mConfirmAlertbean.getLayoutId();
        }
        View inflate = View.inflate(getContext(), i, null);
        AppMethodBeat.o(15746);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        AppMethodBeat.i(15747, false);
        super.onViewCreated(view, bundle);
        a.a("dialogsdk", "ConfirmAlertDialog---onViewCreated");
        initView(view);
        AppMethodBeat.o(15747);
    }

    /* access modifiers changed from: protected */
    public void initView(View view) {
        AppMethodBeat.i(15748, false);
        a.a("dialogsdk", "ConfirmAlertDialog---initView");
        this.mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        this.mTvContent = (TextView) view.findViewById(R.id.tv_detail);
        this.mTvCancel = (TextView) view.findViewById(R.id.tv_double_btn_cancel);
        this.mTvOk = (TextView) view.findViewById(R.id.tv_double_btn_ok);
        this.mTvCancel.setOnClickListener(this);
        this.mTvOk.setOnClickListener(this);
        getDialog().setOnDismissListener(new AnonymousClass1());
        setData();
        AppMethodBeat.o(15748);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.unitepoplib.view.dialog.ConfirmAlertDialog$1  reason: invalid class name */
    public class AnonymousClass1 implements DialogInterface.OnDismissListener {
        AnonymousClass1() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            AppMethodBeat.i(15737, false);
            if (TextUtils.isEmpty(ConfirmAlertDialog.this.clickType)) {
                b.a().a(1004, ConfirmAlertDialog.this.mTag, ConfirmAlertDialog.this.mConfirmAlertbean);
            }
            if (ConfirmAlertDialog.this.mDialogDissMissListener != null) {
                ConfirmAlertDialog.this.mDialogDissMissListener.b(ConfirmAlertDialog.this.mConfirmAlertbean);
            }
            b.a().a(1008, ConfirmAlertDialog.this.mTag, ConfirmAlertDialog.this.mConfirmAlertbean);
            AppMethodBeat.o(15737);
        }
    }

    private void postMsg() {
        AppMethodBeat.i(15749, false);
        if ("clickCancel".equalsIgnoreCase(this.clickType)) {
            b.a().a(1002, this.mTag, this.mConfirmAlertbean);
        } else if ("clickOk".equalsIgnoreCase(this.clickType)) {
            b.a().a(1001, this.mTag, this.mConfirmAlertbean);
        }
        AppMethodBeat.o(15749);
    }

    private void setData() {
        AppMethodBeat.i(15750, false);
        ConfirmDialogBean confirmDialogBean = this.mConfirmAlertbean;
        if (confirmDialogBean != null) {
            if (TextUtils.isEmpty(confirmDialogBean.getTitle())) {
                this.mTvTitle.setVisibility(8);
            } else {
                this.mTvTitle.setVisibility(0);
                this.mTvTitle.setText(this.mConfirmAlertbean.getTitle());
            }
            if (TextUtils.isEmpty(this.mConfirmAlertbean.getContent())) {
                this.mTvContent.setVisibility(8);
            } else {
                this.mTvContent.setVisibility(0);
                this.mTvContent.setText(this.mConfirmAlertbean.getContent());
            }
            if (TextUtils.isEmpty(this.mConfirmAlertbean.getCancelTxt())) {
                this.mTvCancel.setVisibility(8);
            } else {
                this.mTvCancel.setVisibility(0);
                this.mTvCancel.setText(this.mConfirmAlertbean.getCancelTxt());
            }
            if (TextUtils.isEmpty(this.mConfirmAlertbean.getConfirmTxt())) {
                this.mTvOk.setVisibility(8);
            } else {
                this.mTvOk.setVisibility(0);
                this.mTvOk.setText(this.mConfirmAlertbean.getConfirmTxt());
            }
            setCancelable(this.mConfirmAlertbean.isCancelable());
        }
        AppMethodBeat.o(15750);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(15752, false);
        int id = view.getId();
        if (id == R.id.tv_double_btn_cancel) {
            this.clickType = "clickCancel";
        } else if (id == R.id.tv_double_btn_ok) {
            this.clickType = "clickOk";
        }
        postMsg();
        if (this.mConfirmAlertbean.isAutoDismiss()) {
            dismissAllowingStateLoss();
        }
        if (!this.mConfirmAlertbean.isBlockingUp()) {
            UnitePopManager.showNextDialog(this.mTag);
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(15752);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        AppMethodBeat.i(15753, false);
        super.onResume();
        getDialog().getWindow().setLayout(cn.missfresh.unitepoplib.b.b.a(280, getContext()), -2);
        AppMethodBeat.onResume(this);
        AppMethodBeat.o(15753);
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        AppMethodBeat.i(15755, false);
        try {
            fragmentManager.beginTransaction().remove(this).commit();
            super.show(fragmentManager, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(15755);
    }

    public void setmDialogDissMissListener(a aVar) {
        this.mDialogDissMissListener = aVar;
    }
}
