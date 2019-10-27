package core

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.IOSMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.IOException
import java.net.URL
import java.util.Properties
import java.util.concurrent.TimeUnit


class AppiumDriverController {


    var driver: AppiumDriver<MobileElement>? = null
    private val localURL = "http://127.0.0.1:4725/wd/hub"


    /**
     * Driver instance initialization in case of platform
     */

    enum class Platforms {
        ANDROID_SIMULATOR,
        IOS_SIMULATOR
    }


    @Throws(IOException::class)
    fun start() {
        if (driver != null) {
            return
        }

        val propertiesStream = Thread.currentThread().contextClassLoader
                .getResourceAsStream("config.properties")

        properties.load(propertiesStream)
        simulatorName = properties.getProperty("sim.name")
        platformName = properties.getProperty("os")

        selectedPlatform = detectOS(platformName)
        val caps = DesiredCapabilities()

        when (selectedPlatform) {
            Platforms.IOS_SIMULATOR -> {
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest")
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS")
                caps.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.apple.MobileAddressBook")
                caps.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true)
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, simulatorName)
                caps.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT")
                caps.setCapability(MobileCapabilityType.LANGUAGE, "EN")
                caps.setCapability(MobileCapabilityType.NO_RESET, true)
                caps.setCapability(MobileCapabilityType.FULL_RESET, false)

                driver = IOSDriver(URL(localURL), caps)
                driver!!.context("NATIVE_APP")
            }

            Platforms.ANDROID_SIMULATOR -> {
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2")
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, simulatorName)
                caps.setCapability(AndroidMobileCapabilityType.AVD, simulatorName)
                caps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true)
                caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.contacts")
                caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.contacts.activities.PeopleActivity")
                caps.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT")
                caps.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true)
                caps.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true)
                caps.setCapability(MobileCapabilityType.NO_RESET, true)
                caps.setCapability(MobileCapabilityType.FULL_RESET, false)

                driver = AndroidDriver(URL(localURL), caps)
                driver!!.context("NATIVE_APP")
            }
        }

        driver!!.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
    }


    fun stop() {
        if (driver != null) {
            driver!!.quit()
            driver = null
        }

        try {
            Runtime.getRuntime().exec("xcrun simctl shutdown all")
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


    private fun detectOS(os: String): Platforms {
        return if (os.toLowerCase() == "ios") {
            Platforms.IOS_SIMULATOR
        } else Platforms.ANDROID_SIMULATOR
    }


    fun getPlatformName(): Platforms {
        return selectedPlatform
    }


    companion object {

        private lateinit var simulatorName: String
        private lateinit var platformName: String
        private lateinit var selectedPlatform: Platforms

        var properties = Properties()
        var instance = AppiumDriverController()

    }
}
