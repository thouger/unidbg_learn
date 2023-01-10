package com.sobot.chat.camera.c;

import android.hardware.Camera;
import android.media.AudioRecord;
import android.util.Log;

/* compiled from: CheckPermission */
public class d {
    public static int a() {
        int minBufferSize = AudioRecord.getMinBufferSize(44100, 16, 2);
        AudioRecord audioRecord = new AudioRecord(0, 44100, 16, 2, minBufferSize * 100);
        short[] sArr = new short[minBufferSize];
        try {
            audioRecord.startRecording();
            if (audioRecord.getRecordingState() != 3) {
                audioRecord.stop();
                audioRecord.release();
                Log.d("CheckAudioPermission", "\u5f55\u97f3\u673a\u88ab\u5360\u7528");
                return -1;
            } else if (audioRecord.read(sArr, 0, sArr.length) <= 0) {
                audioRecord.stop();
                audioRecord.release();
                Log.d("CheckAudioPermission", "\u5f55\u97f3\u7684\u7ed3\u679c\u4e3a\u7a7a");
                return -2;
            } else {
                audioRecord.stop();
                audioRecord.release();
                return 1;
            }
        } catch (Exception unused) {
            audioRecord.release();
            return -2;
        }
    }

    public static synchronized boolean a(int i) {
        boolean z;
        synchronized (d.class) {
            Camera camera = null;
            z = false;
            try {
                camera = Camera.open(i);
                camera.setParameters(camera.getParameters());
                if (camera != null) {
                    camera.release();
                    z = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (camera != null) {
                    camera.release();
                }
            } catch (Throwable th) {
                if (camera != null) {
                    camera.release();
                }
                throw th;
            }
        }
        return z;
    }
}
