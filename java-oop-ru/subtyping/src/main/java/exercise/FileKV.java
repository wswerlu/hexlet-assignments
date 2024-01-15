package exercise;

// BEGIN
import java.util.Map;

public class FileKV implements KeyValueStorage{
    private String path;

    public FileKV(String path, Map<String, String> storage) {
        this.path = path;

        for (Map.Entry<String, String> data : storage.entrySet()) {
            set(data.getKey(), data.getValue());
        }
    }

    @Override
    public void set(String key, String value) {
        String serializedContent = Utils.readFile(path);
        Map<String, String> unserializedContent = Utils.unserialize(serializedContent);

        unserializedContent.put(key, value);

        String content = Utils.serialize(unserializedContent);
        Utils.writeFile(path, content);
    }

    @Override
    public void unset(String key) {
        String serializedContent = Utils.readFile(path);
        Map<String, String> unserializedContent = Utils.unserialize(serializedContent);

        unserializedContent.remove(key);

        String content = Utils.serialize(unserializedContent);
        Utils.writeFile(path, content);
    }

    @Override
    public String get(String key, String defaultValue) {
        String serializedContent = Utils.readFile(path);
        Map<String, String> unserializedContent = Utils.unserialize(serializedContent);

        return unserializedContent.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        String serializedContent = Utils.readFile(path);
        return Utils.unserialize(serializedContent);
    }
}
// END
