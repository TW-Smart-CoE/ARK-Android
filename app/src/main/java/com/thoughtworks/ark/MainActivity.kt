package com.thoughtworks.ark

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.thoughtworks.ark.core.utils.ExternalFileUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        Log.i("storage1", "判断SD卡是否被挂载 " + ExternalFileUtils.isSDCardMounted.toString())
        Log.i("storage1", "获取SD卡的根目录 " + ExternalFileUtils.sDCardBaseDir)
        Log.i("storage1", "获取SD卡的完整空间大小，返回MB " + ExternalFileUtils.sDCardSize.toString())
        Log.i("storage1", "获取SD卡的剩余空间大小 " + ExternalFileUtils.sDCardFreeSize.toString())
        Log.i("storage1", "获取SD卡的可用空间大小 " + ExternalFileUtils.sDCardAvailableSize.toString())
        Log.i("storage1", "获取文件 " + ExternalFileUtils.loadFileFromSDCard(""))


    }
}