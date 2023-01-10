package cn.missfresh.basiclib.net.a;

/* compiled from: ResultCallback */
public interface a<T> {
    void onComplete();

    void onFail(int i, String str);

    void onSuccess(T t);
}
