package org.fpm.di.example;

import org.fpm.di.Binder;

import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyBinder implements Binder {
    private ArrayList<Class<?>> class_data = new ArrayList<>();
    private HashMap<Class<?>, Class<?>> injection_data = new HashMap<>();
    private HashMap<Class<?>, Object> singleton_data = new HashMap<>();

    @Override
    public <T> void bind(Class<T> clazz) {
        class_data.add(clazz);
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        injection_data.put(clazz,implementation);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        singleton_data.put(clazz,instance);
    }

    public void preprocess(){
        ArrayList<Class<?>> new_class_data = new ArrayList<>();
        ArrayList<Class<?>> singleton_classes = new ArrayList<>();
        for (Class<?> a: class_data){
            if(a.isAnnotationPresent(Singleton.class)) singleton_classes.add(a);
            else new_class_data.add(a);
        }
        class_data = new_class_data;
        for (Class<?> singleton_class: singleton_classes){
            Object singleton_object;
            Constructor<?> singleton_constructor;
            try {
                singleton_constructor = singleton_class.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            try {
                singleton_object = singleton_constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            singleton_data.put(singleton_class,singleton_object);

        }
    }

    public ArrayList<Class<?>> getClass_data() {
        return class_data;
    }

    public HashMap<Class<?>, Class<?>> getInjection_data() {
        return injection_data;
    }

    public HashMap<Class<?>, Object> getSingleton_data() {
        return singleton_data;
    }
}
