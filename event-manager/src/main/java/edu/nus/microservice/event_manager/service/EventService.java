package edu.nus.microservice.event_manager.service;

import edu.nus.microservice.event_manager.dto.EventDetailResponse;
import edu.nus.microservice.event_manager.dto.EventRequest;
import edu.nus.microservice.event_manager.dto.EventResponse;
import edu.nus.microservice.event_manager.model.Event;
import edu.nus.microservice.event_manager.model.EventRegistration;
import edu.nus.microservice.event_manager.repository.EventRegisterRepository;
import edu.nus.microservice.event_manager.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final EventRegisterRepository eventRegisterRepository;

    public List<EventResponse> getAllEvents(UUID userId) {
        List<Event> eventlist = (List<Event>) eventRepository.findAll();
        List<EventRegistration> registerlist = (List<EventRegistration>) eventRegisterRepository.findAll();

        return eventlist.stream().map(event ->mapEventToEventResponseWithRegistration(event,registerlist,userId)).toList();
    }

    private EventResponse mapEventToEventResponseWithRegistration(Event event, List<EventRegistration> registerlist,UUID userId ) {
        ModelMapper modelMapper = new ModelMapper();
        EventResponse response = modelMapper.map(event, EventResponse.class);
        //check whether current user had registered this event
        boolean isRegistered = registerlist.stream().anyMatch(evt -> evt.getEventId().equals(response.getEventId()) && evt.getUserId().equals(userId));
        //check how many user has registered this event
        long regCount = registerlist.stream().filter(evt -> evt.getEventId().equals(event.getEventId())).count();
        response.setRegistered(isRegistered);
        response.setRegistrationCount(regCount);
        return response;
    }

    private EventResponse maptoEventResponse(Event event) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(event, EventResponse.class);
    }

    private EventDetailResponse maptoEventDetailResponse(Event event) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(event, EventDetailResponse.class);
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
        return maptoEventResponse(sgevent);
    }


    public void deleteEventbyId(UUID eventId)
    {
        eventRepository.deleteById(eventId);

    }

    public EventResponse searchEventByTitle(String Title)
    {
        Event sgevent = eventRepository.SearchEventByTitle(Title);

        return  maptoEventResponse(sgevent);
    }

    public EventDetailResponse searchEventById(UUID eventId)
    {
        Event sgevent = eventRepository.QueryEventById(eventId);
        List<EventRegistration> registrationList = eventRegisterRepository.SearchEventRegister(eventId);

        EventDetailResponse response =  maptoEventDetailResponse(sgevent);
        response.setUserList(registrationList);
        return response;
    }

    public  int UpdateEvent( UUID eventId, String eventTitle, String eventDesc, String eventCover, String eventPlace,
                             Date eventStartDt, Date eventEndDt, int eventCapacity)
    {
        return eventRepository.UpdateEvent( eventId, eventTitle, eventDesc, eventCover, eventPlace, eventStartDt, eventEndDt,eventCapacity);
    }
}
