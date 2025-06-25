package com.lmorda.homework

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.lmorda.homework.domain.model.mockContactsDomainDataPage1
import com.lmorda.homework.ui.contacts.ContactsContract
import com.lmorda.homework.ui.contacts.ContactsScreen
import com.lmorda.homework.ui.shared.UiTestTags.LOADING_INDICATOR
import com.lmorda.homework.ui.shared.UiTestTags.LOADING_NEXT_PAGE_INDICATOR
import org.junit.Rule
import org.junit.Test

class ContactsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testContactsScreenList() {
        composeTestRule.setContent {
            ContactsScreen(
                onBack = { },
                state = ContactsContract.State.Loaded(
                    contacts = mockContactsDomainDataPage1,
                    nextCursor = null,
                ),
                push = { },
            )
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

    @Test
    fun testContactsLoadingRefreshState() {
        composeTestRule.setContent {
            ContactsScreen(
                onBack = { },
                state = ContactsContract.State.LoadingRefresh,
                push = { },
            )
        }

        composeTestRule.onNodeWithText("Lou Morda").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag(LOADING_INDICATOR).assertIsDisplayed()
    }

    @Test
    fun testContactsLoadingNextPageState() {
        composeTestRule.setContent {
            ContactsScreen(
                onBack = { },
                state = ContactsContract.State.LoadingRefresh,
                push = { },
            )
        }

        composeTestRule.onNodeWithText("Lou Morda").assertIsDisplayed()
        composeTestRule.onNodeWithTag(LOADING_NEXT_PAGE_INDICATOR).assertIsDisplayed()
    }
}
