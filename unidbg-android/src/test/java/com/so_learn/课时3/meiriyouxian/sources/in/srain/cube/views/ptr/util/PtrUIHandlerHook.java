package in.srain.cube.views.ptr.util;

public abstract class PtrUIHandlerHook implements Runnable {
    private static final byte STATUS_IN_HOOK = 1;
    private static final byte STATUS_PREPARE = 0;
    private static final byte STATUS_RESUMED = 2;
    private Runnable mResumeAction;
    private byte mStatus = 0;

    public void takeOver() {
        takeOver(null);
    }

    public void takeOver(Runnable runnable) {
        if (runnable != null) {
            this.mResumeAction = runnable;
        }
        byte b = this.mStatus;
        if (b == 0) {
            this.mStatus = 1;
            run();
        } else if (b != 1 && b == 2) {
            resume();
        }
    }

    public void reset() {
        this.mStatus = 0;
    }

    public void resume() {
        Runnable runnable = this.mResumeAction;
        if (runnable != null) {
            runnable.run();
        }
        this.mStatus = 2;
    }

    public void setResumeAction(Runnable runnable) {
        this.mResumeAction = runnable;
    }
}
