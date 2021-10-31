package ru.zubov.swecor.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.zubov.swecor.model.Device;
import ru.zubov.swecor.model.Project;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 29.10.2021.
 */
@Repository
public interface DeviceRepository extends CrudRepository<Device, Integer> {

    @Query("select d from Device d left join fetch d.projectId p left join fetch d.events e WHERE d.projectId = :idProject")
    Optional<Set<Device>> findAllDevicesByProject(@Param("idProject") Project idProject);
}
