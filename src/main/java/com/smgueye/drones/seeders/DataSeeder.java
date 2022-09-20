package com.smgueye.drones.seeders;

import com.smgueye.drones.adapters.out.persistence.*;
import com.smgueye.drones.application.port.in.EditMedicationCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(DataSeeder.class);

  @Autowired DroneRepository droneRepository;
  @Autowired MedicationRepository medicationRepository;

  @Override
  public void run(String... args) throws Exception {
    exec();
  }

  private void exec() {
    List<MedicationJpaEntity> emptyList = new ArrayList<>();
    DroneJpaEntity lightDrone = new DroneJpaEntity(
      1L, "01111111000007", DroneModel.LightWeight, 150, 78, DroneState.IDLE, emptyList);
    droneRepository.save(lightDrone);
    DroneJpaEntity middleWeightDrone = new DroneJpaEntity(
        2L,"01111111000008", DroneModel.MiddleWeight, 300, 23, DroneState.DELIVERING, emptyList);
    droneRepository.save(middleWeightDrone);
    DroneJpaEntity heavyDrone = new DroneJpaEntity(
        3L, "01111111000009", DroneModel.LightWeight, 500, 100, DroneState.LOADING, emptyList);
    droneRepository.save(heavyDrone);

    medicationRepository.save(
      new MedicationJpaEntity(1L, "VALIUM", 10, "0002-0800", null, heavyDrone));
    medicationRepository.save(
      new MedicationJpaEntity(2L, "EFFERALGAN 1000mg", 9, "3012-0800", null, heavyDrone));
    medicationRepository.save(
      new MedicationJpaEntity(3L, "DOLIPRANE 500mg", 15, "0002-1855", null, middleWeightDrone));
    medicationRepository.save(
      new MedicationJpaEntity(4L, "ASPEGIC", 10, "7302-0848", null, middleWeightDrone));
  }
}
