package ru.netology;



import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;


public class CardTest {

    private Meeting meeting = DataGenerator.Registration.generate();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    @BeforeEach
    void openUrl() {
        open("http://localhost:9999");
    }


    @Test
    void test() {

        Configuration.holdBrowserOpen = true;

        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue(meeting.getCity());
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(formatter.format(meeting.getDateFirstMeeting()));
        form.$("[data-test-id=name] input").setValue(meeting.getFirstName() + " " + meeting.getLastName());
        form.$("[data-test-id=phone] input").setValue("+79996664545");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();


        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(formatter.format(meeting.getDateSecondMeeting()));
        form.$(".button").click();
        $("[data-test-id=replan-notification]").shouldBe(exist);
        $$(".button").find(exactText("Перепланировать")).click();

    }


}


