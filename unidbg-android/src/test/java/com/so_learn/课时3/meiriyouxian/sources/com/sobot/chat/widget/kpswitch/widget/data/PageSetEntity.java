package com.sobot.chat.widget.kpswitch.widget.data;

import com.sobot.chat.widget.kpswitch.widget.data.a;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.UUID;

public class PageSetEntity<T extends a> implements Serializable {
    protected final String mIconUri;
    protected final boolean mIsShowIndicator;
    protected final int mPageCount;
    protected final LinkedList<T> mPageEntityList;
    protected final String mSetName;
    protected final String uuid = UUID.randomUUID().toString();

    public static class a<T extends a> {
        protected int f;
        protected boolean g = true;
        protected LinkedList<T> h = new LinkedList<>();
        protected String i;
        protected String j;
    }

    public PageSetEntity(a aVar) {
        this.mPageCount = aVar.f;
        this.mIsShowIndicator = aVar.g;
        this.mPageEntityList = aVar.h;
        this.mIconUri = aVar.i;
        this.mSetName = aVar.j;
    }

    public String getIconUri() {
        return this.mIconUri;
    }

    public int getPageCount() {
        LinkedList<T> linkedList = this.mPageEntityList;
        if (linkedList == null) {
            return 0;
        }
        return linkedList.size();
    }

    public LinkedList<T> getPageEntityList() {
        return this.mPageEntityList;
    }

    public String getUuid() {
        return this.uuid;
    }

    public boolean isShowIndicator() {
        return this.mIsShowIndicator;
    }
}
