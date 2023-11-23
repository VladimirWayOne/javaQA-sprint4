import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.BasePage;
import pages.OrderPage;

import static org.hamcrest.CoreMatchers.*;

// Класс для  тестирования перехода на страницу Яндекса (Дзена) или Главную страницу самоката
@RunWith(Parameterized.class)
public class RedirectTest extends BaseTest {
    private final String startUrl;

    // Конструктор
    public RedirectTest(String startUrl) {
        super();
        this.startUrl = startUrl;
    }

    @Parameterized.Parameters
    public static Object[][] getStartUrl() {
        return new Object[][]{
                // Короткий текст
                { BasePage.MAIN_PAGE_URL},
                // Длинный текст
                {OrderPage.ORDER_PAGE_URL},


        };
    }


    // Если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса
    @Test
    public void checkClickLogoYandex_expectGoToYandexMainPage() {
        super.implicitlyWait(5); // Неявное ожидание

        // Создаем экземпляр класса верхней части станицы c логотипом
        BasePage basePage = new BasePage(driver);
        basePage.openUrl(startUrl); // Открываем страницу в браузере
        basePage.clickYandexLogoAndSwitchToWindow(); // Кликаем на логотип Яндекса и переходим в новое окно

        // Проверяем, что URL в новом окне совпадает с URL на главной странице Яндекса
        String expectedDzen = "dzen.ru";
        String expectedYandex = "yandex.ru";// Ожидаемый URL
        String actual = driver.getCurrentUrl(); // Фактический URL
        // ассерт по вхождению, так как может открыться капча дзена
        MatcherAssert.assertThat(  actual, anyOf(containsString(expectedDzen), containsString(expectedYandex)));
    }

    // Если нажать на логотип Самоката, то вернемся/останемся на главную страницу Самоката
    @Test
    public void checkClickLogoScooter_expectGoToScooterMainPage() {
        super.implicitlyWait(5); // Неявное ожидание

        // Создаем экземпляр класса верхней части станицы c логотипом
        BasePage basePage = new BasePage(driver);
        basePage.openUrl(startUrl); // Открываем страницу в браузере
        basePage.clickScooterLogo(); // Кликаем на логотип Яндекса и переходим в новое окно

        // Проверяем, что URL в новом окне совпадает с URL на главной странице Яндекса
        String expected = "https://qa-scooter.praktikum-services.ru/"; // Ожидаемый URL
        String actual = driver.getCurrentUrl(); // Фактический URL
        // ассерт по вхождению, так как может открыться капча дзена
        MatcherAssert.assertThat(  actual, is(expected) );
    }

}
