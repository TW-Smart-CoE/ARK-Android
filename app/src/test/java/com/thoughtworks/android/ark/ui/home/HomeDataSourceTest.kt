package com.thoughtworks.android.ark.ui.home

import com.google.common.truth.Truth.assertThat
import com.thoughtworks.android.ark.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeDataSourceTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun testGetData() = runTest {
        val dataSource = HomeDataSource(mainCoroutineRule.testDispatcher)
        val result = dataSource.getData()
        assertThat(result).isEqualTo("Test data")
    }
}