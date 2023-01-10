package cn.missfresh.buttomline.abtest.inf;

public interface ABConfig {
    public static final int FALSE = 0;
    public static final int TRUE = 1;

    String[] getCaseIds();

    String getTag();
}
