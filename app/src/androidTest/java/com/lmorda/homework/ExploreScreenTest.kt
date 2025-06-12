package com.lmorda.homework

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.lmorda.homework.domain.model.mockDomainData
import com.lmorda.homework.ui.explore.ExploreContract
import com.lmorda.homework.ui.explore.ExploreScreen
import com.lmorda.homework.ui.shared.UiTestTags
import org.junit.Rule
import org.junit.Test

class ExploreScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testExploreScreenList() {
        composeTestRule.setContent {
            ExploreScreen(
                state = ExploreContract.State.Loaded(
                    vehicles = mockDomainData.records,
                    nextCursor = null,
                    nameLike = null,
                ),
                push = {},
                onNavigateToDetails = {},
                showContacts = false,
            )
        }

        composeTestRule.onNodeWithText("Vehicle-001").assertIsDisplayed()
        composeTestRule.onNodeWithText("Vehicle-002").assertIsDisplayed()
        composeTestRule.onNodeWithText("Vehicle-003").assertIsDisplayed()
    }

    @Test
    fun testExploreScreenShowContactsIcon() {
        composeTestRule.setContent {
            ExploreScreen(
                state = ExploreContract.State.Loaded(
                    vehicles = mockDomainData.records,
                    nextCursor = null,
                    nameLike = null,
                ),
                push = {},
                onNavigateToDetails = {},
                showContacts = true,
            )
        }

        composeTestRule.onNodeWithTag(UiTestTags.SHOW_ICON).assertIsDisplayed()
    }

    // TODO: Add more UI tests
}
