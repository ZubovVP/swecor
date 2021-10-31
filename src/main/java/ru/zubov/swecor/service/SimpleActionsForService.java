package ru.zubov.swecor.service;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 29.10.2021.
 */
public interface SimpleActionsForService<E, I> {
    Optional<E> save(E element);

    Optional<E> findById(I id);

    Optional<Set<E>> findAll();

    void delete(I id);



}
