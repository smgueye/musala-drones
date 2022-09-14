package com.smgueye.drones.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Medication {
  @Getter private final Long id;

  @Getter private final String name;

  @Getter private final Integer weight;

  @Getter private final String code;

  @Getter private final Long droneId;

  public static Medication withId(
    Long id, String name, Integer weight, String code, Long droneId
  ) {
    return new Medication(id, name, weight, code, droneId);
  }
}
