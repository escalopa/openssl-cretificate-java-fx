package source.opensslcretificatejavafx.configuration;


import source.opensslcretificatejavafx.configuration.annotations.Autowire;
import source.opensslcretificatejavafx.configuration.annotations.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

public class Context {

    private final HashMap<String, Object> objectsMap;

    public Context(String path) {
        objectsMap = new HashMap<>();
        readClasses(path);
    }

    public Object get(String class_name) {
        return objectsMap.get(class_name);
    }

    private void readClasses(String path) {
        try {
            List<Class<?>> classList = PathScan.find(path);

            for (Class<?> clazz : classList) {
                if (clazz.isAnnotationPresent(Component.class)) {
                    Object object = clazz.getDeclaredConstructor().newInstance();
                    objectsMap.put(clazz.getSimpleName(), object);
                }
            }

            for (Class<?> clazz : classList) {
                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);
                    //Initialize fields that only has @Autowire
                    if ((field.isAnnotationPresent(Autowire.class))) {
                        //Initialize field in case it does not exist in advance
                        objectsMap.putIfAbsent(clazz.getSimpleName(), field.getType().getDeclaredConstructor().newInstance());
                        field.set(objectsMap.get(clazz.getSimpleName()), objectsMap.get(field.getType().getSimpleName()));
                    }
                }
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}