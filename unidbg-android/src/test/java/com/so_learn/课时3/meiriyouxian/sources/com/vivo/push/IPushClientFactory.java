package com.vivo.push;

import android.content.Intent;
import com.vivo.push.c.o;

public interface IPushClientFactory {
    o createReceiveTask(g gVar);

    g createReceiverCommand(Intent intent);

    e createTask(g gVar);
}
