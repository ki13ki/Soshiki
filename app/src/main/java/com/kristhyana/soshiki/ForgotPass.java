package com.kristhyana.soshiki;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import java.util.List;
import java.util.Random;

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
        return view;
    }

    @Override
    public void onClick(View v) {
        String recipient = mEmail.getText().toString();
        if (recipient != null) {
            sendEmail(recipient);
            dismiss();
        } else {
            nullEmess.setVisibility(View.VISIBLE);
            nullEmess.setText(R.string.nullEmail);
        }

    }

    interface Communicator {
        public void onDialogMessage(String message);
    }


    public void sendEmail(String recieverEmail) {
        ParseUser.requestPasswordResetInBackground(recieverEmail, new RequestPasswordResetCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("resetEmail", "successfully Send.");
                } else {
                   Log.d("Error", "Reset password email fail to be sent" + e.getMessage());
                }
            }
        });
        /*String temp = tempPassword(recieverEmail);
        String senderEmail = getString(R.string.senderEmail);
        String tokenPass = getString(R.string.token);
        if (senderEmail !=null || tokenPass != null) {
            try {
                GMailSender sender = new GMailSender(senderEmail, tokenPass);
                sender.sendMail("Reset Password",
                        "You have requested to reset your password. " +
                                "The temporay password is " + temp + "." + " When you login into your account" +
                                " you will be requested to reset your password.", senderEmail, recieverEmail);

            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
        }else{
            Log.d("Error", "Missing info ");
        }
*/
    }


/*    private String tempPassword(String recieverEmail) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            char c = chars[random.nextInt(chars.length)];//new Character((char) (random.nextInt(chars.length) + 65));
            sb.append(c);
        }
        String output = sb.toString();
        Log.d("pass", output);
        setPassword(output, recieverEmail);
        return output;

    }

    private void setPassword(final String pass, String email) {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.fromLocalDatastore();
        query.whereEqualTo("email", email);
        query.findInBackground(new FindCallback<ParseUser>() {

            @Override
            public void done(List<ParseUser> usersObject, ParseException e) {
                if (e == null) {
                    usersObject.get(0).put("password", pass);
                    usersObject.get(0).put("tempPassword", pass);
                    usersObject.get(0).saveInBackground();

                } else {
                    Log.d("Email", "Error: " + e.getMessage());
                }
            }
        });
    }*/
}
