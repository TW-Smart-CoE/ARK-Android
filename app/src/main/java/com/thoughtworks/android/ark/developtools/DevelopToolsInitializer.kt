package com.thoughtworks.android.ark.developtools

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import com.thoughtworks.android.ark.R
import com.thoughtworks.android.core.utils.isDevEnvironment

class DevelopToolsInitializer {
    fun init(context: Context) {
        if (isDevEnvironment()) {
            createShortcuts(context)
        }
    }

    private fun createShortcuts(context: Context) {
        val shortcutManager = context.getSystemService(ShortcutManager::class.java)
        val shortcutInfo = mutableListOf<ShortcutInfo>()
        shortcutInfo.add(createDevelopToolsShortcut(context))

        if (shortcutManager != null) {
            shortcutManager.dynamicShortcuts = shortcutInfo
        }
    }

    private fun createDevelopToolsShortcut(context: Context): ShortcutInfo {
        return ShortcutInfo.Builder(context, ID_DEVELOP_TOOLS)
            .setShortLabel(LABEL_DEVELOP_TOOLS)
            .setLongLabel(LABEL_DEVELOP_TOOLS)
            .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher_round))
            .setIntent(
                Intent().apply {
                    action = Intent.ACTION_VIEW
                    component = ComponentName(context, DevelopToolsActivity::class.java)
                }
            )
            .build()
    }

    companion object {
        const val ID_DEVELOP_TOOLS = "develop_tools"
        const val LABEL_DEVELOP_TOOLS = "Develop Tools"
    }
}