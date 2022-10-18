package com.thoughtworks.ark.sample.coroutines

import com.google.common.truth.Truth
import com.thoughtworks.android.core.testing.util.MainCoroutineRule
import com.thoughtworks.ark.sample.coroutines.data.repository.CoroutinesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutinesViewModelTest {
    @get:Rule
    val mainCoroutinesRule = MainCoroutineRule()

    @Test
    fun shouldLoadDataWhenCreated() = runTest {
        // given
        val repository = mockk<CoroutinesRepository>()
        coEvery { repository.loadData() } returns flowOf("TestData")
        val viewModel = CoroutinesViewModel(repository)

        // when
        val item = viewModel.uiState.value

        // then
        Truth.assertThat(item).isNotNull()
        Truth.assertThat(item.label).isEqualTo("TestData")
    }
}