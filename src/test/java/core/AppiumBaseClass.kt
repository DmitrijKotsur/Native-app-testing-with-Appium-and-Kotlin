package core

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement

open class AppiumBaseClass {

    fun driver(): AppiumDriver<MobileElement> {
        return AppiumDriverController.instance.driver!!
    }

}
