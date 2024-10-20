package edu.nus.microservice.event_manager.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRegistration {
  @Id
  @Column(name = "register_id")
  private UUID registerId;

  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "event_id")
  private UUID eventId;

  @Column(name = "register_dt")
  private Date registerDt;

  @Column(name = "register_status")
  private String registerStatus;
}
