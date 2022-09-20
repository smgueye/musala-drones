package com.smgueye.drones.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Medication {
  @Getter
  private final Long id;

  @Getter
  private final String name;

  @Getter
  private final Integer weight;

  @Getter
  private final String code;

<<<<<<< HEAD
  @Getter
  private final String imageUrl;

  @Getter private final String imageUrl;

  @JsonIgnore
  @Getter
  private final Long droneId;
=======
  @JsonIgnore
  @Getter private final Long droneId;
>>>>>>> 5757f89 (feat(delivery-check): Delivery\n)

  public static Medication withId(
<<<<<<< HEAD
      Long id, String name, Integer weight, String code, String imageUrl, Long droneId) {
=======
    Long id, String name, Integer weight, String code, String imageUrl, Long droneId
  ) {
>>>>>>> 219364a (fix(medication-load): Add medication\n)
    return new Medication(id, name, weight, code, imageUrl, droneId);
  }
}
