package cn.missfresh.sherlock.to;

import java.io.Serializable;

public class TraceTO extends CommonTO implements Serializable {
    private static final long serialVersionUID = 6890464089729255319L;
    private String activity;
    private String dumpStack;
    private long elapsedTime;
    private int isAnr;
    private String scene;
    private String stack;
    private String stackKey;
    private int stackSize;
    private int threadBlock;

    public String getActivity() {
        return this.activity;
    }

    public String getDumpStack() {
        return this.dumpStack;
    }

    public long getElapsedTime() {
        return this.elapsedTime;
    }

    public String getScene() {
        return this.scene;
    }

    public String getStack() {
        return this.stack;
    }

    public String getStackKey() {
        return this.stackKey;
    }

    public int getStackSize() {
        return this.stackSize;
    }

    public int getThreadBlock() {
        return this.threadBlock;
    }

    public int isAnr() {
        return this.isAnr;
    }

    public void setActivity(String str) {
        this.activity = str;
    }

    public void setAnr(int i) {
        this.isAnr = i;
    }

    public void setDumpStack(String str) {
        this.dumpStack = str;
    }

    public void setElapsedTime(long j) {
        this.elapsedTime = j;
    }

    public void setScene(String str) {
        this.scene = str;
    }

    public void setStack(String str) {
        this.stack = str;
    }

    public void setStackKey(String str) {
        this.stackKey = str;
    }

    public void setStackSize(int i) {
        this.stackSize = i;
    }

    public void setThreadBlock(int i) {
        this.threadBlock = i;
    }
}
