package ware.ramesh.sachin.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.widget.Toast;

/**
 * Created by HP on 19/03/2016.
 */
public class DataBaseHelper   extends SQLiteOpenHelper
{
    public static final String dbName="srwDbase.db";
    String TABLE_NAME="REGISTRATION";
    String name=null;
    String mobile;
    String email;
    String password;
    String  address;

    int id=0;




    public DataBaseHelper(Context context)
    {
        super(context,  dbName, null, 1);
          // SQLiteDatabase.openOrCreateDatabase("/sdcard/sachin_Db/" + dbName, null);

        Log.d("===========>", " constructor called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,MOBILE TEXT)");
            Log.d("===========>", " REGISTRATION table created");

        }catch (Exception ex)
        {
            Log.d("===========>"," Exception Occured in table creation: "+ex.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            db.execSQL("drop table if exists "+TABLE_NAME);
            Log.d("===========>", " REGISTRATION table dropped");
            onCreate(db);
        }
        catch (Exception ex)
        {
            Log.d("===========>","Exception Occured in table dropp: "+ex.getMessage());
        }
    }


    public boolean insertData(String username,String usermobile)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put("NAME",username);
            cv.put("MOBILE",usermobile);
            Log.d("in insertData=======>", "userName: " + username + " mobile:" + usermobile);

            SQLiteDatabase db=this.getWritableDatabase();
            Long result=db.insert(TABLE_NAME,null,cv);
            Log.d("in insertData======>", "result:" + result);
            if(result==-1)
                return false;
            else
                return true;

        }
        catch(Exception e)
        {
            Log.d("=========>","Insert Error: "+e.getMessage());
            return false;
        }
    }

    public Cursor getAllData()
    {
        Cursor cursor=null;
        try
        {
            SQLiteDatabase db=this.getWritableDatabase();
            cursor=db.rawQuery("select * from " + TABLE_NAME, null);
        }
        catch (Exception e)
        {
            Log.d("Exception=====>","getAllData Message:"+e.getMessage());
        }
        return cursor;
    }

    public int deleteRecords()
    {
        int rowsDeleted=0;
        try
        {
            SQLiteDatabase db=this.getWritableDatabase();
           // db.execSQL("delete * from "+TABLE_NAME);
             rowsDeleted=db.delete(TABLE_NAME, null, null);


        }
        catch (Exception e)
        {
            Log.d("Exception=====>","clearRecords Message:"+e.getMessage());
        }
        return  rowsDeleted;
    }


}
