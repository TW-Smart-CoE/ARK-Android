package com.thoughtworks.ark.deeplink.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.ark.Schemes
import com.thoughtworks.ark.deeplink.DeeplinkManager
import com.thoughtworks.ark.deeplink.DeeplinkSchemeInterceptor
import com.thoughtworks.ark.router.Router

class DeeplinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseScheme(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        parseScheme(intent)
    }

    private fun parseScheme(intent: Intent?) {
        val originScheme = intent?.dataString ?: ""
        val destScheme = DeeplinkManager.parseDestScheme(originScheme)
        if (destScheme.isNotEmpty()) {
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
}