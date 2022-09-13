package com.smgueye.drones.adapters.out.persistence;


import com.smgueye.drones.application.port.in.EditDroneCommand;
import com.smgueye.drones.application.port.out.DroneRegistrationPort;
import com.smgueye.drones.common.PersistenceAdapter;
import com.smgueye.drones.domain.Drone;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@PersistenceAdapter
public class DronePersistenceAdapter implements DroneRegistrationPort {

  private final DroneRepository repository;
  private final DroneMapper mapper;

  @Override
  public Drone register(EditDroneCommand command) {
    DroneJpaEntity entity = repository.save(
      new DroneJpaEntity(
        null,
        command.getSerialNumber(),
        command.getModel(),
        command.getWeight(),
        command.getBattery(),
        command.getState()));
    return mapper.mapToDomainEntity(entity);
  }
}
