package com.smgueye.drones.application.services;

import com.smgueye.drones.application.port.in.CheckDeliveryUseCase;
import com.smgueye.drones.application.port.out.DroneQueryPort;
import com.smgueye.drones.common.UseCase;
import com.smgueye.drones.domain.Drone;
import com.smgueye.drones.domain.Medication;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CheckDeliveryService implements CheckDeliveryUseCase {

  private final DroneQueryPort droneQueryPort;

  @Override
  public List<Medication> getAllMedications(Long droneId) {
    return droneQueryPort.getAllMedications(droneId);
  }

  @Override
  public List<Drone> getAllAvailableDrones() {
    return droneQueryPort.getAvailableDrones();
  }

  @Override
  public List<Drone> getAllDrones() {
    return droneQueryPort.getAllDrones();
  }

  @Override
  public Integer getBatteryLevel(Long droneId) {
    return droneQueryPort
      .getDroneById(droneId)
      .getBattery();
  }
}
