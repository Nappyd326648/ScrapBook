package com.example.in0418gq.scrapbook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.in0418gq.scrapbook.database.InspirationDbSchema.InspirationTable;

/**
 * Created by in0418gq on 10/18/16.
 */
public class InspirationBaseHelper extends SQLiteOpenHelper {
    private static final String TAG ="InspirationBaseHelper";
    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "inspirationBase.db";

    public InspirationBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //building the table
        db.execSQL("create table" + InspirationTable.NAME + "(" +
        "_id integer primary key autoincrement, " + InspirationTable.Cols.UUID + ", "
        + InspirationTable.Cols.TITLE + ", " + InspirationTable.Cols.DATE + ")");
    }
    @Override
    public void  onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
