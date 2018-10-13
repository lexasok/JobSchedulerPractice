package net.ozero.jobschedulerprectice.services;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import net.ozero.jobschedulerprectice.MainActivity;
import net.ozero.jobschedulerprectice.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlarmJobService extends android.app.job.JobService {
    @Override
    public boolean onStartJob(JobParameters params) {

        PersistableBundle bundle = params.getExtras();
        Date time = new Date(bundle.getLong(MainActivity.EXTRA_TIME));
        DateFormat dateFormat = new SimpleDateFormat();
        String timeStr = dateFormat.format(time);

        int id = bundle.getInt(MainActivity.EXTRA_ID);

        Log.i(getClass().getName(), "onStartJob");

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(timeStr)
                        .setContentText("№ " + id)
                        .setVibrate(new long[] {500, 500, 500});

        Notification notification = builder.build();

        notification.flags = notification.flags | Notification.FLAG_NO_CLEAR;

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        if (notificationManager != null) {
            notificationManager.notify(id, notification);
        } else {
            Log.i(getClass().getName(), "Notification manager is null");
        }

        jobFinished(params, true);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(getClass().getName(), "onStopJob");
        return true;
    }

}
