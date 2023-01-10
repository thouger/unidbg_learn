package cn.missfresh.lib.b;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import retrofit2.f;
import retrofit2.r;

/* compiled from: MFFastJsonConverterFactory */
public class a extends cn.missfresh.basiclib.net.b.b.a {
    public static a a() {
        AppMethodBeat.i(10968, false);
        a aVar = new a();
        AppMethodBeat.o(10968);
        return aVar;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.basiclib.net.b.b.a
    public f<ResponseBody, ?> a(Type type, Annotation[] annotationArr, r rVar) {
        AppMethodBeat.i(10970, false);
        b bVar = new b(type, annotationArr);
        AppMethodBeat.o(10970);
        return bVar;
    }
}
