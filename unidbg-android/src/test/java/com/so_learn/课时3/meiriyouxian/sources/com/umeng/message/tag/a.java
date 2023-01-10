package com.umeng.message.tag;

import com.umeng.commonsdk.debug.UMLog;

/* compiled from: TagLengthFilter */
public class a implements TagFilter {
    private static final String a = a.class.getName();
    private static int b = 256;

    @Override // com.umeng.message.tag.TagFilter
    public boolean filter(String str) {
        int i;
        if (str == null || "".equals(str.trim())) {
            return false;
        }
        if (str == null || str.length() <= (i = b)) {
            return true;
        }
        UMLog.mutlInfo(a, 0, String.format("The length of %s exceeds allowed max length %i", str, Integer.valueOf(i)));
        return false;
    }
}
