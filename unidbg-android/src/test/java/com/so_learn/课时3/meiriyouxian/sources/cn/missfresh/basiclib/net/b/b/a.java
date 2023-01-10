package cn.missfresh.basiclib.net.b.b;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.f;
import retrofit2.r;

/* compiled from: BasicFastJsonConverterFactory */
public class a extends cn.missfresh.basiclib.net.b.a.a {
    public f<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, r rVar) {
        AppMethodBeat.i(3608, false);
        f<ResponseBody, ?> a = a(type, annotationArr, rVar);
        AppMethodBeat.o(3608);
        return a;
    }

    /* access modifiers changed from: protected */
    public f<ResponseBody, ?> a(Type type, Annotation[] annotationArr, r rVar) {
        AppMethodBeat.i(3612, false);
        c cVar = new c(type);
        AppMethodBeat.o(3612);
        return cVar;
    }

    public f<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, r rVar) {
        AppMethodBeat.i(3616, false);
        f<?, RequestBody> a = a(cn.missfresh.basiclib.net.a.a().d());
        AppMethodBeat.o(3616);
        return a;
    }

    /* access modifiers changed from: protected */
    public f<?, RequestBody> a(cn.missfresh.basiclib.net.b.c.a aVar) {
        AppMethodBeat.i(3620, false);
        b bVar = new b(aVar);
        AppMethodBeat.o(3620);
        return bVar;
    }
}
