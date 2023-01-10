package com.google.common.base;

import android.telecom.Logging.Session;
import android.text.format.DateFormat;
import com.umeng.message.proguard.l;
import java.io.Serializable;

public enum CaseFormat {
    LOWER_HYPHEN(b.a('-'), "-") {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CaseFormat
        public String normalizeWord(String str) {
            return a.a(str);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CaseFormat
        public String convert(CaseFormat caseFormat, String str) {
            if (caseFormat == LOWER_UNDERSCORE) {
                return str.replace('-', '_');
            }
            if (caseFormat == UPPER_UNDERSCORE) {
                return a.b(str.replace('-', '_'));
            }
            return CaseFormat.super.convert(caseFormat, str);
        }
    },
    LOWER_UNDERSCORE(b.a('_'), Session.SESSION_SEPARATION_CHAR_CHILD) {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CaseFormat
        public String normalizeWord(String str) {
            return a.a(str);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CaseFormat
        public String convert(CaseFormat caseFormat, String str) {
            if (caseFormat == LOWER_HYPHEN) {
                return str.replace('_', '-');
            }
            if (caseFormat == UPPER_UNDERSCORE) {
                return a.b(str);
            }
            return CaseFormat.super.convert(caseFormat, str);
        }
    },
    LOWER_CAMEL(b.a((char) DateFormat.CAPITAL_AM_PM, 'Z'), "") {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CaseFormat
        public String normalizeWord(String str) {
            return CaseFormat.firstCharOnlyToUpper(str);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CaseFormat
        public String normalizeFirstWord(String str) {
            return a.a(str);
        }
    },
    UPPER_CAMEL(b.a((char) DateFormat.CAPITAL_AM_PM, 'Z'), "") {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CaseFormat
        public String normalizeWord(String str) {
            return CaseFormat.firstCharOnlyToUpper(str);
        }
    },
    UPPER_UNDERSCORE(b.a('_'), Session.SESSION_SEPARATION_CHAR_CHILD) {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CaseFormat
        public String normalizeWord(String str) {
            return a.b(str);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CaseFormat
        public String convert(CaseFormat caseFormat, String str) {
            if (caseFormat == LOWER_HYPHEN) {
                return a.a(str.replace('_', '-'));
            }
            if (caseFormat == LOWER_UNDERSCORE) {
                return a.a(str);
            }
            return CaseFormat.super.convert(caseFormat, str);
        }
    };
    
    private final b wordBoundary;
    private final String wordSeparator;

    /* access modifiers changed from: package-private */
    public abstract String normalizeWord(String str);

    private CaseFormat(b bVar, String str) {
        this.wordBoundary = bVar;
        this.wordSeparator = str;
    }

    public final String to(CaseFormat caseFormat, String str) {
        m.a(caseFormat);
        m.a(str);
        return caseFormat == this ? str : convert(caseFormat, str);
    }

    /* access modifiers changed from: package-private */
    public String convert(CaseFormat caseFormat, String str) {
        int i = 0;
        StringBuilder sb = null;
        int i2 = -1;
        while (true) {
            i2 = this.wordBoundary.a(str, i2 + 1);
            if (i2 == -1) {
                break;
            }
            if (i == 0) {
                sb = new StringBuilder(str.length() + (this.wordSeparator.length() * 4));
                sb.append(caseFormat.normalizeFirstWord(str.substring(i, i2)));
            } else {
                sb.append(caseFormat.normalizeWord(str.substring(i, i2)));
            }
            sb.append(caseFormat.wordSeparator);
            i = this.wordSeparator.length() + i2;
        }
        if (i == 0) {
            return caseFormat.normalizeFirstWord(str);
        }
        sb.append(caseFormat.normalizeWord(str.substring(i)));
        return sb.toString();
    }

    public Converter<String, String> converterTo(CaseFormat caseFormat) {
        return new StringConverter(this, caseFormat);
    }

    private static final class StringConverter extends Converter<String, String> implements Serializable {
        private static final long serialVersionUID = 0;
        private final CaseFormat sourceFormat;
        private final CaseFormat targetFormat;

        StringConverter(CaseFormat caseFormat, CaseFormat caseFormat2) {
            this.sourceFormat = (CaseFormat) m.a(caseFormat);
            this.targetFormat = (CaseFormat) m.a(caseFormat2);
        }

        /* access modifiers changed from: protected */
        public String doForward(String str) {
            return this.sourceFormat.to(this.targetFormat, str);
        }

        /* access modifiers changed from: protected */
        public String doBackward(String str) {
            return this.targetFormat.to(this.sourceFormat, str);
        }

        @Override // com.google.common.base.Converter, com.google.common.base.g, java.lang.Object
        public boolean equals(Object obj) {
            if (!(obj instanceof StringConverter)) {
                return false;
            }
            StringConverter stringConverter = (StringConverter) obj;
            if (!this.sourceFormat.equals(stringConverter.sourceFormat) || !this.targetFormat.equals(stringConverter.targetFormat)) {
                return false;
            }
            return true;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.sourceFormat.hashCode() ^ this.targetFormat.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            return this.sourceFormat + ".converterTo(" + this.targetFormat + l.t;
        }
    }

    /* access modifiers changed from: package-private */
    public String normalizeFirstWord(String str) {
        return normalizeWord(str);
    }

    /* access modifiers changed from: private */
    public static String firstCharOnlyToUpper(String str) {
        if (str.isEmpty()) {
            return str;
        }
        return a.a(str.charAt(0)) + a.a(str.substring(1));
    }
}
