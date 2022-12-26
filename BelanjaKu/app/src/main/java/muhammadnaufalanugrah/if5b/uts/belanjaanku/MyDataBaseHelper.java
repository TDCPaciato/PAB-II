package muhammadnaufalanugrah.if5b.uts.belanjaanku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private Context ctx;
    private static final String DATABASE_NAME = "DB_BELANJA";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tbBelanja";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAMA = "nama";
    private static final String FIELD_KATEGORI = "kategori";
    private static final String FIELD_HARGA = "harga";

    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_NAMA + " TEXT, " +
                FIELD_KATEGORI + " TEXT, " +
                FIELD_HARGA + " DOUBLE ); ";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addBelanja(Belanja belanja){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NAMA, belanja.getNama());
        cv.put(FIELD_KATEGORI, belanja.getKategori());
        cv.put(FIELD_HARGA, belanja.getHarga());

        long result = db.insert(TABLE_NAME, null, cv);
        return result;
    }

    public Cursor getAllBelanja (){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
