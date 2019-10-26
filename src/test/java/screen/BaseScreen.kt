package screen

import core.AppiumDriverController
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.support.PageFactory

abstract class BaseScreen(driver: AppiumDriver<*>) {

    val driver = AppiumDriverController.instance.driver

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }

}
