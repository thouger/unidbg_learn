package cn.missfresh.module.base.helper;

import android.text.TextUtils;
import cn.missfresh.module.base.bean.BaseSkuBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SkuUtil */
public class l {
    public static Map<String, BaseSkuBean> a(Map<String, BaseSkuBean> map) {
        AppMethodBeat.i(13166, false);
        HashMap hashMap = new HashMap();
        for (String str : map.keySet()) {
            BaseSkuBean baseSkuBean = map.get(str);
            String[] split = str.split(";");
            ArrayList<ArrayList<String>> a = a(split);
            if (a != null) {
                for (int i = 0; i < a.size(); i++) {
                    a(hashMap, a.get(i), baseSkuBean);
                }
            }
            hashMap.put(TextUtils.join(";", split), baseSkuBean);
        }
        AppMethodBeat.o(13166);
        return hashMap;
    }

    private static ArrayList<ArrayList<String>> a(String[] strArr) {
        AppMethodBeat.i(13168, false);
        if (strArr == null || strArr.length <= 0) {
            ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
            AppMethodBeat.o(13168);
            return arrayList;
        }
        int length = strArr.length;
        ArrayList<ArrayList<String>> arrayList2 = new ArrayList<>();
        for (int i = 1; i < length; i++) {
            ArrayList<Integer[]> a = a(length, i);
            for (int i2 = 0; i2 < a.size(); i2++) {
                Integer[] numArr = a.get(i2);
                ArrayList<String> arrayList3 = new ArrayList<>();
                for (int i3 = 0; i3 < numArr.length; i3++) {
                    if (numArr[i3].intValue() == 1) {
                        arrayList3.add(strArr[i3]);
                    }
                }
                arrayList2.add(arrayList3);
            }
        }
        AppMethodBeat.o(13168);
        return arrayList2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.ArrayList<java.lang.Integer[]> */
    /* JADX WARN: Multi-variable type inference failed */
    private static ArrayList<Integer[]> a(int i, int i2) {
        AppMethodBeat.i(13171, false);
        if (i2 <= 0) {
            ArrayList<Integer[]> arrayList = new ArrayList<>();
            AppMethodBeat.o(13171);
            return arrayList;
        }
        ArrayList<Integer[]> arrayList2 = new ArrayList<>();
        Integer[] numArr = new Integer[i];
        int i3 = 0;
        while (true) {
            int i4 = 1;
            if (i3 >= i) {
                break;
            }
            if (i3 >= i2) {
                i4 = 0;
            }
            numArr[i3] = Integer.valueOf(i4);
            i3++;
        }
        arrayList2.add(numArr.clone());
        boolean z = true;
        while (z) {
            int i5 = 0;
            int i6 = 0;
            while (true) {
                if (i5 >= i - 1) {
                    break;
                }
                if (numArr[i5].intValue() == 1) {
                    int i7 = i5 + 1;
                    if (numArr[i7].intValue() == 0) {
                        int i8 = 0;
                        while (i8 < i5) {
                            numArr[i8] = Integer.valueOf(i8 < i6 ? 1 : 0);
                            i8++;
                        }
                        numArr[i5] = 0;
                        numArr[i7] = 1;
                        Integer[] numArr2 = (Integer[]) numArr.clone();
                        arrayList2.add(numArr2);
                        if (!TextUtils.join("", numArr2).substring(i - i2).contains("0")) {
                            z = false;
                        }
                    }
                }
                if (numArr[i5].intValue() == 1) {
                    i6++;
                }
                i5++;
            }
        }
        AppMethodBeat.o(13171);
        return arrayList2;
    }

    private static void a(HashMap<String, BaseSkuBean> hashMap, ArrayList<String> arrayList, BaseSkuBean baseSkuBean) {
        AppMethodBeat.i(13173, false);
        String join = TextUtils.join(";", arrayList);
        if (hashMap.keySet().contains(join)) {
            hashMap.get(join).setStock(hashMap.get(join).getStock() + baseSkuBean.getStock());
            hashMap.get(join).setPricePro(baseSkuBean.getPricePro());
        } else {
            hashMap.put(join, new BaseSkuBean(baseSkuBean.getPricePro(), baseSkuBean.getStock()));
        }
        AppMethodBeat.o(13173);
    }
}
