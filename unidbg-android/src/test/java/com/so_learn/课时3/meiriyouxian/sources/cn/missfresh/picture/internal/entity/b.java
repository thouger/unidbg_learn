package cn.missfresh.picture.internal.entity;

import android.content.Context;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.picture.internal.ui.widget.IncapableDialog;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: IncapableCause */
public class b {
    private int a = 0;
    private String b;
    private String c;

    public b(String str) {
        this.c = str;
    }

    public b(int i, String str) {
        this.a = i;
        this.c = str;
    }

    public static void a(Context context, b bVar) {
        AppMethodBeat.i(18658, false);
        if (bVar == null) {
            AppMethodBeat.o(18658);
            return;
        }
        int i = bVar.a;
        if (i == 1) {
            IncapableDialog.a(bVar.b, bVar.c).show(((FragmentActivity) context).getSupportFragmentManager(), IncapableDialog.class.getName());
        } else if (i != 2) {
            Toast.makeText(context, bVar.c, 0).show();
        }
        AppMethodBeat.o(18658);
    }
}
