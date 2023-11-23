import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.BasePage;
import pages.HomePage;

@RunWith(Parameterized.class)
public class FaqTest extends BaseTest{
    private final int questionNumber; // Поле с номером вопроса в разделе "Вопросы о важном"

    // Конструктор с параметрами
    public FaqTest (int questionField) {
        this.questionNumber = questionField;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionNumber() {
        return new Object[][] {
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
        };
    }

    @Test
    public void checkClickQuestion_expectTextIsDisplayed() {
        super.implicitlyWait(5); // Неявное ожидание

        HomePage homePage = new HomePage(driver); // Создаем экземпляр класса главной страницы
        homePage.openUrl(BasePage.MAIN_PAGE_URL); // Открываем страницу в браузере
        homePage.acceptCoockie();
        homePage.clickFaqQuestion(questionNumber); // Кликаем на вопрос

        // Проверяем, открылся ли текст
        boolean actual = homePage.faqAnswerIsDisplayed(questionNumber);
        Assert.assertTrue("Expect: price and payment text is displayed", actual);
    }
}
