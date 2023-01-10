package com.umeng.analytics.pro;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: TIOStreamTransport */
public class cb extends cd {
    protected InputStream a = null;
    protected OutputStream b = null;

    @Override // com.umeng.analytics.pro.cd
    public boolean a() {
        return true;
    }

    @Override // com.umeng.analytics.pro.cd
    public void b() throws ce {
    }

    protected cb() {
    }

    public cb(InputStream inputStream) {
        this.a = inputStream;
    }

    public cb(OutputStream outputStream) {
        this.b = outputStream;
    }

    public cb(InputStream inputStream, OutputStream outputStream) {
        this.a = inputStream;
        this.b = outputStream;
    }

    @Override // com.umeng.analytics.pro.cd
    public void c() {
        InputStream inputStream = this.a;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.a = null;
        }
        OutputStream outputStream = this.b;
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.b = null;
        }
    }

    @Override // com.umeng.analytics.pro.cd
    public int a(byte[] bArr, int i, int i2) throws ce {
        InputStream inputStream = this.a;
        if (inputStream != null) {
            try {
                int read = inputStream.read(bArr, i, i2);
                if (read >= 0) {
                    return read;
                }
                throw new ce(4);
            } catch (IOException e) {
                throw new ce(0, e);
            }
        } else {
            throw new ce(1, "Cannot read from null inputStream");
        }
    }

    @Override // com.umeng.analytics.pro.cd
    public void b(byte[] bArr, int i, int i2) throws ce {
        OutputStream outputStream = this.b;
        if (outputStream != null) {
            try {
                outputStream.write(bArr, i, i2);
            } catch (IOException e) {
                throw new ce(0, e);
            }
        } else {
            throw new ce(1, "Cannot write to null outputStream");
        }
    }

    @Override // com.umeng.analytics.pro.cd
    public void d() throws ce {
        OutputStream outputStream = this.b;
        if (outputStream != null) {
            try {
                outputStream.flush();
            } catch (IOException e) {
                throw new ce(0, e);
            }
        } else {
            throw new ce(1, "Cannot flush null outputStream");
        }
    }
}
