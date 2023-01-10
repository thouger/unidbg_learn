package com.sobot.chat.camera.b;

import android.content.Context;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.sobot.chat.camera.a;
import com.sobot.chat.camera.d.a;

/* compiled from: CameraMachine */
public class c implements e {
    private Context a;
    private e b = this.d;
    private a c;
    private e d = new d(this);
    private e e = new a(this);
    private e f = new b(this);

    public c(Context context, a aVar, a.AbstractC0141a aVar2) {
        this.a = context;
        this.c = aVar;
    }

    public com.sobot.chat.camera.d.a c() {
        return this.c;
    }

    public Context d() {
        return this.a;
    }

    public void a(e eVar) {
        this.b = eVar;
    }

    /* access modifiers changed from: package-private */
    public e e() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public e f() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public e g() {
        return this.d;
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(SurfaceHolder surfaceHolder, float f) {
        this.b.a(surfaceHolder, f);
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(float f, float f2, a.c cVar) {
        this.b.a(f, f2, cVar);
    }

    @Override // com.sobot.chat.camera.b.e
    public void b(SurfaceHolder surfaceHolder, float f) {
        this.b.b(surfaceHolder, f);
    }

    @Override // com.sobot.chat.camera.b.e
    public void a() {
        this.b.a();
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(Surface surface, float f) {
        this.b.a(surface, f);
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(boolean z, long j) {
        this.b.a(z, j);
    }

    @Override // com.sobot.chat.camera.b.e
    public void c(SurfaceHolder surfaceHolder, float f) {
        this.b.c(surfaceHolder, f);
    }

    @Override // com.sobot.chat.camera.b.e
    public void b() {
        this.b.b();
    }

    @Override // com.sobot.chat.camera.b.e
    public void a(float f, int i) {
        this.b.a(f, i);
    }
}
