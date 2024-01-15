package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static KeyValueStorage swapKeyValue(KeyValueStorage storage) {
        for (Map.Entry<String, String> data : storage.toMap().entrySet()) {
            storage.unset(data.getKey());
            storage.set(data.getValue(), data.getKey());
        }
        return storage;
    }
}
// END
