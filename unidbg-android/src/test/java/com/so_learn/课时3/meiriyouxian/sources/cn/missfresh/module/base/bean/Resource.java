package cn.missfresh.module.base.bean;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Resource<T> {
    public static final int CUSTOM = 5;
    public static final int EMPTY = 2;
    public static final int ERROR = 1;
    public static final int LOADING = 3;
    public static final int LOGIN = 4;
    public static final int NOMORE = 6;
    public static final int SUCCESS = 0;
    private int mCode;
    private T mData;
    private String mMsg;
    private int mStatus;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }

    public int getStatus() {
        return this.mStatus;
    }

    public T getData() {
        return this.mData;
    }

    public int getCode() {
        return this.mCode;
    }

    public String getMsg() {
        return this.mMsg;
    }

    public Resource(int i, T t, int i2, String str) {
        this.mStatus = i;
        this.mData = t;
        this.mCode = i2;
        this.mMsg = str;
    }

    public static <T> Resource success(T t) {
        AppMethodBeat.i(6832, false);
        Resource resource = new Resource(0, t, 0, "");
        AppMethodBeat.o(6832);
        return resource;
    }

    public static <T> Resource error(int i, String str) {
        AppMethodBeat.i(6836, false);
        Resource resource = new Resource(1, null, i, str);
        AppMethodBeat.o(6836);
        return resource;
    }

    public static <T> Resource loading(String str) {
        AppMethodBeat.i(6841, false);
        Resource resource = new Resource(3, null, 0, str);
        AppMethodBeat.o(6841);
        return resource;
    }

    public static <T> Resource empty(String str) {
        AppMethodBeat.i(6844, false);
        Resource resource = new Resource(2, null, 0, str);
        AppMethodBeat.o(6844);
        return resource;
    }

    public static <T> Resource login(T t) {
        AppMethodBeat.i(6847, false);
        Resource resource = new Resource(4, t, 400, "");
        AppMethodBeat.o(6847);
        return resource;
    }

    public static <T> Resource custom(T t) {
        AppMethodBeat.i(6850, false);
        Resource resource = new Resource(5, t, 0, "");
        AppMethodBeat.o(6850);
        return resource;
    }

    public static <T> Resource noMore(T t) {
        AppMethodBeat.i(6852, false);
        Resource resource = new Resource(6, t, 0, "");
        AppMethodBeat.o(6852);
        return resource;
    }
}
