package Controller;

import DTO.Job;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/job")
public class JobController {

    @PostMapping("/add")
    public void addJob(@RequestBody Job job){
        log.info("Received job {} ", job);
        try{

        }catch(Exception e){

        }
    }

}
