package rizky_kurniawan.if5b.uts.belanjaanku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DatabaseName = "dbBelanjaKu";
    private static final int DatabaseVersion = 1;
    private static final String TABLE_NAME = "TblBelanjaKu";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_KATEGORI = "kategori";
    private static final String COLUMN_HARGA = "harga";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAMA + " TEXT, "
                + COLUMN_KATEGORI + " TEXT, "
                + COLUMN_HARGA + " TEXT ); ";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertBelanjaKu(String nama, String kategori, String harga) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAMA, nama);
        contentValues.put(COLUMN_KATEGORI, kategori);
        contentValues.put(COLUMN_HARGA, harga);

        long hasil = sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        return hasil;
    }

    public Cursor getBelanjaKu() {
        String query = "SELECT*FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = null;

        if (sqLiteDatabase != null)
            cursor = sqLiteDatabase.rawQuery(query, null);

        return cursor;
    }
}
