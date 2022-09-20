package com.smgueye.drones.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DroneRepository extends JpaRepository<DroneJpaEntity, Long> {
<<<<<<< HEAD
<<<<<<< HEAD
  @Query(value = "SELECT drone FROM DroneJpaEntity drone WHERE drone.state = 1 AND drone.battery >= 25")
=======
  @Query(value = "SELECT drone FROM DroneJpaEntity drone WHERE drone.state = 1")
>>>>>>> 5757f89 (feat(delivery-check): Delivery\n)
=======
  @Query(value = "SELECT drone FROM DroneJpaEntity drone WHERE drone.state = 1 AND drone.battery >= 25")
>>>>>>> 1639508 (fix(drone-registration): Registration)
  List<DroneJpaEntity> findAllAvailableDrones();
}
