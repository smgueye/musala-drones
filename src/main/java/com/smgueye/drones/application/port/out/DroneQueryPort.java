package com.smgueye.drones.application.port.out;

import com.smgueye.drones.domain.Drone;
import com.smgueye.drones.domain.Medication;

import java.util.List;

public interface DroneQueryPort {

  Long getNumberOfDrones();

  List<Drone> getAllDrones();

  Drone getDroneById(Long droneId);

  List<Medication> getAllMedications(Long droneId);

  List<Drone> getAvailableDrones();

  Integer checkBatteryLevel(Long droneId);
}
