package com.vineetha.mobilelab_7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteDB extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "lab3.db";
    private static final String USER_DATA = "USER_INFORMATION";
    private static final String COLUMN_1 = "EMAIL_ID";
    private static final String COLUMN_2 = "TYPE";
    private static final String COLUMN_3 = "INFORMATION";

    public SQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("On Create");
        // Creating Table First
        db.execSQL("create table " + USER_DATA +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "EMAIL_ID TEXT,TYPE TEXT,INFORMATION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // On DB Upgrade, Dropping and Creating Table Again
        db.execSQL("DROP TABLE IF EXISTS "+USER_DATA);
        onCreate(db);
    }

    // Inserting data
    public boolean insertData(String emailId,String type,String information) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1,emailId.toLowerCase());
        contentValues.put(COLUMN_2,type);
        contentValues.put(COLUMN_3,information);
        // Inserting the Data, Return Type will be 'long'
        long result = db.insert(USER_DATA,null ,contentValues);
        if(result == -1) {
            //f Insert haven't happened properly, it will return a "long" value '-1'
            // as Output
            return false;
        }
        else {
            // If Insertion is Successful
            return true;
        }
    }

    // Check if Data Exist
    public Cursor checkIfTypeExistForUser(String emailId, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+USER_DATA+" where LOWER(EMAIL_ID) = '"+emailId.toLowerCase()+"'"
                +" AND TYPE = '"+type+"'" ,null);
        return res;
    }


    // Updating the Data
    public boolean updateData(String emailId,String type,String information) {

        SQLiteDatabase db = this.getWritableDatabase();
        String filter = "LOWER(EMAIL_ID)='"+emailId.toLowerCase()+"'";
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1,emailId.toLowerCase());
        contentValues.put(COLUMN_2,type);
        contentValues.put(COLUMN_3,information);
        // Updating the Data, Return Type will be 'long'
        long result = db.update(USER_DATA,contentValues ,filter,null);
        if(result == -1) {
            //f Insert haven't happened properly, it will return a "long" value '-1'
            // as Output
            return false;
        }
        else {
            // If Insertion is Successful
            return true;
        }
    }


//    public Cursor retrieve(){
//        SQLiteDatabase db = getReadableDatabase();
//        String[] cols = {
//                COLUMN_1,
//                COLUMN_2};
//        String sortOrder = COLUMN_1 + " ASC";
//        Cursor c = db.query(
//                USER_DATA,                    // The table to query
//                cols,                                 // The columns to return
//                null,                                       // The columns for the WHERE clause
//                null,                                       // The values for the WHERE clause
//                null,                                       // don't group the rows
//                null,                                       // don't filter by row groups
//                null                                   // The sort order
//        );
//        return c;
//    }

}