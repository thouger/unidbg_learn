package cn.missfresh.unitepoplib.bean;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DispatcherBean {
    private boolean blockingUp;
    private ArrayList<DialogBean> dialogQueue;

    private boolean isQueueEmpty() {
        AppMethodBeat.i(15337, false);
        ArrayList<DialogBean> arrayList = this.dialogQueue;
        if (arrayList == null || arrayList.isEmpty()) {
            AppMethodBeat.o(15337);
            return true;
        }
        AppMethodBeat.o(15337);
        return false;
    }

    private DialogBean pop() {
        AppMethodBeat.i(15340, false);
        ArrayList<DialogBean> arrayList = this.dialogQueue;
        if (arrayList == null || arrayList.isEmpty()) {
            AppMethodBeat.o(15340);
            return null;
        }
        DialogBean dialogBean = this.dialogQueue.get(0);
        AppMethodBeat.o(15340);
        return dialogBean;
    }

    private DialogBean pop(String str) {
        AppMethodBeat.i(15342, false);
        ArrayList<DialogBean> arrayList = this.dialogQueue;
        if (arrayList == null || arrayList.isEmpty() || TextUtils.isEmpty(str)) {
            AppMethodBeat.o(15342);
            return null;
        }
        for (int i = 0; i < this.dialogQueue.size(); i++) {
            if (str.equalsIgnoreCase(this.dialogQueue.get(i).getTemplateId())) {
                DialogBean dialogBean = this.dialogQueue.get(i);
                AppMethodBeat.o(15342);
                return dialogBean;
            }
        }
        AppMethodBeat.o(15342);
        return null;
    }

    public boolean insert(DialogBean dialogBean) {
        AppMethodBeat.i(15343, false);
        if (dialogBean == null) {
            AppMethodBeat.o(15343);
            return false;
        }
        if (this.dialogQueue == null) {
            this.dialogQueue = new ArrayList<>();
        }
        if (this.dialogQueue.isEmpty()) {
            this.dialogQueue.add(dialogBean);
            AppMethodBeat.o(15343);
            return true;
        } else if (!dialogBean.isHigherTag()) {
            this.dialogQueue.add(dialogBean);
            AppMethodBeat.o(15343);
            return true;
        } else {
            if (this.dialogQueue.get(0).isShowing()) {
                this.dialogQueue.add(1, dialogBean);
            } else {
                this.dialogQueue.add(0, dialogBean);
            }
            AppMethodBeat.o(15343);
            return true;
        }
    }

    public boolean insertFirst(DialogBean dialogBean) {
        AppMethodBeat.i(15344, false);
        if (dialogBean == null) {
            AppMethodBeat.o(15344);
            return false;
        }
        if (this.dialogQueue == null) {
            this.dialogQueue = new ArrayList<>();
        }
        this.dialogQueue.add(0, dialogBean);
        AppMethodBeat.o(15344);
        return true;
    }

    public boolean insertAll(List<DialogBean> list) {
        AppMethodBeat.i(15346, false);
        if (list == null) {
            AppMethodBeat.o(15346);
            return false;
        }
        if (this.dialogQueue == null) {
            this.dialogQueue = new ArrayList<>();
        }
        this.dialogQueue.addAll(list);
        AppMethodBeat.o(15346);
        return true;
    }

    public boolean update(DialogBean dialogBean) {
        int i = 0;
        AppMethodBeat.i(15349, false);
        if (dialogBean == null) {
            AppMethodBeat.o(15349);
            return false;
        }
        if (this.dialogQueue == null) {
            this.dialogQueue = new ArrayList<>();
        }
        while (true) {
            if (i >= this.dialogQueue.size()) {
                break;
            } else if (this.dialogQueue.get(i).getTemplateId() == dialogBean.getTemplateId()) {
                this.dialogQueue.remove(i);
                this.dialogQueue.add(i, dialogBean);
                break;
            } else {
                if (i == this.dialogQueue.size() - 1) {
                    this.dialogQueue.add(dialogBean);
                }
                i++;
            }
        }
        AppMethodBeat.o(15349);
        return true;
    }

    public boolean remove(DialogBean dialogBean) {
        AppMethodBeat.i(15351, false);
        if (dialogBean == null) {
            AppMethodBeat.o(15351);
            return false;
        }
        ArrayList<DialogBean> arrayList = this.dialogQueue;
        if (arrayList == null) {
            AppMethodBeat.o(15351);
            return true;
        }
        boolean remove = arrayList.remove(dialogBean);
        AppMethodBeat.o(15351);
        return remove;
    }

    public void clearQueue() {
        AppMethodBeat.i(15352, false);
        ArrayList<DialogBean> arrayList = this.dialogQueue;
        if (arrayList != null) {
            arrayList.clear();
        }
        AppMethodBeat.o(15352);
    }

    public boolean isBlockingUp() {
        return this.blockingUp;
    }

    public void setBlockingUp(boolean z) {
        this.blockingUp = z;
    }

    public ArrayList<DialogBean> getDialogQueue() {
        return this.dialogQueue;
    }

    public void setDialogQueue(ArrayList<DialogBean> arrayList) {
        this.dialogQueue = arrayList;
    }

    public DialogBean removeFirst() {
        AppMethodBeat.i(15358, false);
        ArrayList<DialogBean> arrayList = this.dialogQueue;
        if (arrayList == null) {
            AppMethodBeat.o(15358);
            return null;
        } else if (arrayList.isEmpty()) {
            AppMethodBeat.o(15358);
            return null;
        } else if (this.dialogQueue.get(0).isShowing()) {
            DialogBean remove = this.dialogQueue.remove(0);
            AppMethodBeat.o(15358);
            return remove;
        } else {
            AppMethodBeat.o(15358);
            return null;
        }
    }

    public DialogBean getDialogBeanByTemplateId(String str) {
        AppMethodBeat.i(15359, false);
        DialogBean dialogBean = null;
        if (this.dialogQueue.isEmpty() || TextUtils.isEmpty(str)) {
            AppMethodBeat.o(15359);
            return null;
        }
        Iterator<DialogBean> it2 = this.dialogQueue.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            DialogBean next = it2.next();
            if (str.equalsIgnoreCase(next.getTemplateId())) {
                dialogBean = next;
                break;
            }
        }
        AppMethodBeat.o(15359);
        return dialogBean;
    }

    public String toString() {
        AppMethodBeat.i(15360, false);
        String str = "DispatcherBean{blockingUp=" + this.blockingUp + ", dialogQueue=" + this.dialogQueue + '}';
        AppMethodBeat.o(15360);
        return str;
    }
}
