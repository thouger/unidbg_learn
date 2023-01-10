package cn.missfresh.module.base.payment.recharge.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.payment.recharge.model.StoredViewModel;
import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;

public class StoredPhoneFragment extends BaseTipDialog implements TextWatcher, View.OnClickListener {
    public static final String a = StoredPhoneFragment.class.getName();
    private static int j = 11;
    private static String k = "1";
    private static String m = "phone";
    private TextView d;
    private TextView e;
    private TextView f;
    private EditText g;
    private View h;
    private StoredViewModel i;
    private String l;

    public static void a(AppCompatActivity appCompatActivity, String str, BaseTipDialog.a aVar) {
        JniLib.cV(appCompatActivity, str, aVar, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_DISCONNECT));
    }

    private void c(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT));
    }

    private void k() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_RENAME));
    }

    private void l() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_DELETE_GROUP));
    }

    private void m() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_APN_RESTORE_DEFAULT));
    }

    private String n() {
        return (String) JniLib.cL(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_DREAM_START_DELAY));
    }

    public void a(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_STORAGE_CLEAR_CACHE));
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        JniLib.cV(this, editable, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_STORAGE_SYSTEM_INFO));
    }

    public boolean b(String str) {
        return JniLib.cZ(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_STORAGE_OTHER_INFO));
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        JniLib.cV(this, charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_STORAGE_USER_INFO));
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public double d() {
        return JniLib.cD(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_FINGERPRINT_ICON_TOUCH));
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public int e() {
        return JniLib.cI(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_FINGERPINT_ERROR));
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        JniLib.cV(this, bundle, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_FINGERPINT_EDIT));
    }

    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_FINGERPINT_DELETE_LAST));
    }

    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog, androidx.fragment.app.Fragment
    public void onResume() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_FINGERPRINT_CANCEL_SETUP));
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        JniLib.cV(this, charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_FINGERPRINT_SKIP_SETUP));
    }

    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_PROXY_SELECTOR_ERROR));
    }

    static /* synthetic */ void a(StoredPhoneFragment storedPhoneFragment, String str) {
        AppMethodBeat.i(17775, false);
        storedPhoneFragment.c(str);
        AppMethodBeat.o(17775);
    }

    static /* synthetic */ void a(StoredPhoneFragment storedPhoneFragment, boolean z) {
        AppMethodBeat.i(17774, false);
        storedPhoneFragment.a(z);
        AppMethodBeat.o(17774);
    }

    static /* synthetic */ String b(StoredPhoneFragment storedPhoneFragment) {
        AppMethodBeat.i(17770, false);
        String n = storedPhoneFragment.n();
        AppMethodBeat.o(17770);
        return n;
    }

    static {
        AppMethodBeat.i(17776, false);
        AppMethodBeat.o(17776);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public int R_() {
        return R.layout.fragment_stored_phone;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public void a(View view) {
        AppMethodBeat.i(17732, false);
        this.d = (TextView) view.findViewById(R.id.left_tv);
        this.e = (TextView) view.findViewById(R.id.right_tv);
        this.f = (TextView) view.findViewById(R.id.error_tip_tv);
        this.g = (EditText) view.findViewById(R.id.phone_et);
        this.h = view.findViewById(R.id.line_v);
        setCancelable(false);
        l();
        m();
        AppMethodBeat.o(17732);
    }

    /* renamed from: cn.missfresh.module.base.payment.recharge.view.StoredPhoneFragment$1  reason: invalid class name */
    class AnonymousClass1 implements Observer<String> {
        final /* synthetic */ StoredPhoneFragment a;

        AnonymousClass1(StoredPhoneFragment storedPhoneFragment) {
            JniLib.cV(this, storedPhoneFragment, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_VOLUME_INIT));
        }

        public void a(String str) {
            JniLib.cV(this, str, 560);
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(17723, false);
            a((String) obj);
            AppMethodBeat.o(17723);
        }
    }

    /* renamed from: cn.missfresh.module.base.payment.recharge.view.StoredPhoneFragment$2  reason: invalid class name */
    class AnonymousClass2 implements Observer<String> {
        final /* synthetic */ StoredPhoneFragment a;

        AnonymousClass2(StoredPhoneFragment storedPhoneFragment) {
            JniLib.cV(this, storedPhoneFragment, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_VOLUME_RENAME));
        }

        public void a(String str) {
            JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_VOLUME_UNMOUNT));
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(17728, false);
            a((String) obj);
            AppMethodBeat.o(17728);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(17748, true);
        if (view.getId() == R.id.left_tv) {
            if (this.c != null) {
                this.c.a(100, null);
            }
            dismiss();
        } else if (view.getId() == R.id.right_tv) {
            if (!b(n())) {
                c("\u8bf7\u8f93\u5165\u6709\u6548\u7684\u624b\u673a\u53f7\u7801");
                a(false);
                AppMethodBeat.onClick(this, view);
                AppMethodBeat.o(17748);
                return;
            }
            this.e.setClickable(false);
            this.i.a(n());
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(17748);
    }

    private void a(boolean z) {
        AppMethodBeat.i(17759, false);
        if (!z) {
            this.h.setBackgroundResource(R.color.color_ff4891);
            this.g.setTextColor(ContextCompat.getColor(getContext(), R.color.color_ff4891));
            AppMethodBeat.o(17759);
            return;
        }
        if (n().length() == 0) {
            this.h.setBackgroundResource(R.color.color_D8DBDE);
        } else {
            this.h.setBackgroundResource(R.color.color_181D21);
        }
        this.g.setTextColor(ContextCompat.getColor(getContext(), R.color.color_181D21));
        c("");
        AppMethodBeat.o(17759);
    }
}
