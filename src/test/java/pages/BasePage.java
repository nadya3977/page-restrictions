package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected WebDriver driver;

    public void waitToBeClickableAndClick(WebElement element) throws Error {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(element));
        el.click();
    }

    public void waitForVisibility(WebElement element) throws Error {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitToBePresent(String xpath) throws Error {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public void waitForJavascriptComplete() {
        new WebDriverWait(driver, 15).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitUntilUrlContains(String url) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.urlContains(url));
    }

    public void waitUntilAllElementsAreVisible(String xpath) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath(xpath))));

    }
    public void waitUntilAllElementsAreNotVisible(String xpath) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.not(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath(xpath)))));

    }


}
