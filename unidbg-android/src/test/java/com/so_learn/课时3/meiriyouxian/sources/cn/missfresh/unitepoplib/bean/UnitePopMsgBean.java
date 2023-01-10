package cn.missfresh.unitepoplib.bean;

import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class UnitePopMsgBean {
    private DialogBean dialogBean;
    private String tag;
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public DialogBean getDialogBean() {
        return this.dialogBean;
    }

    public void setDialogBean(DialogBean dialogBean) {
        this.dialogBean = dialogBean;
    }

    public String toString() {
        AppMethodBeat.i(15414, false);
        String str = "UnitePopMsgBean{type=" + this.type + ", tag='" + this.tag + DateFormat.QUOTE + ", dialogBean=" + this.dialogBean + '}';
        AppMethodBeat.o(15414);
        return str;
    }
}
