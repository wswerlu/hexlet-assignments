package exercise;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

// BEGIN
public class PairedTag extends Tag{
    private final String body;
    private final List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = new ArrayList<>(children);
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
        result.append(body);

        for (Tag child : children) {
            result.append(child.toString());
        }

        result
                .append("</")
                .append(getName())
                .append(">");

        return result.toString();
    }
}
// END
