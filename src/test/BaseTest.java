import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;




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

    @Before
    public void setUp(){

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--disable-dev-shm-usage", "--remote-allow-origins=*");
        driver = new ChromeDriver(options);
//        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
//        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--start-maximized");
//        driver = new FirefoxDriver();
    }


    // Неявное ожидание
    public void implicitlyWait(int numberOfSeconds) {
        driver.manage().timeouts().implicitlyWait(numberOfSeconds, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
