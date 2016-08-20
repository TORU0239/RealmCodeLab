package io.realm.hanson2.twitter.app;

import android.app.Application;
import android.support.annotation.NonNull;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.hanson2.twitter.auth.TwitterAuthUtil;
import twitter4j.TwitterFactory;

/**
 * Created by toru on 2016. 8. 20..
 */
public class TwitterTimelineApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthUtil.init(this);
        TwitterFactory.getSingleton().setOAuthConsumer("sb96gLIvrBFVbTb0tBZf8hax3", "yWVehuoUMwk5XaqKwQR8Lm0EpEq27UDKLeHdpYYBzSRhR1GDrN");

        Realm.setDefaultConfiguration(buildRealmConfiguration());
    }

    @NonNull
    private RealmConfiguration buildRealmConfiguration(){
        return new RealmConfiguration.Builder(this)
                .schemaVersion(1L)
                .migration(new RealmMigration() {
                    @Override
                    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
                        if (oldVersion == 0L) {
                            final RealmObjectSchema tweetSchema = realm.getSchema().get("Tweet");
                            tweetSchema.addField("favorited", boolean.class);
                            //noinspection UnusedAssignment
                            oldVersion++;
                        }
                    }
                })
                .build();
    }
}