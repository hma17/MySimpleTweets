package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;


public class ComposeActivity extends AppCompatActivity {

    EditText etCompose;
    TwitterClient client;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etCompose = (EditText) findViewById(R.id.etCompose);


    }

    //network request to statuses/update
    public void onSendTweet(View view) {
                client.sendTweet(etCompose.toString(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Tweet newtweet = null;
                        try {
                            newtweet = Tweet.fromJSON(response);
                            Intent i = new Intent();
                            setResult(RESULT_OK, i);
                            finish();
                            i.putExtra("tweet", Parcels.wrap(newtweet)); // pass arbitrary data to launched activity
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                });
            }




    }


