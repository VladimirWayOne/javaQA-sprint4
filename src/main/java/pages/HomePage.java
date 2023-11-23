package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    // Верхняя кнопка заказа
    private final By TOP_ORDER_BUTTON = By.xpath(".//div[starts-with(@class, 'Header')]/button[text()='Заказать']");
    // Нижняя кнопка закакза
    private By BOTTOM_ORDER_BUTTON = By.xpath(".//div[starts-with(@class, 'Home')]/button[text()='Заказать']");
    // Кнопки с вопросами
    private By FAQ_BUTTONS = By.xpath(".//div[@class='accordion__button']");
    // Параграфы с ответами
    private By FAQ_ANSWERS = By.cssSelector(".accordion__panel > p");
    // Кнопка информации
    private By ORDER_STATUS_BUTTON = By.xpath(".//button[text()='Статус заказа']");


    private By faqAnswer(int answerNumber) {
        // Возвращает локатор параграфа с ответом FAQ (нумерация сверху вниз).
        return By.xpath(".//div[@class='accordion__panel' and @id='accordion__panel-{"+answerNumber+"}']/p");
    }

    public HomePage(WebDriver driver) {
        super(driver);

    }


    public void clickBottomOrderButton() {
        WebElement bottomOrderButton = driver.findElement(BOTTOM_ORDER_BUTTON);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", bottomOrderButton);
        bottomOrderButton.click();
    }
    public void clickTopOrderButton() {
        driver.findElement(TOP_ORDER_BUTTON).click();
    }

     public void clickFaqQuestion(int questionNumber) {
         driver.findElements(FAQ_BUTTONS).get(questionNumber).click();
    }
    public boolean faqAnswerIsDisplayed(int answerNumber) {
        return driver.findElements(FAQ_ANSWERS).get(answerNumber).isDisplayed();
    }
}
