package io.realm.hanson2.twitter.pattern.view;

import io.realm.RealmResults;
import io.realm.hanson2.twitter.model.Tweet;

/**
 * Created by toru on 2016. 8. 20..
 */
public interface MainView {
    // 뷰만 핸들링하는 부분
    void onList(RealmResults<Tweet> tweet);

    // realm 객체 release될 때 view 를 핸들링하는 부분
    void onListRelease();
}