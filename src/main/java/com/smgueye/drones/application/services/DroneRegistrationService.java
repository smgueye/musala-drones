package com.smgueye.drones.application.services;

import com.smgueye.drones.application.port.in.DroneRegistrationUseCase;
import com.smgueye.drones.application.port.in.EditDroneCommand;
import com.smgueye.drones.application.port.out.DroneRegistrationPort;
import com.smgueye.drones.common.UseCase;
import com.smgueye.drones.domain.Drone;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class DroneRegistrationService implements DroneRegistrationUseCase {

  private final DroneRegistrationPort droneRegistrationPort;

  @Override
  public Drone register(EditDroneCommand command) {
    return droneRegistrationPort.register(command);
  }
}
