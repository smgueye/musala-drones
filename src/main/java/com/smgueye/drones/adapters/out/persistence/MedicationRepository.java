package com.smgueye.drones.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicationRepository extends JpaRepository<MedicationJpaEntity, Long> {

  @Query("SELECT medication FROM MedicationJpaEntity medication WHERE medication.drone.id = :droneId")
  List<MedicationJpaEntity> findAllByDroneId(@Param("droneId") Long droneId);
}
