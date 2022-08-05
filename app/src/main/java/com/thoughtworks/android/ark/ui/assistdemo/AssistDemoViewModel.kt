package com.thoughtworks.android.ark.ui.assistdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@AssistedFactory
interface AssistDemoViewModelFactory {
    fun create(@Assisted userId: String): AssistDemoViewModel
}

class AssistDemoViewModel @AssistedInject constructor(
    @Assisted private val userId: String,
    private val repository: AssistDemoRepository
) : ViewModel() {

    private val _text = liveData {
        emit(repository.loadData(userId))
    }

    val text: LiveData<String> = _text
}