package com.smgueye.drones.application.port.out;

import com.smgueye.drones.application.port.in.EditMedicationCommand;
import com.smgueye.drones.domain.Medication;

public interface LoadingMedicationPort {
  Medication loadMedication(EditMedicationCommand command);
}
