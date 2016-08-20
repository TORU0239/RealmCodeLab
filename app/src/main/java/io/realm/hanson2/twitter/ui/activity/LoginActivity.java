package io.realm.hanson2.twitter.ui.activity;

import io.realm.hanson2.twitter.R;
import io.realm.hanson2.twitter.auth.TwitterAuthUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final AccessToken accessToken = TwitterAuthUtil.loadAccessToken();
        if (accessToken != null) {
            /*
               엑세스 토큰이 저장되어 있는 경우나
               인증을 성공한 경우 여기를 지나간다.
            */
            TwitterFactory.getSingleton().setOAuthAccessToken(accessToken);
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        final View button = findViewById(R.id.login);
        //noinspection ConstantConditions
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeLogin();
            }
        });
    }

    private void executeLogin() {
        new AsyncTask<Void, Throwable, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                TwitterAuthUtil.clearTokens();
                final Twitter twitter = TwitterFactory.getSingleton();
                try {
                    final RequestToken requestToken = twitter.getOAuthRequestToken(TwitterAuthUtil.getCallbackUrlString());
                    final String authUrl = requestToken.getAuthorizationURL();
                    TwitterAuthUtil.saveRequestToken(requestToken);
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(authUrl)));
                }
                catch (TwitterException e) {
                    Log.e("RealmTwitter", "통신 오류가 발생했습니다: " + e.getMessage());
                    publishProgress(e);
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Throwable... values) {
                super.onProgressUpdate(values);

                Toast.makeText(LoginActivity.this, "통신 오류가 발생했습니다: " + values[0].getMessage(), Toast.LENGTH_LONG).show();
            }
        }.execute();
    }
}