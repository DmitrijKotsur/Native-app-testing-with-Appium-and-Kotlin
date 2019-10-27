package test

import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import test.data.contactCompany
import test.data.contactName

class EditContactTest: BaseTest() {

    @BeforeClass
    fun setUp() {
        if (!contactsListScreen.checkContactExist(contactName)) {
            contactsListScreen
                    .clickNewContact()
                    .createNewContact(contactName)
        }
    }

    @AfterClass
    fun tearDown() {
        reopenApp()
        contactsListScreen
                .searchContactByName(contactName)
                .clickOnContact(contactName)
                .clickEditContactButton()
                .clearCompanyField(contactCompany)
    }

    @Test
    fun editContactCompanyTest() {
        reopenApp()
        contactsListScreen
                .searchContactByName(contactName)
                .clickOnContact(contactName)
                .clickEditContactButton()
                .fillCompanyField(contactCompany)
                .clickSaveButton()
                .checkOrganisationField(contactCompany, contactName)
    }

}
