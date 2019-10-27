package screen

import core.AppiumDriverController
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.By
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

abstract class BaseScreen(driver: AppiumDriver<*>) {

    val driver = AppiumDriverController.instance.driver

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }

    fun elementIsExist(element: String): Boolean {
        return try {
            val element = driver!!.findElementById(element)
            element.isDisplayed
        } catch (e: org.openqa.selenium.NoSuchElementException) {
            false
        }
    }

    fun waitForElementAndClick(element: String) {
        WebDriverWait(driver, 5).until(
                ExpectedConditions.elementToBeClickable(By.id(element))
        )
        driver!!.findElementById(element).click()
    }

}
