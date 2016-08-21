package io.realm.hanson2.twitter.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.hanson2.twitter.R;
import io.realm.hanson2.twitter.model.Tweet;
import io.realm.hanson2.twitter.pattern.presenter.MainPresenterImp;
import io.realm.hanson2.twitter.pattern.presenter.MainPresenter;
import io.realm.hanson2.twitter.pattern.view.MainView;

public class TimelineFragment extends ListFragment implements MainView{
    protected MainPresenter mainPresenter;

    public TimelineFragment() {
        mainPresenter = new MainPresenterImp(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ListAdapter adapter = new ArrayAdapter<>(getContext(),
                R.layout.list_item_tweet,
                R.id.text,
                Arrays.asList("Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
                ));

        setListAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.w("Timeline", "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        mainPresenter.onGetMyTweet();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainPresenter.onRealmRelease();
    }

    @Override
    public void onList(final RealmResults<Tweet> tweet) {
        RealmBaseAdapter<Tweet> tweetAdapter = new RealmBaseAdapter<Tweet>(getContext(), tweet) {
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view == null){
                    view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_tweet, viewGroup, false);
                }

                TextView screenName = (TextView)view.findViewById(R.id.screen_name);
                TextView tweetText = (TextView)view.findViewById(R.id.text);

                screenName.setText(tweet.get(i).getScreenName());
                tweetText.setText(tweet.get(i).getText());

                return view;
            }
        };
        setListAdapter(tweetAdapter);
    }

    @Override
    public void onListRelease() {
        ((RealmBaseAdapter<?>) getListAdapter()).updateData(null);
    }
}