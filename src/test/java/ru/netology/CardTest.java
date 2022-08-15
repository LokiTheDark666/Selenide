package ru.netology;



import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;




import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;


public class CardTest {

    private Meeting meeting = DataGenerator.Registration.generate();




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
        form.$("[data-test-id=date] input").setValue(meeting.getFuturedateFirstMeeting());
        form.$("[data-test-id=name] input").setValue(meeting.getName());
        form.$("[data-test-id=phone] input").setValue(meeting.getPhoneNumber());
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=success-notification]")
                .shouldBe(Condition.text(("Встреча успешно запланирована на ")+
                        (meeting.getFuturedateFirstMeeting())))
                .shouldBe(Condition.visible);



        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(meeting.getFuturedateSecondMeeting());
        form.$(".button").click();
        $("[data-test-id=replan-notification]").shouldBe(exist);
        $$(".button").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]")
                .shouldBe(Condition.text(("Встреча успешно запланирована на ")+
                        (meeting.getFuturedateSecondMeeting())))
                .shouldBe(Condition.visible);


    }


}


