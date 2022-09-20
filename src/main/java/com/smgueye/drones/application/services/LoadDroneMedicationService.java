package com.smgueye.drones.application.services;

import com.smgueye.drones.application.port.in.EditMedicationCommand;
import com.smgueye.drones.application.port.in.LoadingMedicationUseCase;
import com.smgueye.drones.application.port.out.DroneQueryPort;
import com.smgueye.drones.application.port.out.LoadingMedicationPort;
import com.smgueye.drones.common.UseCase;
import com.smgueye.drones.domain.Drone;
import com.smgueye.drones.domain.Medication;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class LoadDroneMedicationService implements LoadingMedicationUseCase {

  private final LoadingMedicationPort loadingMedicationPort;

  private final DroneQueryPort droneQueryPort;

  @Override
  public Drone getDrone(Long droneId) {
    return droneQueryPort.getDroneById(droneId);
  }

  @Override
  public Medication loadIntoDrone(EditMedicationCommand command) {
    return loadingMedicationPort.loadMedication(command);
  }
}
