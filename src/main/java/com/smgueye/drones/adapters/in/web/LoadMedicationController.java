package com.smgueye.drones.adapters.in.web;

import com.smgueye.drones.application.port.in.EditMedicationCommand;
import com.smgueye.drones.application.port.in.LoadingMedicationUseCase;
import com.smgueye.drones.common.WebAdapter;
import com.smgueye.drones.domain.Drone;
import com.smgueye.drones.domain.Medication;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class LoadMedicationController {

  private final LoadingMedicationUseCase loadMedicationUseCase;

  @PostMapping(path = "/api/v1/medications")
  Medication loadMedication(@RequestBody LoadMedicationRequest request) throws Exception {
    Drone drone = loadMedicationUseCase.getDrone(request.getDroneId());
    EditMedicationCommand command = new EditMedicationCommand(null,
      request.getName(),
      request.getWeight(),
      request.getCode(),
      request.getImageUrl(),
      drone);
    return loadMedicationUseCase.loadIntoDrone(command);
  }
}
