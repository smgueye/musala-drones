package com.smgueye.drones.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DroneRepository extends JpaRepository<DroneJpaEntity, Long> {
  @Query(value = "SELECT drone FROM DroneJpaEntity drone WHERE drone.state = 1 AND drone.battery >= 25")
  List<DroneJpaEntity> findAllAvailableDrones();
}
