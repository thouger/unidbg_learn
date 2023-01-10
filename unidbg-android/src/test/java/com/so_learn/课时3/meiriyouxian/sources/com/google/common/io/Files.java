package com.google.common.io;

import com.google.common.base.n;
import com.google.common.collect.be;
import com.google.common.graph.a;
import java.io.File;

public final class Files {
    private static final be<File> a = new AnonymousClass2();
    private static final a<File> b = new AnonymousClass3();

    /* renamed from: com.google.common.io.Files$1  reason: invalid class name */
    static class AnonymousClass1 {
    }

    /* renamed from: com.google.common.io.Files$2  reason: invalid class name */
    static class AnonymousClass2 extends be<File> {
        public String toString() {
            return "Files.fileTreeTraverser()";
        }

        AnonymousClass2() {
        }
    }

    /* renamed from: com.google.common.io.Files$3  reason: invalid class name */
    static class AnonymousClass3 implements a<File> {
        AnonymousClass3() {
        }
    }

    private enum FilePredicate implements n<File> {
        IS_DIRECTORY {
            @Override // java.lang.Enum, java.lang.Object
            public String toString() {
                return "Files.isDirectory()";
            }

            public boolean apply(File file) {
                return file.isDirectory();
            }
        },
        IS_FILE {
            @Override // java.lang.Enum, java.lang.Object
            public String toString() {
                return "Files.isFile()";
            }

            public boolean apply(File file) {
                return file.isFile();
            }
        };

        /* synthetic */ FilePredicate(AnonymousClass1 r3) {
            this();
        }
    }
}
