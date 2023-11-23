import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.BasePage;
import pages.HomePage;
import pages.OrderPage;

@RunWith(Parameterized.class)
public class OrderWorkFlowTest extends BaseTest {
    // Поля класса
    private final String firstName; // Имя заказчика
    private final String lastName; // Фамилия заказчика
    private final String address; // Адрес доставки
    private final String subway; // Метро доставки
    private final String telephoneNumber; // Номер телефона
    private final String date; // Номер телефона
    private final int rentalPeriod; // Период аренды из выпадающего меню
    private final int colour; // Цвет самоката
    private final String comment; // Комментарий
    private boolean actual; // Ожидаемый результат

    // Конструктор с параметрами
    public OrderWorkFlowTest( String firstName, String lastName, String address, String subway, String telephoneNumber, String date, int rentalPeriod, int colour, String comment) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.subway = subway;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    // Параметры
    @Parameterized.Parameters
    public static Object[][] getOrderFormData() {
        return new Object[][]{
                // Короткий текст
                { "Имя", "Фио", "Бийск", "Сокольники", "83823315149", "25.11.2023", 1, 0, "1"},
                // Длинный текст
                { "Имяимяим",
                        "Фамилияфамилияфамилияфамилияфа",
                        "РФ, г. Новосибирск, ул. Дема, адрес в 49 символов", "Сокольники", "+791347295468", "25.11.2023",
                        2, 1,
                        "КомментарийКомментарийКомментарийКомментарийКомментарийКомментарийКомментарийКомментарий" +
                                "КомментарийКомментарийКомментарийКомментарийКомментарийКомментарийКомментарийКоммен" +
                                "тарийКомментарийКомментарийКомментарийКомментарий"},


        };
    }

    // Тест: проверяем весь флоу позитивного сценария заказа самоката при нажатиии Верхней кнопки
    @Test
    public void checkOrderScooterTopButtonValidData_expectSchooterIsOrdered() {
        super.implicitlyWait(3); // Неявное ожидание

        HomePage homePage = new HomePage(driver);
        homePage.openUrl(BasePage.MAIN_PAGE_URL);
        homePage.clickTopOrderButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillUserData(firstName, lastName, address, subway, telephoneNumber);
        orderPage.goToScooterParams();
        orderPage.fillRentData(date, rentalPeriod, colour, comment);
        orderPage.acceptOrder();
        actual = orderPage.isOrderInfoVisible();
        Assert.assertTrue("Информация о заказе отображается", actual);

    }
    @Test
    public void checkOrderScooterBottomButtonValidData_expectSchooterIsOrdered() {
        super.implicitlyWait(3); // Неявное ожидание

        HomePage homePage = new HomePage(driver);
        homePage.openUrl(BasePage.MAIN_PAGE_URL);
        homePage.clickBottomOrderButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillUserData(firstName, lastName, address, subway, telephoneNumber);
        orderPage.goToScooterParams();
        orderPage.fillRentData(date, rentalPeriod, colour, comment);
        orderPage.acceptOrder();
        actual = orderPage.isOrderInfoVisible();
        Assert.assertTrue("Информация о заказе отображается", actual);

    }

}
