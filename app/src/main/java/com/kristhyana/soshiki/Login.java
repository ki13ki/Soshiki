package com.kristhyana.soshiki;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


import butterknife.Bind;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity  implements ForgotPass.Communicator{

    @Bind(R.id.companyIcon)
    ImageView compIm;
    @Bind(R.id.username)
    EditText user;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.loginMessage)
    TextView loginMess;
    @Bind(R.id.loginBtn)
    Button login;
    @Bind(R.id.forgotPassword)
    TextView forPass;
    String usernameTxt;
    String passwordTxt;
    int count = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    public void LoginCred(View view) {
        usernameTxt = user.getText().toString();
        passwordTxt = password.getText().toString();

        if (usernameTxt != null || passwordTxt != null) {


            ParseUser.logInInBackground(usernameTxt, passwordTxt, new LogInCallback() {
                public void done(ParseUser userInfo, ParseException e) {
                    if (e == null) {
                        Intent intent;
                        if (userInfo.getBoolean("active") == true) {
                            if (userInfo.getString("tempPassword") != null) {
                                intent = new Intent(Login.this, ResetPass.class);
                                startActivity(intent);
                                finish();
                            } else {
                                intent = new Intent(Login.this, Home.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            loginMess.setVisibility(View.VISIBLE);
                            loginMess.setText(R.string.notActive);
                        }
                    } else {
                        count--;
                        loginMess.setVisibility(View.VISIBLE);
                        loginMess.setText(R.string.login_message);
                    }
                }
            });
            if (count == 0) {
                login.setEnabled(false);
                loginMess.setVisibility(View.VISIBLE);
                loginMess.setText(R.string.login_lock);
            }
        } else{
            loginMess.setVisibility(View.VISIBLE);
            loginMess.setText(R.string.nullLogin);
        }
    }

    public void ForgotPass(View view) {
        showDialog(view);

    }

    public void showDialog(View view) {

        FragmentManager manager = getFragmentManager();
        ForgotPass forgotPass = new ForgotPass();
        forgotPass.show(manager, "Forgot Password");
    }

    @Override
    public void onDialogMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }
}
