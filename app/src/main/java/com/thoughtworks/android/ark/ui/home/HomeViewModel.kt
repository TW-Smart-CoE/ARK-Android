package com.thoughtworks.android.ark.ui.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.thoughtworks.demo.RemoteDataApi
import com.thoughtworks.demo.UserRepository
import com.thoughtworks.network.client.RetrofitClient
import com.thoughtworks.network.entity.RetrofitResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _text = liveData {
        emit(repository.loadData())
    }

    val text: LiveData<String> = _text

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            UserRepository(RemoteDataApi(RetrofitClient())).getData().collect {
                val result = when(it) {
                    is RetrofitResponse.Loading -> "Loading"
                    is RetrofitResponse.Success<*> -> "Success"
                    else -> "Error"
                }
                Log.e("tag", result)
            }
        }
    }
}