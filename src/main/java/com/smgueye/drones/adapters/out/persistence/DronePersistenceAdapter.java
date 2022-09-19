package com.smgueye.drones.adapters.out.persistence;


import com.smgueye.drones.application.port.in.EditDroneCommand;
import com.smgueye.drones.application.port.in.EditMedicationCommand;
import com.smgueye.drones.application.port.out.DroneQueryPort;
import com.smgueye.drones.application.port.out.DroneRegistrationPort;
import com.smgueye.drones.application.port.out.LoadingMedicationPort;
import com.smgueye.drones.common.PersistenceAdapter;
import com.smgueye.drones.domain.Drone;
import com.smgueye.drones.domain.Medication;
import com.smgueye.drones.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@PersistenceAdapter
public class DronePersistenceAdapter implements
  DroneRegistrationPort,
  DroneQueryPort,
  LoadingMedicationPort {

  private static final Logger logger = LoggerFactory.getLogger(DronePersistenceAdapter.class);

  private final DroneRepository droneRepository;
  private final MedicationRepository medicationRepository;
  private final DroneMapper mapper;

  public DroneJpaEntity getDroneEntityById(Long droneId) {
    Optional<DroneJpaEntity> optionalDrone = droneRepository.findById(droneId);
    if (optionalDrone.isEmpty())
      throw new EntityNotFoundException(Drone.class, "id", droneId.toString());
     return optionalDrone.get();
  }

  @Override
  public Drone register(EditDroneCommand command) {
    DroneJpaEntity entity = droneRepository.save(
      new DroneJpaEntity(
        null,
        command.getSerialNumber(),
        command.getModel(),
        command.getWeight(),
        command.getBattery(),
        command.getState(),
      null));
    return mapper.mapToDomainEntity(entity);
  }

  @Override
  public Medication loadMedication(EditMedicationCommand command) {
    DroneJpaEntity drone = getDroneEntityById(command.getDrone().getId());
    MedicationJpaEntity entity = medicationRepository.save(new MedicationJpaEntity(
      null,
      command.getName(),
      command.getWeight(),
      command.getCode(),
      command.getImageUrl(),
      drone));
    return mapper.mapMedicationToEntity(entity);
  }

  @Override
  public Long getNumberOfDrones() {
    return droneRepository.count();
  }

  @Override
  public List<Drone> getAllDrones() {
    List<DroneJpaEntity> entities = droneRepository.findAll();
    List<Drone> drones = new ArrayList<>();
    for(DroneJpaEntity entity : entities)
      drones.add(mapper.mapToDomainEntity(entity));
    return drones;
  }

  @Override
  public Drone getDroneById(Long droneId) {
    return mapper.mapToDomainEntity(getDroneEntityById(droneId));
  }

  @Override
  public List<Medication> getAllMedications(Long droneId) {
    List<MedicationJpaEntity> entities = medicationRepository.findAllByDroneId(droneId);
    List<Medication> medications = new ArrayList<>();
    for (MedicationJpaEntity entity : entities)
      medications.add(mapper.mapMedicationToEntity(entity));

    return medications;
  }

  @Override
  public List<Drone> getAvailableDrones() {
    List<DroneJpaEntity> entities = droneRepository.findAllAvailableDrones();
    List<Drone> drones = new ArrayList<>();
    for(DroneJpaEntity entity : entities)
      drones.add(mapper.mapToDomainEntity(entity));
    return drones;
  }

  @Override
  public Integer checkBatteryLevel(Long droneId) {
    return null;
  }
}
