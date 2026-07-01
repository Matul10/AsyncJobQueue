package Workers;

import DTO.JobData;
import lombok.extern.slf4j.Slf4j;
import model.Job;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailWorker implements Worker{
    public void handleWork(Job job){
        log.info("Email Worker called for job id: {} ", job.getId());
        try{
            Thread.sleep(3000);
            System.out.println("Email sent for job id: " + job.getId());
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }
}
