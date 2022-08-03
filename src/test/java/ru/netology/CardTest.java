package ru.netology;

import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardTest {



    @BeforeEach
        void openUrl() {
            open("http://localhost:9999");
        }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");



    @Test
    void test() {

        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("Москва");
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(formatter.format(LocalDate.now().plusDays(3)));
        form.$("[data-test-id=name] input").setValue("Василий Алибабаевич-Пупкин");
        form.$("[data-test-id=phone] input").setValue("+78889996565");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='notification']").shouldBe(exist, Duration.ofSeconds(15));


    }

}
