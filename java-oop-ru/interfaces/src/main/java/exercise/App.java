package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {public static List<String> buildApartmentsList(List<Home> homes, long count) {
    return homes.stream()
            .limit(count)
            .sorted(Comparator.comparing(Home::getArea))
            .map(Object::toString)
            .collect(Collectors.toList());
}
}
// END
