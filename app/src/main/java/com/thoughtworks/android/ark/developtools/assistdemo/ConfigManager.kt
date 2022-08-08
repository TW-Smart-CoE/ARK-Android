package com.thoughtworks.android.ark.developtools.assistdemo

import android.content.Context
import androidx.core.content.edit
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext

interface ConfigManager {
    fun saveString(data: String)
    fun getString(): String
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
    @Assisted(LocalConfigManagerFactory.PREFS_TAG) private val prefsTag: String,
    @Assisted(LocalConfigManagerFactory.CONFIG_KEY) private val configKey: String
) : ConfigManager {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(prefsTag, Context.MODE_PRIVATE)
    }

    override fun saveString(data: String) {
        sharedPreferences.edit {
            putString(configKey, data)
        }
    }

    override fun getString(): String {
        return sharedPreferences.getString(configKey, "") ?: ""
    }
}