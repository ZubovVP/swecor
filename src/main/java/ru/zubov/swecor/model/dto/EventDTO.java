package ru.zubov.swecor.model.dto;

import lombok.Data;
import ru.zubov.swecor.model.Event;
import ru.zubov.swecor.model.Type;

import java.util.Date;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 01.11.2021.
 */
@Data
public class EventDTO {
    private int id;
    private int deviceId;
    private Date date;
    private Type type;
    private Boolean isRead;

    public EventDTO(Event event) {
        this.setId(event.getId());
        this.setType(event.getType());
        this.setIsRead(event.getIsRead());
        this.setDate(event.getDate());
        this.setDeviceId(event.getDeviceId().getId());
    }

    public EventDTO() {
    }
}
