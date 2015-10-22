package com.kristhyana.soshiki;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForgotPass extends DialogFragment  implements View.OnClickListener {
    @Bind(R.id.forgotPassEmail)
    EditText mEmail;
    @Bind(R.id.sendBtn)
    Button sendBtn;
    @Bind(R.id.nullEmailMess)
    TextView nullEmess;
    Communicator communicator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof Communicator) {
            communicator = (Communicator) activity;
        } else {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_forgot_pass, container);
        ButterKnife.bind(this, view);
        sendBtn.setOnClickListener(this);
        //  getDialog().setTitle("Forgot Password");
        return view;
    }

    @Override
    public void onClick(View v) {
        if (mEmail != null) {
            sendEmail();
        } else {
            nullEmess.setVisibility(View.VISIBLE);
            nullEmess.setText(R.string.nullEmail);
        }

    }

    interface Communicator {
        public void onDialogMessage(String message);
    }


    public void sendEmail() {
        String temp = tempPassword();
        

        try {
            GMailSender sender = new GMailSender("sendermail@gmail.com", "password");
            sender.sendMail("Reset Password",
                    "You have requested to reset your password. " +
                            "The temporay password is " + temp +"." +" When you login into your account" +
                            " you will be requested to reset your password.",
                    "sendermail@gmail.com",
                    "receivermail@gmail.com");
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }

    }

    private String tempPassword() {
    }
}
