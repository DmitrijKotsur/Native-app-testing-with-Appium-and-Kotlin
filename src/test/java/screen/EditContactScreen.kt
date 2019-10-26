package screen

interface EditContactScreen {

    fun clearCompanyField(companyName: String): EditContactScreen
    fun fillCompanyField(companyName: String): EditContactScreen
    fun clickSaveButton(): ContactDetailsScreen
    fun createNewContact(fullName: String): ContactDetailsScreen

}