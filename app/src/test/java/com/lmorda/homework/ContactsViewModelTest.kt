package com.lmorda.homework

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.lmorda.homework.domain.repository.DataRepository
import com.lmorda.homework.domain.model.mockContactsDomainData
import com.lmorda.homework.domain.model.mockDomainData
import com.lmorda.homework.ui.contacts.ContactsContract.Event.OnRefresh
import com.lmorda.homework.ui.contacts.ContactsContract.State.Initial
import com.lmorda.homework.ui.contacts.ContactsContract.State.Loaded
import com.lmorda.homework.ui.contacts.ContactsContract.State.LoadingRefresh
import com.lmorda.homework.ui.contacts.ContactsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ContactsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private val dataRepository: DataRepository = mockk()

    private lateinit var viewModel: ContactsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loading state on init`() = runTest {
        coEvery {
            dataRepository.getVehicles(
                startCursor = null,
                sort = null,
                filter = null,
            )
        } returns mockDomainData

        viewModel = ContactsViewModel()
        assertEquals(Initial, viewModel.state.value)
    }

    @Test
    fun `on refresh emits Initial then LoadingRefresh then Loaded`() = runTest {
        val viewModel = ContactsViewModel()

        viewModel.state.test {
            assertEquals(Initial, awaitItem())
            viewModel.push(OnRefresh)
            assertEquals(LoadingRefresh, awaitItem())
            val loaded = awaitItem()
            assertEquals(
                Loaded(
                    contacts = mockContactsDomainData[1].contacts,
                    nextCursor = mockContactsDomainData[1].nextCursor
                ),
                loaded
            )
            cancelAndIgnoreRemainingEvents()
        }
    }


    // TODO: Add more unit tests
}
