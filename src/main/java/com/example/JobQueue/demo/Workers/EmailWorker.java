package com.example.JobQueue.demo.Workers;

import com.example.JobQueue.demo.Enums.JobStatus;
import com.example.JobQueue.demo.Repo.JobRepo;
import lombok.extern.slf4j.Slf4j;
import com.example.JobQueue.demo.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailWorker implements Worker{
    @Autowired
    private JobRepo jobRepo;


    public void handleWork(Job job){
        log.info("Email Worker called for job id: {} ", job.getId());
        try{
            Thread.sleep(3000);
            System.out.println("Email sent for job id: " + job.getId());
            job.setJobStatus(JobStatus.COMPLETED);
            jobRepo.save(job);
        }catch(Exception e){
            log.error(e.getMessage());
            job.setJobStatus(JobStatus.FAILED);
            jobRepo.save(job);
        }
    }
}
