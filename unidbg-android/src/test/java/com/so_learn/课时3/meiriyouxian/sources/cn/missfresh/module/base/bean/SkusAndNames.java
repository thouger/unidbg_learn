package cn.missfresh.module.base.bean;

import java.util.List;

public class SkusAndNames {
    public final List<String> names;
    public final List<Integer> quantity;
    public final List<String> skus;

    public SkusAndNames(List<String> list, List<String> list2, List<Integer> list3) {
        this.skus = list;
        this.names = list2;
        this.quantity = list3;
    }
}
