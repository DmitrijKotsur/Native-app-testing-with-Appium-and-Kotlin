package screen.ios

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.ios.IOSElement
import io.appium.java_client.pagefactory.iOSFindBy
import io.appium.java_client.pagefactory.iOSXCUITFindBy
import screen.BaseScreen
import screen.ContactDetailsScreen
import screen.ContactsListScreen
import screen.EditContactScreen

class ContactsListScreenIOS(driver: AppiumDriver<*>): BaseScreen(driver), ContactsListScreen {


    @iOSFindBy(accessibility = "Search")
    private var searchField: IOSElement? = null

    @iOSFindBy(accessibility = "Add")
    private var addContactButton: IOSElement? = null

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeCell' AND visible==1 ")
    private var contactsListItems: List<MobileElement>? = null

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND visible==1")
    private var noResultsStub: IOSElement? = null


    override fun searchContactByName(name: String): ContactsListScreen {
        searchField!!.setValue(name)
        return this
    }

    override fun clickOnContact(name: String): ContactDetailsScreen {
        findContactInListView(name)!!.click()
        return ContactDetailsScreenIOS(driver!!)
    }

    override fun checkContactExist(name: String): Boolean {
        searchContactByName(name)
        if (findContactInListView(name) != null) {
            return true
        }
        return false
    }

    override fun clickNewContact(): EditContactScreen {
        addContactButton!!.click()
        return EditContactScreenIOS(driver!!)
    }

    private fun findContactInListView(name: String): MobileElement? {
        return driver!!.findElement(
                MobileBy.iOSNsPredicateString(
                        "type == 'XCUIElementTypeCell' AND visible==1 AND name CONTAINS '$name'"
                ))
    }

}