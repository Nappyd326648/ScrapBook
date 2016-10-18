package com.example.in0418gq.scrapbook.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.in0418gq.scrapbook.Inspiration;
import com.example.in0418gq.scrapbook.database.InspirationDbSchema.InspirationTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by in0418gq on 10/18/16.
 */
public class InspirationCursorWrapper extends CursorWrapper {
    public InspirationCursorWrapper(Cursor cursor){super(cursor);}

    public Inspiration getInspiration(){
        String uuidString = getString(getColumnIndex(InspirationTable.Cols.UUID));
        String title = getString(getColumnIndex(InspirationTable.Cols.TITLE));
        long date = getLong(getColumnIndex(InspirationTable.Cols.DATE));

        Inspiration inspiration = new Inspiration(UUID.fromString(uuidString));
        inspiration.setTitle(title);
        inspiration.setDate(new Date(date));

        return inspiration;
    }

}
