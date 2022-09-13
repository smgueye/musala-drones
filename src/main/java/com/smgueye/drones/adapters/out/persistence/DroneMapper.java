package com.smgueye.drones.adapters.out.persistence;

import com.smgueye.drones.domain.Drone;
import org.springframework.stereotype.Component;

@Component
public class DroneMapper {
  Drone mapToDomainEntity(DroneJpaEntity drone) {
    return Drone.withId(
      drone.getId(),
      drone.getSerialNumber(),
      drone.getModel(),
      drone.getWeight(),
      drone.getBattery(),
      drone.getState()
    );
  }
}
