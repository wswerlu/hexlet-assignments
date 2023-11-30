package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
public class AppTest {
    @Test
    public void firstTest() {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };

        String[][] expectedEnlargeImage = {
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        };

        String[][] actualEnlargeImage = App.enlargeArrayImage(image);

        assertThat(actualEnlargeImage).isEqualTo(expectedEnlargeImage);
    }

    @Test
    public void secondTest() {
        String[][] image = {
                {"*", "*", "*"},
                {"*", " ", "*"},
                {"*", " ", "*"},
                {"*", "*", "*"},
        };

        String[][] expectedEnlargeImage = {
                {"*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*"},
                {"*", "*", " ", " ", "*", "*"},
                {"*", "*", " ", " ", "*", "*"},
                {"*", "*", " ", " ", "*", "*"},
                {"*", "*", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*"},
        };

        String[][] actualEnlargeImage = App.enlargeArrayImage(image);

        assertThat(actualEnlargeImage).isEqualTo(expectedEnlargeImage);
    }

    @Test
    public void thirdTest() {
        String[][] image = new String[1][1];

        String[][] expectedEnlargeImage = new String[2][2];

        String[][] actualEnlargeImage = App.enlargeArrayImage(image);

        assertThat(actualEnlargeImage).isEqualTo(expectedEnlargeImage);
    }
}
// END
