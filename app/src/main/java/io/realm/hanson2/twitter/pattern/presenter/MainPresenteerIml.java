package io.realm.hanson2.twitter.pattern.presenter;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.hanson2.twitter.model.Tweet;
import io.realm.hanson2.twitter.pattern.view.MainView;

/**
 * Created by toru on 2016. 8. 20..
 */
public class MainPresenteerIml implements MainPresenter{
    private MainView mainView;
    private Realm realm;

    public MainPresenteerIml(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onGetMyTweet() {
        realm = Realm.getDefaultInstance();
        final RealmResults<Tweet> tweetResult = realm.where(Tweet.class).findAllSorted("createdAt", Sort.DESCENDING);
        mainView.onList(tweetResult);
    }
}