package core

import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import io.appium.java_client.service.local.flags.GeneralServerFlag
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File
import java.io.IOException
import java.net.ServerSocket


class AppiumServer {

    private var service: AppiumDriverLocalService? = null
    private var builder: AppiumServiceBuilder? = null
    private var capabilities: DesiredCapabilities? = null
    private val port = 4725
    private val ipAddress = "127.0.0.1"


    fun startServer() {
        capabilities = DesiredCapabilities()
        capabilities!!.setCapability("noReset", "false")

        builder = AppiumServiceBuilder()
        builder!!.withIPAddress(ipAddress)
        builder!!.usingPort(port)
        builder!!.withCapabilities(capabilities)
        builder!!.withArgument(GeneralServerFlag.SESSION_OVERRIDE)

        service = AppiumDriverLocalService.buildService(builder!!)
        service!!.start()
    }

    fun stopServer() {
        service!!.stop()
    }

    fun checkServerIsRunning(port: Int): Boolean {
        var isServerRunning = false
        val serverSocket: ServerSocket?

        try {
            serverSocket = ServerSocket(port)
            serverSocket.close()
        } catch (e: IOException) {
            isServerRunning = true
        }

        return isServerRunning
    }

}
