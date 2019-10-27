package screen.android

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AndroidFindBy
import org.testng.Assert.assertTrue
import screen.BaseScreen
import screen.ContactDetailsScreen

class ContactDetailsScreenAndroid(driver: AppiumDriver<*>): BaseScreen(driver), ContactDetailsScreen {

    @AndroidFindBy(id = "com.android.contacts:id/menu_edit")
    private val editContactButton: AndroidElement? = null


    override fun clickEditContactButton(): EditContactScreenAndroid {
        editContactButton!!.click()
        return EditContactScreenAndroid(driver!!)
    }

    override fun checkOrganisationField(title: String, contactName: String): ContactDetailsScreen {
        val companyTitle: MobileElement? = driver!!.findElementByXPath(
                "//android.widget.TextView[@text=\"$title\"]")
        assertTrue(companyTitle != null, "Company $title not found in contact $contactName")
        return this
    }

}
