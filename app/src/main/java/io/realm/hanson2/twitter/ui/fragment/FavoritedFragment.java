package io.realm.hanson2.twitter.ui.fragment;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.hanson2.twitter.model.Tweet;

/**
 * Created by toru on 2016. 8. 20..
 */
public class FavoritedFragment extends TimelineFragment {

    @NonNull
    @Override
    protected RealmResults<Tweet> buildTweetList(Realm realmInstance) {
        return realmInstance.where(Tweet.class)
                            .equalTo("favorited", true)
                            .findAllSorted("createdAt", Sort.DESCENDING);
    }
}
