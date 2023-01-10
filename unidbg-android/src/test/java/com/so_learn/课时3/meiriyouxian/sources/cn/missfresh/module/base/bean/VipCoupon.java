package cn.missfresh.module.base.bean;

import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSON;

public class VipCoupon {
    public Content content;
    public Content vip_content;

    public boolean isValid() {
        Content content;
        boolean z = false;
        AppMethodBeat.i(9865, false);
        Content content2 = this.content;
        if (content2 != null && !content2.isDirty() && (content = this.vip_content) != null && !content.isDirty()) {
            z = true;
        }
        AppMethodBeat.o(9865);
        return z;
    }

    public String toString() {
        AppMethodBeat.i(9868, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(9868);
        return jSONString;
    }

    public static class Content {
        public String content_down;
        public int content_type;
        public String content_up;

        public boolean isFreeShipping() {
            return this.content_type == 0;
        }

        public boolean isDirty() {
            boolean z = false;
            AppMethodBeat.i(9853, false);
            if (b.a(this.content_up) || b.a(this.content_down)) {
                z = true;
            }
            AppMethodBeat.o(9853);
            return z;
        }

        public String toString() {
            AppMethodBeat.i(9856, false);
            String str = "Content{content_up='" + this.content_up + DateFormat.QUOTE + ", content_down='" + this.content_down + DateFormat.QUOTE + '}';
            AppMethodBeat.o(9856);
            return str;
        }
    }
}
