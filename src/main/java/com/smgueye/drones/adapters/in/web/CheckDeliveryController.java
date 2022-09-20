package com.smgueye.drones.adapters.in.web;

import com.smgueye.drones.application.port.in.CheckDeliveryUseCase;
import com.smgueye.drones.common.WebAdapter;
import com.smgueye.drones.domain.Drone;
import com.smgueye.drones.domain.Medication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CheckDeliveryController {

  private final CheckDeliveryUseCase checkDeliveryUseCase;

  @GetMapping(path = "/api/v1/drones/{droneId}/medications")
  List<Medication> getAllMedicationsByDroneId(@PathVariable Long droneId) {
    return checkDeliveryUseCase.getAllMedications(droneId);
  }

  @GetMapping(path = "/api/v1/drones/available")
  List<Drone> getAllAvailableDrones() {
    return checkDeliveryUseCase.getAllAvailableDrones();
  }

  @GetMapping(path = "/api/v1/drones/{droneId}/battery-level")
  ResponseEntity<Object> getBatteryLevelByDroneId(@PathVariable Long droneId) {
    Integer batteryLevel = checkDeliveryUseCase.getBatteryLevel(droneId);
    Map<String, String> responseData = new HashMap<>();
    responseData.put("battery", String.format("%d%%", batteryLevel));
    return new ResponseEntity<>(responseData, HttpStatus.OK);
  }
}
