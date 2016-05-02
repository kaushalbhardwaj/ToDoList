package com.example.khome.todolist;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;


import java.util.Calendar;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("in alarm recieve");

        /*NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new Notification(R.drawable.ic_access_time_black_24dp,"Note Notification", System.currentTimeMillis());
        Intent notificationIntent = new Intent(context.getApplicationContext(),MainPage.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0,notificationIntent, 0);


        notification.
        notification.setLatestEventInfo(MainActivity.this, notificationTitle,notificationMessage, pendingIntent);
        notificationManager.notify(9999, notification);*/


        Intent i = new Intent(context.getApplicationContext(), MainPage.class);
        PendingIntent pIntent = PendingIntent.getActivity(context.getApplicationContext(), (int) System.currentTimeMillis(), i, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(context.getApplicationContext())
                .setContentTitle("Note Notification")
                .setContentText("To Do List").setSmallIcon(R.drawable.ic_access_time_black_24dp)
                .setContentIntent(pIntent).build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);
        System.out.println("after notify");




       /* Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //Notification ring
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        System.out.println("reciever");

        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);*/


    }

    public static void startAlarm(Context con) {
        PendingIntent pendingIntent;
        AlarmManager manager = (AlarmManager) con.getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;
        Intent alarmIntent = new Intent(con, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(con, 0, alarmIntent, 0);


    }
}
