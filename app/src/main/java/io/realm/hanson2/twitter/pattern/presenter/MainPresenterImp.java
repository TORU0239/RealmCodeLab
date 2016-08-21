package io.realm.hanson2.twitter.pattern.presenter;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.hanson2.twitter.model.Tweet;
import io.realm.hanson2.twitter.pattern.view.MainView;

/**
 * Created by toru on 2016. 8. 20..
 */
public class MainPresenterImp implements MainPresenter{
    protected MainView mainView;
    protected Realm realm;

    public MainPresenterImp(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onGetMyTweet() {
        realm = Realm.getDefaultInstance();
        final RealmResults<Tweet> tweetResult = realm.where(Tweet.class).findAllSorted("createdAt", Sort.DESCENDING);
        mainView.onList(tweetResult);
    }

    @Override
    public void onRealmRelease() {
        mainView.onListRelease();
        realm.close();
        realm = null;
    }
}