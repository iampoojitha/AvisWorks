package spring.TextHash.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Sched {

    @Scheduled(cron = "*/2 * * * *")
    public void task(){
            log.info("-------------------- Doing Scheduled task ----------------");
    }
}
