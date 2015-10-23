package com.kristhyana.soshiki;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResetPass extends AppCompatActivity {
    @Bind(R.id.oldPass)
    EditText tempPass;
    @Bind(R.id.NewPass)
    EditText newPass;
    @Bind(R.id.NewPass2)
    EditText newPass2;
    @Bind(R.id.enterBtn)
    Button enter;
    @Bind(R.id.resetErrorMess)
    TextView resetMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        ButterKnife.bind(this);
    }


    public void checkNewPassword(View view) {
        String pass1 = newPass.getText().toString();
        String pass2 = newPass2.getText().toString();
        String pass = tempPass.getText().toString();
        if (pass.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            resetMessage.setVisibility(View.VISIBLE);
            resetMessage.setText("All fields are required!");
        } else {
            if (!pass1.equals(pass2)) {
                resetMessage.setVisibility(View.VISIBLE);
                resetMessage.setText("New Password fields do not match");
                newPass.getText().clear();
                newPass2.getText().clear();
            } else {
                changePassword(tempPass.getText().toString(), newPass.getText().toString());
                Intent intent = new Intent(ResetPass.this, Home.class);
                startActivity(intent);
                finish();
            }
        }

    }


    private void changePassword(final String currentPass, final String newPassword)  {
        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.setPassword(newPassword);
        currentUser.put("tempPassword", "");
        currentUser.saveInBackground();

//        ParseQuery<ParseUser> query = ParseUser.getQuery();
//        query.fromLocalDatastore();
//        query.whereEqualTo("password", currentPass);
//        query.findInBackground(new FindCallback<ParseUser>() {
//            @Override
//            public void done(List<ParseUser> usersObject, ParseException e) {
//                if (e == null) {
//                    usersObject.get(0).put("tempPassword", null);
//                    usersObject.get(0).saveInBackground();
//                } else {
//                    Log.d("password", "Error: " + e.getMessage());
//                }
//            }
//        });

    }
}
