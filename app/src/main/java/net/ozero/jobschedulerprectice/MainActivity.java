package net.ozero.jobschedulerprectice;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.IntentSender;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import net.ozero.jobschedulerprectice.services.AlarmJobService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void buttonSetAlarmClicked(View view) {

        setAlarm();
    }

    private void setAlarm() {

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        JobInfo job =
                new JobInfo.Builder(1, new ComponentName(this, AlarmJobService.class))
                        .setMinimumLatency(80*1000)
                        .setOverrideDeadline(85*1000)
                        .setBackoffCriteria(5*1000, JobInfo.BACKOFF_POLICY_LINEAR)
                        .setPersisted(true)
                        .build();

        if (jobScheduler != null) {
            jobScheduler.schedule(job);
            Log.i(getClass().getName(), "job set");
        } else {
            Log.i(getClass().getName(), "scheduler = null!");
        }
    }
}
