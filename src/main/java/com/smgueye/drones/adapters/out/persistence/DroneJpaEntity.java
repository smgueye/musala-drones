package com.smgueye.drones.adapters.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "drones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneJpaEntity {

  @Id @GeneratedValue private Long id;

  @Column(name = "serial_number", nullable = false, length = 100, unique = true)
  private String serialNumber;

  @Enumerated(EnumType.ORDINAL)
  private DroneModel model;

  @Column private Integer weight = 500;

  @Column private Integer battery = 100;

  @Enumerated(EnumType.ORDINAL)
  private DroneState state;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "drone")
  private List<MedicationJpaEntity> medications;
}
