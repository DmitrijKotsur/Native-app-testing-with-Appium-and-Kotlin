package screen

interface EditContactScreen {

    fun clearCompanyField(): EditContactScreen
    fun fillCompanyField(companyName: String): EditContactScreen
    fun clickSaveButton(): ContactDetailsScreen
    fun createNewContact(fullName: String): ContactDetailsScreen

}