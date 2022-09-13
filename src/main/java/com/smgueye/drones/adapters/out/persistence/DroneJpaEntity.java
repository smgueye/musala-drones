package com.smgueye.drones.adapters.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "drones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneJpaEntity {

  @Id @GeneratedValue private Long id;

  @Column(name = "serial_number", nullable = false, length = 100)
  private String serialNumber;

  @Enumerated(EnumType.ORDINAL)
  private DroneModel model;

  @Column private Integer weight = 500;

  @Column private Integer battery = 100;

  @Enumerated(EnumType.ORDINAL)
  private DroneState state;
}
