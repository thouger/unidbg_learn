package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.security.keystore.KeyProperties;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.g;
import com.google.android.exoplayer2.upstream.h;
import com.google.android.exoplayer2.upstream.s;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: Aes128DataSource */
/* access modifiers changed from: package-private */
public class a implements f {
    private final f a;
    private final byte[] b;
    private final byte[] c;
    private CipherInputStream d;

    public a(f fVar, byte[] bArr, byte[] bArr2) {
        this.a = fVar;
        this.b = bArr;
        this.c = bArr2;
    }

    @Override // com.google.android.exoplayer2.upstream.f
    public final void a(s sVar) {
        this.a.a(sVar);
    }

    @Override // com.google.android.exoplayer2.upstream.f
    public final long a(h hVar) throws IOException {
        try {
            Cipher d = d();
            try {
                d.init(2, new SecretKeySpec(this.b, KeyProperties.KEY_ALGORITHM_AES), new IvParameterSpec(this.c));
                g gVar = new g(this.a, hVar);
                this.d = new CipherInputStream(gVar, d);
                gVar.a();
                return -1;
            } catch (InvalidAlgorithmParameterException | InvalidKeyException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.f
    public final int a(byte[] bArr, int i, int i2) throws IOException {
        com.google.android.exoplayer2.util.a.a(this.d);
        int read = this.d.read(bArr, i, i2);
        if (read < 0) {
            return -1;
        }
        return read;
    }

    @Override // com.google.android.exoplayer2.upstream.f
    public final Uri a() {
        return this.a.a();
    }

    @Override // com.google.android.exoplayer2.upstream.f
    public final Map<String, List<String>> b() {
        return this.a.b();
    }

    @Override // com.google.android.exoplayer2.upstream.f
    public void c() throws IOException {
        if (this.d != null) {
            this.d = null;
            this.a.c();
        }
    }

    /* access modifiers changed from: protected */
    public Cipher d() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance("AES/CBC/PKCS7Padding");
    }
}
