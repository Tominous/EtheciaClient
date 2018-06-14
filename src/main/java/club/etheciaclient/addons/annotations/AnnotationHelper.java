package club.etheciaclient.addons.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationHelper {
    public static boolean hasAnnotation(Class<? extends Annotation> annotation, Class<?> clazz) {
        return clazz.isAnnotationPresent(annotation);
    }
    public static boolean hasAnnotation(Class<? extends Annotation> annotation, Method method) {
        return method.isAnnotationPresent(annotation);
    }
    public static boolean hasAnnotation(Class<? extends Annotation> annotation, Field field) {
        return field.isAnnotationPresent(annotation);
    }
    public static Annotation getAnnotation(Class<? extends Annotation> ann, Class<?> clazz) {
        return clazz.getAnnotation(ann);
    }
    public static Annotation getAnnotation(Class<? extends Annotation> ann, Method method) {
        return method.getAnnotation(ann);
    }
    public static Annotation getAnnotation(Class<? extends Annotation> ann, Field field) {
        return field.getAnnotation(ann);
    }
}
