package cn.missfresh.sherlock.to;

import java.io.Serializable;

public class MethodTO extends CommonTO implements Serializable {
    private static final long serialVersionUID = 9094230483229137582L;
    private String className;
    private long costedMilles;
    private float cpu;
    private String methodMsg;
    private String methodName;

    public String getClassName() {
        return this.className;
    }

    public long getCostedMilles() {
        return this.costedMilles;
    }

    public float getCpu() {
        return this.cpu;
    }

    public String getMethodMsg() {
        return this.methodMsg;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setClassName(String str) {
        this.className = str;
    }

    public void setCostedMilles(long j) {
        this.costedMilles = j;
    }

    public void setCpu(float f) {
        this.cpu = f;
    }

    public void setMethodMsg(String str) {
        this.methodMsg = str;
    }

    public void setMethodName(String str) {
        this.methodName = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return " \n# SherlockMethod\n* className:\t" + this.className + "\n* methodName:\t" + this.methodName + "\n* costedMilles:\t" + this.costedMilles + "\n* methodMsg:\t" + this.methodMsg + "\n";
    }
}
