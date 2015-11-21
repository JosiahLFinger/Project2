package com.example.josiahwilliams.scrapbookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Josiah Williams on 11/21/2015.
 */
public class NotesDbOpenHelper extends SQLiteOpenHelper {


    //formatting
    public static abstract class FeedEntry implements BaseColumns {

        public static final String TABLE_NAME = "notees";

        public static final String UUID = "uuid";
        public static final String DATE = "date";
        public static final String TITLE = "title";
        public static final String CONTENTS = "contents";
        public static final String KEYWORDS = "keywords";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FeedEntry.UUID + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.DATE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.TITLE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.CONTENTS + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.KEYWORDS + TEXT_TYPE + COMMA_SEP +
                    " )";

    //for deletion
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    //If you change the database schema, you must increment the data base version
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "notesdb.db";

    SQLiteDatabase db;

    public NotesDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //opens database
    public void openDB() {
        db = getWritableDatabase();
    }

    //closes database
    public void closeDB() {

        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    //insert data into notes
    public long insert(int id, String date, String title, String contents, String keywords) {
        ContentValues values = new ContentValues();

        if (id != -1) {
            values.put(FeedEntry._ID, id); //needed with uuid???
            //values.put(FeedEntry.UUID, uuid);
            values.put(FeedEntry.DATE, date);
            values.put(FeedEntry.TITLE, title);
            values.put(FeedEntry.CONTENTS, contents);
            values.put(FeedEntry.KEYWORDS, keywords);
        }
        return db.insert(FeedEntry.TABLE_NAME, null, values);
    }

    //method to delete rows based on uuid
    public long delete(String uuid) {
        String where = FeedEntry.UUID + " = " + uuid;

        return db.delete(FeedEntry.TABLE_NAME, where, null);
    }

    public Cursor getAllRecords() {
        //db.query(FeedEntry.TABLE_NAME, null, null, null, null, null, null);

        String query = "SELECT * FROM " + FeedEntry.TABLE_NAME;
        return db.rawQuery(query, null);
    }
}
