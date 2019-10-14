package com.example.springsample.core;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class SampleHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = 0 ;
        System.out.println("**************excute**************");
        System.out.println(Arrays.asList(method).toString());
        if (method.isDefault()) {
            Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                    .getDeclaredConstructor(Class.class, int.class);
            constructor.setAccessible(true);

            Class<?> declaringClass = method.getDeclaringClass();
            int allModes = MethodHandles.Lookup.PUBLIC | MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED | MethodHandles.Lookup.PACKAGE;

            return constructor.newInstance(declaringClass, allModes)
                    .unreflectSpecial(method, declaringClass)
                    .bindTo(proxy)
                    .invokeWithArguments(args);
        }
        return  result;
    }
}
