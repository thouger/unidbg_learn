package cn.missfresh.picture.luban;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: InputStreamAdapter */
public abstract class d implements e {
    private InputStream a;

    public abstract InputStream b() throws IOException;

    @Override // cn.missfresh.picture.luban.e
    public InputStream a() throws IOException {
        c();
        this.a = b();
        return this.a;
    }

    @Override // cn.missfresh.picture.luban.e
    public void c() {
        InputStream inputStream = this.a;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            } catch (Throwable th) {
                this.a = null;
                throw th;
            }
            this.a = null;
        }
    }
}
