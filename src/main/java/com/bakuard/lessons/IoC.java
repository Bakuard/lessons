package com.bakuard.lessons;

import com.bakuard.lessons.exceptions.UnknownDependencyTypeException;
import com.bakuard.lessons.exceptions.UnknownScope;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
* Требование предоставить один метод для всех основных операций контейнера противоречит принципу единой
* ответственности и, как следствие, создает следующие проблемы:
* 1. У возвращаемого методом значения появляется несколько семантически разных назначений, которые
*    никак не выражаются в сигнатуре метода. В случае методов register и unregister - возвращаемое
*    значение не нужно, либо его семантика отличается от значения возвращаемого методом resolve.
* 2. Для методов register и unregister требуется вполне четкий и определенный набор параметров, в то время,
*    как для метода resolve набор необходимых аргументов зависит от типа создаваемой зависимости. Разумеется,
*    обобщеный подход используемый для аргументов resolve можно использовать и для методов register и unregister,
*    но в таком случае мы отказываемся от возможности выразить контракт этих двух методов в более ясном
*    виде. Что на мой взгляд, гораздо важнее теоретической обощености.
*
* Принцип открытости замкнутости не нарушается при добавлении новых методов в интерфейс, при условии, что
* контракт старых методов остается не изменным.
 */
public class IoC {

    private Map<String, Map<Class<?>, Fabric<?>>> fabrics;
    private final ReadWriteLock lock;

    public IoC() {
        fabrics = new HashMap<>();
        lock = new ReentrantReadWriteLock();
    }

    /**
     * Возвращает объект указанного типа. Процесс создания объекта делегируется фабрике зарегестрированной
     * для укзанного scope и укзанного типа.
     * @param scope наименование scope.
     * @param createdType ссылка на класс, на который зарегестирована фабрика.
     * @param params набор параметров необходимых для создания объекта указанного типа.
     * @return объект указанного типа.
     * @param <T> тип объектов, на который зарегестирована фабрика.
     * @throws UnknownScope если указнный scope не зарегестрирован в контейнере.
     * @throws UnknownDependencyTypeException если среди типов зарегестрированных для указанного scope нет
     *         указанного типа.
     */
    public <T> T resolve(String scope, Class<T> createdType, Params params) {
        try {
            lock.readLock().lock();

            if (fabrics.containsKey(scope)) {
                if (fabrics.get(scope).containsKey(createdType)) {
                    return (T) fabrics.get(scope).get(createdType).create(params);
                }
                throw new UnknownDependencyTypeException();
            }
            throw new UnknownScope();
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Регистрирует фабрику для создания зависимостей указанного типа в указанном scope. Особые случаи: <br/>
     * 1. Если указанный scope ещё не зарегестрирован - сначал региструрет его, а затем регистрирует фабрику
     *    связанную с этим scope. <br/>
     * 2. Если для указанного scope и указанного типа уже зарегестрирована фабрика - заменяет её на указанную. <br/>
     * @param scope наименование scope.
     * @param createdType ссылка на класс, для которого регистрируется фабрика.
     * @param fabric фабрика объектов указанного типа.
     * @param <T> тип объектов, для которых регистрируется фабрика.
     */
    public <T> void register(String scope, Class<T> createdType, Fabric<T> fabric) {
        try {
            lock.writeLock().lock();

            fabrics.computeIfAbsent(scope, k -> new HashMap<>()).put(createdType, fabric);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Удаляет ранее зарегестрированную фабрику для указанного типа обхектов из указанного scope. Особые случаи: <br/>
     * 1. Если среди фабрик указнного scope нет фабрики для указанного типа - метод ничего не делает. <br/>
     * 2. Если среди всех scope этого контейнера нет указанного scope - метод ничего не делает. <br/>
     * @param scope наименование scope.
     * @param createdType ссылка на класс, фабрика которого удаляется.
     * @param <T> тип объектов, фабрика которого удаляется.
     */
    public <T> void unregister(String scope, Class<T> createdType) {
        try {
            lock.writeLock().lock();

            if (fabrics.containsKey(scope)) fabrics.get(scope).remove(createdType);
        } finally {
            lock.writeLock().unlock();
        }
    }

}
