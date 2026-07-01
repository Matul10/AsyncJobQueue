package Service;

import DTO.JobData;
import DTO.JobResponse;
import Repo.JobRepo;
import Workers.WorkerStrategy;
import lombok.extern.slf4j.Slf4j;
import model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobService {
    @Autowired
    private JobRepo jobRepo;
    private WorkerStrategy worker = new WorkerStrategy();

    public JobResponse addJob(JobData jobData){
        log.info("Job {} of type {} is recieved " , jobData.getJobName(),jobData.getJobType());
        Job newJob = Job.builder()
                .name(jobData.getJobName())
                .jobType(jobData.getJobType())
                .build();
        try{
            newJob = jobRepo.save(newJob);
            worker.doWork(newJob);
            jobRepo.
            return new JobResponse(
                    true,
                    newJob,
                    "job completed successfully",
                    newJob.getId()
            );
        } catch (Exception e) {
            log.error("Error while doing work : {} ", e.getMessage());
            return new JobResponse(
                    false,
                    newJob,
                    "job failed !",
                    newJob.getId()
            );
        }

    }
}
