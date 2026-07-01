package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import model.Job;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JobResponse {
    private boolean status;
    private Job job;
    private String message;
    private int jobId;
}
