package com.example.JobQueue.demo.Workers;

import com.example.JobQueue.demo.Factory.WorkerFactory;
import com.example.JobQueue.demo.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkerStrategy {
    @Autowired
    private WorkerFactory workerFactory;
    private Worker currentWorker = null;

    public WorkerStrategy(){
//        currentWorker = null;
    }
    public void doWork(Job job){
        currentWorker = workerFactory.getWorker(job.getJobType());
        currentWorker.handleWork(job);
    }
}
