package com.thoughtworks.ark.deeplink.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.thoughtworks.ark.Schemes
import com.thoughtworks.ark.deeplink.DeeplinkSchemeInterceptor
import com.thoughtworks.ark.router.Router

class DeeplinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseScheme(intent)
    }

    @SuppressLint("MissingSuperCall")
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        parseScheme(intent)
    }

    private fun parseScheme(intent: Intent?) {
        val uri = (intent?.data?.toString() ?: "").toUri()
        val destScheme = uri.getQueryParameter(KEY_DEST)
        if (!destScheme.isNullOrEmpty()) {
            Router.scheme(destScheme)
                .addInterceptor(DeeplinkSchemeInterceptor())
                .route(
                    this,
                    onError = {
                        Router.scheme(Schemes.MAIN).route(this)
                    }
                )
        } else {
            Router.scheme(Schemes.MAIN).route(this)
        }
        finish()
    }

    companion object {
        const val KEY_DEST = "dest"
    }
}