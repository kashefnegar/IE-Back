package datalayer;
import datalayer.dbConnection.impl.SQLiteBasicDBConnectionPool;
import models.Projects;

import java.sql.Connection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.*;

public class getProjectControler {
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);
    public void beepForAnHour() {
        final Runnable beeper = new Runnable() {
            public void run(){
//                Projects myProjects;
                Projects myProjects = Projects.getInstance();
                myProjects.get_project_url("http://142.93.134.194:8000/joboonja/project");
            }
        };
        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 1 ,8999, SECONDS);
        scheduler.schedule(new Runnable() {
            public void run() { beeperHandle.cancel(true); }
        }, 60 * 60, SECONDS);
    }
}
