package com.smgueye.drones.adapters.out.persistence;

import com.smgueye.drones.application.port.in.EditMedicationCommand;
import com.smgueye.drones.domain.Drone;
import com.smgueye.drones.domain.Medication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DroneMapper {

  private static final Logger logger = LoggerFactory.getLogger(EditMedicationCommand.class);

  Drone mapToDomainEntity(DroneJpaEntity drone) {
    ArrayList<Medication> medicationSet = new ArrayList<>();

    if (drone.getMedications() != null) {
      for (MedicationJpaEntity medication : drone.getMedications()) {
        medicationSet.add(Medication.withId(
          medication.getId(),
          medication.getName(),
          medication.getWeight(),
          medication.getCode(),
          medication.getDrone().getId()));
      }
    }

    return Drone.withId(
      drone.getId(),
      drone.getSerialNumber(),
      drone.getModel(),
      drone.getWeight(),
      drone.getBattery(),
      drone.getState(),
      medicationSet
    );
  }

  Medication mapMedicationToEntity(MedicationJpaEntity medication) {
    return Medication.withId(
      medication.getId(),
      medication.getName(),
      medication.getWeight(),
      medication.getCode(),
      medication.getDrone().getId());
  }
}
