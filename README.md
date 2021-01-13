# Panoply Job system
### By Tamir Mayblat

This is a basic job system that can schedule and execute jobs.

How to schedule a job:
```java
Scheduler scheduler = new Scheduler();

scheduler.schedule(
    () -> System.out.println("panoply job"),           // the runnable to be scheduled
    Schedules.fixedDelaySchedule(FixedDelayIntervalsEnum.ONE_HOUR) // the schedule associated to the runnable
);
```
Done!

Feel free to run all the tests and check the results.

### Other features:
* Jobs should be created using FixedDelayIntervalsEnum predefined periods (every 1,2,6,12 hours) , however, this is not mandatory in order to add more flexibility.
* A Jobs can be scheduled to run once using executeOnce method.
* Job with the same name will not be created twice (this is a variation of the bonus part).
* It's possible to cancel a job that has not been set to run (READY) or is running (see relevant test).
* You can use SchedulerStats in order to get status readings of the system for example:

```java
SchedulerStats stats = scheduler.stats();
System.out.println("jobs count: " + stats.getJobsCount());
System.out.println(stats.getThreadPoolStats());
```   

Thanks!