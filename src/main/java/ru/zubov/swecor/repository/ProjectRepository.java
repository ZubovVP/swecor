package ru.zubov.swecor.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
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
public interface ProjectRepository extends CrudRepository<Project, Integer> {

    @Query("select p from Project p left join fetch p.devices d left join fetch d.events e")
    Set<Project> findAllDeep();
}
