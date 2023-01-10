package com.google.android.exoplayer2.source;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.logging.nano.MetricsProto;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.a;
import java.util.Arrays;

public final class TrackGroup implements Parcelable {
    public static final Parcelable.Creator<TrackGroup> CREATOR = new AnonymousClass1();
    public final int a;
    private final Format[] b;
    private int c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TrackGroup(Format... formatArr) {
        a.b(formatArr.length > 0);
        this.b = formatArr;
        this.a = formatArr.length;
    }

    TrackGroup(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = new Format[this.a];
        for (int i = 0; i < this.a; i++) {
            this.b[i] = (Format) parcel.readParcelable(Format.class.getClassLoader());
        }
    }

    public Format a(int i) {
        return this.b[i];
    }

    public int a(Format format) {
        int i = 0;
        while (true) {
            Format[] formatArr = this.b;
            if (i >= formatArr.length) {
                return -1;
            }
            if (format == formatArr[i]) {
                return i;
            }
            i++;
        }
    }

    public int hashCode() {
        if (this.c == 0) {
            this.c = MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE + Arrays.hashCode(this.b);
        }
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TrackGroup trackGroup = (TrackGroup) obj;
        return this.a == trackGroup.a && Arrays.equals(this.b, trackGroup.b);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        for (int i2 = 0; i2 < this.a; i2++) {
            parcel.writeParcelable(this.b[i2], 0);
        }
    }

    /* renamed from: com.google.android.exoplayer2.source.TrackGroup$1  reason: invalid class name */
    static class AnonymousClass1 implements Parcelable.Creator<TrackGroup> {
        AnonymousClass1() {
        }

        /* renamed from: a */
        public TrackGroup createFromParcel(Parcel parcel) {
            return new TrackGroup(parcel);
        }

        /* renamed from: a */
        public TrackGroup[] newArray(int i) {
            return new TrackGroup[i];
        }
    }
}
