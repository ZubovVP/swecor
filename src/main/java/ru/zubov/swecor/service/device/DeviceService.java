package ru.zubov.swecor.service.device;

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
@Service
public class DeviceService implements DeviceActionsAble<Device> {
    private final DeviceRepository repository;

    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Device> save(Device element) {
        return Optional.of(this.repository.save(element));
    }

    @Override
    public Optional<Device> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Set<Device>> findAll() {
        Iterable<Device> result = this.repository.findAll();
        return Optional.of(new HashSet<>(IterableUtils.toList(result)));
    }

    @Override
    public void delete(Integer id) {
        Device device = new Device();
        device.setId(id);
        this.repository.delete(device);
    }

    @Override
    public Optional<Set<Device>> findAllDevicesByProject(int idProject) {
        Project project = new Project();
        project.setId(idProject);
        return this.repository.findAllDevicesByProject(project);
    }
}
