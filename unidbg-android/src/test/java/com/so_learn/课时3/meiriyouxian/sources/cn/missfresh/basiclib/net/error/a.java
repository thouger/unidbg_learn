package cn.missfresh.basiclib.net.error;

import android.app.backup.BackupTransport;
import android.media.MediaPlayer;
import android.net.ParseException;
import cn.missfresh.basiclib.net.bean.ErrorBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.cert.CertPathValidatorException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import org.apache.http.conn.ConnectTimeoutException;
import retrofit2.HttpException;

/* compiled from: ExceptionManager */
public class a {
    public static ErrorBean a(Throwable th) {
        AppMethodBeat.i(3695, false);
        if (th instanceof HttpException) {
            HttpException httpException = (HttpException) th;
            ErrorBean errorBean = new ErrorBean(httpException.code(), httpException.getMessage());
            AppMethodBeat.o(3695);
            return errorBean;
        } else if (th instanceof ApiException) {
            ErrorBean errorBean2 = new ErrorBean(((ApiException) th).getErrorCode(), th.getMessage());
            AppMethodBeat.o(3695);
            return errorBean2;
        } else if ((th instanceof JSONException) || (th instanceof ParseException)) {
            ErrorBean errorBean3 = new ErrorBean(BackupTransport.TRANSPORT_NOT_INITIALIZED, "\u89e3\u6790\u9519\u8bef");
            AppMethodBeat.o(3695);
            return errorBean3;
        } else if (th instanceof ConnectException) {
            ErrorBean errorBean4 = new ErrorBean(-1002, "\u8fde\u63a5\u5931\u8d25");
            AppMethodBeat.o(3695);
            return errorBean4;
        } else if (th instanceof SSLHandshakeException) {
            ErrorBean errorBean5 = new ErrorBean(-1005, "\u8bc1\u4e66\u9a8c\u8bc1\u5931\u8d25");
            AppMethodBeat.o(3695);
            return errorBean5;
        } else if (th instanceof CertPathValidatorException) {
            ErrorBean errorBean6 = new ErrorBean(MediaPlayer.MEDIA_ERROR_MALFORMED, "\u8bc1\u4e66\u8def\u5f84\u6ca1\u627e\u5230");
            AppMethodBeat.o(3695);
            return errorBean6;
        } else if (th instanceof SSLPeerUnverifiedException) {
            ErrorBean errorBean7 = new ErrorBean(MediaPlayer.MEDIA_ERROR_MALFORMED, "\u65e0\u6709\u6548\u7684SSL\u8bc1\u4e66");
            AppMethodBeat.o(3695);
            return errorBean7;
        } else if (th instanceof ConnectTimeoutException) {
            ErrorBean errorBean8 = new ErrorBean(-1006, "\u8fde\u63a5\u8d85\u65f6");
            AppMethodBeat.o(3695);
            return errorBean8;
        } else if (th instanceof SocketTimeoutException) {
            ErrorBean errorBean9 = new ErrorBean(-1006, "\u8fde\u63a5\u8d85\u65f6");
            AppMethodBeat.o(3695);
            return errorBean9;
        } else if (th instanceof ClassCastException) {
            ErrorBean errorBean10 = new ErrorBean(-1008, "\u7c7b\u578b\u8f6c\u6362\u51fa\u9519");
            AppMethodBeat.o(3695);
            return errorBean10;
        } else if (th instanceof NullPointerException) {
            ErrorBean errorBean11 = new ErrorBean(-100, "\u6570\u636e\u6709\u7a7a");
            AppMethodBeat.o(3695);
            return errorBean11;
        } else if (th instanceof UnknownHostException) {
            ErrorBean errorBean12 = new ErrorBean(404, "\u670d\u52a1\u5668\u5730\u5740\u672a\u627e\u5230,\u8bf7\u68c0\u67e5\u7f51\u7edc\u6216Url");
            AppMethodBeat.o(3695);
            return errorBean12;
        } else {
            ErrorBean errorBean13 = new ErrorBean(-1000, th.getMessage());
            AppMethodBeat.o(3695);
            return errorBean13;
        }
    }
}
