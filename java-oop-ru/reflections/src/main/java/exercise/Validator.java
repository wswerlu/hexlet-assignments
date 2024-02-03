package exercise;

import java.lang.reflect.Field;
// BEGIN
import java.util.Arrays;
import java.util.*;
import java.util.stream.Collectors;

public class Validator {
    public static List<String> validate(Object object) throws IllegalAccessException {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(NotNull.class) != null)
                .peek(field -> field.setAccessible(true))
                .filter(field -> {
                    try {
                        return field.get(object) == null;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public static Map<String, List<String>> advancedValidate(Object object) throws IllegalAccessException {
        Map<String, List<String>> result = new HashMap<>();

        validate(object)
                .forEach(field -> result.computeIfAbsent(field, value -> new ArrayList<>()).add("can not be null"));

        Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(MinLength.class) != null)
                .peek(field -> field.setAccessible(true))
                .map(field -> new AbstractMap.SimpleEntry<>(field, field.getAnnotation(MinLength.class).minLength()))
                .filter(entry -> {
                    try {
                        return  entry.getKey().get(object).toString().length() < entry.getValue();
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .forEach(entry -> result.computeIfAbsent(entry.getKey().getName(), value -> new ArrayList<>())
                        .add("length less than " + entry.getValue()));

        return result;
    }
}
// END
