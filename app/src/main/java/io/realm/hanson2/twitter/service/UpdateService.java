package io.realm.hanson2.twitter.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.hanson2.twitter.model.Tweet;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Created by toru on 2016. 8. 20..
 */
public class UpdateService extends IntentService {

    public UpdateService() {
        super(UpdateService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        loadTimeline();
    }

    private void loadTimeline() {
        final Twitter twitter = TwitterFactory.getSingleton();
        final ResponseList<Status> homeTimeline;

        try {
            homeTimeline = twitter.getHomeTimeline();
        }
        catch (TwitterException e) {
            Toast.makeText(this, "통신 오류가 발생하였습니다: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("RealmTwitter", "통신 오류가 발생하였습니다.", e);
            return;
        }

        final Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    for (Status status : homeTimeline) {
                        final Tweet tweet = new Tweet(status);
                        realm.copyToRealmOrUpdate(tweet);
                    }
                }
            });
        }
        finally {
            realm.close();
        }
    }
}