package io.realm.hanson2.twitter.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import io.realm.hanson2.twitter.R;
import io.realm.hanson2.twitter.service.UpdateService;
import io.realm.hanson2.twitter.ui.adapter.MainPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        //noinspection ConstantConditions
        pager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        startService(new Intent(this, UpdateService.class));
    }
}