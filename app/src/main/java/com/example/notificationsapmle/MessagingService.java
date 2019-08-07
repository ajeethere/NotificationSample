package com.example.notificationsapmle;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {
    String CHANNEL_ID = "firebasenotification";
    Context context=MessagingService.this;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        showNotification(context,"Firebase Notification",remoteMessage.getNotification().getBody());
    }
    public void showNotification(Context context, String title, String msg) {
        RemoteViews notificationLayout = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
        RemoteViews notificationLayoutExpanded = new RemoteViews(context.getPackageName(), R.layout.notification_layout);

        PendingIntent intent = PendingIntent.getActivity(context, 1, new Intent(context, MainActivity.class), 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "my notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title).setSmallIcon(R.drawable.notifcation_icon)
                .setContentIntent(intent)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpanded)
                .setAutoCancel(true).setContentText(msg);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(1, notification.build());
    }
}
