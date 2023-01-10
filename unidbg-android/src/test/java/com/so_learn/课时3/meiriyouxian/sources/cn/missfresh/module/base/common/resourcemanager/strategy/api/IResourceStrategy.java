package cn.missfresh.module.base.common.resourcemanager.strategy.api;

import cn.missfresh.module.base.common.resourcemanager.bean.SkipBean;
import java.util.Map;

public interface IResourceStrategy {
    void skip(SkipBean skipBean, Map<String, Object> map);
}
