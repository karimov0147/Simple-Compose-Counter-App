package com.example.composesquare

import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val repository = MainRepository()

    var clickedLiveData = MutableLiveData<Int>(0)

    fun onClicked(){
       clickedLiveData.value = repository.incrementCounter()
    }



}