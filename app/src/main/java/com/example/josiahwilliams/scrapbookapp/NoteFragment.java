package com.example.josiahwilliams.scrapbookapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Josiah Williams on 11/16/2015.
 */
public class NoteFragment extends Fragment {

    private static final String ARG_NOTE_ID = "note_id";

    NotesDbOpenHelper dbhelper;

    private Note mNote;
    private EditText mTitle;
    private EditText mNoteContents;
    private EditText mKeywords;
    //TODO add save button instead of onTextChangedListener
    private Button mSaveNoteButton;
    private Button mDeleteNoteButton;


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

        //open database
        dbhelper.openDB();

        //creates new note with it's own unique id
        UUID noteId = (UUID) getArguments().getSerializable(ARG_NOTE_ID);
        mNote = (Note) NoteLab.getList(getActivity()).getNote(noteId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        //create TextFields, set it's text, and set up textChangedListener
        mTitle = (EditText) view.findViewById(R.id.title_edit_text);
        mTitle.setText(mNote.getTitle());

        mNoteContents = (EditText) view.findViewById(R.id.note_contents_edit_text);
        mNoteContents.setText(mNote.getContents());

        mKeywords = (EditText) view.findViewById(R.id.keywords_edit_text);
        mKeywords.setText(mNote.getKeyword());

        //TODO make button save to database
        mSaveNoteButton = (Button) view.findViewById(R.id.save_note_button);
        mDeleteNoteButton = (Button) view.findViewById(R.id.delete_note_button);
        mSaveNoteButton.setOnClickListener(dbButtonListener);
        mDeleteNoteButton.setOnClickListener(dbButtonListener);

        return view;
    }

    //onClick listener for database buttons
    private View.OnClickListener dbButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.new_note_button:
                    Date date = new Date();
                    long result = dbhelper.insert(-1, date.toString(), getValue(mTitle), getValue(mNoteContents), getValue(mKeywords))

                    if(result == -1) {
                        Toast.makeText(getActivity(), "error occured while inserting", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "New note saved successfully" + result, Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.save_note_button:

                    break;
                case R.id.delete_note_button:
                    break;
            }
        }
    };

    //formating for dbbuttonlistener for easier entry
    public String getValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    @Override
    public void onStop(){
        super.onStop();
        //close database
        dbhelper.closeDB();
    }
}
