package com.synergykit.android.request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomas_000 on 10.3.14.
 */
public class Notification {
    private String payload;
    private String sound;
    private String alert;
    private String badge;
    private List<String> userIds;

    public Notification() {
        this.userIds = new ArrayList<String>();
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }
}
