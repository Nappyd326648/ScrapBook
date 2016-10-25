package com.example.in0418gq.scrapbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.in0418gq.scrapbook.database.InspirationBaseHelper;
import com.example.in0418gq.scrapbook.database.InspirationCursorWrapper;
import com.example.in0418gq.scrapbook.database.InspirationDbSchema.InspirationTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by nappy on 10/19/2016.
 */

public class InspirationLab {
    private static InspirationLab sinspirationLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static InspirationLab get(Context context){
        if (sinspirationLab==null){
            sinspirationLab=new InspirationLab(context);
        }
        return sinspirationLab;
    }
    public void addInspiration(Inspiration i){
        ContentValues values = getContentValues(i);
        mDatabase.insert(InspirationTable.NAME,null,values);
    }

    private InspirationLab(Context context){
        mContext= context.getApplicationContext();
        mDatabase = new InspirationBaseHelper(mContext).getWritableDatabase();
    }

    public List<Inspiration> getInspirations(){
        List<Inspiration> inspirations = new ArrayList<>();
        InspirationCursorWrapper cursor = queryInspirations(null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            inspirations.add(cursor.getInspiration());
            cursor.moveToNext();
        }
        cursor.close();
        return inspirations;
    }

    public Inspiration getInspiration(UUID id){
        InspirationCursorWrapper cursor = queryCrimes(
                InspirationTable.Cols.UUID+" = ?",
                new String[]{id.toString()});
        try {
            if (cursor.getCount()== 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getInspiration();
        }finally {
            cursor.close();
        }
    }
    public File getPhotoFile(Inspiration inspiration){
        File externalFileDir=mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFileDir== null){
            return null;
        }
        return new File(externalFileDir,inspiration.getPhotoFilename());
    }
    public void updateCrime(Inspiration inspiration){
        String uuidString = inspiration.getId().toString();
        ContentValues values = getContentValues(inspiration);
        mDatabase.update(InspirationTable.NAME, values,
                InspirationTable.Cols.UUID+" = ?",
                new String[]{uuidString});
    }
    private static ContentValues getContentValues(Inspiration inspiration) {
        ContentValues values = new ContentValues();
        values.put(InspirationTable.Cols.UUID, inspiration.getId().toString());
        values.put(InspirationTable.Cols.TITLE, inspiration.getTitle());
        values.put(InspirationTable.Cols.DATE, inspiration.getDate().getTime());


        return values;
    }
    private InspirationCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(InspirationTable.NAME, null, whereClause,
                whereArgs,null,null,null );
        return new InspirationCursorWrapper(cursor);
    }
}
