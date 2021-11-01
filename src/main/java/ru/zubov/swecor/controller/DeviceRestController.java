package ru.zubov.swecor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zubov.swecor.model.Device;
import ru.zubov.swecor.model.deviceInfo.DeviceInfo;
import ru.zubov.swecor.model.dto.DeviceDTO;
import ru.zubov.swecor.service.device.DeviceActionsAble;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 31.10.2021.
 */
@RestController
@RequestMapping("/device")
public class DeviceRestController {
    private final DeviceActionsAble<Device> deviceService;

    public DeviceRestController(DeviceActionsAble<Device> deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/findDevices/{id}")
    public ResponseEntity<Map<String, DeviceInfo>> findAllDevices(@PathVariable int id) {
        Optional<Set<Device>> devices = this.deviceService.findAllDevicesByProject(id);
        return devices.map(deviceSet -> new ResponseEntity<>(deviceSet.stream().map(DeviceInfo::deviceToDeviceInfo)
                .collect(Collectors.toMap(DeviceInfo::getSerialNumber, Function.identity())), HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/save")
    public ResponseEntity<DeviceDTO> saveOrUpdate(@RequestBody Device device) {
        Optional<Device> result = this.deviceService.save(device);
        return result.map(value -> new ResponseEntity<>(
                new DeviceDTO(value), HttpStatus.CREATED
        )).orElseGet(() -> new ResponseEntity<>(
                null, HttpStatus.BAD_REQUEST
        ));
    }

    @GetMapping("/find")
    private ResponseEntity<Set<DeviceDTO>> findAll() {
        Optional<Set<Device>> devices = this.deviceService.findAll();
        return devices.map(projectSet -> new ResponseEntity<>(projectSet.stream().map(DeviceDTO::new).collect(Collectors.toSet()), HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DeviceDTO> findById(@PathVariable int id) {
        Optional<Device> device = this.deviceService.findById(id);
        return device.map(value -> new ResponseEntity<>(new DeviceDTO(value), HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody int id) {
        this.deviceService.delete(id);
        return ResponseEntity.ok().build();
    }
}
