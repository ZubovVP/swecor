package ru.zubov.swecor.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zubov.swecor.model.Project;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 29.10.2021.
 */
public interface ProjectRepository extends CrudRepository<Project, String> {

}
