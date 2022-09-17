package com.smgueye.drones.adapters.in.web;

import com.smgueye.drones.application.port.in.CheckDroneMedicationsUseCase;
import com.smgueye.drones.common.WebAdapter;
import com.smgueye.drones.domain.Drone;
import com.smgueye.drones.domain.Medication;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CheckDeliveryController {

  private final CheckDroneMedicationsUseCase checkDroneMedicationsUseCase;

  @GetMapping(path = "/api/v1/drones/{droneId}/medications")
  List<Medication> getAllMedicationsByDroneId(@PathVariable Long droneId) {
    return checkDroneMedicationsUseCase.getAllMedications(droneId);
  }
}
