package com.bun.miitmdid.core;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bun.miitmdid.a.a;
import com.bun.miitmdid.supplier.IdSupplier;
import com.bun.miitmdid.supplier.a;
import com.bun.miitmdid.utils.SupplierListener;

public class MdidSdk implements SupplierListener {
    private IIdentifierListener _InnerListener;
    private a _setting;

    /* renamed from: com.bun.miitmdid.core.MdidSdk$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[a.EnumC0069a.values().length];

        static {
            AppMethodBeat.i(5123, false);
            try {
                a[a.EnumC0069a.XIAOMI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[a.EnumC0069a.VIVO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[a.EnumC0069a.HUA_WEI.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[a.EnumC0069a.OPPO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[a.EnumC0069a.MOTO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[a.EnumC0069a.LENOVO.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[a.EnumC0069a.ASUS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[a.EnumC0069a.SAMSUNG.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[a.EnumC0069a.MEIZU.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[a.EnumC0069a.ALPS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[a.EnumC0069a.NUBIA.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            AppMethodBeat.o(5123);
        }
    }

    public MdidSdk() {
        AppMethodBeat.i(5126, false);
        try {
            com.bun.miitmdid.utils.a.a(true);
        } catch (Exception e) {
            com.bun.miitmdid.utils.a.b("mdidsdk", "extractor exception!", e);
        }
        AppMethodBeat.o(5126);
    }

    public MdidSdk(boolean z) {
        AppMethodBeat.i(5129, false);
        try {
            com.bun.miitmdid.utils.a.a(z);
        } catch (Exception e) {
            com.bun.miitmdid.utils.a.b("mdidsdk", "extractor exception!", e);
        }
        AppMethodBeat.o(5129);
    }

    private native int _InnerFailed(int i, IdSupplier idSupplier);

    public native int InitSdk(Context context, IIdentifierListener iIdentifierListener);

    @Override // com.bun.miitmdid.utils.SupplierListener
    public native void OnSupport(boolean z, IdSupplier idSupplier);

    public native void UnInitSdk();
}
