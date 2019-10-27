package screen.ios

import io.appium.java_client.AppiumDriver
import io.appium.java_client.ios.IOSElement
import io.appium.java_client.pagefactory.iOSFindBy
import org.testng.Assert.assertTrue
import org.testng.AssertJUnit.assertEquals
import screen.BaseScreen
import screen.ContactDetailsScreen
import screen.EditContactScreen

class EditContactScreenIOS(driver: AppiumDriver<*>): BaseScreen(driver), EditContactScreen {


    @iOSFindBy(accessibility = "Done")
    private var editDoneButton: IOSElement? = null

    @iOSFindBy(accessibility = "First name")
    private var editTextFirstName: IOSElement? = null

    @iOSFindBy(accessibility = "Last name")
    private var editTextLastName: IOSElement? = null

    @iOSFindBy(accessibility = "Company")
    private var editTextContactCompany: IOSElement? = null

    @iOSFindBy(accessibility = "Clear text")
    private var clearEditTextCompanyButton: IOSElement? = null


    override fun clearCompanyField(companyName: String): EditContactScreen {
        editTextContactCompany!!.click()
        clearEditTextCompanyButton!!.click()
        return this
    }

    override fun fillCompanyField(companyName: String): EditContactScreen {
        editTextContactCompany!!.setValue("Arrival")
        return this
    }

    override fun clickSaveButton(): ContactDetailsScreen {
        assertTrue(editDoneButton!!.isEnabled, " 'Done' button is not enabled")
        editDoneButton!!.click()
        return ContactDetailsScreenIOS(driver!!)
    }

    override fun createNewContact(fullName: String): ContactDetailsScreen {
        val firstName = fullName.split(" ")[0]
        val lastName = fullName.split(" ")[1]

        editTextFirstName!!.setValue(firstName)
        editTextLastName!!.setValue(lastName)
        editDoneButton!!.click()

        return ContactDetailsScreenIOS(driver!!)
    }

}


