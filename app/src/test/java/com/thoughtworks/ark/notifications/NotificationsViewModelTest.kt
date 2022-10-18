package com.thoughtworks.ark.notifications

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class NotificationsViewModelTest {
    @Test
    fun shouldInitialNotificationsWhenCreated() {
        // given
        val viewModel = NotificationsViewModel()

        // when
        val item = viewModel.state

        // then
        assertThat(item.label).isEqualTo("This is notifications Fragment")
    }
}