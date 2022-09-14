package com.smgueye.drones.adapters.in.web;

import com.smgueye.drones.application.port.in.DroneRegistrationUseCase;
import com.smgueye.drones.application.port.in.EditDroneCommand;
import com.smgueye.drones.common.WebAdapter;
import com.smgueye.drones.domain.Drone;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class DroneRegistrationController {

  private final DroneRegistrationUseCase registrationUseCase;

  @PostMapping(path = "/api/v1/drones/registration")
  Drone registration(@RequestBody EditDroneRequest request) {
    EditDroneCommand command = new EditDroneCommand(null,
      request.getSerialNumber(),
      request.getModel(),
      request.getWeight(),
      request.getBattery(),
      request.getState());
    return registrationUseCase.register(command);
  }
}
