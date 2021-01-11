package core.basesyntax.lib;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.BetDaoImpl;
import core.basesyntax.dao.UserDao;
import core.basesyntax.dao.UserDaoImpl;
import core.basesyntax.exceptions.AnnotationException;
import core.basesyntax.factory.Factory;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Injector {
    public static Object getInstance(Class clazz) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor declaredConstructor = clazz.getDeclaredConstructor();
        Object instance = declaredConstructor.newInstance();
        Field[] declaredFields = clazz.getDeclaredFields();
        Class<Dao> annotationDao = Dao.class;
        for (Field field : declaredFields) {
            if (field.getAnnotation(Inject.class) != null) {
                field.setAccessible(true);
                if (!BetDaoImpl.class.isAnnotationPresent(annotationDao)
                        && !UserDaoImpl.class.isAnnotationPresent(annotationDao)) {
                    if (field.getType() == BetDao.class) {
                        field.set(instance, Factory.getBetDao());
                    } else if (field.getType() == UserDao.class) {
                        field.set(instance, Factory.getUserDao());
                    }
                } else {
                    throw new AnnotationException("Can't find required annotation");
                }
            }
        }
        return instance;
    }
}
