package ru.zubov.swecor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zubov.swecor.model.Event;
import ru.zubov.swecor.model.dto.EventDTO;
import ru.zubov.swecor.service.event.EventActionsAble;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 31.10.2021.
 */
@RestController
@RequestMapping("/event")
public class EventRestController {
    private final EventActionsAble<Event> eventService;

    public EventRestController(EventActionsAble<Event> eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/save")
    public ResponseEntity<EventDTO> saveOrUpdate(@RequestBody Event event) {
        Optional<Event> result = this.eventService.save(event);
        return result.map(value -> new ResponseEntity<>(
                new EventDTO(value), HttpStatus.CREATED
        )).orElseGet(() -> new ResponseEntity<>(
                null, HttpStatus.BAD_REQUEST
        ));
    }

    @GetMapping("/find")
    private ResponseEntity<Set<EventDTO>> findAll() {
        Optional<Set<Event>> devices = this.eventService.findAll();
        return devices.map(projectSet -> new ResponseEntity<>(projectSet.stream().map(EventDTO::new).collect(Collectors.toSet()), HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<EventDTO> findById(@PathVariable int id) {
        Optional<Event> device = this.eventService.findById(id);
        return device.map(value -> new ResponseEntity<>(new EventDTO(value), HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody int id) {
        this.eventService.delete(id);
        return ResponseEntity.ok().build();
    }
}
