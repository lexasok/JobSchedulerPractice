package net.ozero.jobschedulerprectice.services;


import android.app.job.JobParameters;

public class JobService extends android.app.job.JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
