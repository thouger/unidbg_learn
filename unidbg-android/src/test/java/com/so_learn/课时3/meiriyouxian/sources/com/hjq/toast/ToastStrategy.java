package com.hjq.toast;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ToastStrategy extends Handler implements IToastStrategy {
    private static final int DELAY_TIMEOUT = 300;
    private static final int MAX_TOAST_CAPACITY = 3;
    private static final int TYPE_CANCEL = 3;
    private static final int TYPE_CONTINUE = 2;
    private static final int TYPE_SHOW = 1;
    private volatile boolean isShow;
    private volatile Queue<CharSequence> mQueue = getToastQueue();
    private Toast mToast;

    public ToastStrategy() {
        super(Looper.getMainLooper());
    }

    @Override // com.hjq.toast.IToastStrategy
    public void bind(Toast toast) {
        this.mToast = toast;
    }

    @Override // com.hjq.toast.IToastStrategy
    public void show(CharSequence charSequence) {
        if ((this.mQueue.isEmpty() || !this.mQueue.contains(charSequence)) && !this.mQueue.offer(charSequence)) {
            this.mQueue.poll();
            this.mQueue.offer(charSequence);
        }
        if (!this.isShow) {
            this.isShow = true;
            sendEmptyMessageDelayed(1, 300);
        }
    }

    @Override // com.hjq.toast.IToastStrategy
    public void cancel() {
        if (this.isShow) {
            this.isShow = false;
            sendEmptyMessage(3);
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            CharSequence peek = this.mQueue.peek();
            if (peek != null) {
                this.mToast.setText(peek);
                this.mToast.show();
                sendEmptyMessageDelayed(2, (long) (getToastDuration(peek) + 300));
                return;
            }
            this.isShow = false;
        } else if (i == 2) {
            this.mQueue.poll();
            if (!this.mQueue.isEmpty()) {
                sendEmptyMessage(1);
            } else {
                this.isShow = false;
            }
        } else if (i == 3) {
            this.isShow = false;
            this.mQueue.clear();
            this.mToast.cancel();
        }
    }

    public Queue<CharSequence> getToastQueue() {
        return new ArrayBlockingQueue(3);
    }

    public int getToastDuration(CharSequence charSequence) {
        if (charSequence.length() > 20) {
            return IToastStrategy.LONG_DURATION_TIMEOUT;
        }
        return 2000;
    }
}
