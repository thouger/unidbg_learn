package com.vivo.push.model;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SubscribeAppInfo {
    public static final int SUBSCIRBE = 1;
    public static final int SUBSCIRBE_CANCLE = 2;
    private int mActualStatus;
    private String mName;
    private int mTargetStatus;

    public SubscribeAppInfo(String str, int i, int i2) {
        this.mName = str;
        this.mTargetStatus = i;
        this.mActualStatus = i2;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public int getTargetStatus() {
        return this.mTargetStatus;
    }

    public void setTargetStatus(int i) {
        this.mTargetStatus = i;
    }

    public int getActualStatus() {
        return this.mActualStatus;
    }

    public void setActualStatus(int i) {
        this.mActualStatus = i;
    }

    public int hashCode() {
        int i = 0;
        AppMethodBeat.i(1978, false);
        String str = this.mName;
        if (str != null) {
            i = str.hashCode();
        }
        int i2 = ((i + 31) * 31) + this.mTargetStatus;
        AppMethodBeat.o(1978);
        return i2;
    }

    public boolean equals(Object obj) {
        AppMethodBeat.i(1982, false);
        if (this == obj) {
            AppMethodBeat.o(1982);
            return true;
        } else if (obj == null) {
            AppMethodBeat.o(1982);
            return false;
        } else if (getClass() != obj.getClass()) {
            AppMethodBeat.o(1982);
            return false;
        } else {
            SubscribeAppInfo subscribeAppInfo = (SubscribeAppInfo) obj;
            String str = this.mName;
            if (str == null) {
                if (subscribeAppInfo.mName != null) {
                    AppMethodBeat.o(1982);
                    return false;
                }
            } else if (!str.equals(subscribeAppInfo.mName)) {
                AppMethodBeat.o(1982);
                return false;
            }
            int i = this.mTargetStatus;
            int i2 = subscribeAppInfo.mTargetStatus;
            AppMethodBeat.o(1982);
            return i == i2;
        }
    }

    public String toString() {
        AppMethodBeat.i(1985, false);
        String str = "SubscribeAppInfo [mName=" + this.mName + ", mTargetStatus=" + this.mTargetStatus + ", mActualStatus=" + this.mActualStatus + "]";
        AppMethodBeat.o(1985);
        return str;
    }
}
