package com.kristhyana.soshiki;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Home extends AppCompatActivity {
@Bind(R.id.userName)
    TextView user;
    @Bind(R.id.compWeb)
    WebView companyWeb;
    @Bind(R.id.webMess)
    TextView webMess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        ParseUser currentUser = ParseUser.getCurrentUser();
        String id = currentUser.getUsername();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("EmployeeInfo");
        query.whereEqualTo("EmployeeID", id);
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> usersObject, ParseException e) {
                if (e == null) {
                    String name = usersObject.get(0).getString("employeeFirstName");
                    user.setText(name);

                } else {
                    Log.d("Email", "Error: " + e.getMessage());
                }
            }
        });
        Boolean isConnected = isNetworkAvailable();
        if(isConnected.equals(true)) {

            WebSettings webSettings = companyWeb.getSettings();
            webSettings.setJavaScriptEnabled(true);
            companyWeb.loadUrl("http://www.google.com");
        }else{
            companyWeb.setVisibility(View.INVISIBLE);
            webMess.setVisibility(View.VISIBLE);

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
