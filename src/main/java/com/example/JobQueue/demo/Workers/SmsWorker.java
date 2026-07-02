package com.example.JobQueue.demo.Workers;

import com.example.JobQueue.demo.Enums.JobStatus;
import com.example.JobQueue.demo.Repo.JobRepo;
import com.example.JobQueue.demo.model.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsWorker implements Worker{
    @Autowired
    private JobRepo jobRepo;


    @Override
    public void handleWork(Job job) {
        log.info("SMS Worker called for job id: {} ", job.getId());
        try{
            Thread.sleep(2000);
            System.out.println("SMS sent for job id: " + job.getId());
            job.setJobStatus(JobStatus.COMPLETED);
            jobRepo.save(job);
        }catch(Exception e){
            log.error(e.getMessage());
            job.setJobStatus(JobStatus.FAILED);
            jobRepo.save(job);
        }
    }
}
