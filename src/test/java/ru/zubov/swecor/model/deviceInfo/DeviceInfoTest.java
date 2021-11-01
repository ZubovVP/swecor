package ru.zubov.swecor.model.deviceInfo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.zubov.swecor.model.Device;
import ru.zubov.swecor.model.Event;
import ru.zubov.swecor.model.Project;
import ru.zubov.swecor.model.Type;

import java.util.Date;
import java.util.HashSet;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 01.11.2021.
 */
class DeviceInfoTest {

    @Test
    void addEventsOnlyEventTypeShouldReturnWarningTypeZeroAndErrorTypeZeroHasErrorsIsFalse() {
        Project project = Project.of(2, "TestProject", new HashSet<>());
        Device device1 = Device.of(1, project, "4DVC", new HashSet<>());
        Event event1 = Event.of(1, device1, new Date(System.currentTimeMillis()), Type.EVENT, false);
        Event event2 = Event.of(2, device1, new Date(System.currentTimeMillis()), Type.EVENT, false);
        device1.getEvents().add(event1);
        device1.getEvents().add(event2);
        project.getDevices().add(device1);
        DeviceInfo deviceInfo = DeviceInfo.deviceToDeviceInfo(device1);

        Assert.assertEquals(deviceInfo.getId(), 1);
        Assert.assertEquals(deviceInfo.getProjectId(), 2);
        Assert.assertEquals(deviceInfo.getSerialNumber(), device1.getSerialNumber());
        Assert.assertEquals(deviceInfo.getSummaryInfo().get(InfoForDevice.eventCount).intValue(), 2);
        Assert.assertEquals(deviceInfo.getSummaryInfo().get(InfoForDevice.warningCount).intValue(), 0);
        Assert.assertEquals(deviceInfo.getSummaryInfo().get(InfoForDevice.errorCount).intValue(), 0);
        Assert.assertFalse(deviceInfo.isHasErrors());
    }

    @Test
    void addEventsOnlyWarningTypeShouldReturnWarningTypeZeroAndErrorTypeZeroHasErrorsIsFalse() {
        Project project = Project.of(2, "TestProject", new HashSet<>());
        Device device1 = Device.of(1, project, "4DVC", new HashSet<>());
        Event event1 = Event.of(1, device1, new Date(System.currentTimeMillis()), Type.WARNING, false);
        Event event2 = Event.of(2, device1, new Date(System.currentTimeMillis()), Type.WARNING, false);
        device1.getEvents().add(event1);
        device1.getEvents().add(event2);
        project.getDevices().add(device1);
        DeviceInfo deviceInfo = DeviceInfo.deviceToDeviceInfo(device1);

        Assert.assertEquals(deviceInfo.getId(), 1);
        Assert.assertEquals(deviceInfo.getProjectId(), 2);
        Assert.assertEquals(deviceInfo.getSerialNumber(), device1.getSerialNumber());
        Assert.assertEquals(deviceInfo.getSummaryInfo().get(InfoForDevice.eventCount).intValue(), 0);
        Assert.assertEquals(deviceInfo.getSummaryInfo().get(InfoForDevice.warningCount).intValue(), 2);
        Assert.assertEquals(deviceInfo.getSummaryInfo().get(InfoForDevice.errorCount).intValue(), 0);
        Assert.assertFalse(deviceInfo.isHasErrors());
    }

    @Test
    void addEventsOnlyErrorsTypeShouldReturnWarningTypeZeroAndErrorTypeZeroHasErrorsIsTrue() {
        Project project = Project.of(2, "TestProject", new HashSet<>());
        Device device1 = Device.of(1, project, "4DVC", new HashSet<>());
        Event event1 = Event.of(1, device1, new Date(System.currentTimeMillis()), Type.ERROR, false);
        Event event2 = Event.of(2, device1, new Date(System.currentTimeMillis()), Type.ERROR, false);
        device1.getEvents().add(event1);
        device1.getEvents().add(event2);
        project.getDevices().add(device1);
        DeviceInfo deviceInfo = DeviceInfo.deviceToDeviceInfo(device1);

        Assert.assertEquals(deviceInfo.getId(), 1);
        Assert.assertEquals(deviceInfo.getProjectId(), 2);
        Assert.assertEquals(deviceInfo.getSerialNumber(), device1.getSerialNumber());
        Assert.assertEquals(deviceInfo.getSummaryInfo().get(InfoForDevice.eventCount).intValue(), 0);
        Assert.assertEquals(deviceInfo.getSummaryInfo().get(InfoForDevice.warningCount).intValue(), 0);
        Assert.assertEquals(deviceInfo.getSummaryInfo().get(InfoForDevice.errorCount).intValue(), 2);
        Assert.assertTrue(deviceInfo.isHasErrors());
    }

    @Test
    void addEventsFullTypeShouldReturnWarningTypeOneEventTypeOneErrorTypeOneAndHasErrorsIsTrue() {
        Project project = Project.of(2, "TestProject", new HashSet<>());
        Device device1 = Device.of(1, project, "4DVC", new HashSet<>());
        Event event1 = Event.of(1, device1, new Date(System.currentTimeMillis()), Type.EVENT, false);
        Event event2 = Event.of(2, device1, new Date(System.currentTimeMillis()), Type.WARNING, false);
        Event event3 = Event.of(3, device1, new Date(System.currentTimeMillis()), Type.ERROR, false);

        device1.getEvents().add(event1);
        device1.getEvents().add(event2);
        device1.getEvents().add(event3);
        project.getDevices().add(device1);
        DeviceInfo deviceInfo = DeviceInfo.deviceToDeviceInfo(device1);

        Assert.assertEquals(deviceInfo.getId(), 1);
        Assert.assertEquals(deviceInfo.getProjectId(), 2);
        Assert.assertEquals(deviceInfo.getSerialNumber(), device1.getSerialNumber());
        Assert.assertEquals(deviceInfo.getSummaryInfo().get(InfoForDevice.eventCount).intValue(), 1);
        Assert.assertEquals(deviceInfo.getSummaryInfo().get(InfoForDevice.warningCount).intValue(), 1);
        Assert.assertEquals(deviceInfo.getSummaryInfo().get(InfoForDevice.errorCount).intValue(), 1);
        Assert.assertTrue(deviceInfo.isHasErrors());
    }

}