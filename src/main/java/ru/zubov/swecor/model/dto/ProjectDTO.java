package ru.zubov.swecor.model.dto;

import lombok.Data;
import ru.zubov.swecor.model.Device;
import ru.zubov.swecor.model.Project;

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
public class ProjectDTO {
    private int id;
    private String name;
    private Set<DeviceDTO> devices = new HashSet<>();

    public ProjectDTO() {
    }

    public ProjectDTO(Project project) {
        this.setId(project.getId());
        this.setName(project.getName());
        for (Device device : project.getDevices()) {
            DeviceDTO deviceDTO = new DeviceDTO(device);
          this.getDevices().add(deviceDTO);
        }
    }
}
