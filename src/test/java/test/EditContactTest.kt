package test

import org.junit.*
import test.data.contactCompany
import test.data.contactName

class EditContactTest: BaseTest() {


    @Before
    fun setUp() {
        if (!contactsListScreen.checkContactExist(contactName)) {
            contactsListScreen
                    .clickNewContact()
                    .createNewContact(contactName)
        }
    }

    @After
    fun tearDown() {
        reopenApp()
        contactsListScreen
                .searchContactByName(contactName)
                .clickOnContact(contactName)
                .clickEditContactButton()
                .clearCompanyField()
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
