package io.flutter.embedding.android;

import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import io.flutter.Log;
import io.flutter.embedding.engine.systemchannels.KeyEventChannel;
import io.flutter.plugin.editing.TextInputPlugin;
import java.util.ArrayDeque;
import java.util.Deque;

public class AndroidKeyProcessor {
    private static final String TAG = "AndroidKeyProcessor";
    private int combiningCharacter;
    private EventResponder eventResponder;
    private final KeyEventChannel keyEventChannel;
    private final TextInputPlugin textInputPlugin;

    public AndroidKeyProcessor(View view, KeyEventChannel keyEventChannel, TextInputPlugin textInputPlugin) {
        this.keyEventChannel = keyEventChannel;
        this.textInputPlugin = textInputPlugin;
        textInputPlugin.setKeyEventProcessor(this);
        this.eventResponder = new EventResponder(view, textInputPlugin);
        this.keyEventChannel.setEventResponseHandler(this.eventResponder);
    }

    public void destroy() {
        this.keyEventChannel.setEventResponseHandler(null);
    }

    public boolean onKeyEvent(KeyEvent keyEvent) {
        int action = keyEvent.getAction();
        if (action != 0 && action != 1) {
            return false;
        }
        if (isPendingEvent(keyEvent)) {
            this.eventResponder.removePendingEvent(keyEvent);
            return false;
        }
        KeyEventChannel.FlutterKeyEvent flutterKeyEvent = new KeyEventChannel.FlutterKeyEvent(keyEvent, applyCombiningCharacterToBaseCharacter(keyEvent.getUnicodeChar()));
        this.eventResponder.addEvent(keyEvent);
        if (action == 0) {
            this.keyEventChannel.keyDown(flutterKeyEvent);
        } else {
            this.keyEventChannel.keyUp(flutterKeyEvent);
        }
        return true;
    }

    public boolean isPendingEvent(KeyEvent keyEvent) {
        return this.eventResponder.findPendingEvent(keyEvent) != null;
    }

    private Character applyCombiningCharacterToBaseCharacter(int i) {
        if (i == 0) {
            return null;
        }
        char c = (char) i;
        if ((Integer.MIN_VALUE & i) != 0) {
            int i2 = i & Integer.MAX_VALUE;
            int i3 = this.combiningCharacter;
            if (i3 != 0) {
                this.combiningCharacter = KeyCharacterMap.getDeadChar(i3, i2);
            } else {
                this.combiningCharacter = i2;
            }
        } else {
            int i4 = this.combiningCharacter;
            if (i4 != 0) {
                int deadChar = KeyCharacterMap.getDeadChar(i4, i);
                if (deadChar > 0) {
                    c = (char) deadChar;
                }
                this.combiningCharacter = 0;
            }
        }
        return Character.valueOf(c);
    }

    /* access modifiers changed from: private */
    public static class EventResponder implements KeyEventChannel.EventResponseHandler {
        private static final long MAX_PENDING_EVENTS = 1000;
        final Deque<KeyEvent> pendingEvents = new ArrayDeque();
        private final TextInputPlugin textInputPlugin;
        private final View view;

        public EventResponder(View view, TextInputPlugin textInputPlugin) {
            this.view = view;
            this.textInputPlugin = textInputPlugin;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removePendingEvent(KeyEvent keyEvent) {
            this.pendingEvents.remove(keyEvent);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private KeyEvent findPendingEvent(KeyEvent keyEvent) {
            for (KeyEvent keyEvent2 : this.pendingEvents) {
                if (keyEvent2 == keyEvent) {
                    return keyEvent2;
                }
            }
            return null;
        }

        @Override // io.flutter.embedding.engine.systemchannels.KeyEventChannel.EventResponseHandler
        public void onKeyEventHandled(KeyEvent keyEvent) {
            removePendingEvent(keyEvent);
        }

        @Override // io.flutter.embedding.engine.systemchannels.KeyEventChannel.EventResponseHandler
        public void onKeyEventNotHandled(KeyEvent keyEvent) {
            redispatchKeyEvent(findPendingEvent(keyEvent));
        }

        public void addEvent(KeyEvent keyEvent) {
            this.pendingEvents.addLast(keyEvent);
            if (((long) this.pendingEvents.size()) > 1000) {
                Log.e(AndroidKeyProcessor.TAG, "There are " + this.pendingEvents.size() + " keyboard events that have not yet received a response. Are responses being sent?");
            }
        }

        private void redispatchKeyEvent(KeyEvent keyEvent) {
            if (!this.textInputPlugin.getInputMethodManager().isAcceptingText() || this.textInputPlugin.getLastInputConnection() == null || !this.textInputPlugin.getLastInputConnection().sendKeyEvent(keyEvent)) {
                View view = this.view;
                if (view != null) {
                    view.getRootView().dispatchKeyEvent(keyEvent);
                    return;
                }
                return;
            }
            removePendingEvent(keyEvent);
        }
    }
}
