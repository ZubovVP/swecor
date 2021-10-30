package ru.zubov.swecor.service;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import ru.zubov.swecor.model.Device;
import ru.zubov.swecor.repository.DeviceRepository;

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
public class DeviceService implements SimpleActionsForService<Device, Integer> {
    private final DeviceRepository repository;

    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Device save(Device element) {
        return this.repository.save(element);
    }

    @Override
    public Device findById(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Set<Device> findAll() {
        Iterable<Device> result = this.repository.findAll();
        return new HashSet<>(IterableUtils.toList(result));
    }

    @Override
    public void delete(Integer id) {
        Device device = new Device();
        device.setId(id);
        this.repository.delete(device);
    }

    @Override
    public Set<Device> findAllDeep() {
        return this.repository.findAllDeep();
    }
}
