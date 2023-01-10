package cn.missfresh.buttomline.abtest.bean;

import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class Data {
    private int code;
    private List<Plan> data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public List<Plan> getData() {
        return this.data;
    }

    public void setData(List<Plan> list) {
        this.data = list;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        AppMethodBeat.i(7286, false);
        String str = "Data{code=" + this.code + ", message='" + this.message + DateFormat.QUOTE + ", data=" + this.data + '}';
        AppMethodBeat.o(7286);
        return str;
    }
}
