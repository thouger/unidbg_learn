package com.sobot.chat.widget.emoji;

import android.content.Context;
import com.sobot.chat.utils.q;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public enum DisplayRules {
    QQBIAOQING0(0, 1, "expression_1", "[\u5fae\u7b11]", "[\u5fae\u7b11]"),
    QQBIAOQING1(0, 1, "expression_2", "[\u6487\u5634]", "[\u6487\u5634]"),
    QQBIAOQING2(0, 1, "expression_3", "[\u8272]", "[\u8272]"),
    QQBIAOQING3(0, 1, "expression_4", "[\u53d1\u5446]", "[\u53d1\u5446]"),
    QQBIAOQING4(0, 1, "expression_5", "[\u5f97\u610f]", "[\u5f97\u610f]"),
    QQBIAOQING5(0, 1, "expression_6", "[\u5927\u54ed]", "[\u5927\u54ed]"),
    QQBIAOQING6(0, 1, "expression_7", "[\u5bb3\u7f9e]", "[\u5bb3\u7f9e]"),
    QQBIAOQING7(0, 1, "expression_8", "[\u95ed\u5634]", "[\u95ed\u5634]"),
    QQBIAOQING8(0, 1, "expression_9", "[\u7761]", "[\u7761]"),
    QQBIAOQING9(0, 1, "expression_10", "[\u6d41\u6cea]", "[\u6d41\u6cea]"),
    QQBIAOQING10(0, 1, "expression_11", "[\u5c34\u5c2c]", "[\u5c34\u5c2c]"),
    QQBIAOQING11(0, 1, "expression_12", "[\u53d1\u6012]", "[\u53d1\u6012]"),
    QQBIAOQING12(0, 1, "expression_13", "[\u8c03\u76ae]", "[\u8c03\u76ae]"),
    QQBIAOQING13(0, 1, "expression_14", "[\u5472\u7259]", "[\u5472\u7259]"),
    QQBIAOQING14(0, 1, "expression_15", "[\u60ca\u8bb6]", "[\u60ca\u8bb6]"),
    QQBIAOQING15(0, 1, "expression_16", "[\u96be\u8fc7]", "[\u96be\u8fc7]"),
    QQBIAOQING16(0, 1, "expression_17", "[\u9177]", "[\u9177]"),
    QQBIAOQING17(0, 1, "expression_18", "[\u51b7\u6c57]", "[\u51b7\u6c57]"),
    QQBIAOQING18(0, 1, "expression_19", "[\u6293\u72c2]", "[\u6293\u72c2]"),
    QQBIAOQING19(0, 1, "expression_20", "[\u5410]", "[\u5410]"),
    QQBIAOQING20(0, 1, "expression_21", "[\u5077\u7b11]", "[\u5077\u7b11]"),
    QQBIAOQING21(0, 1, "expression_22", "[\u6109\u5feb]", "[\u6109\u5feb]"),
    QQBIAOQING22(0, 1, "expression_23", "[\u767d\u773c]", "[\u767d\u773c]"),
    QQBIAOQING23(0, 1, "expression_24", "[\u50b2\u6162]", "[\u50b2\u6162]"),
    QQBIAOQING24(0, 1, "expression_25", "[\u9965\u997f]", "[\u9965\u997f]"),
    QQBIAOQING25(0, 1, "expression_26", "[\u56f0]", "[\u56f0]"),
    QQBIAOQING26(0, 1, "expression_27", "[\u60ca\u6050]", "[\u60ca\u6050]"),
    QQBIAOQING27(0, 1, "expression_28", "[\u6d41\u6c57]", "[\u6d41\u6c57]"),
    QQBIAOQING28(0, 1, "expression_29", "[\u61a8\u7b11]", "[\u61a8\u7b11]"),
    QQBIAOQING29(0, 1, "expression_30", "[\u60a0\u95f2]", "[\u60a0\u95f2]"),
    QQBIAOQING30(0, 1, "expression_31", "[\u594b\u6597]", "[\u594b\u6597]"),
    QQBIAOQING31(0, 1, "expression_32", "[\u5492\u9a82]", "[\u5492\u9a82]"),
    QQBIAOQING32(0, 1, "expression_33", "[\u7591\u95ee]", "[\u7591\u95ee]"),
    QQBIAOQING33(0, 1, "expression_34", "[\u5618]", "[\u5618]"),
    QQBIAOQING34(0, 1, "expression_35", "[\u6655]", "[\u6655]"),
    QQBIAOQING35(0, 1, "expression_36", "[\u75af\u4e86]", "[\u75af\u4e86]"),
    QQBIAOQING36(0, 1, "expression_37", "[\u8870]", "[\u8870]"),
    QQBIAOQING37(0, 1, "expression_38", "[\u9ab7\u9ac5]", "[\u9ab7\u9ac5]"),
    QQBIAOQING38(0, 1, "expression_39", "[\u6572\u6253]", "[\u6572\u6253]"),
    QQBIAOQING39(0, 1, "expression_40", "[\u518d\u89c1]", "[\u518d\u89c1]"),
    QQBIAOQING40(0, 1, "expression_41", "[\u64e6\u6c57]", "[\u64e6\u6c57]"),
    QQBIAOQING41(0, 1, "expression_42", "[\u62a0\u9f3b]", "[\u62a0\u9f3b]"),
    QQBIAOQING42(0, 1, "expression_43", "[\u9f13\u638c]", "[\u9f13\u638c]"),
    QQBIAOQING43(0, 1, "expression_44", "[\u7cd7\u5927\u4e86]", "[\u7cd7\u5927\u4e86]"),
    QQBIAOQING44(0, 1, "expression_45", "[\u574f\u7b11]", "[\u574f\u7b11]"),
    QQBIAOQING45(0, 1, "expression_46", "[\u5de6\u54fc\u54fc]", "[\u5de6\u54fc\u54fc]"),
    QQBIAOQING46(0, 1, "expression_47", "[\u53f3\u54fc\u54fc]", "[\u53f3\u54fc\u54fc]"),
    QQBIAOQING47(0, 1, "expression_48", "[\u54c8\u6b20]", "[\u54c8\u6b20]"),
    QQBIAOQING48(0, 1, "expression_49", "[\u9119\u89c6]", "[\u9119\u89c6]"),
    QQBIAOQING49(0, 1, "expression_50", "[\u59d4\u5c48]", "[\u59d4\u5c48]"),
    QQBIAOQING50(0, 1, "expression_51", "[\u5feb\u54ed\u4e86]", "[\u5feb\u54ed\u4e86]"),
    QQBIAOQING51(0, 1, "expression_52", "[\u9634\u9669]", "[\u9634\u9669]"),
    QQBIAOQING52(0, 1, "expression_53", "[\u4eb2\u4eb2]", "[\u4eb2\u4eb2]"),
    QQBIAOQING53(0, 1, "expression_54", "[\u5413]", "[\u5413]"),
    QQBIAOQING54(0, 1, "expression_55", "[\u53ef\u601c]", "[\u53ef\u601c]"),
    QQBIAOQING55(0, 1, "expression_56", "[\u83dc\u5200]", "[\u83dc\u5200]"),
    QQBIAOQING56(0, 1, "expression_57", "[\u897f\u74dc]", "[\u897f\u74dc]"),
    QQBIAOQING57(0, 1, "expression_58", "[\u5564\u9152]", "[\u5564\u9152]"),
    QQBIAOQING58(0, 1, "expression_59", "[\u7bee\u7403]", "[\u7bee\u7403]"),
    QQBIAOQING59(0, 1, "expression_60", "[\u4e52\u4e53]", "[\u4e52\u4e53]"),
    QQBIAOQING60(0, 1, "expression_61", "[\u5496\u5561]", "[\u5496\u5561]"),
    QQBIAOQING61(0, 1, "expression_62", "[\u996d]", "[\u996d]"),
    QQBIAOQING62(0, 1, "expression_63", "[\u732a\u5934]", "[\u732a\u5934]"),
    QQBIAOQING63(0, 1, "expression_64", "[\u73ab\u7470]", "[\u73ab\u7470]"),
    QQBIAOQING64(0, 1, "expression_65", "[\u8c03\u8c22]", "[\u8c03\u8c22]"),
    QQBIAOQING65(0, 1, "expression_66", "[\u5634\u5507]", "[\u5634\u5507]"),
    QQBIAOQING66(0, 1, "expression_67", "[\u7231\u5fc3]", "[\u7231\u5fc3]"),
    QQBIAOQING67(0, 1, "expression_68", "[\u5fc3\u788e]", "[\u5fc3\u788e]"),
    QQBIAOQING68(0, 1, "expression_69", "[\u86cb\u7cd5]", "[\u86cb\u7cd5]"),
    QQBIAOQING69(0, 1, "expression_70", "[\u95ea\u7535]", "[\u95ea\u7535]"),
    QQBIAOQING70(0, 1, "expression_71", "[\u70b8\u5f39]", "[\u70b8\u5f39]"),
    QQBIAOQING71(0, 1, "expression_72", "[\u5200]", "[\u5200]"),
    QQBIAOQING72(0, 1, "expression_73", "[\u8db3\u7403]", "[\u8db3\u7403]"),
    QQBIAOQING73(0, 1, "expression_74", "[\u74e2\u866b]", "[\u74e2\u866b]"),
    QQBIAOQING74(0, 1, "expression_75", "[\u4fbf\u4fbf]", "[\u4fbf\u4fbf]"),
    QQBIAOQING75(0, 1, "expression_76", "[\u6708\u4eae]", "[\u6708\u4eae]"),
    QQBIAOQING76(0, 1, "expression_77", "[\u592a\u9633]", "[\u592a\u9633]"),
    QQBIAOQING77(0, 1, "expression_78", "[\u793c\u7269]", "[\u793c\u7269]"),
    QQBIAOQING78(0, 1, "expression_79", "[\u62e5\u62b1]", "[\u62e5\u62b1]"),
    QQBIAOQING79(0, 1, "expression_80", "[\u5f3a]", "[\u5f3a]"),
    QQBIAOQING80(0, 1, "expression_81", "[\u5f31]", "[\u5f31]"),
    QQBIAOQING81(0, 1, "expression_82", "[\u63e1\u624b]", "[\u63e1\u624b]"),
    QQBIAOQING82(0, 1, "expression_83", "[\u80dc\u5229]", "[\u80dc\u5229]"),
    QQBIAOQING83(0, 1, "expression_84", "[\u62b1\u62f3]", "[\u62b1\u62f3]"),
    QQBIAOQING84(0, 1, "expression_85", "[\u52fe\u5f15]", "[\u52fe\u5f15]"),
    QQBIAOQING85(0, 1, "expression_86", "[\u62f3\u5934]", "[\u62f3\u5934]"),
    QQBIAOQING86(0, 1, "expression_87", "[\u5dee\u52b2]", "[\u5dee\u52b2]"),
    QQBIAOQING87(0, 1, "expression_88", "[\u7231\u4f60]", "[\u7231\u4f60]"),
    QQBIAOQING88(0, 1, "expression_89", "[NO]", "[NO]"),
    QQBIAOQING89(0, 1, "expression_90", "[OK]", "[OK]"),
    QQBIAOQING90(0, 1, "expression_91", "[\u7231\u60c5]", "[\u7231\u60c5]"),
    QQBIAOQING91(0, 1, "expression_92", "[\u98de\u543b]", "[\u98de\u543b]"),
    QQBIAOQING92(0, 1, "expression_93", "[\u8df3\u8df3]", "[\u8df3\u8df3]"),
    QQBIAOQING93(0, 1, "expression_94", "[\u53d1\u6296]", "[\u53d1\u6296]"),
    QQBIAOQING94(0, 1, "expression_95", "[\u6004\u706b]", "[\u6004\u706b]"),
    QQBIAOQING95(0, 1, "expression_96", "[\u8f6c\u5708]", "[\u8f6c\u5708]"),
    QQBIAOQING96(0, 1, "expression_97", "[\u78d5\u5934]", "[\u78d5\u5934]"),
    QQBIAOQING97(0, 1, "expression_98", "[\u56de\u5934]", "[\u56de\u5934]"),
    QQBIAOQING98(0, 1, "expression_99", "[\u8df3\u7ef3]", "[\u8df3\u7ef3]"),
    QQBIAOQING99(0, 1, "expression_100", "[\u6295\u964d]", "[\u6295\u964d]"),
    QQBIAOQING100(0, 1, "expression_101", "[\u6fc0\u52a8]", "[\u6fc0\u52a8]"),
    QQBIAOQING101(0, 1, "expression_102", "[\u4e71\u821e]", "[\u4e71\u821e]"),
    QQBIAOQING102(0, 1, "expression_103", "[\u732e\u543b]", "[\u732e\u543b]"),
    QQBIAOQING103(0, 1, "expression_104", "[\u53f3\u592a\u6781]", "[\u53f3\u592a\u6781]"),
    QQBIAOQING104(0, 1, "expression_105", "[\u5de6\u592a\u6781]", "[\u5de6\u592a\u6781]");
    
    private static Map<String, Integer> sEmojiMap;
    private String emojiStr;
    private String remote;
    private String resName;
    private int type;
    private int value;

    private DisplayRules(int i, int i2, String str, String str2, String str3) {
        this.type = i;
        this.emojiStr = str2;
        this.value = i2;
        this.resName = str;
        this.remote = str3;
    }

    public String getRemote() {
        return this.remote;
    }

    public String getEmojiStr() {
        return this.emojiStr;
    }

    public int getValue() {
        return this.value;
    }

    public String getResName() {
        return this.resName;
    }

    public int getType() {
        return this.type;
    }

    public static Map<String, Integer> getMapAll(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (sEmojiMap == null) {
            sEmojiMap = new HashMap();
            DisplayRules[] values = values();
            for (DisplayRules displayRules : values) {
                int a = q.a(applicationContext, "drawable", displayRules.getResName());
                if (a != 0) {
                    sEmojiMap.put(displayRules.getEmojiStr(), Integer.valueOf(a));
                }
            }
        }
        return sEmojiMap;
    }

    public static ArrayList<a> getListAll(Context context) {
        Context applicationContext = context.getApplicationContext();
        ArrayList<a> arrayList = new ArrayList<>();
        DisplayRules[] values = values();
        for (DisplayRules displayRules : values) {
            int a = q.a(applicationContext, "drawable", displayRules.getResName());
            if (a != 0) {
                arrayList.add(new a(displayRules.getResName(), displayRules.getValue(), displayRules.getEmojiStr(), displayRules.getRemote(), a));
            }
        }
        return arrayList;
    }
}
