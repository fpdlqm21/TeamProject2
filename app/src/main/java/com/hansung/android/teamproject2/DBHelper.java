package com.hansung.android.teamproject2;

//데이터베이스를 저장 삭제 수정 하려는 DBHelper(06.12)
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    final static String TAG = "SQLiteDBTest";

    public DBHelper(Context context) {
        super(context, UserContract.DB_NAME, null, UserContract.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,getClass().getName()+".onCreate()");
        db.execSQL(UserContract.Users.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, getClass().getName() + ".onUpgrade()");
        db.execSQL(UserContract.Users.DELETE_TABLE);
        onCreate(db);
    }

    public void insertUserBySQL(String hour, String minute, String AMPM) {
        try{
            String sql = String.format(
                    "INSERT INTO %s (%s, %s, %s) VALUES ('%s', '%s', '%s')",
                    UserContract.Users.TABLE_NAME,
                    UserContract.Users._ID,
                    UserContract.Users.KEY_HOUR,
                    UserContract.Users.KEY_MINUTE,
                    UserContract.Users.KEY_AMPM,
                    hour, minute, AMPM
            );

            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG, "Error in inserting recodes");
        }
    }

    public Cursor getAllUseresBySQL() {
        String sql = "Select * FROM "+ UserContract.Users.TABLE_NAME;
        return getReadableDatabase().rawQuery(sql, null);
    }
}
