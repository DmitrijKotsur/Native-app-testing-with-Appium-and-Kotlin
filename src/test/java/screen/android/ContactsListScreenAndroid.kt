package screen.android

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.ios.IOSElement
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.support.FindAll
import org.openqa.selenium.support.FindBy
import screen.BaseScreen
import screen.ContactDetailsScreen
import screen.ContactsListScreen
import screen.EditContactScreen
import java.util.logging.Logger

class ContactsListScreenAndroid(driver: AppiumDriver<*>): BaseScreen(driver), ContactsListScreen {


    @AndroidFindBy(id = "com.android.contacts:id/floating_action_button")
    private var addContactButton: AndroidElement? = null

    @AndroidFindBy(id = "com.android.contacts:id/menu_search")
    private var searchContactButton: AndroidElement? = null

    @AndroidFindBy(id = "com.android.contacts:id/search_view")
    private var searchField: AndroidElement? = null

    @AndroidFindBy(id = "com.android.contacts:id/totalContactsText")
    private var noResultStub: AndroidElement? = null


    override fun searchContactByName(name: String): ContactsListScreenAndroid {
        searchContactButton!!.click()
        searchField!!.setValue(name)
        return this
    }

    override fun clickOnContact(name: String): ContactDetailsScreen {
        findContactInListView(name)!!.click()
        return ContactDetailsScreenAndroid(driver!!)
    }

    override fun clickNewContact(): EditContactScreen {
        addContactButton!!.click()
        return EditContactScreenAndroid(driver!!)
    }

    override fun checkContactExist(name: String): Boolean {
        var isExist = false
        if (searchContactButton != null) {
            searchContactButton!!.click()
            searchField!!.setValue(name)
            if (noResultStub == null) {
                isExist = true
            }
            // Double click back btn for FAB 'add new contact' became visible
            driver!!.navigate().back()
            return isExist
        }
        return false
    }

    private fun findContactInListView(name: String): MobileElement? {
        val result = driver!!.findElementByXPath("//android.widget.TextView[@content-desc=\"$name\"]")
        return result
    }

}
