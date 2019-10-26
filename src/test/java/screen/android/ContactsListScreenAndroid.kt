package screen.android

import io.appium.java_client.AppiumDriver
import screen.BaseScreen
import screen.ContactDetailsScreen
import screen.ContactsListScreen
import screen.EditContactScreen

class ContactsListScreenAndroid(driver: AppiumDriver<*>): BaseScreen(driver), ContactsListScreen {


    override fun searchContactByName(name: String): ContactsListScreenAndroid {
        return this
    }


    override fun clickOnContact(name: String): ContactDetailsScreen {
        return ContactDetailsScreenAndroid(driver!!)
    }


    override fun clickNewContact(): EditContactScreen {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun checkContactExist(name: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}