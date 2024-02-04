package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        Arrays.stream(address.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Inspect.class))
                .forEach(method -> {
                    String methodName = method.getName();
                    String methodReturnType = method.getReturnType().getSimpleName();

                    System.out.printf("Method %s returns a value of type %s%n", methodName, methodReturnType);
                });
        // END
    }
}
