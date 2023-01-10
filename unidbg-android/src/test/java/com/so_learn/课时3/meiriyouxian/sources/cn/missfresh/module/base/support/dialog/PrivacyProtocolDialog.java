package cn.missfresh.module.base.support.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.interfaces.b;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.unitepoplib.bean.DialogBean;
import cn.missfresh.unitepoplib.view.UniteDialogFragment;
import com.alibaba.android.arouter.b.a;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrivacyProtocolDialog extends UniteDialogFragment implements View.OnClickListener {
    private TextView a;
    private TextView b;
    private String c;
    private String d;
    private List<String> e;
    private int f = 0;
    private final String g = "\u300a\u6bcf\u65e5\u4f18\u9c9c\u7528\u6237\u534f\u8bae\u300b";
    private final String h = "\u300a\u6bcf\u65e5\u4f18\u9c9c\u9690\u79c1\u653f\u7b56\u300b";
    private b i;

    @Override // cn.missfresh.unitepoplib.view.UniteDialogFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    @Override // cn.missfresh.unitepoplib.view.UniteDialogFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public PrivacyProtocolDialog(String str, String str2, List<String> list) {
        this.c = str;
        this.d = str2;
        this.e = list;
    }

    public static PrivacyProtocolDialog a(DialogBean dialogBean, String str, String str2, String str3, List<String> list) {
        AppMethodBeat.i(21634, false);
        PrivacyProtocolDialog privacyProtocolDialog = new PrivacyProtocolDialog(str2, str3, list);
        Bundle bundle = new Bundle();
        bundle.putSerializable("extra_dialog_bean", dialogBean);
        bundle.putString("queue_tag", str);
        privacyProtocolDialog.setArguments(bundle);
        AppMethodBeat.o(21634);
        return privacyProtocolDialog;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AppMethodBeat.i(21635, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        View inflate = View.inflate(getContext(), R.layout.dialog_privacy_protocol, null);
        AppMethodBeat.o(21635);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        AppMethodBeat.i(21637, false);
        super.onViewCreated(view, bundle);
        a(view);
        AppMethodBeat.o(21637);
    }

    /* access modifiers changed from: protected */
    public void a(View view) {
        int i = 0;
        AppMethodBeat.i(21640, false);
        this.a = (TextView) view.findViewById(R.id.tv_title);
        this.b = (TextView) view.findViewById(R.id.tv_detail);
        view.findViewById(R.id.tv_double_btn_cancel).setOnClickListener(this);
        view.findViewById(R.id.tv_double_btn_ok).setOnClickListener(this);
        this.a.setText(this.c);
        Matcher matcher = Pattern.compile("\\{\\w+\\}").matcher(this.d);
        SpannableString spannableString = new SpannableString(this.d.replaceAll("\\{", "").replaceAll("\\}", ""));
        if (this.e != null) {
            while (matcher.find()) {
                int i2 = i + 1;
                spannableString.setSpan(new AnonymousClass1(this.e.get(i)), matcher.start() - ((i2 - 1) * 2), matcher.end() - (i2 * 2), 33);
                i = i2;
            }
        }
        this.b.setMovementMethod(LinkMovementMethod.getInstance());
        this.b.setText(spannableString);
        AppMethodBeat.o(21640);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.dialog.PrivacyProtocolDialog$1  reason: invalid class name */
    public class AnonymousClass1 extends ClickableSpan {
        final /* synthetic */ String a;

        AnonymousClass1(String str) {
            this.a = str;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            AppMethodBeat.i(21626, false);
            if (!q.a((Activity) PrivacyProtocolDialog.this.getActivity())) {
                AppMethodBeat.o(21626);
                return;
            }
            a.a().a("/promotion/user_protocol").withString("h5_url", this.a).navigation();
            AppMethodBeat.o(21626);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            AppMethodBeat.i(21627, false);
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(true);
            AppMethodBeat.o(21627);
        }
    }

    public void a(b bVar) {
        this.i = bVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21643, false);
        int id = view.getId();
        if (id == R.id.tv_double_btn_cancel) {
            if (cn.missfresh.module.base.common.c.a.b == 1) {
                cn.missfresh.module.base.common.c.a.a = 0;
                o.a(1);
                e.r();
            } else if (cn.missfresh.module.base.common.c.a.b == 2) {
                cn.missfresh.module.base.manager.a.a().d();
            }
            StatisticsManager.z("click_no", new Object[0]);
        } else if (id == R.id.tv_double_btn_ok) {
            e.a(cn.missfresh.module.base.common.c.a.a > 0 ? cn.missfresh.module.base.common.c.a.a : e.af());
            StatisticsManager.z("click_yes", new Object[0]);
        }
        dismissAllowingStateLoss();
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21643);
    }

    @Override // cn.missfresh.unitepoplib.view.UniteDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        AppMethodBeat.i(21644, false);
        super.onResume();
        getDialog().getWindow().setLayout(aw.b(320), -2);
        AppMethodBeat.onResume(this);
        AppMethodBeat.o(21644);
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        AppMethodBeat.i(21645, false);
        try {
            fragmentManager.beginTransaction().remove(this).commit();
            super.show(fragmentManager, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StatisticsManager.z("exposure_pop", new Object[0]);
        AppMethodBeat.o(21645);
    }

    @Override // cn.missfresh.unitepoplib.view.UniteDialogFragment, androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        AppMethodBeat.i(21647, false);
        super.onDismiss(dialogInterface);
        b bVar = this.i;
        if (bVar != null) {
            bVar.a();
        }
        AppMethodBeat.o(21647);
    }
}
