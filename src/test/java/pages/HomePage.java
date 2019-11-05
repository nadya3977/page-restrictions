package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    @FindBy(id = "profileGlobalItem")
    private WebElement profile;

    @FindBy(xpath = "//a[@href='/wiki/logout.action']")
    private WebElement logOutButton;


    public HomePage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void logOut() {

        new WebDriverWait(driver, 10).until(ExpectedConditions.not(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath("//div[@data-test-id='restrictions-dialog-modal']")))));
        waitToBePresent("//*[@id='profileGlobalItem']");
        waitToBeClickableAndClick(profile);
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'Droplist')]//div[contains(@data-webitem-location,'system.user')]")));

        waitToBePresent("//a[@href='/wiki/logout.action']");
        waitToBeClickableAndClick(logOutButton);
        waitUntilUrlContains("id.atlassian.com/login");
    }

    public PublishedPage navigateToPublishedPage(String url) {
        driver.get(url);
        waitForJavascriptComplete();
         waitForJavascriptComplete();
        return new PublishedPage(driver);
    }


}
