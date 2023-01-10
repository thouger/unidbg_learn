package com.sobot.chat.widget.emoji;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public enum DisplayEmojiRules {
    QQBIAOQING0("\ud83d\ude03", "SMILING FACE WITH OPEN MOUTH"),
    QQBIAOQING1("\ud83d\ude04", "SPACE"),
    QQBIAOQING2("\ud83d\ude01", "SMILING FACE WITH OPEN MOUTH AND SMILING EYES"),
    QQBIAOQING3("\ud83d\ude06", "GRINNING FACE WITH SMILING EYES"),
    QQBIAOQING4("\ud83d\ude05", "SMILING FACE WITH OPEN MOUTH AND TIGHTLY-CLOSED EYES"),
    QQBIAOQING5("\ud83e\udd23", "SMILING FACE WITH OPEN MOUTH AND COLD SWEAT"),
    QQBIAOQING6("\ud83d\ude1d", "ROLLING ON THE FLOOR LAUGHING"),
    QQBIAOQING7("\ud83d\ude42", "FACE WITH TEARS OF JOY"),
    QQBIAOQING8("\ud83d\ude09", "SLIGHTLY SMILING FACE"),
    QQBIAOQING9("\ud83d\ude0a", "WINKING FACE"),
    QQBIAOQING10("\ud83d\ude07", "SMILING FACE WITH SMILING EYES"),
    QQBIAOQING11("\ud83d\ude0d", "SMILING FACE WITH HALO"),
    QQBIAOQING12("\ud83e\udd29", "SMILING FACE WITH HEART-SHAPED EYES"),
    QQBIAOQING13("\ud83d\ude18", "GRINNING FACE WITH STAR EYES"),
    QQBIAOQING14("\ud83d\ude1a", "FACE THROWING A KISS"),
    QQBIAOQING15("\ud83d\ude19", "KISSING FACE WITH CLOSED EYES"),
    QQBIAOQING16("\ud83d\ude0b", "KISSING FACE WITH SMILING EYES"),
    QQBIAOQING17("\ud83d\ude1c", "FACE SAVOURING DELICIOUS FOOD"),
    QQBIAOQING18("\ud83d\ude1d", "FACE WITH STUCK-OUT TONGUE AND WINKING EYE"),
    QQBIAOQING19("\ud83e\udd17", "FACE WITH STUCK-OUT TONGUE AND TIGHTLY-CLOSED EYES"),
    QQBIAOQING20("\ud83e\udd2d", "HUGGING FACE"),
    QQBIAOQING21("\ud83e\udd14", "SMILING FACE WITH SMILING EYES AND HAND COVERING MOUTH"),
    QQBIAOQING22("\ud83e\udd10", "THINKING FACE"),
    QQBIAOQING23("\ud83d\ude11", "ZIPPER-MOUTH FACE"),
    QQBIAOQING24("\ud83d\ude0f", "EXPRESSIONLESS FACE"),
    QQBIAOQING25("\ud83d\ude12", "SMIRKING FACE"),
    QQBIAOQING26("\ud83d\ude0c", "UNAMUSED FACE"),
    QQBIAOQING27("\ud83d\ude14", "RELIEVED FACE"),
    QQBIAOQING28("\ud83d\ude37", "PENSIVE FACE"),
    QQBIAOQING29("\ud83e\udd12", "FACE WITH MEDICAL MASK"),
    QQBIAOQING30("\ud83d\ude35", "FACE WITH THERMOMETER"),
    QQBIAOQING31("\ud83e\udd20", "DIZZY FACE"),
    QQBIAOQING32("\ud83d\ude0e", "FACE WITH COWBOY HAT"),
    QQBIAOQING33("\ud83e\udd13", "SMILING FACE WITH SUNGLASSES"),
    QQBIAOQING34("\ud83d\ude33", "NERD FACE"),
    QQBIAOQING35("\ud83d\ude28", "FLUSHED FACE"),
    QQBIAOQING36("\ud83d\ude30", "FACE WITH OPEN MOUTH AND COLD SWEAT"),
    QQBIAOQING37("\ud83d\ude25", "DISAPPOINTED BUT RELIEVED FACE"),
    QQBIAOQING38("\ud83d\ude22", "CRYING FACE"),
    QQBIAOQING39("\ud83d\ude2d", "LOUDLY CRYING FACE"),
    QQBIAOQING40("\ud83d\ude31", "FACE SCREAMING IN FEAR"),
    QQBIAOQING41("\ud83d\ude16", "CONFOUNDED FACE"),
    QQBIAOQING42("\ud83d\ude23", "PERSEVERING FACE"),
    QQBIAOQING43("\ud83d\ude13", "FACE WITH COLD SWEAT"),
    QQBIAOQING44("\ud83d\ude20", "ANGRY FACE"),
    QQBIAOQING45("\ud83d\udc4b", "WAVING HAND SIGN"),
    QQBIAOQING46("\ud83d\udc4c", "OK HAND SIGN"),
    QQBIAOQING47("\u270c", "VICTORY HAND"),
    QQBIAOQING48("\ud83e\udd1f", "I LOVE YOU HAND SIGN"),
    QQBIAOQING49("\ud83d\udc4d", "THUMBS UP SIGN"),
    QQBIAOQING50("\ud83d\udc4f", "CLAPPING HANDS SIGN"),
    QQBIAOQING51("\ud83e\udd1d", "HANDSHAKE"),
    QQBIAOQING52("\ud83d\ude4f", "PERSON WITH FOLDED HANDS"),
    QQBIAOQING53("\ud83d\udcaa", "FLEXED BICEPS"),
    QQBIAOQING54("\ud83d\ude47", "PERSON BOWING DEEPLY"),
    QQBIAOQING55("\ud83d\udc2e", "COW FACE"),
    QQBIAOQING56("\ud83c\udf39", "ROSE"),
    QQBIAOQING57("\ud83e\udd40", "WILTED FLOWER"),
    QQBIAOQING58("\ud83d\udc8b", "KISS MARK"),
    QQBIAOQING59("\u2764\ufe0f", "HEAVY BLACK HEART"),
    QQBIAOQING60("\ud83d\udc94", "BROKEN HEART"),
    QQBIAOQING61("\u2b50", "WHITE MEDIUM STAR"),
    QQBIAOQING62("\ud83c\udf89", "PARTY POPPER"),
    QQBIAOQING63("\ud83c\udf7a", "BEER MUG"),
    QQBIAOQING64("\ud83c\udf81", "WRAPPED PRESENT");
    
    private static Map<String, String> sEmojiMap;
    private String emojiCode;
    private String emojiDes;

    private DisplayEmojiRules(String str, String str2) {
        this.emojiDes = str2;
        this.emojiCode = str;
    }

    public String getEmojiDes() {
        return this.emojiDes;
    }

    public void setEmojiDes(String str) {
        this.emojiDes = str;
    }

    public String getEmojiCode() {
        return this.emojiCode;
    }

    public void setEmojiCode(String str) {
        this.emojiCode = str;
    }

    public static Map<String, String> getMapAll(Context context) {
        if (sEmojiMap == null) {
            sEmojiMap = new HashMap();
            DisplayEmojiRules[] values = values();
            for (DisplayEmojiRules displayEmojiRules : values) {
                sEmojiMap.put(displayEmojiRules.getEmojiCode(), displayEmojiRules.getEmojiDes());
            }
        }
        return sEmojiMap;
    }

    public static ArrayList<b> getListAll(Context context) {
        ArrayList<b> arrayList = new ArrayList<>();
        DisplayEmojiRules[] values = values();
        for (DisplayEmojiRules displayEmojiRules : values) {
            arrayList.add(new b(displayEmojiRules.getEmojiCode(), displayEmojiRules.getEmojiDes()));
        }
        return arrayList;
    }
}
