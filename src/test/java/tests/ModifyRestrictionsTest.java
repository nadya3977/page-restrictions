package tests;

import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PublishedPage;
import pages.RestrictionsPopUp;
import static org.junit.Assert.*;

public class ModifyRestrictionsTest extends BaseTest {

    @Test
    public void myFirstTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLogin();
        HomePage homePage = loginPage.login("nadya.v.yusupova@gmail.com", "63977Attl");
       // driver.get("https://nvyu.atlassian.net/wiki/spaces/TESTSPACE/pages/65575/bla2");
        PublishedPage page = homePage.navigateToPublishedPage("https://nvyu.atlassian.net/wiki/spaces/TESTSPACE/pages/65575/bla2");

        //initial check

        Thread.sleep(2000);
        assertTrue(page.isRestrictionButtonDisplayed());
        RestrictionsPopUp popup= page.clickRestrictionButton();
        assertTrue(popup.isEditRestrictionsDropDownDisplayed());
        assertTrue(popup.isCancelButtonEnabled());

        // remove all restrictions/

        popup.setPermissions("Anyone can view and edit");

        //check that restrictions are set to "Anyone can view and edit"
        Thread.sleep(3000);
        assertTrue(page.isRestrictionButtonDisplayed());
        popup=page.clickRestrictionButton();
        assertEquals("Anyone can view and edit",popup.checkSelectedPermissions());

        //set new permissions
        page=popup.setUserRestrictions("n5","Viewing and editing restricted","Can view and edit");

        //check new permissions as admin user
        Thread.sleep(3000);
        assertTrue(page.isRestrictionButtonDisplayed());
        popup= page.clickRestrictionButton();
        popup.isUserAdded("n5");
        assertEquals("Can view and edit",popup.checkAddedUsePermissions("n5"));
        popup.clickCancelButton();

        //login with added user and check that he can access the page

        Thread.sleep(2000);
        homePage.logOut();

        Thread.sleep(2000);
        loginPage.login("n5@mailforspam.com", "123P@ssword");
        homePage.navigateToPublishedPage("https://nvyu.atlassian.net/wiki/spaces/TESTSPACE/pages/65575/bla2");
        assertEquals("bla2",page.title());

        //login with user who was not added - not able to access the page

        Thread.sleep(2000);
        homePage.logOut();
        Thread.sleep(2000);
        loginPage.login("n4@mailforspam.com", "123P@ssword");
        homePage.navigateToPublishedPage("https://nvyu.atlassian.net/wiki/spaces/TESTSPACE/pages/65575/bla2");
        assertTrue(page.isRestrictedTitleShown());
    }
}
