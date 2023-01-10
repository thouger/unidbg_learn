package com.alipay.b.a.a.b;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* access modifiers changed from: package-private */
public final class c implements FileFilter {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        return Pattern.matches("cpu[0-9]+", file.getName());
    }
}
