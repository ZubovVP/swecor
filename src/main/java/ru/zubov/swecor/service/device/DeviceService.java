package ru.zubov.swecor.service.device;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import ru.zubov.swecor.model.Device;
import ru.zubov.swecor.model.Project;
import ru.zubov.swecor.repository.DeviceRepository;

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
public class DeviceService implements DeviceActionsAble<Device> {
    private final DeviceRepository repository;

    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Device> save(Device element) {
        validateDevice(element);
        log.info("Processing add/update device {}", element);
        Optional<Device> result = Optional.of(this.repository.save(element));
        log.info("Device {} created successfully", element);
        return result;
    }

    @Override
    public Optional<Device> findById(Integer id) {
        Optional<Device> result = this.repository.findById(id);
        if (result.isPresent()) {
            log.info("Find device by id - {} successfully", id);
        } else {
            log.info("Find device by id - {} don't found", id);
        }
        return result;
    }

    @Override
    public Optional<Set<Device>> findAll() {
        Iterable<Device> result = this.repository.findAll();
        return Optional.of(new HashSet<>(IterableUtils.toList(result)));
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.info("Delete device id - {} is empty.", id);
            return;
        }
        Device device = new Device();
        device.setId(id);
        log.info("Delete device id - {} complete.", id);
        this.repository.delete(device);
    }

    @Override
    public Optional<Set<Device>> findAllDevicesByProject(int idProject) {
        Project project = new Project();
        project.setId(idProject);
        return this.repository.findAllDevicesByProject(project);
    }

    private void validateDevice(Device device) {
        if (device == null || device.getSerialNumber() == null || device.getProjectId().getId() == 0 || device.getSerialNumber().isBlank()) {
            throw new NullPointerException("Device or serial number or project id mustn't be empty");
        }
    }
}
