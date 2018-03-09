package geb.mobile.android.specification

import geb.mobile.GebMobileBaseSpec
// import geb.mobile.android.activities.HomeScreenActivity
// import geb.mobile.android.activities.CameraActivity
// import geb.mobile.android.activities.FirstTimeLoginActivity
// import geb.mobile.android.activities.AlbumCreationActivity
// import geb.mobile.android.activities.TakeImageActivity
// import geb.mobile.android.activities.ViewAllImageActivity
// import geb.mobile.android.activities.ChromSigninActivity
// import geb.mobile.android.activities.AccessPermissionActivity
// import geb.mobile.android.activities.RedirectActivity
// import geb.mobile.android.activities.SettingsActivity
// import geb.mobile.android.activities.ViewAllPhotosActivity
import geb.mobile.android.activities.WikisearchActivity

import spock.lang.Stepwise


/**
 * Sample test case for android app on Wiki native app:
 * TODO: rename activity to match the app's current activity
 */
@Stepwise
class GebMobileAutomationTestWithPagesSpec extends GebMobileBaseSpec {

// This is a sample test case:
    def "open test-app and enter search string "() {
        given: "I land on Home screen"
        at WikisearchActivity

        when: "I click on the search button"
        searchButton.click()

        and: "I type in search string and scroll down"
        searchText = "BrowserStack"
        swipeAction

        then: "I should see results"
        assert resultList.size() > 0
    }


//     def "0. Set screen lock PIN in settings"() {
//         given: "I am in device Settings"
//         at SettingsActivity

//         when: "I select device lock tab"
//         lockTab.click()
//         sleep(500)

//         and: "I pick a type of device lock"
//         lockType.click()
//         sleep(500)
//         pinOptions.click()
//         sleep(500)

//         and: "I enter a devie password"
//         pinField = "123456"
//         sleep(500)
//         nextButton.click()

//         and: "I reenter device password to confirm"
//         pinField = "123456"
//         nextButton.click()

//         then: "I should be able to open the app"
//         switchToAPP

//     }

//     def "1. First time login to the app"() {
//         given: "I open the app"
//         at FirstTimeLoginActivity

//         when: "I click on the auth button"
//         authenticateButton.click()
//         sleep(500)

//         and: "I type in password to unlock and continue"
// //        this is the device password
//         passwordField = "123456"
//         sleep(500)
//         nextButton.click()
//         sleep(500)

//         and: "I see the camera access request message and allow"
//         at AccessPermissionActivity
//         allowAccessButton.click()
//         sleep(1000)

//         then: "I should see the homepage with create album button and no existing album"
//         at HomeScreenActivity
//         createAlbumButton
//         assert albums.size() < 1
//     }
    
//     def "2.0 Go to create album details"() {
//         given: "I am at the create album page"
//         at HomeScreenActivity

//         when: "I click on the create button"
//         createAlbumButton.click()

//         then: "I should go to Details page"
//         at AlbumCreationActivity
//         addImageButton
//     }

//     def "2.1 Create empty album"() {
//         given: "I am at the Details page"
//         at AlbumCreationActivity

//         when: "I click on the back button"
//         backButton.click()

//         and: "I go back to Albums page and create one more album"
//         at HomeScreenActivity
//         createAlbumButton.click()

//         and: "I am at the Details page of a new album and go back"
//         at AlbumCreationActivity
//         backButton.click()
//         sleep(1000)

//         then: "I should see two empty albums created"
//         at HomeScreenActivity
//         assert albums.size() >= 1
//         createAlbumButton.click()
//     }


//     def "2.2 Create album with photos"() {
//         given: "I am at the create album page"
//         at AlbumCreationActivity

//         when: "I click on the add image button"
//         addImageButton.click()

//         and: "I give permission to access the camera"
//         at AccessPermissionActivity
//         allowAccessButton.click()
//         sleep(3000)

// //TODO: setup the variable to pass:
//         and: "I take three pics and go back"
//         at TakeImageActivity
//         shutterButton.click()
//         sleep(500)
//         shutterButton.click()
//         sleep(500)
//         shutterButton.click()
//         sleep(500)
//         backButton.click()
//         sleep(500)

//         Then: "I should see the two pics on Details page"
//         at AlbumCreationActivity
//         assert photos.size() >= 1

//     }

//     def "3.0 Manage photos in Details page"() {
//         given: "I am at the create album page"
//         at AlbumCreationActivity

//         when: "I delete a photo"
//         addImageButton.click()

//         and: "I confirm to delete"
//         at AccessPermissionActivity
//         allowAccessButton.click()
//         sleep(3000)

//         Then: "I should see two pics left"
//         at AlbumCreationActivity
//         assert photos.size() == 2

//     }

//     def "3.1 Manage photos by going to Photos page"() {
//         given: "I am at the create album page"
//         at AlbumCreationActivity

//         when: "I click on view all photo button"
//         viewAllImagesButton.click()

//         then: "I should be in Photos page"
//         at ViewAllPhotosActivity
//     }

//     def "3.2 Manage photos in Photos page"() {
//         given: "I am at the Photos page"
//         at ViewAllPhotosActivity

//         when: "I click on select button"
//         selectPhotoButton.click()
        
//         and: "I select some photos"
//         selectSinglePhotoButtons[1].click()

//         and: "I delete the photo and go back"
//         mainDeleteButton.click()
//         sleep(1000)
//         confirmDeletePhotosButton.click()
//         sleep(500)
//         backToDetailsButton.click()

//         Then: "I should see only one pic left"
//         at AlbumCreationActivity
//         assert photos.size() == 1
//     }

//     def "2.3 Create album with text info and upload"() {
//         Given: "I am at the create album page"
//         at AlbumCreationActivity

//         When: "I enter Name and Comment for the album"
//         nameField = "Test name"
//         commentField = "Test comment"

//         And: "I upload the album"
//         uploadButton.click()
//         sleep(1000)

//         then: "I should be asked to provide device lock info"
//         at RedirectActivity
//         loginButton
//     }

    // The rest will be tested manually
}
