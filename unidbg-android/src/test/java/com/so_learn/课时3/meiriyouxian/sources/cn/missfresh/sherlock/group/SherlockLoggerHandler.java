package cn.missfresh.sherlock.group;

import cn.missfresh.sherlock.tool.j;

public class SherlockLoggerHandler {
    public static SherlockLoggerHandler CUSTOM_IMPL = DEFAULT_IMPL;
    public static SherlockLoggerHandler DEFAULT_IMPL = new AnonymousClass1();

    /* renamed from: cn.missfresh.sherlock.group.SherlockLoggerHandler$1  reason: invalid class name */
    static class AnonymousClass1 extends SherlockLoggerHandler {
        AnonymousClass1() {
        }

        @Override // cn.missfresh.sherlock.group.SherlockLoggerHandler
        public void log(String str, String str2, long j, String str3) {
            j.b(str, str3);
        }
    }

    public static void installLogImpl(SherlockLoggerHandler sherlockLoggerHandler) {
        CUSTOM_IMPL = sherlockLoggerHandler;
    }

    /* access modifiers changed from: protected */
    public void log(String str, String str2, long j, String str3) {
    }
}
