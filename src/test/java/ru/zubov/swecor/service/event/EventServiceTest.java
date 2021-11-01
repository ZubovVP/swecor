package ru.zubov.swecor.service.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.zubov.swecor.model.Device;
import ru.zubov.swecor.model.Event;
import ru.zubov.swecor.model.Type;
import ru.zubov.swecor.repository.EventRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 01.11.2021.
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EventServiceTest {
    @Mock
    EventRepository repository;
    @InjectMocks
    EventService service;

    @BeforeEach
    void setUp() {
        service = new EventService(repository);
    }


    @Test
    void saveEventShouldReturnEventWithId() {
        Device device = Device.of(1, null, "4DBC", new HashSet<>());
        Event event = Event.of(0, device, new Date(System.currentTimeMillis()), Type.EVENT, false);
        Mockito.when(repository.save(event)).thenReturn(Event.of(1, device, new Date(System.currentTimeMillis()), Type.EVENT, false));
        assertEquals(1, service.save(event).get().getId());
    }

    @Test
    void saveEventWithIdDeviceIsZeroShouldException() {
        Device device = Device.of(0, null, "4DBC", new HashSet<>());
        Event event = Event.of(0, device, new Date(System.currentTimeMillis()), Type.EVENT, false);
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> new EventService(repository).save(event));
        assertTrue(exception.getMessage().contains("Event or type or device id mustn't be empty"));
    }

    @Test
    void saveEventWhereEventIsNullShouldException() {
        Event event = null;
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> new EventService(repository).save(event));
        assertTrue(exception.getMessage().contains("Event or type or device id mustn't be empty"));
    }

    @Test
    void saveEventWhereEventTypeIsNullShouldException() {
        Device device = Device.of(1, null, "4DBC", new HashSet<>());
        Event event = Event.of(0, device, new Date(System.currentTimeMillis()), null, false);
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> new EventService(repository).save(event));
        assertTrue(exception.getMessage().contains("Event or type or device id mustn't be empty"));
    }

    @Test
    void findByIdEventShouldReturnEvent() {
        Device device = Device.of(1, null, "4DBC", new HashSet<>());
        Event event = Event.of(0, device, new Date(System.currentTimeMillis()), Type.EVENT, false);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(event));
        assertEquals(event, service.findById(1).get());
    }

    @Test
    void findAllEventsShouldReturnEvents() {
        Device device = Device.of(1, null, "4DBC", new HashSet<>());
        Event event1 = Event.of(0, device, new Date(System.currentTimeMillis()), Type.EVENT, false);
        Event event2 = Event.of(0, device, new Date(System.currentTimeMillis()), Type.ERROR, false);
        Mockito.when(repository.findAll()).thenReturn(Set.of(event1, event2));
        assertTrue(service.findAll().get().contains(event1));
    }
}