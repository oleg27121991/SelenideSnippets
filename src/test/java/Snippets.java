import com.codeborne.selenide.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Snippets {

    void browser_command_examples() {
        open("https://google.com"); // Открыть веб-страницу по указанному URL.
        open("/customer/orders"); // Открыть относительный URL с учетом базового URL, если он задан.
        open("/", AuthenticationType.BASIC, new BasicAuthCredentials("", "user", "password")); // Открыть URL с аутентификацией HTTP Basic.
        Selenide.back(); // Вернуться на предыдущую страницу.
        Selenide.refresh(); // Обновить текущую страницу.
        Selenide.clearBrowserCookies(); // Очистить куки браузера.
        Selenide.clearBrowserLocalStorage(); // Очистить локальное хранилище браузера.
        executeJavaScript("sessionStorage.clear();"); // Выполнить JavaScript для очистки sessionStorage.
        Selenide.confirm(); // Подтвердить диалоговое окно браузера (нажать "OK").
        Selenide.dismiss(); // Отклонить диалоговое окно браузера (нажать "Cancel").
        Selenide.closeWindow(); // Закрыть активную вкладку браузера.
        Selenide.closeWebDriver(); // Закрыть браузер полностью.
        Selenide.switchTo().frame("new"); // Переключиться внутрь iframe с указанным именем.
        Selenide.switchTo().defaultContent(); // Вернуться к основному содержимому страницы после переключения в iframe.
        Selenide.switchTo().window("The Internet"); // Переключиться на другую вкладку браузера.
        var cookie = new Cookie("foo", "bar");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie); // Добавить cookie в браузер.
    }

    void selectors_examples() {
        $("div").click(); // Найти элемент по тегу "div" и выполнить клик.
        element("div").click(); // То же самое, но используя метод element().
        $("div", 2).click(); // Найти третий элемент "div" на странице и выполнить клик.
        $x("//h1/div").click(); // Найти элемент с помощью XPath и выполнить клик.
        $(byXpath("//h1/div")).click(); // То же самое, используя метод byXpath().
        $(byText("full text")).click(); // Найти элемент по тексту и выполнить клик.
        $(withText("ull tex")).click(); // Найти элемент по частичному тексту и выполнить клик.
        $(byTagAndText("div", "full text")); // Найти элемент по тегу и тексту.
        $(withTagAndText("div", "ull text")); // Найти элемент по тегу и частичному тексту.
        $("").parent(); // Найти родительский элемент.
        $("").sibling(1); // Найти соседний элемент с указанным индексом.
        $("").preceding(1); // Найти предшествующий элемент с указанным индексом.
        $("").closest("div"); // Найти ближайший элемент с указанным тегом.
        $("").ancestor("div"); // То же самое, что и closest.
        $("div:last-child"); // Найти последний элемент с указанным тегом.
        $("div").$("h1").find(byText("abc")).click(); // Найти вложенный элемент и выполнить клик.
        $(byAttribute("abc", "x")).click(); // Найти элемент по атрибуту и значению и выполнить клик.
        $("[abc=x]").click(); // То же самое, используя сокращенный синтаксис.
        $(byId("mytext")).click(); // Найти элемент по ID и выполнить клик.
        $("#mytext").click(); // То же самое, используя сокращенный синтаксис.
        $(byClassName("red")).click(); // Найти элемент по классу и выполнить клик.
        $(".red").click(); // То же самое, используя сокращенный синтаксис.
    }

    void actions_examples() {
        $("").click(); // Выполнить клик по элементу.
        $("").doubleClick(); // Выполнить двойной клик по элементу.
        $("").contextClick(); // Выполнить контекстное (правое) нажатие по элементу.
        $("").hover(); // Навести курсор мыши на элемент.
        $("").setValue("text"); // Ввести текст в элемент.
        $("").append("text"); // Добавить текст к существующему тексту в элементе.
        $("").clear(); // Очистить текст в элементе.
        $("").setValue(""); // Очистить текст в элементе (вариант с пустой строкой).
        $("div").sendKeys("c"); // Отправить горячую клавишу "c" на элемент.
        actions().sendKeys("c").perform(); // Отправить горячую клавишу "c" на всем приложении.
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Отправить сочетание клавиш (Ctrl + F).
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f")); // То же самое, используя элемент "html".
        $("").pressEnter(); // Нажать клавишу "Enter".
        $("").pressEscape(); // Нажать клавишу "Escape".
        $("").pressTab(); // Нажать клавишу "Tab".
        // Комплексные действия с клавишами и мышью, пример:
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();
        // Устаревшие действия для HTML, которые не работают с многими современными фреймворками.
        $("").selectOption("dropdown_option"); // Выбрать опцию в выпадающем списке.
        $("").selectRadio("radio_options"); // Выбрать радиокнопку.
    }

    void assertions_examples() {
        $("").shouldBe(visible); // Проверить видимость элемента.
        $("").shouldNotBe(visible); // Проверить, что элемент не видим.
        $("").shouldHave(text("abc")); // Проверить наличие текста "abc" в элементе.
        $("").shouldNotHave(text("abc")); // Проверить отсутствие текста "abc" в элементе.
        $("").should(appear); // Проверить видимость элемента.
        $("").shouldNot(appear); // Проверить, что элемент не видим.
        // Проверка с увеличенным временем ожидания:
        $("").shouldBe(visible, Duration.ofSeconds(30)); // Проверить видимость элемента с заданным временем ожидания.
    }

    void conditions_examples() {
        $("").shouldBe(visible); // Проверить видимость элемента.
        $("").shouldBe(hidden); // Проверить, что элемент скрыт.
        $("").shouldHave(text("abc")); // Проверить наличие текста "abc" в элементе.
        $("").shouldHave(exactText("abc")); // Проверить точное совпадение текста "abc" в элементе.
        $("").shouldHave(textCaseSensitive("abc")); // Проверить текст с учетом регистра.
        $("").shouldHave(exactTextCaseSensitive("abc")); // Проверить точное совпадение текста с учетом регистра.
        $("").should(matchText("[0-9]abc$")); // Проверить текст с использованием регулярного выражения.
        $("").shouldHave(cssClass("red")); // Проверить наличие класса "red".
        $("").shouldHave(cssValue("font-size", "12")); // Проверить значение CSS-свойства "font-size".
        $("").shouldHave(value("25")); // Проверить значение атрибута "value".
        $("").shouldHave(exactValue("25")); // Проверить точное значение атрибута "value".
        $("").shouldBe(empty); // Проверить, что элемент пуст.
        $("").shouldHave(attribute("disabled")); // Проверить наличие атрибута "disabled".
        $("").shouldHave(attribute("name", "example")); // Проверить атрибут "name" с указанным значением.
        $("").shouldHave(attributeMatching("name", "[0-9]abc$")); // Проверить атрибут с использованием регулярного выражения.
        $("").shouldBe(checked); // Проверить, что флажок (чекбокс) выбран.
        // Предупреждение! Проверяет только наличие элемента в DOM, не его видимость!
        $("").should(exist); // Проверить наличие элемента в DOM.
        // Предупреждение! Проверяет только атрибут "disabled". Не работает с многими современными фреймворками.
        $("").shouldBe(disabled); // Проверить, что элемент заблокирован.
        $("").shouldBe(enabled); // Проверить, что элемент активен.
    }

    void collections_examples() {
        $$("div"); // Ничего не делает! Это просто создает коллекцию элементов, которую нужно использовать дальше.
        $$x("//div"); // Создать коллекцию элементов с использованием XPath.
        // Выборки и фильтрации:
        $$("div").filterBy(text("123")).shouldHave(size(1)); // Выбрать элементы с текстом "123" и проверить количество.
        $$("div").excludeWith(text("123")).shouldHave(size(1)); // Исключить элементы с текстом "123" и проверить количество.
        $$("div").first().click(); // Выбрать первый элемент коллекции и выполнить клик.
        elements("div").first().click(); // То же самое, используя метод elements().
        $$("div").last().click(); // Выбрать последний элемент коллекции и выполнить клик.
        $$("div").get(1).click(); // Выбрать второй элемент (с индексом 1) и выполнить клик.
        $("div", 1).click(); // То же самое, используя селектор и индекс.
        $$("div").findBy(text("123")).click(); // Найти первый элемент с указанным текстом и выполнить клик.
        // Утверждения:
        $$("").shouldHave(size(0)); // Проверить, что коллекция пуста.
        $$("").shouldBe(CollectionCondition.empty); // То же самое, используя CollectionCondition.
        $$("").shouldHave(texts("Alfa", "Beta", "Gamma")); // Проверить наличие текстов в коллекции.
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma")); // Проверить точное совпадение текстов в коллекции.
        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa")); // Проверить тексты в любом порядке.
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa")); // Проверить тексты с учетом регистра в любом порядке.
        $$("").shouldHave(itemWithText("Gamma")); // Проверить наличие элемента с указанным текстом.
        $$("").shouldHave(sizeGreaterThan(0)); // Проверить, что количество элементов больше 0.
        $$("").shouldHave(sizeGreaterThanOrEqual(1)); // Проверить, что количество элементов больше или равно 1.
        $$("").shouldHave(sizeLessThan(3)); // Проверить, что количество элементов меньше 3.
        $$("").shouldHave(sizeLessThanOrEqual(2)); // Проверить, что количество элементов меньше или равно 2.
    }

    void file_operation_examples() throws FileNotFoundException {
        File file1 = $("a.fileLink").download(); // Загружает файл, связанный с элементом <a href="..">.
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // Загружает файл с использованием указанных опций, обычно используется для скачивания файлов в папку. Может вызывать проблемы с Grid/Selenoid.
        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile(file); // Выбирает файл для загрузки, указанный как параметр.
        $("#file-upload").uploadFromClasspath("readme.txt"); // Загружает файл из classpath.
        // Не забудьте подтвердить загрузку, обычно это делается кликом на кнопке
        $("uploadButton").click();
    }

    void javascript_examples() {
        executeJavaScript("alert('selenide')"); // Выполняет JavaScript код, который выводит диалоговое окно с текстом "selenide".
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12); // Выполняет JavaScript код с аргументами и выводит сумму аргументов в диалоговом окне.
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7); // Выполняет JavaScript код, который возвращает результат операции умножения 6 на 7 (42) и сохраняет его в переменной fortytwo.
    }
}