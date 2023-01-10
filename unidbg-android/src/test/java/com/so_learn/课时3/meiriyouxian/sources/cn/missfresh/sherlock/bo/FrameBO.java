package cn.missfresh.sherlock.bo;

public class FrameBO {
    private long animationTime;
    private long inputTime;
    private long traversalTime;

    public long getAnimationTime() {
        return this.animationTime;
    }

    public long getInputTime() {
        return this.inputTime;
    }

    public long getTraversalTime() {
        return this.traversalTime;
    }

    public void setAnimationTime(long j) {
        this.animationTime = j;
    }

    public void setInputTime(long j) {
        this.inputTime = j;
    }

    public void setTraversalTime(long j) {
        this.traversalTime = j;
    }
}
