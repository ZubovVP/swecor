package ru.zubov.swecor.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zubov.swecor.model.Event;
import ru.zubov.swecor.model.Project;

import java.util.Set;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 29.10.2021.
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    @Query("select e from Event e left join fetch e.deviceId d left join fetch d.projectId p")
    Set<Project> findAllDeep();
}
