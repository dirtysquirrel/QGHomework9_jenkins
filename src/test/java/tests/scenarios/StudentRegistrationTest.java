package tests.scenarios;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StudentRegistrationTest extends TestBase {
    StudentRegistrationForm studentRegistrationForm = new StudentRegistrationForm();

    @Test
    public void checkRegistrationForm() {
        studentRegistrationForm.openPage();
        studentRegistrationForm.fillForm();
        studentRegistrationForm.checkData();

    }

}
