package com.sobot.chat.camera.c;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.WindowManager;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* compiled from: CameraParamUtil */
public class c {
    private static c b;
    private a a = new a();

    private c() {
    }

    public static c a() {
        c cVar = b;
        if (cVar != null) {
            return cVar;
        }
        b = new c();
        return b;
    }

    public Camera.Size a(List<Camera.Size> list, int i, float f) {
        Collections.sort(list, this.a);
        Iterator<Camera.Size> it2 = list.iterator();
        int i2 = 0;
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Camera.Size next = it2.next();
            if (next.width > i && a(next, f)) {
                Log.i("JCameraView", "MakeSure Preview :w = " + next.width + " h = " + next.height);
                break;
            }
            i2++;
        }
        if (i2 == list.size()) {
            return a(list, f);
        }
        return list.get(i2);
    }

    public Camera.Size b(List<Camera.Size> list, int i, float f) {
        Collections.sort(list, this.a);
        Iterator<Camera.Size> it2 = list.iterator();
        int i2 = 0;
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Camera.Size next = it2.next();
            if (next.width > i && a(next, f)) {
                Log.i("JCameraView", "MakeSure Picture :w = " + next.width + " h = " + next.height);
                break;
            }
            i2++;
        }
        if (i2 == list.size()) {
            return a(list, f);
        }
        return list.get(i2);
    }

    private Camera.Size a(List<Camera.Size> list, float f) {
        float f2 = 100.0f;
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            Camera.Size size = list.get(i2);
            float f3 = f - (((float) size.width) / ((float) size.height));
            if (Math.abs(f3) < f2) {
                f2 = Math.abs(f3);
                i = i2;
            }
        }
        return list.get(i);
    }

    private boolean a(Camera.Size size, float f) {
        return ((double) Math.abs((((float) size.width) / ((float) size.height)) - f)) <= 0.2d;
    }

    public boolean a(List<String> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(list.get(i))) {
                Log.i("JCameraView", "FocusMode supported " + str);
                return true;
            }
        }
        Log.i("JCameraView", "FocusMode not supported " + str);
        return false;
    }

    public boolean a(List<Integer> list, int i) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (i == list.get(i2).intValue()) {
                Log.i("JCameraView", "Formats supported " + i);
                return true;
            }
        }
        Log.i("JCameraView", "Formats not supported " + i);
        return false;
    }

    /* compiled from: CameraParamUtil */
    /* access modifiers changed from: private */
    public class a implements Comparator<Camera.Size> {
        private a() {
        }

        /* renamed from: a */
        public int compare(Camera.Size size, Camera.Size size2) {
            if (size.width != size2.width) {
                return size.width > size2.width ? 1 : -1;
            }
            if (size.height == size2.height) {
                return 0;
            }
            return size.height > size2.height ? 1 : -1;
        }
    }

    public int a(Context context, int i) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        try {
            Camera.getCameraInfo(i, cameraInfo);
        } catch (Exception unused) {
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int i2 = 0;
        int rotation = windowManager != null ? windowManager.getDefaultDisplay().getRotation() : 0;
        if (rotation != 0) {
            if (rotation == 1) {
                i2 = 90;
            } else if (rotation == 2) {
                i2 = 180;
            } else if (rotation == 3) {
                i2 = 270;
            }
        }
        if (cameraInfo.facing == 1) {
            return (360 - ((cameraInfo.orientation + i2) % 360)) % 360;
        }
        return ((cameraInfo.orientation - i2) + 360) % 360;
    }
}
