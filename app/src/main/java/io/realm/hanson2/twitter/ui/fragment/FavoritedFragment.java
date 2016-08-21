package io.realm.hanson2.twitter.ui.fragment;

import io.realm.hanson2.twitter.pattern.presenter.FavoritedPresenterImp;

/**
 * Created by toru on 2016. 8. 20..
 */
public class FavoritedFragment extends TimelineFragment{
    public FavoritedFragment() {
        mainPresenter = new FavoritedPresenterImp(this);
    }
}