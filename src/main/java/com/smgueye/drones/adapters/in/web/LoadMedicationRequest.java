package com.smgueye.drones.adapters.in.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class LoadMedicationRequest {
  private final String name;
  private final Integer weight;
  private final String code;
  private final String imageUrl;
  private final Long droneId;
}
