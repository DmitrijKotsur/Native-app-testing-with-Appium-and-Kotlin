package screen.android

import io.appium.java_client.AppiumDriver
import screen.BaseScreen
import screen.ContactDetailsScreen
import screen.EditContactScreen

class ContactDetailsScreenAndroid(driver: AppiumDriver<*>): BaseScreen(driver), ContactDetailsScreen {

    override fun clickEditContactButton(): EditContactScreen {
        return EditContactScreenAndroid(driver!!)
    }

    override fun checkOrganisationField(title: String, contactName: String): ContactDetailsScreen {
        return this
    }

}
