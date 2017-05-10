package wae.thesis.indiv.core;

import wae.thesis.indiv.api.ServiceBinder;
import wae.thesis.indiv.api.exception.ClassNotFoundException;
import wae.thesis.indiv.api.item.ErrorCode;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

/**
 * Created by Nguyen Tan Dat.
 */
public class ReflectionUtils {

    public static Annotation[] getAnnotationsByClassName(String className) {
        Annotation[] annotations = null;
        try {
            Class expectedClass = Class.forName(className);
            annotations = expectedClass.getAnnotations();
        } catch (java.lang.ClassNotFoundException e) {
            throw new ClassNotFoundException(
                  "CLASS_NOT_FOUND",
                  ErrorCode.SERVICE_NOT_FOUND);
        }

        return annotations;
    }

    public static Annotation getWantedAnnotation(Annotation[] annotations, Class expectedAnno) {
        return Stream.of(annotations)
              .filter(annotation -> annotation.annotationType().equals(expectedAnno))
              .findFirst()
              .orElse(null);
    }
}
