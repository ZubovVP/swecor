package ru.zubov.swecor.service.project;

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
public interface ProjectActionsAble<E> extends SimpleActionsForService<E, Integer> {

    Optional<Set<E>> findAllDeep();

}
