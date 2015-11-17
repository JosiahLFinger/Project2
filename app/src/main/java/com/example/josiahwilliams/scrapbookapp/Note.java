package com.example.josiahwilliams.scrapbookapp;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Josiah Williams on 11/16/2015.
 */
public class Note {

    private UUID mId;
    private String mKeyword;
    private String mTitle;
    private String mContents;
    private Date mDate;

    public Note() {
        //generate unique id
        mId = UUID.randomUUID();
        //get current date
        mDate = new Date();
    }

    public Note(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getKeyword() {
        return mKeyword;
    }
    public void setKeyword(String keyword) {
        mKeyword = keyword;
    }

    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContents() {
        return mContents;
    }
    public void setContents(String contents) {
        mContents = contents;
    }
}
