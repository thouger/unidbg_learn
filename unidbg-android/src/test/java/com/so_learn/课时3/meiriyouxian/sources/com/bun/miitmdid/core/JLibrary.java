package com.bun.miitmdid.core;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JLibrary {
    public static String ASSET = "assets/";
    public static String SeriailizationString = "stub_liu_0_dex_so:A3AEECD8@com/jdog;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;";
    public static final String TAG = JLibrary.class.getSimpleName();
    public static ClassLoader classLoader;
    public static Context context;
    public static String o00o0a0odod;
    public static String o00o0a0odou;
    public static String xdata = ".00000000000";
    public static String ydata = ".11111111111";

    /* access modifiers changed from: package-private */
    public enum ReturnStatus {
        RETURN_OK,
        RETURN_ERR;

        static {
            AppMethodBeat.i(4766, false);
            AppMethodBeat.o(4766);
        }

        public static ReturnStatus valueOf(String str) {
            AppMethodBeat.i(4773, false);
            ReturnStatus returnStatus = (ReturnStatus) Enum.valueOf(ReturnStatus.class, str);
            AppMethodBeat.o(4773);
            return returnStatus;
        }
    }

    static {
        AppMethodBeat.i(4785, false);
        AppMethodBeat.o(4785);
    }

    public static ReturnStatus InitEntry(Context context2) {
        AppMethodBeat.i(4790, false);
        if (context2 != null) {
            try {
                context = context2;
                classLoader = JLibrary.class.getClassLoader();
                System.loadLibrary(SeriailizationString.substring(SeriailizationString.lastIndexOf(58) + 1, SeriailizationString.indexOf(64)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            ReturnStatus returnStatus = ReturnStatus.RETURN_OK;
            AppMethodBeat.o(4790);
            return returnStatus;
        }
        ExceptionInInitializerError exceptionInInitializerError = new ExceptionInInitializerError("pass InitEntry arg(context) is null");
        AppMethodBeat.o(4790);
        throw exceptionInInitializerError;
    }

    public static ByteBuffer ReadByteBuffer(String str) {
        ByteBuffer byteBuffer;
        AppMethodBeat.i(4793, false);
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byteBuffer = ByteBuffer.allocate(fileInputStream.available());
            for (int i = 0; i < fileInputStream.available(); i += fileInputStream.read(byteBuffer.array(), i, fileInputStream.available() - i)) {
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            byteBuffer = null;
        }
        AppMethodBeat.o(4793);
        return byteBuffer;
    }

    public static Object[] o0o0o0o0o0(Object obj, String str, String str2, List<IOException> list) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        AppMethodBeat.i(4798, false);
        String[] split = str.split(";");
        ArrayList arrayList = new ArrayList();
        for (String str3 : split) {
            arrayList.add(ReadByteBuffer(str3));
        }
        Object[] objArr = (Object[]) o0o0o0o0o0o0(obj, str2, ByteBuffer[].class, List.class).invoke(obj, arrayList.toArray(new ByteBuffer[arrayList.size()]), list);
        AppMethodBeat.o(4798);
        return objArr;
    }

    private static Method o0o0o0o0o0o0(Object obj, String str, Class<?>... clsArr) throws NoSuchMethodException {
        AppMethodBeat.i(4800, false);
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                AppMethodBeat.o(4800);
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
            }
        }
        NoSuchMethodException noSuchMethodException = new NoSuchMethodException("Method " + str + " with parameters " + Arrays.asList(clsArr) + " not found in " + obj.getClass());
        AppMethodBeat.o(4800);
        throw noSuchMethodException;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0058, code lost:
        if (new java.io.File(java.lang.String.valueOf(com.bun.miitmdid.core.JLibrary.o00o0a0odod) + r6).exists() == false) goto L_0x005a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void o0oo0o0(android.content.Context r5, java.lang.String r6) throws java.lang.Exception {
        /*
            java.lang.String r0 = ""
            r1 = 4802(0x12c2, float:6.729E-42)
            r2 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r2)
            android.content.res.AssetManager r2 = r5.getAssets()     // Catch:{ IOException -> 0x0070 }
            java.io.InputStream r2 = r2.open(r6)     // Catch:{ IOException -> 0x0070 }
            java.lang.String r3 = com.bun.miitmdid.core.Utils.getXdataDir(r5, r0)     // Catch:{ IOException -> 0x0070 }
            com.bun.miitmdid.core.JLibrary.o00o0a0odod = r3     // Catch:{ IOException -> 0x0070 }
            java.lang.String r0 = com.bun.miitmdid.core.Utils.getYdataDir(r5, r0)     // Catch:{ IOException -> 0x0070 }
            com.bun.miitmdid.core.JLibrary.o00o0a0odou = r0     // Catch:{ IOException -> 0x0070 }
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0070 }
            java.lang.String r3 = com.bun.miitmdid.core.JLibrary.o00o0a0odod     // Catch:{ IOException -> 0x0070 }
            r0.<init>(r3)     // Catch:{ IOException -> 0x0070 }
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x0070 }
            java.lang.String r4 = com.bun.miitmdid.core.JLibrary.o00o0a0odou     // Catch:{ IOException -> 0x0070 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0070 }
            boolean r4 = r0.exists()     // Catch:{ IOException -> 0x0070 }
            if (r4 != 0) goto L_0x0037
            r0.mkdir()     // Catch:{ IOException -> 0x0070 }
            r3.mkdir()     // Catch:{ IOException -> 0x0070 }
        L_0x0037:
            boolean r0 = com.bun.miitmdid.core.Utils.update(r5)     // Catch:{ IOException -> 0x0070 }
            if (r0 != 0) goto L_0x005a
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0070 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0070 }
            java.lang.String r4 = com.bun.miitmdid.core.JLibrary.o00o0a0odod     // Catch:{ IOException -> 0x0070 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x0070 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0070 }
            r3.append(r6)     // Catch:{ IOException -> 0x0070 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0070 }
            r0.<init>(r3)     // Catch:{ IOException -> 0x0070 }
            boolean r0 = r0.exists()     // Catch:{ IOException -> 0x0070 }
            if (r0 != 0) goto L_0x0074
        L_0x005a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0070 }
            java.lang.String r3 = com.bun.miitmdid.core.JLibrary.o00o0a0odod     // Catch:{ IOException -> 0x0070 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x0070 }
            r0.<init>(r3)     // Catch:{ IOException -> 0x0070 }
            r0.append(r6)     // Catch:{ IOException -> 0x0070 }
            java.lang.String r6 = r0.toString()     // Catch:{ IOException -> 0x0070 }
            com.bun.miitmdid.core.Utils.x0xooXdata(r2, r6, r5)     // Catch:{ IOException -> 0x0070 }
            goto L_0x0074
        L_0x0070:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0074:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bun.miitmdid.core.JLibrary.o0oo0o0(android.content.Context, java.lang.String):void");
    }
}
