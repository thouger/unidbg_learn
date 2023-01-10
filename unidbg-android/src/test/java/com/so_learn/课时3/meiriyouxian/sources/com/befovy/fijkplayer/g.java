package com.befovy.fijkplayer;

import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.IOException;
import java.io.InputStream;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;

/* compiled from: RawMediaDataSource */
class g implements IMediaDataSource {
    private InputStream a;
    private long b = 0;

    public g(InputStream inputStream) {
        this.a = inputStream;
    }

    public int readAt(long j, byte[] bArr, int i, int i2) {
        AppMethodBeat.i(426, false);
        if (i2 <= 0) {
            AppMethodBeat.o(426);
            return i2;
        }
        int i3 = -1;
        try {
            if (this.b != j) {
                this.a.reset();
                this.b = this.a.skip(j);
            }
            i3 = this.a.read(bArr, i, i2);
            this.b += (long) i3;
        } catch (IOException e) {
            Log.e("DataSource", "failed to read" + e.getMessage());
        }
        AppMethodBeat.o(426);
        return i3;
    }

    public long getSize() {
        long j;
        AppMethodBeat.i(428, false);
        try {
            j = (long) this.a.available();
        } catch (IOException e) {
            Log.e("DataSource", "failed to get size" + e.getMessage());
            j = -1;
        }
        AppMethodBeat.o(428);
        return j;
    }

    public void close() {
        AppMethodBeat.i(430, false);
        InputStream inputStream = this.a;
        if (inputStream != null) {
            try {
                inputStream.close();
                this.a = null;
            } catch (IOException e) {
                Log.e("DataSource", "failed to close" + e.getMessage());
            }
        }
        AppMethodBeat.o(430);
    }
}
