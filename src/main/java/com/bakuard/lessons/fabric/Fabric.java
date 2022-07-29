package com.bakuard.lessons.fabric;

import com.bakuard.lessons.Params;

/**
 * Интерфейс абстрактной фабрики. Каждая реалзиация этого интерфейса должна отвечать за создание ровно одной
 * разновидностей объектов. Под разновиднсотью объектов подразумеваются объекты разных типов или объекты одного типа,
 * но отличающиеся своим состоянием.
 * @param <T> тип создаваемых объектов фабрикой.
 */
@FunctionalInterface
public interface Fabric<T> {

    public T create(Params params);

}
