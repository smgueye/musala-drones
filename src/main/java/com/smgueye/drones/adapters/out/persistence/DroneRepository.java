package com.smgueye.drones.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DroneRepository extends JpaRepository<DroneJpaEntity, Long> {
  @Query(value = "SELECT * FROM drones WHERE state IN (0, 1) AND id IN (" +
                    "SELECT m.drone_id FROM medications m " +
                      "LEFT JOIN drones d on d.id = m.drone_id " +
                      "GROUP BY m.drone_id, d.weight " +
                      "HAVING SUM(m.weight) < d.weight)", nativeQuery = true)
  List<DroneJpaEntity> findAllAvailableDrones();
}
