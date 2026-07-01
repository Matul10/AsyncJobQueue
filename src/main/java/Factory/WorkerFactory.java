package Factory;

import Enums.JobType;
import Workers.EmailWorker;
import Workers.Worker;

public class WorkerFactory {
    public static Worker getWorker(JobType jobType){
        return switch (jobType){
            case EMAIL -> new EmailWorker();
            default -> throw new IllegalArgumentException("Invalid job type");
        };
    }
}
