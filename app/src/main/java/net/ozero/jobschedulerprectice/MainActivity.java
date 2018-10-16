package net.ozero.jobschedulerprectice;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import net.ozero.jobschedulerprectice.services.AlarmJobService;
import net.ozero.jobschedulerprectice.services.AlarmService;
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

        Intent intent = new Intent(this, AlarmService.class);
        intent.putExtra(EXTRA_ID, mId);
        intent.putExtra(EXTRA_TIME, System.currentTimeMillis());

        startService(intent);
    }

    private Bundle getBundle() {

        Bundle bundle = new Bundle();

        int id = mId;
        bundle.putInt(EXTRA_ID, id);
        bundle.putLong(EXTRA_TIME, System.currentTimeMillis());

        return bundle;
    }
}
