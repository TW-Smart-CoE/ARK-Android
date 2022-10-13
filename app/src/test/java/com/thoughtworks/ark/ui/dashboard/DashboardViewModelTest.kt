package com.thoughtworks.ark.ui.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.thoughtworks.ark.MainCoroutineRule
import com.thoughtworks.ark.ui.dashboard.data.repository.DashboardRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun shouldLoadDataWhenDashboardViewModelCreated() = runTest {
        // given
        val repository = mockk<DashboardRepository>()
        coEvery { repository.loadData() } returns flowOf("TestData")
        val viewModel = DashboardViewModel(repository)

        // when
        val item = viewModel.uiState.value

        // then
        assertThat(item).isNotNull()
        assertThat(item.label).isEqualTo("TestData")
    }
}