package ru.zubov.swecor.service;

import java.util.Set;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 29.10.2021.
 */
public interface SimpleActionsForService<E, I> {
    E save(E element);

    E findById(I id);

    Set<E> findAll();

    void delete(I id);

    Set<E> findAllDeep();

}
