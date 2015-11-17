package com.example.josiahwilliams.scrapbookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Josiah Williams on 11/16/2015.
 */
public class NoteListFragment extends Fragment {

    private RecyclerView mNoteRecyclerView;
    private NoteAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        mNoteRecyclerView = (RecyclerView) view.findViewById(R.id.note_recycler_view);
        mNoteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        //builds the list
        NoteLab noteLab = NoteLab.getList(getActivity());
        List<Note> notes = noteLab.getNotes();

        //if there is no adapter it gets built
        if (mAdapter == null) {
            mAdapter = new NoteAdapter(notes);
            mNoteRecyclerView.setAdapter(mAdapter);
            //if there is an adapter, data is changed
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Note mNote;

        private TextView mTitleTextView;
        private TextView mContecntsTextView;
        private TextView mKeywordsTextView;

        public NoteHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            //sets up the new view based on id's
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_note_title_text_view);
            mContecntsTextView = (TextView) itemView.findViewById(R.id.list_item_note_content_text_view);
            mKeywordsTextView = (TextView) itemView.findViewById(R.id.list_item_note_keywords_text_view);
        }

        @Override
        //method for when a view is pressed
        public void onClick(View v) {
            //gets Note id to display notes
            Intent intent = NoteActivity.newIntent(getActivity(), mNote.getId());
            startActivity(intent);
        }

        //method gathers information from the note and sets the text
        public void bindNote(Note note) {
            mNote = note;

            //TODO get not info from database
            mTitleTextView.setText(mNote.getTitle());
            mContecntsTextView.setText(mNote.getContents());
            mKeywordsTextView.setText(mNote.getKeyword());
        }
    }

    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder> {

        private List<Note> mNotes;

        public NoteAdapter(List<Note> notes) {
            mNotes = notes;
        }

        @Override
        //called when new view is needed
        public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(getActivity());
            //create view using the layout just created
            View view = li.inflate(R.layout.list_note_item, parent, false);
            //wrap in noteholder
            return new NoteHolder(view);
        }

        @Override
        public void onBindViewHolder(NoteHolder holder, int position) {
            Note note = mNotes.get(position);
            //sends note to method to set text in text views
            holder.bindNote(note);
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }
    }
}
