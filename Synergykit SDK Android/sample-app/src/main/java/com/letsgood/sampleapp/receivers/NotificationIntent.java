package com.letsgood.sampleapp.receivers;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.letsgood.sampleapp.R;

import java.util.Random;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 9. 4. 2015.
 */
public class NotificationIntent extends IntentService {

    /* Constants */
    private static final String INTENT_NAME = "SynergyKit Sample App Notification Intent";

    /* Attribute */
    private GoogleCloudMessaging googleCloudMessaging = null;
    private NotificationManager notificationManager = null;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public NotificationIntent() {
        super(INTENT_NAME);

        googleCloudMessaging = GoogleCloudMessaging.getInstance(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                                                    .setSmallIcon(R.mipmap.ic_launcher)
                                                    .setContentTitle(getString(R.string.app_name))
                                                    .setStyle(new NotificationCompat.BigTextStyle().bigText(extras.getString("alert")))
                                                     .setContentText(extras.getString("alert"))
                                                     .setAutoCancel(true);

        notificationManager.notify(1111,builder.build());

    }
}
