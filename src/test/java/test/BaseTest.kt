package test

import core.AppiumBaseClass
import core.AppiumDriverController
import core.AppiumServer
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import screen.ContactsListScreen
import screen.android.ContactsListScreenAndroid
import screen.ios.ContactsListScreenIOS
import java.io.IOException


abstract class BaseTest: AppiumBaseClass() {


    private lateinit var appiumServer: AppiumServer

    protected lateinit var contactsListScreen: ContactsListScreen

    @BeforeClass
    fun configureEnvironment() {

        try {
            Runtime.getRuntime().exec("/usr/bin/killall -KILL node")
        } catch (e: IOException) {
            e.printStackTrace()
        }

        appiumServer = AppiumServer()
        if (!appiumServer.checkServerIsRunning(4725)) {
            appiumServer.startServer()
        }

        AppiumDriverController.instance.start()

        val driver = AppiumDriverController.instance.driver!!

        contactsListScreen = when (AppiumDriverController.instance.getPlatformName()) {
            AppiumDriverController.Platforms.IOS_SIMULATOR -> {
                ContactsListScreenIOS(driver = driver)
            }

            AppiumDriverController.Platforms.ANDROID_SIMULATOR -> {
                ContactsListScreenAndroid(driver = driver)
            }
        }
    }


    @AfterClass
    fun stopAll() {
        AppiumDriverController.instance.stop()
        appiumServer.stopServer()
    }



    protected fun reopenApp() {
        driver().closeApp()
        driver().launchApp()
    }

}
