package com.smgueye.drones.application.port.in;

import com.smgueye.drones.domain.Medication;

import java.util.List;

public interface CheckDroneMedicationsUseCase {
  List<Medication> getAllMedications(Long droneId);
}
