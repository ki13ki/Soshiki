package com.kristhyana.soshiki;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Kristhyana on 10/21/2015.
 */
public class SendEmail extends AsyncTask<Void, Void, String> {
    @Override
    protected String doInBackground(Void... params)
    {
        try{GMailSender sender = new GMailSender("sender.sendermail.com","senders password");
            sender.sendMail("subject",
                    "body",
                    "sender.sendermail.com",
                    "reciepients.recepientmail.com");
        }
        catch(Exception e)
        {
            Log.e("error", e.getMessage(), e);
            return "Email Not Sent";
        }
        return "Email Sent";
    }

    @Override
    protected void onPostExecute(String result)
    {
    }
    @Override
    protected void onPreExecute()
    {
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
    }
}