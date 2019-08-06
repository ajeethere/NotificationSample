package com.example.notificationsapmle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.show_notification);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();
            }
        });
    }
    public void setAlarm(){
        Long time=new GregorianCalendar().getTimeInMillis()+5*1000;
        Intent i=new Intent(this,Receiver.class);
        AlarmManager manager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this,1,i,PendingIntent.FLAG_UPDATE_CURRENT));
    }
}
