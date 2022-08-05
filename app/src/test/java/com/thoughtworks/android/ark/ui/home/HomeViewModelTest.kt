package com.thoughtworks.android.ark.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.thoughtworks.android.ark.MainCoroutineRule
import com.thoughtworks.android.ark.getOrAwaitValue
import com.thoughtworks.android.ark.ui.network.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun shouldLoadDataWhenHomeViewModelCreated() = runTest {
        // given
        val repository = mockk<HomeRepository>()
        val userRepo = mockk<UserRepository>()
        coEvery { repository.loadData() } returns "TestData"
        val homeViewModel = HomeViewModel(repository, userRepo)

        // then
        val result = homeViewModel.text.getOrAwaitValue()
        assertThat(result).isEqualTo("TestData")
    }
}