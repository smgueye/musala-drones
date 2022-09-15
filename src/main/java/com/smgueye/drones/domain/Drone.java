package com.smgueye.drones.domain;

import com.smgueye.drones.adapters.out.persistence.DroneModel;
import com.smgueye.drones.adapters.out.persistence.DroneState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Drone {
  @Getter private final Long id;

  @Getter private final String serialNumber;

  @Getter private final DroneModel model;

  @Getter private final Integer weight;

  @Getter private final Integer battery;

  @Getter private final DroneState state;

  @Getter private final ArrayList<Medication> medicationList;

  public static Drone withId(
    Long id,
    String serialNumber,
    DroneModel model,
    Integer weight,
    Integer battery,
    DroneState state,
    ArrayList<Medication> medicationList
  ) {
    return new Drone(id, serialNumber, model, weight, battery, state, medicationList);
  }

  public boolean isOverWeighted(Integer newWeight) {
    int totalMedicationWeight = newWeight;
    for (Medication medication : medicationList) {
      totalMedicationWeight += medication.getWeight();
    }
    return totalMedicationWeight > weight;
  }
}
