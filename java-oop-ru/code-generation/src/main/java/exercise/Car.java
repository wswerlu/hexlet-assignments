package exercise;

import java.io.IOException;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() throws IOException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public static Car unserialize(String jsonString) throws IOException {
        return new ObjectMapper().readValue(jsonString, Car.class);
    }
    // END
}
