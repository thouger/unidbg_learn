package cn.missfresh.module.base.bean;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class MsgActionListBean$MsgActionBean extends BaseMsgBean {
    public String content;
    public long id;
    public String image;
    public String link;
    public String timeDesc;
    public String title;

    public MsgActionListBean$MsgActionBean() {
        AppMethodBeat.i(5004, false);
        setMsgType(1);
        AppMethodBeat.o(5004);
    }
}
