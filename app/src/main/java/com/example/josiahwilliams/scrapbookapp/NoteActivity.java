package com.example.josiahwilliams.scrapbookapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by Josiah Williams on 11/16/2015.
 */
public class NoteActivity extends MainFragmentWithButtons {

    private static final String EXTRA_NOTE_ID = "com.example.josiahwilliams.scrapbookapp.note_id";

    public static Intent newIntent(Context context, UUID noteId) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, noteId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID noteId = (UUID) getIntent().getSerializableExtra(EXTRA_NOTE_ID);
        return NoteFragment.newInstance(noteId);
    }
}
