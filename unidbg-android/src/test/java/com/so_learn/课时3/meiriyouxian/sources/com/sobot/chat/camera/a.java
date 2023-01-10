package com.sobot.chat.camera;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import android.widget.ImageView;
import com.sobot.chat.camera.a.d;
import com.sobot.chat.camera.c.g;
import com.sobot.chat.camera.c.h;
import com.tencent.qcloud.tim.uikit.component.video.JCameraView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: CameraInterface */
public class a implements Camera.PreviewCallback {
    private static volatile a b;
    private int A = JCameraView.MEDIA_QUALITY_MIDDLE;
    private SensorManager B = null;
    private Context C;
    private SensorEventListener D = new AnonymousClass1();
    private int E;
    int a = 0;
    private Camera c;
    private Camera.Parameters d;
    private boolean e = false;
    private int f = -1;
    private int g = -1;
    private int h = -1;
    private SurfaceHolder i = null;
    private float j = -1.0f;
    private boolean k = false;
    private MediaRecorder l;
    private String m;
    private String n;
    private String o;
    private Bitmap p = null;
    private d q;
    private ImageView r;
    private int s;
    private int t;
    private int u = 0;
    private int v = 90;
    private int w = 0;
    private byte[] x;
    private int y = 0;
    private int z = 0;

    /* compiled from: CameraInterface */
    /* renamed from: com.sobot.chat.camera.a$a  reason: collision with other inner class name */
    public interface AbstractC0141a {
        void a();
    }

    /* compiled from: CameraInterface */
    /* access modifiers changed from: package-private */
    public interface b {
    }

    /* compiled from: CameraInterface */
    public interface c {
        void a();
    }

    /* compiled from: CameraInterface */
    public interface d {
        void a(String str, Bitmap bitmap);
    }

    /* compiled from: CameraInterface */
    public interface e {
        void a(Bitmap bitmap, boolean z);
    }

