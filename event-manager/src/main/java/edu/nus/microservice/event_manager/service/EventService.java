package edu.nus.microservice.event_manager.service;

import edu.nus.microservice.event_manager.dto.EventRequest;
import edu.nus.microservice.event_manager.dto.EventResponse;
import edu.nus.microservice.event_manager.model.Event;
import edu.nus.microservice.event_manager.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final EventRepository eventRepository;


    public List<EventResponse> getAllEvents() {
        List<Event> eventlist = (List<Event>) eventRepository.findAll();

        return eventlist.stream().map(this::maptoEventResponse).toList();
    }

    private EventResponse maptoEventResponse(Event event) {
        return EventResponse.builder()
                .eventDesc(event.getEventDesc())

                .build();
    }

    public EventResponse createEvent(EventRequest eventRequest) {
        // 如果 eventId 为空，自动生成 UUID
        UUID eventId = (eventRequest.getEventId() == null) ? UUID.randomUUID() : eventRequest.getEventId();

        // 自动设置 CreateDt
        Date createDt = new Date();

        // 设置 eventOwnerId, 假设从上下文获取当前用户的 ID
        // 你需要根据你的安全实现获取当前登录用户的 ID
//        String eventOwnerId = getCurrentUserId(); // 示例方法，实际实现需替换

        // 如果 eventStatus 为空，默认设置为 "open"
        String eventStatus = (eventRequest.getEventStatus() == null) ? "open" : eventRequest.getEventStatus();

        // 创建 Event 实体对象
        Event sgevent = Event.builder()
                .eventId(eventId)
                .eventCreateDt(createDt)
                .eventCover(eventRequest.getEventCover())
                .eventCapacity(eventRequest.getEventCapacity())
//                .eventOwnerId(eventOwnerId)
                .eventPlace(eventRequest.getEventPlace())
                .eventStartDt(eventRequest.getEventStartDt())
                .eventStatus(eventStatus)
                .eventTitle(eventRequest.getEventTitle())
                .eventEndDt(eventRequest.getEventEndDt())
                .eventDesc(eventRequest.getEventDesc())
                .build();

        // 保存到数据库
        eventRepository.save(sgevent);

        // 返回 EventResponse
        return new EventResponse(
                sgevent.getEventId(),
                sgevent.getEventTitle(),
                sgevent.getEventDesc(),
                sgevent.getEventCreateDt(),
                sgevent.getEventStartDt(),
                sgevent.getEventEndDt(),
                sgevent.getEventPlace(),
                sgevent.getEventCapacity(),
                sgevent.getEventOwnerId(),
                sgevent.getEventStatus(),
                sgevent.getEventCover()
        );
    }


    public void deleteEventbyId(UUID eventId)
    {
        eventRepository.deleteById(eventId);

    }

    public EventResponse searchEventByTitle(String Title)
    {
        Event sgevent = eventRepository.SearchEventByTitle(Title);


        return new EventResponse(sgevent.getEventId(),sgevent.getEventTitle(),
                sgevent.getEventDesc(),sgevent.getEventCreateDt(),sgevent.getEventStartDt(),
                sgevent.getEventEndDt(),sgevent.getEventPlace(),sgevent.getEventCapacity(),
                sgevent.getEventOwnerId(),sgevent.getEventStatus(),sgevent.getEventCover());

    }

    public EventResponse searchEventById(UUID eventId)
    {
        Event sgevent = eventRepository.QueryEventById(eventId);


        return new EventResponse(sgevent.getEventId(),sgevent.getEventTitle(),
                sgevent.getEventDesc(),sgevent.getEventCreateDt(),sgevent.getEventStartDt(),
                sgevent.getEventEndDt(),sgevent.getEventPlace(),sgevent.getEventCapacity(),
                sgevent.getEventOwnerId(),sgevent.getEventStatus(),sgevent.getEventCover());

    }

    public  int UpdateEvent( UUID eventId, String eventTitle, String eventDesc, String eventCover, String eventPlace,
                             Date eventStartDt, Date eventEndDt, int eventCapacity)
    {
        return eventRepository.UpdateEvent( eventId, eventTitle, eventDesc, eventCover, eventPlace, eventStartDt, eventEndDt,eventCapacity);
    }
}
