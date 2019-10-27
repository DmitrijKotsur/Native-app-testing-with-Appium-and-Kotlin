package screen

import core.AppiumDriverController
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.By
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.lang.Exception

abstract class BaseScreen(driver: AppiumDriver<*>) {

    val driver = AppiumDriverController.instance.driver

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }

    fun elementIsExistById(element: String): Boolean {
        return try {
            val elements = driver!!.findElementsById(element)
            elements.size > 0
        } catch (e: org.openqa.selenium.NoSuchElementException) {
            false
        }
    }

    fun elementIsExistByXpath(element: String): Boolean {
        return try {
            val elements = driver!!.findElementsByXPath(element)
            elements.size > 0
        } catch (e: Exception) {
            false
        }
    }

}
