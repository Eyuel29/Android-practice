package com.joel.messsage_app_demo;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageFragment extends DialogFragment {

    MessageSender messageSender;

    public void setMessageSender(MessageSender messageSender){
        this.messageSender = messageSender;
    }

    public MessageFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_message, container, false);

        EditText inputMessage = view.findViewById(R.id.inputMessageContent);
        EditText inputPhone = view.findViewById(R.id.inputReceiverPhone);
        Button buttonSend = view.findViewById(R.id.buttonSendMessage);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputMessage.getText().toString().equals("") ||
                    inputPhone.getText().toString().equals("")){
                    Toast.makeText(requireActivity(), "Invalid input", Toast.LENGTH_SHORT).show();
                }else{
                    String messageContent = inputMessage.getText().toString();
                    String messageReceiver = inputPhone.getText().toString();
                    messageSender.sendMessage(messageContent, messageReceiver);
                    getDialog().dismiss();
                }
            }
        });
        return view;
    }
}