package cn.missfresh.sherlock.to;

import java.io.Serializable;
import java.util.List;

public class CrashTO extends CommonTO implements Serializable {
    public static final int FLUTTER_TYPE = 2;
    public static final int JAVA_TYPE = 0;
    public static final int NATIVE_TYPE = 1;
    private static final long serialVersionUID = 1422221235109483723L;
    private String backtrace;
    private String buildId;
    private String cpuRate;
    private String crashTime;
    private int crashType;
    private String exception;
    private String exceptionInfo;
    private String exceptionMD5;
    private String extend;
    private int isNativeCrash;
    private String memoryInfo;
    private String memoryRate;
    private String openFiles;
    private String pid;
    private List<Point> points;
    private String processName;
    private String signal;
    private String stack;
    private String systemStack;
    private String threadName;
    private String tid;

    public String getBacktrace() {
        return this.backtrace;
    }

    public String getBuildId() {
        return this.buildId;
    }

    public String getCpuRate() {
        return this.cpuRate;
    }

    public String getCrashTime() {
        return this.crashTime;
    }

    public int getCrashType() {
        return this.crashType;
    }

    public String getException() {
        return this.exception;
    }

    public String getExceptionInfo() {
        return this.exceptionInfo;
    }

    public String getExceptionMD5() {
        return this.exceptionMD5;
    }

    public String getExtend() {
        return this.extend;
    }

    public String getMemoryInfo() {
        return this.memoryInfo;
    }

    public String getMemoryRate() {
        return this.memoryRate;
    }

    public String getOpenFiles() {
        return this.openFiles;
    }

    public String getPid() {
        return this.pid;
    }

    public List<Point> getPoints() {
        return this.points;
    }

    public String getProcessName() {
        return this.processName;
    }

    public String getSignal() {
        return this.signal;
    }

    public String getStack() {
        return this.stack;
    }

    public String getSystemStack() {
        return this.systemStack;
    }

    public String getThreadName() {
        return this.threadName;
    }

    public String getTid() {
        return this.tid;
    }

    public int isNativeCrash() {
        return this.isNativeCrash;
    }

    public void setBacktrace(String str) {
        this.backtrace = str;
    }

    public void setBuildId(String str) {
        this.buildId = str;
    }

    public void setCpuRate(String str) {
        this.cpuRate = str;
    }

    public void setCrashTime(String str) {
        this.crashTime = str;
    }

    public void setCrashType(int i) {
        this.crashType = i;
    }

    public void setException(String str) {
        this.exception = str;
    }

    public void setExceptionInfo(String str) {
        this.exceptionInfo = str;
    }

    public void setExceptionMD5(String str) {
        this.exceptionMD5 = str;
    }

    public void setExtend(String str) {
        this.extend = str;
    }

    public void setMemoryInfo(String str) {
        this.memoryInfo = str;
    }

    public void setMemoryRate(String str) {
        this.memoryRate = str;
    }

    public void setNativeCrash(int i) {
        this.isNativeCrash = i;
    }

    public void setOpenFiles(String str) {
        this.openFiles = str;
    }

    public void setPid(String str) {
        this.pid = str;
    }

    public void setPoints(List<Point> list) {
        this.points = list;
    }

    public void setProcessName(String str) {
        this.processName = str;
    }

    public void setSignal(String str) {
        this.signal = str;
    }

    public void setStack(String str) {
        this.stack = str;
    }

    public void setSystemStack(String str) {
        this.systemStack = str;
    }

    public void setThreadName(String str) {
        this.threadName = str;
    }

    public void setTid(String str) {
        this.tid = str;
    }
}
