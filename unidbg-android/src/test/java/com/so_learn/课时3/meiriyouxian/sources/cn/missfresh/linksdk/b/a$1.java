package cn.missfresh.linksdk.b;

import android.text.TextUtils;
import cn.missfresh.linksdk.b.a;
import cn.missfresh.linksdk.c.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/* compiled from: MFHttpManager */
class a$1 implements Callback {
    final /* synthetic */ a.a a;
    final /* synthetic */ b b;
    final /* synthetic */ a c;

    a$1(a aVar, a.a aVar2, b bVar) {
        this.c = aVar;
        this.a = aVar2;
        this.b = bVar;
    }

    public void onFailure(Call call, IOException iOException) {
        AppMethodBeat.i(13809, false);
        a.a(this.c, this.a, this.b, iOException.getMessage());
        AppMethodBeat.o(13809);
    }

    public void onResponse(Call call, Response response) throws IOException {
        AppMethodBeat.i(13811, false);
        if (call.isCanceled()) {
            String a = a.a(this.c);
            c.a(a, "AD Info request is canceled, params : " + this.b.toString());
        }
        if (!response.isSuccessful() || response.body() == null) {
            a.a(this.c, this.a, this.b, "request failed");
        } else {
            String string = response.body().string();
            if (!TextUtils.isEmpty(string)) {
                this.a.a(string);
            } else {
                a.a(this.c, this.a, this.b, "data empty");
            }
        }
        AppMethodBeat.o(13811);
    }
}
