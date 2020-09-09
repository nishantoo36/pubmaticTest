package utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SeleniumUtility {

    protected WebDriver driver;

    public SeleniumUtility(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void takeScreenShot(String screenshotName, String path) {
        try {
            File sourcePath = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            File destinationPath = new File(path + screenshotName + ".png");
            FileUtils.copyFile(sourcePath, destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void clickOnElement(WebElement ele, int waitTime) {
        if (isElementAvailable(ele, waitTime)) {
            if (isElementClickAble(ele, waitTime / 2)) {
                ele.click();
            } else {
                Assert.fail(ele + "is not clickable");
            }
        } else {
            Assert.fail(ele + "is not available");
        }
    }

    protected void enterValue(WebElement ele, String val, int waitTime) {
        if (isElementAvailable(ele, waitTime)) {
            ele.sendKeys(val);
        } else {
            Assert.fail(ele + "is not available");
        }
    }

    protected boolean isElementAvailable(WebElement element, int timeoutVal) {
        boolean ret = false;
        try {
            fluentWait(timeoutVal).until(ExpectedConditions.visibilityOf(element));
            if (element.isDisplayed()) {
                ret = true;
                return ret;
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
        return ret;
    }

    protected boolean isElementClickAble(WebElement element, int timeoutVal) {
        boolean ret = false;
        try {
            fluentWait(timeoutVal).until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected Wait fluentWait(int timeoutVal) {
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(timeoutVal))
                .pollingEvery(Duration.ofMillis(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(IndexOutOfBoundsException.class);
        return wait;
    }

    protected void openUrl(String url) {
        driver.get(url);
    }


    protected String getText(WebElement ele, int time) {
        if (isElementAvailable(ele, time)) {
            return ele.getText();
        }
        throw new RuntimeException("Element is not available");
    }

    public List<String> getAllElementsTextFromElementList(List<WebElement> elements, int timeOut) throws InterruptedException {
        List<String> elementData = new ArrayList<String>(elements.size());
        if (isElementListAvailable(elements, timeOut)) {
            for (WebElement val : elements) {
                elementData.add(val.getText());
            }
            return elementData;
        } else {
            Assert.fail(elements + "Elements are not present on screen");
            return null;
        }
    }

    public boolean isElementListAvailable(List<WebElement> elements, int timeoutVal) throws InterruptedException {
        boolean ret = false;
        for (int i = 0; i < 5; i++) {
            try {
                fluentWait(timeoutVal).until(ExpectedConditions.visibilityOf(elements.get(0)));
                if (elements.get(0).isDisplayed()) {
                    ret = true;
                    return ret;
                }
            } catch (IndexOutOfBoundsException e) {
                Thread.sleep(1000);
                continue;
            } catch (TimeoutException e) {
                e.printStackTrace();
                return false;
            }
        }
        return ret;
    }

    public WebElement getElementByTextFromElementList(List<WebElement> elements, String textToCompare, int timeout) throws InterruptedException {
        if (isElementListAvailable(elements, timeout)) {
            for (WebElement ele : elements) {
                if (ele.getText().equals(textToCompare)) {
                    return ele;
                }
            }
        } else {
            Assert.fail(elements + "Elements are not present on screen");
        }
        Assert.fail("Unable to find element which is having value as " + textToCompare);
        return null;
    }


}
