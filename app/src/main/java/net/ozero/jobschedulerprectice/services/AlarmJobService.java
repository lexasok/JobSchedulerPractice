package net.ozero.jobschedulerprectice.services;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import net.ozero.jobschedulerprectice.R;

public class AlarmJobService extends android.app.job.JobService {
    @Override
    public boolean onStartJob(JobParameters params) {

        Log.i(getClass().getName(), "onStartJob");

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Notification text")
                        .setVibrate(new long[] {500, 500, 500});

        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        if (notificationManager != null) {
            notificationManager.notify(1, notification);
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
