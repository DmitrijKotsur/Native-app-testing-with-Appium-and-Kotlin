package screen.android

import io.appium.java_client.AppiumDriver
import screen.BaseScreen
import screen.ContactDetailsScreen
import screen.EditContactScreen

class EditContactScreenAndroid(driver: AppiumDriver<*>): BaseScreen(driver), EditContactScreen {

    override fun createNewContact(fullName: String): ContactDetailsScreen {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fillCompanyField(companyName: String): EditContactScreen {
        return this
    }

    override fun clickSaveButton(): ContactDetailsScreen {
        return ContactDetailsScreenAndroid(driver!!)
    }

    override fun clearCompanyField(): EditContactScreen {
        return this
    }
}