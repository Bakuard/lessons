package com.bakuard.lessons;

import com.bakuard.lessons.exception.UnknownParameterException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Представялет набор аргументов необходимых для разрешения каждой зависимости. <br/>
 * Объекты данного класса не изменяемы и могут безопасно использоваться из нескольких потоков.
 */
public final class Params {

    private Map<String, Object> params;

    public Params() {
        this.params = new HashMap<>();
    }

    private Params(Map<String, Object> params) {
        this.params = params;
    }

    /**
     * Создает и возвращает новый объект содержащий заданные параметры. Новый объект отличается от текущего
     * добавленным параметром с именем name и значением value. Особые случаи: <br/>
     * 1. Если среди параметров уже есть параметр с указанным именем и значением - метод просто вернет ссылку
     *    на текущий объект. <br/>
     * @param name имя параметра.
     * @param value значение параметра.
     * @return новый объект содержащий заданные параметры или этот же объект.
     * @param <T> тип значения параметра.
     */
    public <T> Params put(String name, T value) {
        Params result = this;

        if(!Objects.equals(params.get(name), value)) {
            Map<String, Object> newParams = new HashMap<>(params);
            newParams.put(name, value);
            result = new Params(newParams);
        }

        return result;
    }

    /**
     * Возвращает параметр по его имени.
     * @param name имя возвращаемого параметра.
     * @return значение параметра по его имени.
     * @param <T> тип значения параметра.
     * @throws UnknownParameterException если нет параметра с указанным именем.
     */
    public <T> T get(String name) {
        Objects.requireNonNull(name, "Parameter name can't be null");

        if(!params.containsKey(name)) {
            throw new UnknownParameterException("Missing required param with name '" + name + "'");
        }

        return (T) params.get(name);
    }

    /**
     * Создает и возвращает новый объект содержащий заданные параметры. Новый объект отличается от текущего
     * удаленным параметром с именем name. Особые случаи: <br/>
     * 1. Если среди параметров нет параметра с указанным именем - метод просто вернет ссылку на текущий
     *    объект. <br/>
     * @param name имя параметра.
     * @return новый объект содержащий заданные параметры или этот же объект.
     */
    public Params remove(String name) {
        Params result = this;

        if(params.containsKey(name)) {
            Map<String, Object> newParams = new HashMap<>(params);
            newParams.remove(name);
            result = new Params(newParams);
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Params params1 = (Params) o;
        return params.equals(params1.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(params);
    }

    @Override
    public String toString() {
        return "Params" + params;
    }

}
