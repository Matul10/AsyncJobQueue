package Controller;

import DTO.JobData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/job")
public class JobController {

    @PostMapping("/add")
    public void addJob(@RequestBody JobData jobData){
        log.info("Received job {} ", jobData);
        try{

        }catch(Exception e){

        }
    }

}
