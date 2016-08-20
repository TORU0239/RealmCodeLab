package io.realm.hanson2.twitter.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import twitter4j.Status;

/**
 * Created by toru on 2016. 8. 20..
 */
public class Tweet extends RealmObject{
    private String screenName;
    private String text;
    private String iconUrl;

    @PrimaryKey
    private long id;
    private Date createdAt;

    private boolean favorited;

    // must have empty constructor.
    public Tweet() {}

    public Tweet(Status status){
        setScreenName(status.getUser().getScreenName());
        setText(status.getText());
        setIconUrl(status.getUser().getProfileImageURLHttps());

        setId(status.getId());
        setCreatedAt(status.getCreatedAt());

        setFavorited(status.isFavorited());
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
