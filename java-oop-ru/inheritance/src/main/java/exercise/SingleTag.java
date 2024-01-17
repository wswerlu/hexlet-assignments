package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag{
    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("<" + getName());
        for (Map.Entry<String, String> attribute : getAttributes().entrySet()) {
            result
                    .append(" ")
                    .append(attribute.getKey())
                    .append("=\"")
                    .append(attribute.getValue())
                    .append("\"");
        }
        result.append(">");

        return result.toString();
    }
}
// END
