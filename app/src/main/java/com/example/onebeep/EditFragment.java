package com.example.onebeep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditFragment extends Fragment {





    public EditFragment() {
        // Required empty public constructor
    }

//    public static EditFragment newInstance(String param1, String param2) {
//        EditFragment fragment = new EditFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        Button sbtn = view.findViewById(R.id.sbtn);
        Button erbbtn = view.findViewById(R.id.erbbtn);
        final EditText editTime = view.findViewById(R.id.editTime);



        sbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (editTime.getText().toString().length() > 6 || editTime.getText().toString().length() < 6) {
                        Toast.makeText(v.getContext(), "Invalid Value", Toast.LENGTH_SHORT).show();
                    }
                    if (Integer.parseInt(editTime.getText().toString().substring(0, 2)) >= 0 && Integer.parseInt(editTime.getText().toString().substring(0, 2)) <= 23 &&
                            Integer.parseInt(editTime.getText().toString().substring(2, 4)) >= 0 && Integer.parseInt(editTime.getText().toString().substring(2, 4)) <= 59 &&
                            Integer.parseInt(editTime.getText().toString().substring(4, 6)) >= 0 && Integer.parseInt(editTime.getText().toString().substring(4, 6)) <= 59
                            &&!(Integer.parseInt(editTime.getText().toString().substring(0, 2)) == 0 && Integer.parseInt(editTime.getText().toString().substring(2,4)) == 0 &&
                            Integer.parseInt(editTime.getText().toString().substring(4,6)) == 0)) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("changedTime", editTime.getText().toString());
//                        getContext().setResult(Activity.RESULT_OK, resultIntent);
//                        getContext().finish();
                    } else {
                        Toast.makeText(v.getContext(), "Please select a value between 000001 and 235959", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e){
                    Toast.makeText(v.getContext(), "Invalid Value", Toast.LENGTH_SHORT).show();
                }
            }
        });
        erbbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("changedTime", "nochange");
//                setResult(Activity.RESULT_OK, resultIntent);
//                finish();
            }
        });

        return view;
    }
}