package com.lmorda.homework

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lmorda.homework.domain.repository.DataRepository
import com.lmorda.homework.domain.featureflag.FeatureFlag
import com.lmorda.homework.domain.featureflag.FeatureFlagRepository
import com.lmorda.homework.domain.model.mockDomainData
import com.lmorda.homework.domain.usecase.GetVehiclePageUseCase
import com.lmorda.homework.ui.explore.ExploreContract
import com.lmorda.homework.ui.explore.ExploreViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
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
class ExploreViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private val dataRepository: DataRepository = mockk()

    private val featureFlagRepository: FeatureFlagRepository = mockk()

    private val getVehiclePageUseCase: GetVehiclePageUseCase = mockk()

    private lateinit var viewModel: ExploreViewModel

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

        every {
            featureFlagRepository.isFeatureEnabled(FeatureFlag.ShowContacts)
        } returns flowOf(true)

        viewModel = ExploreViewModel(
            dataRepository = dataRepository,
            featureFlagRepository = featureFlagRepository,
            getVehiclePageUseCase = getVehiclePageUseCase,
        )

        assertEquals(ExploreContract.State.Initial, viewModel.state.value)

        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(viewModel.showContacts.first(), true)
    }

    @Test
    fun `get vehicle page use case called state on next page`() = runTest {
        // TODO: Add unit test to verify use case is getting called correctly
    }
}
