package com.smgueye.drones;

import com.smgueye.drones.application.port.in.EditMedicationCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class DronesApplication {

	private static final Logger logger = LoggerFactory.getLogger(DronesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DronesApplication.class, args);
	}

	@Scheduled(cron = "${scheduling.cron}")
	void auditDrones() throws InterruptedException {
		logger.info("Drone - battery - state.");
	}
}
