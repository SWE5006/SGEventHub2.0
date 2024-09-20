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
  private UUID userId;

  @Id
  private UUID eventId;

  private Date registerDt;

  private String registerStatus;
}
