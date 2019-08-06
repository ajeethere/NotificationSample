package com.example.notificationsapmle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
                Long time=new GregorianCalendar().getTimeInMillis()+5*1000;
                setAlarm(time);
            }
        });
    }
    public void setAlarm(Long alarm){
        Intent i=new Intent(this,Receiver.class);
        AlarmManager manager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP,alarm, PendingIntent.getBroadcast(this,1,i,PendingIntent.FLAG_UPDATE_CURRENT));
    }
}
