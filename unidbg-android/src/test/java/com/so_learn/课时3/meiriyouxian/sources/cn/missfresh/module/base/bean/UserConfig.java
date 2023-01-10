package cn.missfresh.module.base.bean;

import java.util.List;

public class UserConfig {
    public List<UserConfigList> userConfigList;

    public static class UserConfigList {
        public int business;
        public int statusId;
        public int typeId;
    }
}
