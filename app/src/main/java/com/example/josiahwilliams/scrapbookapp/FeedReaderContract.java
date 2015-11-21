package com.example.josiahwilliams.scrapbookapp;

import android.provider.BaseColumns;

/**
 * Created by Josiah Williams on 11/16/2015.
 */
public final class FeedReaderContract {

    public FeedReaderContract() {}

    public static abstract class FeedEntry implements BaseColumns {

        public static final String TABLE_NAME = "entry";

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
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.UUID + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.DATE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.TITLE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.CONTENTS + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.KEYWORDS + TEXT_TYPE + COMMA_SEP +
                    " )";

    //for deletion
    private static final String SQL_DELETE_ENTRIES =
    "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

}
