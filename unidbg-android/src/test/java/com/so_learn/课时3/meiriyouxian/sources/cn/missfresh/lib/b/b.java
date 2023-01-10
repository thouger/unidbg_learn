package cn.missfresh.lib.b;

import cn.missfresh.basiclib.net.b.b.c;
import cn.missfresh.basiclib.net.error.ApiException;
import cn.missfresh.lib.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.accs.common.Constants;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;

/* compiled from: MFFastJsonResponseBodyConvert */
public final class b<T> extends c<T> {
    private String b = "";
    private String c = "";
    private String d = "";

    @Override // cn.missfresh.basiclib.net.b.b.c
    public /* synthetic */ Object convert(Object obj) throws IOException {
        AppMethodBeat.i(10990, false);
        Object a = a((ResponseBody) obj);
        AppMethodBeat.o(10990);
        return a;
    }

    b(Type type, Annotation[] annotationArr) {
        super(type);
        AppMethodBeat.i(10975, false);
        a(annotationArr);
        AppMethodBeat.o(10975);
    }

    @Override // cn.missfresh.basiclib.net.b.b.c
    public T a(ResponseBody responseBody) throws IOException {
        int i = 0;
        AppMethodBeat.i(10977, false);
        T t = (T) responseBody.string().trim();
        responseBody.close();
        if (cn.missfresh.utils.b.a((String) t)) {
            ApiException apiException = new ApiException(-100, "\u8fd4\u56de\u6570\u636e\u4e3a\u7a7a");
            AppMethodBeat.o(10977);
            throw apiException;
        } else if (!b(t)) {
            if (this.a == String.class) {
                AppMethodBeat.o(10977);
                return t;
            }
            ApiException apiException2 = new ApiException(-1008, "\u6570\u636e\u683c\u5f0f\u9519\u8bef");
            AppMethodBeat.o(10977);
            throw apiException2;
        } else if (t.startsWith("[", 0)) {
            T a = a((String) t);
            AppMethodBeat.o(10977);
            return a;
        } else {
            JSONObject parseObject = JSON.parseObject(t);
            if (parseObject.containsKey(this.b)) {
                i = parseObject.getIntValue(this.b);
            }
            if (i == 0) {
                if (!cn.missfresh.utils.b.a(this.d)) {
                    t = (T) parseObject.getString(this.d);
                }
                T a2 = a((String) t);
                AppMethodBeat.o(10977);
                return a2;
            }
            ApiException apiException3 = new ApiException(i, parseObject.getString(this.c));
            AppMethodBeat.o(10977);
            throw apiException3;
        }
    }

    private boolean b(String str) {
        AppMethodBeat.i(10980, false);
        if (d(str)) {
            AppMethodBeat.o(10980);
            return true;
        } else if (c(str)) {
            AppMethodBeat.o(10980);
            return true;
        } else {
            AppMethodBeat.o(10980);
            return false;
        }
    }

    private boolean c(String str) {
        AppMethodBeat.i(10982, false);
        if (cn.missfresh.utils.b.a(str)) {
            AppMethodBeat.o(10982);
            return false;
        } else if (!str.startsWith("[") || !str.endsWith("]")) {
            AppMethodBeat.o(10982);
            return false;
        } else {
            AppMethodBeat.o(10982);
            return true;
        }
    }

    private boolean d(String str) {
        AppMethodBeat.i(10985, false);
        if (cn.missfresh.utils.b.a(str)) {
            AppMethodBeat.o(10985);
            return false;
        } else if (!str.startsWith("{") || !str.endsWith("}")) {
            AppMethodBeat.o(10985);
            return false;
        } else {
            AppMethodBeat.o(10985);
            return true;
        }
    }

    private void a(Annotation[] annotationArr) {
        int i = 0;
        AppMethodBeat.i(10987, false);
        if (annotationArr != null && annotationArr.length > 0) {
            int length = annotationArr.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                Annotation annotation = annotationArr[i];
                if (annotation instanceof a) {
                    a aVar = (a) annotation;
                    this.d = aVar.a();
                    this.b = aVar.b();
                    this.c = aVar.c();
                    break;
                }
                i++;
            }
        }
        if (cn.missfresh.utils.b.a(this.b)) {
            this.b = Constants.KEY_HTTP_CODE;
        }
        if (cn.missfresh.utils.b.a(this.c)) {
            this.c = "msg";
        }
        AppMethodBeat.o(10987);
    }
}
