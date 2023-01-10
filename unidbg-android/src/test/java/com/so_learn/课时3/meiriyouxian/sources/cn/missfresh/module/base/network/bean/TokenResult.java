package cn.missfresh.module.base.network.bean;

import cn.missfresh.module.base.bean.ImageToken;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;
import java.util.List;

public class TokenResult {
    private String bucket;
    private List<ImageToken> result;

    public TokenResult() {
        JniLib.cV(this, 95);
    }

    public String getBucket() {
        return this.bucket;
    }

    public void setBucket(String str) {
        this.bucket = str;
    }

    public List<ImageToken> getResult() {
        return this.result;
    }

    public void setResult(List<ImageToken> list) {
        this.result = list;
    }

    public ImageToken getItemTokenByFileName(String str) {
        AppMethodBeat.i(15734, false);
        ImageToken imageToken = new ImageToken();
        List<ImageToken> list = this.result;
        if (list == null || list.size() <= 0) {
            AppMethodBeat.o(15734);
            return imageToken;
        }
        int size = this.result.size();
        for (int i = 0; i < size; i++) {
            imageToken = this.result.get(i);
            if (imageToken != null && str.equals(imageToken.filename)) {
                break;
            }
        }
        AppMethodBeat.o(15734);
        return imageToken;
    }
}
