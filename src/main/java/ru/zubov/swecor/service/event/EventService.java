package ru.zubov.swecor.service.event;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import ru.zubov.swecor.model.Device;
import ru.zubov.swecor.model.Event;
import ru.zubov.swecor.repository.EventRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 29.10.2021.
 */
@Slf4j
@Service
public class EventService implements EventActionsAble<Event> {
    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Event> save(Event element) {
        validateEvent(element);
        log.info("Processing add/update event {}", element);
        Optional<Event> result = Optional.of(this.repository.save(element));
        log.info("Event {} created successfully", element);
        return result;
    }

    @Override
    public Optional<Event> findById(Integer id) {
        Optional<Event> result = this.repository.findById(id);
        if (result.isPresent()) {
            log.info("Find event by id - {} successfully", id);
        } else {
            log.info("Find device by id - {} don't found", id);
        }
        return result;
    }

    @Override
    public Optional<Set<Event>> findAll() {
        Iterable<Event> result = this.repository.findAll();
        return Optional.of(new HashSet<>(IterableUtils.toList(result)));
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.info("Delete event id - {} is empty.", id);
            return;
        }
        Event event = new Event();
        event.setId(id);
        this.repository.delete(event);
        log.info("Delete event id - {} complete.", id);
    }

    private void validateEvent(Event event) {
        if (event == null || event.getType() == null || event.getDeviceId().getId() == 0) {
            throw new NullPointerException("Event or type or device id mustn't be empty");
        }
    }
}
