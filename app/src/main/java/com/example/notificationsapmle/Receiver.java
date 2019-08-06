package com.example.notificationsapmle;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Receiver extends BroadcastReceiver {
    String CHANNEL_ID="mynotification";
    @Override
    public void onReceive(Context context, Intent intent) {
        showNotification(context,"Times Up","Time has come");
    }
    public void showNotification(Context context,String title, String msg){
        PendingIntent intent=PendingIntent.getActivity(context,1,new Intent(context,MainActivity.class),0);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,"my notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder notification=new NotificationCompat.Builder(context,CHANNEL_ID)
                .setContentTitle(title).setSmallIcon(R.drawable.notifcation_icon)
                .setContentIntent(intent)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .setAutoCancel(true).setContentText(msg);

        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(context);
        managerCompat.notify(1,notification.build());
    }}
