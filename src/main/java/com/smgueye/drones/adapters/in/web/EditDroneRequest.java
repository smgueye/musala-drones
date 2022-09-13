package com.smgueye.drones.adapters.in.web;


import com.smgueye.drones.adapters.out.persistence.DroneModel;
import com.smgueye.drones.adapters.out.persistence.DroneState;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class EditDroneRequest {
  private final String serialNumber;
  private final DroneModel model;
  private final Integer weight;
  private final Integer battery;
  private final DroneState state;
}
