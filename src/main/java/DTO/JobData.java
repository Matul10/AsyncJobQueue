package DTO;

import Enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JobData {
    private String jobName;
    private JobType jobType;
}
