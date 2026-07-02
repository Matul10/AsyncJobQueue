package com.example.JobQueue.demo.Controller;

import com.example.JobQueue.demo.DTO.ApiResponse;
import com.example.JobQueue.demo.DTO.JobData;
import com.example.JobQueue.demo.DTO.JobResponse;
import com.example.JobQueue.demo.Service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse<JobResponse>> addJob(@RequestBody JobData jobData){
        log.info("Received job {} ", jobData);
        try{
            JobResponse response = jobService.addJob(jobData);
            ApiResponse<JobResponse> apiResp = ApiResponse.success(response,"Job Completed Successfully");
            return new ResponseEntity<>(apiResp, HttpStatus.OK );
        }catch(Exception e){
            ApiResponse<JobResponse> apiResp = ApiResponse.error(null,"Error occured : " + e.getMessage());
            return new ResponseEntity<>(apiResp,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
