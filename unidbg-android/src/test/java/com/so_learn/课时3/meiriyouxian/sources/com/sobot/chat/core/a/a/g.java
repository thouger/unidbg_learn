package com.sobot.chat.core.a.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: SocketInputReader */
public class g extends Reader {
    final g a = this;
    private InputStream b;

    public g(InputStream inputStream) {
        super(inputStream);
        this.b = inputStream;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.lock) {
            if (this.b != null) {
                this.b.close();
                this.b = null;
            }
        }
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        throw new IOException("read() is not support for SocketInputReader, try readBytes().");
    }

    public byte[] a(int i) throws IOException {
        if (i <= 0) {
            return null;
        }
        synchronized (this.lock) {
            if (a()) {
                try {
                    byte[] bArr = new byte[i];
                    int i2 = 0;
                    do {
                        int read = this.b.read(bArr, i2, i - i2);
                        i2 += read;
                        if (read == -1) {
                            break;
                        }
                    } while (i2 < i);
                    if (i2 != i) {
                        return null;
                    }
                    return bArr;
                } catch (IOException unused) {
                    return null;
                }
            } else {
                throw new IOException("InputStreamReader is closed");
            }
        }
    }

    public byte[] a(byte[] bArr, boolean z) throws IOException {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        synchronized (this.lock) {
            if (a()) {
                try {
                    ArrayList arrayList = new ArrayList();
                    int i = 0;
                    do {
                        int read = this.b.read();
                        if (-1 == read) {
                            break;
                        }
                        arrayList.add(Byte.valueOf((byte) read));
                        i = read == (bArr[i] & 255) ? i + 1 : 0;
                    } while (i != bArr.length);
                    if (arrayList.size() == 0) {
                        return null;
                    }
                    int size = arrayList.size() - (z ? 0 : bArr.length);
                    byte[] bArr2 = new byte[size];
                    Iterator it2 = arrayList.iterator();
                    for (int i2 = 0; i2 < size; i2++) {
                        bArr2[i2] = ((Byte) it2.next()).byteValue();
                    }
                    return bArr2;
                } catch (IOException unused) {
                    return null;
                }
            } else {
                throw new IOException("InputStreamReader is closed");
            }
        }
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        boolean z;
        synchronized (this.lock) {
            if (this.b != null) {
                z = false;
                try {
                    if (this.b.available() > 0) {
                        z = true;
                    }
                } catch (IOException unused) {
                    return false;
                }
            } else {
                throw new IOException("InputStreamReader is closed");
            }
        }
        return z;
    }

    private boolean a() {
        return this.b != null;
    }
}
