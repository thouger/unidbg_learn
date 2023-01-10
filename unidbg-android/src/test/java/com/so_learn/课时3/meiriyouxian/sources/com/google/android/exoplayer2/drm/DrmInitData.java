package com.google.android.exoplayer2.drm;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.c;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.z;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public final class DrmInitData implements Parcelable, Comparator<SchemeData> {
    public static final Parcelable.Creator<DrmInitData> CREATOR = new AnonymousClass1();
    public final String a;
    public final int b;
    private final SchemeData[] c;
    private int d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static DrmInitData a(DrmInitData drmInitData, DrmInitData drmInitData2) {
        String str;
        ArrayList arrayList = new ArrayList();
        if (drmInitData != null) {
            str = drmInitData.a;
            SchemeData[] schemeDataArr = drmInitData.c;
            for (SchemeData schemeData : schemeDataArr) {
                if (schemeData.a()) {
                    arrayList.add(schemeData);
                }
            }
        } else {
            str = null;
        }
        if (drmInitData2 != null) {
            if (str == null) {
                str = drmInitData2.a;
            }
            int size = arrayList.size();
            SchemeData[] schemeDataArr2 = drmInitData2.c;
            for (SchemeData schemeData2 : schemeDataArr2) {
                if (schemeData2.a() && !a(arrayList, size, schemeData2.f)) {
                    arrayList.add(schemeData2);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new DrmInitData(str, arrayList);
    }

    public DrmInitData(List<SchemeData> list) {
        this(null, false, (SchemeData[]) list.toArray(new SchemeData[list.size()]));
    }

    public DrmInitData(String str, List<SchemeData> list) {
        this(str, false, (SchemeData[]) list.toArray(new SchemeData[list.size()]));
    }

    public DrmInitData(SchemeData... schemeDataArr) {
        this((String) null, schemeDataArr);
    }

    public DrmInitData(String str, SchemeData... schemeDataArr) {
        this(str, true, schemeDataArr);
    }

    private DrmInitData(String str, boolean z, SchemeData... schemeDataArr) {
        this.a = str;
        schemeDataArr = z ? (SchemeData[]) schemeDataArr.clone() : schemeDataArr;
        Arrays.sort(schemeDataArr, this);
        this.c = schemeDataArr;
        this.b = schemeDataArr.length;
    }

    DrmInitData(Parcel parcel) {
        this.a = parcel.readString();
        this.c = (SchemeData[]) parcel.createTypedArray(SchemeData.CREATOR);
        this.b = this.c.length;
    }

    public SchemeData a(int i) {
        return this.c[i];
    }

    public DrmInitData a(String str) {
        if (z.a((Object) this.a, (Object) str)) {
            return this;
        }
        return new DrmInitData(str, false, this.c);
    }

    @Override // java.lang.Object
    public int hashCode() {
        if (this.d == 0) {
            String str = this.a;
            this.d = ((str == null ? 0 : str.hashCode()) * 31) + Arrays.hashCode(this.c);
        }
        return this.d;
    }

    @Override // java.util.Comparator, java.lang.Object
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DrmInitData drmInitData = (DrmInitData) obj;
        return z.a(this.a, drmInitData.a) && Arrays.equals(this.c, drmInitData.c);
    }

    /* renamed from: a */
    public int compare(SchemeData schemeData, SchemeData schemeData2) {
        if (c.a.equals(schemeData.f)) {
            return c.a.equals(schemeData2.f) ? 0 : 1;
        }
        return schemeData.f.compareTo(schemeData2.f);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeTypedArray(this.c, 0);
    }

    /* renamed from: com.google.android.exoplayer2.drm.DrmInitData$1  reason: invalid class name */
    static class AnonymousClass1 implements Parcelable.Creator<DrmInitData> {
        AnonymousClass1() {
        }

        /* renamed from: a */
        public DrmInitData createFromParcel(Parcel parcel) {
            return new DrmInitData(parcel);
        }

        /* renamed from: a */
        public DrmInitData[] newArray(int i) {
            return new DrmInitData[i];
        }
    }

    private static boolean a(ArrayList<SchemeData> arrayList, int i, UUID uuid) {
        for (int i2 = 0; i2 < i; i2++) {
            if (arrayList.get(i2).f.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public static final class SchemeData implements Parcelable {
        public static final Parcelable.Creator<SchemeData> CREATOR = new AnonymousClass1();
        public final String a;
        public final String b;
        public final byte[] c;
        public final boolean d;
        private int e;
        private final UUID f;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public SchemeData(UUID uuid, String str, byte[] bArr) {
            this(uuid, str, bArr, false);
        }

        public SchemeData(UUID uuid, String str, byte[] bArr, boolean z) {
            this(uuid, null, str, bArr, z);
        }

        public SchemeData(UUID uuid, String str, String str2, byte[] bArr, boolean z) {
            this.f = (UUID) a.a(uuid);
            this.a = str;
            this.b = (String) a.a(str2);
            this.c = bArr;
            this.d = z;
        }

        SchemeData(Parcel parcel) {
            this.f = new UUID(parcel.readLong(), parcel.readLong());
            this.a = parcel.readString();
            this.b = parcel.readString();
            this.c = parcel.createByteArray();
            this.d = parcel.readByte() != 0;
        }

        public boolean a(UUID uuid) {
            return c.a.equals(this.f) || uuid.equals(this.f);
        }

        public boolean a(SchemeData schemeData) {
            return a() && !schemeData.a() && a(schemeData.f);
        }

        public boolean a() {
            return this.c != null;
        }

        public SchemeData a(byte[] bArr) {
            return new SchemeData(this.f, this.a, this.b, bArr, this.d);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof SchemeData)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            SchemeData schemeData = (SchemeData) obj;
            return z.a(this.a, schemeData.a) && z.a(this.b, schemeData.b) && z.a(this.f, schemeData.f) && Arrays.equals(this.c, schemeData.c);
        }

        public int hashCode() {
            if (this.e == 0) {
                int hashCode = this.f.hashCode() * 31;
                String str = this.a;
                this.e = ((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.b.hashCode()) * 31) + Arrays.hashCode(this.c);
            }
            return this.e;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.f.getMostSignificantBits());
            parcel.writeLong(this.f.getLeastSignificantBits());
            parcel.writeString(this.a);
            parcel.writeString(this.b);
            parcel.writeByteArray(this.c);
            parcel.writeByte(this.d ? (byte) 1 : 0);
        }

        /* renamed from: com.google.android.exoplayer2.drm.DrmInitData$SchemeData$1  reason: invalid class name */
        static class AnonymousClass1 implements Parcelable.Creator<SchemeData> {
            AnonymousClass1() {
            }

            /* renamed from: a */
            public SchemeData createFromParcel(Parcel parcel) {
                return new SchemeData(parcel);
            }

            /* renamed from: a */
            public SchemeData[] newArray(int i) {
                return new SchemeData[i];
            }
        }
    }
}
