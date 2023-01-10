package com.sobot.chat.listener;

import android.view.View;
import java.util.Calendar;

public abstract class NoDoubleClickListener implements View.OnClickListener {
    private long a = 0;

    public abstract void a(View view);

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (timeInMillis - this.a > 1000) {
            this.a = timeInMillis;
            a(view);
        }
    }
}
