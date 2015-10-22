package com.kristhyana.soshiki;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by Kristhyana on 10/21/2015.
 */
public class ParseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        //Parse initialization code
        Parse.initialize(this, "gxVqw2SQCeAeSOLUFE7A2QYXGGf8AGTqQEYDrud1",
                "C6Ai62I1r66zt4A1Ibmpg7bt4MDMsy1pll4Lk3pC");


        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
