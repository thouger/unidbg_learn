package cn.missfresh.module.base.manager.bean;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.lidroid.xutils.db.annotation.Table;

@Table(name = "subscribe_sku_bean")
public class SubscribeSKUBean {
    int id;
    long saveTime;
    String sku;

    public long getSaveTime() {
        return this.saveTime;
    }

    public void setSaveTime(long j) {
        this.saveTime = j;
    }

    public SubscribeSKUBean() {
    }

    public SubscribeSKUBean(String str) {
        this.sku = str;
    }

    public int hashCode() {
        int i = 0;
        AppMethodBeat.i(15053, false);
        String str = this.sku;
        if (str != null) {
            i = str.hashCode();
        }
        int i2 = MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE + i;
        AppMethodBeat.o(15053);
        return i2;
    }

    public boolean equals(Object obj) {
        AppMethodBeat.i(15054, false);
        if (obj != null) {
            boolean equals = this.sku.equals(obj.toString());
            AppMethodBeat.o(15054);
            return equals;
        }
        AppMethodBeat.o(15054);
        return false;
    }

    public void setSku(String str) {
        this.sku = str;
    }

    public String getSku() {
        return this.sku;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String toString() {
        return this.sku;
    }
}
