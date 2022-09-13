package com.smgueye.drones.application.port.in;

import com.smgueye.drones.domain.Drone;

public interface DroneRegistrationUseCase {
  Drone register(EditDroneCommand command);
}
