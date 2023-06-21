package com.ntico.config;

import com.ntico.controller.BornesController;


import com.ntico.service.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@EnableAsync
public class ScheduledFixedRateExample {
    @Autowired
    private BornesController BControl;


    @Async
    @Scheduled(fixedRate = 30000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException, IOException {
        System.out.println(
                "Fixed rate task async - " + System.currentTimeMillis() / 1000);
        Thread.sleep(2000);


        ResponseEntity<String> response = BControl.getBorne_ALL();
        String infoB = response.getBody();

        if (!infoB.equals("InfoB")) {
            // Les bornes sont utilisées
            System.out.println("Les bornes sont utilisées : " + infoB);
        } else {
            // Les bornes ne sont pas utilisées
            System.out.println("Les bornes ne sont pas utilisées.");
        }

        ResponseEntity<List<Log>> response8 = BControl.updateAllBornesB();
        List<Log> allBornes8 = response8.getBody();

        if (allBornes8 != null && !allBornes8.isEmpty()) {
            // Les bornes sont utilisées
            System.out.println("Les bornes sont utilisées : " + allBornes8);
        } else {
            // Les bornes ne sont pas utilisées
            System.out.println("Les bornes ne sont pas utilisées.");
        }



    }
}


