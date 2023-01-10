package cn.missfresh.unitepoplib.bean;

import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.unitepoplib.factory.MoudleType;
import java.io.Serializable;

public class DialogBean implements Serializable {
    protected boolean autoDismiss;
    protected boolean blockingUp;
    protected boolean cancelable;
    protected int layoutId;
    protected String moudleName;
    protected String popStyle;
    protected String priority;
    protected int status;
    protected int styleType;
    protected String templateId;

    public DialogBean templateId(String str) {
        this.templateId = str;
        return this;
    }

    public DialogBean styleType(int i) {
        this.styleType = i;
        return this;
    }

    public DialogBean status(int i) {
        this.status = i;
        return this;
    }

    public DialogBean blockingUp(boolean z) {
        this.blockingUp = z;
        return this;
    }

    public DialogBean priority(String str) {
        this.priority = str;
        return this;
    }

    public DialogBean moudleName(String str) {
        this.moudleName = str;
        return this;
    }

    public DialogBean popStyle(String str) {
        this.popStyle = str;
        return this;
    }

    public DialogBean layoutId(int i) {
        this.layoutId = i;
        return this;
    }

    public DialogBean cancelable(boolean z) {
        this.cancelable = z;
        return this;
    }

    public DialogBean autoDismiss(boolean z) {
        this.autoDismiss = z;
        return this;
    }

    public String getMoudleName() {
        return this.moudleName;
    }

    public String getTemplateId() {
        return this.templateId;
    }

    public int getStyleType() {
        return this.styleType;
    }

    public String getPriority() {
        return this.priority;
    }

    public String getPop_style() {
        return this.popStyle;
    }

    public boolean isBlockingUp() {
        return this.blockingUp;
    }

    public boolean isReady() {
        return this.status == 1;
    }

    public boolean isShowing() {
        return this.status == 2;
    }

    public boolean ignore() {
        return this.status == 3;
    }

    public boolean isHigherTag() {
        AppMethodBeat.i(15324, false);
        boolean equalsIgnoreCase = "higher".equalsIgnoreCase(this.priority);
        AppMethodBeat.o(15324);
        return equalsIgnoreCase;
    }

    public int getLayoutId() {
        return this.layoutId;
    }

    public boolean isCancelable() {
        return this.cancelable;
    }

    public boolean isAutoDismiss() {
        return this.autoDismiss;
    }

    public DialogBean() {
        AppMethodBeat.i(15330, false);
        this.layoutId = -1;
        this.autoDismiss = true;
        this.styleType = MoudleType.CUSTOM_DIALOG.getType();
        this.status = 0;
        this.layoutId = -1;
        AppMethodBeat.o(15330);
    }

    @Override // java.lang.Object
    public String toString() {
        AppMethodBeat.i(15332, false);
        String str = "DialogBean{templateId='" + this.templateId + DateFormat.QUOTE + ", styleType=" + this.styleType + ", status=" + this.status + '}';
        AppMethodBeat.o(15332);
        return str;
    }
}
