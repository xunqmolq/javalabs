package org.fpm.di.example;

import org.fpm.di.Container;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

public class DummyContainer implements Container {
    DummyBinder dummyBinder;
    public DummyContainer(DummyBinder dummyBinder) {
        this.dummyBinder = dummyBinder;
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        if (dummyBinder.getClass_data().contains(clazz))
            return create(clazz);
        if (dummyBinder.getInjection_data().containsKey(clazz))
            return (T) getComponent(dummyBinder.getInjection_data().get(clazz));
        if (dummyBinder.getSingleton_data().containsKey(clazz))
            return (T) dummyBinder.getSingleton_data().get(clazz);
        return null;
    }

    private <T> T create(Class<T> clazz) {
        for (Constructor<?> constructor: clazz.getConstructors()){
            if (constructor.isAnnotationPresent(Inject.class)){
                LinkedList<Object> parametrs = new LinkedList<>();
                for (Class<?> parametr: constructor.getParameterTypes())
                    parametrs.add(getComponent(parametr));
                try {
                    return (T) constructor.newInstance(parametrs.toArray());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        Object singleton_object;
        Constructor<?> singleton_constructor;
        try {
            singleton_constructor = clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            singleton_object = singleton_constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return (T) singleton_object;
    }
}
