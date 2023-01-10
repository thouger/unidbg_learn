package cn.missfresh.location;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.ContextCompat;
import cn.missfresh.location_api.StickyLiveData;
import cn.missfresh.location_api.b;
import cn.missfresh.location_api.bean.Location;
import cn.missfresh.location_api.c;
import cn.missfresh.location_api.d;
import cn.missfresh.location_api.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.unionpay.tsmservice.mi.data.Constant;

/* compiled from: MFLocationWarpper */
public class a implements c {
    private b a;
    private cn.missfresh.location_api.a b;
    private Context c;
    private e d;

    public a(cn.missfresh.location_api.a aVar) {
        AppMethodBeat.i(6612, false);
        if (aVar != null) {
            this.b = aVar;
            this.a = aVar.b();
            this.c = aVar.a();
            AppMethodBeat.o(6612);
            return;
        }
        NullPointerException nullPointerException = new NullPointerException("baseConfig not allow null");
        AppMethodBeat.o(6612);
        throw nullPointerException;
    }

    public void a(d dVar) {
        AppMethodBeat.i(6614, false);
        this.a.a(this.b, dVar);
        AppMethodBeat.o(6614);
    }

    public void a(e eVar) {
        this.d = eVar;
    }

    public int a() {
        AppMethodBeat.i(6617, false);
        if (d()) {
            AppMethodBeat.o(6617);
            return -1;
        }
        int a = this.a.a();
        AppMethodBeat.o(6617);
        return a;
    }

    public int a(int i) {
        AppMethodBeat.i(6618, false);
        if (d()) {
            AppMethodBeat.o(6618);
            return -1;
        }
        int a = this.a.a(i);
        AppMethodBeat.o(6618);
        return a;
    }

    public StickyLiveData<Location> b() {
        AppMethodBeat.i(6620, false);
        StickyLiveData<Location> b = this.a.b();
        AppMethodBeat.o(6620);
        return b;
    }

    public void c() {
        AppMethodBeat.i(6627, false);
        this.a.c();
        AppMethodBeat.o(6627);
    }

    public String b(int i) {
        AppMethodBeat.i(6630, false);
        String b = this.a.b(i);
        AppMethodBeat.o(6630);
        return b;
    }

    private boolean d() {
        AppMethodBeat.i(6638, false);
        if (!(ContextCompat.checkSelfPermission(this.c, Manifest.permission.ACCESS_COARSE_LOCATION) == 0 || ContextCompat.checkSelfPermission(this.c, Manifest.permission.ACCESS_FINE_LOCATION) == 0) || (!a(Manifest.permission.ACCESS_COARSE_LOCATION) && !a(Manifest.permission.ACCESS_FINE_LOCATION))) {
            e eVar = this.d;
            if (eVar != null) {
                eVar.a();
            }
            AppMethodBeat.o(6638);
            return true;
        }
        AppMethodBeat.o(6638);
        return false;
    }

    public boolean a(String str) {
        AppMethodBeat.i(6640, false);
        if (TextUtils.equals(Constant.DEVICE_XIAOMI, Build.MANUFACTURER)) {
            String permissionToOp = AppOpsManagerCompat.permissionToOp(str);
            Context context = this.c;
            if (AppOpsManagerCompat.noteProxyOp(context, permissionToOp, context.getPackageName()) != 1 || Build.VERSION.SDK_INT < 23) {
                AppMethodBeat.o(6640);
                return true;
            }
            AppMethodBeat.o(6640);
            return false;
        }
        AppMethodBeat.o(6640);
        return true;
    }
}
