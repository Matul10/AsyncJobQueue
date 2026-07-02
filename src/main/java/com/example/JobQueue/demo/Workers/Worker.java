package com.example.JobQueue.demo.Workers;

import com.example.JobQueue.demo.model.Job;

public interface Worker {
    void handleWork(Job job);
}
