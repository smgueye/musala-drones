package com.smgueye.drones.application.port.in;

import com.smgueye.drones.domain.Drone;
import com.smgueye.drones.domain.Medication;

public interface LoadingMedicationUseCase {

  Drone getDrone(Long droneId);

  Medication loadIntoDrone(EditMedicationCommand command);
}
