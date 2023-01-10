package cn.missfresh.sherlock.group;

import java.util.Arrays;

public class ParameterPrinter {
    private String divider = ", ";
    private int paramIndex = 0;
    private StringBuilder result = new StringBuilder();
    private String tag = "";

    public ParameterPrinter(String str, String str2) {
        this.tag = str;
        StringBuilder sb = this.result;
        sb.append("\u21e2 ");
        sb.append(str2);
        sb.append("[");
    }

    private String arrayToString(Object obj) {
        if (obj instanceof Object[]) {
            return Arrays.deepToString((Object[]) obj);
        }
        if (obj instanceof int[]) {
            return Arrays.toString((int[]) obj);
        }
        if (obj instanceof char[]) {
            return Arrays.toString((char[]) obj);
        }
        if (obj instanceof boolean[]) {
            return Arrays.toString((boolean[]) obj);
        }
        if (obj instanceof byte[]) {
            return Arrays.toString((byte[]) obj);
        }
        if (obj instanceof long[]) {
            return Arrays.toString((long[]) obj);
        }
        if (obj instanceof double[]) {
            return Arrays.toString((double[]) obj);
        }
        if (obj instanceof float[]) {
            return Arrays.toString((float[]) obj);
        }
        return obj instanceof short[] ? Arrays.toString((short[]) obj) : "Unknown type array";
    }

    public ParameterPrinter append(String str, int i) {
        int i2 = this.paramIndex;
        this.paramIndex = i2 + 1;
        if (i2 != 0) {
            this.result.append(this.divider);
        }
        this.result.append(String.format("%s=\"%s\"", str, Integer.valueOf(i)));
        return this;
    }

    public void printWithCustomLogger() {
        this.result.append("]");
        SherlockLoggerHandler.CUSTOM_IMPL.log(this.tag, null, 0, this.result.toString());
    }

    public ParameterPrinter append(String str, boolean z) {
        int i = this.paramIndex;
        this.paramIndex = i + 1;
        if (i != 0) {
            this.result.append(this.divider);
        }
        this.result.append(String.format("%s=\"%s\"", str, Boolean.valueOf(z)));
        return this;
    }

    public ParameterPrinter append(String str, short s) {
        int i = this.paramIndex;
        this.paramIndex = i + 1;
        if (i != 0) {
            this.result.append(this.divider);
        }
        this.result.append(String.format("%s=\"%s\"", str, Short.valueOf(s)));
        return this;
    }

    public ParameterPrinter append(String str, byte b) {
        int i = this.paramIndex;
        this.paramIndex = i + 1;
        if (i != 0) {
            this.result.append(this.divider);
        }
        this.result.append(String.format("%s=\"%s\"", str, Byte.valueOf(b)));
        return this;
    }

    public ParameterPrinter append(String str, char c) {
        int i = this.paramIndex;
        this.paramIndex = i + 1;
        if (i != 0) {
            this.result.append(this.divider);
        }
        this.result.append(String.format("%s=\"%s\"", str, Character.valueOf(c)));
        return this;
    }

    public ParameterPrinter append(String str, long j) {
        int i = this.paramIndex;
        this.paramIndex = i + 1;
        if (i != 0) {
            this.result.append(this.divider);
        }
        this.result.append(String.format("%s=\"%s\"", str, Long.valueOf(j)));
        return this;
    }

    public ParameterPrinter append(String str, double d) {
        int i = this.paramIndex;
        this.paramIndex = i + 1;
        if (i != 0) {
            this.result.append(this.divider);
        }
        this.result.append(String.format("%s=\"%s\"", str, Double.valueOf(d)));
        return this;
    }

    public ParameterPrinter append(String str, float f) {
        int i = this.paramIndex;
        this.paramIndex = i + 1;
        if (i != 0) {
            this.result.append(this.divider);
        }
        this.result.append(String.format("%s=\"%s\"", str, Float.valueOf(f)));
        return this;
    }

    public ParameterPrinter append(String str, Object obj) {
        int i = this.paramIndex;
        this.paramIndex = i + 1;
        if (i != 0) {
            this.result.append(this.divider);
        }
        if (obj == null || !obj.getClass().isArray()) {
            this.result.append(String.format("%s=\"%s\"", str, obj));
        } else {
            this.result.append(String.format("%s=\"%s\"", str, arrayToString(obj)));
        }
        return this;
    }
}
