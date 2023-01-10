package cn.missfresh.basiclib.net.b.b;

import cn.missfresh.basiclib.net.b.c.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* compiled from: BasicFastJsonRequestConverter */
public class b<T> extends cn.missfresh.basiclib.net.b.a.b<T> {
    protected static final MediaType a = MediaType.parse("application/json; charset=UTF-8");
    protected a b;

    public /* synthetic */ Object convert(Object obj) throws IOException {
        AppMethodBeat.i(3635, false);
        RequestBody a2 = a(obj);
        AppMethodBeat.o(3635);
        return a2;
    }

    static {
        AppMethodBeat.i(3640, false);
        AppMethodBeat.o(3640);
    }

    public b(a aVar) {
        this.b = aVar;
    }

    public RequestBody a(T t) throws IOException {
        byte[] bArr;
        AppMethodBeat.i(3630, false);
        if (this.b.k() != null && this.b.l() != null) {
            bArr = JSON.toJSONBytes(t, this.b.k(), this.b.l());
        } else if (this.b.k() != null) {
            bArr = JSON.toJSONBytes(t, this.b.k(), new SerializerFeature[0]);
        } else if (this.b.l() != null) {
            bArr = JSON.toJSONBytes(t, this.b.l());
        } else {
            bArr = JSON.toJSONBytes(t, new SerializerFeature[0]);
        }
        RequestBody create = RequestBody.create(a, bArr);
        AppMethodBeat.o(3630);
        return create;
    }
}
