package com.thoughtworks.android.ark.ui.assistdemo

import android.content.Context
import androidx.core.content.edit
import com.thoughtworks.android.ark.ui.assistdemo.LocalConfigManagerFactory.Companion.CONFIG_KEY
import com.thoughtworks.android.ark.ui.assistdemo.LocalConfigManagerFactory.Companion.PREFS_TAG
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext

interface ConfigManager {
    fun saveSomeConfig(data: String)
    fun getSomeConfig(): String
}

@AssistedFactory
interface LocalConfigManagerFactory {
    companion object {
        const val PREFS_TAG = "tag"
        const val CONFIG_KEY = "someConfigKey"
    }

    fun create(
        @Assisted(PREFS_TAG) prefsTag: String,
        @Assisted(CONFIG_KEY) configKey: String
    ): LocalConfigManager
}


class LocalConfigManager @AssistedInject constructor(
    @ApplicationContext private val context: Context,
    @Assisted(PREFS_TAG) private val prefsTag: String,
    @Assisted(CONFIG_KEY) private val configKey: String
) : ConfigManager {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(prefsTag, Context.MODE_PRIVATE)
    }

    override fun saveSomeConfig(data: String) {
        sharedPreferences.edit {
            putString(configKey, data)
        }
    }

    override fun getSomeConfig(): String {
        return sharedPreferences.getString(configKey, "") ?: ""
    }
}