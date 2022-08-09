package com.thoughtworks.android.ark.ui.home

import androidx.lifecycle.*
import com.thoughtworks.android.ark.ui.network.UserRepository
import com.thoughtworks.android.core.network.entity.RetrofitResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val userRepo: UserRepository
) : ViewModel() {

    private val _text = liveData {
        emit(repository.loadData())
    }

    val text: LiveData<String> = _text

    private val _state = MutableLiveData("")
    val state:LiveData<String>
        get() = _state

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            _state.postValue("Loading")
            userRepo.getData().collect {
                _state.postValue(when(it) {
                    is RetrofitResponse.Loading -> "Loading"
                    is RetrofitResponse.Success -> it.data.data?.get(0).toString()
                    is RetrofitResponse.Error -> it.errorMsg
                    else -> "unknown"
                })
            }
        }
    }
}