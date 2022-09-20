package com.smgueye.drones.application.port.in;

import com.smgueye.drones.adapters.out.persistence.DroneModel;
import com.smgueye.drones.adapters.out.persistence.DroneState;
import com.smgueye.drones.common.SelfValidating;
import com.smgueye.drones.exceptions.EntityException;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
@EqualsAndHashCode(callSuper = false)
public class EditDroneCommand extends SelfValidating<EditDroneCommand> {

  Integer id;

  @NotNull
  @Size(max = 100)
  String serialNumber;

  @NotNull
  DroneModel model;

  @NotNull
  @Max(value = 500)
  @Min(value = 0)
  Integer weight;

  @NotNull
  @Max(value = 100)
  @Min(value = 0)
  Integer battery;

  @NotNull
  DroneState state;

  public EditDroneCommand(
      Integer id,
      String serialNumber,
      DroneModel model,
      Integer weight,
      Integer battery,
      DroneState state) {
    this.id = id;
    this.serialNumber = serialNumber;
    this.model = model;

    this.weight = weight;
    this.battery = battery;
    this.state = state;

    if (battery < 25 && state != DroneState.IDLE)
      throw new EntityException("Drone with a battery level under 25% must be in IDLE state.");

    this.validateSelf();
  }
}
