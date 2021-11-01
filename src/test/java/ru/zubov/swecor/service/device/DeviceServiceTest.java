package ru.zubov.swecor.service.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.zubov.swecor.model.Device;
import ru.zubov.swecor.model.Project;
import ru.zubov.swecor.repository.DeviceRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 01.11.2021.
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {
    @Mock
    DeviceRepository repository;
    @InjectMocks
    DeviceService service;

    @BeforeEach
    void setUp() {
        service = new DeviceService(repository);
    }

    @Test
    void saveDeviceShouldReturnDeviceWithId() {
        Project project = Project.of(1, "TestProject", new HashSet<>());
        Device device = Device.of(0, project, "4DBC", new HashSet<>());
        Mockito.when(repository.save(device)).thenReturn(Device.of(1, project, "4DBC", new HashSet<>()));
        assertEquals(1, service.save(device).get().getId());
    }

    @Test
    void saveDeviceWithIdProjectIsZeroShouldException() {
        Project project = Project.of(0, "TestProject", new HashSet<>());
        Device device = Device.of(0, project, "4DBC", new HashSet<>());
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> new DeviceService(repository).save(device));
        assertTrue(exception.getMessage().contains("Device or serial number or project id mustn't be empty"));
    }

    @Test
    void saveDeviceWhereDeviceIsNullShouldException() {
        Device device = null;
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> new DeviceService(repository).save(device));
        assertTrue(exception.getMessage().contains("Device or serial number or project id mustn't be empty"));
    }
    @Test
    void saveDeviceWithSerialNumberIsNullShouldException() {
        Project project = Project.of(1, "TestProject", new HashSet<>());
        Device device = Device.of(0, project, null, new HashSet<>());
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> new DeviceService(repository).save(device));
        assertTrue(exception.getMessage().contains("Device or serial number or project id mustn't be empty"));
    }

    @Test
    void saveDeviceWithSerialNumberIsEmptyShouldException() {
        Project project = Project.of(1, "TestProject", new HashSet<>());
        Device device = Device.of(0, project, "", new HashSet<>());
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> new DeviceService(repository).save(device));
        assertTrue(exception.getMessage().contains("Device or serial number or project id mustn't be empty"));
    }


    @Test
    void findByIdDeviceShouldReturnDevice() {
        Project project = Project.of(1, "TestProject", new HashSet<>());
        Device device = Device.of(1, project, "4DBC", new HashSet<>());
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(device));
        assertEquals(device, service.findById(1).get());
    }

    @Test
    void findAllDevicesShouldReturnDevices() {
        Project project = Project.of(1, "TestProject", new HashSet<>());
        Device device1 = Device.of(1, project, "4ABC", new HashSet<>());
        Device device2 = Device.of(2, project, "4CDB", new HashSet<>());
        Mockito.when(repository.findAll()).thenReturn(Set.of(device1, device2));
        assertTrue(service.findAll().get().contains(device1));
    }

    @Test
    void findAllDevicesByProject() {
        Project project = Project.of(1, null, new HashSet<>());
        Device device1 = Device.of(1, project, "4ABC", new HashSet<>());
        Device device2 = Device.of(2, project, "4CDB", new HashSet<>());
        Mockito.when(repository.findAllDevicesByProject(project)).thenReturn(Optional.of(Set.of(device1, device2)));
        assertTrue(service.findAllDevicesByProject(project.getId()).get().contains(device1));
    }
}