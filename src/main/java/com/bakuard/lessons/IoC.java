package com.bakuard.lessons;

import com.bakuard.lessons.exception.UnknownBeanException;
import com.bakuard.lessons.fabric.Fabric;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class IoC {

    private static final Map<String, IoC> iocs = new ConcurrentHashMap<>();

    public static IoC scope(String scope) {
        IoC result = iocs.get(scope);
        if(result == null) {
            result = new IoC();
            iocs.put(scope, result);
        }
        return result;
    }

    public static IoC removeScope(String scope) {
        return iocs.remove(scope);
    }


    private Map<String, Fabric<?>> fabrics;
    private final ReadWriteLock lock;

    private IoC() {
        fabrics = new HashMap<>();
        lock = new ReentrantReadWriteLock();
    }

    /**
     * Возвращает указанный бин (в терминологии Spring).
     * @param beanName наименование разновидностей объектов, на который зарегестирована фабрика.
     * @param params набор параметров необходимых для создания объекта указанного типа.
     * @return объект указанного типа.
     * @param <T> тип объектов, на который зарегестирована фабрика.
     * @throws UnknownBeanException если среди типов зарегестрированных для указанного scope нет
     *         указанного типа.
     */
    public <T> T resolve(String beanName, Params params) {
        try {
            lock.readLock().lock();

            if(fabrics.containsKey(beanName)) {
                return (T) fabrics.get(beanName).create(params);
            }
            throw new UnknownBeanException("Unknown bean with name '" + beanName + '\'');
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Регистрирует фабрику для создания бинов (в терминологии Spring) с указанным именем. Особые случаи: <br/>
     * 1. Если для указанного бина уже зарегестрирована фабрика - заменяет её на указанную. <br/>
     * @param beanName наименование разновидностей объектов, на который зарегестирована фабрика.
     * @param fabric фабрика объектов указанного типа.
     * @param <T> тип объектов, для которых регистрируется фабрика.
     */
    public <T> void register(String beanName, Fabric<T> fabric) {
        try {
            lock.writeLock().lock();

            fabrics.put(beanName, fabric);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Удаляет ранее зарегестрированную фабрику для указанного бина (в терминологии Spring). Особые случаи: <br/>
     * 1. Если среди фабрик указнного нет фабрики для указанного бина - метод ничего не делает. <br/>
     * @param beanName наименование разновидностей объектов, на который зарегестирована фабрика.
     * @param <T> тип объектов, фабрика которого удаляется.
     */
    public <T> void unregister(String beanName) {
        try {
            lock.writeLock().lock();

            if (fabrics.containsKey(beanName)) fabrics.remove(beanName);
        } finally {
            lock.writeLock().unlock();
        }
    }

}
