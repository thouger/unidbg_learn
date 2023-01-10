package cn.missfresh.sherlock.config;

public enum Type {
    NETWORK(1),
    CRASH(2),
    SCREENSHOT(3),
    START(4),
    TRACE(5),
    DROPFPS(6),
    METHOD(7),
    API(8),
    LOG(9),
    BUSINESS(11);
    
    private int value;

    private Type(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
