package com.example.JobQueue.demo.Factory;

import com.example.JobQueue.demo.Enums.JobType;
import com.example.JobQueue.demo.Workers.EmailWorker;
import com.example.JobQueue.demo.Workers.SmsWorker;
import com.example.JobQueue.demo.Workers.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class WorkerFactory {
    @Autowired
    private ApplicationContext applicationContext;

    public  Worker getWorker(JobType jobType){
        return switch (jobType){
            case EMAIL ->  applicationContext.getBean(EmailWorker.class);
            case SMS -> applicationContext.getBean(SmsWorker.class);
            default -> throw new IllegalArgumentException("Invalid job type");
        };
    }
}
