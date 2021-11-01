package ru.zubov.swecor.model.projectInfo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.zubov.swecor.model.Device;
import ru.zubov.swecor.model.Event;
import ru.zubov.swecor.model.Project;
import ru.zubov.swecor.model.Type;
import ru.zubov.swecor.model.deviceInfo.DeviceInfo;
import ru.zubov.swecor.model.deviceInfo.InfoForDevice;

import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 01.11.2021.
 */
class ProjectInfoTest {

    @Test
    void addEventsOnlyEventTypeShouldReturnDeviceCountTwoDeviceWithErrorsZeroAndStableDevicesTwo() {
        Project project = Project.of(2, "TestProject", new HashSet<>());
        Device device = Device.of(1, project, "4DVC", new HashSet<>());
        Event event1 = Event.of(1, device, new Date(System.currentTimeMillis()), Type.EVENT, false);
        Event event2 = Event.of(2, device, new Date(System.currentTimeMillis()), Type.EVENT, false);
        device.getEvents().add(event1);
        device.getEvents().add(event2);
        project.getDevices().add(device);

        ProjectInfo projectInfo = ProjectInfo.projectToDeviceInfo(project);

        Assert.assertEquals(projectInfo.getId(), project.getId());
        Assert.assertEquals(projectInfo.getProjectName(), project.getName());
        Assert.assertEquals(projectInfo.getStats().get(InfoForProject.deviceCount).intValue(), project.getDevices().size());
        Assert.assertEquals(projectInfo.getStats().get(InfoForProject.deviceWithErrors).intValue(), 0);
        Assert.assertEquals(projectInfo.getStats().get(InfoForProject.stableDevices).intValue(), 2);
    }

    @Test
    void addEventsOnlyWarningTypeShouldReturnDeviceCountTwoDeviceWithErrorsTwoAndStableDevicesZero() {
        Project project = Project.of(2, "TestProject", new HashSet<>());
        Device device = Device.of(1, project, "4DVC", new HashSet<>());
        Event event1 = Event.of(1, device, new Date(System.currentTimeMillis()), Type.WARNING, false);
        Event event2 = Event.of(2, device, new Date(System.currentTimeMillis()), Type.WARNING, false);
        device.getEvents().add(event1);
        device.getEvents().add(event2);
        project.getDevices().add(device);

        ProjectInfo projectInfo = ProjectInfo.projectToDeviceInfo(project);

        Assert.assertEquals(projectInfo.getId(), project.getId());
        Assert.assertEquals(projectInfo.getProjectName(), project.getName());
        Assert.assertEquals(projectInfo.getStats().get(InfoForProject.deviceCount).intValue(), project.getDevices().size());
        Assert.assertEquals(projectInfo.getStats().get(InfoForProject.deviceWithErrors).intValue(), 2);
        Assert.assertEquals(projectInfo.getStats().get(InfoForProject.stableDevices).intValue(), 0);
    }

    @Test
    void addEventsOnlyErrorsTypeShouldReturnDeviceCountTwoDeviceWithErrorsTwoAndStableDevicesZero() {
        Project project = Project.of(2, "TestProject", new HashSet<>());
        Device device = Device.of(1, project, "4DVC", new HashSet<>());
        Event event1 = Event.of(1, device, new Date(System.currentTimeMillis()), Type.ERROR, false);
        Event event2 = Event.of(2, device, new Date(System.currentTimeMillis()), Type.ERROR, false);
        device.getEvents().add(event1);
        device.getEvents().add(event2);
        project.getDevices().add(device);

        ProjectInfo projectInfo = ProjectInfo.projectToDeviceInfo(project);

        Assert.assertEquals(projectInfo.getId(), project.getId());
        Assert.assertEquals(projectInfo.getProjectName(), project.getName());
        Assert.assertEquals(projectInfo.getStats().get(InfoForProject.deviceCount).intValue(), project.getDevices().size());
        Assert.assertEquals(projectInfo.getStats().get(InfoForProject.deviceWithErrors).intValue(), 2);
        Assert.assertEquals(projectInfo.getStats().get(InfoForProject.stableDevices).intValue(), 0);
    }

    @Test
    void addEventsFullTypeShouldReturnDeviceCountThreeDeviceWithErrorsTwoAndStableDevicesOne() {
        Project project = Project.of(2, "TestProject", new HashSet<>());
        Device device = Device.of(1, project, "4DVC", new HashSet<>());
        Event event1 = Event.of(1, device, new Date(System.currentTimeMillis()), Type.EVENT, false);
        Event event2 = Event.of(2, device, new Date(System.currentTimeMillis()), Type.WARNING, false);
        Event event3 = Event.of(3, device, new Date(System.currentTimeMillis()), Type.ERROR, false);

        device.getEvents().add(event1);
        device.getEvents().add(event2);
        device.getEvents().add(event3);
        project.getDevices().add(device);

        ProjectInfo projectInfo = ProjectInfo.projectToDeviceInfo(project);

        Assert.assertEquals(projectInfo.getId(), project.getId());
        Assert.assertEquals(projectInfo.getProjectName(), project.getName());
        Assert.assertEquals(projectInfo.getStats().get(InfoForProject.deviceCount).intValue(), project.getDevices().size());
        Assert.assertEquals(projectInfo.getStats().get(InfoForProject.deviceWithErrors).intValue(), 2);
        Assert.assertEquals(projectInfo.getStats().get(InfoForProject.stableDevices).intValue(), 1);
    }
}