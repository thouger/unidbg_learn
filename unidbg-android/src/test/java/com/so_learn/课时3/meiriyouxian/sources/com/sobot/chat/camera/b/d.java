package com.sobot.chat.camera.b;

import android.graphics.Bitmap;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.sobot.chat.camera.a;
import com.sobot.chat.camera.c.h;
import com.tencent.qcloud.tim.uikit.component.video.state.PreviewState;

/* compiled from: PreviewState */
/* access modifiers changed from: package-private */
public class d implements e {
    private c a;

    d(c cVar) {
        this.a = cVar;
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(SurfaceHolder surfaceHolder, float f) {
        a.a().b(surfaceHolder, f);
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(float f, float f2, a.c cVar) {
        h.a("preview state foucs");
        if (this.a.c().a(f, f2)) {
            a.a().a(this.a.d(), f, f2, cVar);
        }
    }

    @Override // com.sobot.chat.camera.b.e
    public void b(SurfaceHolder surfaceHolder, float f) {
        a.a().a(surfaceHolder, f);
    }

    /* compiled from: PreviewState */
    /* renamed from: com.sobot.chat.camera.b.d$1  reason: invalid class name */
    class AnonymousClass1 implements a.e {
        AnonymousClass1() {
        }

        @Override // com.sobot.chat.camera.a.e
        public void a(Bitmap bitmap, boolean z) {
            d.this.a.c().a(bitmap, z);
            d.this.a.a(d.this.a.e());
            h.a("capture");
        }
    }

    @Override // com.sobot.chat.camera.b.e
    public void a() {
        a.a().a(new AnonymousClass1());
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(Surface surface, float f) {
        a.a().a(surface, f, (a.b) null);
    }

    /* compiled from: PreviewState */
    /* renamed from: com.sobot.chat.camera.b.d$2  reason: invalid class name */
    class AnonymousClass2 implements a.d {
        final /* synthetic */ boolean a;

        AnonymousClass2(boolean z) {
            this.a = z;
        }

        @Override // com.sobot.chat.camera.a.d
        public void a(String str, Bitmap bitmap) {
            if (this.a) {
                d.this.a.c().a(3);
                return;
            }
            d.this.a.c().a(bitmap, str);
            d.this.a.a(d.this.a.f());
        }
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(boolean z, long j) {
        a.a().a(z, new AnonymousClass2(z));
    }

    @Override // com.sobot.chat.camera.b.e
    public void c(SurfaceHolder surfaceHolder, float f) {
        h.a("\u6d4f\u89c8\u72b6\u6001\u4e0b,\u6ca1\u6709 cancle \u4e8b\u4ef6");
    }

    @Override // com.sobot.chat.camera.b.e
    public void b() {
        h.a("\u6d4f\u89c8\u72b6\u6001\u4e0b,\u6ca1\u6709 confirm \u4e8b\u4ef6");
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(float f, int i) {
        h.a(PreviewState.TAG, "zoom");
        a.a().a(f, i);
    }
}
