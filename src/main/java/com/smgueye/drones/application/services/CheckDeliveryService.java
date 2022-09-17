package com.smgueye.drones.application.services;

import com.smgueye.drones.application.port.in.CheckDroneMedicationsUseCase;
import com.smgueye.drones.application.port.out.DroneQueryPort;
import com.smgueye.drones.common.UseCase;
import com.smgueye.drones.domain.Medication;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CheckDeliveryService implements CheckDroneMedicationsUseCase {

  private final DroneQueryPort droneQueryPort;

  @Override
  public List<Medication> getAllMedications(Long droneId) {
    return droneQueryPort.getAllMedications(droneId);
  }
}
