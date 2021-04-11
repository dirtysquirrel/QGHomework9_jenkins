package tests.scenarios;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StudentRegistrationTest {
    StudentRegistrationForm studentRegistrationForm = new StudentRegistrationForm();


    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }


    @Test
    public void checkRegistrationForm() {
        studentRegistrationForm.openPage();
        studentRegistrationForm.fillForm();
        studentRegistrationForm.checkData();

    }

}
