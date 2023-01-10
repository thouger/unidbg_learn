package io.flutter.plugin.editing;

import io.flutter.embedding.engine.FlutterJNI;

class FlutterTextUtils {
    public static final int CANCEL_TAG = 917631;
    public static final int CARRIAGE_RETURN = 13;
    public static final int COMBINING_ENCLOSING_KEYCAP = 8419;
    public static final int LINE_FEED = 10;
    public static final int ZERO_WIDTH_JOINER = 8205;
    private final FlutterJNI flutterJNI;

    public boolean isKeycapBase(int i) {
        return (48 <= i && i <= 57) || i == 35 || i == 42;
    }

    public boolean isTagSpecChar(int i) {
        return 917536 <= i && i <= 917630;
    }

    public FlutterTextUtils(FlutterJNI flutterJNI) {
        this.flutterJNI = flutterJNI;
    }

    public boolean isEmoji(int i) {
        return this.flutterJNI.nativeFlutterTextUtilsIsEmoji(i);
    }

    public boolean isEmojiModifier(int i) {
        return this.flutterJNI.nativeFlutterTextUtilsIsEmojiModifier(i);
    }

    public boolean isEmojiModifierBase(int i) {
        return this.flutterJNI.nativeFlutterTextUtilsIsEmojiModifierBase(i);
    }

    public boolean isVariationSelector(int i) {
        return this.flutterJNI.nativeFlutterTextUtilsIsVariationSelector(i);
    }

    public boolean isRegionalIndicatorSymbol(int i) {
        return this.flutterJNI.nativeFlutterTextUtilsIsRegionalIndicator(i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0158 A[EDGE_INSN: B:100:0x0158->B:93:0x0158 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0150  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getOffsetBefore(java.lang.CharSequence r9, int r10) {
        /*
        // Method dump skipped, instructions count: 346
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugin.editing.FlutterTextUtils.getOffsetBefore(java.lang.CharSequence, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:87:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0148 A[EDGE_INSN: B:99:0x0148->B:90:0x0148 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getOffsetAfter(java.lang.CharSequence r10, int r11) {
        /*
        // Method dump skipped, instructions count: 330
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugin.editing.FlutterTextUtils.getOffsetAfter(java.lang.CharSequence, int):int");
    }
}
