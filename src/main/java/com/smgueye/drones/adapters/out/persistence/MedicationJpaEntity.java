package com.smgueye.drones.adapters.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "medications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationJpaEntity {

  @Id @GeneratedValue private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column private Integer weight = 0;

  @Column(unique = true)
  private String code;

  @Column private String imageUrl;

  @ManyToOne
  @JoinColumn(name = "drone_id", nullable = false)
  private DroneJpaEntity drone;
}
