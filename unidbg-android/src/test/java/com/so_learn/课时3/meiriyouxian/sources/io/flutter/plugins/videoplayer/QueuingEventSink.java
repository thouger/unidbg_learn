package io.flutter.plugins.videoplayer;

import io.flutter.plugin.common.EventChannel;
import java.util.ArrayList;
import java.util.Iterator;

/* access modifiers changed from: package-private */
public final class QueuingEventSink implements EventChannel.EventSink {
    private EventChannel.EventSink delegate;
    private boolean done = false;
    private ArrayList<Object> eventQueue = new ArrayList<>();

    QueuingEventSink() {
    }

    public void setDelegate(EventChannel.EventSink eventSink) {
        this.delegate = eventSink;
        maybeFlush();
    }

    @Override // io.flutter.plugin.common.EventChannel.EventSink
    public void endOfStream() {
        enqueue(new EndOfStreamEvent());
        maybeFlush();
        this.done = true;
    }

    @Override // io.flutter.plugin.common.EventChannel.EventSink
    public void error(String str, String str2, Object obj) {
        enqueue(new ErrorEvent(str, str2, obj));
        maybeFlush();
    }

    @Override // io.flutter.plugin.common.EventChannel.EventSink
    public void success(Object obj) {
        enqueue(obj);
        maybeFlush();
    }

    private void enqueue(Object obj) {
        if (!this.done) {
            this.eventQueue.add(obj);
        }
    }

    private void maybeFlush() {
        if (this.delegate != null) {
            Iterator<Object> it2 = this.eventQueue.iterator();
            while (it2.hasNext()) {
                Object next = it2.next();
                if (next instanceof EndOfStreamEvent) {
                    this.delegate.endOfStream();
                } else if (next instanceof ErrorEvent) {
                    ErrorEvent errorEvent = (ErrorEvent) next;
                    this.delegate.error(errorEvent.code, errorEvent.message, errorEvent.details);
                } else {
                    this.delegate.success(next);
                }
            }
            this.eventQueue.clear();
        }
    }

    /* access modifiers changed from: private */
    public static class EndOfStreamEvent {
        private EndOfStreamEvent() {
        }
    }

    /* access modifiers changed from: private */
    public static class ErrorEvent {
        String code;
        Object details;
        String message;

        ErrorEvent(String str, String str2, Object obj) {
            this.code = str;
            this.message = str2;
            this.details = obj;
        }
    }
}
