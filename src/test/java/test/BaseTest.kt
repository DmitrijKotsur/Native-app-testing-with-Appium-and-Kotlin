package test

import core.AppiumBaseClass
import core.AppiumDriverController
import core.AppiumServer
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import screen.ContactDetailsScreen
import screen.ContactsListScreen
import screen.EditContactScreen
import screen.android.ContactDetailsScreenAndroid
import screen.android.ContactsListScreenAndroid
import screen.android.EditContactScreenAndroid
import screen.ios.ContactDetailsScreenIOS
import screen.ios.ContactsListScreenIOS
import screen.ios.EditContactScreenIOS
import java.io.IOException


@RunWith(JUnit4::class)
abstract class BaseTest: AppiumBaseClass() {

    companion object {

        @JvmStatic
        private lateinit var appiumServer: AppiumServer
        @JvmStatic
        protected lateinit var contactsListScreen: ContactsListScreen

        @JvmStatic
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

            when (AppiumDriverController.instance.getPlatformName()) {
                AppiumDriverController.Platforms.IOS_SIMULATOR -> {
                    contactsListScreen = ContactsListScreenIOS(driver = AppiumDriverController.instance.driver!!)
                }

                AppiumDriverController.Platforms.ANDROID_SIMULATOR -> {
                    contactsListScreen = ContactsListScreenAndroid(driver = AppiumDriverController.instance.driver!!)

                }
            }
        }

        @JvmStatic
        @AfterClass
        fun stopAll() {
            AppiumDriverController.instance.stop()
            appiumServer.stopServer()
        }

    }

    protected fun reopenApp() {
        driver().closeApp()
        driver().launchApp()
    }


}