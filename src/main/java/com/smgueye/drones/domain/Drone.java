package com.smgueye.drones.domain;

import com.smgueye.drones.adapters.out.persistence.DroneModel;
import com.smgueye.drones.adapters.out.persistence.DroneState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Drone {
  /**
   *
   */
  @Getter private final Long id;

  @Getter private final String serialNumber;

  @Getter private final DroneModel model;

  @Getter private final Integer weight;

  @Getter private final Integer battery;

  @Getter private final DroneState state;

  public static Drone withId(
    Long id,
    String serialNumber,
    DroneModel model,
    Integer weight,
    Integer battery,
    DroneState state
  ) {
    return new Drone(id, serialNumber, model, weight, battery, state);
  }
}
