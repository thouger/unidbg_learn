package io.flutter.plugins.webviewflutter;

import android.hardware.display.DisplayManager;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

class DisplayListenerProxy {
    private static final String TAG = "DisplayListenerProxy";
    private ArrayList<DisplayManager.DisplayListener> listenersBeforeWebView;

    DisplayListenerProxy() {
    }

    /* access modifiers changed from: package-private */
    public void onPreWebViewInitialization(DisplayManager displayManager) {
        this.listenersBeforeWebView = yoinkDisplayListeners(displayManager);
    }

    /* access modifiers changed from: package-private */
    public void onPostWebViewInitialization(DisplayManager displayManager) {
        ArrayList<DisplayManager.DisplayListener> yoinkDisplayListeners = yoinkDisplayListeners(displayManager);
        yoinkDisplayListeners.removeAll(this.listenersBeforeWebView);
        if (!yoinkDisplayListeners.isEmpty()) {
            Iterator<DisplayManager.DisplayListener> it2 = yoinkDisplayListeners.iterator();
            while (it2.hasNext()) {
                displayManager.unregisterDisplayListener(it2.next());
                displayManager.registerDisplayListener(new AnonymousClass1(yoinkDisplayListeners, displayManager), null);
            }
        }
    }

    /* renamed from: io.flutter.plugins.webviewflutter.DisplayListenerProxy$1  reason: invalid class name */
    class AnonymousClass1 implements DisplayManager.DisplayListener {
        final /* synthetic */ DisplayManager val$displayManager;
        final /* synthetic */ ArrayList val$webViewListeners;

        AnonymousClass1(ArrayList arrayList, DisplayManager displayManager) {
            this.val$webViewListeners = arrayList;
            this.val$displayManager = displayManager;
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i) {
            Iterator it2 = this.val$webViewListeners.iterator();
            while (it2.hasNext()) {
                ((DisplayManager.DisplayListener) it2.next()).onDisplayAdded(i);
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i) {
            Iterator it2 = this.val$webViewListeners.iterator();
            while (it2.hasNext()) {
                ((DisplayManager.DisplayListener) it2.next()).onDisplayRemoved(i);
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i) {
            if (this.val$displayManager.getDisplay(i) != null) {
                Iterator it2 = this.val$webViewListeners.iterator();
                while (it2.hasNext()) {
                    ((DisplayManager.DisplayListener) it2.next()).onDisplayChanged(i);
                }
            }
        }
    }

    private static ArrayList<DisplayManager.DisplayListener> yoinkDisplayListeners(DisplayManager displayManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return new ArrayList<>();
        }
        try {
            Field declaredField = DisplayManager.class.getDeclaredField("mGlobal");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(displayManager);
            Field declaredField2 = obj.getClass().getDeclaredField("mDisplayListeners");
            declaredField2.setAccessible(true);
            Field field = null;
            ArrayList<DisplayManager.DisplayListener> arrayList = new ArrayList<>();
            Iterator it2 = ((ArrayList) declaredField2.get(obj)).iterator();
            while (it2.hasNext()) {
                Object next = it2.next();
                if (field == null) {
                    field = next.getClass().getField("mListener");
                    field.setAccessible(true);
                }
                arrayList.add((DisplayManager.DisplayListener) field.get(next));
            }
            return arrayList;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Log.w(TAG, "Could not extract WebView's display listeners. " + e);
            return new ArrayList<>();
        }
    }
}
