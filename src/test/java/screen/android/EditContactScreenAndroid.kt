package screen.android

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.support.FindBy
import screen.BaseScreen
import screen.ContactDetailsScreen
import screen.EditContactScreen

class EditContactScreenAndroid(driver: AppiumDriver<*>): BaseScreen(driver), EditContactScreen {

    @FindBy(xpath = "//android.widget.EditText[@text=\"Name\"]")
    private var editNameField: AndroidElement? = null

    @AndroidFindBy(id = "com.android.contacts:id/menu_save")
    private var editDoneButton: AndroidElement? = null

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/" +
            "android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/" +
            "android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.TextView")
    private val moreFieldsButton: AndroidElement? = null


    override fun createNewContact(fullName: String): ContactDetailsScreen {
        editNameField!!.setValue(fullName)
        editDoneButton!!.click()
        return ContactDetailsScreenAndroid(driver!!)
    }

    override fun fillCompanyField(companyName: String): EditContactScreen {
        moreFieldsButton!!.click()
        driver!!.findElementByXPath(
                "//android.widget.EditText[@text=\"Company\"]"
        ).setValue(companyName)
        editDoneButton!!.click()
        return this
    }

    override fun clickSaveButton(): ContactDetailsScreen {
        editDoneButton!!.click()
        return ContactDetailsScreenAndroid(driver!!)
    }

    override fun clearCompanyField(companyName: String): EditContactScreen {
        var element = driver!!.findElementByXPath(
                "//android.widget.EditText[@text=\"$companyName\"]"
        )
        element.click()
        editDoneButton!!.click()

        return this
    }
}
