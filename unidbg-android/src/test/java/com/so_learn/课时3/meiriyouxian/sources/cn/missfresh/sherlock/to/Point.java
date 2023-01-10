package cn.missfresh.sherlock.to;

import java.io.Serializable;

public class Point implements Serializable {
    private static final long serialVersionUID = -7867568067769707298L;
    private String className;
    private long methodId;
    private String methodName;
    private long time;

    public String getClassName() {
        return this.className;
    }

    public long getMethodId() {
        return this.methodId;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public long getTime() {
        return this.time;
    }

    public void setClassName(String str) {
        this.className = str;
    }

    public void setMethodId(long j) {
        this.methodId = j;
    }

    public void setMethodName(String str) {
        this.methodName = str;
    }

    public void setTime(long j) {
        this.time = j;
    }
}
