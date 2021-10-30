package ru.zubov.swecor.service;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import ru.zubov.swecor.model.Event;
import ru.zubov.swecor.repository.EventRepository;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 29.10.2021.
 */
@Service
public class EventService implements SimpleActionsForService<Event, Integer> {
    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event save(Event element) {
        return null;
    }

    @Override
    public Event findById(Integer id) {
        return null;
    }

    @Override
    public Set<Event> findAll() {
        Iterable<Event> result = this.repository.findAll();
        return new HashSet<>(IterableUtils.toList(result));
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Set<Event> findAllDeep() {
        return null;
    }
}
