package net.ozero.jobschedulerprectice;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import net.ozero.jobschedulerprectice.services.AlarmJobService;
import net.ozero.jobschedulerprectice.services.IdGenerator;

public class MainActivity extends AppCompatActivity {

    private int mId;
    private Button mButton;

    public static final String EXTRA_ID = "id";
    public static final String EXTRA_TIME = "time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        mButton = findViewById(R.id.button);
        mButton.setText(getButtonTitle());
    }

    private String getButtonTitle() {
        mId = IdGenerator.INSTANCE.getNextId();

        return getResources().getString(R.string.title_button_set_alarm) + "\nID: " + mId;
    }

    public void buttonSetAlarmClicked(View view) {

        setAlarm();
        initViews();
    }

    private void setAlarm() {

        PersistableBundle bundle = getBundle();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        JobInfo job =
                new JobInfo.Builder(bundle.getInt(EXTRA_ID), new ComponentName(this, AlarmJobService.class))
                        .setMinimumLatency(15*1000)
                        .setOverrideDeadline(20*1000)
                        .setBackoffCriteria(5*1000, JobInfo.BACKOFF_POLICY_LINEAR)
                        .setPersisted(true)
                        .setExtras(getBundle())
                        .build();

        if (jobScheduler != null) {
            jobScheduler.schedule(job);
            Log.i(getClass().getName(), "job set");
        } else {
            Log.i(getClass().getName(), "scheduler = null!");
        }
    }

    private PersistableBundle getBundle() {

        PersistableBundle bundle = new PersistableBundle();

        int id = mId;
        bundle.putInt(EXTRA_ID, id);
        bundle.putLong(EXTRA_TIME, System.currentTimeMillis());

        return bundle;
    }
}
