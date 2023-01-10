package com.umeng.message.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.proguard.l;
import com.umeng.message.proguard.m;
import com.umeng.message.provider.a;

public class MessageProvider extends ContentProvider {
    private static final String a = MessageProvider.class.getSimpleName();
    private static final UriMatcher b = new UriMatcher(-1);
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 3;
    private static final int h = 4;
    private static final int i = 5;
    private static final int j = 6;
    private static final int k = 7;
    private static final int l = 8;
    private static final int m = 9;
    private static final int n = 10;
    private static Context o;
    private a c;
    private b d;

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        o = getContext();
        b();
        b.addURI(a.a(o).a, "MessageStores", 1);
        b.addURI(a.a(o).a, "MsgTemps", 2);
        b.addURI(a.a(o).a, l.e, 3);
        b.addURI(a.a(o).a, "MsgAliasDeleteAll", 4);
        b.addURI(a.a(o).a, "MsgLogStores", 5);
        b.addURI(a.a(o).a, "MsgLogIdTypeStores", 6);
        b.addURI(a.a(o).a, "MsgLogStoreForAgoos", 7);
        b.addURI(a.a(o).a, "MsgLogIdTypeStoreForAgoos", 8);
        b.addURI(a.a(o).a, "MsgConfigInfos", 9);
        b.addURI(a.a(o).a, "InAppLogStores", 10);
        return true;
    }

    private void b() {
        try {
            synchronized (this) {
                this.c = new a(getContext());
                this.d = new b(getContext());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Cursor cursor;
        switch (b.match(uri)) {
            case 1:
            case 4:
            case 6:
            default:
                cursor = null;
                break;
            case 2:
                a aVar = this.c;
                if (!(aVar == null || aVar.getWritableDatabase() == null)) {
                    cursor = this.c.getWritableDatabase().query(l.d, strArr, str, strArr2, null, null, str2);
                    break;
                }
                cursor = null;
                break;
            case 3:
                a aVar2 = this.c;
                if (!(aVar2 == null || aVar2.getWritableDatabase() == null)) {
                    cursor = this.c.getWritableDatabase().query(l.e, strArr, str, strArr2, null, null, str2);
                    break;
                }
                cursor = null;
                break;
            case 5:
                b bVar = this.d;
                if (!(bVar == null || bVar.getWritableDatabase() == null)) {
                    cursor = this.d.getWritableDatabase().query(m.d, strArr, str, strArr2, null, null, str2);
                    break;
                }
                cursor = null;
                break;
            case 7:
                b bVar2 = this.d;
                if (!(bVar2 == null || bVar2.getWritableDatabase() == null)) {
                    cursor = this.d.getWritableDatabase().query(m.f, strArr, str, strArr2, null, null, str2);
                    break;
                }
                cursor = null;
                break;
            case 8:
                b bVar3 = this.d;
                if (!(bVar3 == null || bVar3.getWritableDatabase() == null)) {
                    cursor = this.d.getWritableDatabase().query(m.g, strArr, str, strArr2, null, null, str2);
                    break;
                }
                cursor = null;
                break;
            case 9:
                b bVar4 = this.d;
                if (!(bVar4 == null || bVar4.getWritableDatabase() == null)) {
                    cursor = this.d.getWritableDatabase().query(m.h, strArr, str, strArr2, null, null, str2);
                    break;
                }
                cursor = null;
                break;
            case 10:
                b bVar5 = this.d;
                if (!(bVar5 == null || bVar5.getWritableDatabase() == null)) {
                    cursor = this.d.getWritableDatabase().query(m.i, strArr, str, strArr2, null, null, str2);
                    break;
                }
                cursor = null;
                break;
        }
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int match = b.match(uri);
        if (match == 1 || match == 2 || match == 3 || match == 5 || match == 7 || match == 8 || match == 9) {
            return a.C0190a.k;
        }
        throw new IllegalArgumentException("Unknown URI " + uri);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        switch (b.match(uri)) {
            case 1:
                a aVar = this.c;
                long insertWithOnConflict = (aVar == null || aVar.getWritableDatabase() == null) ? 0 : this.c.getWritableDatabase().insertWithOnConflict(l.c, null, contentValues, 5);
                if (insertWithOnConflict > 0) {
                    Uri withAppendedId = ContentUris.withAppendedId(a.a(o).b, insertWithOnConflict);
                    getContext().getContentResolver().notifyChange(withAppendedId, null);
                    return withAppendedId;
                }
                break;
            case 2:
                a aVar2 = this.c;
                long insertWithOnConflict2 = (aVar2 == null || aVar2.getWritableDatabase() == null) ? 0 : this.c.getWritableDatabase().insertWithOnConflict(l.d, null, contentValues, 5);
                if (insertWithOnConflict2 > 0) {
                    Uri withAppendedId2 = ContentUris.withAppendedId(a.a(o).b, insertWithOnConflict2);
                    getContext().getContentResolver().notifyChange(withAppendedId2, null);
                    return withAppendedId2;
                }
                break;
            case 3:
                a aVar3 = this.c;
                long insertWithOnConflict3 = (aVar3 == null || aVar3.getWritableDatabase() == null) ? 0 : this.c.getWritableDatabase().insertWithOnConflict(l.e, null, contentValues, 5);
                if (insertWithOnConflict3 > 0) {
                    Uri withAppendedId3 = ContentUris.withAppendedId(a.a(o).d, insertWithOnConflict3);
                    getContext().getContentResolver().notifyChange(withAppendedId3, null);
                    return withAppendedId3;
                }
                break;
            case 5:
                b bVar = this.d;
                long insertWithOnConflict4 = (bVar == null || bVar.getWritableDatabase() == null) ? 0 : this.d.getWritableDatabase().insertWithOnConflict(m.d, null, contentValues, 5);
                if (insertWithOnConflict4 > 0) {
                    Uri withAppendedId4 = ContentUris.withAppendedId(a.a(o).f, insertWithOnConflict4);
                    getContext().getContentResolver().notifyChange(withAppendedId4, null);
                    return withAppendedId4;
                }
                break;
            case 6:
                b bVar2 = this.d;
                long insertWithOnConflict5 = (bVar2 == null || bVar2.getWritableDatabase() == null) ? 0 : this.d.getWritableDatabase().insertWithOnConflict(m.e, null, contentValues, 5);
                if (insertWithOnConflict5 > 0) {
                    return ContentUris.withAppendedId(a.a(o).g, insertWithOnConflict5);
                }
                break;
            case 7:
                b bVar3 = this.d;
                long insertWithOnConflict6 = (bVar3 == null || bVar3.getWritableDatabase() == null) ? 0 : this.d.getWritableDatabase().insertWithOnConflict(m.f, null, contentValues, 5);
                if (insertWithOnConflict6 > 0) {
                    return ContentUris.withAppendedId(a.a(o).h, insertWithOnConflict6);
                }
                break;
            case 8:
                b bVar4 = this.d;
                long insertWithOnConflict7 = (bVar4 == null || bVar4.getWritableDatabase() == null) ? 0 : this.d.getWritableDatabase().insertWithOnConflict(m.g, null, contentValues, 5);
                if (insertWithOnConflict7 > 0) {
                    return ContentUris.withAppendedId(a.a(o).i, insertWithOnConflict7);
                }
                break;
            case 9:
                b bVar5 = this.d;
                long insertWithOnConflict8 = (bVar5 == null || bVar5.getWritableDatabase() == null) ? 0 : this.d.getWritableDatabase().insertWithOnConflict(m.h, null, contentValues, 5);
                if (insertWithOnConflict8 > 0) {
                    return ContentUris.withAppendedId(a.a(o).j, insertWithOnConflict8);
                }
                break;
            case 10:
                b bVar6 = this.d;
                long insertWithOnConflict9 = (bVar6 == null || bVar6.getWritableDatabase() == null) ? 0 : this.d.getWritableDatabase().insertWithOnConflict(m.i, null, contentValues, 5);
                if (insertWithOnConflict9 > 0) {
                    return ContentUris.withAppendedId(a.a(o).k, insertWithOnConflict9);
                }
                break;
        }
        return null;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        int i2;
        switch (b.match(uri)) {
            case 1:
            case 9:
            default:
                i2 = 0;
                break;
            case 2:
                a aVar = this.c;
                if (!(aVar == null || aVar.getWritableDatabase() == null)) {
                    i2 = this.c.getWritableDatabase().delete(l.d, str, strArr);
                    break;
                }
                i2 = 0;
                break;
            case 3:
                a aVar2 = this.c;
                if (!(aVar2 == null || aVar2.getWritableDatabase() == null)) {
                    i2 = this.c.getWritableDatabase().delete(l.e, str, strArr);
                    break;
                }
                i2 = 0;
                break;
            case 4:
                a aVar3 = this.c;
                if (!(aVar3 == null || aVar3.getWritableDatabase() == null)) {
                    i2 = this.c.getWritableDatabase().delete(l.e, null, null);
                    break;
                }
                i2 = 0;
                break;
            case 5:
                b bVar = this.d;
                if (!(bVar == null || bVar.getWritableDatabase() == null)) {
                    i2 = this.d.getWritableDatabase().delete(m.d, str, strArr);
                    break;
                }
                i2 = 0;
                break;
            case 6:
                b bVar2 = this.d;
                if (!(bVar2 == null || bVar2.getWritableDatabase() == null)) {
                    i2 = this.d.getWritableDatabase().delete(m.e, str, strArr);
                    break;
                }
                i2 = 0;
                break;
            case 7:
                b bVar3 = this.d;
                if (!(bVar3 == null || bVar3.getWritableDatabase() == null)) {
                    i2 = this.d.getWritableDatabase().delete(m.f, str, strArr);
                    break;
                }
                i2 = 0;
                break;
            case 8:
                b bVar4 = this.d;
                if (!(bVar4 == null || bVar4.getWritableDatabase() == null)) {
                    i2 = this.d.getWritableDatabase().delete(m.g, str, strArr);
                    break;
                }
                i2 = 0;
                break;
            case 10:
                b bVar5 = this.d;
                if (!(bVar5 == null || bVar5.getWritableDatabase() == null)) {
                    i2 = this.d.getWritableDatabase().delete(m.i, str, strArr);
                    break;
                }
                i2 = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return i2;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int i2;
        b bVar;
        int match = b.match(uri);
        if (match == 1) {
            a aVar = this.c;
            if (!(aVar == null || aVar.getWritableDatabase() == null)) {
                i2 = this.c.getWritableDatabase().updateWithOnConflict(l.c, contentValues, str, strArr, 5);
                getContext().getContentResolver().notifyChange(uri, null);
                return i2;
            }
        } else if (match == 2) {
            a aVar2 = this.c;
            if (!(aVar2 == null || aVar2.getWritableDatabase() == null)) {
                i2 = this.c.getWritableDatabase().updateWithOnConflict(l.d, contentValues, str, strArr, 5);
                getContext().getContentResolver().notifyChange(uri, null);
                return i2;
            }
        } else if (match == 3) {
            a aVar3 = this.c;
            if (!(aVar3 == null || aVar3.getWritableDatabase() == null)) {
                this.c.getWritableDatabase().updateWithOnConflict(l.e, contentValues, str, strArr, 5);
            }
        } else if (match == 9) {
            b bVar2 = this.d;
            if (!(bVar2 == null || bVar2.getWritableDatabase() == null)) {
                i2 = this.d.getWritableDatabase().updateWithOnConflict(m.h, contentValues, str, strArr, 5);
                getContext().getContentResolver().notifyChange(uri, null);
                return i2;
            }
        } else if (!(match != 10 || (bVar = this.d) == null || bVar.getWritableDatabase() == null)) {
            i2 = this.d.getWritableDatabase().updateWithOnConflict(m.i, contentValues, str, strArr, 5);
            getContext().getContentResolver().notifyChange(uri, null);
            return i2;
        }
        i2 = 0;
        getContext().getContentResolver().notifyChange(uri, null);
        return i2;
    }

    /*  JADX ERROR: StackOverflowError in pass: MarkFinallyVisitor
        java.lang.StackOverflowError
        	at jadx.core.dex.instructions.IfNode.isSame(IfNode.java:122)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.sameInsns(MarkFinallyVisitor.java:451)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.compareBlocks(MarkFinallyVisitor.java:436)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.checkBlocksTree(MarkFinallyVisitor.java:408)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.checkBlocksTree(MarkFinallyVisitor.java:411)
        */
    @Override // android.content.ContentProvider
    public android.content.ContentProviderResult[] applyBatch(java.util.ArrayList<android.content.ContentProviderOperation> r8) throws android.content.OperationApplicationException {
        /*
            r7 = this;
            java.lang.String r0 = "notifychange endTransaction..uri:"
            com.umeng.message.provider.MessageProvider$b r1 = r7.d
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()
            r1.beginTransaction()
            r1 = 0
            android.content.ContentProviderResult[] r2 = super.applyBatch(r8)     // Catch:{ all -> 0x006c }
            com.umeng.message.provider.MessageProvider$b r3 = r7.d     // Catch:{ all -> 0x006c }
            android.database.sqlite.SQLiteDatabase r3 = r3.getWritableDatabase()     // Catch:{ all -> 0x006c }
            r3.setTransactionSuccessful()     // Catch:{ all -> 0x006c }
            com.umeng.message.provider.MessageProvider$b r3 = r7.d
            android.database.sqlite.SQLiteDatabase r3 = r3.getWritableDatabase()
            r3.endTransaction()
            java.util.Iterator r8 = r8.iterator()
            r3 = r1
        L_0x0028:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto L_0x006b
            java.lang.Object r4 = r8.next()
            android.content.ContentProviderOperation r4 = (android.content.ContentProviderOperation) r4
            android.net.Uri r5 = r4.getUri()
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L_0x003f
            goto L_0x0028
        L_0x003f:
            android.net.Uri r3 = r4.getUri()
            android.content.Context r5 = r7.getContext()
            android.content.ContentResolver r5 = r5.getContentResolver()
            android.net.Uri r6 = r4.getUri()
            r5.notifyChange(r6, r1)
            java.lang.String r5 = com.umeng.message.provider.MessageProvider.a
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r0)
            android.net.Uri r4 = r4.getUri()
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            android.util.Log.i(r5, r4)
            goto L_0x0028
        L_0x006b:
            return r2
        L_0x006c:
            r2 = move-exception
            com.umeng.message.provider.MessageProvider$b r3 = r7.d
            android.database.sqlite.SQLiteDatabase r3 = r3.getWritableDatabase()
            r3.endTransaction()
            java.util.Iterator r8 = r8.iterator()
            r3 = r1
        L_0x007b:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto L_0x00be
            java.lang.Object r4 = r8.next()
            android.content.ContentProviderOperation r4 = (android.content.ContentProviderOperation) r4
            android.net.Uri r5 = r4.getUri()
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L_0x0092
            goto L_0x007b
        L_0x0092:
            android.net.Uri r3 = r4.getUri()
            android.content.Context r5 = r7.getContext()
            android.content.ContentResolver r5 = r5.getContentResolver()
            android.net.Uri r6 = r4.getUri()
            r5.notifyChange(r6, r1)
            java.lang.String r5 = com.umeng.message.provider.MessageProvider.a
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r0)
            android.net.Uri r4 = r4.getUri()
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            android.util.Log.i(r5, r4)
            goto L_0x007b
        L_0x00be:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.provider.MessageProvider.applyBatch(java.util.ArrayList):android.content.ContentProviderResult[]");
    }

    /* access modifiers changed from: private */
    public class a extends SQLiteOpenHelper {
        public a(Context context) {
            super(context, l.b, null, 3);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Log.i(MessageProvider.a, "MessageStoreHelper-->onCreate-->start");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS MessageStore(_id Integer  PRIMARY KEY  AUTOINCREMENT  , MsdId Varchar  , Json Varchar  , SdkVersion Varchar  , ArrivalTime Long  , ActionType Integer )");
            sQLiteDatabase.execSQL("create table if not exists MsgTemp(id INTEGER AUTO_INCREMENT,tempkey varchar default NULL, tempvalue varchar default NULL,PRIMARY KEY(id))");
            sQLiteDatabase.execSQL("create table if not exists MsgAlias(time long,type varchar default NULL,alias varchar default NULL,exclusive int,error int,message varchar,PRIMARY KEY(time))");
            Log.i(MessageProvider.a, "MessageStoreHelper-->onCreate-->end");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i <= 2) {
                sQLiteDatabase.execSQL("drop table MsgTemp");
            }
            onCreate(sQLiteDatabase);
            Log.i(MessageProvider.a, "MessageStoreHelper-->onUpgrade");
        }
    }

    /* access modifiers changed from: private */
    public class b extends SQLiteOpenHelper {
        public b(Context context) {
            super(context, m.b, null, 7);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("create table if not exists MsgLogStore (MsgId varchar, ActionType Integer, Time long, pa varchar, PRIMARY KEY(MsgId, ActionType))");
            sQLiteDatabase.execSQL("create table if not exists MsgLogIdTypeStore (MsgId varchar, MsgType varchar, PRIMARY KEY(MsgId))");
            sQLiteDatabase.execSQL("create table if not exists MsgLogStoreForAgoo (MsgId varchar, TaskId varchar, MsgStatus varchar, Time long, PRIMARY KEY(MsgId, MsgStatus))");
            sQLiteDatabase.execSQL("create table if not exists MsgLogIdTypeStoreForAgoo (MsgId varchar, TaskId varchar, MsgStatus varchar, PRIMARY KEY(MsgId))");
            sQLiteDatabase.execSQL("create table if not exists MsgConfigInfo (SerialNo integer default 1, AppLaunchAt long default 0, UpdateResponse varchar default NULL)");
            sQLiteDatabase.execSQL("create table if not exists InAppLogStore (Time long, MsgId varchar, MsgType Integer, NumDisplay Integer, NumOpenFull Integer, NumOpenTop Integer, NumOpenBottom Integer, NumClose Integer, NumDuration Integer, NumCustom Integer, PRIMARY KEY(Time))");
            Log.i(MessageProvider.a, "MsgLogStoreHelper-->onCreate");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            String str = MessageProvider.a;
            Log.i(str, "oldVersion:" + i + ",newVersion:" + i2);
            if (i <= 4) {
                Log.i(MessageProvider.a, "MsgLogStoreHelper-->drop delete");
                sQLiteDatabase.execSQL("drop table MsgConfigInfo");
            }
            if (i <= 5) {
                Log.i(MessageProvider.a, "MsgLogStoreHelper-->drop MsgLogStore");
                sQLiteDatabase.execSQL("ALTER TABLE MsgLogStore ADD COLUMN pa varchar");
            }
            if (i <= 6 && a(sQLiteDatabase, m.i)) {
                Log.i(MessageProvider.a, "MsgLogStoreHelper-->alter InAppLogStore");
                sQLiteDatabase.execSQL("ALTER TABLE InAppLogStore ADD COLUMN NumCustom Integer");
            }
            onCreate(sQLiteDatabase);
            Log.i(MessageProvider.a, "MsgLogStoreHelper-->onUpgrade");
        }

        private boolean a(SQLiteDatabase sQLiteDatabase, String str) {
            boolean z = false;
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                Cursor rawQuery = sQLiteDatabase.rawQuery("select count(*) as c from sqlite_master where type = 'table' and name = '" + str.trim() + "'", null);
                if (rawQuery.moveToNext() && rawQuery.getInt(0) > 0) {
                    z = true;
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (Exception unused) {
            }
            return z;
        }
    }
}
