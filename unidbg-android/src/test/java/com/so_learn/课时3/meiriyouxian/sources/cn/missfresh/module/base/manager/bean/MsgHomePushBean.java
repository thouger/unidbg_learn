package cn.missfresh.module.base.manager.bean;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;

public class MsgHomePushBean {
    public String content;
    public long id;
    public String image;
    public String link;
    public String title;

    public String toString() {
        AppMethodBeat.i(15033, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(15033);
        return jSONString;
    }
}
