package screen

interface ContactDetailsScreen {

    fun clickEditContactButton(): EditContactScreen
    fun checkOrganisationField(title: String, contactName: String): ContactDetailsScreen

}