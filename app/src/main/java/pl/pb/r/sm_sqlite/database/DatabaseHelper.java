package pl.pb.r.sm_sqlite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Radosław Naruszewicz on 2016-11-30.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "example.db";
    private static final int SCHEMA = 1;
    static final String ID = "_ID";
    static final String NAME = "name";
    static final String VALUE = "value";
    static final String TABLE = "tab_one";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE tab_one (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, value REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        throw  new RuntimeException("It's not possible");
    }
}
