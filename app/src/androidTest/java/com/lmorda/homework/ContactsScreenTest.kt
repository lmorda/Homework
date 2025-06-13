package com.lmorda.homework

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.lmorda.homework.ui.contacts.ContactsScreen
import org.junit.Rule
import org.junit.Test

class ContactsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testContactsScreenList() {
        composeTestRule.setContent {
            ContactsScreen(onBack = {})
        }

        composeTestRule.onNodeWithText("Lou Morda").assertIsDisplayed()
        composeTestRule.onNodeWithText("lmorda81@gmail.com").assertIsDisplayed()
        composeTestRule.onNodeWithText("Added Mar 05, 2025").assertIsDisplayed()

        composeTestRule.onNodeWithText("Kate Morda").assertIsDisplayed()
        composeTestRule.onNodeWithText("Kate.Morda@gmail.com").assertIsDisplayed()
        composeTestRule.onNodeWithText("Added Mar 06, 2025").assertIsDisplayed()

        composeTestRule.onNodeWithText("Jane Doe").assertIsDisplayed()
        composeTestRule.onNodeWithText("jane.doe@example.com").assertIsDisplayed()
        composeTestRule.onNodeWithText("Added Jan 15, 2025").assertIsDisplayed()

        composeTestRule.onNodeWithText("John Smith").assertIsDisplayed()
        composeTestRule.onNodeWithText("john.smith@example.com").assertIsDisplayed()
        composeTestRule.onNodeWithText("Added Feb 10, 2025").assertIsDisplayed()

        composeTestRule.onNodeWithText("Emily Johnson").assertIsDisplayed()
        composeTestRule.onNodeWithText("emily.johnson@example.com").assertIsDisplayed()
        composeTestRule.onNodeWithText("Added Apr 01, 2025").assertIsDisplayed()
    }

}
