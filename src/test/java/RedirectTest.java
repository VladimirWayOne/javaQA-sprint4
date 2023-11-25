import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.BasePage;
import pages.OrderPage;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;

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
                {BasePage.MAIN_PAGE_URL},
                // Длинный текст
                {OrderPage.ORDER_PAGE_URL},


        };
    }


    // Если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса
    @Test
    public void checkClickLogoYandexExpectGoToYandexMainPage() {

        // Создаем экземпляр класса верхней части станицы c логотипом
        BasePage basePage = new BasePage(driver);
        basePage.openUrl(startUrl); // Открываем страницу в браузере
        basePage.clickYandexLogoAndSwitchToWindow(); // Кликаем на логотип Яндекса и переходим в новое окно

        // Проверяем, что URL в новом окне совпадает с URL на главной странице Яндекса
        String expectedDzen = "dzen.ru";
        String expectedYandex = "yandex.ru";// Ожидаемый URL
        String actual = driver.getCurrentUrl(); // Фактический URL
        // ассерт по вхождению, так как может открыться капча дзена
        Assert.assertThat("Осуществляется переход на страницу Яндекса/Дзена", actual, anyOf(containsString(expectedDzen), containsString(expectedYandex)));
    }

    // Если нажать на логотип Самоката, то вернемся/останемся на главную страницу Самоката
    @Test
    public void checkClickLogoScooterExpectGoToScooterMainPage() {


        // Создаем экземпляр класса верхней части станицы c логотипом
        BasePage basePage = new BasePage(driver);
        basePage.openUrl(startUrl); // Открываем страницу в браузере
        basePage.clickScooterLogo(); // Кликаем на логотип Яндекса и переходим в новое окно

        // Проверяем, что URL в новом окне совпадает с URL на главной странице Яндекса
        String expected = "https://qa-scooter.praktikum-services.ru/"; // Ожидаемый URL
        String actual = driver.getCurrentUrl(); // Фактический URL
        // ассерт по вхождению, так как может открыться капча дзена
        Assert.assertEquals("Отображается главная страница самоката: ", actual, expected);
    }

}