package cn.missfresh.module.base.widget.tag.bean;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.util.List;

public class ProductTagInfoBean {
    private List<TagInfo> bottom;
    private List<TagInfo> priceTop;
    private List<TagInfo> rank;
    private List<TagInfo> titleLeft;
    private List<TagInfo> topLeft;
    private List<TagInfo> topRight;

    public TagInfo getTopLeft() {
        AppMethodBeat.i(24364, false);
        TagInfo tagInfo = b.a(this.topLeft) ? null : this.topLeft.get(0);
        AppMethodBeat.o(24364);
        return tagInfo;
    }

    public void setTopLeft(List<TagInfo> list) {
        this.topLeft = list;
    }

    public TagInfo getTopRight() {
        AppMethodBeat.i(24365, false);
        TagInfo tagInfo = b.a(this.topRight) ? null : this.topRight.get(0);
        AppMethodBeat.o(24365);
        return tagInfo;
    }

    public void setTopRight(List<TagInfo> list) {
        this.topRight = list;
    }

    public TagInfo getBottom() {
        AppMethodBeat.i(24366, false);
        TagInfo tagInfo = b.a(this.bottom) ? null : this.bottom.get(0);
        AppMethodBeat.o(24366);
        return tagInfo;
    }

    public void setBottom(List<TagInfo> list) {
        this.bottom = list;
    }

    public TagInfo getTitleLeft() {
        AppMethodBeat.i(24367, false);
        TagInfo tagInfo = b.a(this.titleLeft) ? null : this.titleLeft.get(0);
        AppMethodBeat.o(24367);
        return tagInfo;
    }

    public void setTitleLeft(List<TagInfo> list) {
        this.titleLeft = list;
    }

    public List<TagInfo> getPriceTop() {
        return this.priceTop;
    }

    public void setPriceTop(List<TagInfo> list) {
        this.priceTop = list;
    }

    public TagInfo getRank() {
        AppMethodBeat.i(24368, false);
        TagInfo tagInfo = b.a(this.rank) ? null : this.rank.get(0);
        AppMethodBeat.o(24368);
        return tagInfo;
    }

    public void setRank(List<TagInfo> list) {
        this.rank = list;
    }
}
