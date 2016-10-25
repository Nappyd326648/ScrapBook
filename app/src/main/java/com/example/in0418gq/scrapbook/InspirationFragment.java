package com.example.in0418gq.scrapbook;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.Date;
import java.util.UUID;

/**
 * Created by nappy on 10/22/2016.
 */

public class InspirationFragment extends Fragment{


    private static final String ARG_INSPIRATION_ID = "inspiration_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT =1;


    private Inspiration mInspiration;
    private EditText mTitleField;
    private EditText mTag;
    private Button mConfirmButton;


    public static InspirationFragment newInstance(UUID inspirationId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_INSPIRATION_ID, inspirationId);

        InspirationFragment fragment = new InspirationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID inspirationId = (UUID) getArguments().getSerializable(ARG_INSPIRATION_ID);
        mInspiration = InspirationLab.get(getActivity()).getInspiration(inspirationId);

    }
    @Override
    public void onPause(){
        super.onPause();
        InspirationLab.get(getActivity()).updateInspiration(mInspiration);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inspiration_list, container, false);

        mTitleField = (EditText) v.findViewById(R.id.inspiration_title);
        mTitleField.setText(mInspiration.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mInspiration.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mConfirmButton = (Button) v.findViewById(R.id.ok);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");

                startActivity(i);
            }
        });

        return v;
    }
}
