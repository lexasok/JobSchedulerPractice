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

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Notification text");

        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        if (notificationManager != null) {
            notificationManager.notify(1, notification);
        } else {
            Log.i(getClass().getName(), "Notification manager is null");
        }

        jobFinished(params, false);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
