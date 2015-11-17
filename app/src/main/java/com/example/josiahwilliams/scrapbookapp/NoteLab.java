package com.example.josiahwilliams.scrapbookapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

//import com.example.josiahwilliams.scrapbookapp.NoteDatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Josiah Williams on 11/16/2015.
 */
public class NoteLab {

    private static NoteLab sNoteList;

    private List<Note> mNotes;

    private Context mContext;
    private SQLiteDatabase mNoteDatabase;

    public static NoteLab getList(Context context) {
        if (sNoteList == null) {
            sNoteList = new NoteLab(context);
        }
        return sNoteList;
    }

    private NoteLab(Context context) {
        //TODO get notes from database
        //populates list with temporary notes
        mNotes = new ArrayList<>();

        for (int x = 1; x < 26; x++) {
            Note note = new Note();
            note.setTitle("Idea number " + x);
            note.setContents("In here is going to be where the user would put their note contents \n" +
                    "for their note in idea number " + x);
            note.setKeyword("#idea" + x + " #randomthingnumber" + x);
            mNotes.add(note);
        }
    }

    public List<Note> getNotes() {
        return mNotes;
    }

    public Note getNote(UUID noteId) {
        for (Note note:mNotes) {
            if (note.getId().equals(noteId)) {
                return note;
            }
        }
        return null;
    }
}
