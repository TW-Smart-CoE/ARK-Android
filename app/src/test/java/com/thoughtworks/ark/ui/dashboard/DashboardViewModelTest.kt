package com.thoughtworks.ark.ui.dashboard

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DashboardViewModelTest {
    @Test
    fun shouldInitialDashboardWhenCreated() {
        // given
        val viewModel = DashboardViewModel()

        // when
        val item = viewModel.uiState.value

        // then
        assertThat(item.label).isEqualTo("This is dashboard Fragment")
    }
}