package com.thoughtworks.ark.ui.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.thoughtworks.ark.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    fun shouldInitialDashboardWhenCreated() = runTest {
        // given
        val viewModel = DashboardViewModel()

        // when
        val item = viewModel.uiState.value

        // then
        assertThat(item.label).isEqualTo("This is dashboard Fragment")
    }
}