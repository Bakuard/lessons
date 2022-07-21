package com.bakuard.lessons;

/**
 * Интерфейс абстрактной фабрики. Каждая реалзиация этого интерфейса должна отвечать за создание ровно одного типа
 * объектов.
 * @param <T> тип создаваемых объектов фабрикой.
 */
@FunctionalInterface
public interface Fabric<T> {

    public T create(Params params);

}
