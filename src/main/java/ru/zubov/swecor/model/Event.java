package ru.zubov.swecor.model;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 29.10.2021.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    @JsonIgnore
    private Device deviceId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data")
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @NotNull
    @Column(name = "is_read")
    private Boolean isRead;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id &&
                isRead == event.isRead &&
                type == event.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, isRead);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + date +
                ", type=" + type +
                ", isRead=" + isRead +
                '}';
    }
}
