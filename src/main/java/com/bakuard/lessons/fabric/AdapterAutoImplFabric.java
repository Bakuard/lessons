package com.bakuard.lessons.fabric;

import com.bakuard.lessons.IoC;
import com.bakuard.lessons.Params;
import org.joor.Reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

public class AdapterAutoImplFabric<T> implements Fabric<T> {

    private Class<T> adapterInterface;
    private String beanName;
    private T adapterImpl;
    private IoC ioC;

    public AdapterAutoImplFabric(Class<T> adapterInterface, IoC ioC, String beanName) {
        this.adapterInterface = adapterInterface;
        this.ioC = ioC;
        this.beanName = beanName;
    }

    public String beanName() {
        return beanName;
    }

    @Override
    public T create(Params params) {
        if(adapterImpl == null) {
            adapterImpl = implement(adapterInterface);
        }
        return adapterImpl;
    }


    private T implement(Class<T> adapterInterface) {
        Method[] methods = adapterInterface.getMethods();
        return Reflect.compile(
                adapterInterface.getName() + "Impl",
                toClassDeclaration(
                        adapterInterface.getPackageName(),
                        adapterInterface.getSimpleName(),
                        Arrays.stream(methods).
                                map(m -> {
                                    String result = null;
                                    if(m.getName().startsWith("set")) {
                                        Parameter p = m.getParameters()[0];
                                        result = toSetMethodDeclaration(
                                                beanName,
                                                m.getName(),
                                                p.getType().getName()
                                        );
                                    } else if(m.getName().startsWith("get")) {
                                        result = toGetMethodDeclaration(
                                                beanName,
                                                m.getName(),
                                                m.getReturnType().getName()
                                        );
                                    } else {
                                        result = toNotJavaBeanMethodDeclarations(
                                                beanName,
                                                m.getName()
                                        );
                                    }
                                    return result;
                                }).
                                toList()
                )
        ).create(ioC).get();
    }

    private String toClassDeclaration(String packageName,
                                      String interfaceName,
                                      List<String> methodDeclarations) {
        return  """
                package %packageName%;
                
                public class %interfaceName%Impl implements %interfaceName% {
                    
                    private com.bakuard.lessons.IoC ioC;
                    
                    public %interfaceName%Impl(com.bakuard.lessons.IoC ioC) {
                        this.ioC = ioC;
                    }
                    
                    %methods%
                    
                }
                """.
                replaceAll("%interfaceName%", interfaceName).
                replace(
                "%methods%",
                        methodDeclarations.stream().
                                reduce((a, b) -> String.join("\n", a, b)).
                                orElseThrow(() -> new IllegalArgumentException("Adapter interface must have at least one methods"))
                ).
                replace("%packageName%", packageName);
    }

    private String toSetMethodDeclaration(String beanName,
                                          String methodName,
                                          String parameterTypeName) {
        return """
                public void %methodName%(%type% value) {
                    ioC.register(
                        "%beanName%.%methodName%",
                        (com.bakuard.lessons.Params params) -> value
                    );
                }
                """.
                replaceAll("%methodName%", methodName).
                replace("%beanName%", beanName).
                replace("%type%", parameterTypeName);
    }

    private String toGetMethodDeclaration(String beanName,
                                          String methodName,
                                          String returnedTypeName) {
        return """
                public %type% %methodName%() {
                    return (%type%) ioC.resolve(
                        "%beanName%.%methodName%",
                        new com.bakuard.lessons.Params()
                    );
                }
                """.
                replaceAll("%methodName%", methodName).
                replace("%beanName%", beanName).
                replaceAll("%type%", returnedTypeName);
    }

    private String toNotJavaBeanMethodDeclarations(String beanName,
                                                   String methodName) {
        return """
                public void %methodName%() {
                    com.bakuard.lessons.component.Command command =
                    (com.bakuard.lessons.component.Command) ioC.resolve(
                        "%beanName%.%methodName%",
                        new com.bakuard.lessons.Params()
                    );
                    command.execute();
                }
                """.
                replace("%beanName%", beanName).
                replaceAll("%methodName%", methodName);
    }

}
