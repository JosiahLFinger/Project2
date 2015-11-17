package com.example.josiahwilliams.scrapbookapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by Josiah Williams on 11/16/2015.
 */
public class NoteFragment extends Fragment {

    private static final String ARG_NOTE_ID = "note_id";

    private Note mNote;
    private EditText mTitle;
    private EditText mNoteContents;
    private EditText mKeywords;

    //creates a new instance of notefragment to store not info on
    public static NoteFragment newInstance(UUID noteId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTE_ID, noteId);

        //Create new fragment and give it the id from above argument
        NoteFragment noteFragment = new NoteFragment();
        noteFragment.setArguments(args);
        return noteFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);

        //creates new note with it's own unique id
        UUID noteId = (UUID) getArguments().getSerializable(ARG_NOTE_ID);
        mNote = (Note) NoteLab.getList(getActivity()).getNote(noteId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        //create editTextField, set it's text, and set up textChangedListener
        mTitle = (EditText) view.findViewById(R.id.title_edit_text);
        mTitle.setText(mNote.getTitle());
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nothing here
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNote.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //nothing here
            }
        });

        //create editTextField, set it's text, and set up textChangedListener
        mNoteContents = (EditText) view.findViewById(R.id.note_contents_edit_text);
        mNoteContents.setText(mNote.getContents());
        mNoteContents.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nothing here
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNote.setKeyword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //nothing here
            }
        });

        //create editTextField, set it's text, and set up textChangedListener
        mKeywords = (EditText) view.findViewById(R.id.keywords_edit_text);
        mKeywords.setText(mNote.getKeyword());
        mKeywords.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nothing here
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNote.setContents(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //nothing here
            }
        });
        return view;
    }
}
