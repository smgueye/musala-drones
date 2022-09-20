package com.smgueye.drones.application.port.out;

import com.smgueye.drones.application.port.in.EditDroneCommand;
import com.smgueye.drones.domain.Drone;

public interface DroneRegistrationPort {

  Drone register(EditDroneCommand command);

}
