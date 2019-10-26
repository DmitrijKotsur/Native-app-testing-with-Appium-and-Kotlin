package screen

interface ContactsListScreen {

    fun searchContactByName(name: String): ContactsListScreen
    fun clickOnContact(name: String): ContactDetailsScreen
    fun checkContactExist(name: String): Boolean
    fun clickNewContact(): EditContactScreen
}