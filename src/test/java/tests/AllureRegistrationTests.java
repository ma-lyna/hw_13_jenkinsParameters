package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AllureRegistrationTests extends TestBase {

    @Test
    void fillRegistrationFormTest () {

        step("Open page", () -> {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        });

        step("Fill in the form", () -> {
        $("#firstName").setValue("Marina");
        $("#lastName").setValue("Konovalchik");
        $("#userEmail").setValue("marina.konovalchik@gmail.com");
        $(byText("Female")).click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__day--026").click();
        $("#subjectsInput").setValue("English");
        $(".subjects-auto-complete__menu").$(byText("English")).click();
        $(byText("Reading")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/avatar.png"));
        $("#currentAddress").setValue("Minsk, Belarus");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();
        $("#submit").click();
        });

        step("Verify final results", () -> {
        $(".modal-content").shouldBe(Condition.visible);
        $(".modal-content").shouldHave(text("Marina"),
                text("Konovalchik"),
                text("marina.konovalchik@gmail.com"),
                text("Female"),
                text("1234567890"),
                text("26 January,1995"),
                text("English"),
                text("Reading"),
                text("avatar.png"),
                text("Minsk, Belarus"),
                text("Haryana"),
                text("Karnal"));
        $("#closeLargeModal").click();
        });
    }
}






