package com.thoughtworks.android.ark.developtools.assistdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AssistDemoFragment : Fragment() {

    @Inject
    lateinit var configManagerFactory: LocalConfigManagerFactory

    private val configManager by lazy {
        configManagerFactory.create("tag", "key")
    }

    private val userId: String = "123"

    @Inject
    lateinit var assistDemoViewModelFactory: AssistDemoViewModelFactory

    private val assistDemoViewModel by lazy { assistDemoViewModelFactory.create(userId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configManager.saveString("test")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    AssistDemoContent()
                }
            }
        }
    }

    @Composable
    fun AssistDemoContent() {
        Column {
            val text by assistDemoViewModel.text.observeAsState("")
            Text(text = configManager.getString())
            Text(text = text)
        }
    }
}