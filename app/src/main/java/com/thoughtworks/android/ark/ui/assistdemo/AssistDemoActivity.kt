package com.thoughtworks.android.ark.ui.assistdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.android.ark.R
import com.thoughtworks.android.ark.databinding.ActivityAssistDemoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssistDemoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAssistDemoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.root, AssistDemoFragment())
            .commit()
    }
}