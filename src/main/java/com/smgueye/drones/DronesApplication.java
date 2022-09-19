package com.smgueye.drones;

import com.smgueye.drones.application.port.in.CheckDeliveryUseCase;
import com.smgueye.drones.application.port.in.EditMedicationCommand;
import com.smgueye.drones.domain.Drone;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DronesApplication {

	private static final Logger logger = LoggerFactory.getLogger(DronesApplication.class);

	private final CheckDeliveryUseCase checkDeliveryUseCase;

	public static void main(String[] args) {
		SpringApplication.run(DronesApplication.class, args);
	}

	@Scheduled(cron = "${scheduling.cron}")
	void auditDrones() throws InterruptedException {
		List<Drone> drones = checkDeliveryUseCase.getAllDrones();
		logger.info("Drone battery checking [START]");
		for (Drone drone : drones)
			logger.info("id={}, serial_number={}, battery={}", "1", drone.getSerialNumber(), drone.getBattery().toString());
		logger.info("Drone battery checking [END]");
	}
}
