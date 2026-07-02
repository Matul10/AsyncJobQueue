package com.example.JobQueue.demo.Service;

import com.example.JobQueue.demo.DTO.JobData;
import com.example.JobQueue.demo.DTO.JobResponse;
import com.example.JobQueue.demo.Repo.JobRepo;
import com.example.JobQueue.demo.Workers.WorkerStrategy;
import lombok.extern.slf4j.Slf4j;
import com.example.JobQueue.demo.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobService {
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private WorkerStrategy worker;

    public JobResponse addJob(JobData jobData){
        log.info("Job {} of type {} is recieved " , jobData.getJobName(),jobData.getJobType());
        Job newJob = Job.builder()
                .name(jobData.getJobName())
                .jobType(jobData.getJobType())
                .build();

        newJob = jobRepo.save(newJob);
        worker.doWork(newJob);
        return new JobResponse(
                true,
                newJob,
                "job completed successfully",
                newJob.getId()
        );
    }
}
