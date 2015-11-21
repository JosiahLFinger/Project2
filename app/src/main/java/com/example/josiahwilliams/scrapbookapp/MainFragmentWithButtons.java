package com.example.josiahwilliams.scrapbookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Josiah Williams on 11/16/2015.
 */
public abstract class MainFragmentWithButtons extends AppCompatActivity{

    protected abstract Fragment createFragment();

    private Button mNoteButton;
    private Button mCameraButton;

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_main_view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        mNoteButton = (Button) view.findViewById(R.id.note_button);
        mNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity())
            }
        });

        mCameraButton = (Button) view.findViewById(R.id.camera_button);
        return view;
    }
}
