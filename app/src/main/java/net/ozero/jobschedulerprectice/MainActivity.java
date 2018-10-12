package net.ozero.jobschedulerprectice;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
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

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        JobInfo job =
                new JobInfo.Builder(1, new ComponentName(this, AlarmJobService.class))
                .setMinimumLatency(10*1000)
                .setOverrideDeadline(15*1000)
                .build();

        if (jobScheduler != null) {
            jobScheduler.schedule(job);
        } else {
            Log.i(getClass().getName(), "scheduler = null!");
        }
    }
}
