package cn.missfresh.risk.d;

import android.security.keystore.KeyProperties;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: AESClientUtils */
public class a {
    public static byte[] a(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        AppMethodBeat.i(2422, false);
        byte[] a = a(bArr, bArr2, 1);
        AppMethodBeat.o(2422);
        return a;
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, int i) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        AppMethodBeat.i(2432, false);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, KeyProperties.KEY_ALGORITHM_AES);
        IvParameterSpec ivParameterSpec = new IvParameterSpec("A-16-Byte-String".getBytes("UTF-8"));
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(i, secretKeySpec, ivParameterSpec);
        byte[] doFinal = instance.doFinal(bArr);
        AppMethodBeat.o(2432);
        return doFinal;
    }
}
