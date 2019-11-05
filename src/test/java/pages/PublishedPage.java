package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PublishedPage extends BasePage {

    private static String URL_MATCH = "/pages";

    @FindBy(xpath = "//button[@data-test-id='restrictions.dialog.button']")
    private WebElement restrictionsButton;

    @FindBy(id ="title-text")
    private WebElement titleText;

    @FindBy(xpath = "//h2/span[contains(text(),'This is a restricted page')]")
    private WebElement restrictedTitle;


    public PublishedPage(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("Unexpected page");
        }

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean isRestrictionButtonDisplayed() {

       // waitForVisibility(restrictionsButton);
        return restrictionsButton.isDisplayed();
    }

    public String title() {

        waitForVisibility(titleText);
        return titleText.getText();
    }

    public boolean isRestrictedTitleShown(){
        return restrictedTitle.isDisplayed();
    }

    public RestrictionsPopUpPage clickRestrictionButton() {
       // waitForVisibility(restrictionsButton);
        waitUntilAllElementsAreVisible("//button[@data-test-id='restrictions.dialog.button']");
        waitToBeClickableAndClick(restrictionsButton);
        waitUntilAllElementsAreVisible("//div[@data-test-id='restrictions-dialog-modal']");
        return new RestrictionsPopUpPage(driver);
    }

}
