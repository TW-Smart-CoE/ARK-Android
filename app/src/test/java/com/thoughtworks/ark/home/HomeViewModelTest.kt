package com.thoughtworks.ark.home

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeViewModelTest {
    @Test
    fun shouldInitialHomeWhenCreated() {
        // given
        val viewModel = HomeViewModel()

        // when
        val item = viewModel.uiState.value

        // then
        assertThat(item.label).isEqualTo("This is home Fragment")
    }
}