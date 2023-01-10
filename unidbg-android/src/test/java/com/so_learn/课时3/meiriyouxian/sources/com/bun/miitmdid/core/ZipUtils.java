package com.bun.miitmdid.core;

import android.app.backup.FullBackup;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;
import java.util.zip.ZipException;

public class ZipUtils {
    private static final int BUFFER_SIZE = 16384;
    private static final int ENDHDR = 22;
    private static final int ENDSIG = 101010256;

    /* access modifiers changed from: package-private */
    public static class CentralDirectory {
        long offset;
        long size;

        CentralDirectory() {
        }
    }

    static long computeCrcOfCentralDir(RandomAccessFile randomAccessFile, CentralDirectory centralDirectory) throws IOException {
        AppMethodBeat.i(4858, false);
        CRC32 crc32 = new CRC32();
        long j = centralDirectory.size;
        randomAccessFile.seek(centralDirectory.offset);
        int min = (int) Math.min(16384L, j);
        byte[] bArr = new byte[16384];
        while (true) {
            int read = randomAccessFile.read(bArr, 0, min);
            if (read != -1) {
                crc32.update(bArr, 0, read);
                j -= (long) read;
                if (j == 0) {
                    break;
                }
                min = (int) Math.min(16384L, j);
            } else {
                break;
            }
        }
        long value = crc32.getValue();
        AppMethodBeat.o(4858);
        return value;
    }

    static CentralDirectory findCentralDirectory(RandomAccessFile randomAccessFile) throws IOException, ZipException {
        AppMethodBeat.i(4861, false);
        long length = randomAccessFile.length() - 22;
        long j = 0;
        if (length >= 0) {
            long j2 = length - 65536;
            if (j2 >= 0) {
                j = j2;
            }
            int reverseBytes = Integer.reverseBytes(ENDSIG);
            do {
                randomAccessFile.seek(length);
                if (randomAccessFile.readInt() == reverseBytes) {
                    randomAccessFile.skipBytes(2);
                    randomAccessFile.skipBytes(2);
                    randomAccessFile.skipBytes(2);
                    randomAccessFile.skipBytes(2);
                    CentralDirectory centralDirectory = new CentralDirectory();
                    centralDirectory.size = ((long) Integer.reverseBytes(randomAccessFile.readInt())) & 4294967295L;
                    centralDirectory.offset = ((long) Integer.reverseBytes(randomAccessFile.readInt())) & 4294967295L;
                    AppMethodBeat.o(4861);
                    return centralDirectory;
                }
                length--;
            } while (length >= j);
            ZipException zipException = new ZipException("End Of Central Directory signature not found");
            AppMethodBeat.o(4861);
            throw zipException;
        }
        ZipException zipException2 = new ZipException("File too short to be a zip file: " + randomAccessFile.length());
        AppMethodBeat.o(4861);
        throw zipException2;
    }

    public static long getZipCrc(File file) throws IOException {
        int i = 4863;
        AppMethodBeat.i(4863, false);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, FullBackup.ROOT_TREE_TOKEN);
        try {
            return computeCrcOfCentralDir(randomAccessFile, findCentralDirectory(randomAccessFile));
        } finally {
            randomAccessFile.close();
            AppMethodBeat.o(i);
        }
    }
}
