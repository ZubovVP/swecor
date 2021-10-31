package ru.zubov.swecor.model.projectInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zubov.swecor.model.Device;
import ru.zubov.swecor.model.Project;
import ru.zubov.swecor.model.Type;
import ru.zubov.swecor.model.deviceInfo.InfoForDevice;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 30.10.2021.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class ProjectInfo {
    private int id;
    private String projectName;
    private Map<InfoForProject, Integer> stats;
    private String[] devices;

    public static ProjectInfo projectToDeviceInfo(Project project) {
        ProjectInfo result = new ProjectInfo();
        result.setId(project.getId());
        result.setProjectName(project.getName());
        result.setStats(convertStats(project.getDevices()));
        result.setDevices(convertSerialNumbers(project.getDevices()));
        return result;
    }

    private static Map<InfoForProject, Integer> convertStats(Set<Device> devices) {
        AtomicInteger deviceWithErrors = new AtomicInteger(0);
        AtomicInteger stableDevices = new AtomicInteger(0);
        LocalDateTime dateBefore1Day = LocalDateTime.now(ZoneOffset.UTC).minusDays(1);

        devices.forEach(e -> {
            e.getEvents().forEach(i -> {
                if (i.getType().name().equals(Type.EVENT.name())) {
                    stableDevices.incrementAndGet();
                } else if ((i.getType().name().equals(Type.ERROR.name()) || i.getType().name().equals(Type.WARNING.name()))
                        && LocalDateTime.ofInstant(i.getDate().toInstant(), ZoneOffset.UTC).isAfter(dateBefore1Day)) {
                    deviceWithErrors.incrementAndGet();
                }
            });
        });
        return Map.of(InfoForProject.deviceCount, devices.size(), InfoForProject.deviceWithErrors, deviceWithErrors.get(), InfoForProject.stableDevices, stableDevices.get());
    }

    private static String[] convertSerialNumbers(Set<Device> devices) {
        return devices.stream().map(Device::getSerialNumber).toArray(String[]::new);
    }
}

