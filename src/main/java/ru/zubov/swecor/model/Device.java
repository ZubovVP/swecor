package ru.zubov.swecor.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 29.10.2021.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project projectId;

    @Column(name = "serial_number")
    private String serialNumber;

    @OneToMany(mappedBy = "deviceId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> events;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return id == device.id &&
                Objects.equals(projectId, device.projectId) &&
                Objects.equals(serialNumber, device.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, serialNumber);
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
