package io.realm.hanson2.twitter.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import io.realm.hanson2.twitter.R;
import io.realm.hanson2.twitter.auth.TwitterAuthUtil;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class CallbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callback);

        final Uri uri = getIntent().getData();
        if (uri == null || !uri.toString().startsWith(TwitterAuthUtil.getCallbackUrlString())) {
            Toast.makeText(this, "unexpected Uri in Intent: " + uri, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        final String verifier = uri.getQueryParameter("oauth_verifier");

        final RequestToken requestToken = TwitterAuthUtil.loadRequestToken();
        TwitterAuthUtil.clearTokens();
        if (requestToken == null) {
            Toast.makeText(this, "RequestToken 읽기를 실패했습니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        new AsyncTask<Void, Throwable, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final Twitter twitter = TwitterFactory.getSingleton();

                    final AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
                    TwitterAuthUtil.saveAccessToken(accessToken);
                    startMainActivity();
                } catch (TwitterException e) {
                    Log.e("RealmTwitter", "통신 오류가 발생했습니다: " + e.getMessage());
                    publishProgress(e);
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Throwable... values) {
                super.onProgressUpdate(values);

                Toast.makeText(CallbackActivity.this,
                        "통신 오류가 발생했습니다: " + values[0].getMessage(), Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

    private void startMainActivity() {
        final Intent intent = new Intent(CallbackActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