    private static int a(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                synchronized (a.class) {
                    if (b == null) {
                        b = new a();
                    }
                }
            }
            aVar = b;
        }
        return aVar;
    }

    public void a(ImageView imageView) {
        this.r = imageView;
        if (imageView != null) {
            this.v = com.sobot.chat.camera.c.c.a().a(imageView.getContext(), this.f);
        }
    }

    /* compiled from: CameraInterface */
    /* renamed from: com.sobot.chat.camera.a$1  reason: invalid class name */
    class AnonymousClass1 implements SensorEventListener {
        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        AnonymousClass1() {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (1 == sensorEvent.sensor.getType()) {
                float[] fArr = sensorEvent.values;
                a.this.u = com.sobot.chat.camera.c.a.a(fArr[0], fArr[1]);
                a.this.d();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d() {
        int i;
        int i2;
        if (this.r != null && (i = this.w) != (i2 = this.u)) {
            int i3 = 270;
            int i4 = 180;
            int i5 = 90;
            if (i == 0) {
                i4 = i2 != 90 ? i2 != 270 ? 0 : 90 : -90;
                i5 = 0;
            } else if (i == 90) {
                i4 = (i2 == 0 || i2 != 180) ? 0 : -180;
                i5 = -90;
            } else if (i == 180) {
                if (i2 != 90) {
                    i3 = i2 != 270 ? 0 : 90;
                }
                i5 = 180;
                i4 = i3;
            } else if (i != 270) {
                i4 = 0;
                i5 = 0;
            } else if (i2 == 0 || i2 != 180) {
                i4 = 0;
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.r, "rotation", (float) i5, (float) i4);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(ofFloat);
            animatorSet.setDuration(500L);
            animatorSet.start();
            this.w = this.u;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        this.n = str;
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public void a(float f, int i) {
        int i2;
        Camera camera = this.c;
        if (camera != null) {
            if (this.d == null) {
                this.d = camera.getParameters();
            }
            if (this.d.isZoomSupported() && this.d.isSmoothZoomSupported()) {
                if (i != 144) {
                    if (i == 145 && !this.k) {
                        int i3 = (int) (f / 50.0f);
                        if (i3 < this.d.getMaxZoom()) {
                            this.y += i3;
                            int i4 = this.y;
                            if (i4 < 0) {
                                this.y = 0;
                            } else if (i4 > this.d.getMaxZoom()) {
                                this.y = this.d.getMaxZoom();
                            }
                            this.d.setZoom(this.y);
                            this.c.setParameters(this.d);
                        }
                        h.a("setZoom = " + this.y);
                    }
                } else if (this.k && f >= 0.0f && (i2 = (int) (f / 40.0f)) <= this.d.getMaxZoom() && i2 >= this.y && this.z != i2) {
                    this.d.setZoom(i2);
                    this.c.setParameters(this.d);
                    this.z = i2;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        this.A = i;
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        this.x = bArr;
    }

    private a() {
        e();
        this.f = this.g;
        this.n = "";
    }

    /* access modifiers changed from: package-private */
    public void a(AbstractC0141a aVar) {
        d dVar;
        if (Build.VERSION.SDK_INT >= 23 || com.sobot.chat.camera.c.d.a(this.f) || (dVar = this.q) == null) {
            if (this.c == null) {
                b(this.f);
            }
            aVar.a();
            return;
        }
        dVar.a();
    }

    private synchronized void b(int i) {
        try {
            this.c = Camera.open(i);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (this.q != null) {
                this.q.a();
            }
        }
        if (Build.VERSION.SDK_INT > 17 && this.c != null) {
            try {
                this.c.enableShutterSound(false);
            } catch (Exception e3) {
                e3.printStackTrace();
                Log.e("CJT", "enable shutter sound faild");
            }
        }
    }

    public synchronized void a(SurfaceHolder surfaceHolder, float f) {
        if (this.f == this.g) {
            this.f = this.h;
        } else {
            this.f = this.g;
        }
        c();
        h.a("open start");
        b(this.f);
        if (Build.VERSION.SDK_INT > 17 && this.c != null) {
            try {
                this.c.enableShutterSound(false);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        h.a("open end");
        b(surfaceHolder, f);
    }

    public void b(SurfaceHolder surfaceHolder, float f) {
        if (this.e) {
            h.a("doStartPreview isPreviewing");
        }
        if (this.j < 0.0f) {
            this.j = f;
        }
        if (surfaceHolder != null) {
            this.i = surfaceHolder;
            Camera camera = this.c;
            if (camera != null) {
                try {
                    this.d = camera.getParameters();
                    Camera.Size a = com.sobot.chat.camera.c.c.a().a(this.d.getSupportedPreviewSizes(), 1000, f);
                    Camera.Size b2 = com.sobot.chat.camera.c.c.a().b(this.d.getSupportedPictureSizes(), 1000, f);
                    this.d.setPreviewSize(a.width, a.height);
                    this.s = a.width;
                    this.t = a.height;
                    this.d.setPictureSize(b2.width, b2.height);
                    this.d.setRecordingHint(true);
                    if (com.sobot.chat.camera.c.c.a().a(this.d.getSupportedFocusModes(), "auto")) {
                        this.d.setFocusMode("auto");
                    }
                    if (com.sobot.chat.camera.c.c.a().a(this.d.getSupportedPictureFormats(), 256)) {
                        this.d.setPictureFormat(256);
                        this.d.setJpegQuality(100);
                    }
                    this.c.setParameters(this.d);
                    this.d = this.c.getParameters();
                    this.c.setPreviewDisplay(surfaceHolder);
                    this.c.setDisplayOrientation(this.v);
                    this.c.setPreviewCallback(this);
                    this.c.startPreview();
                    this.e = true;
                    Log.i("CJT", "=== Start Preview ===");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void b() {
        Camera camera = this.c;
        if (camera != null) {
            try {
                camera.setPreviewCallback(null);
                this.c.stopPreview();
                this.c.setPreviewDisplay(null);
                this.e = false;
                Log.i("CJT", "=== Stop Preview ===");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.q = null;
        Camera camera = this.c;
        if (camera != null) {
            try {
                camera.setPreviewCallback(null);
                this.r = null;
                this.c.stopPreview();
                this.c.setPreviewDisplay(null);
                this.i = null;
                this.e = false;
                this.c.release();
                this.c = null;
                Log.i("CJT", "=== Destroy Camera ===");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            Log.i("CJT", "=== Camera  Null===");
        }
    }

    public void a(e eVar) {
        if (this.c != null) {
            int i = this.v;
            if (i == 90) {
                this.E = Math.abs(this.u + i) % 360;
            } else if (i == 270) {
                this.E = Math.abs(i - this.u);
            }
            Log.i("CJT", this.u + " = " + this.v + " = " + this.E);
            this.c.takePicture(null, null, new AnonymousClass2(eVar));
        }
    }

    /* compiled from: CameraInterface */
    /* renamed from: com.sobot.chat.camera.a$2  reason: invalid class name */
    class AnonymousClass2 implements Camera.PictureCallback {
        final /* synthetic */ e a;

        AnonymousClass2(e eVar) {
            this.a = eVar;
        }

        @Override // android.hardware.Camera.PictureCallback
        public void onPictureTaken(byte[] bArr, Camera camera) {
            a aVar = a.this;
            Bitmap a = aVar.a(bArr, aVar.C);
            Matrix matrix = new Matrix();
            if (a.this.f == a.this.g) {
                matrix.setRotate((float) a.this.E);
            } else if (a.this.f == a.this.h) {
                matrix.setRotate((float) (360 - a.this.E));
                matrix.postScale(-1.0f, 1.0f);
            }
            Bitmap createBitmap = Bitmap.createBitmap(a, 0, 0, a.getWidth(), a.getHeight(), matrix, true);
            if (this.a == null) {
                return;
            }
            if (a.this.E == 90 || a.this.E == 270) {
                this.a.a(createBitmap, true);
            } else {
                this.a.a(createBitmap, false);
            }
        }
    }

    public void a(Surface surface, float f, b bVar) {
        Camera.Size size;
        this.c.stopPreview();
        this.c.setPreviewCallback(null);
        int i = (this.u + 90) % 360;
        Camera.Parameters parameters = this.c.getParameters();
        int i2 = parameters.getPreviewSize().width;
        int i3 = parameters.getPreviewSize().height;
        YuvImage yuvImage = new YuvImage(this.x, parameters.getPreviewFormat(), i2, i3, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, i2, i3), 50, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        this.p = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        Matrix matrix = new Matrix();
        int i4 = this.f;
        if (i4 == this.g) {
            matrix.setRotate((float) i);
        } else if (i4 == this.h) {
            matrix.setRotate(270.0f);
        }
        Bitmap bitmap = this.p;
        this.p = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), this.p.getHeight(), matrix, true);
        if (!this.k) {
            if (this.c == null) {
                b(this.f);
            }
            if (this.l == null) {
                this.l = new MediaRecorder();
            }
            if (this.d == null) {
                this.d = this.c.getParameters();
            }
            if (this.d.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                this.d.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            }
            this.c.setParameters(this.d);
            this.c.unlock();
            this.l.reset();
            this.l.setCamera(this.c);
            this.l.setVideoSource(1);
            this.l.setAudioSource(1);
            this.l.setOutputFormat(2);
            this.l.setVideoEncoder(2);
            this.l.setAudioEncoder(3);
            if (this.d.getSupportedVideoSizes() == null) {
                size = com.sobot.chat.camera.c.c.a().a(this.d.getSupportedPreviewSizes(), 600, f);
            } else {
                size = com.sobot.chat.camera.c.c.a().a(this.d.getSupportedVideoSizes(), 600, f);
            }
            Log.i("CJT", "setVideoSize    width = " + size.width + "height = " + size.height);
            if (size.width == size.height) {
                this.l.setVideoSize(this.s, this.t);
            } else {
                this.l.setVideoSize(size.width, size.height);
            }
            if (this.f != this.h) {
                this.l.setOrientationHint(i);
            } else if (this.v == 270) {
                if (i == 0) {
                    this.l.setOrientationHint(180);
                } else if (i == 270) {
                    this.l.setOrientationHint(270);
                } else {
                    this.l.setOrientationHint(90);
                }
            } else if (i == 90) {
                this.l.setOrientationHint(270);
            } else if (i == 270) {
                this.l.setOrientationHint(90);
            } else {
                this.l.setOrientationHint(i);
            }
            if (com.sobot.chat.camera.c.e.b()) {
                this.l.setVideoEncodingBitRate(JCameraView.MEDIA_QUALITY_FUNNY);
            } else {
                this.l.setVideoEncodingBitRate(this.A);
            }
            this.l.setPreviewDisplay(surface);
            this.m = "v_" + System.currentTimeMillis() + ".mp4";
            if (this.n.equals("")) {
                this.n = com.sobot.chat.utils.d.b(this.C);
            }
            this.o = this.n + File.separator + this.m;
            this.l.setOutputFile(this.o);
            try {
                this.l.prepare();
                this.l.start();
                this.k = true;
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
                Log.i("CJT", "startRecord IllegalStateException");
                d dVar = this.q;
                if (dVar != null) {
                    dVar.a();
                }
            } catch (IOException e3) {
                e3.printStackTrace();
                Log.i("CJT", "startRecord IOException");
                d dVar2 = this.q;
                if (dVar2 != null) {
                    dVar2.a();
                }
            } catch (RuntimeException unused) {
                Log.i("CJT", "startRecord RuntimeException");
            } catch (Exception unused2) {
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0021, code lost:
        r2.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        r3.l = null;
        r3.k = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        if (r2 == null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        if (r4 == false) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        if (com.sobot.chat.camera.c.f.a(r3.o) == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0047, code lost:
        if (r5 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0049, code lost:
        r5.a(null, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004d, code lost:
        b();
        r4 = r3.n + java.io.File.separator + r3.m;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0068, code lost:
        if (r5 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006a, code lost:
        r5.a(r4, r3.p);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        if (r2 != null) goto L_0x0021;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r4, com.sobot.chat.camera.a.d r5) {
        /*
            r3 = this;
            boolean r0 = r3.k
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            android.media.MediaRecorder r0 = r3.l
            if (r0 == 0) goto L_0x007c
            r1 = 0
            r0.setOnErrorListener(r1)
            android.media.MediaRecorder r0 = r3.l
            r0.setOnInfoListener(r1)
            android.media.MediaRecorder r0 = r3.l
            r0.setPreviewDisplay(r1)
            r0 = 0
            android.media.MediaRecorder r2 = r3.l     // Catch:{ Exception -> 0x002b }
            r2.stop()     // Catch:{ Exception -> 0x002b }
            android.media.MediaRecorder r2 = r3.l
            if (r2 == 0) goto L_0x0024
        L_0x0021:
            r2.release()
        L_0x0024:
            r3.l = r1
            r3.k = r0
            goto L_0x003d
        L_0x0029:
            r4 = move-exception
            goto L_0x0070
        L_0x002b:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ all -> 0x0029 }
            r3.l = r1     // Catch:{ all -> 0x0029 }
            android.media.MediaRecorder r2 = new android.media.MediaRecorder     // Catch:{ all -> 0x0029 }
            r2.<init>()     // Catch:{ all -> 0x0029 }
            r3.l = r2     // Catch:{ all -> 0x0029 }
            android.media.MediaRecorder r2 = r3.l
            if (r2 == 0) goto L_0x0024
            goto L_0x0021
        L_0x003d:
            if (r4 == 0) goto L_0x004d
            java.lang.String r4 = r3.o
            boolean r4 = com.sobot.chat.camera.c.f.a(r4)
            if (r4 == 0) goto L_0x004c
            if (r5 == 0) goto L_0x004c
            r5.a(r1, r1)
        L_0x004c:
            return
        L_0x004d:
            r3.b()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = r3.n
            r4.append(r0)
            java.lang.String r0 = java.io.File.separator
            r4.append(r0)
            java.lang.String r0 = r3.m
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            if (r5 == 0) goto L_0x007c
            android.graphics.Bitmap r0 = r3.p
            r5.a(r4, r0)
            goto L_0x007c
        L_0x0070:
            android.media.MediaRecorder r5 = r3.l
            if (r5 == 0) goto L_0x0077
            r5.release()
        L_0x0077:
            r3.l = r1
            r3.k = r0
            throw r4
        L_0x007c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.camera.a.a(boolean, com.sobot.chat.camera.a$d):void");
    }

    private void e() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            int i2 = cameraInfo.facing;
            if (i2 == 0) {
                this.g = cameraInfo.facing;
            } else if (i2 == 1) {
                this.h = cameraInfo.facing;
            }
        }
    }

    public void a(Context context, float f, float f2, c cVar) {
        Camera camera = this.c;
        if (camera != null) {
            Camera.Parameters parameters = camera.getParameters();
            Rect a = a(f, f2, 1.0f, context);
            this.c.cancelAutoFocus();
            if (parameters.getMaxNumFocusAreas() > 0) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Camera.Area(a, 800));
                parameters.setFocusAreas(arrayList);
                String focusMode = parameters.getFocusMode();
                try {
                    parameters.setFocusMode("auto");
                    this.c.setParameters(parameters);
                    this.c.autoFocus(new AnonymousClass3(focusMode, cVar, context, f, f2));
                } catch (Exception unused) {
                    Log.e("CJT", "autoFocus failer");
                }
            } else {
                Log.i("CJT", "focus areas not supported");
                cVar.a();
            }
        }
    }

    /* compiled from: CameraInterface */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.a$3  reason: invalid class name */
    public class AnonymousClass3 implements Camera.AutoFocusCallback {
        final /* synthetic */ String a;
        final /* synthetic */ c b;
        final /* synthetic */ Context c;
        final /* synthetic */ float d;
        final /* synthetic */ float e;

        AnonymousClass3(String str, c cVar, Context context, float f, float f2) {
            this.a = str;
            this.b = cVar;
            this.c = context;
            this.d = f;
            this.e = f2;
        }

        @Override // android.hardware.Camera.AutoFocusCallback
        public void onAutoFocus(boolean z, Camera camera) {
            if (z || a.this.a > 10) {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setFocusMode(this.a);
                camera.setParameters(parameters);
                a.this.a = 0;
                this.b.a();
                return;
            }
            a.this.a++;
            a.this.a(this.c, this.d, this.e, this.b);
        }
    }

    private static Rect a(float f, float f2, float f3, Context context) {
        int intValue = Float.valueOf(f3 * 300.0f).intValue();
        int i = intValue / 2;
        int a = a(((int) (((f / ((float) g.b(context))) * 2000.0f) - 1000.0f)) - i, -1000, 1000);
        int a2 = a(((int) (((f2 / ((float) g.a(context))) * 2000.0f) - 1000.0f)) - i, -1000, 1000);
        RectF rectF = new RectF((float) a, (float) a2, (float) (a + intValue), (float) (a2 + intValue));
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    /* access modifiers changed from: package-private */
    public void a(d dVar) {
        this.q = dVar;
    }

    /* access modifiers changed from: package-private */
    public void a(Context context) {
        if (this.B == null) {
            this.B = (SensorManager) context.getSystemService("sensor");
        }
        SensorManager sensorManager = this.B;
        if (sensorManager != null) {
            sensorManager.registerListener(this.D, sensorManager.getDefaultSensor(1), 3);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(Context context) {
        if (this.B == null) {
            this.B = (SensorManager) context.getSystemService("sensor");
        }
        SensorManager sensorManager = this.B;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this.D);
        }
        this.B = null;
    }

    public void c(Context context) {
        this.C = context.getApplicationContext();
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.e = z;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Bitmap a(byte[] bArr, Context context) {
        int i;
        int i2;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            i2 = windowManager.getDefaultDisplay().getWidth();
            i = windowManager.getDefaultDisplay().getHeight();
        } else {
            i = 0;
            i2 = 0;
        }
        options.inSampleSize = a(options, i2, i);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
    }

    private static int a(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 <= i2 && i4 <= i) {
            return 1;
        }
        int round = Math.round(((float) i3) / ((float) i2));
        int round2 = Math.round(((float) i4) / ((float) i));
        return round < round2 ? round : round2;
    }
}
