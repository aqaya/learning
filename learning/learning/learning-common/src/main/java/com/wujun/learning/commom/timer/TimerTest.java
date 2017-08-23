package com.wujun.learning.commom.timer;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Collection;
import java.util.Date;

public class TimerTest implements Job, JobListener {

    public static void main(String[] args) throws SchedulerException {
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTest("task1"), 1000L, 1000L);
        //timer.schedule(new TimerTest("task2"), 3000L,2000L);

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail jobDetail = new JobDetailImpl("myJob", "myJobGroup", TimerTest.class);
        jobDetail.getJobDataMap().put("type", "full");
        jobDetail.getJobDataMap().put("a", "123");

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "myTriggerGroup")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(5).repeatForever())
                .build();
        scheduler.getListenerManager().addJobListener(new TimerTest());
        scheduler.start();

        scheduler.scheduleJob(jobDetail, trigger);

        System.out.println("TimerTest.main over");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Collection<Object> values = context.getJobDetail().getJobDataMap().values();
        System.out.println(values);
        System.out.println(new Date());
    }

    public static final String LISTENER_NAME = "dummyJobListenerName";

    @Override
    public String getName() {
        return LISTENER_NAME; //must return a name
    }

    // Run this if job is about to be executed.
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {

        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("jobToBeExecuted");
        System.out.println("Job : " + jobName + " is going to start...");

    }

    // No idea when will run this?
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("jobExecutionVetoed");
    }

    //Run this after job has been executed
    @Override
    public void jobWasExecuted(JobExecutionContext context,
                               JobExecutionException jobException) {
        System.out.println("jobWasExecuted");

        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("Job : " + jobName + " is finished...");

        if (jobException != null &&
                !jobException.getMessage().equals("")) {
            System.out.println("Exception thrown by: " + jobName
                    + " Exception: " + jobException.getMessage());
        }

    }

    //    @Override
//    public void run() {
//        System.out.println(new Date(this.scheduledExecutionTime()));
//        try {
//            Thread.sleep(5000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(taskName + " __ " + new Date());
//    }
}
