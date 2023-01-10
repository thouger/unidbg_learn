package cn.missfresh.unitepoplib.listener;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.unitepoplib.bean.DialogBean;
import cn.missfresh.unitepoplib.bean.UnitePopMsgBean;
import java.util.HashMap;

/* compiled from: UnitePopModel */
public class b {
    private HashMap<String, UniteDialogEventListener<UnitePopMsgBean>> a;

    private b() {
        AppMethodBeat.i(15533, false);
        this.a = new HashMap<>();
        AppMethodBeat.o(15533);
    }

    public static b a() {
        AppMethodBeat.i(15537, false);
        b bVar = a.a;
        AppMethodBeat.o(15537);
        return bVar;
    }

    /* compiled from: UnitePopModel */
    private static class a {
        private static b a = new b();

        static {
            AppMethodBeat.i(15529, false);
            AppMethodBeat.o(15529);
        }
    }

    public void a(String str, UniteDialogEventListener uniteDialogEventListener) {
        AppMethodBeat.i(15540, false);
        if (uniteDialogEventListener == null) {
            AppMethodBeat.o(15540);
            return;
        }
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "registerDialogListener===>" + uniteDialogEventListener.toString());
        this.a.put(str, uniteDialogEventListener);
        AppMethodBeat.o(15540);
    }

    public void a(UniteDialogEventListener<UnitePopMsgBean> uniteDialogEventListener) {
        AppMethodBeat.i(15542, false);
        if (uniteDialogEventListener == null) {
            AppMethodBeat.o(15542);
            return;
        }
        this.a.remove(uniteDialogEventListener);
        AppMethodBeat.o(15542);
    }

    public void a(int i, String str, DialogBean dialogBean) {
        AppMethodBeat.i(15545, false);
        if (TextUtils.isEmpty(str) || dialogBean == null) {
            AppMethodBeat.o(15545);
        } else if (!this.a.containsKey(str)) {
            AppMethodBeat.o(15545);
        } else {
            UnitePopMsgBean unitePopMsgBean = new UnitePopMsgBean();
            unitePopMsgBean.setTag(str);
            unitePopMsgBean.setType(i);
            unitePopMsgBean.setDialogBean(dialogBean);
            a(unitePopMsgBean);
            AppMethodBeat.o(15545);
        }
    }

    private void a(UnitePopMsgBean unitePopMsgBean) {
        AppMethodBeat.i(15548, false);
        if (unitePopMsgBean == null || this.a == null || TextUtils.isEmpty(unitePopMsgBean.getTag())) {
            AppMethodBeat.o(15548);
        } else if (!this.a.containsKey(unitePopMsgBean.getTag())) {
            AppMethodBeat.o(15548);
        } else {
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "mUnitePopMsgListeners.size() == " + this.a.size() + " unitePopMsgBean==" + unitePopMsgBean);
            this.a.get(unitePopMsgBean.getTag()).onChanged(unitePopMsgBean);
            AppMethodBeat.o(15548);
        }
    }
}
