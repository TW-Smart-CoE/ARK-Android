package com.thoughtworks.ark.deeplink

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import java.net.URLEncoder

@RunWith(AndroidJUnit4::class)
class DeeplinkManagerTest {

    @Test
    fun should_return_empty_string_when_origin_scheme_is_empty() {
        val result = DeeplinkManager.parseDestScheme("")

        assertThat(result).isEmpty()
    }

    @Test
    fun should_return_empty_string_when_origin_scheme_without_dest_parameter() {
        val result = DeeplinkManager.parseDestScheme("test://app")

        assertThat(result).isEmpty()
    }

    @Test
    fun should_return_dest_scheme_when_origin_scheme_with_dest_parameter() {
        val destValue = "test://home"
        val result = DeeplinkManager.parseDestScheme("test://app?dest=$destValue")

        assertThat(result).isEqualTo(destValue)
    }

    @Test
    fun should_return_decoded_dest_scheme_when_origin_scheme_with_encoded_dest_parameter() {
        val originDestValue = "test://home"
        val encodedDestValue = URLEncoder.encode(originDestValue, Charsets.UTF_8.name())
        val result = DeeplinkManager.parseDestScheme("test://app?dest=${encodedDestValue}")

        assertThat(result).isEqualTo(originDestValue)
    }
}