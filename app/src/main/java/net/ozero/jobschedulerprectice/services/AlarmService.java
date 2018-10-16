package net.ozero.jobschedulerprectice.services;

import android.app.IntentService;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import static net.ozero.jobschedulerprectice.MainActivity.EXTRA_ID;
import static net.ozero.jobschedulerprectice.MainActivity.EXTRA_TIME;

public class AlarmService extends IntentService {

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        setAlarm(intent);
    }

    private void setAlarm(Intent intent) {

        PersistableBundle bundle = getBundle(intent);

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        JobInfo job =
                new JobInfo.Builder(bundle.getInt(EXTRA_ID), new ComponentName(this, AlarmJobService.class))
                        .setMinimumLatency(15*1000)
                        .setOverrideDeadline(20*1000)
                        .setBackoffCriteria(5*1000, JobInfo.BACKOFF_POLICY_LINEAR)
                        .setPersisted(true)
                        .setExtras(bundle)
                        .build();

        if (jobScheduler != null) {
            jobScheduler.schedule(job);
            Log.i(getClass().getName(), "job set");
        } else {
            Log.i(getClass().getName(), "scheduler = null!");
        }
    }

    private PersistableBundle getBundle(Intent intent) {

        PersistableBundle bundle = new PersistableBundle();

        bundle.putInt(EXTRA_ID, intent.getIntExtra(EXTRA_ID, 1));
        bundle.putString(EXTRA_TIME, intent.getStringExtra(EXTRA_TIME));

        return bundle;
    }
}
