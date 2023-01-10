package com.sobot.chat.widget.timePicker.a;

/* compiled from: SobotNumericWheelAdapter */
public class a implements b {
    private int a;
    private int b;

    public a() {
        this(0, 9);
    }

    public a(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    @Override // com.sobot.chat.widget.timePicker.a.b
    public Object a(int i) {
        if (i < 0 || i >= a()) {
            return 0;
        }
        return Integer.valueOf(this.a + i);
    }

    @Override // com.sobot.chat.widget.timePicker.a.b
    public int a() {
        return (this.b - this.a) + 1;
    }

    @Override // com.sobot.chat.widget.timePicker.a.b
    public int a(Object obj) {
        try {
            return ((Integer) obj).intValue() - this.a;
        } catch (Exception unused) {
            return -1;
        }
    }
}
