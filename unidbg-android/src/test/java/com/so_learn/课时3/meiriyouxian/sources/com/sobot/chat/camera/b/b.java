package com.sobot.chat.camera.b;

import android.view.Surface;
import android.view.SurfaceHolder;
import com.sobot.chat.camera.a;
import com.sobot.chat.camera.c.h;

/* compiled from: BorrowVideoState */
public class b implements e {
    private final String a = "BorrowVideoState";
    private c b;

    @Override // com.sobot.chat.camera.b.e
    public void a() {
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(float f, float f2, a.c cVar) {
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(Surface surface, float f) {
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(boolean z, long j) {
    }

    @Override // com.sobot.chat.camera.b.e
    public void b(SurfaceHolder surfaceHolder, float f) {
    }

    public b(c cVar) {
        this.b = cVar;
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(SurfaceHolder surfaceHolder, float f) {
        a.a().b(surfaceHolder, f);
        c cVar = this.b;
        cVar.a(cVar.g());
    }

    @Override // com.sobot.chat.camera.b.e
    public void c(SurfaceHolder surfaceHolder, float f) {
        this.b.c().a(2);
        c cVar = this.b;
        cVar.a(cVar.g());
    }

    @Override // com.sobot.chat.camera.b.e
    public void b() {
        this.b.c().b(2);
        c cVar = this.b;
        cVar.a(cVar.g());
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(float f, int i) {
        h.a("BorrowVideoState", "zoom");
    }
}
