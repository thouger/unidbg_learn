package io.reactivex.observers;

import io.reactivex.disposables.b;
import io.reactivex.observers.BaseTestConsumer;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public abstract class BaseTestConsumer<T, U extends BaseTestConsumer<T, U>> implements b {
    protected final CountDownLatch a;
    protected final List<T> b;
    protected final List<Throwable> c;
    protected long d;
    protected Thread e;
    protected boolean f;
    protected int g;
    protected int h;

    public enum TestWaitStrategy implements Runnable {
        SPIN {
            @Override // io.reactivex.observers.BaseTestConsumer.TestWaitStrategy, java.lang.Runnable
            public void run() {
            }
        },
        YIELD {
            @Override // io.reactivex.observers.BaseTestConsumer.TestWaitStrategy, java.lang.Runnable
            public void run() {
                Thread.yield();
            }
        },
        SLEEP_1MS {
            @Override // io.reactivex.observers.BaseTestConsumer.TestWaitStrategy, java.lang.Runnable
            public void run() {
                sleep(1);
            }
        },
        SLEEP_10MS {
            @Override // io.reactivex.observers.BaseTestConsumer.TestWaitStrategy, java.lang.Runnable
            public void run() {
                sleep(10);
            }
        },
        SLEEP_100MS {
            @Override // io.reactivex.observers.BaseTestConsumer.TestWaitStrategy, java.lang.Runnable
            public void run() {
                sleep(100);
            }
        },
        SLEEP_1000MS {
            @Override // io.reactivex.observers.BaseTestConsumer.TestWaitStrategy, java.lang.Runnable
            public void run() {
                sleep(1000);
            }
        };

        @Override // java.lang.Runnable
        public abstract void run();

        static void sleep(int i) {
            try {
                Thread.sleep((long) i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
