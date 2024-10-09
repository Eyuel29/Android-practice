package com.joel.dialog_using_fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DialogFragment extends androidx.fragment.app.DialogFragment {

    public DialogFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_dialog, container, false);

        EditText usernameInput = view.findViewById(R.id.inputUsername);
        EditText passwordInput = view.findViewById(R.id.inputPassword);
        Button buttonLogin = view.findViewById(R.id.buttonOk);
        Button buttonCancel = view.findViewById(R.id.buttonCancel);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.setData(username,password);
                getDialog().dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getDialog().dismiss();
            }
        });

        return view;
    }
}