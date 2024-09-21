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
public class EventRegistration {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID registerId;
  private UUID userId;
  private UUID eventId;
  private Date registerDt;
  private String registerStatus;
}
