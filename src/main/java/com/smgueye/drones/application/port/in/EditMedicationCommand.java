package com.smgueye.drones.application.port.in;

import com.smgueye.drones.adapters.out.persistence.DroneState;
import com.smgueye.drones.common.SelfValidating;
import com.smgueye.drones.domain.Drone;
import com.smgueye.drones.exceptions.EntityException;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Value
@EqualsAndHashCode(callSuper = false)
public class EditMedicationCommand extends SelfValidating<EditMedicationCommand> {

  private static final Logger logger = LoggerFactory.getLogger(EditMedicationCommand.class);

  Integer id;

  @NotNull
  @Pattern(regexp = "^[A-Za-z0-9-_]+$")
  String name;

  @NotNull
  @Min(value = 0)
  Integer weight;

  @NotNull
  @Pattern(regexp = "^[A-Z0-9-_]+$")
  String code;

  @NotNull
  String imageUrl;

  @NotNull
  Drone drone;

  public EditMedicationCommand(Integer id, String name, Integer weight, String code, String imageUrl, Drone drone)
      throws Exception {
    this.id = id;
    this.name = name;
    this.weight = weight;
    this.code = code;
    this.imageUrl = imageUrl;
    this.drone = drone;

    boolean isOverWeighted = drone.isOverWeighted(this.weight);
    boolean hasNotSufficientBattery = drone.getBattery() < 25;
    if (isOverWeighted || hasNotSufficientBattery)
      throw new EntityException("Unable to load medication due to drone overload");

    boolean isNotAvailable = drone.getState() != DroneState.LOADING;
    if (isNotAvailable)
      throw new EntityException("Unable to load medication due to drone availability");
    this.validateSelf();
  }
}
