package com.example.JobQueue.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.JobQueue.demo.model.Job;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JobResponse {
    private boolean status;
    private Job job;
    private String message;
    private int jobId;
}
