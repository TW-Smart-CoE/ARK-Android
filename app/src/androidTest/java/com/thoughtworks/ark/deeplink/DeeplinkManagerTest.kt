package com.thoughtworks.ark.deeplink

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import java.net.URLEncoder

@RunWith(AndroidJUnit4::class)
class DeeplinkManagerTest {

    @Test
    fun shouldReturnEmptyStringWhenOriginSchemeIsEmpty() {
        val result = DeeplinkManager.parseDestScheme("")

        assertThat(result).isEmpty()
    }

    @Test
    fun shouldReturnEmptyStringWhenOriginSchemeWithoutDestParameter() {
        val result = DeeplinkManager.parseDestScheme("test://app")

        assertThat(result).isEmpty()
    }

    @Test
    fun shouldReturnDestSchemeWhenOriginSchemeWithDestParameter() {
        val destValue = "test://home"
        val result = DeeplinkManager.parseDestScheme("test://app?dest=$destValue")

        assertThat(result).isEqualTo(destValue)
    }

    @Test
    fun shouldReturnDecodedDestSchemeWhenOriginSchemeWithEncodedDestParameter() {
        val originDestValue = "test://home"
        val encodedDestValue = URLEncoder.encode(originDestValue, Charsets.UTF_8.name())
        val result = DeeplinkManager.parseDestScheme("test://app?dest=${encodedDestValue}")

        assertThat(result).isEqualTo(originDestValue)
    }
}