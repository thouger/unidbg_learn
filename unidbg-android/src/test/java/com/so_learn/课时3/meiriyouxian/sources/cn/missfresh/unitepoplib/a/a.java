package cn.missfresh.unitepoplib.a;

import android.app.Activity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.unitepoplib.bean.DialogBean;
import cn.missfresh.unitepoplib.bean.DispatcherBean;
import cn.missfresh.unitepoplib.factory.MoudleType;
import cn.missfresh.unitepoplib.listener.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: DialogDispatcher */
public class a {
    private static a e;
    private HashMap<String, DispatcherBean> a;
    private String b = "";
    private String c = "";
    private Activity d;

    private a() {
    }

    public static a a() {
        AppMethodBeat.i(15429, false);
        if (e == null) {
            e = new a();
        }
        a aVar = e;
        AppMethodBeat.o(15429);
        return aVar;
    }

    public void a(String str, DialogBean dialogBean) {
        AppMethodBeat.i(15432, false);
        StringBuilder sb = new StringBuilder();
        sb.append("insertDialog--start  ===>  ");
        sb.append(dialogBean == null ? "\u7a7a" : dialogBean.toString());
        sb.append(" tag===");
        sb.append(str);
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", sb.toString());
        e(str).insert(dialogBean);
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "insertDialog--end");
        c();
        AppMethodBeat.o(15432);
    }

    public void a(String str, List<DialogBean> list) {
        AppMethodBeat.i(15434, false);
        e(str).insertAll(list);
        c();
        AppMethodBeat.o(15434);
    }

    private HashMap<String, DispatcherBean> e() {
        AppMethodBeat.i(15437, false);
        if (this.a == null) {
            this.a = new HashMap<>();
        }
        HashMap<String, DispatcherBean> hashMap = this.a;
        AppMethodBeat.o(15437);
        return hashMap;
    }

    private void c(Activity activity) {
        AppMethodBeat.i(15441, false);
        if (activity == null) {
            AppMethodBeat.o(15441);
            return;
        }
        String name = activity.getClass().getName();
        Set<String> keySet = e().keySet();
        ArrayList arrayList = new ArrayList();
        for (String str : keySet) {
            if (str.startsWith(name)) {
                arrayList.add(str);
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            e().remove(arrayList.get(i));
        }
        AppMethodBeat.o(15441);
    }

    private DispatcherBean e(String str) {
        AppMethodBeat.i(15444, false);
        DispatcherBean dispatcherBean = e().get(str);
        if (dispatcherBean == null) {
            dispatcherBean = new DispatcherBean();
            e().put(str, dispatcherBean);
        }
        AppMethodBeat.o(15444);
        return dispatcherBean;
    }

    public String b() {
        AppMethodBeat.i(15446, false);
        String str = this.c + this.b;
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "getCurrentKey() ===> " + str);
        AppMethodBeat.o(15446);
        return str;
    }

    public void c() {
        AppMethodBeat.i(15450, false);
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "in dispatcherQueue method ===> start");
        if (this.a == null) {
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "in dispatcherQueue method ===> mDispatcherTarget is null\u3002");
            AppMethodBeat.o(15450);
            return;
        }
        String b = b();
        DispatcherBean dispatcherBean = this.a.get(b);
        if (dispatcherBean == null) {
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "in dispatcherQueue method ===> currentLiveKey's DispatcherBean is empty\u3002 getCurrentKey()==" + b);
            AppMethodBeat.o(15450);
            return;
        }
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "in dispatcherQueue method ===> dispatcherBean === " + dispatcherBean.toString());
        ArrayList<DialogBean> dialogQueue = dispatcherBean.getDialogQueue();
        if (dialogQueue == null || dialogQueue.size() == 0) {
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "in dispatcherQueue method ===> currentLiveKey's queue is empty\u3002");
            AppMethodBeat.o(15450);
            return;
        }
        DialogBean dialogBean = dialogQueue.get(0);
        if (dialogBean == null) {
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "in dispatcherQueue method ===> topdialog is empty\u3002");
            AppMethodBeat.o(15450);
        } else if (dialogBean.isShowing()) {
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "in dispatcherQueue method ===> topdialog is showing\u3002");
            AppMethodBeat.o(15450);
        } else {
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "topDialog == " + dialogBean.toString());
            if (dialogBean.ignore()) {
                cn.missfresh.unitepoplib.b.a.a("dialogSDk", "in dispatcherQueue method ===> topDialog is ignore");
                dispatcherBean.remove(dialogBean);
                c();
                AppMethodBeat.o(15450);
            } else if (!dialogBean.isReady()) {
                cn.missfresh.unitepoplib.b.a.a("dialogSDk", "in dispatcherQueue method ===> topDialog is not ready");
                AppMethodBeat.o(15450);
            } else {
                if (dialogBean.isReady()) {
                    cn.missfresh.unitepoplib.b.a.a("dialogSDk", "in dispatcherQueue method ===> topdialog show\u3002" + dialogBean);
                    dialogBean.status(2);
                    if (dialogBean.getStyleType() == MoudleType.CUSTOM_DIALOG.getType()) {
                        b.a().a(1005, b, dialogBean);
                    } else {
                        cn.missfresh.unitepoplib.factory.a.a().a(b, dialogBean);
                    }
                    cn.missfresh.unitepoplib.b.a.a("dialogSDk", "in dispatcherQueue method ===> after topdialog show\u3002" + dialogBean);
                }
                AppMethodBeat.o(15450);
            }
        }
    }

    public void a(String str) {
        AppMethodBeat.i(15454, false);
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "setCurrentFragmentTag--start");
        String b = b();
        DispatcherBean dispatcherBean = e().get(b);
        if (!(dispatcherBean == null || dispatcherBean.getDialogQueue() == null || dispatcherBean.getDialogQueue().size() <= 0)) {
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "setCurrentFragmentTag--queue is not empty");
            DialogBean dialogBean = dispatcherBean.getDialogQueue().get(0);
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "\r\nsetCurrentFragmentTag--\u4e0a\u4e00\u4e2atag==" + b + "\r\n\u5bf9\u5e94\u7684\u961f\u5217" + dispatcherBean.getDialogQueue() + " \r\n\u5bf9\u5e94\u7684\u7b2c\u4e00\u4e2a\u5f39\u6846" + dialogBean);
            if (dialogBean.isShowing()) {
                dialogBean.status(1);
                cn.missfresh.unitepoplib.b.a.a("dialogSDk", "setCurrentFragmentTag--reset fragmentTag");
                if (dialogBean.getStyleType() != MoudleType.CUSTOM_DIALOG.getType()) {
                    cn.missfresh.unitepoplib.b.a.a("dialogSDk", "setCurrentFragmentTag--do dismissCurrentDialog");
                    cn.missfresh.unitepoplib.factory.a.a().a(dialogBean);
                } else {
                    this.b = str;
                    b.a().a(1006, b, dialogBean);
                }
            }
        }
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "setCurrentFragmentTag--reset fragmentTag");
        this.b = str;
        c();
        AppMethodBeat.o(15454);
    }

    private DialogBean f(String str) {
        AppMethodBeat.i(15458, false);
        DispatcherBean dispatcherBean = this.a.get(str);
        if (dispatcherBean == null) {
            AppMethodBeat.o(15458);
            return null;
        }
        DialogBean removeFirst = dispatcherBean.removeFirst();
        AppMethodBeat.o(15458);
        return removeFirst;
    }

    public void a(Activity activity) {
        AppMethodBeat.i(15461, false);
        c(activity);
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "DislogDispatcher.onActivityDestroyed ===> \r\ncurrentActTag ==" + this.c + "\r\ncurrentAct == " + this.d + "\r\ngetDispatcherTarget()==" + e());
        AppMethodBeat.o(15461);
    }

    public void b(Activity activity) {
        AppMethodBeat.i(15463, false);
        a("");
        this.c = activity.getClass().getName();
        this.d = activity;
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "DislogDispatcher.onActivityResumed ===> \r\ncurrentActTag ==" + this.c + "\r\ncurrentAct == " + this.d + "\r\ngetDispatcherTarget()==" + e());
        c();
        AppMethodBeat.o(15463);
    }

    public void b(String str) {
        AppMethodBeat.i(15465, false);
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "showNextDialog--start");
        DialogBean f = f(str);
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "showNextDialog--dismissCurrentDialog");
        cn.missfresh.unitepoplib.factory.a.a().a(f);
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "showNextDialog--end");
        c();
        AppMethodBeat.o(15465);
    }

    public void b(String str, DialogBean dialogBean) {
        AppMethodBeat.i(15468, false);
        StringBuilder sb = new StringBuilder();
        sb.append("updateQueue--start  ===>  ");
        sb.append(dialogBean == null ? "\u7a7a" : dialogBean.toString());
        sb.append(" tag===");
        sb.append(str);
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", sb.toString());
        DispatcherBean dispatcherBean = e().get(str);
        if (dispatcherBean == null || dispatcherBean.getDialogQueue().isEmpty()) {
            AppMethodBeat.o(15468);
            return;
        }
        dispatcherBean.update(dialogBean);
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "updateQueue--end");
        c();
        AppMethodBeat.o(15468);
    }

    public void a(String str, int i) {
        DialogBean dialogBeanByTemplateId;
        AppMethodBeat.i(15472, false);
        String str2 = i == 1 ? "\u51c6\u5907\u5c31\u7eea" : i == 3 ? "\u4e0d\u9700\u8981\u4e86" : i == 2 ? "\u5c55\u793a\u4e86" : "\u51c6\u5907\u4e2d";
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "updateDialogByTemplateId--start " + str2 + " tempplateId === " + str);
        if (e().isEmpty()) {
            AppMethodBeat.o(15472);
            return;
        }
        Iterator<String> it2 = e().keySet().iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            DispatcherBean dispatcherBean = e().get(it2.next());
            if (dispatcherBean != null && !dispatcherBean.getDialogQueue().isEmpty() && (dialogBeanByTemplateId = dispatcherBean.getDialogBeanByTemplateId(str)) != null) {
                if (dialogBeanByTemplateId.isShowing()) {
                    cn.missfresh.unitepoplib.b.a.a("dialogSDk", "updateDialogByTemplateId--dissmissCurrentDialog");
                    cn.missfresh.unitepoplib.factory.a.a().a(dialogBeanByTemplateId);
                }
                dialogBeanByTemplateId.status(i);
            }
        }
        cn.missfresh.unitepoplib.b.a.a("dialogSDk", "updateDialogByTemplateId--end");
        c();
        AppMethodBeat.o(15472);
    }

    public Activity d() {
        return this.d;
    }

    public DialogBean c(String str) {
        int i = 0;
        AppMethodBeat.i(15478, false);
        DispatcherBean dispatcherBean = e().get(str);
        if (dispatcherBean == null || dispatcherBean.getDialogQueue() == null) {
            AppMethodBeat.o(15478);
            return null;
        }
        ArrayList<DialogBean> dialogQueue = dispatcherBean.getDialogQueue();
        while (true) {
            if (i >= dialogQueue.size()) {
                i = -1;
                break;
            } else if (dialogQueue.get(i).isShowing()) {
                break;
            } else {
                i++;
            }
        }
        DialogBean dialogBean = dialogQueue.get(i);
        AppMethodBeat.o(15478);
        return dialogBean;
    }

    public boolean d(String str) {
        AppMethodBeat.i(15481, false);
        DispatcherBean dispatcherBean = e().get(str);
        if (dispatcherBean == null || dispatcherBean.getDialogQueue() == null) {
            AppMethodBeat.o(15481);
            return false;
        }
        AppMethodBeat.o(15481);
        return true;
    }
}
