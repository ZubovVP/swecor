package ru.zubov.swecor.service.device;

import ru.zubov.swecor.service.SimpleActionsForService;

import java.util.Optional;
import java.util.Set;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 31.10.2021.
 */
public interface DeviceActionsAble<E> extends SimpleActionsForService<E, Integer> {

    Optional<Set<E>> findAllDevicesByProject(int idProject);

}
