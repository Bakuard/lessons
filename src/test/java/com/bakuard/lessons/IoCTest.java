package com.bakuard.lessons;

import com.bakuard.lessons.exceptions.UnknownDependencyTypeException;
import com.bakuard.lessons.exceptions.UnknownScope;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

class IoCTest {

    @Test
    @DisplayName("""
            register(scope, createdType, fabric):
             there is not such scope,
             there is not such createdType
             => add new scope and fabric to this scope
            """)
    public void register1() {
        IoC ioC = new IoC();

        ioC.register("new Scope", BigInteger.class, params -> BigInteger.TEN);

        Assertions.assertEquals(
                BigInteger.TEN,
                ioC.resolve("new Scope", BigInteger.class, new Params())
        );
    }

    @Test
    @DisplayName("""
            register(scope, createdType, fabric):
             there is such scope,
             there is not such createdType
             => add fabric to this scope
            """)
    public void register2() {
        IoC ioC = new IoC();
        ioC.register("new Scope", BigInteger.class, params -> BigInteger.TEN);

        ioC.register("new Scope", BigDecimal.class, params -> BigDecimal.TEN);

        Assertions.assertEquals(
                BigDecimal.TEN,
                ioC.resolve("new Scope", BigDecimal.class, new Params())
        );
    }

    @Test
    @DisplayName("""
            register(scope, createdType, fabric):
             there is such scope,
             there is such createdType
             => replace fabric
            """)
    public void register3() {
        IoC ioC = new IoC();
        ioC.register("new Scope", BigInteger.class, params -> BigInteger.TEN);

        ioC.register("new Scope", BigInteger.class, params -> BigInteger.ZERO);

        Assertions.assertEquals(
                BigInteger.ZERO,
                ioC.resolve("new Scope", BigInteger.class, new Params())
        );
    }

    @Test
    @DisplayName("""
            unregister(scope, createdType):
             there is now such scope,
             there is not such createdType
             => do nothing
            """)
    public void unregister1() {
        IoC ioC = new IoC();
        ioC.register("new Scope", BigInteger.class, params -> BigInteger.TEN);
        ioC.register("new Scope", BigDecimal.class, params -> BigDecimal.TEN);

        ioC.unregister("unknown scope", Long.class);

        Assertions.assertAll(
                () -> Assertions.assertEquals(
                        BigInteger.TEN, ioC.resolve("new Scope", BigInteger.class, new Params())),
                () -> Assertions.assertEquals(
                        BigDecimal.TEN, ioC.resolve("new Scope", BigDecimal.class, new Params()))
        );
    }

    @Test
    @DisplayName("""
            unregister(scope, createdType):
             there is such scope,
             there is not such createdType
             => do nothing
            """)
    public void unregister2() {
        IoC ioC = new IoC();
        ioC.register("new Scope", BigInteger.class, params -> BigInteger.TEN);
        ioC.register("new Scope", BigDecimal.class, params -> BigDecimal.TEN);

        ioC.unregister("new Scope", Long.class);

        Assertions.assertAll(
                () -> Assertions.assertEquals(
                        BigInteger.TEN, ioC.resolve("new Scope", BigInteger.class, new Params())),
                () -> Assertions.assertEquals(
                        BigDecimal.TEN, ioC.resolve("new Scope", BigDecimal.class, new Params()))
        );
    }

    @Test
    @DisplayName("""
            unregister(scope, createdType):
             there is such scope,
             there is such createdType
             => remove fabric
            """)
    public void unregister3() {
        IoC ioC = new IoC();
        ioC.register("new Scope", BigInteger.class, params -> BigInteger.TEN);
        ioC.register("new Scope", BigDecimal.class, params -> BigDecimal.TEN);

        ioC.unregister("new Scope", BigDecimal.class);

        Assertions.assertAll(
                () -> Assertions.assertEquals(
                        BigInteger.TEN, ioC.resolve("new Scope", BigInteger.class, new Params())),
                () -> Assertions.assertThrows(
                        UnknownDependencyTypeException.class,
                        () -> ioC.resolve("new Scope", BigDecimal.class, new Params())
                )
        );
    }

    @Test
    @DisplayName("""
            resolve(scope, createdType, params):
             there is now such scope,
             there is not such createdType
             => exception
            """)
    public void resolve1() {
        IoC ioC = new IoC();
        ioC.register("new Scope", BigInteger.class, params -> BigInteger.TEN);

        Assertions.assertThrows(
                UnknownScope.class,
                () -> ioC.resolve("unknown scope", BigInteger.class, new Params())
        );
    }

    @Test
    @DisplayName("""
            resolve(scope, createdType, params):
             there is such scope,
             there is not such createdType
             => exception
            """)
    public void resolve2() {
        IoC ioC = new IoC();
        ioC.register("new Scope", BigInteger.class, params -> BigInteger.TEN);

        Assertions.assertThrows(
                UnknownDependencyTypeException.class,
                () -> ioC.resolve("new Scope", BigDecimal.class, new Params())
        );
    }

    @Test
    @DisplayName("""
            resolve(scope, createdType, params):
             there is such scope,
             there is such createdType
             => return object
            """)
    public void resolve3() {
        IoC ioC = new IoC();
        ioC.register("new Scope", BigInteger.class, params -> BigInteger.TEN);

        Assertions.assertEquals(
                BigInteger.TEN,
                ioC.resolve("new Scope", BigInteger.class, new Params())
        );
    }

}