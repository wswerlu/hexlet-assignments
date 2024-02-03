package exercise;

import java.lang.reflect.Field;
// BEGIN
import java.util.Arrays;
import java.util.*;
import java.util.stream.Collectors;

public class Validator {
    public static List<String> validate(Object object) throws IllegalAccessException {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(NotNull.class))
                .filter(field -> getValue(field, object) == null)
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public static Map<String, List<String>> advancedValidate(Object object) throws IllegalAccessException {
        Map<String, List<String>> result = new HashMap<>();

        Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(MinLength.class) || field.isAnnotationPresent(NotNull.class))
                .forEach(field -> {
                    String fieldName = field.getName();
                    List<String> fieldErrors = getErrorsList(field, object);

                    if (!fieldErrors.isEmpty()) {
                        result.put(fieldName, fieldErrors);
                    }
                });

        return result;
    }

    private static List<String> getErrorsList(Field field, Object object) {
        List<String> errors = new ArrayList<>();
        String value = (String) getValue(field, object);

        if (field.isAnnotationPresent(NotNull.class) && value == null) {
            errors.add("can not be null");
        }

        if (field.isAnnotationPresent(MinLength.class)) {
            int minLength = field.getAnnotation(MinLength.class).minLength();

            if (value == null || value.length() < minLength) {
                errors.add("length less than " + minLength);
            }
        }

        return errors;
    }

    private static Object getValue(Field field, Object object) {
        Object value;

        try {
            field.setAccessible(true);
            value = field.get(object);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return value;
    }
}
// END
