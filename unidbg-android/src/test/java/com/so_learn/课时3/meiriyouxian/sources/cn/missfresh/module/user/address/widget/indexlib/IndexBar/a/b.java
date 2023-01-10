package cn.missfresh.module.user.address.widget.indexlib.IndexBar.a;

import cn.missfresh.module.user.address.widget.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import cn.missfresh.module.user.address.widget.indexlib.IndexBar.bean.Pinyin;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.Collections;
import java.util.List;

/* compiled from: IndexBarDataHelperImpl */
public class b implements a {
    public a a(List<? extends BaseIndexPinyinBean> list) {
        AppMethodBeat.i(6590, false);
        if (list == null || list.isEmpty()) {
            AppMethodBeat.o(6590);
            return this;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            BaseIndexPinyinBean baseIndexPinyinBean = (BaseIndexPinyinBean) list.get(i);
            StringBuilder sb = new StringBuilder();
            if (baseIndexPinyinBean.isNeedToPinyin()) {
                String target = baseIndexPinyinBean.getTarget();
                for (int i2 = 0; i2 < target.length(); i2++) {
                    sb.append(Pinyin.toPinyin(target.charAt(i2)).toUpperCase());
                }
                baseIndexPinyinBean.setBaseIndexPinyin(sb.toString());
            }
        }
        AppMethodBeat.o(6590);
        return this;
    }

    public a b(List<? extends BaseIndexPinyinBean> list) {
        AppMethodBeat.i(6592, false);
        if (list == null || list.isEmpty()) {
            AppMethodBeat.o(6592);
            return this;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            BaseIndexPinyinBean baseIndexPinyinBean = (BaseIndexPinyinBean) list.get(i);
            if (baseIndexPinyinBean.isNeedToPinyin()) {
                String substring = baseIndexPinyinBean.getBaseIndexPinyin().toString().substring(0, 1);
                if (substring.matches("[A-Z]")) {
                    baseIndexPinyinBean.setBaseIndexTag(substring);
                } else {
                    baseIndexPinyinBean.setBaseIndexTag("#");
                }
            }
        }
        AppMethodBeat.o(6592);
        return this;
    }

    public a c(List<? extends BaseIndexPinyinBean> list) {
        AppMethodBeat.i(6594, false);
        if (list == null || list.isEmpty()) {
            AppMethodBeat.o(6594);
            return this;
        }
        a(list);
        b(list);
        Collections.sort(list, new 1(this));
        AppMethodBeat.o(6594);
        return this;
    }

    public a a(List<? extends BaseIndexPinyinBean> list, List<String> list2) {
        AppMethodBeat.i(6596, false);
        if (list == null || list.isEmpty()) {
            AppMethodBeat.o(6596);
            return this;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String baseIndexTag = ((BaseIndexPinyinBean) list.get(i)).getBaseIndexTag();
            if (!list2.contains(baseIndexTag)) {
                list2.add(baseIndexTag);
            }
        }
        AppMethodBeat.o(6596);
        return this;
    }
}
