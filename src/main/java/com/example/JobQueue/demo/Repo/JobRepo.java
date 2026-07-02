package com.example.JobQueue.demo.Repo;

import jakarta.transaction.Transactional;
import com.example.JobQueue.demo.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<Job,Integer> {
//    @Modifying
//    @Transactional
//    @Query("UPDATE Job j SET j.jobStatus = 'SUCCESS' WHERE j.id = :jobId")
//    public void updateJobStatusToSuccess(@Param("jobId") int jobId);
//
//    @Modifying
//    @Transactional
//    @Query("UPDATE Job j SET j.jobStatus = 'FAILED' WHERE j.id = :jobId")
//    public void updateJobStatusToFailed(@Param("jobId") int jobId);
}
