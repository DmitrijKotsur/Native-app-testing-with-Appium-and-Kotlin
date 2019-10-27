package screen.ios

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.ios.IOSElement
import io.appium.java_client.pagefactory.iOSFindBy
import org.testng.Assert.assertEquals
import screen.BaseScreen
import screen.ContactDetailsScreen
import screen.EditContactScreen

class ContactDetailsScreenIOS(driver: AppiumDriver<*>): BaseScreen(driver), ContactDetailsScreen {


    @iOSFindBy(accessibility = "Edit")
    private var editContactButton: IOSElement? = null


    override fun clickEditContactButton(): EditContactScreen {
        editContactButton!!.click()
        return EditContactScreenIOS(driver!!)
    }

    override fun checkOrganisationField(title: String, contactName: String): ContactDetailsScreen {
        val companyTitle: MobileElement = driver!!
                .findElementByXPath(
                        "//XCUIElementTypeStaticText[@name=\"$contactName\"]/following-sibling::XCUIElementTypeStaticText"
                )
        assertEquals(companyTitle.text.trim(), title, "Actual company $title not equals ${companyTitle.text}")
        return this
    }

}
