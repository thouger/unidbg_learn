package cn.missfresh.tinkerlib;

import android.app.ActivityManager;
import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.lib.util.TinkerLog;

public class TinkerPatchListener extends DefaultPatchListener {
    private final int a;

    public TinkerPatchListener(Context context) {
        super(context);
        AppMethodBeat.i(12855, false);
        this.a = ((ActivityManager) context.getSystemService("activity")).getMemoryClass();
        TinkerLog.i("Tinker.TinkerPatchListener", "application maxMemory:" + this.a, new Object[0]);
        AppMethodBeat.o(12855);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0071, code lost:
        if (r2 == null) goto L_0x0075;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0077  */
    @Override // com.tencent.tinker.lib.listener.DefaultPatchListener
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int patchCheck(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            r0 = 12857(0x3239, float:1.8016E-41)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            java.io.File r2 = new java.io.File
            r2.<init>(r9)
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r1] = r9
            long r4 = com.tencent.tinker.loader.shareutil.SharePatchFileUtil.getFileOrDirectorySize(r2)
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r5 = 1
            r3[r5] = r4
            java.lang.String r4 = "Tinker.TinkerPatchListener"
            java.lang.String r6 = "receive a patch file: %s, file size:%d"
            com.tencent.tinker.lib.util.TinkerLog.i(r4, r6, r3)
            int r9 = super.patchCheck(r9, r10)
            if (r9 != 0) goto L_0x0033
            r6 = 62914560(0x3c00000, double:3.10839227E-316)
            int r9 = r8.a
            int r9 = cn.missfresh.tinkerlib.g.a(r6, r9)
        L_0x0033:
            if (r9 != 0) goto L_0x0048
            android.content.Context r3 = r8.context
            r6 = 4
            java.lang.String r7 = "tinker_share_config"
            android.content.SharedPreferences r3 = r3.getSharedPreferences(r7, r6)
            int r10 = r3.getInt(r10, r1)
            r3 = 3
            if (r10 < r3) goto L_0x0048
            r9 = -23
        L_0x0048:
            r10 = -24
            if (r9 != 0) goto L_0x0074
            java.util.Properties r2 = com.tencent.tinker.loader.shareutil.ShareTinkerInternals.fastGetPatchPackageMeta(r2)
            if (r2 != 0) goto L_0x0053
            goto L_0x0075
        L_0x0053:
            java.lang.String r3 = "platform"
            java.lang.String r2 = r2.getProperty(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "get platform:"
            r3.append(r6)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            java.lang.Object[] r6 = new java.lang.Object[r1]
            com.tencent.tinker.lib.util.TinkerLog.i(r4, r3, r6)
            if (r2 != 0) goto L_0x0074
            goto L_0x0075
        L_0x0074:
            r10 = r9
        L_0x0075:
            if (r10 != 0) goto L_0x0078
            r1 = r5
        L_0x0078:
            cn.missfresh.tinkerlib.e.a(r1)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.tinkerlib.TinkerPatchListener.patchCheck(java.lang.String, java.lang.String):int");
    }
}
