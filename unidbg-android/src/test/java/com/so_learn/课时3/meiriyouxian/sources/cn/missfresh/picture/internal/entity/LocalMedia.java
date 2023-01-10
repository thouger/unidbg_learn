package cn.missfresh.picture.internal.entity;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import cn.missfresh.picture.MimeType;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class LocalMedia implements Parcelable {
    public static final Parcelable.Creator<LocalMedia> CREATOR = new AnonymousClass1();
    public final long a;
    public final String b;
    public final Uri c;
    public final long d;
    public final long e;
    public final long f;
    public final long g;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: cn.missfresh.picture.internal.entity.LocalMedia$1  reason: invalid class name */
    static class AnonymousClass1 implements Parcelable.Creator<LocalMedia> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            AppMethodBeat.i(18469, false);
            LocalMedia a = a(parcel);
            AppMethodBeat.o(18469);
            return a;
        }

        @Override // android.os.Parcelable.Creator
        public /* synthetic */ Object[] newArray(int i) {
            AppMethodBeat.i(18465, false);
            LocalMedia[] a = a(i);
            AppMethodBeat.o(18465);
            return a;
        }

        public LocalMedia a(Parcel parcel) {
            AppMethodBeat.i(18459, false);
            LocalMedia localMedia = new LocalMedia(parcel, null);
            AppMethodBeat.o(18459);
            return localMedia;
        }

        public LocalMedia[] a(int i) {
            return new LocalMedia[i];
        }
    }

    /* synthetic */ LocalMedia(Parcel parcel, AnonymousClass1 r2) {
        this(parcel);
    }

    static {
        AppMethodBeat.i(18081, false);
        AppMethodBeat.o(18081);
    }

    private LocalMedia(long j, String str, long j2, long j3, long j4, long j5) {
        Uri uri;
        AppMethodBeat.i(18056, false);
        this.a = j;
        this.b = str;
        if (c()) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if (e()) {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else {
            uri = MediaStore.Files.getContentUri("external");
        }
        this.c = ContentUris.withAppendedId(uri, j);
        this.d = j2;
        this.g = j3;
        this.e = j4;
        this.f = j5;
        AppMethodBeat.o(18056);
    }

    private LocalMedia(Parcel parcel) {
        AppMethodBeat.i(18059, false);
        this.a = parcel.readLong();
        this.b = parcel.readString();
        this.c = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.d = parcel.readLong();
        this.g = parcel.readLong();
        this.e = parcel.readLong();
        this.f = parcel.readLong();
        AppMethodBeat.o(18059);
    }

    public static LocalMedia a(Cursor cursor) {
        AppMethodBeat.i(18063, false);
        LocalMedia localMedia = new LocalMedia(cursor.getLong(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("mime_type")), cursor.getLong(cursor.getColumnIndex("_size")), cursor.getLong(cursor.getColumnIndex("duration")), cursor.getLong(cursor.getColumnIndex("width")), cursor.getLong(cursor.getColumnIndex("height")));
        AppMethodBeat.o(18063);
        return localMedia;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        AppMethodBeat.i(18066, false);
        parcel.writeLong(this.a);
        parcel.writeString(this.b);
        parcel.writeParcelable(this.c, 0);
        parcel.writeLong(this.d);
        parcel.writeLong(this.g);
        parcel.writeLong(this.e);
        parcel.writeLong(this.f);
        AppMethodBeat.o(18066);
    }

    public Uri a() {
        return this.c;
    }

    public boolean b() {
        return this.a == -1;
    }

    public boolean c() {
        AppMethodBeat.i(18069, false);
        boolean isImage = MimeType.isImage(this.b);
        AppMethodBeat.o(18069);
        return isImage;
    }

    public boolean d() {
        AppMethodBeat.i(18071, false);
        boolean isGif = MimeType.isGif(this.b);
        AppMethodBeat.o(18071);
        return isGif;
    }

    public boolean e() {
        AppMethodBeat.i(18074, false);
        boolean isVideo = MimeType.isVideo(this.b);
        AppMethodBeat.o(18074);
        return isVideo;
    }

    public boolean equals(Object obj) {
        String str;
        Uri uri;
        boolean z = false;
        AppMethodBeat.i(18077, false);
        if (!(obj instanceof LocalMedia)) {
            AppMethodBeat.o(18077);
            return false;
        }
        LocalMedia localMedia = (LocalMedia) obj;
        if (this.a == localMedia.a && ((((str = this.b) != null && str.equals(localMedia.b)) || (this.b == null && localMedia.b == null)) && ((((uri = this.c) != null && uri.equals(localMedia.c)) || (this.c == null && localMedia.c == null)) && this.d == localMedia.d && this.g == localMedia.g))) {
            z = true;
        }
        AppMethodBeat.o(18077);
        return z;
    }

    public int hashCode() {
        AppMethodBeat.i(18079, false);
        int hashCode = Long.valueOf(this.a).hashCode() + 31;
        String str = this.b;
        if (str != null) {
            hashCode = (hashCode * 31) + str.hashCode();
        }
        int hashCode2 = (((((hashCode * 31) + this.c.hashCode()) * 31) + Long.valueOf(this.d).hashCode()) * 31) + Long.valueOf(this.g).hashCode();
        AppMethodBeat.o(18079);
        return hashCode2;
    }
}
