package com.example.josiahwilliams.scrapbookapp;

/**
 * Created by Josiah Williams on 11/16/2015.
 */
public class NoteDatabase {

    public static final class NoteTable {
        public static final String NAME = "saved_notes";

        public static final class Columns {
            public static final String UUID = "uuid: ";
            public static final String DATE = "date: ";
            public static final String TITLE = "title: ";
            public static final String CONTENTS = "contents: ";
            public static final String KEYWORDS = "keywords: ";
        }
    }
}
