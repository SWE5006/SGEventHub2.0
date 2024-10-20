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

  @Column(name = "event_id")
  private UUID eventId; // 使用UUID作为主键类型

  @Column(name = "event_title")
  private String eventTitle;

  @Column(name = "event_desc")
  private String eventDesc;

  @Column(name = "event_create_dt")
  private Date eventCreateDt;

  @Column(name = "event_start_dt")
  private Date eventStartDt;

  @Column(name = "event_end_dt")
  private Date eventEndDt;

  @Column(name = "event_place")
  private String eventPlace;

  @Column(name = "event_capacity")
  private int eventCapacity;

  @Column(name = "event_owner_id")
  private String eventOwnerId;

  @Column(name = "event_status")
  private String eventStatus;

  @Column(name = "event_cover", length = 16777215)
  private String eventCover;
}
