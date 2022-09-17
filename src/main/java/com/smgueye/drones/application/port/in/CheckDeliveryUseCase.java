package com.smgueye.drones.application.port.in;

import com.smgueye.drones.domain.Drone;
import com.smgueye.drones.domain.Medication;

import java.util.List;

public interface CheckDeliveryUseCase {
  List<Medication> getAllMedications(Long droneId);

  List<Drone> getAllAvailableDrones();
}
