package cn.missfresh.buttomline.abtest.inf;

import cn.missfresh.buttomline.abtest.bean.Plan;
import java.util.List;

public interface Future {
    int get(String str, AbtestRequestCallback abtestRequestCallback);

    int immutableGet(String str);

    void requestServer(String str);

    void set(String str, String str2);

    void set(List<Plan> list);
}
