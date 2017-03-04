package ware.ramesh.sachin.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by HP on 21/08/2016.
 */
public class PlusPlusDbHelper  extends SQLiteOpenHelper {

    public static final String dbName="srwDbase.db";
    String TABLE_NAME="expences";

    PlusPlusDbHelper(Context context)
    {
        super(context,  dbName, null, 1);
        // SQLiteDatabase.openOrCreateDatabase("/sdcard/sachin_Db/" + dbName, null);

        Log.d("===========>", " constructor called");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
