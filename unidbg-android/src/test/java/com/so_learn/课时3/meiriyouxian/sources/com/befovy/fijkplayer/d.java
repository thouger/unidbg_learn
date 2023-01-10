package com.befovy.fijkplayer;

import android.app.backup.FullBackup;
import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;

/* compiled from: FileMediaDataSource */
class d implements IMediaDataSource {
    private RandomAccessFile a;
    private long b;

    public d(File file) {
        AppMethodBeat.i(403, false);
        try {
            this.a = new RandomAccessFile(file, FullBackup.ROOT_TREE_TOKEN);
            this.b = this.a.length();
        } catch (IOException e) {
            this.a = null;
            this.b = -1;
            Log.e("DataSource", "failed to open RandomAccess" + e.getMessage());
        }
        AppMethodBeat.o(403);
    }

    public int readAt(long j, byte[] bArr, int i, int i2) {
        AppMethodBeat.i(406, false);
        if (i2 == 0) {
            AppMethodBeat.o(406);
            return 0;
        }
        int i3 = -1;
        RandomAccessFile randomAccessFile = this.a;
        if (randomAccessFile != null) {
            try {
                if (randomAccessFile.getFilePointer() != j) {
                    this.a.seek(j);
                }
                i3 = this.a.read(bArr, 0, i2);
            } catch (IOException e) {
                Log.e("DataSource", "failed to read" + e.getMessage());
            }
        }
        AppMethodBeat.o(406);
        return i3;
    }

    public long getSize() {
        return this.b;
    }

    public void close() {
        AppMethodBeat.i(409, false);
        RandomAccessFile randomAccessFile = this.a;
        if (randomAccessFile != null) {
            try {
                randomAccessFile.close();
                this.b = 0;
                this.a = null;
            } catch (IOException e) {
                Log.e("DataSource", "failed to close" + e.getMessage());
            }
        }
        AppMethodBeat.o(409);
    }
}
