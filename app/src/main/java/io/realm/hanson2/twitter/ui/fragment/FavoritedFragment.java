package io.realm.hanson2.twitter.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.hanson2.twitter.pattern.presenter.FavoritedPresenterImp;

/**
 * Created by toru on 2016. 8. 20..
 */
public class FavoritedFragment extends TimelineFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainPresenter = new FavoritedPresenterImp(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}