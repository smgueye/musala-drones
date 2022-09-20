package com.smgueye.drones.application.services;

import com.smgueye.drones.application.port.in.DroneRegistrationUseCase;
import com.smgueye.drones.application.port.in.EditDroneCommand;
import com.smgueye.drones.application.port.out.DroneQueryPort;
import com.smgueye.drones.application.port.out.DroneRegistrationPort;
import com.smgueye.drones.common.UseCase;
import com.smgueye.drones.domain.Drone;
import com.smgueye.drones.exceptions.EntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
@UseCase
public class DroneRegistrationService implements DroneRegistrationUseCase {

  @Value("${drones.max-drone-number}")
  private Integer MAX_DRONE_NUMBER;

  private final DroneRegistrationPort droneRegistrationPort;
  private final DroneQueryPort droneQueryPort;

  @Override
  public Drone register(EditDroneCommand command) {
    if (droneNumber() >= MAX_DRONE_NUMBER)
      throw new EntityException("Ceiling is reached, can not add more drones.");
    return droneRegistrationPort.register(command);
  }

  private Long droneNumber() {
    return droneQueryPort.getNumberOfDrones();
  }
}
