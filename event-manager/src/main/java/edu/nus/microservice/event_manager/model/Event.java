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
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID eventId;
  private String eventTitle;
  private String eventDesc;
  private Date eventCreateDt;
  private Date eventStartDt;
  private Date eventEndDt;
  private String eventPlace;
  private int eventCapacity;
  private String eventOwnerId;
  private String eventStatus;
  private StringBuffer eventCover;
}
