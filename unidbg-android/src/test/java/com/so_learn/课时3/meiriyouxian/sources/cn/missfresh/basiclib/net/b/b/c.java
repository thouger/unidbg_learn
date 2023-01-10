package cn.missfresh.basiclib.net.b.b;

import cn.missfresh.basiclib.net.b.c.a;
import cn.missfresh.basiclib.net.error.ApiException;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;

/* compiled from: BasicFastJsonResponseBodyConvert */
public class c<T> extends cn.missfresh.basiclib.net.b.a.c<T> {
    protected Type a;

    public /* synthetic */ Object convert(Object obj) throws IOException {
        AppMethodBeat.i(3663, false);
        Object a = a((ResponseBody) obj);
        AppMethodBeat.o(3663);
        return a;
    }

    public c(Type type) {
        this.a = type;
    }

    public T a(ResponseBody responseBody) throws IOException {
        AppMethodBeat.i(3650, false);
        T t = (T) a(responseBody.string());
        AppMethodBeat.o(3650);
        return t;
    }

    /* access modifiers changed from: protected */
    public T a(String str) {
        AppMethodBeat.i(3655, false);
        if (this.a == String.class) {
            T t = (T) a((c<T>) str);
            AppMethodBeat.o(3655);
            return t;
        }
        a d = cn.missfresh.basiclib.net.a.a().d();
        T t2 = (T) a((c<T>) JSON.parseObject(str, this.a, d.g(), d.j(), d.h(), d.i()));
        AppMethodBeat.o(3655);
        return t2;
    }

    private T a(T t) {
        AppMethodBeat.i(3659, false);
        if (t == null) {
            Type type = this.a;
            if (type instanceof Class) {
                try {
                    T t2 = (T) ((Class) type).newInstance();
                    AppMethodBeat.o(3659);
                    return t2;
                } catch (Exception unused) {
                    ApiException apiException = new ApiException(-100, "object is null");
                    AppMethodBeat.o(3659);
                    throw apiException;
                }
            } else {
                ApiException apiException2 = new ApiException(-100, "object is null");
                AppMethodBeat.o(3659);
                throw apiException2;
            }
        } else {
            AppMethodBeat.o(3659);
            return t;
        }
    }
}
