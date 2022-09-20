package rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    public static void main(String[] args) throws IOException {
        Properties config = new Properties();
        Properties or = new Properties();

        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
        FileInputStream fileInputStream1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");

        config.load(fileInputStream);
        or.load(fileInputStream1);
        System.out.println(config.getProperty("browser"));
        System.out.println(or.getProperty("bmlBtn"));

    }
}
