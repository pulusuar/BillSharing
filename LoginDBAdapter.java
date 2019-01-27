package com.avinash.billsharingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by AVINASH on 27-07-2017.
 */

public class LoginDBAdapter {

    //Details of Database and TableName
    public static final String DATABASE_NAME = "signup";
    public static final String DATABASE_TABLE = "login";
    public static final int DATABASE_VERSION = 1;
    public static final String TAG = "LoginDBAdapter";

    //Columns of Table "login"
    public static final String COL_ROW_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_PHONE = "phoneNo";

    //Query to create Database Table
    private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + "(" + COL_ROW_ID + " integer primary key autoincrement," +
            COL_NAME + " text not null," + COL_USERNAME + " text not null," + COL_PASSWORD + " text not null," + COL_PHONE + " text not null);";

    Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public LoginDBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper=new DatabaseHelper(context);
    }


    public static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    //Opens the Database
    LoginDBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //Closes the Database
    void close() {
        DBHelper.close();
    }

    //To insert details
    long insert_details(String name, String username, String password, String phone) {
        ContentValues initialValues = new ContentValues();
        //Values to be inserted in the table
        initialValues.put(COL_NAME, name);
        initialValues.put(COL_USERNAME, username);
        initialValues.put(COL_PASSWORD, password);
        initialValues.put(COL_PHONE, phone);
        //Quert to insert the values
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    boolean checkUser(String un){
        //Array for columns to fetch
        String columns[]={COL_ROW_ID};
        db=DBHelper.getReadableDatabase();
        //Selection criteria
        String selection=COL_USERNAME+"=?";
        //selection arguments
        String[] selectionArgs={un};
        //Query to get all the records based on above criteria
        Cursor cursor=db.query(DATABASE_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        //No. of records accessed
        int cursorCount=cursor.getCount();
        cursor.close();
        //If the records accessed are non-zero
        if(cursorCount>0)
            return true;
        //If no records were accessed
        return false;
    }

    boolean checkValid(String un,String pw){
        //Array for columns to fetch
        String columns[]={COL_ROW_ID};

        db=DBHelper.getReadableDatabase();
        //Selection criteria
        String selection=COL_USERNAME+"=?"+" AND "+COL_PASSWORD+"=?";
        //selection arguments
        String[] selectionArgs={un,pw};
        //Query to fetch all records based on above criteria
        Cursor cursor=db.query(DATABASE_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        //No. of records accessed
        int cursorCount=cursor.getCount();
        cursor.close();
        //If the accessed records are non-zero
        if(cursorCount>0)
            return true;
        //If no records were accessed
        return false;
    }
}