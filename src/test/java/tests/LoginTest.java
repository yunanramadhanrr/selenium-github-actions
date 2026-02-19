package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.LoginPages;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {

    @Test
    void validLoginTest() {

        LoginPages loginPage = new LoginPages(driver);
        loginPage.login("standard_user", "secret_sauce");

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("inventory"),
                "Login gagal!"
        );
    }

    @Test
    void invalidLoginTest() {

        LoginPages loginPage = new LoginPages(driver);
        loginPage.login("standard_user", "wrong_password");

        String errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();

        Assertions.assertTrue(
                errorMessage.contains("Username and password do not match"),
                "Error message tidak muncul!"
        );
    }

    @Test
    void lockedUserLoginTest() {

        LoginPages loginPage = new LoginPages(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        String errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();

        Assertions.assertTrue(
                errorMessage.contains("locked out"),
                "User terkunci tidak terdeteksi!"
        );
    }
}