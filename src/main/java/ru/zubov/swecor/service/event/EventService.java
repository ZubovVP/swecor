package ru.zubov.swecor.service.event;

import lombok.extern.log4j.Log4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
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
@Log4j
@Service
public class EventService implements EventActionsAble<Event> {
    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Event> save(Event element) {
        return Optional.of(this.repository.save(element));
    }

    @Override
    public Optional<Event> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Set<Event>> findAll() {
        Iterable<Event> result = this.repository.findAll();
        return Optional.of(new HashSet<>(IterableUtils.toList(result)));
    }

    @Override
    public void delete(Integer id) {
        Event event = new Event();
        event.setId(id);
        this.repository.delete(event);
    }
}
