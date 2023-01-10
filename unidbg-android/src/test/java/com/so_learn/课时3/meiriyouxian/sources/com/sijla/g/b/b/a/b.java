package com.sijla.g.b.b.a;

import com.sijla.g.a.e;
import com.sijla.g.b.b.a;
import com.tencent.smtt.sdk.TbsListener;
import java.io.IOException;
import java.io.InputStream;

public class b implements a {
    public com.sijla.g.b.a.a a(int i, InputStream inputStream) {
        com.sijla.g.b.a.a aVar = new com.sijla.g.b.a.a();
        try {
            aVar.a(e.a(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
            aVar.a((int) TbsListener.ErrorCode.INFO_SDKINIT_IS_SYS_FORCED);
            aVar.a(e.getMessage());
        }
        return aVar;
    }
}
