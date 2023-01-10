package cn.missfresh.unitepoplib.factory;

import android.text.TextUtils;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.unitepoplib.UnitePopManager;
import cn.missfresh.unitepoplib.bean.ConfirmDialogBean;
import cn.missfresh.unitepoplib.bean.DialogBean;
import cn.missfresh.unitepoplib.bean.PicDialogBean;
import cn.missfresh.unitepoplib.view.dialog.AdPicCloseDialog;
import cn.missfresh.unitepoplib.view.dialog.ConfirmAlertDialog;
import java.util.HashMap;

/* compiled from: DialogFactory */
public class a implements cn.missfresh.unitepoplib.listener.a, cn.missfresh.unitepoplib.view.dialog.a {
    private static a a;
    private HashMap<DialogBean, Object> b = new HashMap<>();

    private a() {
        AppMethodBeat.i(15482, false);
        AppMethodBeat.o(15482);
    }

    public static a a() {
        AppMethodBeat.i(15485, false);
        if (a == null) {
            a = new a();
        }
        a aVar = a;
        AppMethodBeat.o(15485);
        return aVar;
    }

    public boolean a(String str, DialogBean dialogBean) {
        boolean z = false;
        AppMethodBeat.i(15488, false);
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "DialogFactory.createDialog ---> tag == " + str + " topDialog == " + dialogBean);
        if (dialogBean.getStyleType() == MoudleType.PIC_DIALOG.getType()) {
            c(str, dialogBean);
        } else if (dialogBean.getStyleType() == MoudleType.ALERT_TWO_BTN_DIALOG.getType()) {
            b(str, dialogBean);
        } else {
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "DialogFactory.show factory cann't show unkown type dialog");
            AppMethodBeat.o(15488);
            return z;
        }
        z = true;
        AppMethodBeat.o(15488);
        return z;
    }

    private void b(String str, DialogBean dialogBean) {
        AppMethodBeat.i(15489, false);
        if (a(str) && (dialogBean instanceof ConfirmDialogBean) && (UnitePopManager.b() instanceof FragmentActivity)) {
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "DialogFactory.show create confirm dialog");
            FragmentManager supportFragmentManager = ((FragmentActivity) UnitePopManager.b()).getSupportFragmentManager();
            ConfirmAlertDialog confirmAlertDialog = (ConfirmAlertDialog) supportFragmentManager.findFragmentByTag(dialogBean.getTemplateId());
            if (confirmAlertDialog == null) {
                confirmAlertDialog = ConfirmAlertDialog.getInstance((ConfirmDialogBean) dialogBean, str);
                confirmAlertDialog.setmDialogDissMissListener(this);
                supportFragmentManager.beginTransaction().add(confirmAlertDialog, dialogBean.getTemplateId()).commitNowAllowingStateLoss();
            } else {
                supportFragmentManager.beginTransaction().show(confirmAlertDialog).commitNowAllowingStateLoss();
            }
            HashMap<DialogBean, Object> hashMap = this.b;
            if (hashMap != null) {
                hashMap.put(dialogBean, confirmAlertDialog);
            }
        }
        AppMethodBeat.o(15489);
    }

    private void c(String str, DialogBean dialogBean) {
        AppMethodBeat.i(15491, false);
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "DialogFactory.show create pic dialog");
        if (dialogBean instanceof PicDialogBean) {
            AdPicCloseDialog adPicCloseDialog = new AdPicCloseDialog(UnitePopManager.b());
            adPicCloseDialog.a((PicDialogBean) dialogBean);
            adPicCloseDialog.a(str);
            adPicCloseDialog.a(this);
            adPicCloseDialog.a();
            this.b.put(dialogBean, adPicCloseDialog);
        }
        AppMethodBeat.o(15491);
    }

    @Override // cn.missfresh.unitepoplib.listener.a
    public boolean a(String str) {
        AppMethodBeat.i(15494, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(15494);
            return false;
        } else if (UnitePopManager.b() == null) {
            AppMethodBeat.o(15494);
            return false;
        } else if (!str.equalsIgnoreCase(UnitePopManager.a())) {
            AppMethodBeat.o(15494);
            return false;
        } else if (UnitePopManager.b().isFinishing()) {
            AppMethodBeat.o(15494);
            return false;
        } else {
            AppMethodBeat.o(15494);
            return true;
        }
    }

    public void a(DialogBean dialogBean) {
        AppMethodBeat.i(15495, false);
        if (dialogBean == null) {
            AppMethodBeat.o(15495);
            return;
        }
        cn.missfresh.unitepoplib.b.a.a("dialogSdk", "dissmissCurrentDialog--" + this.b);
        Object obj = this.b.get(dialogBean);
        if (obj == null) {
            AppMethodBeat.o(15495);
            return;
        }
        if (obj instanceof DialogFragment) {
            ((DialogFragment) obj).dismissAllowingStateLoss();
        }
        if (obj instanceof AdPicCloseDialog) {
            ((AdPicCloseDialog) obj).b();
        }
        this.b.remove(dialogBean);
        AppMethodBeat.o(15495);
    }

    @Override // cn.missfresh.unitepoplib.view.dialog.a
    public void b(DialogBean dialogBean) {
        AppMethodBeat.i(15498, false);
        a(dialogBean);
        AppMethodBeat.o(15498);
    }
}
