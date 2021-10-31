package ru.zubov.swecor.model.deviceInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zubov.swecor.model.Device;
import ru.zubov.swecor.model.Event;
import ru.zubov.swecor.model.Type;

import java.util.Map;
import java.util.Set;
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
public class DeviceInfo {
    private int id;
    private String serialNumber;
    private int projectId;
    private boolean hasErrors;
    private Map<InfoForDevice, Integer> summaryInfo;

    public static DeviceInfo deviceToDeviceInfo(Device device) {
        DeviceInfo result = new DeviceInfo();
        result.setId(device.getId());
        result.setSerialNumber(device.getSerialNumber());
        result.setProjectId(device.getProjectId().getId());
        result.setSummaryInfo(convertSummaryInfo(device.getEvents()));
        result.setHasErrors(result.getSummaryInfo().get(InfoForDevice.errorCount) > 0);
        return result;
    }

    private static Map<InfoForDevice, Integer> convertSummaryInfo(Set<Event> events) {
        AtomicInteger eventCount = new AtomicInteger(0);
        AtomicInteger warningCount = new AtomicInteger(0);
        AtomicInteger errorCount = new AtomicInteger(0);
        events.forEach(e -> {
            if (e.getType().name().equals(Type.EVENT.name())) {
                eventCount.incrementAndGet();
            } else if (e.getType().name().equals(Type.WARNING.name())) {
                warningCount.incrementAndGet();
            } else if (e.getType().name().equals(Type.ERROR.name())) {
                errorCount.incrementAndGet();
            }
        });
        return Map.of(InfoForDevice.eventCount, errorCount.get(), InfoForDevice.warningCount, warningCount.get(), InfoForDevice.errorCount, eventCount.get());
    }
}
