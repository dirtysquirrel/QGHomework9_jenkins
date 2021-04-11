package tests.scenarios;

import com.github.javafaker.Faker;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.*;


public class StudentRegistrationForm {
    Faker faker = new Faker();

    String name = faker.name().firstName(),
            lastName = faker.name().lastName(),
            mail = faker.internet().emailAddress(),
            gender = "Female",
            phoneNumber = faker.number().digits(10),
            month = "April",
            year = "1904",
            day = "26",
            subject = "Ma",
            hobby = "Music",
            picture = ".gitignore",
            address = faker.address().fullAddress(),
            state = "Haryana",
            city = "Karnal";

    public void openPage() {
        step("открываем страницу", () -> {
            open("https://demoqa.com/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });
    }

    public void fillForm() {
        step("заполняем форму регистрации", () -> {
            step("заполняем общие данные", () -> {
                $("#firstName").setValue(name);
                $("#lastName").setValue(lastName);
                $("#userEmail").setValue(mail);
                $("label[for='gender-radio-2']").scrollTo().click();
                $("#userNumber").setValue(phoneNumber);
            });

            step("заполняем дату рождения", () -> {
                $("#dateOfBirthInput").scrollTo().click();
                $(".react-datepicker__month-select").selectOption(month);
                $(".react-datepicker__year-select").selectOption(year);
                $(".react-datepicker__day--0" + day).scrollTo().click();
            });

            step("заполняем данные о предметах", () -> {
                $("#subjectsInput").setValue(subject);
                $("#react-select-2-option-0").scrollTo().click();
                subject = $(".subjects-auto-complete__multi-value__label").scrollTo().getText();
            });

            step("заполняем данные о хобби", () -> {
                $("#hobbiesWrapper").$(byText(hobby)).scrollTo().click();
            });

            step("загружаем фото", () -> {
                File file = new File(picture);
                $("#uploadPicture").uploadFile(file);
            });
            step("вводим адрес", () -> {
                $("#currentAddress").setValue(address);

                $("#state").scrollTo().scrollTo().click();
                $(byText(state)).scrollTo().click();

                $(byText("Select City")).scrollTo().click();
                $(byText(city)).scrollTo().click();
            });
            step("сохраняем данные", () -> {
                $("#submit").scrollTo().click();
            });
        });
    }

    public void checkData() {
        step("проверка заполнения формы", () -> {
            $(".modal-body").shouldHave(
                    text(name),
                    text(lastName),
                    text(mail),
                    text(phoneNumber),
                    text(gender),
                    text(day + " " + month + "," + year),
                    text(subject),
                    text(hobby),
                    text(picture),
                    text(address),
                    text(state + " " + city)
            );
        });
    }
}

