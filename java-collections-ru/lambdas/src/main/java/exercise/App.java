package exercise;

// BEGIN
public class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        String[][] result = new String[image.length * 2][ image[0].length * 2];

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                result[2*i][2*j] = image[i][j];
                result[2*i][2*j+1] = image[i][j];
                result[2*i+1][2*j] = image[i][j];
                result[2*i+1][2*j+1] = image[i][j];
            }
        }

        return result;
    }
}
// END
