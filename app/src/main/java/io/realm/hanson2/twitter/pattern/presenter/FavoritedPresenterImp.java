package io.realm.hanson2.twitter.pattern.presenter;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.hanson2.twitter.model.Tweet;
import io.realm.hanson2.twitter.pattern.view.MainView;

/**
 * Created by toru on 2016. 8. 20..
 */
public class FavoritedPresenterImp extends MainPresenterImp{

    public FavoritedPresenterImp(MainView mainView) {
        super(mainView);
    }

    @Override
    public void onGetMyTweet() {
        realm = Realm.getDefaultInstance();
        final RealmResults<Tweet> tweetResult = realm.where(Tweet.class).findAllSorted("favorited", Sort.DESCENDING);
        Log.w("Favorited", "onGetMyTweet: " + tweetResult.size());
        mainView.onList(tweetResult);
    }
}