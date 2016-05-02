package com.example.khome.todolist;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author Nilanchala
 *         <p/>
 *         Broadcast reciever, starts when the device gets starts.
 *         Start your repeating alarm here.
 */
public class DeviceBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            AlarmReceiver.startAlarm(context);
            //Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();
            System.out.println("got device boot");
        }
    }
}