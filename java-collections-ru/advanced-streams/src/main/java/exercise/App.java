package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {
    public static String getForwardedVariables(String content) {
        return Arrays.stream(content.replaceAll("\"", "").split("\n"))
                .filter(str -> str.startsWith("environment"))
                .map(env -> env.split("environment=")[1])
                .flatMap(variable -> Arrays.stream(variable.split(",")))
                .filter(str -> str.startsWith("X_FORWARDED_"))
                .map(str -> str.replace("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
//END
