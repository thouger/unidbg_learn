package com.umeng.message;

import com.umeng.message.entity.UNotificationItem;
import java.util.LinkedList;

public class MessageNotificationQueue {
    private static MessageNotificationQueue b;
    private LinkedList<UNotificationItem> a = new LinkedList<>();

    private MessageNotificationQueue() {
    }

    public static synchronized MessageNotificationQueue getInstance() {
        MessageNotificationQueue messageNotificationQueue;
        synchronized (MessageNotificationQueue.class) {
            if (b == null) {
                b = new MessageNotificationQueue();
            }
            messageNotificationQueue = b;
        }
        return messageNotificationQueue;
    }

    public void addLast(UNotificationItem uNotificationItem) {
        this.a.addLast(uNotificationItem);
    }

    public UNotificationItem pollFirst() {
        return this.a.pollFirst();
    }

    public void remove(UNotificationItem uNotificationItem) {
        this.a.remove(uNotificationItem);
    }

    public int size() {
        return this.a.size();
    }

    public LinkedList<UNotificationItem> getQueue() {
        return this.a;
    }
}
