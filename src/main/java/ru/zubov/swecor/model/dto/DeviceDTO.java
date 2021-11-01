package ru.zubov.swecor.model.dto;

import lombok.Data;
import ru.zubov.swecor.model.Device;
import ru.zubov.swecor.model.Event;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 01.11.2021.
 */
@Data
public class DeviceDTO {
    private int id;
    private int projectId;
    private String serialNumber;
    private Set<EventDTO> events = new HashSet<>();

    public DeviceDTO() {
    }

    public  DeviceDTO (Device device) {
        this.setId(device.getId());
        this.setProjectId(device.getId());
        this.setSerialNumber(device.getSerialNumber());
        for (Event event : device.getEvents()) {
            this.getEvents().add(new EventDTO(event));
        }
    }
}
