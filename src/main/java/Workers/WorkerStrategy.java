package Workers;

import Enums.JobType;
import Factory.WorkerFactory;
import model.Job;

public class WorkerStrategy {
    private Worker currentWorker;
    public WorkerStrategy(){
        currentWorker = null;
    }
    public void doWork(Job job){
        currentWorker = WorkerFactory.getWorker(job.getJobType());
        currentWorker.handleWork(job);
    }
}
