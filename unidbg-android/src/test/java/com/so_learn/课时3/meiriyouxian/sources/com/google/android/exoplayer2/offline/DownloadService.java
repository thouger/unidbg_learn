package com.google.android.exoplayer2.offline;

import android.app.Service;
import com.google.android.exoplayer2.scheduler.a;
import java.util.HashMap;

public abstract class DownloadService extends Service {
    private static final HashMap<Class<? extends DownloadService>, Object> a = new HashMap<>();
    private static final a b = new a(1, false, false);
}
