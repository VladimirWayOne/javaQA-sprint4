import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

// Наследуемый класс, который содержит методы для всех тестов (аналог фикстур в Python)
public class BaseTest {
    protected WebDriver driver;

    // Почему-то при запуске тестов ругается через раз на конструктор с ошибкой:
    // java: cannot find symbol
    //  symbol: class BaseTest
    // временное решение закомментить/раскомментить
    public BaseTest() {
    }

    private String getWebDriverName() {
        Properties driverPropertiesFile = new Properties();
        try {
            driverPropertiesFile.load(new FileReader("src/main/resources/browser.properties"));
            return driverPropertiesFile.getProperty("browserName");
        } catch (IOException e) {
            e.printStackTrace();
            return "chrome";
        }
    }

    private WebDriver getWebDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized", "--disable-dev-shm-usage", "--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                return new FirefoxDriver(firefoxOptions);
            default:
                throw new RuntimeException("Incorrect BrowserName");
        }
    }


    @Before
    public void setUp() {
        driver = getWebDriver(getWebDriverName());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // Неявное ожидание

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}