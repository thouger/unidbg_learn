package cn.missfresh.module.base.network;

import com.bangcle.andjni.JniLib;
import java.io.Serializable;

public class MissFreshResponse implements Serializable {
    public String accessToken;
    public int code;
    public String content;
    public boolean isSuccessful;
    public String user_id;

    public MissFreshResponse() {
        JniLib.cV(this, 17);
    }

    @Override // java.lang.Object
    public String toString() {
        return (String) JniLib.cL(this, 16);
    }
}
